# Work talk with a nitwit

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.nitwit.followup`](#conversations-scene-work-nitwit-followup)
- [`conversations.scene.work.nitwit.i_noticed.active.respond`](#conversations-scene-work-nitwit-i-noticed-active-respond)
- [`conversations.scene.work.nitwit.i_noticed.succeeded.respond`](#conversations-scene-work-nitwit-i-noticed-succeeded-respond)
- [`conversations.scene.work.nitwit.left_out.active.respond`](#conversations-scene-work-nitwit-left-out-active-respond)
- [`conversations.scene.work.nitwit.left_out.succeeded.respond`](#conversations-scene-work-nitwit-left-out-succeeded-respond)
- [`conversations.scene.work.nitwit.refused_job.blocked.respond`](#conversations-scene-work-nitwit-refused-job-blocked-respond)
- [`conversations.scene.work.nitwit.refused_job.succeeded.respond`](#conversations-scene-work-nitwit-refused-job-succeeded-respond)
- [`conversations.topic.work.nitwit.craft.respond`](#conversations-topic-work-nitwit-craft-respond)
- [`conversations.topic.work.nitwit.followup`](#conversations-topic-work-nitwit-followup)
- [`conversations.topic.work.nitwit.future.respond`](#conversations-topic-work-nitwit-future-respond)
- [`conversations.topic.work.nitwit.respond`](#conversations-topic-work-nitwit-respond)
- [`conversations.topic.work.nitwit.risk.respond`](#conversations-topic-work-nitwit-risk-respond)
- [`conversations.topic.work.nitwit.task.respond`](#conversations-topic-work-nitwit-task-respond)
- [`conversations.topic.work.nitwit.village.respond`](#conversations-topic-work-nitwit-village-respond)

---

## `conversations.scene.work.nitwit.followup`

**Reached from 10 route(s):** `conversations.scene.work.nitwit.i_noticed.active.respond` / `take_it_seriously`; `conversations.scene.work.nitwit.i_noticed.active.respond` / `ask_who_to_tell`; `conversations.scene.work.nitwit.i_noticed.succeeded.respond` / `note_the_shift`; `conversations.scene.work.nitwit.left_out.active.respond` / `ask_what_she_would_say`; `conversations.scene.work.nitwit.left_out.active.respond` / `offer_to_stand_with_her`; `conversations.scene.work.nitwit.left_out.succeeded.respond` / `note_she_did_it`; `conversations.scene.work.nitwit.refused_job.blocked.respond` / `ask_if_she_can`; `conversations.scene.work.nitwit.refused_job.blocked.respond` / `offer_to_ask_for_her`; `conversations.scene.work.nitwit.refused_job.blocked.respond` / `say_it_is_unfair`; `conversations.scene.work.nitwit.refused_job.succeeded.respond` / `ask_how_it_changed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.nitwit.i_noticed.active.explained` — e.g. "The guard, if I catch her walking rather than standing. Standing still, she is being official at me. Walking, she just listens."
- `conversations.scene.work.nitwit.i_noticed.active.reported` — e.g. "%2$s, between the second and third bell, and there were two sets of prints and only one of them came back."
- `conversations.scene.work.nitwit.i_noticed.succeeded.acknowledged` — e.g. "First. I know how small that sounds. I am not going to pretend it was small to me."
- `conversations.scene.work.nitwit.left_out.active.answered` — e.g. "That the bucket rope is the problem and not the well. I am there at first light every day and I am the only one who sees it wet."
- `conversations.scene.work.nitwit.left_out.active.steadied` — e.g. "Behind, not instead of. If you say it for me they will hear you, and I will still be the person who cannot say things."
- `conversations.scene.work.nitwit.left_out.succeeded.acknowledged` — e.g. "I did. With somebody standing there, which I want counted, because doing it alone would have been a different thing entirely."
- `conversations.scene.work.nitwit.refused_job.blocked.declined` — e.g. "Then it is your word they trust, and next time I am back at the start with a smaller start."
- `conversations.scene.work.nitwit.refused_job.blocked.explained` — e.g. "Yes. Slower than the baker's boy and I have never once spilled any, which is a trade the baker would take."
- `conversations.scene.work.nitwit.refused_job.blocked.steadied` — e.g. "It is not. I am careful about that word, though, because most of them are being nice and nice is harder to argue with."
- `conversations.scene.work.nitwit.refused_job.succeeded.answered` — e.g. "I turned up at the same hour for a month and did it badly and then less badly, where they could see."


```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.nitwit.followup   [15 chars]
    en  Something more?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of being written off?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.nitwit.*` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.followup.ask_more` — accepted phrasings: "whats the hardest part of being written off"; "what is the hardest part of being written off"; "hardest thing about people writing you off"
  - the message must contain one of: `hardest`, `written`
  - scored words: `hardest`(1.8), `written`(1.8), `whats`(0.8), `part`(0.8), `being`(0.8), `off`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.followup.ask_more   [45 chars]
    en  What's the hardest part of being written off?
    >>  ............................................
    pt  Qual é a parte mais difícil de ser descartada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of being written off?"
       spoken on: conversations.scene.work.nitwit.followup, button `ask_more`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.hard`: the villager explains. Subject `work.nitwit.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.nitwit.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.nitwit.hard/1   [74 chars]
    en  Some days. It's a better coat than the alternative, which is being pitied.
    >>  ............................................
    pt  Alguns dias. É um casaco melhor que a alternativa, que é ter pena de mim.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.hard/2   [87 chars]
    en  It wears. Then somebody laughs properly rather than politely and it's fine again, %1$s.
    >>  ............................................
    pt  Cansa. Aí alguém ri de verdade em vez de rir por educação e fica tudo bem de novo, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your day."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.nitwit.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.followup.leave   [27 chars]
    en  I'll leave you to your day.
    >>  ............................................
    pt  Vou deixar você com o seu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your day."
       spoken on: conversations.scene.work.nitwit.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave; conversations.topic.work.nitwit.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.nitwit.leave/1   [70 chars]
    en  They've been drifting badly all morning. Someone has to say something.
    >>  ............................................
    pt  Elas andaram mal a manhã toda. Alguém tem que falar alguma coisa.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.leave/2   [55 chars]
    en  Aye. They're doing an eight today, %1$s. Look up later.
    >>  ............................................
    pt  É. Hoje elas estão nota oito, %1$s. Olhe pra cima mais tarde.
    >>  ............................................
```

---


## `conversations.scene.work.nitwit.i_noticed.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.nitwit.i_noticed.active` — e.g. "There was %2$s yesterday and I told two people and both of them said thank you in the voice."


```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.nitwit.i_noticed.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.active.respond   [18 chars]
    en  Something you saw.
    >>  ............................................
    pt  Uma coisa que você viu.
    >>  ............................................
```


### Button `take_it_seriously` — "Tell me exactly what you saw."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.nitwit.i_noticed.active` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.i_noticed.active.take_it_seriously` — accepted phrasings: "tell me exactly what you saw"; "tell me exactly what you saw"; "walk me through what you noticed"
  - the message must contain one of: `exactly`, `noticed`, `saw`
  - scored words: `exactly`(1.8), `noticed`(1.8), `saw`(1.8), `tell`(0.8), `walk`(0.8), `through`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.active.respond.take_it_seriously
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.i_noticed.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.active.respond.take_it_seriously   [29 chars]
    en  Tell me exactly what you saw.
    >>  ............................................
    pt  Me conte exatamente o que você viu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.nitwit.noticed.believed`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, respect +3  _(recorded under topic `work.nitwit.what_i_notice`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.nitwit.i_noticed"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.active.reported
WHO    VILLAGER — what the player reads after pressing "Tell me exactly what you saw."
       spoken on: conversations.scene.work.nitwit.i_noticed.active.respond, button `take_it_seriously`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.i_noticed.active.reported`: the villager reports. Subject `work.nitwit.what_i_notice`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.active.reported/1   [106 chars]
    en  %2$s, between the second and third bell, and there were two sets of prints and only one of them came back.
    >>  ............................................
    pt  %2$s, entre o segundo e o terceiro sino, e havia duas trilhas de pegadas e só uma voltou.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.active.reported/2   [129 chars]
    en  Right. I have been holding this for a day and a half and you are the first person to ask for the details rather than the summary.
    >>  ............................................
    pt  Certo. Estou segurando isso há um dia e meio e você é a primeira pessoa a pedir os detalhes em vez do resumo.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.active.reported/3   [110 chars]
    en  %2$s. I can show you where. It will take ten minutes and if I am wrong I will say so out loud in front of you.
    >>  ............................................
    pt  %2$s. Posso te mostrar onde. Leva dez minutos e, se eu estiver errada, eu digo em voz alta na sua frente.
    >>  ............................................
```


### Button `ask_who_to_tell` — "Who would actually listen?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.i_noticed.active` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.i_noticed.active.ask_who_to_tell` — accepted phrasings: "who would actually listen"; "who would actually listen"; "which person in the village would listen"
  - the message must contain one of: `listen`, `person`
  - scored words: `listen`(1.8), `person`(1.8), `who`(0.8), `actually`(0.8), `which`(0.8), `village`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.active.respond.ask_who_to_tell
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.i_noticed.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.active.respond.ask_who_to_tell   [26 chars]
    en  Who would actually listen?
    >>  ............................................
    pt  Quem de fato escutaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.what_i_notice`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.nitwit.i_noticed"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.active.explained
WHO    VILLAGER — what the player reads after pressing "Who would actually listen?"
       spoken on: conversations.scene.work.nitwit.i_noticed.active.respond, button `ask_who_to_tell`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.i_noticed.active.explained`: the villager explains. Subject `work.nitwit.what_i_notice`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.active.explained/1   [127 chars]
    en  The guard, if I catch her walking rather than standing. Standing still, she is being official at me. Walking, she just listens.
    >>  ............................................
    pt  A guarda, se eu pegar ela caminhando em vez de parada. Parada, ela está sendo oficial comigo. Caminhando, ela só escuta.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.active.explained/2   [108 chars]
    en  Nobody, first time. Anybody, third time, if I say the same words in the same order and do not get flustered.
    >>  ............................................
    pt  Ninguém, na primeira vez. Qualquer um, na terceira, se eu disser as mesmas palavras na mesma ordem e não me atrapalhar.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.active.explained/3   [133 chars]
    en  That is the actual problem and it has taken me years to see it. It is not that I am wrong. It is that I have to be right three times.
    >>  ............................................
    pt  É esse o problema de verdade e levei anos para enxergar. Não é que eu esteja errada. É que eu preciso estar certa três vezes.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.nitwit.i_noticed.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.i_noticed.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.active.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.nitwit.i_noticed.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave; conversations.topic.work.nitwit.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.nitwit.i_noticed.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.nitwit.i_noticed.succeeded` — e.g. "%2$s turned out to matter. The guard went and looked and came back and said so where people could hear."


```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.nitwit.i_noticed.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.respond   [23 chars]
    en  That thing you noticed.
    >>  ............................................
    pt  Aquilo que você reparou.
    >>  ............................................
```


### Button `note_the_shift` — "They asked you first."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.nitwit.i_noticed.succeeded` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.i_noticed.succeeded.note_the_shift` — accepted phrasings: "they asked you first"; "they asked you first"; "you were the first person they asked"
  - the message must contain one of: `asked`, `first`
  - scored words: `asked`(1.8), `first`(1.8), `were`(0.8), `person`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.respond.note_the_shift
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.i_noticed.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.respond.note_the_shift   [21 chars]
    en  They asked you first.
    >>  ............................................
    pt  Perguntaram a você primeiro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.nitwit.what_i_notice`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.nitwit.i_noticed"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "They asked you first."
       spoken on: conversations.scene.work.nitwit.i_noticed.succeeded.respond, button `note_the_shift`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.i_noticed.succeeded.acknowledged`: the villager accepts. Subject `work.nitwit.what_i_notice`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.acknowledged/1   [82 chars]
    en  First. I know how small that sounds. I am not going to pretend it was small to me.
    >>  ............................................
    pt  Primeiro. Eu sei o quanto isso soa pequeno. Não vou fingir que foi pequeno para mim.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.acknowledged/2   [97 chars]
    en  Once. I am not building anything on one, and I did notice, and I will notice if it happens again.
    >>  ............................................
    pt  Uma vez. Não estou construindo nada em cima de uma vez, e eu reparei, e vou reparar se acontecer de novo.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.acknowledged/3   [141 chars]
    en  Thank you for saying the thing rather than making it a lesson about believing in myself. I did not need the lesson. I needed somebody to ask.
    >>  ............................................
    pt  Obrigada por dizer a coisa em vez de transformar numa lição sobre acreditar em mim. Eu não precisava da lição. Precisava que alguém perguntasse.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.nitwit.i_noticed.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.i_noticed.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.nitwit.i_noticed.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave; conversations.topic.work.nitwit.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.nitwit.left_out.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.nitwit.left_out.active` — e.g. "There was %2$s and nobody told me it was happening, and it was about a thing I use every day."


```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.nitwit.left_out.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.active.respond   [12 chars]
    en  The meeting.
    >>  ............................................
    pt  A reunião.
    >>  ............................................
```


### Button `ask_what_she_would_say` — "What would you have said?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.left_out.active` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.left_out.active.ask_what_she_would_say` — accepted phrasings: "what would you have said"; "what would you have said"; "what was your sentence going to be"
  - the message must contain one of: `said`, `sentence`
  - scored words: `said`(1.8), `sentence`(1.8), `going`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.active.respond.ask_what_she_would_say
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.left_out.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.active.respond.ask_what_she_would_say   [25 chars]
    en  What would you have said?
    >>  ............................................
    pt  O que você teria dito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, respect +2  _(recorded under topic `work.nitwit.being_talked_over`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.nitwit.left_out"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.active.answered
WHO    VILLAGER — what the player reads after pressing "What would you have said?"
       spoken on: conversations.scene.work.nitwit.left_out.active.respond, button `ask_what_she_would_say`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left_out.active.answered`: the villager explains. Subject `work.nitwit.being_talked_over`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.active.answered/1   [128 chars]
    en  That the bucket rope is the problem and not the well. I am there at first light every day and I am the only one who sees it wet.
    >>  ............................................
    pt  Que o problema é a corda do balde e não o poço. Estou lá todo dia ao amanhecer e sou a única que vê aquilo molhado.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.active.answered/2   [104 chars]
    en  One sentence. That is all I had. I had practised it, which is the part I would rather not have told you.
    >>  ............................................
    pt  Uma frase. Era tudo o que eu tinha. Eu tinha ensaiado, que é a parte que eu preferia não ter contado.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.active.answered/3   [115 chars]
    en  It does not matter now. It will matter again in the spring, and I will say it then, in the same words, standing up.
    >>  ............................................
    pt  Agora não importa. Vai importar de novo na primavera, e eu digo então, com as mesmas palavras, de pé.
    >>  ............................................
```


### Button `offer_to_stand_with_her` — "Say it again and I'll back you."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.nitwit.left_out.active` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.left_out.active.offer_to_stand_with_her` — accepted phrasings: "say it again and ill back you"; "say it again and i will back you"; "i will stand behind you when you say it"
  - the message must contain one of: `back`, `stand`, `again`
  - scored words: `back`(1.8), `stand`(1.8), `again`(1.8), `say`(0.8), `ill`(0.8), `behind`(0.8), `when`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.active.respond.offer_to_stand_with_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.left_out.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.active.respond.offer_to_stand_with_her   [31 chars]
    en  Say it again and I'll back you.
    >>  ............................................
    pt  Diga de novo e eu te apoio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.nitwit.meeting.backed`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, trust +3  _(recorded under topic `work.nitwit.being_talked_over`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.nitwit.left_out"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.active.steadied
WHO    VILLAGER — what the player reads after pressing "Say it again and I'll back you."
       spoken on: conversations.scene.work.nitwit.left_out.active.respond, button `offer_to_stand_with_her`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left_out.active.steadied`: the villager accepts. Subject `work.nitwit.being_talked_over`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.active.steadied/1   [118 chars]
    en  Behind, not instead of. If you say it for me they will hear you, and I will still be the person who cannot say things.
    >>  ............................................
    pt  Atrás, não no lugar. Se você disser por mim, eles vão ouvir você, e eu continuo sendo a pessoa que não consegue dizer as coisas.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.active.steadied/2   [104 chars]
    en  All right. Next time. And if they talk over me, wait, and then say you would like to hear the end of it.
    >>  ............................................
    pt  Está bem. Na próxima. E se falarem por cima de mim, espere, e depois diga que gostaria de ouvir o final.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.active.steadied/3   [95 chars]
    en  That is the useful version of help and almost nobody offers it. Everybody offers the other one.
    >>  ............................................
    pt  Essa é a versão útil da ajuda e quase ninguém oferece. Todo mundo oferece a outra.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.nitwit.left_out.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.left_out.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.active.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.nitwit.left_out.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave; conversations.topic.work.nitwit.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.nitwit.left_out.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.nitwit.left_out.succeeded` — e.g. "I said it standing up and somebody waited for me to finish, and then the rope got replaced."


```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.nitwit.left_out.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.succeeded.respond   [24 chars]
    en  The meeting, in the end.
    >>  ............................................
    pt  A reunião, no fim.
    >>  ............................................
```


### Button `note_she_did_it` — "You said it yourself."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.nitwit.left_out.succeeded` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.left_out.succeeded.note_she_did_it` — accepted phrasings: "you said it yourself"; "you said it yourself"; "you were the one who said it"
  - the message must contain one of: `yourself`, `said`
  - scored words: `yourself`(1.8), `said`(1.8), `were`(0.8), `one`(0.8), `who`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.succeeded.respond.note_she_did_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.left_out.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.succeeded.respond.note_she_did_it   [21 chars]
    en  You said it yourself.
    >>  ............................................
    pt  Você mesma disse.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.nitwit.being_talked_over`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.nitwit.left_out"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You said it yourself."
       spoken on: conversations.scene.work.nitwit.left_out.succeeded.respond, button `note_she_did_it`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left_out.succeeded.acknowledged`: the villager accepts. Subject `work.nitwit.being_talked_over`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.succeeded.acknowledged/1   [125 chars]
    en  I did. With somebody standing there, which I want counted, because doing it alone would have been a different thing entirely.
    >>  ............................................
    pt  Disse. Com alguém ali de pé, o que eu quero que conte, porque fazer sozinha teria sido outra coisa totalmente.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.succeeded.acknowledged/2   [90 chars]
    en  And the rope is new. That is the part I keep going back to. A sentence turned into a rope.
    >>  ............................................
    pt  E a corda é nova. É a parte à qual eu volto sempre. Uma frase virou uma corda.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.succeeded.acknowledged/3   [114 chars]
    en  Yes. And next time I will need less help, and the time after that probably less again, and that is the whole plan.
    >>  ............................................
    pt  Sim. E na próxima vou precisar de menos ajuda, e na seguinte provavelmente menos ainda, e é esse o plano inteiro.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.nitwit.left_out.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.left_out.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.succeeded.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.nitwit.left_out.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave; conversations.topic.work.nitwit.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.nitwit.refused_job.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.nitwit.refused_job.blocked` — e.g. "I asked %3$s if I could help with %2$s and got a laugh, and the laugh was meant kindly, which is worse."


```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.nitwit.refused_job.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond   [9 chars]
    en  The jobs.
    >>  ............................................
    pt  Os serviços.
    >>  ............................................
```


### Button `ask_if_she_can` — "Can you do it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.refused_job.blocked` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.refused_job.blocked.ask_if_she_can` — accepted phrasings: "can you do it"; "can you do it"; "are you able to do that job"
  - the message must contain one of: `able`, `job`, `do`
  - scored words: `able`(1.8), `job`(1.8), `do`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond.ask_if_she_can
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.refused_job.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond.ask_if_she_can   [14 chars]
    en  Can you do it?
    >>  ............................................
    pt  Você consegue fazer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +2  _(recorded under topic `work.nitwit.small_usefulness`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.nitwit.refused_job"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Can you do it?"
       spoken on: conversations.scene.work.nitwit.refused_job.blocked.respond, button `ask_if_she_can`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.refused_job.blocked.explained`: the villager explains. Subject `work.nitwit.small_usefulness`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.explained/1   [106 chars]
    en  Yes. Slower than the baker's boy and I have never once spilled any, which is a trade the baker would take.
    >>  ............................................
    pt  Consigo. Mais devagar que o menino do padeiro e nunca derramei nada, o que é uma troca que o padeiro aceitaria.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.explained/2   [105 chars]
    en  %2$s, yes. Some things no. I know the difference better than anybody, because I am the one who finds out.
    >>  ............................................
    pt  %2$s, sim. Algumas coisas não. Eu sei a diferença melhor que qualquer um, porque sou eu quem descobre.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.explained/3   [89 chars]
    en  Thank you for asking that instead of asking whether I mind. Everyone asks whether I mind.
    >>  ............................................
    pt  Obrigada por perguntar isso em vez de perguntar se eu me incomodo. Todo mundo pergunta se eu me incomodo.
    >>  ............................................
```


### Button `offer_to_ask_for_her` — "I could speak to them for you."

*stance family `practical_help` · tone `gentle` · outcome `resisted` · answers the beat(s) `work.nitwit.refused_job.blocked` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.refused_job.blocked.offer_to_ask_for_her` — accepted phrasings: "i could speak to them for you"; "i could speak to them for you"; "shall i put in a word for you"
  - the message must contain one of: `speak`, `word`
  - scored words: `speak`(1.8), `word`(1.8), `shall`(0.8), `put`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond.offer_to_ask_for_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.refused_job.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond.offer_to_ask_for_her   [30 chars]
    en  I could speak to them for you.
    >>  ............................................
    pt  Eu posso falar com eles por você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `work.nitwit.small_usefulness`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.nitwit.refused_job"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked.declined
WHO    VILLAGER — what the player reads after pressing "I could speak to them for you."
       spoken on: conversations.scene.work.nitwit.refused_job.blocked.respond, button `offer_to_ask_for_her`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.refused_job.blocked.declined`: the villager resists. Subject `work.nitwit.small_usefulness`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.declined/1   [91 chars]
    en  Then it is your word they trust, and next time I am back at the start with a smaller start.
    >>  ............................................
    pt  Aí é na sua palavra que eles confiam, e da próxima vez eu volto ao começo com um começo menor.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.declined/2   [118 chars]
    en  That is kind and I would rather you did not. I have watched people be spoken for and they never stop being spoken for.
    >>  ............................................
    pt  É gentil e eu preferia que não. Já vi gente falada por outros e nunca param de ser faladas por outros.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.declined/3   [91 chars]
    en  Ask me to help you with something instead. That is worth four of anybody putting in a word.
    >>  ............................................
    pt  Peça ajuda a mim em alguma coisa. Isso vale por quatro pessoas dando uma palavra.
    >>  ............................................
```


### Button `say_it_is_unfair` — "You deserve a fair chance at it."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.nitwit.refused_job.blocked` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.refused_job.blocked.say_it_is_unfair` — accepted phrasings: "you deserve a fair chance at it"; "that is unfair on you"; "you deserve a fair chance at it"
  - the message must contain one of: `unfair`, `deserve`, `chance`
  - scored words: `unfair`(1.8), `deserve`(1.8), `chance`(1.8), `fair`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond.say_it_is_unfair
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.refused_job.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond.say_it_is_unfair   [32 chars]
    en  You deserve a fair chance at it.
    >>  ............................................
    pt  Você merece uma chance justa nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +2  _(recorded under topic `work.nitwit.small_usefulness`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.nitwit.refused_job"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "You deserve a fair chance at it."
       spoken on: conversations.scene.work.nitwit.refused_job.blocked.respond, button `say_it_is_unfair`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.refused_job.blocked.steadied`: the villager accepts. Subject `work.nitwit.small_usefulness`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.steadied/1   [118 chars]
    en  It is not. I am careful about that word, though, because most of them are being nice and nice is harder to argue with.
    >>  ............................................
    pt  Não é. Mas eu sou cuidadosa com essa palavra, porque a maioria está sendo gentil, e gentileza é mais difícil de contestar.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.steadied/2   [118 chars]
    en  Thank you. I would rather be told it is unfair than told I am doing well, and I get told I am doing well a great deal.
    >>  ............................................
    pt  Obrigada. Prefiro que me digam que é injusto a que me digam que estou indo bem, e me dizem que estou indo bem o tempo todo.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.steadied/3   [134 chars]
    en  I have stopped keeping a list. The list did nothing for me except make me sour company, and sour company gets asked to help even less.
    >>  ............................................
    pt  Parei de fazer lista. A lista não fez nada por mim além de me deixar azeda, e companhia azeda é chamada para ajudar ainda menos.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.nitwit.refused_job.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.refused_job.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.nitwit.refused_job.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave; conversations.topic.work.nitwit.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.nitwit.refused_job.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.nitwit.refused_job.succeeded` — e.g. "I do %2$s now. Every day. Nobody announced it — they just stopped finding somebody else."


```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.nitwit.refused_job.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded.respond   [16 chars]
    en  The jobs, since.
    >>  ............................................
    pt  Os serviços, depois disso.
    >>  ............................................
```


### Button `ask_how_it_changed` — "How did they come round?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.nitwit.refused_job.succeeded` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.nitwit.refused_job.succeeded.ask_how_it_changed` — accepted phrasings: "how did they come round"; "how did they come round"; "what changed their minds about it"
  - the message must contain one of: `round`, `changed`
  - scored words: `round`(1.8), `changed`(1.8), `come`(0.8), `their`(0.8), `minds`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.succeeded.respond.ask_how_it_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.refused_job.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded.respond.ask_how_it_changed   [24 chars]
    en  How did they come round?
    >>  ............................................
    pt  Como eles mudaram de ideia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.small_usefulness`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.nitwit.refused_job"}
- Then opens: `conversations.scene.work.nitwit.followup`
- …where the player's next choices will be: "What's the hardest part of being written off?" | "I'll leave you to your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "How did they come round?"
       spoken on: conversations.scene.work.nitwit.refused_job.succeeded.respond, button `ask_how_it_changed`
       leaves the player on: conversations.scene.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.refused_job.succeeded.answered`: the villager explains. Subject `work.nitwit.small_usefulness`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded.answered/1   [100 chars]
    en  I turned up at the same hour for a month and did it badly and then less badly, where they could see.
    >>  ............................................
    pt  Apareci no mesmo horário por um mês e fiz mal, e depois menos mal, onde eles podiam ver.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded.answered/2   [143 chars]
    en  Somebody was ill. That is the honest answer — a gap opened and I was standing in it, and afterwards it would have been awkward to take it back.
    >>  ............................................
    pt  Alguém adoeceu. É a resposta honesta — abriu uma brecha e eu estava parada nela, e depois teria sido constrangedor tomar de volta.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded.answered/3   [121 chars]
    en  I stopped asking. Asking makes it a favour. Being there at the right time makes it a solution, and people like solutions.
    >>  ............................................
    pt  Parei de pedir. Pedir transforma em favor. Estar ali na hora certa transforma em solução, e as pessoas gostam de solução.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `work.nitwit.refused_job.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.nitwit.refused_job.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.nitwit.refused_job.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.topic.work.nitwit.craft.respond / leave; conversations.topic.work.nitwit.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.nitwit.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.nitwit.craft` — e.g. "I know every path out of this valley and which ones flood. Nobody taught me. I walked them."


```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.nitwit.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.nitwit.craft.respond   [21 chars]
    en  That's what I've got.
    >>  ............................................
    pt  É o que eu tenho.
    >>  ............................................
```


### Button `ask_paths` — "Which ones flood?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.craft` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.craft.ask_paths` — accepted phrasings: "which ones flood"
  - the message must contain one of: `flood`, `paths`, `tracks`
  - scored words: `flood`(1.5), `paths`(1.2), `tracks`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.craft.respond.ask_paths
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.craft.respond.ask_paths   [17 chars]
    en  Which ones flood?
    >>  ............................................
    pt  Quais alagam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.craft.ask_paths`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.craft.ask_paths
WHO    VILLAGER — what the player reads after pressing "Which ones flood?"
       spoken on: conversations.topic.work.nitwit.craft.respond, button `ask_paths`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.craft.ask_paths`: the villager explains. Subject `work.nitwit.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.craft.ask_paths/1   [77 chars]
    en  The low one past the mill, always. The east track only when the thaw is fast.
    >>  ............................................
    pt  A baixa depois do moinho, sempre. A trilha leste só quando o degelo é rápido.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.craft.ask_paths/2   [89 chars]
    en  Three of the five. I told the cartographer once and he wrote it down, which surprised me.
    >>  ............................................
    pt  Três das cinco. Falei pro cartógrafo uma vez e ele anotou, o que me surpreendeu.
    >>  ............................................
```


### Button `admire` — "That's real knowledge. It's just not written down."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.nitwit.craft` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.craft.admire` — accepted phrasings: "that's real knowledge. it's just not written down"
  - the message must contain one of: `knowledge`, `written`
  - scored words: `knowledge`(1.5), `written`(1.2), `real`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.craft.respond.admire   [50 chars]
    en  That's real knowledge. It's just not written down.
    >>  ............................................
    pt  Isso é conhecimento de verdade. Só não está escrito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.nitwit.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.nitwit.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.craft.admire
WHO    VILLAGER — what the player reads after pressing "That's real knowledge. It's just not written down."
       spoken on: conversations.topic.work.nitwit.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.craft.admire`: the villager accepts. Subject `work.nitwit.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.craft.admire/1   [86 chars]
    en  ...Written down. Yes. That's the whole difference, isn't it, and it's not a small one.
    >>  ............................................
    pt  ...Escrito. Sim. É toda a diferença, não é, e não é pequena.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.craft.admire/2   [89 chars]
    en  Nobody's put it that way to me. I've been told it's noticing, like that's a lesser thing.
    >>  ............................................
    pt  Ninguém colocou assim pra mim. Já me disseram que é reparar, como se fosse menor.
    >>  ............................................
```


### Button `ask_names` — "Who isn't speaking to whom?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.craft` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.craft.ask_names` — accepted phrasings: "who isn't speaking to whom"
  - the message must contain one of: `speaking`, `whom`, `feud`
  - scored words: `speaking`(1.5), `whom`(1.2), `feud`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.craft.respond.ask_names
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.craft.respond.ask_names   [27 chars]
    en  Who isn't speaking to whom?
    >>  ............................................
    pt  Quem não está se falando com quem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.craft.ask_names`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.craft.ask_names
WHO    VILLAGER — what the player reads after pressing "Who isn't speaking to whom?"
       spoken on: conversations.topic.work.nitwit.craft.respond, button `ask_names`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.craft.ask_names`: the villager explains. Subject `work.nitwit.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.craft.ask_names/1   [100 chars]
    en  I'll not say. Knowing it and spreading it are different, and I've been careful about that for years.
    >>  ............................................
    pt  Não vou dizer. Saber e espalhar são coisas diferentes, e eu tenho cuidado com isso há anos.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.craft.ask_names/2   [86 chars]
    en  Two households, over a fence line, since the spring. It'll mend. They always do, %1$s.
    >>  ............................................
    pt  Duas casas, por uma divisa, desde a primavera. Vai passar. Sempre passa, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the clouds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.nitwit.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.craft.respond.leave   [36 chars]
    en  I'll let you get back to the clouds.
    >>  ............................................
    pt  Vou deixar você voltar pras nuvens.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the clouds."
       spoken on: conversations.topic.work.nitwit.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.nitwit.followup`

**Reached from 20 route(s):** `conversations.scene.work.nitwit.followup` / `ask_more`; `conversations.topic.work.nitwit.craft.respond` / `ask_paths`; `conversations.topic.work.nitwit.craft.respond` / `admire`; `conversations.topic.work.nitwit.craft.respond` / `ask_names`; `conversations.topic.work.nitwit.future.respond` / `ask_post`; `conversations.topic.work.nitwit.future.respond` / `encourage`; `conversations.topic.work.nitwit.future.respond` / `ask_leaving`; `conversations.topic.work.nitwit.respond` / `ask_hard`; `conversations.topic.work.nitwit.respond` / `value`; `conversations.topic.work.nitwit.respond` / `challenge`; `conversations.topic.work.nitwit.respond` / `challenge`; `conversations.topic.work.nitwit.risk.respond` / `ask_carry` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.nitwit.challenge.landed` — e.g. "I could. I've watched every one of them and none of them wants me back."
- `conversations.work.prof.nitwit.challenge.stung` — e.g. "...If it were only wanting, %1$s, there'd be no nitwits anywhere."
- `conversations.work.prof.nitwit.craft.admire` — e.g. "...Written down. Yes. That's the whole difference, isn't it, and it's not a small one."
- `conversations.work.prof.nitwit.craft.ask_names` — e.g. "I'll not say. Knowing it and spreading it are different, and I've been careful about that for years."
- `conversations.work.prof.nitwit.craft.ask_paths` — e.g. "The low one past the mill, always. The east track only when the thaw is fast."
- `conversations.work.prof.nitwit.future.ask_leaving` — e.g. "The road wants watching and there's nobody else at the gate. That's a poor reason and it's mine."
- `conversations.work.prof.nitwit.future.ask_post` — e.g. "The gate. I'm already doing it. I'd only want somebody to write my name next to it."
- `conversations.work.prof.nitwit.future.encourage` — e.g. "...You'd have to say it where somebody hears. That's the whole of it and it's not much to ask."
- `conversations.work.prof.nitwit.hard` — e.g. "Some days. It's a better coat than the alternative, which is being pitied."
- `conversations.work.prof.nitwit.risk.ask_carry` — e.g. "Everything. They talk over me the way you talk over a chair, and I hear all of it."
- `conversations.work.prof.nitwit.risk.ask_forty` — e.g. "It is. And the waiting isn't the hard part; the deciding to keep watching the road is."
- `conversations.work.prof.nitwit.risk.sympathise` — e.g. "...No. It isn't. I've known that for thirty years and never had it said back to me."
- `conversations.work.prof.nitwit.task.ask_nothing` — e.g. "Not since I was young. They decided something about me and then they never revisited it."
- `conversations.work.prof.nitwit.task.ask_road` — e.g. "A cart with two people and a dog, and the dog wasn't theirs. It came back out alone."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.nitwit.followup   [24 chars]
    en  That's me, more or less.
    >>  ............................................
    pt  Sou eu, mais ou menos.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.nitwit.challenge.landed`, `work.nitwit.challenge.stung`, `work.nitwit.craft.admire`, `work.nitwit.craft.ask_names`, `work.nitwit.craft.ask_paths`, `work.nitwit.future.ask_leaving`, `work.nitwit.future.ask_post`, `work.nitwit.future.encourage`, `work.nitwit.hard`, `work.nitwit.risk.ask_carry`, `work.nitwit.risk.ask_forty`, `work.nitwit.risk.sympathise`, `work.nitwit.task.ask_nothing`, `work.nitwit.task.ask_road`, `work.nitwit.task.take_seriously`, `work.nitwit.value`, `work.nitwit.village.ask_mayor`, `work.nitwit.village.ask_raid`, `work.nitwit.village.say_thanks` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.nitwit.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `furniture`
  - scored words: `thought`(1.2), `furniture`(1.5), `nobody`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.nitwit.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.nitwit.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.nitwit.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.nitwit.thanks`: the villager accepts. Subject `work.nitwit.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.thanks/1   [65 chars]
    en  Nobody does. It's restful, mostly, being thought of as furniture.
    >>  ............................................
    pt  Ninguém pensa. É descansado, na maior parte, ser tratado como móvel.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.thanks/2   [74 chars]
    en  That's twice now you've said something I'll be turning over tonight, %1$s.
    >>  ............................................
    pt  É a segunda vez que você diz algo que eu vou remoer hoje à noite, %1$s.
    >>  ............................................
```


### Button `ask_more` — "What do you actually do all day?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.challenge.landed`, `work.nitwit.challenge.stung`, `work.nitwit.craft.admire`, `work.nitwit.craft.ask_names`, `work.nitwit.craft.ask_paths`, `work.nitwit.future.ask_leaving`, `work.nitwit.future.ask_post`, `work.nitwit.future.encourage`, `work.nitwit.hard`, `work.nitwit.risk.ask_carry`, `work.nitwit.risk.ask_forty`, `work.nitwit.risk.sympathise`, `work.nitwit.task.ask_nothing`, `work.nitwit.task.ask_road`, `work.nitwit.task.take_seriously`, `work.nitwit.value`, `work.nitwit.village.ask_mayor`, `work.nitwit.village.ask_raid`, `work.nitwit.village.say_thanks` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.nitwit.more` — accepted phrasings: "what do you actually do all day"
  - the message must contain one of: `actually`, `day`, `errands`
  - scored words: `actually`(1.2), `day`(1.0), `errands`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.followup.ask_more   [32 chars]
    en  What do you actually do all day?
    >>  ............................................
    pt  O que você faz o dia inteiro, na real?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.nitwit.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.more
WHO    VILLAGER — what the player reads after pressing "What do you actually do all day?"
       spoken on: conversations.topic.work.nitwit.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.nitwit.more`: the villager discloses. Subject `work.nitwit.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.more/1   [84 chars]
    en  Errands nobody assigns. The baker's water, the widow's shutters. It adds up quietly.
    >>  ............................................
    pt  Recados que ninguém manda fazer. A água do padeiro, as venezianas da viúva. Vai somando em silêncio.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.more/2   [77 chars]
    en  Watch. Listen. Carry things for people who don't ask. It's not nothing, %1$s.
    >>  ............................................
    pt  Observo. Escuto. Carrego coisas pra quem não pede. Não é nada, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. It adds up quietly, and quietly is the only way it's ever added up.
    >>  ............................................
    pt  Tarefas que ninguém designa. Soma em silêncio, e em silêncio é o único jeito que já somou.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with a name. Forty years of waiting to be asked is a long time, %1$s.
    >>  ............................................
    pt  Um posto com nome. Quarenta anos esperando ser perguntado é muito tempo, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. They get done and nobody notices, which suits the work well enough.
    >>  ............................................
    pt  Tarefas que ninguém designa. São feitas e ninguém repara, o que serve bem ao trabalho.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.nitwit.more/2
    en  A post, one day. The road wants watching whether anybody writes it down or not.
    >>  ............................................
    pt  Um posto, um dia. A estrada precisa de vigia, escrevam ou não.
    >>  ............................................
  confident.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water, the widow's shutters. It adds up quietly.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro, as venezianas da viúva. Soma em silêncio.
    >>  ............................................
  confident.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with a name on it. Gate, well, road — anything people say out loud.
    >>  ............................................
    pt  Um posto com nome. Portão, poço, estrada — qualquer coisa que as pessoas digam alto.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water, the widow's shutters. It adds up quietly.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro, as venezianas da viúva. Soma em silêncio.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with a name on it. Gate, well, road — anything people say out loud.
    >>  ............................................
    pt  Um posto com nome. Portão, poço, estrada — qualquer coisa que as pessoas digam alto.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water, the widow's shutters — small things, and they know.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro, as venezianas da viúva — coisinhas, e elas sabem.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with my name beside it. That's all. Somebody would only have to say it where others hear.
    >>  ............................................
    pt  Um posto com meu nome do lado. Só isso. Alguém só teria que dizer onde outros ouçam.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water, the widow's shutters — small things, and they know.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro, as venezianas da viúva — coisinhas, e elas sabem.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with my name beside it. That's all. Somebody would only have to say it where others hear.
    >>  ............................................
    pt  Um posto com meu nome do lado. Só isso. Alguém só teria que dizer onde outros ouçam.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water, the widow's shutters — small things, and they know.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro, as venezianas da viúva — coisinhas, e elas sabem.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with my name beside it. That's all. Somebody would only have to say it where others hear.
    >>  ............................................
    pt  Um posto com meu nome do lado. Só isso. Alguém só teria que dizer onde outros ouçam.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. It adds up quietly, and quietly is the only way it's ever added up.
    >>  ............................................
    pt  Tarefas que ninguém designa. Soma em silêncio, e em silêncio é o único jeito que já somou.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with a name. Forty years of waiting to be asked is a long time, %1$s.
    >>  ............................................
    pt  Um posto com nome. Quarenta anos esperando ser perguntado é muito tempo, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water, the widow's shutters. It adds up quietly.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro, as venezianas da viúva. Soma em silêncio.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with a name on it. Gate, well, road — anything people say out loud.
    >>  ............................................
    pt  Um posto com nome. Portão, poço, estrada — qualquer coisa que as pessoas digam alto.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water, the widow's shutters. It adds up quietly.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro, as venezianas da viúva. Soma em silêncio.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with a name on it. Gate, well, road — anything people say out loud.
    >>  ............................................
    pt  Um posto com nome. Portão, poço, estrada — qualquer coisa que as pessoas digam alto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water. The widow's shutters. Nobody counts them and I do.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro. As venezianas da viúva. Ninguém conta e eu conto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.nitwit.more/2
    en  A post. The gate, most likely. I'm already doing it; I'd only want it written down.
    >>  ............................................
    pt  Um posto. O portão, provavelmente. Eu já faço; eu só queria que fosse escrito.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. They get done and nobody notices, which suits the work well enough.
    >>  ............................................
    pt  Tarefas que ninguém designa. São feitas e ninguém repara, o que serve bem ao trabalho.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.nitwit.more/2
    en  A post, one day. The road wants watching whether anybody writes it down or not.
    >>  ............................................
    pt  Um posto, um dia. A estrada precisa de vigia, escrevam ou não.
    >>  ............................................
  odd.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water. The widow's shutters. Nobody counts them and I do.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro. As venezianas da viúva. Ninguém conta e eu conto.
    >>  ............................................
  odd.dialogue.conversations.work.prof.nitwit.more/2
    en  A post. The gate, most likely. I'm already doing it; I'd only want it written down.
    >>  ............................................
    pt  Um posto. O portão, provavelmente. Eu já faço; eu só queria que fosse escrito.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. They get done and nobody notices, which suits the work well enough.
    >>  ............................................
    pt  Tarefas que ninguém designa. São feitas e ninguém repara, o que serve bem ao trabalho.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.nitwit.more/2
    en  A post, one day. The road wants watching whether anybody writes it down or not.
    >>  ............................................
    pt  Um posto, um dia. A estrada precisa de vigia, escrevam ou não.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns! The baker's water, the widow's shutters. I'm practically a public service.
    >>  ............................................
    pt  Tarefas que ninguém designa! A água do padeiro, as venezianas da viúva. Sou praticamente serviço público.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.nitwit.more/2
    en  A post. Any post. Road-watch doesn't exist, so I invented it and I've been doing it for years.
    >>  ............................................
    pt  Um posto. Qualquer um. Vigia de estrada não existe, então eu inventei e faço há anos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns! The baker's water, the widow's shutters. I'm practically a public service.
    >>  ............................................
    pt  Tarefas que ninguém designa! A água do padeiro, as venezianas da viúva. Sou praticamente serviço público.
    >>  ............................................
  playful.dialogue.conversations.work.prof.nitwit.more/2
    en  A post. Any post. Road-watch doesn't exist, so I invented it and I've been doing it for years.
    >>  ............................................
    pt  Um posto. Qualquer um. Vigia de estrada não existe, então eu inventei e faço há anos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. They get done and nobody notices, which suits the work well enough.
    >>  ............................................
    pt  Tarefas que ninguém designa. São feitas e ninguém repara, o que serve bem ao trabalho.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.nitwit.more/2
    en  A post, one day. The road wants watching whether anybody writes it down or not.
    >>  ............................................
    pt  Um posto, um dia. A estrada precisa de vigia, escrevam ou não.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. It adds up quietly, and quietly is the only way it's ever added up.
    >>  ............................................
    pt  Tarefas que ninguém designa. Soma em silêncio, e em silêncio é o único jeito que já somou.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.nitwit.more/2
    en  A post with a name. Forty years of waiting to be asked is a long time, %1$s.
    >>  ............................................
    pt  Um posto com nome. Quarenta anos esperando ser perguntado é muito tempo, %1$s.
    >>  ............................................
  shy.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns. The baker's water. The widow's shutters. Nobody counts them and I do.
    >>  ............................................
    pt  Tarefas que ninguém designa. A água do padeiro. As venezianas da viúva. Ninguém conta e eu conto.
    >>  ............................................
  shy.dialogue.conversations.work.prof.nitwit.more/2
    en  A post. The gate, most likely. I'm already doing it; I'd only want it written down.
    >>  ............................................
    pt  Um posto. O portão, provavelmente. Eu já faço; eu só queria que fosse escrito.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns! The baker's water, the widow's shutters. I'm practically a public service.
    >>  ............................................
    pt  Tarefas que ninguém designa! A água do padeiro, as venezianas da viúva. Sou praticamente serviço público.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.nitwit.more/2
    en  A post. Any post. Road-watch doesn't exist, so I invented it and I've been doing it for years.
    >>  ............................................
    pt  Um posto. Qualquer um. Vigia de estrada não existe, então eu inventei e faço há anos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.nitwit.more/1
    en  Errands nobody assigns! The baker's water, the widow's shutters. I'm practically a public service.
    >>  ............................................
    pt  Tarefas que ninguém designa! A água do padeiro, as venezianas da viúva. Sou praticamente serviço público.
    >>  ............................................
  witty.dialogue.conversations.work.prof.nitwit.more/2
    en  A post. Any post. Road-watch doesn't exist, so I invented it and I've been doing it for years.
    >>  ............................................
    pt  Um posto. Qualquer um. Vigia de estrada não existe, então eu inventei e faço há anos.
    >>  ............................................
```

</details>


### Button `leave` — "Enjoy the clouds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.nitwit.challenge.landed`, `work.nitwit.challenge.stung`, `work.nitwit.craft.admire`, `work.nitwit.craft.ask_names`, `work.nitwit.craft.ask_paths`, `work.nitwit.future.ask_leaving`, `work.nitwit.future.ask_post`, `work.nitwit.future.encourage`, `work.nitwit.hard`, `work.nitwit.risk.ask_carry`, `work.nitwit.risk.ask_forty`, `work.nitwit.risk.sympathise`, `work.nitwit.task.ask_nothing`, `work.nitwit.task.ask_road`, `work.nitwit.task.take_seriously`, `work.nitwit.value`, `work.nitwit.village.ask_mayor`, `work.nitwit.village.ask_raid`, `work.nitwit.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.followup.leave   [17 chars]
    en  Enjoy the clouds.
    >>  ............................................
    pt  Aproveite as nuvens.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "Enjoy the clouds."
       spoken on: conversations.topic.work.nitwit.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.nitwit.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.nitwit.future` — e.g. "I'd take a post. Any post. Gate, well, road — something with a name that people say out loud."


```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.nitwit.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.nitwit.future.respond   [26 chars]
    en  That's what's ahead of me.
    >>  ............................................
    pt  É o que está à minha frente.
    >>  ............................................
```


### Button `ask_post` — "What post would you want?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.future` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.future.ask_post` — accepted phrasings: "what post would you want"
  - the message must contain one of: `post`, `gate`, `title`
  - scored words: `post`(1.5), `gate`(1.2), `title`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.future.respond.ask_post
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.future.respond.ask_post   [25 chars]
    en  What post would you want?
    >>  ............................................
    pt  Que posto você quereria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.future.ask_post`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.future.ask_post
WHO    VILLAGER — what the player reads after pressing "What post would you want?"
       spoken on: conversations.topic.work.nitwit.future.respond, button `ask_post`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.future.ask_post`: the villager explains. Subject `work.nitwit.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.future.ask_post/1   [83 chars]
    en  The gate. I'm already doing it. I'd only want somebody to write my name next to it.
    >>  ............................................
    pt  O portão. Já faço. Só queria que alguém escrevesse meu nome do lado.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.future.ask_post/2   [90 chars]
    en  Road-watch. It's the thing I'm best at and there is no such post, %1$s, so I invented one.
    >>  ............................................
    pt  Vigia de estrada. É no que eu sou melhor e não existe esse posto, %1$s, então eu inventei.
    >>  ............................................
```


### Button `encourage` — "Then I'll say your name next to it."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.nitwit.future` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.future.encourage` — accepted phrasings: "then i'll say your name next to it"
  - the message must contain one of: `name`, `beside`, `aloud`
  - scored words: `name`(1.5), `beside`(1.2), `aloud`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.future.respond.encourage   [35 chars]
    en  Then I'll say your name next to it.
    >>  ............................................
    pt  Então eu vou dizer seu nome do lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.nitwit.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.nitwit.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then I'll say your name next to it."
       spoken on: conversations.topic.work.nitwit.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.future.encourage`: the villager accepts. Subject `work.nitwit.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.future.encourage/1   [94 chars]
    en  ...You'd have to say it where somebody hears. That's the whole of it and it's not much to ask.
    >>  ............................................
    pt  ...Teria que dizer onde alguém ouça. É tudo, e não é muito pedir.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.future.encourage/2   [77 chars]
    en  Say it to the guard. He's not unkind, he's only never thought about it, %1$s.
    >>  ............................................
    pt  Diga ao guarda. Ele não é ruim, só nunca pensou nisso, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. I couldn't say it myself, which is why I've not.
    >>  ............................................
    pt  ...Onde alguém ouça. Eu mesmo não conseguiria dizer, por isso não disse.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. If it comes from you it isn't me begging.
    >>  ............................................
    pt  Diga ao guarda. Se vier de você não é eu implorando.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. Twelve years and nobody has said it where somebody hears.
    >>  ............................................
    pt  ...Onde alguém ouça. Doze anos e ninguém disse onde alguém ouvisse.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind. Most of them aren't; they're only busy.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau. Quase nenhum é; só estão ocupados.
    >>  ............................................
  confident.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears. That's the whole of it.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça. É tudo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears. That's the whole of it.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça. É tudo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears, %1$s. That's not much to ask.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça, %1$s. Não é muito pedir.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears, %1$s. That's not much to ask.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça, %1$s. Não é muito pedir.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears, %1$s. That's not much to ask.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça, %1$s. Não é muito pedir.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. I couldn't say it myself, which is why I've not.
    >>  ............................................
    pt  ...Onde alguém ouça. Eu mesmo não conseguiria dizer, por isso não disse.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. If it comes from you it isn't me begging.
    >>  ............................................
    pt  Diga ao guarda. Se vier de você não é eu implorando.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears. That's the whole of it.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça. É tudo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears. That's the whole of it.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça. É tudo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. That's all it is.
    >>  ............................................
    pt  ...Onde alguém ouça. É só isso.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. Twelve years and nobody has said it where somebody hears.
    >>  ............................................
    pt  ...Onde alguém ouça. Doze anos e ninguém disse onde alguém ouvisse.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind. Most of them aren't; they're only busy.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau. Quase nenhum é; só estão ocupados.
    >>  ............................................
  odd.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. That's all it is.
    >>  ............................................
    pt  ...Onde alguém ouça. É só isso.
    >>  ............................................
  odd.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. Twelve years and nobody has said it where somebody hears.
    >>  ............................................
    pt  ...Onde alguém ouça. Doze anos e ninguém disse onde alguém ouvisse.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind. Most of them aren't; they're only busy.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau. Quase nenhum é; só estão ocupados.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears! That's the whole of it, and it's nothing.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça! É tudo, e não é nada.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never once thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso nem uma vez.
    >>  ............................................
  playful.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears! That's the whole of it, and it's nothing.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça! É tudo, e não é nada.
    >>  ............................................
  playful.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never once thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso nem uma vez.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. Twelve years and nobody has said it where somebody hears.
    >>  ............................................
    pt  ...Onde alguém ouça. Doze anos e ninguém disse onde alguém ouvisse.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind. Most of them aren't; they're only busy.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau. Quase nenhum é; só estão ocupados.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. I couldn't say it myself, which is why I've not.
    >>  ............................................
    pt  ...Onde alguém ouça. Eu mesmo não conseguiria dizer, por isso não disse.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. If it comes from you it isn't me begging.
    >>  ............................................
    pt  Diga ao guarda. Se vier de você não é eu implorando.
    >>  ............................................
  shy.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...Where somebody hears. That's all it is.
    >>  ............................................
    pt  ...Onde alguém ouça. É só isso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears! That's the whole of it, and it's nothing.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça! É tudo, e não é nada.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never once thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso nem uma vez.
    >>  ............................................
  witty.dialogue.conversations.work.prof.nitwit.future.encourage/1
    en  ...You'd have to say it where somebody hears! That's the whole of it, and it's nothing.
    >>  ............................................
    pt  ...Você teria que dizer onde alguém ouça! É tudo, e não é nada.
    >>  ............................................
  witty.dialogue.conversations.work.prof.nitwit.future.encourage/2
    en  Say it to the guard. He's not unkind, he's only never once thought about it.
    >>  ............................................
    pt  Diga ao guarda. Ele não é mau, só nunca pensou nisso nem uma vez.
    >>  ............................................
```

</details>


### Button `ask_leaving` — "What holds you here?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.future` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.future.ask_leaving` — accepted phrasings: "what holds you here"
  - the message must contain one of: `holds`, `leaving`
  - scored words: `holds`(1.5), `leaving`(1.2), `here`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.future.respond.ask_leaving
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.future.respond.ask_leaving   [20 chars]
    en  What holds you here?
    >>  ............................................
    pt  O que te segura aqui?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.future.ask_leaving`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.future.ask_leaving
WHO    VILLAGER — what the player reads after pressing "What holds you here?"
       spoken on: conversations.topic.work.nitwit.future.respond, button `ask_leaving`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.future.ask_leaving`: the villager explains. Subject `work.nitwit.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.future.ask_leaving/1   [96 chars]
    en  The road wants watching and there's nobody else at the gate. That's a poor reason and it's mine.
    >>  ............................................
    pt  A estrada precisa de vigia e não tem mais ninguém no portão. É uma razão pobre e é minha.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.future.ask_leaving/2   [90 chars]
    en  Two households who'd not know each other had made up. Somebody has to notice things, %1$s.
    >>  ............................................
    pt  Duas casas que não saberiam que fizeram as pazes. Alguém tem que reparar nas coisas, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the clouds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.nitwit.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.future.respond.leave   [36 chars]
    en  I'll let you get back to the clouds.
    >>  ............................................
    pt  Vou deixar você voltar pras nuvens.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the clouds."
       spoken on: conversations.topic.work.nitwit.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.nitwit.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.nitwit` — e.g. "Work? Oh, I supervise. The clouds, mostly. Somebody has to make sure they keep moving."


```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.nitwit.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.nitwit.respond   [31 chars]
    en  That's the post, such as it is.
    >>  ............................................
    pt  É o cargo, tal como é.
    >>  ............................................
```


### Button `ask_hard` — "Does the joke wear thin?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.identity` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.hard` — accepted phrasings: "does the joke wear thin"
  - the message must contain one of: `joke`, `thin`, `tiring`
  - scored words: `joke`(1.5), `thin`(1.2), `tiring`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.respond.ask_hard   [24 chars]
    en  Does the joke wear thin?
    >>  ............................................
    pt  A piada não cansa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.nitwit.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.hard
WHO    VILLAGER — what the player reads after pressing "Does the joke wear thin?"
       spoken on: conversations.topic.work.nitwit.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.hard`: the villager explains. Subject `work.nitwit.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / ask_more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You notice more than the busy people do."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.nitwit.identity` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.value` — accepted phrasings: "you notice more than the busy people do"
  - the message must contain one of: `notice`, `observe`
  - scored words: `notice`(1.5), `observe`(1.5), `busy`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.respond.value   [40 chars]
    en  You notice more than the busy people do.
    >>  ............................................
    pt  Você repara mais que a gente ocupada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.nitwit.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.nitwit.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.value
WHO    VILLAGER — what the player reads after pressing "You notice more than the busy people do."
       spoken on: conversations.topic.work.nitwit.respond, button `value`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.value`: the villager accepts. Subject `work.nitwit.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.value/1   [77 chars]
    en  ...That's the first time anyone's put it as an ability rather than an excuse.
    >>  ............................................
    pt  ...É a primeira vez que alguém coloca isso como habilidade e não como desculpa.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.value/2   [56 chars]
    en  I do. Nobody's guarded around a man with no job to lose.
    >>  ............................................
    pt  Reparo. Ninguém fica na defensiva perto de um homem sem emprego a perder.
    >>  ............................................
```


### Button `challenge` — "You could learn a trade if you wanted."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.nitwit.identity` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.challenge` — accepted phrasings: "you could learn a trade if you wanted"
  - the message must contain one of: `learn`, `trade`, `wanted`
  - scored words: `learn`(1.5), `trade`(1.0), `wanted`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.respond.challenge   [38 chars]
    en  You could learn a trade if you wanted.
    >>  ............................................
    pt  Você podia aprender um ofício se quisesse.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.nitwit.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.nitwit.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You could learn a trade if you wanted."
       spoken on: conversations.topic.work.nitwit.respond, button `challenge`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.challenge.landed`: the villager resists. Subject `work.nitwit.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.challenge.landed/1   [71 chars]
    en  I could. I've watched every one of them and none of them wants me back.
    >>  ............................................
    pt  Podia. Já observei todos eles e nenhum me quer de volta.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.challenge.landed/2   [77 chars]
    en  Wanted to, once. It's a longer story than the square gives me room for, %1$s.
    >>  ............................................
    pt  Já quis, uma vez. É uma história mais longa do que a praça me dá espaço, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.nitwit.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.nitwit.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You could learn a trade if you wanted."
       spoken on: conversations.topic.work.nitwit.respond, button `challenge`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.challenge.stung`: the villager resists. Subject `work.nitwit.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.challenge.stung/1   [65 chars]
    en  ...If it were only wanting, %1$s, there'd be no nitwits anywhere.
    >>  ............................................
    pt  ...Se fosse só querer, %1$s, não haveria bobos em lugar nenhum.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.challenge.stung/2   [68 chars]
    en  Everyone says that. Not one of them has offered to teach me a trade.
    >>  ............................................
    pt  Todo mundo diz isso. Nenhum deles se ofereceu pra me ensinar um ofício.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the clouds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.nitwit.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.respond.leave   [36 chars]
    en  I'll let you get back to the clouds.
    >>  ............................................
    pt  Vou deixar você voltar pras nuvens.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the clouds."
       spoken on: conversations.topic.work.nitwit.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.nitwit.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.nitwit.risk` — e.g. "The risk is being here in forty years having been asked nothing. It's a slow sort of danger."


```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.nitwit.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.nitwit.risk.respond   [23 chars]
    en  That's what's under it.
    >>  ............................................
    pt  É o que está por baixo.
    >>  ............................................
```


### Button `ask_carry` — "What do people say in front of you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.risk` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.risk.ask_carry` — accepted phrasings: "what do people say in front of you"
  - the message must contain one of: `front`, `hear`, `overheard`
  - scored words: `front`(1.2), `hear`(1.5), `overheard`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.risk.respond.ask_carry
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.risk.respond.ask_carry   [35 chars]
    en  What do people say in front of you?
    >>  ............................................
    pt  O que as pessoas dizem na sua frente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.risk.ask_carry`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.risk.ask_carry
WHO    VILLAGER — what the player reads after pressing "What do people say in front of you?"
       spoken on: conversations.topic.work.nitwit.risk.respond, button `ask_carry`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.risk.ask_carry`: the villager explains. Subject `work.nitwit.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.risk.ask_carry/1   [82 chars]
    en  Everything. They talk over me the way you talk over a chair, and I hear all of it.
    >>  ............................................
    pt  Tudo. Falam por cima de mim como se fala por cima de uma cadeira, e eu ouço tudo.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.risk.ask_carry/2   [87 chars]
    en  Things they'd be ruined by. I've never repeated one and no one has thanked me for that.
    >>  ............................................
    pt  Coisas que os arruinariam. Nunca repeti nenhuma e ninguém me agradeceu por isso.
    >>  ............................................
```


### Button `sympathise` — "Being talked over isn't the same as not being there."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.nitwit.risk` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.risk.sympathise` — accepted phrasings: "being talked over isn't the same as not being there"
  - the message must contain one of: `talked`, `unseen`, `present`
  - scored words: `talked`(1.5), `unseen`(1.2), `present`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.risk.respond.sympathise   [52 chars]
    en  Being talked over isn't the same as not being there.
    >>  ............................................
    pt  Ser ignorado não é o mesmo que não estar ali.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.nitwit.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.nitwit.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Being talked over isn't the same as not being there."
       spoken on: conversations.topic.work.nitwit.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.risk.sympathise`: the villager accepts. Subject `work.nitwit.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.risk.sympathise/1   [83 chars]
    en  ...No. It isn't. I've known that for thirty years and never had it said back to me.
    >>  ............................................
    pt  ...Não. Não é. Sei disso há trinta anos e nunca ouvi de volta.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.risk.sympathise/2   [76 chars]
    en  That's the sentence. That's the exact sentence, %1$s, and I'd like a moment.
    >>  ............................................
    pt  É a frase. É exatamente a frase, %1$s, e eu queria um momento.
    >>  ............................................
```


### Button `ask_forty` — "Forty years is a long time to wait to be asked."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.risk` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.risk.ask_forty` — accepted phrasings: "forty years is a long time to wait to be asked"
  - the message must contain one of: `forty`, `waiting`, `asked`
  - scored words: `forty`(1.5), `waiting`(1.2), `asked`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.risk.respond.ask_forty
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.risk.respond.ask_forty   [47 chars]
    en  Forty years is a long time to wait to be asked.
    >>  ............................................
    pt  Quarenta anos é muito pra esperar ser perguntado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.risk.ask_forty`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.risk.ask_forty
WHO    VILLAGER — what the player reads after pressing "Forty years is a long time to wait to be asked."
       spoken on: conversations.topic.work.nitwit.risk.respond, button `ask_forty`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.risk.ask_forty`: the villager explains. Subject `work.nitwit.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.risk.ask_forty/1   [86 chars]
    en  It is. And the waiting isn't the hard part; the deciding to keep watching the road is.
    >>  ............................................
    pt  É. E esperar não é a parte difícil; decidir continuar olhando a estrada é.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.risk.ask_forty/2   [101 chars]
    en  I gave up expecting it a decade ago and I did not give up watching, %1$s. Make of that what you like.
    >>  ............................................
    pt  Desisti de esperar uma década atrás e não desisti de olhar, %1$s. Tire suas conclusões.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the clouds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.nitwit.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.risk.respond.leave   [36 chars]
    en  I'll let you get back to the clouds.
    >>  ............................................
    pt  Vou deixar você voltar pras nuvens.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the clouds."
       spoken on: conversations.topic.work.nitwit.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.nitwit.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.nitwit.task` — e.g. "Watching the road. Somebody has to and nobody else has the time for it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.nitwit.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.nitwit.task.respond   [21 chars]
    en  That's the day, then.
    >>  ............................................
    pt  É o dia, então.
    >>  ............................................
```


### Button `ask_road` — "Who came in before noon?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.task` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.task.ask_road` — accepted phrasings: "who came in before noon"
  - the message must contain one of: `came`, `road`, `noon`
  - scored words: `came`(1.2), `road`(1.5), `noon`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.task.respond.ask_road
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.task.respond.ask_road   [24 chars]
    en  Who came in before noon?
    >>  ............................................
    pt  Quem entrou antes do meio-dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.task.ask_road`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.task.ask_road
WHO    VILLAGER — what the player reads after pressing "Who came in before noon?"
       spoken on: conversations.topic.work.nitwit.task.respond, button `ask_road`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.task.ask_road`: the villager explains. Subject `work.nitwit.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.task.ask_road/1   [84 chars]
    en  A cart with two people and a dog, and the dog wasn't theirs. It came back out alone.
    >>  ............................................
    pt  Uma carroça com duas pessoas e um cachorro, e o cachorro não era deles. Ele saiu sozinho.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.task.ask_road/2   [66 chars]
    en  Nobody. Which is the first day this month, and I've counted, %1$s.
    >>  ............................................
    pt  Ninguém. É o primeiro dia deste mês, e eu contei, %1$s.
    >>  ............................................
```


### Button `take_seriously` — "That's worth knowing. Does anyone ask you?"

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.nitwit.task` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.task.take_seriously` — accepted phrasings: "that's worth knowing. does anyone ask you"
  - the message must contain one of: `asks`, `knowing`, `worth`
  - scored words: `asks`(1.5), `knowing`(1.2), `worth`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.task.respond.take_seriously
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.task.respond.take_seriously   [42 chars]
    en  That's worth knowing. Does anyone ask you?
    >>  ............................................
    pt  Vale saber. Alguém te pergunta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.nitwit.task.take_seriously`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.nitwit.task.take_seriously`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.task.take_seriously
WHO    VILLAGER — what the player reads after pressing "That's worth knowing. Does anyone ask you?"
       spoken on: conversations.topic.work.nitwit.task.respond, button `take_seriously`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.task.take_seriously`: the villager accepts. Subject `work.nitwit.task`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.task.take_seriously/1   [81 chars]
    en  ...No. You're the first in a while to treat it as knowing rather than as talking.
    >>  ............................................
    pt  ...Não. Você é o primeiro em muito tempo a tratar como saber e não como falatório.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.task.take_seriously/2   [58 chars]
    en  The guard did once, after the raid. Only once, mind, %1$s.
    >>  ............................................
    pt  O guarda perguntou uma vez, depois do ataque. Só uma vez, veja bem, %1$s.
    >>  ............................................
```


### Button `ask_nothing` — "Nobody's given you anything to do?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.task` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.task.ask_nothing` — accepted phrasings: "nobody's given you anything to do"
  - the message must contain one of: `given`, `nothing`, `unasked`
  - scored words: `given`(1.2), `nothing`(1.5), `unasked`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.task.respond.ask_nothing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.task.respond.ask_nothing   [34 chars]
    en  Nobody's given you anything to do?
    >>  ............................................
    pt  Ninguém te deu nada pra fazer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.task.ask_nothing`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.task.ask_nothing
WHO    VILLAGER — what the player reads after pressing "Nobody's given you anything to do?"
       spoken on: conversations.topic.work.nitwit.task.respond, button `ask_nothing`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.task.ask_nothing`: the villager explains. Subject `work.nitwit.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.task.ask_nothing/1   [88 chars]
    en  Not since I was young. They decided something about me and then they never revisited it.
    >>  ............................................
    pt  Desde jovem, não. Decidiram algo sobre mim e nunca revisaram.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.task.ask_nothing/2   [69 chars]
    en  They gave up asking and I gave up offering. It happened slowly, %1$s.
    >>  ............................................
    pt  Pararam de pedir e eu parei de oferecer. Foi devagar, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the clouds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.nitwit.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.task.respond.leave   [36 chars]
    en  I'll let you get back to the clouds.
    >>  ............................................
    pt  Vou deixar você voltar pras nuvens.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the clouds."
       spoken on: conversations.topic.work.nitwit.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.nitwit.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.nitwit.village` — e.g. "I know this place better than the mayor does and he has never once come to ask me anything."


```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.nitwit.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.nitwit.village.respond   [19 chars]
    en  That's how it sits.
    >>  ............................................
    pt  É assim que fica.
    >>  ............................................
```


### Button `ask_raid` — "Did you tell anyone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.village` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.village.ask_raid` — accepted phrasings: "did you tell anyone"
  - the message must contain one of: `told`, `raid`
  - scored words: `told`(1.5), `raid`(1.2), `anyone`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.village.respond.ask_raid
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.village.respond.ask_raid   [20 chars]
    en  Did you tell anyone?
    >>  ............................................
    pt  Você contou a alguém?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.village.ask_raid`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.village.ask_raid
WHO    VILLAGER — what the player reads after pressing "Did you tell anyone?"
       spoken on: conversations.topic.work.nitwit.village.respond, button `ask_raid`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.village.ask_raid`: the villager explains. Subject `work.nitwit.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.village.ask_raid/1   [86 chars]
    en  I tried to tell the guard and he told me to get indoors. He was busy. I understand it.
    >>  ............................................
    pt  Tentei falar com o guarda e ele mandou eu entrar. Ele estava ocupado. Eu entendo.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.village.ask_raid/2   [85 chars]
    en  I shouted it twice and then I went and got the children out myself, %1$s. Nobody saw.
    >>  ............................................
    pt  Gritei duas vezes e aí eu mesmo tirei as crianças, %1$s. Ninguém viu.
    >>  ............................................
```


### Button `say_thanks` — "Then somebody should say it now. You got them out."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.nitwit.village` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.village.say_thanks` — accepted phrasings: "then somebody should say it now. you got them out"
  - the message must contain one of: `children`, `acknowledge`
  - scored words: `out`(0.5), `children`(1.2), `acknowledge`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.village.respond.say_thanks   [50 chars]
    en  Then somebody should say it now. You got them out.
    >>  ............................................
    pt  Então alguém devia dizer agora. Você os tirou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.nitwit.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.nitwit.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Then somebody should say it now. You got them out."
       spoken on: conversations.topic.work.nitwit.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.village.say_thanks`: the villager accepts. Subject `work.nitwit.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.village.say_thanks/1   [76 chars]
    en  ...Right. Well. I'd got used to it being a thing I knew and nobody else did.
    >>  ............................................
    pt  ...Certo. Bom. Eu tinha me acostumado a ser algo que só eu sabia.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.village.say_thanks/2   [69 chars]
    en  That's — yes. Give me a moment with that, %1$s. It's been four years.
    >>  ............................................
    pt  Isso é — sim. Me dê um momento com isso, %1$s. Faz quatro anos.
    >>  ............................................
```


### Button `ask_mayor` — "Would you tell the mayor if he asked?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.nitwit.village` · offered only once the villager has actually said `work:nitwit`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.nitwit.village.ask_mayor` — accepted phrasings: "would you tell the mayor if he asked"
  - the message must contain one of: `mayor`, `asked`
  - scored words: `mayor`(1.5), `asked`(1.0), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.village.respond.ask_mayor
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.village.respond.ask_mayor   [37 chars]
    en  Would you tell the mayor if he asked?
    >>  ............................................
    pt  Você contaria ao prefeito se ele perguntasse?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.nitwit.village.ask_mayor`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.nitwit.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What do you actually do all day?" | "Enjoy the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.village.ask_mayor
WHO    VILLAGER — what the player reads after pressing "Would you tell the mayor if he asked?"
       spoken on: conversations.topic.work.nitwit.village.respond, button `ask_mayor`
       leaves the player on: conversations.topic.work.nitwit.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.village.ask_mayor`: the villager explains. Subject `work.nitwit.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.nitwit.village.ask_mayor/1   [75 chars]
    en  In a heartbeat, and in order, and with the parts he wouldn't like included.
    >>  ............................................
    pt  Na hora, em ordem, e com as partes que ele não gostaria incluídas.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.village.ask_mayor/2   [74 chars]
    en  He won't. But yes. I've had the answer ready for about eleven years, %1$s.
    >>  ............................................
    pt  Ele não vai. Mas sim. Tenho a resposta pronta há uns onze anos, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the clouds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.nitwit.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.nitwit.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.nitwit.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.nitwit.village.respond.leave   [36 chars]
    en  I'll let you get back to the clouds.
    >>  ............................................
    pt  Vou deixar você voltar pras nuvens.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the clouds."
       spoken on: conversations.topic.work.nitwit.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left`: the villager accepts. Subject `work.nitwit.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.nitwit.followup / leave; conversations.scene.work.nitwit.i_noticed.active.respond / leave; conversations.scene.work.nitwit.i_noticed.succeeded.respond / leave; conversations.scene.work.nitwit.left_out.active.respond / leave; conversations.scene.work.nitwit.left_out.succeeded.respond / leave; conversations.scene.work.nitwit.refused_job.blocked.respond / leave; conversations.scene.work.nitwit.refused_job.succeeded.respond / leave; conversations.topic.work.nitwit.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.nitwit.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

