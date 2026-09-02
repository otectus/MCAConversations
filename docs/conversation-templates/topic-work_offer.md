# Topic: work_offer

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `work_offer` |
| Opened from | question `conversations.cat.profession`, button `work_offer` |
| Depth class (its heart budget) | `service` |
| Returns to | `conversations.cat.profession` |
| Ages that can reach it | adult |
| Stance families it must offer | `curiosity`, `practical_help`, `respectful_disagreement`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.profession`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.profession.work_offer
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.profession
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.profession.work_offer   [24 chars]
    en  Anything you need doing?
    >>  ............................................
    pt  Precisa que eu faça alguma coisa?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.topic.work_offer.followup`](#conversations-topic-work-offer-followup)
- [`conversations.topic.work_offer.none.respond`](#conversations-topic-work-offer-none-respond)
- [`conversations.topic.work_offer.respond`](#conversations-topic-work-offer-respond)

---

## `conversations.topic.work_offer.followup`

**Reached from 3 route(s):** `conversations.topic.work_offer.respond` / `ask_needed`; `conversations.topic.work_offer.respond` / `ask_terms`; `conversations.topic.work_offer.respond` / `ask_terms`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work_offer.ask_needed` — e.g. "Straightforward enough. I'll show you the whole of it if you're in."
- `conversations.work_offer.ask_terms` — e.g. "Fair question. I'd ask it too. You'll be paid properly, %1$s."
- `conversations.work_offer.ask_terms.hard_bargain` — e.g. "Now that's the right question, and you're the first today to ask it before saying yes."


```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work_offer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work_offer.followup   [26 chars]
    en  That's the job. Your call.
    >>  ............................................
    pt  É esse o serviço. Você decide.
    >>  ............................................
```


### Button `accept` — "Alright. I'll take it on."

*stance family `restraint` · tone `plain` · answers the beat(s) `work_offer.ask_needed.to.work_offer`, `work_offer.ask_terms.hard_bargain.to.work_offer`, `work_offer.ask_terms.to.work_offer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work_offer.accept_after` — accepted phrasings: "alright, i will take it on"; "agreed"; "i will take it on"
  - the message must contain one of: `take`, `alright`, `agreed`
  - scored words: `take`(1.5), `on`(0.4), `alright`(1.0), `agreed`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.followup.accept
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.followup.accept   [25 chars]
    en  Alright. I'll take it on.
    >>  ............................................
    pt  Certo. Vou assumir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +2  _(recorded under topic `work_offer.accept`)_
- Does: `conversations_quest_open` = {"mode": "menu"}
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.accept
WHO    VILLAGER — what the player reads after pressing "Alright. I'll take it on."
       spoken on: conversations.topic.work_offer.followup, button `accept`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.accept.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.work_offer.respond / accept
```

```text
  dialogue.conversations.work_offer.accept/1   [34 chars]
    en  Then let's talk properly about it.
    >>  ............................................
    pt  Então vamos conversar direito sobre isso.
    >>  ............................................
  dialogue.conversations.work_offer.accept/2   [36 chars]
    en  Good. Come here and I'll lay it out.
    >>  ............................................
    pt  Bom. Venha aqui que eu explico.
    >>  ............................................
  dialogue.conversations.work_offer.accept/3   [40 chars]
    en  That's settled, then. Here's the detail.
    >>  ............................................
    pt  Então está acertado. Aqui estão os detalhes.
    >>  ............................................
```


### Button `decline` — "Not this time, I'm afraid."

*stance family `candor` · tone `gentle` · answers the beat(s) `work_offer.ask_needed.to.work_offer`, `work_offer.ask_terms.hard_bargain.to.work_offer`, `work_offer.ask_terms.to.work_offer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work_offer.decline` — accepted phrasings: "not this time i am afraid"; "i will pass"; "not this time"
  - the message must contain one of: `afraid`, `pass`, `time`
  - scored words: `not`(0.4), `time`(1.0), `afraid`(1.5), `pass`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.followup.decline
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.followup.decline   [26 chars]
    en  Not this time, I'm afraid.
    >>  ............................................
    pt  Desta vez não, infelizmente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `work_offer.followup.decline`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.decline
WHO    VILLAGER — what the player reads after pressing "Not this time, I'm afraid."
       spoken on: conversations.topic.work_offer.followup, button `decline`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.decline.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work_offer.decline/1   [36 chars]
    en  No matter. The asking wasn't a debt.
    >>  ............................................
    pt  Sem problema. Perguntar não criou dívida.
    >>  ............................................
  dialogue.conversations.work_offer.decline/2   [44 chars]
    en  Fair enough. It'll keep till someone's free.
    >>  ............................................
    pt  Justo. Isso espera até alguém estar livre.
    >>  ............................................
  dialogue.conversations.work_offer.decline/3   [44 chars]
    en  Understood. Come back if that changes, %1$s.
    >>  ............................................
    pt  Entendido. Volte se isso mudar, %1$s.
    >>  ............................................
```


### Button `refuse_rudely` — "Find someone else."

*stance family `dismissal` · tone `hostile` · answers the beat(s) `work_offer.ask_needed.to.work_offer`, `work_offer.ask_terms.hard_bargain.to.work_offer`, `work_offer.ask_terms.to.work_offer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work_offer.refuse_rudely` — accepted phrasings: "find someone else"; "do it yourself"; "ask someone else"
  - the message must contain one of: `else`, `someone`, `yourself`
  - scored words: `else`(1.5), `someone`(1.2), `yourself`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.followup.refuse_rudely
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.followup.refuse_rudely   [18 chars]
    en  Find someone else.
    >>  ............................................
    pt  Ache outra pessoa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `work_offer.followup.refuse_rudely`, budget `service`, replay policy `daily_repeat`
- Does: disposition — respect -3, tension +4  _(recorded under topic `work_offer.followup.refuse_rudely`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.refuse_rudely
WHO    VILLAGER — what the player reads after pressing "Find someone else."
       spoken on: conversations.topic.work_offer.followup, button `refuse_rudely`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.refuse_rudely.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work_offer.refuse_rudely/1   [40 chars]
    en  ...Right. I'll ask someone with manners.
    >>  ............................................
    pt  ...Certo. Vou pedir para alguém com modos.
    >>  ............................................
  dialogue.conversations.work_offer.refuse_rudely/2   [46 chars]
    en  There was no call for that. It was only a job.
    >>  ............................................
    pt  Não precisava disso. Era só um serviço.
    >>  ............................................
  dialogue.conversations.work_offer.refuse_rudely/3   [34 chars]
    en  Noted. I'll not trouble you again.
    >>  ............................................
    pt  Anotado. Não vou te incomodar de novo.
    >>  ............................................
```


### Button `leave` — "Let me think on it."

*stance family `exit` · tone `plain` · answers the beat(s) `work_offer.ask_needed.to.work_offer`, `work_offer.ask_terms.hard_bargain.to.work_offer`, `work_offer.ask_terms.to.work_offer` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.followup.leave   [19 chars]
    en  Let me think on it.
    >>  ............................................
    pt  Deixa eu pensar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.leave
WHO    VILLAGER — what the player reads after pressing "Let me think on it."
       spoken on: conversations.topic.work_offer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.leave.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.work_offer.respond / leave
```

```text
  dialogue.conversations.work_offer.leave/1   [33 chars]
    en  Take your time. It'll wait a day.
    >>  ............................................
    pt  Vá no seu tempo. Espera um dia.
    >>  ............................................
  dialogue.conversations.work_offer.leave/2   [27 chars]
    en  Right you are. Think on it.
    >>  ............................................
    pt  Isso mesmo. Pense nisso.
    >>  ............................................
  dialogue.conversations.work_offer.leave/3   [17 chars]
    en  Off you go, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```

---


## `conversations.topic.work_offer.none.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work_offer`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.quest.none` — e.g. "Nothing needs doing right now, %1$s. But I appreciate you asking."


```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.none.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work_offer.none.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work_offer.none.respond   [29 chars]
    en  Nothing needs doing just now.
    >>  ............................................
    pt  Nada precisa ser feito agora.
    >>  ............................................
```


### Button `offer_anyway` — "Anything I can help with regardless?"

*stance family `practical_help` · tone `plain` · answers the beat(s) `quest.none.to.work_offer.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work_offer.none.offer_anyway` — accepted phrasings: "anything i can help with regardless"; "can i help anyway"; "anything at all i can help with"
  - the message must contain one of: `help`, `regardless`, `anything`
  - scored words: `help`(1.5), `regardless`(1.5), `anything`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.none.respond.offer_anyway
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.none.respond.offer_anyway   [36 chars]
    en  Anything I can help with regardless?
    >>  ............................................
    pt  Posso ajudar com alguma coisa mesmo assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work_offer.none.offer_anyway`, budget `service`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +1  _(recorded under topic `work_offer.none.offer_anyway`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.none.offer_anyway
WHO    VILLAGER — what the player reads after pressing "Anything I can help with regardless?"
       spoken on: conversations.topic.work_offer.none.respond, button `offer_anyway`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.none.offer_anyway.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work_offer.none.offer_anyway/1   [70 chars]
    en  ...That's decent of you. There's always something, if you're offering.
    >>  ............................................
    pt  ...Isso é decente da sua parte. Sempre tem algo, se você está oferecendo.
    >>  ............................................
  dialogue.conversations.work_offer.none.offer_anyway/2   [59 chars]
    en  Nothing paid, but I'll not turn down a spare pair of hands.
    >>  ............................................
    pt  Nada pago, mas não recuso um par de mãos extra.
    >>  ............................................
  dialogue.conversations.work_offer.none.offer_anyway/3   [62 chars]
    en  You'd help with no job in it? That's rarer than a quest, %1$s.
    >>  ............................................
    pt  Você ajudaria sem ter serviço? Isso é mais raro que uma missão, %1$s.
    >>  ............................................
```


### Button `ask_later` — "I'll check back."

*stance family `exit` · tone `plain` · answers the beat(s) `quest.none.to.work_offer.none` · **this is the graceful way out of the node***

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work_offer.none.ask_later` — accepted phrasings: "i will check back"; "i will ask later"; "i will come back later"
  - the message must contain one of: `back`, `later`, `check`
  - scored words: `back`(1.2), `later`(1.5), `check`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.none.respond.ask_later
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.none.respond.ask_later   [16 chars]
    en  I'll check back.
    >>  ............................................
    pt  Volto depois.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.proud` (this player only)
- Fires when: RULED OUT when the `states` feature is OFF  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `work_offer.none.ask_later`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.ask_later.proud
WHO    VILLAGER — what the player reads after pressing "I'll check back."
       spoken on: conversations.topic.work_offer.none.respond, button `ask_later`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.ask_later.proud.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work_offer.ask_later.proud/1   [90 chars]
    en  Nothing today — and after the last thing you did for me, I'd almost be embarrassed to ask.
    >>  ............................................
    pt  Nada hoje — e depois da última coisa que você fez por mim, eu quase teria vergonha de pedir.
    >>  ............................................
  dialogue.conversations.work_offer.ask_later.proud/2   [82 chars]
    en  Come back tomorrow, %1$s. You've done enough for this village to be going on with.
    >>  ............................................
    pt  Volte amanhã, %1$s. Você já fez o bastante por esta vila por ora.
    >>  ............................................
  dialogue.conversations.work_offer.ask_later.proud/3   [58 chars]
    en  Not just now. I'm still telling people about the last one.
    >>  ............................................
    pt  Agora não. Ainda estou contando para todo mundo sobre a última.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when has the memory `mcaconversations.state.proud` (this player only)  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `work_offer.none.ask_later`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.none.ask_later
WHO    VILLAGER — what the player reads after pressing "I'll check back."
       spoken on: conversations.topic.work_offer.none.respond, button `ask_later`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.none.ask_later.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work_offer.none.ask_later/1   [40 chars]
    en  Do. Things pile up faster than I'd like.
    >>  ............................................
    pt  Volte sim. As coisas se acumulam mais rápido do que eu gostaria.
    >>  ............................................
  dialogue.conversations.work_offer.none.ask_later/2   [56 chars]
    en  Aye, check back. There's always something by week's end.
    >>  ............................................
    pt  É, volte. Sempre tem algo no fim da semana.
    >>  ............................................
  dialogue.conversations.work_offer.none.ask_later/3   [40 chars]
    en  I'll have something for you soon enough.
    >>  ............................................
    pt  Vou ter algo para você em breve.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `quest.none.to.work_offer.none` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.none.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.none.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work_offer.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work_offer.none.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.none.leave.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work_offer.none.leave/1   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
  dialogue.conversations.work_offer.none.leave/2   [16 chars]
    en  Go safely, %1$s.
    >>  ............................................
    pt  Vá com cuidado, %1$s.
    >>  ............................................
  dialogue.conversations.work_offer.none.leave/3   [19 chars]
    en  Another time, then.
    >>  ............................................
    pt  Outra hora, então.
    >>  ............................................
```

---


## `conversations.topic.work_offer.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work_offer`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.quest.offer` — e.g. "Since you ask, %1$s — aye, there's a thing or two. Let me show you what I need."


```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work_offer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work_offer.respond   [29 chars]
    en  There's work, if you want it.
    >>  ............................................
    pt  Tem trabalho, se você quiser.
    >>  ............................................
```


### Button `ask_needed` — "What needs doing?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `quest.offer.to.work_offer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work_offer.ask_needed` — accepted phrasings: "what needs doing"; "what is the task"; "what needs done"
  - the message must contain one of: `needs`, `doing`, `task`
  - scored words: `needs`(1.5), `doing`(1.0), `task`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.respond.ask_needed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.respond.ask_needed   [17 chars]
    en  What needs doing?
    >>  ............................................
    pt  O que precisa ser feito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work_offer.respond.ask_needed`)_
- Then opens: `conversations.topic.work_offer.followup`
- …where the player's next choices will be: "Alright. I'll take it on." | "Not this time, I'm afraid." | "Find someone else." | "Let me think on it."

```text
POOL   dialogue key: dialogue.conversations.work_offer.ask_needed
WHO    VILLAGER — what the player reads after pressing "What needs doing?"
       spoken on: conversations.topic.work_offer.respond, button `ask_needed`
       leaves the player on: conversations.topic.work_offer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.ask_needed.to.work_offer`: the villager accepts. Subject `work_offer`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.work_offer.ask_needed/1   [67 chars]
    en  Straightforward enough. I'll show you the whole of it if you're in.
    >>  ............................................
    pt  Bem simples. Te mostro tudo se você topar.
    >>  ............................................
  dialogue.conversations.work_offer.ask_needed/2   [61 chars]
    en  Nothing heroic. It's the sort of thing that just needs doing.
    >>  ............................................
    pt  Nada heroico. É o tipo de coisa que só precisa ser feita.
    >>  ............................................
  dialogue.conversations.work_offer.ask_needed/3   [54 chars]
    en  More than I can manage alone, which is why I'm asking.
    >>  ............................................
    pt  Mais do que eu consigo sozinho, por isso estou pedindo.
    >>  ............................................
```


### Button `ask_terms` — "What's in it for me?"

*stance family `curiosity` · tone `blunt` · answers the beat(s) `quest.offer.to.work_offer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work_offer.ask_terms` — accepted phrasings: "what is in it for me"; "what does it pay"; "what are the terms"
  - the message must contain one of: `pay`, `worth`, `reward`, `terms`
  - scored words: `pay`(1.5), `worth`(1.2), `reward`(1.5), `terms`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.respond.ask_terms
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.respond.ask_terms   [20 chars]
    en  What's in it for me?
    >>  ............................................
    pt  O que eu ganho com isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `greedy`, `confident`
- Does: disposition — respect +5, tension +2  _(recorded under topic `work_offer.respond.ask_terms`)_
- Then opens: `conversations.topic.work_offer.followup`
- …where the player's next choices will be: "Alright. I'll take it on." | "Not this time, I'm afraid." | "Find someone else." | "Let me think on it."

```text
POOL   dialogue key: dialogue.conversations.work_offer.ask_terms.hard_bargain
WHO    VILLAGER — what the player reads after pressing "What's in it for me?"
       spoken on: conversations.topic.work_offer.respond, button `ask_terms`
       leaves the player on: conversations.topic.work_offer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.ask_terms.hard_bargain.to.work_offer`: the villager accepts. Subject `work_offer`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.work_offer.ask_terms.hard_bargain/1   [86 chars]
    en  Now that's the right question, and you're the first today to ask it before saying yes.
    >>  ............................................
    pt  Essa é a pergunta certa, e você é o primeiro hoje a fazer antes de dizer sim.
    >>  ............................................
  dialogue.conversations.work_offer.ask_terms.hard_bargain/2   [63 chars]
    en  Terms. Good. I'd have thought less of you for not asking, %1$s.
    >>  ............................................
    pt  As condições. Bom. Eu teria gostado menos de você se não perguntasse, %1$s.
    >>  ............................................
  dialogue.conversations.work_offer.ask_terms.hard_bargain/3   [80 chars]
    en  Straight to the price. I like that. It saves us both an afternoon of politeness.
    >>  ............................................
    pt  Direto ao preço. Gosto disso. Poupa uma tarde inteira de gentileza dos dois lados.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `greedy`, `confident`  _(chance -2000)_
- Does: disposition — respect +2  _(recorded under topic `work_offer.respond.ask_terms`)_
- Then opens: `conversations.topic.work_offer.followup`
- …where the player's next choices will be: "Alright. I'll take it on." | "Not this time, I'm afraid." | "Find someone else." | "Let me think on it."

```text
POOL   dialogue key: dialogue.conversations.work_offer.ask_terms
WHO    VILLAGER — what the player reads after pressing "What's in it for me?"
       spoken on: conversations.topic.work_offer.respond, button `ask_terms`
       leaves the player on: conversations.topic.work_offer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.ask_terms.to.work_offer`: the villager accepts. Subject `work_offer`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.work_offer.ask_terms/1   [61 chars]
    en  Fair question. I'd ask it too. You'll be paid properly, %1$s.
    >>  ............................................
    pt  Pergunta justa. Eu também perguntaria. Você será pago direito, %1$s.
    >>  ............................................
  dialogue.conversations.work_offer.ask_terms/2   [68 chars]
    en  Straight to the terms. Good — I'd rather deal with someone who asks.
    >>  ............................................
    pt  Direto aos termos. Bom — prefiro lidar com quem pergunta.
    >>  ............................................
  dialogue.conversations.work_offer.ask_terms/3   [54 chars]
    en  Whatever's fair. I'll not have it said I short anyone.
    >>  ............................................
    pt  O que for justo. Não vou permitir que digam que eu enganei alguém.
    >>  ............................................
```


### Button `accept` — "I'll do it."

*stance family `restraint` · tone `plain` · answers the beat(s) `quest.offer.to.work_offer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work_offer.accept` — accepted phrasings: "i will do it"; "i will take it"; "yes, i am in"
  - the message must contain one of: `do`, `take`, `yes`
  - scored words: `do`(0.5), `take`(1.2), `yes`(1.2), `in`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.respond.accept
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.respond.accept   [11 chars]
    en  I'll do it.
    >>  ............................................
    pt  Eu faço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +2  _(recorded under topic `work_offer.accept`)_
- Does: `conversations_quest_open` = {"mode": "menu"}
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.accept
WHO    VILLAGER — what the player reads after pressing "I'll do it."
       spoken on: conversations.topic.work_offer.respond, button `accept`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.accept.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.work_offer.followup / accept
```

> Written out in full under **`conversations.topic.work_offer.followup` / button `accept`** earlier in this file. Fill it in there, once.


### Button `leave` — "Not today."

*stance family `exit` · tone `plain` · answers the beat(s) `quest.offer.to.work_offer` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work_offer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work_offer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work_offer.respond.leave   [10 chars]
    en  Not today.
    >>  ............................................
    pt  Hoje não.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work_offer.leave
WHO    VILLAGER — what the player reads after pressing "Not today."
       spoken on: conversations.topic.work_offer.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work_offer.leave.terminal`: the villager accepts. Subject `work_offer.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.work_offer.followup / leave
```

> Written out in full under **`conversations.topic.work_offer.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

