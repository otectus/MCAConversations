# Work talk with a none

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.none.followup`](#conversations-scene-work-none-followup)
- [`conversations.scene.work.none.old_admiration.blocked.respond`](#conversations-scene-work-none-old-admiration-blocked-respond)
- [`conversations.scene.work.none.old_admiration.succeeded.respond`](#conversations-scene-work-none-old-admiration-succeeded-respond)
- [`conversations.scene.work.none.quiet_usefulness.active.respond`](#conversations-scene-work-none-quiet-usefulness-active-respond)
- [`conversations.scene.work.none.quiet_usefulness.succeeded.respond`](#conversations-scene-work-none-quiet-usefulness-succeeded-respond)
- [`conversations.scene.work.none.trade_decision.blocked.respond`](#conversations-scene-work-none-trade-decision-blocked-respond)
- [`conversations.scene.work.none.trade_decision.failed.respond`](#conversations-scene-work-none-trade-decision-failed-respond)
- [`conversations.scene.work.none.trade_decision.succeeded.respond`](#conversations-scene-work-none-trade-decision-succeeded-respond)
- [`conversations.topic.work.none.craft.respond`](#conversations-topic-work-none-craft-respond)
- [`conversations.topic.work.none.followup`](#conversations-topic-work-none-followup)
- [`conversations.topic.work.none.future.respond`](#conversations-topic-work-none-future-respond)
- [`conversations.topic.work.none.respond`](#conversations-topic-work-none-respond)
- [`conversations.topic.work.none.risk.respond`](#conversations-topic-work-none-risk-respond)
- [`conversations.topic.work.none.task.respond`](#conversations-topic-work-none-task-respond)
- [`conversations.topic.work.none.village.respond`](#conversations-topic-work-none-village-respond)

---

## `conversations.scene.work.none.followup`

**Reached from 15 route(s):** `conversations.scene.work.none.old_admiration.blocked.respond` / `ask_what_changed`; `conversations.scene.work.none.old_admiration.blocked.respond` / `encourage_again`; `conversations.scene.work.none.old_admiration.succeeded.respond` / `hold_them_to_it`; `conversations.scene.work.none.old_admiration.succeeded.respond` / `ask_what_they_said`; `conversations.scene.work.none.quiet_usefulness.active.respond` / `name_it_work`; `conversations.scene.work.none.quiet_usefulness.active.respond` / `ask_who_asked`; `conversations.scene.work.none.quiet_usefulness.succeeded.respond` / `glad`; `conversations.scene.work.none.quiet_usefulness.succeeded.respond` / `ask_change`; `conversations.scene.work.none.trade_decision.blocked.respond` / `ask_why_that_one`; `conversations.scene.work.none.trade_decision.blocked.respond` / `offer_to_return`; `conversations.scene.work.none.trade_decision.blocked.respond` / `question_doubt`; `conversations.scene.work.none.trade_decision.failed.respond` / `sit_with_it` …and 3 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.none.old_admiration.blocked.explained` — e.g. "Nothing dramatic. I was needed at home for two years and then the two years were over and the idea had gone quiet, and quiet ideas are very hard to wake."
- `conversations.scene.work.none.old_admiration.blocked.stirred` — e.g. "That is a dangerous thing to say to somebody in my position and I am going to carry it around for a week."
- `conversations.scene.work.none.old_admiration.succeeded.held` — e.g. "Good. That is why I said it to somebody instead of writing it down, and writing it down is what I have done every other time."
- `conversations.scene.work.none.old_admiration.succeeded.reported` — e.g. "That it is mostly waiting and then all at once, and that everybody who lasts is the sort who does not mind the waiting. I do not know yet if I am."
- `conversations.scene.work.none.quiet_usefulness.active.explained` — e.g. "No one. I did %2$s once because it needed doing and I was standing there, and then it was three weeks later and it was mine."
- `conversations.scene.work.none.quiet_usefulness.active.named` — e.g. "It has not, and I have spent a year deciding whether that matters. Hearing somebody else call %2$s work settles more than it should."
- `conversations.scene.work.none.quiet_usefulness.succeeded.considered` — e.g. "It might. Being seen doing %2$s is very different from doing it, and I am not sure yet which one I was actually short of."
- `conversations.scene.work.none.quiet_usefulness.succeeded.warmed` — e.g. "It was, and I am aware of how much I am making of two sentences. I am going to make a lot of them for a while yet."
- `conversations.scene.work.none.trade_decision.blocked.accepted` — e.g. "Then I will have to, because you will ask. That is a cheap trick and I am grateful for it."
- `conversations.scene.work.none.trade_decision.blocked.explained` — e.g. "Because I have watched %2$s from outside for two years and I still have not got bored of watching, which seems like the only honest test I have."
- `conversations.scene.work.none.trade_decision.blocked.resisted` — e.g. "It might be. It is also true. %2$s does not stop being real because I am also frightened of it."
- `conversations.scene.work.none.trade_decision.failed.considered` — e.g. "Nothing, for a bit. I know that is not the answer people want. I have decided to be all right with not having one for a month."
- `conversations.scene.work.none.trade_decision.failed.received` — e.g. "Both being true is the part I could not hold on my own. Everyone picks one and tells me that one."
- `conversations.scene.work.none.trade_decision.succeeded.credited` — e.g. "I did it because somebody was going to ask. That is not doing it alone and I am not going to pretend otherwise."
- …and 1 more pools


```text
POOL   dialogue key: dialogue.conversations.scene.work.none.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.none.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.none.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What do you spend your days on?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.none.*` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.followup.ask_more` — accepted phrasings: "what do you spend your days on"; "how do you spend your days"; "what fills your days"
  - the message must contain one of: `days`, `spend`
  - scored words: `days`(1.8), `spend`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.followup.ask_more   [31 chars]
    en  What do you spend your days on?
    >>  ............................................
    pt  Com o que você passa os seus dias?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.hard
WHO    VILLAGER — what the player reads after pressing "What do you spend your days on?"
       spoken on: conversations.scene.work.none.followup, button `ask_more`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.hard`: the villager explains. Subject `work.none.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.none.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.none.hard/1   [79 chars]
    en  In summer. In February it's a different word entirely and nobody uses that one.
    >>  ............................................
    pt  No verão. Em fevereiro é outra palavra totalmente diferente e ninguém usa essa.
    >>  ............................................
  dialogue.conversations.work.prof.none.hard/2   [73 chars]
    en  It's free the way a field is free, %1$s. Nothing planted, nothing coming.
    >>  ............................................
    pt  É livre do jeito que um campo é livre, %1$s. Nada plantado, nada vindo.
    >>  ............................................
```


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.none.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.followup.leave   [16 chars]
    en  I'll let you be.
    >>  ............................................
    pt  Vou deixar você em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.scene.work.none.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave; conversations.topic.work.none.craft.respond / leave …and 6 more
```

```text
  dialogue.conversations.work.prof.none.leave/1   [48 chars]
    en  To what, exactly? ...No, go on, that was unfair.
    >>  ............................................
    pt  Com o quê, exatamente? ...Não, pode ir, isso foi injusto.
    >>  ............................................
  dialogue.conversations.work.prof.none.leave/2   [63 chars]
    en  Aye. Ask me again next season, %1$s, and it might be different.
    >>  ............................................
    pt  É. Me pergunte na próxima estação, %1$s, e talvez seja diferente.
    >>  ............................................
```

---


## `conversations.scene.work.none.old_admiration.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.none.old_admiration.blocked` — e.g. "When I was small I was going to work at %2$s. I have not thought about that in years and then somebody mentioned it and I have thought of nothing else for two days."


```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.none.old_admiration.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.none.old_admiration.blocked.respond   [19 chars]
    en  About the old idea.
    >>  ............................................
    pt  Sobre a ideia antiga.
    >>  ............................................
```


### Button `ask_what_changed` — "What happened to that idea?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.old_admiration.blocked` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.old_admiration.blocked.ask_what_changed` — accepted phrasings: "what happened to that idea"; "what happened to that plan"; "why did that idea stop"
  - the message must contain one of: `happened`, `idea`, `plan`
  - scored words: `happened`(1.8), `idea`(1.8), `plan`(1.8), `why`(0.8), `stop`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.blocked.respond.ask_what_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.old_admiration.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.old_admiration.blocked.respond.ask_what_changed   [27 chars]
    en  What happened to that idea?
    >>  ............................................
    pt  O que aconteceu com essa ideia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `work.none.admiration`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.none.old_admiration"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What happened to that idea?"
       spoken on: conversations.scene.work.none.old_admiration.blocked.respond, button `ask_what_changed`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.old_admiration.blocked.explained`: the villager explains. Subject `work.none.admiration`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.old_admiration.blocked.explained/1   [153 chars]
    en  Nothing dramatic. I was needed at home for two years and then the two years were over and the idea had gone quiet, and quiet ideas are very hard to wake.
    >>  ............................................
    pt  Nada de dramático. Precisaram de mim em casa por dois anos, e quando os dois anos acabaram a ideia tinha ficado quieta, e ideia quieta é dificílima de acordar.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.blocked.explained/2   [123 chars]
    en  I told one person and they laughed. Not unkindly. That is the annoying part — it was not even cruelty, and it still did it.
    >>  ............................................
    pt  Contei para uma pessoa e ela riu. Sem maldade. É essa a parte irritante — nem foi crueldade, e ainda assim resolveu.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.blocked.explained/3   [123 chars]
    en  I got older and it got smaller, and one day I noticed I had stopped saying it out loud. I do not remember deciding to stop.
    >>  ............................................
    pt  Fiquei mais velho e ela ficou menor, e um dia notei que tinha parado de dizer em voz alta. Não lembro de ter decidido parar.
    >>  ............................................
```


### Button `encourage_again` — "A child's idea can still be a good one."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.none.old_admiration.blocked` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.old_admiration.blocked.encourage_again` — accepted phrasings: "a childs idea can still be a good one"; "a childs idea can still be right"; "being young when you wanted it doesnt make it wrong"
  - the message must contain one of: `childs`, `idea`, `young`
  - scored words: `childs`(1.8), `idea`(1.8), `young`(1.8), `still`(0.8), `good`(0.8), `one`(0.8), `right`(0.8), `being`(0.8), `when`(0.8), `wanted`(0.8), `doesnt`(0.8), `make`(0.8), `wrong`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.blocked.respond.encourage_again
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.old_admiration.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.old_admiration.blocked.respond.encourage_again   [39 chars]
    en  A child's idea can still be a good one.
    >>  ............................................
    pt  Uma ideia de criança ainda pode ser boa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +1  _(recorded under topic `work.none.admiration`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.old_admiration", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.none.old_admiration"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.blocked.stirred
WHO    VILLAGER — what the player reads after pressing "A child's idea can still be a good one."
       spoken on: conversations.scene.work.none.old_admiration.blocked.respond, button `encourage_again`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.old_admiration.blocked.stirred`: the villager qualifys. Subject `work.none.admiration`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.old_admiration.blocked.stirred/1   [105 chars]
    en  That is a dangerous thing to say to somebody in my position and I am going to carry it around for a week.
    >>  ............................................
    pt  Isso é uma coisa perigosa de se dizer a alguém na minha situação, e eu vou carregar isso por uma semana.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.blocked.stirred/2   [142 chars]
    en  I have been calling it childish so that it would stop being an option. You have just taken that away from me, and I am not sure I forgive you.
    >>  ............................................
    pt  Venho chamando de infantil para que deixasse de ser uma opção. Você acabou de me tirar isso, e não sei se te perdoo.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.blocked.stirred/3   [147 chars]
    en  Then I have wanted the same thing for twenty years, which is either constancy or a failure to move on, and today I would like to call it constancy.
    >>  ............................................
    pt  Então quero a mesma coisa há vinte anos, o que é constância ou incapacidade de seguir em frente, e hoje eu gostaria de chamar de constância.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your day."

*stance family `exit` · tone `plain` · answers the beat(s) `work.none.old_admiration.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.old_admiration.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.old_admiration.blocked.respond.leave   [34 chars]
    en  I'll let you get on with your day.
    >>  ............................................
    pt  Vou deixar você tocar o seu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your day."
       spoken on: conversations.scene.work.none.old_admiration.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave; conversations.topic.work.none.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.none.old_admiration.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.none.old_admiration.succeeded` — e.g. "I asked at %2$s. Only what the work is like — not for anything — and they talked to me for an hour like a person who might."


```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.none.old_admiration.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.none.old_admiration.succeeded.respond   [28 chars]
    en  So you're thinking about it.
    >>  ............................................
    pt  Então você está pensando nisso.
    >>  ............................................
```


### Button `hold_them_to_it` — "Then I'll remember you said it."

*stance family `encouragement` · tone `plain` · outcome `accepted` · answers the beat(s) `work.none.old_admiration.succeeded` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.old_admiration.succeeded.hold_them_to_it` — accepted phrasings: "then ill remember you said it"; "ill hold you to that"; "i will remember you said that"
  - the message must contain one of: `remember`, `hold`
  - scored words: `remember`(1.8), `hold`(1.8), `ill`(0.8), `said`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.succeeded.respond.hold_them_to_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.old_admiration.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.old_admiration.succeeded.respond.hold_them_to_it   [31 chars]
    en  Then I'll remember you said it.
    >>  ............................................
    pt  Então vou lembrar que você disse.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +3, warmth +2  _(recorded under topic `work.none.admiration`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.none.old_admiration"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.succeeded.held
WHO    VILLAGER — what the player reads after pressing "Then I'll remember you said it."
       spoken on: conversations.scene.work.none.old_admiration.succeeded.respond, button `hold_them_to_it`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.old_admiration.succeeded.held`: the villager accepts. Subject `work.none.admiration`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.old_admiration.succeeded.held/1   [125 chars]
    en  Good. That is why I said it to somebody instead of writing it down, and writing it down is what I have done every other time.
    >>  ............................................
    pt  Ótimo. Foi por isso que eu disse a alguém em vez de anotar, e anotar é o que eu fiz todas as outras vezes.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.succeeded.held/2   [96 chars]
    en  Please do. And be a nuisance about it, because I am very good at making a season last two years.
    >>  ............................................
    pt  Por favor, lembre. E seja chato quanto a isso, porque eu sou muito bom em fazer uma estação durar dois anos.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.succeeded.held/3   [76 chars]
    en  Then it is real, in the only way I have ever been able to make a thing real.
    >>  ............................................
    pt  Então é real, do único jeito que eu já consegui tornar alguma coisa real.
    >>  ............................................
```


### Button `ask_what_they_said` — "What did they tell you about the work?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.old_admiration.succeeded` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.old_admiration.succeeded.ask_what_they_said` — accepted phrasings: "what did they tell you about the work"; "what did they tell you"; "what did you learn about it"
  - the message must contain one of: `tell`, `told`, `learn`
  - scored words: `tell`(1.8), `told`(1.8), `learn`(1.8), `work`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.succeeded.respond.ask_what_they_said
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.old_admiration.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.old_admiration.succeeded.respond.ask_what_they_said   [38 chars]
    en  What did they tell you about the work?
    >>  ............................................
    pt  O que contaram sobre o trabalho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.admiration`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.none.old_admiration"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.succeeded.reported
WHO    VILLAGER — what the player reads after pressing "What did they tell you about the work?"
       spoken on: conversations.scene.work.none.old_admiration.succeeded.respond, button `ask_what_they_said`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.old_admiration.succeeded.reported`: the villager explains. Subject `work.none.admiration`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.old_admiration.succeeded.reported/1   [146 chars]
    en  That it is mostly waiting and then all at once, and that everybody who lasts is the sort who does not mind the waiting. I do not know yet if I am.
    >>  ............................................
    pt  Que é quase só esperar e depois tudo de uma vez, e que quem aguenta é o tipo que não se incomoda de esperar. Ainda não sei se eu sou.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.succeeded.reported/2   [109 chars]
    en  The honest version, which I appreciated. Cold hands, long days, and one thing a month that makes it worth it.
    >>  ............................................
    pt  A versão honesta, o que eu agradeci. Mãos frias, dias longos, e uma coisa por mês que faz valer a pena.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.succeeded.reported/3   [114 chars]
    en  More than I expected. I think they had been waiting for somebody to ask about %2$s and nobody had for a long time.
    >>  ............................................
    pt  Mais do que eu esperava. Acho que estavam esperando alguém perguntar sobre %2$s e fazia tempo que ninguém perguntava.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your day."

*stance family `exit` · tone `plain` · answers the beat(s) `work.none.old_admiration.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.old_admiration.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.old_admiration.succeeded.respond.leave   [34 chars]
    en  I'll let you get on with your day.
    >>  ............................................
    pt  Vou deixar você tocar o seu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your day."
       spoken on: conversations.scene.work.none.old_admiration.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave; conversations.topic.work.none.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.none.quiet_usefulness.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.none.quiet_usefulness.active` — e.g. "I spend most mornings %2$s. Nobody pays me and nobody would notice if I stopped, and I have started to think that second part is the interesting one."


```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.none.quiet_usefulness.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.active.respond   [15 chars]
    en  About the days.
    >>  ............................................
    pt  Sobre os dias.
    >>  ............................................
```


### Button `name_it_work` — "That's work. It just hasn't got a name."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.none.quiet_usefulness.active` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.quiet_usefulness.active.name_it_work` — accepted phrasings: "thats work it just hasnt got a name"; "that is work without a title"; "it is work even unpaid"
  - the message must contain one of: `work`, `title`, `unpaid`
  - scored words: `work`(1.8), `title`(1.8), `unpaid`(1.8), `thats`(0.8), `hasnt`(0.8), `got`(0.8), `name`(0.8), `without`(0.8), `even`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.active.respond.name_it_work
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.quiet_usefulness.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.active.respond.name_it_work   [39 chars]
    en  That's work. It just hasn't got a name.
    >>  ............................................
    pt  Isso é trabalho. Só não tem nome.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.none.quiet_usefulness.named`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.none.freedom`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.quiet_usefulness", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.none.quiet_usefulness"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.active.named
WHO    VILLAGER — what the player reads after pressing "That's work. It just hasn't got a name."
       spoken on: conversations.scene.work.none.quiet_usefulness.active.respond, button `name_it_work`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.quiet_usefulness.active.named`: the villager accepts. Subject `work.none.freedom`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.active.named/1   [132 chars]
    en  It has not, and I have spent a year deciding whether that matters. Hearing somebody else call %2$s work settles more than it should.
    >>  ............................................
    pt  Não tem, e passei um ano decidindo se isso importa. Ouvir outra pessoa chamar %2$s de trabalho resolve mais do que deveria.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.active.named/2   [125 chars]
    en  Say that where the village can hear it and I will owe you something. I have been arguing it to myself in the dark for months.
    >>  ............................................
    pt  Diga isso onde a vila possa ouvir e eu vou lhe dever alguma coisa. Venho argumentando isso comigo mesmo no escuro há meses.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.active.named/3   [87 chars]
    en  Then I have a trade and no title, which is an odd thing to be relieved about, and I am.
    >>  ............................................
    pt  Então tenho um ofício e nenhum título, o que é uma coisa estranha de aliviar, e alivia.
    >>  ............................................
```


### Button `ask_who_asked` — "Who asked you to start doing it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.quiet_usefulness.active` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.quiet_usefulness.active.ask_who_asked` — accepted phrasings: "who asked you to start doing it"; "who asked you to do that"; "how did you start doing it"
  - the message must contain one of: `asked`, `start`, `doing`
  - scored words: `asked`(1.8), `start`(1.8), `doing`(1.8), `who`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.active.respond.ask_who_asked
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.quiet_usefulness.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.active.respond.ask_who_asked   [32 chars]
    en  Who asked you to start doing it?
    >>  ............................................
    pt  Quem pediu para você começar a fazer isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.freedom`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.none.quiet_usefulness"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.active.explained
WHO    VILLAGER — what the player reads after pressing "Who asked you to start doing it?"
       spoken on: conversations.scene.work.none.quiet_usefulness.active.respond, button `ask_who_asked`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.quiet_usefulness.active.explained`: the villager explains. Subject `work.none.freedom`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.active.explained/1   [124 chars]
    en  No one. I did %2$s once because it needed doing and I was standing there, and then it was three weeks later and it was mine.
    >>  ............................................
    pt  Ninguém. Fiz %2$s uma vez porque precisava ser feito e eu estava ali, e de repente eram três semanas depois e já era meu.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.active.explained/2   [137 chars]
    en  An old woman who has since died. She asked once. I have kept it up out of a habit that started as a favour to somebody who cannot see it.
    >>  ............................................
    pt  Uma senhora que já morreu. Ela pediu uma vez. Continuei por um hábito que começou como favor a alguém que não pode mais ver.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.active.explained/3   [134 chars]
    en  It arrived the way these things arrive. Nobody decided. That is either the best or the worst thing about it, depending on the morning.
    >>  ............................................
    pt  Chegou do jeito que essas coisas chegam. Ninguém decidiu. É a melhor ou a pior coisa disso, dependendo da manhã.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your day."

*stance family `exit` · tone `plain` · answers the beat(s) `work.none.quiet_usefulness.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.quiet_usefulness.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.active.respond.leave   [34 chars]
    en  I'll let you get on with your day.
    >>  ............................................
    pt  Vou deixar você tocar o seu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your day."
       spoken on: conversations.scene.work.none.quiet_usefulness.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave; conversations.topic.work.none.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.none.quiet_usefulness.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.none.quiet_usefulness.succeeded` — e.g. "Two people thanked me this week for %2$s. Two. After a year. I have thought about it more than I would like to admit."


```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.none.quiet_usefulness.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.respond   [17 chars]
    en  Somebody noticed.
    >>  ............................................
    pt  Alguém reparou.
    >>  ............................................
```


### Button `glad` — "About time somebody said it."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.none.quiet_usefulness.succeeded` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.quiet_usefulness.succeeded.glad` — accepted phrasings: "about time somebody said it"; "it was about time"; "long overdue that someone said so"
  - the message must contain one of: `time`, `overdue`
  - scored words: `time`(1.8), `overdue`(1.8), `somebody`(0.8), `said`(0.8), `long`(0.8), `someone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.respond.glad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.quiet_usefulness.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.respond.glad   [28 chars]
    en  About time somebody said it.
    >>  ............................................
    pt  Já era hora de alguém dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.none.freedom`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.none.quiet_usefulness"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.warmed
WHO    VILLAGER — what the player reads after pressing "About time somebody said it."
       spoken on: conversations.scene.work.none.quiet_usefulness.succeeded.respond, button `glad`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.quiet_usefulness.succeeded.warmed`: the villager accepts. Subject `work.none.freedom`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.warmed/1   [114 chars]
    en  It was, and I am aware of how much I am making of two sentences. I am going to make a lot of them for a while yet.
    >>  ............................................
    pt  Era mesmo, e eu tenho noção do tanto que estou fazendo de duas frases. Vou fazer bastante delas ainda por um tempo.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.warmed/2   [91 chars]
    en  I had decided I did not need it. Turns out I had decided that because I was not getting it.
    >>  ............................................
    pt  Eu tinha decidido que não precisava disso. Descobri que decidi isso porque não estava recebendo.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.warmed/3   [113 chars]
    en  You are the third to say so and I have counted all three, which tells you everything about how the year has gone.
    >>  ............................................
    pt  Você é o terceiro a dizer, e eu contei os três, o que diz tudo sobre como foi o ano.
    >>  ............................................
```


### Button `ask_change` — "Does that change what you'll do next?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.quiet_usefulness.succeeded` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.quiet_usefulness.succeeded.ask_change` — accepted phrasings: "does that change what youll do next"; "does that change anything for you"; "will you do something different now"
  - the message must contain one of: `change`, `different`
  - scored words: `change`(1.8), `different`(1.8), `does`(0.8), `youll`(0.8), `next`(0.8), `anything`(0.8), `something`(0.8), `now`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.respond.ask_change
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.quiet_usefulness.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.respond.ask_change   [37 chars]
    en  Does that change what you'll do next?
    >>  ............................................
    pt  Isso muda o que você vai fazer depois?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.freedom`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.none.quiet_usefulness"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.considered
WHO    VILLAGER — what the player reads after pressing "Does that change what you'll do next?"
       spoken on: conversations.scene.work.none.quiet_usefulness.succeeded.respond, button `ask_change`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.quiet_usefulness.succeeded.considered`: the villager explains. Subject `work.none.freedom`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.considered/1   [121 chars]
    en  It might. Being seen doing %2$s is very different from doing it, and I am not sure yet which one I was actually short of.
    >>  ............................................
    pt  Talvez. Ser visto fazendo %2$s é muito diferente de fazer, e ainda não sei de qual das duas eu estava carente.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.considered/2   [113 chars]
    en  No. And that surprised me. I thought a title was what I wanted and it turns out I wanted somebody to say my name.
    >>  ............................................
    pt  Não. E isso me surpreendeu. Achei que eu queria um título, e parece que eu queria alguém dizendo meu nome.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.considered/3   [121 chars]
    en  Ask me in the spring. Everything I decide in a good week I unmake in a bad one, so I have stopped deciding in good weeks.
    >>  ............................................
    pt  Me pergunte na primavera. Tudo que decido numa boa semana eu desfaço numa ruim, então parei de decidir em boas semanas.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your day."

*stance family `exit` · tone `plain` · answers the beat(s) `work.none.quiet_usefulness.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.quiet_usefulness.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded.respond.leave   [34 chars]
    en  I'll let you get on with your day.
    >>  ............................................
    pt  Vou deixar você tocar o seu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your day."
       spoken on: conversations.scene.work.none.quiet_usefulness.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave; conversations.topic.work.none.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.none.trade_decision.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.none.trade_decision.blocked` — e.g. "I have been standing outside %2$s for three mornings now, working up to going in. %3$s is what stops me, and I know how thin that sounds."


```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.none.trade_decision.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked.respond   [18 chars]
    en  About what's next.
    >>  ............................................
    pt  Sobre o que vem depois.
    >>  ............................................
```


### Button `ask_why_that_one` — "Why that trade in particular?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.trade_decision.blocked` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.trade_decision.blocked.ask_why_that_one` — accepted phrasings: "why that trade in particular"; "what draws you to that one"; "why that trade and not another"
  - the message must contain one of: `trade`, `draws`, `particular`
  - scored words: `trade`(1.8), `draws`(1.8), `particular`(1.8), `why`(0.8), `one`(0.8), `another`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked.respond.ask_why_that_one
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked.respond.ask_why_that_one   [29 chars]
    en  Why that trade in particular?
    >>  ............................................
    pt  Por que justamente esse ofício?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `work.none.between_trades`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.none.trade_decision"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Why that trade in particular?"
       spoken on: conversations.scene.work.none.trade_decision.blocked.respond, button `ask_why_that_one`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.blocked.explained`: the villager explains. Subject `work.none.between_trades`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked.explained/1   [144 chars]
    en  Because I have watched %2$s from outside for two years and I still have not got bored of watching, which seems like the only honest test I have.
    >>  ............................................
    pt  Porque venho olhando %2$s de fora há dois anos e ainda não me cansei de olhar, o que me parece o único teste honesto que eu tenho.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.blocked.explained/2   [140 chars]
    en  My aunt did that work. Not here — a long way from here — and she was the only adult who ever explained anything to me instead of telling me.
    >>  ............................................
    pt  Minha tia fazia esse trabalho. Não aqui — bem longe daqui — e foi a única adulta que me explicou as coisas em vez de mandar.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.blocked.explained/3   [112 chars]
    en  Because it is the one where the useful part is visible at the end of the day. I would like a day I can point at.
    >>  ............................................
    pt  Porque é aquele em que a parte útil aparece no fim do dia. Eu gostaria de ter um dia que eu possa apontar.
    >>  ............................................
```


### Button `offer_to_return` — "Go in this week. I'll come back and hear how it went."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.none.trade_decision.blocked` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.trade_decision.blocked.offer_to_return` — accepted phrasings: "go in this week ill come back and hear how it went"; "ill come back and ask how it went"; "go this week and tell me after"
  - the message must contain one of: `come`, `week`, `went`
  - scored words: `come`(1.8), `week`(1.8), `went`(1.8), `ill`(0.8), `back`(0.8), `hear`(0.8), `ask`(0.8), `tell`(0.8), `after`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked.respond.offer_to_return
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked.respond.offer_to_return   [53 chars]
    en  Go in this week. I'll come back and hear how it went.
    >>  ............................................
    pt  Entre esta semana. Eu volto para saber como foi.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.none.trade_decision.support`, budget `standard`, replay policy `once`
- Does: disposition — trust +3, warmth +3  _(recorded under topic `work.none.between_trades`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.trade_decision", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.none.trade_decision", "obligation": "commitment:work.none.come_back_after"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.none.come_back_after"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Go in this week. I'll come back and hear how it went."
       spoken on: conversations.scene.work.none.trade_decision.blocked.respond, button `offer_to_return`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.blocked.accepted`: the villager accepts. Subject `work.none.between_trades`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked.accepted/1   [90 chars]
    en  Then I will have to, because you will ask. That is a cheap trick and I am grateful for it.
    >>  ............................................
    pt  Então vou ter de ir, porque você vai perguntar. É um truque barato e eu agradeço por ele.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.blocked.accepted/2   [123 chars]
    en  Somebody expecting an answer is worth more than any amount of me deciding on my own. I will go to %2$s before you are back.
    >>  ............................................
    pt  Alguém esperando uma resposta vale mais do que qualquer quantidade de eu decidindo sozinho. Vou a %2$s antes de você voltar.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.blocked.accepted/3   [113 chars]
    en  All right. This week. And if it goes badly you get to hear that too, which is the part I am actually agreeing to.
    >>  ............................................
    pt  Está bem. Esta semana. E se correr mal, você vai ouvir isso também, que é a parte com que eu de fato estou concordando.
    >>  ............................................
```


### Button `question_doubt` — "That reason sounds like fear wearing a coat."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.none.trade_decision.blocked` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.trade_decision.blocked.question_doubt` — accepted phrasings: "that reason sounds like fear wearing a coat"; "that sounds like fear in a coat"; "i think that reason is fear"
  - the message must contain one of: `fear`, `coat`, `reason`
  - scored words: `fear`(1.8), `coat`(1.8), `reason`(1.8), `sounds`(0.8), `like`(0.8), `wearing`(0.8), `think`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked.respond.question_doubt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked.respond.question_doubt   [44 chars]
    en  That reason sounds like fear wearing a coat.
    >>  ............................................
    pt  Esse motivo parece medo de casaco.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, tension +2  _(recorded under topic `work.none.between_trades`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.none.trade_decision"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "That reason sounds like fear wearing a coat."
       spoken on: conversations.scene.work.none.trade_decision.blocked.respond, button `question_doubt`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.blocked.resisted`: the villager resists. Subject `work.none.between_trades`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked.resisted/1   [95 chars]
    en  It might be. It is also true. %2$s does not stop being real because I am also frightened of it.
    >>  ............................................
    pt  Pode ser. E também é verdade. %2$s não deixa de ser real só porque eu também tenho medo.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.blocked.resisted/2   [114 chars]
    en  You say that from inside a life that worked out. I am not saying you are wrong. I am saying it is easy from there.
    >>  ............................................
    pt  Você diz isso de dentro de uma vida que deu certo. Não estou dizendo que está errado. Estou dizendo que daí é fácil.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.blocked.resisted/3   [114 chars]
    en  Perhaps. And if I go in and it is not fear, if it is simply true, then I have spent the last thing I had to spend.
    >>  ............................................
    pt  Talvez. E se eu entrar e não for medo, se for simplesmente verdade, então gastei a última coisa que eu tinha para gastar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your day."

*stance family `exit` · tone `plain` · answers the beat(s) `work.none.trade_decision.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked.respond.leave   [34 chars]
    en  I'll let you get on with your day.
    >>  ............................................
    pt  Vou deixar você tocar o seu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your day."
       spoken on: conversations.scene.work.none.trade_decision.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave; conversations.topic.work.none.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.none.trade_decision.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.none.trade_decision.failed` — e.g. "%2$s said no. Politely, and with a reason, and the reason was %3$s, so at least I was not imagining it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.none.trade_decision.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.none.trade_decision.failed.respond   [15 chars]
    en  It didn't take.
    >>  ............................................
    pt  Não deu certo.
    >>  ............................................
```


### Button `sit_with_it` — "That took courage, and it still hurt. Both are true."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.none.trade_decision.failed` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.trade_decision.failed.sit_with_it` — accepted phrasings: "that took courage and it still hurt both are true"; "it took courage and it still hurt"; "brave and painful at the same time"
  - the message must contain one of: `courage`, `brave`, `hurt`
  - scored words: `courage`(1.8), `brave`(1.8), `hurt`(1.8), `took`(0.8), `still`(0.8), `both`(0.8), `true`(0.8), `painful`(0.8), `same`(0.8), `time`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.failed.respond.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.failed.respond.sit_with_it   [52 chars]
    en  That took courage, and it still hurt. Both are true.
    >>  ............................................
    pt  Foi corajoso, e doeu mesmo assim. As duas coisas são verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.none.trade_decision.mourn`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +3  _(recorded under topic `work.none.between_trades`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.none.trade_decision"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.failed.received
WHO    VILLAGER — what the player reads after pressing "That took courage, and it still hurt. Both are true."
       spoken on: conversations.scene.work.none.trade_decision.failed.respond, button `sit_with_it`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.failed.received`: the villager qualifys. Subject `work.none.between_trades`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.failed.received/1   [97 chars]
    en  Both being true is the part I could not hold on my own. Everyone picks one and tells me that one.
    >>  ............................................
    pt  As duas coisas serem verdade é a parte que eu não conseguia segurar sozinho. Todo mundo escolhe uma e me diz aquela.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.failed.received/2   [97 chars]
    en  Thank you. I have had 'at least you tried' four times this week and it lands like a door closing.
    >>  ............................................
    pt  Obrigado. Já ouvi 'ao menos você tentou' quatro vezes esta semana e soa como uma porta se fechando.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.failed.received/3   [125 chars]
    en  I would rather have gone and been turned down than still be standing outside working up to it. Some days I even believe that.
    >>  ............................................
    pt  Prefiro ter ido e sido recusado a ainda estar parado do lado de fora criando coragem. Em alguns dias eu até acredito nisso.
    >>  ............................................
```


### Button `ask_what_now` — "What will you try instead?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.trade_decision.failed` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.trade_decision.failed.ask_what_now` — accepted phrasings: "what will you try instead"; "what will you do now"; "where do you go from here"
  - the message must contain one of: `instead`, `now`, `here`
  - scored words: `instead`(1.8), `now`(1.8), `here`(1.8), `try`(0.8), `where`(0.8), `from`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.failed.respond.ask_what_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.failed.respond.ask_what_now   [26 chars]
    en  What will you try instead?
    >>  ............................................
    pt  O que você vai tentar em vez disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.none.between_trades`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.none.trade_decision"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.failed.considered
WHO    VILLAGER — what the player reads after pressing "What will you try instead?"
       spoken on: conversations.scene.work.none.trade_decision.failed.respond, button `ask_what_now`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.failed.considered`: the villager explains. Subject `work.none.between_trades`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.failed.considered/1   [126 chars]
    en  Nothing, for a bit. I know that is not the answer people want. I have decided to be all right with not having one for a month.
    >>  ............................................
    pt  Nada, por um tempo. Sei que não é a resposta que as pessoas querem. Decidi ficar em paz com não ter uma por um mês.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.failed.considered/2   [114 chars]
    en  Something with fewer people watching me learn. That is the only thing I have worked out and it is more than I had.
    >>  ............................................
    pt  Algo com menos gente me olhando aprender. É a única coisa que eu concluí, e é mais do que eu tinha.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.failed.considered/3   [108 chars]
    en  I have not got that far. Ask me at the turn of the season and I might have, and if I have not, ask me again.
    >>  ............................................
    pt  Ainda não cheguei aí. Me pergunte na virada da estação e talvez eu tenha, e se não tiver, pergunte de novo.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your day."

*stance family `exit` · tone `plain` · answers the beat(s) `work.none.trade_decision.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.failed.respond.leave   [34 chars]
    en  I'll let you get on with your day.
    >>  ............................................
    pt  Vou deixar você tocar o seu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your day."
       spoken on: conversations.scene.work.none.trade_decision.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave; conversations.topic.work.none.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.none.trade_decision.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.none.trade_decision.succeeded` — e.g. "I went to %2$s. They said come back Thursday, which is not yes and is a great deal more than I had on Monday."


```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.none.trade_decision.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.none.trade_decision.succeeded.respond   [9 chars]
    en  You went.
    >>  ............................................
    pt  Você foi.
    >>  ............................................
```


### Button `credit_them` — "You did the hard part yourself."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.none.trade_decision.succeeded` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.trade_decision.succeeded.credit_them` — accepted phrasings: "you did the hard part yourself"; "that was your doing not mine"; "you did the difficult bit alone"
  - the message must contain one of: `hard`, `difficult`, `yourself`, `doing`
  - scored words: `hard`(1.8), `difficult`(1.8), `yourself`(1.8), `doing`(1.8), `part`(0.8), `mine`(0.8), `bit`(0.8), `alone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.succeeded.respond.credit_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.succeeded.respond.credit_them   [31 chars]
    en  You did the hard part yourself.
    >>  ............................................
    pt  A parte difícil foi você que fez.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.none.trade_decision.credit`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.none.between_trades`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.none.trade_decision"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.succeeded.credited
WHO    VILLAGER — what the player reads after pressing "You did the hard part yourself."
       spoken on: conversations.scene.work.none.trade_decision.succeeded.respond, button `credit_them`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.succeeded.credited`: the villager qualifys. Subject `work.none.between_trades`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.succeeded.credited/1   [111 chars]
    en  I did it because somebody was going to ask. That is not doing it alone and I am not going to pretend otherwise.
    >>  ............................................
    pt  Eu fiz porque alguém ia perguntar. Isso não é fazer sozinho e não vou fingir que foi.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.succeeded.credited/2   [110 chars]
    en  The hard part took about nine seconds. The four weeks before it were the difficult bit, and nobody sees those.
    >>  ............................................
    pt  A parte difícil levou uns nove segundos. As quatro semanas antes é que foram o duro, e essas ninguém vê.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.succeeded.credited/3   [98 chars]
    en  Say that again in a month when I am bad at it and want to stop. I will need it more then than now.
    >>  ............................................
    pt  Repita isso daqui a um mês, quando eu estiver ruim nisso e quiser parar. Vou precisar mais lá do que agora.
    >>  ............................................
```


### Button `ask_first_day` — "What was the first day like?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.trade_decision.succeeded` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.none.trade_decision.succeeded.ask_first_day` — accepted phrasings: "what was the first day like"; "how was the first day"; "what happened on your first day"
  - the message must contain one of: `first`, `day`
  - scored words: `first`(1.8), `day`(1.8), `like`(0.8), `happened`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.succeeded.respond.ask_first_day
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.succeeded.respond.ask_first_day   [28 chars]
    en  What was the first day like?
    >>  ............................................
    pt  Como foi o primeiro dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.between_trades`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.none.trade_decision"}
- Then opens: `conversations.scene.work.none.followup`
- …where the player's next choices will be: "What do you spend your days on?" | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.succeeded.recounted
WHO    VILLAGER — what the player reads after pressing "What was the first day like?"
       spoken on: conversations.scene.work.none.trade_decision.succeeded.respond, button `ask_first_day`
       leaves the player on: conversations.scene.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.succeeded.recounted`: the villager reminisces. Subject `work.none.between_trades`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.succeeded.recounted/1   [131 chars]
    en  Long, and I was in the way for most of it. At %2$s there is a way of standing that says you belong there and I have not learned it.
    >>  ............................................
    pt  Longo, e passei quase todo ele atrapalhando. N%2$s existe um jeito de ficar de pé que diz que você pertence ali, e eu não aprendi.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.succeeded.recounted/2   [115 chars]
    en  Quiet. I had built it up into something enormous and it was mostly sweeping. I have never enjoyed sweeping so much.
    >>  ............................................
    pt  Calmo. Eu tinha transformado aquilo em algo enorme e era quase só varrer. Nunca gostei tanto de varrer.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.succeeded.recounted/3   [121 chars]
    en  I made one mistake before noon and nobody made anything of it, which frightened me more than being shouted at would have.
    >>  ............................................
    pt  Cometi um erro antes do meio-dia e ninguém fez caso, o que me assustou mais do que se tivessem gritado.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with your day."

*stance family `exit` · tone `plain` · answers the beat(s) `work.none.trade_decision.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.none.trade_decision.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.none.trade_decision.succeeded.respond.leave   [34 chars]
    en  I'll let you get on with your day.
    >>  ............................................
    pt  Vou deixar você tocar o seu dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with your day."
       spoken on: conversations.scene.work.none.trade_decision.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.topic.work.none.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.none.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.none.craft` — e.g. "I've half of six trades. Enough to be useful in all of them and enough to be trusted with none."


```text
POOL   dialogue key: dialogue.conversations.topic.work.none.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.none.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.none.craft.respond   [27 chars]
    en  That's the whole inventory.
    >>  ............................................
    pt  É todo o inventário.
    >>  ............................................
```


### Button `ask_six` — "Which six?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.craft` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.craft.ask_six` — accepted phrasings: "which six"
  - the message must contain one of: `six`, `trades`
  - scored words: `six`(1.5), `trades`(1.2), `which`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.craft.respond.ask_six
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.craft.respond.ask_six   [10 chars]
    en  Which six?
    >>  ............................................
    pt  Quais seis?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.craft.ask_six`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.craft.ask_six
WHO    VILLAGER — what the player reads after pressing "Which six?"
       spoken on: conversations.topic.work.none.craft.respond, button `ask_six`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.craft.ask_six`: the villager explains. Subject `work.none.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.craft.ask_six/1   [95 chars]
    en  Mill, forge, field, roof, pen and pit. Two months each, and then somebody's son turned sixteen.
    >>  ............................................
    pt  Moinho, forja, campo, telhado, curral e tanque. Dois meses cada, e aí o filho de alguém fez dezesseis.
    >>  ............................................
  dialogue.conversations.work.prof.none.craft.ask_six/2   [87 chars]
    en  Enough of each to know exactly how much I don't know, %1$s, which is its own education.
    >>  ............................................
    pt  De cada um o bastante pra saber exatamente o quanto eu não sei, %1$s, que é uma educação própria.
    >>  ............................................
```


### Button `admire` — "Knowing who needs what is a skill people build careers on."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.none.craft` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.craft.admire` — accepted phrasings: "knowing who needs what is a skill people build careers on"
  - the message must contain one of: `skill`, `careers`, `needs`
  - scored words: `skill`(1.5), `careers`(1.2), `needs`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.craft.respond.admire   [58 chars]
    en  Knowing who needs what is a skill people build careers on.
    >>  ............................................
    pt  Saber quem precisa do quê é uma habilidade que sustenta carreiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.none.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.none.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.craft.admire
WHO    VILLAGER — what the player reads after pressing "Knowing who needs what is a skill people build careers on."
       spoken on: conversations.topic.work.none.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.craft.admire`: the villager accepts. Subject `work.none.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.craft.admire/1   [60 chars]
    en  ...Elsewhere, maybe. Here it's called being about the place.
    >>  ............................................
    pt  ...Em outro lugar, talvez. Aqui chamam de estar por aí.
    >>  ............................................
  dialogue.conversations.work.prof.none.craft.admire/2   [76 chars]
    en  No one has ever called it a skill in front of me. Say it again slower, %1$s.
    >>  ............................................
    pt  Ninguém nunca chamou de habilidade na minha frente. Diga de novo devagar, %1$s.
    >>  ............................................
```


### Button `ask_sixteen` — "Somebody's son turned sixteen?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.craft` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.craft.ask_sixteen` — accepted phrasings: "somebody's son turned sixteen"
  - the message must contain one of: `sixteen`, `son`, `inherited`
  - scored words: `sixteen`(1.5), `son`(1.2), `inherited`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.craft.respond.ask_sixteen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.craft.respond.ask_sixteen   [30 chars]
    en  Somebody's son turned sixteen?
    >>  ............................................
    pt  O filho de alguém fez dezesseis?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.craft.ask_sixteen`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.craft.ask_sixteen
WHO    VILLAGER — what the player reads after pressing "Somebody's son turned sixteen?"
       spoken on: conversations.topic.work.none.craft.respond, button `ask_sixteen`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.craft.ask_sixteen`: the villager explains. Subject `work.none.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.craft.ask_sixteen/1   [86 chars]
    en  Every time. A trade goes to blood before it goes to whoever's been carrying the sacks.
    >>  ............................................
    pt  Toda vez. Um ofício vai pro sangue antes de ir pra quem carregou os sacos.
    >>  ............................................
  dialogue.conversations.work.prof.none.craft.ask_sixteen/2   [80 chars]
    en  Four of the six went that way. I don't blame them and I've counted anyway, %1$s.
    >>  ............................................
    pt  Quatro dos seis foram assim. Não os culpo e contei mesmo assim, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.none.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.craft.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.work.none.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.none.followup`

**Reached from 20 route(s):** `conversations.scene.work.none.followup` / `ask_more`; `conversations.topic.work.none.craft.respond` / `ask_six`; `conversations.topic.work.none.craft.respond` / `admire`; `conversations.topic.work.none.craft.respond` / `ask_sixteen`; `conversations.topic.work.none.future.respond` / `ask_fill`; `conversations.topic.work.none.future.respond` / `encourage`; `conversations.topic.work.none.future.respond` / `ask_east`; `conversations.topic.work.none.respond` / `ask_hard`; `conversations.topic.work.none.respond` / `value`; `conversations.topic.work.none.respond` / `challenge`; `conversations.topic.work.none.respond` / `challenge`; `conversations.topic.work.none.risk.respond` / `ask_queue` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.none.challenge.landed` — e.g. "I will. I'd rather choose late and right than early and stuck."
- `conversations.work.prof.none.challenge.stung` — e.g. "...I'm aware. Thank you. That's very helpful."
- `conversations.work.prof.none.craft.admire` — e.g. "...Elsewhere, maybe. Here it's called being about the place."
- `conversations.work.prof.none.craft.ask_six` — e.g. "Mill, forge, field, roof, pen and pit. Two months each, and then somebody's son turned sixteen."
- `conversations.work.prof.none.craft.ask_sixteen` — e.g. "Every time. A trade goes to blood before it goes to whoever's been carrying the sacks."
- `conversations.work.prof.none.future.ask_east` — e.g. "Everyone here. Which is a stupid answer from a man nobody has a post for, and it's the true one."
- `conversations.work.prof.none.future.ask_fill` — e.g. "The gap. That's the test — if nothing changes when I go, then I was never a post."
- `conversations.work.prof.none.future.encourage` — e.g. "...Ask first. Right. I'd been treating it as a thing that would be offered, %1$s."
- `conversations.work.prof.none.hard` — e.g. "In summer. In February it's a different word entirely and nobody uses that one."
- `conversations.work.prof.none.risk.ask_bottom` — e.g. "A post with a title and a wage, however small. Then I'd be somebody the winter has to account for."
- `conversations.work.prof.none.risk.ask_queue` — e.g. "Twice. The butcher found reasons to need help both times, and we've never discussed it."
- `conversations.work.prof.none.risk.sympathise` — e.g. "...No. And I've never let myself put it that plainly, so thank you and also ouch."
- `conversations.work.prof.none.task.ask_named` — e.g. "Desperately, and I've given up saying so because of how it makes people look at me."
- `conversations.work.prof.none.task.ask_sacks` — e.g. "In flour, and a bit more than the hour's worth. He knows what he's doing and so do I."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.none.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.none.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.none.followup   [31 chars]
    en  That's the lean season, anyway.
    >>  ............................................
    pt  É a estação magra, enfim.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.none.challenge.landed`, `work.none.challenge.stung`, `work.none.craft.admire`, `work.none.craft.ask_six`, `work.none.craft.ask_sixteen`, `work.none.future.ask_east`, `work.none.future.ask_fill`, `work.none.future.encourage`, `work.none.hard`, `work.none.risk.ask_bottom`, `work.none.risk.ask_queue`, `work.none.risk.sympathise`, `work.none.task.ask_named`, `work.none.task.ask_sacks`, `work.none.task.offer_hands`, `work.none.value`, `work.none.village.ask_after`, `work.none.village.ask_emergency`, `work.none.village.say_thanks` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.none.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `aloud`
  - scored words: `thought`(1.2), `aloud`(1.5), `useful`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.none.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.none.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.none.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.none.thanks`: the villager accepts. Subject `work.none.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.thanks/1   [61 chars]
    en  Neither had I until I said it out loud just now. Useful, you.
    >>  ............................................
    pt  Nem eu, até dizer em voz alta agora. Você é útil.
    >>  ............................................
  dialogue.conversations.work.prof.none.thanks/2   [72 chars]
    en  It's easier to hear from someone else's mouth, %1$s. Thank you for that.
    >>  ............................................
    pt  É mais fácil ouvir da boca de outra pessoa, %1$s. Obrigado por isso.
    >>  ............................................
```


### Button `ask_more` — "Which trade tempts you most?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.challenge.landed`, `work.none.challenge.stung`, `work.none.craft.admire`, `work.none.craft.ask_six`, `work.none.craft.ask_sixteen`, `work.none.future.ask_east`, `work.none.future.ask_fill`, `work.none.future.encourage`, `work.none.hard`, `work.none.risk.ask_bottom`, `work.none.risk.ask_queue`, `work.none.risk.sympathise`, `work.none.task.ask_named`, `work.none.task.ask_sacks`, `work.none.task.offer_hands`, `work.none.value`, `work.none.village.ask_after`, `work.none.village.ask_emergency`, `work.none.village.say_thanks` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.none.more` — accepted phrasings: "which trade tempts you most"
  - the message must contain one of: `tempts`, `trade`, `considering`
  - scored words: `tempts`(1.5), `trade`(1.0), `considering`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.followup.ask_more   [28 chars]
    en  Which trade tempts you most?
    >>  ............................................
    pt  Qual ofício te tenta mais?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.none.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.more
WHO    VILLAGER — what the player reads after pressing "Which trade tempts you most?"
       spoken on: conversations.topic.work.none.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.none.more`: the villager discloses. Subject `work.none.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.more/1   [69 chars]
    en  The mason's. Something about work that's still there when you're not.
    >>  ............................................
    pt  O de pedreiro. Alguma coisa em trabalho que continua ali quando você não está.
    >>  ............................................
  dialogue.conversations.work.prof.none.more/2   [86 chars]
    en  The library, if I'm honest. Don't tell anyone. It's not a thing you say out loud here.
    >>  ............................................
    pt  A biblioteca, se eu for honesto. Não conte a ninguém. Não é coisa que se diga em voz alta aqui.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Something that lasts. I've nothing yet that would last a week without me.
    >>  ............................................
    pt  O do pedreiro. Algo que dure. Eu não tenho nada que durasse uma semana sem mim.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.none.more/2
    en  A post. If nothing changes when I go, then I was never a post, and that's the part I can't shake.
    >>  ............................................
    pt  Um posto. Se nada mudar quando eu for, então eu nunca fui um posto, e é a parte que não sai da cabeça.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Slow work that outlasts the worker. There's a great deal of comfort in that.
    >>  ............................................
    pt  O do pedreiro. Trabalho lento que sobrevive ao trabalhador. Tem muito conforto nisso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.none.more/2
    en  A post. It'll come or it won't. I've been useful without one for years.
    >>  ............................................
    pt  Um posto. Vem ou não vem. Eu fui útil sem um por anos.
    >>  ............................................
  confident.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Something about work that's still there when you're not.
    >>  ............................................
    pt  O do pedreiro. Algo sobre trabalho que continua lá quando você não está.
    >>  ............................................
  confident.dialogue.conversations.work.prof.none.more/2
    en  A post. Anything the village would have to fill again if I left.
    >>  ............................................
    pt  Um posto. Qualquer coisa que o vilarejo teria que preencher de novo se eu fosse.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Something about work that's still there when you're not.
    >>  ............................................
    pt  O do pedreiro. Algo sobre trabalho que continua lá quando você não está.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.none.more/2
    en  A post. Anything the village would have to fill again if I left.
    >>  ............................................
    pt  Um posto. Qualquer coisa que o vilarejo teria que preencher de novo se eu fosse.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.none.more/1
    en  The mason's. He builds things that outlast people, and he'd let me carry for a season.
    >>  ............................................
    pt  O do pedreiro. Ele constrói coisas que sobrevivem às pessoas, e me deixaria carregar por uma estação.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.none.more/2
    en  A post. Somebody would have to fill the gap when I go — that's the whole test, and I'd like to pass it.
    >>  ............................................
    pt  Um posto. Alguém teria que preencher a lacuna quando eu for — é todo o teste, e eu queria passar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.none.more/1
    en  The mason's. He builds things that outlast people, and he'd let me carry for a season.
    >>  ............................................
    pt  O do pedreiro. Ele constrói coisas que sobrevivem às pessoas, e me deixaria carregar por uma estação.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.none.more/2
    en  A post. Somebody would have to fill the gap when I go — that's the whole test, and I'd like to pass it.
    >>  ............................................
    pt  Um posto. Alguém teria que preencher a lacuna quando eu for — é todo o teste, e eu queria passar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.none.more/1
    en  The mason's. He builds things that outlast people, and he'd let me carry for a season.
    >>  ............................................
    pt  O do pedreiro. Ele constrói coisas que sobrevivem às pessoas, e me deixaria carregar por uma estação.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.none.more/2
    en  A post. Somebody would have to fill the gap when I go — that's the whole test, and I'd like to pass it.
    >>  ............................................
    pt  Um posto. Alguém teria que preencher a lacuna quando eu for — é todo o teste, e eu queria passar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Something that lasts. I've nothing yet that would last a week without me.
    >>  ............................................
    pt  O do pedreiro. Algo que dure. Eu não tenho nada que durasse uma semana sem mim.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.none.more/2
    en  A post. If nothing changes when I go, then I was never a post, and that's the part I can't shake.
    >>  ............................................
    pt  Um posto. Se nada mudar quando eu for, então eu nunca fui um posto, e é a parte que não sai da cabeça.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Something about work that's still there when you're not.
    >>  ............................................
    pt  O do pedreiro. Algo sobre trabalho que continua lá quando você não está.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.none.more/2
    en  A post. Anything the village would have to fill again if I left.
    >>  ............................................
    pt  Um posto. Qualquer coisa que o vilarejo teria que preencher de novo se eu fosse.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Something about work that's still there when you're not.
    >>  ............................................
    pt  O do pedreiro. Algo sobre trabalho que continua lá quando você não está.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.none.more/2
    en  A post. Anything the village would have to fill again if I left.
    >>  ............................................
    pt  Um posto. Qualquer coisa que o vilarejo teria que preencher de novo se eu fosse.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Work that's still there when you're not. That's the whole of the appeal.
    >>  ............................................
    pt  O do pedreiro. Trabalho que continua lá quando você não está. É todo o apelo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.none.more/2
    en  A post. Sacks, roofs, the flood line, the counting. All of it unnamed and all of it mine.
    >>  ............................................
    pt  Um posto. Sacos, telhados, a linha da cheia, a contagem. Tudo sem nome e tudo meu.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Slow work that outlasts the worker. There's a great deal of comfort in that.
    >>  ............................................
    pt  O do pedreiro. Trabalho lento que sobrevive ao trabalhador. Tem muito conforto nisso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.none.more/2
    en  A post. It'll come or it won't. I've been useful without one for years.
    >>  ............................................
    pt  Um posto. Vem ou não vem. Eu fui útil sem um por anos.
    >>  ............................................
  odd.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Work that's still there when you're not. That's the whole of the appeal.
    >>  ............................................
    pt  O do pedreiro. Trabalho que continua lá quando você não está. É todo o apelo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.none.more/2
    en  A post. Sacks, roofs, the flood line, the counting. All of it unnamed and all of it mine.
    >>  ............................................
    pt  Um posto. Sacos, telhados, a linha da cheia, a contagem. Tudo sem nome e tudo meu.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Slow work that outlasts the worker. There's a great deal of comfort in that.
    >>  ............................................
    pt  O do pedreiro. Trabalho lento que sobrevive ao trabalhador. Tem muito conforto nisso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.none.more/2
    en  A post. It'll come or it won't. I've been useful without one for years.
    >>  ............................................
    pt  Um posto. Vem ou não vem. Eu fui útil sem um por anos.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.none.more/1
    en  The mason's! Something about making a thing that's still there when you're not. Very cheering.
    >>  ............................................
    pt  O do pedreiro! Algo sobre fazer uma coisa que continua lá quando você não está. Muito animador.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.none.more/2
    en  A post. Not a grand one. Just one with a title, so the winter has to account for me.
    >>  ............................................
    pt  Um posto. Não grandioso. Só um com título, pra que o inverno tenha que me contar.
    >>  ............................................
  playful.dialogue.conversations.work.prof.none.more/1
    en  The mason's! Something about making a thing that's still there when you're not. Very cheering.
    >>  ............................................
    pt  O do pedreiro! Algo sobre fazer uma coisa que continua lá quando você não está. Muito animador.
    >>  ............................................
  playful.dialogue.conversations.work.prof.none.more/2
    en  A post. Not a grand one. Just one with a title, so the winter has to account for me.
    >>  ............................................
    pt  Um posto. Não grandioso. Só um com título, pra que o inverno tenha que me contar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Slow work that outlasts the worker. There's a great deal of comfort in that.
    >>  ............................................
    pt  O do pedreiro. Trabalho lento que sobrevive ao trabalhador. Tem muito conforto nisso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.none.more/2
    en  A post. It'll come or it won't. I've been useful without one for years.
    >>  ............................................
    pt  Um posto. Vem ou não vem. Eu fui útil sem um por anos.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Something that lasts. I've nothing yet that would last a week without me.
    >>  ............................................
    pt  O do pedreiro. Algo que dure. Eu não tenho nada que durasse uma semana sem mim.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.none.more/2
    en  A post. If nothing changes when I go, then I was never a post, and that's the part I can't shake.
    >>  ............................................
    pt  Um posto. Se nada mudar quando eu for, então eu nunca fui um posto, e é a parte que não sai da cabeça.
    >>  ............................................
  shy.dialogue.conversations.work.prof.none.more/1
    en  The mason's. Work that's still there when you're not. That's the whole of the appeal.
    >>  ............................................
    pt  O do pedreiro. Trabalho que continua lá quando você não está. É todo o apelo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.none.more/2
    en  A post. Sacks, roofs, the flood line, the counting. All of it unnamed and all of it mine.
    >>  ............................................
    pt  Um posto. Sacos, telhados, a linha da cheia, a contagem. Tudo sem nome e tudo meu.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.none.more/1
    en  The mason's! Something about making a thing that's still there when you're not. Very cheering.
    >>  ............................................
    pt  O do pedreiro! Algo sobre fazer uma coisa que continua lá quando você não está. Muito animador.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.none.more/2
    en  A post. Not a grand one. Just one with a title, so the winter has to account for me.
    >>  ............................................
    pt  Um posto. Não grandioso. Só um com título, pra que o inverno tenha que me contar.
    >>  ............................................
  witty.dialogue.conversations.work.prof.none.more/1
    en  The mason's! Something about making a thing that's still there when you're not. Very cheering.
    >>  ............................................
    pt  O do pedreiro! Algo sobre fazer uma coisa que continua lá quando você não está. Muito animador.
    >>  ............................................
  witty.dialogue.conversations.work.prof.none.more/2
    en  A post. Not a grand one. Just one with a title, so the winter has to account for me.
    >>  ............................................
    pt  Um posto. Não grandioso. Só um com título, pra que o inverno tenha que me contar.
    >>  ............................................
```

</details>


### Button `leave` — "Good luck deciding."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.none.challenge.landed`, `work.none.challenge.stung`, `work.none.craft.admire`, `work.none.craft.ask_six`, `work.none.craft.ask_sixteen`, `work.none.future.ask_east`, `work.none.future.ask_fill`, `work.none.future.encourage`, `work.none.hard`, `work.none.risk.ask_bottom`, `work.none.risk.ask_queue`, `work.none.risk.sympathise`, `work.none.task.ask_named`, `work.none.task.ask_sacks`, `work.none.task.offer_hands`, `work.none.value`, `work.none.village.ask_after`, `work.none.village.ask_emergency`, `work.none.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.followup.leave   [19 chars]
    en  Good luck deciding.
    >>  ............................................
    pt  Boa sorte na escolha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "Good luck deciding."
       spoken on: conversations.topic.work.none.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.none.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.none.future` — e.g. "A post. Not a grand one. Something the village would have to fill again if I left."


```text
POOL   dialogue key: dialogue.conversations.topic.work.none.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.none.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.none.future.respond   [22 chars]
    en  That's the fork in it.
    >>  ............................................
    pt  É a bifurcação.
    >>  ............................................
```


### Button `ask_fill` — "What would they have to fill?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.future` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.future.ask_fill` — accepted phrasings: "what would they have to fill"
  - the message must contain one of: `fill`, `gap`, `replace`
  - scored words: `fill`(1.5), `gap`(1.5), `replace`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.future.respond.ask_fill
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.future.respond.ask_fill   [29 chars]
    en  What would they have to fill?
    >>  ............................................
    pt  O que teriam que preencher?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.future.ask_fill`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.future.ask_fill
WHO    VILLAGER — what the player reads after pressing "What would they have to fill?"
       spoken on: conversations.topic.work.none.future.respond, button `ask_fill`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.future.ask_fill`: the villager explains. Subject `work.none.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.future.ask_fill/1   [81 chars]
    en  The gap. That's the test — if nothing changes when I go, then I was never a post.
    >>  ............................................
    pt  A lacuna. É o teste — se nada muda quando eu for, então eu nunca fui um posto.
    >>  ............................................
  dialogue.conversations.work.prof.none.future.ask_fill/2   [87 chars]
    en  Sacks, roofs, the flood line, the counting. All of it unnamed and all of it mine, %1$s.
    >>  ............................................
    pt  Sacos, telhados, a linha da cheia, a contagem. Tudo sem nome e tudo meu, %1$s.
    >>  ............................................
```


### Button `encourage` — "Ask for the post before you go east."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.none.future` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.future.encourage` — accepted phrasings: "ask for the post before you go east"
  - the message must contain one of: `ask`, `first`
  - scored words: `ask`(1.2), `first`(1.2), `before`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.future.respond.encourage   [36 chars]
    en  Ask for the post before you go east.
    >>  ............................................
    pt  Peça o posto antes de ir pro leste.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.none.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.none.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.future.encourage
WHO    VILLAGER — what the player reads after pressing "Ask for the post before you go east."
       spoken on: conversations.topic.work.none.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.future.encourage`: the villager accepts. Subject `work.none.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.future.encourage/1   [81 chars]
    en  ...Ask first. Right. I'd been treating it as a thing that would be offered, %1$s.
    >>  ............................................
    pt  ...Pedir primeiro. Certo. Eu vinha tratando como algo que seria oferecido, %1$s.
    >>  ............................................
  dialogue.conversations.work.prof.none.future.encourage/2   [86 chars]
    en  Nobody's told me to ask. Everybody's told me to be patient. Those are opposite advice.
    >>  ............................................
    pt  Ninguém me disse pra pedir. Todo mundo disse pra ter paciência. São conselhos opostos.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I've been waiting to be offered because asking risks a no.
    >>  ............................................
    pt  ...Pedir primeiro. Esperei ser oferecido porque pedir arrisca um não.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. Patience has been easier than the asking, and I knew it.
    >>  ............................................
    pt  Todos dizem tenha paciência. Paciência foi mais fácil que pedir, e eu sabia.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I've had years of waiting to be offered and it never once happened.
    >>  ............................................
    pt  ...Pedir primeiro. Tive anos esperando ser oferecido e nunca aconteceu.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. Patience is what you say to somebody you've no plan for.
    >>  ............................................
    pt  Todos dizem tenha paciência. Paciência é o que se diz a quem não se tem plano.
    >>  ............................................
  confident.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. Right. I'd been treating it as a thing that would be offered.
    >>  ............................................
    pt  ...Pedir primeiro. Certo. Eu tratava como algo que seria oferecido.
    >>  ............................................
  confident.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody's told me to be patient. Those are opposites.
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos me mandaram ter paciência. São opostos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. Right. I'd been treating it as a thing that would be offered.
    >>  ............................................
    pt  ...Pedir primeiro. Certo. Eu tratava como algo que seria oferecido.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody's told me to be patient. Those are opposites.
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos me mandaram ter paciência. São opostos.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first, %1$s. I'd been waiting to be offered, which is not the same thing.
    >>  ............................................
    pt  ...Pedir primeiro, %1$s. Eu esperava ser oferecido, o que não é a mesma coisa.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody's told me to be patient, and you're the first to differ.
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos mandaram ter paciência, e você é o primeiro a discordar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first, %1$s. I'd been waiting to be offered, which is not the same thing.
    >>  ............................................
    pt  ...Pedir primeiro, %1$s. Eu esperava ser oferecido, o que não é a mesma coisa.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody's told me to be patient, and you're the first to differ.
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos mandaram ter paciência, e você é o primeiro a discordar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first, %1$s. I'd been waiting to be offered, which is not the same thing.
    >>  ............................................
    pt  ...Pedir primeiro, %1$s. Eu esperava ser oferecido, o que não é a mesma coisa.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody's told me to be patient, and you're the first to differ.
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos mandaram ter paciência, e você é o primeiro a discordar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I've been waiting to be offered because asking risks a no.
    >>  ............................................
    pt  ...Pedir primeiro. Esperei ser oferecido porque pedir arrisca um não.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. Patience has been easier than the asking, and I knew it.
    >>  ............................................
    pt  Todos dizem tenha paciência. Paciência foi mais fácil que pedir, e eu sabia.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. Right. I'd been treating it as a thing that would be offered.
    >>  ............................................
    pt  ...Pedir primeiro. Certo. Eu tratava como algo que seria oferecido.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody's told me to be patient. Those are opposites.
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos me mandaram ter paciência. São opostos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. Right. I'd been treating it as a thing that would be offered.
    >>  ............................................
    pt  ...Pedir primeiro. Certo. Eu tratava como algo que seria oferecido.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody's told me to be patient. Those are opposites.
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos me mandaram ter paciência. São opostos.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I'd been waiting to be offered.
    >>  ............................................
    pt  ...Pedir primeiro. Eu esperava ser oferecido.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. You say ask.
    >>  ............................................
    pt  Todos dizem tenha paciência. Você diz peça.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I've had years of waiting to be offered and it never once happened.
    >>  ............................................
    pt  ...Pedir primeiro. Tive anos esperando ser oferecido e nunca aconteceu.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. Patience is what you say to somebody you've no plan for.
    >>  ............................................
    pt  Todos dizem tenha paciência. Paciência é o que se diz a quem não se tem plano.
    >>  ............................................
  odd.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I'd been waiting to be offered.
    >>  ............................................
    pt  ...Pedir primeiro. Eu esperava ser oferecido.
    >>  ............................................
  odd.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. You say ask.
    >>  ............................................
    pt  Todos dizem tenha paciência. Você diz peça.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I've had years of waiting to be offered and it never once happened.
    >>  ............................................
    pt  ...Pedir primeiro. Tive anos esperando ser oferecido e nunca aconteceu.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. Patience is what you say to somebody you've no plan for.
    >>  ............................................
    pt  Todos dizem tenha paciência. Paciência é o que se diz a quem não se tem plano.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first! Right. I'd been treating it as a thing that would be offered to me.
    >>  ............................................
    pt  ...Pedir primeiro! Certo. Eu tratava como algo que me seria oferecido.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody says be patient. Those are opposite advice!
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos dizem tenha paciência. São conselhos opostos!
    >>  ............................................
  playful.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first! Right. I'd been treating it as a thing that would be offered to me.
    >>  ............................................
    pt  ...Pedir primeiro! Certo. Eu tratava como algo que me seria oferecido.
    >>  ............................................
  playful.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody says be patient. Those are opposite advice!
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos dizem tenha paciência. São conselhos opostos!
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I've had years of waiting to be offered and it never once happened.
    >>  ............................................
    pt  ...Pedir primeiro. Tive anos esperando ser oferecido e nunca aconteceu.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. Patience is what you say to somebody you've no plan for.
    >>  ............................................
    pt  Todos dizem tenha paciência. Paciência é o que se diz a quem não se tem plano.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I've been waiting to be offered because asking risks a no.
    >>  ............................................
    pt  ...Pedir primeiro. Esperei ser oferecido porque pedir arrisca um não.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. Patience has been easier than the asking, and I knew it.
    >>  ............................................
    pt  Todos dizem tenha paciência. Paciência foi mais fácil que pedir, e eu sabia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first. I'd been waiting to be offered.
    >>  ............................................
    pt  ...Pedir primeiro. Eu esperava ser oferecido.
    >>  ............................................
  shy.dialogue.conversations.work.prof.none.future.encourage/2
    en  Everyone says be patient. You say ask.
    >>  ............................................
    pt  Todos dizem tenha paciência. Você diz peça.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first! Right. I'd been treating it as a thing that would be offered to me.
    >>  ............................................
    pt  ...Pedir primeiro! Certo. Eu tratava como algo que me seria oferecido.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody says be patient. Those are opposite advice!
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos dizem tenha paciência. São conselhos opostos!
    >>  ............................................
  witty.dialogue.conversations.work.prof.none.future.encourage/1
    en  ...Ask first! Right. I'd been treating it as a thing that would be offered to me.
    >>  ............................................
    pt  ...Pedir primeiro! Certo. Eu tratava como algo que me seria oferecido.
    >>  ............................................
  witty.dialogue.conversations.work.prof.none.future.encourage/2
    en  Nobody's told me to ask. Everybody says be patient. Those are opposite advice!
    >>  ............................................
    pt  Ninguém me mandou pedir. Todos dizem tenha paciência. São conselhos opostos!
    >>  ............................................
```

</details>


### Button `ask_east` — "What's holding you from the town?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.future` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.future.ask_east` — accepted phrasings: "what's holding you from the town"
  - the message must contain one of: `town`, `east`, `holding`
  - scored words: `town`(1.5), `east`(1.2), `holding`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.future.respond.ask_east
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.future.respond.ask_east   [33 chars]
    en  What's holding you from the town?
    >>  ............................................
    pt  O que te segura da cidade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.future.ask_east`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.future.ask_east
WHO    VILLAGER — what the player reads after pressing "What's holding you from the town?"
       spoken on: conversations.topic.work.none.future.respond, button `ask_east`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.future.ask_east`: the villager explains. Subject `work.none.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.future.ask_east/1   [96 chars]
    en  Everyone here. Which is a stupid answer from a man nobody has a post for, and it's the true one.
    >>  ............................................
    pt  Todo mundo daqui. É uma resposta idiota de um homem sem posto, e é a verdadeira.
    >>  ............................................
  dialogue.conversations.work.prof.none.future.ask_east/2   [89 chars]
    en  Four days is nothing. Knowing nobody at the other end is the four days that matter, %1$s.
    >>  ............................................
    pt  Quatro dias não é nada. Não conhecer ninguém do outro lado são os quatro dias que importam, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.none.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.future.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.work.none.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.none.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.none` — e.g. "No trade yet — I'm between destinies. The lean season of a life, my gran called it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.none.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.none.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.none.respond   [27 chars]
    en  That's where I am, for now.
    >>  ............................................
    pt  É onde eu estou, por enquanto.
    >>  ............................................
```


### Button `ask_hard` — "Is it as free as it sounds?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.identity` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.hard` — accepted phrasings: "is it as free as it sounds"
  - the message must contain one of: `free`, `sounds`, `winter`
  - scored words: `free`(1.5), `sounds`(1.0), `winter`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.respond.ask_hard   [27 chars]
    en  Is it as free as it sounds?
    >>  ............................................
    pt  É tão livre quanto parece?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.none.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.hard
WHO    VILLAGER — what the player reads after pressing "Is it as free as it sounds?"
       spoken on: conversations.topic.work.none.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.hard`: the villager explains. Subject `work.none.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / ask_more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "There's time in that most people never get."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.none.identity` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.value` — accepted phrasings: "there's time in that most people never get"
  - the message must contain one of: `time`, `freedom`
  - scored words: `time`(1.2), `never`(0.8), `freedom`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.respond.value   [43 chars]
    en  There's time in that most people never get.
    >>  ............................................
    pt  Tem um tempo aí que a maioria nunca tem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.none.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.none.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.value
WHO    VILLAGER — what the player reads after pressing "There's time in that most people never get."
       spoken on: conversations.topic.work.none.respond, button `value`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.value`: the villager accepts. Subject `work.none.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.value/1   [71 chars]
    en  There is. I keep telling myself that, and about half the days it works.
    >>  ............................................
    pt  Tem. Eu fico repetindo isso pra mim, e em quase metade dos dias funciona.
    >>  ............................................
  dialogue.conversations.work.prof.none.value/2   [67 chars]
    en  That's the good version of it and I'll take the good version today.
    >>  ............................................
    pt  É a versão boa da coisa e hoje eu fico com a versão boa.
    >>  ............................................
```


### Button `challenge` — "You'll have to choose something eventually."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.none.identity` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.challenge` — accepted phrasings: "you'll have to choose something eventually"
  - the message must contain one of: `choose`, `eventually`, `decide`
  - scored words: `choose`(1.5), `eventually`(1.2), `decide`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.respond.challenge   [43 chars]
    en  You'll have to choose something eventually.
    >>  ............................................
    pt  Você vai ter que escolher alguma coisa uma hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.none.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.none.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You'll have to choose something eventually."
       spoken on: conversations.topic.work.none.respond, button `challenge`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.challenge.landed`: the villager resists. Subject `work.none.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.challenge.landed/1   [62 chars]
    en  I will. I'd rather choose late and right than early and stuck.
    >>  ............................................
    pt  Vou. Prefiro escolher tarde e certo a cedo e preso.
    >>  ............................................
  dialogue.conversations.work.prof.none.challenge.landed/2   [51 chars]
    en  Everyone tells me that. Nobody tells me what, %1$s.
    >>  ............................................
    pt  Todo mundo me diz isso. Ninguém me diz o quê, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.none.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.none.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You'll have to choose something eventually."
       spoken on: conversations.topic.work.none.respond, button `challenge`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.challenge.stung`: the villager resists. Subject `work.none.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.challenge.stung/1   [45 chars]
    en  ...I'm aware. Thank you. That's very helpful.
    >>  ............................................
    pt  ...Eu sei. Obrigado. Muito útil.
    >>  ............................................
  dialogue.conversations.work.prof.none.challenge.stung/2   [67 chars]
    en  Eventually. Aye. There's a bell in that word and I hear it nightly.
    >>  ............................................
    pt  Uma hora. É. Tem um sino nessa palavra e eu ouço toda noite.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.none.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.work.none.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.none.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.none.risk` — e.g. "A bad winter and I'm the one nobody has a reason to feed. I know exactly where I sit in that queue."


```text
POOL   dialogue key: dialogue.conversations.topic.work.none.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.none.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.none.risk.respond   [25 chars]
    en  That's what's underneath.
    >>  ............................................
    pt  É o que está por baixo.
    >>  ............................................
```


### Button `ask_queue` — "Has it come to that?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.risk` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.risk.ask_queue` — accepted phrasings: "has it come to that"
  - the message must contain one of: `hungry`, `fed`
  - scored words: `come`(0.8), `hungry`(1.5), `fed`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.risk.respond.ask_queue
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.risk.respond.ask_queue   [20 chars]
    en  Has it come to that?
    >>  ............................................
    pt  Já chegou a esse ponto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.risk.ask_queue`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.risk.ask_queue
WHO    VILLAGER — what the player reads after pressing "Has it come to that?"
       spoken on: conversations.topic.work.none.risk.respond, button `ask_queue`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.risk.ask_queue`: the villager explains. Subject `work.none.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.risk.ask_queue/1   [87 chars]
    en  Twice. The butcher found reasons to need help both times, and we've never discussed it.
    >>  ............................................
    pt  Duas vezes. O açougueiro achou motivos pra precisar de ajuda nas duas, e nunca conversamos sobre isso.
    >>  ............................................
  dialogue.conversations.work.prof.none.risk.ask_queue/2   [78 chars]
    en  Not yet. I've been careful to be useful to enough people that it hasn't, %1$s.
    >>  ............................................
    pt  Ainda não. Tenho o cuidado de ser útil a gente suficiente pra não chegar, %1$s.
    >>  ............................................
```


### Button `sympathise` — "Being useful shouldn't have to be a survival plan."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.none.risk` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.risk.sympathise` — accepted phrasings: "being useful shouldn't have to be a survival plan"
  - the message must contain one of: `useful`, `survival`
  - scored words: `useful`(1.5), `survival`(1.5), `plan`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.risk.respond.sympathise   [50 chars]
    en  Being useful shouldn't have to be a survival plan.
    >>  ............................................
    pt  Ser útil não devia ter que ser plano de sobrevivência.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.none.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.none.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Being useful shouldn't have to be a survival plan."
       spoken on: conversations.topic.work.none.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.risk.sympathise`: the villager accepts. Subject `work.none.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.risk.sympathise/1   [81 chars]
    en  ...No. And I've never let myself put it that plainly, so thank you and also ouch.
    >>  ............................................
    pt  ...Não devia. E eu nunca me deixei dizer tão direto, então obrigado e também ai.
    >>  ............................................
  dialogue.conversations.work.prof.none.risk.sympathise/2   [87 chars]
    en  It shouldn't. It is. And I'd rather you knew that than thought I was cheerful about it.
    >>  ............................................
    pt  Não devia. É. E prefiro que você saiba a que ache que eu estou alegre com isso.
    >>  ............................................
```


### Button `ask_bottom` — "What would a bottom look like?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.risk` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.risk.ask_bottom` — accepted phrasings: "what would a bottom look like"
  - the message must contain one of: `bottom`, `wage`, `security`
  - scored words: `bottom`(1.5), `wage`(1.5), `security`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.risk.respond.ask_bottom
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.risk.respond.ask_bottom   [30 chars]
    en  What would a bottom look like?
    >>  ............................................
    pt  Como seria um fundo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.risk.ask_bottom`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.risk.ask_bottom
WHO    VILLAGER — what the player reads after pressing "What would a bottom look like?"
       spoken on: conversations.topic.work.none.risk.respond, button `ask_bottom`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.risk.ask_bottom`: the villager explains. Subject `work.none.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.risk.ask_bottom/1   [98 chars]
    en  A post with a title and a wage, however small. Then I'd be somebody the winter has to account for.
    >>  ............................................
    pt  Um posto com título e salário, por menor que seja. Aí eu seria alguém que o inverno tem que contar.
    >>  ............................................
  dialogue.conversations.work.prof.none.risk.ask_bottom/2   [77 chars]
    en  Anything written down with my name on it, %1$s. That's all a trade really is.
    >>  ............................................
    pt  Qualquer coisa escrita com meu nome, %1$s. É só isso que um ofício é.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.none.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.risk.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.work.none.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.none.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.none.task` — e.g. "Whatever wants doing. This morning it was the mill's sacks; this afternoon nobody's decided yet."


```text
POOL   dialogue key: dialogue.conversations.topic.work.none.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.none.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.none.task.respond   [23 chars]
    en  That's how the days go.
    >>  ............................................
    pt  É assim que os dias vão.
    >>  ............................................
```


### Button `ask_sacks` — "Does the mill pay you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.task` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.task.ask_sacks` — accepted phrasings: "does the mill pay you"
  - the message must contain one of: `mill`, `pay`, `sacks`
  - scored words: `mill`(1.5), `pay`(1.2), `sacks`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.task.respond.ask_sacks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.task.respond.ask_sacks   [22 chars]
    en  Does the mill pay you?
    >>  ............................................
    pt  O moinho te paga?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.task.ask_sacks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.task.ask_sacks
WHO    VILLAGER — what the player reads after pressing "Does the mill pay you?"
       spoken on: conversations.topic.work.none.task.respond, button `ask_sacks`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.task.ask_sacks`: the villager explains. Subject `work.none.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.task.ask_sacks/1   [85 chars]
    en  In flour, and a bit more than the hour's worth. He knows what he's doing and so do I.
    >>  ............................................
    pt  Em farinha, e um pouco mais que a hora vale. Ele sabe o que faz e eu também.
    >>  ............................................
  dialogue.conversations.work.prof.none.task.ask_sacks/2   [73 chars]
    en  Sometimes. It's not an arrangement, %1$s, and that's the part that wears.
    >>  ............................................
    pt  Às vezes. Não é um acordo, %1$s, e é essa a parte que cansa.
    >>  ............................................
```


### Button `offer_hands` — "I'll take the afternoon with you."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.none.task` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.task.offer_hands` — accepted phrasings: "i'll take the afternoon with you"
  - the message must contain one of: `afternoon`, `along`
  - scored words: `afternoon`(1.2), `along`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.task.respond.offer_hands   [33 chars]
    en  I'll take the afternoon with you.
    >>  ............................................
    pt  Eu pego a tarde com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.none.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.none.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I'll take the afternoon with you."
       spoken on: conversations.topic.work.none.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.task.offer_hands`: the villager accepts. Subject `work.none.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.task.offer_hands/1   [97 chars]
    en  ...Would you. Then we'll go and ask the mason, and he'll say yes to two where he'd say no to one.
    >>  ............................................
    pt  ...Você viria? Então vamos pedir ao pedreiro, e ele diz sim pra dois onde diria não pra um.
    >>  ............................................
  dialogue.conversations.work.prof.none.task.offer_hands/2   [83 chars]
    en  That's the first time anyone's offered to come along, %1$s, rather than to send me.
    >>  ............................................
    pt  É a primeira vez que alguém se oferece pra vir junto, %1$s, em vez de me mandar.
    >>  ............................................
```


### Button `ask_named` — "Would you want something with a name to it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.task` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.task.ask_named` — accepted phrasings: "would you want something with a name to it"
  - the message must contain one of: `named`, `title`
  - scored words: `named`(1.5), `title`(1.5), `something`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.task.respond.ask_named
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.task.respond.ask_named   [43 chars]
    en  Would you want something with a name to it?
    >>  ............................................
    pt  Você quereria algo com nome?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.task.ask_named`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.task.ask_named
WHO    VILLAGER — what the player reads after pressing "Would you want something with a name to it?"
       spoken on: conversations.topic.work.none.task.respond, button `ask_named`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.task.ask_named`: the villager explains. Subject `work.none.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.task.ask_named/1   [83 chars]
    en  Desperately, and I've given up saying so because of how it makes people look at me.
    >>  ............................................
    pt  Desesperadamente, e eu desisti de dizer por causa de como me olham.
    >>  ............................................
  dialogue.conversations.work.prof.none.task.ask_named/2   [65 chars]
    en  Any name. I'd sweep the church if the sweeping had a title, %1$s.
    >>  ............................................
    pt  Qualquer nome. Eu varreria a igreja se varrer tivesse um título, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.none.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.task.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.work.none.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.none.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.none.village` — e.g. "Every trade here has had a week of me in it. Nobody adds those weeks up, but they're in the walls."


```text
POOL   dialogue key: dialogue.conversations.topic.work.none.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.none.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.none.village.respond   [22 chars]
    en  That's my share of it.
    >>  ............................................
    pt  É a minha parte.
    >>  ............................................
```


### Button `ask_emergency` — "What happens in an emergency?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.village` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.village.ask_emergency` — accepted phrasings: "what happens in an emergency"
  - the message must contain one of: `emergency`, `flood`, `crisis`
  - scored words: `emergency`(1.5), `flood`(1.2), `crisis`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.village.respond.ask_emergency
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.village.respond.ask_emergency   [29 chars]
    en  What happens in an emergency?
    >>  ............................................
    pt  O que acontece numa emergência?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.village.ask_emergency`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.village.ask_emergency
WHO    VILLAGER — what the player reads after pressing "What happens in an emergency?"
       spoken on: conversations.topic.work.none.village.respond, button `ask_emergency`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.village.ask_emergency`: the villager explains. Subject `work.none.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.village.ask_emergency/1   [76 chars]
    en  They come to me. Every time. And then afterwards it goes back to how it was.
    >>  ............................................
    pt  Vêm até mim. Toda vez. E depois volta a ser como era.
    >>  ............................................
  dialogue.conversations.work.prof.none.village.ask_emergency/2   [86 chars]
    en  The flood, two years back. I ran the whole line of it and nobody remembers that, %1$s.
    >>  ............................................
    pt  A cheia, dois anos atrás. Eu comandei a fila inteira e ninguém lembra disso, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Those weeks are in the walls. Somebody should say so."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.none.village` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.village.say_thanks` — accepted phrasings: "those weeks are in the walls. somebody should say so"
  - the message must contain one of: `walls`, `weeks`, `counted`
  - scored words: `walls`(1.5), `weeks`(1.2), `counted`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.village.respond.say_thanks   [53 chars]
    en  Those weeks are in the walls. Somebody should say so.
    >>  ............................................
    pt  Essas semanas estão nas paredes. Alguém devia dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.none.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.none.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Those weeks are in the walls. Somebody should say so."
       spoken on: conversations.topic.work.none.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.village.say_thanks`: the villager accepts. Subject `work.none.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.village.say_thanks/1   [91 chars]
    en  ...In the walls. That's mine now, I'm keeping that. I've not been handed a sentence before.
    >>  ............................................
    pt  ...Nas paredes. É minha agora, vou guardar. Não tinham me dado uma frase antes.
    >>  ............................................
  dialogue.conversations.work.prof.none.village.say_thanks/2   [75 chars]
    en  Say it to the mason. He built half of them and he'd know it was true, %1$s.
    >>  ............................................
    pt  Diga ao pedreiro. Ele construiu metade e saberia que é verdade, %1$s.
    >>  ............................................
```


### Button `ask_after` — "Does it go back to how it was every time?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.none.village` · offered only once the villager has actually said `work:none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.none.village.ask_after` — accepted phrasings: "does it go back to how it was every time"
  - the message must contain one of: `afterwards`, `forgotten`, `back`
  - scored words: `afterwards`(1.5), `forgotten`(1.2), `back`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.village.respond.ask_after
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.village.respond.ask_after   [41 chars]
    en  Does it go back to how it was every time?
    >>  ............................................
    pt  Volta a ser como era toda vez?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.none.village.ask_after`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.none.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which trade tempts you most?" | "Good luck deciding."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.village.ask_after
WHO    VILLAGER — what the player reads after pressing "Does it go back to how it was every time?"
       spoken on: conversations.topic.work.none.village.respond, button `ask_after`
       leaves the player on: conversations.topic.work.none.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.village.ask_after`: the villager explains. Subject `work.none.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.none.village.ask_after/1   [81 chars]
    en  Every time. I gave up being surprised and I have not given up being disappointed.
    >>  ............................................
    pt  Toda vez. Desisti de me surpreender e não desisti de me decepcionar.
    >>  ............................................
  dialogue.conversations.work.prof.none.village.ask_after/2   [89 chars]
    en  Within a fortnight. The flood took three weeks to be forgotten, which was a record, %1$s.
    >>  ............................................
    pt  Em quinze dias. A cheia levou três semanas pra ser esquecida, o que foi recorde, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.none.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.none.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.none.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.none.village.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.work.none.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.left`: the villager accepts. Subject `work.none.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.none.followup / leave; conversations.scene.work.none.old_admiration.blocked.respond / leave; conversations.scene.work.none.old_admiration.succeeded.respond / leave; conversations.scene.work.none.quiet_usefulness.active.respond / leave; conversations.scene.work.none.quiet_usefulness.succeeded.respond / leave; conversations.scene.work.none.trade_decision.blocked.respond / leave; conversations.scene.work.none.trade_decision.failed.respond / leave; conversations.scene.work.none.trade_decision.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.none.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

