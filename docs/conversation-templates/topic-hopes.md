# Topic: hopes

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `hopes` |
| Opened from | question `conversations.cat.personal`, button `hopes` |
| Depth class (its heart budget) | `deep` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `encouragement`, `challenge`, `dismissal`, `exit` |
| Narrative arc | `hopes`, max stage 2 |
| Milestones it can set | `hopes.named` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.hopes
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.hopes   [24 chars]
    en  What are you hoping for?
    >>  ............................................
    pt  O que você espera da vida?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.hopes.resume.followup`](#conversations-arc-hopes-resume-followup)
- [`conversations.arc.hopes.resume.respond`](#conversations-arc-hopes-resume-respond)
- [`conversations.scene.hopes.followup`](#conversations-scene-hopes-followup)
- [`conversations.scene.hopes.spring_list.respond`](#conversations-scene-hopes-spring-list-respond)
- [`conversations.scene.hopes.the_long_one.respond`](#conversations-scene-hopes-the-long-one-respond)
- [`conversations.topic.hopes.belittled.followup`](#conversations-topic-hopes-belittled-followup)
- [`conversations.topic.hopes.close`](#conversations-topic-hopes-close)
- [`conversations.topic.hopes.followup`](#conversations-topic-hopes-followup)
- [`conversations.topic.hopes.guarded.respond`](#conversations-topic-hopes-guarded-respond)
- [`conversations.topic.hopes.respond`](#conversations-topic-hopes-respond)
- [`conversations.topic.hopes.toddler.respond`](#conversations-topic-hopes-toddler-respond)
- [`conversations.topic.hopes.young.respond`](#conversations-topic-hopes-young-respond)

---

## `conversations.arc.hopes.resume.followup`

**Reached from 3 route(s):** `conversations.arc.hopes.resume.respond` / `still_hoping`; `conversations.arc.hopes.resume.respond` / `still_hoping`; `conversations.arc.hopes.resume.respond` / `offer_help`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.hopes.resume.offer_help` — e.g. "...You'd actually help. That changes it from a hope to a job of work."
- `conversations.hopes.resume.still_hoping.named` — e.g. "You remembered the shape of it, not just that there was one. Yes. Still."
- `conversations.hopes.resume.still_hoping.plain` — e.g. "Still. It hasn't moved, but neither have I stopped."


```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.hopes.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.hopes.resume.followup   [31 chars]
    en  That's the state of the hoping.
    >>  ............................................
    pt  É esse o estado da esperança.
    >>  ............................................
```


### Button `share_hope` — "I'm hoping for it too, now."

*stance family `encouragement` · tone `plain` · answers the beat(s) `hopes.resume.offer_help.to.hopes`, `hopes.resume.still_hoping.named.to.hopes`, `hopes.resume.still_hoping.plain.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.resume.followup.share_hope` — accepted phrasings: "i am hoping for it too now"; "i hope for it too now"; "i am hoping for that as well"
  - the message must contain one of: `hoping`, `too`
  - scored words: `hoping`(1.5), `too`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.followup.share_hope
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.hopes.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.hopes.resume.followup.share_hope   [27 chars]
    en  I'm hoping for it too, now.
    >>  ............................................
    pt  Agora eu também espero por isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.resume.followup.share_hope`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, familiarity +2  _(recorded under topic `hopes.resume.followup.share_hope`)_
- Then opens: `conversations.topic.hopes.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you mine, if you want it." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.followup.share_hope
WHO    VILLAGER — what the player reads after pressing "I'm hoping for it too, now."
       spoken on: conversations.arc.hopes.resume.followup, button `share_hope`
       leaves the player on: conversations.topic.hopes.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.followup.share_hope.to.hopes`: the villager accepts. Subject `hopes`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.resume.followup.share_hope/1   [87 chars]
    en  You too, now. That's twice the hoping and none of the extra work. I'll take that trade.
    >>  ............................................
    pt  Você também, agora. É o dobro de esperança e nenhum trabalho a mais. Aceito essa troca.
    >>  ............................................
  dialogue.conversations.hopes.resume.followup.share_hope/2   [90 chars]
    en  Then if it doesn't come, at least two of us will be disappointed. That's oddly comforting.
    >>  ............................................
    pt  Então se não vier, pelo menos dois vão se decepcionar. É estranhamente reconfortante.
    >>  ............................................
  dialogue.conversations.hopes.resume.followup.share_hope/3   [68 chars]
    en  Careful, %1$s — hoping alongside somebody is how you end up helping.
    >>  ............................................
    pt  Cuidado, %1$s — esperar junto de alguém é como a gente acaba ajudando.
    >>  ............................................
```


### Button `practical` — "What would move it along?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `hopes.resume.offer_help.to.hopes`, `hopes.resume.still_hoping.named.to.hopes`, `hopes.resume.still_hoping.plain.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.resume.followup.practical` — accepted phrasings: "what would move it along"; "what would move that along"; "how do we move it along"
  - the message must contain one of: `move`
  - scored words: `move`(1.6), `along`(1.1)

```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.followup.practical
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.hopes.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.hopes.resume.followup.practical   [25 chars]
    en  What would move it along?
    >>  ............................................
    pt  O que faria isso andar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.resume.followup.practical`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `hopes.resume.followup.practical`)_
- Then opens: `conversations.topic.hopes.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you mine, if you want it." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.followup.practical
WHO    VILLAGER — what the player reads after pressing "What would move it along?"
       spoken on: conversations.arc.hopes.resume.followup, button `practical`
       leaves the player on: conversations.topic.hopes.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.followup.practical.to.hopes`: the villager accepts. Subject `hopes`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.resume.followup.practical/1   [74 chars]
    en  Move it along. Right. Less hoping and more Tuesday, is what you're saying.
    >>  ............................................
    pt  Fazer andar. Certo. Menos esperança e mais terça-feira, é o que você está dizendo.
    >>  ............................................
  dialogue.conversations.hopes.resume.followup.practical/2   [62 chars]
    en  A dry spell and a spare afternoon. That's genuinely all of it.
    >>  ............................................
    pt  Uma semana sem chuva e uma tarde livre. É sinceramente tudo.
    >>  ............................................
  dialogue.conversations.hopes.resume.followup.practical/3   [67 chars]
    en  You'd turn a hope into a list. ...Go on, then. Let's make the list.
    >>  ............................................
    pt  Você transformaria uma esperança numa lista. ...Vai, então. Vamos fazer a lista.
    >>  ............................................
```


### Button `temper` — "Don't pin everything on it."

*stance family `candor` · tone `plain` · answers the beat(s) `hopes.resume.offer_help.to.hopes`, `hopes.resume.still_hoping.named.to.hopes`, `hopes.resume.still_hoping.plain.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.resume.followup.temper` — accepted phrasings: "do not pin everything on it"; "do not pin it all on this"; "do not put everything on that"
  - the message must contain one of: `pin`, `everything`
  - scored words: `pin`(1.6), `everything`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.followup.temper
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.hopes.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.hopes.resume.followup.temper   [27 chars]
    en  Don't pin everything on it.
    >>  ............................................
    pt  Não aposte tudo nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, tension +3  _(recorded under topic `hopes.resume.followup.temper`)_
- Then opens: `conversations.topic.hopes.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you mine, if you want it." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.followup.temper
WHO    VILLAGER — what the player reads after pressing "Don't pin everything on it."
       spoken on: conversations.arc.hopes.resume.followup, button `temper`
       leaves the player on: conversations.topic.hopes.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.followup.temper.to.hopes`: the villager accepts. Subject `hopes`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.resume.followup.temper/1   [73 chars]
    en  I know. I've been careful not to build the house before the ground's dry.
    >>  ............................................
    pt  Eu sei. Tenho tomado cuidado para não construir a casa antes de o chão secar.
    >>  ............................................
  dialogue.conversations.hopes.resume.followup.temper/2   [93 chars]
    en  That's the sensible thing to say and I don't like you for saying it. ...You're right, though.
    >>  ............................................
    pt  É a coisa sensata de dizer e eu não gosto de você por dizer. ...Mas você tem razão.
    >>  ............................................
  dialogue.conversations.hopes.resume.followup.temper/3   [85 chars]
    en  Everything on one hope. Aye. I've done that before, %1$s, and I remember the landing.
    >>  ............................................
    pt  Tudo numa esperança só. É. Já fiz isso antes, %1$s, e eu lembro do tombo.
    >>  ............................................
```


### Button `leave` — "I hope it comes."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.resume.offer_help.to.hopes`, `hopes.resume.still_hoping.named.to.hopes`, `hopes.resume.still_hoping.plain.to.hopes` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.hopes.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.hopes.resume.followup.leave   [16 chars]
    en  I hope it comes.
    >>  ............................................
    pt  Espero que venha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.followup.leave
WHO    VILLAGER — what the player reads after pressing "I hope it comes."
       spoken on: conversations.arc.hopes.resume.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.followup.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.resume.followup.leave/1   [46 chars]
    en  So do I. Thank you for hoping in my direction.
    >>  ............................................
    pt  Eu também. Obrigado por esperar na minha direção.
    >>  ............................................
  dialogue.conversations.hopes.resume.followup.leave/2   [25 chars]
    en  Right you are. We'll see.
    >>  ............................................
    pt  Isso mesmo. A gente vê.
    >>  ............................................
  dialogue.conversations.hopes.resume.followup.leave/3   [47 chars]
    en  Off you go, %1$s. Fingers crossed and all that.
    >>  ............................................
    pt  Pode ir, %1$s. Dedos cruzados e tal.
    >>  ............................................
```

---


## `conversations.arc.hopes.resume.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `hopes`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.hopes.revisit` — e.g. "I've thought more about what I said I was hoping for. There's one wish under all the others I didn't mention."


```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.hopes.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.hopes.resume.respond   [28 chars]
    en  That thing I was hoping for.
    >>  ............................................
    pt  Aquilo que eu estava esperando.
    >>  ............................................
```


### Button `still_hoping` — "Still hoping for it?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `hopes.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.resume.still_hoping` — accepted phrasings: "still hoping for it"; "are you still hoping"; "still hoping"
  - the message must contain one of: `still`, `hoping`
  - scored words: `still`(1.5), `hoping`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.respond.still_hoping
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.hopes.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.hopes.resume.respond.still_hoping   [20 chars]
    en  Still hoping for it?
    >>  ............................................
    pt  Ainda espera por isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when milestone `hopes.named` is set
- Does: **hearts +2** — decision id `hopes.resume.still_hoping`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `hopes.resume.still_hoping`)_
- Does: arc `hopes` — advance to stage 2
- Then opens: `conversations.arc.hopes.resume.followup`
- …where the player's next choices will be: "I'm hoping for it too, now." | "What would move it along?" | "Don't pin everything on it." | "I hope it comes."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.still_hoping.named
WHO    VILLAGER — what the player reads after pressing "Still hoping for it?"
       spoken on: conversations.arc.hopes.resume.respond, button `still_hoping`
       leaves the player on: conversations.arc.hopes.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.still_hoping.named.to.hopes`: the villager accepts. Subject `hopes`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.resume.still_hoping.named/1   [72 chars]
    en  You remembered the shape of it, not just that there was one. Yes. Still.
    >>  ............................................
    pt  Você lembrou do formato, não só de que existia. Sim. Ainda.
    >>  ............................................
  dialogue.conversations.hopes.resume.still_hoping.named/2   [61 chars]
    en  We worked out the first step, you and I. I've taken it. Once.
    >>  ............................................
    pt  A gente definiu o primeiro passo, você e eu. Eu dei. Uma vez.
    >>  ............................................
  dialogue.conversations.hopes.resume.still_hoping.named/3   [73 chars]
    en  Still hoping — and now it has a shape, which helps more than you'd think.
    >>  ............................................
    pt  Ainda esperando — e agora tem um formato, o que ajuda mais do que você imagina.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, %1$s. Not just that there was one. I'd not expected that.
    >>  ............................................
    pt  Você lembrou o formato, %1$s. Não só que existia uma. Eu não esperava.
    >>  ............................................
  anxious.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. I'd told myself it had gone in one ear, to save myself later.
    >>  ............................................
    pt  Você tinha os detalhes. Eu dizia a mim mesmo que tinha entrado por um ouvido, pra me poupar.
    >>  ............................................
  anxious.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Still, yes. And being asked about the right one makes it feel less foolish.
    >>  ............................................
    pt  Ainda, sim. E ser perguntado sobre a certa faz parecer menos bobo.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it. Yes. Still. It'll be still next year too.
    >>  ............................................
    pt  Você lembrou o formato. Sim. Ainda. Vai ser ainda ano que vem também.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details, months on. That's a better memory than most and it keeps.
    >>  ............................................
    pt  Você tinha os detalhes, meses depois. É uma memória melhor que a maioria e se conserva.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. Still hoping. Hopes that survive a year tend to survive the next one.
    >>  ............................................
    pt  Certo. Ainda esperando. Esperanças que sobrevivem um ano costumam sobreviver o próximo.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, not just that there was one. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato, não só que existia uma. Sim. Ainda.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. Most people keep the fact and lose the thing.
    >>  ............................................
    pt  Você tinha os detalhes. A maioria guarda o fato e perde a coisa.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. You remembered which hope. That's rarer than remembering there was one.
    >>  ............................................
    pt  Certo. Você lembrou qual esperança. É mais raro que lembrar que havia uma.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, not just that there was one. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato, não só que existia uma. Sim. Ainda.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. Most people keep the fact and lose the thing.
    >>  ............................................
    pt  Você tinha os detalhes. A maioria guarda o fato e perde a coisa.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. You remembered which hope. That's rarer than remembering there was one.
    >>  ............................................
    pt  Certo. Você lembrou qual esperança. É mais raro que lembrar que havia uma.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, %1$s. Not just that there was one. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato, %1$s. Não só que existia uma. Sim. Ainda.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. That's the part that tells me you were listening and not waiting.
    >>  ............................................
    pt  Você tinha os detalhes. É a parte que me diz que você escutava e não esperava a vez.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. You remembered which one, and that matters more than the answer does.
    >>  ............................................
    pt  Certo. Você lembrou qual, e isso importa mais que a resposta.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, %1$s. Not just that there was one. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato, %1$s. Não só que existia uma. Sim. Ainda.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. That's the part that tells me you were listening and not waiting.
    >>  ............................................
    pt  Você tinha os detalhes. É a parte que me diz que você escutava e não esperava a vez.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. You remembered which one, and that matters more than the answer does.
    >>  ............................................
    pt  Certo. Você lembrou qual, e isso importa mais que a resposta.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, %1$s. Not just that there was one. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato, %1$s. Não só que existia uma. Sim. Ainda.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. That's the part that tells me you were listening and not waiting.
    >>  ............................................
    pt  Você tinha os detalhes. É a parte que me diz que você escutava e não esperava a vez.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. You remembered which one, and that matters more than the answer does.
    >>  ............................................
    pt  Certo. Você lembrou qual, e isso importa mais que a resposta.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, %1$s. Not just that there was one. I'd not expected that.
    >>  ............................................
    pt  Você lembrou o formato, %1$s. Não só que existia uma. Eu não esperava.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. I'd told myself it had gone in one ear, to save myself later.
    >>  ............................................
    pt  Você tinha os detalhes. Eu dizia a mim mesmo que tinha entrado por um ouvido, pra me poupar.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Still, yes. And being asked about the right one makes it feel less foolish.
    >>  ............................................
    pt  Ainda, sim. E ser perguntado sobre a certa faz parecer menos bobo.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, not just that there was one. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato, não só que existia uma. Sim. Ainda.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. Most people keep the fact and lose the thing.
    >>  ............................................
    pt  Você tinha os detalhes. A maioria guarda o fato e perde a coisa.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. You remembered which hope. That's rarer than remembering there was one.
    >>  ............................................
    pt  Certo. Você lembrou qual esperança. É mais raro que lembrar que havia uma.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, not just that there was one. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato, não só que existia uma. Sim. Ainda.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. Most people keep the fact and lose the thing.
    >>  ............................................
    pt  Você tinha os detalhes. A maioria guarda o fato e perde a coisa.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. You remembered which hope. That's rarer than remembering there was one.
    >>  ............................................
    pt  Certo. Você lembrou qual esperança. É mais raro que lembrar que havia uma.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato. Sim. Ainda.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. Right.
    >>  ............................................
    pt  Você tinha os detalhes. Certo.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Still, yes. And you remembered which.
    >>  ............................................
    pt  Ainda, sim. E você lembrou qual.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it. Yes. Still. It'll be still next year too.
    >>  ............................................
    pt  Você lembrou o formato. Sim. Ainda. Vai ser ainda ano que vem também.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details, months on. That's a better memory than most and it keeps.
    >>  ............................................
    pt  Você tinha os detalhes, meses depois. É uma memória melhor que a maioria e se conserva.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. Still hoping. Hopes that survive a year tend to survive the next one.
    >>  ............................................
    pt  Certo. Ainda esperando. Esperanças que sobrevivem um ano costumam sobreviver o próximo.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato. Sim. Ainda.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. Right.
    >>  ............................................
    pt  Você tinha os detalhes. Certo.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Still, yes. And you remembered which.
    >>  ............................................
    pt  Ainda, sim. E você lembrou qual.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it. Yes. Still. It'll be still next year too.
    >>  ............................................
    pt  Você lembrou o formato. Sim. Ainda. Vai ser ainda ano que vem também.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details, months on. That's a better memory than most and it keeps.
    >>  ............................................
    pt  Você tinha os detalhes, meses depois. É uma memória melhor que a maioria e se conserva.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. Still hoping. Hopes that survive a year tend to survive the next one.
    >>  ............................................
    pt  Certo. Ainda esperando. Esperanças que sobrevivem um ano costumam sobreviver o próximo.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it! Not just that there was one. Yes. Still. Very much still.
    >>  ............................................
    pt  Você lembrou o formato! Não só que existia uma. Sim. Ainda. Muito ainda.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details! Most people keep the fact and mislay the thing entirely.
    >>  ............................................
    pt  Você tinha os detalhes! A maioria guarda o fato e perde a coisa.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right — you remembered which one. I'm impressed and slightly unnerved.
    >>  ............................................
    pt  Certo — você lembrou qual. Estou impressionado e um pouco desconcertado.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it! Not just that there was one. Yes. Still. Very much still.
    >>  ............................................
    pt  Você lembrou o formato! Não só que existia uma. Sim. Ainda. Muito ainda.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details! Most people keep the fact and mislay the thing entirely.
    >>  ............................................
    pt  Você tinha os detalhes! A maioria guarda o fato e perde a coisa.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right — you remembered which one. I'm impressed and slightly unnerved.
    >>  ............................................
    pt  Certo — você lembrou qual. Estou impressionado e um pouco desconcertado.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it. Yes. Still. It'll be still next year too.
    >>  ............................................
    pt  Você lembrou o formato. Sim. Ainda. Vai ser ainda ano que vem também.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details, months on. That's a better memory than most and it keeps.
    >>  ............................................
    pt  Você tinha os detalhes, meses depois. É uma memória melhor que a maioria e se conserva.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right. Still hoping. Hopes that survive a year tend to survive the next one.
    >>  ............................................
    pt  Certo. Ainda esperando. Esperanças que sobrevivem um ano costumam sobreviver o próximo.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it, %1$s. Not just that there was one. I'd not expected that.
    >>  ............................................
    pt  Você lembrou o formato, %1$s. Não só que existia uma. Eu não esperava.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. I'd told myself it had gone in one ear, to save myself later.
    >>  ............................................
    pt  Você tinha os detalhes. Eu dizia a mim mesmo que tinha entrado por um ouvido, pra me poupar.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Still, yes. And being asked about the right one makes it feel less foolish.
    >>  ............................................
    pt  Ainda, sim. E ser perguntado sobre a certa faz parecer menos bobo.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it. Yes. Still.
    >>  ............................................
    pt  Você lembrou o formato. Sim. Ainda.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details. Right.
    >>  ............................................
    pt  Você tinha os detalhes. Certo.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Still, yes. And you remembered which.
    >>  ............................................
    pt  Ainda, sim. E você lembrou qual.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it! Not just that there was one. Yes. Still. Very much still.
    >>  ............................................
    pt  Você lembrou o formato! Não só que existia uma. Sim. Ainda. Muito ainda.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details! Most people keep the fact and mislay the thing entirely.
    >>  ............................................
    pt  Você tinha os detalhes! A maioria guarda o fato e perde a coisa.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right — you remembered which one. I'm impressed and slightly unnerved.
    >>  ............................................
    pt  Certo — você lembrou qual. Estou impressionado e um pouco desconcertado.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.still_hoping.named/1
    en  You remembered the shape of it! Not just that there was one. Yes. Still. Very much still.
    >>  ............................................
    pt  Você lembrou o formato! Não só que existia uma. Sim. Ainda. Muito ainda.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.still_hoping.named/2
    en  You had the details! Most people keep the fact and mislay the thing entirely.
    >>  ............................................
    pt  Você tinha os detalhes! A maioria guarda o fato e perde a coisa.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.still_hoping.named/3
    en  Right — you remembered which one. I'm impressed and slightly unnerved.
    >>  ............................................
    pt  Certo — você lembrou qual. Estou impressionado e um pouco desconcertado.
    >>  ............................................
```

</details>


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when milestone `hopes.named` is set  _(chance -2000)_
- Does: **hearts +1** — decision id `hopes.resume.still_hoping`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `hopes.resume.still_hoping`)_
- Does: arc `hopes` — advance to stage 2
- Then opens: `conversations.arc.hopes.resume.followup`
- …where the player's next choices will be: "I'm hoping for it too, now." | "What would move it along?" | "Don't pin everything on it." | "I hope it comes."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.still_hoping.plain
WHO    VILLAGER — what the player reads after pressing "Still hoping for it?"
       spoken on: conversations.arc.hopes.resume.respond, button `still_hoping`
       leaves the player on: conversations.arc.hopes.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.still_hoping.plain.to.hopes`: the villager accepts. Subject `hopes`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.resume.still_hoping.plain/1   [51 chars]
    en  Still. It hasn't moved, but neither have I stopped.
    >>  ............................................
    pt  Ainda. Não se mexeu, mas eu também não parei.
    >>  ............................................
  dialogue.conversations.hopes.resume.still_hoping.plain/2   [53 chars]
    en  Just so. Hoping's cheap. Doing is the expensive part.
    >>  ............................................
    pt  Pois é. Esperar é barato. Fazer é a parte cara.
    >>  ............................................
  dialogue.conversations.hopes.resume.still_hoping.plain/3   [39 chars]
    en  Some days. Today, since you asked, yes.
    >>  ............................................
    pt  Alguns dias. Hoje, já que perguntou, sim.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved, %1$s, but neither have I stopped, and some weeks that's all I have.
    >>  ............................................
    pt  Ainda. Não se moveu, %1$s, mas eu também não parei, e em algumas semanas é tudo que eu tenho.
    >>  ............................................
  anxious.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. I'd half hoped it would fade so I could stop carrying it.
    >>  ............................................
    pt  Sim. A mesma. Eu meio que esperava que passasse pra eu parar de carregar.
    >>  ............................................
  anxious.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. Saying so out loud makes it sound smaller than it is.
    >>  ............................................
    pt  Ainda esperando. Dizer em voz alta faz parecer menor do que é.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved and neither have I. That's how it's been for years.
    >>  ............................................
    pt  Ainda. Não se moveu e eu também não. É assim há anos.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. Hopes keep their own hours and this one is patient.
    >>  ............................................
    pt  Sim. A mesma. Esperanças têm o próprio horário e esta é paciente.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. Ask me in five years and I expect the answer will be the same.
    >>  ............................................
    pt  Ainda esperando. Me pergunte em cinco anos e eu espero a mesma resposta.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved, but neither have I stopped.
    >>  ............................................
    pt  Ainda. Não se moveu, mas eu também não parei.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same hope, same place. That's not nothing.
    >>  ............................................
    pt  Sim. Mesma esperança, mesmo lugar. Não é nada.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. It's a hope, not a mood.
    >>  ............................................
    pt  Ainda esperando. É esperança, não humor.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved, but neither have I stopped.
    >>  ............................................
    pt  Ainda. Não se moveu, mas eu também não parei.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same hope, same place. That's not nothing.
    >>  ............................................
    pt  Sim. Mesma esperança, mesmo lugar. Não é nada.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. It's a hope, not a mood.
    >>  ............................................
    pt  Ainda esperando. É esperança, não humor.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still, %1$s. It hasn't moved, but neither have I stopped.
    >>  ............................................
    pt  Ainda, %1$s. Não se moveu, mas eu também não parei.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. Thank you for coming back to ask about it.
    >>  ............................................
    pt  Sim. A mesma. Obrigado por voltar pra perguntar.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. And you asking is most of why it's still worth hoping at.
    >>  ............................................
    pt  Ainda esperando. E você perguntar é quase toda a razão de ainda valer.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still, %1$s. It hasn't moved, but neither have I stopped.
    >>  ............................................
    pt  Ainda, %1$s. Não se moveu, mas eu também não parei.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. Thank you for coming back to ask about it.
    >>  ............................................
    pt  Sim. A mesma. Obrigado por voltar pra perguntar.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. And you asking is most of why it's still worth hoping at.
    >>  ............................................
    pt  Ainda esperando. E você perguntar é quase toda a razão de ainda valer.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still, %1$s. It hasn't moved, but neither have I stopped.
    >>  ............................................
    pt  Ainda, %1$s. Não se moveu, mas eu também não parei.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. Thank you for coming back to ask about it.
    >>  ............................................
    pt  Sim. A mesma. Obrigado por voltar pra perguntar.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. And you asking is most of why it's still worth hoping at.
    >>  ............................................
    pt  Ainda esperando. E você perguntar é quase toda a razão de ainda valer.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved, %1$s, but neither have I stopped, and some weeks that's all I have.
    >>  ............................................
    pt  Ainda. Não se moveu, %1$s, mas eu também não parei, e em algumas semanas é tudo que eu tenho.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. I'd half hoped it would fade so I could stop carrying it.
    >>  ............................................
    pt  Sim. A mesma. Eu meio que esperava que passasse pra eu parar de carregar.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. Saying so out loud makes it sound smaller than it is.
    >>  ............................................
    pt  Ainda esperando. Dizer em voz alta faz parecer menor do que é.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved, but neither have I stopped.
    >>  ............................................
    pt  Ainda. Não se moveu, mas eu também não parei.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same hope, same place. That's not nothing.
    >>  ............................................
    pt  Sim. Mesma esperança, mesmo lugar. Não é nada.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. It's a hope, not a mood.
    >>  ............................................
    pt  Ainda esperando. É esperança, não humor.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved, but neither have I stopped.
    >>  ............................................
    pt  Ainda. Não se moveu, mas eu também não parei.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same hope, same place. That's not nothing.
    >>  ............................................
    pt  Sim. Mesma esperança, mesmo lugar. Não é nada.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. It's a hope, not a mood.
    >>  ............................................
    pt  Ainda esperando. É esperança, não humor.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved.
    >>  ............................................
    pt  Ainda. Não se moveu.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one.
    >>  ............................................
    pt  Sim. A mesma.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping.
    >>  ............................................
    pt  Ainda esperando.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved and neither have I. That's how it's been for years.
    >>  ............................................
    pt  Ainda. Não se moveu e eu também não. É assim há anos.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. Hopes keep their own hours and this one is patient.
    >>  ............................................
    pt  Sim. A mesma. Esperanças têm o próprio horário e esta é paciente.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. Ask me in five years and I expect the answer will be the same.
    >>  ............................................
    pt  Ainda esperando. Me pergunte em cinco anos e eu espero a mesma resposta.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved.
    >>  ............................................
    pt  Ainda. Não se moveu.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one.
    >>  ............................................
    pt  Sim. A mesma.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping.
    >>  ............................................
    pt  Ainda esperando.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved and neither have I. That's how it's been for years.
    >>  ............................................
    pt  Ainda. Não se moveu e eu também não. É assim há anos.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. Hopes keep their own hours and this one is patient.
    >>  ............................................
    pt  Sim. A mesma. Esperanças têm o próprio horário e esta é paciente.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. Ask me in five years and I expect the answer will be the same.
    >>  ............................................
    pt  Ainda esperando. Me pergunte em cinco anos e eu espero a mesma resposta.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still! It hasn't moved, but neither have I stopped, which I count as a draw.
    >>  ............................................
    pt  Ainda! Não se moveu, mas eu também não parei, o que eu conto como empate.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same hope, same place. Stubbornly, gloriously the same.
    >>  ............................................
    pt  Sim. Mesma esperança, mesmo lugar. Teimosa e gloriosamente igual.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping! It's a hope. They're famously hard to discourage.
    >>  ............................................
    pt  Ainda esperando! É esperança. São famosamente difíceis de desanimar.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still! It hasn't moved, but neither have I stopped, which I count as a draw.
    >>  ............................................
    pt  Ainda! Não se moveu, mas eu também não parei, o que eu conto como empate.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same hope, same place. Stubbornly, gloriously the same.
    >>  ............................................
    pt  Sim. Mesma esperança, mesmo lugar. Teimosa e gloriosamente igual.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping! It's a hope. They're famously hard to discourage.
    >>  ............................................
    pt  Ainda esperando! É esperança. São famosamente difíceis de desanimar.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved and neither have I. That's how it's been for years.
    >>  ............................................
    pt  Ainda. Não se moveu e eu também não. É assim há anos.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. Hopes keep their own hours and this one is patient.
    >>  ............................................
    pt  Sim. A mesma. Esperanças têm o próprio horário e esta é paciente.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. Ask me in five years and I expect the answer will be the same.
    >>  ............................................
    pt  Ainda esperando. Me pergunte em cinco anos e eu espero a mesma resposta.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved, %1$s, but neither have I stopped, and some weeks that's all I have.
    >>  ............................................
    pt  Ainda. Não se moveu, %1$s, mas eu também não parei, e em algumas semanas é tudo que eu tenho.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one. I'd half hoped it would fade so I could stop carrying it.
    >>  ............................................
    pt  Sim. A mesma. Eu meio que esperava que passasse pra eu parar de carregar.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping. Saying so out loud makes it sound smaller than it is.
    >>  ............................................
    pt  Ainda esperando. Dizer em voz alta faz parecer menor do que é.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still. It hasn't moved.
    >>  ............................................
    pt  Ainda. Não se moveu.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same one.
    >>  ............................................
    pt  Sim. A mesma.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping.
    >>  ............................................
    pt  Ainda esperando.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still! It hasn't moved, but neither have I stopped, which I count as a draw.
    >>  ............................................
    pt  Ainda! Não se moveu, mas eu também não parei, o que eu conto como empate.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same hope, same place. Stubbornly, gloriously the same.
    >>  ............................................
    pt  Sim. Mesma esperança, mesmo lugar. Teimosa e gloriosamente igual.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping! It's a hope. They're famously hard to discourage.
    >>  ............................................
    pt  Ainda esperando! É esperança. São famosamente difíceis de desanimar.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.still_hoping.plain/1
    en  Still! It hasn't moved, but neither have I stopped, which I count as a draw.
    >>  ............................................
    pt  Ainda! Não se moveu, mas eu também não parei, o que eu conto como empate.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.still_hoping.plain/2
    en  Yes. Same hope, same place. Stubbornly, gloriously the same.
    >>  ............................................
    pt  Sim. Mesma esperança, mesmo lugar. Teimosa e gloriosamente igual.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.still_hoping.plain/3
    en  Still hoping! It's a hope. They're famously hard to discourage.
    >>  ............................................
    pt  Ainda esperando! É esperança. São famosamente difíceis de desanimar.
    >>  ............................................
```

</details>


### Button `offer_help` — "Let me help with it."

*stance family `practical_help` · tone `plain` · answers the beat(s) `hopes.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.resume.offer_help` — accepted phrasings: "let me help with it"; "i can help with that"; "give me a hand in it"
  - the message must contain one of: `help`, `hand`
  - scored words: `help`(1.5), `let`(0.6), `hand`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.respond.offer_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.hopes.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.hopes.resume.respond.offer_help   [20 chars]
    en  Let me help with it.
    >>  ............................................
    pt  Deixa eu ajudar com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `hopes.resume.offer_help`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `hopes.resume.offer_help`)_
- Does: arc `hopes` — advance to stage 2
- Then opens: `conversations.arc.hopes.resume.followup`
- …where the player's next choices will be: "I'm hoping for it too, now." | "What would move it along?" | "Don't pin everything on it." | "I hope it comes."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.offer_help
WHO    VILLAGER — what the player reads after pressing "Let me help with it."
       spoken on: conversations.arc.hopes.resume.respond, button `offer_help`
       leaves the player on: conversations.arc.hopes.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.offer_help.to.hopes`: the villager accepts. Subject `hopes`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.resume.offer_help/1   [69 chars]
    en  ...You'd actually help. That changes it from a hope to a job of work.
    >>  ............................................
    pt  ...Você ajudaria de verdade. Isso transforma de esperança em trabalho.
    >>  ............................................
  dialogue.conversations.hopes.resume.offer_help/2   [49 chars]
    en  Help. Right. Then it might actually happen, %1$s.
    >>  ............................................
    pt  Ajuda. Certo. Então pode até acontecer, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.resume.offer_help/3   [56 chars]
    en  Nobody offers with hopes. They only offer with problems.
    >>  ............................................
    pt  Ninguém se oferece com esperanças. Só se oferecem com problemas.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help. That changes it from a hope to a job of work, %1$s, and now it could fail.
    >>  ............................................
    pt  ...Você ajudaria de verdade. Isso muda de esperança pra serviço, %1$s, e agora pode falhar.
    >>  ............................................
  anxious.dialogue.conversations.hopes.resume.offer_help/2
    en  You'd help. A hope can't disappoint you. A plan can, and I've been careful about that.
    >>  ............................................
    pt  Você ajudaria. Uma esperança não te decepciona. Um plano decepciona, e eu tomava cuidado.
    >>  ............................................
  anxious.dialogue.conversations.hopes.resume.offer_help/3
    en  Right. Then I'll have to want it out loud, which is the part I've been avoiding.
    >>  ............................................
    pt  Certo. Aí eu vou ter que querer em voz alta, que é a parte que eu evitava.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help. Then it becomes a job of work, and work gets done eventually.
    >>  ............................................
    pt  Você ajudaria de verdade. Aí vira serviço, e serviço se faz uma hora.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. Slowly, and with two of us. That's the shape things actually finish in.
    >>  ............................................
    pt  Certo. Devagar, e com dois. É o formato em que as coisas realmente terminam.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. Good. There's no hurry, and now there's no excuse either.
    >>  ............................................
    pt  Você ajudaria. Bom. Sem pressa, e agora sem desculpa também.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help. That changes it from a hope to a job of work.
    >>  ............................................
    pt  Você ajudaria de verdade. Isso muda de esperança pra serviço.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. Then it stops being a wish and starts being a thing with steps.
    >>  ............................................
    pt  Certo. Aí deixa de ser desejo e vira coisa com passos.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. I'll have to take it seriously now, which is your fault.
    >>  ............................................
    pt  Você ajudaria. Agora eu vou ter que levar a sério, e a culpa é sua.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help. That changes it from a hope to a job of work.
    >>  ............................................
    pt  Você ajudaria de verdade. Isso muda de esperança pra serviço.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. Then it stops being a wish and starts being a thing with steps.
    >>  ............................................
    pt  Certo. Aí deixa de ser desejo e vira coisa com passos.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. I'll have to take it seriously now, which is your fault.
    >>  ............................................
    pt  Você ajudaria. Agora eu vou ter que levar a sério, e a culpa é sua.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help, %1$s. That changes it from a hope to a job of work.
    >>  ............................................
    pt  ...Você ajudaria de verdade, %1$s. Isso muda de esperança pra serviço.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.offer_help/2
    en  You'd help. Then let's find the first bit and I'll not do it alone this time.
    >>  ............................................
    pt  Você ajudaria. Então vamos achar a primeira parte e eu não faço sozinho desta vez.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.offer_help/3
    en  Right. With somebody else in it, it's a different thing entirely.
    >>  ............................................
    pt  Certo. Com outra pessoa dentro, é uma coisa completamente diferente.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help, %1$s. That changes it from a hope to a job of work.
    >>  ............................................
    pt  ...Você ajudaria de verdade, %1$s. Isso muda de esperança pra serviço.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.offer_help/2
    en  You'd help. Then let's find the first bit and I'll not do it alone this time.
    >>  ............................................
    pt  Você ajudaria. Então vamos achar a primeira parte e eu não faço sozinho desta vez.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.offer_help/3
    en  Right. With somebody else in it, it's a different thing entirely.
    >>  ............................................
    pt  Certo. Com outra pessoa dentro, é uma coisa completamente diferente.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help, %1$s. That changes it from a hope to a job of work.
    >>  ............................................
    pt  ...Você ajudaria de verdade, %1$s. Isso muda de esperança pra serviço.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.offer_help/2
    en  You'd help. Then let's find the first bit and I'll not do it alone this time.
    >>  ............................................
    pt  Você ajudaria. Então vamos achar a primeira parte e eu não faço sozinho desta vez.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.offer_help/3
    en  Right. With somebody else in it, it's a different thing entirely.
    >>  ............................................
    pt  Certo. Com outra pessoa dentro, é uma coisa completamente diferente.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help. That changes it from a hope to a job of work, %1$s, and now it could fail.
    >>  ............................................
    pt  ...Você ajudaria de verdade. Isso muda de esperança pra serviço, %1$s, e agora pode falhar.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.offer_help/2
    en  You'd help. A hope can't disappoint you. A plan can, and I've been careful about that.
    >>  ............................................
    pt  Você ajudaria. Uma esperança não te decepciona. Um plano decepciona, e eu tomava cuidado.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.offer_help/3
    en  Right. Then I'll have to want it out loud, which is the part I've been avoiding.
    >>  ............................................
    pt  Certo. Aí eu vou ter que querer em voz alta, que é a parte que eu evitava.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help. That changes it from a hope to a job of work.
    >>  ............................................
    pt  Você ajudaria de verdade. Isso muda de esperança pra serviço.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. Then it stops being a wish and starts being a thing with steps.
    >>  ............................................
    pt  Certo. Aí deixa de ser desejo e vira coisa com passos.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. I'll have to take it seriously now, which is your fault.
    >>  ............................................
    pt  Você ajudaria. Agora eu vou ter que levar a sério, e a culpa é sua.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help. That changes it from a hope to a job of work.
    >>  ............................................
    pt  Você ajudaria de verdade. Isso muda de esperança pra serviço.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. Then it stops being a wish and starts being a thing with steps.
    >>  ............................................
    pt  Certo. Aí deixa de ser desejo e vira coisa com passos.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. I'll have to take it seriously now, which is your fault.
    >>  ............................................
    pt  Você ajudaria. Agora eu vou ter que levar a sério, e a culpa é sua.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help. That changes it.
    >>  ............................................
    pt  ...Você ajudaria de verdade. Isso muda.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. A job of work, then.
    >>  ............................................
    pt  Certo. Um serviço, então.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. I'd not expected that.
    >>  ............................................
    pt  Você ajudaria. Eu não esperava.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help. Then it becomes a job of work, and work gets done eventually.
    >>  ............................................
    pt  Você ajudaria de verdade. Aí vira serviço, e serviço se faz uma hora.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. Slowly, and with two of us. That's the shape things actually finish in.
    >>  ............................................
    pt  Certo. Devagar, e com dois. É o formato em que as coisas realmente terminam.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. Good. There's no hurry, and now there's no excuse either.
    >>  ............................................
    pt  Você ajudaria. Bom. Sem pressa, e agora sem desculpa também.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help. That changes it.
    >>  ............................................
    pt  ...Você ajudaria de verdade. Isso muda.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. A job of work, then.
    >>  ............................................
    pt  Certo. Um serviço, então.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. I'd not expected that.
    >>  ............................................
    pt  Você ajudaria. Eu não esperava.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help. Then it becomes a job of work, and work gets done eventually.
    >>  ............................................
    pt  Você ajudaria de verdade. Aí vira serviço, e serviço se faz uma hora.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. Slowly, and with two of us. That's the shape things actually finish in.
    >>  ............................................
    pt  Certo. Devagar, e com dois. É o formato em que as coisas realmente terminam.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. Good. There's no hurry, and now there's no excuse either.
    >>  ............................................
    pt  Você ajudaria. Bom. Sem pressa, e agora sem desculpa também.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help! That changes it from a hope to a job of work. Terrifying.
    >>  ............................................
    pt  Você ajudaria de verdade! Isso muda de esperança pra serviço. Aterrorizante.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.offer_help/2
    en  Right — now it has steps. I was quite comfortable with it having no steps.
    >>  ............................................
    pt  Certo — agora tem passos. Eu estava bem confortável sem passos.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help! Now I have to do something about it. What have you done.
    >>  ............................................
    pt  Você ajudaria! Agora eu tenho que fazer algo. O que você fez.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help! That changes it from a hope to a job of work. Terrifying.
    >>  ............................................
    pt  Você ajudaria de verdade! Isso muda de esperança pra serviço. Aterrorizante.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.offer_help/2
    en  Right — now it has steps. I was quite comfortable with it having no steps.
    >>  ............................................
    pt  Certo — agora tem passos. Eu estava bem confortável sem passos.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help! Now I have to do something about it. What have you done.
    >>  ............................................
    pt  Você ajudaria! Agora eu tenho que fazer algo. O que você fez.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help. Then it becomes a job of work, and work gets done eventually.
    >>  ............................................
    pt  Você ajudaria de verdade. Aí vira serviço, e serviço se faz uma hora.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. Slowly, and with two of us. That's the shape things actually finish in.
    >>  ............................................
    pt  Certo. Devagar, e com dois. É o formato em que as coisas realmente terminam.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. Good. There's no hurry, and now there's no excuse either.
    >>  ............................................
    pt  Você ajudaria. Bom. Sem pressa, e agora sem desculpa também.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help. That changes it from a hope to a job of work, %1$s, and now it could fail.
    >>  ............................................
    pt  ...Você ajudaria de verdade. Isso muda de esperança pra serviço, %1$s, e agora pode falhar.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.offer_help/2
    en  You'd help. A hope can't disappoint you. A plan can, and I've been careful about that.
    >>  ............................................
    pt  Você ajudaria. Uma esperança não te decepciona. Um plano decepciona, e eu tomava cuidado.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.offer_help/3
    en  Right. Then I'll have to want it out loud, which is the part I've been avoiding.
    >>  ............................................
    pt  Certo. Aí eu vou ter que querer em voz alta, que é a parte que eu evitava.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.offer_help/1
    en  ...You'd actually help. That changes it.
    >>  ............................................
    pt  ...Você ajudaria de verdade. Isso muda.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.offer_help/2
    en  Right. A job of work, then.
    >>  ............................................
    pt  Certo. Um serviço, então.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help. I'd not expected that.
    >>  ............................................
    pt  Você ajudaria. Eu não esperava.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help! That changes it from a hope to a job of work. Terrifying.
    >>  ............................................
    pt  Você ajudaria de verdade! Isso muda de esperança pra serviço. Aterrorizante.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.offer_help/2
    en  Right — now it has steps. I was quite comfortable with it having no steps.
    >>  ............................................
    pt  Certo — agora tem passos. Eu estava bem confortável sem passos.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help! Now I have to do something about it. What have you done.
    >>  ............................................
    pt  Você ajudaria! Agora eu tenho que fazer algo. O que você fez.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.offer_help/1
    en  You'd actually help! That changes it from a hope to a job of work. Terrifying.
    >>  ............................................
    pt  Você ajudaria de verdade! Isso muda de esperança pra serviço. Aterrorizante.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.offer_help/2
    en  Right — now it has steps. I was quite comfortable with it having no steps.
    >>  ............................................
    pt  Certo — agora tem passos. Eu estava bem confortável sem passos.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.offer_help/3
    en  You'd help! Now I have to do something about it. What have you done.
    >>  ............................................
    pt  Você ajudaria! Agora eu tenho que fazer algo. O que você fez.
    >>  ............................................
```

</details>


### Button `dismiss` — "You're still on that?"

*stance family `dismissal` · tone `blunt` · answers the beat(s) `hopes.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.resume.dismiss` — accepted phrasings: "you are still on that"; "that again"; "give it up"
  - the message must contain one of: `still`, `again`, `give`
  - scored words: `still`(1.2), `that`(0.3), `again`(1.2), `give`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.hopes.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.hopes.resume.respond.dismiss   [21 chars]
    en  You're still on that?
    >>  ............................................
    pt  Você ainda está nisso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `hopes.resume.dismiss`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `hopes.resume.dismiss`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.dismiss
WHO    VILLAGER — what the player reads after pressing "You're still on that?"
       spoken on: conversations.arc.hopes.resume.respond, button `dismiss`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.dismiss.terminal`: the villager dismisss. Subject `hopes.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.resume.dismiss/1   [47 chars]
    en  ...Still on that. Yes. It's a hope, not a mood.
    >>  ............................................
    pt  ...Ainda nisso. Sim. É uma esperança, não um humor.
    >>  ............................................
  dialogue.conversations.hopes.resume.dismiss/2   [49 chars]
    en  You asked me about it, %1$s. I answered honestly.
    >>  ............................................
    pt  Você me perguntou sobre isso, %1$s. Eu respondi com honestidade.
    >>  ............................................
  dialogue.conversations.hopes.resume.dismiss/3   [31 chars]
    en  Right. I'll stop mentioning it.
    >>  ............................................
    pt  Certo. Vou parar de mencionar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.resume.dismiss/1
    en  ...Still. I know it's tiresome to hear twice, %1$s.
    >>  ............................................
    pt  ...Ainda. Eu sei que é cansativo ouvir duas vezes, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.hopes.resume.dismiss/2
    en  It hasn't gone. I'd rather it had, some weeks.
    >>  ............................................
    pt  Não foi embora. Em algumas semanas eu preferia que tivesse.
    >>  ............................................
  anxious.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll stop saying it out loud.
    >>  ............................................
    pt  ...Certo. Vou parar de dizer em voz alta.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on it, aye. It's a hope, and hopes keep their own hours.
    >>  ............................................
    pt  Ainda nisso, sim. É uma esperança, e esperanças têm o próprio horário.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.dismiss/2
    en  It'll be the same one next year. That's how I know it's real.
    >>  ............................................
    pt  Vai ser a mesma ano que vem. É assim que eu sei que é de verdade.
    >>  ............................................
  athletic.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. No rush on it. There never was.
    >>  ............................................
    pt  ...Certo. Sem pressa. Nunca teve.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on that. Yes. It's a hope, not a mood.
    >>  ............................................
    pt  Ainda nisso. Sim. É uma esperança, não um humor.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.dismiss/2
    en  It hasn't gone anywhere. That's rather what a hope is.
    >>  ............................................
    pt  Não foi a lugar nenhum. É meio que o que uma esperança é.
    >>  ............................................
  confident.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll stop mentioning it and keep having it.
    >>  ............................................
    pt  ...Certo. Paro de mencionar e continuo tendo.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on that. Yes. It's a hope, not a mood.
    >>  ............................................
    pt  Ainda nisso. Sim. É uma esperança, não um humor.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.dismiss/2
    en  It hasn't gone anywhere. That's rather what a hope is.
    >>  ............................................
    pt  Não foi a lugar nenhum. É meio que o que uma esperança é.
    >>  ............................................
  crabby.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll stop mentioning it and keep having it.
    >>  ............................................
    pt  ...Certo. Paro de mencionar e continuo tendo.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.dismiss/1
    en  Still, yes. I'd thought you'd remember, %1$s — you asked me first.
    >>  ............................................
    pt  Ainda, sim. Achei que você fosse lembrar, %1$s — você perguntou primeiro.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.dismiss/2
    en  It's the same one. I've not had a new one since I told you.
    >>  ............................................
    pt  É a mesma. Não tive uma nova desde que te contei.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll not bring it up unless you do.
    >>  ............................................
    pt  ...Certo. Não levanto de novo a menos que você levante.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.dismiss/1
    en  Still, yes. I'd thought you'd remember, %1$s — you asked me first.
    >>  ............................................
    pt  Ainda, sim. Achei que você fosse lembrar, %1$s — você perguntou primeiro.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.dismiss/2
    en  It's the same one. I've not had a new one since I told you.
    >>  ............................................
    pt  É a mesma. Não tive uma nova desde que te contei.
    >>  ............................................
  flirty.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll not bring it up unless you do.
    >>  ............................................
    pt  ...Certo. Não levanto de novo a menos que você levante.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.dismiss/1
    en  Still, yes. I'd thought you'd remember, %1$s — you asked me first.
    >>  ............................................
    pt  Ainda, sim. Achei que você fosse lembrar, %1$s — você perguntou primeiro.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.dismiss/2
    en  It's the same one. I've not had a new one since I told you.
    >>  ............................................
    pt  É a mesma. Não tive uma nova desde que te contei.
    >>  ............................................
  friendly.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll not bring it up unless you do.
    >>  ............................................
    pt  ...Certo. Não levanto de novo a menos que você levante.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.dismiss/1
    en  ...Still. I know it's tiresome to hear twice, %1$s.
    >>  ............................................
    pt  ...Ainda. Eu sei que é cansativo ouvir duas vezes, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.dismiss/2
    en  It hasn't gone. I'd rather it had, some weeks.
    >>  ............................................
    pt  Não foi embora. Em algumas semanas eu preferia que tivesse.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll stop saying it out loud.
    >>  ............................................
    pt  ...Certo. Vou parar de dizer em voz alta.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on that. Yes. It's a hope, not a mood.
    >>  ............................................
    pt  Ainda nisso. Sim. É uma esperança, não um humor.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.dismiss/2
    en  It hasn't gone anywhere. That's rather what a hope is.
    >>  ............................................
    pt  Não foi a lugar nenhum. É meio que o que uma esperança é.
    >>  ............................................
  greedy.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll stop mentioning it and keep having it.
    >>  ............................................
    pt  ...Certo. Paro de mencionar e continuo tendo.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on that. Yes. It's a hope, not a mood.
    >>  ............................................
    pt  Ainda nisso. Sim. É uma esperança, não um humor.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.dismiss/2
    en  It hasn't gone anywhere. That's rather what a hope is.
    >>  ............................................
    pt  Não foi a lugar nenhum. É meio que o que uma esperança é.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll stop mentioning it and keep having it.
    >>  ............................................
    pt  ...Certo. Paro de mencionar e continuo tendo.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.dismiss/1
    en  ...Still, yes.
    >>  ............................................
    pt  ...Ainda, sim.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.dismiss/2
    en  It's a hope. They don't move about much.
    >>  ............................................
    pt  É uma esperança. Elas não se mexem muito.
    >>  ............................................
  introverted.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll leave it.
    >>  ............................................
    pt  ...Certo. Vou deixar.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on it, aye. It's a hope, and hopes keep their own hours.
    >>  ............................................
    pt  Ainda nisso, sim. É uma esperança, e esperanças têm o próprio horário.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.dismiss/2
    en  It'll be the same one next year. That's how I know it's real.
    >>  ............................................
    pt  Vai ser a mesma ano que vem. É assim que eu sei que é de verdade.
    >>  ............................................
  lazy.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. No rush on it. There never was.
    >>  ............................................
    pt  ...Certo. Sem pressa. Nunca teve.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.dismiss/1
    en  ...Still, yes.
    >>  ............................................
    pt  ...Ainda, sim.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.dismiss/2
    en  It's a hope. They don't move about much.
    >>  ............................................
    pt  É uma esperança. Elas não se mexem muito.
    >>  ............................................
  odd.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll leave it.
    >>  ............................................
    pt  ...Certo. Vou deixar.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on it, aye. It's a hope, and hopes keep their own hours.
    >>  ............................................
    pt  Ainda nisso, sim. É uma esperança, e esperanças têm o próprio horário.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.dismiss/2
    en  It'll be the same one next year. That's how I know it's real.
    >>  ............................................
    pt  Vai ser a mesma ano que vem. É assim que eu sei que é de verdade.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. No rush on it. There never was.
    >>  ............................................
    pt  ...Certo. Sem pressa. Nunca teve.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on that! Yes. Do keep up, %1$s.
    >>  ............................................
    pt  Ainda nisso! Sim. Acompanhe, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.dismiss/2
    en  Right! It's a hope. They're famously persistent.
    >>  ............................................
    pt  Certo! É uma esperança. Elas são famosamente persistentes.
    >>  ............................................
  peppy.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Ha. It'll be there next time you ask, too.
    >>  ............................................
    pt  ...Ha. Vai estar lá da próxima vez que você perguntar também.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on that! Yes. Do keep up, %1$s.
    >>  ............................................
    pt  Ainda nisso! Sim. Acompanhe, %1$s.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.dismiss/2
    en  Right! It's a hope. They're famously persistent.
    >>  ............................................
    pt  Certo! É uma esperança. Elas são famosamente persistentes.
    >>  ............................................
  playful.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Ha. It'll be there next time you ask, too.
    >>  ............................................
    pt  ...Ha. Vai estar lá da próxima vez que você perguntar também.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on it, aye. It's a hope, and hopes keep their own hours.
    >>  ............................................
    pt  Ainda nisso, sim. É uma esperança, e esperanças têm o próprio horário.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.dismiss/2
    en  It'll be the same one next year. That's how I know it's real.
    >>  ............................................
    pt  Vai ser a mesma ano que vem. É assim que eu sei que é de verdade.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. No rush on it. There never was.
    >>  ............................................
    pt  ...Certo. Sem pressa. Nunca teve.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.dismiss/1
    en  ...Still. I know it's tiresome to hear twice, %1$s.
    >>  ............................................
    pt  ...Ainda. Eu sei que é cansativo ouvir duas vezes, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.dismiss/2
    en  It hasn't gone. I'd rather it had, some weeks.
    >>  ............................................
    pt  Não foi embora. Em algumas semanas eu preferia que tivesse.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll stop saying it out loud.
    >>  ............................................
    pt  ...Certo. Vou parar de dizer em voz alta.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.dismiss/1
    en  ...Still, yes.
    >>  ............................................
    pt  ...Ainda, sim.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.dismiss/2
    en  It's a hope. They don't move about much.
    >>  ............................................
    pt  É uma esperança. Elas não se mexem muito.
    >>  ............................................
  shy.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Right. I'll leave it.
    >>  ............................................
    pt  ...Certo. Vou deixar.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on that! Yes. Do keep up, %1$s.
    >>  ............................................
    pt  Ainda nisso! Sim. Acompanhe, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.dismiss/2
    en  Right! It's a hope. They're famously persistent.
    >>  ............................................
    pt  Certo! É uma esperança. Elas são famosamente persistentes.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Ha. It'll be there next time you ask, too.
    >>  ............................................
    pt  ...Ha. Vai estar lá da próxima vez que você perguntar também.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.dismiss/1
    en  Still on that! Yes. Do keep up, %1$s.
    >>  ............................................
    pt  Ainda nisso! Sim. Acompanhe, %1$s.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.dismiss/2
    en  Right! It's a hope. They're famously persistent.
    >>  ............................................
    pt  Certo! É uma esperança. Elas são famosamente persistentes.
    >>  ............................................
  witty.dialogue.conversations.hopes.resume.dismiss/3
    en  ...Ha. It'll be there next time you ask, too.
    >>  ............................................
    pt  ...Ha. Vai estar lá da próxima vez que você perguntar também.
    >>  ............................................
```

</details>


### Button `leave` — "I hope it comes."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.revisit.opens` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.hopes.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.hopes.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.hopes.resume.respond.leave   [16 chars]
    en  I hope it comes.
    >>  ............................................
    pt  Espero que aconteça.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.resume.leave
WHO    VILLAGER — what the player reads after pressing "I hope it comes."
       spoken on: conversations.arc.hopes.resume.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.resume.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.resume.leave/1   [41 chars]
    en  So do I. Thank you for remembering, %1$s.
    >>  ............................................
    pt  Eu também. Obrigado por lembrar, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.resume.leave/2   [26 chars]
    en  So I've found. Off you go.
    >>  ............................................
    pt  Foi o que eu vi. Pode ir.
    >>  ............................................
  dialogue.conversations.hopes.resume.leave/3   [19 chars]
    en  I'll see you about.
    >>  ............................................
    pt  A gente se vê por aí.
    >>  ............................................
```

---


## `conversations.scene.hopes.followup`

**Reached from 4 route(s):** `conversations.scene.hopes.spring_list.respond` / `ask_the_large_one`; `conversations.scene.hopes.spring_list.respond` / `wish_them_well`; `conversations.scene.hopes.the_long_one.respond` / `ask_what_would_help`; `conversations.scene.hopes.the_long_one.respond` / `keep_it_safe`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.hopes.spring_list.answered` — e.g. "It takes more than a year, which is why it is on a one-year list. You put the long one on so that it does not stop being true."
- `conversations.scene.hopes.spring_list.thanked` — e.g. "Two would be a good year. Three would be a year I talked about for a decade, and I am trying not to want that."
- `conversations.scene.hopes.the_long_one.explained` — e.g. "Time and one conversation I have been avoiding for four years, and the conversation is the whole of it."
- `conversations.scene.hopes.the_long_one.steadied` — e.g. "I know. That is why it got said. There are two people it can be said to and you are one of them now."


```text
POOL   dialogue key: dialogue.conversations.scene.hopes.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.hopes.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.hopes.followup   [32 chars]
    en  Anything else you're hoping for?
    >>  ............................................
    pt  Mais alguma coisa que você espera?
    >>  ............................................
```


### Button `leave` — "That's the year, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:hopes.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.hopes.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.hopes.followup.leave   [22 chars]
    en  That's the year, then.
    >>  ............................................
    pt  É o ano, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.leaving
WHO    VILLAGER — what the player reads after pressing "That's the year, then."
       spoken on: conversations.scene.hopes.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.scene.leaving`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.hopes.spring_list.respond / leave; conversations.scene.hopes.the_long_one.respond / leave
```

```text
  dialogue.conversations.scene.hopes.leaving/1   [13 chars]
    en  We shall see.
    >>  ............................................
    pt  Vamos ver.
    >>  ............................................
  dialogue.conversations.scene.hopes.leaving/2   [30 chars]
    en  Ask me again at the end of it.
    >>  ............................................
    pt  Me pergunte de novo no fim.
    >>  ............................................
  dialogue.conversations.scene.hopes.leaving/3   [32 chars]
    en  Right. That is the plan, anyway.
    >>  ............................................
    pt  Certo. É o plano, pelo menos.
    >>  ............................................
```

---


## `conversations.scene.hopes.spring_list.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `hopes`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.hopes.spring_list` — e.g. "Three things, written on the inside of a cupboard door where only I see them."


```text
POOL   dialogue key: dialogue.conversations.scene.hopes.spring_list.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.hopes.spring_list.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.hopes.spring_list.respond   [10 chars]
    en  This year.
    >>  ............................................
    pt  Este ano.
    >>  ............................................
```


### Button `ask_the_large_one` — "What's the large one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `hopes.spring_list.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.hopes.spring_list.ask_the_large_one` — accepted phrasings: "whats the large one"; "what is the large one"; "tell me the big hope"
  - the message must contain one of: `large`, `big`
  - scored words: `large`(1.8), `big`(1.8), `whats`(0.8), `one`(0.8), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.spring_list.respond.ask_the_large_one
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.hopes.spring_list.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.hopes.spring_list.respond.ask_the_large_one   [21 chars]
    en  What's the large one?
    >>  ............................................
    pt  Qual é a grande?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `hopes.this_year`)_
- Does: session `turn`
- Then opens: `conversations.scene.hopes.followup`
- …where the player's next choices will be: "That's the year, then."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.spring_list.answered
WHO    VILLAGER — what the player reads after pressing "What's the large one?"
       spoken on: conversations.scene.hopes.spring_list.respond, button `ask_the_large_one`
       leaves the player on: conversations.scene.hopes.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.spring_list.open.answered`: the villager explains. Subject `hopes.this_year`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:hopes` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.hopes.spring_list.answered/1   [126 chars]
    en  It takes more than a year, which is why it is on a one-year list. You put the long one on so that it does not stop being true.
    >>  ............................................
    pt  Leva mais de um ano, e é por isso que está numa lista de um ano. A gente põe a longa para ela não deixar de ser verdade.
    >>  ............................................
  dialogue.conversations.scene.hopes.spring_list.answered/2   [99 chars]
    en  Something that requires two other people to agree with me, and I have not asked either of them yet.
    >>  ............................................
    pt  Uma coisa que exige que duas outras pessoas concordem comigo, e eu ainda não perguntei a nenhuma delas.
    >>  ............................................
  dialogue.conversations.scene.hopes.spring_list.answered/3   [94 chars]
    en  I would rather show you than say it. Ask me at harvest and there may be something to point at.
    >>  ............................................
    pt  Prefiro mostrar a dizer. Me pergunte na colheita e talvez haja algo para apontar.
    >>  ............................................
```


### Button `wish_them_well` — "I hope the year gives you all three."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `hopes.spring_list.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.hopes.spring_list.wish_them_well` — accepted phrasings: "i hope the year gives you all three"; "i hope the year gives you all three"; "hope all three come off"
  - the message must contain one of: `three`, `hope`
  - scored words: `three`(1.8), `hope`(1.8), `year`(0.8), `gives`(0.8), `all`(0.8), `come`(0.8), `off`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.spring_list.respond.wish_them_well
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.hopes.spring_list.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.hopes.spring_list.respond.wish_them_well   [36 chars]
    en  I hope the year gives you all three.
    >>  ............................................
    pt  Espero que o ano te dê as três.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `hopes.this_year`)_
- Does: session `turn`
- Then opens: `conversations.scene.hopes.followup`
- …where the player's next choices will be: "That's the year, then."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.spring_list.thanked
WHO    VILLAGER — what the player reads after pressing "I hope the year gives you all three."
       spoken on: conversations.scene.hopes.spring_list.respond, button `wish_them_well`
       leaves the player on: conversations.scene.hopes.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.spring_list.open.thanked`: the villager accepts. Subject `hopes.this_year`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:hopes` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.hopes.spring_list.thanked/1   [110 chars]
    en  Two would be a good year. Three would be a year I talked about for a decade, and I am trying not to want that.
    >>  ............................................
    pt  Duas já seria um bom ano. Três seria um ano de que eu falaria por uma década, e estou tentando não querer isso.
    >>  ............................................
  dialogue.conversations.scene.hopes.spring_list.thanked/2   [88 chars]
    en  Thank you. Saying it to somebody is the only part of a list that ever actually moves it.
    >>  ............................................
    pt  Obrigada. Dizer a alguém é a única parte de uma lista que de fato faz ela andar.
    >>  ............................................
  dialogue.conversations.scene.hopes.spring_list.thanked/3   [119 chars]
    en  If none of them come off I will still have had the spring where I wrote them down, and that is worth having on its own.
    >>  ............................................
    pt  Se nenhuma der certo, eu ainda vou ter tido a primavera em que as escrevi, e isso já vale por si.
    >>  ............................................
```


### Button `leave` — "I hope it comes off."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.spring_list.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.spring_list.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.hopes.spring_list.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.hopes.spring_list.respond.leave   [20 chars]
    en  I hope it comes off.
    >>  ............................................
    pt  Espero que dê certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.leaving
WHO    VILLAGER — what the player reads after pressing "I hope it comes off."
       spoken on: conversations.scene.hopes.spring_list.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.scene.leaving`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.hopes.followup / leave; conversations.scene.hopes.the_long_one.respond / leave
```

> Written out in full under **`conversations.scene.hopes.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.hopes.the_long_one.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `hopes`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.hopes.the_long_one` — e.g. "There is one I have had for eleven years and have said out loud about four times, all to the same two people."


```text
POOL   dialogue key: dialogue.conversations.scene.hopes.the_long_one.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.hopes.the_long_one.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.hopes.the_long_one.respond   [22 chars]
    en  The one you don't say.
    >>  ............................................
    pt  Aquela que você não diz.
    >>  ............................................
```


### Button `ask_what_would_help` — "What would move it along?"

*stance family `practical_help` · tone `gentle` · outcome `engaged` · answers the beat(s) `hopes.the_long_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.hopes.the_long_one.ask_what_would_help` — accepted phrasings: "what would move it along"; "what would move it along"; "what would make it likelier"
  - the message must contain one of: `move`, `likelier`
  - scored words: `move`(1.8), `likelier`(1.8), `along`(0.8), `make`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.the_long_one.respond.ask_what_would_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.hopes.the_long_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.hopes.the_long_one.respond.ask_what_would_help   [25 chars]
    en  What would move it along?
    >>  ............................................
    pt  O que faria isso andar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +3, warmth +2  _(recorded under topic `hopes.carried`)_
- Does: session `turn`
- Then opens: `conversations.scene.hopes.followup`
- …where the player's next choices will be: "That's the year, then."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.the_long_one.explained
WHO    VILLAGER — what the player reads after pressing "What would move it along?"
       spoken on: conversations.scene.hopes.the_long_one.respond, button `ask_what_would_help`
       leaves the player on: conversations.scene.hopes.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.the_long_one.open.explained`: the villager explains. Subject `hopes.carried`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:hopes` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.hopes.the_long_one.explained/1   [103 chars]
    en  Time and one conversation I have been avoiding for four years, and the conversation is the whole of it.
    >>  ............................................
    pt  Tempo e uma conversa que eu venho evitando há quatro anos, e a conversa é tudo.
    >>  ............................................
  dialogue.conversations.scene.hopes.the_long_one.explained/2   [116 chars]
    en  Nothing you could do, and I want to be clear that I am not saying that to be gracious. It is genuinely mine to move.
    >>  ............................................
    pt  Nada que você possa fazer, e quero deixar claro que não digo isso por gentileza. É genuinamente minha para mover.
    >>  ............................................
  dialogue.conversations.scene.hopes.the_long_one.explained/3   [110 chars]
    en  Somebody asking me about it once a year without pushing. That is what you are doing now and it is not nothing.
    >>  ............................................
    pt  Alguém me perguntando uma vez por ano sem empurrar. É o que você está fazendo agora e não é pouco.
    >>  ............................................
```


### Button `keep_it_safe` — "It's safe with me."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `hopes.the_long_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.hopes.the_long_one.keep_it_safe` — accepted phrasings: "its safe with me"; "it is safe with me"; "i will keep that to myself"
  - the message must contain one of: `safe`, `keep`, `myself`
  - scored words: `safe`(1.8), `keep`(1.8), `myself`(1.8), `its`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.the_long_one.respond.keep_it_safe
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.hopes.the_long_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.hopes.the_long_one.respond.keep_it_safe   [18 chars]
    en  It's safe with me.
    >>  ............................................
    pt  Está seguro comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.hopes.kept`, budget `deep`, replay policy `once`
- Does: disposition — trust +5, warmth +3  _(recorded under topic `hopes.carried`)_
- Does: session `turn`
- Then opens: `conversations.scene.hopes.followup`
- …where the player's next choices will be: "That's the year, then."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.the_long_one.steadied
WHO    VILLAGER — what the player reads after pressing "It's safe with me."
       spoken on: conversations.scene.hopes.the_long_one.respond, button `keep_it_safe`
       leaves the player on: conversations.scene.hopes.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.the_long_one.open.steadied`: the villager accepts. Subject `hopes.carried`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:hopes` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.hopes.the_long_one.steadied/1   [100 chars]
    en  I know. That is why it got said. There are two people it can be said to and you are one of them now.
    >>  ............................................
    pt  Eu sei. É por isso que foi dito. São duas pessoas a quem isso pode ser dito e agora você é uma delas.
    >>  ............................................
  dialogue.conversations.scene.hopes.the_long_one.steadied/2   [107 chars]
    en  Thank you. A hope that is repeated at the well stops being a hope and becomes a thing people ask you about.
    >>  ............................................
    pt  Obrigada. Uma esperança repetida no poço deixa de ser esperança e vira uma coisa sobre a qual perguntam.
    >>  ............................................
  dialogue.conversations.scene.hopes.the_long_one.steadied/3   [133 chars]
    en  Then it is in two heads instead of one, which is either safer or twice as likely to get out, and I have decided to believe the first.
    >>  ............................................
    pt  Então está em duas cabeças em vez de uma, o que é mais seguro ou duas vezes mais provável de vazar, e eu decidi acreditar na primeira.
    >>  ............................................
```


### Button `leave` — "I hope it comes off."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.the_long_one.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.the_long_one.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.hopes.the_long_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.hopes.the_long_one.respond.leave   [20 chars]
    en  I hope it comes off.
    >>  ............................................
    pt  Espero que dê certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.leaving
WHO    VILLAGER — what the player reads after pressing "I hope it comes off."
       spoken on: conversations.scene.hopes.the_long_one.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.scene.leaving`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.hopes.followup / leave; conversations.scene.hopes.spring_list.respond / leave
```

> Written out in full under **`conversations.scene.hopes.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.hopes.belittled.followup`

**Reached from 1 route(s):** `conversations.topic.hopes.respond` / `mock`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.hopes.respond.mock` — e.g. "...It's a small thing, %1$s. That's what makes it sting."


```text
POOL   dialogue key: dialogue.conversations.topic.hopes.belittled.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.hopes.belittled.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.hopes.belittled.followup   [33 chars]
    en  I'll keep the next one to myself.
    >>  ............................................
    pt  A próxima eu guardo pra mim.
    >>  ............................................
```


### Button `apologize` — "Don't. That was small of me."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `hopes.rebuked` · offered only once the villager has actually said `player:belittled_the_hope`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.rebuked.apologize` — accepted phrasings: "don't. that was small of me"
  - the message must contain one of: `small`, `petty`
  - scored words: `small`(1.5), `petty`(1.5), `sorry`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.belittled.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.belittled.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.belittled.followup.apologize   [28 chars]
    en  Don't. That was small of me.
    >>  ............................................
    pt  Não faça isso. Fui mesquinho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `hopes.rebuked.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.rebuked.apologize
WHO    VILLAGER — what the player reads after pressing "Don't. That was small of me."
       spoken on: conversations.topic.hopes.belittled.followup, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.rebuked.apologize`: the villager qualifys. Subject `hopes.wish`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.hopes.rebuked.apologize/1   [59 chars]
    en  ...It was. But you said so, which is more than most manage.
    >>  ............................................
    pt  ...Foi. Mas você admitiu, o que é mais do que a maioria consegue.
    >>  ............................................
  dialogue.conversations.hopes.rebuked.apologize/2   [51 chars]
    en  Small. Aye. Hopes are easy to be small about, %1$s.
    >>  ............................................
    pt  Mesquinho. É. É fácil ser mesquinho com esperança, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.rebuked.apologize/3   [38 chars]
    en  Then I'll tell you the next one. Once.
    >>  ............................................
    pt  Então eu te conto a próxima. Uma vez.
    >>  ............................................
```


### Button `explain` — "It sounded bigger than you meant it, I think."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `hopes.rebuked`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.rebuked.explain` — accepted phrasings: "it sounded bigger than you meant it, i think"
  - the message must contain one of: `sounded`, `bigger`
  - scored words: `sounded`(1.5), `bigger`(1.5), `meant`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.belittled.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.belittled.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.belittled.followup.explain   [45 chars]
    en  It sounded bigger than you meant it, I think.
    >>  ............................................
    pt  Acho que soou maior do que você quis dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `hopes.rebuked.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.rebuked.explain
WHO    VILLAGER — what the player reads after pressing "It sounded bigger than you meant it, I think."
       spoken on: conversations.topic.hopes.belittled.followup, button `explain`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.rebuked.explain`: the villager qualifys. Subject `hopes.wish`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.hopes.rebuked.explain/1   [68 chars]
    en  ...It did. That's why I said it quietly and you said it back loudly.
    >>  ............................................
    pt  ...Soou. Por isso eu disse baixo e você repetiu alto.
    >>  ............................................
  dialogue.conversations.hopes.rebuked.explain/2   [74 chars]
    en  Everything sounds bigger said out loud, %1$s. That's why nobody says them.
    >>  ............................................
    pt  Tudo soa maior dito em voz alta, %1$s. É por isso que ninguém diz.
    >>  ............................................
  dialogue.conversations.hopes.rebuked.explain/3   [46 chars]
    en  Perhaps. It's still mine, whatever size it is.
    >>  ............................................
    pt  Talvez. Ainda é minha, do tamanho que for.
    >>  ............................................
```


### Button `leave` — "I'll say no more about it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `hopes.rebuked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.belittled.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.belittled.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.belittled.followup.leave   [26 chars]
    en  I'll say no more about it.
    >>  ............................................
    pt  Não falo mais disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.rebuked.leave
WHO    VILLAGER — what the player reads after pressing "I'll say no more about it."
       spoken on: conversations.topic.hopes.belittled.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.rebuked.leave`: the villager accepts. Subject `hopes.wish`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.hopes.rebuked.leave/1   [14 chars]
    en  Best that way.
    >>  ............................................
    pt  Melhor assim.
    >>  ............................................
  dialogue.conversations.hopes.rebuked.leave/2   [24 chars]
    en  Quite. Off you go, %1$s.
    >>  ............................................
    pt  Exato. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.rebuked.leave/3   [37 chars]
    en  Mm. Ask me about the weather instead.
    >>  ............................................
    pt  Mm. Me pergunte do tempo em vez disso.
    >>  ............................................
```

---


## `conversations.topic.hopes.close`

**Reached from 5 route(s):** `conversations.arc.hopes.resume.followup` / `share_hope`; `conversations.arc.hopes.resume.followup` / `practical`; `conversations.arc.hopes.resume.followup` / `temper`; `conversations.topic.hopes.followup` / `ask_first_step`; `conversations.topic.hopes.followup` / `share_own`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.hopes.followup.ask_first_step` — e.g. "...I've never got that far. Break it down, you say. Alright: first the wall, then the roof."
- `conversations.hopes.followup.share_own` — e.g. "Do you? Then we're both waiting on something. Good company."
- `conversations.hopes.resume.followup.practical` — e.g. "Move it along. Right. Less hoping and more Tuesday, is what you're saying."
- `conversations.hopes.resume.followup.share_hope` — e.g. "You too, now. That's twice the hoping and none of the extra work. I'll take that trade."
- `conversations.hopes.resume.followup.temper` — e.g. "I know. I've been careful not to build the house before the ground's dry."


```text
POOL   dialogue key: dialogue.conversations.topic.hopes.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.hopes.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.hopes.close   [22 chars]
    en  Anyway. It's said now.
    >>  ............................................
    pt  Enfim. Já está dito.
    >>  ............................................
```


### Button `thank` — "Thank you for telling me."

*stance family `candor` · tone `gentle` · answers the beat(s) `hopes.followup.ask_first_step.to.hopes`, `hopes.followup.share_own.to.hopes`, `hopes.resume.followup.practical.to.hopes`, `hopes.resume.followup.share_hope.to.hopes`, `hopes.resume.followup.temper.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.close.thank` — accepted phrasings: "thank you for telling me"; "thank you for the hope"; "i am grateful you told me"
  - the message must contain one of: `thank`, `telling`
  - scored words: `thank`(1.5), `telling`(1.2), `hope`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.close.thank   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.close.thank`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, trust +1  _(recorded under topic `hopes.close.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.close.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.hopes.close, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.close.thank.terminal`: the villager accepts. Subject `hopes.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.close.thank/1   [62 chars]
    en  Thank you for not laughing, more like. That's the harder half.
    >>  ............................................
    pt  Obrigado por não rir, isso sim. Essa é a metade difícil.
    >>  ............................................
  dialogue.conversations.hopes.close.thank/2   [72 chars]
    en  You're welcome, %1$s. Now forget I said it, in case saying it spoils it.
    >>  ............................................
    pt  De nada, %1$s. Agora esqueça que eu disse, vai que dizer estraga.
    >>  ............................................
  dialogue.conversations.hopes.close.thank/3   [75 chars]
    en  So it is. It's an embarrassing thing, hoping out loud. You made it less so.
    >>  ............................................
    pt  É assim mesmo. É constrangedor, esperar em voz alta. Você deixou menos.
    >>  ............................................
```


### Button `say_means` — "That took something to say."

*stance family `candor` · tone `gentle` · answers the beat(s) `hopes.followup.ask_first_step.to.hopes`, `hopes.followup.share_own.to.hopes`, `hopes.resume.followup.practical.to.hopes`, `hopes.resume.followup.share_hope.to.hopes`, `hopes.resume.followup.temper.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.close.say_means` — accepted phrasings: "that took something to say"; "that was brave of you"; "that took courage"
  - the message must contain one of: `took`, `brave`, `courage`
  - scored words: `took`(1.5), `brave`(1.2), `courage`(1.5), `hope`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.close.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.close.say_means   [27 chars]
    en  That took something to say.
    >>  ............................................
    pt  Falar isso exigiu coragem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.close.say_means`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `hopes.close.say_means`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.close.say_means
WHO    VILLAGER — what the player reads after pressing "That took something to say."
       spoken on: conversations.topic.hopes.close, button `say_means`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.close.say_means.terminal`: the villager accepts. Subject `hopes.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.close.say_means/1   [73 chars]
    en  ...It did. Hoping where somebody can hear you is a small act of daftness.
    >>  ............................................
    pt  ...Exigiu. Esperar onde alguém pode ouvir é um pequeno ato de bobagem.
    >>  ............................................
  dialogue.conversations.hopes.close.say_means/2   [55 chars]
    en  It's the being-seen-hoping that's hard. Not the hoping.
    >>  ............................................
    pt  O difícil é ser visto esperando. Não o esperar.
    >>  ............................................
  dialogue.conversations.hopes.close.say_means/3   [90 chars]
    en  You noticed that. It's the softest thing I own, %1$s, and I just left it out on the table.
    >>  ............................................
    pt  Você reparou. É a coisa mais mole que eu tenho, %1$s, e eu acabei de deixar em cima da mesa.
    >>  ............................................
```


### Button `confide` — "I'll tell you mine, if you want it."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `hopes.followup.ask_first_step.to.hopes`, `hopes.followup.share_own.to.hopes`, `hopes.resume.followup.practical.to.hopes`, `hopes.resume.followup.share_hope.to.hopes`, `hopes.resume.followup.temper.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.close.confide` — accepted phrasings: "i will tell you mine if you want it"; "let me tell you mine"; "here is mine then"
  - the message must contain one of: `mine`
  - scored words: `mine`(1.5), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.close.confide
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.close.confide   [35 chars]
    en  I'll tell you mine, if you want it.
    >>  ............................................
    pt  Eu te digo a minha, se quiser.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.close.confide`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, familiarity +4  _(recorded under topic `hopes.close.confide`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.close.confide
WHO    VILLAGER — what the player reads after pressing "I'll tell you mine, if you want it."
       spoken on: conversations.topic.hopes.close, button `confide`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.close.confide.terminal`: the villager discloses. Subject `hopes.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.close.confide/1   [82 chars]
    en  ...Go on, then. It's easier to hope at something when there's two of you doing it.
    >>  ............................................
    pt  ...Pode dizer. É mais fácil esperar por algo quando são dois esperando.
    >>  ............................................
  dialogue.conversations.hopes.close.confide/2   [80 chars]
    en  You've one of your own. Of course you have. Everyone does and nobody says, %1$s.
    >>  ............................................
    pt  Você tem uma sua. Claro que tem. Todo mundo tem e ninguém diz, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.close.confide/3   [70 chars]
    en  Aye — and now we're both stood here being daft about the future. Good.
    >>  ............................................
    pt  É — e agora nós dois estamos aqui bancando os bobos com o futuro. Ótimo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.close.confide/1
    en  ...Go on, then. It's easier to hope at something when there's two of you doing it, %1$s.
    >>  ............................................
    pt  ...Vá em frente. É mais fácil esperar por algo quando tem dois fazendo, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.hopes.close.confide/2
    en  You didn't have to trade. It makes mine feel less foolish and I'd not expected that.
    >>  ............................................
    pt  Você não precisava trocar. Faz o meu parecer menos bobo e eu não esperava isso.
    >>  ............................................
  anxious.dialogue.conversations.hopes.close.confide/3
    en  Two of us. I've been hoping at this alone for a long time.
    >>  ............................................
    pt  Dois de nós. Venho esperando por isso sozinho há muito tempo.
    >>  ............................................
  athletic.dialogue.conversations.hopes.close.confide/1
    en  Go on, then. Hopes keep better in pairs, in my experience.
    >>  ............................................
    pt  Vá em frente. Esperanças se conservam melhor em pares, na minha experiência.
    >>  ............................................
  athletic.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine. Neither of us is in a hurry, which is the right pace for hoping.
    >>  ............................................
    pt  O seu pelo meu. Nenhum de nós com pressa, que é o ritmo certo pra esperar.
    >>  ............................................
  athletic.dialogue.conversations.hopes.close.confide/3
    en  Two of us. It'll take as long as it takes and now there's company in the waiting.
    >>  ............................................
    pt  Dois de nós. Vai levar o tempo que levar e agora tem companhia na espera.
    >>  ............................................
  confident.dialogue.conversations.hopes.close.confide/1
    en  Go on, then. It's easier to hope at something when there's two of you doing it.
    >>  ............................................
    pt  Vá em frente. É mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  confident.dialogue.conversations.hopes.close.confide/2
    en  Right. Yours for mine. That's a fair trade and I'll take it.
    >>  ............................................
    pt  Certo. O seu pelo meu. É troca justa e eu aceito.
    >>  ............................................
  confident.dialogue.conversations.hopes.close.confide/3
    en  Two of us hoping. That's more than I had this morning.
    >>  ............................................
    pt  Dois esperando. É mais do que eu tinha de manhã.
    >>  ............................................
  crabby.dialogue.conversations.hopes.close.confide/1
    en  Go on, then. It's easier to hope at something when there's two of you doing it.
    >>  ............................................
    pt  Vá em frente. É mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  crabby.dialogue.conversations.hopes.close.confide/2
    en  Right. Yours for mine. That's a fair trade and I'll take it.
    >>  ............................................
    pt  Certo. O seu pelo meu. É troca justa e eu aceito.
    >>  ............................................
  crabby.dialogue.conversations.hopes.close.confide/3
    en  Two of us hoping. That's more than I had this morning.
    >>  ............................................
    pt  Dois esperando. É mais do que eu tinha de manhã.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.close.confide/1
    en  Go on, then, %1$s. It's easier to hope at something when there's two of you doing it.
    >>  ............................................
    pt  Vá em frente, %1$s. É mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.close.confide/2
    en  You'd trade yours for mine. I'll not forget that, and I'll ask you about yours next week.
    >>  ............................................
    pt  Você trocaria o seu pelo meu. Eu não vou esquecer, e semana que vem eu pergunto do seu.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.close.confide/3
    en  Two of us. That's what I'd been missing and I hadn't known to say so.
    >>  ............................................
    pt  Dois de nós. Era o que estava faltando e eu não sabia dizer.
    >>  ............................................
  flirty.dialogue.conversations.hopes.close.confide/1
    en  Go on, then, %1$s. It's easier to hope at something when there's two of you doing it.
    >>  ............................................
    pt  Vá em frente, %1$s. É mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  flirty.dialogue.conversations.hopes.close.confide/2
    en  You'd trade yours for mine. I'll not forget that, and I'll ask you about yours next week.
    >>  ............................................
    pt  Você trocaria o seu pelo meu. Eu não vou esquecer, e semana que vem eu pergunto do seu.
    >>  ............................................
  flirty.dialogue.conversations.hopes.close.confide/3
    en  Two of us. That's what I'd been missing and I hadn't known to say so.
    >>  ............................................
    pt  Dois de nós. Era o que estava faltando e eu não sabia dizer.
    >>  ............................................
  friendly.dialogue.conversations.hopes.close.confide/1
    en  Go on, then, %1$s. It's easier to hope at something when there's two of you doing it.
    >>  ............................................
    pt  Vá em frente, %1$s. É mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  friendly.dialogue.conversations.hopes.close.confide/2
    en  You'd trade yours for mine. I'll not forget that, and I'll ask you about yours next week.
    >>  ............................................
    pt  Você trocaria o seu pelo meu. Eu não vou esquecer, e semana que vem eu pergunto do seu.
    >>  ............................................
  friendly.dialogue.conversations.hopes.close.confide/3
    en  Two of us. That's what I'd been missing and I hadn't known to say so.
    >>  ............................................
    pt  Dois de nós. Era o que estava faltando e eu não sabia dizer.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.close.confide/1
    en  ...Go on, then. It's easier to hope at something when there's two of you doing it, %1$s.
    >>  ............................................
    pt  ...Vá em frente. É mais fácil esperar por algo quando tem dois fazendo, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.close.confide/2
    en  You didn't have to trade. It makes mine feel less foolish and I'd not expected that.
    >>  ............................................
    pt  Você não precisava trocar. Faz o meu parecer menos bobo e eu não esperava isso.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.close.confide/3
    en  Two of us. I've been hoping at this alone for a long time.
    >>  ............................................
    pt  Dois de nós. Venho esperando por isso sozinho há muito tempo.
    >>  ............................................
  greedy.dialogue.conversations.hopes.close.confide/1
    en  Go on, then. It's easier to hope at something when there's two of you doing it.
    >>  ............................................
    pt  Vá em frente. É mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  greedy.dialogue.conversations.hopes.close.confide/2
    en  Right. Yours for mine. That's a fair trade and I'll take it.
    >>  ............................................
    pt  Certo. O seu pelo meu. É troca justa e eu aceito.
    >>  ............................................
  greedy.dialogue.conversations.hopes.close.confide/3
    en  Two of us hoping. That's more than I had this morning.
    >>  ............................................
    pt  Dois esperando. É mais do que eu tinha de manhã.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.close.confide/1
    en  Go on, then. It's easier to hope at something when there's two of you doing it.
    >>  ............................................
    pt  Vá em frente. É mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.close.confide/2
    en  Right. Yours for mine. That's a fair trade and I'll take it.
    >>  ............................................
    pt  Certo. O seu pelo meu. É troca justa e eu aceito.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.close.confide/3
    en  Two of us hoping. That's more than I had this morning.
    >>  ............................................
    pt  Dois esperando. É mais do que eu tinha de manhã.
    >>  ............................................
  introverted.dialogue.conversations.hopes.close.confide/1
    en  ...Go on, then.
    >>  ............................................
    pt  ...Vá em frente.
    >>  ............................................
  introverted.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine. Alright.
    >>  ............................................
    pt  O seu pelo meu. Está bem.
    >>  ............................................
  introverted.dialogue.conversations.hopes.close.confide/3
    en  ...Two of us. That's better.
    >>  ............................................
    pt  ...Dois de nós. É melhor.
    >>  ............................................
  lazy.dialogue.conversations.hopes.close.confide/1
    en  Go on, then. Hopes keep better in pairs, in my experience.
    >>  ............................................
    pt  Vá em frente. Esperanças se conservam melhor em pares, na minha experiência.
    >>  ............................................
  lazy.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine. Neither of us is in a hurry, which is the right pace for hoping.
    >>  ............................................
    pt  O seu pelo meu. Nenhum de nós com pressa, que é o ritmo certo pra esperar.
    >>  ............................................
  lazy.dialogue.conversations.hopes.close.confide/3
    en  Two of us. It'll take as long as it takes and now there's company in the waiting.
    >>  ............................................
    pt  Dois de nós. Vai levar o tempo que levar e agora tem companhia na espera.
    >>  ............................................
  odd.dialogue.conversations.hopes.close.confide/1
    en  ...Go on, then.
    >>  ............................................
    pt  ...Vá em frente.
    >>  ............................................
  odd.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine. Alright.
    >>  ............................................
    pt  O seu pelo meu. Está bem.
    >>  ............................................
  odd.dialogue.conversations.hopes.close.confide/3
    en  ...Two of us. That's better.
    >>  ............................................
    pt  ...Dois de nós. É melhor.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.close.confide/1
    en  Go on, then. Hopes keep better in pairs, in my experience.
    >>  ............................................
    pt  Vá em frente. Esperanças se conservam melhor em pares, na minha experiência.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine. Neither of us is in a hurry, which is the right pace for hoping.
    >>  ............................................
    pt  O seu pelo meu. Nenhum de nós com pressa, que é o ritmo certo pra esperar.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.close.confide/3
    en  Two of us. It'll take as long as it takes and now there's company in the waiting.
    >>  ............................................
    pt  Dois de nós. Vai levar o tempo que levar e agora tem companhia na espera.
    >>  ............................................
  peppy.dialogue.conversations.hopes.close.confide/1
    en  Go on, then! It's much easier to hope at something when there are two of you doing it.
    >>  ............................................
    pt  Vá em frente! É muito mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  peppy.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine! Excellent. Now it's a scheme and schemes get done.
    >>  ............................................
    pt  O seu pelo meu! Excelente. Agora é um esquema e esquemas se realizam.
    >>  ............................................
  peppy.dialogue.conversations.hopes.close.confide/3
    en  Two of us hoping. That's a considerable improvement on this morning.
    >>  ............................................
    pt  Dois esperando. É uma melhora considerável em relação à manhã.
    >>  ............................................
  playful.dialogue.conversations.hopes.close.confide/1
    en  Go on, then! It's much easier to hope at something when there are two of you doing it.
    >>  ............................................
    pt  Vá em frente! É muito mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  playful.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine! Excellent. Now it's a scheme and schemes get done.
    >>  ............................................
    pt  O seu pelo meu! Excelente. Agora é um esquema e esquemas se realizam.
    >>  ............................................
  playful.dialogue.conversations.hopes.close.confide/3
    en  Two of us hoping. That's a considerable improvement on this morning.
    >>  ............................................
    pt  Dois esperando. É uma melhora considerável em relação à manhã.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.close.confide/1
    en  Go on, then. Hopes keep better in pairs, in my experience.
    >>  ............................................
    pt  Vá em frente. Esperanças se conservam melhor em pares, na minha experiência.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine. Neither of us is in a hurry, which is the right pace for hoping.
    >>  ............................................
    pt  O seu pelo meu. Nenhum de nós com pressa, que é o ritmo certo pra esperar.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.close.confide/3
    en  Two of us. It'll take as long as it takes and now there's company in the waiting.
    >>  ............................................
    pt  Dois de nós. Vai levar o tempo que levar e agora tem companhia na espera.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.close.confide/1
    en  ...Go on, then. It's easier to hope at something when there's two of you doing it, %1$s.
    >>  ............................................
    pt  ...Vá em frente. É mais fácil esperar por algo quando tem dois fazendo, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.close.confide/2
    en  You didn't have to trade. It makes mine feel less foolish and I'd not expected that.
    >>  ............................................
    pt  Você não precisava trocar. Faz o meu parecer menos bobo e eu não esperava isso.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.close.confide/3
    en  Two of us. I've been hoping at this alone for a long time.
    >>  ............................................
    pt  Dois de nós. Venho esperando por isso sozinho há muito tempo.
    >>  ............................................
  shy.dialogue.conversations.hopes.close.confide/1
    en  ...Go on, then.
    >>  ............................................
    pt  ...Vá em frente.
    >>  ............................................
  shy.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine. Alright.
    >>  ............................................
    pt  O seu pelo meu. Está bem.
    >>  ............................................
  shy.dialogue.conversations.hopes.close.confide/3
    en  ...Two of us. That's better.
    >>  ............................................
    pt  ...Dois de nós. É melhor.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.close.confide/1
    en  Go on, then! It's much easier to hope at something when there are two of you doing it.
    >>  ............................................
    pt  Vá em frente! É muito mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine! Excellent. Now it's a scheme and schemes get done.
    >>  ............................................
    pt  O seu pelo meu! Excelente. Agora é um esquema e esquemas se realizam.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.close.confide/3
    en  Two of us hoping. That's a considerable improvement on this morning.
    >>  ............................................
    pt  Dois esperando. É uma melhora considerável em relação à manhã.
    >>  ............................................
  witty.dialogue.conversations.hopes.close.confide/1
    en  Go on, then! It's much easier to hope at something when there are two of you doing it.
    >>  ............................................
    pt  Vá em frente! É muito mais fácil esperar por algo quando tem dois fazendo.
    >>  ............................................
  witty.dialogue.conversations.hopes.close.confide/2
    en  Yours for mine! Excellent. Now it's a scheme and schemes get done.
    >>  ............................................
    pt  O seu pelo meu! Excelente. Agora é um esquema e esquemas se realizam.
    >>  ............................................
  witty.dialogue.conversations.hopes.close.confide/3
    en  Two of us hoping. That's a considerable improvement on this morning.
    >>  ............................................
    pt  Dois esperando. É uma melhora considerável em relação à manhã.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.followup.ask_first_step.to.hopes`, `hopes.followup.share_own.to.hopes`, `hopes.resume.followup.practical.to.hopes`, `hopes.resume.followup.share_hope.to.hopes`, `hopes.resume.followup.temper.to.hopes` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.close.leave   [16 chars]
    en  I'll let you be.
    >>  ............................................
    pt  Vou te deixar em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.close.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.hopes.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.close.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.close.leave/1   [26 chars]
    en  Aye. Go on, and thank you.
    >>  ............................................
    pt  Tá. Pode ir, e obrigado.
    >>  ............................................
  dialogue.conversations.hopes.close.leave/2   [34 chars]
    en  Right. Enough of that for one day.
    >>  ............................................
    pt  Certo. Já chega disso por um dia.
    >>  ............................................
  dialogue.conversations.hopes.close.leave/3   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
```

---


## `conversations.topic.hopes.followup`

**Reached from 3 route(s):** `conversations.topic.hopes.respond` / `listen`; `conversations.topic.hopes.respond` / `listen`; `conversations.topic.hopes.respond` / `encourage`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.hopes.listen.low` — e.g. "...You'll wait. That's decent of you. I'm not sure I've got the hoping in me today, mind."
- `conversations.hopes.respond.encourage` — e.g. "So do I. It helps, hearing someone else say it."
- `conversations.hopes.respond.listen` — e.g. "...Right. Then here's the rest of it, since you're actually waiting."


```text
POOL   dialogue key: dialogue.conversations.topic.hopes.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.hopes.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.hopes.followup   [27 chars]
    en  That's what I'm hoping for.
    >>  ............................................
    pt  É isso que eu espero.
    >>  ............................................
```


### Button `ask_first_step` — "What would the first step be?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `hopes.listen.low.to.hopes`, `hopes.respond.encourage.to.hopes`, `hopes.respond.listen.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.followup.ask_first_step` — accepted phrasings: "what would the first step be"; "where would you start"; "what is step one"
  - the message must contain one of: `first`, `step`, `start`
  - scored words: `first`(1.5), `step`(1.5), `start`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.followup.ask_first_step
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.followup.ask_first_step   [29 chars]
    en  What would the first step be?
    >>  ............................................
    pt  Qual seria o primeiro passo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `hopes.followup.ask_first_step`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +3  _(recorded under topic `hopes.followup.ask_first_step`)_
- Does: arc `hopes` — advance to stage 1
- Does: milestone `hopes.named` set (fires once, ever)
- Then opens: `conversations.topic.hopes.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you mine, if you want it." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.hopes.followup.ask_first_step
WHO    VILLAGER — what the player reads after pressing "What would the first step be?"
       spoken on: conversations.topic.hopes.followup, button `ask_first_step`
       leaves the player on: conversations.topic.hopes.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.followup.ask_first_step.to.hopes`: the villager accepts. Subject `hopes`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.followup.ask_first_step/1   [91 chars]
    en  ...I've never got that far. Break it down, you say. Alright: first the wall, then the roof.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe. Destrinchar, você diz. Tudo bem: primeiro a parede, depois o telhado.
    >>  ............................................
  dialogue.conversations.hopes.followup.ask_first_step/2   [60 chars]
    en  The first step. Huh. That makes it a plan instead of a wish.
    >>  ............................................
    pt  O primeiro passo. Hm. Isso transforma em plano em vez de desejo.
    >>  ............................................
  dialogue.conversations.hopes.followup.ask_first_step/3   [69 chars]
    en  Give me a day on that one. It's a better question than I'm ready for.
    >>  ............................................
    pt  Me dá um dia para essa. É uma pergunta melhor do que eu estou pronto para responder.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far, %1$s. The whole of it has always been the thing that stopped me.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe, %1$s. A coisa inteira sempre foi o que me parava.
    >>  ............................................
  anxious.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. I've been frightened of the last one for so long I never looked at the first.
    >>  ............................................
    pt  O primeiro passo. Tenho medo do último há tanto tempo que nunca olhei pro primeiro.
    >>  ............................................
  anxious.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. Even one frightens me a little, and that's better than all of it frightening me.
    >>  ............................................
    pt  Um passo. Até um me assusta um pouco, e é melhor que tudo me assustar.
    >>  ............................................
  athletic.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far. Break it down, you say. Alright — and it'll take a year or two.
    >>  ............................................
    pt  Eu nunca cheguei tão longe. Dividir, você diz. Está bem — e vai levar um ano ou dois.
    >>  ............................................
  athletic.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Then another, in its own time. That's how anything large gets done.
    >>  ............................................
    pt  O primeiro passo. Depois outro, no tempo dele. É assim que algo grande se faz.
    >>  ............................................
  athletic.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. There's no hurry past that one; I'd only lose my footing.
    >>  ............................................
    pt  Um passo. Não há pressa além dele; eu só perderia o pé.
    >>  ............................................
  confident.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far. Break it down, you say. Alright: first the money.
    >>  ............................................
    pt  Eu nunca cheguei tão longe. Dividir, você diz. Está bem: primeiro o dinheiro.
    >>  ............................................
  confident.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Right. Nobody's asked me to say one out loud before.
    >>  ............................................
    pt  O primeiro passo. Certo. Ninguém tinha me pedido pra dizer um em voz alta.
    >>  ............................................
  confident.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. I can name one. I couldn't name the whole of it.
    >>  ............................................
    pt  Um passo. Um eu sei nomear. A coisa inteira eu não sabia.
    >>  ............................................
  crabby.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far. Break it down, you say. Alright: first the money.
    >>  ............................................
    pt  Eu nunca cheguei tão longe. Dividir, você diz. Está bem: primeiro o dinheiro.
    >>  ............................................
  crabby.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Right. Nobody's asked me to say one out loud before.
    >>  ............................................
    pt  O primeiro passo. Certo. Ninguém tinha me pedido pra dizer um em voz alta.
    >>  ............................................
  crabby.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. I can name one. I couldn't name the whole of it.
    >>  ............................................
    pt  Um passo. Um eu sei nomear. A coisa inteira eu não sabia.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far, %1$s. Break it down, you say. Alright: first the money.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe, %1$s. Dividir, você diz. Está bem: primeiro o dinheiro.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Sit down — I'd like to work it out with somebody rather than at somebody.
    >>  ............................................
    pt  O primeiro passo. Sente-se — eu queria resolver isso com alguém e não diante de alguém.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. You've made it a thing I could start, which nobody else has managed.
    >>  ............................................
    pt  Um passo. Você transformou em algo que eu poderia começar, o que mais ninguém conseguiu.
    >>  ............................................
  flirty.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far, %1$s. Break it down, you say. Alright: first the money.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe, %1$s. Dividir, você diz. Está bem: primeiro o dinheiro.
    >>  ............................................
  flirty.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Sit down — I'd like to work it out with somebody rather than at somebody.
    >>  ............................................
    pt  O primeiro passo. Sente-se — eu queria resolver isso com alguém e não diante de alguém.
    >>  ............................................
  flirty.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. You've made it a thing I could start, which nobody else has managed.
    >>  ............................................
    pt  Um passo. Você transformou em algo que eu poderia começar, o que mais ninguém conseguiu.
    >>  ............................................
  friendly.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far, %1$s. Break it down, you say. Alright: first the money.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe, %1$s. Dividir, você diz. Está bem: primeiro o dinheiro.
    >>  ............................................
  friendly.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Sit down — I'd like to work it out with somebody rather than at somebody.
    >>  ............................................
    pt  O primeiro passo. Sente-se — eu queria resolver isso com alguém e não diante de alguém.
    >>  ............................................
  friendly.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. You've made it a thing I could start, which nobody else has managed.
    >>  ............................................
    pt  Um passo. Você transformou em algo que eu poderia começar, o que mais ninguém conseguiu.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far, %1$s. The whole of it has always been the thing that stopped me.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe, %1$s. A coisa inteira sempre foi o que me parava.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. I've been frightened of the last one for so long I never looked at the first.
    >>  ............................................
    pt  O primeiro passo. Tenho medo do último há tanto tempo que nunca olhei pro primeiro.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. Even one frightens me a little, and that's better than all of it frightening me.
    >>  ............................................
    pt  Um passo. Até um me assusta um pouco, e é melhor que tudo me assustar.
    >>  ............................................
  greedy.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far. Break it down, you say. Alright: first the money.
    >>  ............................................
    pt  Eu nunca cheguei tão longe. Dividir, você diz. Está bem: primeiro o dinheiro.
    >>  ............................................
  greedy.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Right. Nobody's asked me to say one out loud before.
    >>  ............................................
    pt  O primeiro passo. Certo. Ninguém tinha me pedido pra dizer um em voz alta.
    >>  ............................................
  greedy.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. I can name one. I couldn't name the whole of it.
    >>  ............................................
    pt  Um passo. Um eu sei nomear. A coisa inteira eu não sabia.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far. Break it down, you say. Alright: first the money.
    >>  ............................................
    pt  Eu nunca cheguei tão longe. Dividir, você diz. Está bem: primeiro o dinheiro.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Right. Nobody's asked me to say one out loud before.
    >>  ............................................
    pt  O primeiro passo. Certo. Ninguém tinha me pedido pra dizer um em voz alta.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. I can name one. I couldn't name the whole of it.
    >>  ............................................
    pt  Um passo. Um eu sei nomear. A coisa inteira eu não sabia.
    >>  ............................................
  introverted.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far. Alright: first the money.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe. Está bem: primeiro o dinheiro.
    >>  ............................................
  introverted.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Right.
    >>  ............................................
    pt  O primeiro passo. Certo.
    >>  ............................................
  introverted.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. That I can hold.
    >>  ............................................
    pt  Um passo. Isso eu consigo segurar.
    >>  ............................................
  lazy.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far. Break it down, you say. Alright — and it'll take a year or two.
    >>  ............................................
    pt  Eu nunca cheguei tão longe. Dividir, você diz. Está bem — e vai levar um ano ou dois.
    >>  ............................................
  lazy.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Then another, in its own time. That's how anything large gets done.
    >>  ............................................
    pt  O primeiro passo. Depois outro, no tempo dele. É assim que algo grande se faz.
    >>  ............................................
  lazy.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. There's no hurry past that one; I'd only lose my footing.
    >>  ............................................
    pt  Um passo. Não há pressa além dele; eu só perderia o pé.
    >>  ............................................
  odd.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far. Alright: first the money.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe. Está bem: primeiro o dinheiro.
    >>  ............................................
  odd.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Right.
    >>  ............................................
    pt  O primeiro passo. Certo.
    >>  ............................................
  odd.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. That I can hold.
    >>  ............................................
    pt  Um passo. Isso eu consigo segurar.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far. Break it down, you say. Alright — and it'll take a year or two.
    >>  ............................................
    pt  Eu nunca cheguei tão longe. Dividir, você diz. Está bem — e vai levar um ano ou dois.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Then another, in its own time. That's how anything large gets done.
    >>  ............................................
    pt  O primeiro passo. Depois outro, no tempo dele. É assim que algo grande se faz.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. There's no hurry past that one; I'd only lose my footing.
    >>  ............................................
    pt  Um passo. Não há pressa além dele; eu só perderia o pé.
    >>  ............................................
  peppy.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far! Break it down, you say. Alright — first the money. Ugh.
    >>  ............................................
    pt  Eu nunca cheguei tão longe! Dividir, você diz. Está bem — primeiro o dinheiro. Ugh.
    >>  ............................................
  peppy.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step! Nobody asks for the first step. Everyone asks about the last one.
    >>  ............................................
    pt  O primeiro passo! Ninguém pergunta o primeiro passo. Todos perguntam o último.
    >>  ............................................
  peppy.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. Just one. That is a wildly more manageable question, thank you.
    >>  ............................................
    pt  Um passo. Só um. É uma pergunta absurdamente mais administrável, obrigado.
    >>  ............................................
  playful.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far! Break it down, you say. Alright — first the money. Ugh.
    >>  ............................................
    pt  Eu nunca cheguei tão longe! Dividir, você diz. Está bem — primeiro o dinheiro. Ugh.
    >>  ............................................
  playful.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step! Nobody asks for the first step. Everyone asks about the last one.
    >>  ............................................
    pt  O primeiro passo! Ninguém pergunta o primeiro passo. Todos perguntam o último.
    >>  ............................................
  playful.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. Just one. That is a wildly more manageable question, thank you.
    >>  ............................................
    pt  Um passo. Só um. É uma pergunta absurdamente mais administrável, obrigado.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far. Break it down, you say. Alright — and it'll take a year or two.
    >>  ............................................
    pt  Eu nunca cheguei tão longe. Dividir, você diz. Está bem — e vai levar um ano ou dois.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Then another, in its own time. That's how anything large gets done.
    >>  ............................................
    pt  O primeiro passo. Depois outro, no tempo dele. É assim que algo grande se faz.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. There's no hurry past that one; I'd only lose my footing.
    >>  ............................................
    pt  Um passo. Não há pressa além dele; eu só perderia o pé.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far, %1$s. The whole of it has always been the thing that stopped me.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe, %1$s. A coisa inteira sempre foi o que me parava.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. I've been frightened of the last one for so long I never looked at the first.
    >>  ............................................
    pt  O primeiro passo. Tenho medo do último há tanto tempo que nunca olhei pro primeiro.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. Even one frightens me a little, and that's better than all of it frightening me.
    >>  ............................................
    pt  Um passo. Até um me assusta um pouco, e é melhor que tudo me assustar.
    >>  ............................................
  shy.dialogue.conversations.hopes.followup.ask_first_step/1
    en  ...I've never got that far. Alright: first the money.
    >>  ............................................
    pt  ...Eu nunca cheguei tão longe. Está bem: primeiro o dinheiro.
    >>  ............................................
  shy.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step. Right.
    >>  ............................................
    pt  O primeiro passo. Certo.
    >>  ............................................
  shy.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. That I can hold.
    >>  ............................................
    pt  Um passo. Isso eu consigo segurar.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far! Break it down, you say. Alright — first the money. Ugh.
    >>  ............................................
    pt  Eu nunca cheguei tão longe! Dividir, você diz. Está bem — primeiro o dinheiro. Ugh.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step! Nobody asks for the first step. Everyone asks about the last one.
    >>  ............................................
    pt  O primeiro passo! Ninguém pergunta o primeiro passo. Todos perguntam o último.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. Just one. That is a wildly more manageable question, thank you.
    >>  ............................................
    pt  Um passo. Só um. É uma pergunta absurdamente mais administrável, obrigado.
    >>  ............................................
  witty.dialogue.conversations.hopes.followup.ask_first_step/1
    en  I've never got that far! Break it down, you say. Alright — first the money. Ugh.
    >>  ............................................
    pt  Eu nunca cheguei tão longe! Dividir, você diz. Está bem — primeiro o dinheiro. Ugh.
    >>  ............................................
  witty.dialogue.conversations.hopes.followup.ask_first_step/2
    en  The first step! Nobody asks for the first step. Everyone asks about the last one.
    >>  ............................................
    pt  O primeiro passo! Ninguém pergunta o primeiro passo. Todos perguntam o último.
    >>  ............................................
  witty.dialogue.conversations.hopes.followup.ask_first_step/3
    en  One step. Just one. That is a wildly more manageable question, thank you.
    >>  ............................................
    pt  Um passo. Só um. É uma pergunta absurdamente mais administrável, obrigado.
    >>  ............................................
```

</details>


### Button `share_own` — "I hope for something like that too."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `hopes.listen.low.to.hopes`, `hopes.respond.encourage.to.hopes`, `hopes.respond.listen.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.followup.share_own` — accepted phrasings: "i hope for something like that too"; "i want the same myself"; "me too"
  - the message must contain one of: `too`, `myself`, `same`
  - scored words: `too`(1.2), `myself`(1.2), `same`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.followup.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.followup.share_own   [35 chars]
    en  I hope for something like that too.
    >>  ............................................
    pt  Eu também espero algo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.followup.share_own`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — familiarity +4, warmth +2  _(recorded under topic `hopes.followup.share_own`)_
- Does: arc `hopes` — advance to stage 1
- Then opens: `conversations.topic.hopes.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you mine, if you want it." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.hopes.followup.share_own
WHO    VILLAGER — what the player reads after pressing "I hope for something like that too."
       spoken on: conversations.topic.hopes.followup, button `share_own`
       leaves the player on: conversations.topic.hopes.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.followup.share_own.to.hopes`: the villager accepts. Subject `hopes`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.followup.share_own/1   [59 chars]
    en  Do you? Then we're both waiting on something. Good company.
    >>  ............................................
    pt  Você também? Então nós dois estamos esperando algo. Boa companhia.
    >>  ............................................
  dialogue.conversations.hopes.followup.share_own/2   [37 chars]
    en  Tell me yours. I've shown mine, %1$s.
    >>  ............................................
    pt  Me conta a sua. Eu mostrei a minha, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.followup.share_own/3   [52 chars]
    en  It's easier to hope in twos. I've always thought so.
    >>  ............................................
    pt  É mais fácil esperar em dupla. Sempre achei isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something, %1$s. That helps more than it should.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo, %1$s. Isso ajuda mais do que deveria.
    >>  ............................................
  anxious.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. I'd assumed everyone else had theirs already and was just being polite.
    >>  ............................................
    pt  Você também. Eu supunha que todos já tivessem o deles e só fossem educados.
    >>  ............................................
  anxious.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. It's less lonely to be told that than I'd expected.
    >>  ............................................
    pt  Dois esperando. É menos solitário ouvir isso do que eu esperava.
    >>  ............................................
  athletic.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something, and waiting goes easier in pairs.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo, e esperar vai melhor em dupla.
    >>  ............................................
  athletic.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. These things arrive when they arrive; better to wait alongside somebody.
    >>  ............................................
    pt  Você também. Essas coisas chegam quando chegam; melhor esperar ao lado de alguém.
    >>  ............................................
  athletic.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us. Good. No hurry for either of us, then.
    >>  ............................................
    pt  Dois de nós. Bom. Então sem pressa pra nenhum.
    >>  ............................................
  confident.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo.
    >>  ............................................
  confident.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Right. That's better company than I expected.
    >>  ............................................
    pt  Você também. Certo. É companhia melhor do que eu esperava.
    >>  ............................................
  confident.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. That's a different sort of waiting.
    >>  ............................................
    pt  Dois esperando. É outro tipo de espera.
    >>  ............................................
  crabby.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo.
    >>  ............................................
  crabby.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Right. That's better company than I expected.
    >>  ............................................
    pt  Você também. Certo. É companhia melhor do que eu esperava.
    >>  ............................................
  crabby.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. That's a different sort of waiting.
    >>  ............................................
    pt  Dois esperando. É outro tipo de espera.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.followup.share_own/1
    en  Do you, %1$s? Then we're both waiting on something. Good company.
    >>  ............................................
    pt  Você também, %1$s? Então nós dois esperamos por algo. Boa companhia.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Tell me about yours — I'd rather this went both ways.
    >>  ............................................
    pt  Você também. Me conte a sua — prefiro que isso vá nos dois sentidos.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us. I'll ask about yours next time, and I mean that.
    >>  ............................................
    pt  Dois de nós. Vou perguntar da sua na próxima, e eu falo sério.
    >>  ............................................
  flirty.dialogue.conversations.hopes.followup.share_own/1
    en  Do you, %1$s? Then we're both waiting on something. Good company.
    >>  ............................................
    pt  Você também, %1$s? Então nós dois esperamos por algo. Boa companhia.
    >>  ............................................
  flirty.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Tell me about yours — I'd rather this went both ways.
    >>  ............................................
    pt  Você também. Me conte a sua — prefiro que isso vá nos dois sentidos.
    >>  ............................................
  flirty.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us. I'll ask about yours next time, and I mean that.
    >>  ............................................
    pt  Dois de nós. Vou perguntar da sua na próxima, e eu falo sério.
    >>  ............................................
  friendly.dialogue.conversations.hopes.followup.share_own/1
    en  Do you, %1$s? Then we're both waiting on something. Good company.
    >>  ............................................
    pt  Você também, %1$s? Então nós dois esperamos por algo. Boa companhia.
    >>  ............................................
  friendly.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Tell me about yours — I'd rather this went both ways.
    >>  ............................................
    pt  Você também. Me conte a sua — prefiro que isso vá nos dois sentidos.
    >>  ............................................
  friendly.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us. I'll ask about yours next time, and I mean that.
    >>  ............................................
    pt  Dois de nós. Vou perguntar da sua na próxima, e eu falo sério.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something, %1$s. That helps more than it should.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo, %1$s. Isso ajuda mais do que deveria.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. I'd assumed everyone else had theirs already and was just being polite.
    >>  ............................................
    pt  Você também. Eu supunha que todos já tivessem o deles e só fossem educados.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. It's less lonely to be told that than I'd expected.
    >>  ............................................
    pt  Dois esperando. É menos solitário ouvir isso do que eu esperava.
    >>  ............................................
  greedy.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo.
    >>  ............................................
  greedy.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Right. That's better company than I expected.
    >>  ............................................
    pt  Você também. Certo. É companhia melhor do que eu esperava.
    >>  ............................................
  greedy.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. That's a different sort of waiting.
    >>  ............................................
    pt  Dois esperando. É outro tipo de espera.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Right. That's better company than I expected.
    >>  ............................................
    pt  Você também. Certo. É companhia melhor do que eu esperava.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. That's a different sort of waiting.
    >>  ............................................
    pt  Dois esperando. É outro tipo de espera.
    >>  ............................................
  introverted.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo.
    >>  ............................................
  introverted.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  introverted.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. Good.
    >>  ............................................
    pt  Dois esperando. Bom.
    >>  ............................................
  lazy.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something, and waiting goes easier in pairs.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo, e esperar vai melhor em dupla.
    >>  ............................................
  lazy.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. These things arrive when they arrive; better to wait alongside somebody.
    >>  ............................................
    pt  Você também. Essas coisas chegam quando chegam; melhor esperar ao lado de alguém.
    >>  ............................................
  lazy.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us. Good. No hurry for either of us, then.
    >>  ............................................
    pt  Dois de nós. Bom. Então sem pressa pra nenhum.
    >>  ............................................
  odd.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo.
    >>  ............................................
  odd.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  odd.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. Good.
    >>  ............................................
    pt  Dois esperando. Bom.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something, and waiting goes easier in pairs.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo, e esperar vai melhor em dupla.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. These things arrive when they arrive; better to wait alongside somebody.
    >>  ............................................
    pt  Você também. Essas coisas chegam quando chegam; melhor esperar ao lado de alguém.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us. Good. No hurry for either of us, then.
    >>  ............................................
    pt  Dois de nós. Bom. Então sem pressa pra nenhum.
    >>  ............................................
  peppy.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something! Good company, that.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo! Boa companhia.
    >>  ............................................
  peppy.dialogue.conversations.hopes.followup.share_own/2
    en  You as well! Excellent. Waiting is far better as a pair.
    >>  ............................................
    pt  Você também! Excelente. Esperar é bem melhor em dupla.
    >>  ............................................
  peppy.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. That's practically a society. I'll draft the rules.
    >>  ............................................
    pt  Dois esperando. É praticamente uma sociedade. Eu redijo as regras.
    >>  ............................................
  playful.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something! Good company, that.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo! Boa companhia.
    >>  ............................................
  playful.dialogue.conversations.hopes.followup.share_own/2
    en  You as well! Excellent. Waiting is far better as a pair.
    >>  ............................................
    pt  Você também! Excelente. Esperar é bem melhor em dupla.
    >>  ............................................
  playful.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. That's practically a society. I'll draft the rules.
    >>  ............................................
    pt  Dois esperando. É praticamente uma sociedade. Eu redijo as regras.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something, and waiting goes easier in pairs.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo, e esperar vai melhor em dupla.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. These things arrive when they arrive; better to wait alongside somebody.
    >>  ............................................
    pt  Você também. Essas coisas chegam quando chegam; melhor esperar ao lado de alguém.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us. Good. No hurry for either of us, then.
    >>  ............................................
    pt  Dois de nós. Bom. Então sem pressa pra nenhum.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something, %1$s. That helps more than it should.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo, %1$s. Isso ajuda mais do que deveria.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. I'd assumed everyone else had theirs already and was just being polite.
    >>  ............................................
    pt  Você também. Eu supunha que todos já tivessem o deles e só fossem educados.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. It's less lonely to be told that than I'd expected.
    >>  ............................................
    pt  Dois esperando. É menos solitário ouvir isso do que eu esperava.
    >>  ............................................
  shy.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo.
    >>  ............................................
  shy.dialogue.conversations.hopes.followup.share_own/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  shy.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. Good.
    >>  ............................................
    pt  Dois esperando. Bom.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something! Good company, that.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo! Boa companhia.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.followup.share_own/2
    en  You as well! Excellent. Waiting is far better as a pair.
    >>  ............................................
    pt  Você também! Excelente. Esperar é bem melhor em dupla.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. That's practically a society. I'll draft the rules.
    >>  ............................................
    pt  Dois esperando. É praticamente uma sociedade. Eu redijo as regras.
    >>  ............................................
  witty.dialogue.conversations.hopes.followup.share_own/1
    en  Do you? Then we're both waiting on something! Good company, that.
    >>  ............................................
    pt  Você também? Então nós dois esperamos por algo! Boa companhia.
    >>  ............................................
  witty.dialogue.conversations.hopes.followup.share_own/2
    en  You as well! Excellent. Waiting is far better as a pair.
    >>  ............................................
    pt  Você também! Excelente. Esperar é bem melhor em dupla.
    >>  ............................................
  witty.dialogue.conversations.hopes.followup.share_own/3
    en  Two of us waiting. That's practically a society. I'll draft the rules.
    >>  ............................................
    pt  Dois esperando. É praticamente uma sociedade. Eu redijo as regras.
    >>  ............................................
```

</details>


### Button `question_it` — "Is that really what you want?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `hopes.listen.low.to.hopes`, `hopes.respond.encourage.to.hopes`, `hopes.respond.listen.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.followup.question_it` — accepted phrasings: "is that really what you want"; "are you sure that is what you want"; "do you really want that"
  - the message must contain one of: `really`, `want`, `sure`
  - scored words: `really`(1.5), `want`(1.0), `sure`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.followup.question_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.followup.question_it   [29 chars]
    en  Is that really what you want?
    >>  ............................................
    pt  É isso mesmo que você quer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts +1** — decision id `hopes.followup.question_it`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `hopes.followup.question_it`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.followup.question_it.landed
WHO    VILLAGER — what the player reads after pressing "Is that really what you want?"
       spoken on: conversations.topic.hopes.followup, button `question_it`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.followup.question_it.landed.terminal`: the villager accepts. Subject `hopes.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.followup.question_it.landed/1   [59 chars]
    en  ...Is it. That's a fair question and I've been avoiding it.
    >>  ............................................
    pt  ...Será. É uma pergunta justa e eu venho evitando.
    >>  ............................................
  dialogue.conversations.hopes.followup.question_it.landed/2   [61 chars]
    en  Straight to the bone. Good. Let me actually think about that.
    >>  ............................................
    pt  Direto ao osso. Bom. Deixa eu pensar de verdade nisso.
    >>  ............................................
  dialogue.conversations.hopes.followup.question_it.landed/3   [75 chars]
    en  Hm. It might be the wrong hope, at that. ...You get one back: what's yours?
    >>  ............................................
    pt  Hm. Pode ser a esperança errada, é verdade. ...Você ganha uma de volta: qual é a sua?
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`
- Does: **hearts -1** — decision id `hopes.followup.question_it`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `hopes.followup.question_it`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.followup.question_it.flat
WHO    VILLAGER — what the player reads after pressing "Is that really what you want?"
       spoken on: conversations.topic.hopes.followup, button `question_it`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.followup.question_it.flat.terminal`: the villager accepts. Subject `hopes.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.followup.question_it.flat/1   [44 chars]
    en  ...Yes. It is. Why would I say it otherwise?
    >>  ............................................
    pt  ...Sim. É. Por que eu diria se não fosse?
    >>  ............................................
  dialogue.conversations.hopes.followup.question_it.flat/2   [60 chars]
    en  That's a cold way to take something I just handed you, %1$s.
    >>  ............................................
    pt  É um jeito frio de receber algo que acabei de te entregar, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.followup.question_it.flat/3   [51 chars]
    en  Now I'm doubting it, which I expect was the effect.
    >>  ............................................
    pt  Agora estou em dúvida, o que imagino ser o efeito pretendido.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `hopes.followup.question_it`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.followup.question_it.polite
WHO    VILLAGER — what the player reads after pressing "Is that really what you want?"
       spoken on: conversations.topic.hopes.followup, button `question_it`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.followup.question_it.polite.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.followup.question_it.polite/1   [40 chars]
    en  Most days, yes. Some days I'm less sure.
    >>  ............................................
    pt  Na maioria dos dias, sim. Em alguns dias tenho menos certeza.
    >>  ............................................
  dialogue.conversations.hopes.followup.question_it.polite/2   [63 chars]
    en  It's what I've got. Whether it's what I want is another matter.
    >>  ............................................
    pt  É o que eu tenho. Se é o que eu quero é outra questão.
    >>  ............................................
  dialogue.conversations.hopes.followup.question_it.polite/3   [42 chars]
    en  Fair to ask. I'll not pretend I'm certain.
    >>  ............................................
    pt  Justo perguntar. Não vou fingir que tenho certeza.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.listen.low.to.hopes`, `hopes.respond.encourage.to.hopes`, `hopes.respond.listen.to.hopes` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.followup.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.hopes.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.respond.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.hopes.respond / leave
```

```text
  dialogue.conversations.hopes.respond.leave/1   [34 chars]
    en  It is. Thank you for asking, %1$s.
    >>  ............................................
    pt  É sim. Obrigado por perguntar, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.respond.leave/2   [32 chars]
    en  Off you go. It'll still be here.
    >>  ............................................
    pt  Pode ir. Vai continuar aqui.
    >>  ............................................
  dialogue.conversations.hopes.respond.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.topic.hopes.guarded.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `hopes`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.deflect.personal` — e.g. "That's... a bit close to the bone for someone I barely know."


```text
POOL   dialogue key: dialogue.conversations.topic.hopes.guarded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.hopes.guarded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.hopes.guarded.respond   [33 chars]
    en  Naming it feels like tempting it.
    >>  ............................................
    pt  Dar nome parece tentar a sorte.
    >>  ............................................
```


### Button `respect` — "Then don't name it."

*stance family `restraint` · tone `plain` · answers the beat(s) `deflect.personal.to.hopes.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.guarded.respect` — accepted phrasings: "that is yours to keep"; "keep it to yourself"; "that hope is yours"
  - the message must contain one of: `yours`, `keep`
  - scored words: `yours`(1.5), `keep`(1.2), `hope`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.guarded.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.guarded.respond.respect   [19 chars]
    en  Then don't name it.
    >>  ............................................
    pt  Então não dê nome.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.guarded.respect`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `hopes.guarded.respect`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.guarded.respect
WHO    VILLAGER — what the player reads after pressing "Then don't name it."
       spoken on: conversations.topic.hopes.guarded.respond, button `respect`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.guarded.respect.terminal`: the villager deflects. Subject `hopes.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.guarded.respect/1   [76 chars]
    en  ...Thank you. Ask me again after harvest and we'll see what's left standing.
    >>  ............................................
    pt  ...Obrigado. Me pergunte depois da colheita e a gente vê o que sobrou de pé.
    >>  ............................................
  dialogue.conversations.hopes.guarded.respect/2   [83 chars]
    en  True enough. It'll either happen or it won't, and talking at it does neither, %1$s.
    >>  ............................................
    pt  Bem verdade. Ou acontece ou não, e falar disso não faz nem uma coisa nem outra, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.guarded.respect/3   [67 chars]
    en  Good. Let it stay a hope a while longer. They keep better unspoken.
    >>  ............................................
    pt  Bom. Que continue esperança mais um tempo. Elas se conservam melhor caladas.
    >>  ............................................
```


### Button `ask_safer` — "Something with less riding on it, then."

*stance family `curiosity` · tone `gentle` · answers the beat(s) `deflect.personal.to.hopes.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.guarded.ask_safer` — accepted phrasings: "tell me something lighter"; "something easier then"; "let us keep it light"
  - the message must contain one of: `lighter`, `easier`
  - scored words: `lighter`(1.5), `easier`(1.2), `hope`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.guarded.respond.ask_safer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.guarded.respond.ask_safer   [39 chars]
    en  Something with less riding on it, then.
    >>  ............................................
    pt  Então algo com menos em jogo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `hopes.guarded.ask_safer`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.guarded.ask_safer
WHO    VILLAGER — what the player reads after pressing "Something with less riding on it, then."
       spoken on: conversations.topic.hopes.guarded.respond, button `ask_safer`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.guarded.ask_safer.terminal`: the villager deflects. Subject `hopes.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.guarded.ask_safer/1   [95 chars]
    en  Ask me about the weather. Hopes and weather are cousins — neither improves for being discussed.
    >>  ............................................
    pt  Me pergunte do tempo. Esperança e tempo são primos — nenhum melhora por ser discutido.
    >>  ............................................
  dialogue.conversations.hopes.guarded.ask_safer/2   [80 chars]
    en  The small hopes you can have. Supper. A dry week. Those I'll say out loud, %1$s.
    >>  ............................................
    pt  As esperanças pequenas você pode ter. A janta. Uma semana seca. Essas eu digo, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.guarded.ask_safer/3   [57 chars]
    en  Something with less riding on it, aye. That I can manage.
    >>  ............................................
    pt  Algo com menos em jogo, isso. Isso eu consigo.
    >>  ............................................
```


### Button `press` — "Say it anyway. I'll not jinx you."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `deflect.personal.to.hopes.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.guarded.press` — accepted phrasings: "come on, you can tell me"; "tell me the hope"; "go on, tell me"
  - the message must contain one of: `come`, `tell`
  - scored words: `come`(1.2), `tell`(1.0), `hope`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.guarded.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.guarded.respond.press   [33 chars]
    en  Say it anyway. I'll not jinx you.
    >>  ............................................
    pt  Fala mesmo assim. Não vou dar azar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `hopes.guarded.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5  _(recorded under topic `hopes.guarded.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.guarded.press
WHO    VILLAGER — what the player reads after pressing "Say it anyway. I'll not jinx you."
       spoken on: conversations.topic.hopes.guarded.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.guarded.press.terminal`: the villager resists. Subject `hopes.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.guarded.press/1   [88 chars]
    en  Don't. Say a hope out loud to the wrong person and it turns into a promise you owe them.
    >>  ............................................
    pt  Não. Diga uma esperança em voz alta para a pessoa errada e ela vira uma dívida.
    >>  ............................................
  dialogue.conversations.hopes.guarded.press/2   [68 chars]
    en  I'm superstitious about it and I'll not be argued out of that, %1$s.
    >>  ............................................
    pt  Eu sou supersticioso com isso e não vou ser convencido do contrário, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.guarded.press/3   [95 chars]
    en  No. Naming it is how you jinx it. My grandmother said so and I've seen nothing to disprove her.
    >>  ............................................
    pt  Não. Dar nome é como se estraga. Minha avó dizia isso e eu nunca vi prova em contrário.
    >>  ............................................
```


### Button `leave` — "Fair. I'll not press it."

*stance family `exit` · tone `plain` · answers the beat(s) `deflect.personal.to.hopes.guarded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.guarded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.guarded.respond.leave   [24 chars]
    en  Fair. I'll not press it.
    >>  ............................................
    pt  Justo. Não vou insistir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.guarded.leave
WHO    VILLAGER — what the player reads after pressing "Fair. I'll not press it."
       spoken on: conversations.topic.hopes.guarded.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.guarded.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.guarded.leave/1   [22 chars]
    en  Aye. No hard feelings.
    >>  ............................................
    pt  Tá. Sem ressentimento.
    >>  ............................................
  dialogue.conversations.hopes.guarded.leave/2   [40 chars]
    en  Off you go. We'll get there or we won't.
    >>  ............................................
    pt  Pode ir. A gente chega lá ou não.
    >>  ............................................
  dialogue.conversations.hopes.guarded.leave/3   [20 chars]
    en  Right you are, %1$s.
    >>  ............................................
    pt  Isso mesmo, %1$s.
    >>  ............................................
```

---


## `conversations.topic.hopes.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `hopes`; `conversations.cat.personal` / `hopes`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.hopes.first` — e.g. "Hoping for? A good harvest, a quiet winter, and to see the people I love do well. Simple things, %1$s. The best ones usually are."
- `conversations.hopes.revisit` — e.g. "I've thought more about what I said I was hoping for. There's one wish under all the others I didn't mention."


```text
POOL   dialogue key: dialogue.conversations.topic.hopes.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.hopes.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.hopes.respond   [45 chars]
    en  It's a small thing to want. I want it anyway.
    >>  ............................................
    pt  É uma coisa pequena de se querer. Mas eu quero mesmo assim.
    >>  ............................................
```


### Button `listen` — "I'm listening."

*stance family `restraint` · tone `gentle` · answers the beat(s) `hopes.first.to.hopes`, `hopes.revisit.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.respond.listen` — accepted phrasings: "i am listening"; "i will listen"; "go on, i am listening"
  - the message must contain one of: `listening`, `listen`
  - scored words: `listening`(1.5), `listen`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.respond.listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.respond.listen   [14 chars]
    en  I'm listening.
    >>  ............................................
    pt  Estou ouvindo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the mood is `depressed`
- Does: **hearts +1** — decision id `hopes.respond.listen`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, warmth +1  _(recorded under topic `hopes.respond.listen`)_
- Then opens: `conversations.topic.hopes.followup`
- …where the player's next choices will be: "What would the first step be?" | "I hope for something like that too." | "Is that really what you want?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.hopes.listen.low
WHO    VILLAGER — what the player reads after pressing "I'm listening."
       spoken on: conversations.topic.hopes.respond, button `listen`
       leaves the player on: conversations.topic.hopes.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.listen.low.to.hopes`: the villager accepts. Subject `hopes`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.listen.low/1   [89 chars]
    en  ...You'll wait. That's decent of you. I'm not sure I've got the hoping in me today, mind.
    >>  ............................................
    pt  ...Você vai esperar. É decente da sua parte. Só não sei se tenho esperança em mim hoje.
    >>  ............................................
  dialogue.conversations.hopes.listen.low/2   [80 chars]
    en  It's a thin sort of hope this week, %1$s. But it's there, and you waited for it.
    >>  ............................................
    pt  É uma esperança fina esta semana, %1$s. Mas existe, e você esperou por ela.
    >>  ............................................
  dialogue.conversations.hopes.listen.low/3   [63 chars]
    en  Give me a moment. Hoping is heavier work on days like this one.
    >>  ............................................
    pt  Me dá um instante. Esperar é trabalho mais pesado em dias assim.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the mood is `depressed`  _(chance -2000)_
- Does: **hearts +1** — decision id `hopes.respond.listen`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, warmth +1  _(recorded under topic `hopes.respond.listen`)_
- Then opens: `conversations.topic.hopes.followup`
- …where the player's next choices will be: "What would the first step be?" | "I hope for something like that too." | "Is that really what you want?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.hopes.respond.listen
WHO    VILLAGER — what the player reads after pressing "I'm listening."
       spoken on: conversations.topic.hopes.respond, button `listen`
       leaves the player on: conversations.topic.hopes.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.respond.listen.to.hopes`: the villager accepts. Subject `hopes`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.respond.listen/1   [68 chars]
    en  ...Right. Then here's the rest of it, since you're actually waiting.
    >>  ............................................
    pt  ...Certo. Então aqui está o resto, já que você está esperando de verdade.
    >>  ............................................
  dialogue.conversations.hopes.respond.listen/2   [57 chars]
    en  You'll wait for it. Most people fill the gap with advice.
    >>  ............................................
    pt  Você vai esperar. A maioria preenche o silêncio com conselho.
    >>  ............................................
  dialogue.conversations.hopes.respond.listen/3   [57 chars]
    en  Listening's rarer than helping, and worth more some days.
    >>  ............................................
    pt  Ouvir é mais raro que ajudar, e vale mais em alguns dias.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.respond.listen
    en  You're still here. Right. I'd got as far as assuming you'd wandered off mid-sentence.
    >>  ............................................
    pt  Você ainda está aqui. Certo. Eu já tinha assumido que você tinha saído no meio da frase.
    >>  ............................................
  athletic.dialogue.conversations.hopes.respond.listen
    en  You waited. Most people fill the gap with a plan I didn't ask for.
    >>  ............................................
    pt  Você esperou. A maioria preenche o vazio com um plano que eu não pedi.
    >>  ............................................
  confident.dialogue.conversations.hopes.respond.listen
    en  You waited me out. That takes more nerve than interrupting does.
    >>  ............................................
    pt  Você me deixou chegar lá. Isso exige mais coragem do que interromper.
    >>  ............................................
  crabby.dialogue.conversations.hopes.respond.listen
    en  You waited. Nobody waits. It's put me completely off my stride, which I resent.
    >>  ............................................
    pt  Você esperou. Ninguém espera. Isso me desconcertou completamente, e eu detesto.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.respond.listen
    en  You waited. Do you know how rare that is? Right, sit down, this is a long one.
    >>  ............................................
    pt  Você esperou. Sabe como isso é raro? Certo, senta, essa é longa.
    >>  ............................................
  flirty.dialogue.conversations.hopes.respond.listen
    en  You waited. That's dangerously attractive, and I'd like it on the record.
    >>  ............................................
    pt  Você esperou. Isso é perigosamente atraente, e eu quero isso registrado.
    >>  ............................................
  friendly.dialogue.conversations.hopes.respond.listen
    en  You waited for me to find the words. That's a real kindness and I don't think you know it.
    >>  ............................................
    pt  Você esperou eu achar as palavras. Isso é uma gentileza real e acho que você nem sabe.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.respond.listen
    en  You waited. I'd assumed you'd gone. That's what I assume, mostly, and I'm usually right.
    >>  ............................................
    pt  Você esperou. Eu tinha assumido que você tinha ido. É o que eu assumo, e costumo acertar.
    >>  ............................................
  greedy.dialogue.conversations.hopes.respond.listen
    en  You waited. Time's the one thing nobody spends freely, and you just spent some on me.
    >>  ............................................
    pt  Você esperou. Tempo é a única coisa que ninguém gasta à toa, e você acabou de gastar comigo.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.respond.listen
    en  You waited instead of filling the silence with advice. That's the first restful thing all week.
    >>  ............................................
    pt  Você esperou em vez de encher o silêncio de conselho. É a primeira coisa repousante da semana.
    >>  ............................................
  introverted.dialogue.conversations.hopes.respond.listen
    en  You waited. Most people take a pause as an invitation. You took it as a pause.
    >>  ............................................
    pt  Você esperou. A maioria toma uma pausa como convite. Você tomou como pausa.
    >>  ............................................
  lazy.dialogue.conversations.hopes.respond.listen
    en  You waited. Nice pace, this. Nobody hurrying anybody. I could do a whole conversation like this.
    >>  ............................................
    pt  Você esperou. Bom ritmo, esse. Ninguém apressando ninguém. Eu faria uma conversa inteira assim.
    >>  ............................................
  odd.dialogue.conversations.hopes.respond.listen
    en  You waited. People don't wait. They fill. You just... didn't. I've been thinking about it.
    >>  ............................................
    pt  Você esperou. As pessoas não esperam. Elas preenchem. Você simplesmente... não. Fiquei pensando nisso.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.respond.listen
    en  You waited. Silence is where most true things get said, and you left room for it.
    >>  ............................................
    pt  Você esperou. É no silêncio que a maioria das coisas verdadeiras é dita, e você deixou espaço.
    >>  ............................................
  peppy.dialogue.conversations.hopes.respond.listen
    en  You waited! Nobody waits! Okay okay okay, here it is, I've been bursting.
    >>  ............................................
    pt  Você esperou! Ninguém espera! Tá, tá, tá, aqui vai, eu estava explodindo.
    >>  ............................................
  playful.dialogue.conversations.hopes.respond.listen
    en  You waited. Very sneaky. Now I've no excuse not to say the thing.
    >>  ............................................
    pt  Você esperou. Muito manhoso. Agora eu não tenho desculpa para não dizer a coisa.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.respond.listen
    en  You waited. Nice. Most people can't sit in a gap for two seconds together.
    >>  ............................................
    pt  Você esperou. Legal. A maioria não aguenta dois segundos num vazio.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.respond.listen
    en  You waited for me. I could feel you waiting and it was the opposite of pressure, somehow.
    >>  ............................................
    pt  Você esperou por mim. Eu senti você esperando e foi o oposto de pressão, de algum jeito.
    >>  ............................................
  shy.dialogue.conversations.hopes.respond.listen
    en  You waited. Nobody waits long enough. ...Alright. Here it is.
    >>  ............................................
    pt  Você esperou. Ninguém espera o suficiente. ...Tudo bem. Aqui vai.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.respond.listen
    en  You waited! Good. It's a better hope for having been said properly.
    >>  ............................................
    pt  Você esperou! Bom. É uma esperança melhor por ter sido dita direito.
    >>  ............................................
  witty.dialogue.conversations.hopes.respond.listen
    en  You waited. A rare and underrated skill. Right — the hope, before I lose my nerve and make a joke.
    >>  ............................................
    pt  Você esperou. Habilidade rara e subestimada. Certo — a esperança, antes que eu faça uma piada.
    >>  ............................................
```

</details>


### Button `encourage` — "I hope you get it."

*stance family `encouragement` · tone `plain` · answers the beat(s) `hopes.first.to.hopes`, `hopes.revisit.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.respond.encourage` — accepted phrasings: "i hope you get it"; "you deserve it"; "i hope it comes"
  - the message must contain one of: `hope`, `deserve`, `get`
  - scored words: `hope`(1.2), `get`(0.6), `deserve`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.respond.encourage   [18 chars]
    en  I hope you get it.
    >>  ............................................
    pt  Espero que você consiga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `hopes.respond.encourage`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4  _(recorded under topic `hopes.respond.encourage`)_
- Then opens: `conversations.topic.hopes.followup`
- …where the player's next choices will be: "What would the first step be?" | "I hope for something like that too." | "Is that really what you want?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.hopes.respond.encourage
WHO    VILLAGER — what the player reads after pressing "I hope you get it."
       spoken on: conversations.topic.hopes.respond, button `encourage`
       leaves the player on: conversations.topic.hopes.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.respond.encourage.to.hopes`: the villager accepts. Subject `hopes`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.respond.encourage/1   [47 chars]
    en  So do I. It helps, hearing someone else say it.
    >>  ............................................
    pt  Eu também. Ajuda ouvir outra pessoa dizer.
    >>  ............................................
  dialogue.conversations.hopes.respond.encourage/2   [54 chars]
    en  Thank you. It's a small hope. Small ones still bruise.
    >>  ............................................
    pt  Obrigado. É uma esperança pequena. As pequenas também machucam.
    >>  ............................................
  dialogue.conversations.hopes.respond.encourage/3   [45 chars]
    en  From your mouth to whoever's listening, %1$s.
    >>  ............................................
    pt  Da sua boca a quem estiver ouvindo, %1$s.
    >>  ............................................
```


### Button `mock` — "That's a bit much to hope for."

*stance family `dismissal` · tone `hostile` · answers the beat(s) `hopes.first.to.hopes`, `hopes.revisit.to.hopes`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.respond.mock` — accepted phrasings: "that is a bit much to hope for"; "rather ambitious"; "keep dreaming"
  - the message must contain one of: `much`, `ambitious`, `dreaming`
  - scored words: `much`(1.5), `ambitious`(1.5), `dreaming`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.respond.mock
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.respond.mock   [30 chars]
    en  That's a bit much to hope for.
    >>  ............................................
    pt  É esperar demais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `hopes.respond.mock`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -5, tension +6  _(recorded under topic `hopes.respond.mock`)_
- Does: session `turn`
- Then opens: `conversations.topic.hopes.belittled.followup`
- …where the player's next choices will be: "Don't. That was small of me." | "It sounded bigger than you meant it, I think." | "I'll say no more about it."

```text
POOL   dialogue key: dialogue.conversations.hopes.respond.mock
WHO    VILLAGER — what the player reads after pressing "That's a bit much to hope for."
       spoken on: conversations.topic.hopes.respond, button `mock`
       leaves the player on: conversations.topic.hopes.belittled.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.rebuked`: the villager refuses. Subject `hopes.wish`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   this is the line that establishes `player:belittled_the_hope` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.hopes.respond.mock/1   [56 chars]
    en  ...It's a small thing, %1$s. That's what makes it sting.
    >>  ............................................
    pt  ...É uma coisa pequena, %1$s. É isso que faz doer.
    >>  ............................................
  dialogue.conversations.hopes.respond.mock/2   [50 chars]
    en  Too much. Right. I'll keep the next one to myself.
    >>  ............................................
    pt  Demais. Certo. Vou guardar a próxima para mim.
    >>  ............................................
  dialogue.conversations.hopes.respond.mock/3   [61 chars]
    en  Everyone's hoping for something. Mine's no bigger than yours.
    >>  ............................................
    pt  Todo mundo espera algo. A minha não é maior que a sua.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.respond.mock/1
    en  ...It's small. Small things are the ones you can't defend, %1$s.
    >>  ............................................
    pt  ...É pequena. Coisas pequenas são as que você não consegue defender, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.hopes.respond.mock/2
    en  I knew how it would sound. I said it anyway, and now I wish I hadn't.
    >>  ............................................
    pt  Eu sabia como ia soar. Disse mesmo assim, e agora queria não ter dito.
    >>  ............................................
  anxious.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. Yes. Silly. I'd got there before you.
    >>  ............................................
    pt  ...Certo. Sim. Boba. Eu cheguei lá antes de você.
    >>  ............................................
  athletic.dialogue.conversations.hopes.respond.mock/1
    en  It's a small thing. Small things last longer, in my experience.
    >>  ............................................
    pt  É uma coisa pequena. Coisas pequenas duram mais, na minha experiência.
    >>  ............................................
  athletic.dialogue.conversations.hopes.respond.mock/2
    en  ...Aye, it's not much. It's been not much for eleven years and I still want it.
    >>  ............................................
    pt  ...É, não é muito. Não é muito faz onze anos e eu ainda quero.
    >>  ............................................
  athletic.dialogue.conversations.hopes.respond.mock/3
    en  Right. It'll keep being small and I'll keep wanting it.
    >>  ............................................
    pt  Certo. Vai continuar pequena e eu vou continuar querendo.
    >>  ............................................
  confident.dialogue.conversations.hopes.respond.mock/1
    en  It's a small thing. That's what makes it sting.
    >>  ............................................
    pt  É uma coisa pequena. É isso que faz doer.
    >>  ............................................
  confident.dialogue.conversations.hopes.respond.mock/2
    en  Right. I'll not tell you the next small thing I want.
    >>  ............................................
    pt  Certo. Não te conto a próxima coisinha que eu queira.
    >>  ............................................
  confident.dialogue.conversations.hopes.respond.mock/3
    en  ...I know it's small. I chose a small one on purpose.
    >>  ............................................
    pt  ...Eu sei que é pequena. Escolhi uma pequena de propósito.
    >>  ............................................
  crabby.dialogue.conversations.hopes.respond.mock/1
    en  It's a small thing. That's what makes it sting.
    >>  ............................................
    pt  É uma coisa pequena. É isso que faz doer.
    >>  ............................................
  crabby.dialogue.conversations.hopes.respond.mock/2
    en  Right. I'll not tell you the next small thing I want.
    >>  ............................................
    pt  Certo. Não te conto a próxima coisinha que eu queira.
    >>  ............................................
  crabby.dialogue.conversations.hopes.respond.mock/3
    en  ...I know it's small. I chose a small one on purpose.
    >>  ............................................
    pt  ...Eu sei que é pequena. Escolhi uma pequena de propósito.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.respond.mock/1
    en  It's small, %1$s. That's why I told you and not the square.
    >>  ............................................
    pt  É pequena, %1$s. Por isso eu contei a você e não à praça.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.respond.mock/2
    en  I'd hoped you'd be the one who didn't laugh at that one.
    >>  ............................................
    pt  Eu esperava que você fosse quem não risse dessa.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. I'll keep the small ones to myself from now on.
    >>  ............................................
    pt  ...Certo. De agora em diante eu guardo as pequenas pra mim.
    >>  ............................................
  flirty.dialogue.conversations.hopes.respond.mock/1
    en  It's small, %1$s. That's why I told you and not the square.
    >>  ............................................
    pt  É pequena, %1$s. Por isso eu contei a você e não à praça.
    >>  ............................................
  flirty.dialogue.conversations.hopes.respond.mock/2
    en  I'd hoped you'd be the one who didn't laugh at that one.
    >>  ............................................
    pt  Eu esperava que você fosse quem não risse dessa.
    >>  ............................................
  flirty.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. I'll keep the small ones to myself from now on.
    >>  ............................................
    pt  ...Certo. De agora em diante eu guardo as pequenas pra mim.
    >>  ............................................
  friendly.dialogue.conversations.hopes.respond.mock/1
    en  It's small, %1$s. That's why I told you and not the square.
    >>  ............................................
    pt  É pequena, %1$s. Por isso eu contei a você e não à praça.
    >>  ............................................
  friendly.dialogue.conversations.hopes.respond.mock/2
    en  I'd hoped you'd be the one who didn't laugh at that one.
    >>  ............................................
    pt  Eu esperava que você fosse quem não risse dessa.
    >>  ............................................
  friendly.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. I'll keep the small ones to myself from now on.
    >>  ............................................
    pt  ...Certo. De agora em diante eu guardo as pequenas pra mim.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.respond.mock/1
    en  ...It's small. Small things are the ones you can't defend, %1$s.
    >>  ............................................
    pt  ...É pequena. Coisas pequenas são as que você não consegue defender, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.respond.mock/2
    en  I knew how it would sound. I said it anyway, and now I wish I hadn't.
    >>  ............................................
    pt  Eu sabia como ia soar. Disse mesmo assim, e agora queria não ter dito.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. Yes. Silly. I'd got there before you.
    >>  ............................................
    pt  ...Certo. Sim. Boba. Eu cheguei lá antes de você.
    >>  ............................................
  greedy.dialogue.conversations.hopes.respond.mock/1
    en  It's a small thing. That's what makes it sting.
    >>  ............................................
    pt  É uma coisa pequena. É isso que faz doer.
    >>  ............................................
  greedy.dialogue.conversations.hopes.respond.mock/2
    en  Right. I'll not tell you the next small thing I want.
    >>  ............................................
    pt  Certo. Não te conto a próxima coisinha que eu queira.
    >>  ............................................
  greedy.dialogue.conversations.hopes.respond.mock/3
    en  ...I know it's small. I chose a small one on purpose.
    >>  ............................................
    pt  ...Eu sei que é pequena. Escolhi uma pequena de propósito.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.respond.mock/1
    en  It's a small thing. That's what makes it sting.
    >>  ............................................
    pt  É uma coisa pequena. É isso que faz doer.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.respond.mock/2
    en  Right. I'll not tell you the next small thing I want.
    >>  ............................................
    pt  Certo. Não te conto a próxima coisinha que eu queira.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.respond.mock/3
    en  ...I know it's small. I chose a small one on purpose.
    >>  ............................................
    pt  ...Eu sei que é pequena. Escolhi uma pequena de propósito.
    >>  ............................................
  introverted.dialogue.conversations.hopes.respond.mock/1
    en  ...It's a small thing. That's what makes it sting.
    >>  ............................................
    pt  ...É uma coisa pequena. É isso que faz doer.
    >>  ............................................
  introverted.dialogue.conversations.hopes.respond.mock/2
    en  I know. I'd not have said it if I thought it was large.
    >>  ............................................
    pt  Eu sei. Não teria dito se achasse que era grande.
    >>  ............................................
  introverted.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. Forget it.
    >>  ............................................
    pt  ...Certo. Esqueça.
    >>  ............................................
  lazy.dialogue.conversations.hopes.respond.mock/1
    en  It's a small thing. Small things last longer, in my experience.
    >>  ............................................
    pt  É uma coisa pequena. Coisas pequenas duram mais, na minha experiência.
    >>  ............................................
  lazy.dialogue.conversations.hopes.respond.mock/2
    en  ...Aye, it's not much. It's been not much for eleven years and I still want it.
    >>  ............................................
    pt  ...É, não é muito. Não é muito faz onze anos e eu ainda quero.
    >>  ............................................
  lazy.dialogue.conversations.hopes.respond.mock/3
    en  Right. It'll keep being small and I'll keep wanting it.
    >>  ............................................
    pt  Certo. Vai continuar pequena e eu vou continuar querendo.
    >>  ............................................
  odd.dialogue.conversations.hopes.respond.mock/1
    en  ...It's a small thing. That's what makes it sting.
    >>  ............................................
    pt  ...É uma coisa pequena. É isso que faz doer.
    >>  ............................................
  odd.dialogue.conversations.hopes.respond.mock/2
    en  I know. I'd not have said it if I thought it was large.
    >>  ............................................
    pt  Eu sei. Não teria dito se achasse que era grande.
    >>  ............................................
  odd.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. Forget it.
    >>  ............................................
    pt  ...Certo. Esqueça.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.respond.mock/1
    en  It's a small thing. Small things last longer, in my experience.
    >>  ............................................
    pt  É uma coisa pequena. Coisas pequenas duram mais, na minha experiência.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.respond.mock/2
    en  ...Aye, it's not much. It's been not much for eleven years and I still want it.
    >>  ............................................
    pt  ...É, não é muito. Não é muito faz onze anos e eu ainda quero.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.respond.mock/3
    en  Right. It'll keep being small and I'll keep wanting it.
    >>  ............................................
    pt  Certo. Vai continuar pequena e eu vou continuar querendo.
    >>  ............................................
  peppy.dialogue.conversations.hopes.respond.mock/1
    en  ...Ha. Yes. Tiny. That's rather the point of it, %1$s.
    >>  ............................................
    pt  ...Ha. Sim. Minúscula. É meio que a questão, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.hopes.respond.mock/2
    en  Right! Small and ridiculous. Both true, and I still want it.
    >>  ............................................
    pt  Certo! Pequena e ridícula. As duas coisas verdade, e eu ainda quero.
    >>  ............................................
  peppy.dialogue.conversations.hopes.respond.mock/3
    en  ...Laugh away. I'll be over here quietly wanting it.
    >>  ............................................
    pt  ...Ria à vontade. Eu vou ficar aqui querendo em silêncio.
    >>  ............................................
  playful.dialogue.conversations.hopes.respond.mock/1
    en  ...Ha. Yes. Tiny. That's rather the point of it, %1$s.
    >>  ............................................
    pt  ...Ha. Sim. Minúscula. É meio que a questão, %1$s.
    >>  ............................................
  playful.dialogue.conversations.hopes.respond.mock/2
    en  Right! Small and ridiculous. Both true, and I still want it.
    >>  ............................................
    pt  Certo! Pequena e ridícula. As duas coisas verdade, e eu ainda quero.
    >>  ............................................
  playful.dialogue.conversations.hopes.respond.mock/3
    en  ...Laugh away. I'll be over here quietly wanting it.
    >>  ............................................
    pt  ...Ria à vontade. Eu vou ficar aqui querendo em silêncio.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.respond.mock/1
    en  It's a small thing. Small things last longer, in my experience.
    >>  ............................................
    pt  É uma coisa pequena. Coisas pequenas duram mais, na minha experiência.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.respond.mock/2
    en  ...Aye, it's not much. It's been not much for eleven years and I still want it.
    >>  ............................................
    pt  ...É, não é muito. Não é muito faz onze anos e eu ainda quero.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.respond.mock/3
    en  Right. It'll keep being small and I'll keep wanting it.
    >>  ............................................
    pt  Certo. Vai continuar pequena e eu vou continuar querendo.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.respond.mock/1
    en  ...It's small. Small things are the ones you can't defend, %1$s.
    >>  ............................................
    pt  ...É pequena. Coisas pequenas são as que você não consegue defender, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.respond.mock/2
    en  I knew how it would sound. I said it anyway, and now I wish I hadn't.
    >>  ............................................
    pt  Eu sabia como ia soar. Disse mesmo assim, e agora queria não ter dito.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. Yes. Silly. I'd got there before you.
    >>  ............................................
    pt  ...Certo. Sim. Boba. Eu cheguei lá antes de você.
    >>  ............................................
  shy.dialogue.conversations.hopes.respond.mock/1
    en  ...It's a small thing. That's what makes it sting.
    >>  ............................................
    pt  ...É uma coisa pequena. É isso que faz doer.
    >>  ............................................
  shy.dialogue.conversations.hopes.respond.mock/2
    en  I know. I'd not have said it if I thought it was large.
    >>  ............................................
    pt  Eu sei. Não teria dito se achasse que era grande.
    >>  ............................................
  shy.dialogue.conversations.hopes.respond.mock/3
    en  ...Right. Forget it.
    >>  ............................................
    pt  ...Certo. Esqueça.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.respond.mock/1
    en  ...Ha. Yes. Tiny. That's rather the point of it, %1$s.
    >>  ............................................
    pt  ...Ha. Sim. Minúscula. É meio que a questão, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.respond.mock/2
    en  Right! Small and ridiculous. Both true, and I still want it.
    >>  ............................................
    pt  Certo! Pequena e ridícula. As duas coisas verdade, e eu ainda quero.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.respond.mock/3
    en  ...Laugh away. I'll be over here quietly wanting it.
    >>  ............................................
    pt  ...Ria à vontade. Eu vou ficar aqui querendo em silêncio.
    >>  ............................................
  witty.dialogue.conversations.hopes.respond.mock/1
    en  ...Ha. Yes. Tiny. That's rather the point of it, %1$s.
    >>  ............................................
    pt  ...Ha. Sim. Minúscula. É meio que a questão, %1$s.
    >>  ............................................
  witty.dialogue.conversations.hopes.respond.mock/2
    en  Right! Small and ridiculous. Both true, and I still want it.
    >>  ............................................
    pt  Certo! Pequena e ridícula. As duas coisas verdade, e eu ainda quero.
    >>  ............................................
  witty.dialogue.conversations.hopes.respond.mock/3
    en  ...Laugh away. I'll be over here quietly wanting it.
    >>  ............................................
    pt  ...Ria à vontade. Eu vou ficar aqui querendo em silêncio.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.first.to.hopes`, `hopes.revisit.to.hopes` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.hopes.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.respond.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.hopes.followup / leave
```

> Written out in full under **`conversations.topic.hopes.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.hopes.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `hopes`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.hopes.toddler` — e.g. "Snacks tomorrow! And the day after!"


```text
POOL   dialogue key: dialogue.conversations.topic.hopes.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.hopes.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.hopes.toddler.respond   [27 chars]
    en  That's what I'm hoping for.
    >>  ............................................
    pt  É isso que eu estou esperando.
    >>  ............................................
```


### Button `delight` — "I hope so too."

*stance family `encouragement` · tone `playful` · answers the beat(s) `hopes.toddler.to.hopes.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.toddler.delight` — accepted phrasings: "i hope so too"; "i hope that too"; "so do i, i hope so"
  - the message must contain one of: `hope`
  - scored words: `hope`(1.5), `too`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.toddler.respond.delight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.toddler.respond.delight   [14 chars]
    en  I hope so too.
    >>  ............................................
    pt  Eu também espero.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.toddler.delight`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `hopes.toddler.delight`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.toddler.delight
WHO    VILLAGER — what the player reads after pressing "I hope so too."
       spoken on: conversations.topic.hopes.toddler.respond, button `delight`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.toddler.delight.terminal`: the villager celebrates. Subject `hopes.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.toddler.delight/1   [51 chars]
    en  You hope too! Then it's TWO hopes. Two is stronger.
    >>  ............................................
    pt  Você também espera! Então são DUAS esperanças. Duas é mais forte.
    >>  ............................................
  dialogue.conversations.hopes.toddler.delight/2   [57 chars]
    en  Good. Everyone should hope for it. Then it has to happen.
    >>  ............................................
    pt  Bom. Todo mundo devia esperar isso. Aí tem que acontecer.
    >>  ............................................
  dialogue.conversations.hopes.toddler.delight/3   [41 chars]
    en  Yes! Hope with me, %1$s. Hard as you can.
    >>  ............................................
    pt  Isso! Espera comigo, %1$s. Com toda a força.
    >>  ............................................
```


### Button `ask` — "What else are you hoping for?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `hopes.toddler.to.hopes.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.toddler.ask` — accepted phrasings: "what else are you hoping for"; "anything else you hope for"; "is there anything else"
  - the message must contain one of: `else`, `hoping`
  - scored words: `else`(1.5), `hoping`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.toddler.respond.ask   [29 chars]
    en  What else are you hoping for?
    >>  ............................................
    pt  O que mais você espera?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `hopes.toddler.ask`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.toddler.ask
WHO    VILLAGER — what the player reads after pressing "What else are you hoping for?"
       spoken on: conversations.topic.hopes.toddler.respond, button `ask`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.toddler.ask.terminal`: the villager asks. Subject `hopes.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.toddler.ask/1   [43 chars]
    en  Um. A hat. And for tomorrow to come faster.
    >>  ............................................
    pt  Hã. Um chapéu. E que amanhã venha mais rápido.
    >>  ............................................
  dialogue.conversations.hopes.toddler.ask/2   [59 chars]
    en  That the loud thing outside stops being loud. And a pocket.
    >>  ............................................
    pt  Que a coisa barulhenta lá fora pare de ser barulhenta. E um bolso.
    >>  ............................................
  dialogue.conversations.hopes.toddler.ask/3   [53 chars]
    en  Lots of stuff. I keep them in my head. There's LOADS.
    >>  ............................................
    pt  Um monte de coisa. Eu guardo na cabeça. Tem MUITAS.
    >>  ............................................
```


### Button `leave` — "Off you go and hope, then."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.toddler.to.hopes.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.toddler.respond.leave   [26 chars]
    en  Off you go and hope, then.
    >>  ............................................
    pt  Vai esperar, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go and hope, then."
       spoken on: conversations.topic.hopes.toddler.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.toddler.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.toddler.leave/1   [45 chars]
    en  Bye! I'm going to go and hope somewhere else.
    >>  ............................................
    pt  Tchau! Vou esperar em outro lugar.
    >>  ............................................
  dialogue.conversations.hopes.toddler.leave/2   [15 chars]
    en  Okay bye, %1$s!
    >>  ............................................
    pt  Tá, tchau, %1$s!
    >>  ............................................
  dialogue.conversations.hopes.toddler.leave/3   [19 chars]
    en  Bye bye. Hope hard!
    >>  ............................................
    pt  Tchau tchau. Espera com força!
    >>  ............................................
```

---


## `conversations.topic.hopes.young.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `hopes`; `conversations.cat.personal` / `hopes`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.hopes.child` — e.g. "I hope I get my own fishing rod this year. A real one."
- `conversations.hopes.teen` — e.g. "To get out of chores... and maybe see the next village over someday."


```text
POOL   dialogue key: dialogue.conversations.topic.hopes.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.hopes.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.hopes.young.respond   [34 chars]
    en  That's the one I keep checking on.
    >>  ............................................
    pt  É essa que eu fico conferindo.
    >>  ............................................
```


### Button `interested` — "Tell me the whole list, then."

*stance family `curiosity` · tone `plain` · answers the beat(s) `hopes.child.to.hopes.young`, `hopes.teen.to.hopes.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.young.interested` — accepted phrasings: "tell me properly"; "tell me the whole hope"; "go on, properly"
  - the message must contain one of: `properly`, `whole`
  - scored words: `properly`(1.5), `whole`(1.2), `hope`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.young.respond.interested
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.young.respond.interested   [29 chars]
    en  Tell me the whole list, then.
    >>  ............................................
    pt  Então me conta a lista inteira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.young.interested`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `hopes.young.interested`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.young.interested
WHO    VILLAGER — what the player reads after pressing "Tell me the whole list, then."
       spoken on: conversations.topic.hopes.young.respond, button `interested`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.young.interested.terminal`: the villager accepts. Subject `hopes.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.young.interested/1   [74 chars]
    en  Really? Okay — it's only a little one but it's the one I keep checking on.
    >>  ............................................
    pt  Sério? Tá — é pequena mas é a que eu fico conferindo.
    >>  ............................................
  dialogue.conversations.hopes.young.interested/2   [78 chars]
    en  You want to hear the whole list? There's a list. It's longer than you'd think.
    >>  ............................................
    pt  Você quer ouvir a lista toda? Tem uma lista. É maior do que parece.
    >>  ............................................
  dialogue.conversations.hopes.young.interested/3   [88 chars]
    en  Right, sit down, because some of them are years away and I've thought about all of them.
    >>  ............................................
    pt  Certo, senta, porque algumas são daqui a anos e eu já pensei em todas.
    >>  ............................................
```


### Button `encourage` — "I hope you get it too."

*stance family `encouragement` · tone `plain` · answers the beat(s) `hopes.child.to.hopes.young`, `hopes.teen.to.hopes.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.young.encourage` — accepted phrasings: "that is worth having"; "that hope is worth having"; "worth holding onto"
  - the message must contain one of: `worth`, `having`
  - scored words: `worth`(1.5), `having`(1.0), `hope`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.young.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.young.respond.encourage   [22 chars]
    en  I hope you get it too.
    >>  ............................................
    pt  Eu também espero que você consiga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `hopes.young.encourage`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +2  _(recorded under topic `hopes.young.encourage`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.young.encourage
WHO    VILLAGER — what the player reads after pressing "I hope you get it too."
       spoken on: conversations.topic.hopes.young.respond, button `encourage`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.young.encourage.terminal`: the villager accepts. Subject `hopes.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.young.encourage/1   [88 chars]
    en  ...You hope so too? Then that's two of us and it's twice as likely. That's how it works.
    >>  ............................................
    pt  ...Você também espera? Então somos dois e é duas vezes mais provável. Funciona assim.
    >>  ............................................
  dialogue.conversations.hopes.young.encourage/2   [66 chars]
    en  It IS going to happen. Probably. Thanks for saying it might, %1$s.
    >>  ............................................
    pt  VAI acontecer. Provavelmente. Obrigado por dizer que pode, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.young.encourage/3   [90 chars]
    en  Nobody usually hopes along with me. They just say 'that'd be nice' and change the subject.
    >>  ............................................
    pt  Normalmente ninguém espera junto comigo. Só falam 'seria legal' e mudam de assunto.
    >>  ............................................
```


### Button `dismiss` — "That's a bit silly, isn't it."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `hopes.child.to.hopes.young`, `hopes.teen.to.hopes.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `hopes.young.dismiss` — accepted phrasings: "you will change your mind"; "you will grow out of it"; "you will think differently"
  - the message must contain one of: `change`, `mind`, `grow`
  - scored words: `change`(1.5), `mind`(1.2), `grow`(1.2), `hope`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.young.respond.dismiss   [29 chars]
    en  That's a bit silly, isn't it.
    >>  ............................................
    pt  Isso é meio bobo, né.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `hopes.young.dismiss`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `hopes.young.dismiss`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.young.dismiss
WHO    VILLAGER — what the player reads after pressing "That's a bit silly, isn't it."
       spoken on: conversations.topic.hopes.young.respond, button `dismiss`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.young.dismiss.terminal`: the villager dismisss. Subject `hopes.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.young.dismiss/1   [74 chars]
    en  ...It's not silly. It's just not YOURS, so it looks silly from over there.
    >>  ............................................
    pt  ...Não é bobo. É só que não é SEU, então parece bobo daí de onde você está.
    >>  ............................................
  dialogue.conversations.hopes.young.dismiss/2   [50 chars]
    en  You'd hope for it too if it was yours to hope for.
    >>  ............................................
    pt  Você também esperaria se fosse a sua para esperar.
    >>  ............................................
  dialogue.conversations.hopes.young.dismiss/3   [29 chars]
    en  Fine. I'll hope quietly then.
    >>  ............................................
    pt  Tá bom. Então eu espero caladinho.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.hopes.young.dismiss/1
    en  ...It's not silly. It's just small, and small is easy to laugh at, %1$s.
    >>  ............................................
    pt  ...Não é bobo. É só pequeno, e pequeno é fácil de rir, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.hopes.young.dismiss/2
    en  I knew you'd say that. I told you anyway.
    >>  ............................................
    pt  Eu sabia que você ia dizer isso. Contei mesmo assim.
    >>  ............................................
  anxious.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. I'll keep the rest of them in my head.
    >>  ............................................
    pt  ...Certo. Vou guardar o resto na cabeça.
    >>  ............................................
  athletic.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly. It's just early. Most things look silly early.
    >>  ............................................
    pt  Não é bobo. É só cedo. Quase tudo parece bobo cedo.
    >>  ............................................
  athletic.dialogue.conversations.hopes.young.dismiss/2
    en  ...Aye, well. Ask me again in ten years and see how silly it looks then.
    >>  ............................................
    pt  ...É, bom. Me pergunte em dez anos e veja como fica bobo.
    >>  ............................................
  athletic.dialogue.conversations.hopes.young.dismiss/3
    en  Right you are. It'll still be there tomorrow.
    >>  ............................................
    pt  Você tem razão. Vai continuar lá amanhã.
    >>  ............................................
  confident.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly. It's just not YOURS, so it looks silly from over there.
    >>  ............................................
    pt  Não é bobo. Só não é SEU, então parece bobo daí de onde você está.
    >>  ............................................
  confident.dialogue.conversations.hopes.young.dismiss/2
    en  Right. I'll want it without telling you, then.
    >>  ............................................
    pt  Certo. Então eu vou querer sem te contar.
    >>  ............................................
  confident.dialogue.conversations.hopes.young.dismiss/3
    en  ...You asked me. I answered. That's the whole of my crime.
    >>  ............................................
    pt  ...Você perguntou. Eu respondi. É todo o meu crime.
    >>  ............................................
  crabby.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly. It's just not YOURS, so it looks silly from over there.
    >>  ............................................
    pt  Não é bobo. Só não é SEU, então parece bobo daí de onde você está.
    >>  ............................................
  crabby.dialogue.conversations.hopes.young.dismiss/2
    en  Right. I'll want it without telling you, then.
    >>  ............................................
    pt  Certo. Então eu vou querer sem te contar.
    >>  ............................................
  crabby.dialogue.conversations.hopes.young.dismiss/3
    en  ...You asked me. I answered. That's the whole of my crime.
    >>  ............................................
    pt  ...Você perguntou. Eu respondi. É todo o meu crime.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly, %1$s. It's mine, and I told you first.
    >>  ............................................
    pt  Não é bobo, %1$s. É meu, e eu contei a você primeiro.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.young.dismiss/2
    en  I thought you of all people would like it.
    >>  ............................................
    pt  Achei que você, logo você, fosse gostar.
    >>  ............................................
  extroverted.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. I'll tell somebody who'd want to hear it.
    >>  ............................................
    pt  ...Certo. Vou contar pra alguém que queira ouvir.
    >>  ............................................
  flirty.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly, %1$s. It's mine, and I told you first.
    >>  ............................................
    pt  Não é bobo, %1$s. É meu, e eu contei a você primeiro.
    >>  ............................................
  flirty.dialogue.conversations.hopes.young.dismiss/2
    en  I thought you of all people would like it.
    >>  ............................................
    pt  Achei que você, logo você, fosse gostar.
    >>  ............................................
  flirty.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. I'll tell somebody who'd want to hear it.
    >>  ............................................
    pt  ...Certo. Vou contar pra alguém que queira ouvir.
    >>  ............................................
  friendly.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly, %1$s. It's mine, and I told you first.
    >>  ............................................
    pt  Não é bobo, %1$s. É meu, e eu contei a você primeiro.
    >>  ............................................
  friendly.dialogue.conversations.hopes.young.dismiss/2
    en  I thought you of all people would like it.
    >>  ............................................
    pt  Achei que você, logo você, fosse gostar.
    >>  ............................................
  friendly.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. I'll tell somebody who'd want to hear it.
    >>  ............................................
    pt  ...Certo. Vou contar pra alguém que queira ouvir.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.young.dismiss/1
    en  ...It's not silly. It's just small, and small is easy to laugh at, %1$s.
    >>  ............................................
    pt  ...Não é bobo. É só pequeno, e pequeno é fácil de rir, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.young.dismiss/2
    en  I knew you'd say that. I told you anyway.
    >>  ............................................
    pt  Eu sabia que você ia dizer isso. Contei mesmo assim.
    >>  ............................................
  gloomy.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. I'll keep the rest of them in my head.
    >>  ............................................
    pt  ...Certo. Vou guardar o resto na cabeça.
    >>  ............................................
  greedy.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly. It's just not YOURS, so it looks silly from over there.
    >>  ............................................
    pt  Não é bobo. Só não é SEU, então parece bobo daí de onde você está.
    >>  ............................................
  greedy.dialogue.conversations.hopes.young.dismiss/2
    en  Right. I'll want it without telling you, then.
    >>  ............................................
    pt  Certo. Então eu vou querer sem te contar.
    >>  ............................................
  greedy.dialogue.conversations.hopes.young.dismiss/3
    en  ...You asked me. I answered. That's the whole of my crime.
    >>  ............................................
    pt  ...Você perguntou. Eu respondi. É todo o meu crime.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly. It's just not YOURS, so it looks silly from over there.
    >>  ............................................
    pt  Não é bobo. Só não é SEU, então parece bobo daí de onde você está.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.young.dismiss/2
    en  Right. I'll want it without telling you, then.
    >>  ............................................
    pt  Certo. Então eu vou querer sem te contar.
    >>  ............................................
  grumpy.dialogue.conversations.hopes.young.dismiss/3
    en  ...You asked me. I answered. That's the whole of my crime.
    >>  ............................................
    pt  ...Você perguntou. Eu respondi. É todo o meu crime.
    >>  ............................................
  introverted.dialogue.conversations.hopes.young.dismiss/1
    en  ...It's not silly.
    >>  ............................................
    pt  ...Não é bobo.
    >>  ............................................
  introverted.dialogue.conversations.hopes.young.dismiss/2
    en  It's mine. That's all.
    >>  ............................................
    pt  É meu. Só isso.
    >>  ............................................
  introverted.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. Forget it, then.
    >>  ............................................
    pt  ...Certo. Esqueça, então.
    >>  ............................................
  lazy.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly. It's just early. Most things look silly early.
    >>  ............................................
    pt  Não é bobo. É só cedo. Quase tudo parece bobo cedo.
    >>  ............................................
  lazy.dialogue.conversations.hopes.young.dismiss/2
    en  ...Aye, well. Ask me again in ten years and see how silly it looks then.
    >>  ............................................
    pt  ...É, bom. Me pergunte em dez anos e veja como fica bobo.
    >>  ............................................
  lazy.dialogue.conversations.hopes.young.dismiss/3
    en  Right you are. It'll still be there tomorrow.
    >>  ............................................
    pt  Você tem razão. Vai continuar lá amanhã.
    >>  ............................................
  odd.dialogue.conversations.hopes.young.dismiss/1
    en  ...It's not silly.
    >>  ............................................
    pt  ...Não é bobo.
    >>  ............................................
  odd.dialogue.conversations.hopes.young.dismiss/2
    en  It's mine. That's all.
    >>  ............................................
    pt  É meu. Só isso.
    >>  ............................................
  odd.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. Forget it, then.
    >>  ............................................
    pt  ...Certo. Esqueça, então.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly. It's just early. Most things look silly early.
    >>  ............................................
    pt  Não é bobo. É só cedo. Quase tudo parece bobo cedo.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.young.dismiss/2
    en  ...Aye, well. Ask me again in ten years and see how silly it looks then.
    >>  ............................................
    pt  ...É, bom. Me pergunte em dez anos e veja como fica bobo.
    >>  ............................................
  peaceful.dialogue.conversations.hopes.young.dismiss/3
    en  Right you are. It'll still be there tomorrow.
    >>  ............................................
    pt  Você tem razão. Vai continuar lá amanhã.
    >>  ............................................
  peppy.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly! It's just not yours, which is different and much less interesting.
    >>  ............................................
    pt  Não é bobo! Só não é seu, o que é diferente e bem menos interessante.
    >>  ............................................
  peppy.dialogue.conversations.hopes.young.dismiss/2
    en  Right! Silly. Every good idea started out silly, ask anyone.
    >>  ............................................
    pt  Certo! Bobo. Toda boa ideia começou boba, pergunte a qualquer um.
    >>  ............................................
  peppy.dialogue.conversations.hopes.young.dismiss/3
    en  ...Ha. I'll remember you said that when I've got it.
    >>  ............................................
    pt  ...Ha. Vou lembrar que você disse isso quando eu conseguir.
    >>  ............................................
  playful.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly! It's just not yours, which is different and much less interesting.
    >>  ............................................
    pt  Não é bobo! Só não é seu, o que é diferente e bem menos interessante.
    >>  ............................................
  playful.dialogue.conversations.hopes.young.dismiss/2
    en  Right! Silly. Every good idea started out silly, ask anyone.
    >>  ............................................
    pt  Certo! Bobo. Toda boa ideia começou boba, pergunte a qualquer um.
    >>  ............................................
  playful.dialogue.conversations.hopes.young.dismiss/3
    en  ...Ha. I'll remember you said that when I've got it.
    >>  ............................................
    pt  ...Ha. Vou lembrar que você disse isso quando eu conseguir.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly. It's just early. Most things look silly early.
    >>  ............................................
    pt  Não é bobo. É só cedo. Quase tudo parece bobo cedo.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.young.dismiss/2
    en  ...Aye, well. Ask me again in ten years and see how silly it looks then.
    >>  ............................................
    pt  ...É, bom. Me pergunte em dez anos e veja como fica bobo.
    >>  ............................................
  relaxed.dialogue.conversations.hopes.young.dismiss/3
    en  Right you are. It'll still be there tomorrow.
    >>  ............................................
    pt  Você tem razão. Vai continuar lá amanhã.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.young.dismiss/1
    en  ...It's not silly. It's just small, and small is easy to laugh at, %1$s.
    >>  ............................................
    pt  ...Não é bobo. É só pequeno, e pequeno é fácil de rir, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.young.dismiss/2
    en  I knew you'd say that. I told you anyway.
    >>  ............................................
    pt  Eu sabia que você ia dizer isso. Contei mesmo assim.
    >>  ............................................
  sensitive.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. I'll keep the rest of them in my head.
    >>  ............................................
    pt  ...Certo. Vou guardar o resto na cabeça.
    >>  ............................................
  shy.dialogue.conversations.hopes.young.dismiss/1
    en  ...It's not silly.
    >>  ............................................
    pt  ...Não é bobo.
    >>  ............................................
  shy.dialogue.conversations.hopes.young.dismiss/2
    en  It's mine. That's all.
    >>  ............................................
    pt  É meu. Só isso.
    >>  ............................................
  shy.dialogue.conversations.hopes.young.dismiss/3
    en  ...Right. Forget it, then.
    >>  ............................................
    pt  ...Certo. Esqueça, então.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly! It's just not yours, which is different and much less interesting.
    >>  ............................................
    pt  Não é bobo! Só não é seu, o que é diferente e bem menos interessante.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.young.dismiss/2
    en  Right! Silly. Every good idea started out silly, ask anyone.
    >>  ............................................
    pt  Certo! Bobo. Toda boa ideia começou boba, pergunte a qualquer um.
    >>  ............................................
  upbeat.dialogue.conversations.hopes.young.dismiss/3
    en  ...Ha. I'll remember you said that when I've got it.
    >>  ............................................
    pt  ...Ha. Vou lembrar que você disse isso quando eu conseguir.
    >>  ............................................
  witty.dialogue.conversations.hopes.young.dismiss/1
    en  It's not silly! It's just not yours, which is different and much less interesting.
    >>  ............................................
    pt  Não é bobo! Só não é seu, o que é diferente e bem menos interessante.
    >>  ............................................
  witty.dialogue.conversations.hopes.young.dismiss/2
    en  Right! Silly. Every good idea started out silly, ask anyone.
    >>  ............................................
    pt  Certo! Bobo. Toda boa ideia começou boba, pergunte a qualquer um.
    >>  ............................................
  witty.dialogue.conversations.hopes.young.dismiss/3
    en  ...Ha. I'll remember you said that when I've got it.
    >>  ............................................
    pt  ...Ha. Vou lembrar que você disse isso quando eu conseguir.
    >>  ............................................
```

</details>


### Button `leave` — "Keep hoping. I'll get on."

*stance family `exit` · tone `plain` · answers the beat(s) `hopes.child.to.hopes.young`, `hopes.teen.to.hopes.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.hopes.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.hopes.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.hopes.young.respond.leave   [25 chars]
    en  Keep hoping. I'll get on.
    >>  ............................................
    pt  Continue esperando. Eu vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.young.leave
WHO    VILLAGER — what the player reads after pressing "Keep hoping. I'll get on."
       spoken on: conversations.topic.hopes.young.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.young.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.young.leave/1   [9 chars]
    en  Bye then!
    >>  ............................................
    pt  Tchau então!
    >>  ............................................
  dialogue.conversations.hopes.young.leave/2   [14 chars]
    en  See you, %1$s.
    >>  ............................................
    pt  Até mais, %1$s.
    >>  ............................................
  dialogue.conversations.hopes.young.leave/3   [10 chars]
    en  Okay. Bye.
    >>  ............................................
    pt  Tá. Tchau.
    >>  ............................................
```

---

