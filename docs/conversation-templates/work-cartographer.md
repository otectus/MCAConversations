# Work talk with a cartographer

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.cartographer.followup`](#conversations-scene-work-cartographer-followup)
- [`conversations.scene.work.cartographer.stalled_survey.blocked.respond`](#conversations-scene-work-cartographer-stalled-survey-blocked-respond)
- [`conversations.scene.work.cartographer.stalled_survey.succeeded.respond`](#conversations-scene-work-cartographer-stalled-survey-succeeded-respond)
- [`conversations.scene.work.cartographer.unverified_account.active.respond`](#conversations-scene-work-cartographer-unverified-account-active-respond)
- [`conversations.scene.work.cartographer.unverified_account.succeeded.respond`](#conversations-scene-work-cartographer-unverified-account-succeeded-respond)
- [`conversations.scene.work.cartographer.wrong_map.blocked.respond`](#conversations-scene-work-cartographer-wrong-map-blocked-respond)
- [`conversations.scene.work.cartographer.wrong_map.succeeded.respond`](#conversations-scene-work-cartographer-wrong-map-succeeded-respond)
- [`conversations.topic.work.cartographer.craft.respond`](#conversations-topic-work-cartographer-craft-respond)
- [`conversations.topic.work.cartographer.followup`](#conversations-topic-work-cartographer-followup)
- [`conversations.topic.work.cartographer.future.respond`](#conversations-topic-work-cartographer-future-respond)
- [`conversations.topic.work.cartographer.respond`](#conversations-topic-work-cartographer-respond)
- [`conversations.topic.work.cartographer.risk.respond`](#conversations-topic-work-cartographer-risk-respond)
- [`conversations.topic.work.cartographer.task.respond`](#conversations-topic-work-cartographer-task-respond)
- [`conversations.topic.work.cartographer.village.respond`](#conversations-topic-work-cartographer-village-respond)

---

## `conversations.scene.work.cartographer.followup`

**Reached from 10 route(s):** `conversations.scene.work.cartographer.stalled_survey.blocked.respond` / `ask_why_it_fades`; `conversations.scene.work.cartographer.stalled_survey.blocked.respond` / `offer_paper`; `conversations.scene.work.cartographer.stalled_survey.blocked.respond` / `advise_rough_copy`; `conversations.scene.work.cartographer.stalled_survey.succeeded.respond` / `ask_about_the_light_hand`; `conversations.scene.work.cartographer.unverified_account.active.respond` / `ask_how_to_judge`; `conversations.scene.work.cartographer.unverified_account.active.respond` / `advise_marking_it`; `conversations.scene.work.cartographer.unverified_account.succeeded.respond` / `note_the_method`; `conversations.scene.work.cartographer.wrong_map.blocked.respond` / `advise_recall`; `conversations.scene.work.cartographer.wrong_map.blocked.respond` / `ask_how_it_happened`; `conversations.scene.work.cartographer.wrong_map.succeeded.respond` / `note_the_dating`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cartographer.stalled_survey.blocked.accepted` — e.g. "Then %2$s becomes a thing that exists outside me, which is the entire point of the trade."
- `conversations.scene.work.cartographer.stalled_survey.blocked.conceded` — e.g. "On a board with charcoal, yes. It is not a map. It is a way of keeping the memory from rotting, and that is worth doing tonight."
- `conversations.scene.work.cartographer.stalled_survey.blocked.explained` — e.g. "Because half of surveying is what you remember and only half is what you wrote. In a fortnight I will be guessing which bend came first."
- `conversations.scene.work.cartographer.stalled_survey.succeeded.explained` — e.g. "So the person holding it knows which lines to trust. A map that looks equally certain everywhere is lying in the places it is wrong."
- `conversations.scene.work.cartographer.unverified_account.active.accepted` — e.g. "In a dotted hand, with the teller's name in the margin. Then it is evidence rather than a claim, and the next surveyor can settle it."
- `conversations.scene.work.cartographer.unverified_account.active.explained` — e.g. "I ask about the boring parts. Where did you sleep, what did you eat, was there wood. Liars have scenery and no supper."
- `conversations.scene.work.cartographer.unverified_account.succeeded.acknowledged` — e.g. "It is the only method I have. I cannot walk everywhere, so I have to be good at deciding whom to believe."
- `conversations.scene.work.cartographer.wrong_map.blocked.accepted` — e.g. "All four. I know where three of them are and the fourth went downriver, so I will be writing a letter I do not want to write."
- `conversations.scene.work.cartographer.wrong_map.blocked.explained` — e.g. "I took somebody's word for it. He was confident and it was raining and I did not want to walk another two miles. That is the whole story."
- `conversations.scene.work.cartographer.wrong_map.succeeded.acknowledged` — e.g. "It cost one embarrassing autumn to learn and it will save every year after this one, which is a fair exchange."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cartographer.followup   [15 chars]
    en  Was there more?
    >>  ............................................
    pt  Tinha mais?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of drawing a coast?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.cartographer.*` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.followup.ask_more` — accepted phrasings: "whats the hardest part of drawing a coast"; "what is the hardest part of drawing a coast"; "hardest thing about drawing a coast"
  - the message must contain one of: `hardest`, `coast`
  - scored words: `hardest`(1.8), `coast`(1.8), `whats`(0.8), `part`(0.8), `drawing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.followup.ask_more   [43 chars]
    en  What's the hardest part of drawing a coast?
    >>  ............................................
    pt  Qual é a parte mais difícil de desenhar uma costa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of drawing a coast?"
       spoken on: conversations.scene.work.cartographer.followup, button `ask_more`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.hard`: the villager explains. Subject `work.cartographer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.cartographer.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.cartographer.hard/1   [83 chars]
    en  Somebody walks two days in the wrong direction on my word. I've done it once. Once.
    >>  ............................................
    pt  Alguém anda dois dias na direção errada por causa da minha palavra. Fiz isso uma vez. Uma.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.hard/2   [96 chars]
    en  It doesn't fail loudly, %1$s. It fails to a stranger, far off, who never comes back to complain.
    >>  ............................................
    pt  Não falha em voz alta, %1$s. Falha com um estranho, longe, que nunca volta pra reclamar.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the sheet."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.cartographer.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.followup.leave   [28 chars]
    en  I'll leave you to the sheet.
    >>  ............................................
    pt  Vou deixar você com a folha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the sheet."
       spoken on: conversations.scene.work.cartographer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave; conversations.topic.work.cartographer.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.cartographer.leave/1   [54 chars]
    en  The blank part isn't going to fill itself. Off you go.
    >>  ............................................
    pt  A parte em branco não vai se preencher sozinha. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.leave/2   [37 chars]
    en  Aye. Don't lean on the wet ink, %1$s.
    >>  ............................................
    pt  É. Não encoste na tinta molhada, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.cartographer.stalled_survey.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cartographer.stalled_survey.blocked` — e.g. "I have walked all of %2$s and I have %3$s, so it exists only in my head and my head is not a document."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cartographer.stalled_survey.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond   [11 chars]
    en  The survey.
    >>  ............................................
    pt  O levantamento.
    >>  ............................................
```


### Button `ask_why_it_fades` — "Why does waiting make it worse?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.stalled_survey.blocked` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.stalled_survey.blocked.ask_why_it_fades` — accepted phrasings: "why does waiting make it worse"; "why does waiting make it worse"; "what does the delay do to the measurements"
  - the message must contain one of: `waiting`, `delay`, `measurements`
  - scored words: `waiting`(1.8), `delay`(1.8), `measurements`(1.8), `why`(0.8), `does`(0.8), `worse`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond.ask_why_it_fades
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.stalled_survey.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond.ask_why_it_fades   [31 chars]
    en  Why does waiting make it worse?
    >>  ............................................
    pt  Por que esperar piora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.cartographer.paper`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cartographer.stalled_survey"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Why does waiting make it worse?"
       spoken on: conversations.scene.work.cartographer.stalled_survey.blocked.respond, button `ask_why_it_fades`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.stalled_survey.blocked.explained`: the villager explains. Subject `work.cartographer.paper`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.explained/1   [136 chars]
    en  Because half of surveying is what you remember and only half is what you wrote. In a fortnight I will be guessing which bend came first.
    >>  ............................................
    pt  Porque metade do levantamento é o que você lembra e só metade é o que você escreveu. Em duas semanas vou estar chutando qual curva vinha primeiro.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.explained/2   [139 chars]
    en  The notebook says two hundred paces. It does not say that I stopped for water in the middle, and by winter I will not remember that either.
    >>  ............................................
    pt  O caderno diz duzentos passos. Não diz que eu parei para beber água no meio, e até o inverno eu também não vou lembrar disso.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.explained/3   [116 chars]
    en  %2$s itself moves. Water shifts, paths close. A survey is only true on the day, and the day is getting further away.
    >>  ............................................
    pt  %2$s em si se move. A água muda, trilhas fecham. Um levantamento só é verdade no dia, e o dia está ficando cada vez mais longe.
    >>  ............................................
```


### Button `offer_paper` — "I'll bring you paper."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.cartographer.stalled_survey.blocked` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.stalled_survey.blocked.offer_paper` — accepted phrasings: "ill bring you paper"; "i can bring you paper"; "let me fetch paper for that"
  - the message must contain one of: `paper`
  - scored words: `paper`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond.offer_paper
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.stalled_survey.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond.offer_paper   [21 chars]
    en  I'll bring you paper.
    >>  ............................................
    pt  Vou te trazer papel.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cartographer.survey.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.cartographer.paper`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.stalled_survey", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.cartographer.stalled_survey", "obligation": "commitment:work.cartographer.bring_paper"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.cartographer.bring_paper"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you paper."
       spoken on: conversations.scene.work.cartographer.stalled_survey.blocked.respond, button `offer_paper`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.stalled_survey.blocked.accepted`: the villager accepts. Subject `work.cartographer.paper`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.accepted/1   [89 chars]
    en  Then %2$s becomes a thing that exists outside me, which is the entire point of the trade.
    >>  ............................................
    pt  Então %2$s vira uma coisa que existe fora de mim, que é o objetivo inteiro do ofício.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.accepted/2   [123 chars]
    en  Bring it and I will draw for two days without speaking to anybody, and you should take that as thanks rather than rudeness.
    >>  ............................................
    pt  Traga e eu desenho dois dias sem falar com ninguém, e você deve tomar isso como agradecimento, não como grosseria.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.accepted/3   [121 chars]
    en  Yes. And I will put a note in the corner saying who made the sheet possible, because a map is a record of more than land.
    >>  ............................................
    pt  Sim. E vou pôr uma nota no canto dizendo quem tornou a folha possível, porque um mapa é registro de mais coisas do que terra.
    >>  ............................................
```


### Button `advise_rough_copy` — "Sketch it rough on anything to hand."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.stalled_survey.blocked` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.stalled_survey.blocked.advise_rough_copy` — accepted phrasings: "sketch it rough on anything to hand"; "sketch it rough on anything to hand"; "make a rough draft on scrap"
  - the message must contain one of: `sketch`, `rough`, `scrap`
  - scored words: `sketch`(1.8), `rough`(1.8), `scrap`(1.8), `anything`(0.8), `hand`(0.8), `draft`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond.advise_rough_copy
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.stalled_survey.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond.advise_rough_copy   [36 chars]
    en  Sketch it rough on anything to hand.
    >>  ............................................
    pt  Rascunhe em qualquer coisa que tiver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.cartographer.paper`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cartographer.stalled_survey"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.conceded
WHO    VILLAGER — what the player reads after pressing "Sketch it rough on anything to hand."
       spoken on: conversations.scene.work.cartographer.stalled_survey.blocked.respond, button `advise_rough_copy`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.stalled_survey.blocked.conceded`: the villager accepts. Subject `work.cartographer.paper`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.conceded/1   [128 chars]
    en  On a board with charcoal, yes. It is not a map. It is a way of keeping the memory from rotting, and that is worth doing tonight.
    >>  ............................................
    pt  Numa tábua com carvão, sim. Não é um mapa. É um jeito de impedir a memória de apodrecer, e vale a pena fazer hoje à noite.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.conceded/2   [106 chars]
    en  You are right and I have been precious about it. A bad record beats a perfect intention every single time.
    >>  ............................................
    pt  Você tem razão e eu andei preciosista. Um registro ruim vence uma intenção perfeita todas as vezes.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.conceded/3   [119 chars]
    en  I resisted that for a week because I did not want to see my own work look shabby. Which is vanity dressed as standards.
    >>  ............................................
    pt  Resisti a isso por uma semana porque não queria ver meu próprio trabalho parecendo pobre. O que é vaidade fantasiada de padrão.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sheet."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cartographer.stalled_survey.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.stalled_survey.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the sheet.
    >>  ............................................
    pt  Vou deixar você voltar à folha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sheet."
       spoken on: conversations.scene.work.cartographer.stalled_survey.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave; conversations.topic.work.cartographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cartographer.stalled_survey.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cartographer.stalled_survey.succeeded` — e.g. "%2$s is on paper. Two days of drawing and it came out closer to what I walked than I had dared expect."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cartographer.stalled_survey.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.respond   [18 chars]
    en  The survey, drawn.
    >>  ............................................
    pt  O levantamento, desenhado.
    >>  ............................................
```


### Button `ask_about_the_light_hand` — "Why draw the doubtful parts faintly?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.stalled_survey.succeeded` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.stalled_survey.succeeded.ask_about_the_light_hand` — accepted phrasings: "why draw the doubtful parts faintly"; "why draw the doubtful parts faintly"; "what does the faint line mean"
  - the message must contain one of: `faintly`, `faint`, `doubtful`
  - scored words: `faintly`(1.8), `faint`(1.8), `doubtful`(1.8), `why`(0.8), `draw`(0.8), `parts`(0.8), `does`(0.8), `line`(0.8), `mean`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.respond.ask_about_the_light_hand
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.stalled_survey.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.respond.ask_about_the_light_hand   [36 chars]
    en  Why draw the doubtful parts faintly?
    >>  ............................................
    pt  Por que desenhar leve as partes duvidosas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +2  _(recorded under topic `work.cartographer.paper`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cartographer.stalled_survey"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "Why draw the doubtful parts faintly?"
       spoken on: conversations.scene.work.cartographer.stalled_survey.succeeded.respond, button `ask_about_the_light_hand`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.stalled_survey.succeeded.explained`: the villager explains. Subject `work.cartographer.paper`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.explained/1   [132 chars]
    en  So the person holding it knows which lines to trust. A map that looks equally certain everywhere is lying in the places it is wrong.
    >>  ............................................
    pt  Para quem segura saber em quais linhas confiar. Um mapa que parece igualmente certo em tudo mente exatamente onde está errado.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.explained/2   [135 chars]
    en  Because somebody will one day walk into the marsh on my authority, and I want them to be able to see how much authority I actually had.
    >>  ............................................
    pt  Porque um dia alguém vai entrar no pântano sob a minha autoridade, e quero que consiga ver quanta autoridade eu de fato tinha.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.explained/3   [125 chars]
    en  It costs me nothing and it has saved two people a wet night. I would rather look uncertain on paper than confident and wrong.
    >>  ............................................
    pt  Não me custa nada e já poupou uma noite molhada a duas pessoas. Prefiro parecer incerta no papel a parecer confiante e estar errada.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sheet."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cartographer.stalled_survey.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.stalled_survey.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the sheet.
    >>  ............................................
    pt  Vou deixar você voltar à folha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sheet."
       spoken on: conversations.scene.work.cartographer.stalled_survey.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave; conversations.topic.work.cartographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cartographer.unverified_account.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cartographer.unverified_account.active` — e.g. "%2$s described a lake three days east and I cannot check it, and a lake is a large thing to take on trust."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cartographer.unverified_account.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.active.respond   [12 chars]
    en  The account.
    >>  ............................................
    pt  O relato.
    >>  ............................................
```


### Button `ask_how_to_judge` — "How do you judge an account?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.unverified_account.active` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.unverified_account.active.ask_how_to_judge` — accepted phrasings: "how do you judge an account"; "how do you judge an account"; "what makes a traveller believable"
  - the message must contain one of: `judge`, `believable`, `account`
  - scored words: `judge`(1.8), `believable`(1.8), `account`(1.8), `makes`(0.8), `traveller`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.active.respond.ask_how_to_judge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.unverified_account.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.active.respond.ask_how_to_judge   [28 chars]
    en  How do you judge an account?
    >>  ............................................
    pt  Como você julga um relato?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.cartographer.accounts`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cartographer.unverified_account"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.active.explained
WHO    VILLAGER — what the player reads after pressing "How do you judge an account?"
       spoken on: conversations.scene.work.cartographer.unverified_account.active.respond, button `ask_how_to_judge`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.unverified_account.active.explained`: the villager explains. Subject `work.cartographer.accounts`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.active.explained/1   [118 chars]
    en  I ask about the boring parts. Where did you sleep, what did you eat, was there wood. Liars have scenery and no supper.
    >>  ............................................
    pt  Pergunto sobre as partes chatas. Onde dormiu, o que comeu, se havia lenha. Mentiroso tem paisagem e não tem janta.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.active.explained/2   [90 chars]
    en  I ask twice, a week apart. A walked road comes back the same and an invented one improves.
    >>  ............................................
    pt  Pergunto duas vezes, com uma semana de intervalo. Uma estrada percorrida volta igual e uma inventada melhora.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.active.explained/3   [129 chars]
    en  The best sign is somebody saying they do not know. A traveller who admits one gap is worth four who are certain about everything.
    >>  ............................................
    pt  O melhor sinal é alguém dizer que não sabe. Um viajante que admite uma lacuna vale por quatro que têm certeza de tudo.
    >>  ............................................
```


### Button `advise_marking_it` — "Draw it, but mark it as hearsay."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.unverified_account.active` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.unverified_account.active.advise_marking_it` — accepted phrasings: "draw it but mark it as hearsay"; "draw it but mark it as hearsay"; "record it as reported rather than surveyed"
  - the message must contain one of: `hearsay`, `reported`, `record`
  - scored words: `hearsay`(1.8), `reported`(1.8), `record`(1.8), `draw`(0.8), `but`(0.8), `mark`(0.8), `rather`(0.8), `than`(0.8), `surveyed`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.active.respond.advise_marking_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.unverified_account.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.active.respond.advise_marking_it   [32 chars]
    en  Draw it, but mark it as hearsay.
    >>  ............................................
    pt  Desenhe, mas marque como relato.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.cartographer.accounts`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cartographer.unverified_account"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.active.accepted
WHO    VILLAGER — what the player reads after pressing "Draw it, but mark it as hearsay."
       spoken on: conversations.scene.work.cartographer.unverified_account.active.respond, button `advise_marking_it`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.unverified_account.active.accepted`: the villager accepts. Subject `work.cartographer.accounts`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.active.accepted/1   [133 chars]
    en  In a dotted hand, with the teller's name in the margin. Then it is evidence rather than a claim, and the next surveyor can settle it.
    >>  ............................................
    pt  Em traço pontilhado, com o nome de quem contou na margem. Aí vira prova em vez de alegação, e o próximo topógrafo resolve.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.active.accepted/2   [118 chars]
    en  Yes. Leaving it blank is its own kind of lie, because a blank says nothing is there and I have been told something is.
    >>  ............................................
    pt  Sim. Deixar em branco é um tipo de mentira, porque branco diz que não há nada ali, e me disseram que há.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.active.accepted/3   [120 chars]
    en  That is what I will do, and I will feel uneasy every time I look at that corner of the sheet, which is probably correct.
    >>  ............................................
    pt  É o que vou fazer, e vou me sentir desconfortável toda vez que olhar aquele canto da folha, o que provavelmente é o certo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sheet."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cartographer.unverified_account.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.unverified_account.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.active.respond.leave   [35 chars]
    en  I'll let you get back to the sheet.
    >>  ............................................
    pt  Vou deixar você voltar à folha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sheet."
       spoken on: conversations.scene.work.cartographer.unverified_account.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave; conversations.topic.work.cartographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cartographer.unverified_account.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cartographer.unverified_account.succeeded` — e.g. "Somebody else came through and described the same lake, from the other side, without knowing about %2$s."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cartographer.unverified_account.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.respond   [10 chars]
    en  That lake.
    >>  ............................................
    pt  Aquele lago.
    >>  ............................................
```


### Button `note_the_method` — "Two independent accounts is proper work."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cartographer.unverified_account.succeeded` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.unverified_account.succeeded.note_the_method` — accepted phrasings: "two independent accounts is proper work"; "two independent accounts is proper work"; "waiting for a second account was right"
  - the message must contain one of: `independent`, `second`, `accounts`
  - scored words: `independent`(1.8), `second`(1.8), `accounts`(1.8), `two`(0.8), `proper`(0.8), `work`(0.8), `waiting`(0.8), `account`(0.8), `right`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.respond.note_the_method
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.unverified_account.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.respond.note_the_method   [40 chars]
    en  Two independent accounts is proper work.
    >>  ............................................
    pt  Dois relatos independentes é trabalho sério.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +1  _(recorded under topic `work.cartographer.accounts`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cartographer.unverified_account"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Two independent accounts is proper work."
       spoken on: conversations.scene.work.cartographer.unverified_account.succeeded.respond, button `note_the_method`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.unverified_account.succeeded.acknowledged`: the villager accepts. Subject `work.cartographer.accounts`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.acknowledged/1   [105 chars]
    en  It is the only method I have. I cannot walk everywhere, so I have to be good at deciding whom to believe.
    >>  ............................................
    pt  É o único método que tenho. Não posso caminhar por toda parte, então preciso ser boa em decidir em quem acreditar.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.acknowledged/2   [114 chars]
    en  Thank you. It took eleven months of leaving a corner of the sheet unfinished, and I was asked about it four times.
    >>  ............................................
    pt  Obrigada. Custou onze meses com um canto da folha inacabado, e me perguntaram sobre ele quatro vezes.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.acknowledged/3   [141 chars]
    en  And if the second account had contradicted the first, I would have drawn nothing and been right to. That is the part people find frustrating.
    >>  ............................................
    pt  E se o segundo relato tivesse contradito o primeiro, eu não teria desenhado nada, e estaria certa. É a parte que as pessoas acham irritante.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sheet."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cartographer.unverified_account.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.unverified_account.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the sheet.
    >>  ............................................
    pt  Vou deixar você voltar à folha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sheet."
       spoken on: conversations.scene.work.cartographer.unverified_account.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave; conversations.topic.work.cartographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cartographer.wrong_map.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cartographer.wrong_map.blocked` — e.g. "There is %2$s on a sheet I sold to four people, and I did not find out until one of them came back cross."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cartographer.wrong_map.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.respond   [17 chars]
    en  The map you sold.
    >>  ............................................
    pt  O mapa que você vendeu.
    >>  ............................................
```


### Button `advise_recall` — "Go and correct all four sheets."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.wrong_map.blocked` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.wrong_map.blocked.advise_recall` — accepted phrasings: "go and correct all four sheets"; "go and correct all four sheets"; "find the buyers and amend the sheets"
  - the message must contain one of: `correct`, `sheets`, `amend`
  - scored words: `correct`(1.8), `sheets`(1.8), `amend`(1.8), `all`(0.8), `four`(0.8), `find`(0.8), `buyers`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.blocked.respond.advise_recall
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.wrong_map.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.respond.advise_recall   [31 chars]
    en  Go and correct all four sheets.
    >>  ............................................
    pt  Vá corrigir as quatro folhas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cartographer.wrong_map.owned`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.cartographer.a_wrong_map`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cartographer.wrong_map"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Go and correct all four sheets."
       spoken on: conversations.scene.work.cartographer.wrong_map.blocked.respond, button `advise_recall`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.wrong_map.blocked.accepted`: the villager accepts. Subject `work.cartographer.a_wrong_map`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.accepted/1   [125 chars]
    en  All four. I know where three of them are and the fourth went downriver, so I will be writing a letter I do not want to write.
    >>  ............................................
    pt  Todas as quatro. Sei onde estão três, e a quarta foi rio abaixo, então vou escrever uma carta que não quero escrever.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.accepted/2   [132 chars]
    en  Yes. And I will do it before I fix the master sheet, because the master sheet is for me and the four are for people who are walking.
    >>  ............................................
    pt  Sim. E vou fazer isso antes de corrigir a folha-mestra, porque a folha-mestra é para mim e as quatro são para gente que está caminhando.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.accepted/3   [133 chars]
    en  That is the answer. It is also four separate conversations in which I say I was wrong, and I am dreading the second one particularly.
    >>  ............................................
    pt  É a resposta. Também são quatro conversas separadas em que eu digo que errei, e estou temendo a segunda em especial.
    >>  ............................................
```


### Button `ask_how_it_happened` — "How did the error get in?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.wrong_map.blocked` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.wrong_map.blocked.ask_how_it_happened` — accepted phrasings: "how did the error get in"; "how did the error get in"; "where did the mistake come from"
  - the message must contain one of: `error`, `mistake`
  - scored words: `error`(1.8), `mistake`(1.8), `get`(0.8), `where`(0.8), `come`(0.8), `from`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.blocked.respond.ask_how_it_happened
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.wrong_map.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.respond.ask_how_it_happened   [25 chars]
    en  How did the error get in?
    >>  ............................................
    pt  Como o erro entrou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.a_wrong_map`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cartographer.wrong_map"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How did the error get in?"
       spoken on: conversations.scene.work.cartographer.wrong_map.blocked.respond, button `ask_how_it_happened`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.wrong_map.blocked.explained`: the villager explains. Subject `work.cartographer.a_wrong_map`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.explained/1   [137 chars]
    en  I took somebody's word for it. He was confident and it was raining and I did not want to walk another two miles. That is the whole story.
    >>  ............................................
    pt  Aceitei a palavra de alguém. Ele estava confiante, chovia, e eu não queria andar mais três quilômetros. É a história inteira.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.explained/2   [102 chars]
    en  I drew it from an older sheet without checking, which is how every wrong map in history has been made.
    >>  ............................................
    pt  Desenhei a partir de uma folha antiga sem conferir, que é como todo mapa errado da história foi feito.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.explained/3   [126 chars]
    en  It was true when I drew it. That is the honest defence and it is not much of one, because I have not been back in three years.
    >>  ............................................
    pt  Era verdade quando desenhei. É a defesa honesta e não é grande coisa, porque faz três anos que eu não volto lá.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sheet."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cartographer.wrong_map.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.wrong_map.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the sheet.
    >>  ............................................
    pt  Vou deixar você voltar à folha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sheet."
       spoken on: conversations.scene.work.cartographer.wrong_map.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave; conversations.topic.work.cartographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cartographer.wrong_map.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cartographer.wrong_map.succeeded` — e.g. "All four are corrected. The fourth took a letter and six weeks and a reply that was kinder than I deserved."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cartographer.wrong_map.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.respond   [18 chars]
    en  Those four sheets.
    >>  ............................................
    pt  Aquelas quatro folhas.
    >>  ............................................
```


### Button `note_the_dating` — "Dating them is a good habit."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cartographer.wrong_map.succeeded` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cartographer.wrong_map.succeeded.note_the_dating` — accepted phrasings: "dating them is a good habit"; "dating them is a good habit"; "putting the date on is wise"
  - the message must contain one of: `dating`, `date`
  - scored words: `dating`(1.8), `date`(1.8), `good`(0.8), `habit`(0.8), `putting`(0.8), `wise`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.respond.note_the_dating
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.wrong_map.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.respond.note_the_dating   [28 chars]
    en  Dating them is a good habit.
    >>  ............................................
    pt  Datá-las é um bom hábito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +1  _(recorded under topic `work.cartographer.a_wrong_map`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cartographer.wrong_map"}
- Then opens: `conversations.scene.work.cartographer.followup`
- …where the player's next choices will be: "What's the hardest part of drawing a coast?" | "I'll leave you to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Dating them is a good habit."
       spoken on: conversations.scene.work.cartographer.wrong_map.succeeded.respond, button `note_the_dating`
       leaves the player on: conversations.scene.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.wrong_map.succeeded.acknowledged`: the villager accepts. Subject `work.cartographer.a_wrong_map`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.acknowledged/1   [110 chars]
    en  It cost one embarrassing autumn to learn and it will save every year after this one, which is a fair exchange.
    >>  ............................................
    pt  Custou um outono constrangedor para aprender e vai poupar todos os anos daqui em diante, o que é uma troca justa.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.acknowledged/2   [113 chars]
    en  Thank you. Nobody taught it to me. I suspect every cartographer invents it separately, after their own bad sheet.
    >>  ............................................
    pt  Obrigada. Ninguém me ensinou. Desconfio que todo cartógrafo inventa isso sozinho, depois da própria folha ruim.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.acknowledged/3   [110 chars]
    en  It also admits that a map has an age, which some of my customers find unsettling and all of them need to know.
    >>  ............................................
    pt  Também admite que um mapa tem idade, o que incomoda alguns clientes e todos precisam saber.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sheet."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cartographer.wrong_map.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cartographer.wrong_map.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the sheet.
    >>  ............................................
    pt  Vou deixar você voltar à folha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sheet."
       spoken on: conversations.scene.work.cartographer.wrong_map.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.topic.work.cartographer.craft.respond / leave; conversations.topic.work.cartographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cartographer.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cartographer.craft` — e.g. "I learned pacing before I learned drawing. A map is arithmetic that happens to be beautiful."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cartographer.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cartographer.craft.respond   [24 chars]
    en  That's the method of it.
    >>  ............................................
    pt  É esse o método.
    >>  ............................................
```


### Button `ask_dotted` — "What does a dotted line mean?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.craft` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.craft.ask_dotted` — accepted phrasings: "what does a dotted line mean"
  - the message must contain one of: `dotted`, `line`
  - scored words: `dotted`(1.5), `line`(1.0), `mean`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.craft.respond.ask_dotted
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.craft.respond.ask_dotted   [29 chars]
    en  What does a dotted line mean?
    >>  ............................................
    pt  O que significa uma linha pontilhada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.craft.ask_dotted`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.craft.ask_dotted
WHO    VILLAGER — what the player reads after pressing "What does a dotted line mean?"
       spoken on: conversations.topic.work.cartographer.craft.respond, button `ask_dotted`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.craft.ask_dotted`: the villager explains. Subject `work.cartographer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.craft.ask_dotted/1   [80 chars]
    en  That I was told, not that I saw. Anyone who confuses those two gets people lost.
    >>  ............................................
    pt  Que me contaram, não que eu vi. Quem confunde os dois faz gente se perder.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.craft.ask_dotted/2   [74 chars]
    en  Doubt. Solid means I stood there, %1$s. Dotted means somebody swore to me.
    >>  ............................................
    pt  Dúvida. Contínua quer dizer que eu estive lá, %1$s. Pontilhada quer dizer que alguém jurou.
    >>  ............................................
```


### Button `admire` — "Marking your own doubt takes some honesty."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.craft` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.craft.admire` — accepted phrasings: "marking your own doubt takes some honesty"
  - the message must contain one of: `doubt`, `honesty`, `marking`
  - scored words: `doubt`(1.5), `honesty`(1.5), `marking`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.craft.respond.admire   [42 chars]
    en  Marking your own doubt takes some honesty.
    >>  ............................................
    pt  Marcar a própria dúvida exige honestidade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cartographer.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cartographer.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.craft.admire
WHO    VILLAGER — what the player reads after pressing "Marking your own doubt takes some honesty."
       spoken on: conversations.topic.work.cartographer.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.craft.admire`: the villager accepts. Subject `work.cartographer.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.craft.admire/1   [72 chars]
    en  It takes one bad afternoon watching somebody set off on a guess of mine.
    >>  ............................................
    pt  Exige uma tarde ruim vendo alguém partir baseado num palpite meu.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.craft.admire/2   [63 chars]
    en  It's the only part of the trade I'd defend in front of anybody.
    >>  ............................................
    pt  É a única parte do ofício que eu defenderia diante de qualquer um.
    >>  ............................................
```


### Button `ask_pacing` — "How accurate is pacing, really?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.craft` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.craft.ask_pacing` — accepted phrasings: "how accurate is pacing, really"
  - the message must contain one of: `accurate`, `pacing`, `measure`
  - scored words: `accurate`(1.5), `pacing`(1.5), `measure`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.craft.respond.ask_pacing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.craft.respond.ask_pacing   [31 chars]
    en  How accurate is pacing, really?
    >>  ............................................
    pt  Medir por passos é preciso mesmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.craft.ask_pacing`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.craft.ask_pacing
WHO    VILLAGER — what the player reads after pressing "How accurate is pacing, really?"
       spoken on: conversations.topic.work.cartographer.craft.respond, button `ask_pacing`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.craft.ask_pacing`: the villager explains. Subject `work.cartographer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.craft.ask_pacing/1   [81 chars]
    en  Within a tenth, over a mile, on flat. On a hill it's a lie with a confident face.
    >>  ............................................
    pt  Um décimo de erro, numa milha, no plano. Num morro é mentira com cara de confiança.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.craft.ask_pacing/2   [83 chars]
    en  Accurate enough that I've never needed a chain. Slopes are where it goes to pieces.
    >>  ............................................
    pt  Preciso o bastante pra eu nunca ter precisado de corrente. Em ladeira é que desanda.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the survey."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cartographer.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.craft.respond.leave   [36 chars]
    en  I'll let you get back to the survey.
    >>  ............................................
    pt  Vou deixar você voltar ao levantamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the survey."
       spoken on: conversations.topic.work.cartographer.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cartographer.followup`

**Reached from 20 route(s):** `conversations.scene.work.cartographer.followup` / `ask_more`; `conversations.topic.work.cartographer.craft.respond` / `ask_dotted`; `conversations.topic.work.cartographer.craft.respond` / `admire`; `conversations.topic.work.cartographer.craft.respond` / `ask_pacing`; `conversations.topic.work.cartographer.future.respond` / `ask_coast`; `conversations.topic.work.cartographer.future.respond` / `encourage`; `conversations.topic.work.cartographer.future.respond` / `ask_edge`; `conversations.topic.work.cartographer.respond` / `ask_hard`; `conversations.topic.work.cartographer.respond` / `value`; `conversations.topic.work.cartographer.respond` / `challenge`; `conversations.topic.work.cartographer.respond` / `challenge`; `conversations.topic.work.cartographer.risk.respond` / `ask_wrong` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cartographer.challenge.landed` — e.g. "Some of them, yes, and I mark those differently. Look at the hatching sometime."
- `conversations.work.prof.cartographer.challenge.stung` — e.g. "...I've walked more of this map than anyone in the square has walked of the road."
- `conversations.work.prof.cartographer.craft.admire` — e.g. "It takes one bad afternoon watching somebody set off on a guess of mine."
- `conversations.work.prof.cartographer.craft.ask_dotted` — e.g. "That I was told, not that I saw. Anyone who confuses those two gets people lost."
- `conversations.work.prof.cartographer.craft.ask_pacing` — e.g. "Within a tenth, over a mile, on flat. On a hill it's a lie with a confident face."
- `conversations.work.prof.cartographer.future.ask_coast` — e.g. "The dullest one. Liars embellish and honest people describe mud."
- `conversations.work.prof.cartographer.future.ask_edge` — e.g. "White, and very tempting, and it has been the same white for eleven years."
- `conversations.work.prof.cartographer.future.encourage` — e.g. "...Said like that it sounds like a plan and not a fancy. I'll need a month."
- `conversations.work.prof.cartographer.hard` — e.g. "Somebody walks two days in the wrong direction on my word. I've done it once. Once."
- `conversations.work.prof.cartographer.risk.ask_gap` — e.g. "Four days, alone, through country I've heard bad reports of. I have not been brave enough."
- `conversations.work.prof.cartographer.risk.ask_wrong` — e.g. "Once, badly. A trader lost two days and his temper. I redrew that sheet from nothing."
- `conversations.work.prof.cartographer.risk.sympathise` — e.g. "...They do. And the more careful the drawing, the more they trust it. That is the cruel part."
- `conversations.work.prof.cartographer.task.ask_ford` — e.g. "Sixty paces downstream. Enough to drown somebody who trusted the old line."
- `conversations.work.prof.cartographer.task.ask_memory` — e.g. "Same day, always. A map drawn a week later is a map of what I wish I'd seen."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cartographer.followup   [29 chars]
    en  That's the shape of the work.
    >>  ............................................
    pt  É esse o formato do trabalho.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.challenge.landed`, `work.cartographer.challenge.stung`, `work.cartographer.craft.admire`, `work.cartographer.craft.ask_dotted`, `work.cartographer.craft.ask_pacing`, `work.cartographer.future.ask_coast`, `work.cartographer.future.ask_edge`, `work.cartographer.future.encourage`, `work.cartographer.hard`, `work.cartographer.risk.ask_gap`, `work.cartographer.risk.ask_wrong`, `work.cartographer.risk.sympathise`, `work.cartographer.task.ask_ford`, `work.cartographer.task.ask_memory`, `work.cartographer.task.offer_hands`, `work.cartographer.value`, `work.cartographer.village.ask_families`, `work.cartographer.village.ask_mayor`, `work.cartographer.village.say_thanks` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.cartographer.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `promise`
  - scored words: `thought`(1.2), `promise`(1.5), `map`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cartographer.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.cartographer.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.cartographer.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cartographer.thanks`: the villager accepts. Subject `work.cartographer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.thanks/1   [71 chars]
    en  Most folk see a decoration. It's a promise, and promises can be broken.
    >>  ............................................
    pt  O povo vê enfeite. É uma promessa, e promessa pode ser quebrada.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.thanks/2   [71 chars]
    en  A map is somebody's plan, %1$s. That's why I'm slow about drawing them.
    >>  ............................................
    pt  Um mapa é o plano de alguém, %1$s. Por isso eu demoro pra desenhar.
    >>  ............................................
```


### Button `ask_more` — "Which edge do you want to fill next?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.challenge.landed`, `work.cartographer.challenge.stung`, `work.cartographer.craft.admire`, `work.cartographer.craft.ask_dotted`, `work.cartographer.craft.ask_pacing`, `work.cartographer.future.ask_coast`, `work.cartographer.future.ask_edge`, `work.cartographer.future.encourage`, `work.cartographer.hard`, `work.cartographer.risk.ask_gap`, `work.cartographer.risk.ask_wrong`, `work.cartographer.risk.sympathise`, `work.cartographer.task.ask_ford`, `work.cartographer.task.ask_memory`, `work.cartographer.task.offer_hands`, `work.cartographer.value`, `work.cartographer.village.ask_families`, `work.cartographer.village.ask_mayor`, `work.cartographer.village.say_thanks` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.cartographer.more` — accepted phrasings: "which edge do you want to fill next"
  - the message must contain one of: `edge`, `next`, `fill`
  - scored words: `edge`(1.5), `next`(1.0), `fill`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.followup.ask_more   [36 chars]
    en  Which edge do you want to fill next?
    >>  ............................................
    pt  Qual borda você quer preencher primeiro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.cartographer.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.more
WHO    VILLAGER — what the player reads after pressing "Which edge do you want to fill next?"
       spoken on: conversations.topic.work.cartographer.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cartographer.more`: the villager discloses. Subject `work.cartographer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.more/1   [91 chars]
    en  North. There's a valley up there everyone describes differently, which means nobody's been.
    >>  ............................................
    pt  Norte. Tem um vale lá que todo mundo descreve diferente, o que quer dizer que ninguém foi.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.more/2   [64 chars]
    en  The coast, if I could get a boat and a month. I've asked. Twice.
    >>  ............................................
    pt  A costa, se eu conseguisse um barco e um mês. Já pedi. Duas vezes.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cartographer.more/1
    en  North, and I've stood at the edge of it twice with the sheet in my hand and turned round twice.
    >>  ............................................
    pt  Norte, e eu já fiquei na borda dele duas vezes com a folha na mão e voltei duas vezes.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford was wrong for a season. Nobody drowned. I have thought about that a great deal.
    >>  ............................................
    pt  O vau esteve errado por uma estação. Ninguém se afogou. Eu pensei muito nisso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cartographer.more/1
    en  North. It'll take a summer to do properly and there's no version that takes less.
    >>  ............................................
    pt  Norte. Vai levar um verão pra fazer direito e não existe versão que leve menos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved. Rivers do. I redrew it and the sheet is honest again.
    >>  ............................................
    pt  O vau se moveu. Rios se movem. Eu redesenhei e a folha voltou a ser honesta.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cartographer.more/1
    en  North. There's a valley up there everyone describes differently, which means nobody has been.
    >>  ............................................
    pt  Norte. Tem um vale lá que todos descrevem diferente, o que significa que ninguém foi.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford. It moved sixty paces downstream and my sheet lied about it for a season.
    >>  ............................................
    pt  O vau. Moveu sessenta passos rio abaixo e minha folha mentiu sobre isso por uma estação.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cartographer.more/1
    en  North. There's a valley up there everyone describes differently, which means nobody has been.
    >>  ............................................
    pt  Norte. Tem um vale lá que todos descrevem diferente, o que significa que ninguém foi.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford. It moved sixty paces downstream and my sheet lied about it for a season.
    >>  ............................................
    pt  O vau. Moveu sessenta passos rio abaixo e minha folha mentiu sobre isso por uma estação.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cartographer.more/1
    en  North. Come with me when the weather turns and we'll settle that valley between us.
    >>  ............................................
    pt  Norte. Venha comigo quando o tempo virar e a gente resolve aquele vale.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved. If you're ever going that way, take my new sheet — the old one will drown you.
    >>  ............................................
    pt  O vau se moveu. Se você for por lá, leve minha folha nova — a antiga te afoga.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cartographer.more/1
    en  North. Come with me when the weather turns and we'll settle that valley between us.
    >>  ............................................
    pt  Norte. Venha comigo quando o tempo virar e a gente resolve aquele vale.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved. If you're ever going that way, take my new sheet — the old one will drown you.
    >>  ............................................
    pt  O vau se moveu. Se você for por lá, leve minha folha nova — a antiga te afoga.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cartographer.more/1
    en  North. Come with me when the weather turns and we'll settle that valley between us.
    >>  ............................................
    pt  Norte. Venha comigo quando o tempo virar e a gente resolve aquele vale.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved. If you're ever going that way, take my new sheet — the old one will drown you.
    >>  ............................................
    pt  O vau se moveu. Se você for por lá, leve minha folha nova — a antiga te afoga.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cartographer.more/1
    en  North, and I've stood at the edge of it twice with the sheet in my hand and turned round twice.
    >>  ............................................
    pt  Norte, e eu já fiquei na borda dele duas vezes com a folha na mão e voltei duas vezes.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford was wrong for a season. Nobody drowned. I have thought about that a great deal.
    >>  ............................................
    pt  O vau esteve errado por uma estação. Ninguém se afogou. Eu pensei muito nisso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cartographer.more/1
    en  North. There's a valley up there everyone describes differently, which means nobody has been.
    >>  ............................................
    pt  Norte. Tem um vale lá que todos descrevem diferente, o que significa que ninguém foi.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford. It moved sixty paces downstream and my sheet lied about it for a season.
    >>  ............................................
    pt  O vau. Moveu sessenta passos rio abaixo e minha folha mentiu sobre isso por uma estação.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cartographer.more/1
    en  North. There's a valley up there everyone describes differently, which means nobody has been.
    >>  ............................................
    pt  Norte. Tem um vale lá que todos descrevem diferente, o que significa que ninguém foi.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford. It moved sixty paces downstream and my sheet lied about it for a season.
    >>  ............................................
    pt  O vau. Moveu sessenta passos rio abaixo e minha folha mentiu sobre isso por uma estação.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cartographer.more/1
    en  North. Three descriptions and every one contradicts the others, so I have marked it blank.
    >>  ............................................
    pt  Norte. Três descrições e cada uma contradiz as outras, então eu marquei em branco.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford. Sixty paces downstream. Enough to drown somebody who trusted the old line.
    >>  ............................................
    pt  O vau. Sessenta passos rio abaixo. O bastante pra afogar quem confiasse na linha antiga.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cartographer.more/1
    en  North. It'll take a summer to do properly and there's no version that takes less.
    >>  ............................................
    pt  Norte. Vai levar um verão pra fazer direito e não existe versão que leve menos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved. Rivers do. I redrew it and the sheet is honest again.
    >>  ............................................
    pt  O vau se moveu. Rios se movem. Eu redesenhei e a folha voltou a ser honesta.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cartographer.more/1
    en  North. Three descriptions and every one contradicts the others, so I have marked it blank.
    >>  ............................................
    pt  Norte. Três descrições e cada uma contradiz as outras, então eu marquei em branco.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford. Sixty paces downstream. Enough to drown somebody who trusted the old line.
    >>  ............................................
    pt  O vau. Sessenta passos rio abaixo. O bastante pra afogar quem confiasse na linha antiga.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cartographer.more/1
    en  North. It'll take a summer to do properly and there's no version that takes less.
    >>  ............................................
    pt  Norte. Vai levar um verão pra fazer direito e não existe versão que leve menos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved. Rivers do. I redrew it and the sheet is honest again.
    >>  ............................................
    pt  O vau se moveu. Rios se movem. Eu redesenhei e a folha voltou a ser honesta.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cartographer.more/1
    en  North! Everyone describes that valley differently, which means not one of them has actually been.
    >>  ............................................
    pt  Norte! Todos descrevem aquele vale diferente, o que significa que nenhum deles foi de fato.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved sixty paces. Sixty! Rivers have no respect for cartography whatsoever.
    >>  ............................................
    pt  O vau moveu sessenta passos. Sessenta! Rios não têm respeito nenhum pela cartografia.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cartographer.more/1
    en  North! Everyone describes that valley differently, which means not one of them has actually been.
    >>  ............................................
    pt  Norte! Todos descrevem aquele vale diferente, o que significa que nenhum deles foi de fato.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved sixty paces. Sixty! Rivers have no respect for cartography whatsoever.
    >>  ............................................
    pt  O vau moveu sessenta passos. Sessenta! Rios não têm respeito nenhum pela cartografia.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cartographer.more/1
    en  North. It'll take a summer to do properly and there's no version that takes less.
    >>  ............................................
    pt  Norte. Vai levar um verão pra fazer direito e não existe versão que leve menos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved. Rivers do. I redrew it and the sheet is honest again.
    >>  ............................................
    pt  O vau se moveu. Rios se movem. Eu redesenhei e a folha voltou a ser honesta.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cartographer.more/1
    en  North, and I've stood at the edge of it twice with the sheet in my hand and turned round twice.
    >>  ............................................
    pt  Norte, e eu já fiquei na borda dele duas vezes com a folha na mão e voltei duas vezes.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford was wrong for a season. Nobody drowned. I have thought about that a great deal.
    >>  ............................................
    pt  O vau esteve errado por uma estação. Ninguém se afogou. Eu pensei muito nisso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cartographer.more/1
    en  North. Three descriptions and every one contradicts the others, so I have marked it blank.
    >>  ............................................
    pt  Norte. Três descrições e cada uma contradiz as outras, então eu marquei em branco.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford. Sixty paces downstream. Enough to drown somebody who trusted the old line.
    >>  ............................................
    pt  O vau. Sessenta passos rio abaixo. O bastante pra afogar quem confiasse na linha antiga.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cartographer.more/1
    en  North! Everyone describes that valley differently, which means not one of them has actually been.
    >>  ............................................
    pt  Norte! Todos descrevem aquele vale diferente, o que significa que nenhum deles foi de fato.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved sixty paces. Sixty! Rivers have no respect for cartography whatsoever.
    >>  ............................................
    pt  O vau moveu sessenta passos. Sessenta! Rios não têm respeito nenhum pela cartografia.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cartographer.more/1
    en  North! Everyone describes that valley differently, which means not one of them has actually been.
    >>  ............................................
    pt  Norte! Todos descrevem aquele vale diferente, o que significa que nenhum deles foi de fato.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cartographer.more/2
    en  The ford moved sixty paces. Sixty! Rivers have no respect for cartography whatsoever.
    >>  ............................................
    pt  O vau moveu sessenta passos. Sessenta! Rios não têm respeito nenhum pela cartografia.
    >>  ............................................
```

</details>


### Button `leave` — "Fair roads."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cartographer.challenge.landed`, `work.cartographer.challenge.stung`, `work.cartographer.craft.admire`, `work.cartographer.craft.ask_dotted`, `work.cartographer.craft.ask_pacing`, `work.cartographer.future.ask_coast`, `work.cartographer.future.ask_edge`, `work.cartographer.future.encourage`, `work.cartographer.hard`, `work.cartographer.risk.ask_gap`, `work.cartographer.risk.ask_wrong`, `work.cartographer.risk.sympathise`, `work.cartographer.task.ask_ford`, `work.cartographer.task.ask_memory`, `work.cartographer.task.offer_hands`, `work.cartographer.value`, `work.cartographer.village.ask_families`, `work.cartographer.village.ask_mayor`, `work.cartographer.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.followup.leave   [11 chars]
    en  Fair roads.
    >>  ............................................
    pt  Boas estradas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "Fair roads."
       spoken on: conversations.topic.work.cartographer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cartographer.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cartographer.future` — e.g. "The coast. I've drawn it from three people's descriptions and every one contradicts the others."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cartographer.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cartographer.future.respond   [20 chars]
    en  That's the far edge.
    >>  ............................................
    pt  É a borda distante.
    >>  ............................................
```


### Button `ask_coast` — "Which description do you believe?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.future` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.future.ask_coast` — accepted phrasings: "which description do you believe"
  - the message must contain one of: `description`, `believe`, `coast`
  - scored words: `description`(1.5), `believe`(1.2), `coast`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.future.respond.ask_coast
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.future.respond.ask_coast   [33 chars]
    en  Which description do you believe?
    >>  ............................................
    pt  Em qual descrição você acredita?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.future.ask_coast`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.future.ask_coast
WHO    VILLAGER — what the player reads after pressing "Which description do you believe?"
       spoken on: conversations.topic.work.cartographer.future.respond, button `ask_coast`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.future.ask_coast`: the villager explains. Subject `work.cartographer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.future.ask_coast/1   [64 chars]
    en  The dullest one. Liars embellish and honest people describe mud.
    >>  ............................................
    pt  Na mais sem graça. Mentirosos enfeitam e gente honesta descreve lama.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.future.ask_coast/2   [60 chars]
    en  None. That's why it's dotted, and why I'll have to go, %1$s.
    >>  ............................................
    pt  Nenhuma. Por isso está pontilhada, e por isso eu vou ter que ir, %1$s.
    >>  ............................................
```


### Button `encourage` — "Then go, and take the paper."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.future` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.future.encourage` — accepted phrasings: "then go, and take the paper"
  - the message must contain one of: `paper`, `journey`
  - scored words: `go`(0.6), `paper`(1.2), `journey`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.future.respond.encourage   [28 chars]
    en  Then go, and take the paper.
    >>  ............................................
    pt  Então vá, e leve o papel.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cartographer.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cartographer.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then go, and take the paper."
       spoken on: conversations.topic.work.cartographer.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.future.encourage`: the villager accepts. Subject `work.cartographer.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.future.encourage/1   [75 chars]
    en  ...Said like that it sounds like a plan and not a fancy. I'll need a month.
    >>  ............................................
    pt  ...Dito assim parece um plano e não um devaneio. Vou precisar de um mês.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.future.encourage/2   [68 chars]
    en  Everyone tells me to be careful. You are the first to tell me to go.
    >>  ............................................
    pt  Todo mundo me manda ter cuidado. Você é o primeiro a mandar eu ir.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it's a plan. I've called it a fancy so it couldn't disappoint me.
    >>  ............................................
    pt  ...Dito assim é um plano. Chamei de capricho pra não poder me decepcionar.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. Being told to go frightens me more than the coast does.
    >>  ............................................
    pt  Todos mandam ter cuidado. Mandar ir me assusta mais que a costa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...A plan, said that way. A month. I've waited nine years for a month.
    >>  ............................................
    pt  ...Um plano, dito assim. Um mês. Esperei nove anos por um mês.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. Careful has drawn me nineteen years of one coast.
    >>  ............................................
    pt  Todos mandam ter cuidado. O cuidado me deu dezenove anos de uma costa só.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it sounds like a plan and not a fancy. I'll need a month.
    >>  ............................................
    pt  ...Dito assim soa como plano e não como capricho. Vou precisar de um mês.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go.
    >>  ............................................
    pt  Todos me dizem pra ter cuidado. Você é o primeiro a mandar eu ir.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it sounds like a plan and not a fancy. I'll need a month.
    >>  ............................................
    pt  ...Dito assim soa como plano e não como capricho. Vou precisar de um mês.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go.
    >>  ............................................
    pt  Todos me dizem pra ter cuidado. Você é o primeiro a mandar eu ir.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said your way it's a plan and not a fancy, %1$s. A month, I'd need.
    >>  ............................................
    pt  ...Dito do seu jeito é plano e não capricho, %1$s. Um mês, eu precisaria.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go.
    >>  ............................................
    pt  Todos mandam ter cuidado. Você é o primeiro a mandar ir.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said your way it's a plan and not a fancy, %1$s. A month, I'd need.
    >>  ............................................
    pt  ...Dito do seu jeito é plano e não capricho, %1$s. Um mês, eu precisaria.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go.
    >>  ............................................
    pt  Todos mandam ter cuidado. Você é o primeiro a mandar ir.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said your way it's a plan and not a fancy, %1$s. A month, I'd need.
    >>  ............................................
    pt  ...Dito do seu jeito é plano e não capricho, %1$s. Um mês, eu precisaria.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go.
    >>  ............................................
    pt  Todos mandam ter cuidado. Você é o primeiro a mandar ir.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it's a plan. I've called it a fancy so it couldn't disappoint me.
    >>  ............................................
    pt  ...Dito assim é um plano. Chamei de capricho pra não poder me decepcionar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. Being told to go frightens me more than the coast does.
    >>  ............................................
    pt  Todos mandam ter cuidado. Mandar ir me assusta mais que a costa.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it sounds like a plan and not a fancy. I'll need a month.
    >>  ............................................
    pt  ...Dito assim soa como plano e não como capricho. Vou precisar de um mês.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go.
    >>  ............................................
    pt  Todos me dizem pra ter cuidado. Você é o primeiro a mandar eu ir.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it sounds like a plan and not a fancy. I'll need a month.
    >>  ............................................
    pt  ...Dito assim soa como plano e não como capricho. Vou precisar de um mês.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go.
    >>  ............................................
    pt  Todos me dizem pra ter cuidado. Você é o primeiro a mandar eu ir.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...A plan, then. Not a fancy. A month.
    >>  ............................................
    pt  ...Um plano, então. Não capricho. Um mês.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone says be careful. You said go.
    >>  ............................................
    pt  Todos dizem tenha cuidado. Você disse vá.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...A plan, said that way. A month. I've waited nine years for a month.
    >>  ............................................
    pt  ...Um plano, dito assim. Um mês. Esperei nove anos por um mês.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. Careful has drawn me nineteen years of one coast.
    >>  ............................................
    pt  Todos mandam ter cuidado. O cuidado me deu dezenove anos de uma costa só.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...A plan, then. Not a fancy. A month.
    >>  ............................................
    pt  ...Um plano, então. Não capricho. Um mês.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone says be careful. You said go.
    >>  ............................................
    pt  Todos dizem tenha cuidado. Você disse vá.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...A plan, said that way. A month. I've waited nine years for a month.
    >>  ............................................
    pt  ...Um plano, dito assim. Um mês. Esperei nove anos por um mês.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. Careful has drawn me nineteen years of one coast.
    >>  ............................................
    pt  Todos mandam ter cuidado. O cuidado me deu dezenove anos de uma costa só.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it's a plan! Not a fancy. A plan. I'll need a month and a boat.
    >>  ............................................
    pt  ...Dito assim é um plano! Não capricho. Plano. Vou precisar de um mês e um barco.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go, and I like you for it.
    >>  ............................................
    pt  Todos mandam ter cuidado. Você é o primeiro a mandar ir, e gostei de você por isso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it's a plan! Not a fancy. A plan. I'll need a month and a boat.
    >>  ............................................
    pt  ...Dito assim é um plano! Não capricho. Plano. Vou precisar de um mês e um barco.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go, and I like you for it.
    >>  ............................................
    pt  Todos mandam ter cuidado. Você é o primeiro a mandar ir, e gostei de você por isso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...A plan, said that way. A month. I've waited nine years for a month.
    >>  ............................................
    pt  ...Um plano, dito assim. Um mês. Esperei nove anos por um mês.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. Careful has drawn me nineteen years of one coast.
    >>  ............................................
    pt  Todos mandam ter cuidado. O cuidado me deu dezenove anos de uma costa só.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it's a plan. I've called it a fancy so it couldn't disappoint me.
    >>  ............................................
    pt  ...Dito assim é um plano. Chamei de capricho pra não poder me decepcionar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. Being told to go frightens me more than the coast does.
    >>  ............................................
    pt  Todos mandam ter cuidado. Mandar ir me assusta mais que a costa.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...A plan, then. Not a fancy. A month.
    >>  ............................................
    pt  ...Um plano, então. Não capricho. Um mês.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone says be careful. You said go.
    >>  ............................................
    pt  Todos dizem tenha cuidado. Você disse vá.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it's a plan! Not a fancy. A plan. I'll need a month and a boat.
    >>  ............................................
    pt  ...Dito assim é um plano! Não capricho. Plano. Vou precisar de um mês e um barco.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go, and I like you for it.
    >>  ............................................
    pt  Todos mandam ter cuidado. Você é o primeiro a mandar ir, e gostei de você por isso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cartographer.future.encourage/1
    en  ...Said like that it's a plan! Not a fancy. A plan. I'll need a month and a boat.
    >>  ............................................
    pt  ...Dito assim é um plano! Não capricho. Plano. Vou precisar de um mês e um barco.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cartographer.future.encourage/2
    en  Everyone tells me to be careful. You're the first to tell me to go, and I like you for it.
    >>  ............................................
    pt  Todos mandam ter cuidado. Você é o primeiro a mandar ir, e gostei de você por isso.
    >>  ............................................
```

</details>


### Button `ask_edge` — "What's it like, the edge of your map?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.future` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.future.ask_edge` — accepted phrasings: "what's it like, the edge of your map"
  - the message must contain one of: `edge`, `blank`
  - scored words: `edge`(1.5), `blank`(1.5), `like`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.future.respond.ask_edge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.future.respond.ask_edge   [37 chars]
    en  What's it like, the edge of your map?
    >>  ............................................
    pt  Como é a borda do seu mapa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.future.ask_edge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.future.ask_edge
WHO    VILLAGER — what the player reads after pressing "What's it like, the edge of your map?"
       spoken on: conversations.topic.work.cartographer.future.respond, button `ask_edge`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.future.ask_edge`: the villager explains. Subject `work.cartographer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.future.ask_edge/1   [74 chars]
    en  White, and very tempting, and it has been the same white for eleven years.
    >>  ............................................
    pt  Branca, muito tentadora, e é a mesma branca há onze anos.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.future.ask_edge/2   [61 chars]
    en  It's a line I drew myself, which is the maddening part of it.
    >>  ............................................
    pt  É uma linha que eu mesmo desenhei, que é a parte enlouquecedora.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the survey."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cartographer.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.future.respond.leave   [36 chars]
    en  I'll let you get back to the survey.
    >>  ............................................
    pt  Vou deixar você voltar ao levantamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the survey."
       spoken on: conversations.topic.work.cartographer.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cartographer.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cartographer` — e.g. "I draw the world so nobody gets lost the way I once did. Every map is a small rescue."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cartographer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cartographer.respond   [37 chars]
    en  That's the table and the edges of it.
    >>  ............................................
    pt  É a mesa e as bordas dela.
    >>  ............................................
```


### Button `ask_hard` — "What happens when a map is wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.identity` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.hard` — accepted phrasings: "what happens when a map is wrong"
  - the message must contain one of: `wrong`, `mistake`, `lost`
  - scored words: `wrong`(1.5), `mistake`(1.2), `lost`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.respond.ask_hard   [33 chars]
    en  What happens when a map is wrong?
    >>  ............................................
    pt  O que acontece quando um mapa está errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.cartographer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.hard
WHO    VILLAGER — what the player reads after pressing "What happens when a map is wrong?"
       spoken on: conversations.topic.work.cartographer.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.hard`: the villager explains. Subject `work.cartographer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / ask_more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Every traveller who arrives owes you something."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.identity` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.value` — accepted phrasings: "every traveller who arrives owes you something"
  - the message must contain one of: `traveller`, `arrives`, `owes`
  - scored words: `traveller`(1.5), `arrives`(1.2), `owes`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.respond.value   [47 chars]
    en  Every traveller who arrives owes you something.
    >>  ............................................
    pt  Todo viajante que chega te deve alguma coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cartographer.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.cartographer.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.value
WHO    VILLAGER — what the player reads after pressing "Every traveller who arrives owes you something."
       spoken on: conversations.topic.work.cartographer.respond, button `value`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.value`: the villager accepts. Subject `work.cartographer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.value/1   [65 chars]
    en  Some of them even know it. Two have said so, and I remember both.
    >>  ............................................
    pt  Alguns até sabem. Dois já disseram, e eu lembro dos dois.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.value/2   [70 chars]
    en  That's a generous way to put a lot of ink and squinting. I'll take it.
    >>  ............................................
    pt  É um jeito generoso de descrever muita tinta e muito apertar os olhos. Eu aceito.
    >>  ............................................
```


### Button `challenge` — "You draw lines you've never walked."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.cartographer.identity` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.challenge` — accepted phrasings: "you draw lines you've never walked"
  - the message must contain one of: `lines`, `walked`
  - scored words: `lines`(1.5), `walked`(1.5), `never`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.respond.challenge   [35 chars]
    en  You draw lines you've never walked.
    >>  ............................................
    pt  Você desenha linhas que nunca andou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.cartographer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.cartographer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You draw lines you've never walked."
       spoken on: conversations.topic.work.cartographer.respond, button `challenge`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.challenge.landed`: the villager resists. Subject `work.cartographer.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.challenge.landed/1   [79 chars]
    en  Some of them, yes, and I mark those differently. Look at the hatching sometime.
    >>  ............................................
    pt  Algumas, sim, e essas eu marco diferente. Repare no hachurado uma hora dessas.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.challenge.landed/2   [81 chars]
    en  That's a fair hit. The honest ones among us mark what we've only been told, %1$s.
    >>  ............................................
    pt  É um golpe justo. Os honestos entre nós marcam o que só ouvimos falar, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.cartographer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.cartographer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You draw lines you've never walked."
       spoken on: conversations.topic.work.cartographer.respond, button `challenge`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.challenge.stung`: the villager resists. Subject `work.cartographer.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.challenge.stung/1   [81 chars]
    en  ...I've walked more of this map than anyone in the square has walked of the road.
    >>  ............................................
    pt  ...Eu andei mais deste mapa do que qualquer um da praça andou de estrada.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.challenge.stung/2   [74 chars]
    en  Never walked. Right. Ask me about the ford, %1$s, and then say that again.
    >>  ............................................
    pt  Nunca andei. Certo. Me pergunte sobre o vau, %1$s, e repita isso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the survey."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cartographer.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.respond.leave   [36 chars]
    en  I'll let you get back to the survey.
    >>  ............................................
    pt  Vou deixar você voltar ao levantamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the survey."
       spoken on: conversations.topic.work.cartographer.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cartographer.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cartographer.risk` — e.g. "A wrong map is worse than no map. No map makes people careful; mine makes them confident."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cartographer.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cartographer.risk.respond   [24 chars]
    en  That's the danger in it.
    >>  ............................................
    pt  É esse o perigo.
    >>  ............................................
```


### Button `ask_wrong` — "Has one of yours been wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.risk` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.risk.ask_wrong` — accepted phrasings: "has one of yours been wrong"
  - the message must contain one of: `wrong`, `mistake`
  - scored words: `wrong`(1.5), `mistake`(1.2), `yours`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.risk.respond.ask_wrong
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.risk.respond.ask_wrong   [28 chars]
    en  Has one of yours been wrong?
    >>  ............................................
    pt  Um seu já esteve errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.risk.ask_wrong`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.risk.ask_wrong
WHO    VILLAGER — what the player reads after pressing "Has one of yours been wrong?"
       spoken on: conversations.topic.work.cartographer.risk.respond, button `ask_wrong`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.risk.ask_wrong`: the villager explains. Subject `work.cartographer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.risk.ask_wrong/1   [85 chars]
    en  Once, badly. A trader lost two days and his temper. I redrew that sheet from nothing.
    >>  ............................................
    pt  Uma vez, feio. Um comerciante perdeu dois dias e a paciência. Refiz aquela folha do zero.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.risk.ask_wrong/2   [86 chars]
    en  The ford, until this week. Nobody drowned, and I have thought about that a great deal.
    >>  ............................................
    pt  O vau, até esta semana. Ninguém se afogou, e eu pensei muito nisso.
    >>  ............................................
```


### Button `sympathise` — "People trust paper more than they should."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.risk` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.risk.sympathise` — accepted phrasings: "people trust paper more than they should"
  - the message must contain one of: `trust`, `paper`
  - scored words: `trust`(1.5), `paper`(1.5), `people`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.risk.respond.sympathise   [41 chars]
    en  People trust paper more than they should.
    >>  ............................................
    pt  As pessoas confiam demais no papel.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cartographer.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cartographer.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "People trust paper more than they should."
       spoken on: conversations.topic.work.cartographer.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.risk.sympathise`: the villager accepts. Subject `work.cartographer.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.risk.sympathise/1   [93 chars]
    en  ...They do. And the more careful the drawing, the more they trust it. That is the cruel part.
    >>  ............................................
    pt  ...Confiam. E quanto mais caprichado o desenho, mais confiam. É essa a parte cruel.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.risk.sympathise/2   [71 chars]
    en  Which is why I mark the blanks large, %1$s. A blank is an honest thing.
    >>  ............................................
    pt  Por isso eu deixo as lacunas grandes, %1$s. Uma lacuna é uma coisa honesta.
    >>  ............................................
```


### Button `ask_gap` — "Why not survey the north gap?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.risk` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.risk.ask_gap` — accepted phrasings: "why not survey the north gap"
  - the message must contain one of: `gap`, `north`, `survey`
  - scored words: `gap`(1.5), `north`(1.2), `survey`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.risk.respond.ask_gap
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.risk.respond.ask_gap   [29 chars]
    en  Why not survey the north gap?
    >>  ............................................
    pt  Por que não levantar a lacuna norte?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.risk.ask_gap`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.risk.ask_gap
WHO    VILLAGER — what the player reads after pressing "Why not survey the north gap?"
       spoken on: conversations.topic.work.cartographer.risk.respond, button `ask_gap`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.risk.ask_gap`: the villager explains. Subject `work.cartographer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.risk.ask_gap/1   [90 chars]
    en  Four days, alone, through country I've heard bad reports of. I have not been brave enough.
    >>  ............................................
    pt  Quatro dias, sozinho, por terra de que ouvi relatos ruins. Não tive coragem suficiente.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.risk.ask_gap/2   [84 chars]
    en  I've stood at the edge of it twice with the sheet in my hand and turned round twice.
    >>  ............................................
    pt  Já fiquei na borda dela duas vezes com a folha na mão e voltei duas vezes.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the survey."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cartographer.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.risk.respond.leave   [36 chars]
    en  I'll let you get back to the survey.
    >>  ............................................
    pt  Vou deixar você voltar ao levantamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the survey."
       spoken on: conversations.topic.work.cartographer.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cartographer.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cartographer.task` — e.g. "Redrawing the ford. It moved in the spring floods and my map has been lying since."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cartographer.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cartographer.task.respond   [23 chars]
    en  That's the table today.
    >>  ............................................
    pt  É a mesa hoje.
    >>  ............................................
```


### Button `ask_ford` — "How far did it move?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.task` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.task.ask_ford` — accepted phrasings: "how far did it move"
  - the message must contain one of: `far`, `moved`, `ford`
  - scored words: `far`(1.2), `moved`(1.5), `ford`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.task.respond.ask_ford
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.task.respond.ask_ford   [20 chars]
    en  How far did it move?
    >>  ............................................
    pt  O quanto ele se moveu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.task.ask_ford`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.task.ask_ford
WHO    VILLAGER — what the player reads after pressing "How far did it move?"
       spoken on: conversations.topic.work.cartographer.task.respond, button `ask_ford`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.task.ask_ford`: the villager explains. Subject `work.cartographer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.task.ask_ford/1   [74 chars]
    en  Sixty paces downstream. Enough to drown somebody who trusted the old line.
    >>  ............................................
    pt  Sessenta passos rio abaixo. O bastante pra afogar quem confiasse na linha antiga.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.task.ask_ford/2   [60 chars]
    en  Not far. Far enough that the crossing is now a wading, %1$s.
    >>  ............................................
    pt  Não muito. O bastante pra travessia virar um atravessar na água, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I could walk it and report back."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.cartographer.task` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.task.offer_hands` — accepted phrasings: "i could walk it and report back"
  - the message must contain one of: `walk`, `report`, `survey`
  - scored words: `walk`(1.2), `report`(1.5), `survey`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.task.respond.offer_hands   [32 chars]
    en  I could walk it and report back.
    >>  ............................................
    pt  Eu podia andar e te reportar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cartographer.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cartographer.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could walk it and report back."
       spoken on: conversations.topic.work.cartographer.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.task.offer_hands`: the villager accepts. Subject `work.cartographer.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.task.offer_hands/1   [86 chars]
    en  ...You could. Count your paces and note the bank on both sides. Don't guess the depth.
    >>  ............................................
    pt  ...Podia. Conte seus passos e anote as duas margens. Não chute a profundidade.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.task.offer_hands/2   [89 chars]
    en  Then take the old sheet and mark where it lies. Honestly, %1$s, that would save me a day.
    >>  ............................................
    pt  Então leve a folha antiga e marque onde está. Sinceramente, %1$s, isso me pouparia um dia.
    >>  ............................................
```


### Button `ask_memory` — "You draw it all from memory?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.task` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.task.ask_memory` — accepted phrasings: "you draw it all from memory"
  - the message must contain one of: `memory`, `draw`, `later`
  - scored words: `memory`(1.5), `draw`(1.0), `later`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.task.respond.ask_memory
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.task.respond.ask_memory   [28 chars]
    en  You draw it all from memory?
    >>  ............................................
    pt  Você desenha tudo de memória?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.task.ask_memory`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.task.ask_memory
WHO    VILLAGER — what the player reads after pressing "You draw it all from memory?"
       spoken on: conversations.topic.work.cartographer.task.respond, button `ask_memory`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.task.ask_memory`: the villager explains. Subject `work.cartographer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.task.ask_memory/1   [76 chars]
    en  Same day, always. A map drawn a week later is a map of what I wish I'd seen.
    >>  ............................................
    pt  No mesmo dia, sempre. Um mapa feito uma semana depois é um mapa do que eu queria ter visto.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.task.ask_memory/2   [82 chars]
    en  Memory and a very bad sketch made in the rain. Both lie, but they lie differently.
    >>  ............................................
    pt  Memória e um rascunho péssimo feito na chuva. Os dois mentem, mas mentem diferente.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the survey."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cartographer.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.task.respond.leave   [36 chars]
    en  I'll let you get back to the survey.
    >>  ............................................
    pt  Vou deixar você voltar ao levantamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the survey."
       spoken on: conversations.topic.work.cartographer.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cartographer.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cartographer.village` — e.g. "Three families arrived here because a map of mine reached a town four valleys over."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cartographer.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cartographer.village.respond   [30 chars]
    en  That's what it does out there.
    >>  ............................................
    pt  É o que ele faz lá fora.
    >>  ............................................
```


### Button `ask_families` — "Do they know it was your map?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.village` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.village.ask_families` — accepted phrasings: "do they know it was your map"
  - the message must contain one of: `families`, `arrived`
  - scored words: `families`(1.5), `know`(0.8), `arrived`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.village.respond.ask_families
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.village.respond.ask_families   [29 chars]
    en  Do they know it was your map?
    >>  ............................................
    pt  Eles sabem que foi seu mapa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.village.ask_families`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.village.ask_families
WHO    VILLAGER — what the player reads after pressing "Do they know it was your map?"
       spoken on: conversations.topic.work.cartographer.village.respond, button `ask_families`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.village.ask_families`: the villager explains. Subject `work.cartographer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.village.ask_families/1   [86 chars]
    en  One of them does. She brought it back to show me, folded to pieces, and I nearly wept.
    >>  ............................................
    pt  Uma delas sabe. Ela trouxe de volta pra me mostrar, dobrado em pedaços, e eu quase chorei.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.village.ask_families/2   [66 chars]
    en  None of them. That's the correct amount of credit for a map, %1$s.
    >>  ............................................
    pt  Nenhum. É a quantidade certa de crédito para um mapa, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Three families came because of you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cartographer.village` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.village.say_thanks` — accepted phrasings: "three families came because of you"
  - the message must contain one of: `families`, `came`, `three`
  - scored words: `families`(1.2), `came`(1.2), `three`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.village.respond.say_thanks   [35 chars]
    en  Three families came because of you.
    >>  ............................................
    pt  Três famílias vieram por sua causa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cartographer.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cartographer.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Three families came because of you."
       spoken on: conversations.topic.work.cartographer.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.village.say_thanks`: the villager accepts. Subject `work.cartographer.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.village.say_thanks/1   [74 chars]
    en  ...It is. I'd been counting it as three sheets of paper and one long walk.
    >>  ............................................
    pt  ...É. Eu vinha contando como três folhas de papel e uma longa caminhada.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.village.say_thanks/2   [61 chars]
    en  Then I've done more with ink than most people do with a cart.
    >>  ............................................
    pt  Então eu fiz mais com tinta do que a maioria faz com uma carroça.
    >>  ............................................
```


### Button `ask_mayor` — "What do you do about the mayor?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cartographer.village` · offered only once the villager has actually said `work:cartographer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cartographer.village.ask_mayor` — accepted phrasings: "what do you do about the mayor"
  - the message must contain one of: `mayor`, `boundaries`
  - scored words: `mayor`(1.5), `boundaries`(1.2), `about`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.village.respond.ask_mayor
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.village.respond.ask_mayor   [31 chars]
    en  What do you do about the mayor?
    >>  ............................................
    pt  O que você faz sobre o prefeito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cartographer.village.ask_mayor`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cartographer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which edge do you want to fill next?" | "Fair roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.village.ask_mayor
WHO    VILLAGER — what the player reads after pressing "What do you do about the mayor?"
       spoken on: conversations.topic.work.cartographer.village.respond, button `ask_mayor`
       leaves the player on: conversations.topic.work.cartographer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.village.ask_mayor`: the villager explains. Subject `work.cartographer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cartographer.village.ask_mayor/1   [83 chars]
    en  I drew him a second sheet with no boundaries on it at all. He has not used it once.
    >>  ............................................
    pt  Desenhei uma segunda folha sem nenhuma divisa. Ele não usou nem uma vez.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer.village.ask_mayor/2   [73 chars]
    en  Nothing. A map goes where it goes. That's the price of drawing one, %1$s.
    >>  ............................................
    pt  Nada. Um mapa vai aonde vai. É o preço de desenhar um, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the survey."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cartographer.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cartographer.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cartographer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cartographer.village.respond.leave   [36 chars]
    en  I'll let you get back to the survey.
    >>  ............................................
    pt  Vou deixar você voltar ao levantamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the survey."
       spoken on: conversations.topic.work.cartographer.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.left`: the villager accepts. Subject `work.cartographer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cartographer.followup / leave; conversations.scene.work.cartographer.stalled_survey.blocked.respond / leave; conversations.scene.work.cartographer.stalled_survey.succeeded.respond / leave; conversations.scene.work.cartographer.unverified_account.active.respond / leave; conversations.scene.work.cartographer.unverified_account.succeeded.respond / leave; conversations.scene.work.cartographer.wrong_map.blocked.respond / leave; conversations.scene.work.cartographer.wrong_map.succeeded.respond / leave; conversations.topic.work.cartographer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.cartographer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

