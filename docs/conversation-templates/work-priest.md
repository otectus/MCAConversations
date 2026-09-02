# Work talk with a priest

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.priest.blessing_refused.blocked.respond`](#conversations-scene-work-priest-blessing-refused-blocked-respond)
- [`conversations.scene.work.priest.blessing_refused.succeeded.respond`](#conversations-scene-work-priest-blessing-refused-succeeded-respond)
- [`conversations.scene.work.priest.followup`](#conversations-scene-work-priest-followup)
- [`conversations.scene.work.priest.frightened_family.active.respond`](#conversations-scene-work-priest-frightened-family-active-respond)
- [`conversations.scene.work.priest.frightened_family.succeeded.respond`](#conversations-scene-work-priest-frightened-family-succeeded-respond)
- [`conversations.scene.work.priest.the_register.active.respond`](#conversations-scene-work-priest-the-register-active-respond)
- [`conversations.scene.work.priest.the_register.succeeded.respond`](#conversations-scene-work-priest-the-register-succeeded-respond)
- [`conversations.topic.work.priest.craft.respond`](#conversations-topic-work-priest-craft-respond)
- [`conversations.topic.work.priest.followup`](#conversations-topic-work-priest-followup)
- [`conversations.topic.work.priest.future.respond`](#conversations-topic-work-priest-future-respond)
- [`conversations.topic.work.priest.respond`](#conversations-topic-work-priest-respond)
- [`conversations.topic.work.priest.risk.respond`](#conversations-topic-work-priest-risk-respond)
- [`conversations.topic.work.priest.task.respond`](#conversations-topic-work-priest-task-respond)
- [`conversations.topic.work.priest.village.respond`](#conversations-topic-work-priest-village-respond)

---

## `conversations.scene.work.priest.blessing_refused.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.priest.blessing_refused.blocked` — e.g. "Four households have asked me for %3$s about %2$s, and every one of them asked kindly, and I have said no to all four."


```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.priest.blessing_refused.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond   [23 chars]
    en  What they've asked for.
    >>  ............................................
    pt  O que pediram.
    >>  ............................................
```


### Button `ask_what_harm` — "What harm would it do?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.blessing_refused.blocked` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.blessing_refused.blocked.ask_what_harm` — accepted phrasings: "what harm would it do"; "what harm would it do"; "what is the harm in the rite"
  - the message must contain one of: `harm`, `rite`
  - scored words: `harm`(1.8), `rite`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond.ask_what_harm
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.blessing_refused.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond.ask_what_harm   [22 chars]
    en  What harm would it do?
    >>  ............................................
    pt  Que mal isso faria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.priest.the_accused`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.priest.blessing_refused"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What harm would it do?"
       spoken on: conversations.scene.work.priest.blessing_refused.blocked.respond, button `ask_what_harm`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.blessing_refused.blocked.explained`: the villager explains. Subject `work.priest.the_accused`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.explained/1   [149 chars]
    en  It would settle it. Not truthfully — socially. After a rite, %2$s is the family it was done about, and that is permanent in a way an argument is not.
    >>  ............................................
    pt  Resolveria. Não com verdade — socialmente. Depois de um rito, %2$s é a família sobre a qual aquilo foi feito, e isso é permanente de um jeito que uma discussão não é.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.explained/2   [141 chars]
    en  The rite does nothing. The saying of it does everything, and I have watched a saying outlive the family it was said about by two generations.
    >>  ............................................
    pt  O rito não faz nada. O dizer faz tudo, e eu já vi um dito sobreviver por duas gerações à família sobre a qual foi dito.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.explained/3   [135 chars]
    en  It would also make me useful to a frightened crowd, and a chapel that is useful to a frightened crowd has stopped being a check on one.
    >>  ............................................
    pt  Também me tornaria útil a uma multidão assustada, e uma capela útil a uma multidão assustada parou de ser um freio para ela.
    >>  ............................................
```


### Button `back_the_refusal` — "Hold the line on that."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.priest.blessing_refused.blocked` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.blessing_refused.blocked.back_the_refusal` — accepted phrasings: "hold the line on that"; "hold the line on that"; "keep the chapel out of the accusation"
  - the message must contain one of: `line`, `chapel`, `accusation`
  - scored words: `line`(1.8), `chapel`(1.8), `accusation`(1.8), `hold`(0.8), `keep`(0.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond.back_the_refusal
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.blessing_refused.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond.back_the_refusal   [22 chars]
    en  Hold the line on that.
    >>  ............................................
    pt  Mantenha essa posição.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.priest.blessing.backed`, budget `deep`, replay policy `once`
- Does: disposition — respect +4, trust +2  _(recorded under topic `work.priest.the_accused`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.priest.blessing_refused"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Hold the line on that."
       spoken on: conversations.scene.work.priest.blessing_refused.blocked.respond, button `back_the_refusal`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.blessing_refused.blocked.steadied`: the villager accepts. Subject `work.priest.the_accused`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.steadied/1   [109 chars]
    en  I will. It costs me a quarter of my congregation for a season and I have costed that out and I can afford it.
    >>  ............................................
    pt  Vou. Custa-me um quarto da congregação por uma estação, e eu fiz essa conta e posso pagar.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.steadied/2   [110 chars]
    en  Yes. And I will offer them something instead, because a refusal with nothing behind it is just a door closing.
    >>  ............................................
    pt  Sim. E vou oferecer outra coisa no lugar, porque uma recusa sem nada atrás é só uma porta se fechando.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.steadied/3   [138 chars]
    en  Thank you. Four kind requests in a week wears a person down more than one hostile one, and I was nearer to yes than I would like to admit.
    >>  ............................................
    pt  Obrigada. Quatro pedidos gentis numa semana desgastam mais do que um hostil, e eu estava mais perto do sim do que gostaria de admitir.
    >>  ............................................
```


### Button `ask_what_else` — "Could you offer them something else?"

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.priest.blessing_refused.blocked` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.blessing_refused.blocked.ask_what_else` — accepted phrasings: "could you offer them something else"; "could you offer them something else"; "is there another thing you could give them"
  - the message must contain one of: `offer`, `another`, `give`
  - scored words: `offer`(1.8), `another`(1.8), `give`(1.8), `something`(0.8), `else`(0.8), `thing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond.ask_what_else
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.blessing_refused.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond.ask_what_else   [36 chars]
    en  Could you offer them something else?
    >>  ............................................
    pt  Você poderia oferecer outra coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +1  _(recorded under topic `work.priest.the_accused`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.priest.blessing_refused"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked.considered
WHO    VILLAGER — what the player reads after pressing "Could you offer them something else?"
       spoken on: conversations.scene.work.priest.blessing_refused.blocked.respond, button `ask_what_else`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.blessing_refused.blocked.considered`: the villager accepts. Subject `work.priest.the_accused`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.considered/1   [130 chars]
    en  A vigil, open to anybody, about being afraid rather than about a family. It gives the fear somewhere to go that is not a doorstep.
    >>  ............................................
    pt  Uma vigília, aberta a qualquer um, sobre o medo em vez de sobre uma família. Dá ao medo um lugar para ir que não é a soleira de alguém.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.considered/2   [109 chars]
    en  That is the right question and it is better than the one I had been asking, which was only whether to refuse.
    >>  ............................................
    pt  Essa é a pergunta certa e é melhor do que a que eu vinha fazendo, que era só se eu devia recusar.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.considered/3   [133 chars]
    en  I can sit with them. Four evenings, four houses, no rite and no register. It will take a fortnight and it is what they actually want.
    >>  ............................................
    pt  Posso me sentar com eles. Quatro noites, quatro casas, sem rito e sem registro. Vai levar duas semanas e é o que eles de fato querem.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the chapel."

*stance family `exit` · tone `plain` · answers the beat(s) `work.priest.blessing_refused.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.blessing_refused.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked.respond.leave   [36 chars]
    en  I'll let you get back to the chapel.
    >>  ............................................
    pt  Vou deixar você voltar à capela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the chapel."
       spoken on: conversations.scene.work.priest.blessing_refused.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave; conversations.topic.work.priest.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.priest.leave/1   [10 chars]
    en  Go gently.
    >>  ............................................
    pt  Vá com calma.
    >>  ............................................
  dialogue.conversations.work.prof.priest.leave/2   [33 chars]
    en  Aye. Mind the road at dusk, %1$s.
    >>  ............................................
    pt  É. Cuidado com a estrada no fim da tarde, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.priest.blessing_refused.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.priest.blessing_refused.succeeded` — e.g. "I held the vigil instead. Nineteen people came, and %2$s came too, and stood at the back, and nobody said a word about it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.priest.blessing_refused.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded.respond   [22 chars]
    en  Those four households.
    >>  ............................................
    pt  Aquelas quatro casas.
    >>  ............................................
```


### Button `ask_about_the_vigil` — "What was the vigil like?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.priest.blessing_refused.succeeded` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.blessing_refused.succeeded.ask_about_the_vigil` — accepted phrasings: "what was the vigil like"; "what was the vigil like"; "how did the vigil go"
  - the message must contain one of: `vigil`
  - scored words: `vigil`(1.8), `like`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.succeeded.respond.ask_about_the_vigil
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.blessing_refused.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded.respond.ask_about_the_vigil   [24 chars]
    en  What was the vigil like?
    >>  ............................................
    pt  Como foi a vigília?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `work.priest.the_accused`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.priest.blessing_refused"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What was the vigil like?"
       spoken on: conversations.scene.work.priest.blessing_refused.succeeded.respond, button `ask_about_the_vigil`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.blessing_refused.succeeded.answered`: the villager explains. Subject `work.priest.the_accused`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded.answered/1   [146 chars]
    en  Very quiet. I said four sentences and then we sat, and sitting in a room with nineteen frightened people does more than four sentences ever could.
    >>  ............................................
    pt  Muito silenciosa. Eu disse quatro frases e depois nos sentamos, e sentar numa sala com dezenove pessoas assustadas faz mais do que quatro frases jamais fariam.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded.answered/2   [134 chars]
    en  One woman cried and afterwards said she had not been crying about any of it, and I believed her, and it did not matter which was true.
    >>  ............................................
    pt  Uma mulher chorou e depois disse que não estava chorando por nada daquilo, e eu acreditei, e não importava qual era verdade.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded.answered/3   [137 chars]
    en  Nobody was named. That was the whole design and it is why it worked. Fear that is not pointed at anybody eventually has nowhere to stand.
    >>  ............................................
    pt  Ninguém foi nomeado. Era esse o desenho inteiro e é por isso que funcionou. Medo que não aponta para ninguém acaba sem lugar para ficar de pé.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the chapel."

*stance family `exit` · tone `plain` · answers the beat(s) `work.priest.blessing_refused.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.blessing_refused.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the chapel.
    >>  ............................................
    pt  Vou deixar você voltar à capela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the chapel."
       spoken on: conversations.scene.work.priest.blessing_refused.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave; conversations.topic.work.priest.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.priest.followup`

**Reached from 11 route(s):** `conversations.scene.work.priest.blessing_refused.blocked.respond` / `ask_what_harm`; `conversations.scene.work.priest.blessing_refused.blocked.respond` / `back_the_refusal`; `conversations.scene.work.priest.blessing_refused.blocked.respond` / `ask_what_else`; `conversations.scene.work.priest.blessing_refused.succeeded.respond` / `ask_about_the_vigil`; `conversations.scene.work.priest.frightened_family.active.respond` / `ask_what_helps`; `conversations.scene.work.priest.frightened_family.active.respond` / `offer_candles`; `conversations.scene.work.priest.frightened_family.active.respond` / `acknowledge_the_hours`; `conversations.scene.work.priest.frightened_family.succeeded.respond` / `note_the_ordinary`; `conversations.scene.work.priest.the_register.active.respond` / `ask_what_it_shows`; `conversations.scene.work.priest.the_register.active.respond` / `urge_reading_it_out`; `conversations.scene.work.priest.the_register.succeeded.respond` / `note_the_reading`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.priest.blessing_refused.blocked.considered` — e.g. "A vigil, open to anybody, about being afraid rather than about a family. It gives the fear somewhere to go that is not a doorstep."
- `conversations.scene.work.priest.blessing_refused.blocked.explained` — e.g. "It would settle it. Not truthfully — socially. After a rite, %2$s is the family it was done about, and that is permanent in a way an argument is not."
- `conversations.scene.work.priest.blessing_refused.blocked.steadied` — e.g. "I will. It costs me a quarter of my congregation for a season and I have costed that out and I can afford it."
- `conversations.scene.work.priest.blessing_refused.succeeded.answered` — e.g. "Very quiet. I said four sentences and then we sat, and sitting in a room with nineteen frightened people does more than four sentences ever could."
- `conversations.scene.work.priest.frightened_family.active.accepted` — e.g. "Then they get a lit room and I get an hour that is about something practical, and both of those are worth more than a sermon."
- `conversations.scene.work.priest.frightened_family.active.explained` — e.g. "Light and company and a routine. Not one of those is spiritual and all three of them work, and I have stopped being embarrassed about that."
- `conversations.scene.work.priest.frightened_family.active.steadied` — e.g. "It is what the chapel is for. If I am not doing this I am dusting a building, and there are people who would do the dusting better."
- `conversations.scene.work.priest.frightened_family.succeeded.acknowledged` — e.g. "It is the only medicine I have ever had and it does not look like anything from outside, which is why nobody believes it works."
- `conversations.scene.work.priest.the_register.active.accepted` — e.g. "Every season, and after a poor harvest especially, which is when it is least welcome and most needed."
- `conversations.scene.work.priest.the_register.active.explained` — e.g. "That one in nine was something and eight in nine were a bad season and a neighbour nobody liked. I read that page aloud once a year."
- `conversations.scene.work.priest.the_register.succeeded.acknowledged` — e.g. "One winter. I have written it in the book as one winter and not as a victory, because eleven years has taught me the difference."


```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.priest.followup   [30 chars]
    en  Anything else weighing on you?
    >>  ............................................
    pt  Mais alguma coisa pesando?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a long vigil?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.priest.*` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.followup.ask_more` — accepted phrasings: "whats the hardest part of a long vigil"; "what is the hardest part of a long vigil"; "hardest thing about keeping a vigil"
  - the message must contain one of: `hardest`, `vigil`
  - scored words: `hardest`(1.8), `vigil`(1.8), `whats`(0.8), `part`(0.8), `long`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.followup.ask_more   [40 chars]
    en  What's the hardest part of a long vigil?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma vigília longa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a long vigil?"
       spoken on: conversations.scene.work.priest.followup, button `ask_more`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.hard`: the villager explains. Subject `work.priest.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.priest.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.priest.hard/1   [85 chars]
    en  The truth, slowly, and then I sit down and let them be angry at me. It helps someone.
    >>  ............................................
    pt  A verdade, devagar, e aí eu sento e deixo que fiquem com raiva de mim. Ajuda alguém.
    >>  ............................................
  dialogue.conversations.work.prof.priest.hard/2   [78 chars]
    en  As little as possible at first, %1$s. Grief arrives before understanding does.
    >>  ............................................
    pt  O mínimo possível no começo, %1$s. O luto chega antes do entendimento.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the chapel."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.priest.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.followup.leave   [29 chars]
    en  I'll leave you to the chapel.
    >>  ............................................
    pt  Vou deixar você com a capela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the chapel."
       spoken on: conversations.scene.work.priest.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave; conversations.topic.work.priest.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.priest.frightened_family.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.priest.frightened_family.active` — e.g. "There is %2$s in this village and nothing I can say makes it smaller, because the fear is not about an argument."


```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.priest.frightened_family.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active.respond   [20 chars]
    en  The frightened ones.
    >>  ............................................
    pt  Os assustados.
    >>  ............................................
```


### Button `ask_what_helps` — "What actually helps them?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.priest.frightened_family.active` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.frightened_family.active.ask_what_helps` — accepted phrasings: "what actually helps them"; "what actually helps them"; "what makes a difference to that fear"
  - the message must contain one of: `helps`, `difference`
  - scored words: `helps`(1.8), `difference`(1.8), `actually`(0.8), `makes`(0.8), `fear`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active.respond.ask_what_helps
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.frightened_family.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active.respond.ask_what_helps   [25 chars]
    en  What actually helps them?
    >>  ............................................
    pt  O que de fato ajuda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.the_frightened`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.priest.frightened_family"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active.explained
WHO    VILLAGER — what the player reads after pressing "What actually helps them?"
       spoken on: conversations.scene.work.priest.frightened_family.active.respond, button `ask_what_helps`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.frightened_family.active.explained`: the villager explains. Subject `work.priest.the_frightened`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active.explained/1   [139 chars]
    en  Light and company and a routine. Not one of those is spiritual and all three of them work, and I have stopped being embarrassed about that.
    >>  ............................................
    pt  Luz, companhia e rotina. Nenhuma das três é espiritual e as três funcionam, e eu parei de ter vergonha disso.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.active.explained/2   [156 chars]
    en  Being asked about something else. An hour of ordinary conversation does more than an hour of reassurance, because reassurance keeps the subject in the room.
    >>  ............................................
    pt  Ser perguntado sobre outra coisa. Uma hora de conversa comum faz mais que uma hora de consolo, porque o consolo mantém o assunto na sala.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.active.explained/3   [95 chars]
    en  Sleep. Almost everything else follows from sleep, and almost nothing helps until it comes back.
    >>  ............................................
    pt  Sono. Quase tudo o mais decorre do sono, e quase nada ajuda até ele voltar.
    >>  ............................................
```


### Button `offer_candles` — "I'll bring candles for those houses."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.priest.frightened_family.active` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.frightened_family.active.offer_candles` — accepted phrasings: "ill bring candles for those houses"; "i can bring candles for those houses"; "let me fetch candles for them"
  - the message must contain one of: `candles`, `candle`
  - scored words: `candles`(1.8), `candle`(1.8), `ill`(0.8), `bring`(0.8), `those`(0.8), `houses`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active.respond.offer_candles
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.frightened_family.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active.respond.offer_candles   [36 chars]
    en  I'll bring candles for those houses.
    >>  ............................................
    pt  Vou levar velas para essas casas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.priest.frightened.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.priest.the_frightened`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.frightened_family", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.priest.frightened_family", "obligation": "commitment:work.priest.bring_candles"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.priest.bring_candles"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring candles for those houses."
       spoken on: conversations.scene.work.priest.frightened_family.active.respond, button `offer_candles`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.frightened_family.active.accepted`: the villager accepts. Subject `work.priest.the_frightened`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active.accepted/1   [125 chars]
    en  Then they get a lit room and I get an hour that is about something practical, and both of those are worth more than a sermon.
    >>  ............................................
    pt  Então eles têm um cômodo aceso e eu tenho uma hora que é sobre algo prático, e as duas coisas valem mais que um sermão.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.active.accepted/2   [163 chars]
    en  Leave them at the chapel and I will take them round, because a candle from the chapel is a different object from a candle from a neighbour, and tonight that helps.
    >>  ............................................
    pt  Deixe na capela e eu distribuo, porque uma vela da capela é um objeto diferente de uma vela do vizinho, e hoje isso ajuda.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.active.accepted/3   [125 chars]
    en  Yes. And do not tell anybody it was you, because they will feel they owe you, and owing is one more thing to lie awake about.
    >>  ............................................
    pt  Sim. E não conte a ninguém que foi você, porque vão sentir que te devem, e dever é mais uma coisa para se ficar acordado pensando.
    >>  ............................................
```


### Button `acknowledge_the_hours` — "Every evening is a lot to give."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.priest.frightened_family.active` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.frightened_family.active.acknowledge_the_hours` — accepted phrasings: "every evening is a lot to give"; "every evening is a lot to give"; "that is a great deal of your evenings"
  - the message must contain one of: `evening`, `evenings`
  - scored words: `evening`(1.8), `evenings`(1.8), `every`(0.8), `lot`(0.8), `give`(0.8), `great`(0.8), `deal`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active.respond.acknowledge_the_hours
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.frightened_family.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active.respond.acknowledge_the_hours   [31 chars]
    en  Every evening is a lot to give.
    >>  ............................................
    pt  Toda noite é muito para dar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4  _(recorded under topic `work.priest.the_frightened`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.priest.frightened_family"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active.steadied
WHO    VILLAGER — what the player reads after pressing "Every evening is a lot to give."
       spoken on: conversations.scene.work.priest.frightened_family.active.respond, button `acknowledge_the_hours`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.frightened_family.active.steadied`: the villager accepts. Subject `work.priest.the_frightened`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active.steadied/1   [131 chars]
    en  It is what the chapel is for. If I am not doing this I am dusting a building, and there are people who would do the dusting better.
    >>  ............................................
    pt  É para isso que a capela serve. Se eu não estou fazendo isso, estou tirando pó de um prédio, e há gente que tiraria pó melhor.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.active.steadied/2   [134 chars]
    en  Thank you. It is a lot and I have four more weeks of it in me, and I have started counting, which is a sign I should be careful about.
    >>  ............................................
    pt  Obrigada. É muito e eu tenho mais quatro semanas disso em mim, e comecei a contar, o que é um sinal com o qual eu deveria ter cuidado.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.active.steadied/3   [156 chars]
    en  Somebody sat with my mother every evening for a winter, thirty years ago, and I have never established who. This is the nearest I can get to paying it back.
    >>  ............................................
    pt  Alguém sentou com a minha mãe toda noite durante um inverno, trinta anos atrás, e eu nunca descobri quem. Isto é o mais perto que eu chego de retribuir.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the chapel."

*stance family `exit` · tone `plain` · answers the beat(s) `work.priest.frightened_family.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.frightened_family.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active.respond.leave   [36 chars]
    en  I'll let you get back to the chapel.
    >>  ............................................
    pt  Vou deixar você voltar à capela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the chapel."
       spoken on: conversations.scene.work.priest.frightened_family.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave; conversations.topic.work.priest.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.priest.frightened_family.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.priest.frightened_family.succeeded` — e.g. "%2$s is sleeping. It took five weeks and candles and a great many ordinary conversations about turnips."


```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.priest.frightened_family.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.succeeded.respond   [15 chars]
    en  Those evenings.
    >>  ............................................
    pt  Aquelas noites.
    >>  ............................................
```


### Button `note_the_ordinary` — "Ordinary conversation did that."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.priest.frightened_family.succeeded` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.frightened_family.succeeded.note_the_ordinary` — accepted phrasings: "ordinary conversation did that"; "ordinary conversation did that"; "the ordinary hours were the medicine"
  - the message must contain one of: `ordinary`, `conversation`, `hours`
  - scored words: `ordinary`(1.8), `conversation`(1.8), `hours`(1.8), `were`(0.8), `medicine`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.succeeded.respond.note_the_ordinary
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.frightened_family.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.succeeded.respond.note_the_ordinary   [31 chars]
    en  Ordinary conversation did that.
    >>  ............................................
    pt  Conversa comum fez isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.priest.the_frightened`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.priest.frightened_family"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Ordinary conversation did that."
       spoken on: conversations.scene.work.priest.frightened_family.succeeded.respond, button `note_the_ordinary`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.frightened_family.succeeded.acknowledged`: the villager accepts. Subject `work.priest.the_frightened`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.succeeded.acknowledged/1   [127 chars]
    en  It is the only medicine I have ever had and it does not look like anything from outside, which is why nobody believes it works.
    >>  ............................................
    pt  É o único remédio que eu já tive e não parece nada visto de fora, e é por isso que ninguém acredita que funciona.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.succeeded.acknowledged/2   [121 chars]
    en  Thank you. I want to be careful — five weeks would probably have done it without me. I will never know and I went anyway.
    >>  ............................................
    pt  Obrigada. Quero ser cuidadosa — cinco semanas provavelmente teriam resolvido sem mim. Nunca vou saber, e eu fui assim mesmo.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.succeeded.acknowledged/3   [122 chars]
    en  Turnips. Five weeks of turnips and weather and somebody's roof. If I had brought answers instead, we would still be at it.
    >>  ............................................
    pt  Nabos. Cinco semanas de nabos, tempo e o telhado de alguém. Se eu tivesse levado respostas, a gente ainda estaria nisso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the chapel."

*stance family `exit` · tone `plain` · answers the beat(s) `work.priest.frightened_family.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.frightened_family.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the chapel.
    >>  ............................................
    pt  Vou deixar você voltar à capela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the chapel."
       spoken on: conversations.scene.work.priest.frightened_family.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave; conversations.topic.work.priest.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.priest.the_register.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.priest.the_register.active` — e.g. "I keep a register of every accusation this village has made and how each one ended. There are %2$s in it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.priest.the_register.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.priest.the_register.active.respond   [18 chars]
    en  The book you keep.
    >>  ............................................
    pt  O livro que você mantém.
    >>  ............................................
```


### Button `ask_what_it_shows` — "What does the register show?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.the_register.active` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.the_register.active.ask_what_it_shows` — accepted phrasings: "what does the register show"; "what does the register show"; "what do the entries add up to"
  - the message must contain one of: `register`, `entries`
  - scored words: `register`(1.8), `entries`(1.8), `does`(0.8), `show`(0.8), `add`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.active.respond.ask_what_it_shows
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.the_register.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.the_register.active.respond.ask_what_it_shows   [28 chars]
    en  What does the register show?
    >>  ............................................
    pt  O que o registro mostra?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.priest.the_register`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.priest.the_register"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.active.explained
WHO    VILLAGER — what the player reads after pressing "What does the register show?"
       spoken on: conversations.scene.work.priest.the_register.active.respond, button `ask_what_it_shows`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.the_register.active.explained`: the villager explains. Subject `work.priest.the_register`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.the_register.active.explained/1   [132 chars]
    en  That one in nine was something and eight in nine were a bad season and a neighbour nobody liked. I read that page aloud once a year.
    >>  ............................................
    pt  Que um em nove era algo e oito em nove eram uma estação ruim e um vizinho de quem ninguém gostava. Leio essa página em voz alta uma vez por ano.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.active.explained/2   [130 chars]
    en  That the accusations cluster after a poor harvest, which tells you what they are actually about and it is not what anybody thinks.
    >>  ............................................
    pt  Que as acusações se agrupam depois de uma colheita fraca, o que revela sobre o que elas de fato são, e não é o que ninguém imagina.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.active.explained/3   [113 chars]
    en  That four of the eight families left within three years. That is the column nobody wants me to have written down.
    >>  ............................................
    pt  Que quatro das oito famílias foram embora em três anos. É a coluna que ninguém quer que eu tenha anotado.
    >>  ............................................
```


### Button `urge_reading_it_out` — "Read that page out more often."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.priest.the_register.active` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.the_register.active.urge_reading_it_out` — accepted phrasings: "read that page out more often"; "read that page out more often"; "read the tally aloud each season"
  - the message must contain one of: `page`, `tally`, `aloud`
  - scored words: `page`(1.8), `tally`(1.8), `aloud`(1.8), `read`(0.8), `out`(0.8), `more`(0.8), `often`(0.8), `each`(0.8), `season`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.active.respond.urge_reading_it_out
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.the_register.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.the_register.active.respond.urge_reading_it_out   [30 chars]
    en  Read that page out more often.
    >>  ............................................
    pt  Leia essa página em voz alta mais vezes.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.priest.the_register`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.priest.the_register"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.active.accepted
WHO    VILLAGER — what the player reads after pressing "Read that page out more often."
       spoken on: conversations.scene.work.priest.the_register.active.respond, button `urge_reading_it_out`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.the_register.active.accepted`: the villager accepts. Subject `work.priest.the_register`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.the_register.active.accepted/1   [101 chars]
    en  Every season, and after a poor harvest especially, which is when it is least welcome and most needed.
    >>  ............................................
    pt  Toda estação, e especialmente depois de uma colheita fraca, que é quando é menos bem-vinda e mais necessária.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.active.accepted/2   [130 chars]
    en  Yes. It is four sentences and a list of numbers and it takes ninety seconds, and it is the most useful ninety seconds of the year.
    >>  ............................................
    pt  Sim. São quatro frases e uma lista de números e leva noventa segundos, e são os noventa segundos mais úteis do ano.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.active.accepted/3   [115 chars]
    en  I have been reading it once, in the spring, because that felt tactful. Tact has been costing families their houses.
    >>  ............................................
    pt  Eu vinha lendo uma vez, na primavera, porque parecia mais delicado. A delicadeza vem custando a casa de famílias.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the chapel."

*stance family `exit` · tone `plain` · answers the beat(s) `work.priest.the_register.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.the_register.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.the_register.active.respond.leave   [36 chars]
    en  I'll let you get back to the chapel.
    >>  ............................................
    pt  Vou deixar você voltar à capela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the chapel."
       spoken on: conversations.scene.work.priest.the_register.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave; conversations.topic.work.priest.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.priest.the_register.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.priest.the_register.succeeded` — e.g. "I read it out after the poor harvest and there was no accusation at all this winter, for the first time in eleven years."


```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.priest.the_register.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.priest.the_register.succeeded.respond   [20 chars]
    en  The register, since.
    >>  ............................................
    pt  O registro, depois disso.
    >>  ............................................
```


### Button `note_the_reading` — "A winter with no accusation."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.priest.the_register.succeeded` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.priest.the_register.succeeded.note_the_reading` — accepted phrasings: "a winter with no accusation"; "a winter with no accusation is a result"; "that quiet winter was your doing"
  - the message must contain one of: `winter`, `accusation`
  - scored words: `winter`(1.8), `accusation`(1.8), `result`(0.8), `quiet`(0.8), `doing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.succeeded.respond.note_the_reading
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.the_register.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.the_register.succeeded.respond.note_the_reading   [28 chars]
    en  A winter with no accusation.
    >>  ............................................
    pt  Um inverno sem acusação.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +4  _(recorded under topic `work.priest.the_register`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.priest.the_register"}
- Then opens: `conversations.scene.work.priest.followup`
- …where the player's next choices will be: "What's the hardest part of a long vigil?" | "I'll leave you to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "A winter with no accusation."
       spoken on: conversations.scene.work.priest.the_register.succeeded.respond, button `note_the_reading`
       leaves the player on: conversations.scene.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.the_register.succeeded.acknowledged`: the villager accepts. Subject `work.priest.the_register`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.the_register.succeeded.acknowledged/1   [128 chars]
    en  One winter. I have written it in the book as one winter and not as a victory, because eleven years has taught me the difference.
    >>  ............................................
    pt  Um inverno. Anotei no livro como um inverno e não como vitória, porque onze anos me ensinaram a diferença.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.succeeded.acknowledged/2   [128 chars]
    en  Thank you. It may have been a mild season and a good year for everybody. The register will tell me in four years and not before.
    >>  ............................................
    pt  Obrigada. Pode ter sido uma estação amena e um ano bom para todo mundo. O registro vai me dizer em quatro anos e não antes.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.succeeded.acknowledged/3   [140 chars]
    en  The person who copied out the tally is the result, more than the winter. A record that only one person keeps is one funeral from being lost.
    >>  ............................................
    pt  Quem copiou a contagem é o resultado, mais do que o inverno. Um registro que só uma pessoa mantém está a um funeral de se perder.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the chapel."

*stance family `exit` · tone `plain` · answers the beat(s) `work.priest.the_register.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.priest.the_register.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.priest.the_register.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the chapel.
    >>  ............................................
    pt  Vou deixar você voltar à capela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the chapel."
       spoken on: conversations.scene.work.priest.the_register.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.topic.work.priest.craft.respond / leave; conversations.topic.work.priest.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.priest.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.priest.craft` — e.g. "Nothing I do is doctrine. It's sitting in rooms and not filling the silence, and that took twenty years."


```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.priest.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.priest.craft.respond   [27 chars]
    en  That's the substance of it.
    >>  ............................................
    pt  É essa a substância.
    >>  ............................................
```


### Button `ask_silence` — "Why does the pause matter so much?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.craft` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.craft.ask_silence` — accepted phrasings: "why does the pause matter so much"
  - the message must contain one of: `pause`, `matter`, `restraint`
  - scored words: `pause`(1.5), `matter`(1.0), `restraint`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.craft.respond.ask_silence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.craft.respond.ask_silence   [34 chars]
    en  Why does the pause matter so much?
    >>  ............................................
    pt  Por que a pausa importa tanto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.craft.ask_silence`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.craft.ask_silence
WHO    VILLAGER — what the player reads after pressing "Why does the pause matter so much?"
       spoken on: conversations.topic.work.priest.craft.respond, button `ask_silence`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.craft.ask_silence`: the villager explains. Subject `work.priest.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.craft.ask_silence/1   [104 chars]
    en  Because what they came to say is behind the third thing they say, and answering stops them at the first.
    >>  ............................................
    pt  Porque o que vieram dizer está atrás da terceira coisa que dizem, e responder os para na primeira.
    >>  ............................................
  dialogue.conversations.work.prof.priest.craft.ask_silence/2   [89 chars]
    en  Because a room with a silence in it is a room where somebody can change their mind, %1$s.
    >>  ............................................
    pt  Porque um cômodo com silêncio é um cômodo onde alguém pode mudar de ideia, %1$s.
    >>  ............................................
```


### Button `admire` — "Twenty years to unlearn answering is an honest account."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.priest.craft` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.craft.admire` — accepted phrasings: "twenty years to unlearn answering is an honest account"
  - the message must contain one of: `unlearn`, `answering`, `honest`
  - scored words: `unlearn`(1.5), `answering`(1.2), `honest`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.craft.respond.admire   [55 chars]
    en  Twenty years to unlearn answering is an honest account.
    >>  ............................................
    pt  Vinte anos pra desaprender a responder é um relato honesto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.priest.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.priest.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.craft.admire
WHO    VILLAGER — what the player reads after pressing "Twenty years to unlearn answering is an honest account."
       spoken on: conversations.topic.work.priest.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.craft.admire`: the villager accepts. Subject `work.priest.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.craft.admire/1   [85 chars]
    en  It's also an embarrassing one, and I give it to every young cleric who'll sit for it.
    >>  ............................................
    pt  Também é constrangedor, e eu conto a todo clérigo jovem que sentar pra ouvir.
    >>  ............................................
  dialogue.conversations.work.prof.priest.craft.admire/2   [99 chars]
    en  Most of my trade won't say it, %1$s, because the answering is what people think they're paying for.
    >>  ............................................
    pt  Quase ninguém do meu ofício diz, %1$s, porque responder é o que acham que estão pagando.
    >>  ............................................
```


### Button `ask_third` — "What's the third thing they say?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.craft` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.craft.ask_third` — accepted phrasings: "what's the third thing they say"
  - the message must contain one of: `third`, `true`
  - scored words: `third`(1.5), `say`(0.5), `true`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.craft.respond.ask_third
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.craft.respond.ask_third   [32 chars]
    en  What's the third thing they say?
    >>  ............................................
    pt  Qual é a terceira coisa que dizem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.craft.ask_third`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.craft.ask_third
WHO    VILLAGER — what the player reads after pressing "What's the third thing they say?"
       spoken on: conversations.topic.work.priest.craft.respond, button `ask_third`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.craft.ask_third`: the villager explains. Subject `work.priest.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.craft.ask_third/1   [104 chars]
    en  The true one. The first is why they came, the second is what they've rehearsed, the third is the reason.
    >>  ............................................
    pt  A verdadeira. A primeira é por que vieram, a segunda é o que ensaiaram, a terceira é o motivo.
    >>  ............................................
  dialogue.conversations.work.prof.priest.craft.ask_third/2   [96 chars]
    en  It's different every time and it always arrives in the same place, %1$s, about forty minutes in.
    >>  ............................................
    pt  É diferente toda vez e sempre chega no mesmo ponto, %1$s, uns quarenta minutos depois.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.priest.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.craft.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.priest.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.priest.followup`

**Reached from 20 route(s):** `conversations.scene.work.priest.followup` / `ask_more`; `conversations.topic.work.priest.craft.respond` / `ask_silence`; `conversations.topic.work.priest.craft.respond` / `admire`; `conversations.topic.work.priest.craft.respond` / `ask_third`; `conversations.topic.work.priest.future.respond` / `ask_public`; `conversations.topic.work.priest.future.respond` / `encourage`; `conversations.topic.work.priest.future.respond` / `ask_keeper`; `conversations.topic.work.priest.respond` / `ask_hard`; `conversations.topic.work.priest.respond` / `value`; `conversations.topic.work.priest.respond` / `challenge`; `conversations.topic.work.priest.respond` / `challenge`; `conversations.topic.work.priest.risk.respond` / `ask_likely` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.priest.challenge.landed` — e.g. "Sometimes. Frightened and prepared is better than calm and surprised."
- `conversations.work.prof.priest.challenge.stung` — e.g. "...I frighten nobody who wasn't already lying awake."
- `conversations.work.prof.priest.craft.admire` — e.g. "It's also an embarrassing one, and I give it to every young cleric who'll sit for it."
- `conversations.work.prof.priest.craft.ask_silence` — e.g. "Because what they came to say is behind the third thing they say, and answering stops them at the first."
- `conversations.work.prof.priest.craft.ask_third` — e.g. "The true one. The first is why they came, the second is what they've rehearsed, the third is the reason."
- `conversations.work.prof.priest.future.ask_keeper` — e.g. "Somebody who visits without a reason and is welcome anyway. That's a rarer person than a good cleric."
- `conversations.work.prof.priest.future.ask_public` — e.g. "About the rule, yes. A valley that watches two careful people disagree learns something a sermon can't teach."
- `conversations.work.prof.priest.future.encourage` — e.g. "...At the reading. That's a better occasion than I'd have chosen and I'd not have chosen one at all."
- `conversations.work.prof.priest.hard` — e.g. "The truth, slowly, and then I sit down and let them be angry at me. It helps someone."
- `conversations.work.prof.priest.risk.ask_hunter` — e.g. "He is the fortnight, most of it. We disagree about everything except how long to wait."
- `conversations.work.prof.priest.risk.ask_likely` — e.g. "Believing the calm one and doubting the frantic one. Calm is easier to sit with and it means nothing."
- `conversations.work.prof.priest.risk.sympathise` — e.g. "...It's what I have. A fortnight is often enough for the thing to explain itself, and often isn't always."
- `conversations.work.prof.priest.task.ask_sentences` — e.g. "The ones that would make them feel certain. Certainty is what I'm here to take away, not to hand out."
- `conversations.work.prof.priest.task.ask_wrong_thing` — e.g. "Their neighbour. It's almost always their neighbour, and it is almost never their neighbour."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.priest.followup   [24 chars]
    en  That's the parish, then.
    >>  ............................................
    pt  É a paróquia, então.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.priest.challenge.landed`, `work.priest.challenge.stung`, `work.priest.craft.admire`, `work.priest.craft.ask_silence`, `work.priest.craft.ask_third`, `work.priest.future.ask_keeper`, `work.priest.future.ask_public`, `work.priest.future.encourage`, `work.priest.hard`, `work.priest.risk.ask_hunter`, `work.priest.risk.ask_likely`, `work.priest.risk.sympathise`, `work.priest.task.ask_sentences`, `work.priest.task.ask_wrong_thing`, `work.priest.task.offer_hands`, `work.priest.value`, `work.priest.village.ask_close`, `work.priest.village.ask_list`, `work.priest.village.say_thanks` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.priest.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `logistics`, `ritual`
  - scored words: `thought`(1.2), `logistics`(1.5), `ritual`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.priest.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.priest.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.priest.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.priest.thanks`: the villager accepts. Subject `work.priest.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.thanks/1   [70 chars]
    en  Few do. It looks like ritual from the pews and it is mostly logistics.
    >>  ............................................
    pt  Poucos pensam. Do banco parece ritual e é quase tudo logística.
    >>  ............................................
  dialogue.conversations.work.prof.priest.thanks/2   [69 chars]
    en  The rites are the short part, %1$s. The sitting-with is the long one.
    >>  ............................................
    pt  Os ritos são a parte curta, %1$s. Ficar sentado com alguém é a longa.
    >>  ............................................
```


### Button `ask_more` — "What can't you protect against?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.challenge.landed`, `work.priest.challenge.stung`, `work.priest.craft.admire`, `work.priest.craft.ask_silence`, `work.priest.craft.ask_third`, `work.priest.future.ask_keeper`, `work.priest.future.ask_public`, `work.priest.future.encourage`, `work.priest.hard`, `work.priest.risk.ask_hunter`, `work.priest.risk.ask_likely`, `work.priest.risk.sympathise`, `work.priest.task.ask_sentences`, `work.priest.task.ask_wrong_thing`, `work.priest.task.offer_hands`, `work.priest.value`, `work.priest.village.ask_close`, `work.priest.village.ask_list`, `work.priest.village.say_thanks` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.priest.more` — accepted phrasings: "what can't you protect against"
  - the message must contain one of: `protect`, `powerless`
  - scored words: `protect`(1.5), `against`(0.8), `powerless`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.followup.ask_more   [31 chars]
    en  What can't you protect against?
    >>  ............................................
    pt  Contra o que você não consegue proteger?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.priest.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.more
WHO    VILLAGER — what the player reads after pressing "What can't you protect against?"
       spoken on: conversations.topic.work.priest.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.priest.more`: the villager discloses. Subject `work.priest.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.more/1   [70 chars]
    en  Being invited in. Nothing I have works against a door somebody opened.
    >>  ............................................
    pt  Ser convidado a entrar. Nada do que eu tenho funciona contra uma porta que alguém abriu.
    >>  ............................................
  dialogue.conversations.work.prof.priest.more/2   [61 chars]
    en  Grief. I've never found a rite for it, and I've looked, %1$s.
    >>  ............................................
    pt  O luto. Nunca achei um rito pra ele, e eu procurei, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. I keep a list of the families who never come and it is not a comfortable list.
    >>  ............................................
    pt  Ser convidado a entrar. Guardo uma lista das famílias que nunca vêm e não é uma lista confortável.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. Twenty years of holding a fortnight open with nothing but my own patience.
    >>  ............................................
    pt  Uma regra escrita. Vinte anos segurando uma quinzena aberta com nada além da minha paciência.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. It comes when it comes. Turning up without a reason is most of how it comes.
    >>  ............................................
    pt  Ser convidado a entrar. Vem quando vem. Aparecer sem motivo é quase todo o jeito de vir.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.priest.more/2
    en  A written rule, eventually. Nothing that important gets written quickly and I'd not want it to.
    >>  ............................................
    pt  Uma regra escrita, uma hora. Nada tão importante se escreve rápido e eu não gostaria que fosse.
    >>  ............................................
  confident.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. Nothing I have works against a door somebody opened.
    >>  ............................................
    pt  Ser convidado a entrar. Nada do que eu tenho funciona contra uma porta que alguém abriu.
    >>  ............................................
  confident.dialogue.conversations.work.prof.priest.more/2
    en  A written rule for when a watch may name somebody. There isn't one anywhere and I've looked.
    >>  ............................................
    pt  Uma regra escrita pra quando uma vigia pode nomear alguém. Não existe em lugar nenhum e eu procurei.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. Nothing I have works against a door somebody opened.
    >>  ............................................
    pt  Ser convidado a entrar. Nada do que eu tenho funciona contra uma porta que alguém abriu.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.priest.more/2
    en  A written rule for when a watch may name somebody. There isn't one anywhere and I've looked.
    >>  ............................................
    pt  Uma regra escrita pra quando uma vigia pode nomear alguém. Não existe em lugar nenhum e eu procurei.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. It's the only thing that works and it's the only thing I can't ask for.
    >>  ............................................
    pt  Ser convidado a entrar. É a única coisa que funciona e a única que eu não posso pedir.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. The hunter and I disagree about everything except how long to wait.
    >>  ............................................
    pt  Uma regra escrita. O caçador e eu discordamos de tudo menos de quanto esperar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. It's the only thing that works and it's the only thing I can't ask for.
    >>  ............................................
    pt  Ser convidado a entrar. É a única coisa que funciona e a única que eu não posso pedir.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. The hunter and I disagree about everything except how long to wait.
    >>  ............................................
    pt  Uma regra escrita. O caçador e eu discordamos de tudo menos de quanto esperar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. It's the only thing that works and it's the only thing I can't ask for.
    >>  ............................................
    pt  Ser convidado a entrar. É a única coisa que funciona e a única que eu não posso pedir.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. The hunter and I disagree about everything except how long to wait.
    >>  ............................................
    pt  Uma regra escrita. O caçador e eu discordamos de tudo menos de quanto esperar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. I keep a list of the families who never come and it is not a comfortable list.
    >>  ............................................
    pt  Ser convidado a entrar. Guardo uma lista das famílias que nunca vêm e não é uma lista confortável.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. Twenty years of holding a fortnight open with nothing but my own patience.
    >>  ............................................
    pt  Uma regra escrita. Vinte anos segurando uma quinzena aberta com nada além da minha paciência.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. Nothing I have works against a door somebody opened.
    >>  ............................................
    pt  Ser convidado a entrar. Nada do que eu tenho funciona contra uma porta que alguém abriu.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.priest.more/2
    en  A written rule for when a watch may name somebody. There isn't one anywhere and I've looked.
    >>  ............................................
    pt  Uma regra escrita pra quando uma vigia pode nomear alguém. Não existe em lugar nenhum e eu procurei.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. Nothing I have works against a door somebody opened.
    >>  ............................................
    pt  Ser convidado a entrar. Nada do que eu tenho funciona contra uma porta que alguém abriu.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.priest.more/2
    en  A written rule for when a watch may name somebody. There isn't one anywhere and I've looked.
    >>  ............................................
    pt  Uma regra escrita pra quando uma vigia pode nomear alguém. Não existe em lugar nenhum e eu procurei.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. A door somebody opened is the only door I have ever got through.
    >>  ............................................
    pt  Ser convidado a entrar. Uma porta que alguém abriu é a única por que eu já passei.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. Three nights of watching, two witnesses, and never on the word of somebody who gains.
    >>  ............................................
    pt  Uma regra escrita. Três noites de observação, duas testemunhas, e nunca pela palavra de quem ganha.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. It comes when it comes. Turning up without a reason is most of how it comes.
    >>  ............................................
    pt  Ser convidado a entrar. Vem quando vem. Aparecer sem motivo é quase todo o jeito de vir.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.priest.more/2
    en  A written rule, eventually. Nothing that important gets written quickly and I'd not want it to.
    >>  ............................................
    pt  Uma regra escrita, uma hora. Nada tão importante se escreve rápido e eu não gostaria que fosse.
    >>  ............................................
  odd.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. A door somebody opened is the only door I have ever got through.
    >>  ............................................
    pt  Ser convidado a entrar. Uma porta que alguém abriu é a única por que eu já passei.
    >>  ............................................
  odd.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. Three nights of watching, two witnesses, and never on the word of somebody who gains.
    >>  ............................................
    pt  Uma regra escrita. Três noites de observação, duas testemunhas, e nunca pela palavra de quem ganha.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. It comes when it comes. Turning up without a reason is most of how it comes.
    >>  ............................................
    pt  Ser convidado a entrar. Vem quando vem. Aparecer sem motivo é quase todo o jeito de vir.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.priest.more/2
    en  A written rule, eventually. Nothing that important gets written quickly and I'd not want it to.
    >>  ............................................
    pt  Uma regra escrita, uma hora. Nada tão importante se escreve rápido e eu não gostaria que fosse.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in! Nothing I have works against a door somebody opened. It's the whole trick.
    >>  ............................................
    pt  Ser convidado a entrar! Nada do que eu tenho funciona contra uma porta que alguém abriu. É todo o truque.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.priest.more/2
    en  A written rule about naming people. Nobody wants to write it because it mostly says 'not yet'.
    >>  ............................................
    pt  Uma regra escrita sobre nomear gente. Ninguém quer escrever porque ela diz quase só 'ainda não'.
    >>  ............................................
  playful.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in! Nothing I have works against a door somebody opened. It's the whole trick.
    >>  ............................................
    pt  Ser convidado a entrar! Nada do que eu tenho funciona contra uma porta que alguém abriu. É todo o truque.
    >>  ............................................
  playful.dialogue.conversations.work.prof.priest.more/2
    en  A written rule about naming people. Nobody wants to write it because it mostly says 'not yet'.
    >>  ............................................
    pt  Uma regra escrita sobre nomear gente. Ninguém quer escrever porque ela diz quase só 'ainda não'.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. It comes when it comes. Turning up without a reason is most of how it comes.
    >>  ............................................
    pt  Ser convidado a entrar. Vem quando vem. Aparecer sem motivo é quase todo o jeito de vir.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.priest.more/2
    en  A written rule, eventually. Nothing that important gets written quickly and I'd not want it to.
    >>  ............................................
    pt  Uma regra escrita, uma hora. Nada tão importante se escreve rápido e eu não gostaria que fosse.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. I keep a list of the families who never come and it is not a comfortable list.
    >>  ............................................
    pt  Ser convidado a entrar. Guardo uma lista das famílias que nunca vêm e não é uma lista confortável.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. Twenty years of holding a fortnight open with nothing but my own patience.
    >>  ............................................
    pt  Uma regra escrita. Vinte anos segurando uma quinzena aberta com nada além da minha paciência.
    >>  ............................................
  shy.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in. A door somebody opened is the only door I have ever got through.
    >>  ............................................
    pt  Ser convidado a entrar. Uma porta que alguém abriu é a única por que eu já passei.
    >>  ............................................
  shy.dialogue.conversations.work.prof.priest.more/2
    en  A written rule. Three nights of watching, two witnesses, and never on the word of somebody who gains.
    >>  ............................................
    pt  Uma regra escrita. Três noites de observação, duas testemunhas, e nunca pela palavra de quem ganha.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in! Nothing I have works against a door somebody opened. It's the whole trick.
    >>  ............................................
    pt  Ser convidado a entrar! Nada do que eu tenho funciona contra uma porta que alguém abriu. É todo o truque.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.priest.more/2
    en  A written rule about naming people. Nobody wants to write it because it mostly says 'not yet'.
    >>  ............................................
    pt  Uma regra escrita sobre nomear gente. Ninguém quer escrever porque ela diz quase só 'ainda não'.
    >>  ............................................
  witty.dialogue.conversations.work.prof.priest.more/1
    en  Being invited in! Nothing I have works against a door somebody opened. It's the whole trick.
    >>  ............................................
    pt  Ser convidado a entrar! Nada do que eu tenho funciona contra uma porta que alguém abriu. É todo o truque.
    >>  ............................................
  witty.dialogue.conversations.work.prof.priest.more/2
    en  A written rule about naming people. Nobody wants to write it because it mostly says 'not yet'.
    >>  ............................................
    pt  Uma regra escrita sobre nomear gente. Ninguém quer escrever porque ela diz quase só 'ainda não'.
    >>  ............................................
```

</details>


### Button `leave` — "Keep well."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.priest.challenge.landed`, `work.priest.challenge.stung`, `work.priest.craft.admire`, `work.priest.craft.ask_silence`, `work.priest.craft.ask_third`, `work.priest.future.ask_keeper`, `work.priest.future.ask_public`, `work.priest.future.encourage`, `work.priest.hard`, `work.priest.risk.ask_hunter`, `work.priest.risk.ask_likely`, `work.priest.risk.sympathise`, `work.priest.task.ask_sentences`, `work.priest.task.ask_wrong_thing`, `work.priest.task.offer_hands`, `work.priest.value`, `work.priest.village.ask_close`, `work.priest.village.ask_list`, `work.priest.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.followup.leave   [10 chars]
    en  Keep well.
    >>  ............................................
    pt  Fique bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "Keep well."
       spoken on: conversations.topic.work.priest.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.priest.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.priest.future` — e.g. "Somebody has to keep the unwritten list after me. It's the only thing I do that couldn't be replaced by a book."


```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.priest.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.priest.future.respond   [22 chars]
    en  That's what I'd leave.
    >>  ............................................
    pt  É o que eu deixaria.
    >>  ............................................
```


### Button `ask_public` — "You'd argue with him in public now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.future` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.future.ask_public` — accepted phrasings: "you'd argue with him in public now"
  - the message must contain one of: `public`, `argue`, `openly`
  - scored words: `public`(1.5), `argue`(1.2), `openly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.future.respond.ask_public
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.future.respond.ask_public   [35 chars]
    en  You'd argue with him in public now?
    >>  ............................................
    pt  Você discutiria com ele em público agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.future.ask_public`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.future.ask_public
WHO    VILLAGER — what the player reads after pressing "You'd argue with him in public now?"
       spoken on: conversations.topic.work.priest.future.respond, button `ask_public`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.future.ask_public`: the villager explains. Subject `work.priest.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.future.ask_public/1   [109 chars]
    en  About the rule, yes. A valley that watches two careful people disagree learns something a sermon can't teach.
    >>  ............................................
    pt  Sobre a regra, sim. Um vale que vê duas pessoas cuidadosas discordarem aprende algo que um sermão não ensina.
    >>  ............................................
  dialogue.conversations.work.prof.priest.future.ask_public/2   [113 chars]
    en  Twenty years of agreeing in public has taught them that we're one thing, %1$s. We're not, and they should see it.
    >>  ............................................
    pt  Vinte anos concordando em público ensinou que somos uma coisa só, %1$s. Não somos, e eles deviam ver.
    >>  ............................................
```


### Button `encourage` — "Then argue at the reading of the rule. Let them watch."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.priest.future` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.future.encourage` — accepted phrasings: "then argue at the reading of the rule. let them watch"
  - the message must contain one of: `reading`, `watch`, `occasion`
  - scored words: `reading`(1.5), `watch`(1.0), `occasion`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.future.respond.encourage   [54 chars]
    en  Then argue at the reading of the rule. Let them watch.
    >>  ............................................
    pt  Então discuta na leitura da regra. Deixe que assistam.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.priest.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.priest.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then argue at the reading of the rule. Let them watch."
       spoken on: conversations.topic.work.priest.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.future.encourage`: the villager accepts. Subject `work.priest.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.future.encourage/1   [100 chars]
    en  ...At the reading. That's a better occasion than I'd have chosen and I'd not have chosen one at all.
    >>  ............................................
    pt  ...Na leitura. É uma ocasião melhor que a que eu teria escolhido e eu não teria escolhido nenhuma.
    >>  ............................................
  dialogue.conversations.work.prof.priest.future.encourage/2   [91 chars]
    en  He'd agree in a moment. He's been waiting twenty years for me to stop protecting him, %1$s.
    >>  ............................................
    pt  Ele concordaria na hora. Ele espera há vinte anos que eu pare de protegê-lo, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. I'd have chosen no occasion at all, which is the cowardice of it.
    >>  ............................................
    pt  ...Na leitura. Eu não teria escolhido ocasião nenhuma, e é essa a covardia.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years of protecting him from a thing he wanted.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos protegendo-o de algo que ele queria.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. Occasions matter more than I used to think, and I'm old enough to know.
    >>  ............................................
    pt  ...Na leitura. Ocasiões importam mais do que eu achava, e já tenho idade pra saber.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years is a long time to spare a man his own choice.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos é muito tempo pra poupar um homem da própria escolha.
    >>  ............................................
  confident.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. That's a better occasion than I'd have chosen.
    >>  ............................................
    pt  ...Na leitura. É uma ocasião melhor do que eu teria escolhido.
    >>  ............................................
  confident.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. He's waited twenty years for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos pra eu parar de protegê-lo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. That's a better occasion than I'd have chosen.
    >>  ............................................
    pt  ...Na leitura. É uma ocasião melhor do que eu teria escolhido.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. He's waited twenty years for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos pra eu parar de protegê-lo.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading, %1$s. A better occasion than I'd have chosen, and I'd have chosen none.
    >>  ............................................
    pt  ...Na leitura, %1$s. Ocasião melhor que a minha, e a minha seria nenhuma.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. He's been waiting twenty years for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos pra eu parar de protegê-lo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading, %1$s. A better occasion than I'd have chosen, and I'd have chosen none.
    >>  ............................................
    pt  ...Na leitura, %1$s. Ocasião melhor que a minha, e a minha seria nenhuma.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. He's been waiting twenty years for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos pra eu parar de protegê-lo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading, %1$s. A better occasion than I'd have chosen, and I'd have chosen none.
    >>  ............................................
    pt  ...Na leitura, %1$s. Ocasião melhor que a minha, e a minha seria nenhuma.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. He's been waiting twenty years for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos pra eu parar de protegê-lo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. I'd have chosen no occasion at all, which is the cowardice of it.
    >>  ............................................
    pt  ...Na leitura. Eu não teria escolhido ocasião nenhuma, e é essa a covardia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years of protecting him from a thing he wanted.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos protegendo-o de algo que ele queria.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. That's a better occasion than I'd have chosen.
    >>  ............................................
    pt  ...Na leitura. É uma ocasião melhor do que eu teria escolhido.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. He's waited twenty years for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos pra eu parar de protegê-lo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. That's a better occasion than I'd have chosen.
    >>  ............................................
    pt  ...Na leitura. É uma ocasião melhor do que eu teria escolhido.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. He's waited twenty years for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos pra eu parar de protegê-lo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. Better than none.
    >>  ............................................
    pt  ...Na leitura. Melhor que nenhuma.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree at once. He's waited twenty years.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. Occasions matter more than I used to think, and I'm old enough to know.
    >>  ............................................
    pt  ...Na leitura. Ocasiões importam mais do que eu achava, e já tenho idade pra saber.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years is a long time to spare a man his own choice.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos é muito tempo pra poupar um homem da própria escolha.
    >>  ............................................
  odd.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. Better than none.
    >>  ............................................
    pt  ...Na leitura. Melhor que nenhuma.
    >>  ............................................
  odd.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree at once. He's waited twenty years.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. Occasions matter more than I used to think, and I'm old enough to know.
    >>  ............................................
    pt  ...Na leitura. Ocasiões importam mais do que eu achava, e já tenho idade pra saber.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years is a long time to spare a man his own choice.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos é muito tempo pra poupar um homem da própria escolha.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading! That's a far better occasion than I'd have chosen, which was none.
    >>  ............................................
    pt  ...Na leitura! É uma ocasião bem melhor que a minha, que era nenhuma.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years of waiting for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos esperando eu parar de protegê-lo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading! That's a far better occasion than I'd have chosen, which was none.
    >>  ............................................
    pt  ...Na leitura! É uma ocasião bem melhor que a minha, que era nenhuma.
    >>  ............................................
  playful.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years of waiting for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos esperando eu parar de protegê-lo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. Occasions matter more than I used to think, and I'm old enough to know.
    >>  ............................................
    pt  ...Na leitura. Ocasiões importam mais do que eu achava, e já tenho idade pra saber.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years is a long time to spare a man his own choice.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos é muito tempo pra poupar um homem da própria escolha.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. I'd have chosen no occasion at all, which is the cowardice of it.
    >>  ............................................
    pt  ...Na leitura. Eu não teria escolhido ocasião nenhuma, e é essa a covardia.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years of protecting him from a thing he wanted.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos protegendo-o de algo que ele queria.
    >>  ............................................
  shy.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading. Better than none.
    >>  ............................................
    pt  ...Na leitura. Melhor que nenhuma.
    >>  ............................................
  shy.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree at once. He's waited twenty years.
    >>  ............................................
    pt  Ele concordaria na hora. Esperou vinte anos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading! That's a far better occasion than I'd have chosen, which was none.
    >>  ............................................
    pt  ...Na leitura! É uma ocasião bem melhor que a minha, que era nenhuma.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years of waiting for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos esperando eu parar de protegê-lo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.priest.future.encourage/1
    en  ...At the reading! That's a far better occasion than I'd have chosen, which was none.
    >>  ............................................
    pt  ...Na leitura! É uma ocasião bem melhor que a minha, que era nenhuma.
    >>  ............................................
  witty.dialogue.conversations.work.prof.priest.future.encourage/2
    en  He'd agree in a moment. Twenty years of waiting for me to stop protecting him.
    >>  ............................................
    pt  Ele concordaria na hora. Vinte anos esperando eu parar de protegê-lo.
    >>  ............................................
```

</details>


### Button `ask_keeper` — "Who could keep an unwritten list?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.future` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.future.ask_keeper` — accepted phrasings: "who could keep an unwritten list"
  - the message must contain one of: `keep`, `unwritten`, `successor`
  - scored words: `keep`(1.2), `unwritten`(1.5), `successor`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.future.respond.ask_keeper
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.future.respond.ask_keeper   [33 chars]
    en  Who could keep an unwritten list?
    >>  ............................................
    pt  Quem poderia guardar uma lista não escrita?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.future.ask_keeper`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.future.ask_keeper
WHO    VILLAGER — what the player reads after pressing "Who could keep an unwritten list?"
       spoken on: conversations.topic.work.priest.future.respond, button `ask_keeper`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.future.ask_keeper`: the villager explains. Subject `work.priest.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.future.ask_keeper/1   [101 chars]
    en  Somebody who visits without a reason and is welcome anyway. That's a rarer person than a good cleric.
    >>  ............................................
    pt  Alguém que visita sem motivo e é bem-vindo assim mesmo. É uma pessoa mais rara que um bom clérigo.
    >>  ............................................
  dialogue.conversations.work.prof.priest.future.ask_keeper/2   [79 chars]
    en  The cook, honestly, %1$s. She knows who's short before I know who's frightened.
    >>  ............................................
    pt  O cozinheiro, sinceramente, %1$s. Ele sabe quem está apertado antes de eu saber quem está com medo.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.priest.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.future.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.priest.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.priest.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.priest` — e.g. "I keep the faith and the holy water stocked. In this region, both run out faster than you'd hope."


```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.priest.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.priest.respond   [39 chars]
    en  That's the parish, reflections and all.
    >>  ............................................
    pt  É a paróquia, reflexos e tudo.
    >>  ............................................
```


### Button `ask_hard` — "What do you say to the family?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.identity` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.hard` — accepted phrasings: "what do you say to the family"
  - the message must contain one of: `family`, `tell`, `afflicted`
  - scored words: `family`(1.5), `tell`(1.0), `afflicted`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.respond.ask_hard   [30 chars]
    en  What do you say to the family?
    >>  ............................................
    pt  O que você diz para a família?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.priest.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.hard
WHO    VILLAGER — what the player reads after pressing "What do you say to the family?"
       spoken on: conversations.topic.work.priest.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.hard`: the villager explains. Subject `work.priest.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.followup / ask_more
```

> Written out in full under **`conversations.scene.work.priest.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You stayed when the last one left."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.priest.identity` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.value` — accepted phrasings: "you stayed when the last one left"
  - the message must contain one of: `stayed`, `left`, `remained`
  - scored words: `stayed`(1.5), `left`(1.0), `remained`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.respond.value   [34 chars]
    en  You stayed when the last one left.
    >>  ............................................
    pt  Você ficou quando o anterior foi embora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.priest.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.priest.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.value
WHO    VILLAGER — what the player reads after pressing "You stayed when the last one left."
       spoken on: conversations.topic.work.priest.respond, button `value`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.value`: the villager accepts. Subject `work.priest.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.value/1   [70 chars]
    en  ...I did. I'd not thought anyone remembered that there was a last one.
    >>  ............................................
    pt  ...Fiquei. Não achei que alguém lembrasse que houve um anterior.
    >>  ............................................
  dialogue.conversations.work.prof.priest.value/2   [66 chars]
    en  Somebody had to be here at the wrong hour. It turned out to be me.
    >>  ............................................
    pt  Alguém tinha que estar aqui na hora errada. Acabou sendo eu.
    >>  ............................................
```


### Button `challenge` — "You're frightening people."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.priest.identity` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.challenge` — accepted phrasings: "you're frightening people"
  - the message must contain one of: `frightening`, `scaring`, `panic`
  - scored words: `frightening`(1.5), `scaring`(1.5), `panic`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.respond.challenge   [26 chars]
    en  You're frightening people.
    >>  ............................................
    pt  Você está assustando as pessoas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.priest.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.priest.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You're frightening people."
       spoken on: conversations.topic.work.priest.respond, button `challenge`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.challenge.landed`: the villager resists. Subject `work.priest.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.challenge.landed/1   [69 chars]
    en  Sometimes. Frightened and prepared is better than calm and surprised.
    >>  ............................................
    pt  Às vezes. Assustado e preparado é melhor que calmo e surpreendido.
    >>  ............................................
  dialogue.conversations.work.prof.priest.challenge.landed/2   [77 chars]
    en  That's a fair charge, %1$s, and I weigh it every time I speak from the front.
    >>  ............................................
    pt  É uma acusação justa, %1$s, e eu peso ela toda vez que falo lá da frente.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.priest.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.priest.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You're frightening people."
       spoken on: conversations.topic.work.priest.respond, button `challenge`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.challenge.stung`: the villager resists. Subject `work.priest.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.challenge.stung/1   [52 chars]
    en  ...I frighten nobody who wasn't already lying awake.
    >>  ............................................
    pt  ...Eu não assusto ninguém que já não estivesse acordado à noite.
    >>  ............................................
  dialogue.conversations.work.prof.priest.challenge.stung/2   [71 chars]
    en  Frightening people. Right. Come to a service before you say that again.
    >>  ............................................
    pt  Assustando as pessoas. Certo. Venha a um culto antes de repetir isso.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.priest.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.priest.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.priest.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.priest.risk` — e.g. "A frightened valley will find somebody to blame within a fortnight. My part is to make that fortnight longer."


```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.priest.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.priest.risk.respond   [32 chars]
    en  That's what's actually at stake.
    >>  ............................................
    pt  É isso que está em jogo.
    >>  ............................................
```


### Button `ask_likely` — "What's the way you're most likely to be wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.risk` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.risk.ask_likely` — accepted phrasings: "what's the way you're most likely to be wrong"
  - the message must contain one of: `likely`, `wrong`, `mistake`
  - scored words: `likely`(1.5), `wrong`(1.0), `mistake`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.risk.respond.ask_likely
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.risk.respond.ask_likely   [46 chars]
    en  What's the way you're most likely to be wrong?
    >>  ............................................
    pt  Qual é o jeito que você tem mais chance de errar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.risk.ask_likely`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.risk.ask_likely
WHO    VILLAGER — what the player reads after pressing "What's the way you're most likely to be wrong?"
       spoken on: conversations.topic.work.priest.risk.respond, button `ask_likely`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.risk.ask_likely`: the villager explains. Subject `work.priest.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.risk.ask_likely/1   [101 chars]
    en  Believing the calm one and doubting the frantic one. Calm is easier to sit with and it means nothing.
    >>  ............................................
    pt  Acreditar no calmo e duvidar do desesperado. Calmo é mais fácil de aguentar e não significa nada.
    >>  ............................................
  dialogue.conversations.work.prof.priest.risk.ask_likely/2   [96 chars]
    en  Taking a family's word because I've known them thirty years, %1$s. Thirty years is not evidence.
    >>  ............................................
    pt  Aceitar a palavra de uma família porque eu os conheço há trinta anos, %1$s. Trinta anos não é prova.
    >>  ............................................
```


### Button `sympathise` — "Making the fortnight longer is a thin thing to hold a valley with."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.priest.risk` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.risk.sympathise` — accepted phrasings: "making the fortnight longer is a thin thing to hold a valley with"
  - the message must contain one of: `fortnight`, `thin`, `hold`
  - scored words: `fortnight`(1.5), `thin`(1.2), `hold`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.risk.respond.sympathise   [66 chars]
    en  Making the fortnight longer is a thin thing to hold a valley with.
    >>  ............................................
    pt  Fazer a quinzena durar é uma coisa fina pra segurar um vale.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.priest.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.priest.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Making the fortnight longer is a thin thing to hold a valley with."
       spoken on: conversations.topic.work.priest.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.risk.sympathise`: the villager accepts. Subject `work.priest.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.risk.sympathise/1   [105 chars]
    en  ...It's what I have. A fortnight is often enough for the thing to explain itself, and often isn't always.
    >>  ............................................
    pt  ...É o que eu tenho. Uma quinzena costuma bastar pra coisa se explicar, e costuma não é sempre.
    >>  ............................................
  dialogue.conversations.work.prof.priest.risk.sympathise/2   [90 chars]
    en  Thin is the word. And I've been holding it for twenty years, %1$s, and it hasn't torn yet.
    >>  ............................................
    pt  Fina é a palavra. E eu venho segurando há vinte anos, %1$s, e ainda não rasgou.
    >>  ............................................
```


### Button `ask_hunter` — "Does the hunter share the fortnight?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.risk` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.risk.ask_hunter` — accepted phrasings: "does the hunter share the fortnight"
  - the message must contain one of: `hunter`, `fortnight`, `together`
  - scored words: `hunter`(1.5), `fortnight`(1.0), `together`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.risk.respond.ask_hunter
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.risk.respond.ask_hunter   [36 chars]
    en  Does the hunter share the fortnight?
    >>  ............................................
    pt  O caçador divide a quinzena?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.risk.ask_hunter`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.risk.ask_hunter
WHO    VILLAGER — what the player reads after pressing "Does the hunter share the fortnight?"
       spoken on: conversations.topic.work.priest.risk.respond, button `ask_hunter`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.risk.ask_hunter`: the villager explains. Subject `work.priest.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.risk.ask_hunter/1   [86 chars]
    en  He is the fortnight, most of it. We disagree about everything except how long to wait.
    >>  ............................................
    pt  Ele é a quinzena, quase toda. Discordamos sobre tudo exceto quanto esperar.
    >>  ............................................
  dialogue.conversations.work.prof.priest.risk.ask_hunter/2   [80 chars]
    en  He's better at it than I am, %1$s, and I have never told him so, and I ought to.
    >>  ............................................
    pt  Ele é melhor nisso que eu, %1$s, e eu nunca disse isso a ele, e eu deveria.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.priest.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.risk.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.priest.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.priest.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.priest.task` — e.g. "Sitting with a family that's frightened of the wrong thing. It takes an afternoon and it is the whole of the work."


```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.priest.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.priest.task.respond   [21 chars]
    en  That's the afternoon.
    >>  ............................................
    pt  É a tarde.
    >>  ............................................
```


### Button `ask_sentences` — "What are the two sentences?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.task` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.task.ask_sentences` — accepted phrasings: "what are the two sentences"
  - the message must contain one of: `sentences`, `remove`
  - scored words: `sentences`(1.5), `two`(0.8), `remove`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.task.respond.ask_sentences
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.task.respond.ask_sentences   [27 chars]
    en  What are the two sentences?
    >>  ............................................
    pt  Quais são as duas frases?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.task.ask_sentences`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.task.ask_sentences
WHO    VILLAGER — what the player reads after pressing "What are the two sentences?"
       spoken on: conversations.topic.work.priest.task.respond, button `ask_sentences`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.task.ask_sentences`: the villager explains. Subject `work.priest.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.task.ask_sentences/1   [101 chars]
    en  The ones that would make them feel certain. Certainty is what I'm here to take away, not to hand out.
    >>  ............................................
    pt  As que os fariam sentir certeza. Certeza é o que eu estou aqui pra tirar, não pra distribuir.
    >>  ............................................
  dialogue.conversations.work.prof.priest.task.ask_sentences/2   [92 chars]
    en  The ones that would get repeated in the square by Tuesday, %1$s, in a shape I didn't intend.
    >>  ............................................
    pt  As que seriam repetidas na praça até terça, %1$s, num formato que eu não pretendia.
    >>  ............................................
```


### Button `offer_hands` — "I could sit with them instead this afternoon."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.priest.task` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.task.offer_hands` — accepted phrasings: "i could sit with them instead this afternoon"
  - the message must contain one of: `sit`, `afternoon`
  - scored words: `sit`(1.5), `afternoon`(1.2), `them`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.task.respond.offer_hands   [45 chars]
    en  I could sit with them instead this afternoon.
    >>  ............................................
    pt  Eu podia sentar com eles esta tarde.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.priest.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.priest.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could sit with them instead this afternoon."
       spoken on: conversations.topic.work.priest.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.task.offer_hands`: the villager accepts. Subject `work.priest.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.task.offer_hands/1   [104 chars]
    en  ...Not instead. With, if you like, and say nothing at all for the first hour. That's the difficult part.
    >>  ............................................
    pt  ...Não em vez. Junto, se quiser, e não diga nada na primeira hora. É essa a parte difícil.
    >>  ............................................
  dialogue.conversations.work.prof.priest.task.offer_hands/2   [94 chars]
    en  They'd let you in and they'd talk to you differently, %1$s, and I'd learn something from that.
    >>  ............................................
    pt  Eles te deixariam entrar e falariam diferente com você, %1$s, e eu aprenderia algo com isso.
    >>  ............................................
```


### Button `ask_wrong_thing` — "What are they frightened of?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.task` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.task.ask_wrong_thing` — accepted phrasings: "what are they frightened of"
  - the message must contain one of: `frightened`, `neighbour`, `afraid`
  - scored words: `frightened`(1.5), `neighbour`(1.2), `afraid`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.task.respond.ask_wrong_thing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.task.respond.ask_wrong_thing   [28 chars]
    en  What are they frightened of?
    >>  ............................................
    pt  Do que eles têm medo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.task.ask_wrong_thing`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.task.ask_wrong_thing
WHO    VILLAGER — what the player reads after pressing "What are they frightened of?"
       spoken on: conversations.topic.work.priest.task.respond, button `ask_wrong_thing`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.task.ask_wrong_thing`: the villager explains. Subject `work.priest.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.task.ask_wrong_thing/1   [92 chars]
    en  Their neighbour. It's almost always their neighbour, and it is almost never their neighbour.
    >>  ............................................
    pt  Do vizinho. É quase sempre o vizinho, e quase nunca é o vizinho.
    >>  ............................................
  dialogue.conversations.work.prof.priest.task.ask_wrong_thing/2   [91 chars]
    en  A shape at a window that was a branch, %1$s. I've been out and looked at the branch myself.
    >>  ............................................
    pt  Uma forma numa janela que era um galho, %1$s. Eu mesmo fui olhar o galho.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.priest.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.task.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.priest.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.priest.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.priest.village` — e.g. "Twenty years and no one in this valley has been driven out on a rumour. That is the whole of my record."


```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.priest.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.priest.village.respond   [27 chars]
    en  That's what I've held here.
    >>  ............................................
    pt  É o que eu segurei aqui.
    >>  ............................................
```


### Button `ask_list` — "What do you do about the list?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.village` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.village.ask_list` — accepted phrasings: "what do you do about the list"
  - the message must contain one of: `list`, `visit`
  - scored words: `list`(1.5), `about`(0.4), `visit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.village.respond.ask_list
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.village.respond.ask_list   [30 chars]
    en  What do you do about the list?
    >>  ............................................
    pt  O que você faz com a lista?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.village.ask_list`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.village.ask_list
WHO    VILLAGER — what the player reads after pressing "What do you do about the list?"
       spoken on: conversations.topic.work.priest.village.respond, button `ask_list`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.village.ask_list`: the villager explains. Subject `work.priest.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.village.ask_list/1   [105 chars]
    en  I turn up. Not to talk — to return a borrowed dish, or to ask about a fence. It takes years and it works.
    >>  ............................................
    pt  Eu apareço. Não pra falar — pra devolver um prato emprestado, ou perguntar de uma cerca. Leva anos e funciona.
    >>  ............................................
  dialogue.conversations.work.prof.priest.village.ask_list/2   [86 chars]
    en  The cleric and I compare lists twice a year, %1$s, and neither of us writes them down.
    >>  ............................................
    pt  A clériga e eu comparamos listas duas vezes por ano, %1$s, e nenhum de nós anota.
    >>  ............................................
```


### Button `say_thanks` — "Nobody driven out in twenty years is the record that counts."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.priest.village` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.village.say_thanks` — accepted phrasings: "nobody driven out in twenty years is the record that counts"
  - the message must contain one of: `driven`, `twenty`, `record`
  - scored words: `driven`(1.5), `twenty`(1.0), `record`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.village.respond.say_thanks   [60 chars]
    en  Nobody driven out in twenty years is the record that counts.
    >>  ............................................
    pt  Ninguém expulso em vinte anos é o recorde que conta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.priest.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.priest.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Nobody driven out in twenty years is the record that counts."
       spoken on: conversations.topic.work.priest.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.village.say_thanks`: the villager accepts. Subject `work.priest.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.village.say_thanks/1   [103 chars]
    en  ...It is, and it's a record of things that didn't happen, and that has not been called a record before.
    >>  ............................................
    pt  ...É, e é um recorde de coisas que não aconteceram, e isso não foi chamado de recorde antes.
    >>  ............................................
  dialogue.conversations.work.prof.priest.village.say_thanks/2   [92 chars]
    en  It isn't only mine. It's the hunter's too, %1$s, and he'd say the same about me and mean it.
    >>  ............................................
    pt  Não é só meu. É do caçador também, %1$s, e ele diria o mesmo de mim e falaria sério.
    >>  ............................................
```


### Button `ask_close` — "Has it ever come close?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.priest.village` · offered only once the villager has actually said `work:priest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.priest.village.ask_close` — accepted phrasings: "has it ever come close"
  - the message must contain one of: `close`, `nearly`
  - scored words: `close`(1.5), `nearly`(1.2), `happened`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.village.respond.ask_close
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.village.respond.ask_close   [23 chars]
    en  Has it ever come close?
    >>  ............................................
    pt  Já chegou perto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.priest.village.ask_close`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.priest.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What can't you protect against?" | "Keep well."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.village.ask_close
WHO    VILLAGER — what the player reads after pressing "Has it ever come close?"
       spoken on: conversations.topic.work.priest.village.respond, button `ask_close`
       leaves the player on: conversations.topic.work.priest.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.village.ask_close`: the villager explains. Subject `work.priest.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.village.ask_close/1   [86 chars]
    en  Twice. Once it was four days from happening and I stood in a doorway and did not move.
    >>  ............................................
    pt  Duas vezes. Uma delas faltavam quatro dias e eu fiquei numa porta e não saí.
    >>  ............................................
  dialogue.conversations.work.prof.priest.village.ask_close/2   [91 chars]
    en  The second time it was the hunter who stood there, %1$s, and I was the one who fetched him.
    >>  ............................................
    pt  Na segunda vez foi o caçador que ficou lá, %1$s, e eu fui quem o chamou.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.priest.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.priest.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.priest.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.priest.village.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.priest.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.priest.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.left`: the villager accepts. Subject `work.priest.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.priest.blessing_refused.blocked.respond / leave; conversations.scene.work.priest.blessing_refused.succeeded.respond / leave; conversations.scene.work.priest.followup / leave; conversations.scene.work.priest.frightened_family.active.respond / leave; conversations.scene.work.priest.frightened_family.succeeded.respond / leave; conversations.scene.work.priest.the_register.active.respond / leave; conversations.scene.work.priest.the_register.succeeded.respond / leave; conversations.topic.work.priest.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.priest.blessing_refused.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

