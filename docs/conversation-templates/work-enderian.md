# Work talk with a enderian

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.enderian.being_looked_at.active.respond`](#conversations-scene-work-enderian-being-looked-at-active-respond)
- [`conversations.scene.work.enderian.being_looked_at.succeeded.respond`](#conversations-scene-work-enderian-being-looked-at-succeeded-respond)
- [`conversations.scene.work.enderian.followup`](#conversations-scene-work-enderian-followup)
- [`conversations.scene.work.enderian.lost_consignment.blocked.respond`](#conversations-scene-work-enderian-lost-consignment-blocked-respond)
- [`conversations.scene.work.enderian.lost_consignment.succeeded.respond`](#conversations-scene-work-enderian-lost-consignment-succeeded-respond)
- [`conversations.scene.work.enderian.the_quiet_place.succeeded.respond`](#conversations-scene-work-enderian-the-quiet-place-succeeded-respond)
- [`conversations.topic.work.enderian.craft.respond`](#conversations-topic-work-enderian-craft-respond)
- [`conversations.topic.work.enderian.followup`](#conversations-topic-work-enderian-followup)
- [`conversations.topic.work.enderian.future.respond`](#conversations-topic-work-enderian-future-respond)
- [`conversations.topic.work.enderian.respond`](#conversations-topic-work-enderian-respond)
- [`conversations.topic.work.enderian.risk.respond`](#conversations-topic-work-enderian-risk-respond)
- [`conversations.topic.work.enderian.task.respond`](#conversations-topic-work-enderian-task-respond)
- [`conversations.topic.work.enderian.village.respond`](#conversations-topic-work-enderian-village-respond)

---

## `conversations.scene.work.enderian.being_looked_at.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.enderian.being_looked_at.active` — e.g. "At %2$s I get looked at and then looked away from, in that order, about nine times an hour."


```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.enderian.being_looked_at.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.active.respond   [23 chars]
    en  How people look at you.
    >>  ............................................
    pt  Como olham para você.
    >>  ............................................
```


### Button `ask_what_helps` — "What would make it easier?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.enderian.being_looked_at.active` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.being_looked_at.active.ask_what_helps` — accepted phrasings: "what would make it easier"; "what would make it easier"; "what actually helps with that"
  - the message must contain one of: `easier`, `helps`
  - scored words: `easier`(1.8), `helps`(1.8), `make`(0.8), `actually`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.active.respond.ask_what_helps
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.being_looked_at.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.active.respond.ask_what_helps   [26 chars]
    en  What would make it easier?
    >>  ............................................
    pt  O que tornaria isso mais fácil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `work.enderian.being_looked_at`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.enderian.being_looked_at"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.active.explained
WHO    VILLAGER — what the player reads after pressing "What would make it easier?"
       spoken on: conversations.scene.work.enderian.being_looked_at.active.respond, button `ask_what_helps`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.being_looked_at.active.explained`: the villager explains. Subject `work.enderian.being_looked_at`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.active.explained/1   [152 chars]
    en  Being spoken to about something else. Weather, prices, anything. A person who asks me about turnips has stopped looking at me and started talking to me.
    >>  ............................................
    pt  Ser abordada sobre outra coisa. Tempo, preços, qualquer coisa. Quem me pergunta sobre nabos parou de olhar e começou a falar comigo.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.active.explained/2   [123 chars]
    en  Nothing grand. If somebody stares and then says good morning instead of hurrying off, the whole thing resolves in a second.
    >>  ............................................
    pt  Nada grandioso. Se alguém encara e depois diz bom dia em vez de sair apressado, a coisa toda se resolve em um segundo.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.active.explained/3   [141 chars]
    en  Regularity. The people who see me every day stopped years ago. It is the market crowd, who see me four times a season, who never get past it.
    >>  ............................................
    pt  Regularidade. Quem me vê todo dia parou anos atrás. É o pessoal da feira, que me vê quatro vezes por estação, que nunca passa disso.
    >>  ............................................
```


### Button `say_it_should_not_be_hers` — "Their discomfort shouldn't be your job."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.enderian.being_looked_at.active` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.being_looked_at.active.say_it_should_not_be_hers` — accepted phrasings: "their discomfort shouldnt be your job"; "their discomfort should not be your job"; "managing their awkwardness falls on you"
  - the message must contain one of: `discomfort`, `awkwardness`, `job`
  - scored words: `discomfort`(1.8), `awkwardness`(1.8), `job`(1.8), `their`(0.8), `shouldnt`(0.8), `should`(0.8), `managing`(0.8), `falls`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.active.respond.say_it_should_not_be_hers
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.being_looked_at.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.active.respond.say_it_should_not_be_hers   [39 chars]
    en  Their discomfort shouldn't be your job.
    >>  ............................................
    pt  O desconforto deles não é serviço seu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.enderian.looked_at.seen`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `work.enderian.being_looked_at`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.enderian.being_looked_at"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.active.steadied
WHO    VILLAGER — what the player reads after pressing "Their discomfort shouldn't be your job."
       spoken on: conversations.scene.work.enderian.being_looked_at.active.respond, button `say_it_should_not_be_hers`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.being_looked_at.active.steadied`: the villager accepts. Subject `work.enderian.being_looked_at`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.active.steadied/1   [104 chars]
    en  No, and I do it anyway, because the alternative is a market where I am also the person who made a scene.
    >>  ............................................
    pt  Não é, e eu faço mesmo assim, porque a alternativa é uma feira onde eu também sou a pessoa que fez cena.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.active.steadied/2   [117 chars]
    en  Thank you. I have had that thought for eleven years and never heard it said back to me, and it is different out loud.
    >>  ............................................
    pt  Obrigada. Tenho esse pensamento há onze anos e nunca ouvi de volta, e em voz alta é diferente.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.active.steadied/3   [136 chars]
    en  It is my job in the sense that I am the one who pays if it goes badly. That is not the same as it being fair, and I know the difference.
    >>  ............................................
    pt  É meu serviço no sentido de que sou eu quem paga se der errado. Isso não é o mesmo que ser justo, e eu sei a diferença.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your stock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.enderian.being_looked_at.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.being_looked_at.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.active.respond.leave   [36 chars]
    en  I'll let you get back to your stock.
    >>  ............................................
    pt  Vou deixar você voltar ao seu estoque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your stock."
       spoken on: conversations.scene.work.enderian.being_looked_at.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave; conversations.topic.work.enderian.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.enderian.leave/1   [27 chars]
    en  Yes. Quietly, if you would.
    >>  ............................................
    pt  Sim. Em silêncio, se puder.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.leave/2   [53 chars]
    en  Don't look directly at anything on the way out, %1$s.
    >>  ............................................
    pt  Não olhe diretamente pra nada na saída, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.enderian.being_looked_at.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.enderian.being_looked_at.succeeded` — e.g. "Two of the regular stallholders at %2$s have started saving me a place, and one of them argues with me about prices, which is the real sign."


```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.enderian.being_looked_at.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.respond   [19 chars]
    en  The market, lately.
    >>  ............................................
    pt  A feira, ultimamente.
    >>  ............................................
```


### Button `note_the_arguing` — "Arguing about prices is a good sign."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.enderian.being_looked_at.succeeded` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.being_looked_at.succeeded.note_the_arguing` — accepted phrasings: "arguing about prices is a good sign"; "arguing about prices is a good sign"; "haggling with you means you belong"
  - the message must contain one of: `arguing`, `prices`, `haggling`
  - scored words: `arguing`(1.8), `prices`(1.8), `haggling`(1.8), `good`(0.8), `sign`(0.8), `means`(0.8), `belong`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.respond.note_the_arguing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.being_looked_at.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.respond.note_the_arguing   [36 chars]
    en  Arguing about prices is a good sign.
    >>  ............................................
    pt  Discutir preço é bom sinal.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.enderian.being_looked_at`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.enderian.being_looked_at"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Arguing about prices is a good sign."
       spoken on: conversations.scene.work.enderian.being_looked_at.succeeded.respond, button `note_the_arguing`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.being_looked_at.succeeded.acknowledged`: the villager accepts. Subject `work.enderian.being_looked_at`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.acknowledged/1   [104 chars]
    en  It is the best sign there is. Nobody haggles with a person they are frightened of; they pay and they go.
    >>  ............................................
    pt  É o melhor sinal que existe. Ninguém pechincha com quem tem medo; paga e vai embora.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.acknowledged/2   [95 chars]
    en  Thank you. I noticed it the first time he shouted at me and I walked home unreasonably pleased.
    >>  ............................................
    pt  Obrigada. Reparei na primeira vez que ele gritou comigo e voltei para casa absurdamente contente.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.acknowledged/3   [121 chars]
    en  Eleven years for a man to be rude to me about the price of glass. I would not have believed how much that would be worth.
    >>  ............................................
    pt  Onze anos para um homem ser grosso comigo sobre o preço do vidro. Eu não teria acreditado o quanto isso valeria.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your stock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.enderian.being_looked_at.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.being_looked_at.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to your stock.
    >>  ............................................
    pt  Vou deixar você voltar ao seu estoque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your stock."
       spoken on: conversations.scene.work.enderian.being_looked_at.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave; conversations.topic.work.enderian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.enderian.followup`

**Reached from 9 route(s):** `conversations.scene.work.enderian.being_looked_at.active.respond` / `ask_what_helps`; `conversations.scene.work.enderian.being_looked_at.active.respond` / `say_it_should_not_be_hers`; `conversations.scene.work.enderian.being_looked_at.succeeded.respond` / `note_the_arguing`; `conversations.scene.work.enderian.lost_consignment.blocked.respond` / `ask_about_the_trade`; `conversations.scene.work.enderian.lost_consignment.blocked.respond` / `offer_pearls`; `conversations.scene.work.enderian.lost_consignment.blocked.respond` / `advise_going_for_it`; `conversations.scene.work.enderian.lost_consignment.succeeded.respond` / `ask_about_the_walk`; `conversations.scene.work.enderian.the_quiet_place.succeeded.respond` / `ask_what_it_gives_her`; `conversations.scene.work.enderian.the_quiet_place.succeeded.respond` / `let_it_be`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.enderian.being_looked_at.active.explained` — e.g. "Being spoken to about something else. Weather, prices, anything. A person who asks me about turnips has stopped looking at me and started talking to me."
- `conversations.scene.work.enderian.being_looked_at.active.steadied` — e.g. "No, and I do it anyway, because the alternative is a market where I am also the person who made a scene."
- `conversations.scene.work.enderian.being_looked_at.succeeded.acknowledged` — e.g. "It is the best sign there is. Nobody haggles with a person they are frightened of; they pay and they go."
- `conversations.scene.work.enderian.lost_consignment.blocked.accepted` — e.g. "Then I can hold my buyers and the season is saved, and I will pay you the going rate without being asked twice."
- `conversations.scene.work.enderian.lost_consignment.blocked.considered` — e.g. "Eight days and the shop shut. It is the correct answer and the arithmetic is close enough that I have been avoiding doing it."
- `conversations.scene.work.enderian.lost_consignment.blocked.explained` — e.g. "Three people in a day's ride, and they all want %2$s for reasons they would rather not put in a letter."
- `conversations.scene.work.enderian.lost_consignment.succeeded.answered` — e.g. "Empty and long and completely silent, and I slept better on the road than I have in this village in two years."
- `conversations.scene.work.enderian.the_quiet_place.succeeded.answered` — e.g. "An hour of nobody deciding anything about me. That is the whole of it and it is enough to make the other six days work."
- `conversations.scene.work.enderian.the_quiet_place.succeeded.softened` — e.g. "No, and it has taken a long time to stop offering one anyway, out of habit, before anybody asks."


```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.enderian.followup   [28 chars]
    en  Anything further you wanted?
    >>  ............................................
    pt  Queria mais alguma outra coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of the far places?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.enderian.*` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.followup.ask_more` — accepted phrasings: "whats the hardest part of the far places"; "what is the hardest part of the far places"; "hardest thing about travelling that far"
  - the message must contain one of: `hardest`, `far`
  - scored words: `hardest`(1.8), `far`(1.8), `whats`(0.8), `part`(0.8), `places`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.followup.ask_more   [42 chars]
    en  What's the hardest part of the far places?
    >>  ............................................
    pt  Qual é a parte mais difícil dos lugares distantes?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of the far places?"
       spoken on: conversations.scene.work.enderian.followup, button `ask_more`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.hard`: the villager explains. Subject `work.enderian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.enderian.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.enderian.hard/1   [72 chars]
    en  You get one heartbeat to decide whether you meant it. I have not, since.
    >>  ............................................
    pt  Você tem uma batida de coração pra decidir se foi de propósito. Eu não quebro, desde então.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.hard/2   [74 chars]
    en  Ask the last apprentice. ...You can't, %1$s, which rather makes the point.
    >>  ............................................
    pt  Pergunte ao último aprendiz. ...Você não pode, %1$s, o que meio que prova o ponto.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your stock."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.enderian.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.followup.leave   [29 chars]
    en  I'll leave you to your stock.
    >>  ............................................
    pt  Vou deixar você com o estoque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your stock."
       spoken on: conversations.scene.work.enderian.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave; conversations.topic.work.enderian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.enderian.lost_consignment.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.enderian.lost_consignment.blocked` — e.g. "%2$s should have been here a fortnight ago and there was %3$s, and I have three buyers waiting and nothing to show them."


```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.enderian.lost_consignment.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond   [16 chars]
    en  The consignment.
    >>  ............................................
    pt  A remessa.
    >>  ............................................
```


### Button `ask_about_the_trade` — "Who buys that sort of thing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.lost_consignment.blocked` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.lost_consignment.blocked.ask_about_the_trade` — accepted phrasings: "who buys that sort of thing"; "who buys that sort of thing"; "what is the market for it"
  - the message must contain one of: `buys`, `market`
  - scored words: `buys`(1.8), `market`(1.8), `who`(0.8), `sort`(0.8), `thing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond.ask_about_the_trade
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.lost_consignment.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond.ask_about_the_trade   [28 chars]
    en  Who buys that sort of thing?
    >>  ............................................
    pt  Quem compra esse tipo de coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.pearls`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.enderian.lost_consignment"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Who buys that sort of thing?"
       spoken on: conversations.scene.work.enderian.lost_consignment.blocked.respond, button `ask_about_the_trade`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.lost_consignment.blocked.explained`: the villager explains. Subject `work.enderian.pearls`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.explained/1   [103 chars]
    en  Three people in a day's ride, and they all want %2$s for reasons they would rather not put in a letter.
    >>  ............................................
    pt  Três pessoas a um dia de viagem, e todas querem %2$s por motivos que preferem não pôr numa carta.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.explained/2   [108 chars]
    en  Collectors, mostly, and one man who I am fairly sure is buying it to bury. I take his money and ask nothing.
    >>  ............................................
    pt  Colecionadores, na maioria, e um homem que eu tenho quase certeza que compra para enterrar. Aceito o dinheiro e não pergunto nada.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.explained/3   [106 chars]
    en  Fewer people than you would think, which is why one delayed cart is a season rather than an inconvenience.
    >>  ............................................
    pt  Menos gente do que você imagina, e é por isso que uma carroça atrasada é uma estação inteira, não um contratempo.
    >>  ............................................
```


### Button `offer_pearls` — "I'll bring you pearls."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.enderian.lost_consignment.blocked` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.lost_consignment.blocked.offer_pearls` — accepted phrasings: "ill bring you pearls"; "i can bring you pearls"; "let me fetch pearls for that"
  - the message must contain one of: `pearls`, `pearl`
  - scored words: `pearls`(1.8), `pearl`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond.offer_pearls
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.lost_consignment.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond.offer_pearls   [22 chars]
    en  I'll bring you pearls.
    >>  ............................................
    pt  Vou te trazer pérolas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.enderian.consignment.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.enderian.pearls`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.lost_consignment", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.enderian.lost_consignment", "obligation": "commitment:work.enderian.bring_pearls"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.enderian.bring_pearls"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you pearls."
       spoken on: conversations.scene.work.enderian.lost_consignment.blocked.respond, button `offer_pearls`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.lost_consignment.blocked.accepted`: the villager accepts. Subject `work.enderian.pearls`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.accepted/1   [111 chars]
    en  Then I can hold my buyers and the season is saved, and I will pay you the going rate without being asked twice.
    >>  ............................................
    pt  Então eu seguro meus compradores e a estação está salva, e eu pago o preço de mercado sem você ter que pedir duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.accepted/2   [119 chars]
    en  You are the first person in this village to offer me anything without first asking me a question about where I am from.
    >>  ............................................
    pt  Você é a primeira pessoa nesta vila a me oferecer alguma coisa sem antes fazer uma pergunta sobre de onde eu venho.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.accepted/3   [119 chars]
    en  Yes. Bring them to the shop rather than the house. The shop is where I am a merchant and the house is where I am tired.
    >>  ............................................
    pt  Sim. Traga à loja, não à casa. A loja é onde eu sou comerciante e a casa é onde eu estou cansada.
    >>  ............................................
```


### Button `advise_going_for_it` — "Go and fetch it yourself."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.enderian.lost_consignment.blocked` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.lost_consignment.blocked.advise_going_for_it` — accepted phrasings: "go and fetch it yourself"; "go and fetch it yourself"; "make the journey and collect it"
  - the message must contain one of: `fetch`, `journey`, `collect`
  - scored words: `fetch`(1.8), `journey`(1.8), `collect`(1.8), `yourself`(0.8), `make`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond.advise_going_for_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.lost_consignment.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond.advise_going_for_it   [25 chars]
    en  Go and fetch it yourself.
    >>  ............................................
    pt  Vá buscar você mesma.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.enderian.pearls`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.enderian.lost_consignment"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked.considered
WHO    VILLAGER — what the player reads after pressing "Go and fetch it yourself."
       spoken on: conversations.scene.work.enderian.lost_consignment.blocked.respond, button `advise_going_for_it`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.lost_consignment.blocked.considered`: the villager accepts. Subject `work.enderian.pearls`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.considered/1   [125 chars]
    en  Eight days and the shop shut. It is the correct answer and the arithmetic is close enough that I have been avoiding doing it.
    >>  ............................................
    pt  Oito dias e a loja fechada. É a resposta certa e a conta é apertada o bastante para eu vir evitando fazê-la.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.considered/2   [121 chars]
    en  I would rather walk eight days than write another polite letter. You have given me permission to want the thing I wanted.
    >>  ............................................
    pt  Prefiro caminhar oito dias a escrever outra carta educada. Você me deu permissão para querer o que eu já queria.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.considered/3   [123 chars]
    en  Yes. And I will go on foot and by the low road, which takes longer and means fewer villages where I have to explain myself.
    >>  ............................................
    pt  Sim. E vou a pé pela estrada baixa, que demora mais e passa por menos vilas onde eu preciso me explicar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your stock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.enderian.lost_consignment.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.lost_consignment.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked.respond.leave   [36 chars]
    en  I'll let you get back to your stock.
    >>  ............................................
    pt  Vou deixar você voltar ao seu estoque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your stock."
       spoken on: conversations.scene.work.enderian.lost_consignment.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave; conversations.topic.work.enderian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.enderian.lost_consignment.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.enderian.lost_consignment.succeeded` — e.g. "%2$s is in the shop. Eight days out and eight back, and I have never enjoyed a walk more."


```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.enderian.lost_consignment.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.respond   [28 chars]
    en  The consignment, in the end.
    >>  ............................................
    pt  A remessa, no fim.
    >>  ............................................
```


### Button `ask_about_the_walk` — "What was the walk like?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.enderian.lost_consignment.succeeded` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.lost_consignment.succeeded.ask_about_the_walk` — accepted phrasings: "what was the walk like"; "what was the walk like"; "how was the journey out and back"
  - the message must contain one of: `walk`, `journey`
  - scored words: `walk`(1.8), `journey`(1.8), `like`(0.8), `out`(0.8), `back`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.respond.ask_about_the_walk
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.lost_consignment.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.respond.ask_about_the_walk   [23 chars]
    en  What was the walk like?
    >>  ............................................
    pt  Como foi a caminhada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `work.enderian.pearls`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.enderian.lost_consignment"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What was the walk like?"
       spoken on: conversations.scene.work.enderian.lost_consignment.succeeded.respond, button `ask_about_the_walk`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.lost_consignment.succeeded.answered`: the villager explains. Subject `work.enderian.pearls`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.answered/1   [110 chars]
    en  Empty and long and completely silent, and I slept better on the road than I have in this village in two years.
    >>  ............................................
    pt  Vazia, longa e completamente silenciosa, e eu dormi melhor na estrada do que nesta vila em dois anos.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.answered/2   [106 chars]
    en  Nobody looked at me for eight days. I did not realise that was a thing I was carrying until I put it down.
    >>  ............................................
    pt  Ninguém olhou para mim por oito dias. Eu não sabia que carregava isso até deixar no chão.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.answered/3   [128 chars]
    en  Ordinary. Rain on the third day, a good inn on the fifth, and a woman on the sixth who talked to me for an hour about her goats.
    >>  ............................................
    pt  Comum. Chuva no terceiro dia, uma boa estalagem no quinto, e uma mulher no sexto que falou comigo uma hora sobre as cabras dela.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your stock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.enderian.lost_consignment.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.lost_consignment.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to your stock.
    >>  ............................................
    pt  Vou deixar você voltar ao seu estoque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your stock."
       spoken on: conversations.scene.work.enderian.lost_consignment.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave; conversations.topic.work.enderian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.enderian.the_quiet_place.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.enderian.the_quiet_place.succeeded` — e.g. "I go to %2$s about once a week. People find that unnerving and I have stopped explaining it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.enderian.the_quiet_place.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.respond   [13 chars]
    en  Where you go.
    >>  ............................................
    pt  Aonde você vai.
    >>  ............................................
```


### Button `ask_what_it_gives_her` — "What do you get from it?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.enderian.the_quiet_place.succeeded` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.the_quiet_place.succeeded.ask_what_it_gives_her` — accepted phrasings: "what do you get from it"; "what do you get from it"; "what does that hour give you"
  - the message must contain one of: `get`, `give`, `hour`
  - scored words: `get`(1.8), `give`(1.8), `hour`(1.8), `from`(0.8), `does`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.respond.ask_what_it_gives_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.the_quiet_place.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.respond.ask_what_it_gives_her   [24 chars]
    en  What do you get from it?
    >>  ............................................
    pt  O que você tira disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.enderian.the_silence`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.enderian.the_quiet_place"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What do you get from it?"
       spoken on: conversations.scene.work.enderian.the_quiet_place.succeeded.respond, button `ask_what_it_gives_her`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.the_quiet_place.succeeded.answered`: the villager explains. Subject `work.enderian.the_silence`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.answered/1   [119 chars]
    en  An hour of nobody deciding anything about me. That is the whole of it and it is enough to make the other six days work.
    >>  ............................................
    pt  Uma hora em que ninguém decide nada a meu respeito. É tudo, e basta para os outros seis dias funcionarem.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.answered/2   [118 chars]
    en  Quiet of a specific kind. A house is quiet with people in it, which is a different thing and does not do the same job.
    >>  ............................................
    pt  Silêncio de um tipo específico. Uma casa é silenciosa com gente dentro, o que é outra coisa e não faz o mesmo serviço.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.answered/3   [94 chars]
    en  I could not tell you and I have tried. It is like being asked why you like a particular light.
    >>  ............................................
    pt  Eu não saberia dizer e já tentei. É como perguntarem por que você gosta de uma luz específica.
    >>  ............................................
```


### Button `let_it_be` — "That explanation is yours to keep."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.enderian.the_quiet_place.succeeded` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.enderian.the_quiet_place.succeeded.let_it_be` — accepted phrasings: "that explanation is yours to keep"; "an explanation is yours to withhold"; "keep that to yourself if you want"
  - the message must contain one of: `explanation`, `yourself`, `withhold`
  - scored words: `explanation`(1.8), `yourself`(1.8), `withhold`(1.8), `yours`(0.8), `keep`(0.8), `want`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.respond.let_it_be
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.the_quiet_place.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.respond.let_it_be   [34 chars]
    en  That explanation is yours to keep.
    >>  ............................................
    pt  Essa explicação é sua para guardar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +3  _(recorded under topic `work.enderian.the_silence`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.enderian.the_quiet_place"}
- Then opens: `conversations.scene.work.enderian.followup`
- …where the player's next choices will be: "What's the hardest part of the far places?" | "I'll leave you to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.softened
WHO    VILLAGER — what the player reads after pressing "That explanation is yours to keep."
       spoken on: conversations.scene.work.enderian.the_quiet_place.succeeded.respond, button `let_it_be`
       leaves the player on: conversations.scene.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.the_quiet_place.succeeded.softened`: the villager accepts. Subject `work.enderian.the_silence`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.softened/1   [96 chars]
    en  No, and it has taken a long time to stop offering one anyway, out of habit, before anybody asks.
    >>  ............................................
    pt  Não devo, e levou muito tempo para eu parar de oferecer uma mesmo assim, por hábito, antes de alguém perguntar.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.softened/2   [126 chars]
    en  Thank you. Nearly everybody arrives at that sentence eventually. Almost nobody arrives at it before asking the question first.
    >>  ............................................
    pt  Obrigada. Quase todo mundo chega a essa frase em algum momento. Quase ninguém chega antes de fazer a pergunta.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.softened/3   [115 chars]
    en  I will say one thing about it, since you did not ask: it is not a secret. It is just mine, and those are different.
    >>  ............................................
    pt  Vou dizer uma coisa sobre isso, já que você não perguntou: não é segredo. É só meu, e são coisas diferentes.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your stock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.enderian.the_quiet_place.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.enderian.the_quiet_place.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to your stock.
    >>  ............................................
    pt  Vou deixar você voltar ao seu estoque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your stock."
       spoken on: conversations.scene.work.enderian.the_quiet_place.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave; conversations.topic.work.enderian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.enderian.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.enderian.craft` — e.g. "Nobody teaches this. You find out you can do it and then you find out what it costs, in that order."


```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.enderian.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.enderian.craft.respond   [23 chars]
    en  That's the whole of it.
    >>  ............................................
    pt  É tudo.
    >>  ............................................
```


### Button `ask_meant` — "How often do you arrive where you meant to?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.craft` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.craft.ask_meant` — accepted phrasings: "how often do you arrive where you meant to"
  - the message must contain one of: `arrive`, `meant`
  - scored words: `arrive`(1.5), `meant`(1.2), `often`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.craft.respond.ask_meant
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.craft.respond.ask_meant   [43 chars]
    en  How often do you arrive where you meant to?
    >>  ............................................
    pt  Com que frequência você chega onde pretendia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.craft.ask_meant`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.craft.ask_meant
WHO    VILLAGER — what the player reads after pressing "How often do you arrive where you meant to?"
       spoken on: conversations.topic.work.enderian.craft.respond, button `ask_meant`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.craft.ask_meant`: the villager explains. Subject `work.enderian.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.craft.ask_meant/1   [79 chars]
    en  Nine times in ten. The tenth is why I only do it when the alternative is worse.
    >>  ............................................
    pt  Nove vezes em dez. A décima é por que eu só faço quando a alternativa é pior.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.craft.ask_meant/2   [88 chars]
    en  Better than when I started, %1$s, and I have a scar from each of the lessons in between.
    >>  ............................................
    pt  Melhor do que quando comecei, %1$s, e eu tenho uma cicatriz de cada lição no meio.
    >>  ............................................
```


### Button `admire` — "Finding out the cost afterwards is a hard order to learn in."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.enderian.craft` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.craft.admire` — accepted phrasings: "finding out the cost afterwards is a hard order to learn in"
  - the message must contain one of: `cost`, `afterwards`, `order`
  - scored words: `cost`(1.5), `afterwards`(1.2), `order`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.craft.respond.admire   [60 chars]
    en  Finding out the cost afterwards is a hard order to learn in.
    >>  ............................................
    pt  Descobrir o custo depois é uma ordem difícil de aprender.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.enderian.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.enderian.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.craft.admire
WHO    VILLAGER — what the player reads after pressing "Finding out the cost afterwards is a hard order to learn in."
       spoken on: conversations.topic.work.enderian.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.craft.admire`: the villager accepts. Subject `work.enderian.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.craft.admire/1   [90 chars]
    en  It's the only order available. Nobody could have told me, because nobody here had done it.
    >>  ............................................
    pt  É a única ordem disponível. Ninguém podia me contar, porque ninguém aqui tinha feito.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.craft.admire/2   [85 chars]
    en  It's why I tell anyone who asks, %1$s, in full, at length, until they're bored of me.
    >>  ............................................
    pt  É por isso que eu conto a quem pergunta, %1$s, inteiro, longamente, até enjoarem de mim.
    >>  ............................................
```


### Button `ask_tenth` — "What happens on the tenth time?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.craft` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.craft.ask_tenth` — accepted phrasings: "what happens on the tenth time"
  - the message must contain one of: `tenth`, `wrong`, `elsewhere`
  - scored words: `tenth`(1.5), `wrong`(1.0), `elsewhere`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.craft.respond.ask_tenth
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.craft.respond.ask_tenth   [31 chars]
    en  What happens on the tenth time?
    >>  ............................................
    pt  O que acontece na décima vez?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.craft.ask_tenth`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.craft.ask_tenth
WHO    VILLAGER — what the player reads after pressing "What happens on the tenth time?"
       spoken on: conversations.topic.work.enderian.craft.respond, button `ask_tenth`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.craft.ask_tenth`: the villager explains. Subject `work.enderian.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.craft.ask_tenth/1   [89 chars]
    en  You arrive somewhere adjacent. Usually cold, usually dark, always a long way from a road.
    >>  ............................................
    pt  Você chega num lugar vizinho. Normalmente frio, normalmente escuro, sempre longe de uma estrada.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.craft.ask_tenth/2   [76 chars]
    en  Once it was four days' walk and I told everyone I'd been on an errand, %1$s.
    >>  ............................................
    pt  Uma vez foram quatro dias de caminhada e eu disse a todos que estava numa incumbência, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the notes."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.enderian.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.craft.respond.leave   [35 chars]
    en  I'll let you get back to the notes.
    >>  ............................................
    pt  Vou deixar você voltar às anotações.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the notes."
       spoken on: conversations.topic.work.enderian.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.followup / leave; conversations.topic.work.enderian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.enderian.followup`

**Reached from 20 route(s):** `conversations.scene.work.enderian.followup` / `ask_more`; `conversations.topic.work.enderian.craft.respond` / `ask_meant`; `conversations.topic.work.enderian.craft.respond` / `admire`; `conversations.topic.work.enderian.craft.respond` / `ask_tenth`; `conversations.topic.work.enderian.future.respond` / `ask_warm`; `conversations.topic.work.enderian.future.respond` / `encourage`; `conversations.topic.work.enderian.future.respond` / `ask_keeper`; `conversations.topic.work.enderian.respond` / `ask_hard`; `conversations.topic.work.enderian.respond` / `value`; `conversations.topic.work.enderian.respond` / `challenge`; `conversations.topic.work.enderian.respond` / `challenge`; `conversations.topic.work.enderian.risk.respond` / `ask_hands` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.enderian.challenge.landed` — e.g. "Very possibly. I've written the notes in a hand somebody else can read, just in case."
- `conversations.work.prof.enderian.challenge.stung` — e.g. "...I have taken more precautions than anyone in this village has taken about anything."
- `conversations.work.prof.enderian.craft.admire` — e.g. "It's the only order available. Nobody could have told me, because nobody here had done it."
- `conversations.work.prof.enderian.craft.ask_meant` — e.g. "Nine times in ten. The tenth is why I only do it when the alternative is worse."
- `conversations.work.prof.enderian.craft.ask_tenth` — e.g. "You arrive somewhere adjacent. Usually cold, usually dark, always a long way from a road."
- `conversations.work.prof.enderian.future.ask_keeper` — e.g. "The librarian. He's the only person here who has ever asked me a question about it rather than about me."
- `conversations.work.prof.enderian.future.ask_warm` — e.g. "Once. To put it where it came from, if I can find where that is. That's the errand."
- `conversations.work.prof.enderian.future.encourage` — e.g. "...A last one. You've called it that and now I have to decide whether I meant it."
- `conversations.work.prof.enderian.hard` — e.g. "You get one heartbeat to decide whether you meant it. I have not, since."
- `conversations.work.prof.enderian.risk.ask_hands` — e.g. "Because I can be elsewhere before they've finished a sentence. That's a reasonable thing to mind."
- `conversations.work.prof.enderian.risk.ask_note` — e.g. "Whoever came in when the door stayed shut. Which is a sentence I've never finished before now."
- `conversations.work.prof.enderian.risk.sympathise` — e.g. "...It is. I leave a note now. It says where I meant to go and it is the loneliest thing I own."
- `conversations.work.prof.enderian.task.ask_sickness` — e.g. "The cost of arriving somewhere you didn't walk to. It passes. It always passes. I still count the hours."
- `conversations.work.prof.enderian.task.ask_unnamed` — e.g. "Four things. I've drawn all four and shown them to nobody, because of the questions that follow."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.enderian.followup   [32 chars]
    en  That's the study, such as it is.
    >>  ............................................
    pt  É o estudo, tal como é.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.enderian.challenge.landed`, `work.enderian.challenge.stung`, `work.enderian.craft.admire`, `work.enderian.craft.ask_meant`, `work.enderian.craft.ask_tenth`, `work.enderian.future.ask_keeper`, `work.enderian.future.ask_warm`, `work.enderian.future.encourage`, `work.enderian.hard`, `work.enderian.risk.ask_hands`, `work.enderian.risk.ask_note`, `work.enderian.risk.sympathise`, `work.enderian.task.ask_sickness`, `work.enderian.task.ask_unnamed`, `work.enderian.task.offer_hands`, `work.enderian.value`, `work.enderian.village.ask_invite`, `work.enderian.village.ask_twice`, `work.enderian.village.say_thanks` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.enderian.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `method`, `monsters`
  - scored words: `thought`(1.2), `method`(1.5), `monsters`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.enderian.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.enderian.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.enderian.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.enderian.thanks`: the villager accepts. Subject `work.enderian.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.thanks/1   [48 chars]
    en  Few have. That is precisely why I write it down.
    >>  ............................................
    pt  Poucos pensaram. É exatamente por isso que eu anoto.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.thanks/2   [72 chars]
    en  You're the first to ask about the method rather than the monsters, %1$s.
    >>  ............................................
    pt  Você é o primeiro a perguntar do método em vez dos monstros, %1$s.
    >>  ............................................
```


### Button `ask_more` — "What's the strangest thing you've recorded?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.challenge.landed`, `work.enderian.challenge.stung`, `work.enderian.craft.admire`, `work.enderian.craft.ask_meant`, `work.enderian.craft.ask_tenth`, `work.enderian.future.ask_keeper`, `work.enderian.future.ask_warm`, `work.enderian.future.encourage`, `work.enderian.hard`, `work.enderian.risk.ask_hands`, `work.enderian.risk.ask_note`, `work.enderian.risk.sympathise`, `work.enderian.task.ask_sickness`, `work.enderian.task.ask_unnamed`, `work.enderian.task.offer_hands`, `work.enderian.value`, `work.enderian.village.ask_invite`, `work.enderian.village.ask_twice`, `work.enderian.village.say_thanks` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.enderian.more` — accepted phrasings: "what's the strangest thing you've recorded"
  - the message must contain one of: `strangest`, `recorded`
  - scored words: `strangest`(1.5), `recorded`(1.5), `odd`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.followup.ask_more   [43 chars]
    en  What's the strangest thing you've recorded?
    >>  ............................................
    pt  Qual a coisa mais estranha que você registrou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.enderian.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.more
WHO    VILLAGER — what the player reads after pressing "What's the strangest thing you've recorded?"
       spoken on: conversations.topic.work.enderian.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.enderian.more`: the villager discloses. Subject `work.enderian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.more/1   [83 chars]
    en  One of them moved a block. Placed it, rather. That is not in any account I've read.
    >>  ............................................
    pt  Um deles moveu um bloco. Colocou, na verdade. Isso não está em nenhum relato que eu li.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.more/2   [98 chars]
    en  A silence that came from the wrong direction. I've written it four times and it still reads wrong.
    >>  ............................................
    pt  Um silêncio que veio da direção errada. Já escrevi quatro vezes e ainda soa errado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block, and I have not told anybody, because of the questions that follow.
    >>  ............................................
    pt  Um deles colocou um bloco, e eu não contei a ninguém, por causa das perguntas que vêm.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.enderian.more/2
    en  One of the four has been warm for two years. I'd rather not discuss it further, %1$s.
    >>  ............................................
    pt  Uma das quatro está morna há dois anos. Prefiro não falar mais disso, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. I've watched for two years to be sure of the word before I used it.
    >>  ............................................
    pt  Um deles colocou um bloco. Observei dois anos pra ter certeza da palavra antes de usar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I can't name yet. Names arrive eventually, in my experience. Most of them do.
    >>  ............................................
    pt  Quatro coisas que eu ainda não sei nomear. Nomes chegam uma hora, na minha experiência. Quase todos.
    >>  ............................................
  confident.dialogue.conversations.work.prof.enderian.more/1
    en  One of them moved a block. Placed it, rather. That is not in any account I have read.
    >>  ............................................
    pt  Um deles moveu um bloco. Colocou, melhor dizendo. Isso não está em nenhum relato que eu li.
    >>  ............................................
  confident.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I cannot name. I've drawn all four and shown them to nobody.
    >>  ............................................
    pt  Quatro coisas que eu não sei nomear. Desenhei as quatro e não mostrei a ninguém.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.enderian.more/1
    en  One of them moved a block. Placed it, rather. That is not in any account I have read.
    >>  ............................................
    pt  Um deles moveu um bloco. Colocou, melhor dizendo. Isso não está em nenhum relato que eu li.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I cannot name. I've drawn all four and shown them to nobody.
    >>  ............................................
    pt  Quatro coisas que eu não sei nomear. Desenhei as quatro e não mostrei a ninguém.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. Deliberately. Come and see the drawing — you'd be the first.
    >>  ............................................
    pt  Um deles colocou um bloco. Deliberadamente. Venha ver o desenho — você seria o primeiro.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I can't name. I'd show you all four, and I've shown them to nobody in nineteen years.
    >>  ............................................
    pt  Quatro coisas que eu não sei nomear. Eu mostraria as quatro, e não mostrei a ninguém em dezenove anos.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. Deliberately. Come and see the drawing — you'd be the first.
    >>  ............................................
    pt  Um deles colocou um bloco. Deliberadamente. Venha ver o desenho — você seria o primeiro.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I can't name. I'd show you all four, and I've shown them to nobody in nineteen years.
    >>  ............................................
    pt  Quatro coisas que eu não sei nomear. Eu mostraria as quatro, e não mostrei a ninguém em dezenove anos.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. Deliberately. Come and see the drawing — you'd be the first.
    >>  ............................................
    pt  Um deles colocou um bloco. Deliberadamente. Venha ver o desenho — você seria o primeiro.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I can't name. I'd show you all four, and I've shown them to nobody in nineteen years.
    >>  ............................................
    pt  Quatro coisas que eu não sei nomear. Eu mostraria as quatro, e não mostrei a ninguém em dezenove anos.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block, and I have not told anybody, because of the questions that follow.
    >>  ............................................
    pt  Um deles colocou um bloco, e eu não contei a ninguém, por causa das perguntas que vêm.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.enderian.more/2
    en  One of the four has been warm for two years. I'd rather not discuss it further, %1$s.
    >>  ............................................
    pt  Uma das quatro está morna há dois anos. Prefiro não falar mais disso, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.enderian.more/1
    en  One of them moved a block. Placed it, rather. That is not in any account I have read.
    >>  ............................................
    pt  Um deles moveu um bloco. Colocou, melhor dizendo. Isso não está em nenhum relato que eu li.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I cannot name. I've drawn all four and shown them to nobody.
    >>  ............................................
    pt  Quatro coisas que eu não sei nomear. Desenhei as quatro e não mostrei a ninguém.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.enderian.more/1
    en  One of them moved a block. Placed it, rather. That is not in any account I have read.
    >>  ............................................
    pt  Um deles moveu um bloco. Colocou, melhor dizendo. Isso não está em nenhum relato que eu li.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I cannot name. I've drawn all four and shown them to nobody.
    >>  ............................................
    pt  Quatro coisas que eu não sei nomear. Desenhei as quatro e não mostrei a ninguém.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. Not moved. Placed. That is a different word entirely.
    >>  ............................................
    pt  Um deles colocou um bloco. Não moveu. Colocou. É uma palavra completamente diferente.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.enderian.more/2
    en  Four unnamed things. I've drawn each of them and written the date I found it.
    >>  ............................................
    pt  Quatro coisas sem nome. Desenhei cada uma e anotei a data em que achei.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. I've watched for two years to be sure of the word before I used it.
    >>  ............................................
    pt  Um deles colocou um bloco. Observei dois anos pra ter certeza da palavra antes de usar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I can't name yet. Names arrive eventually, in my experience. Most of them do.
    >>  ............................................
    pt  Quatro coisas que eu ainda não sei nomear. Nomes chegam uma hora, na minha experiência. Quase todos.
    >>  ............................................
  odd.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. Not moved. Placed. That is a different word entirely.
    >>  ............................................
    pt  Um deles colocou um bloco. Não moveu. Colocou. É uma palavra completamente diferente.
    >>  ............................................
  odd.dialogue.conversations.work.prof.enderian.more/2
    en  Four unnamed things. I've drawn each of them and written the date I found it.
    >>  ............................................
    pt  Quatro coisas sem nome. Desenhei cada uma e anotei a data em que achei.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. I've watched for two years to be sure of the word before I used it.
    >>  ............................................
    pt  Um deles colocou um bloco. Observei dois anos pra ter certeza da palavra antes de usar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I can't name yet. Names arrive eventually, in my experience. Most of them do.
    >>  ............................................
    pt  Quatro coisas que eu ainda não sei nomear. Nomes chegam uma hora, na minha experiência. Quase todos.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.enderian.more/1
    en  One of them PLACED a block. Placed it! That is in no account anywhere and I'm quite beside myself.
    >>  ............................................
    pt  Um deles COLOCOU um bloco. Colocou! Isso não está em relato nenhum e eu estou fora de mim.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.enderian.more/2
    en  Four unnamed things on my table. One of them is warm. I try not to think about that one.
    >>  ............................................
    pt  Quatro coisas sem nome na minha mesa. Uma delas é morna. Eu tento não pensar nessa.
    >>  ............................................
  playful.dialogue.conversations.work.prof.enderian.more/1
    en  One of them PLACED a block. Placed it! That is in no account anywhere and I'm quite beside myself.
    >>  ............................................
    pt  Um deles COLOCOU um bloco. Colocou! Isso não está em relato nenhum e eu estou fora de mim.
    >>  ............................................
  playful.dialogue.conversations.work.prof.enderian.more/2
    en  Four unnamed things on my table. One of them is warm. I try not to think about that one.
    >>  ............................................
    pt  Quatro coisas sem nome na minha mesa. Uma delas é morna. Eu tento não pensar nessa.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. I've watched for two years to be sure of the word before I used it.
    >>  ............................................
    pt  Um deles colocou um bloco. Observei dois anos pra ter certeza da palavra antes de usar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.enderian.more/2
    en  Four things I can't name yet. Names arrive eventually, in my experience. Most of them do.
    >>  ............................................
    pt  Quatro coisas que eu ainda não sei nomear. Nomes chegam uma hora, na minha experiência. Quase todos.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block, and I have not told anybody, because of the questions that follow.
    >>  ............................................
    pt  Um deles colocou um bloco, e eu não contei a ninguém, por causa das perguntas que vêm.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.enderian.more/2
    en  One of the four has been warm for two years. I'd rather not discuss it further, %1$s.
    >>  ............................................
    pt  Uma das quatro está morna há dois anos. Prefiro não falar mais disso, %1$s.
    >>  ............................................
  shy.dialogue.conversations.work.prof.enderian.more/1
    en  One of them placed a block. Not moved. Placed. That is a different word entirely.
    >>  ............................................
    pt  Um deles colocou um bloco. Não moveu. Colocou. É uma palavra completamente diferente.
    >>  ............................................
  shy.dialogue.conversations.work.prof.enderian.more/2
    en  Four unnamed things. I've drawn each of them and written the date I found it.
    >>  ............................................
    pt  Quatro coisas sem nome. Desenhei cada uma e anotei a data em que achei.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.enderian.more/1
    en  One of them PLACED a block. Placed it! That is in no account anywhere and I'm quite beside myself.
    >>  ............................................
    pt  Um deles COLOCOU um bloco. Colocou! Isso não está em relato nenhum e eu estou fora de mim.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.enderian.more/2
    en  Four unnamed things on my table. One of them is warm. I try not to think about that one.
    >>  ............................................
    pt  Quatro coisas sem nome na minha mesa. Uma delas é morna. Eu tento não pensar nessa.
    >>  ............................................
  witty.dialogue.conversations.work.prof.enderian.more/1
    en  One of them PLACED a block. Placed it! That is in no account anywhere and I'm quite beside myself.
    >>  ............................................
    pt  Um deles COLOCOU um bloco. Colocou! Isso não está em relato nenhum e eu estou fora de mim.
    >>  ............................................
  witty.dialogue.conversations.work.prof.enderian.more/2
    en  Four unnamed things on my table. One of them is warm. I try not to think about that one.
    >>  ............................................
    pt  Quatro coisas sem nome na minha mesa. Uma delas é morna. Eu tento não pensar nessa.
    >>  ............................................
```

</details>


### Button `leave` — "Careful eyes."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.enderian.challenge.landed`, `work.enderian.challenge.stung`, `work.enderian.craft.admire`, `work.enderian.craft.ask_meant`, `work.enderian.craft.ask_tenth`, `work.enderian.future.ask_keeper`, `work.enderian.future.ask_warm`, `work.enderian.future.encourage`, `work.enderian.hard`, `work.enderian.risk.ask_hands`, `work.enderian.risk.ask_note`, `work.enderian.risk.sympathise`, `work.enderian.task.ask_sickness`, `work.enderian.task.ask_unnamed`, `work.enderian.task.offer_hands`, `work.enderian.value`, `work.enderian.village.ask_invite`, `work.enderian.village.ask_twice`, `work.enderian.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.followup.leave   [13 chars]
    en  Careful eyes.
    >>  ............................................
    pt  Olhos atentos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "Careful eyes."
       spoken on: conversations.topic.work.enderian.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.enderian.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.enderian.future` — e.g. "I'd like to give the catalogue to somebody who'd keep it without being frightened of it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.enderian.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.enderian.future.respond   [25 chars]
    en  That's what's left in it.
    >>  ............................................
    pt  É o que resta nisso.
    >>  ............................................
```


### Button `ask_warm` — "You'd go back for the warm thing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.future` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.future.ask_warm` — accepted phrasings: "you'd go back for the warm thing"
  - the message must contain one of: `warm`, `return`
  - scored words: `warm`(1.5), `back`(0.8), `return`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.future.respond.ask_warm
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.future.respond.ask_warm   [33 chars]
    en  You'd go back for the warm thing?
    >>  ............................................
    pt  Você voltaria pela coisa morna?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.future.ask_warm`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.future.ask_warm
WHO    VILLAGER — what the player reads after pressing "You'd go back for the warm thing?"
       spoken on: conversations.topic.work.enderian.future.respond, button `ask_warm`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.future.ask_warm`: the villager explains. Subject `work.enderian.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.future.ask_warm/1   [83 chars]
    en  Once. To put it where it came from, if I can find where that is. That's the errand.
    >>  ............................................
    pt  Uma vez. Pra pôr de volta de onde veio, se eu achar onde é. É essa a incumbência.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.future.ask_warm/2   [87 chars]
    en  I'd go to give it back, %1$s, not to learn about it. Those are very different journeys.
    >>  ............................................
    pt  Eu iria pra devolver, %1$s, não pra aprender. São viagens muito diferentes.
    >>  ............................................
```


### Button `encourage` — "Then that's a last trip worth taking."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.enderian.future` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.future.encourage` — accepted phrasings: "then that's a last trip worth taking"
  - the message must contain one of: `last`, `trip`, `worth`
  - scored words: `last`(1.5), `trip`(1.2), `worth`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.future.respond.encourage   [37 chars]
    en  Then that's a last trip worth taking.
    >>  ............................................
    pt  Então é uma última viagem que vale.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.enderian.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.enderian.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then that's a last trip worth taking."
       spoken on: conversations.topic.work.enderian.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.future.encourage`: the villager accepts. Subject `work.enderian.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.future.encourage/1   [81 chars]
    en  ...A last one. You've called it that and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Uma última. Você chamou assim e agora eu tenho que decidir se eu quis dizer isso.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.future.encourage/2   [58 chars]
    en  Worth taking. Nothing I do gets called worth taking, %1$s.
    >>  ............................................
    pt  Que vale. Nada que eu faço é chamado de algo que vale, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. Naming it makes it real and I'm not certain I wanted it real.
    >>  ............................................
    pt  ...Um último. Dar nome torna real e não sei se eu queria real.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. I've never had my work called that, and I don't know where to put it.
    >>  ............................................
    pt  Vale a pena levar. Nunca chamaram meu trabalho assim, e não sei onde guardar isso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. At my age you stop pretending there'll be several more.
    >>  ............................................
    pt  ...Um último. Na minha idade você para de fingir que haverá vários outros.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Forty years and that's the first time the word has been used.
    >>  ............................................
    pt  Vale a pena levar. Quarenta anos e é a primeira vez que usam essa palavra.
    >>  ............................................
  confident.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've called it that and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último. Você chamou assim e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nothing I do gets called worth taking.
    >>  ............................................
    pt  Vale a pena levar. Nada do que eu faço é chamado de valer a pena levar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've called it that and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último. Você chamou assim e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nothing I do gets called worth taking.
    >>  ............................................
    pt  Vale a pena levar. Nada do que eu faço é chamado de valer a pena levar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've named it, %1$s, and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último. Você deu nome, %1$s, e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nobody has said that about my work before, %1$s.
    >>  ............................................
    pt  Vale a pena levar. Ninguém disse isso do meu trabalho antes, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've named it, %1$s, and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último. Você deu nome, %1$s, e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nobody has said that about my work before, %1$s.
    >>  ............................................
    pt  Vale a pena levar. Ninguém disse isso do meu trabalho antes, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've named it, %1$s, and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último. Você deu nome, %1$s, e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nobody has said that about my work before, %1$s.
    >>  ............................................
    pt  Vale a pena levar. Ninguém disse isso do meu trabalho antes, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. Naming it makes it real and I'm not certain I wanted it real.
    >>  ............................................
    pt  ...Um último. Dar nome torna real e não sei se eu queria real.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. I've never had my work called that, and I don't know where to put it.
    >>  ............................................
    pt  Vale a pena levar. Nunca chamaram meu trabalho assim, e não sei onde guardar isso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've called it that and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último. Você chamou assim e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nothing I do gets called worth taking.
    >>  ............................................
    pt  Vale a pena levar. Nada do que eu faço é chamado de valer a pena levar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've called it that and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último. Você chamou assim e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nothing I do gets called worth taking.
    >>  ............................................
    pt  Vale a pena levar. Nada do que eu faço é chamado de valer a pena levar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've named it.
    >>  ............................................
    pt  ...Um último. Você deu nome.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. That's a new word for it.
    >>  ............................................
    pt  Vale a pena levar. É uma palavra nova pra isso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. At my age you stop pretending there'll be several more.
    >>  ............................................
    pt  ...Um último. Na minha idade você para de fingir que haverá vários outros.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Forty years and that's the first time the word has been used.
    >>  ............................................
    pt  Vale a pena levar. Quarenta anos e é a primeira vez que usam essa palavra.
    >>  ............................................
  odd.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've named it.
    >>  ............................................
    pt  ...Um último. Você deu nome.
    >>  ............................................
  odd.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. That's a new word for it.
    >>  ............................................
    pt  Vale a pena levar. É uma palavra nova pra isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. At my age you stop pretending there'll be several more.
    >>  ............................................
    pt  ...Um último. Na minha idade você para de fingir que haverá vários outros.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Forty years and that's the first time the word has been used.
    >>  ............................................
    pt  Vale a pena levar. Quarenta anos e é a primeira vez que usam essa palavra.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one! You've called it that, and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último! Você chamou assim, e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nothing I do gets called worth taking, so you'll forgive the face.
    >>  ............................................
    pt  Vale a pena levar. Nada meu é chamado assim, então perdoe a cara que fiz.
    >>  ............................................
  playful.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one! You've called it that, and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último! Você chamou assim, e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nothing I do gets called worth taking, so you'll forgive the face.
    >>  ............................................
    pt  Vale a pena levar. Nada meu é chamado assim, então perdoe a cara que fiz.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. At my age you stop pretending there'll be several more.
    >>  ............................................
    pt  ...Um último. Na minha idade você para de fingir que haverá vários outros.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Forty years and that's the first time the word has been used.
    >>  ............................................
    pt  Vale a pena levar. Quarenta anos e é a primeira vez que usam essa palavra.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. Naming it makes it real and I'm not certain I wanted it real.
    >>  ............................................
    pt  ...Um último. Dar nome torna real e não sei se eu queria real.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. I've never had my work called that, and I don't know where to put it.
    >>  ............................................
    pt  Vale a pena levar. Nunca chamaram meu trabalho assim, e não sei onde guardar isso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one. You've named it.
    >>  ............................................
    pt  ...Um último. Você deu nome.
    >>  ............................................
  shy.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. That's a new word for it.
    >>  ............................................
    pt  Vale a pena levar. É uma palavra nova pra isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one! You've called it that, and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último! Você chamou assim, e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nothing I do gets called worth taking, so you'll forgive the face.
    >>  ............................................
    pt  Vale a pena levar. Nada meu é chamado assim, então perdoe a cara que fiz.
    >>  ............................................
  witty.dialogue.conversations.work.prof.enderian.future.encourage/1
    en  ...A last one! You've called it that, and now I have to decide whether I meant it.
    >>  ............................................
    pt  ...Um último! Você chamou assim, e agora tenho que decidir se eu quis dizer isso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.enderian.future.encourage/2
    en  Worth taking. Nothing I do gets called worth taking, so you'll forgive the face.
    >>  ............................................
    pt  Vale a pena levar. Nada meu é chamado assim, então perdoe a cara que fiz.
    >>  ............................................
```

</details>


### Button `ask_keeper` — "Who wouldn't be frightened of the catalogue?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.future` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.future.ask_keeper` — accepted phrasings: "who wouldn't be frightened of the catalogue"
  - the message must contain one of: `frightened`, `catalogue`, `keeper`
  - scored words: `frightened`(1.5), `catalogue`(1.2), `keeper`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.future.respond.ask_keeper
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.future.respond.ask_keeper   [44 chars]
    en  Who wouldn't be frightened of the catalogue?
    >>  ............................................
    pt  Quem não teria medo do catálogo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.future.ask_keeper`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.future.ask_keeper
WHO    VILLAGER — what the player reads after pressing "Who wouldn't be frightened of the catalogue?"
       spoken on: conversations.topic.work.enderian.future.respond, button `ask_keeper`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.future.ask_keeper`: the villager explains. Subject `work.enderian.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.future.ask_keeper/1   [104 chars]
    en  The librarian. He's the only person here who has ever asked me a question about it rather than about me.
    >>  ............................................
    pt  O bibliotecário. É a única pessoa daqui que já me fez uma pergunta sobre ele em vez de sobre mim.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.future.ask_keeper/2   [100 chars]
    en  Nobody yet. That's why it's still in my house, %1$s, and why the house is not somewhere I can leave.
    >>  ............................................
    pt  Ninguém ainda. Por isso continua na minha casa, %1$s, e por isso a casa não é algo que eu possa largar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the notes."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.enderian.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.future.respond.leave   [35 chars]
    en  I'll let you get back to the notes.
    >>  ............................................
    pt  Vou deixar você voltar às anotações.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the notes."
       spoken on: conversations.topic.work.enderian.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.enderian.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.enderian` — e.g. "I study the tall ones and their pearls. Rule one: notes, never eye contact. Rule two: see rule one."


```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.enderian.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.enderian.respond   [34 chars]
    en  That's the research, and rule one.
    >>  ............................................
    pt  É a pesquisa, e a regra número um.
    >>  ............................................
```


### Button `ask_hard` — "What happens if you break rule one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.identity` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.hard` — accepted phrasings: "what happens if you break rule one"
  - the message must contain one of: `rule`, `break`, `eye`
  - scored words: `rule`(1.5), `break`(1.2), `eye`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.respond.ask_hard   [35 chars]
    en  What happens if you break rule one?
    >>  ............................................
    pt  O que acontece se você quebrar a regra número um?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.enderian.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.hard
WHO    VILLAGER — what the player reads after pressing "What happens if you break rule one?"
       spoken on: conversations.topic.work.enderian.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.hard`: the villager explains. Subject `work.enderian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.followup / ask_more
```

> Written out in full under **`conversations.scene.work.enderian.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Somebody has to write this down."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.enderian.identity` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.value` — accepted phrasings: "somebody has to write this down"
  - the message must contain one of: `write`, `record`
  - scored words: `write`(1.5), `record`(1.5), `somebody`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.respond.value   [32 chars]
    en  Somebody has to write this down.
    >>  ............................................
    pt  Alguém tem que anotar isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.enderian.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.enderian.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.value
WHO    VILLAGER — what the player reads after pressing "Somebody has to write this down."
       spoken on: conversations.topic.work.enderian.respond, button `value`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.value`: the villager accepts. Subject `work.enderian.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.value/1   [77 chars]
    en  Somebody does, and nobody was, so it's me. The notes will outlast the noting.
    >>  ............................................
    pt  Alguém tem, e ninguém estava, então sou eu. As anotações vão durar mais que o ato de anotar.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.value/2   [60 chars]
    en  That's the whole justification, and on bad days it's enough.
    >>  ............................................
    pt  É a justificativa inteira, e em dias ruins ela basta.
    >>  ............................................
```


### Button `challenge` — "You're going to get yourself killed."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.enderian.identity` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.challenge` — accepted phrasings: "you're going to get yourself killed"
  - the message must contain one of: `killed`, `dangerous`, `die`
  - scored words: `killed`(1.5), `dangerous`(1.2), `die`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.respond.challenge   [36 chars]
    en  You're going to get yourself killed.
    >>  ............................................
    pt  Você vai acabar se matando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.enderian.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.enderian.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You're going to get yourself killed."
       spoken on: conversations.topic.work.enderian.respond, button `challenge`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.challenge.landed`: the villager resists. Subject `work.enderian.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.challenge.landed/1   [85 chars]
    en  Very possibly. I've written the notes in a hand somebody else can read, just in case.
    >>  ............................................
    pt  Muito possivelmente. Escrevi as anotações numa letra que outra pessoa consegue ler, por garantia.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.challenge.landed/2   [61 chars]
    en  That's a reasonable prediction, %1$s. I've made the same one.
    >>  ............................................
    pt  É uma previsão razoável, %1$s. Eu fiz a mesma.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.enderian.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.enderian.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You're going to get yourself killed."
       spoken on: conversations.topic.work.enderian.respond, button `challenge`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.challenge.stung`: the villager resists. Subject `work.enderian.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.challenge.stung/1   [86 chars]
    en  ...I have taken more precautions than anyone in this village has taken about anything.
    >>  ............................................
    pt  ...Tomei mais precauções do que qualquer um deste vilarejo tomou por qualquer coisa.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.challenge.stung/2   [45 chars]
    en  Killed. Yes. Thank you for the encouragement.
    >>  ............................................
    pt  Morto. Sim. Obrigado pelo incentivo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the notes."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.enderian.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.respond.leave   [35 chars]
    en  I'll let you get back to the notes.
    >>  ............................................
    pt  Vou deixar você voltar às anotações.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the notes."
       spoken on: conversations.topic.work.enderian.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.enderian.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.enderian.risk` — e.g. "People here watch my hands. Nineteen years and they still watch my hands, and I've decided that's fair."


```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.enderian.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.enderian.risk.respond   [25 chars]
    en  That's what sits with me.
    >>  ............................................
    pt  É o que fica comigo.
    >>  ............................................
```


### Button `ask_hands` — "Why is the watching fair?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.risk` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.risk.ask_hands` — accepted phrasings: "why is the watching fair"
  - the message must contain one of: `watching`, `fair`, `hands`
  - scored words: `watching`(1.5), `fair`(1.2), `hands`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.risk.respond.ask_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.risk.respond.ask_hands   [25 chars]
    en  Why is the watching fair?
    >>  ............................................
    pt  Por que olhar é justo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.risk.ask_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.risk.ask_hands
WHO    VILLAGER — what the player reads after pressing "Why is the watching fair?"
       spoken on: conversations.topic.work.enderian.risk.respond, button `ask_hands`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.risk.ask_hands`: the villager explains. Subject `work.enderian.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.risk.ask_hands/1   [97 chars]
    en  Because I can be elsewhere before they've finished a sentence. That's a reasonable thing to mind.
    >>  ............................................
    pt  Porque eu posso estar em outro lugar antes de terminarem uma frase. É razoável se importar com isso.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.risk.ask_hands/2   [78 chars]
    en  It isn't fair. Calling it fair is how I've made nineteen years bearable, %1$s.
    >>  ............................................
    pt  Não é justo. Chamar de justo é como eu tornei dezenove anos suportáveis, %1$s.
    >>  ............................................
```


### Button `sympathise` — "Nobody knowing what happened is the worse half of that."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.enderian.risk` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.risk.sympathise` — accepted phrasings: "nobody knowing what happened is the worse half of that"
  - the message must contain one of: `knowing`, `happened`, `alone`
  - scored words: `knowing`(1.2), `happened`(1.2), `alone`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.risk.respond.sympathise   [55 chars]
    en  Nobody knowing what happened is the worse half of that.
    >>  ............................................
    pt  Ninguém saber o que houve é a metade pior disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.enderian.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.enderian.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Nobody knowing what happened is the worse half of that."
       spoken on: conversations.topic.work.enderian.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.risk.sympathise`: the villager accepts. Subject `work.enderian.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.risk.sympathise/1   [94 chars]
    en  ...It is. I leave a note now. It says where I meant to go and it is the loneliest thing I own.
    >>  ............................................
    pt  ...É. Eu deixo um bilhete agora. Diz aonde eu pretendia ir e é a coisa mais solitária que eu tenho.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.risk.sympathise/2   [79 chars]
    en  You've gone straight to the part I don't say, %1$s. Give me a moment with that.
    >>  ............................................
    pt  Você foi direto na parte que eu não digo, %1$s. Me dê um momento com isso.
    >>  ............................................
```


### Button `ask_note` — "Who would find the note?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.risk` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.risk.ask_note` — accepted phrasings: "who would find the note"
  - the message must contain one of: `note`, `find`
  - scored words: `note`(1.5), `find`(1.0), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.risk.respond.ask_note
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.risk.respond.ask_note   [24 chars]
    en  Who would find the note?
    >>  ............................................
    pt  Quem acharia o bilhete?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.risk.ask_note`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.risk.ask_note
WHO    VILLAGER — what the player reads after pressing "Who would find the note?"
       spoken on: conversations.topic.work.enderian.risk.respond, button `ask_note`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.risk.ask_note`: the villager explains. Subject `work.enderian.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.risk.ask_note/1   [94 chars]
    en  Whoever came in when the door stayed shut. Which is a sentence I've never finished before now.
    >>  ............................................
    pt  Quem entrasse quando a porta continuasse fechada. É uma frase que eu nunca terminei até agora.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.risk.ask_note/2   [89 chars]
    en  That's the flaw in the plan, %1$s. I've written it to nobody in particular for six years.
    >>  ............................................
    pt  É a falha do plano, %1$s. Escrevo pra ninguém em particular há seis anos.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the notes."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.enderian.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.risk.respond.leave   [35 chars]
    en  I'll let you get back to the notes.
    >>  ............................................
    pt  Vou deixar você voltar às anotações.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the notes."
       spoken on: conversations.topic.work.enderian.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.enderian.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.enderian.task` — e.g. "Sorting what came back through with me. Half of it I can't name and all of it has to be catalogued."


```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.enderian.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.enderian.task.respond   [24 chars]
    en  That's the day I've got.
    >>  ............................................
    pt  É o dia que eu tenho.
    >>  ............................................
```


### Button `ask_sickness` — "What sickness?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.task` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.task.ask_sickness` — accepted phrasings: "what sickness"
  - the message must contain one of: `sickness`, `ill`, `passes`
  - scored words: `sickness`(1.5), `ill`(1.2), `passes`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.task.respond.ask_sickness
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.task.respond.ask_sickness   [14 chars]
    en  What sickness?
    >>  ............................................
    pt  Que mal-estar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.task.ask_sickness`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.task.ask_sickness
WHO    VILLAGER — what the player reads after pressing "What sickness?"
       spoken on: conversations.topic.work.enderian.task.respond, button `ask_sickness`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.task.ask_sickness`: the villager explains. Subject `work.enderian.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.task.ask_sickness/1   [104 chars]
    en  The cost of arriving somewhere you didn't walk to. It passes. It always passes. I still count the hours.
    >>  ............................................
    pt  O custo de chegar num lugar sem caminhar. Passa. Sempre passa. Eu ainda conto as horas.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.task.ask_sickness/2   [82 chars]
    en  It's not catching, %1$s, before you take a step back. It's mine and it stays mine.
    >>  ............................................
    pt  Não é contagioso, %1$s, antes que você recue. É meu e continua meu.
    >>  ............................................
```


### Button `offer_hands` — "I could catalogue while you sit."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.enderian.task` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.task.offer_hands` — accepted phrasings: "i could catalogue while you sit"
  - the message must contain one of: `catalogue`, `sort`
  - scored words: `catalogue`(1.5), `sort`(1.2), `sit`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.task.respond.offer_hands   [32 chars]
    en  I could catalogue while you sit.
    >>  ............................................
    pt  Eu podia catalogar enquanto você senta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.enderian.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.enderian.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could catalogue while you sit."
       spoken on: conversations.topic.work.enderian.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.task.offer_hands`: the villager accepts. Subject `work.enderian.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.task.offer_hands/1   [94 chars]
    en  ...You could. Don't hold anything longer than a moment and put it down where you picked it up.
    >>  ............................................
    pt  ...Podia. Não segure nada por mais que um instante e ponha de volta onde pegou.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.task.offer_hands/2   [92 chars]
    en  Describe it aloud and I'll name it from here, %1$s. That's how it's meant to be done anyway.
    >>  ............................................
    pt  Descreva em voz alta e eu nomeio daqui, %1$s. É assim que devia ser feito de todo jeito.
    >>  ............................................
```


### Button `ask_unnamed` — "What can't you name?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.task` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.task.ask_unnamed` — accepted phrasings: "what can't you name"
  - the message must contain one of: `name`, `unnamed`, `objects`
  - scored words: `name`(1.2), `unnamed`(1.5), `objects`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.task.respond.ask_unnamed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.task.respond.ask_unnamed   [20 chars]
    en  What can't you name?
    >>  ............................................
    pt  O que você não sabe nomear?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.task.ask_unnamed`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.task.ask_unnamed
WHO    VILLAGER — what the player reads after pressing "What can't you name?"
       spoken on: conversations.topic.work.enderian.task.respond, button `ask_unnamed`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.task.ask_unnamed`: the villager explains. Subject `work.enderian.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.task.ask_unnamed/1   [96 chars]
    en  Four things. I've drawn all four and shown them to nobody, because of the questions that follow.
    >>  ............................................
    pt  Quatro coisas. Desenhei as quatro e não mostrei a ninguém, por causa das perguntas que vêm.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.task.ask_unnamed/2   [97 chars]
    en  One of them is warm, %1$s, and it has been warm for two years. I'd rather not discuss it further.
    >>  ............................................
    pt  Uma delas é morna, %1$s, e está morna há dois anos. Prefiro não falar mais sobre isso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the notes."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.enderian.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.task.respond.leave   [35 chars]
    en  I'll let you get back to the notes.
    >>  ............................................
    pt  Vou deixar você voltar às anotações.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the notes."
       spoken on: conversations.topic.work.enderian.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.enderian.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.enderian.village` — e.g. "I've fetched a cleric from four valleys away in an afternoon. Twice. Nobody discusses either time."


```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.enderian.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.enderian.village.respond   [24 chars]
    en  That's my standing here.
    >>  ............................................
    pt  É a minha posição aqui.
    >>  ............................................
```


### Button `ask_twice` — "Who was the cleric for?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.village` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.village.ask_twice` — accepted phrasings: "who was the cleric for"
  - the message must contain one of: `cleric`, `twice`, `whom`
  - scored words: `cleric`(1.5), `twice`(1.2), `whom`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.village.respond.ask_twice
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.village.respond.ask_twice   [23 chars]
    en  Who was the cleric for?
    >>  ............................................
    pt  A clériga foi pra quem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.village.ask_twice`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.village.ask_twice
WHO    VILLAGER — what the player reads after pressing "Who was the cleric for?"
       spoken on: conversations.topic.work.enderian.village.respond, button `ask_twice`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.village.ask_twice`: the villager explains. Subject `work.enderian.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.village.ask_twice/1   [85 chars]
    en  A child, the first time. The second time it was the man who watches my hands hardest.
    >>  ............................................
    pt  Uma criança, na primeira vez. Na segunda foi o homem que mais olha minhas mãos.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.village.ask_twice/2   [88 chars]
    en  Both times for people who have never mentioned it since, %1$s, and I'd not want them to.
    >>  ............................................
    pt  Nas duas vezes pra pessoas que nunca mencionaram depois, %1$s, e eu não gostaria que mencionassem.
    >>  ............................................
```


### Button `say_thanks` — "Two lives in an afternoon each. Say that out loud sometime."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.enderian.village` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.village.say_thanks` — accepted phrasings: "two lives in an afternoon each. say that out loud sometime"
  - the message must contain one of: `lives`, `afternoon`, `aloud`
  - scored words: `lives`(1.5), `afternoon`(1.2), `aloud`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.village.respond.say_thanks   [59 chars]
    en  Two lives in an afternoon each. Say that out loud sometime.
    >>  ............................................
    pt  Duas vidas numa tarde cada. Diga isso em voz alta algum dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.enderian.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.enderian.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Two lives in an afternoon each. Say that out loud sometime."
       spoken on: conversations.topic.work.enderian.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.village.say_thanks`: the villager accepts. Subject `work.enderian.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.village.say_thanks/1   [96 chars]
    en  ...I've never said it out loud. I've thought it on bad evenings and been ashamed of thinking it.
    >>  ............................................
    pt  ...Nunca disse em voz alta. Já pensei em noites ruins e me envergonhei de pensar.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.village.say_thanks/2   [91 chars]
    en  You say it, %1$s. If I say it, it's a man asking to be thanked. If you say it, it's a fact.
    >>  ............................................
    pt  Diga você, %1$s. Se eu disser, é um homem pedindo agradecimento. Se você disser, é um fato.
    >>  ............................................
```


### Button `ask_invite` — "Does the not-inviting bother you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.enderian.village` · offered only once the villager has actually said `work:enderian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.enderian.village.ask_invite` — accepted phrasings: "does the not-inviting bother you"
  - the message must contain one of: `invite`, `bother`, `tolerated`
  - scored words: `invite`(1.5), `bother`(1.2), `tolerated`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.village.respond.ask_invite
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.village.respond.ask_invite   [33 chars]
    en  Does the not-inviting bother you?
    >>  ............................................
    pt  O não convidar te incomoda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.enderian.village.ask_invite`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.enderian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the strangest thing you've recorded?" | "Careful eyes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.village.ask_invite
WHO    VILLAGER — what the player reads after pressing "Does the not-inviting bother you?"
       spoken on: conversations.topic.work.enderian.village.respond, button `ask_invite`
       leaves the player on: conversations.topic.work.enderian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.village.ask_invite`: the villager explains. Subject `work.enderian.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.enderian.village.ask_invite/1   [102 chars]
    en  Less than the tolerating. Being tolerated is a decision people renew every morning, and I can feel it.
    >>  ............................................
    pt  Menos que o tolerar. Ser tolerado é uma decisão que renovam toda manhã, e eu sinto.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.village.ask_invite/2   [97 chars]
    en  It did for eleven years. Now it's just the shape of the room, %1$s, and I've furnished around it.
    >>  ............................................
    pt  Incomodou por onze anos. Agora é só o formato do cômodo, %1$s, e eu mobiliei em volta.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the notes."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.enderian.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.enderian.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.enderian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.enderian.village.respond.leave   [35 chars]
    en  I'll let you get back to the notes.
    >>  ............................................
    pt  Vou deixar você voltar às anotações.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the notes."
       spoken on: conversations.topic.work.enderian.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.left`: the villager accepts. Subject `work.enderian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.enderian.being_looked_at.active.respond / leave; conversations.scene.work.enderian.being_looked_at.succeeded.respond / leave; conversations.scene.work.enderian.followup / leave; conversations.scene.work.enderian.lost_consignment.blocked.respond / leave; conversations.scene.work.enderian.lost_consignment.succeeded.respond / leave; conversations.scene.work.enderian.the_quiet_place.succeeded.respond / leave; conversations.topic.work.enderian.craft.respond / leave; conversations.topic.work.enderian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.enderian.being_looked_at.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

