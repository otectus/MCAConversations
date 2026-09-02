# Work talk with a mercenary

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.mercenary.followup`](#conversations-scene-work-mercenary-followup)
- [`conversations.scene.work.mercenary.job_refused.succeeded.respond`](#conversations-scene-work-mercenary-job-refused-succeeded-respond)
- [`conversations.scene.work.mercenary.reputation.active.respond`](#conversations-scene-work-mercenary-reputation-active-respond)
- [`conversations.scene.work.mercenary.reputation.succeeded.respond`](#conversations-scene-work-mercenary-reputation-succeeded-respond)
- [`conversations.scene.work.mercenary.unpaid_contract.blocked.respond`](#conversations-scene-work-mercenary-unpaid-contract-blocked-respond)
- [`conversations.scene.work.mercenary.unpaid_contract.succeeded.respond`](#conversations-scene-work-mercenary-unpaid-contract-succeeded-respond)
- [`conversations.topic.work.mercenary.craft.respond`](#conversations-topic-work-mercenary-craft-respond)
- [`conversations.topic.work.mercenary.followup`](#conversations-topic-work-mercenary-followup)
- [`conversations.topic.work.mercenary.future.respond`](#conversations-topic-work-mercenary-future-respond)
- [`conversations.topic.work.mercenary.respond`](#conversations-topic-work-mercenary-respond)
- [`conversations.topic.work.mercenary.risk.respond`](#conversations-topic-work-mercenary-risk-respond)
- [`conversations.topic.work.mercenary.task.respond`](#conversations-topic-work-mercenary-task-respond)
- [`conversations.topic.work.mercenary.village.respond`](#conversations-topic-work-mercenary-village-respond)

---

## `conversations.scene.work.mercenary.followup`

**Reached from 9 route(s):** `conversations.scene.work.mercenary.job_refused.succeeded.respond` / `ask_the_line`; `conversations.scene.work.mercenary.job_refused.succeeded.respond` / `respect_the_refusal`; `conversations.scene.work.mercenary.reputation.active.respond` / `ask_if_it_matters`; `conversations.scene.work.mercenary.reputation.active.respond` / `urge_staying`; `conversations.scene.work.mercenary.reputation.succeeded.respond` / `note_it_worked`; `conversations.scene.work.mercenary.unpaid_contract.blocked.respond` / `ask_recourse`; `conversations.scene.work.mercenary.unpaid_contract.blocked.respond` / `offer_wages`; `conversations.scene.work.mercenary.unpaid_contract.blocked.respond` / `advise_the_headman`; `conversations.scene.work.mercenary.unpaid_contract.succeeded.respond` / `note_the_lesson`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mercenary.job_refused.succeeded.acknowledged` — e.g. "It cost me a winter's fuel. I want that said plainly, because a refusal that costs nothing is not worth much."
- `conversations.scene.work.mercenary.job_refused.succeeded.explained` — e.g. "Anything where the other person cannot say no. That is the whole rule. It has never once been ambiguous when I actually applied it."
- `conversations.scene.work.mercenary.reputation.active.accepted` — e.g. "That is the only strategy that has ever worked and it takes about four years. I have three left."
- `conversations.scene.work.mercenary.reputation.active.answered` — e.g. "Less than it did. It bothers me in the specific case — one person, one turned back — and hardly at all as a general fact."
- `conversations.scene.work.mercenary.reputation.succeeded.acknowledged` — e.g. "I did. It is not a heroic way to win an argument, but it is the only one I have ever managed."
- `conversations.scene.work.mercenary.unpaid_contract.blocked.accepted` — e.g. "I will take it, and I will still go on asking him, and if he ever pays I will bring it straight to you."
- `conversations.scene.work.mercenary.unpaid_contract.blocked.considered` — e.g. "That is the right answer and it is slow and public and I will look like a woman who cannot manage her own affairs. I will do it on Thursday."
- `conversations.scene.work.mercenary.unpaid_contract.blocked.explained` — e.g. "Ask again. Ask again louder. And then decide whether I am the sort of person who does the third thing, which I would rather find out about myself slowly."
- `conversations.scene.work.mercenary.unpaid_contract.succeeded.agreed` — e.g. "Written, witnessed, and dull. Three weeks of chasing bought me a very boring habit and I am grateful for it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mercenary.followup   [15 chars]
    en  Something else?
    >>  ............................................
    pt  Alguma outra coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of being hired?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.mercenary.*` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.followup.ask_more` — accepted phrasings: "whats the hardest part of being hired"; "what is the hardest part of being hired"; "hardest thing about being hired"
  - the message must contain one of: `hardest`, `hired`
  - scored words: `hardest`(1.8), `hired`(1.8), `whats`(0.8), `part`(0.8), `being`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.followup.ask_more   [39 chars]
    en  What's the hardest part of being hired?
    >>  ............................................
    pt  Qual é a parte mais difícil de ser contratada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of being hired?"
       spoken on: conversations.scene.work.mercenary.followup, button `ask_more`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.hard`: the villager explains. Subject `work.mercenary.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.mercenary.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.mercenary.hard/1   [84 chars]
    en  Children, and burning what people live in. I've walked off two jobs over the second.
    >>  ............................................
    pt  Crianças, e queimar onde as pessoas moram. Já abandonei dois trabalhos pelo segundo.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.hard/2   [80 chars]
    en  I have one. I'd rather not list it, %1$s — saying it out loud makes it a target.
    >>  ............................................
    pt  Eu tenho uma. Prefiro não listar, %1$s — dizer em voz alta transforma em alvo.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your kit."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.mercenary.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.followup.leave   [27 chars]
    en  I'll leave you to your kit.
    >>  ............................................
    pt  Vou deixar você com o equipamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your kit."
       spoken on: conversations.scene.work.mercenary.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave; conversations.topic.work.mercenary.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.mercenary.leave/1   [13 chars]
    en  Aye. Do that.
    >>  ............................................
    pt  É. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.leave/2   [18 chars]
    en  Enough said, %1$s.
    >>  ............................................
    pt  Já foi dito, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.mercenary.job_refused.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mercenary.job_refused.succeeded` — e.g. "I was offered %2$s last month and I said no, and I have thought about the money most days since."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.job_refused.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mercenary.job_refused.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.respond   [24 chars]
    en  The job you turned down.
    >>  ............................................
    pt  O serviço que você recusou.
    >>  ............................................
```


### Button `ask_the_line` — "Where's your line, exactly?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.job_refused.succeeded` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.job_refused.succeeded.ask_the_line` — accepted phrasings: "wheres your line exactly"; "where is your line exactly"; "how do you decide what to refuse"
  - the message must contain one of: `line`, `refuse`, `decide`
  - scored words: `line`(1.8), `refuse`(1.8), `decide`(1.8), `wheres`(0.8), `exactly`(0.8), `where`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.job_refused.succeeded.respond.ask_the_line
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.job_refused.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.respond.ask_the_line   [27 chars]
    en  Where's your line, exactly?
    >>  ............................................
    pt  Onde exatamente fica o seu limite?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, respect +1  _(recorded under topic `work.mercenary.the_line`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.mercenary.job_refused"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.job_refused.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "Where's your line, exactly?"
       spoken on: conversations.scene.work.mercenary.job_refused.succeeded.respond, button `ask_the_line`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.job_refused.succeeded.explained`: the villager explains. Subject `work.mercenary.the_line`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.explained/1   [131 chars]
    en  Anything where the other person cannot say no. That is the whole rule. It has never once been ambiguous when I actually applied it.
    >>  ............................................
    pt  Qualquer coisa em que a outra pessoa não possa dizer não. É a regra inteira. Nunca foi ambígua quando eu de fato apliquei.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.explained/2   [117 chars]
    en  I ask who is frightened at the end of it. If the answer is somebody who has done nothing, I am not the tool for that.
    >>  ............................................
    pt  Eu pergunto quem fica com medo no fim. Se a resposta é alguém que não fez nada, eu não sou a ferramenta para isso.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.explained/3   [125 chars]
    en  It moves, and I would rather admit that than claim a stone tablet. Two years ago it was in a different place and I was worse.
    >>  ............................................
    pt  Ele se move, e prefiro admitir isso a alegar uma tábua de pedra. Dois anos atrás estava em outro lugar e eu era pior.
    >>  ............................................
```


### Button `respect_the_refusal` — "Refusing cost you something."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.mercenary.job_refused.succeeded` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.job_refused.succeeded.respect_the_refusal` — accepted phrasings: "refusing cost you something"; "refusing cost you something real"; "that refusal had a price for you"
  - the message must contain one of: `refusing`, `cost`, `price`
  - scored words: `refusing`(1.8), `cost`(1.8), `price`(1.8), `something`(0.8), `real`(0.8), `refusal`(0.8), `had`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.job_refused.succeeded.respond.respect_the_refusal
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.job_refused.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.respond.respect_the_refusal   [28 chars]
    en  Refusing cost you something.
    >>  ............................................
    pt  Recusar custou algo a você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mercenary.line.respected`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.mercenary.the_line`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.mercenary.job_refused"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.job_refused.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Refusing cost you something."
       spoken on: conversations.scene.work.mercenary.job_refused.succeeded.respond, button `respect_the_refusal`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.job_refused.succeeded.acknowledged`: the villager accepts. Subject `work.mercenary.the_line`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.acknowledged/1   [109 chars]
    en  It cost me a winter's fuel. I want that said plainly, because a refusal that costs nothing is not worth much.
    >>  ............................................
    pt  Custou o combustível de um inverno. Quero isso dito com todas as letras, porque uma recusa que não custa nada não vale muito.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.acknowledged/2   [118 chars]
    en  Thank you. People either treat it as obvious or as showing off, and it was neither. It was arithmetic I did not enjoy.
    >>  ............................................
    pt  Obrigada. As pessoas tratam isso como óbvio ou como exibição, e não foi nem um nem outro. Foi uma conta que não me deu prazer.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.acknowledged/3   [129 chars]
    en  The worst part is knowing it got done anyway. All I bought was that it was not me, and I have decided that is still worth buying.
    >>  ............................................
    pt  O pior é saber que foi feito de qualquer jeito. Tudo o que comprei foi não ter sido eu, e decidi que ainda vale comprar.
    >>  ............................................
```


### Button `leave` — "I'll let you see to your gear."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mercenary.job_refused.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.job_refused.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.job_refused.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded.respond.leave   [30 chars]
    en  I'll let you see to your gear.
    >>  ............................................
    pt  Vou deixar você cuidar do equipamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you see to your gear."
       spoken on: conversations.scene.work.mercenary.job_refused.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave; conversations.topic.work.mercenary.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mercenary.reputation.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mercenary.reputation.active` — e.g. "Conversation at %2$s goes quiet by about a quarter when I sit down. I have measured it, which is a bleak way to spend an evening."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mercenary.reputation.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.active.respond   [20 chars]
    en  How people take you.
    >>  ............................................
    pt  Como te veem.
    >>  ............................................
```


### Button `ask_if_it_matters` — "Does it bother you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.reputation.active` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.reputation.active.ask_if_it_matters` — accepted phrasings: "does it bother you"; "does it bother you much"; "how much does that sting"
  - the message must contain one of: `bother`, `sting`
  - scored words: `bother`(1.8), `sting`(1.8), `does`(0.8), `much`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.active.respond.ask_if_it_matters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.reputation.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.active.respond.ask_if_it_matters   [19 chars]
    en  Does it bother you?
    >>  ............................................
    pt  Isso te incomoda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.reputation`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mercenary.reputation"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.active.answered
WHO    VILLAGER — what the player reads after pressing "Does it bother you?"
       spoken on: conversations.scene.work.mercenary.reputation.active.respond, button `ask_if_it_matters`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.reputation.active.answered`: the villager explains. Subject `work.mercenary.reputation`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.active.answered/1   [121 chars]
    en  Less than it did. It bothers me in the specific case — one person, one turned back — and hardly at all as a general fact.
    >>  ............................................
    pt  Menos do que antes. Me incomoda no caso específico — uma pessoa, uma virada de costas — e quase nada como fato geral.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.active.answered/2   [130 chars]
    en  On a good week it is a coat I wear. On a bad week I am eleven years old again and standing in a doorway nobody invited me through.
    >>  ............................................
    pt  Numa semana boa é um casaco que eu visto. Numa semana ruim eu tenho onze anos de novo, parada numa porta para a qual ninguém me chamou.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.active.answered/3   [140 chars]
    en  Yes. And I would rather say yes than perform the woman who does not care, because that woman is the reason they are wary in the first place.
    >>  ............................................
    pt  Sim. E prefiro dizer sim a encenar a mulher que não liga, porque essa mulher é o motivo de eles desconfiarem, para começar.
    >>  ............................................
```


### Button `urge_staying` — "Keep sitting down there anyway."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.mercenary.reputation.active` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.reputation.active.urge_staying` — accepted phrasings: "keep sitting down there anyway"; "keep sitting down there anyway"; "carry on showing up there"
  - the message must contain one of: `sitting`, `showing`, `carry`
  - scored words: `sitting`(1.8), `showing`(1.8), `carry`(1.8), `keep`(0.8), `down`(0.8), `anyway`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.active.respond.urge_staying
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.reputation.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.active.respond.urge_staying   [31 chars]
    en  Keep sitting down there anyway.
    >>  ............................................
    pt  Continue sentando lá mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.mercenary.reputation`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mercenary.reputation"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.active.accepted
WHO    VILLAGER — what the player reads after pressing "Keep sitting down there anyway."
       spoken on: conversations.scene.work.mercenary.reputation.active.respond, button `urge_staying`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.reputation.active.accepted`: the villager accepts. Subject `work.mercenary.reputation`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.active.accepted/1   [96 chars]
    en  That is the only strategy that has ever worked and it takes about four years. I have three left.
    >>  ............................................
    pt  É a única estratégia que já funcionou e leva uns quatro anos. Faltam três.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.active.accepted/2   [101 chars]
    en  I will. Presence is cheaper than argument and it wins slower, and I have nowhere in particular to be.
    >>  ............................................
    pt  Vou. Presença é mais barata que discussão e vence mais devagar, e eu não tenho para onde ir.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.active.accepted/3   [116 chars]
    en  Right. And I will keep buying my round, because the round is the argument, and it does not need me to make a speech.
    >>  ............................................
    pt  Certo. E vou continuar pagando minha rodada, porque a rodada é o argumento, e ela não precisa que eu faça discurso.
    >>  ............................................
```


### Button `leave` — "I'll let you see to your gear."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mercenary.reputation.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.reputation.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.active.respond.leave   [30 chars]
    en  I'll let you see to your gear.
    >>  ............................................
    pt  Vou deixar você cuidar do equipamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you see to your gear."
       spoken on: conversations.scene.work.mercenary.reputation.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave; conversations.topic.work.mercenary.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mercenary.reputation.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mercenary.reputation.succeeded` — e.g. "Somebody kept a seat for me at %2$s. A seat. I have thought about it more than I would like to admit."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mercenary.reputation.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.succeeded.respond   [19 chars]
    en  The tavern, lately.
    >>  ............................................
    pt  A taverna, ultimamente.
    >>  ............................................
```


### Button `note_it_worked` — "You outlasted it."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.mercenary.reputation.succeeded` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.reputation.succeeded.note_it_worked` — accepted phrasings: "you outlasted it"; "you outlasted it"; "you simply outlasted their opinion"
  - the message must contain one of: `outlasted`, `opinion`
  - scored words: `outlasted`(1.8), `opinion`(1.8), `simply`(0.8), `their`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.succeeded.respond.note_it_worked
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.reputation.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.succeeded.respond.note_it_worked   [17 chars]
    en  You outlasted it.
    >>  ............................................
    pt  Você aguentou mais que aquilo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.mercenary.reputation`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.mercenary.reputation"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You outlasted it."
       spoken on: conversations.scene.work.mercenary.reputation.succeeded.respond, button `note_it_worked`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.reputation.succeeded.acknowledged`: the villager accepts. Subject `work.mercenary.reputation`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.succeeded.acknowledged/1   [93 chars]
    en  I did. It is not a heroic way to win an argument, but it is the only one I have ever managed.
    >>  ............................................
    pt  Aguentei. Não é um jeito heroico de ganhar uma discussão, mas é o único que eu já consegui.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.succeeded.acknowledged/2   [97 chars]
    en  Thank you. I want to be clear that I nearly left twice, and the second time I had the bag packed.
    >>  ............................................
    pt  Obrigada. Quero deixar claro que quase fui embora duas vezes, e na segunda a mala estava pronta.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.succeeded.acknowledged/3   [93 chars]
    en  Outlasting is the whole trade, in the end. Standing somewhere longer than the objection does.
    >>  ............................................
    pt  Aguentar é o ofício inteiro, no fim. Ficar em algum lugar mais tempo do que a objeção fica.
    >>  ............................................
```


### Button `leave` — "I'll let you see to your gear."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mercenary.reputation.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.reputation.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.succeeded.respond.leave   [30 chars]
    en  I'll let you see to your gear.
    >>  ............................................
    pt  Vou deixar você cuidar do equipamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you see to your gear."
       spoken on: conversations.scene.work.mercenary.reputation.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave; conversations.topic.work.mercenary.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mercenary.unpaid_contract.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mercenary.unpaid_contract.blocked` — e.g. "I finished %2$s for %3$s and I have not been paid, and it has been three weeks of very polite letters."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mercenary.unpaid_contract.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond   [13 chars]
    en  The last job.
    >>  ............................................
    pt  O último serviço.
    >>  ............................................
```


### Button `ask_recourse` — "What can you actually do about it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.unpaid_contract.blocked` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.unpaid_contract.blocked.ask_recourse` — accepted phrasings: "what can you actually do about it"; "what can you actually do about it"; "is there any recourse for you"
  - the message must contain one of: `recourse`, `actually`, `about`
  - scored words: `recourse`(1.8), `actually`(1.8), `about`(1.8), `any`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond.ask_recourse
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.unpaid_contract.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond.ask_recourse   [34 chars]
    en  What can you actually do about it?
    >>  ............................................
    pt  O que você pode de fato fazer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.contract`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mercenary.unpaid_contract"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What can you actually do about it?"
       spoken on: conversations.scene.work.mercenary.unpaid_contract.blocked.respond, button `ask_recourse`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.unpaid_contract.blocked.explained`: the villager explains. Subject `work.mercenary.contract`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.explained/1   [153 chars]
    en  Ask again. Ask again louder. And then decide whether I am the sort of person who does the third thing, which I would rather find out about myself slowly.
    >>  ............................................
    pt  Pedir de novo. Pedir de novo mais alto. E então decidir se sou o tipo de pessoa que faz a terceira coisa, o que prefiro descobrir devagar.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.explained/2   [152 chars]
    en  Very little, honestly. My whole trade rests on being frightening, and the moment I use that to collect a debt I am the thing everybody already suspects.
    >>  ............................................
    pt  Muito pouco, sinceramente. Meu ofício inteiro se apoia em ser assustadora, e no instante em que uso isso para cobrar eu viro o que todo mundo já suspeita.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.explained/3   [142 chars]
    en  There is a headman who could rule on it. %2$s knows there is a headman too, which is why the letters have gone unanswered rather than refused.
    >>  ............................................
    pt  Existe um chefe que poderia decidir. %2$s também sabe que existe um chefe, e por isso as cartas ficaram sem resposta em vez de recusadas.
    >>  ............................................
```


### Button `offer_wages` — "I'll cover the emeralds you're owed."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.mercenary.unpaid_contract.blocked` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.unpaid_contract.blocked.offer_wages` — accepted phrasings: "ill cover the emeralds youre owed"; "i can cover the emeralds you are owed"; "let me pay you the emeralds"
  - the message must contain one of: `emeralds`, `cover`, `pay`
  - scored words: `emeralds`(1.8), `cover`(1.8), `pay`(1.8), `ill`(0.8), `youre`(0.8), `owed`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond.offer_wages
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.unpaid_contract.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond.offer_wages   [36 chars]
    en  I'll cover the emeralds you're owed.
    >>  ............................................
    pt  Eu cubro as esmeraldas que te devem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.mercenary.contract.covered`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.mercenary.contract`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.unpaid_contract", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.mercenary.unpaid_contract", "obligation": "commitment:work.mercenary.bring_wages"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.mercenary.bring_wages"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll cover the emeralds you're owed."
       spoken on: conversations.scene.work.mercenary.unpaid_contract.blocked.respond, button `offer_wages`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.unpaid_contract.blocked.accepted`: the villager accepts. Subject `work.mercenary.contract`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.accepted/1   [103 chars]
    en  I will take it, and I will still go on asking him, and if he ever pays I will bring it straight to you.
    >>  ............................................
    pt  Vou aceitar, e vou continuar cobrando dele, e se ele um dia pagar eu trago direto para você.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.accepted/2   [110 chars]
    en  That is not your debt. I am accepting anyway, because pride has cost me three weeks and has bought me nothing.
    >>  ............................................
    pt  A dívida não é sua. Estou aceitando mesmo assim, porque o orgulho já me custou três semanas e não me comprou nada.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.accepted/3   [89 chars]
    en  Yes. And understand that I am now in your debt instead, and I am careful about who I owe.
    >>  ............................................
    pt  Sim. E entenda que agora estou em dívida com você, e eu sou cuidadosa com quem eu devo.
    >>  ............................................
```


### Button `advise_the_headman` — "Take it to the headman."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mercenary.unpaid_contract.blocked` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.unpaid_contract.blocked.advise_the_headman` — accepted phrasings: "take it to the headman"; "take it to the headman"; "let the headman rule on it"
  - the message must contain one of: `headman`, `rule`
  - scored words: `headman`(1.8), `rule`(1.8), `take`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond.advise_the_headman
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.unpaid_contract.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond.advise_the_headman   [23 chars]
    en  Take it to the headman.
    >>  ............................................
    pt  Leve isso ao chefe da vila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.mercenary.contract`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mercenary.unpaid_contract"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.considered
WHO    VILLAGER — what the player reads after pressing "Take it to the headman."
       spoken on: conversations.scene.work.mercenary.unpaid_contract.blocked.respond, button `advise_the_headman`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.unpaid_contract.blocked.considered`: the villager accepts. Subject `work.mercenary.contract`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.considered/1   [140 chars]
    en  That is the right answer and it is slow and public and I will look like a woman who cannot manage her own affairs. I will do it on Thursday.
    >>  ............................................
    pt  É a resposta certa, e é lenta e pública, e vou parecer uma mulher que não dá conta dos próprios assuntos. Vou fazer na quinta.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.considered/2   [110 chars]
    en  Yes. Better a slow ruling than a fast reputation. I have watched what the fast one does to people in my trade.
    >>  ............................................
    pt  Sim. Melhor uma decisão lenta do que uma reputação rápida. Já vi o que a rápida faz com gente do meu ofício.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.considered/3   [132 chars]
    en  I had been avoiding it because it makes the whole thing everybody's business. You are right that it is already everybody's business.
    >>  ............................................
    pt  Eu vinha evitando porque isso torna a coisa toda assunto de todo mundo. Você tem razão que já é assunto de todo mundo.
    >>  ............................................
```


### Button `leave` — "I'll let you see to your gear."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mercenary.unpaid_contract.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.unpaid_contract.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked.respond.leave   [30 chars]
    en  I'll let you see to your gear.
    >>  ............................................
    pt  Vou deixar você cuidar do equipamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you see to your gear."
       spoken on: conversations.scene.work.mercenary.unpaid_contract.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave; conversations.topic.work.mercenary.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mercenary.unpaid_contract.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mercenary.unpaid_contract.succeeded` — e.g. "Settled. Not generously, not with an apology, but settled, and %2$s is off my books."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mercenary.unpaid_contract.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.respond   [10 chars]
    en  That debt.
    >>  ............................................
    pt  Aquela dívida.
    >>  ............................................
```


### Button `note_the_lesson` — "Written terms from now on, then."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.mercenary.unpaid_contract.succeeded` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mercenary.unpaid_contract.succeeded.note_the_lesson` — accepted phrasings: "written terms from now on then"; "written terms from now on then"; "you will write the terms down next time"
  - the message must contain one of: `written`, `terms`, `write`
  - scored words: `written`(1.8), `terms`(1.8), `write`(1.8), `from`(0.8), `now`(0.8), `down`(0.8), `next`(0.8), `time`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.respond.note_the_lesson
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.unpaid_contract.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.respond.note_the_lesson   [32 chars]
    en  Written terms from now on, then.
    >>  ............................................
    pt  Termos escritos de agora em diante, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.mercenary.contract`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.mercenary.unpaid_contract"}
- Then opens: `conversations.scene.work.mercenary.followup`
- …where the player's next choices will be: "What's the hardest part of being hired?" | "I'll leave you to your kit."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.agreed
WHO    VILLAGER — what the player reads after pressing "Written terms from now on, then."
       spoken on: conversations.scene.work.mercenary.unpaid_contract.succeeded.respond, button `note_the_lesson`
       leaves the player on: conversations.scene.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.unpaid_contract.succeeded.agreed`: the villager accepts. Subject `work.mercenary.contract`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.agreed/1   [108 chars]
    en  Written, witnessed, and dull. Three weeks of chasing bought me a very boring habit and I am grateful for it.
    >>  ............................................
    pt  Escritos, testemunhados e chatos. Três semanas de cobrança me compraram um hábito muito entediante, e sou grata por ele.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.agreed/2   [106 chars]
    en  From now on. And the people who object to signing are exactly the people I have learned to walk away from.
    >>  ............................................
    pt  De agora em diante. E as pessoas que se ofendem em assinar são exatamente as de quem eu aprendi a me afastar.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.agreed/3   [127 chars]
    en  I resisted it for years because it felt like calling everybody a liar. It turns out it is the opposite — it lets me trust them.
    >>  ............................................
    pt  Resisti anos porque parecia chamar todo mundo de mentiroso. Acontece que é o contrário — permite que eu confie neles.
    >>  ............................................
```


### Button `leave` — "I'll let you see to your gear."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mercenary.unpaid_contract.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mercenary.unpaid_contract.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded.respond.leave   [30 chars]
    en  I'll let you see to your gear.
    >>  ............................................
    pt  Vou deixar você cuidar do equipamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you see to your gear."
       spoken on: conversations.scene.work.mercenary.unpaid_contract.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave; conversations.topic.work.mercenary.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mercenary.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mercenary.craft` — e.g. "Half of it is fighting and half is knowing which contracts to refuse. The second half took longer."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mercenary.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mercenary.craft.respond   [21 chars]
    en  That's the sum of it.
    >>  ............................................
    pt  É a soma disso.
    >>  ............................................
```


### Button `ask_refuse` — "How do you tell which to refuse?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.craft` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.craft.ask_refuse` — accepted phrasings: "how do you tell which to refuse"
  - the message must contain one of: `refuse`, `decline`
  - scored words: `refuse`(1.5), `tell`(0.8), `decline`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.craft.respond.ask_refuse
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.craft.respond.ask_refuse   [32 chars]
    en  How do you tell which to refuse?
    >>  ............................................
    pt  Como você sabe quais recusar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.craft.ask_refuse`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.craft.ask_refuse
WHO    VILLAGER — what the player reads after pressing "How do you tell which to refuse?"
       spoken on: conversations.topic.work.mercenary.craft.respond, button `ask_refuse`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.craft.ask_refuse`: the villager explains. Subject `work.mercenary.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.craft.ask_refuse/1   [80 chars]
    en  If they won't say who's on the other side, the other side is somebody's village.
    >>  ............................................
    pt  Se não dizem quem está do outro lado, o outro lado é o vilarejo de alguém.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.craft.ask_refuse/2   [84 chars]
    en  If the pay is high for the work described, %1$s, the work described is not the work.
    >>  ............................................
    pt  Se o pagamento é alto pro serviço descrito, %1$s, o serviço descrito não é o serviço.
    >>  ............................................
```


### Button `admire` — "Learning what to refuse is the part nobody trains for."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mercenary.craft` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.craft.admire` — accepted phrasings: "learning what to refuse is the part nobody trains for"
  - the message must contain one of: `refuse`, `trains`, `learning`
  - scored words: `refuse`(1.2), `trains`(1.5), `learning`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.craft.respond.admire   [54 chars]
    en  Learning what to refuse is the part nobody trains for.
    >>  ............................................
    pt  Aprender o que recusar é a parte pra qual ninguém treina.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mercenary.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mercenary.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.craft.admire
WHO    VILLAGER — what the player reads after pressing "Learning what to refuse is the part nobody trains for."
       spoken on: conversations.topic.work.mercenary.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.craft.admire`: the villager accepts. Subject `work.mercenary.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.craft.admire/1   [78 chars]
    en  No, and it's the part that decides whether you're a soldier or something else.
    >>  ............................................
    pt  Não, e é a parte que decide se você é soldado ou outra coisa.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.craft.admire/2   [80 chars]
    en  The captain who taught me is one of the eleven, %1$s. That is not a coincidence.
    >>  ............................................
    pt  O capitão que me ensinou é um dos onze, %1$s. Não é coincidência.
    >>  ............................................
```


### Button `ask_eleven` — "Eleven of forty?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.craft` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.craft.ask_eleven` — accepted phrasings: "eleven of forty"
  - the message must contain one of: `eleven`, `forty`, `company`
  - scored words: `eleven`(1.5), `forty`(1.2), `company`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.craft.respond.ask_eleven
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.craft.respond.ask_eleven   [16 chars]
    en  Eleven of forty?
    >>  ............................................
    pt  Onze de quarenta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.craft.ask_eleven`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.craft.ask_eleven
WHO    VILLAGER — what the player reads after pressing "Eleven of forty?"
       spoken on: conversations.topic.work.mercenary.craft.respond, button `ask_eleven`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.craft.ask_eleven`: the villager explains. Subject `work.mercenary.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.craft.ask_eleven/1   [87 chars]
    en  Over nineteen years. Most of it wasn't battle — it was fever, and roads, and bad water.
    >>  ............................................
    pt  Ao longo de dezenove anos. A maior parte não foi batalha — foi febre, estradas e água ruim.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.craft.ask_eleven/2   [91 chars]
    en  Eleven I can name. There may be more, %1$s, and I've never had the courage to go and count.
    >>  ............................................
    pt  Onze que eu sei nomear. Pode haver mais, %1$s, e eu nunca tive coragem de ir contar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mercenary.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.craft.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.mercenary.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.followup / leave; conversations.topic.work.mercenary.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mercenary.followup`

**Reached from 20 route(s):** `conversations.scene.work.mercenary.followup` / `ask_more`; `conversations.topic.work.mercenary.craft.respond` / `ask_refuse`; `conversations.topic.work.mercenary.craft.respond` / `admire`; `conversations.topic.work.mercenary.craft.respond` / `ask_eleven`; `conversations.topic.work.mercenary.future.respond` / `ask_company`; `conversations.topic.work.mercenary.future.respond` / `encourage`; `conversations.topic.work.mercenary.future.respond` / `ask_wage`; `conversations.topic.work.mercenary.respond` / `ask_hard`; `conversations.topic.work.mercenary.respond` / `value`; `conversations.topic.work.mercenary.respond` / `challenge`; `conversations.topic.work.mercenary.respond` / `challenge`; `conversations.topic.work.mercenary.risk.respond` / `ask_year` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mercenary.challenge.landed` — e.g. "I do. It's a cleaner arrangement than most people's loyalties, if you look at it plainly."
- `conversations.work.prof.mercenary.challenge.stung` — e.g. "...And you eat food you didn't grow. We're all somebody's arrangement."
- `conversations.work.prof.mercenary.craft.admire` — e.g. "No, and it's the part that decides whether you're a soldier or something else."
- `conversations.work.prof.mercenary.craft.ask_eleven` — e.g. "Over nineteen years. Most of it wasn't battle — it was fever, and roads, and bad water."
- `conversations.work.prof.mercenary.craft.ask_refuse` — e.g. "If they won't say who's on the other side, the other side is somebody's village."
- `conversations.work.prof.mercenary.future.ask_company` — e.g. "Because I know what I'd say if I answered quickly, and I'd like to be somebody who answers slowly."
- `conversations.work.prof.mercenary.future.ask_wage` — e.g. "A wage means somebody expects me next week. A purse means the arrangement was finished on delivery."
- `conversations.work.prof.mercenary.future.encourage` — e.g. "...Ask him. He'd refuse and he'd think about it afterwards, which is more than nothing."
- `conversations.work.prof.mercenary.hard` — e.g. "Children, and burning what people live in. I've walked off two jobs over the second."
- `conversations.work.prof.mercenary.risk.ask_children` — e.g. "Now? Yes. At twenty-six, when I was in that company? I would not have, and that's the answer."
- `conversations.work.prof.mercenary.risk.ask_year` — e.g. "I've had that month. I walked away on the ninth day and I ate very badly for a season."
- `conversations.work.prof.mercenary.risk.sympathise` — e.g. "...It does. It's easier than arguing, and easier than being the man who argues about that."
- `conversations.work.prof.mercenary.task.ask_contract` — e.g. "Escort, ideally. It's honest, it's dull, and nobody's family gets a visit afterwards."
- `conversations.work.prof.mercenary.task.ask_habit` — e.g. "Because the day I let it go is the day I've decided something about myself I'm not ready to decide."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mercenary.followup   [32 chars]
    en  That's the trade, told straight.
    >>  ............................................
    pt  É o ofício, dito direto.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mercenary.challenge.landed`, `work.mercenary.challenge.stung`, `work.mercenary.craft.admire`, `work.mercenary.craft.ask_eleven`, `work.mercenary.craft.ask_refuse`, `work.mercenary.future.ask_company`, `work.mercenary.future.ask_wage`, `work.mercenary.future.encourage`, `work.mercenary.hard`, `work.mercenary.risk.ask_children`, `work.mercenary.risk.ask_year`, `work.mercenary.risk.sympathise`, `work.mercenary.task.ask_contract`, `work.mercenary.task.ask_habit`, `work.mercenary.task.offer_hands`, `work.mercenary.value`, `work.mercenary.village.ask_guard`, `work.mercenary.village.ask_unpaid`, `work.mercenary.village.say_thanks` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.mercenary.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `assuming`
  - scored words: `thought`(1.2), `assuming`(1.5), `asked`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.mercenary.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.mercenary.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.mercenary.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.mercenary.thanks`: the villager accepts. Subject `work.mercenary.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.thanks/1   [60 chars]
    en  Few do. It's easier to think of me as a rumour with a sword.
    >>  ............................................
    pt  Poucos pensam. É mais fácil me tratar como um boato com espada.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.thanks/2   [66 chars]
    en  You asked instead of assuming, %1$s. That's rarer than good steel.
    >>  ............................................
    pt  Você perguntou em vez de supor, %1$s. Isso é mais raro que bom aço.
    >>  ............................................
```


### Button `ask_more` — "What was the last contract?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.challenge.landed`, `work.mercenary.challenge.stung`, `work.mercenary.craft.admire`, `work.mercenary.craft.ask_eleven`, `work.mercenary.craft.ask_refuse`, `work.mercenary.future.ask_company`, `work.mercenary.future.ask_wage`, `work.mercenary.future.encourage`, `work.mercenary.hard`, `work.mercenary.risk.ask_children`, `work.mercenary.risk.ask_year`, `work.mercenary.risk.sympathise`, `work.mercenary.task.ask_contract`, `work.mercenary.task.ask_habit`, `work.mercenary.task.offer_hands`, `work.mercenary.value`, `work.mercenary.village.ask_guard`, `work.mercenary.village.ask_unpaid`, `work.mercenary.village.say_thanks` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.mercenary.more` — accepted phrasings: "what was the last contract"
  - the message must contain one of: `last`, `contract`, `job`
  - scored words: `last`(1.2), `contract`(1.0), `job`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.followup.ask_more   [27 chars]
    en  What was the last contract?
    >>  ............................................
    pt  Qual foi o último contrato?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.mercenary.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.more
WHO    VILLAGER — what the player reads after pressing "What was the last contract?"
       spoken on: conversations.topic.work.mercenary.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.mercenary.more`: the villager discloses. Subject `work.mercenary.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.more/1   [90 chars]
    en  Escort work, two valleys over. Dull, well paid, and everyone came home. My favourite kind.
    >>  ............................................
    pt  Escolta, dois vales adiante. Sem graça, bem paga, e todos voltaram pra casa. Meu tipo favorito.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.more/2   [89 chars]
    en  I'd rather not say. Not because it was bad — because the man who hired me is still alive.
    >>  ............................................
    pt  Prefiro não dizer. Não porque foi ruim — porque o homem que me contratou ainda está vivo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, and everyone came home. I count that one more often than I'd admit.
    >>  ............................................
    pt  Escolta, e todos voltaram pra casa. Eu conto essa mais vezes do que admito.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. There's no bottom under a contract, and I've been standing on nothing for nineteen years.
    >>  ............................................
    pt  Um posto. Não tem fundo sob um contrato, e eu estou de pé sobre nada há dezenove anos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work. Dull is the highest praise I have for a contract and I mean it kindly.
    >>  ............................................
    pt  Escolta. Maçante é o maior elogio que eu tenho pra um contrato e eu falo com carinho.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mercenary.more/2
    en  A post, one day. There's a company reforming in the spring and I've not answered them yet.
    >>  ............................................
    pt  Um posto, um dia. Tem uma companhia se reformando na primavera e eu ainda não respondi.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, two valleys over. Dull, well paid, and everyone came home. My favourite kind.
    >>  ............................................
    pt  Escolta, a dois vales. Maçante, bem paga, e todos voltaram pra casa. Meu tipo favorito.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. Anybody's. I'd take a wage and a name over a contract and a purse, and I'd take it today.
    >>  ............................................
    pt  Um posto. De qualquer um. Trocaria salário e nome por contrato e bolsa, e trocaria hoje.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, two valleys over. Dull, well paid, and everyone came home. My favourite kind.
    >>  ............................................
    pt  Escolta, a dois vales. Maçante, bem paga, e todos voltaram pra casa. Meu tipo favorito.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. Anybody's. I'd take a wage and a name over a contract and a purse, and I'd take it today.
    >>  ............................................
    pt  Um posto. De qualquer um. Trocaria salário e nome por contrato e bolsa, e trocaria hoje.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, two valleys over. Everyone came home. That's the sentence I'd want on the contract.
    >>  ............................................
    pt  Escolta, a dois vales. Todos voltaram pra casa. É a frase que eu queria no contrato.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mercenary.more/2
    en  A post here. I'd rather be somebody this village expects next week than somebody it paid last week.
    >>  ............................................
    pt  Um posto aqui. Prefiro ser alguém que este vilarejo espera semana que vem a alguém que pagou semana passada.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, two valleys over. Everyone came home. That's the sentence I'd want on the contract.
    >>  ............................................
    pt  Escolta, a dois vales. Todos voltaram pra casa. É a frase que eu queria no contrato.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mercenary.more/2
    en  A post here. I'd rather be somebody this village expects next week than somebody it paid last week.
    >>  ............................................
    pt  Um posto aqui. Prefiro ser alguém que este vilarejo espera semana que vem a alguém que pagou semana passada.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, two valleys over. Everyone came home. That's the sentence I'd want on the contract.
    >>  ............................................
    pt  Escolta, a dois vales. Todos voltaram pra casa. É a frase que eu queria no contrato.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mercenary.more/2
    en  A post here. I'd rather be somebody this village expects next week than somebody it paid last week.
    >>  ............................................
    pt  Um posto aqui. Prefiro ser alguém que este vilarejo espera semana que vem a alguém que pagou semana passada.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, and everyone came home. I count that one more often than I'd admit.
    >>  ............................................
    pt  Escolta, e todos voltaram pra casa. Eu conto essa mais vezes do que admito.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. There's no bottom under a contract, and I've been standing on nothing for nineteen years.
    >>  ............................................
    pt  Um posto. Não tem fundo sob um contrato, e eu estou de pé sobre nada há dezenove anos.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, two valleys over. Dull, well paid, and everyone came home. My favourite kind.
    >>  ............................................
    pt  Escolta, a dois vales. Maçante, bem paga, e todos voltaram pra casa. Meu tipo favorito.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. Anybody's. I'd take a wage and a name over a contract and a purse, and I'd take it today.
    >>  ............................................
    pt  Um posto. De qualquer um. Trocaria salário e nome por contrato e bolsa, e trocaria hoje.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, two valleys over. Dull, well paid, and everyone came home. My favourite kind.
    >>  ............................................
    pt  Escolta, a dois vales. Maçante, bem paga, e todos voltaram pra casa. Meu tipo favorito.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. Anybody's. I'd take a wage and a name over a contract and a purse, and I'd take it today.
    >>  ............................................
    pt  Um posto. De qualquer um. Trocaria salário e nome por contrato e bolsa, e trocaria hoje.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work. Two valleys, ten days, and nothing happened. That is what a good contract looks like.
    >>  ............................................
    pt  Escolta. Dois vales, dez dias, e nada aconteceu. É assim que um bom contrato parece.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. A wage means somebody expects me next week. A purse means it finished on delivery.
    >>  ............................................
    pt  Um posto. Salário significa que alguém me espera semana que vem. Bolsa significa que acabou na entrega.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work. Dull is the highest praise I have for a contract and I mean it kindly.
    >>  ............................................
    pt  Escolta. Maçante é o maior elogio que eu tenho pra um contrato e eu falo com carinho.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mercenary.more/2
    en  A post, one day. There's a company reforming in the spring and I've not answered them yet.
    >>  ............................................
    pt  Um posto, um dia. Tem uma companhia se reformando na primavera e eu ainda não respondi.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work. Two valleys, ten days, and nothing happened. That is what a good contract looks like.
    >>  ............................................
    pt  Escolta. Dois vales, dez dias, e nada aconteceu. É assim que um bom contrato parece.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. A wage means somebody expects me next week. A purse means it finished on delivery.
    >>  ............................................
    pt  Um posto. Salário significa que alguém me espera semana que vem. Bolsa significa que acabou na entrega.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work. Dull is the highest praise I have for a contract and I mean it kindly.
    >>  ............................................
    pt  Escolta. Maçante é o maior elogio que eu tenho pra um contrato e eu falo com carinho.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mercenary.more/2
    en  A post, one day. There's a company reforming in the spring and I've not answered them yet.
    >>  ............................................
    pt  Um posto, um dia. Tem uma companhia se reformando na primavera e eu ainda não respondi.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work! Dull, well paid, everyone came home. You can't ask more of a fortnight than that.
    >>  ............................................
    pt  Escolta! Maçante, bem paga, todos voltaram. Não dá pra pedir mais de uma quinzena.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. Any post. I'd sweep a hall if the sweeping came with a wage and a name on a list.
    >>  ............................................
    pt  Um posto. Qualquer um. Eu varreria um salão se varrer viesse com salário e nome numa lista.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work! Dull, well paid, everyone came home. You can't ask more of a fortnight than that.
    >>  ............................................
    pt  Escolta! Maçante, bem paga, todos voltaram. Não dá pra pedir mais de uma quinzena.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. Any post. I'd sweep a hall if the sweeping came with a wage and a name on a list.
    >>  ............................................
    pt  Um posto. Qualquer um. Eu varreria um salão se varrer viesse com salário e nome numa lista.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work. Dull is the highest praise I have for a contract and I mean it kindly.
    >>  ............................................
    pt  Escolta. Maçante é o maior elogio que eu tenho pra um contrato e eu falo com carinho.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mercenary.more/2
    en  A post, one day. There's a company reforming in the spring and I've not answered them yet.
    >>  ............................................
    pt  Um posto, um dia. Tem uma companhia se reformando na primavera e eu ainda não respondi.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work, and everyone came home. I count that one more often than I'd admit.
    >>  ............................................
    pt  Escolta, e todos voltaram pra casa. Eu conto essa mais vezes do que admito.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. There's no bottom under a contract, and I've been standing on nothing for nineteen years.
    >>  ............................................
    pt  Um posto. Não tem fundo sob um contrato, e eu estou de pé sobre nada há dezenove anos.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work. Two valleys, ten days, and nothing happened. That is what a good contract looks like.
    >>  ............................................
    pt  Escolta. Dois vales, dez dias, e nada aconteceu. É assim que um bom contrato parece.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. A wage means somebody expects me next week. A purse means it finished on delivery.
    >>  ............................................
    pt  Um posto. Salário significa que alguém me espera semana que vem. Bolsa significa que acabou na entrega.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work! Dull, well paid, everyone came home. You can't ask more of a fortnight than that.
    >>  ............................................
    pt  Escolta! Maçante, bem paga, todos voltaram. Não dá pra pedir mais de uma quinzena.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. Any post. I'd sweep a hall if the sweeping came with a wage and a name on a list.
    >>  ............................................
    pt  Um posto. Qualquer um. Eu varreria um salão se varrer viesse com salário e nome numa lista.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mercenary.more/1
    en  Escort work! Dull, well paid, everyone came home. You can't ask more of a fortnight than that.
    >>  ............................................
    pt  Escolta! Maçante, bem paga, todos voltaram. Não dá pra pedir mais de uma quinzena.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mercenary.more/2
    en  A post. Any post. I'd sweep a hall if the sweeping came with a wage and a name on a list.
    >>  ............................................
    pt  Um posto. Qualquer um. Eu varreria um salão se varrer viesse com salário e nome numa lista.
    >>  ............................................
```

</details>


### Button `leave` — "Keep your coin."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mercenary.challenge.landed`, `work.mercenary.challenge.stung`, `work.mercenary.craft.admire`, `work.mercenary.craft.ask_eleven`, `work.mercenary.craft.ask_refuse`, `work.mercenary.future.ask_company`, `work.mercenary.future.ask_wage`, `work.mercenary.future.encourage`, `work.mercenary.hard`, `work.mercenary.risk.ask_children`, `work.mercenary.risk.ask_year`, `work.mercenary.risk.sympathise`, `work.mercenary.task.ask_contract`, `work.mercenary.task.ask_habit`, `work.mercenary.task.offer_hands`, `work.mercenary.value`, `work.mercenary.village.ask_guard`, `work.mercenary.village.ask_unpaid`, `work.mercenary.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.followup.leave   [15 chars]
    en  Keep your coin.
    >>  ............................................
    pt  Guarde suas moedas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "Keep your coin."
       spoken on: conversations.topic.work.mercenary.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mercenary.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mercenary.future` — e.g. "A post. Anybody's. I'd take a wage and a name over a contract and a purse, and I'd take it today."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mercenary.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mercenary.future.respond   [22 chars]
    en  That's the fork ahead.
    >>  ............................................
    pt  É a bifurcação à frente.
    >>  ............................................
```


### Button `ask_company` — "Why haven't you answered?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.future` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.future.ask_company` — accepted phrasings: "why haven't you answered"
  - the message must contain one of: `answered`, `company`, `spring`
  - scored words: `answered`(1.5), `company`(1.2), `spring`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.future.respond.ask_company
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.future.respond.ask_company   [25 chars]
    en  Why haven't you answered?
    >>  ............................................
    pt  Por que você não respondeu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.future.ask_company`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.future.ask_company
WHO    VILLAGER — what the player reads after pressing "Why haven't you answered?"
       spoken on: conversations.topic.work.mercenary.future.respond, button `ask_company`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.future.ask_company`: the villager explains. Subject `work.mercenary.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.future.ask_company/1   [98 chars]
    en  Because I know what I'd say if I answered quickly, and I'd like to be somebody who answers slowly.
    >>  ............................................
    pt  Porque eu sei o que eu diria se respondesse rápido, e eu queria ser alguém que responde devagar.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.future.ask_company/2   [78 chars]
    en  Because the captain's dead and I don't know who's giving the orders now, %1$s.
    >>  ............................................
    pt  Porque o capitão morreu e eu não sei quem dá as ordens agora, %1$s.
    >>  ............................................
```


### Button `encourage` — "Ask the guard for the post before spring."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mercenary.future` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.future.encourage` — accepted phrasings: "ask the guard for the post before spring"
  - the message must contain one of: `post`, `before`, `spring`
  - scored words: `post`(1.5), `before`(1.0), `spring`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.future.respond.encourage   [41 chars]
    en  Ask the guard for the post before spring.
    >>  ............................................
    pt  Peça o posto ao guarda antes da primavera.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mercenary.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mercenary.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.future.encourage
WHO    VILLAGER — what the player reads after pressing "Ask the guard for the post before spring."
       spoken on: conversations.topic.work.mercenary.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.future.encourage`: the villager accepts. Subject `work.mercenary.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.future.encourage/1   [87 chars]
    en  ...Ask him. He'd refuse and he'd think about it afterwards, which is more than nothing.
    >>  ............................................
    pt  ...Pedir a ele. Ele recusaria e pensaria depois, o que é mais que nada.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.future.encourage/2   [80 chars]
    en  That's the last conversation I'd choose and possibly the one that matters, %1$s.
    >>  ............................................
    pt  É a última conversa que eu escolheria e possivelmente a que importa, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse, and I'd rather be refused than go on not asking.
    >>  ............................................
    pt  ...Pergunte a ele. Recusaria, e prefiro ser recusado a continuar sem pedir.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose. Which is probably why it's the one.
    >>  ............................................
    pt  É a última conversa que eu escolheria. Que é provavelmente por isso que é a certa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. Men like him refuse first and reconsider slowly; I've been one.
    >>  ............................................
    pt  ...Pergunte a ele. Homens como ele recusam primeiro e reconsideram devagar; já fui um.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose. The ones you avoid are usually the ones.
    >>  ............................................
    pt  É a última conversa que eu escolheria. As que se evita costumam ser as certas.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse and he'd think about it afterwards, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele. Ele recusaria e pensaria depois, o que já é mais que nada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e possivelmente a que importa.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse and he'd think about it afterwards, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele. Ele recusaria e pensaria depois, o que já é mais que nada.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e possivelmente a que importa.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him, %1$s. He'd refuse and think about it afterwards, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele, %1$s. Recusaria e pensaria depois, o que já é mais que nada.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e possivelmente a que importa.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him, %1$s. He'd refuse and think about it afterwards, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele, %1$s. Recusaria e pensaria depois, o que já é mais que nada.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e possivelmente a que importa.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him, %1$s. He'd refuse and think about it afterwards, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele, %1$s. Recusaria e pensaria depois, o que já é mais que nada.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e possivelmente a que importa.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse, and I'd rather be refused than go on not asking.
    >>  ............................................
    pt  ...Pergunte a ele. Recusaria, e prefiro ser recusado a continuar sem pedir.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose. Which is probably why it's the one.
    >>  ............................................
    pt  É a última conversa que eu escolheria. Que é provavelmente por isso que é a certa.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse and he'd think about it afterwards, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele. Ele recusaria e pensaria depois, o que já é mais que nada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e possivelmente a que importa.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse and he'd think about it afterwards, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele. Ele recusaria e pensaria depois, o que já é mais que nada.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e possivelmente a que importa.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse and think on it.
    >>  ............................................
    pt  ...Pergunte a ele. Recusaria e pensaria.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  Last conversation I'd choose. Probably the one that counts.
    >>  ............................................
    pt  Última conversa que eu escolheria. Provavelmente a que conta.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. Men like him refuse first and reconsider slowly; I've been one.
    >>  ............................................
    pt  ...Pergunte a ele. Homens como ele recusam primeiro e reconsideram devagar; já fui um.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose. The ones you avoid are usually the ones.
    >>  ............................................
    pt  É a última conversa que eu escolheria. As que se evita costumam ser as certas.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse and think on it.
    >>  ............................................
    pt  ...Pergunte a ele. Recusaria e pensaria.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  Last conversation I'd choose. Probably the one that counts.
    >>  ............................................
    pt  Última conversa que eu escolheria. Provavelmente a que conta.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. Men like him refuse first and reconsider slowly; I've been one.
    >>  ............................................
    pt  ...Pergunte a ele. Homens como ele recusam primeiro e reconsideram devagar; já fui um.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose. The ones you avoid are usually the ones.
    >>  ............................................
    pt  É a última conversa que eu escolheria. As que se evita costumam ser as certas.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him! He'd refuse and then think about it for a week, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele! Recusaria e depois pensaria uma semana, o que já é mais que nada.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and quite possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e bem possivelmente a que importa.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him! He'd refuse and then think about it for a week, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele! Recusaria e depois pensaria uma semana, o que já é mais que nada.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and quite possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e bem possivelmente a que importa.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. Men like him refuse first and reconsider slowly; I've been one.
    >>  ............................................
    pt  ...Pergunte a ele. Homens como ele recusam primeiro e reconsideram devagar; já fui um.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose. The ones you avoid are usually the ones.
    >>  ............................................
    pt  É a última conversa que eu escolheria. As que se evita costumam ser as certas.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse, and I'd rather be refused than go on not asking.
    >>  ............................................
    pt  ...Pergunte a ele. Recusaria, e prefiro ser recusado a continuar sem pedir.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose. Which is probably why it's the one.
    >>  ............................................
    pt  É a última conversa que eu escolheria. Que é provavelmente por isso que é a certa.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him. He'd refuse and think on it.
    >>  ............................................
    pt  ...Pergunte a ele. Recusaria e pensaria.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  Last conversation I'd choose. Probably the one that counts.
    >>  ............................................
    pt  Última conversa que eu escolheria. Provavelmente a que conta.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him! He'd refuse and then think about it for a week, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele! Recusaria e depois pensaria uma semana, o que já é mais que nada.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and quite possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e bem possivelmente a que importa.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mercenary.future.encourage/1
    en  ...Ask him! He'd refuse and then think about it for a week, which is more than nothing.
    >>  ............................................
    pt  ...Pergunte a ele! Recusaria e depois pensaria uma semana, o que já é mais que nada.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mercenary.future.encourage/2
    en  That's the last conversation I'd choose and quite possibly the one that matters.
    >>  ............................................
    pt  É a última conversa que eu escolheria e bem possivelmente a que importa.
    >>  ............................................
```

</details>


### Button `ask_wage` — "What's the difference between a wage and a purse?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.future` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.future.ask_wage` — accepted phrasings: "what's the difference between a wage and a purse"
  - the message must contain one of: `wage`, `purse`, `difference`
  - scored words: `wage`(1.5), `purse`(1.5), `difference`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.future.respond.ask_wage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.future.respond.ask_wage   [49 chars]
    en  What's the difference between a wage and a purse?
    >>  ............................................
    pt  Qual a diferença entre salário e bolsa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.future.ask_wage`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.future.ask_wage
WHO    VILLAGER — what the player reads after pressing "What's the difference between a wage and a purse?"
       spoken on: conversations.topic.work.mercenary.future.respond, button `ask_wage`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.future.ask_wage`: the villager explains. Subject `work.mercenary.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.future.ask_wage/1   [99 chars]
    en  A wage means somebody expects me next week. A purse means the arrangement was finished on delivery.
    >>  ............................................
    pt  Salário significa que alguém me espera semana que vem. Bolsa significa que acabou na entrega.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.future.ask_wage/2   [97 chars]
    en  One has my name written next to it, %1$s. That's the whole of the difference and it's everything.
    >>  ............................................
    pt  Um tem meu nome escrito do lado, %1$s. É toda a diferença e é tudo.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mercenary.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.future.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.mercenary.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mercenary.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mercenary` — e.g. "Coin for steel, steel for coin. I keep my contracts and my blade clean. One of those is hard."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mercenary.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mercenary.respond   [34 chars]
    en  That's the contract and the blade.
    >>  ............................................
    pt  É o contrato e a lâmina.
    >>  ............................................
```


### Button `ask_hard` — "Where's the line you won't cross?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.identity` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.hard` — accepted phrasings: "where's the line you won't cross"
  - the message must contain one of: `line`, `cross`, `refuse`
  - scored words: `line`(1.5), `cross`(1.5), `refuse`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.respond.ask_hard   [33 chars]
    en  Where's the line you won't cross?
    >>  ............................................
    pt  Qual a linha que você não cruza?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.mercenary.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.hard
WHO    VILLAGER — what the player reads after pressing "Where's the line you won't cross?"
       spoken on: conversations.topic.work.mercenary.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.hard`: the villager explains. Subject `work.mercenary.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / ask_more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You've stayed here longer than a contract explains."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mercenary.identity` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.value` — accepted phrasings: "you've stayed here longer than a contract explains"
  - the message must contain one of: `stayed`, `longer`
  - scored words: `stayed`(1.5), `longer`(1.2), `contract`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.respond.value   [51 chars]
    en  You've stayed here longer than a contract explains.
    >>  ............................................
    pt  Você ficou aqui mais tempo do que um contrato explica.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mercenary.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.mercenary.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.value
WHO    VILLAGER — what the player reads after pressing "You've stayed here longer than a contract explains."
       spoken on: conversations.topic.work.mercenary.respond, button `value`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.value`: the villager accepts. Subject `work.mercenary.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.value/1   [49 chars]
    en  ...I have. I've been avoiding thinking about why.
    >>  ............................................
    pt  ...Fiquei. Venho evitando pensar no porquê.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.value/2   [63 chars]
    en  Noticed that, did you. Nobody else has, and they've had a year.
    >>  ............................................
    pt  Reparou, é. Mais ninguém reparou, e tiveram um ano.
    >>  ............................................
```


### Button `challenge` — "You fight for whoever pays."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.mercenary.identity` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.challenge` — accepted phrasings: "you fight for whoever pays"
  - the message must contain one of: `pays`, `coin`, `loyalty`
  - scored words: `pays`(1.5), `coin`(1.2), `loyalty`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.respond.challenge   [27 chars]
    en  You fight for whoever pays.
    >>  ............................................
    pt  Você luta por quem paga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.mercenary.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.mercenary.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You fight for whoever pays."
       spoken on: conversations.topic.work.mercenary.respond, button `challenge`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.challenge.landed`: the villager resists. Subject `work.mercenary.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.challenge.landed/1   [89 chars]
    en  I do. It's a cleaner arrangement than most people's loyalties, if you look at it plainly.
    >>  ............................................
    pt  Luto. É um arranjo mais limpo que a lealdade da maioria, se você olhar sem rodeio.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.challenge.landed/2   [85 chars]
    en  Whoever pays and whoever I'd not be ashamed of. That second part costs me work, %1$s.
    >>  ............................................
    pt  Quem paga e quem não me deixa envergonhado. Essa segunda parte me custa trabalho, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.mercenary.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.mercenary.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You fight for whoever pays."
       spoken on: conversations.topic.work.mercenary.respond, button `challenge`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.challenge.stung`: the villager resists. Subject `work.mercenary.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.challenge.stung/1   [70 chars]
    en  ...And you eat food you didn't grow. We're all somebody's arrangement.
    >>  ............................................
    pt  ...E você come comida que não plantou. Somos todos o arranjo de alguém.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.challenge.stung/2   [67 chars]
    en  Whoever pays. Aye. That'll be why I turned down the last two, then.
    >>  ............................................
    pt  Quem paga. É. Deve ser por isso que eu recusei os dois últimos, então.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mercenary.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.mercenary.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mercenary.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mercenary.risk` — e.g. "The risk isn't dying. It's the year you're hungry enough to take the contract you'd have refused."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mercenary.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mercenary.risk.respond   [23 chars]
    en  That's the true danger.
    >>  ............................................
    pt  É esse o perigo real.
    >>  ............................................
```


### Button `ask_year` — "Have you had that year?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.risk` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.risk.ask_year` — accepted phrasings: "have you had that year"
  - the message must contain one of: `year`, `hungry`, `tempted`
  - scored words: `year`(1.2), `hungry`(1.5), `tempted`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.risk.respond.ask_year
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.risk.respond.ask_year   [23 chars]
    en  Have you had that year?
    >>  ............................................
    pt  Você já teve esse ano?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.risk.ask_year`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.risk.ask_year
WHO    VILLAGER — what the player reads after pressing "Have you had that year?"
       spoken on: conversations.topic.work.mercenary.risk.respond, button `ask_year`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.risk.ask_year`: the villager explains. Subject `work.mercenary.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.risk.ask_year/1   [86 chars]
    en  I've had that month. I walked away on the ninth day and I ate very badly for a season.
    >>  ............................................
    pt  Tive esse mês. Eu fui embora no nono dia e comi muito mal por uma estação.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.risk.ask_year/2   [93 chars]
    en  Twice I've read a contract twice. Both times I put it down, %1$s, and I count those as close.
    >>  ............................................
    pt  Duas vezes eu li um contrato duas vezes. Nas duas eu larguei, %1$s, e eu conto como perto.
    >>  ............................................
```


### Button `sympathise` — "Calling both of those fair costs you something."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mercenary.risk` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.risk.sympathise` — accepted phrasings: "calling both of those fair costs you something"
  - the message must contain one of: `fair`, `costs`
  - scored words: `fair`(1.5), `costs`(1.2), `both`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.risk.respond.sympathise   [47 chars]
    en  Calling both of those fair costs you something.
    >>  ............................................
    pt  Chamar as duas de justas te custa algo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.mercenary.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mercenary.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Calling both of those fair costs you something."
       spoken on: conversations.topic.work.mercenary.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.risk.sympathise`: the villager accepts. Subject `work.mercenary.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.risk.sympathise/1   [90 chars]
    en  ...It does. It's easier than arguing, and easier than being the man who argues about that.
    >>  ............................................
    pt  ...Custa. É mais fácil que discutir, e mais fácil que ser o homem que discute isso.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.risk.sympathise/2   [95 chars]
    en  It's the honest reading and it is not a comfortable one, %1$s. Thank you for noticing the cost.
    >>  ............................................
    pt  É a leitura honesta e não é confortável, %1$s. Obrigado por reparar no custo.
    >>  ............................................
```


### Button `ask_children` — "Would you leave you alone with children?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.risk` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.risk.ask_children` — accepted phrasings: "would you leave you alone with children"
  - the message must contain one of: `children`, `alone`, `trust`
  - scored words: `children`(1.5), `alone`(1.2), `trust`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.risk.respond.ask_children
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.risk.respond.ask_children   [40 chars]
    en  Would you leave you alone with children?
    >>  ............................................
    pt  Você se deixaria sozinho com crianças?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.risk.ask_children`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.risk.ask_children
WHO    VILLAGER — what the player reads after pressing "Would you leave you alone with children?"
       spoken on: conversations.topic.work.mercenary.risk.respond, button `ask_children`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.risk.ask_children`: the villager explains. Subject `work.mercenary.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.risk.ask_children/1   [93 chars]
    en  Now? Yes. At twenty-six, when I was in that company? I would not have, and that's the answer.
    >>  ............................................
    pt  Agora? Sim. Aos vinte e seis, naquela companhia? Não deixaria, e é essa a resposta.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.risk.ask_children/2   [82 chars]
    en  It's the right question and nobody has asked it to my face before, %1$s. Yes. Now.
    >>  ............................................
    pt  É a pergunta certa e ninguém tinha feito na minha cara, %1$s. Sim. Agora.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mercenary.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.risk.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.mercenary.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mercenary.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mercenary.task` — e.g. "Waiting on a contract that hasn't come. Nine days now, which is nine days of eating and no earning."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mercenary.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mercenary.task.respond   [16 chars]
    en  That's the week.
    >>  ............................................
    pt  É a semana.
    >>  ............................................
```


### Button `ask_contract` — "What sort of contract are you waiting for?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.task` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.task.ask_contract` — accepted phrasings: "what sort of contract are you waiting for"
  - the message must contain one of: `contract`, `sort`, `escort`
  - scored words: `contract`(1.5), `sort`(1.0), `escort`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.task.respond.ask_contract
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.task.respond.ask_contract   [42 chars]
    en  What sort of contract are you waiting for?
    >>  ............................................
    pt  Que tipo de contrato você espera?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.task.ask_contract`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.task.ask_contract
WHO    VILLAGER — what the player reads after pressing "What sort of contract are you waiting for?"
       spoken on: conversations.topic.work.mercenary.task.respond, button `ask_contract`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.task.ask_contract`: the villager explains. Subject `work.mercenary.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.task.ask_contract/1   [85 chars]
    en  Escort, ideally. It's honest, it's dull, and nobody's family gets a visit afterwards.
    >>  ............................................
    pt  Escolta, de preferência. É honesto, é maçante, e nenhuma família recebe visita depois.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.task.ask_contract/2   [86 chars]
    en  One I can take, %1$s. That list is shorter than people assume and it's why I'm hungry.
    >>  ............................................
    pt  Um que eu possa aceitar, %1$s. Essa lista é menor do que imaginam e por isso eu passo fome.
    >>  ............................................
```


### Button `offer_hands` — "There's harvest labour going if you'd take it."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.mercenary.task` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.task.offer_hands` — accepted phrasings: "there's harvest labour going if you'd take it"
  - the message must contain one of: `harvest`, `labour`
  - scored words: `harvest`(1.5), `labour`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.task.respond.offer_hands   [46 chars]
    en  There's harvest labour going if you'd take it.
    >>  ............................................
    pt  Tem trabalho de colheita se você aceitar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.mercenary.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mercenary.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "There's harvest labour going if you'd take it."
       spoken on: conversations.topic.work.mercenary.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.task.offer_hands`: the villager accepts. Subject `work.mercenary.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.task.offer_hands/1   [90 chars]
    en  ...I'd take it. I'd take it and I'd not be too proud, and thank you for saying it plainly.
    >>  ............................................
    pt  ...Eu aceitaria. Aceitaria e não seria orgulhoso demais, e obrigado por dizer direto.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.task.offer_hands/2   [81 chars]
    en  Harvest pays a third of escort and it pays this week, %1$s. That arithmetic wins.
    >>  ............................................
    pt  Colheita paga um terço de escolta e paga esta semana, %1$s. Essa conta ganha.
    >>  ............................................
```


### Button `ask_habit` — "Why keep the kit clean for nothing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.task` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.task.ask_habit` — accepted phrasings: "why keep the kit clean for nothing"
  - the message must contain one of: `habit`, `clean`, `kit`
  - scored words: `habit`(1.2), `clean`(1.5), `kit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.task.respond.ask_habit
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.task.respond.ask_habit   [35 chars]
    en  Why keep the kit clean for nothing?
    >>  ............................................
    pt  Por que manter o equipamento limpo à toa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.task.ask_habit`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.task.ask_habit
WHO    VILLAGER — what the player reads after pressing "Why keep the kit clean for nothing?"
       spoken on: conversations.topic.work.mercenary.task.respond, button `ask_habit`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.task.ask_habit`: the villager explains. Subject `work.mercenary.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.task.ask_habit/1   [99 chars]
    en  Because the day I let it go is the day I've decided something about myself I'm not ready to decide.
    >>  ............................................
    pt  Porque o dia em que eu largar é o dia em que eu decidi algo sobre mim que eu não estou pronto pra decidir.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.task.ask_habit/2   [65 chars]
    en  Because it takes an hour and I have nine days, %1$s. Mostly that.
    >>  ............................................
    pt  Porque leva uma hora e eu tenho nove dias, %1$s. Principalmente isso.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mercenary.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.task.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.mercenary.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mercenary.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mercenary.village` — e.g. "I've done two things for this place and been paid for one. It's the unpaid one they don't know about."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mercenary.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mercenary.village.respond   [26 chars]
    en  That's where I stand here.
    >>  ............................................
    pt  É onde eu fico aqui.
    >>  ............................................
```


### Button `ask_unpaid` — "What was the unpaid one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.village` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.village.ask_unpaid` — accepted phrasings: "what was the unpaid one"
  - the message must contain one of: `unpaid`, `quietly`
  - scored words: `unpaid`(1.5), `other`(0.6), `quietly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.village.respond.ask_unpaid
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.village.respond.ask_unpaid   [24 chars]
    en  What was the unpaid one?
    >>  ............................................
    pt  Qual foi a não paga?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.village.ask_unpaid`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.village.ask_unpaid
WHO    VILLAGER — what the player reads after pressing "What was the unpaid one?"
       spoken on: conversations.topic.work.mercenary.village.respond, button `ask_unpaid`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.village.ask_unpaid`: the villager explains. Subject `work.mercenary.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.village.ask_unpaid/1   [87 chars]
    en  Two men on the east road who were going to come here. They went somewhere else instead.
    >>  ............................................
    pt  Dois homens na estrada leste que viriam pra cá. Foram pra outro lugar.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.village.ask_unpaid/2   [79 chars]
    en  I turned down a contract that had this valley in it, %1$s. Those go unrecorded.
    >>  ............................................
    pt  Recusei um contrato que tinha este vale dentro, %1$s. Esses não ficam registrados.
    >>  ............................................
```


### Button `say_thanks` — "The unpaid one counts. Somebody should know it."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mercenary.village` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.village.say_thanks` — accepted phrasings: "the unpaid one counts. somebody should know it"
  - the message must contain one of: `counts`, `unpaid`
  - scored words: `counts`(1.5), `unpaid`(1.0), `know`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.village.respond.say_thanks   [47 chars]
    en  The unpaid one counts. Somebody should know it.
    >>  ............................................
    pt  A não paga conta. Alguém devia saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mercenary.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mercenary.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "The unpaid one counts. Somebody should know it."
       spoken on: conversations.topic.work.mercenary.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.village.say_thanks`: the villager accepts. Subject `work.mercenary.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.village.say_thanks/1   [74 chars]
    en  ...Somebody now does. I'd not planned that when this conversation started.
    >>  ............................................
    pt  ...Agora alguém sabe. Eu não tinha planejado isso quando a conversa começou.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.village.say_thanks/2   [83 chars]
    en  It counts to me. It counting to somebody else is new, %1$s, and I'll need a minute.
    >>  ............................................
    pt  Conta pra mim. Contar pra outra pessoa é novo, %1$s, e eu vou precisar de um minuto.
    >>  ............................................
```


### Button `ask_guard` — "Why would the guard rather you left?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mercenary.village` · offered only once the villager has actually said `work:mercenary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mercenary.village.ask_guard` — accepted phrasings: "why would the guard rather you left"
  - the message must contain one of: `guard`, `left`
  - scored words: `guard`(1.5), `left`(1.0), `rather`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.village.respond.ask_guard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.village.respond.ask_guard   [36 chars]
    en  Why would the guard rather you left?
    >>  ............................................
    pt  Por que o guarda preferia que você fosse embora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mercenary.village.ask_guard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mercenary.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What was the last contract?" | "Keep your coin."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.village.ask_guard
WHO    VILLAGER — what the player reads after pressing "Why would the guard rather you left?"
       spoken on: conversations.topic.work.mercenary.village.respond, button `ask_guard`
       leaves the player on: conversations.topic.work.mercenary.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.village.ask_guard`: the villager explains. Subject `work.mercenary.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mercenary.village.ask_guard/1   [89 chars]
    en  Because I'm a man with a sword he didn't appoint. In his place I'd feel exactly the same.
    >>  ............................................
    pt  Porque eu sou um homem com espada que ele não nomeou. No lugar dele eu sentiria igual.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.village.ask_guard/2   [105 chars]
    en  Because he's responsible for everyone and I'm responsible for a contract, %1$s. That's a real difference.
    >>  ............................................
    pt  Porque ele responde por todos e eu respondo por um contrato, %1$s. É uma diferença real.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mercenary.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mercenary.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mercenary.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mercenary.village.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.mercenary.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.left`: the villager accepts. Subject `work.mercenary.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mercenary.followup / leave; conversations.scene.work.mercenary.job_refused.succeeded.respond / leave; conversations.scene.work.mercenary.reputation.active.respond / leave; conversations.scene.work.mercenary.reputation.succeeded.respond / leave; conversations.scene.work.mercenary.unpaid_contract.blocked.respond / leave; conversations.scene.work.mercenary.unpaid_contract.succeeded.respond / leave; conversations.topic.work.mercenary.craft.respond / leave; conversations.topic.work.mercenary.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.mercenary.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

