# Work talk with a adventurer

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.adventurer.bad_route.active.respond`](#conversations-scene-work-adventurer-bad-route-active-respond)
- [`conversations.scene.work.adventurer.bad_route.succeeded.respond`](#conversations-scene-work-adventurer-bad-route-succeeded-respond)
- [`conversations.scene.work.adventurer.followup`](#conversations-scene-work-adventurer-followup)
- [`conversations.scene.work.adventurer.souvenir.succeeded.respond`](#conversations-scene-work-adventurer-souvenir-succeeded-respond)
- [`conversations.scene.work.adventurer.unfinished_delve.active.respond`](#conversations-scene-work-adventurer-unfinished-delve-active-respond)
- [`conversations.scene.work.adventurer.unfinished_delve.blocked.respond`](#conversations-scene-work-adventurer-unfinished-delve-blocked-respond)
- [`conversations.scene.work.adventurer.unfinished_delve.succeeded.respond`](#conversations-scene-work-adventurer-unfinished-delve-succeeded-respond)
- [`conversations.topic.work.adventurer.craft.respond`](#conversations-topic-work-adventurer-craft-respond)
- [`conversations.topic.work.adventurer.followup`](#conversations-topic-work-adventurer-followup)
- [`conversations.topic.work.adventurer.future.respond`](#conversations-topic-work-adventurer-future-respond)
- [`conversations.topic.work.adventurer.respond`](#conversations-topic-work-adventurer-respond)
- [`conversations.topic.work.adventurer.risk.respond`](#conversations-topic-work-adventurer-risk-respond)
- [`conversations.topic.work.adventurer.task.respond`](#conversations-topic-work-adventurer-task-respond)
- [`conversations.topic.work.adventurer.village.respond`](#conversations-topic-work-adventurer-village-respond)

---

## `conversations.scene.work.adventurer.bad_route.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.adventurer.bad_route.active` — e.g. "I was told %2$s was three days. It is five, and the person who told me has never walked it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.adventurer.bad_route.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.active.respond   [13 chars]
    en  The road out.
    >>  ............................................
    pt  A estrada de saída.
    >>  ............................................
```


### Button `ask_alternative` — "Is there a better way round?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.bad_route.active` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.bad_route.active.ask_alternative` — accepted phrasings: "is there a better way round"; "is there a better way round"; "what other route exists"
  - the message must contain one of: `better`, `route`, `round`
  - scored words: `better`(1.8), `route`(1.8), `round`(1.8), `way`(0.8), `other`(0.8), `exists`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.active.respond.ask_alternative
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.bad_route.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.active.respond.ask_alternative   [28 chars]
    en  Is there a better way round?
    >>  ............................................
    pt  Tem um caminho melhor?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.route`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.adventurer.bad_route"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.active.explained
WHO    VILLAGER — what the player reads after pressing "Is there a better way round?"
       spoken on: conversations.scene.work.adventurer.bad_route.active.respond, button `ask_alternative`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.bad_route.active.explained`: the villager explains. Subject `work.adventurer.route`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.active.explained/1   [129 chars]
    en  There is one, and it is longer on the map and shorter on the feet, which is a sentence nobody believes until they have done both.
    >>  ............................................
    pt  Existe, e é mais longo no mapa e mais curto nos pés, uma frase em que ninguém acredita antes de ter feito os dois.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.active.explained/2   [129 chars]
    en  Water. Go by water where you can. %2$s exists because somebody once owned a cart and everybody copied them for two hundred years.
    >>  ............................................
    pt  Água. Vá pela água quando der. %2$s existe porque alguém teve uma carroça um dia e todo mundo copiou por duzentos anos.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.active.explained/3   [109 chars]
    en  I am drawing one. Badly. I can walk a country and I cannot draw it, and those turn out to be separate skills.
    >>  ............................................
    pt  Estou desenhando um. Mal. Sei andar por um país e não sei desenhá-lo, e parece que são talentos separados.
    >>  ............................................
```


### Button `advise_say_so` — "Tell people it's five days."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.adventurer.bad_route.active` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.bad_route.active.advise_say_so` — accepted phrasings: "tell people its five days"; "tell people the true number of days"; "correct them out loud"
  - the message must contain one of: `correct`, `true`, `days`
  - scored words: `correct`(1.8), `true`(1.8), `days`(1.8), `tell`(0.8), `people`(0.8), `its`(0.8), `five`(0.8), `number`(0.8), `out`(0.8), `loud`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.active.respond.advise_say_so
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.bad_route.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.active.respond.advise_say_so   [27 chars]
    en  Tell people it's five days.
    >>  ............................................
    pt  Diga às pessoas que são cinco dias.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.adventurer.route`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.adventurer.bad_route"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.active.conceded
WHO    VILLAGER — what the player reads after pressing "Tell people it's five days."
       spoken on: conversations.scene.work.adventurer.bad_route.active.respond, button `advise_say_so`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.bad_route.active.conceded`: the villager accepts. Subject `work.adventurer.route`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.active.conceded/1   [120 chars]
    en  You are right and I have avoided it, because correcting a man about a road he is proud of costs more than two days does.
    >>  ............................................
    pt  Você tem razão e eu evitei, porque corrigir um homem sobre uma estrada de que ele se orgulha custa mais do que dois dias.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.active.conceded/2   [110 chars]
    en  Fine. I will say five. And when somebody argues, I will say five again, in the same voice, until it is boring.
    >>  ............................................
    pt  Certo. Vou dizer cinco. E quando alguém discutir, digo cinco de novo, no mesmo tom, até ficar chato.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.active.conceded/3   [124 chars]
    en  The awkward part is that I have repeated the three-day figure myself, to sound like I belong. So I have to correct me first.
    >>  ............................................
    pt  O constrangedor é que eu mesma já repeti o número de três dias, para parecer do meio. Então tenho que me corrigir primeiro.
    >>  ............................................
```


### Button `leave` — "I'll let you pack."

*stance family `exit` · tone `plain` · answers the beat(s) `work.adventurer.bad_route.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.bad_route.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.active.respond.leave   [18 chars]
    en  I'll let you pack.
    >>  ............................................
    pt  Vou deixar você arrumar as coisas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you pack."
       spoken on: conversations.scene.work.adventurer.bad_route.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave; conversations.topic.work.adventurer.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.adventurer.leave/1   [52 chars]
    en  Resting is the chapter between chapters. Off you go.
    >>  ............................................
    pt  Descansar é o capítulo entre capítulos. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.leave/2   [43 chars]
    en  Aye. I'll be here till the boots dry, %1$s.
    >>  ............................................
    pt  É. Vou estar aqui até as botas secarem, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.adventurer.bad_route.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.adventurer.bad_route.succeeded` — e.g. "I said five days about %2$s, out loud, twice, and the second time somebody thanked me."


```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.adventurer.bad_route.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded.respond   [9 chars]
    en  The road.
    >>  ............................................
    pt  A estrada.
    >>  ............................................
```


### Button `note_it_mattered` — "That saves someone a bad week."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.adventurer.bad_route.succeeded` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.bad_route.succeeded.note_it_mattered` — accepted phrasings: "that saves someone a bad week"; "that saves somebody a bad week"; "someone avoids a rough trip now"
  - the message must contain one of: `saves`, `avoids`, `week`
  - scored words: `saves`(1.8), `avoids`(1.8), `week`(1.8), `someone`(0.8), `bad`(0.8), `somebody`(0.8), `rough`(0.8), `trip`(0.8), `now`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.succeeded.respond.note_it_mattered
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.bad_route.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded.respond.note_it_mattered   [30 chars]
    en  That saves someone a bad week.
    >>  ............................................
    pt  Isso poupa uma semana ruim de alguém.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.adventurer.route`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.adventurer.bad_route"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.succeeded.received
WHO    VILLAGER — what the player reads after pressing "That saves someone a bad week."
       spoken on: conversations.scene.work.adventurer.bad_route.succeeded.respond, button `note_it_mattered`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.bad_route.succeeded.received`: the villager accepts. Subject `work.adventurer.route`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded.received/1   [94 chars]
    en  That is why I said it. Two days of somebody else's life, bought with one awkward conversation.
    >>  ............................................
    pt  Foi por isso que eu disse. Dois dias da vida de outra pessoa, comprados com uma conversa constrangedora.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded.received/2   [97 chars]
    en  It does, and I had to be told that before I would do it, which is worth remembering about myself.
    >>  ............................................
    pt  Poupa, sim, e precisaram me dizer isso antes de eu fazer, o que vale lembrar a meu respeito.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded.received/3   [122 chars]
    en  Somebody will curse the road anyway. But they will curse it having packed for five days, and that is the whole difference.
    >>  ............................................
    pt  Alguém vai xingar a estrada de qualquer jeito. Mas vai xingar tendo se preparado para cinco dias, e a diferença é toda essa.
    >>  ............................................
```


### Button `leave` — "I'll let you pack."

*stance family `exit` · tone `plain` · answers the beat(s) `work.adventurer.bad_route.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.bad_route.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded.respond.leave   [18 chars]
    en  I'll let you pack.
    >>  ............................................
    pt  Vou deixar você arrumar as coisas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you pack."
       spoken on: conversations.scene.work.adventurer.bad_route.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave; conversations.topic.work.adventurer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.adventurer.followup`

**Reached from 12 route(s):** `conversations.scene.work.adventurer.bad_route.active.respond` / `ask_alternative`; `conversations.scene.work.adventurer.bad_route.active.respond` / `advise_say_so`; `conversations.scene.work.adventurer.bad_route.succeeded.respond` / `note_it_mattered`; `conversations.scene.work.adventurer.souvenir.succeeded.respond` / `ask_theory`; `conversations.scene.work.adventurer.souvenir.succeeded.respond` / `admire_it`; `conversations.scene.work.adventurer.unfinished_delve.active.respond` / `ask_when`; `conversations.scene.work.adventurer.unfinished_delve.active.respond` / `wish_well`; `conversations.scene.work.adventurer.unfinished_delve.blocked.respond` / `ask_what_is_down_there`; `conversations.scene.work.adventurer.unfinished_delve.blocked.respond` / `offer_torches`; `conversations.scene.work.adventurer.unfinished_delve.blocked.respond` / `urge_caution`; `conversations.scene.work.adventurer.unfinished_delve.succeeded.respond` / `ask_worth_it`; `conversations.scene.work.adventurer.unfinished_delve.succeeded.respond` / `congratulate`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.adventurer.bad_route.active.conceded` — e.g. "You are right and I have avoided it, because correcting a man about a road he is proud of costs more than two days does."
- `conversations.scene.work.adventurer.bad_route.active.explained` — e.g. "There is one, and it is longer on the map and shorter on the feet, which is a sentence nobody believes until they have done both."
- `conversations.scene.work.adventurer.bad_route.succeeded.received` — e.g. "That is why I said it. Two days of somebody else's life, bought with one awkward conversation."
- `conversations.scene.work.adventurer.souvenir.succeeded.softened` — e.g. "They did. There is a thumbprint on the underside and I have put my thumb next to it more often than I would admit in company."
- `conversations.scene.work.adventurer.souvenir.succeeded.theorised` — e.g. "Somebody made %2$s for a person they liked. Everything about the finish says so and none of it proves it."
- `conversations.scene.work.adventurer.unfinished_delve.active.answered` — e.g. "The first clear morning after the harvest rush. I have learned to go when the village is busy, so that leaving is not an event."
- `conversations.scene.work.adventurer.unfinished_delve.active.thanked` — e.g. "All ten, that is the plan. It is a lower bar than the songs set and a much more useful one."
- `conversations.scene.work.adventurer.unfinished_delve.blocked.accepted` — e.g. "Then I go back. Light is the whole argument — take it away and %2$s is a wall, put it back and it is a stair again."
- `conversations.scene.work.adventurer.unfinished_delve.blocked.explained` — e.g. "Rooms. That is the honest answer. Everyone wants me to say treasure and what is actually past %2$s is rooms, and one of them will have something in it."
- `conversations.scene.work.adventurer.unfinished_delve.blocked.steadied` — e.g. "It was, and you are the second person to say so, and the first one was me at three in the morning being unconvincing."
- `conversations.scene.work.adventurer.unfinished_delve.succeeded.acknowledged` — e.g. "It does. I have finished about half the things I have started, and this one is in the good half."
- `conversations.scene.work.adventurer.unfinished_delve.succeeded.weighed` — e.g. "Not in trade goods. In the other currency, yes: I no longer wonder, and I had been wondering every night since the spring."


```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.adventurer.followup   [25 chars]
    en  Was there something else?
    >>  ............................................
    pt  Tinha mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of coming home?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.adventurer.*` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.followup.ask_more` — accepted phrasings: "whats the hardest part of coming home"; "what is the hardest part about coming back"; "hardest thing about returning home"
  - the message must contain one of: `hardest`, `coming`
  - scored words: `hardest`(1.8), `coming`(1.8), `whats`(0.8), `part`(0.8), `home`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.followup.ask_more   [39 chars]
    en  What's the hardest part of coming home?
    >>  ............................................
    pt  Qual é a parte mais difícil de voltar para casa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of coming home?"
       spoken on: conversations.scene.work.adventurer.followup, button `ask_more`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.hard`: the villager explains. Subject `work.adventurer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.adventurer.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.adventurer.hard/1   [75 chars]
    en  Supplies. Everyone imagines the monster and everyone dies of the empty bag.
    >>  ............................................
    pt  Suprimentos. Todo mundo imagina o monstro e todo mundo morre de saco vazio.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.hard/2   [84 chars]
    en  Nothing dramatic, %1$s. You take a wrong turn on day two and pay for it on day nine.
    >>  ............................................
    pt  Nada dramático, %1$s. Você erra a curva no segundo dia e paga no nono.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your packing."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.adventurer.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.followup.leave   [31 chars]
    en  I'll leave you to your packing.
    >>  ............................................
    pt  Vou deixar você com a mochila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your packing."
       spoken on: conversations.scene.work.adventurer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave; conversations.topic.work.adventurer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.adventurer.souvenir.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.adventurer.souvenir.succeeded` — e.g. "%2$s. I carried it eleven days and I still cannot tell you what it was for."


```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.souvenir.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.adventurer.souvenir.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.respond   [25 chars]
    en  That thing on your shelf.
    >>  ............................................
    pt  Aquilo na sua prateleira.
    >>  ............................................
```


### Button `ask_theory` — "What's your best theory?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.souvenir.succeeded` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.souvenir.succeeded.ask_theory` — accepted phrasings: "whats your best theory"; "what is your best theory about it"; "give me your theory"
  - the message must contain one of: `theory`, `best`
  - scored words: `theory`(1.8), `best`(1.8), `whats`(0.8), `give`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.souvenir.succeeded.respond.ask_theory
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.souvenir.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.respond.ask_theory   [24 chars]
    en  What's your best theory?
    >>  ............................................
    pt  Qual é a sua melhor teoria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.adventurer.the_one_that_got_away`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.adventurer.souvenir"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.souvenir.succeeded.theorised
WHO    VILLAGER — what the player reads after pressing "What's your best theory?"
       spoken on: conversations.scene.work.adventurer.souvenir.succeeded.respond, button `ask_theory`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.souvenir.succeeded.theorised`: the villager explains. Subject `work.adventurer.the_one_that_got_away`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.theorised/1   [105 chars]
    en  Somebody made %2$s for a person they liked. Everything about the finish says so and none of it proves it.
    >>  ............................................
    pt  Alguém fez %2$s para uma pessoa de quem gostava. Todo o acabamento diz isso e nada disso prova.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.theorised/2   [109 chars]
    en  That it was ordinary. That %2$s was to its owner what a spoon is to me, and the strangeness is entirely mine.
    >>  ............................................
    pt  Que era comum. Que %2$s era para o dono o que uma colher é para mim, e a estranheza é inteiramente minha.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.theorised/3   [106 chars]
    en  I have four theories and I rotate them by mood, which tells you they are all decoration on the same shrug.
    >>  ............................................
    pt  Tenho quatro teorias e as revezo conforme o humor, o que já mostra que são todas enfeite do mesmo dar de ombros.
    >>  ............................................
```


### Button `admire_it` — "Somebody made that by hand."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.adventurer.souvenir.succeeded` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.souvenir.succeeded.admire_it` — accepted phrasings: "somebody made that by hand"; "somebody made that by hand"; "a person shaped that themselves"
  - the message must contain one of: `hand`, `shaped`, `made`
  - scored words: `hand`(1.8), `shaped`(1.8), `made`(1.8), `somebody`(0.8), `person`(0.8), `themselves`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.souvenir.succeeded.respond.admire_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.souvenir.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.respond.admire_it   [27 chars]
    en  Somebody made that by hand.
    >>  ............................................
    pt  Alguém fez isso à mão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.adventurer.the_one_that_got_away`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.adventurer.souvenir"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.souvenir.succeeded.softened
WHO    VILLAGER — what the player reads after pressing "Somebody made that by hand."
       spoken on: conversations.scene.work.adventurer.souvenir.succeeded.respond, button `admire_it`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.souvenir.succeeded.softened`: the villager accepts. Subject `work.adventurer.the_one_that_got_away`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.softened/1   [125 chars]
    en  They did. There is a thumbprint on the underside and I have put my thumb next to it more often than I would admit in company.
    >>  ............................................
    pt  Fizeram. Tem uma marca de polegar embaixo, e eu já pus o meu ao lado mais vezes do que admitiria em público.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.softened/2   [91 chars]
    en  That is the part that gets me. Not that it is old — that somebody sat down and finished it.
    >>  ............................................
    pt  É essa a parte que me pega. Não que seja antigo — que alguém sentou e terminou.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.softened/3   [89 chars]
    en  Yes. And they were better at it than I am at anything, which I find I do not mind at all.
    >>  ............................................
    pt  Sim. E eram melhores nisso do que eu sou em qualquer coisa, e descobri que isso não me incomoda nem um pouco.
    >>  ............................................
```


### Button `leave` — "I'll let you pack."

*stance family `exit` · tone `plain` · answers the beat(s) `work.adventurer.souvenir.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.souvenir.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.souvenir.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded.respond.leave   [18 chars]
    en  I'll let you pack.
    >>  ............................................
    pt  Vou deixar você arrumar as coisas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you pack."
       spoken on: conversations.scene.work.adventurer.souvenir.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave; conversations.topic.work.adventurer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.adventurer.unfinished_delve.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.adventurer.unfinished_delve.active` — e.g. "I am going back to %2$s. Not today. But the deciding part is finished, which was the hard part."


```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.adventurer.unfinished_delve.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.respond   [16 chars]
    en  The ruin, again.
    >>  ............................................
    pt  A ruína, de novo.
    >>  ............................................
```


### Button `ask_when` — "Which day do you leave?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.unfinished_delve.active` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.unfinished_delve.active.ask_when` — accepted phrasings: "which day do you leave"; "when are you setting off"; "which day do you leave"
  - the message must contain one of: `setting`, `day`, `leave`
  - scored words: `setting`(1.8), `day`(1.8), `leave`(1.8), `which`(0.8), `when`(0.8), `off`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.active.respond.ask_when
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.respond.ask_when   [23 chars]
    en  Which day do you leave?
    >>  ............................................
    pt  Que dia você parte?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.ruin_found`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.adventurer.unfinished_delve"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.active.answered
WHO    VILLAGER — what the player reads after pressing "Which day do you leave?"
       spoken on: conversations.scene.work.adventurer.unfinished_delve.active.respond, button `ask_when`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.active.answered`: the villager explains. Subject `work.adventurer.ruin_found`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.answered/1   [127 chars]
    en  The first clear morning after the harvest rush. I have learned to go when the village is busy, so that leaving is not an event.
    >>  ............................................
    pt  Na primeira manhã limpa depois da correria da colheita. Aprendi a ir quando a vila está ocupada, para que partir não vire um acontecimento.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.answered/2   [132 chars]
    en  Soon, and I am deliberately not naming a day, because a named day gets a send-off and a send-off makes it harder to come back early.
    >>  ............................................
    pt  Em breve, e estou deliberadamente não marcando um dia, porque dia marcado vira despedida, e despedida dificulta voltar cedo.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.answered/3   [125 chars]
    en  When I have slept properly two nights running. That is a real condition, not an excuse. Tired is how people fall down stairs.
    >>  ............................................
    pt  Quando eu tiver dormido bem duas noites seguidas. É uma condição de verdade, não desculpa. É cansada que a gente cai de escada.
    >>  ............................................
```


### Button `wish_well` — "Come back with all your fingers."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.adventurer.unfinished_delve.active` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.unfinished_delve.active.wish_well` — accepted phrasings: "come back with all your fingers"; "come back with all your fingers"; "return in one piece"
  - the message must contain one of: `fingers`, `piece`, `return`
  - scored words: `fingers`(1.8), `piece`(1.8), `return`(1.8), `come`(0.8), `back`(0.8), `all`(0.8), `one`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.active.respond.wish_well
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.respond.wish_well   [32 chars]
    en  Come back with all your fingers.
    >>  ............................................
    pt  Volte com todos os dedos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.adventurer.ruin_found`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.adventurer.unfinished_delve"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.active.thanked
WHO    VILLAGER — what the player reads after pressing "Come back with all your fingers."
       spoken on: conversations.scene.work.adventurer.unfinished_delve.active.respond, button `wish_well`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.active.thanked`: the villager accepts. Subject `work.adventurer.ruin_found`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.thanked/1   [91 chars]
    en  All ten, that is the plan. It is a lower bar than the songs set and a much more useful one.
    >>  ............................................
    pt  Todos os dez, esse é o plano. É uma meta mais baixa que a das canções e bem mais útil.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.thanked/2   [92 chars]
    en  I will try. The people who say that are the reason coming back is worth doing, so thank you.
    >>  ............................................
    pt  Vou tentar. As pessoas que dizem isso são o motivo de valer a pena voltar, então obrigada.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.thanked/3   [121 chars]
    en  That is the correct thing to wish an adventurer and almost nobody says it. They wish me glory, which is heavier to carry.
    >>  ............................................
    pt  É a coisa certa de se desejar a uma aventureira e quase ninguém diz. Me desejam glória, que é mais pesada de carregar.
    >>  ............................................
```


### Button `leave` — "I'll let you pack."

*stance family `exit` · tone `plain` · answers the beat(s) `work.adventurer.unfinished_delve.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active.respond.leave   [18 chars]
    en  I'll let you pack.
    >>  ............................................
    pt  Vou deixar você arrumar as coisas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you pack."
       spoken on: conversations.scene.work.adventurer.unfinished_delve.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave; conversations.topic.work.adventurer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.adventurer.unfinished_delve.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.adventurer.unfinished_delve.blocked` — e.g. "I got as far as %2$s and turned round, because there was %3$s and I would rather be embarrassed than dead."


```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.adventurer.unfinished_delve.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond   [9 chars]
    en  The ruin.
    >>  ............................................
    pt  A ruína.
    >>  ............................................
```


### Button `ask_what_is_down_there` — "Any guess what is further in?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.unfinished_delve.blocked` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.unfinished_delve.blocked.ask_what_is_down_there` — accepted phrasings: "any guess what is further in"; "what do you reckon is further in"; "any guess what is beyond that"
  - the message must contain one of: `further`, `beyond`, `guess`
  - scored words: `further`(1.8), `beyond`(1.8), `guess`(1.8), `any`(0.8), `reckon`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond.ask_what_is_down_there
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond.ask_what_is_down_there   [29 chars]
    en  Any guess what is further in?
    >>  ............................................
    pt  Algum palpite do que tem mais para dentro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.adventurer.ruin_found`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.adventurer.unfinished_delve"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Any guess what is further in?"
       spoken on: conversations.scene.work.adventurer.unfinished_delve.blocked.respond, button `ask_what_is_down_there`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.blocked.explained`: the villager explains. Subject `work.adventurer.ruin_found`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.explained/1   [151 chars]
    en  Rooms. That is the honest answer. Everyone wants me to say treasure and what is actually past %2$s is rooms, and one of them will have something in it.
    >>  ............................................
    pt  Salas. Essa é a resposta honesta. Todo mundo quer que eu diga tesouro, e o que existe depois de %2$s são salas, e uma delas vai ter alguma coisa dentro.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.explained/2   [148 chars]
    en  I have a guess and I am not going to say it out loud, because I have heard what happens to a guess once it has been repeated at the well four times.
    >>  ............................................
    pt  Tenho um palpite e não vou dizer em voz alta, porque já vi o que acontece com um palpite depois de repetido quatro vezes no poço.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.explained/3   [151 chars]
    en  Air moves through there, which means it goes somewhere. That is all I actually know, and I would rather bring back one certain thing than four stories.
    >>  ............................................
    pt  O ar se move por ali, o que significa que aquilo leva a algum lugar. É tudo o que eu realmente sei, e prefiro trazer uma certeza a trazer quatro histórias.
    >>  ............................................
```


### Button `offer_torches` — "I'll bring you torches for it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.adventurer.unfinished_delve.blocked` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.unfinished_delve.blocked.offer_torches` — accepted phrasings: "ill bring you torches for it"; "i can bring you torches"; "let me fetch some torches for that"
  - the message must contain one of: `torches`, `torch`
  - scored words: `torches`(1.8), `torch`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8), `some`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond.offer_torches
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond.offer_torches   [30 chars]
    en  I'll bring you torches for it.
    >>  ............................................
    pt  Vou te trazer tochas para isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.adventurer.delve.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.adventurer.ruin_found`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.unfinished_delve", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.adventurer.unfinished_delve", "obligation": "commitment:work.adventurer.bring_torches"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.adventurer.bring_torches"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you torches for it."
       spoken on: conversations.scene.work.adventurer.unfinished_delve.blocked.respond, button `offer_torches`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.blocked.accepted`: the villager accepts. Subject `work.adventurer.ruin_found`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.accepted/1   [115 chars]
    en  Then I go back. Light is the whole argument — take it away and %2$s is a wall, put it back and it is a stair again.
    >>  ............................................
    pt  Então eu volto. A luz é o argumento inteiro — tire ela e %2$s é uma parede, devolva e volta a ser uma escada.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.accepted/2   [124 chars]
    en  Yes. And I will tell you what is down there rather than telling the tavern, because you will have paid for the seeing of it.
    >>  ............................................
    pt  Sim. E vou contar a você o que tem lá embaixo em vez de contar à taverna, porque você terá pago pela visão.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.accepted/3   [121 chars]
    en  That is the cheapest solution anyone has offered and it is also the correct one. I will wait until you have them in hand.
    >>  ............................................
    pt  É a solução mais barata que alguém me ofereceu e também é a certa. Espero até você ter isso em mãos.
    >>  ............................................
```


### Button `urge_caution` — "Turning back was the right call."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.adventurer.unfinished_delve.blocked` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.unfinished_delve.blocked.urge_caution` — accepted phrasings: "turning back was the right call"; "turning back was correct"; "you were right to come home"
  - the message must contain one of: `turning`, `correct`, `home`
  - scored words: `turning`(1.8), `correct`(1.8), `home`(1.8), `back`(0.8), `right`(0.8), `call`(0.8), `were`(0.8), `come`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond.urge_caution
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond.urge_caution   [32 chars]
    en  Turning back was the right call.
    >>  ............................................
    pt  Voltar foi a decisão certa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.adventurer.ruin_found`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.adventurer.unfinished_delve"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Turning back was the right call."
       spoken on: conversations.scene.work.adventurer.unfinished_delve.blocked.respond, button `urge_caution`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.blocked.steadied`: the villager accepts. Subject `work.adventurer.ruin_found`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.steadied/1   [117 chars]
    en  It was, and you are the second person to say so, and the first one was me at three in the morning being unconvincing.
    >>  ............................................
    pt  Foi, e você é a segunda pessoa a dizer isso, e a primeira fui eu às três da manhã, sem convencer ninguém.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.steadied/2   [139 chars]
    en  Thank you. The village version has me heroic and reckless, and the true version has me standing in the dark doing arithmetic about torches.
    >>  ............................................
    pt  Obrigada. A versão da vila me pinta heroica e imprudente, e a versão verdadeira me tem parada no escuro fazendo contas sobre tochas.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.steadied/3   [84 chars]
    en  I know. I still resent it. Both of those are allowed to be true at once, apparently.
    >>  ............................................
    pt  Eu sei. Ainda assim me incomoda. Aparentemente as duas coisas podem ser verdade ao mesmo tempo.
    >>  ............................................
```


### Button `leave` — "I'll let you pack."

*stance family `exit` · tone `plain` · answers the beat(s) `work.adventurer.unfinished_delve.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked.respond.leave   [18 chars]
    en  I'll let you pack.
    >>  ............................................
    pt  Vou deixar você arrumar as coisas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you pack."
       spoken on: conversations.scene.work.adventurer.unfinished_delve.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave; conversations.topic.work.adventurer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.adventurer.unfinished_delve.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.adventurer.unfinished_delve.succeeded` — e.g. "I got past %2$s. It took forty minutes and eleven torches and it was, in the end, a corridor."


```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.adventurer.unfinished_delve.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.respond   [14 chars]
    en  You went back.
    >>  ............................................
    pt  Você voltou lá.
    >>  ............................................
```


### Button `ask_worth_it` — "Was it worth the trouble?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.unfinished_delve.succeeded` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.unfinished_delve.succeeded.ask_worth_it` — accepted phrasings: "was it worth the trouble"; "was it worth the trouble"; "did it repay the effort"
  - the message must contain one of: `worth`, `repay`, `effort`
  - scored words: `worth`(1.8), `repay`(1.8), `effort`(1.8), `trouble`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.respond.ask_worth_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.respond.ask_worth_it   [25 chars]
    en  Was it worth the trouble?
    >>  ............................................
    pt  Valeu o trabalho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.ruin_found`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.adventurer.unfinished_delve"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.weighed
WHO    VILLAGER — what the player reads after pressing "Was it worth the trouble?"
       spoken on: conversations.scene.work.adventurer.unfinished_delve.succeeded.respond, button `ask_worth_it`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.succeeded.weighed`: the villager explains. Subject `work.adventurer.ruin_found`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.weighed/1   [122 chars]
    en  Not in trade goods. In the other currency, yes: I no longer wonder, and I had been wondering every night since the spring.
    >>  ............................................
    pt  Em mercadoria, não. Na outra moeda, sim: parei de me perguntar, e eu vinha me perguntando toda noite desde a primavera.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.weighed/2   [90 chars]
    en  It paid for the torches and about a third of my nerve. I count that as a profit, narrowly.
    >>  ............................................
    pt  Pagou as tochas e cerca de um terço da minha coragem. Conto isso como lucro, por pouco.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.weighed/3   [104 chars]
    en  Somebody had been there before me and left it tidy. I did not expect to be moved by tidiness, and I was.
    >>  ............................................
    pt  Alguém esteve lá antes de mim e deixou tudo arrumado. Eu não esperava me emocionar com arrumação, e me emocionei.
    >>  ............................................
```


### Button `congratulate` — "You finished it. That counts."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.adventurer.unfinished_delve.succeeded` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.adventurer.unfinished_delve.succeeded.congratulate` — accepted phrasings: "you finished it that counts"; "you finished what you started"; "that counts for something"
  - the message must contain one of: `finished`, `started`, `counts`
  - scored words: `finished`(1.8), `started`(1.8), `counts`(1.8), `something`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.respond.congratulate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.respond.congratulate   [29 chars]
    en  You finished it. That counts.
    >>  ............................................
    pt  Você terminou. Isso conta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.adventurer.ruin_found`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.adventurer.unfinished_delve"}
- Then opens: `conversations.scene.work.adventurer.followup`
- …where the player's next choices will be: "What's the hardest part of coming home?" | "I'll leave you to your packing."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You finished it. That counts."
       spoken on: conversations.scene.work.adventurer.unfinished_delve.succeeded.respond, button `congratulate`
       leaves the player on: conversations.scene.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.succeeded.acknowledged`: the villager accepts. Subject `work.adventurer.ruin_found`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.acknowledged/1   [96 chars]
    en  It does. I have finished about half the things I have started, and this one is in the good half.
    >>  ............................................
    pt  Conta. Terminei mais ou menos metade do que comecei, e essa está na metade boa.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.acknowledged/2   [96 chars]
    en  Thank you. The finishing is the part nobody watches, and it is the only part that was ever hard.
    >>  ............................................
    pt  Obrigada. Terminar é a parte que ninguém assiste, e é a única que sempre foi difícil.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.acknowledged/3   [141 chars]
    en  I wrote it down when I got back, which I have started doing, because otherwise a finished thing feels the same as an abandoned one by winter.
    >>  ............................................
    pt  Anotei quando voltei, coisa que passei a fazer, porque senão até o inverno uma coisa terminada parece igual a uma abandonada.
    >>  ............................................
```


### Button `leave` — "I'll let you pack."

*stance family `exit` · tone `plain` · answers the beat(s) `work.adventurer.unfinished_delve.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.adventurer.unfinished_delve.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded.respond.leave   [18 chars]
    en  I'll let you pack.
    >>  ............................................
    pt  Vou deixar você arrumar as coisas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you pack."
       spoken on: conversations.scene.work.adventurer.unfinished_delve.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.topic.work.adventurer.craft.respond / leave; conversations.topic.work.adventurer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.adventurer.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.adventurer.craft` — e.g. "Nobody taught me anything. I learned by being wrong somewhere I couldn't afford to be wrong."


```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.adventurer.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.adventurer.craft.respond   [27 chars]
    en  That's the whole education.
    >>  ............................................
    pt  É toda a educação.
    >>  ............................................
```


### Button `ask_turn` — "How do you know when to turn round?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.craft` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.craft.ask_turn` — accepted phrasings: "how do you know when to turn round"
  - the message must contain one of: `turn`, `round`, `retreat`
  - scored words: `turn`(1.5), `round`(1.0), `retreat`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.craft.respond.ask_turn
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.craft.respond.ask_turn   [35 chars]
    en  How do you know when to turn round?
    >>  ............................................
    pt  Como você sabe a hora de voltar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.craft.ask_turn`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.craft.ask_turn
WHO    VILLAGER — what the player reads after pressing "How do you know when to turn round?"
       spoken on: conversations.topic.work.adventurer.craft.respond, button `ask_turn`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.craft.ask_turn`: the villager explains. Subject `work.adventurer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.craft.ask_turn/1   [88 chars]
    en  When the reason you came stops being bigger than the reason to go home. It's arithmetic.
    >>  ............................................
    pt  Quando o motivo de ter vindo deixa de ser maior que o de voltar pra casa. É aritmética.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.craft.ask_turn/2   [90 chars]
    en  Two hours before you want to. Everyone learns that late, %1$s, and some learn it too late.
    >>  ............................................
    pt  Duas horas antes de você querer. Todo mundo aprende tarde, %1$s, e alguns tarde demais.
    >>  ............................................
```


### Button `admire` — "Being wrong somewhere costly is a hard school."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.adventurer.craft` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.craft.admire` — accepted phrasings: "being wrong somewhere costly is a hard school"
  - the message must contain one of: `school`, `costly`
  - scored words: `school`(1.5), `costly`(1.2), `hard`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.craft.respond.admire   [46 chars]
    en  Being wrong somewhere costly is a hard school.
    >>  ............................................
    pt  Errar num lugar caro é uma escola dura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.adventurer.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.adventurer.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.craft.admire
WHO    VILLAGER — what the player reads after pressing "Being wrong somewhere costly is a hard school."
       spoken on: conversations.topic.work.adventurer.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.craft.admire`: the villager accepts. Subject `work.adventurer.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.craft.admire/1   [84 chars]
    en  It's the only school there is for this. There's no guild and there's no examination.
    >>  ............................................
    pt  É a única escola que existe pra isso. Não tem guilda nem exame.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.craft.admire/2   [70 chars]
    en  It's a school with no second year for a good many of the pupils, %1$s.
    >>  ............................................
    pt  É uma escola sem segundo ano pra boa parte dos alunos, %1$s.
    >>  ............................................
```


### Button `ask_wrong` — "What were you wrong about?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.craft` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.craft.ask_wrong` — accepted phrasings: "what were you wrong about"
  - the message must contain one of: `wrong`, `mistake`
  - scored words: `wrong`(1.5), `mistake`(1.2), `about`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.craft.respond.ask_wrong
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.craft.respond.ask_wrong   [26 chars]
    en  What were you wrong about?
    >>  ............................................
    pt  Sobre o que você errou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.craft.ask_wrong`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.craft.ask_wrong
WHO    VILLAGER — what the player reads after pressing "What were you wrong about?"
       spoken on: conversations.topic.work.adventurer.craft.respond, button `ask_wrong`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.craft.ask_wrong`: the villager explains. Subject `work.adventurer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.craft.ask_wrong/1   [92 chars]
    en  The depth of a river and the temper of a man. Both in the same fortnight, when I was twenty.
    >>  ............................................
    pt  A profundidade de um rio e o temperamento de um homem. Na mesma quinzena, aos vinte.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.craft.ask_wrong/2   [77 chars]
    en  How long two people can be angry. I misjudged that by about nine years, %1$s.
    >>  ............................................
    pt  Quanto tempo duas pessoas ficam bravas. Errei isso por uns nove anos, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you rest up."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.adventurer.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.craft.respond.leave   [21 chars]
    en  I'll let you rest up.
    >>  ............................................
    pt  Vou deixar você descansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you rest up."
       spoken on: conversations.topic.work.adventurer.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.adventurer.followup`

**Reached from 20 route(s):** `conversations.scene.work.adventurer.followup` / `ask_more`; `conversations.topic.work.adventurer.craft.respond` / `ask_turn`; `conversations.topic.work.adventurer.craft.respond` / `admire`; `conversations.topic.work.adventurer.craft.respond` / `ask_wrong`; `conversations.topic.work.adventurer.future.respond` / `ask_valley`; `conversations.topic.work.adventurer.future.respond` / `encourage`; `conversations.topic.work.adventurer.future.respond` / `ask_six`; `conversations.topic.work.adventurer.respond` / `ask_hard`; `conversations.topic.work.adventurer.respond` / `value`; `conversations.topic.work.adventurer.respond` / `challenge`; `conversations.topic.work.adventurer.respond` / `challenge`; `conversations.topic.work.adventurer.risk.respond` / `ask_fight` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.adventurer.challenge.landed` — e.g. "Half. You've a good ear, %1$s. The other half is worse than I tell it."
- `conversations.work.prof.adventurer.challenge.stung` — e.g. "...The scar isn't invented. Would you like to see where the rest of it went?"
- `conversations.work.prof.adventurer.craft.admire` — e.g. "It's the only school there is for this. There's no guild and there's no examination."
- `conversations.work.prof.adventurer.craft.ask_turn` — e.g. "When the reason you came stops being bigger than the reason to go home. It's arithmetic."
- `conversations.work.prof.adventurer.craft.ask_wrong` — e.g. "The depth of a river and the temper of a man. Both in the same fortnight, when I was twenty."
- `conversations.work.prof.adventurer.future.ask_six` — e.g. "Six. And every one of them was a real road with a real reason, which is how it works."
- `conversations.work.prof.adventurer.future.ask_valley` — e.g. "Because it's perfect in the telling and it would only be a valley if I stood in it again."
- `conversations.work.prof.adventurer.future.encourage` — e.g. "...The road will be there. That's the sentence I have been refusing to say to myself."
- `conversations.work.prof.adventurer.hard` — e.g. "Supplies. Everyone imagines the monster and everyone dies of the empty bag."
- `conversations.work.prof.adventurer.risk.ask_fight` — e.g. "I won it. That's the part I revisit — I won it, and I've never once been glad about that."
- `conversations.work.prof.adventurer.risk.ask_home` — e.g. "It's the place I keep coming back to. I'm told that's the definition and I'm not convinced."
- `conversations.work.prof.adventurer.risk.sympathise` — e.g. "...It is, and it's a shameful one, because you can't be angry at people for getting on."
- `conversations.work.prof.adventurer.task.ask_strap` — e.g. "It's the best day I've had in a fortnight, which is exactly the problem with it."
- `conversations.work.prof.adventurer.task.ask_waiting` — e.g. "Whether a place I used to know is still standing. It's a short question with a long silence after it."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.adventurer.followup   [29 chars]
    en  That's the road, near enough.
    >>  ............................................
    pt  É a estrada, mais ou menos.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.adventurer.challenge.landed`, `work.adventurer.challenge.stung`, `work.adventurer.craft.admire`, `work.adventurer.craft.ask_turn`, `work.adventurer.craft.ask_wrong`, `work.adventurer.future.ask_six`, `work.adventurer.future.ask_valley`, `work.adventurer.future.encourage`, `work.adventurer.hard`, `work.adventurer.risk.ask_fight`, `work.adventurer.risk.ask_home`, `work.adventurer.risk.sympathise`, `work.adventurer.task.ask_strap`, `work.adventurer.task.ask_waiting`, `work.adventurer.task.offer_hands`, `work.adventurer.value`, `work.adventurer.village.ask_rarely`, `work.adventurer.village.ask_seed`, `work.adventurer.village.say_thanks` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.adventurer.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `useful`, `road`
  - scored words: `thought`(1.2), `useful`(1.2), `road`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.adventurer.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.adventurer.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.adventurer.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.adventurer.thanks`: the villager accepts. Subject `work.adventurer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.thanks/1   [55 chars]
    en  Most who ask want the monster. You asked about the bag.
    >>  ............................................
    pt  Quem pergunta quer o monstro. Você perguntou do saco.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.thanks/2   [74 chars]
    en  That's the useful half of it, %1$s. Nobody puts the useful half in a song.
    >>  ............................................
    pt  É a metade útil, %1$s. Ninguém põe a metade útil numa canção.
    >>  ............................................
```


### Button `ask_more` — "Where are you going next?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.challenge.landed`, `work.adventurer.challenge.stung`, `work.adventurer.craft.admire`, `work.adventurer.craft.ask_turn`, `work.adventurer.craft.ask_wrong`, `work.adventurer.future.ask_six`, `work.adventurer.future.ask_valley`, `work.adventurer.future.encourage`, `work.adventurer.hard`, `work.adventurer.risk.ask_fight`, `work.adventurer.risk.ask_home`, `work.adventurer.risk.sympathise`, `work.adventurer.task.ask_strap`, `work.adventurer.task.ask_waiting`, `work.adventurer.task.offer_hands`, `work.adventurer.value`, `work.adventurer.village.ask_rarely`, `work.adventurer.village.ask_seed`, `work.adventurer.village.say_thanks` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.adventurer.more` — accepted phrasings: "where are you going next"
  - the message must contain one of: `next`, `going`, `destination`
  - scored words: `next`(1.2), `going`(1.5), `destination`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.followup.ask_more   [25 chars]
    en  Where are you going next?
    >>  ............................................
    pt  Pra onde você vai agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.adventurer.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.more
WHO    VILLAGER — what the player reads after pressing "Where are you going next?"
       spoken on: conversations.topic.work.adventurer.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.adventurer.more`: the villager discloses. Subject `work.adventurer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.more/1   [76 chars]
    en  North, when the passes clear. There's a tower there nobody's drawn properly.
    >>  ............................................
    pt  Norte, quando as passagens abrirem. Tem uma torre lá que ninguém desenhou direito.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.more/2   [70 chars]
    en  I've not decided. That's the best part of the week — the not deciding.
    >>  ............................................
    pt  Não decidi. É a melhor parte da semana — o não decidir.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.adventurer.more/1
    en  North, if I go. I've said north for two springs now and stayed put for both of them.
    >>  ............................................
    pt  Norte, se eu for. Digo norte há duas primaveras e fiquei parado nas duas.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.adventurer.more/2
    en  East. There's somebody there I'd like to find still alive, and I'm frightened of the answer.
    >>  ............................................
    pt  Leste. Tem alguém lá que eu queria achar vivo, e eu tenho medo da resposta.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. There's no hurry — the tower has stood a long while already.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Sem pressa — a torre já está de pé faz tempo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.adventurer.more/2
    en  East, eventually. Roads keep. It'll be there when I've a reason to walk it.
    >>  ............................................
    pt  Leste, uma hora. Estradas se conservam. Vai estar lá quando eu tiver motivo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. There's a tower there nobody has drawn properly.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Tem uma torre lá que ninguém desenhou direito.
    >>  ............................................
  confident.dialogue.conversations.work.prof.adventurer.more/2
    en  East. I'm owed an answer by somebody on that road and I intend to collect it.
    >>  ............................................
    pt  Leste. Alguém naquela estrada me deve uma resposta e eu pretendo cobrar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. There's a tower there nobody has drawn properly.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Tem uma torre lá que ninguém desenhou direito.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.adventurer.more/2
    en  East. I'm owed an answer by somebody on that road and I intend to collect it.
    >>  ............................................
    pt  Leste. Alguém naquela estrada me deve uma resposta e eu pretendo cobrar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. You'd like it — there's an inn on the way that keeps a fire all night.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Você ia gostar — tem uma estalagem no caminho que mantém o fogo a noite toda.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.adventurer.more/2
    en  East. Somebody there owes me an answer, and I'd tell you what about if you came along.
    >>  ............................................
    pt  Leste. Alguém lá me deve uma resposta, e eu diria sobre o quê se você viesse junto.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. You'd like it — there's an inn on the way that keeps a fire all night.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Você ia gostar — tem uma estalagem no caminho que mantém o fogo a noite toda.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.adventurer.more/2
    en  East. Somebody there owes me an answer, and I'd tell you what about if you came along.
    >>  ............................................
    pt  Leste. Alguém lá me deve uma resposta, e eu diria sobre o quê se você viesse junto.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. You'd like it — there's an inn on the way that keeps a fire all night.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Você ia gostar — tem uma estalagem no caminho que mantém o fogo a noite toda.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.adventurer.more/2
    en  East. Somebody there owes me an answer, and I'd tell you what about if you came along.
    >>  ............................................
    pt  Leste. Alguém lá me deve uma resposta, e eu diria sobre o quê se você viesse junto.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.adventurer.more/1
    en  North, if I go. I've said north for two springs now and stayed put for both of them.
    >>  ............................................
    pt  Norte, se eu for. Digo norte há duas primaveras e fiquei parado nas duas.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.adventurer.more/2
    en  East. There's somebody there I'd like to find still alive, and I'm frightened of the answer.
    >>  ............................................
    pt  Leste. Tem alguém lá que eu queria achar vivo, e eu tenho medo da resposta.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. There's a tower there nobody has drawn properly.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Tem uma torre lá que ninguém desenhou direito.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.adventurer.more/2
    en  East. I'm owed an answer by somebody on that road and I intend to collect it.
    >>  ............................................
    pt  Leste. Alguém naquela estrada me deve uma resposta e eu pretendo cobrar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. There's a tower there nobody has drawn properly.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Tem uma torre lá que ninguém desenhou direito.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.adventurer.more/2
    en  East. I'm owed an answer by somebody on that road and I intend to collect it.
    >>  ............................................
    pt  Leste. Alguém naquela estrada me deve uma resposta e eu pretendo cobrar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.adventurer.more/1
    en  North. There's a tower nobody has drawn properly, and I'd like to be the one who does.
    >>  ............................................
    pt  Norte. Tem uma torre que ninguém desenhou direito, e eu queria ser quem desenha.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.adventurer.more/2
    en  East, and not far. Four days, and then a road I already know.
    >>  ............................................
    pt  Leste, e não longe. Quatro dias, e depois uma estrada que eu já conheço.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. There's no hurry — the tower has stood a long while already.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Sem pressa — a torre já está de pé faz tempo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.adventurer.more/2
    en  East, eventually. Roads keep. It'll be there when I've a reason to walk it.
    >>  ............................................
    pt  Leste, uma hora. Estradas se conservam. Vai estar lá quando eu tiver motivo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.adventurer.more/1
    en  North. There's a tower nobody has drawn properly, and I'd like to be the one who does.
    >>  ............................................
    pt  Norte. Tem uma torre que ninguém desenhou direito, e eu queria ser quem desenha.
    >>  ............................................
  odd.dialogue.conversations.work.prof.adventurer.more/2
    en  East, and not far. Four days, and then a road I already know.
    >>  ............................................
    pt  Leste, e não longe. Quatro dias, e depois uma estrada que eu já conheço.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. There's no hurry — the tower has stood a long while already.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Sem pressa — a torre já está de pé faz tempo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.adventurer.more/2
    en  East, eventually. Roads keep. It'll be there when I've a reason to walk it.
    >>  ............................................
    pt  Leste, uma hora. Estradas se conservam. Vai estar lá quando eu tiver motivo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.adventurer.more/1
    en  North! There's a tower up there nobody has drawn properly and I intend to be rude about it.
    >>  ............................................
    pt  Norte! Tem uma torre lá que ninguém desenhou direito e eu pretendo ser grosseiro sobre isso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.adventurer.more/2
    en  East, probably. Somewhere with a road I haven't ruined my boots on yet.
    >>  ............................................
    pt  Leste, provavelmente. Algum lugar com uma estrada em que eu ainda não estraguei as botas.
    >>  ............................................
  playful.dialogue.conversations.work.prof.adventurer.more/1
    en  North! There's a tower up there nobody has drawn properly and I intend to be rude about it.
    >>  ............................................
    pt  Norte! Tem uma torre lá que ninguém desenhou direito e eu pretendo ser grosseiro sobre isso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.adventurer.more/2
    en  East, probably. Somewhere with a road I haven't ruined my boots on yet.
    >>  ............................................
    pt  Leste, provavelmente. Algum lugar com uma estrada em que eu ainda não estraguei as botas.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.adventurer.more/1
    en  North, when the passes clear. There's no hurry — the tower has stood a long while already.
    >>  ............................................
    pt  Norte, quando os passos abrirem. Sem pressa — a torre já está de pé faz tempo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.adventurer.more/2
    en  East, eventually. Roads keep. It'll be there when I've a reason to walk it.
    >>  ............................................
    pt  Leste, uma hora. Estradas se conservam. Vai estar lá quando eu tiver motivo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.adventurer.more/1
    en  North, if I go. I've said north for two springs now and stayed put for both of them.
    >>  ............................................
    pt  Norte, se eu for. Digo norte há duas primaveras e fiquei parado nas duas.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.adventurer.more/2
    en  East. There's somebody there I'd like to find still alive, and I'm frightened of the answer.
    >>  ............................................
    pt  Leste. Tem alguém lá que eu queria achar vivo, e eu tenho medo da resposta.
    >>  ............................................
  shy.dialogue.conversations.work.prof.adventurer.more/1
    en  North. There's a tower nobody has drawn properly, and I'd like to be the one who does.
    >>  ............................................
    pt  Norte. Tem uma torre que ninguém desenhou direito, e eu queria ser quem desenha.
    >>  ............................................
  shy.dialogue.conversations.work.prof.adventurer.more/2
    en  East, and not far. Four days, and then a road I already know.
    >>  ............................................
    pt  Leste, e não longe. Quatro dias, e depois uma estrada que eu já conheço.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.adventurer.more/1
    en  North! There's a tower up there nobody has drawn properly and I intend to be rude about it.
    >>  ............................................
    pt  Norte! Tem uma torre lá que ninguém desenhou direito e eu pretendo ser grosseiro sobre isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.adventurer.more/2
    en  East, probably. Somewhere with a road I haven't ruined my boots on yet.
    >>  ............................................
    pt  Leste, provavelmente. Algum lugar com uma estrada em que eu ainda não estraguei as botas.
    >>  ............................................
  witty.dialogue.conversations.work.prof.adventurer.more/1
    en  North! There's a tower up there nobody has drawn properly and I intend to be rude about it.
    >>  ............................................
    pt  Norte! Tem uma torre lá que ninguém desenhou direito e eu pretendo ser grosseiro sobre isso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.adventurer.more/2
    en  East, probably. Somewhere with a road I haven't ruined my boots on yet.
    >>  ............................................
    pt  Leste, provavelmente. Algum lugar com uma estrada em que eu ainda não estraguei as botas.
    >>  ............................................
```

</details>


### Button `leave` — "Safe roads."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.adventurer.challenge.landed`, `work.adventurer.challenge.stung`, `work.adventurer.craft.admire`, `work.adventurer.craft.ask_turn`, `work.adventurer.craft.ask_wrong`, `work.adventurer.future.ask_six`, `work.adventurer.future.ask_valley`, `work.adventurer.future.encourage`, `work.adventurer.hard`, `work.adventurer.risk.ask_fight`, `work.adventurer.risk.ask_home`, `work.adventurer.risk.sympathise`, `work.adventurer.task.ask_strap`, `work.adventurer.task.ask_waiting`, `work.adventurer.task.offer_hands`, `work.adventurer.value`, `work.adventurer.village.ask_rarely`, `work.adventurer.village.ask_seed`, `work.adventurer.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.followup.leave   [11 chars]
    en  Safe roads.
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
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "Safe roads."
       spoken on: conversations.topic.work.adventurer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.adventurer.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.adventurer.future` — e.g. "One more road, and then a roof. That's been the plan for six years and the road keeps being one more."


```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.adventurer.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.adventurer.future.respond   [30 chars]
    en  That's what's left of the map.
    >>  ............................................
    pt  É o que sobrou do mapa.
    >>  ............................................
```


### Button `ask_valley` — "Why not go back to the valley?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.future` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.future.ask_valley` — accepted phrasings: "why not go back to the valley"
  - the message must contain one of: `valley`, `west`
  - scored words: `valley`(1.5), `back`(0.8), `west`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.future.respond.ask_valley
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.future.respond.ask_valley   [30 chars]
    en  Why not go back to the valley?
    >>  ............................................
    pt  Por que não voltar ao vale?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.future.ask_valley`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.future.ask_valley
WHO    VILLAGER — what the player reads after pressing "Why not go back to the valley?"
       spoken on: conversations.topic.work.adventurer.future.respond, button `ask_valley`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.future.ask_valley`: the villager explains. Subject `work.adventurer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.future.ask_valley/1   [89 chars]
    en  Because it's perfect in the telling and it would only be a valley if I stood in it again.
    >>  ............................................
    pt  Porque é perfeito na história e seria só um vale se eu pisasse nele de novo.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.future.ask_valley/2   [89 chars]
    en  Because somebody there asked me to stay and I said no, %1$s, and that was nine years ago.
    >>  ............................................
    pt  Porque alguém lá me pediu pra ficar e eu disse não, %1$s, e faz nove anos.
    >>  ............................................
```


### Button `encourage` — "Take the roof first. The road keeps."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.adventurer.future` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.future.encourage` — accepted phrasings: "take the roof first. the road keeps"
  - the message must contain one of: `roof`, `first`, `settle`
  - scored words: `roof`(1.5), `first`(1.0), `settle`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.future.respond.encourage   [36 chars]
    en  Take the roof first. The road keeps.
    >>  ............................................
    pt  Fique com o telhado primeiro. A estrada espera.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.adventurer.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.adventurer.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.future.encourage
WHO    VILLAGER — what the player reads after pressing "Take the roof first. The road keeps."
       spoken on: conversations.topic.work.adventurer.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.future.encourage`: the villager accepts. Subject `work.adventurer.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.future.encourage/1   [85 chars]
    en  ...The road will be there. That's the sentence I have been refusing to say to myself.
    >>  ............................................
    pt  ...A estrada vai estar lá. É a frase que eu venho me recusando a dizer.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.future.encourage/2   [71 chars]
    en  Roof first. Nobody has ordered them that way round for me before, %1$s.
    >>  ............................................
    pt  Telhado primeiro. Ninguém tinha posto nessa ordem pra mim antes, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. I've been afraid to say that in case it isn't.
    >>  ............................................
    pt  ...A estrada vai estar lá. Tive medo de dizer isso caso não esteja.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. It sounds so small said aloud and it isn't small to me.
    >>  ............................................
    pt  Telhado primeiro. Soa tão pequeno em voz alta e pra mim não é pequeno.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. Roads outlast everyone who walks them; I know that.
    >>  ............................................
    pt  ...A estrada vai estar lá. Estradas duram mais que quem anda nelas; eu sei disso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. Six years of the other order taught me nothing at all.
    >>  ............................................
    pt  Telhado primeiro. Seis anos na ordem contrária não me ensinaram nada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. That's the sentence I've been refusing to say.
    >>  ............................................
    pt  ...A estrada vai estar lá. É a frase que eu venho me recusando a dizer.
    >>  ............................................
  confident.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. Nobody has ordered them that way round for me before.
    >>  ............................................
    pt  Telhado primeiro. Ninguém tinha posto nessa ordem pra mim antes.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. That's the sentence I've been refusing to say.
    >>  ............................................
    pt  ...A estrada vai estar lá. É a frase que eu venho me recusando a dizer.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. Nobody has ordered them that way round for me before.
    >>  ............................................
    pt  Telhado primeiro. Ninguém tinha posto nessa ordem pra mim antes.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there, %1$s. I've been refusing to say that to myself for years.
    >>  ............................................
    pt  ...A estrada vai estar lá, %1$s. Faz anos que eu me recuso a dizer isso.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. You're the first to put them that way round for me.
    >>  ............................................
    pt  Telhado primeiro. Você é o primeiro a pôr nessa ordem pra mim.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there, %1$s. I've been refusing to say that to myself for years.
    >>  ............................................
    pt  ...A estrada vai estar lá, %1$s. Faz anos que eu me recuso a dizer isso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. You're the first to put them that way round for me.
    >>  ............................................
    pt  Telhado primeiro. Você é o primeiro a pôr nessa ordem pra mim.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there, %1$s. I've been refusing to say that to myself for years.
    >>  ............................................
    pt  ...A estrada vai estar lá, %1$s. Faz anos que eu me recuso a dizer isso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. You're the first to put them that way round for me.
    >>  ............................................
    pt  Telhado primeiro. Você é o primeiro a pôr nessa ordem pra mim.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. I've been afraid to say that in case it isn't.
    >>  ............................................
    pt  ...A estrada vai estar lá. Tive medo de dizer isso caso não esteja.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. It sounds so small said aloud and it isn't small to me.
    >>  ............................................
    pt  Telhado primeiro. Soa tão pequeno em voz alta e pra mim não é pequeno.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. That's the sentence I've been refusing to say.
    >>  ............................................
    pt  ...A estrada vai estar lá. É a frase que eu venho me recusando a dizer.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. Nobody has ordered them that way round for me before.
    >>  ............................................
    pt  Telhado primeiro. Ninguém tinha posto nessa ordem pra mim antes.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. That's the sentence I've been refusing to say.
    >>  ............................................
    pt  ...A estrada vai estar lá. É a frase que eu venho me recusando a dizer.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. Nobody has ordered them that way round for me before.
    >>  ............................................
    pt  Telhado primeiro. Ninguém tinha posto nessa ordem pra mim antes.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there.
    >>  ............................................
    pt  ...A estrada vai estar lá.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. That's a new order.
    >>  ............................................
    pt  Telhado primeiro. É uma ordem nova.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. Roads outlast everyone who walks them; I know that.
    >>  ............................................
    pt  ...A estrada vai estar lá. Estradas duram mais que quem anda nelas; eu sei disso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. Six years of the other order taught me nothing at all.
    >>  ............................................
    pt  Telhado primeiro. Seis anos na ordem contrária não me ensinaram nada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there.
    >>  ............................................
    pt  ...A estrada vai estar lá.
    >>  ............................................
  odd.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. That's a new order.
    >>  ............................................
    pt  Telhado primeiro. É uma ordem nova.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. Roads outlast everyone who walks them; I know that.
    >>  ............................................
    pt  ...A estrada vai estar lá. Estradas duram mais que quem anda nelas; eu sei disso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. Six years of the other order taught me nothing at all.
    >>  ............................................
    pt  Telhado primeiro. Seis anos na ordem contrária não me ensinaram nada.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there! Obviously it will. Why has nobody said that to me?
    >>  ............................................
    pt  ...A estrada vai estar lá! Claro que vai. Por que ninguém me disse isso?
    >>  ............................................
  peppy.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first — ha. Nobody has ordered them that way round for me, not once.
    >>  ............................................
    pt  Telhado primeiro — ha. Ninguém tinha posto nessa ordem pra mim, nem uma vez.
    >>  ............................................
  playful.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there! Obviously it will. Why has nobody said that to me?
    >>  ............................................
    pt  ...A estrada vai estar lá! Claro que vai. Por que ninguém me disse isso?
    >>  ............................................
  playful.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first — ha. Nobody has ordered them that way round for me, not once.
    >>  ............................................
    pt  Telhado primeiro — ha. Ninguém tinha posto nessa ordem pra mim, nem uma vez.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. Roads outlast everyone who walks them; I know that.
    >>  ............................................
    pt  ...A estrada vai estar lá. Estradas duram mais que quem anda nelas; eu sei disso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. Six years of the other order taught me nothing at all.
    >>  ............................................
    pt  Telhado primeiro. Seis anos na ordem contrária não me ensinaram nada.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there. I've been afraid to say that in case it isn't.
    >>  ............................................
    pt  ...A estrada vai estar lá. Tive medo de dizer isso caso não esteja.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. It sounds so small said aloud and it isn't small to me.
    >>  ............................................
    pt  Telhado primeiro. Soa tão pequeno em voz alta e pra mim não é pequeno.
    >>  ............................................
  shy.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there.
    >>  ............................................
    pt  ...A estrada vai estar lá.
    >>  ............................................
  shy.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first. That's a new order.
    >>  ............................................
    pt  Telhado primeiro. É uma ordem nova.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there! Obviously it will. Why has nobody said that to me?
    >>  ............................................
    pt  ...A estrada vai estar lá! Claro que vai. Por que ninguém me disse isso?
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first — ha. Nobody has ordered them that way round for me, not once.
    >>  ............................................
    pt  Telhado primeiro — ha. Ninguém tinha posto nessa ordem pra mim, nem uma vez.
    >>  ............................................
  witty.dialogue.conversations.work.prof.adventurer.future.encourage/1
    en  ...The road will be there! Obviously it will. Why has nobody said that to me?
    >>  ............................................
    pt  ...A estrada vai estar lá! Claro que vai. Por que ninguém me disse isso?
    >>  ............................................
  witty.dialogue.conversations.work.prof.adventurer.future.encourage/2
    en  Roof first — ha. Nobody has ordered them that way round for me, not once.
    >>  ............................................
    pt  Telhado primeiro — ha. Ninguém tinha posto nessa ordem pra mim, nem uma vez.
    >>  ............................................
```

</details>


### Button `ask_six` — "Six years of one more road?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.future` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.future.ask_six` — accepted phrasings: "six years of one more road"
  - the message must contain one of: `six`, `delayed`
  - scored words: `six`(1.5), `years`(0.8), `delayed`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.future.respond.ask_six
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.future.respond.ask_six   [27 chars]
    en  Six years of one more road?
    >>  ............................................
    pt  Seis anos de mais uma estrada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.future.ask_six`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.future.ask_six
WHO    VILLAGER — what the player reads after pressing "Six years of one more road?"
       spoken on: conversations.topic.work.adventurer.future.respond, button `ask_six`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.future.ask_six`: the villager explains. Subject `work.adventurer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.future.ask_six/1   [85 chars]
    en  Six. And every one of them was a real road with a real reason, which is how it works.
    >>  ............................................
    pt  Seis. E cada uma foi estrada de verdade com motivo de verdade, é assim que funciona.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.future.ask_six/2   [80 chars]
    en  I count them in winters, %1$s. Six winters is a different number than six years.
    >>  ............................................
    pt  Eu conto em invernos, %1$s. Seis invernos é um número diferente de seis anos.
    >>  ............................................
```


### Button `leave` — "I'll let you rest up."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.adventurer.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.future.respond.leave   [21 chars]
    en  I'll let you rest up.
    >>  ............................................
    pt  Vou deixar você descansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you rest up."
       spoken on: conversations.topic.work.adventurer.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.adventurer.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.adventurer` — e.g. "The road's my trade. I rest here between chapters — villages are where adventurers footnote themselves."


```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.adventurer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.adventurer.respond   [27 chars]
    en  That's the road, footnoted.
    >>  ............................................
    pt  É a estrada, com notas de rodapé.
    >>  ............................................
```


### Button `ask_hard` — "What actually goes wrong out there?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.identity` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.hard` — accepted phrasings: "what actually goes wrong out there"
  - the message must contain one of: `wrong`, `supplies`, `danger`
  - scored words: `wrong`(1.2), `supplies`(1.5), `danger`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.respond.ask_hard   [35 chars]
    en  What actually goes wrong out there?
    >>  ............................................
    pt  O que realmente dá errado lá fora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.adventurer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.hard
WHO    VILLAGER — what the player reads after pressing "What actually goes wrong out there?"
       spoken on: conversations.topic.work.adventurer.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.hard`: the villager explains. Subject `work.adventurer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.followup / ask_more
```

> Written out in full under **`conversations.scene.work.adventurer.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "The stories you bring back are worth something here."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.adventurer.identity` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.value` — accepted phrasings: "the stories you bring back are worth something here"
  - the message must contain one of: `stories`, `bring`, `tales`
  - scored words: `stories`(1.5), `bring`(1.0), `tales`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.respond.value   [52 chars]
    en  The stories you bring back are worth something here.
    >>  ............................................
    pt  As histórias que você traz valem alguma coisa aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.adventurer.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.adventurer.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.value
WHO    VILLAGER — what the player reads after pressing "The stories you bring back are worth something here."
       spoken on: conversations.topic.work.adventurer.respond, button `value`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.value`: the villager accepts. Subject `work.adventurer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.value/1   [76 chars]
    en  They are. Half this village has never seen a hill they didn't grow up under.
    >>  ............................................
    pt  Valem. Metade deste vilarejo nunca viu uma colina sob a qual não tenha crescido.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.value/2   [59 chars]
    en  Aye. And I trade them at a fair rate: one supper, one ruin.
    >>  ............................................
    pt  É. E eu troco a preço justo: uma janta, uma ruína.
    >>  ............................................
```


### Button `challenge` — "Half of that's invented."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.adventurer.identity` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.challenge` — accepted phrasings: "half of that's invented"
  - the message must contain one of: `invented`, `exaggerating`
  - scored words: `invented`(1.5), `exaggerating`(1.5), `half`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.respond.challenge   [24 chars]
    en  Half of that's invented.
    >>  ............................................
    pt  Metade disso é invenção.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.adventurer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.adventurer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.challenge.landed
WHO    VILLAGER — what the player reads after pressing "Half of that's invented."
       spoken on: conversations.topic.work.adventurer.respond, button `challenge`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.challenge.landed`: the villager resists. Subject `work.adventurer.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.challenge.landed/1   [70 chars]
    en  Half. You've a good ear, %1$s. The other half is worse than I tell it.
    >>  ............................................
    pt  Metade. Você tem bom ouvido, %1$s. A outra metade é pior do que eu conto.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.challenge.landed/2   [77 chars]
    en  Some of it. I'd rather a good telling than an accurate one, and so would you.
    >>  ............................................
    pt  Parte é. Prefiro uma boa narração a uma precisa, e você também.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.adventurer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.adventurer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.challenge.stung
WHO    VILLAGER — what the player reads after pressing "Half of that's invented."
       spoken on: conversations.topic.work.adventurer.respond, button `challenge`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.challenge.stung`: the villager resists. Subject `work.adventurer.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.challenge.stung/1   [76 chars]
    en  ...The scar isn't invented. Would you like to see where the rest of it went?
    >>  ............................................
    pt  ...A cicatriz não é invenção. Quer ver pra onde foi o resto?
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.challenge.stung/2   [54 chars]
    en  Invented. Right. Come along next time and check, %1$s.
    >>  ............................................
    pt  Invenção. Certo. Venha junto na próxima e confira, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you rest up."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.adventurer.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.respond.leave   [21 chars]
    en  I'll let you rest up.
    >>  ............................................
    pt  Vou deixar você descansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you rest up."
       spoken on: conversations.topic.work.adventurer.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.adventurer.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.adventurer.risk` — e.g. "The danger isn't the road. It's coming back to a place that got on perfectly well without me."


```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.adventurer.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.adventurer.risk.respond   [21 chars]
    en  That's what it costs.
    >>  ............................................
    pt  É o que custa.
    >>  ............................................
```


### Button `ask_fight` — "What happened in the one you didn't choose?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.risk` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.risk.ask_fight` — accepted phrasings: "what happened in the one you didn't choose"
  - the message must contain one of: `fight`, `choose`
  - scored words: `fight`(1.5), `choose`(1.2), `happened`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.risk.respond.ask_fight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.risk.respond.ask_fight   [43 chars]
    en  What happened in the one you didn't choose?
    >>  ............................................
    pt  O que aconteceu na que você não escolheu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.risk.ask_fight`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.risk.ask_fight
WHO    VILLAGER — what the player reads after pressing "What happened in the one you didn't choose?"
       spoken on: conversations.topic.work.adventurer.risk.respond, button `ask_fight`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.risk.ask_fight`: the villager explains. Subject `work.adventurer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.risk.ask_fight/1   [89 chars]
    en  I won it. That's the part I revisit — I won it, and I've never once been glad about that.
    >>  ............................................
    pt  Eu ganhei. É essa a parte que eu revisito — eu ganhei, e nunca uma vez fiquei contente com isso.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.risk.ask_fight/2   [83 chars]
    en  It was over in a moment and it has taken eleven years, %1$s, and it isn't finished.
    >>  ............................................
    pt  Acabou num instante e já levou onze anos, %1$s, e não acabou.
    >>  ............................................
```


### Button `sympathise` — "Coming back to a place that managed without you is its own wound."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.adventurer.risk` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.risk.sympathise` — accepted phrasings: "coming back to a place that managed without you is its own wound"
  - the message must contain one of: `managed`, `without`, `wound`
  - scored words: `managed`(1.5), `without`(1.2), `wound`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.risk.respond.sympathise   [65 chars]
    en  Coming back to a place that managed without you is its own wound.
    >>  ............................................
    pt  Voltar a um lugar que se virou sem você é uma ferida própria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.adventurer.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.adventurer.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Coming back to a place that managed without you is its own wound."
       spoken on: conversations.topic.work.adventurer.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.risk.sympathise`: the villager accepts. Subject `work.adventurer.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.risk.sympathise/1   [87 chars]
    en  ...It is, and it's a shameful one, because you can't be angry at people for getting on.
    >>  ............................................
    pt  ...É, e é vergonhosa, porque você não pode ficar bravo com quem seguiu a vida.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.risk.sympathise/2   [67 chars]
    en  Nobody warns you about that one. They warn you about bandits, %1$s.
    >>  ............................................
    pt  Ninguém te avisa sobre essa. Avisam sobre bandidos, %1$s.
    >>  ............................................
```


### Button `ask_home` — "Is this place home now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.risk` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.risk.ask_home` — accepted phrasings: "is this place home now"
  - the message must contain one of: `home`, `settled`
  - scored words: `home`(1.5), `here`(0.6), `settled`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.risk.respond.ask_home
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.risk.respond.ask_home   [23 chars]
    en  Is this place home now?
    >>  ............................................
    pt  Este lugar é casa agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.risk.ask_home`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.risk.ask_home
WHO    VILLAGER — what the player reads after pressing "Is this place home now?"
       spoken on: conversations.topic.work.adventurer.risk.respond, button `ask_home`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.risk.ask_home`: the villager explains. Subject `work.adventurer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.risk.ask_home/1   [91 chars]
    en  It's the place I keep coming back to. I'm told that's the definition and I'm not convinced.
    >>  ............................................
    pt  É o lugar pra onde eu volto. Dizem que é a definição e eu não me convenci.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.risk.ask_home/2   [85 chars]
    en  Ask me in a year. That's not a deflection, %1$s, it's genuinely how long it takes me.
    >>  ............................................
    pt  Me pergunte daqui a um ano. Não é desconversa, %1$s, é genuinamente o tempo que eu levo.
    >>  ............................................
```


### Button `leave` — "I'll let you rest up."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.adventurer.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.risk.respond.leave   [21 chars]
    en  I'll let you rest up.
    >>  ............................................
    pt  Vou deixar você descansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you rest up."
       spoken on: conversations.topic.work.adventurer.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.adventurer.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.adventurer.task` — e.g. "Mending a strap and pretending that's a whole day's occupation. It isn't, and I know it isn't."


```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.adventurer.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.adventurer.task.respond   [26 chars]
    en  That's the shape of today.
    >>  ............................................
    pt  É o formato de hoje.
    >>  ............................................
```


### Button `ask_waiting` — "What answer are you waiting on?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.task` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.task.ask_waiting` — accepted phrasings: "what answer are you waiting on"
  - the message must contain one of: `answer`, `waiting`, `east`
  - scored words: `answer`(1.5), `waiting`(1.2), `east`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.task.respond.ask_waiting
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.task.respond.ask_waiting   [31 chars]
    en  What answer are you waiting on?
    >>  ............................................
    pt  Que resposta você espera?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.task.ask_waiting`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.task.ask_waiting
WHO    VILLAGER — what the player reads after pressing "What answer are you waiting on?"
       spoken on: conversations.topic.work.adventurer.task.respond, button `ask_waiting`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.task.ask_waiting`: the villager explains. Subject `work.adventurer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.task.ask_waiting/1   [101 chars]
    en  Whether a place I used to know is still standing. It's a short question with a long silence after it.
    >>  ............................................
    pt  Se um lugar que eu conhecia ainda está de pé. É uma pergunta curta com um silêncio longo depois.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.task.ask_waiting/2   [77 chars]
    en  Money, officially. In truth it's whether anyone from that year is left, %1$s.
    >>  ............................................
    pt  Dinheiro, oficialmente. Na verdade é se sobrou alguém daquele ano, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I can ask along the east road for you."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.adventurer.task` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.task.offer_hands` — accepted phrasings: "i can ask along the east road for you"
  - the message must contain one of: `road`, `ask`, `errand`
  - scored words: `road`(1.2), `ask`(1.0), `errand`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.task.respond.offer_hands   [38 chars]
    en  I can ask along the east road for you.
    >>  ............................................
    pt  Eu posso perguntar pela estrada leste por você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.adventurer.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.adventurer.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can ask along the east road for you."
       spoken on: conversations.topic.work.adventurer.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.task.offer_hands`: the villager accepts. Subject `work.adventurer.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.task.offer_hands/1   [85 chars]
    en  ...You could. Ask at the second inn, not the first. The first one talks to everybody.
    >>  ............................................
    pt  ...Podia. Pergunte na segunda estalagem, não na primeira. A primeira fala com todo mundo.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.task.offer_hands/2   [76 chars]
    en  Then don't use my name, %1$s. Describe me and see whose face does something.
    >>  ............................................
    pt  Então não use meu nome, %1$s. Me descreva e veja o rosto de quem reage.
    >>  ............................................
```


### Button `ask_strap` — "Is a day of mending straps so bad?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.task` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.task.ask_strap` — accepted phrasings: "is a day of mending straps so bad"
  - the message must contain one of: `strap`, `mending`, `idle`
  - scored words: `strap`(1.5), `mending`(1.2), `idle`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.task.respond.ask_strap
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.task.respond.ask_strap   [34 chars]
    en  Is a day of mending straps so bad?
    >>  ............................................
    pt  Um dia consertando correias é tão ruim assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.task.ask_strap`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.task.ask_strap
WHO    VILLAGER — what the player reads after pressing "Is a day of mending straps so bad?"
       spoken on: conversations.topic.work.adventurer.task.respond, button `ask_strap`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.task.ask_strap`: the villager explains. Subject `work.adventurer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.task.ask_strap/1   [80 chars]
    en  It's the best day I've had in a fortnight, which is exactly the problem with it.
    >>  ............................................
    pt  É o melhor dia que eu tive em quinze dias, e é exatamente esse o problema.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.task.ask_strap/2   [64 chars]
    en  It's fine. It's the fourth one this week that troubles me, %1$s.
    >>  ............................................
    pt  É bom. É o quarto desta semana que me incomoda, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you rest up."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.adventurer.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.task.respond.leave   [21 chars]
    en  I'll let you rest up.
    >>  ............................................
    pt  Vou deixar você descansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you rest up."
       spoken on: conversations.topic.work.adventurer.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.adventurer.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.adventurer.village` — e.g. "I brought the seed corn through in the bad year. Two people know that and one of them is me."


```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.adventurer.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.adventurer.village.respond   [22 chars]
    en  That's my place in it.
    >>  ............................................
    pt  É o meu lugar aqui.
    >>  ............................................
```


### Button `ask_seed` — "Who's the other person who knows?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.village` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.village.ask_seed` — accepted phrasings: "who's the other person who knows"
  - the message must contain one of: `knows`, `other`, `seed`
  - scored words: `knows`(1.2), `other`(1.0), `seed`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.village.respond.ask_seed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.village.respond.ask_seed   [33 chars]
    en  Who's the other person who knows?
    >>  ............................................
    pt  Quem é a outra pessoa que sabe?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.village.ask_seed`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.village.ask_seed
WHO    VILLAGER — what the player reads after pressing "Who's the other person who knows?"
       spoken on: conversations.topic.work.adventurer.village.respond, button `ask_seed`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.village.ask_seed`: the villager explains. Subject `work.adventurer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.village.ask_seed/1   [84 chars]
    en  The farmer. He's never said a word about it and he gives me the first of everything.
    >>  ............................................
    pt  O fazendeiro. Ele nunca disse uma palavra e me dá o primeiro de tudo.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.village.ask_seed/2   [91 chars]
    en  The cleric. She was the one who asked me to go, %1$s, and she has never mentioned it since.
    >>  ............................................
    pt  A clériga. Foi ela que me pediu pra ir, %1$s, e nunca mencionou depois.
    >>  ............................................
```


### Button `say_thanks` — "A spare key is what gets people through a locked door."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.adventurer.village` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.village.say_thanks` — accepted phrasings: "a spare key is what gets people through a locked door"
  - the message must contain one of: `key`, `locked`, `door`
  - scored words: `key`(1.5), `locked`(1.2), `door`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.village.respond.say_thanks   [54 chars]
    en  A spare key is what gets people through a locked door.
    >>  ............................................
    pt  Uma chave reserva é o que tira gente de uma porta trancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.adventurer.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.adventurer.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "A spare key is what gets people through a locked door."
       spoken on: conversations.topic.work.adventurer.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.village.say_thanks`: the villager accepts. Subject `work.adventurer.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.village.say_thanks/1   [74 chars]
    en  ...That's a kinder reading than mine and I'm going to sit with it a while.
    >>  ............................................
    pt  ...É uma leitura mais gentil que a minha e eu vou ficar com ela um tempo.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.village.say_thanks/2   [72 chars]
    en  Nobody's argued with the metaphor before. They just agree with it, %1$s.
    >>  ............................................
    pt  Ninguém tinha discutido a metáfora antes. Só concordam, %1$s.
    >>  ............................................
```


### Button `ask_rarely` — "Would you rather be used more often?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.adventurer.village` · offered only once the villager has actually said `work:adventurer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.adventurer.village.ask_rarely` — accepted phrasings: "would you rather be used more often"
  - the message must contain one of: `often`, `rather`, `needed`
  - scored words: `often`(1.5), `rather`(1.0), `needed`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.village.respond.ask_rarely
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.village.respond.ask_rarely   [36 chars]
    en  Would you rather be used more often?
    >>  ............................................
    pt  Você preferia ser usado mais vezes?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.adventurer.village.ask_rarely`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.adventurer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where are you going next?" | "Safe roads."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.village.ask_rarely
WHO    VILLAGER — what the player reads after pressing "Would you rather be used more often?"
       spoken on: conversations.topic.work.adventurer.village.respond, button `ask_rarely`
       leaves the player on: conversations.topic.work.adventurer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.village.ask_rarely`: the villager explains. Subject `work.adventurer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.adventurer.village.ask_rarely/1   [66 chars]
    en  Yes, and I know exactly how that sounds, and I'm saying it anyway.
    >>  ............................................
    pt  Sim, e eu sei exatamente como isso soa, e estou dizendo mesmo assim.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.village.ask_rarely/2   [79 chars]
    en  I'd rather be asked more often. Being used and being asked are different, %1$s.
    >>  ............................................
    pt  Eu preferia ser chamado mais vezes. Ser usado e ser chamado são coisas diferentes, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you rest up."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.adventurer.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.adventurer.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.adventurer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.adventurer.village.respond.leave   [21 chars]
    en  I'll let you rest up.
    >>  ............................................
    pt  Vou deixar você descansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you rest up."
       spoken on: conversations.topic.work.adventurer.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.left`: the villager accepts. Subject `work.adventurer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.adventurer.bad_route.active.respond / leave; conversations.scene.work.adventurer.bad_route.succeeded.respond / leave; conversations.scene.work.adventurer.followup / leave; conversations.scene.work.adventurer.souvenir.succeeded.respond / leave; conversations.scene.work.adventurer.unfinished_delve.active.respond / leave; conversations.scene.work.adventurer.unfinished_delve.blocked.respond / leave; conversations.scene.work.adventurer.unfinished_delve.succeeded.respond / leave; conversations.topic.work.adventurer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.adventurer.bad_route.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

