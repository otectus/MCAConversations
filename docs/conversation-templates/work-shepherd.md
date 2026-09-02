# Work talk with a shepherd

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.shepherd.broken_fence.blocked.respond`](#conversations-scene-work-shepherd-broken-fence-blocked-respond)
- [`conversations.scene.work.shepherd.broken_fence.succeeded.respond`](#conversations-scene-work-shepherd-broken-fence-succeeded-respond)
- [`conversations.scene.work.shepherd.followup`](#conversations-scene-work-shepherd-followup)
- [`conversations.scene.work.shepherd.hard_lambing.blocked.respond`](#conversations-scene-work-shepherd-hard-lambing-blocked-respond)
- [`conversations.scene.work.shepherd.hard_lambing.succeeded.respond`](#conversations-scene-work-shepherd-hard-lambing-succeeded-respond)
- [`conversations.scene.work.shepherd.old_dog.active.respond`](#conversations-scene-work-shepherd-old-dog-active-respond)
- [`conversations.scene.work.shepherd.old_dog.succeeded.respond`](#conversations-scene-work-shepherd-old-dog-succeeded-respond)
- [`conversations.topic.work.shepherd.craft.respond`](#conversations-topic-work-shepherd-craft-respond)
- [`conversations.topic.work.shepherd.followup`](#conversations-topic-work-shepherd-followup)
- [`conversations.topic.work.shepherd.future.respond`](#conversations-topic-work-shepherd-future-respond)
- [`conversations.topic.work.shepherd.respond`](#conversations-topic-work-shepherd-respond)
- [`conversations.topic.work.shepherd.risk.respond`](#conversations-topic-work-shepherd-risk-respond)
- [`conversations.topic.work.shepherd.task.respond`](#conversations-topic-work-shepherd-task-respond)
- [`conversations.topic.work.shepherd.village.respond`](#conversations-topic-work-shepherd-village-respond)

---

## `conversations.scene.work.shepherd.broken_fence.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shepherd.broken_fence.blocked` — e.g. "%2$s is down in three places and it is a two-person job, and there is one of me."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shepherd.broken_fence.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.respond   [10 chars]
    en  The fence.
    >>  ............................................
    pt  A cerca.
    >>  ............................................
```


### Button `offer_hands` — "I'll come up and hold the other end."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.shepherd.broken_fence.blocked` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.broken_fence.blocked.offer_hands` — accepted phrasings: "ill come up and hold the other end"; "i will come up and hold the other end"; "let me be the second pair of hands"
  - the message must contain one of: `hold`, `hands`, `end`
  - scored words: `hold`(1.8), `hands`(1.8), `end`(1.8), `ill`(0.8), `come`(0.8), `other`(0.8), `let`(0.8), `second`(0.8), `pair`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.blocked.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.broken_fence.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.respond.offer_hands   [36 chars]
    en  I'll come up and hold the other end.
    >>  ............................................
    pt  Eu subo e seguro a outra ponta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shepherd.fence.helped`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.shepherd.high_pasture`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shepherd.broken_fence"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll come up and hold the other end."
       spoken on: conversations.scene.work.shepherd.broken_fence.blocked.respond, button `offer_hands`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.broken_fence.blocked.accepted`: the villager accepts. Subject `work.shepherd.high_pasture`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.accepted/1   [96 chars]
    en  Then %2$s is finished in a morning. Bring gloves and expect to be cold and to be fed afterwards.
    >>  ............................................
    pt  Então %2$s fica pronta numa manhã. Traga luvas e espere passar frio e ser alimentada depois.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.accepted/2   [123 chars]
    en  You are the first person who has said a day rather than a maybe. I will hold you to it and I will not be gracious about it.
    >>  ............................................
    pt  Você é a primeira pessoa a dizer um dia em vez de um talvez. Vou cobrar e não vou ser delicada.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.accepted/3   [152 chars]
    en  Yes. Come at first light. It is a long walk up and the light on the top field at that hour is the one thing about this job I would recommend to anybody.
    >>  ............................................
    pt  Sim. Venha ao amanhecer. A subida é longa e a luz no campo alto àquela hora é a única coisa deste trabalho que eu recomendaria a qualquer um.
    >>  ............................................
```


### Button `ask_why_no_help` — "What keeps people off the hill?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.broken_fence.blocked` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.broken_fence.blocked.ask_why_no_help` — accepted phrasings: "what keeps people off the hill"; "what keeps people from coming up"; "the hill keeps people away"
  - the message must contain one of: `keeps`, `hill`, `up`
  - scored words: `keeps`(1.8), `hill`(1.8), `up`(1.8), `people`(0.8), `off`(0.8), `from`(0.8), `coming`(0.8), `away`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.blocked.respond.ask_why_no_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.broken_fence.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.respond.ask_why_no_help   [31 chars]
    en  What keeps people off the hill?
    >>  ............................................
    pt  O que afasta as pessoas da encosta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.high_pasture`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shepherd.broken_fence"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What keeps people off the hill?"
       spoken on: conversations.scene.work.shepherd.broken_fence.blocked.respond, button `ask_why_no_help`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.broken_fence.blocked.explained`: the villager explains. Subject `work.shepherd.high_pasture`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.explained/1   [137 chars]
    en  It is an hour's walk before the work starts. That is the whole reason and it is a perfectly good one, and it still leaves the fence down.
    >>  ............................................
    pt  É uma hora de caminhada antes de o trabalho começar. É o motivo inteiro e é perfeitamente bom, e ainda assim a cerca continua caída.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.explained/2   [113 chars]
    en  Out of sight. If the fence were on the green it would have been mended in a day by four people arguing about how.
    >>  ............................................
    pt  Fora de vista. Se a cerca ficasse no gramado, teria sido consertada num dia por quatro pessoas discutindo como.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.explained/3   [140 chars]
    en  I have not been down to help anybody either, in fairness. You cannot spend nine years on a hill and then be surprised that nobody climbs it.
    >>  ............................................
    pt  Eu também não desci para ajudar ninguém, para ser justa. Não dá para passar nove anos numa encosta e depois se surpreender que ninguém suba.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the flock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shepherd.broken_fence.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.broken_fence.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the flock.
    >>  ............................................
    pt  Vou deixar você voltar ao rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the flock."
       spoken on: conversations.scene.work.shepherd.broken_fence.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave; conversations.topic.work.shepherd.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.shepherd.leave/1   [40 chars]
    en  They'll be glad of the quiet. So will I.
    >>  ............................................
    pt  Elas vão gostar do silêncio. Eu também.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.leave/2   [42 chars]
    en  Mind where you step on the way down, %1$s.
    >>  ............................................
    pt  Cuidado onde pisa na descida, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.shepherd.broken_fence.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shepherd.broken_fence.succeeded` — e.g. "%2$s is up and straight. Two of us, one morning, and nine months of me putting it off."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shepherd.broken_fence.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.respond   [18 chars]
    en  The fence, mended.
    >>  ............................................
    pt  A cerca, consertada.
    >>  ............................................
```


### Button `note_asking_worked` — "Asking worked, then."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.shepherd.broken_fence.succeeded` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.broken_fence.succeeded.note_asking_worked` — accepted phrasings: "asking worked then"; "asking worked then"; "so asking for help worked"
  - the message must contain one of: `asking`, `worked`
  - scored words: `asking`(1.8), `worked`(1.8), `help`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.respond.note_asking_worked
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.broken_fence.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.respond.note_asking_worked   [20 chars]
    en  Asking worked, then.
    >>  ............................................
    pt  Então pedir funcionou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.shepherd.high_pasture`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.shepherd.broken_fence"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Asking worked, then."
       spoken on: conversations.scene.work.shepherd.broken_fence.succeeded.respond, button `note_asking_worked`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.broken_fence.succeeded.acknowledged`: the villager accepts. Subject `work.shepherd.high_pasture`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.acknowledged/1   [115 chars]
    en  Asking with a date attached worked. Asking in general had failed twice, and I had drawn the wrong lesson from that.
    >>  ............................................
    pt  Pedir com data marcada funcionou. Pedir em geral tinha falhado duas vezes, e eu tinha tirado a lição errada disso.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.acknowledged/2   [147 chars]
    en  It did. I have lived alone on a hill long enough to have decided that asking is a kind of losing, which is nonsense and took nine months to notice.
    >>  ............................................
    pt  Funcionou. Vivi sozinha numa encosta tempo suficiente para decidir que pedir é uma forma de perder, o que é bobagem e levou nove meses para eu notar.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.acknowledged/3   [108 chars]
    en  Thank you. I will do it sooner next time. That is the actual promise, and it is a harder one than the fence.
    >>  ............................................
    pt  Obrigada. Vou fazer mais cedo da próxima vez. Essa é a promessa de verdade, e é mais difícil que a cerca.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the flock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shepherd.broken_fence.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.broken_fence.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the flock.
    >>  ............................................
    pt  Vou deixar você voltar ao rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the flock."
       spoken on: conversations.scene.work.shepherd.broken_fence.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave; conversations.topic.work.shepherd.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shepherd.followup`

**Reached from 11 route(s):** `conversations.scene.work.shepherd.broken_fence.blocked.respond` / `offer_hands`; `conversations.scene.work.shepherd.broken_fence.blocked.respond` / `ask_why_no_help`; `conversations.scene.work.shepherd.broken_fence.succeeded.respond` / `note_asking_worked`; `conversations.scene.work.shepherd.hard_lambing.blocked.respond` / `ask_what_goes_wrong`; `conversations.scene.work.shepherd.hard_lambing.blocked.respond` / `offer_wool`; `conversations.scene.work.shepherd.hard_lambing.blocked.respond` / `advise_asking_for_hands`; `conversations.scene.work.shepherd.hard_lambing.succeeded.respond` / `ask_about_the_losses`; `conversations.scene.work.shepherd.old_dog.active.respond` / `ask_what_she_will_do`; `conversations.scene.work.shepherd.old_dog.active.respond` / `say_she_knows_best`; `conversations.scene.work.shepherd.old_dog.active.respond` / `advise_the_young_one`; `conversations.scene.work.shepherd.old_dog.succeeded.respond` / `note_the_kindness`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shepherd.broken_fence.blocked.accepted` — e.g. "Then %2$s is finished in a morning. Bring gloves and expect to be cold and to be fed afterwards."
- `conversations.scene.work.shepherd.broken_fence.blocked.explained` — e.g. "It is an hour's walk before the work starts. That is the whole reason and it is a perfectly good one, and it still leaves the fence down."
- `conversations.scene.work.shepherd.broken_fence.succeeded.acknowledged` — e.g. "Asking with a date attached worked. Asking in general had failed twice, and I had drawn the wrong lesson from that."
- `conversations.scene.work.shepherd.hard_lambing.blocked.accepted` — e.g. "Then %2$s has a chance, and so does the small one, and I get to lie down for three hours."
- `conversations.scene.work.shepherd.hard_lambing.blocked.conceded` — e.g. "I could. I have not, in nine years, and I could not tell you why in a way that would sound sensible out loud."
- `conversations.scene.work.shepherd.hard_lambing.blocked.explained` — e.g. "Cold, mostly. A lamb that is wet and small has about twenty minutes, and twenty minutes is exactly how long it takes me to walk the top field."
- `conversations.scene.work.shepherd.hard_lambing.succeeded.answered` — e.g. "Badly for one evening, and then not at all. That is not coldness. It is the only way to be any use to the rest of them in the morning."
- `conversations.scene.work.shepherd.old_dog.active.accepted` — e.g. "While she can still teach. That is the argument I have been avoiding, because it is the good one."
- `conversations.scene.work.shepherd.old_dog.active.explained` — e.g. "Take a young one up and leave her the low field. She will hate it and she will be alive in three years, and I have to be the one who chooses."
- `conversations.scene.work.shepherd.old_dog.active.steadied` — e.g. "She has had the best working life a dog can have, and that is exactly why the ending has to be done properly."
- `conversations.scene.work.shepherd.old_dog.succeeded.acknowledged` — e.g. "That was the whole trick and I did not think of it. Somebody outside the hill did, which is the argument for talking to people."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shepherd.followup   [27 chars]
    en  Anything else on your list?
    >>  ............................................
    pt  Mais alguma coisa na sua lista?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a lambing night?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.shepherd.*` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.followup.ask_more` — accepted phrasings: "whats the hardest part of a lambing night"; "what is the hardest part of a lambing night"; "hardest thing about lambing"
  - the message must contain one of: `hardest`, `lambing`
  - scored words: `hardest`(1.8), `lambing`(1.8), `whats`(0.8), `part`(0.8), `night`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.followup.ask_more   [43 chars]
    en  What's the hardest part of a lambing night?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma noite de parição?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a lambing night?"
       spoken on: conversations.scene.work.shepherd.followup, button `ask_more`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.hard`: the villager explains. Subject `work.shepherd.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.shepherd.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.shepherd.hard/1   [91 chars]
    en  Wolves, mostly. Cold takes the lambs. And sometimes they simply decide to die out of spite.
    >>  ............................................
    pt  Lobos, principalmente. O frio leva os cordeiros. E às vezes elas simplesmente decidem morrer por birra.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.hard/2   [93 chars]
    en  One a season if the year's kind. I know each one that goes, %1$s. That's the cost of the job.
    >>  ............................................
    pt  Uma por estação se o ano for bom. Eu sei de cada uma que vai, %1$s. É o custo do trabalho.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the flock."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.shepherd.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.followup.leave   [28 chars]
    en  I'll leave you to the flock.
    >>  ............................................
    pt  Vou deixar você com o rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the flock."
       spoken on: conversations.scene.work.shepherd.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave; conversations.topic.work.shepherd.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shepherd.hard_lambing.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shepherd.hard_lambing.blocked` — e.g. "I have %2$s in trouble and %3$s on top of it, and I am running out of hands and hours at the same rate."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shepherd.hard_lambing.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond   [12 chars]
    en  The lambing.
    >>  ............................................
    pt  A parição.
    >>  ............................................
```


### Button `ask_what_goes_wrong` — "What actually goes wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.hard_lambing.blocked` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.hard_lambing.blocked.ask_what_goes_wrong` — accepted phrasings: "what actually goes wrong"; "what actually goes wrong"; "what is the danger for them"
  - the message must contain one of: `wrong`, `danger`
  - scored words: `wrong`(1.8), `danger`(1.8), `actually`(0.8), `goes`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond.ask_what_goes_wrong
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.hard_lambing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond.ask_what_goes_wrong   [25 chars]
    en  What actually goes wrong?
    >>  ............................................
    pt  O que de fato dá errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.lambing`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shepherd.hard_lambing"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What actually goes wrong?"
       spoken on: conversations.scene.work.shepherd.hard_lambing.blocked.respond, button `ask_what_goes_wrong`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.hard_lambing.blocked.explained`: the villager explains. Subject `work.shepherd.lambing`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.explained/1   [142 chars]
    en  Cold, mostly. A lamb that is wet and small has about twenty minutes, and twenty minutes is exactly how long it takes me to walk the top field.
    >>  ............................................
    pt  Frio, principalmente. Um cordeiro molhado e pequeno tem uns vinte minutos, e vinte minutos é exatamente o que leva para eu atravessar o campo alto.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.explained/2   [129 chars]
    en  The mother turns away from one of the twins. There is no reason and no cure and you either see it happen or you find out at dawn.
    >>  ............................................
    pt  A mãe rejeita um dos gêmeos. Não há motivo nem cura, e ou você vê acontecer ou descobre ao amanhecer.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.explained/3   [121 chars]
    en  Nothing dramatic. That is what people never understand — it is not wolves. It is weather and timing and being one person.
    >>  ............................................
    pt  Nada dramático. É isso que as pessoas nunca entendem — não são lobos. É clima, é hora, e é ser uma pessoa só.
    >>  ............................................
```


### Button `offer_wool` — "I'll bring wool to keep them warm."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.shepherd.hard_lambing.blocked` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.hard_lambing.blocked.offer_wool` — accepted phrasings: "ill bring wool to keep them warm"; "i can bring wool to keep them warm"; "let me fetch wool for the lambs"
  - the message must contain one of: `wool`
  - scored words: `wool`(1.8), `ill`(0.8), `bring`(0.8), `keep`(0.8), `warm`(0.8), `let`(0.8), `fetch`(0.8), `lambs`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond.offer_wool
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.hard_lambing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond.offer_wool   [34 chars]
    en  I'll bring wool to keep them warm.
    >>  ............................................
    pt  Vou trazer lã para aquecê-los.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shepherd.lambing.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.shepherd.lambing`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.hard_lambing", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.shepherd.hard_lambing", "obligation": "commitment:work.shepherd.bring_wool"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.shepherd.bring_wool"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring wool to keep them warm."
       spoken on: conversations.scene.work.shepherd.hard_lambing.blocked.respond, button `offer_wool`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.hard_lambing.blocked.accepted`: the villager accepts. Subject `work.shepherd.lambing`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.accepted/1   [89 chars]
    en  Then %2$s has a chance, and so does the small one, and I get to lie down for three hours.
    >>  ............................................
    pt  Então %2$s tem chance, e o pequeno também, e eu consigo deitar por três horas.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.accepted/2   [104 chars]
    en  Bring it to the fold rather than the house. I will be at the fold. I have been at the fold since Sunday.
    >>  ............................................
    pt  Traga ao aprisco, não à casa. Vou estar no aprisco. Estou no aprisco desde domingo.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.accepted/3   [96 chars]
    en  Yes. And come and see them in a week, because you will have earned the look of them standing up.
    >>  ............................................
    pt  Sim. E venha vê-los daqui a uma semana, porque você vai ter merecido a visão deles de pé.
    >>  ............................................
```


### Button `advise_asking_for_hands` — "Ask the village for a night's help."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shepherd.hard_lambing.blocked` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.hard_lambing.blocked.advise_asking_for_hands` — accepted phrasings: "ask the village for a nights help"; "ask the village for a night of help"; "get somebody to share the night watch"
  - the message must contain one of: `village`, `share`, `watch`
  - scored words: `village`(1.8), `share`(1.8), `watch`(1.8), `ask`(0.8), `nights`(0.8), `help`(0.8), `night`(0.8), `get`(0.8), `somebody`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond.advise_asking_for_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.hard_lambing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond.advise_asking_for_hands   [35 chars]
    en  Ask the village for a night's help.
    >>  ............................................
    pt  Peça ajuda à vila por uma noite.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.shepherd.lambing`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shepherd.hard_lambing"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.conceded
WHO    VILLAGER — what the player reads after pressing "Ask the village for a night's help."
       spoken on: conversations.scene.work.shepherd.hard_lambing.blocked.respond, button `advise_asking_for_hands`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.hard_lambing.blocked.conceded`: the villager accepts. Subject `work.shepherd.lambing`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.conceded/1   [109 chars]
    en  I could. I have not, in nine years, and I could not tell you why in a way that would sound sensible out loud.
    >>  ............................................
    pt  Poderia. Não pedi em nove anos, e eu não saberia explicar por quê de um jeito que soasse sensato em voz alta.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.conceded/2   [140 chars]
    en  Two people would halve it. I know that. The difficulty is that asking makes the flock everybody's business, and then everybody has opinions.
    >>  ............................................
    pt  Duas pessoas cortariam pela metade. Eu sei. A dificuldade é que pedir torna o rebanho assunto de todo mundo, e aí todo mundo tem opinião.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.conceded/3   [114 chars]
    en  You are right and I will ask the baker's lad, who is up before dawn anyway and who has never once made it awkward.
    >>  ............................................
    pt  Você tem razão e eu vou pedir ao rapaz do padeiro, que já acorda antes do sol e nunca deixou a situação constrangedora.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the flock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shepherd.hard_lambing.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.hard_lambing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the flock.
    >>  ............................................
    pt  Vou deixar você voltar ao rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the flock."
       spoken on: conversations.scene.work.shepherd.hard_lambing.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave; conversations.topic.work.shepherd.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shepherd.hard_lambing.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shepherd.hard_lambing.succeeded` — e.g. "%2$s came through and both lambs are up. I lost two elsewhere and I am counting this as a good year."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shepherd.hard_lambing.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.respond   [19 chars]
    en  The lambing, after.
    >>  ............................................
    pt  A parição, depois.
    >>  ............................................
```


### Button `ask_about_the_losses` — "How do you take the ones you lose?"

*stance family `empathy` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.shepherd.hard_lambing.succeeded` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.hard_lambing.succeeded.ask_about_the_losses` — accepted phrasings: "how do you take the ones you lose"; "how do you take the ones you lose"; "what do the losses do to you"
  - the message must contain one of: `lose`, `losses`
  - scored words: `lose`(1.8), `losses`(1.8), `take`(0.8), `ones`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.respond.ask_about_the_losses
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.hard_lambing.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.respond.ask_about_the_losses   [34 chars]
    en  How do you take the ones you lose?
    >>  ............................................
    pt  Como você encara os que perde?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `work.shepherd.lambing`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.shepherd.hard_lambing"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "How do you take the ones you lose?"
       spoken on: conversations.scene.work.shepherd.hard_lambing.succeeded.respond, button `ask_about_the_losses`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.hard_lambing.succeeded.answered`: the villager explains. Subject `work.shepherd.lambing`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.answered/1   [134 chars]
    en  Badly for one evening, and then not at all. That is not coldness. It is the only way to be any use to the rest of them in the morning.
    >>  ............................................
    pt  Mal por uma noite, e depois nada. Não é frieza. É a única forma de servir para alguma coisa para os outros de manhã.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.answered/2   [119 chars]
    en  I bury them and I write the date. Nine years of dates. I have never read the list back and I would not stop keeping it.
    >>  ............................................
    pt  Eu enterro e anoto a data. Nove anos de datas. Nunca reli a lista e não deixaria de mantê-la.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.answered/3   [113 chars]
    en  People expect me to be hardened. I am not hardened. I am busy, and busy is what hardened looks like from outside.
    >>  ............................................
    pt  As pessoas esperam que eu esteja endurecida. Não estou endurecida. Estou ocupada, e ocupada é como endurecida parece de fora.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the flock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shepherd.hard_lambing.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.hard_lambing.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the flock.
    >>  ............................................
    pt  Vou deixar você voltar ao rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the flock."
       spoken on: conversations.scene.work.shepherd.hard_lambing.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave; conversations.topic.work.shepherd.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shepherd.old_dog.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shepherd.old_dog.active` — e.g. "She has %2$s and she still goes up the hill, because I ask her to and she has never once said no."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shepherd.old_dog.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active.respond   [8 chars]
    en  The dog.
    >>  ............................................
    pt  O cachorro.
    >>  ............................................
```


### Button `ask_what_she_will_do` — "What will you do about it?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.shepherd.old_dog.active` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.old_dog.active.ask_what_she_will_do` — accepted phrasings: "what will you do about it"; "what will you do about it"; "how will you handle that"
  - the message must contain one of: `handle`, `about`
  - scored words: `handle`(1.8), `about`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active.respond.ask_what_she_will_do
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.old_dog.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active.respond.ask_what_she_will_do   [26 chars]
    en  What will you do about it?
    >>  ............................................
    pt  O que você vai fazer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.the_dog`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shepherd.old_dog"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active.explained
WHO    VILLAGER — what the player reads after pressing "What will you do about it?"
       spoken on: conversations.scene.work.shepherd.old_dog.active.respond, button `ask_what_she_will_do`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.old_dog.active.explained`: the villager explains. Subject `work.shepherd.the_dog`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active.explained/1   [141 chars]
    en  Take a young one up and leave her the low field. She will hate it and she will be alive in three years, and I have to be the one who chooses.
    >>  ............................................
    pt  Levar uma jovem para cima e deixar a ela o campo baixo. Ela vai odiar e vai estar viva daqui a três anos, e sou eu que tenho que escolher.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.active.explained/2   [140 chars]
    en  Shorter days first. Then the low field. Then the doorstep. It is a staircase and I know every step of it because I have been down it before.
    >>  ............................................
    pt  Dias mais curtos primeiro. Depois o campo baixo. Depois a soleira. É uma escada e eu conheço cada degrau porque já desci antes.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.active.explained/3   [115 chars]
    en  Nothing yet, which is the wrong answer and the true one. I am waiting for a morning when she does not get up first.
    >>  ............................................
    pt  Nada ainda, que é a resposta errada e a verdadeira. Estou esperando uma manhã em que ela não se levante primeiro.
    >>  ............................................
```


### Button `say_she_knows_best` — "She's had a good working life."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.shepherd.old_dog.active` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.old_dog.active.say_she_knows_best` — accepted phrasings: "shes had a good working life"; "she has had a good working life"; "eleven years of good work is a lot"
  - the message must contain one of: `working`, `life`, `eleven`
  - scored words: `working`(1.8), `life`(1.8), `eleven`(1.8), `shes`(0.8), `had`(0.8), `good`(0.8), `she`(0.8), `years`(0.8), `work`(0.8), `lot`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active.respond.say_she_knows_best
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.old_dog.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active.respond.say_she_knows_best   [30 chars]
    en  She's had a good working life.
    >>  ............................................
    pt  Ela teve uma boa vida de trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.shepherd.the_dog`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shepherd.old_dog"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active.steadied
WHO    VILLAGER — what the player reads after pressing "She's had a good working life."
       spoken on: conversations.scene.work.shepherd.old_dog.active.respond, button `say_she_knows_best`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.old_dog.active.steadied`: the villager accepts. Subject `work.shepherd.the_dog`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active.steadied/1   [109 chars]
    en  She has had the best working life a dog can have, and that is exactly why the ending has to be done properly.
    >>  ............................................
    pt  Teve a melhor vida de trabalho que uma cadela pode ter, e é exatamente por isso que o fim precisa ser bem feito.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.active.steadied/2   [137 chars]
    en  Thank you. Nobody who keeps a dog in a house understands what she is. She is not a pet and she is not a tool, and there is no third word.
    >>  ............................................
    pt  Obrigada. Ninguém que tem cachorro dentro de casa entende o que ela é. Não é bicho de estimação e não é ferramenta, e não existe uma terceira palavra.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.active.steadied/3   [141 chars]
    en  She would have been miserable anywhere else. That is the thing I hold on to on the mornings I feel like a monster for asking her up the hill.
    >>  ............................................
    pt  Ela teria sido infeliz em qualquer outro lugar. É nisso que eu me seguro nas manhãs em que me sinto um monstro por pedir que ela suba.
    >>  ............................................
```


### Button `advise_the_young_one` — "Start training the young one now."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shepherd.old_dog.active` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.old_dog.active.advise_the_young_one` — accepted phrasings: "start training the young one now"; "start training the young one now"; "bring on a younger dog this season"
  - the message must contain one of: `training`, `younger`, `young`
  - scored words: `training`(1.8), `younger`(1.8), `young`(1.8), `start`(0.8), `one`(0.8), `now`(0.8), `bring`(0.8), `dog`(0.8), `season`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active.respond.advise_the_young_one
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.old_dog.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active.respond.advise_the_young_one   [33 chars]
    en  Start training the young one now.
    >>  ............................................
    pt  Comece a treinar a jovem agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.shepherd.the_dog`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shepherd.old_dog"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active.accepted
WHO    VILLAGER — what the player reads after pressing "Start training the young one now."
       spoken on: conversations.scene.work.shepherd.old_dog.active.respond, button `advise_the_young_one`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.old_dog.active.accepted`: the villager accepts. Subject `work.shepherd.the_dog`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active.accepted/1   [97 chars]
    en  While she can still teach. That is the argument I have been avoiding, because it is the good one.
    >>  ............................................
    pt  Enquanto ela ainda pode ensinar. É o argumento que eu venho evitando, porque é o bom.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.active.accepted/2   [142 chars]
    en  Yes. And she will train it better than I can, and she will know exactly what she is doing, and that is the part I cannot think about for long.
    >>  ............................................
    pt  Sim. E ela vai treinar melhor do que eu, e vai saber exatamente o que está fazendo, e essa é a parte em que eu não consigo pensar por muito tempo.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.active.accepted/3   [128 chars]
    en  This season, then. It gives her a job that is not the hill, and a dog with a job is a different animal from a dog on a doorstep.
    >>  ............................................
    pt  Nesta estação, então. Dá a ela um trabalho que não é a encosta, e uma cadela com trabalho é outro animal, diferente de uma cadela na soleira.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the flock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shepherd.old_dog.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.old_dog.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active.respond.leave   [35 chars]
    en  I'll let you get back to the flock.
    >>  ............................................
    pt  Vou deixar você voltar ao rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the flock."
       spoken on: conversations.scene.work.shepherd.old_dog.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave; conversations.topic.work.shepherd.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shepherd.old_dog.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shepherd.old_dog.succeeded` — e.g. "She has a pup to teach and she has taken to it like she was waiting to be asked."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shepherd.old_dog.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded.respond   [9 chars]
    en  Your dog.
    >>  ............................................
    pt  Sua cadela.
    >>  ............................................
```


### Button `note_the_kindness` — "You gave her a way to keep working."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.shepherd.old_dog.succeeded` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shepherd.old_dog.succeeded.note_the_kindness` — accepted phrasings: "you gave her a way to keep working"; "you gave her a way to keep working"; "she still has work to do"
  - the message must contain one of: `working`, `work`
  - scored words: `working`(1.8), `work`(1.8), `gave`(0.8), `her`(0.8), `way`(0.8), `keep`(0.8), `she`(0.8), `still`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.succeeded.respond.note_the_kindness
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.old_dog.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded.respond.note_the_kindness   [35 chars]
    en  You gave her a way to keep working.
    >>  ............................................
    pt  Você deu a ela um jeito de continuar trabalhando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4, respect +2  _(recorded under topic `work.shepherd.the_dog`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.shepherd.old_dog"}
- Then opens: `conversations.scene.work.shepherd.followup`
- …where the player's next choices will be: "What's the hardest part of a lambing night?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You gave her a way to keep working."
       spoken on: conversations.scene.work.shepherd.old_dog.succeeded.respond, button `note_the_kindness`
       leaves the player on: conversations.scene.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.old_dog.succeeded.acknowledged`: the villager accepts. Subject `work.shepherd.the_dog`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded.acknowledged/1   [127 chars]
    en  That was the whole trick and I did not think of it. Somebody outside the hill did, which is the argument for talking to people.
    >>  ............................................
    pt  Era esse o truque inteiro e eu não pensei nele. Alguém de fora da encosta pensou, que é o argumento a favor de conversar com as pessoas.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded.acknowledged/2   [111 chars]
    en  She needed a job more than she needed rest. I would have got that wrong by a year if I had worked it out alone.
    >>  ............................................
    pt  Ela precisava mais de um trabalho do que de descanso. Eu teria errado isso por um ano se tivesse resolvido sozinha.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded.acknowledged/3   [101 chars]
    en  Thank you. It buys her two summers, probably. Two summers is a great deal when you are counting them.
    >>  ............................................
    pt  Obrigada. Compra dois verões para ela, provavelmente. Dois verões é muita coisa quando se está contando.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the flock."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shepherd.old_dog.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shepherd.old_dog.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the flock.
    >>  ............................................
    pt  Vou deixar você voltar ao rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the flock."
       spoken on: conversations.scene.work.shepherd.old_dog.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.topic.work.shepherd.craft.respond / leave; conversations.topic.work.shepherd.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shepherd.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shepherd.craft` — e.g. "Shearing you learn from someone's hands over yours. Not one person has learned it from words."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shepherd.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shepherd.craft.respond   [21 chars]
    en  That's how it's held.
    >>  ............................................
    pt  É assim que se aprende.
    >>  ............................................
```


### Button `ask_hands` — "Whose hands were over yours?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.craft` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.craft.ask_hands` — accepted phrasings: "whose hands were over yours"
  - the message must contain one of: `hands`, `whose`, `taught`
  - scored words: `hands`(1.5), `whose`(1.2), `taught`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.craft.respond.ask_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.craft.respond.ask_hands   [28 chars]
    en  Whose hands were over yours?
    >>  ............................................
    pt  De quem eram as mãos sobre as suas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.craft.ask_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.craft.ask_hands
WHO    VILLAGER — what the player reads after pressing "Whose hands were over yours?"
       spoken on: conversations.topic.work.shepherd.craft.respond, button `ask_hands`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.craft.ask_hands`: the villager explains. Subject `work.shepherd.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.craft.ask_hands/1   [75 chars]
    en  My aunt's. She sheared until she was seventy and complained the whole time.
    >>  ............................................
    pt  Da minha tia. Ela tosquiou até os setenta e reclamou o tempo todo.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.craft.ask_hands/2   [75 chars]
    en  A neighbour's. He's dead now, and I still hear him telling me to slow down.
    >>  ............................................
    pt  De um vizinho. Ele morreu, e eu ainda ouço ele mandando eu ir devagar.
    >>  ............................................
```


### Button `admire` — "Nine years to learn a smell. That's dedication."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shepherd.craft` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.craft.admire` — accepted phrasings: "nine years to learn a smell. that's dedication"
  - the message must contain one of: `dedication`, `years`
  - scored words: `dedication`(1.5), `years`(1.0), `learn`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.craft.respond.admire   [47 chars]
    en  Nine years to learn a smell. That's dedication.
    >>  ............................................
    pt  Nove anos pra aprender um cheiro. Isso é dedicação.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shepherd.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shepherd.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.craft.admire
WHO    VILLAGER — what the player reads after pressing "Nine years to learn a smell. That's dedication."
       spoken on: conversations.topic.work.shepherd.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.craft.admire`: the villager accepts. Subject `work.shepherd.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.craft.admire/1   [72 chars]
    en  It's not dedication. It's forty animals and nothing else to think about.
    >>  ............................................
    pt  Não é dedicação. São quarenta animais e nada mais em que pensar.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.craft.admire/2   [63 chars]
    en  Say that in the square and watch them decide I'm strange, %1$s.
    >>  ............................................
    pt  Diga isso na praça e veja eles decidirem que eu sou estranho, %1$s.
    >>  ............................................
```


### Button `ask_teach` — "Could you teach it to someone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.craft` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.craft.ask_teach` — accepted phrasings: "could you teach it to someone"
  - the message must contain one of: `teach`, `someone`, `apprentice`
  - scored words: `teach`(1.5), `someone`(1.0), `apprentice`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.craft.respond.ask_teach
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.craft.respond.ask_teach   [30 chars]
    en  Could you teach it to someone?
    >>  ............................................
    pt  Você conseguiria ensinar a alguém?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.craft.ask_teach`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.craft.ask_teach
WHO    VILLAGER — what the player reads after pressing "Could you teach it to someone?"
       spoken on: conversations.topic.work.shepherd.craft.respond, button `ask_teach`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.craft.ask_teach`: the villager explains. Subject `work.shepherd.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.craft.ask_teach/1   [73 chars]
    en  The shearing, aye. The other part they'd have to earn the same way I did.
    >>  ............................................
    pt  A tosquia, sim. A outra parte teriam que ganhar do mesmo jeito que eu.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.craft.ask_teach/2   [66 chars]
    en  I've tried. They want it written down and it will not go on paper.
    >>  ............................................
    pt  Já tentei. Querem por escrito e isso não vai pro papel.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the flock."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shepherd.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.craft.respond.leave   [28 chars]
    en  I'll leave you to the flock.
    >>  ............................................
    pt  Vou deixar você com o rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the flock."
       spoken on: conversations.topic.work.shepherd.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shepherd.followup`

**Reached from 20 route(s):** `conversations.scene.work.shepherd.followup` / `ask_more`; `conversations.topic.work.shepherd.craft.respond` / `ask_hands`; `conversations.topic.work.shepherd.craft.respond` / `admire`; `conversations.topic.work.shepherd.craft.respond` / `ask_teach`; `conversations.topic.work.shepherd.future.respond` / `ask_smaller`; `conversations.topic.work.shepherd.future.respond` / `encourage`; `conversations.topic.work.shepherd.future.respond` / `ask_hill`; `conversations.topic.work.shepherd.respond` / `ask_hard`; `conversations.topic.work.shepherd.respond` / `value`; `conversations.topic.work.shepherd.respond` / `challenge`; `conversations.topic.work.shepherd.respond` / `challenge`; `conversations.topic.work.shepherd.risk.respond` / `ask_lost` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shepherd.challenge.landed` — e.g. "Ha! Say that in lambing week and I'll hand you the lantern."
- `conversations.work.prof.shepherd.challenge.stung` — e.g. "...Come and stand out there at three in the morning and say that again."
- `conversations.work.prof.shepherd.craft.admire` — e.g. "It's not dedication. It's forty animals and nothing else to think about."
- `conversations.work.prof.shepherd.craft.ask_hands` — e.g. "My aunt's. She sheared until she was seventy and complained the whole time."
- `conversations.work.prof.shepherd.craft.ask_teach` — e.g. "The shearing, aye. The other part they'd have to earn the same way I did."
- `conversations.work.prof.shepherd.future.ask_hill` — e.g. "No road. Which means no weaver, no market, no one coming up when it goes wrong."
- `conversations.work.prof.shepherd.future.ask_smaller` — e.g. "Because forty means numbers and twenty means names. I know which I'd rather have."
- `conversations.work.prof.shepherd.future.encourage` — e.g. "...Twenty. Said out loud it sounds like a decision rather than a daydream."
- `conversations.work.prof.shepherd.hard` — e.g. "Wolves, mostly. Cold takes the lambs. And sometimes they simply decide to die out of spite."
- `conversations.work.prof.shepherd.risk.ask_lost` — e.g. "Eleven, in fourteen years. I could name every one and I'd rather not, standing here."
- `conversations.work.prof.shepherd.risk.ask_wolves` — e.g. "A dog and a fence and standing where they can see you. That's the whole armoury."
- `conversations.work.prof.shepherd.risk.sympathise` — e.g. "...I do. It makes the good years better and the bad ones very much worse."
- `conversations.work.prof.shepherd.task.ask_count` — e.g. "The lowest. It's never once been the lowest and I check anyway."
- `conversations.work.prof.shepherd.task.ask_stubborn` — e.g. "Always. A sheep has one idea a day and defends it to the death."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shepherd.followup   [24 chars]
    en  That's sheep. Endlessly.
    >>  ............................................
    pt  É isso, ovelha. Sem fim.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shepherd.challenge.landed`, `work.shepherd.challenge.stung`, `work.shepherd.craft.admire`, `work.shepherd.craft.ask_hands`, `work.shepherd.craft.ask_teach`, `work.shepherd.future.ask_hill`, `work.shepherd.future.ask_smaller`, `work.shepherd.future.encourage`, `work.shepherd.hard`, `work.shepherd.risk.ask_lost`, `work.shepherd.risk.ask_wolves`, `work.shepherd.risk.sympathise`, `work.shepherd.task.ask_count`, `work.shepherd.task.ask_stubborn`, `work.shepherd.task.offer_hands`, `work.shepherd.value`, `work.shepherd.village.ask_shortage`, `work.shepherd.village.ask_weaver`, `work.shepherd.village.say_thanks` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.shepherd.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `flock`
  - scored words: `thought`(1.2), `flock`(1.5), `understand`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.shepherd.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.shepherd.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.shepherd.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.shepherd.thanks`: the villager accepts. Subject `work.shepherd.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.thanks/1   [68 chars]
    en  It's a trade you have to stand in to understand. Folk just see wool.
    >>  ............................................
    pt  É um ofício que você precisa viver pra entender. O povo só vê lã.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.thanks/2   [75 chars]
    en  Nobody does from the square, %1$s. From up here it's all names and weather.
    >>  ............................................
    pt  Ninguém pensa, lá da praça, %1$s. Daqui de cima é tudo nome e tempo.
    >>  ............................................
```


### Button `ask_more` — "Do you name them all?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.challenge.landed`, `work.shepherd.challenge.stung`, `work.shepherd.craft.admire`, `work.shepherd.craft.ask_hands`, `work.shepherd.craft.ask_teach`, `work.shepherd.future.ask_hill`, `work.shepherd.future.ask_smaller`, `work.shepherd.future.encourage`, `work.shepherd.hard`, `work.shepherd.risk.ask_lost`, `work.shepherd.risk.ask_wolves`, `work.shepherd.risk.sympathise`, `work.shepherd.task.ask_count`, `work.shepherd.task.ask_stubborn`, `work.shepherd.task.offer_hands`, `work.shepherd.value`, `work.shepherd.village.ask_shortage`, `work.shepherd.village.ask_weaver`, `work.shepherd.village.say_thanks` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.shepherd.more` — accepted phrasings: "do you name them all"
  - the message must contain one of: `name`, `names`
  - scored words: `name`(1.5), `names`(1.5), `call`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.followup.ask_more   [21 chars]
    en  Do you name them all?
    >>  ............................................
    pt  Você dá nome a todas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.shepherd.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.more
WHO    VILLAGER — what the player reads after pressing "Do you name them all?"
       spoken on: conversations.topic.work.shepherd.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.shepherd.more`: the villager discloses. Subject `work.shepherd.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.more/1   [78 chars]
    en  Every one. It makes the good years better and the bad ones considerably worse.
    >>  ............................................
    pt  Todas. Isso faz os bons anos melhores e os ruins consideravelmente piores.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.more/2   [88 chars]
    en  I try not to. Then one of them does something ridiculous and it's got a name by evening.
    >>  ............................................
    pt  Eu tento não dar. Aí uma faz alguma bobagem e até de noite já tem nome.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Eleven lost in fourteen years, and I could name all eleven, and I'd rather not.
    >>  ............................................
    pt  Todas. Onze perdidas em catorze anos, e eu saberia nomear as onze, e prefiro não.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. I'd like to know each of them at the end. Forty is too many to know.
    >>  ............................................
    pt  Um rebanho menor. Queria conhecer cada uma no fim. Quarenta é demais pra conhecer.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Names cost more in a bad year and they've been worth it every year so far.
    >>  ............................................
    pt  Todas. Nomes custam mais num ano ruim e valeram a pena todo ano até agora.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock, in time. Hills don't hurry and neither do I.
    >>  ............................................
    pt  Um rebanho menor, com o tempo. Morros não têm pressa e eu também não.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. It makes the good years better and the bad ones considerably worse.
    >>  ............................................
    pt  Todas. Faz os bons anos melhores e os ruins consideravelmente piores.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock and more of them old. That's a strange ambition and it's mine.
    >>  ............................................
    pt  Um rebanho menor e mais delas velhas. É uma ambição estranha e é minha.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. It makes the good years better and the bad ones considerably worse.
    >>  ............................................
    pt  Todas. Faz os bons anos melhores e os ruins consideravelmente piores.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock and more of them old. That's a strange ambition and it's mine.
    >>  ............................................
    pt  Um rebanho menor e mais delas velhas. É uma ambição estranha e é minha.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Come at shearing and I'll introduce you to the grey one, who is a menace.
    >>  ............................................
    pt  Todas. Venha na tosquia e eu te apresento à cinzenta, que é uma peste.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. The weaver would have something to say, and she can say it to my face.
    >>  ............................................
    pt  Um rebanho menor. A tecelã teria algo a dizer, e ela pode dizer na minha cara.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Come at shearing and I'll introduce you to the grey one, who is a menace.
    >>  ............................................
    pt  Todas. Venha na tosquia e eu te apresento à cinzenta, que é uma peste.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. The weaver would have something to say, and she can say it to my face.
    >>  ............................................
    pt  Um rebanho menor. A tecelã teria algo a dizer, e ela pode dizer na minha cara.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Come at shearing and I'll introduce you to the grey one, who is a menace.
    >>  ............................................
    pt  Todas. Venha na tosquia e eu te apresento à cinzenta, que é uma peste.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. The weaver would have something to say, and she can say it to my face.
    >>  ............................................
    pt  Um rebanho menor. A tecelã teria algo a dizer, e ela pode dizer na minha cara.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Eleven lost in fourteen years, and I could name all eleven, and I'd rather not.
    >>  ............................................
    pt  Todas. Onze perdidas em catorze anos, e eu saberia nomear as onze, e prefiro não.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. I'd like to know each of them at the end. Forty is too many to know.
    >>  ............................................
    pt  Um rebanho menor. Queria conhecer cada uma no fim. Quarenta é demais pra conhecer.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. It makes the good years better and the bad ones considerably worse.
    >>  ............................................
    pt  Todas. Faz os bons anos melhores e os ruins consideravelmente piores.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock and more of them old. That's a strange ambition and it's mine.
    >>  ............................................
    pt  Um rebanho menor e mais delas velhas. É uma ambição estranha e é minha.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. It makes the good years better and the bad ones considerably worse.
    >>  ............................................
    pt  Todas. Faz os bons anos melhores e os ruins consideravelmente piores.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock and more of them old. That's a strange ambition and it's mine.
    >>  ............................................
    pt  Um rebanho menor e mais delas velhas. É uma ambição estranha e é minha.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. It's a foolish habit and I'll not be giving it up.
    >>  ............................................
    pt  Todas. É um hábito bobo e eu não vou largar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. Twenty, so that I'd know each one at the end of it.
    >>  ............................................
    pt  Um rebanho menor. Vinte, pra eu conhecer cada uma no fim.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Names cost more in a bad year and they've been worth it every year so far.
    >>  ............................................
    pt  Todas. Nomes custam mais num ano ruim e valeram a pena todo ano até agora.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock, in time. Hills don't hurry and neither do I.
    >>  ............................................
    pt  Um rebanho menor, com o tempo. Morros não têm pressa e eu também não.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. It's a foolish habit and I'll not be giving it up.
    >>  ............................................
    pt  Todas. É um hábito bobo e eu não vou largar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. Twenty, so that I'd know each one at the end of it.
    >>  ............................................
    pt  Um rebanho menor. Vinte, pra eu conhecer cada uma no fim.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Names cost more in a bad year and they've been worth it every year so far.
    >>  ............................................
    pt  Todas. Nomes custam mais num ano ruim e valeram a pena todo ano até agora.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock, in time. Hills don't hurry and neither do I.
    >>  ............................................
    pt  Um rebanho menor, com o tempo. Morros não têm pressa e eu também não.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one of them! Which makes the good years better and the bad ones a great deal worse.
    >>  ............................................
    pt  Todas elas! O que faz os bons anos melhores e os ruins bem piores.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. Forty means numbers and twenty means names, and I know which I'd rather have.
    >>  ............................................
    pt  Um rebanho menor. Quarenta é número e vinte é nome, e eu sei qual eu prefiro.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one of them! Which makes the good years better and the bad ones a great deal worse.
    >>  ............................................
    pt  Todas elas! O que faz os bons anos melhores e os ruins bem piores.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. Forty means numbers and twenty means names, and I know which I'd rather have.
    >>  ............................................
    pt  Um rebanho menor. Quarenta é número e vinte é nome, e eu sei qual eu prefiro.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Names cost more in a bad year and they've been worth it every year so far.
    >>  ............................................
    pt  Todas. Nomes custam mais num ano ruim e valeram a pena todo ano até agora.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock, in time. Hills don't hurry and neither do I.
    >>  ............................................
    pt  Um rebanho menor, com o tempo. Morros não têm pressa e eu também não.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. Eleven lost in fourteen years, and I could name all eleven, and I'd rather not.
    >>  ............................................
    pt  Todas. Onze perdidas em catorze anos, e eu saberia nomear as onze, e prefiro não.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. I'd like to know each of them at the end. Forty is too many to know.
    >>  ............................................
    pt  Um rebanho menor. Queria conhecer cada uma no fim. Quarenta é demais pra conhecer.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one. It's a foolish habit and I'll not be giving it up.
    >>  ............................................
    pt  Todas. É um hábito bobo e eu não vou largar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. Twenty, so that I'd know each one at the end of it.
    >>  ............................................
    pt  Um rebanho menor. Vinte, pra eu conhecer cada uma no fim.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one of them! Which makes the good years better and the bad ones a great deal worse.
    >>  ............................................
    pt  Todas elas! O que faz os bons anos melhores e os ruins bem piores.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. Forty means numbers and twenty means names, and I know which I'd rather have.
    >>  ............................................
    pt  Um rebanho menor. Quarenta é número e vinte é nome, e eu sei qual eu prefiro.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shepherd.more/1
    en  Every one of them! Which makes the good years better and the bad ones a great deal worse.
    >>  ............................................
    pt  Todas elas! O que faz os bons anos melhores e os ruins bem piores.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shepherd.more/2
    en  A smaller flock. Forty means numbers and twenty means names, and I know which I'd rather have.
    >>  ............................................
    pt  Um rebanho menor. Quarenta é número e vinte é nome, e eu sei qual eu prefiro.
    >>  ............................................
```

</details>


### Button `leave` — "Mind the wolves."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shepherd.challenge.landed`, `work.shepherd.challenge.stung`, `work.shepherd.craft.admire`, `work.shepherd.craft.ask_hands`, `work.shepherd.craft.ask_teach`, `work.shepherd.future.ask_hill`, `work.shepherd.future.ask_smaller`, `work.shepherd.future.encourage`, `work.shepherd.hard`, `work.shepherd.risk.ask_lost`, `work.shepherd.risk.ask_wolves`, `work.shepherd.risk.sympathise`, `work.shepherd.task.ask_count`, `work.shepherd.task.ask_stubborn`, `work.shepherd.task.offer_hands`, `work.shepherd.value`, `work.shepherd.village.ask_shortage`, `work.shepherd.village.ask_weaver`, `work.shepherd.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.followup.leave   [16 chars]
    en  Mind the wolves.
    >>  ............................................
    pt  Cuidado com os lobos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "Mind the wolves."
       spoken on: conversations.topic.work.shepherd.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shepherd.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shepherd.future` — e.g. "I'd like a smaller flock and more of them old. That's a strange ambition and it's mine."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shepherd.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shepherd.future.respond   [21 chars]
    en  That's the long view.
    >>  ............................................
    pt  É a vista longa.
    >>  ............................................
```


### Button `ask_smaller` — "Why smaller?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.future` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.future.ask_smaller` — accepted phrasings: "why smaller"
  - the message must contain one of: `smaller`, `fewer`
  - scored words: `smaller`(1.5), `fewer`(1.5), `why`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.future.respond.ask_smaller
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.future.respond.ask_smaller   [12 chars]
    en  Why smaller?
    >>  ............................................
    pt  Por que menor?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.future.ask_smaller`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.future.ask_smaller
WHO    VILLAGER — what the player reads after pressing "Why smaller?"
       spoken on: conversations.topic.work.shepherd.future.respond, button `ask_smaller`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.future.ask_smaller`: the villager explains. Subject `work.shepherd.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.future.ask_smaller/1   [81 chars]
    en  Because forty means numbers and twenty means names. I know which I'd rather have.
    >>  ............................................
    pt  Porque quarenta é número e vinte é nome. Sei qual eu prefiro.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.future.ask_smaller/2   [84 chars]
    en  Because I'd like to know each one at the end of it, %1$s. Forty is too many to know.
    >>  ............................................
    pt  Porque eu queria conhecer cada uma no fim, %1$s. Quarenta é demais pra conhecer.
    >>  ............................................
```


### Button `encourage` — "Then take the smaller flock."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shepherd.future` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.future.encourage` — accepted phrasings: "then take the smaller flock"
  - the message must contain one of: `flock`
  - scored words: `take`(0.8), `flock`(1.5), `do`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.future.respond.encourage   [28 chars]
    en  Then take the smaller flock.
    >>  ............................................
    pt  Então fique com o rebanho menor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shepherd.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shepherd.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then take the smaller flock."
       spoken on: conversations.topic.work.shepherd.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.future.encourage`: the villager accepts. Subject `work.shepherd.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.future.encourage/1   [74 chars]
    en  ...Twenty. Said out loud it sounds like a decision rather than a daydream.
    >>  ............................................
    pt  ...Vinte. Dito em voz alta parece decisão e não devaneio.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.future.encourage/2   [72 chars]
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria algo a dizer. Que diga na minha cara, então.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Saying it aloud makes it something I could be talked out of.
    >>  ............................................
    pt  ...Vinte. Dizer em voz alta torna algo de que podem me demover.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say, and I've been arranging my life around that.
    >>  ............................................
    pt  A tecelã teria o que dizer, e venho organizando minha vida em torno disso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Forty years of flocks and this is the first number I've said aloud.
    >>  ............................................
    pt  ...Vinte. Quarenta anos de rebanhos e é o primeiro número que digo em voz alta.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She's had something to say since we were children.
    >>  ............................................
    pt  A tecelã teria o que dizer. Ela tem o que dizer desde que éramos crianças.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Said out loud it sounds like a decision rather than a daydream.
    >>  ............................................
    pt  ...Vinte. Dito em voz alta soa como decisão e não devaneio.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara, então.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Said out loud it sounds like a decision rather than a daydream.
    >>  ............................................
    pt  ...Vinte. Dito em voz alta soa como decisão e não devaneio.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara, então.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty, %1$s. Said out loud it's a decision and not a daydream.
    >>  ............................................
    pt  ...Vinte, %1$s. Dito em voz alta é decisão e não devaneio.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty, %1$s. Said out loud it's a decision and not a daydream.
    >>  ............................................
    pt  ...Vinte, %1$s. Dito em voz alta é decisão e não devaneio.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty, %1$s. Said out loud it's a decision and not a daydream.
    >>  ............................................
    pt  ...Vinte, %1$s. Dito em voz alta é decisão e não devaneio.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Saying it aloud makes it something I could be talked out of.
    >>  ............................................
    pt  ...Vinte. Dizer em voz alta torna algo de que podem me demover.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say, and I've been arranging my life around that.
    >>  ............................................
    pt  A tecelã teria o que dizer, e venho organizando minha vida em torno disso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Said out loud it sounds like a decision rather than a daydream.
    >>  ............................................
    pt  ...Vinte. Dito em voz alta soa como decisão e não devaneio.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara, então.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Said out loud it sounds like a decision rather than a daydream.
    >>  ............................................
    pt  ...Vinte. Dito em voz alta soa como decisão e não devaneio.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara, então.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. A decision, said aloud.
    >>  ............................................
    pt  ...Vinte. Uma decisão, dita em voz alta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver will have something to say. Let her.
    >>  ............................................
    pt  A tecelã vai ter o que dizer. Que diga.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Forty years of flocks and this is the first number I've said aloud.
    >>  ............................................
    pt  ...Vinte. Quarenta anos de rebanhos e é o primeiro número que digo em voz alta.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She's had something to say since we were children.
    >>  ............................................
    pt  A tecelã teria o que dizer. Ela tem o que dizer desde que éramos crianças.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. A decision, said aloud.
    >>  ............................................
    pt  ...Vinte. Uma decisão, dita em voz alta.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver will have something to say. Let her.
    >>  ............................................
    pt  A tecelã vai ter o que dizer. Que diga.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Forty years of flocks and this is the first number I've said aloud.
    >>  ............................................
    pt  ...Vinte. Quarenta anos de rebanhos e é o primeiro número que digo em voz alta.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She's had something to say since we were children.
    >>  ............................................
    pt  A tecelã teria o que dizer. Ela tem o que dizer desde que éramos crianças.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty! Said out loud it's a decision rather than a daydream, isn't it.
    >>  ............................................
    pt  ...Vinte! Dito em voz alta é decisão e não devaneio, não é?
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara, então.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty! Said out loud it's a decision rather than a daydream, isn't it.
    >>  ............................................
    pt  ...Vinte! Dito em voz alta é decisão e não devaneio, não é?
    >>  ............................................
  playful.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara, então.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Forty years of flocks and this is the first number I've said aloud.
    >>  ............................................
    pt  ...Vinte. Quarenta anos de rebanhos e é o primeiro número que digo em voz alta.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She's had something to say since we were children.
    >>  ............................................
    pt  A tecelã teria o que dizer. Ela tem o que dizer desde que éramos crianças.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. Saying it aloud makes it something I could be talked out of.
    >>  ............................................
    pt  ...Vinte. Dizer em voz alta torna algo de que podem me demover.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say, and I've been arranging my life around that.
    >>  ............................................
    pt  A tecelã teria o que dizer, e venho organizando minha vida em torno disso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty. A decision, said aloud.
    >>  ............................................
    pt  ...Vinte. Uma decisão, dita em voz alta.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver will have something to say. Let her.
    >>  ............................................
    pt  A tecelã vai ter o que dizer. Que diga.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty! Said out loud it's a decision rather than a daydream, isn't it.
    >>  ............................................
    pt  ...Vinte! Dito em voz alta é decisão e não devaneio, não é?
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara, então.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shepherd.future.encourage/1
    en  ...Twenty! Said out loud it's a decision rather than a daydream, isn't it.
    >>  ............................................
    pt  ...Vinte! Dito em voz alta é decisão e não devaneio, não é?
    >>  ............................................
  witty.dialogue.conversations.work.prof.shepherd.future.encourage/2
    en  The weaver would have something to say. She can say it to my face, then.
    >>  ............................................
    pt  A tecelã teria o que dizer. Que diga na minha cara, então.
    >>  ............................................
```

</details>


### Button `ask_hill` — "What holds you back from the hill?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.future` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.future.ask_hill` — accepted phrasings: "what holds you back from the hill"
  - the message must contain one of: `hill`, `holds`, `move`
  - scored words: `hill`(1.5), `holds`(1.2), `move`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.future.respond.ask_hill
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.future.respond.ask_hill   [34 chars]
    en  What holds you back from the hill?
    >>  ............................................
    pt  O que te segura longe do morro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.future.ask_hill`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.future.ask_hill
WHO    VILLAGER — what the player reads after pressing "What holds you back from the hill?"
       spoken on: conversations.topic.work.shepherd.future.respond, button `ask_hill`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.future.ask_hill`: the villager explains. Subject `work.shepherd.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.future.ask_hill/1   [79 chars]
    en  No road. Which means no weaver, no market, no one coming up when it goes wrong.
    >>  ............................................
    pt  Sem estrada. O que significa sem tecelã, sem mercado, ninguém subindo quando dá errado.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.future.ask_hill/2   [78 chars]
    en  Forty sheep and one road. Take away the road and the arithmetic stops working.
    >>  ............................................
    pt  Quarenta ovelhas e uma estrada. Tire a estrada e a conta para de fechar.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the flock."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shepherd.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.future.respond.leave   [28 chars]
    en  I'll leave you to the flock.
    >>  ............................................
    pt  Vou deixar você com o rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the flock."
       spoken on: conversations.topic.work.shepherd.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shepherd.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shepherd` — e.g. "The flock knows me better than most people do. Sheep don't gossip. Much."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shepherd.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shepherd.respond   [40 chars]
    en  Forty of them, and each one an argument.
    >>  ............................................
    pt  Quarenta delas, e cada uma uma discussão.
    >>  ............................................
```


### Button `ask_hard` — "What do you lose them to?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.identity` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.hard` — accepted phrasings: "what do you lose them to"
  - the message must contain one of: `lose`, `wolves`, `lambs`
  - scored words: `lose`(1.2), `wolves`(1.5), `lambs`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.respond.ask_hard   [25 chars]
    en  What do you lose them to?
    >>  ............................................
    pt  Você as perde pra quê?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.shepherd.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.hard
WHO    VILLAGER — what the player reads after pressing "What do you lose them to?"
       spoken on: conversations.topic.work.shepherd.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.hard`: the villager explains. Subject `work.shepherd.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.followup / ask_more
```

> Written out in full under **`conversations.scene.work.shepherd.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Everyone here wears something you made."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shepherd.identity` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.value` — accepted phrasings: "everyone here wears something you made"
  - the message must contain one of: `wears`, `wool`
  - scored words: `wears`(1.5), `wool`(1.5), `made`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.respond.value   [39 chars]
    en  Everyone here wears something you made.
    >>  ............................................
    pt  Todo mundo aqui veste algo que você fez.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shepherd.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.shepherd.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.value
WHO    VILLAGER — what the player reads after pressing "Everyone here wears something you made."
       spoken on: conversations.topic.work.shepherd.respond, button `value`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.value`: the villager accepts. Subject `work.shepherd.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.value/1   [68 chars]
    en  They do. Nobody thinks of a sheep when they pull a blanket up, mind.
    >>  ............................................
    pt  Vestem. Mas ninguém pensa numa ovelha quando puxa o cobertor.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.value/2   [60 chars]
    en  Every coat in the square came off a back I know the name of.
    >>  ............................................
    pt  Todo casaco na praça saiu de um lombo cujo nome eu sei.
    >>  ............................................
```


### Button `challenge` — "They mostly look after themselves."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.shepherd.identity` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.challenge` — accepted phrasings: "they mostly look after themselves"
  - the message must contain one of: `themselves`, `sheep`
  - scored words: `themselves`(1.5), `sheep`(1.2), `easy`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.respond.challenge   [34 chars]
    en  They mostly look after themselves.
    >>  ............................................
    pt  Elas quase se viram sozinhas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.shepherd.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.shepherd.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.challenge.landed
WHO    VILLAGER — what the player reads after pressing "They mostly look after themselves."
       spoken on: conversations.topic.work.shepherd.respond, button `challenge`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.challenge.landed`: the villager resists. Subject `work.shepherd.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.challenge.landed/1   [59 chars]
    en  Ha! Say that in lambing week and I'll hand you the lantern.
    >>  ............................................
    pt  Ha! Diga isso na semana de parição e eu te entrego a lanterna.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.challenge.landed/2   [62 chars]
    en  They do, right up until the moment they very much don't, %1$s.
    >>  ............................................
    pt  Elas se viram, até o momento em que definitivamente não se viram, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.shepherd.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.shepherd.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.challenge.stung
WHO    VILLAGER — what the player reads after pressing "They mostly look after themselves."
       spoken on: conversations.topic.work.shepherd.respond, button `challenge`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.challenge.stung`: the villager resists. Subject `work.shepherd.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.challenge.stung/1   [71 chars]
    en  ...Come and stand out there at three in the morning and say that again.
    >>  ............................................
    pt  ...Venha ficar lá fora às três da manhã e repita isso.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.challenge.stung/2   [70 chars]
    en  Themselves. Right. That'll be why I've not slept properly since March.
    >>  ............................................
    pt  Sozinhas. Certo. Deve ser por isso que eu não durmo direito desde março.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the flock."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shepherd.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.respond.leave   [28 chars]
    en  I'll leave you to the flock.
    >>  ............................................
    pt  Vou deixar você com o rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the flock."
       spoken on: conversations.topic.work.shepherd.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shepherd.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shepherd.risk` — e.g. "Wolves take one and unsettle forty. The counting after is the worst hour of the year."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shepherd.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shepherd.risk.respond   [24 chars]
    en  That's what's out there.
    >>  ............................................
    pt  É isso que existe lá fora.
    >>  ............................................
```


### Button `ask_lost` — "How many have you lost?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.risk` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.risk.ask_lost` — accepted phrasings: "how many have you lost"
  - the message must contain one of: `lost`, `many`, `wolves`
  - scored words: `lost`(1.5), `many`(1.0), `wolves`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.risk.respond.ask_lost
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.risk.respond.ask_lost   [23 chars]
    en  How many have you lost?
    >>  ............................................
    pt  Quantas você já perdeu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.risk.ask_lost`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.risk.ask_lost
WHO    VILLAGER — what the player reads after pressing "How many have you lost?"
       spoken on: conversations.topic.work.shepherd.risk.respond, button `ask_lost`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.risk.ask_lost`: the villager explains. Subject `work.shepherd.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.risk.ask_lost/1   [84 chars]
    en  Eleven, in fourteen years. I could name every one and I'd rather not, standing here.
    >>  ............................................
    pt  Onze, em catorze anos. Eu saberia nomear cada uma e prefiro não, aqui de pé.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.risk.ask_lost/2   [64 chars]
    en  Fewer than most. That's a sentence I say to sound calm about it.
    >>  ............................................
    pt  Menos que a maioria. É uma frase que eu digo pra parecer calmo.
    >>  ............................................
```


### Button `sympathise` — "You name them, though."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shepherd.risk` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.risk.sympathise` — accepted phrasings: "you name them, though"
  - the message must contain one of: `name`, `attached`
  - scored words: `name`(1.5), `them`(0.5), `attached`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.risk.respond.sympathise   [22 chars]
    en  You name them, though.
    >>  ............................................
    pt  Mas você dá nome a elas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.shepherd.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shepherd.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "You name them, though."
       spoken on: conversations.topic.work.shepherd.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.risk.sympathise`: the villager accepts. Subject `work.shepherd.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.risk.sympathise/1   [73 chars]
    en  ...I do. It makes the good years better and the bad ones very much worse.
    >>  ............................................
    pt  ...Dou. Faz os bons anos melhores e os ruins muito piores.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.risk.sympathise/2   [67 chars]
    en  Every one. It's a foolish habit and I'll not be giving it up, %1$s.
    >>  ............................................
    pt  Todas. É um hábito bobo e eu não vou largar, %1$s.
    >>  ............................................
```


### Button `ask_wolves` — "Can anything be done about the wolves?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.risk` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.risk.ask_wolves` — accepted phrasings: "can anything be done about the wolves"
  - the message must contain one of: `wolves`, `done`, `protect`
  - scored words: `wolves`(1.5), `done`(1.0), `protect`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.risk.respond.ask_wolves
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.risk.respond.ask_wolves   [38 chars]
    en  Can anything be done about the wolves?
    >>  ............................................
    pt  Dá pra fazer algo sobre os lobos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.risk.ask_wolves`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.risk.ask_wolves
WHO    VILLAGER — what the player reads after pressing "Can anything be done about the wolves?"
       spoken on: conversations.topic.work.shepherd.risk.respond, button `ask_wolves`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.risk.ask_wolves`: the villager explains. Subject `work.shepherd.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.risk.ask_wolves/1   [80 chars]
    en  A dog and a fence and standing where they can see you. That's the whole armoury.
    >>  ............................................
    pt  Um cão, uma cerca e ficar onde eles te vejam. É todo o arsenal.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.risk.ask_wolves/2   [87 chars]
    en  The guard walks up twice a month. It helps less than you'd think and more than nothing.
    >>  ............................................
    pt  O guarda sobe duas vezes por mês. Ajuda menos do que se imagina e mais que nada.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the flock."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shepherd.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.risk.respond.leave   [28 chars]
    en  I'll leave you to the flock.
    >>  ............................................
    pt  Vou deixar você com o rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the flock."
       spoken on: conversations.topic.work.shepherd.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shepherd.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shepherd.task` — e.g. "Counting. I've counted three times and got three numbers, which is how afternoons go."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shepherd.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shepherd.task.respond   [21 chars]
    en  That's the afternoon.
    >>  ............................................
    pt  É a tarde.
    >>  ............................................
```


### Button `ask_count` — "Which number do you trust?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.task` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.task.ask_count` — accepted phrasings: "which number do you trust"
  - the message must contain one of: `number`, `trust`, `count`
  - scored words: `number`(1.5), `trust`(1.2), `count`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.task.respond.ask_count
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.task.respond.ask_count   [26 chars]
    en  Which number do you trust?
    >>  ............................................
    pt  Em qual número você confia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.task.ask_count`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.task.ask_count
WHO    VILLAGER — what the player reads after pressing "Which number do you trust?"
       spoken on: conversations.topic.work.shepherd.task.respond, button `ask_count`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.task.ask_count`: the villager explains. Subject `work.shepherd.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.task.ask_count/1   [63 chars]
    en  The lowest. It's never once been the lowest and I check anyway.
    >>  ............................................
    pt  No menor. Nunca foi o menor e eu confiro mesmo assim.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.task.ask_count/2   [59 chars]
    en  None of them. That's why I walk the line before dark, %1$s.
    >>  ............................................
    pt  Em nenhum. Por isso eu ando a linha antes de escurecer, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I'll walk the far side."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.shepherd.task` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.task.offer_hands` — accepted phrasings: "i'll walk the far side"
  - the message must contain one of: `walk`, `side`
  - scored words: `walk`(1.2), `side`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.task.respond.offer_hands   [23 chars]
    en  I'll walk the far side.
    >>  ............................................
    pt  Eu vou pelo lado de lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.shepherd.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shepherd.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I'll walk the far side."
       spoken on: conversations.topic.work.shepherd.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.task.offer_hands`: the villager accepts. Subject `work.shepherd.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.task.offer_hands/1   [89 chars]
    en  ...Would you. Then go slow and don't wave your arms. They're idiots but they're not deaf.
    >>  ............................................
    pt  ...Você faria? Então vá devagar e não abane os braços. Elas são bobas, mas não surdas.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.task.offer_hands/2   [77 chars]
    en  The far side. Right. Meet me at the gate and don't let the grey one past you.
    >>  ............................................
    pt  O lado de lá. Certo. Me encontre no portão e não deixe a cinzenta passar.
    >>  ............................................
```


### Button `ask_stubborn` — "Do they always argue?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.task` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.task.ask_stubborn` — accepted phrasings: "do they always argue"
  - the message must contain one of: `argue`, `stubborn`
  - scored words: `argue`(1.5), `stubborn`(1.5), `always`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.task.respond.ask_stubborn
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.task.respond.ask_stubborn   [21 chars]
    en  Do they always argue?
    >>  ............................................
    pt  Elas sempre discutem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.task.ask_stubborn`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.task.ask_stubborn
WHO    VILLAGER — what the player reads after pressing "Do they always argue?"
       spoken on: conversations.topic.work.shepherd.task.respond, button `ask_stubborn`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.task.ask_stubborn`: the villager explains. Subject `work.shepherd.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.task.ask_stubborn/1   [63 chars]
    en  Always. A sheep has one idea a day and defends it to the death.
    >>  ............................................
    pt  Sempre. Uma ovelha tem uma ideia por dia e defende até a morte.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.task.ask_stubborn/2   [68 chars]
    en  Only about gates, weather and where they slept last night. So — yes.
    >>  ............................................
    pt  Só sobre portões, tempo e onde dormiram ontem. Então — sim.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the flock."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shepherd.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.task.respond.leave   [28 chars]
    en  I'll leave you to the flock.
    >>  ............................................
    pt  Vou deixar você com o rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the flock."
       spoken on: conversations.topic.work.shepherd.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shepherd.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shepherd.village` — e.g. "Every blanket, every coat, every child's first jumper. All of it walked up that hill first."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shepherd.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shepherd.village.respond   [27 chars]
    en  That's where the wool goes.
    >>  ............................................
    pt  É pra onde vai a lã.
    >>  ............................................
```


### Button `ask_weaver` — "What's the arrangement?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.village` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.village.ask_weaver` — accepted phrasings: "what's the arrangement"
  - the message must contain one of: `arrangement`, `weaver`, `deal`
  - scored words: `arrangement`(1.5), `weaver`(1.5), `deal`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.village.respond.ask_weaver
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.village.respond.ask_weaver   [23 chars]
    en  What's the arrangement?
    >>  ............................................
    pt  Qual é o acordo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.village.ask_weaver`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.village.ask_weaver
WHO    VILLAGER — what the player reads after pressing "What's the arrangement?"
       spoken on: conversations.topic.work.shepherd.village.respond, button `ask_weaver`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.village.ask_weaver`: the villager explains. Subject `work.shepherd.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.village.ask_weaver/1   [77 chars]
    en  I bring it clean and she doesn't ask the price. It's worked for eleven years.
    >>  ............................................
    pt  Eu trago limpa e ela não pergunta o preço. Funciona há onze anos.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.village.ask_weaver/2   [62 chars]
    en  Her mother and my aunt made it. We just kept turning up, %1$s.
    >>  ............................................
    pt  A mãe dela e minha tia fizeram. A gente só continuou aparecendo, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Everyone here is warm because of that hill."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shepherd.village` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.village.say_thanks` — accepted phrasings: "everyone here is warm because of that hill"
  - the message must contain one of: `warm`, `hill`
  - scored words: `warm`(1.5), `hill`(1.2), `because`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.village.respond.say_thanks   [43 chars]
    en  Everyone here is warm because of that hill.
    >>  ............................................
    pt  Todo mundo aqui tem calor por causa daquele morro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shepherd.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shepherd.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Everyone here is warm because of that hill."
       spoken on: conversations.topic.work.shepherd.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.village.say_thanks`: the villager accepts. Subject `work.shepherd.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.village.say_thanks/1   [68 chars]
    en  ...That's a nice way to put a lot of mud and swearing. I'll keep it.
    >>  ............................................
    pt  ...É um jeito bonito de descrever muita lama e xingamento. Vou guardar.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.village.say_thanks/2   [64 chars]
    en  It is. Nobody's said so out loud in my lifetime, so — thank you.
    >>  ............................................
    pt  É verdade. Ninguém disse em voz alta na minha vida, então — obrigado.
    >>  ............................................
```


### Button `ask_shortage` — "What happens in a bad wool year?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shepherd.village` · offered only once the villager has actually said `work:shepherd`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shepherd.village.ask_shortage` — accepted phrasings: "what happens in a bad wool year"
  - the message must contain one of: `shortage`, `wool`
  - scored words: `shortage`(1.5), `bad`(0.8), `wool`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.village.respond.ask_shortage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.village.respond.ask_shortage   [32 chars]
    en  What happens in a bad wool year?
    >>  ............................................
    pt  O que acontece num ano ruim de lã?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shepherd.village.ask_shortage`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shepherd.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you name them all?" | "Mind the wolves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.village.ask_shortage
WHO    VILLAGER — what the player reads after pressing "What happens in a bad wool year?"
       spoken on: conversations.topic.work.shepherd.village.respond, button `ask_shortage`
       leaves the player on: conversations.topic.work.shepherd.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.village.ask_shortage`: the villager explains. Subject `work.shepherd.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shepherd.village.ask_shortage/1   [85 chars]
    en  Everyone wears last year's and complains politely. Then they're kind to me in spring.
    >>  ............................................
    pt  Todo mundo usa a do ano passado e reclama educadamente. Aí são gentis comigo na primavera.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd.village.ask_shortage/2   [74 chars]
    en  Two winters ago. The weaver made do and nobody said a word about it to me.
    >>  ............................................
    pt  Dois invernos atrás. A tecelã se virou e ninguém me disse uma palavra.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the flock."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shepherd.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shepherd.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shepherd.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shepherd.village.respond.leave   [28 chars]
    en  I'll leave you to the flock.
    >>  ............................................
    pt  Vou deixar você com o rebanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the flock."
       spoken on: conversations.topic.work.shepherd.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.left`: the villager accepts. Subject `work.shepherd.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shepherd.broken_fence.blocked.respond / leave; conversations.scene.work.shepherd.broken_fence.succeeded.respond / leave; conversations.scene.work.shepherd.followup / leave; conversations.scene.work.shepherd.hard_lambing.blocked.respond / leave; conversations.scene.work.shepherd.hard_lambing.succeeded.respond / leave; conversations.scene.work.shepherd.old_dog.active.respond / leave; conversations.scene.work.shepherd.old_dog.succeeded.respond / leave; conversations.topic.work.shepherd.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shepherd.broken_fence.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

