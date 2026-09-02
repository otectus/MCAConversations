# Topic: life

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `life` |
| Opened from | question `conversations.cat.personal`, button `life` |
| Depth class (its heart budget) | `deep` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `curiosity`, `self_disclosure`, `dismissal`, `humor`, `restraint`, `exit` |
| Narrative arc | `life`, max stage 2 |
| Milestones it can set | `life.chapter` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.life
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.life   [24 chars]
    en  Tell me about your life.
    >>  ............................................
    pt  Me conta sobre a sua vida.
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.life.resume.followup`](#conversations-arc-life-resume-followup)
- [`conversations.arc.life.resume.respond`](#conversations-arc-life-resume-respond)
- [`conversations.scene.life.followup`](#conversations-scene-life-followup)
- [`conversations.scene.life.how_i_came_here.respond`](#conversations-scene-life-how-i-came-here-respond)
- [`conversations.scene.life.the_chapter_im_in.respond`](#conversations-scene-life-the-chapter-im-in-respond)
- [`conversations.topic.life.close`](#conversations-topic-life-close)
- [`conversations.topic.life.followup`](#conversations-topic-life-followup)
- [`conversations.topic.life.guarded.respond`](#conversations-topic-life-guarded-respond)
- [`conversations.topic.life.rebuked.followup`](#conversations-topic-life-rebuked-followup)
- [`conversations.topic.life.respond`](#conversations-topic-life-respond)
- [`conversations.topic.life.toddler.respond`](#conversations-topic-life-toddler-respond)
- [`conversations.topic.life.young.respond`](#conversations-topic-life-young-respond)

---

## `conversations.arc.life.resume.followup`

**Reached from 3 route(s):** `conversations.arc.life.resume.respond` / `ask_chapter`; `conversations.arc.life.resume.respond` / `ask_chapter`; `conversations.arc.life.resume.respond` / `ask_now`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.life.resume.ask_chapter.plain` — e.g. "There's more to it, aye. Where were we?"
- `conversations.life.resume.ask_chapter.remembered` — e.g. "You remembered which part. ...Alright. Here's how it ended."
- `conversations.life.resume.ask_now` — e.g. "Where it leaves me. Huh. Better than it left me then, I think."


```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.life.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.life.resume.followup   [22 chars]
    en  That's the rest of it.
    >>  ............................................
    pt  É esse o resto.
    >>  ............................................
```


### Button `thank` — "I'm glad I know that now."

*stance family `candor` · tone `gentle` · answers the beat(s) `life.resume.ask_chapter.plain.to.life`, `life.resume.ask_chapter.remembered.to.life`, `life.resume.ask_now.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.resume.followup.thank` — accepted phrasings: "i am glad i know that now"; "glad to know that now"; "i am glad to know it"
  - the message must contain one of: `glad`
  - scored words: `glad`(1.5), `know`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.followup.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.life.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.life.resume.followup.thank   [25 chars]
    en  I'm glad I know that now.
    >>  ............................................
    pt  Fico contente de saber disso agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.resume.followup.thank`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `life.resume.followup.thank`)_
- Then opens: `conversations.topic.life.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Here's one of mine, then." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.life.resume.followup.thank
WHO    VILLAGER — what the player reads after pressing "I'm glad I know that now."
       spoken on: conversations.arc.life.resume.followup, button `thank`
       leaves the player on: conversations.topic.life.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.followup.thank.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.resume.followup.thank/1   [63 chars]
    en  Glad. Hm. I'd braced for 'that's a lot' and got 'glad' instead.
    >>  ............................................
    pt  Contente. Hm. Eu tinha me preparado para 'é muita coisa' e recebi 'contente'.
    >>  ............................................
  dialogue.conversations.life.resume.followup.thank/2   [77 chars]
    en  Then it did what I hoped it would, which is rare for anything I say out loud.
    >>  ............................................
    pt  Então fez o que eu esperava, o que é raro para qualquer coisa que eu digo em voz alta.
    >>  ............................................
  dialogue.conversations.life.resume.followup.thank/3   [64 chars]
    en  True enough. Well. Now you know where the dents came from, %1$s.
    >>  ............................................
    pt  Bem verdade. Bom. Agora você sabe de onde vieram os amassados, %1$s.
    >>  ............................................
```


### Button `connect` — "That explains a lot about you."

*stance family `curiosity` · tone `plain` · answers the beat(s) `life.resume.ask_chapter.plain.to.life`, `life.resume.ask_chapter.remembered.to.life`, `life.resume.ask_now.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.resume.followup.connect` — accepted phrasings: "that explains a lot about you"; "that explains a lot"; "well that explains a great deal"
  - the message must contain one of: `explains`
  - scored words: `explains`(1.7), `lot`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.followup.connect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.life.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.life.resume.followup.connect   [30 chars]
    en  That explains a lot about you.
    >>  ............................................
    pt  Isso explica muita coisa sobre você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.resume.followup.connect`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — familiarity +5, warmth +2  _(recorded under topic `life.resume.followup.connect`)_
- Then opens: `conversations.topic.life.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Here's one of mine, then." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.life.resume.followup.connect
WHO    VILLAGER — what the player reads after pressing "That explains a lot about you."
       spoken on: conversations.arc.life.resume.followup, button `connect`
       leaves the player on: conversations.topic.life.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.followup.connect.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.resume.followup.connect/1   [93 chars]
    en  ...It does, doesn't it. Huh. I've never had those two things stood next to each other before.
    >>  ............................................
    pt  ...Explica mesmo, né. Hm. Nunca tive essas duas coisas lado a lado antes.
    >>  ............................................
  dialogue.conversations.life.resume.followup.connect/2   [93 chars]
    en  You've gone and made sense of me. I'm not sure I wanted to be made sense of, but here we are.
    >>  ............................................
    pt  Você foi lá e me fez fazer sentido. Não sei se eu queria fazer sentido, mas aqui estamos.
    >>  ............................................
  dialogue.conversations.life.resume.followup.connect/3   [82 chars]
    en  It explains the good bits too, mind. Don't only use it for the awkward ones, %1$s.
    >>  ............................................
    pt  Explica as partes boas também. Não use só para as sem graça, %1$s.
    >>  ............................................
```


### Button `probe` — "Was there more you left out?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `life.resume.ask_chapter.plain.to.life`, `life.resume.ask_chapter.remembered.to.life`, `life.resume.ask_now.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.resume.followup.probe` — accepted phrasings: "was there more you left out"; "did you leave anything out"; "what did you leave out"
  - the message must contain one of: `left`, `out`
  - scored words: `left`(1.5), `out`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.followup.probe
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.life.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.life.resume.followup.probe   [28 chars]
    en  Was there more you left out?
    >>  ............................................
    pt  Ficou mais alguma coisa de fora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension +4, trust -2  _(recorded under topic `life.resume.followup.probe`)_
- Then opens: `conversations.topic.life.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Here's one of mine, then." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.life.resume.followup.probe
WHO    VILLAGER — what the player reads after pressing "Was there more you left out?"
       spoken on: conversations.arc.life.resume.followup, button `probe`
       leaves the player on: conversations.topic.life.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.followup.probe.to.life`: the villager accepts. Subject `life`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.resume.followup.probe/1   [75 chars]
    en  There's always more left out. That's what leaving out means. ...Let it lie.
    >>  ............................................
    pt  Sempre fica coisa de fora. É isso que deixar de fora quer dizer. ...Deixe quieto.
    >>  ............................................
  dialogue.conversations.life.resume.followup.probe/2   [83 chars]
    en  You get what I hand you. The rest isn't a locked door, it's just not a door at all.
    >>  ............................................
    pt  Você leva o que eu entrego. O resto não é porta trancada, é que não é porta nenhuma.
    >>  ............................................
  dialogue.conversations.life.resume.followup.probe/3   [84 chars]
    en  Asking for the offcuts, %1$s. I gave you the whole loaf and you're after the crumbs.
    >>  ............................................
    pt  Pedindo as sobras, %1$s. Eu te dei o pão inteiro e você quer as migalhas.
    >>  ............................................
```


### Button `leave` — "That's plenty for one day."

*stance family `exit` · tone `plain` · answers the beat(s) `life.resume.ask_chapter.plain.to.life`, `life.resume.ask_chapter.remembered.to.life`, `life.resume.ask_now.to.life` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.life.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.life.resume.followup.leave   [26 chars]
    en  That's plenty for one day.
    >>  ............................................
    pt  Já é bastante por um dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.resume.followup.leave
WHO    VILLAGER — what the player reads after pressing "That's plenty for one day."
       spoken on: conversations.arc.life.resume.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.followup.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.resume.followup.leave/1   [43 chars]
    en  So it is. Enough history for one afternoon.
    >>  ............................................
    pt  É assim mesmo. Chega de história por uma tarde.
    >>  ............................................
  dialogue.conversations.life.resume.followup.leave/2   [24 chars]
    en  Right you are. It keeps.
    >>  ............................................
    pt  Isso mesmo. Ela guarda.
    >>  ............................................
  dialogue.conversations.life.resume.followup.leave/3   [48 chars]
    en  Off you go, %1$s. Thanks for sitting through it.
    >>  ............................................
    pt  Pode ir, %1$s. Obrigado por aguentar tudo.
    >>  ............................................
```

---


## `conversations.arc.life.resume.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `life`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.life.revisit` — e.g. "You asked me about my life once. I've been thinking about what I left out."


```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.life.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.life.resume.respond   [29 chars]
    en  You remember what I told you.
    >>  ............................................
    pt  Você lembra do que eu te contei.
    >>  ............................................
```


### Button `ask_chapter` — "You never finished that story."

*stance family `curiosity` · tone `plain` · answers the beat(s) `life.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.resume.ask_chapter` — accepted phrasings: "you never finished that story"; "how did it end"; "tell me the rest of it"
  - the message must contain one of: `finished`, `ended`, `rest`
  - scored words: `finished`(1.5), `ended`(1.5), `rest`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.respond.ask_chapter
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.life.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.life.resume.respond.ask_chapter   [30 chars]
    en  You never finished that story.
    >>  ............................................
    pt  Você nunca terminou aquela história.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when milestone `life.chapter` is set
- Does: **hearts +2** — decision id `life.resume.ask_chapter`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, familiarity +3  _(recorded under topic `life.resume.ask_chapter`)_
- Does: arc `life` — advance to stage 2
- Then opens: `conversations.arc.life.resume.followup`
- …where the player's next choices will be: "I'm glad I know that now." | "That explains a lot about you." | "Was there more you left out?" | "That's plenty for one day."

```text
POOL   dialogue key: dialogue.conversations.life.resume.ask_chapter.remembered
WHO    VILLAGER — what the player reads after pressing "You never finished that story."
       spoken on: conversations.arc.life.resume.respond, button `ask_chapter`
       leaves the player on: conversations.arc.life.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.ask_chapter.remembered.to.life`: the villager accepts. Subject `life`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.resume.ask_chapter.remembered/1   [59 chars]
    en  You remembered which part. ...Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou de qual parte. ...Certo. Foi assim que terminou.
    >>  ............................................
  dialogue.conversations.life.resume.ask_chapter.remembered/2   [73 chars]
    en  You remembered it overnight. Not everyone would. That earns you the rest.
    >>  ............................................
    pt  Você lembrou de um dia para o outro. Nem todo mundo lembraria. Isso te dá direito ao resto.
    >>  ............................................
  dialogue.conversations.life.resume.ask_chapter.remembered/3   [64 chars]
    en  The very part I nearly didn't tell you. Fine — the ending, then.
    >>  ............................................
    pt  Justo a parte que eu quase não contei. Certo — o final, então.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part, %1$s. Alright. Here's how it ended, then.
    >>  ............................................
    pt  Você lembrou qual parte, %1$s. Está bem. Então aqui está como terminou.
    >>  ............................................
  anxious.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. I'd assumed it had all run together into 'a story'.
    >>  ............................................
    pt  Você tinha o capítulo certo. Eu supunha que tinha virado tudo 'uma história'.
    >>  ............................................
  anxious.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. That's the part I'll remember about tonight.
    >>  ............................................
    pt  Você sabia onde eu parei. É a parte que eu vou lembrar desta noite.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended, and it takes a while.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou, e leva um tempo.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  The right chapter, months on. Stories keep better with somebody holding the place.
    >>  ............................................
    pt  O capítulo certo, meses depois. Histórias se conservam melhor com alguém guardando o lugar.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Then we'll go on from there, slowly.
    >>  ............................................
    pt  Você sabia onde eu parei. Então a gente segue dali, devagar.
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou.
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. Right. Then I'll not go over old ground.
    >>  ............................................
    pt  Você tinha o capítulo certo. Certo. Então eu não repito o que já foi.
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Good. It saves us both the recap.
    >>  ............................................
    pt  Você sabia onde eu parei. Bom. Poupa o resumo pros dois.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. Right. Then I'll not go over old ground.
    >>  ............................................
    pt  Você tinha o capítulo certo. Certo. Então eu não repito o que já foi.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Good. It saves us both the recap.
    >>  ............................................
    pt  Você sabia onde eu parei. Bom. Poupa o resumo pros dois.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part, %1$s. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte, %1$s. Está bem. Aqui está como terminou.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. That tells me you were listening and not waiting your turn.
    >>  ............................................
    pt  Você tinha o capítulo certo. Isso me diz que você escutava e não esperava a vez.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Sit down, then — the ending is the part worth having.
    >>  ............................................
    pt  Você sabia onde eu parei. Então sente-se — o final é a parte que vale.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part, %1$s. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte, %1$s. Está bem. Aqui está como terminou.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. That tells me you were listening and not waiting your turn.
    >>  ............................................
    pt  Você tinha o capítulo certo. Isso me diz que você escutava e não esperava a vez.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Sit down, then — the ending is the part worth having.
    >>  ............................................
    pt  Você sabia onde eu parei. Então sente-se — o final é a parte que vale.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part, %1$s. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte, %1$s. Está bem. Aqui está como terminou.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. That tells me you were listening and not waiting your turn.
    >>  ............................................
    pt  Você tinha o capítulo certo. Isso me diz que você escutava e não esperava a vez.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Sit down, then — the ending is the part worth having.
    >>  ............................................
    pt  Você sabia onde eu parei. Então sente-se — o final é a parte que vale.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part, %1$s. Alright. Here's how it ended, then.
    >>  ............................................
    pt  Você lembrou qual parte, %1$s. Está bem. Então aqui está como terminou.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. I'd assumed it had all run together into 'a story'.
    >>  ............................................
    pt  Você tinha o capítulo certo. Eu supunha que tinha virado tudo 'uma história'.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. That's the part I'll remember about tonight.
    >>  ............................................
    pt  Você sabia onde eu parei. É a parte que eu vou lembrar desta noite.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. Right. Then I'll not go over old ground.
    >>  ............................................
    pt  Você tinha o capítulo certo. Certo. Então eu não repito o que já foi.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Good. It saves us both the recap.
    >>  ............................................
    pt  Você sabia onde eu parei. Bom. Poupa o resumo pros dois.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. Right. Then I'll not go over old ground.
    >>  ............................................
    pt  Você tinha o capítulo certo. Certo. Então eu não repito o que já foi.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Good. It saves us both the recap.
    >>  ............................................
    pt  Você sabia onde eu parei. Bom. Poupa o resumo pros dois.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  The right chapter. Right.
    >>  ............................................
    pt  O capítulo certo. Certo.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped.
    >>  ............................................
    pt  Você sabia onde eu parei.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended, and it takes a while.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou, e leva um tempo.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  The right chapter, months on. Stories keep better with somebody holding the place.
    >>  ............................................
    pt  O capítulo certo, meses depois. Histórias se conservam melhor com alguém guardando o lugar.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Then we'll go on from there, slowly.
    >>  ............................................
    pt  Você sabia onde eu parei. Então a gente segue dali, devagar.
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou.
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  The right chapter. Right.
    >>  ............................................
    pt  O capítulo certo. Certo.
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped.
    >>  ............................................
    pt  Você sabia onde eu parei.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended, and it takes a while.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou, e leva um tempo.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  The right chapter, months on. Stories keep better with somebody holding the place.
    >>  ............................................
    pt  O capítulo certo, meses depois. Histórias se conservam melhor com alguém guardando o lugar.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Then we'll go on from there, slowly.
    >>  ............................................
    pt  Você sabia onde eu parei. Então a gente segue dali, devagar.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part! Alright. Here's how it ended, then.
    >>  ............................................
    pt  Você lembrou qual parte! Está bem. Então aqui está como terminou.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. Remarkable. I'd have needed reminding myself.
    >>  ............................................
    pt  Você tinha o capítulo certo. Notável. Eu mesmo precisaria de lembrete.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped! Right — no recap, straight to the end.
    >>  ............................................
    pt  Você sabia onde eu parei! Certo — sem resumo, direto ao fim.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part! Alright. Here's how it ended, then.
    >>  ............................................
    pt  Você lembrou qual parte! Está bem. Então aqui está como terminou.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. Remarkable. I'd have needed reminding myself.
    >>  ............................................
    pt  Você tinha o capítulo certo. Notável. Eu mesmo precisaria de lembrete.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped! Right — no recap, straight to the end.
    >>  ............................................
    pt  Você sabia onde eu parei! Certo — sem resumo, direto ao fim.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended, and it takes a while.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou, e leva um tempo.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  The right chapter, months on. Stories keep better with somebody holding the place.
    >>  ............................................
    pt  O capítulo certo, meses depois. Histórias se conservam melhor com alguém guardando o lugar.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. Then we'll go on from there, slowly.
    >>  ............................................
    pt  Você sabia onde eu parei. Então a gente segue dali, devagar.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part, %1$s. Alright. Here's how it ended, then.
    >>  ............................................
    pt  Você lembrou qual parte, %1$s. Está bem. Então aqui está como terminou.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. I'd assumed it had all run together into 'a story'.
    >>  ............................................
    pt  Você tinha o capítulo certo. Eu supunha que tinha virado tudo 'uma história'.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped. That's the part I'll remember about tonight.
    >>  ............................................
    pt  Você sabia onde eu parei. É a parte que eu vou lembrar desta noite.
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part. Alright. Here's how it ended.
    >>  ............................................
    pt  Você lembrou qual parte. Está bem. Aqui está como terminou.
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  The right chapter. Right.
    >>  ............................................
    pt  O capítulo certo. Certo.
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped.
    >>  ............................................
    pt  Você sabia onde eu parei.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part! Alright. Here's how it ended, then.
    >>  ............................................
    pt  Você lembrou qual parte! Está bem. Então aqui está como terminou.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. Remarkable. I'd have needed reminding myself.
    >>  ............................................
    pt  Você tinha o capítulo certo. Notável. Eu mesmo precisaria de lembrete.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped! Right — no recap, straight to the end.
    >>  ............................................
    pt  Você sabia onde eu parei! Certo — sem resumo, direto ao fim.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_chapter.remembered/1
    en  You remembered which part! Alright. Here's how it ended, then.
    >>  ............................................
    pt  Você lembrou qual parte! Está bem. Então aqui está como terminou.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_chapter.remembered/2
    en  You had the right chapter. Remarkable. I'd have needed reminding myself.
    >>  ............................................
    pt  Você tinha o capítulo certo. Notável. Eu mesmo precisaria de lembrete.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_chapter.remembered/3
    en  You knew where I stopped! Right — no recap, straight to the end.
    >>  ............................................
    pt  Você sabia onde eu parei! Certo — sem resumo, direto ao fim.
    >>  ............................................
```

</details>


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when milestone `life.chapter` is set  _(chance -2000)_
- Does: **hearts +1** — decision id `life.resume.ask_chapter`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — familiarity +3  _(recorded under topic `life.resume.ask_chapter`)_
- Does: arc `life` — advance to stage 2
- Then opens: `conversations.arc.life.resume.followup`
- …where the player's next choices will be: "I'm glad I know that now." | "That explains a lot about you." | "Was there more you left out?" | "That's plenty for one day."

```text
POOL   dialogue key: dialogue.conversations.life.resume.ask_chapter.plain
WHO    VILLAGER — what the player reads after pressing "You never finished that story."
       spoken on: conversations.arc.life.resume.respond, button `ask_chapter`
       leaves the player on: conversations.arc.life.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.ask_chapter.plain.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.resume.ask_chapter.plain/1   [39 chars]
    en  There's more to it, aye. Where were we?
    >>  ............................................
    pt  Tem mais coisa, é. Onde a gente parou?
    >>  ............................................
  dialogue.conversations.life.resume.ask_chapter.plain/2   [35 chars]
    en  I did leave it half-told, didn't I.
    >>  ............................................
    pt  Eu deixei pela metade mesmo, né.
    >>  ............................................
  dialogue.conversations.life.resume.ask_chapter.plain/3   [61 chars]
    en  The rest isn't as good as the beginning. But you can have it.
    >>  ............................................
    pt  O resto não é tão bom quanto o começo. Mas pode ficar com ele.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we, %1$s? I've been half hoping you'd not come back to it.
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava, %1$s? Eu meio que esperava que você não voltasse.
    >>  ............................................
  anxious.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. Remind me — I've kept my place and I'd rather you said it first.
    >>  ............................................
    pt  Mais, sim. Me lembre — eu guardei meu lugar e prefiro que você diga primeiro.
    >>  ............................................
  anxious.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. It gets harder from here, so bear with me.
    >>  ............................................
    pt  Tem um resto. Fica mais difícil daqui, então tenha paciência.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we? It'll come back to me.
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava? Vai me voltar.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. It's an old story; it keeps well between tellings.
    >>  ............................................
    pt  Mais, sim. É uma história velha; se conserva bem entre as contadas.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. No hurry — it's waited this long.
    >>  ............................................
    pt  Tem um resto. Sem pressa — já esperou até agora.
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it. Where were we?
    >>  ............................................
    pt  Tem mais. Onde a gente estava?
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, aye. Remind me where I stopped.
    >>  ............................................
    pt  Mais, é. Me lembre onde eu parei.
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. I'd have to find my place again.
    >>  ............................................
    pt  Tem um resto. Eu teria que achar meu lugar de novo.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it. Where were we?
    >>  ............................................
    pt  Tem mais. Onde a gente estava?
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, aye. Remind me where I stopped.
    >>  ............................................
    pt  Mais, é. Me lembre onde eu parei.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. I'd have to find my place again.
    >>  ............................................
    pt  Tem um resto. Eu teria que achar meu lugar de novo.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we, %1$s?
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava, %1$s?
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. You'll have to remind me — I'd rather pick up where you left off.
    >>  ............................................
    pt  Mais, sim. Você vai ter que me lembrar — prefiro continuar de onde você parou.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. Sit down and I'll find my place.
    >>  ............................................
    pt  Tem um resto. Sente-se e eu acho meu lugar.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we, %1$s?
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava, %1$s?
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. You'll have to remind me — I'd rather pick up where you left off.
    >>  ............................................
    pt  Mais, sim. Você vai ter que me lembrar — prefiro continuar de onde você parou.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. Sit down and I'll find my place.
    >>  ............................................
    pt  Tem um resto. Sente-se e eu acho meu lugar.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we, %1$s?
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava, %1$s?
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. You'll have to remind me — I'd rather pick up where you left off.
    >>  ............................................
    pt  Mais, sim. Você vai ter que me lembrar — prefiro continuar de onde você parou.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. Sit down and I'll find my place.
    >>  ............................................
    pt  Tem um resto. Sente-se e eu acho meu lugar.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we, %1$s? I've been half hoping you'd not come back to it.
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava, %1$s? Eu meio que esperava que você não voltasse.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. Remind me — I've kept my place and I'd rather you said it first.
    >>  ............................................
    pt  Mais, sim. Me lembre — eu guardei meu lugar e prefiro que você diga primeiro.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. It gets harder from here, so bear with me.
    >>  ............................................
    pt  Tem um resto. Fica mais difícil daqui, então tenha paciência.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it. Where were we?
    >>  ............................................
    pt  Tem mais. Onde a gente estava?
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, aye. Remind me where I stopped.
    >>  ............................................
    pt  Mais, é. Me lembre onde eu parei.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. I'd have to find my place again.
    >>  ............................................
    pt  Tem um resto. Eu teria que achar meu lugar de novo.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it. Where were we?
    >>  ............................................
    pt  Tem mais. Onde a gente estava?
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, aye. Remind me where I stopped.
    >>  ............................................
    pt  Mais, é. Me lembre onde eu parei.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. I'd have to find my place again.
    >>  ............................................
    pt  Tem um resto. Eu teria que achar meu lugar de novo.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it. Where were we?
    >>  ............................................
    pt  Tem mais. Onde a gente estava?
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, aye.
    >>  ............................................
    pt  Mais, é.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest.
    >>  ............................................
    pt  Tem um resto.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we? It'll come back to me.
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava? Vai me voltar.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. It's an old story; it keeps well between tellings.
    >>  ............................................
    pt  Mais, sim. É uma história velha; se conserva bem entre as contadas.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. No hurry — it's waited this long.
    >>  ............................................
    pt  Tem um resto. Sem pressa — já esperou até agora.
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it. Where were we?
    >>  ............................................
    pt  Tem mais. Onde a gente estava?
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, aye.
    >>  ............................................
    pt  Mais, é.
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest.
    >>  ............................................
    pt  Tem um resto.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we? It'll come back to me.
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava? Vai me voltar.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. It's an old story; it keeps well between tellings.
    >>  ............................................
    pt  Mais, sim. É uma história velha; se conserva bem entre as contadas.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. No hurry — it's waited this long.
    >>  ............................................
    pt  Tem um resto. Sem pressa — já esperou até agora.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye! Where were we? I've lost my place entirely.
    >>  ............................................
    pt  Tem mais, é! Onde a gente estava? Perdi meu lugar completamente.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. Remind me where I stopped — I tell it in a different order each time.
    >>  ............................................
    pt  Mais, sim. Me lembre onde eu parei — eu conto numa ordem diferente toda vez.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest! Give me a moment to find the thread.
    >>  ............................................
    pt  Tem um resto! Me dê um momento pra achar o fio.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye! Where were we? I've lost my place entirely.
    >>  ............................................
    pt  Tem mais, é! Onde a gente estava? Perdi meu lugar completamente.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. Remind me where I stopped — I tell it in a different order each time.
    >>  ............................................
    pt  Mais, sim. Me lembre onde eu parei — eu conto numa ordem diferente toda vez.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest! Give me a moment to find the thread.
    >>  ............................................
    pt  Tem um resto! Me dê um momento pra achar o fio.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we? It'll come back to me.
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava? Vai me voltar.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. It's an old story; it keeps well between tellings.
    >>  ............................................
    pt  Mais, sim. É uma história velha; se conserva bem entre as contadas.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. No hurry — it's waited this long.
    >>  ............................................
    pt  Tem um resto. Sem pressa — já esperou até agora.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye. Where were we, %1$s? I've been half hoping you'd not come back to it.
    >>  ............................................
    pt  Tem mais, é. Onde a gente estava, %1$s? Eu meio que esperava que você não voltasse.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. Remind me — I've kept my place and I'd rather you said it first.
    >>  ............................................
    pt  Mais, sim. Me lembre — eu guardei meu lugar e prefiro que você diga primeiro.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest. It gets harder from here, so bear with me.
    >>  ............................................
    pt  Tem um resto. Fica mais difícil daqui, então tenha paciência.
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it. Where were we?
    >>  ............................................
    pt  Tem mais. Onde a gente estava?
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, aye.
    >>  ............................................
    pt  Mais, é.
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest.
    >>  ............................................
    pt  Tem um resto.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye! Where were we? I've lost my place entirely.
    >>  ............................................
    pt  Tem mais, é! Onde a gente estava? Perdi meu lugar completamente.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. Remind me where I stopped — I tell it in a different order each time.
    >>  ............................................
    pt  Mais, sim. Me lembre onde eu parei — eu conto numa ordem diferente toda vez.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest! Give me a moment to find the thread.
    >>  ............................................
    pt  Tem um resto! Me dê um momento pra achar o fio.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_chapter.plain/1
    en  There's more to it, aye! Where were we? I've lost my place entirely.
    >>  ............................................
    pt  Tem mais, é! Onde a gente estava? Perdi meu lugar completamente.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_chapter.plain/2
    en  More, yes. Remind me where I stopped — I tell it in a different order each time.
    >>  ............................................
    pt  Mais, sim. Me lembre onde eu parei — eu conto numa ordem diferente toda vez.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_chapter.plain/3
    en  There's a rest! Give me a moment to find the thread.
    >>  ............................................
    pt  Tem um resto! Me dê um momento pra achar o fio.
    >>  ............................................
```

</details>


### Button `ask_now` — "And where does that leave you now?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `life.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.resume.ask_now` — accepted phrasings: "where does that leave you now"; "and now"; "how are you with it today"
  - the message must contain one of: `now`, `today`, `leave`
  - scored words: `now`(1.5), `leave`(1.0), `today`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.respond.ask_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.life.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.life.resume.respond.ask_now   [34 chars]
    en  And where does that leave you now?
    >>  ............................................
    pt  E onde isso te deixa agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.resume.ask_now`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, familiarity +2  _(recorded under topic `life.resume.ask_now`)_
- Does: arc `life` — advance to stage 2
- Then opens: `conversations.arc.life.resume.followup`
- …where the player's next choices will be: "I'm glad I know that now." | "That explains a lot about you." | "Was there more you left out?" | "That's plenty for one day."

```text
POOL   dialogue key: dialogue.conversations.life.resume.ask_now
WHO    VILLAGER — what the player reads after pressing "And where does that leave you now?"
       spoken on: conversations.arc.life.resume.respond, button `ask_now`
       leaves the player on: conversations.arc.life.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.ask_now.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.resume.ask_now/1   [62 chars]
    en  Where it leaves me. Huh. Better than it left me then, I think.
    >>  ............................................
    pt  Onde me deixa. Hm. Melhor do que me deixava antes, eu acho.
    >>  ............................................
  dialogue.conversations.life.resume.ask_now/2   [54 chars]
    en  Standing here, mostly. That's the honest answer, %1$s.
    >>  ............................................
    pt  Aqui de pé, principalmente. É a resposta honesta, %1$s.
    >>  ............................................
  dialogue.conversations.life.resume.ask_now/3   [52 chars]
    en  Further along than I was. That's all anyone manages.
    >>  ............................................
    pt  Mais adiante do que eu estava. É tudo que qualquer um consegue.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Huh. Better than it left me then, %1$s. I'd not checked in a while.
    >>  ............................................
    pt  Onde isso me deixa. Huh. Melhor do que me deixou na época, %1$s. Fazia tempo que eu não conferia.
    >>  ............................................
  anxious.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. I hadn't known that until you asked, which is a strange thing to find out.
    >>  ............................................
    pt  Agora? Melhor. Eu não sabia até você perguntar, o que é estranho de descobrir.
    >>  ............................................
  anxious.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. Lighter. I'd been carrying it as though it hadn't changed.
    >>  ............................................
    pt  Fica diferente. Mais leve. Eu carregava como se não tivesse mudado.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then. Time does most of that.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época. O tempo faz quase tudo isso.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. Twenty years will do that to almost anything.
    >>  ............................................
    pt  Agora? Melhor. Vinte anos fazem isso com quase tudo.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. It'll sit differently again in another ten years, I expect.
    >>  ............................................
    pt  Fica diferente. Vai ficar diferente de novo em mais dez anos, eu imagino.
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then, I think.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época, eu acho.
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. Not fixed. Better.
    >>  ............................................
    pt  Agora? Melhor. Não resolvido. Melhor.
    >>  ............................................
  confident.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now. That's the honest answer.
    >>  ............................................
    pt  Fica diferente agora. É a resposta honesta.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then, I think.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época, eu acho.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. Not fixed. Better.
    >>  ............................................
    pt  Agora? Melhor. Não resolvido. Melhor.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now. That's the honest answer.
    >>  ............................................
    pt  Fica diferente agora. É a resposta honesta.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Huh. Better than it left me then, %1$s.
    >>  ............................................
    pt  Onde isso me deixa. Huh. Melhor do que me deixou na época, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. And being asked about now rather than then is part of why.
    >>  ............................................
    pt  Agora? Melhor. E ser perguntado sobre agora e não sobre então é parte do porquê.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. Nobody asks that, and it's the question I'd want asked.
    >>  ............................................
    pt  Fica diferente. Ninguém pergunta isso, e é a pergunta que eu queria que fizessem.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Huh. Better than it left me then, %1$s.
    >>  ............................................
    pt  Onde isso me deixa. Huh. Melhor do que me deixou na época, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. And being asked about now rather than then is part of why.
    >>  ............................................
    pt  Agora? Melhor. E ser perguntado sobre agora e não sobre então é parte do porquê.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. Nobody asks that, and it's the question I'd want asked.
    >>  ............................................
    pt  Fica diferente. Ninguém pergunta isso, e é a pergunta que eu queria que fizessem.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Huh. Better than it left me then, %1$s.
    >>  ............................................
    pt  Onde isso me deixa. Huh. Melhor do que me deixou na época, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. And being asked about now rather than then is part of why.
    >>  ............................................
    pt  Agora? Melhor. E ser perguntado sobre agora e não sobre então é parte do porquê.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. Nobody asks that, and it's the question I'd want asked.
    >>  ............................................
    pt  Fica diferente. Ninguém pergunta isso, e é a pergunta que eu queria que fizessem.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Huh. Better than it left me then, %1$s. I'd not checked in a while.
    >>  ............................................
    pt  Onde isso me deixa. Huh. Melhor do que me deixou na época, %1$s. Fazia tempo que eu não conferia.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. I hadn't known that until you asked, which is a strange thing to find out.
    >>  ............................................
    pt  Agora? Melhor. Eu não sabia até você perguntar, o que é estranho de descobrir.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. Lighter. I'd been carrying it as though it hadn't changed.
    >>  ............................................
    pt  Fica diferente. Mais leve. Eu carregava como se não tivesse mudado.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then, I think.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época, eu acho.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. Not fixed. Better.
    >>  ............................................
    pt  Agora? Melhor. Não resolvido. Melhor.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now. That's the honest answer.
    >>  ............................................
    pt  Fica diferente agora. É a resposta honesta.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then, I think.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época, eu acho.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. Not fixed. Better.
    >>  ............................................
    pt  Agora? Melhor. Não resolvido. Melhor.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now. That's the honest answer.
    >>  ............................................
    pt  Fica diferente agora. É a resposta honesta.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better.
    >>  ............................................
    pt  Agora? Melhor.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now.
    >>  ............................................
    pt  Fica diferente agora.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then. Time does most of that.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época. O tempo faz quase tudo isso.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. Twenty years will do that to almost anything.
    >>  ............................................
    pt  Agora? Melhor. Vinte anos fazem isso com quase tudo.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. It'll sit differently again in another ten years, I expect.
    >>  ............................................
    pt  Fica diferente. Vai ficar diferente de novo em mais dez anos, eu imagino.
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época.
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better.
    >>  ............................................
    pt  Agora? Melhor.
    >>  ............................................
  odd.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now.
    >>  ............................................
    pt  Fica diferente agora.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then. Time does most of that.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época. O tempo faz quase tudo isso.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. Twenty years will do that to almost anything.
    >>  ............................................
    pt  Agora? Melhor. Vinte anos fazem isso com quase tudo.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. It'll sit differently again in another ten years, I expect.
    >>  ............................................
    pt  Fica diferente. Vai ficar diferente de novo em mais dez anos, eu imagino.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me! Huh. Better than it left me then, I think.
    >>  ............................................
    pt  Onde isso me deixa! Huh. Melhor do que me deixou na época, eu acho.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better! Not fixed, but better, and I'll take better.
    >>  ............................................
    pt  Agora? Melhor! Não resolvido, mas melhor, e eu aceito melhor.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now. Nobody ever asks the 'now' question.
    >>  ............................................
    pt  Fica diferente agora. Ninguém pergunta a parte do 'agora'.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me! Huh. Better than it left me then, I think.
    >>  ............................................
    pt  Onde isso me deixa! Huh. Melhor do que me deixou na época, eu acho.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better! Not fixed, but better, and I'll take better.
    >>  ............................................
    pt  Agora? Melhor! Não resolvido, mas melhor, e eu aceito melhor.
    >>  ............................................
  playful.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now. Nobody ever asks the 'now' question.
    >>  ............................................
    pt  Fica diferente agora. Ninguém pergunta a parte do 'agora'.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then. Time does most of that.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época. O tempo faz quase tudo isso.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. Twenty years will do that to almost anything.
    >>  ............................................
    pt  Agora? Melhor. Vinte anos fazem isso com quase tudo.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. It'll sit differently again in another ten years, I expect.
    >>  ............................................
    pt  Fica diferente. Vai ficar diferente de novo em mais dez anos, eu imagino.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Huh. Better than it left me then, %1$s. I'd not checked in a while.
    >>  ............................................
    pt  Onde isso me deixa. Huh. Melhor do que me deixou na época, %1$s. Fazia tempo que eu não conferia.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better. I hadn't known that until you asked, which is a strange thing to find out.
    >>  ............................................
    pt  Agora? Melhor. Eu não sabia até você perguntar, o que é estranho de descobrir.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently. Lighter. I'd been carrying it as though it hadn't changed.
    >>  ............................................
    pt  Fica diferente. Mais leve. Eu carregava como se não tivesse mudado.
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me. Better than it left me then.
    >>  ............................................
    pt  Onde isso me deixa. Melhor do que me deixou na época.
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better.
    >>  ............................................
    pt  Agora? Melhor.
    >>  ............................................
  shy.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now.
    >>  ............................................
    pt  Fica diferente agora.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me! Huh. Better than it left me then, I think.
    >>  ............................................
    pt  Onde isso me deixa! Huh. Melhor do que me deixou na época, eu acho.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better! Not fixed, but better, and I'll take better.
    >>  ............................................
    pt  Agora? Melhor! Não resolvido, mas melhor, e eu aceito melhor.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now. Nobody ever asks the 'now' question.
    >>  ............................................
    pt  Fica diferente agora. Ninguém pergunta a parte do 'agora'.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_now/1
    en  Where it leaves me! Huh. Better than it left me then, I think.
    >>  ............................................
    pt  Onde isso me deixa! Huh. Melhor do que me deixou na época, eu acho.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_now/2
    en  Now? Better! Not fixed, but better, and I'll take better.
    >>  ............................................
    pt  Agora? Melhor! Não resolvido, mas melhor, e eu aceito melhor.
    >>  ............................................
  witty.dialogue.conversations.life.resume.ask_now/3
    en  It sits differently now. Nobody ever asks the 'now' question.
    >>  ............................................
    pt  Fica diferente agora. Ninguém pergunta a parte do 'agora'.
    >>  ............................................
```

</details>


### Button `brush_off` — "It was a long time ago."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `life.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.resume.brush_off` — accepted phrasings: "it was a long time ago"; "that is in the past"; "ancient history"
  - the message must contain one of: `ago`, `past`, `history`
  - scored words: `ago`(1.5), `past`(1.2), `history`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.respond.brush_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.life.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.life.resume.respond.brush_off   [23 chars]
    en  It was a long time ago.
    >>  ............................................
    pt  Foi há muito tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `life.resume.brush_off`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `life.resume.brush_off`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.resume.brush_off
WHO    VILLAGER — what the player reads after pressing "It was a long time ago."
       spoken on: conversations.arc.life.resume.respond, button `brush_off`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.brush_off.terminal`: the villager refuses. Subject `life.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.resume.brush_off/1   [43 chars]
    en  ...It was. And you asked me to tell it, so.
    >>  ............................................
    pt  ...Foi. E você me pediu para contar, então.
    >>  ............................................
  dialogue.conversations.life.resume.brush_off/2   [33 chars]
    en  Long ago doesn't mean gone, %1$s.
    >>  ............................................
    pt  Há muito tempo não quer dizer que passou, %1$s.
    >>  ............................................
  dialogue.conversations.life.resume.brush_off/3   [65 chars]
    en  Just so. I'll not bring it up again, if that's what you'd prefer.
    >>  ............................................
    pt  Pois é. Não vou tocar no assunto de novo, se você prefere assim.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.resume.brush_off/1
    en  ...It was long. I knew it while I was telling it, %1$s.
    >>  ............................................
    pt  ...Era longa. Eu sabia enquanto contava, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.life.resume.brush_off/2
    en  Sorry. You asked and I took too much of the evening.
    >>  ............................................
    pt  Desculpe. Você perguntou e eu tomei a noite toda.
    >>  ............................................
  anxious.dialogue.conversations.life.resume.brush_off/3
    en  ...Right. I'll keep it shorter, or keep it entirely.
    >>  ............................................
    pt  ...Certo. Vou encurtar, ou guardar inteira.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.brush_off/1
    en  It was. Old stories are, mostly.
    >>  ............................................
    pt  Era. Histórias velhas são, na maioria.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.brush_off/2
    en  ...Aye. It'll still be there if you ever want the rest.
    >>  ............................................
    pt  ...É. Vai continuar lá se você quiser o resto um dia.
    >>  ............................................
  athletic.dialogue.conversations.life.resume.brush_off/3
    en  Right you are. No harm in stopping halfway.
    >>  ............................................
    pt  Você tem razão. Não tem mal em parar no meio.
    >>  ............................................
  confident.dialogue.conversations.life.resume.brush_off/1
    en  It was. And you asked me to tell it, so.
    >>  ............................................
    pt  Era. E você me pediu pra contar, então.
    >>  ............................................
  confident.dialogue.conversations.life.resume.brush_off/2
    en  Right. Then we'll not go back to it.
    >>  ............................................
    pt  Certo. Então não voltamos a isso.
    >>  ............................................
  confident.dialogue.conversations.life.resume.brush_off/3
    en  ...I'll take that as the end of the story.
    >>  ............................................
    pt  ...Vou tomar isso como o fim da história.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.brush_off/1
    en  It was. And you asked me to tell it, so.
    >>  ............................................
    pt  Era. E você me pediu pra contar, então.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.brush_off/2
    en  Right. Then we'll not go back to it.
    >>  ............................................
    pt  Certo. Então não voltamos a isso.
    >>  ............................................
  crabby.dialogue.conversations.life.resume.brush_off/3
    en  ...I'll take that as the end of the story.
    >>  ............................................
    pt  ...Vou tomar isso como o fim da história.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.brush_off/1
    en  It was, and you asked, %1$s. I'd not have started it otherwise.
    >>  ............................................
    pt  Era, e você perguntou, %1$s. Senão eu não teria começado.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.brush_off/2
    en  Right. I'll stop. You can ask me again another evening if you like.
    >>  ............................................
    pt  Certo. Eu paro. Pode me perguntar de novo outra noite, se quiser.
    >>  ............................................
  extroverted.dialogue.conversations.life.resume.brush_off/3
    en  ...I'd rather have told you badly than not at all, mind.
    >>  ............................................
    pt  ...Mas eu preferia ter contado mal a não ter contado.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.brush_off/1
    en  It was, and you asked, %1$s. I'd not have started it otherwise.
    >>  ............................................
    pt  Era, e você perguntou, %1$s. Senão eu não teria começado.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.brush_off/2
    en  Right. I'll stop. You can ask me again another evening if you like.
    >>  ............................................
    pt  Certo. Eu paro. Pode me perguntar de novo outra noite, se quiser.
    >>  ............................................
  flirty.dialogue.conversations.life.resume.brush_off/3
    en  ...I'd rather have told you badly than not at all, mind.
    >>  ............................................
    pt  ...Mas eu preferia ter contado mal a não ter contado.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.brush_off/1
    en  It was, and you asked, %1$s. I'd not have started it otherwise.
    >>  ............................................
    pt  Era, e você perguntou, %1$s. Senão eu não teria começado.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.brush_off/2
    en  Right. I'll stop. You can ask me again another evening if you like.
    >>  ............................................
    pt  Certo. Eu paro. Pode me perguntar de novo outra noite, se quiser.
    >>  ............................................
  friendly.dialogue.conversations.life.resume.brush_off/3
    en  ...I'd rather have told you badly than not at all, mind.
    >>  ............................................
    pt  ...Mas eu preferia ter contado mal a não ter contado.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.brush_off/1
    en  ...It was long. I knew it while I was telling it, %1$s.
    >>  ............................................
    pt  ...Era longa. Eu sabia enquanto contava, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.brush_off/2
    en  Sorry. You asked and I took too much of the evening.
    >>  ............................................
    pt  Desculpe. Você perguntou e eu tomei a noite toda.
    >>  ............................................
  gloomy.dialogue.conversations.life.resume.brush_off/3
    en  ...Right. I'll keep it shorter, or keep it entirely.
    >>  ............................................
    pt  ...Certo. Vou encurtar, ou guardar inteira.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.brush_off/1
    en  It was. And you asked me to tell it, so.
    >>  ............................................
    pt  Era. E você me pediu pra contar, então.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.brush_off/2
    en  Right. Then we'll not go back to it.
    >>  ............................................
    pt  Certo. Então não voltamos a isso.
    >>  ............................................
  greedy.dialogue.conversations.life.resume.brush_off/3
    en  ...I'll take that as the end of the story.
    >>  ............................................
    pt  ...Vou tomar isso como o fim da história.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.brush_off/1
    en  It was. And you asked me to tell it, so.
    >>  ............................................
    pt  Era. E você me pediu pra contar, então.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.brush_off/2
    en  Right. Then we'll not go back to it.
    >>  ............................................
    pt  Certo. Então não voltamos a isso.
    >>  ............................................
  grumpy.dialogue.conversations.life.resume.brush_off/3
    en  ...I'll take that as the end of the story.
    >>  ............................................
    pt  ...Vou tomar isso como o fim da história.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.brush_off/1
    en  ...It was. You asked.
    >>  ............................................
    pt  ...Era. Você perguntou.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.brush_off/2
    en  Right. I'll stop.
    >>  ............................................
    pt  Certo. Eu paro.
    >>  ............................................
  introverted.dialogue.conversations.life.resume.brush_off/3
    en  ...That's the end of it, then.
    >>  ............................................
    pt  ...Então é o fim.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.brush_off/1
    en  It was. Old stories are, mostly.
    >>  ............................................
    pt  Era. Histórias velhas são, na maioria.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.brush_off/2
    en  ...Aye. It'll still be there if you ever want the rest.
    >>  ............................................
    pt  ...É. Vai continuar lá se você quiser o resto um dia.
    >>  ............................................
  lazy.dialogue.conversations.life.resume.brush_off/3
    en  Right you are. No harm in stopping halfway.
    >>  ............................................
    pt  Você tem razão. Não tem mal em parar no meio.
    >>  ............................................
  odd.dialogue.conversations.life.resume.brush_off/1
    en  ...It was. You asked.
    >>  ............................................
    pt  ...Era. Você perguntou.
    >>  ............................................
  odd.dialogue.conversations.life.resume.brush_off/2
    en  Right. I'll stop.
    >>  ............................................
    pt  Certo. Eu paro.
    >>  ............................................
  odd.dialogue.conversations.life.resume.brush_off/3
    en  ...That's the end of it, then.
    >>  ............................................
    pt  ...Então é o fim.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.brush_off/1
    en  It was. Old stories are, mostly.
    >>  ............................................
    pt  Era. Histórias velhas são, na maioria.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.brush_off/2
    en  ...Aye. It'll still be there if you ever want the rest.
    >>  ............................................
    pt  ...É. Vai continuar lá se você quiser o resto um dia.
    >>  ............................................
  peaceful.dialogue.conversations.life.resume.brush_off/3
    en  Right you are. No harm in stopping halfway.
    >>  ............................................
    pt  Você tem razão. Não tem mal em parar no meio.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.brush_off/1
    en  It was! And you asked. I'd not have volunteered it, %1$s.
    >>  ............................................
    pt  Era! E você perguntou. Eu não teria me oferecido, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.brush_off/2
    en  Right, well. Consider the story shelved.
    >>  ............................................
    pt  Certo, bom. Considere a história arquivada.
    >>  ............................................
  peppy.dialogue.conversations.life.resume.brush_off/3
    en  ...Ha. Fine. I'll find a shorter one for next time.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou achar uma mais curta pra próxima.
    >>  ............................................
  playful.dialogue.conversations.life.resume.brush_off/1
    en  It was! And you asked. I'd not have volunteered it, %1$s.
    >>  ............................................
    pt  Era! E você perguntou. Eu não teria me oferecido, %1$s.
    >>  ............................................
  playful.dialogue.conversations.life.resume.brush_off/2
    en  Right, well. Consider the story shelved.
    >>  ............................................
    pt  Certo, bom. Considere a história arquivada.
    >>  ............................................
  playful.dialogue.conversations.life.resume.brush_off/3
    en  ...Ha. Fine. I'll find a shorter one for next time.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou achar uma mais curta pra próxima.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.brush_off/1
    en  It was. Old stories are, mostly.
    >>  ............................................
    pt  Era. Histórias velhas são, na maioria.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.brush_off/2
    en  ...Aye. It'll still be there if you ever want the rest.
    >>  ............................................
    pt  ...É. Vai continuar lá se você quiser o resto um dia.
    >>  ............................................
  relaxed.dialogue.conversations.life.resume.brush_off/3
    en  Right you are. No harm in stopping halfway.
    >>  ............................................
    pt  Você tem razão. Não tem mal em parar no meio.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.brush_off/1
    en  ...It was long. I knew it while I was telling it, %1$s.
    >>  ............................................
    pt  ...Era longa. Eu sabia enquanto contava, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.brush_off/2
    en  Sorry. You asked and I took too much of the evening.
    >>  ............................................
    pt  Desculpe. Você perguntou e eu tomei a noite toda.
    >>  ............................................
  sensitive.dialogue.conversations.life.resume.brush_off/3
    en  ...Right. I'll keep it shorter, or keep it entirely.
    >>  ............................................
    pt  ...Certo. Vou encurtar, ou guardar inteira.
    >>  ............................................
  shy.dialogue.conversations.life.resume.brush_off/1
    en  ...It was. You asked.
    >>  ............................................
    pt  ...Era. Você perguntou.
    >>  ............................................
  shy.dialogue.conversations.life.resume.brush_off/2
    en  Right. I'll stop.
    >>  ............................................
    pt  Certo. Eu paro.
    >>  ............................................
  shy.dialogue.conversations.life.resume.brush_off/3
    en  ...That's the end of it, then.
    >>  ............................................
    pt  ...Então é o fim.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.brush_off/1
    en  It was! And you asked. I'd not have volunteered it, %1$s.
    >>  ............................................
    pt  Era! E você perguntou. Eu não teria me oferecido, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.brush_off/2
    en  Right, well. Consider the story shelved.
    >>  ............................................
    pt  Certo, bom. Considere a história arquivada.
    >>  ............................................
  upbeat.dialogue.conversations.life.resume.brush_off/3
    en  ...Ha. Fine. I'll find a shorter one for next time.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou achar uma mais curta pra próxima.
    >>  ............................................
  witty.dialogue.conversations.life.resume.brush_off/1
    en  It was! And you asked. I'd not have volunteered it, %1$s.
    >>  ............................................
    pt  Era! E você perguntou. Eu não teria me oferecido, %1$s.
    >>  ............................................
  witty.dialogue.conversations.life.resume.brush_off/2
    en  Right, well. Consider the story shelved.
    >>  ............................................
    pt  Certo, bom. Considere a história arquivada.
    >>  ............................................
  witty.dialogue.conversations.life.resume.brush_off/3
    en  ...Ha. Fine. I'll find a shorter one for next time.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou achar uma mais curta pra próxima.
    >>  ............................................
```

</details>


### Button `leave` — "Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `life.revisit.opens` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.life.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.life.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.life.resume.respond.leave   [13 chars]
    en  Another time.
    >>  ............................................
    pt  Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.resume.leave
WHO    VILLAGER — what the player reads after pressing "Another time."
       spoken on: conversations.arc.life.resume.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.resume.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.resume.leave/1   [37 chars]
    en  Quite. It'll keep — it has this long.
    >>  ............................................
    pt  Exato. Espera — já esperou tanto tempo.
    >>  ............................................
  dialogue.conversations.life.resume.leave/2   [45 chars]
    en  Go on, %1$s. Thank you for coming back to it.
    >>  ............................................
    pt  Pode ir, %1$s. Obrigado por ter voltado ao assunto.
    >>  ............................................
  dialogue.conversations.life.resume.leave/3   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
```

---


## `conversations.scene.life.followup`

**Reached from 4 route(s):** `conversations.scene.life.how_i_came_here.respond` / `ask_about_before`; `conversations.scene.life.how_i_came_here.respond` / `say_glad_they_stayed`; `conversations.scene.life.the_chapter_im_in.respond` / `ask_what_changed`; `conversations.scene.life.the_chapter_im_in.respond` / `say_that_sounds_good`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.life.how_i_came_here.moved` — e.g. "That is a thing you say to somebody who arrived, and I arrived eleven years ago, and it still lands."
- `conversations.scene.life.how_i_came_here.told` — e.g. "North, and further than people assume, and I do not say the name much because saying it makes people ask the next question."
- `conversations.scene.life.the_chapter_im_in.acknowledged` — e.g. "It is one, and nobody puts it on a list of them, and I have decided to put it on mine."
- `conversations.scene.life.the_chapter_im_in.explained` — e.g. "I stopped waiting for the part where it starts. That is genuinely all of it and it took a stupid number of years."


```text
POOL   dialogue key: dialogue.conversations.scene.life.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.life.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.life.followup   [25 chars]
    en  Anything else about that?
    >>  ............................................
    pt  Mais alguma coisa sobre isso?
    >>  ............................................
```


### Button `leave` — "We'll leave the rest for another day."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:life.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.life.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.life.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.life.followup.leave   [37 chars]
    en  We'll leave the rest for another day.
    >>  ............................................
    pt  Deixamos o resto para outro dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.life.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave the rest for another day."
       spoken on: conversations.scene.life.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.scene.leaving`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.life.how_i_came_here.respond / leave; conversations.scene.life.the_chapter_im_in.respond / leave
```

```text
  dialogue.conversations.scene.life.leaving/1   [24 chars]
    en  That is the shape of it.
    >>  ............................................
    pt  É esse o formato.
    >>  ............................................
  dialogue.conversations.scene.life.leaving/2   [28 chars]
    en  There is more, and it keeps.
    >>  ............................................
    pt  Tem mais, e não estraga.
    >>  ............................................
  dialogue.conversations.scene.life.leaving/3   [23 chars]
    en  Right. Enough about me.
    >>  ............................................
    pt  Certo. Chega de falar de mim.
    >>  ............................................
```

---


## `conversations.scene.life.how_i_came_here.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `life`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.life.how_i_came_here` — e.g. "I did not choose this village. I chose to stop, and this is where I was when I did."


```text
POOL   dialogue key: dialogue.conversations.scene.life.how_i_came_here.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.life.how_i_came_here.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.life.how_i_came_here.respond   [18 chars]
    en  How you came here.
    >>  ............................................
    pt  Como você veio parar aqui.
    >>  ............................................
```


### Button `ask_about_before` — "Where were you before?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `life.how_i_came_here.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.life.how_i_came_here.ask_about_before` — accepted phrasings: "where were you before"; "where were you before"; "what was there before this"
  - the message must contain one of: `before`
  - scored words: `before`(1.8), `where`(0.8), `were`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.life.how_i_came_here.respond.ask_about_before
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.life.how_i_came_here.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.life.how_i_came_here.respond.ask_about_before   [22 chars]
    en  Where were you before?
    >>  ............................................
    pt  Onde você estava antes?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `life.origin`)_
- Does: session `turn`
- Then opens: `conversations.scene.life.followup`
- …where the player's next choices will be: "We'll leave the rest for another day."

```text
POOL   dialogue key: dialogue.conversations.scene.life.how_i_came_here.told
WHO    VILLAGER — what the player reads after pressing "Where were you before?"
       spoken on: conversations.scene.life.how_i_came_here.respond, button `ask_about_before`
       leaves the player on: conversations.scene.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.how_i_came_here.open.told`: the villager reminisces. Subject `life.origin`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:life` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.life.how_i_came_here.told/1   [123 chars]
    en  North, and further than people assume, and I do not say the name much because saying it makes people ask the next question.
    >>  ............................................
    pt  No norte, e mais longe do que as pessoas imaginam, e eu não digo o nome muito porque dizer faz as pessoas fazerem a próxima pergunta.
    >>  ............................................
  dialogue.conversations.scene.life.how_i_came_here.told/2   [119 chars]
    en  Two other places, neither for long. It is only in the third place that you find out whether you are the sort who stays.
    >>  ............................................
    pt  Dois outros lugares, nenhum por muito tempo. Só no terceiro lugar você descobre se é do tipo que fica.
    >>  ............................................
  dialogue.conversations.scene.life.how_i_came_here.told/3   [120 chars]
    en  Somewhere with better bread and worse neighbours. I have thought about the trade a great deal and I would make it again.
    >>  ............................................
    pt  Um lugar com pão melhor e vizinhos piores. Já pensei muito nessa troca e eu a faria de novo.
    >>  ............................................
```


### Button `say_glad_they_stayed` — "This place is better for you being here."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `life.how_i_came_here.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.life.how_i_came_here.say_glad_they_stayed` — accepted phrasings: "this place is better for you being here"; "this place is better for you being here"; "the village is better with you in it"
  - the message must contain one of: `better`, `village`, `place`
  - scored words: `better`(1.8), `village`(1.8), `place`(1.8), `being`(0.8), `here`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.life.how_i_came_here.respond.say_glad_they_stayed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.life.how_i_came_here.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.life.how_i_came_here.respond.say_glad_they_stayed   [40 chars]
    en  This place is better for you being here.
    >>  ............................................
    pt  Este lugar é melhor com você aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.life.origin.welcomed`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `life.origin`)_
- Does: session `turn`
- Then opens: `conversations.scene.life.followup`
- …where the player's next choices will be: "We'll leave the rest for another day."

```text
POOL   dialogue key: dialogue.conversations.scene.life.how_i_came_here.moved
WHO    VILLAGER — what the player reads after pressing "This place is better for you being here."
       spoken on: conversations.scene.life.how_i_came_here.respond, button `say_glad_they_stayed`
       leaves the player on: conversations.scene.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.how_i_came_here.open.moved`: the villager accepts. Subject `life.origin`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:life` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.life.how_i_came_here.moved/1   [100 chars]
    en  That is a thing you say to somebody who arrived, and I arrived eleven years ago, and it still lands.
    >>  ............................................
    pt  É uma coisa que se diz a quem chegou, e eu cheguei há onze anos, e ainda assim acerta.
    >>  ............................................
  dialogue.conversations.scene.life.how_i_came_here.moved/2   [94 chars]
    en  Thank you. I have been useful here for a long time and useful is not the same word as welcome.
    >>  ............................................
    pt  Obrigada. Faz muito tempo que eu sou útil aqui, e útil não é a mesma palavra que bem-vinda.
    >>  ............................................
  dialogue.conversations.scene.life.how_i_came_here.moved/3   [112 chars]
    en  I will take that and I will be awkward about it, which is what happens when a sentence goes somewhere unguarded.
    >>  ............................................
    pt  Vou aceitar e vou ficar sem jeito, que é o que acontece quando uma frase chega num lugar desprotegido.
    >>  ............................................
```


### Button `leave` — "Thank you for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `life.how_i_came_here.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.life.how_i_came_here.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.life.how_i_came_here.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.life.how_i_came_here.respond.leave   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.life.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.scene.life.how_i_came_here.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.scene.leaving`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.life.followup / leave; conversations.scene.life.the_chapter_im_in.respond / leave
```

> Written out in full under **`conversations.scene.life.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.life.the_chapter_im_in.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `life`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.life.the_chapter_im_in` — e.g. "I am in the middle of something and I will not know what it was until it is over. That is most of a life, I think."


```text
POOL   dialogue key: dialogue.conversations.scene.life.the_chapter_im_in.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.life.the_chapter_im_in.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.life.the_chapter_im_in.respond   [16 chars]
    en  Where you're at.
    >>  ............................................
    pt  Onde você está.
    >>  ............................................
```


### Button `ask_what_changed` — "What changed in four years?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `life.the_chapter_im_in.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.life.the_chapter_im_in.ask_what_changed` — accepted phrasings: "what changed in four years"; "what changed in four years"; "what is different now"
  - the message must contain one of: `changed`, `different`
  - scored words: `changed`(1.8), `different`(1.8), `four`(0.8), `years`(0.8), `now`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.life.the_chapter_im_in.respond.ask_what_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.life.the_chapter_im_in.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.life.the_chapter_im_in.respond.ask_what_changed   [27 chars]
    en  What changed in four years?
    >>  ............................................
    pt  O que mudou em quatro anos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `life.now`)_
- Does: session `turn`
- Then opens: `conversations.scene.life.followup`
- …where the player's next choices will be: "We'll leave the rest for another day."

```text
POOL   dialogue key: dialogue.conversations.scene.life.the_chapter_im_in.explained
WHO    VILLAGER — what the player reads after pressing "What changed in four years?"
       spoken on: conversations.scene.life.the_chapter_im_in.respond, button `ask_what_changed`
       leaves the player on: conversations.scene.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.the_chapter_im_in.open.explained`: the villager explains. Subject `life.now`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:life` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.life.the_chapter_im_in.explained/1   [113 chars]
    en  I stopped waiting for the part where it starts. That is genuinely all of it and it took a stupid number of years.
    >>  ............................................
    pt  Parei de esperar a parte em que começa. É genuinamente tudo, e levou um número idiota de anos.
    >>  ............................................
  dialogue.conversations.scene.life.the_chapter_im_in.explained/2   [89 chars]
    en  One person stayed. Everything downstream of that is different and none of it is dramatic.
    >>  ............................................
    pt  Uma pessoa ficou. Tudo depois disso é diferente e nada é dramático.
    >>  ............................................
  dialogue.conversations.scene.life.the_chapter_im_in.explained/3   [105 chars]
    en  I got good at one thing. Being reliably good at one thing turns out to reorganise a whole life around it.
    >>  ............................................
    pt  Fiquei boa em uma coisa. Ser confiavelmente boa em uma coisa acaba reorganizando uma vida inteira em torno disso.
    >>  ............................................
```


### Button `say_that_sounds_good` — "Steady sounds like an achievement."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `life.the_chapter_im_in.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.life.the_chapter_im_in.say_that_sounds_good` — accepted phrasings: "steady sounds like an achievement"; "steady sounds like an achievement"; "that steadiness is worth something"
  - the message must contain one of: `steady`, `steadiness`, `achievement`
  - scored words: `steady`(1.8), `steadiness`(1.8), `achievement`(1.8), `sounds`(0.8), `like`(0.8), `worth`(0.8), `something`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.life.the_chapter_im_in.respond.say_that_sounds_good
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.life.the_chapter_im_in.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.life.the_chapter_im_in.respond.say_that_sounds_good   [34 chars]
    en  Steady sounds like an achievement.
    >>  ............................................
    pt  Estável parece uma conquista.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `life.now`)_
- Does: session `turn`
- Then opens: `conversations.scene.life.followup`
- …where the player's next choices will be: "We'll leave the rest for another day."

```text
POOL   dialogue key: dialogue.conversations.scene.life.the_chapter_im_in.acknowledged
WHO    VILLAGER — what the player reads after pressing "Steady sounds like an achievement."
       spoken on: conversations.scene.life.the_chapter_im_in.respond, button `say_that_sounds_good`
       leaves the player on: conversations.scene.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.the_chapter_im_in.open.acknowledged`: the villager accepts. Subject `life.now`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:life` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.life.the_chapter_im_in.acknowledged/1   [86 chars]
    en  It is one, and nobody puts it on a list of them, and I have decided to put it on mine.
    >>  ............................................
    pt  É uma, e ninguém coloca isso numa lista de conquistas, e eu decidi colocar na minha.
    >>  ............................................
  dialogue.conversations.scene.life.the_chapter_im_in.acknowledged/2   [100 chars]
    en  Thank you. I would have found that sentence insulting at twenty-two, which is how I know it is true.
    >>  ............................................
    pt  Obrigada. Eu teria achado essa frase ofensiva aos vinte e dois, e é assim que eu sei que é verdade.
    >>  ............................................
  dialogue.conversations.scene.life.the_chapter_im_in.acknowledged/3   [106 chars]
    en  It is an achievement that has to be redone every morning, which is the sort nobody hands out anything for.
    >>  ............................................
    pt  É uma conquista que precisa ser refeita toda manhã, do tipo pelo qual ninguém entrega nada.
    >>  ............................................
```


### Button `leave` — "Thank you for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `life.the_chapter_im_in.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.life.the_chapter_im_in.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.life.the_chapter_im_in.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.life.the_chapter_im_in.respond.leave   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.life.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.scene.life.the_chapter_im_in.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.scene.leaving`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.life.followup / leave; conversations.scene.life.how_i_came_here.respond / leave
```

> Written out in full under **`conversations.scene.life.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.life.close`

**Reached from 6 route(s):** `conversations.arc.life.resume.followup` / `thank`; `conversations.arc.life.resume.followup` / `connect`; `conversations.arc.life.resume.followup` / `probe`; `conversations.topic.life.followup` / `follow_thread`; `conversations.topic.life.followup` / `compare_own`; `conversations.topic.life.followup` / `joke`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.life.followup.compare_own` — e.g. "Did it? Then you'll know why I've never made much of it."
- `conversations.life.followup.follow_thread` — e.g. "...Alright. You want the next part. Huh. Right, then. Here it is."
- `conversations.life.followup.joke` — e.g. "Ha! I have, actually. He's had one long meeting since 1247 and I've had all of that."
- `conversations.life.resume.followup.connect` — e.g. "...It does, doesn't it. Huh. I've never had those two things stood next to each other before."
- `conversations.life.resume.followup.probe` — e.g. "There's always more left out. That's what leaving out means. ...Let it lie."
- `conversations.life.resume.followup.thank` — e.g. "Glad. Hm. I'd braced for 'that's a lot' and got 'glad' instead."


```text
POOL   dialogue key: dialogue.conversations.topic.life.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.life.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.life.close   [22 chars]
    en  Anyway. It's said now.
    >>  ............................................
    pt  Enfim. Já está dito.
    >>  ............................................
```


### Button `thank` — "Thank you for telling me."

*stance family `candor` · tone `gentle` · answers the beat(s) `life.followup.compare_own.to.life`, `life.followup.follow_thread.to.life`, `life.followup.joke.to.life`, `life.resume.followup.connect.to.life`, `life.resume.followup.probe.to.life`, `life.resume.followup.thank.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.close.thank` — accepted phrasings: "thank you for telling me"; "thank you for the story"; "i am grateful you told me"
  - the message must contain one of: `thank`, `telling`
  - scored words: `thank`(1.5), `telling`(1.2), `story`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.life.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.close.thank   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.close.thank`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, trust +1  _(recorded under topic `life.close.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.close.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.life.close, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.close.thank.terminal`: the villager accepts. Subject `life.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.close.thank/1   [85 chars]
    en  Thank me for what — for being old? ...No, I know what you mean. You're welcome, %1$s.
    >>  ............................................
    pt  Me agradecer pelo quê — por ser velho? ...Não, eu sei o que você quis dizer. De nada, %1$s.
    >>  ............................................
  dialogue.conversations.life.close.thank/2   [83 chars]
    en  It's only what happened. But aye — not everyone gets told, so take the thanks back.
    >>  ............................................
    pt  É só o que aconteceu. Mas é — nem todo mundo é informado, então devolvo o obrigado.
    >>  ............................................
  dialogue.conversations.life.close.thank/3   [70 chars]
    en  Hm. It's a strange thing to be thanked for a life. I'll take it, mind.
    >>  ............................................
    pt  Hm. É estranho ser agradecido por uma vida. Mas eu aceito.
    >>  ............................................
```


### Button `say_means` — "That took something to say."

*stance family `candor` · tone `gentle` · answers the beat(s) `life.followup.compare_own.to.life`, `life.followup.follow_thread.to.life`, `life.followup.joke.to.life`, `life.resume.followup.connect.to.life`, `life.resume.followup.probe.to.life`, `life.resume.followup.thank.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.close.say_means` — accepted phrasings: "that took something to say"; "that was brave of you"; "that took courage"
  - the message must contain one of: `took`, `brave`, `courage`
  - scored words: `took`(1.5), `brave`(1.2), `courage`(1.5), `story`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.life.close.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.close.say_means   [27 chars]
    en  That took something to say.
    >>  ............................................
    pt  Falar isso exigiu coragem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.close.say_means`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `life.close.say_means`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.close.say_means
WHO    VILLAGER — what the player reads after pressing "That took something to say."
       spoken on: conversations.topic.life.close, button `say_means`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.close.say_means.terminal`: the villager accepts. Subject `life.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.close.say_means/1   [62 chars]
    en  ...It did. You don't lay the whole thing out for just anybody.
    >>  ............................................
    pt  ...Exigiu. A gente não põe a coisa toda na mesa para qualquer um.
    >>  ............................................
  dialogue.conversations.life.close.say_means/2   [81 chars]
    en  Telling it in order is the hard part. You keep wanting to defend the middle bits.
    >>  ............................................
    pt  Contar em ordem é a parte difícil. Dá vontade de defender o meio o tempo todo.
    >>  ............................................
  dialogue.conversations.life.close.say_means/3   [77 chars]
    en  You noticed that. It's easy to hear a life and think it's only talking, %1$s.
    >>  ............................................
    pt  Você reparou. É fácil ouvir uma vida e achar que é só conversa, %1$s.
    >>  ............................................
```


### Button `confide` — "Here's one of mine, then."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `life.followup.compare_own.to.life`, `life.followup.follow_thread.to.life`, `life.followup.joke.to.life`, `life.resume.followup.connect.to.life`, `life.resume.followup.probe.to.life`, `life.resume.followup.thank.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.close.confide` — accepted phrasings: "here is one of mine then"; "let me tell you one of mine"; "i have one of my own"
  - the message must contain one of: `mine`
  - scored words: `mine`(1.5), `one`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.life.close.confide
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.close.confide   [25 chars]
    en  Here's one of mine, then.
    >>  ............................................
    pt  Aqui vai uma minha, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.close.confide`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, familiarity +4  _(recorded under topic `life.close.confide`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.close.confide
WHO    VILLAGER — what the player reads after pressing "Here's one of mine, then."
       spoken on: conversations.topic.life.close, button `confide`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.close.confide.terminal`: the villager discloses. Subject `life.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.close.confide/1   [87 chars]
    en  ...You didn't have to do that. Trading is better than being asked, though. Much better.
    >>  ............................................
    pt  ...Você não precisava. Mas trocar é melhor do que ser perguntado. Muito melhor.
    >>  ............................................
  dialogue.conversations.life.close.confide/2   [64 chars]
    en  Now we're both stood out in it. That's a fairer way round, %1$s.
    >>  ............................................
    pt  Agora nós dois estamos expostos. É um jeito mais justo, %1$s.
    >>  ............................................
  dialogue.conversations.life.close.confide/3   [60 chars]
    en  Hah. You went first on the second one. I'll not forget that.
    >>  ............................................
    pt  Rá. Você foi primeiro na segunda. Não vou esquecer disso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.close.confide/1
    en  ...You didn't have to do that. Trading is better than being asked, %1$s. Much better.
    >>  ............................................
    pt  ...Você não precisava fazer isso. Trocar é melhor que ser perguntado, %1$s. Muito melhor.
    >>  ............................................
  anxious.dialogue.conversations.life.close.confide/2
    en  You gave me one back. I'd been feeling exposed and now I'm not, and that was deliberate of you.
    >>  ............................................
    pt  Você me deu um de volta. Eu estava me sentindo exposto e agora não, e você fez de propósito.
    >>  ............................................
  anxious.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I don't think you know how much easier you just made that.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Acho que você não sabe o quanto facilitou.
    >>  ............................................
  athletic.dialogue.conversations.life.close.confide/1
    en  You didn't have to. Trading is better than being asked, and it lasts better too.
    >>  ............................................
    pt  Você não precisava. Trocar é melhor que ser perguntado, e dura mais também.
    >>  ............................................
  athletic.dialogue.conversations.life.close.confide/2
    en  You gave me one back. That's how these things stay even over years.
    >>  ............................................
    pt  Você me deu um de volta. É assim que essas coisas ficam niveladas ao longo dos anos.
    >>  ............................................
  athletic.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. It's slower and it holds, which is most of what I look for.
    >>  ............................................
    pt  Trocar é melhor que perguntar. É mais lento e se mantém, que é quase tudo que eu procuro.
    >>  ............................................
  confident.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that. Trading is better than being asked, though.
    >>  ............................................
    pt  Você não precisava fazer isso. Mas trocar é melhor que ser perguntado.
    >>  ............................................
  confident.dialogue.conversations.life.close.confide/2
    en  Right. You've given me one back. That levels it, and I prefer level.
    >>  ............................................
    pt  Certo. Você me deu um de volta. Isso nivela, e eu prefiro nivelado.
    >>  ............................................
  confident.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I'd not have said so an hour ago.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Eu não teria dito isso uma hora atrás.
    >>  ............................................
  crabby.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that. Trading is better than being asked, though.
    >>  ............................................
    pt  Você não precisava fazer isso. Mas trocar é melhor que ser perguntado.
    >>  ............................................
  crabby.dialogue.conversations.life.close.confide/2
    en  Right. You've given me one back. That levels it, and I prefer level.
    >>  ............................................
    pt  Certo. Você me deu um de volta. Isso nivela, e eu prefiro nivelado.
    >>  ............................................
  crabby.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I'd not have said so an hour ago.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Eu não teria dito isso uma hora atrás.
    >>  ............................................
  extroverted.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that, %1$s. Trading is better than being asked, though. Much better.
    >>  ............................................
    pt  Você não precisava fazer isso, %1$s. Mas trocar é melhor que ser perguntado. Muito melhor.
    >>  ............................................
  extroverted.dialogue.conversations.life.close.confide/2
    en  You gave me one back. That's the kindest thing anyone's done in this conversation.
    >>  ............................................
    pt  Você me deu um de volta. É a coisa mais gentil que alguém fez nesta conversa.
    >>  ............................................
  extroverted.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I'll remember that you knew that without being told.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Vou lembrar que você soube sem que dissessem.
    >>  ............................................
  flirty.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that, %1$s. Trading is better than being asked, though. Much better.
    >>  ............................................
    pt  Você não precisava fazer isso, %1$s. Mas trocar é melhor que ser perguntado. Muito melhor.
    >>  ............................................
  flirty.dialogue.conversations.life.close.confide/2
    en  You gave me one back. That's the kindest thing anyone's done in this conversation.
    >>  ............................................
    pt  Você me deu um de volta. É a coisa mais gentil que alguém fez nesta conversa.
    >>  ............................................
  flirty.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I'll remember that you knew that without being told.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Vou lembrar que você soube sem que dissessem.
    >>  ............................................
  friendly.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that, %1$s. Trading is better than being asked, though. Much better.
    >>  ............................................
    pt  Você não precisava fazer isso, %1$s. Mas trocar é melhor que ser perguntado. Muito melhor.
    >>  ............................................
  friendly.dialogue.conversations.life.close.confide/2
    en  You gave me one back. That's the kindest thing anyone's done in this conversation.
    >>  ............................................
    pt  Você me deu um de volta. É a coisa mais gentil que alguém fez nesta conversa.
    >>  ............................................
  friendly.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I'll remember that you knew that without being told.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Vou lembrar que você soube sem que dissessem.
    >>  ............................................
  gloomy.dialogue.conversations.life.close.confide/1
    en  ...You didn't have to do that. Trading is better than being asked, %1$s. Much better.
    >>  ............................................
    pt  ...Você não precisava fazer isso. Trocar é melhor que ser perguntado, %1$s. Muito melhor.
    >>  ............................................
  gloomy.dialogue.conversations.life.close.confide/2
    en  You gave me one back. I'd been feeling exposed and now I'm not, and that was deliberate of you.
    >>  ............................................
    pt  Você me deu um de volta. Eu estava me sentindo exposto e agora não, e você fez de propósito.
    >>  ............................................
  gloomy.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I don't think you know how much easier you just made that.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Acho que você não sabe o quanto facilitou.
    >>  ............................................
  greedy.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that. Trading is better than being asked, though.
    >>  ............................................
    pt  Você não precisava fazer isso. Mas trocar é melhor que ser perguntado.
    >>  ............................................
  greedy.dialogue.conversations.life.close.confide/2
    en  Right. You've given me one back. That levels it, and I prefer level.
    >>  ............................................
    pt  Certo. Você me deu um de volta. Isso nivela, e eu prefiro nivelado.
    >>  ............................................
  greedy.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I'd not have said so an hour ago.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Eu não teria dito isso uma hora atrás.
    >>  ............................................
  grumpy.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that. Trading is better than being asked, though.
    >>  ............................................
    pt  Você não precisava fazer isso. Mas trocar é melhor que ser perguntado.
    >>  ............................................
  grumpy.dialogue.conversations.life.close.confide/2
    en  Right. You've given me one back. That levels it, and I prefer level.
    >>  ............................................
    pt  Certo. Você me deu um de volta. Isso nivela, e eu prefiro nivelado.
    >>  ............................................
  grumpy.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I'd not have said so an hour ago.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Eu não teria dito isso uma hora atrás.
    >>  ............................................
  introverted.dialogue.conversations.life.close.confide/1
    en  ...You didn't have to do that.
    >>  ............................................
    pt  ...Você não precisava fazer isso.
    >>  ............................................
  introverted.dialogue.conversations.life.close.confide/2
    en  Trading is better than being asked.
    >>  ............................................
    pt  Trocar é melhor que ser perguntado.
    >>  ............................................
  introverted.dialogue.conversations.life.close.confide/3
    en  ...Right. That's evened it.
    >>  ............................................
    pt  ...Certo. Isso nivelou.
    >>  ............................................
  lazy.dialogue.conversations.life.close.confide/1
    en  You didn't have to. Trading is better than being asked, and it lasts better too.
    >>  ............................................
    pt  Você não precisava. Trocar é melhor que ser perguntado, e dura mais também.
    >>  ............................................
  lazy.dialogue.conversations.life.close.confide/2
    en  You gave me one back. That's how these things stay even over years.
    >>  ............................................
    pt  Você me deu um de volta. É assim que essas coisas ficam niveladas ao longo dos anos.
    >>  ............................................
  lazy.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. It's slower and it holds, which is most of what I look for.
    >>  ............................................
    pt  Trocar é melhor que perguntar. É mais lento e se mantém, que é quase tudo que eu procuro.
    >>  ............................................
  odd.dialogue.conversations.life.close.confide/1
    en  ...You didn't have to do that.
    >>  ............................................
    pt  ...Você não precisava fazer isso.
    >>  ............................................
  odd.dialogue.conversations.life.close.confide/2
    en  Trading is better than being asked.
    >>  ............................................
    pt  Trocar é melhor que ser perguntado.
    >>  ............................................
  odd.dialogue.conversations.life.close.confide/3
    en  ...Right. That's evened it.
    >>  ............................................
    pt  ...Certo. Isso nivelou.
    >>  ............................................
  peaceful.dialogue.conversations.life.close.confide/1
    en  You didn't have to. Trading is better than being asked, and it lasts better too.
    >>  ............................................
    pt  Você não precisava. Trocar é melhor que ser perguntado, e dura mais também.
    >>  ............................................
  peaceful.dialogue.conversations.life.close.confide/2
    en  You gave me one back. That's how these things stay even over years.
    >>  ............................................
    pt  Você me deu um de volta. É assim que essas coisas ficam niveladas ao longo dos anos.
    >>  ............................................
  peaceful.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. It's slower and it holds, which is most of what I look for.
    >>  ............................................
    pt  Trocar é melhor que perguntar. É mais lento e se mantém, que é quase tudo que eu procuro.
    >>  ............................................
  peppy.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that! Trading is much better than being asked, though. Much.
    >>  ............................................
    pt  Você não precisava fazer isso! Mas trocar é muito melhor que ser perguntado. Muito.
    >>  ............................................
  peppy.dialogue.conversations.life.close.confide/2
    en  You've given me one back. Now we're even and I find that enormously comfortable.
    >>  ............................................
    pt  Você me deu um de volta. Agora estamos quites e eu acho isso imensamente confortável.
    >>  ............................................
  peppy.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. Nobody tells you that and it turns out to be the whole trick.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Ninguém te conta e acaba sendo todo o truque.
    >>  ............................................
  playful.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that! Trading is much better than being asked, though. Much.
    >>  ............................................
    pt  Você não precisava fazer isso! Mas trocar é muito melhor que ser perguntado. Muito.
    >>  ............................................
  playful.dialogue.conversations.life.close.confide/2
    en  You've given me one back. Now we're even and I find that enormously comfortable.
    >>  ............................................
    pt  Você me deu um de volta. Agora estamos quites e eu acho isso imensamente confortável.
    >>  ............................................
  playful.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. Nobody tells you that and it turns out to be the whole trick.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Ninguém te conta e acaba sendo todo o truque.
    >>  ............................................
  relaxed.dialogue.conversations.life.close.confide/1
    en  You didn't have to. Trading is better than being asked, and it lasts better too.
    >>  ............................................
    pt  Você não precisava. Trocar é melhor que ser perguntado, e dura mais também.
    >>  ............................................
  relaxed.dialogue.conversations.life.close.confide/2
    en  You gave me one back. That's how these things stay even over years.
    >>  ............................................
    pt  Você me deu um de volta. É assim que essas coisas ficam niveladas ao longo dos anos.
    >>  ............................................
  relaxed.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. It's slower and it holds, which is most of what I look for.
    >>  ............................................
    pt  Trocar é melhor que perguntar. É mais lento e se mantém, que é quase tudo que eu procuro.
    >>  ............................................
  sensitive.dialogue.conversations.life.close.confide/1
    en  ...You didn't have to do that. Trading is better than being asked, %1$s. Much better.
    >>  ............................................
    pt  ...Você não precisava fazer isso. Trocar é melhor que ser perguntado, %1$s. Muito melhor.
    >>  ............................................
  sensitive.dialogue.conversations.life.close.confide/2
    en  You gave me one back. I'd been feeling exposed and now I'm not, and that was deliberate of you.
    >>  ............................................
    pt  Você me deu um de volta. Eu estava me sentindo exposto e agora não, e você fez de propósito.
    >>  ............................................
  sensitive.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. I don't think you know how much easier you just made that.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Acho que você não sabe o quanto facilitou.
    >>  ............................................
  shy.dialogue.conversations.life.close.confide/1
    en  ...You didn't have to do that.
    >>  ............................................
    pt  ...Você não precisava fazer isso.
    >>  ............................................
  shy.dialogue.conversations.life.close.confide/2
    en  Trading is better than being asked.
    >>  ............................................
    pt  Trocar é melhor que ser perguntado.
    >>  ............................................
  shy.dialogue.conversations.life.close.confide/3
    en  ...Right. That's evened it.
    >>  ............................................
    pt  ...Certo. Isso nivelou.
    >>  ............................................
  upbeat.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that! Trading is much better than being asked, though. Much.
    >>  ............................................
    pt  Você não precisava fazer isso! Mas trocar é muito melhor que ser perguntado. Muito.
    >>  ............................................
  upbeat.dialogue.conversations.life.close.confide/2
    en  You've given me one back. Now we're even and I find that enormously comfortable.
    >>  ............................................
    pt  Você me deu um de volta. Agora estamos quites e eu acho isso imensamente confortável.
    >>  ............................................
  upbeat.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. Nobody tells you that and it turns out to be the whole trick.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Ninguém te conta e acaba sendo todo o truque.
    >>  ............................................
  witty.dialogue.conversations.life.close.confide/1
    en  You didn't have to do that! Trading is much better than being asked, though. Much.
    >>  ............................................
    pt  Você não precisava fazer isso! Mas trocar é muito melhor que ser perguntado. Muito.
    >>  ............................................
  witty.dialogue.conversations.life.close.confide/2
    en  You've given me one back. Now we're even and I find that enormously comfortable.
    >>  ............................................
    pt  Você me deu um de volta. Agora estamos quites e eu acho isso imensamente confortável.
    >>  ............................................
  witty.dialogue.conversations.life.close.confide/3
    en  Trading beats asking. Nobody tells you that and it turns out to be the whole trick.
    >>  ............................................
    pt  Trocar é melhor que perguntar. Ninguém te conta e acaba sendo todo o truque.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `life.followup.compare_own.to.life`, `life.followup.follow_thread.to.life`, `life.followup.joke.to.life`, `life.resume.followup.connect.to.life`, `life.resume.followup.probe.to.life`, `life.resume.followup.thank.to.life` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.life.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.close.leave   [16 chars]
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
POOL   dialogue key: dialogue.conversations.life.close.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.life.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.close.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.close.leave/1   [26 chars]
    en  Aye. Go on, and thank you.
    >>  ............................................
    pt  Tá. Pode ir, e obrigado.
    >>  ............................................
  dialogue.conversations.life.close.leave/2   [34 chars]
    en  Right. Enough of that for one day.
    >>  ............................................
    pt  Certo. Já chega disso por um dia.
    >>  ............................................
  dialogue.conversations.life.close.leave/3   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
```

---


## `conversations.topic.life.followup`

**Reached from 5 route(s):** `conversations.topic.life.respond` / `ask_which`; `conversations.topic.life.respond` / `ask_which`; `conversations.topic.life.respond` / `empathise`; `conversations.topic.life.respond` / `empathise`; `conversations.topic.life.respond` / `no_words`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.life.ask_which.low` — e.g. "Which part matters. ...Today, the part where it stopped going the way I planned."
- `conversations.life.no_words` — e.g. "...Good. Nobody should have anything ready for a thing like that. Sit there a minute instead."
- `conversations.life.respond.ask_which` — e.g. "Which part. Nobody asks that — they just want the whole thing at once."
- `conversations.life.respond.empathise` — e.g. "It wasn't. Thank you for saying so instead of telling me it built character."
- `conversations.life.respond.empathise.longknown` — e.g. "You'd know. You've been about long enough to have watched half of it happen."


```text
POOL   dialogue key: dialogue.conversations.topic.life.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.life.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.life.followup   [28 chars]
    en  So that's where I came from.
    >>  ............................................
    pt  Então é de lá que eu venho.
    >>  ............................................
```


### Button `follow_thread` — "Tell me more about that part."

*stance family `curiosity` · tone `plain` · answers the beat(s) `life.ask_which.low.to.life`, `life.no_words.to.life`, `life.respond.ask_which.to.life`, `life.respond.empathise.longknown.to.life`, `life.respond.empathise.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.followup.follow_thread` — accepted phrasings: "tell me more about that part"; "continue that part"; "more about that bit"
  - the message must contain one of: `more`, `part`, `continue`
  - scored words: `more`(1.2), `part`(1.2), `continue`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.life.followup.follow_thread
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.followup.follow_thread   [29 chars]
    en  Tell me more about that part.
    >>  ............................................
    pt  Me conta mais sobre essa parte.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `life.followup.follow_thread`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +5, familiarity +4  _(recorded under topic `life.followup.follow_thread`)_
- Does: arc `life` — advance to stage 1
- Does: milestone `life.chapter` set (fires once, ever)
- Then opens: `conversations.topic.life.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Here's one of mine, then." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.life.followup.follow_thread
WHO    VILLAGER — what the player reads after pressing "Tell me more about that part."
       spoken on: conversations.topic.life.followup, button `follow_thread`
       leaves the player on: conversations.topic.life.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.followup.follow_thread.to.life`: the villager accepts. Subject `life`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.followup.follow_thread/1   [65 chars]
    en  ...Alright. You want the next part. Huh. Right, then. Here it is.
    >>  ............................................
    pt  ...Tudo bem. Você quer a próxima parte. Hm. Certo, então. Lá vai.
    >>  ............................................
  dialogue.conversations.life.followup.follow_thread/2   [67 chars]
    en  You're the first to ask twice. That's how you get the real version.
    >>  ............................................
    pt  Você é o primeiro a perguntar duas vezes. É assim que se ouve a versão real.
    >>  ............................................
  dialogue.conversations.life.followup.follow_thread/3   [68 chars]
    en  Then you'll hear the bit I keep back. Don't make me regret it, %1$s.
    >>  ............................................
    pt  Então você vai ouvir a parte que eu guardo. Não me faça me arrepender, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright. You want the next part, %1$s. Nobody has ever wanted the next part.
    >>  ............................................
    pt  ...Está bem. Você quer a próxima parte, %1$s. Ninguém nunca quis a próxima parte.
    >>  ............................................
  anxious.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Give me a moment to find where it starts — I don't tell this bit.
    >>  ............................................
    pt  A próxima parte. Me dê um momento pra achar onde começa — eu não conto essa parte.
    >>  ............................................
  anxious.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. I'll try. If I stop halfway, that's not you, that's the part.
    >>  ............................................
    pt  Você quer mais. Vou tentar. Se eu parar no meio, não é você, é a parte.
    >>  ............................................
  athletic.dialogue.conversations.life.followup.follow_thread/1
    en  Alright. You want the next part. Sit down; it's not a short one.
    >>  ............................................
    pt  Está bem. Você quer a próxima parte. Sente-se; não é curta.
    >>  ............................................
  athletic.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. It goes slowly and there's no shorter version worth having.
    >>  ............................................
    pt  A próxima parte. Vai devagar e não há versão mais curta que valha.
    >>  ............................................
  athletic.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Fine. It'll take the evening and the evening is long.
    >>  ............................................
    pt  Você quer mais. Tudo bem. Vai levar a noite e a noite é longa.
    >>  ............................................
  confident.dialogue.conversations.life.followup.follow_thread/1
    en  Alright. You want the next part. Right, then. Here it is.
    >>  ............................................
    pt  Está bem. Você quer a próxima parte. Certo, então. Aqui vai.
    >>  ............................................
  confident.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Nobody asks for the next part. Sit down.
    >>  ............................................
    pt  A próxima parte. Ninguém pede a próxima parte. Sente-se.
    >>  ............................................
  confident.dialogue.conversations.life.followup.follow_thread/3
    en  You want more of it. Fine. It gets worse before it gets ordinary.
    >>  ............................................
    pt  Você quer mais. Tudo bem. Piora antes de ficar comum.
    >>  ............................................
  crabby.dialogue.conversations.life.followup.follow_thread/1
    en  Alright. You want the next part. Right, then. Here it is.
    >>  ............................................
    pt  Está bem. Você quer a próxima parte. Certo, então. Aqui vai.
    >>  ............................................
  crabby.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Nobody asks for the next part. Sit down.
    >>  ............................................
    pt  A próxima parte. Ninguém pede a próxima parte. Sente-se.
    >>  ............................................
  crabby.dialogue.conversations.life.followup.follow_thread/3
    en  You want more of it. Fine. It gets worse before it gets ordinary.
    >>  ............................................
    pt  Você quer mais. Tudo bem. Piora antes de ficar comum.
    >>  ............................................
  extroverted.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright, %1$s. You want the next part. Right, then. Here it is.
    >>  ............................................
    pt  ...Está bem, %1$s. Você quer a próxima parte. Certo, então. Aqui vai.
    >>  ............................................
  extroverted.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Sit properly — this one takes a while and I'd rather not rush it.
    >>  ............................................
    pt  A próxima parte. Sente-se direito — essa leva um tempo e eu prefiro não apressar.
    >>  ............................................
  extroverted.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Nobody wants more. I'll give you the whole of it, then.
    >>  ............................................
    pt  Você quer mais. Ninguém quer mais. Então eu te dou tudo.
    >>  ............................................
  flirty.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright, %1$s. You want the next part. Right, then. Here it is.
    >>  ............................................
    pt  ...Está bem, %1$s. Você quer a próxima parte. Certo, então. Aqui vai.
    >>  ............................................
  flirty.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Sit properly — this one takes a while and I'd rather not rush it.
    >>  ............................................
    pt  A próxima parte. Sente-se direito — essa leva um tempo e eu prefiro não apressar.
    >>  ............................................
  flirty.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Nobody wants more. I'll give you the whole of it, then.
    >>  ............................................
    pt  Você quer mais. Ninguém quer mais. Então eu te dou tudo.
    >>  ............................................
  friendly.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright, %1$s. You want the next part. Right, then. Here it is.
    >>  ............................................
    pt  ...Está bem, %1$s. Você quer a próxima parte. Certo, então. Aqui vai.
    >>  ............................................
  friendly.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Sit properly — this one takes a while and I'd rather not rush it.
    >>  ............................................
    pt  A próxima parte. Sente-se direito — essa leva um tempo e eu prefiro não apressar.
    >>  ............................................
  friendly.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Nobody wants more. I'll give you the whole of it, then.
    >>  ............................................
    pt  Você quer mais. Ninguém quer mais. Então eu te dou tudo.
    >>  ............................................
  gloomy.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright. You want the next part, %1$s. Nobody has ever wanted the next part.
    >>  ............................................
    pt  ...Está bem. Você quer a próxima parte, %1$s. Ninguém nunca quis a próxima parte.
    >>  ............................................
  gloomy.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Give me a moment to find where it starts — I don't tell this bit.
    >>  ............................................
    pt  A próxima parte. Me dê um momento pra achar onde começa — eu não conto essa parte.
    >>  ............................................
  gloomy.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. I'll try. If I stop halfway, that's not you, that's the part.
    >>  ............................................
    pt  Você quer mais. Vou tentar. Se eu parar no meio, não é você, é a parte.
    >>  ............................................
  greedy.dialogue.conversations.life.followup.follow_thread/1
    en  Alright. You want the next part. Right, then. Here it is.
    >>  ............................................
    pt  Está bem. Você quer a próxima parte. Certo, então. Aqui vai.
    >>  ............................................
  greedy.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Nobody asks for the next part. Sit down.
    >>  ............................................
    pt  A próxima parte. Ninguém pede a próxima parte. Sente-se.
    >>  ............................................
  greedy.dialogue.conversations.life.followup.follow_thread/3
    en  You want more of it. Fine. It gets worse before it gets ordinary.
    >>  ............................................
    pt  Você quer mais. Tudo bem. Piora antes de ficar comum.
    >>  ............................................
  grumpy.dialogue.conversations.life.followup.follow_thread/1
    en  Alright. You want the next part. Right, then. Here it is.
    >>  ............................................
    pt  Está bem. Você quer a próxima parte. Certo, então. Aqui vai.
    >>  ............................................
  grumpy.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Nobody asks for the next part. Sit down.
    >>  ............................................
    pt  A próxima parte. Ninguém pede a próxima parte. Sente-se.
    >>  ............................................
  grumpy.dialogue.conversations.life.followup.follow_thread/3
    en  You want more of it. Fine. It gets worse before it gets ordinary.
    >>  ............................................
    pt  Você quer mais. Tudo bem. Piora antes de ficar comum.
    >>  ............................................
  introverted.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright. You want the next part. Here it is.
    >>  ............................................
    pt  ...Está bem. Você quer a próxima parte. Aqui vai.
    >>  ............................................
  introverted.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Right.
    >>  ............................................
    pt  A próxima parte. Certo.
    >>  ............................................
  introverted.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Fine.
    >>  ............................................
    pt  Você quer mais. Tudo bem.
    >>  ............................................
  lazy.dialogue.conversations.life.followup.follow_thread/1
    en  Alright. You want the next part. Sit down; it's not a short one.
    >>  ............................................
    pt  Está bem. Você quer a próxima parte. Sente-se; não é curta.
    >>  ............................................
  lazy.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. It goes slowly and there's no shorter version worth having.
    >>  ............................................
    pt  A próxima parte. Vai devagar e não há versão mais curta que valha.
    >>  ............................................
  lazy.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Fine. It'll take the evening and the evening is long.
    >>  ............................................
    pt  Você quer mais. Tudo bem. Vai levar a noite e a noite é longa.
    >>  ............................................
  odd.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright. You want the next part. Here it is.
    >>  ............................................
    pt  ...Está bem. Você quer a próxima parte. Aqui vai.
    >>  ............................................
  odd.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Right.
    >>  ............................................
    pt  A próxima parte. Certo.
    >>  ............................................
  odd.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Fine.
    >>  ............................................
    pt  Você quer mais. Tudo bem.
    >>  ............................................
  peaceful.dialogue.conversations.life.followup.follow_thread/1
    en  Alright. You want the next part. Sit down; it's not a short one.
    >>  ............................................
    pt  Está bem. Você quer a próxima parte. Sente-se; não é curta.
    >>  ............................................
  peaceful.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. It goes slowly and there's no shorter version worth having.
    >>  ............................................
    pt  A próxima parte. Vai devagar e não há versão mais curta que valha.
    >>  ............................................
  peaceful.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Fine. It'll take the evening and the evening is long.
    >>  ............................................
    pt  Você quer mais. Tudo bem. Vai levar a noite e a noite é longa.
    >>  ............................................
  peppy.dialogue.conversations.life.followup.follow_thread/1
    en  Alright! You want the next part. Huh. Right, then — here it is.
    >>  ............................................
    pt  Está bem! Você quer a próxima parte. Huh. Certo, então — aqui vai.
    >>  ............................................
  peppy.dialogue.conversations.life.followup.follow_thread/2
    en  The next part! Nobody asks for the next part. They all nod and change the subject.
    >>  ............................................
    pt  A próxima parte! Ninguém pede a próxima parte. Todos acenam e mudam de assunto.
    >>  ............................................
  peppy.dialogue.conversations.life.followup.follow_thread/3
    en  More of it! Fine. Fair warning: it gets worse before it gets dull.
    >>  ............................................
    pt  Mais! Tudo bem. Aviso: piora antes de ficar sem graça.
    >>  ............................................
  playful.dialogue.conversations.life.followup.follow_thread/1
    en  Alright! You want the next part. Huh. Right, then — here it is.
    >>  ............................................
    pt  Está bem! Você quer a próxima parte. Huh. Certo, então — aqui vai.
    >>  ............................................
  playful.dialogue.conversations.life.followup.follow_thread/2
    en  The next part! Nobody asks for the next part. They all nod and change the subject.
    >>  ............................................
    pt  A próxima parte! Ninguém pede a próxima parte. Todos acenam e mudam de assunto.
    >>  ............................................
  playful.dialogue.conversations.life.followup.follow_thread/3
    en  More of it! Fine. Fair warning: it gets worse before it gets dull.
    >>  ............................................
    pt  Mais! Tudo bem. Aviso: piora antes de ficar sem graça.
    >>  ............................................
  relaxed.dialogue.conversations.life.followup.follow_thread/1
    en  Alright. You want the next part. Sit down; it's not a short one.
    >>  ............................................
    pt  Está bem. Você quer a próxima parte. Sente-se; não é curta.
    >>  ............................................
  relaxed.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. It goes slowly and there's no shorter version worth having.
    >>  ............................................
    pt  A próxima parte. Vai devagar e não há versão mais curta que valha.
    >>  ............................................
  relaxed.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Fine. It'll take the evening and the evening is long.
    >>  ............................................
    pt  Você quer mais. Tudo bem. Vai levar a noite e a noite é longa.
    >>  ............................................
  sensitive.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright. You want the next part, %1$s. Nobody has ever wanted the next part.
    >>  ............................................
    pt  ...Está bem. Você quer a próxima parte, %1$s. Ninguém nunca quis a próxima parte.
    >>  ............................................
  sensitive.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Give me a moment to find where it starts — I don't tell this bit.
    >>  ............................................
    pt  A próxima parte. Me dê um momento pra achar onde começa — eu não conto essa parte.
    >>  ............................................
  sensitive.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. I'll try. If I stop halfway, that's not you, that's the part.
    >>  ............................................
    pt  Você quer mais. Vou tentar. Se eu parar no meio, não é você, é a parte.
    >>  ............................................
  shy.dialogue.conversations.life.followup.follow_thread/1
    en  ...Alright. You want the next part. Here it is.
    >>  ............................................
    pt  ...Está bem. Você quer a próxima parte. Aqui vai.
    >>  ............................................
  shy.dialogue.conversations.life.followup.follow_thread/2
    en  The next part. Right.
    >>  ............................................
    pt  A próxima parte. Certo.
    >>  ............................................
  shy.dialogue.conversations.life.followup.follow_thread/3
    en  You want more. Fine.
    >>  ............................................
    pt  Você quer mais. Tudo bem.
    >>  ............................................
  upbeat.dialogue.conversations.life.followup.follow_thread/1
    en  Alright! You want the next part. Huh. Right, then — here it is.
    >>  ............................................
    pt  Está bem! Você quer a próxima parte. Huh. Certo, então — aqui vai.
    >>  ............................................
  upbeat.dialogue.conversations.life.followup.follow_thread/2
    en  The next part! Nobody asks for the next part. They all nod and change the subject.
    >>  ............................................
    pt  A próxima parte! Ninguém pede a próxima parte. Todos acenam e mudam de assunto.
    >>  ............................................
  upbeat.dialogue.conversations.life.followup.follow_thread/3
    en  More of it! Fine. Fair warning: it gets worse before it gets dull.
    >>  ............................................
    pt  Mais! Tudo bem. Aviso: piora antes de ficar sem graça.
    >>  ............................................
  witty.dialogue.conversations.life.followup.follow_thread/1
    en  Alright! You want the next part. Huh. Right, then — here it is.
    >>  ............................................
    pt  Está bem! Você quer a próxima parte. Huh. Certo, então — aqui vai.
    >>  ............................................
  witty.dialogue.conversations.life.followup.follow_thread/2
    en  The next part! Nobody asks for the next part. They all nod and change the subject.
    >>  ............................................
    pt  A próxima parte! Ninguém pede a próxima parte. Todos acenam e mudam de assunto.
    >>  ............................................
  witty.dialogue.conversations.life.followup.follow_thread/3
    en  More of it! Fine. Fair warning: it gets worse before it gets dull.
    >>  ............................................
    pt  Mais! Tudo bem. Aviso: piora antes de ficar sem graça.
    >>  ............................................
```

</details>


### Button `compare_own` — "Something like that happened to me."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `life.ask_which.low.to.life`, `life.no_words.to.life`, `life.respond.ask_which.to.life`, `life.respond.empathise.longknown.to.life`, `life.respond.empathise.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.followup.compare_own` — accepted phrasings: "something like that happened to me"; "something similar happened to me"; "same happened to me"
  - the message must contain one of: `happened`, `similar`, `me`
  - scored words: `happened`(1.2), `me`(0.5), `similar`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.life.followup.compare_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.followup.compare_own   [35 chars]
    en  Something like that happened to me.
    >>  ............................................
    pt  Algo parecido aconteceu comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.followup.compare_own`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — familiarity +4, warmth +2  _(recorded under topic `life.followup.compare_own`)_
- Does: arc `life` — advance to stage 1
- Then opens: `conversations.topic.life.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Here's one of mine, then." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.life.followup.compare_own
WHO    VILLAGER — what the player reads after pressing "Something like that happened to me."
       spoken on: conversations.topic.life.followup, button `compare_own`
       leaves the player on: conversations.topic.life.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.followup.compare_own.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.followup.compare_own/1   [56 chars]
    en  Did it? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Aconteceu? Então você sabe por que eu nunca fiz alarde disso.
    >>  ............................................
  dialogue.conversations.life.followup.compare_own/2   [47 chars]
    en  Two of us, then. It helps, oddly, knowing that.
    >>  ............................................
    pt  Nós dois, então. Ajuda, estranhamente, saber disso.
    >>  ............................................
  dialogue.conversations.life.followup.compare_own/3   [43 chars]
    en  Tell me yours some time. Fair's fair, %1$s.
    >>  ............................................
    pt  Me conta a sua qualquer hora. É justo, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it, %1$s.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.life.followup.compare_own/2
    en  You as well. I'm sorry. I'd not wish it and I'm glad not to be the only one.
    >>  ............................................
    pt  Você também. Sinto muito. Eu não desejaria e fico contente de não ser o único.
    >>  ............................................
  anxious.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. Those two things sit oddly together and they're both true.
    >>  ............................................
    pt  Igual, então. Essas duas coisas ficam estranhas juntas e as duas são verdade.
    >>  ............................................
  athletic.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it. Time does the rest.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso. O tempo faz o resto.
    >>  ............................................
  athletic.dialogue.conversations.life.followup.compare_own/2
    en  You as well. It sits differently after twenty years, doesn't it.
    >>  ............................................
    pt  Você também. Fica diferente depois de vinte anos, não fica?
    >>  ............................................
  athletic.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. There's a comfort in that which took me a long while to find.
    >>  ............................................
    pt  Igual, então. Tem um consolo nisso que eu levei muito tempo pra achar.
    >>  ............................................
  confident.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  confident.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Right. Then I needn't explain the shape of it.
    >>  ............................................
    pt  Você também. Certo. Então eu não preciso explicar o formato.
    >>  ............................................
  confident.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. That saves us both a great deal of describing.
    >>  ............................................
    pt  Igual, então. Isso poupa muita descrição pra nós dois.
    >>  ............................................
  crabby.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  crabby.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Right. Then I needn't explain the shape of it.
    >>  ............................................
    pt  Você também. Certo. Então eu não preciso explicar o formato.
    >>  ............................................
  crabby.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. That saves us both a great deal of describing.
    >>  ............................................
    pt  Igual, então. Isso poupa muita descrição pra nós dois.
    >>  ............................................
  extroverted.dialogue.conversations.life.followup.compare_own/1
    en  Did it, %1$s? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi, %1$s? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  extroverted.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Tell me yours properly some evening — I'd like to hear it.
    >>  ............................................
    pt  Você também. Me conte a sua direito numa noite — eu queria ouvir.
    >>  ............................................
  extroverted.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. That's a strange comfort and I'll take it.
    >>  ............................................
    pt  Igual, então. É um consolo estranho e eu aceito.
    >>  ............................................
  flirty.dialogue.conversations.life.followup.compare_own/1
    en  Did it, %1$s? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi, %1$s? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  flirty.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Tell me yours properly some evening — I'd like to hear it.
    >>  ............................................
    pt  Você também. Me conte a sua direito numa noite — eu queria ouvir.
    >>  ............................................
  flirty.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. That's a strange comfort and I'll take it.
    >>  ............................................
    pt  Igual, então. É um consolo estranho e eu aceito.
    >>  ............................................
  friendly.dialogue.conversations.life.followup.compare_own/1
    en  Did it, %1$s? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi, %1$s? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  friendly.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Tell me yours properly some evening — I'd like to hear it.
    >>  ............................................
    pt  Você também. Me conte a sua direito numa noite — eu queria ouvir.
    >>  ............................................
  friendly.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. That's a strange comfort and I'll take it.
    >>  ............................................
    pt  Igual, então. É um consolo estranho e eu aceito.
    >>  ............................................
  gloomy.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it, %1$s.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.life.followup.compare_own/2
    en  You as well. I'm sorry. I'd not wish it and I'm glad not to be the only one.
    >>  ............................................
    pt  Você também. Sinto muito. Eu não desejaria e fico contente de não ser o único.
    >>  ............................................
  gloomy.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. Those two things sit oddly together and they're both true.
    >>  ............................................
    pt  Igual, então. Essas duas coisas ficam estranhas juntas e as duas são verdade.
    >>  ............................................
  greedy.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  greedy.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Right. Then I needn't explain the shape of it.
    >>  ............................................
    pt  Você também. Certo. Então eu não preciso explicar o formato.
    >>  ............................................
  greedy.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. That saves us both a great deal of describing.
    >>  ............................................
    pt  Igual, então. Isso poupa muita descrição pra nós dois.
    >>  ............................................
  grumpy.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  grumpy.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Right. Then I needn't explain the shape of it.
    >>  ............................................
    pt  Você também. Certo. Então eu não preciso explicar o formato.
    >>  ............................................
  grumpy.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. That saves us both a great deal of describing.
    >>  ............................................
    pt  Igual, então. Isso poupa muita descrição pra nós dois.
    >>  ............................................
  introverted.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  introverted.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  introverted.dialogue.conversations.life.followup.compare_own/3
    en  Same, then.
    >>  ............................................
    pt  Igual, então.
    >>  ............................................
  lazy.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it. Time does the rest.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso. O tempo faz o resto.
    >>  ............................................
  lazy.dialogue.conversations.life.followup.compare_own/2
    en  You as well. It sits differently after twenty years, doesn't it.
    >>  ............................................
    pt  Você também. Fica diferente depois de vinte anos, não fica?
    >>  ............................................
  lazy.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. There's a comfort in that which took me a long while to find.
    >>  ............................................
    pt  Igual, então. Tem um consolo nisso que eu levei muito tempo pra achar.
    >>  ............................................
  odd.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  odd.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  odd.dialogue.conversations.life.followup.compare_own/3
    en  Same, then.
    >>  ............................................
    pt  Igual, então.
    >>  ............................................
  peaceful.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it. Time does the rest.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso. O tempo faz o resto.
    >>  ............................................
  peaceful.dialogue.conversations.life.followup.compare_own/2
    en  You as well. It sits differently after twenty years, doesn't it.
    >>  ............................................
    pt  Você também. Fica diferente depois de vinte anos, não fica?
    >>  ............................................
  peaceful.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. There's a comfort in that which took me a long while to find.
    >>  ............................................
    pt  Igual, então. Tem um consolo nisso que eu levei muito tempo pra achar.
    >>  ............................................
  peppy.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it! Small mercies.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso! Pequenas graças.
    >>  ............................................
  peppy.dialogue.conversations.life.followup.compare_own/2
    en  You as well! Right. Then we can skip the tedious explaining part.
    >>  ............................................
    pt  Você também! Certo. Então a gente pula a parte chata de explicar.
    >>  ............................................
  peppy.dialogue.conversations.life.followup.compare_own/3
    en  Same, then! Excellent. Nobody has to be brave about it.
    >>  ............................................
    pt  Igual, então! Excelente. Ninguém precisa ser corajoso sobre isso.
    >>  ............................................
  playful.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it! Small mercies.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso! Pequenas graças.
    >>  ............................................
  playful.dialogue.conversations.life.followup.compare_own/2
    en  You as well! Right. Then we can skip the tedious explaining part.
    >>  ............................................
    pt  Você também! Certo. Então a gente pula a parte chata de explicar.
    >>  ............................................
  playful.dialogue.conversations.life.followup.compare_own/3
    en  Same, then! Excellent. Nobody has to be brave about it.
    >>  ............................................
    pt  Igual, então! Excelente. Ninguém precisa ser corajoso sobre isso.
    >>  ............................................
  relaxed.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it. Time does the rest.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso. O tempo faz o resto.
    >>  ............................................
  relaxed.dialogue.conversations.life.followup.compare_own/2
    en  You as well. It sits differently after twenty years, doesn't it.
    >>  ............................................
    pt  Você também. Fica diferente depois de vinte anos, não fica?
    >>  ............................................
  relaxed.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. There's a comfort in that which took me a long while to find.
    >>  ............................................
    pt  Igual, então. Tem um consolo nisso que eu levei muito tempo pra achar.
    >>  ............................................
  sensitive.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it, %1$s.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.life.followup.compare_own/2
    en  You as well. I'm sorry. I'd not wish it and I'm glad not to be the only one.
    >>  ............................................
    pt  Você também. Sinto muito. Eu não desejaria e fico contente de não ser o único.
    >>  ............................................
  sensitive.dialogue.conversations.life.followup.compare_own/3
    en  Same, then. Those two things sit oddly together and they're both true.
    >>  ............................................
    pt  Igual, então. Essas duas coisas ficam estranhas juntas e as duas são verdade.
    >>  ............................................
  shy.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso.
    >>  ............................................
  shy.dialogue.conversations.life.followup.compare_own/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  shy.dialogue.conversations.life.followup.compare_own/3
    en  Same, then.
    >>  ............................................
    pt  Igual, então.
    >>  ............................................
  upbeat.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it! Small mercies.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso! Pequenas graças.
    >>  ............................................
  upbeat.dialogue.conversations.life.followup.compare_own/2
    en  You as well! Right. Then we can skip the tedious explaining part.
    >>  ............................................
    pt  Você também! Certo. Então a gente pula a parte chata de explicar.
    >>  ............................................
  upbeat.dialogue.conversations.life.followup.compare_own/3
    en  Same, then! Excellent. Nobody has to be brave about it.
    >>  ............................................
    pt  Igual, então! Excelente. Ninguém precisa ser corajoso sobre isso.
    >>  ............................................
  witty.dialogue.conversations.life.followup.compare_own/1
    en  Did it? Then you'll know why I've never made much of it! Small mercies.
    >>  ............................................
    pt  Foi? Então você sabe por que eu nunca fiz caso disso! Pequenas graças.
    >>  ............................................
  witty.dialogue.conversations.life.followup.compare_own/2
    en  You as well! Right. Then we can skip the tedious explaining part.
    >>  ............................................
    pt  Você também! Certo. Então a gente pula a parte chata de explicar.
    >>  ............................................
  witty.dialogue.conversations.life.followup.compare_own/3
    en  Same, then! Excellent. Nobody has to be brave about it.
    >>  ............................................
    pt  Igual, então! Excelente. Ninguém precisa ser corajoso sobre isso.
    >>  ............................................
```

</details>


### Button `change_subject` — "Let's leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `life.ask_which.low.to.life`, `life.no_words.to.life`, `life.respond.ask_which.to.life`, `life.respond.empathise.longknown.to.life`, `life.respond.empathise.to.life` · **this is the graceful way out of the node***

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.followup.change_subject` — accepted phrasings: "let us leave it there"; "that is enough of that"; "leave it there"
  - the message must contain one of: `leave`, `enough`
  - scored words: `leave`(1.5), `there`(0.8), `enough`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.life.followup.change_subject
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.followup.change_subject   [21 chars]
    en  Let's leave it there.
    >>  ............................................
    pt  Vamos parar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.followup.change_subject
WHO    VILLAGER — what the player reads after pressing "Let's leave it there."
       spoken on: conversations.topic.life.followup, button `change_subject`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.followup.change_subject.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.followup.change_subject/1   [48 chars]
    en  Quite, let's. I've dug enough for one afternoon.
    >>  ............................................
    pt  Exato, vamos. Já cavei o suficiente por uma tarde.
    >>  ............................................
  dialogue.conversations.life.followup.change_subject/2   [48 chars]
    en  Good. It gets heavier the longer you hold it up.
    >>  ............................................
    pt  Bom. Fica mais pesado quanto mais tempo você segura.
    >>  ............................................
  dialogue.conversations.life.followup.change_subject/3   [55 chars]
    en  Right. Ask me about the weather and we'll both recover.
    >>  ............................................
    pt  Certo. Me pergunte do tempo e nós dois nos recuperamos.
    >>  ............................................
```


### Button `joke` — "You've had a busier life than the mayor, then."

*stance family `humor` · tone `playful` · answers the beat(s) `life.ask_which.low.to.life`, `life.no_words.to.life`, `life.respond.ask_which.to.life`, `life.respond.empathise.longknown.to.life`, `life.respond.empathise.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.followup.joke` — accepted phrasings: "you have had a busier life than the mayor"; "busier than the mayor then"; "that is busier than the mayor"
  - the message must contain one of: `busier`, `mayor`
  - scored words: `busier`(1.6), `mayor`(1.3)

```text
POOL   dialogue key: dialogue.conversations.topic.life.followup.joke
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.followup.joke   [46 chars]
    en  You've had a busier life than the mayor, then.
    >>  ............................................
    pt  Então você teve uma vida mais movimentada que a do prefeito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.followup.joke`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, tension -2  _(recorded under topic `life.followup.joke`)_
- Then opens: `conversations.topic.life.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Here's one of mine, then." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.life.followup.joke
WHO    VILLAGER — what the player reads after pressing "You've had a busier life than the mayor, then."
       spoken on: conversations.topic.life.followup, button `joke`
       leaves the player on: conversations.topic.life.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.followup.joke.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.followup.joke/1   [84 chars]
    en  Ha! I have, actually. He's had one long meeting since 1247 and I've had all of that.
    >>  ............................................
    pt  Ha! Tive mesmo. Ele teve uma reunião longa desde 1247 e eu tive tudo isso.
    >>  ............................................
  dialogue.conversations.life.followup.joke/2   [78 chars]
    en  The mayor. Don't. ...Although now you've said it I can't stop comparing, %1$s.
    >>  ............................................
    pt  O prefeito. Não. ...Mas agora que você disse eu não consigo parar de comparar, %1$s.
    >>  ............................................
  dialogue.conversations.life.followup.joke/3   [63 chars]
    en  Busier and worse paid. Put that on the stone when I'm under it.
    >>  ............................................
    pt  Mais movimentada e pior paga. Escreva isso na pedra quando eu estiver embaixo dela.
    >>  ............................................
```


### Button `leave` — "That's a life, right enough. I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `life.ask_which.low.to.life`, `life.no_words.to.life`, `life.respond.ask_which.to.life`, `life.respond.empathise.longknown.to.life`, `life.respond.empathise.to.life` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.life.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.followup.leave   [37 chars]
    en  That's a life, right enough. I'll go.
    >>  ............................................
    pt  É uma vida inteira, isso. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.respond.leave
WHO    VILLAGER — what the player reads after pressing "That's a life, right enough. I'll go."
       spoken on: conversations.topic.life.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.respond.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.life.respond / leave
```

```text
  dialogue.conversations.life.respond.leave/1   [62 chars]
    en  So I've found. It's not a short story and you've things to do.
    >>  ............................................
    pt  Foi o que eu vi. Não é uma história curta e você tem o que fazer.
    >>  ............................................
  dialogue.conversations.life.respond.leave/2   [41 chars]
    en  Go on. Thank you for asking at all, %1$s.
    >>  ............................................
    pt  Pode ir. Obrigado por ter perguntado, %1$s.
    >>  ............................................
  dialogue.conversations.life.respond.leave/3   [47 chars]
    en  Right. That's enough of my history for one day.
    >>  ............................................
    pt  Certo. Já chega da minha história por hoje.
    >>  ............................................
```

---


## `conversations.topic.life.guarded.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `life`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.deflect.personal` — e.g. "That's... a bit close to the bone for someone I barely know."


```text
POOL   dialogue key: dialogue.conversations.topic.life.guarded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.life.guarded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.life.guarded.respond   [49 chars]
    en  It's a long story, and you've had the first page.
    >>  ............................................
    pt  É uma história longa, e você teve só a primeira página.
    >>  ............................................
```


### Button `respect` — "I'll wait for the rest."

*stance family `restraint` · tone `plain` · answers the beat(s) `deflect.personal.to.life.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.guarded.respect` — accepted phrasings: "that is yours to keep"; "keep it to yourself"; "that story is yours"
  - the message must contain one of: `yours`, `keep`
  - scored words: `yours`(1.5), `keep`(1.2), `story`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.life.guarded.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.guarded.respond.respect   [23 chars]
    en  I'll wait for the rest.
    >>  ............................................
    pt  Eu espero o resto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.guarded.respect`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `life.guarded.respect`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.guarded.respect
WHO    VILLAGER — what the player reads after pressing "I'll wait for the rest."
       spoken on: conversations.topic.life.guarded.respond, button `respect`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.guarded.respect.terminal`: the villager deflects. Subject `life.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.guarded.respect/1   [77 chars]
    en  ...Thank you. Most want the ending first and lose interest before the middle.
    >>  ............................................
    pt  ...Obrigado. A maioria quer o final primeiro e desiste antes do meio.
    >>  ............................................
  dialogue.conversations.life.guarded.respect/2   [92 chars]
    en  True enough. You'll get it in pieces, in the order it happened, if you're still about, %1$s.
    >>  ............................................
    pt  Bem verdade. Você recebe em pedaços, na ordem em que aconteceu, se ainda estiver por aqui, %1$s.
    >>  ............................................
  dialogue.conversations.life.guarded.respect/3   [78 chars]
    en  Good. It's a long story and it's better told to somebody who isn't in a hurry.
    >>  ............................................
    pt  Bom. É uma história longa e fica melhor contada a quem não tem pressa.
    >>  ............................................
```


### Button `ask_safer` — "Tell me a recent bit, then."

*stance family `curiosity` · tone `gentle` · answers the beat(s) `deflect.personal.to.life.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.guarded.ask_safer` — accepted phrasings: "tell me something lighter"; "something easier then"; "let us keep it light"
  - the message must contain one of: `lighter`, `easier`
  - scored words: `lighter`(1.5), `easier`(1.2), `story`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.life.guarded.respond.ask_safer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.guarded.respond.ask_safer   [27 chars]
    en  Tell me a recent bit, then.
    >>  ............................................
    pt  Então me conta uma parte recente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `life.guarded.ask_safer`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.guarded.ask_safer
WHO    VILLAGER — what the player reads after pressing "Tell me a recent bit, then."
       spoken on: conversations.topic.life.guarded.respond, button `ask_safer`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.guarded.ask_safer.terminal`: the villager deflects. Subject `life.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.guarded.ask_safer/1   [82 chars]
    en  The recent parts you can have. It's the old ones that cost something to hand over.
    >>  ............................................
    pt  As partes recentes você pode ter. São as antigas que custam para entregar.
    >>  ............................................
  dialogue.conversations.life.guarded.ask_safer/2   [61 chars]
    en  Ask me about this year. This year I can do standing up, %1$s.
    >>  ............................................
    pt  Me pergunte deste ano. Este ano eu conto de pé, %1$s.
    >>  ............................................
  dialogue.conversations.life.guarded.ask_safer/3   [78 chars]
    en  Something from the shallow end of it, aye. There's plenty there worth telling.
    >>  ............................................
    pt  Algo da parte rasa, isso. Tem bastante coisa lá que vale contar.
    >>  ............................................
```


### Button `press` — "Skip ahead for me."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `deflect.personal.to.life.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.guarded.press` — accepted phrasings: "come on, you can tell me"; "tell me the story"; "go on, tell me"
  - the message must contain one of: `come`, `tell`
  - scored words: `come`(1.2), `tell`(1.0), `story`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.life.guarded.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.guarded.respond.press   [18 chars]
    en  Skip ahead for me.
    >>  ............................................
    pt  Pula pra frente pra mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `life.guarded.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5  _(recorded under topic `life.guarded.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.guarded.press
WHO    VILLAGER — what the player reads after pressing "Skip ahead for me."
       spoken on: conversations.topic.life.guarded.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.guarded.press.terminal`: the villager resists. Subject `life.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.guarded.press/1   [90 chars]
    en  You want the whole of it before you've been told the first page. It doesn't work that way.
    >>  ............................................
    pt  Você quer tudo antes de ter ouvido a primeira página. Não funciona assim.
    >>  ............................................
  dialogue.conversations.life.guarded.press/2   [80 chars]
    en  A life isn't a thing you get handed, %1$s. It's a thing you're let into, slowly.
    >>  ............................................
    pt  Uma vida não se entrega, %1$s. A gente é deixado entrar nela, devagar.
    >>  ............................................
  dialogue.conversations.life.guarded.press/3   [90 chars]
    en  No. Ask me what I did yesterday and I'll answer. Ask me what made me, and we're not there.
    >>  ............................................
    pt  Não. Me pergunte o que eu fiz ontem e eu respondo. Me pergunte o que me fez, e não chegamos lá.
    >>  ............................................
```


### Button `leave` — "I'll come back for the next page."

*stance family `exit` · tone `plain` · answers the beat(s) `deflect.personal.to.life.guarded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.life.guarded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.guarded.respond.leave   [33 chars]
    en  I'll come back for the next page.
    >>  ............................................
    pt  Volto pra próxima página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.guarded.leave
WHO    VILLAGER — what the player reads after pressing "I'll come back for the next page."
       spoken on: conversations.topic.life.guarded.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.guarded.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.guarded.leave/1   [22 chars]
    en  Aye. No hard feelings.
    >>  ............................................
    pt  Tá. Sem ressentimento.
    >>  ............................................
  dialogue.conversations.life.guarded.leave/2   [40 chars]
    en  Off you go. We'll get there or we won't.
    >>  ............................................
    pt  Pode ir. A gente chega lá ou não.
    >>  ............................................
  dialogue.conversations.life.guarded.leave/3   [20 chars]
    en  Right you are, %1$s.
    >>  ............................................
    pt  Isso mesmo, %1$s.
    >>  ............................................
```

---


## `conversations.topic.life.rebuked.followup`

**Reached from 1 route(s):** `conversations.topic.life.respond` / `judge`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.life.respond.judge` — e.g. "...I was fourteen, %1$s. What exactly should I have done better?"


```text
POOL   dialogue key: dialogue.conversations.topic.life.rebuked.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.life.rebuked.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.life.rebuked.followup   [30 chars]
    en  I told you that in confidence.
    >>  ............................................
    pt  Eu te contei isso em confiança.
    >>  ............................................
```


### Button `apologize` — "That was mine to hear, not to grade."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `life.rebuked` · offered only once the villager has actually said `player:marked_their_life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.rebuked.apologize` — accepted phrasings: "that was mine to hear, not to grade"
  - the message must contain one of: `grade`, `judge`, `confidence`
  - scored words: `grade`(1.5), `judge`(1.2), `confidence`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.life.rebuked.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.rebuked.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.rebuked.followup.apologize   [36 chars]
    en  That was mine to hear, not to grade.
    >>  ............................................
    pt  Aquilo era pra eu ouvir, não pra eu avaliar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `life.rebuked.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.rebuked.apologize
WHO    VILLAGER — what the player reads after pressing "That was mine to hear, not to grade."
       spoken on: conversations.topic.life.rebuked.followup, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.rebuked.apologize`: the villager qualifys. Subject `life.history`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.life.rebuked.apologize/1   [56 chars]
    en  ...It was. And you're the first who's noticed that fast.
    >>  ............................................
    pt  ...Era. E você é o primeiro a perceber isso tão rápido.
    >>  ............................................
  dialogue.conversations.life.rebuked.apologize/2   [54 chars]
    en  Quite. Folk hear a life and start keeping score, %1$s.
    >>  ............................................
    pt  Exato. O povo ouve uma vida e começa a dar nota, %1$s.
    >>  ............................................
  dialogue.conversations.life.rebuked.apologize/3   [54 chars]
    en  Then we'll leave it there and I'll not have wasted it.
    >>  ............................................
    pt  Então paramos por aqui e eu não terei desperdiçado.
    >>  ............................................
```


### Button `explain` — "I meant it about the time, not about you."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `life.rebuked`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.rebuked.explain` — accepted phrasings: "i meant it about the time, not about you"
  - the message must contain one of: `time`, `meant`
  - scored words: `time`(1.2), `meant`(1.2), `about`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.life.rebuked.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.rebuked.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.rebuked.followup.explain   [41 chars]
    en  I meant it about the time, not about you.
    >>  ............................................
    pt  Eu falei do tempo, não de você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `life.rebuked.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.rebuked.explain
WHO    VILLAGER — what the player reads after pressing "I meant it about the time, not about you."
       spoken on: conversations.topic.life.rebuked.followup, button `explain`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.rebuked.explain`: the villager qualifys. Subject `life.history`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.life.rebuked.explain/1   [63 chars]
    en  ...Perhaps. It's difficult to hear the difference from in here.
    >>  ............................................
    pt  ...Talvez. É difícil ouvir a diferença aqui de dentro.
    >>  ............................................
  dialogue.conversations.life.rebuked.explain/2   [79 chars]
    en  The time and me were the same thing back then, %1$s. That's rather the trouble.
    >>  ............................................
    pt  O tempo e eu éramos a mesma coisa naquela época, %1$s. É esse o problema.
    >>  ............................................
  dialogue.conversations.life.rebuked.explain/3   [58 chars]
    en  Then say the time next time. It'd have landed differently.
    >>  ............................................
    pt  Então da próxima diga o tempo. Teria caído diferente.
    >>  ............................................
```


### Button `leave` — "I'll not say anything else."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `life.rebuked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.life.rebuked.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.rebuked.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.rebuked.followup.leave   [27 chars]
    en  I'll not say anything else.
    >>  ............................................
    pt  Não vou dizer mais nada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.rebuked.leave
WHO    VILLAGER — what the player reads after pressing "I'll not say anything else."
       spoken on: conversations.topic.life.rebuked.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.rebuked.leave`: the villager accepts. Subject `life.history`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.life.rebuked.leave/1   [35 chars]
    en  Good. That's the right amount, now.
    >>  ............................................
    pt  Bom. Agora é a quantidade certa.
    >>  ............................................
  dialogue.conversations.life.rebuked.leave/2   [24 chars]
    en  It is. Off you go, %1$s.
    >>  ............................................
    pt  É sim. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.life.rebuked.leave/3   [42 chars]
    en  Mm. Come back when we've both slept on it.
    >>  ............................................
    pt  Mm. Volte quando os dois tiverem dormido sobre isso.
    >>  ............................................
```

---


## `conversations.topic.life.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `life`; `conversations.cat.personal` / `life`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.life.first` — e.g. "My life? Hm. Give me a second... it's a long story with a lot of turns badly signposted."
- `conversations.life.revisit` — e.g. "You asked me about my life once. I've been thinking about what I left out."


```text
POOL   dialogue key: dialogue.conversations.topic.life.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.life.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.life.respond   [37 chars]
    en  That's the shape of it, more or less.
    >>  ............................................
    pt  É mais ou menos essa a forma.
    >>  ............................................
```


### Button `ask_which` — "Which part matters most to you?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `life.first.to.life`, `life.revisit.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.respond.ask_which` — accepted phrasings: "which part matters most"; "which part matters to you"; "what part matters"
  - the message must contain one of: `part`, `which`, `matters`
  - scored words: `part`(1.5), `which`(1.2), `matters`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.life.respond.ask_which
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.respond.ask_which   [31 chars]
    en  Which part matters most to you?
    >>  ............................................
    pt  Qual parte importa mais para você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the mood is `depressed`
- Does: **hearts +1** — decision id `life.respond.ask_which`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — familiarity +4, trust +1  _(recorded under topic `life.respond.ask_which`)_
- Then opens: `conversations.topic.life.followup`
- …where the player's next choices will be: "Tell me more about that part." | "Something like that happened to me." | "Let's leave it there." | "You've had a busier life than the mayor, then." | "That's a life, right enough. I'll go."

```text
POOL   dialogue key: dialogue.conversations.life.ask_which.low
WHO    VILLAGER — what the player reads after pressing "Which part matters most to you?"
       spoken on: conversations.topic.life.respond, button `ask_which`
       leaves the player on: conversations.topic.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.ask_which.low.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.ask_which.low/1   [80 chars]
    en  Which part matters. ...Today, the part where it stopped going the way I planned.
    >>  ............................................
    pt  Qual parte importa. ...Hoje, a parte em que parou de sair como eu tinha planejado.
    >>  ............................................
  dialogue.conversations.life.ask_which.low/2   [72 chars]
    en  Ask me on a better day, %1$s, and you'll get a different answer to that.
    >>  ............................................
    pt  Me pergunte num dia melhor, %1$s, e você recebe outra resposta.
    >>  ............................................
  dialogue.conversations.life.ask_which.low/3   [78 chars]
    en  All of it matters. Today none of it feels like it does. That passes, I'm told.
    >>  ............................................
    pt  Tudo importa. Hoje nada parece importar. Dizem que passa.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the mood is `depressed`  _(chance -2000)_
- Does: **hearts +1** — decision id `life.respond.ask_which`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — familiarity +4, trust +1  _(recorded under topic `life.respond.ask_which`)_
- Then opens: `conversations.topic.life.followup`
- …where the player's next choices will be: "Tell me more about that part." | "Something like that happened to me." | "Let's leave it there." | "You've had a busier life than the mayor, then." | "That's a life, right enough. I'll go."

```text
POOL   dialogue key: dialogue.conversations.life.respond.ask_which
WHO    VILLAGER — what the player reads after pressing "Which part matters most to you?"
       spoken on: conversations.topic.life.respond, button `ask_which`
       leaves the player on: conversations.topic.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.respond.ask_which.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.respond.ask_which/1   [70 chars]
    en  Which part. Nobody asks that — they just want the whole thing at once.
    >>  ............................................
    pt  Qual parte. Ninguém pergunta isso — só querem tudo de uma vez.
    >>  ............................................
  dialogue.conversations.life.respond.ask_which/2   [68 chars]
    en  ...The middle. The bit I usually skip. You've a good instinct, %1$s.
    >>  ............................................
    pt  ...O meio. A parte que eu costumo pular. Você tem bom instinto, %1$s.
    >>  ............................................
  dialogue.conversations.life.respond.ask_which/3   [51 chars]
    en  Good question. Give me a moment to choose honestly.
    >>  ............................................
    pt  Boa pergunta. Me dá um instante para escolher com honestidade.
    >>  ............................................
```


### Button `empathise` — "That can't have been easy."

*stance family `empathy` · tone `gentle` · answers the beat(s) `life.first.to.life`, `life.revisit.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.respond.empathise` — accepted phrasings: "that cannot have been easy"; "that sounds hard"; "that must have been rough"
  - the message must contain one of: `easy`, `hard`, `rough`
  - scored words: `easy`(1.5), `hard`(1.0), `rough`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.life.respond.empathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.respond.empathise   [26 chars]
    en  That can't have been easy.
    >>  ............................................
    pt  Isso não deve ter sido fácil.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when disposition familiarity >= 45
- Fires when: RULED OUT when the `dispositions` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `life.respond.empathise`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `life.respond.empathise`)_
- Then opens: `conversations.topic.life.followup`
- …where the player's next choices will be: "Tell me more about that part." | "Something like that happened to me." | "Let's leave it there." | "You've had a busier life than the mayor, then." | "That's a life, right enough. I'll go."

```text
POOL   dialogue key: dialogue.conversations.life.respond.empathise.longknown
WHO    VILLAGER — what the player reads after pressing "That can't have been easy."
       spoken on: conversations.topic.life.respond, button `empathise`
       leaves the player on: conversations.topic.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.respond.empathise.longknown.to.life`: the villager accepts. Subject `life`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.respond.empathise.longknown/1   [76 chars]
    en  You'd know. You've been about long enough to have watched half of it happen.
    >>  ............................................
    pt  Você saberia. Está por aqui há tempo bastante para ter visto metade acontecer.
    >>  ............................................
  dialogue.conversations.life.respond.empathise.longknown/2   [77 chars]
    en  It wasn't, and you were there for some of it. That's a strange comfort, %1$s.
    >>  ............................................
    pt  Não foi, e você esteve presente em parte. É um consolo estranho, %1$s.
    >>  ............................................
  dialogue.conversations.life.respond.empathise.longknown/3   [93 chars]
    en  Coming from somebody who's known me this long, that means more than it would from a stranger.
    >>  ............................................
    pt  Vindo de quem me conhece há tanto tempo, isso vale mais do que valeria de um estranho.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when disposition familiarity >= 45  _(chance -2000)_
- Does: **hearts +2** — decision id `life.respond.empathise`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `life.respond.empathise`)_
- Then opens: `conversations.topic.life.followup`
- …where the player's next choices will be: "Tell me more about that part." | "Something like that happened to me." | "Let's leave it there." | "You've had a busier life than the mayor, then." | "That's a life, right enough. I'll go."

```text
POOL   dialogue key: dialogue.conversations.life.respond.empathise
WHO    VILLAGER — what the player reads after pressing "That can't have been easy."
       spoken on: conversations.topic.life.respond, button `empathise`
       leaves the player on: conversations.topic.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.respond.empathise.to.life`: the villager accepts. Subject `life`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.respond.empathise/1   [76 chars]
    en  It wasn't. Thank you for saying so instead of telling me it built character.
    >>  ............................................
    pt  Não foi. Obrigado por dizer isso em vez de me dizer que forjou meu caráter.
    >>  ............................................
  dialogue.conversations.life.respond.empathise/2   [51 chars]
    en  No. And most people hear it and change the subject.
    >>  ............................................
    pt  Não. E a maioria ouve e muda de assunto.
    >>  ............................................
  dialogue.conversations.life.respond.empathise/3   [57 chars]
    en  ...Aye. It's strange, being allowed to say that out loud.
    >>  ............................................
    pt  ...É. É estranho ter permissão para dizer isso em voz alta.
    >>  ............................................
```


### Button `judge` — "You could have done better than that."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `life.first.to.life`, `life.revisit.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.respond.judge` — accepted phrasings: "you could have done better"; "you should have known better"; "that was wrong of you"
  - the message must contain one of: `better`, `should`, `wrong`
  - scored words: `better`(1.5), `should`(1.0), `wrong`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.life.respond.judge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.respond.judge   [37 chars]
    en  You could have done better than that.
    >>  ............................................
    pt  Você podia ter feito melhor que isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `life.respond.judge`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -4, tension +6, trust -2  _(recorded under topic `life.respond.judge`)_
- Does: session `turn`
- Then opens: `conversations.topic.life.rebuked.followup`
- …where the player's next choices will be: "That was mine to hear, not to grade." | "I meant it about the time, not about you." | "I'll not say anything else."

```text
POOL   dialogue key: dialogue.conversations.life.respond.judge
WHO    VILLAGER — what the player reads after pressing "You could have done better than that."
       spoken on: conversations.topic.life.respond, button `judge`
       leaves the player on: conversations.topic.life.rebuked.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.rebuked`: the villager refuses. Subject `life.history`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   this is the line that establishes `player:marked_their_life` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.life.respond.judge/1   [64 chars]
    en  ...I was fourteen, %1$s. What exactly should I have done better?
    >>  ............................................
    pt  ...Eu tinha catorze anos, %1$s. O que exatamente eu devia ter feito melhor?
    >>  ............................................
  dialogue.conversations.life.respond.judge/2   [55 chars]
    en  Everyone could have done better. That's what a life is.
    >>  ............................................
    pt  Todo mundo podia ter feito melhor. É isso que é uma vida.
    >>  ............................................
  dialogue.conversations.life.respond.judge/3   [47 chars]
    en  I told you that in confidence, not for marking.
    >>  ............................................
    pt  Eu te contei isso em confiança, não para ser avaliado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen. I've said worse to myself about it than you just did.
    >>  ............................................
    pt  ...Eu tinha catorze anos. Já disse coisa pior a mim mesmo do que você acabou de dizer.
    >>  ............................................
  anxious.dialogue.conversations.life.respond.judge/2
    en  That's the sentence I've been carrying for twenty years, %1$s. Thank you.
    >>  ............................................
    pt  É a frase que eu carrego há vinte anos, %1$s. Obrigado.
    >>  ............................................
  anxious.dialogue.conversations.life.respond.judge/3
    en  ...Right. Yes. I know. I've always known.
    >>  ............................................
    pt  ...Certo. Sim. Eu sei. Sempre soube.
    >>  ............................................
  athletic.dialogue.conversations.life.respond.judge/1
    en  I was fourteen. Fourteen does what fourteen does.
    >>  ............................................
    pt  Eu tinha catorze anos. Catorze faz o que catorze faz.
    >>  ............................................
  athletic.dialogue.conversations.life.respond.judge/2
    en  ...Aye, well. He did what he could see at the time, and that's all any of us do.
    >>  ............................................
    pt  ...É, bom. Ele fez o que dava pra ver na hora, e é o que todos fazemos.
    >>  ............................................
  athletic.dialogue.conversations.life.respond.judge/3
    en  Right. It's a long while ago and I've made my peace with him.
    >>  ............................................
    pt  Certo. Foi há muito tempo e eu fiz as pazes com ele.
    >>  ............................................
  confident.dialogue.conversations.life.respond.judge/1
    en  I was fourteen. What exactly should I have done better?
    >>  ............................................
    pt  Eu tinha catorze anos. O que exatamente eu devia ter feito melhor?
    >>  ............................................
  confident.dialogue.conversations.life.respond.judge/2
    en  Say that to the fourteen-year-old, not to me. He's not here to answer.
    >>  ............................................
    pt  Diga isso ao menino de catorze, não a mim. Ele não está aqui pra responder.
    >>  ............................................
  confident.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll not tell you the rest of it.
    >>  ............................................
    pt  ...Certo. Não vou te contar o resto.
    >>  ............................................
  crabby.dialogue.conversations.life.respond.judge/1
    en  I was fourteen. What exactly should I have done better?
    >>  ............................................
    pt  Eu tinha catorze anos. O que exatamente eu devia ter feito melhor?
    >>  ............................................
  crabby.dialogue.conversations.life.respond.judge/2
    en  Say that to the fourteen-year-old, not to me. He's not here to answer.
    >>  ............................................
    pt  Diga isso ao menino de catorze, não a mim. Ele não está aqui pra responder.
    >>  ............................................
  crabby.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll not tell you the rest of it.
    >>  ............................................
    pt  ...Certo. Não vou te contar o resto.
    >>  ............................................
  extroverted.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen, %1$s. I told you that so you'd know me, not judge him.
    >>  ............................................
    pt  ...Eu tinha catorze anos, %1$s. Contei pra você me conhecer, não pra julgar ele.
    >>  ............................................
  extroverted.dialogue.conversations.life.respond.judge/2
    en  That's the hardest thing you could have said to that story.
    >>  ............................................
    pt  É a coisa mais dura que você podia dizer a essa história.
    >>  ............................................
  extroverted.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll stop telling you the old ones.
    >>  ............................................
    pt  ...Certo. Vou parar de te contar as antigas.
    >>  ............................................
  flirty.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen, %1$s. I told you that so you'd know me, not judge him.
    >>  ............................................
    pt  ...Eu tinha catorze anos, %1$s. Contei pra você me conhecer, não pra julgar ele.
    >>  ............................................
  flirty.dialogue.conversations.life.respond.judge/2
    en  That's the hardest thing you could have said to that story.
    >>  ............................................
    pt  É a coisa mais dura que você podia dizer a essa história.
    >>  ............................................
  flirty.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll stop telling you the old ones.
    >>  ............................................
    pt  ...Certo. Vou parar de te contar as antigas.
    >>  ............................................
  friendly.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen, %1$s. I told you that so you'd know me, not judge him.
    >>  ............................................
    pt  ...Eu tinha catorze anos, %1$s. Contei pra você me conhecer, não pra julgar ele.
    >>  ............................................
  friendly.dialogue.conversations.life.respond.judge/2
    en  That's the hardest thing you could have said to that story.
    >>  ............................................
    pt  É a coisa mais dura que você podia dizer a essa história.
    >>  ............................................
  friendly.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll stop telling you the old ones.
    >>  ............................................
    pt  ...Certo. Vou parar de te contar as antigas.
    >>  ............................................
  gloomy.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen. I've said worse to myself about it than you just did.
    >>  ............................................
    pt  ...Eu tinha catorze anos. Já disse coisa pior a mim mesmo do que você acabou de dizer.
    >>  ............................................
  gloomy.dialogue.conversations.life.respond.judge/2
    en  That's the sentence I've been carrying for twenty years, %1$s. Thank you.
    >>  ............................................
    pt  É a frase que eu carrego há vinte anos, %1$s. Obrigado.
    >>  ............................................
  gloomy.dialogue.conversations.life.respond.judge/3
    en  ...Right. Yes. I know. I've always known.
    >>  ............................................
    pt  ...Certo. Sim. Eu sei. Sempre soube.
    >>  ............................................
  greedy.dialogue.conversations.life.respond.judge/1
    en  I was fourteen. What exactly should I have done better?
    >>  ............................................
    pt  Eu tinha catorze anos. O que exatamente eu devia ter feito melhor?
    >>  ............................................
  greedy.dialogue.conversations.life.respond.judge/2
    en  Say that to the fourteen-year-old, not to me. He's not here to answer.
    >>  ............................................
    pt  Diga isso ao menino de catorze, não a mim. Ele não está aqui pra responder.
    >>  ............................................
  greedy.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll not tell you the rest of it.
    >>  ............................................
    pt  ...Certo. Não vou te contar o resto.
    >>  ............................................
  grumpy.dialogue.conversations.life.respond.judge/1
    en  I was fourteen. What exactly should I have done better?
    >>  ............................................
    pt  Eu tinha catorze anos. O que exatamente eu devia ter feito melhor?
    >>  ............................................
  grumpy.dialogue.conversations.life.respond.judge/2
    en  Say that to the fourteen-year-old, not to me. He's not here to answer.
    >>  ............................................
    pt  Diga isso ao menino de catorze, não a mim. Ele não está aqui pra responder.
    >>  ............................................
  grumpy.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll not tell you the rest of it.
    >>  ............................................
    pt  ...Certo. Não vou te contar o resto.
    >>  ............................................
  introverted.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen.
    >>  ............................................
    pt  ...Eu tinha catorze anos.
    >>  ............................................
  introverted.dialogue.conversations.life.respond.judge/2
    en  That's not a fair thing to say about a boy.
    >>  ............................................
    pt  Não é justo dizer isso de um menino.
    >>  ............................................
  introverted.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll leave it there.
    >>  ............................................
    pt  ...Certo. Deixo aí.
    >>  ............................................
  lazy.dialogue.conversations.life.respond.judge/1
    en  I was fourteen. Fourteen does what fourteen does.
    >>  ............................................
    pt  Eu tinha catorze anos. Catorze faz o que catorze faz.
    >>  ............................................
  lazy.dialogue.conversations.life.respond.judge/2
    en  ...Aye, well. He did what he could see at the time, and that's all any of us do.
    >>  ............................................
    pt  ...É, bom. Ele fez o que dava pra ver na hora, e é o que todos fazemos.
    >>  ............................................
  lazy.dialogue.conversations.life.respond.judge/3
    en  Right. It's a long while ago and I've made my peace with him.
    >>  ............................................
    pt  Certo. Foi há muito tempo e eu fiz as pazes com ele.
    >>  ............................................
  odd.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen.
    >>  ............................................
    pt  ...Eu tinha catorze anos.
    >>  ............................................
  odd.dialogue.conversations.life.respond.judge/2
    en  That's not a fair thing to say about a boy.
    >>  ............................................
    pt  Não é justo dizer isso de um menino.
    >>  ............................................
  odd.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll leave it there.
    >>  ............................................
    pt  ...Certo. Deixo aí.
    >>  ............................................
  peaceful.dialogue.conversations.life.respond.judge/1
    en  I was fourteen. Fourteen does what fourteen does.
    >>  ............................................
    pt  Eu tinha catorze anos. Catorze faz o que catorze faz.
    >>  ............................................
  peaceful.dialogue.conversations.life.respond.judge/2
    en  ...Aye, well. He did what he could see at the time, and that's all any of us do.
    >>  ............................................
    pt  ...É, bom. Ele fez o que dava pra ver na hora, e é o que todos fazemos.
    >>  ............................................
  peaceful.dialogue.conversations.life.respond.judge/3
    en  Right. It's a long while ago and I've made my peace with him.
    >>  ............................................
    pt  Certo. Foi há muito tempo e eu fiz as pazes com ele.
    >>  ............................................
  peppy.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen, %1$s. Marvellous hindsight you've got there.
    >>  ............................................
    pt  ...Eu tinha catorze anos, %1$s. Que retrospecto maravilhoso o seu.
    >>  ............................................
  peppy.dialogue.conversations.life.respond.judge/2
    en  Right! Yes. Should have handled it better at fourteen. Noted for next time.
    >>  ............................................
    pt  Certo! Sim. Devia ter lidado melhor aos catorze. Anotado pra próxima.
    >>  ............................................
  peppy.dialogue.conversations.life.respond.judge/3
    en  ...Ha. Easy to steer a boat you weren't in.
    >>  ............................................
    pt  ...Ha. Fácil pilotar um barco em que você não estava.
    >>  ............................................
  playful.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen, %1$s. Marvellous hindsight you've got there.
    >>  ............................................
    pt  ...Eu tinha catorze anos, %1$s. Que retrospecto maravilhoso o seu.
    >>  ............................................
  playful.dialogue.conversations.life.respond.judge/2
    en  Right! Yes. Should have handled it better at fourteen. Noted for next time.
    >>  ............................................
    pt  Certo! Sim. Devia ter lidado melhor aos catorze. Anotado pra próxima.
    >>  ............................................
  playful.dialogue.conversations.life.respond.judge/3
    en  ...Ha. Easy to steer a boat you weren't in.
    >>  ............................................
    pt  ...Ha. Fácil pilotar um barco em que você não estava.
    >>  ............................................
  relaxed.dialogue.conversations.life.respond.judge/1
    en  I was fourteen. Fourteen does what fourteen does.
    >>  ............................................
    pt  Eu tinha catorze anos. Catorze faz o que catorze faz.
    >>  ............................................
  relaxed.dialogue.conversations.life.respond.judge/2
    en  ...Aye, well. He did what he could see at the time, and that's all any of us do.
    >>  ............................................
    pt  ...É, bom. Ele fez o que dava pra ver na hora, e é o que todos fazemos.
    >>  ............................................
  relaxed.dialogue.conversations.life.respond.judge/3
    en  Right. It's a long while ago and I've made my peace with him.
    >>  ............................................
    pt  Certo. Foi há muito tempo e eu fiz as pazes com ele.
    >>  ............................................
  sensitive.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen. I've said worse to myself about it than you just did.
    >>  ............................................
    pt  ...Eu tinha catorze anos. Já disse coisa pior a mim mesmo do que você acabou de dizer.
    >>  ............................................
  sensitive.dialogue.conversations.life.respond.judge/2
    en  That's the sentence I've been carrying for twenty years, %1$s. Thank you.
    >>  ............................................
    pt  É a frase que eu carrego há vinte anos, %1$s. Obrigado.
    >>  ............................................
  sensitive.dialogue.conversations.life.respond.judge/3
    en  ...Right. Yes. I know. I've always known.
    >>  ............................................
    pt  ...Certo. Sim. Eu sei. Sempre soube.
    >>  ............................................
  shy.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen.
    >>  ............................................
    pt  ...Eu tinha catorze anos.
    >>  ............................................
  shy.dialogue.conversations.life.respond.judge/2
    en  That's not a fair thing to say about a boy.
    >>  ............................................
    pt  Não é justo dizer isso de um menino.
    >>  ............................................
  shy.dialogue.conversations.life.respond.judge/3
    en  ...Right. I'll leave it there.
    >>  ............................................
    pt  ...Certo. Deixo aí.
    >>  ............................................
  upbeat.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen, %1$s. Marvellous hindsight you've got there.
    >>  ............................................
    pt  ...Eu tinha catorze anos, %1$s. Que retrospecto maravilhoso o seu.
    >>  ............................................
  upbeat.dialogue.conversations.life.respond.judge/2
    en  Right! Yes. Should have handled it better at fourteen. Noted for next time.
    >>  ............................................
    pt  Certo! Sim. Devia ter lidado melhor aos catorze. Anotado pra próxima.
    >>  ............................................
  upbeat.dialogue.conversations.life.respond.judge/3
    en  ...Ha. Easy to steer a boat you weren't in.
    >>  ............................................
    pt  ...Ha. Fácil pilotar um barco em que você não estava.
    >>  ............................................
  witty.dialogue.conversations.life.respond.judge/1
    en  ...I was fourteen, %1$s. Marvellous hindsight you've got there.
    >>  ............................................
    pt  ...Eu tinha catorze anos, %1$s. Que retrospecto maravilhoso o seu.
    >>  ............................................
  witty.dialogue.conversations.life.respond.judge/2
    en  Right! Yes. Should have handled it better at fourteen. Noted for next time.
    >>  ............................................
    pt  Certo! Sim. Devia ter lidado melhor aos catorze. Anotado pra próxima.
    >>  ............................................
  witty.dialogue.conversations.life.respond.judge/3
    en  ...Ha. Easy to steer a boat you weren't in.
    >>  ............................................
    pt  ...Ha. Fácil pilotar um barco em que você não estava.
    >>  ............................................
```

</details>


### Button `no_words` — "I don't know what to say to that."

*stance family `restraint` · tone `gentle` · answers the beat(s) `life.first.to.life`, `life.revisit.to.life`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.respond.no_words` — accepted phrasings: "i have no response to that"; "i do not have a response for that"; "i would not know what to answer"
  - the message must contain one of: `response`, `words`
  - scored words: `response`(1.2), `words`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.life.respond.no_words
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.respond.no_words   [33 chars]
    en  I don't know what to say to that.
    >>  ............................................
    pt  Não sei o que dizer sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.no_words`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `life.no_words`)_
- Then opens: `conversations.topic.life.followup`
- …where the player's next choices will be: "Tell me more about that part." | "Something like that happened to me." | "Let's leave it there." | "You've had a busier life than the mayor, then." | "That's a life, right enough. I'll go."

```text
POOL   dialogue key: dialogue.conversations.life.no_words
WHO    VILLAGER — what the player reads after pressing "I don't know what to say to that."
       spoken on: conversations.topic.life.respond, button `no_words`
       leaves the player on: conversations.topic.life.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.no_words.to.life`: the villager accepts. Subject `life`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.no_words/1   [93 chars]
    en  ...Good. Nobody should have anything ready for a thing like that. Sit there a minute instead.
    >>  ............................................
    pt  ...Ótimo. Ninguém devia ter resposta pronta para uma coisa dessas. Fica aí um minuto em vez disso.
    >>  ............................................
  dialogue.conversations.life.no_words/2   [83 chars]
    en  You don't have to have an answer, %1$s. I've had thirty years and I've not got one.
    >>  ............................................
    pt  Você não precisa ter resposta, %1$s. Eu tive trinta anos e não tenho uma.
    >>  ............................................
  dialogue.conversations.life.no_words/3   [86 chars]
    en  That's more honest than a comfort would have been. Thank you for not reaching for one.
    >>  ............................................
    pt  Isso é mais honesto do que um consolo teria sido. Obrigado por não buscar um.
    >>  ............................................
```


### Button `leave` — "I've kept you long enough."

*stance family `exit` · tone `plain` · answers the beat(s) `life.first.to.life`, `life.revisit.to.life` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.life.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.respond.leave   [26 chars]
    en  I've kept you long enough.
    >>  ............................................
    pt  Já tomei bastante do seu tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.respond.leave
WHO    VILLAGER — what the player reads after pressing "I've kept you long enough."
       spoken on: conversations.topic.life.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.respond.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.life.followup / leave
```

> Written out in full under **`conversations.topic.life.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.life.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `life`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.life.toddler` — e.g. "I'm little! I do puddles and snacks and naps. It's a full day."


```text
POOL   dialogue key: dialogue.conversations.topic.life.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.life.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.life.toddler.respond   [28 chars]
    en  That's my whole life so far.
    >>  ............................................
    pt  É essa a minha vida inteira até agora.
    >>  ............................................
```


### Button `delight` — "That sounds like a very good life."

*stance family `encouragement` · tone `playful` · answers the beat(s) `life.toddler.to.life.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.toddler.delight` — accepted phrasings: "that sounds like a very good life"; "sounds like a good life"; "a very good life"
  - the message must contain one of: `life`, `good`
  - scored words: `life`(1.5), `good`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.life.toddler.respond.delight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.toddler.respond.delight   [34 chars]
    en  That sounds like a very good life.
    >>  ............................................
    pt  Parece uma vida muito boa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.toddler.delight`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `life.toddler.delight`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.toddler.delight
WHO    VILLAGER — what the player reads after pressing "That sounds like a very good life."
       spoken on: conversations.topic.life.toddler.respond, button `delight`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.toddler.delight.terminal`: the villager celebrates. Subject `life.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.toddler.delight/1   [47 chars]
    en  It IS good! I'm having the best one. I checked.
    >>  ............................................
    pt  É boa SIM! Eu tenho a melhor. Eu conferi.
    >>  ............................................
  dialogue.conversations.life.toddler.delight/2   [52 chars]
    en  You think so too! Then it's true, 'cause you're big.
    >>  ............................................
    pt  Você também acha! Então é verdade, porque você é grande.
    >>  ............................................
  dialogue.conversations.life.toddler.delight/3   [39 chars]
    en  A GOOD life. Yes. That's what I've got.
    >>  ............................................
    pt  Uma vida BOA. Isso. É o que eu tenho.
    >>  ............................................
```


### Button `ask` — "What's the best part of a day?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `life.toddler.to.life.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.toddler.ask` — accepted phrasings: "what is the best part of a day"; "best part of the day"; "which part of the day is best"
  - the message must contain one of: `part`, `day`
  - scored words: `part`(1.5), `day`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.life.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.toddler.respond.ask   [30 chars]
    en  What's the best part of a day?
    >>  ............................................
    pt  Qual é a melhor parte do dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `life.toddler.ask`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.toddler.ask
WHO    VILLAGER — what the player reads after pressing "What's the best part of a day?"
       spoken on: conversations.topic.life.toddler.respond, button `ask`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.toddler.ask.terminal`: the villager asks. Subject `life.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.toddler.ask/1   [47 chars]
    en  The middle bit. That's when everything happens.
    >>  ............................................
    pt  A parte do meio. É quando tudo acontece.
    >>  ............................................
  dialogue.conversations.life.toddler.ask/2   [61 chars]
    en  When it's not too early and not bedtime. The in-between part.
    >>  ............................................
    pt  Quando não é cedo demais nem hora de dormir. A parte do meio.
    >>  ............................................
  dialogue.conversations.life.toddler.ask/3   [65 chars]
    en  The part with the snack in it. Obviously the part with the snack.
    >>  ............................................
    pt  A parte com o lanche. Óbvio que é a parte com o lanche.
    >>  ............................................
```


### Button `leave` — "Off you go, then."

*stance family `exit` · tone `plain` · answers the beat(s) `life.toddler.to.life.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.life.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.toddler.respond.leave   [17 chars]
    en  Off you go, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go, then."
       spoken on: conversations.topic.life.toddler.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.toddler.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.toddler.leave/1   [32 chars]
    en  Bye! I've got a lot to do today.
    >>  ............................................
    pt  Tchau! Tenho muita coisa pra fazer hoje.
    >>  ............................................
  dialogue.conversations.life.toddler.leave/2   [15 chars]
    en  Okay bye, %1$s!
    >>  ............................................
    pt  Tá, tchau, %1$s!
    >>  ............................................
  dialogue.conversations.life.toddler.leave/3   [39 chars]
    en  Bye bye. I'll be here. I'm always here.
    >>  ............................................
    pt  Tchau tchau. Eu fico aqui. Eu fico sempre aqui.
    >>  ............................................
```

---


## `conversations.topic.life.young.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `life`; `conversations.cat.personal` / `life`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.life.child` — e.g. "School, chores, and expert-level puddle finding."
- `conversations.life.teen` — e.g. "Chores, mostly. Apparently that's 'life' now."


```text
POOL   dialogue key: dialogue.conversations.topic.life.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.life.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.life.young.respond   [25 chars]
    en  That's me so far, anyway.
    >>  ............................................
    pt  Isso sou eu até agora, enfim.
    >>  ............................................
```


### Button `interested` — "Go on, tell me the long version."

*stance family `curiosity` · tone `plain` · answers the beat(s) `life.child.to.life.young`, `life.teen.to.life.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.young.interested` — accepted phrasings: "tell me properly"; "tell me the whole story"; "go on, properly"
  - the message must contain one of: `properly`, `whole`
  - scored words: `properly`(1.5), `whole`(1.2), `story`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.life.young.respond.interested
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.young.respond.interested   [32 chars]
    en  Go on, tell me the long version.
    >>  ............................................
    pt  Vai, me conta a versão longa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.young.interested`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `life.young.interested`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.young.interested
WHO    VILLAGER — what the player reads after pressing "Go on, tell me the long version."
       spoken on: conversations.topic.life.young.respond, button `interested`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.young.interested.terminal`: the villager accepts. Subject `life.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.young.interested/1   [54 chars]
    en  Really? Right — nobody ever asks for the long version.
    >>  ............................................
    pt  Sério? Certo — ninguém nunca pede a versão longa.
    >>  ............................................
  dialogue.conversations.life.young.interested/2   [38 chars]
    en  You want to hear it properly? Alright!
    >>  ............................................
    pt  Você quer ouvir direito? Beleza!
    >>  ............................................
  dialogue.conversations.life.young.interested/3   [36 chars]
    en  Sit down, then. This takes a minute.
    >>  ............................................
    pt  Senta, então. Isso leva um minuto.
    >>  ............................................
```


### Button `encourage` — "That's a life worth having."

*stance family `encouragement` · tone `plain` · answers the beat(s) `life.child.to.life.young`, `life.teen.to.life.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.young.encourage` — accepted phrasings: "that is worth having"; "that story is worth having"; "worth holding onto"
  - the message must contain one of: `worth`, `having`
  - scored words: `worth`(1.5), `having`(1.0), `story`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.life.young.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.young.respond.encourage   [27 chars]
    en  That's a life worth having.
    >>  ............................................
    pt  É uma vida que vale a pena.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `life.young.encourage`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +2  _(recorded under topic `life.young.encourage`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.young.encourage
WHO    VILLAGER — what the player reads after pressing "That's a life worth having."
       spoken on: conversations.topic.life.young.respond, button `encourage`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.young.encourage.terminal`: the villager accepts. Subject `life.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.young.encourage/1   [35 chars]
    en  ...You think so? Most people laugh.
    >>  ............................................
    pt  ...Você acha? A maioria ri.
    >>  ............................................
  dialogue.conversations.life.young.encourage/2   [41 chars]
    en  It IS worth having. Thanks for saying it.
    >>  ............................................
    pt  VALE a pena mesmo. Obrigado por dizer.
    >>  ............................................
  dialogue.conversations.life.young.encourage/3   [63 chars]
    en  Hm. Alright. Don't make a speech about it or I'll take it back.
    >>  ............................................
    pt  Hm. Tá bom. Não faça discurso sobre isso ou eu retiro o que eu disse.
    >>  ............................................
```


### Button `dismiss` — "You'll see it differently when you're older."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `life.child.to.life.young`, `life.teen.to.life.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `life.young.dismiss` — accepted phrasings: "you will change your mind"; "you will grow out of it"; "you will think differently"
  - the message must contain one of: `change`, `mind`, `grow`
  - scored words: `change`(1.5), `mind`(1.2), `grow`(1.2), `story`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.life.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.young.respond.dismiss   [44 chars]
    en  You'll see it differently when you're older.
    >>  ............................................
    pt  Você vai ver isso diferente quando crescer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `life.young.dismiss`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `life.young.dismiss`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.young.dismiss
WHO    VILLAGER — what the player reads after pressing "You'll see it differently when you're older."
       spoken on: conversations.topic.life.young.respond, button `dismiss`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.young.dismiss.terminal`: the villager dismisss. Subject `life.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.young.dismiss/1   [24 chars]
    en  ...Maybe. But not today.
    >>  ............................................
    pt  ...Talvez. Mas hoje não.
    >>  ............................................
  dialogue.conversations.life.young.dismiss/2   [44 chars]
    en  Everyone says that. Everyone's wrong so far.
    >>  ............................................
    pt  Todo mundo diz isso. Até agora todo mundo errou.
    >>  ............................................
  dialogue.conversations.life.young.dismiss/3   [29 chars]
    en  Fine. Forget I said anything.
    >>  ............................................
    pt  Tá. Esquece que eu falei.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. It doesn't feel like maybe from here, %1$s.
    >>  ............................................
    pt  ...Talvez. Daqui não parece talvez, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.life.young.dismiss/2
    en  Right. Everyone says that and it never helps in the moment.
    >>  ............................................
    pt  Certo. Todo mundo diz isso e nunca ajuda na hora.
    >>  ............................................
  anxious.dialogue.conversations.life.young.dismiss/3
    en  ...I'll wait, then. I've been waiting a while already.
    >>  ............................................
    pt  ...Vou esperar, então. Já espero faz um tempo.
    >>  ............................................
  athletic.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. There's time enough to find out.
    >>  ............................................
    pt  ...Talvez. Tem tempo de sobra pra descobrir.
    >>  ............................................
  athletic.dialogue.conversations.life.young.dismiss/2
    en  Aye, maybe. Most things look different from further along.
    >>  ............................................
    pt  É, talvez. Quase tudo parece diferente de mais adiante.
    >>  ............................................
  athletic.dialogue.conversations.life.young.dismiss/3
    en  ...Right you are. I'll leave it a while.
    >>  ............................................
    pt  ...Você tem razão. Vou deixar um tempo.
    >>  ............................................
  confident.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. But not today.
    >>  ............................................
    pt  ...Talvez. Mas hoje não.
    >>  ............................................
  confident.dialogue.conversations.life.young.dismiss/2
    en  Right. I'll grow out of it, apparently.
    >>  ............................................
    pt  Certo. Vou superar, aparentemente.
    >>  ............................................
  confident.dialogue.conversations.life.young.dismiss/3
    en  ...Then I'll say it again when I'm older and see if it holds.
    >>  ............................................
    pt  ...Então eu digo de novo quando for mais velho e vejo se se sustenta.
    >>  ............................................
  crabby.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. But not today.
    >>  ............................................
    pt  ...Talvez. Mas hoje não.
    >>  ............................................
  crabby.dialogue.conversations.life.young.dismiss/2
    en  Right. I'll grow out of it, apparently.
    >>  ............................................
    pt  Certo. Vou superar, aparentemente.
    >>  ............................................
  crabby.dialogue.conversations.life.young.dismiss/3
    en  ...Then I'll say it again when I'm older and see if it holds.
    >>  ............................................
    pt  ...Então eu digo de novo quando for mais velho e vejo se se sustenta.
    >>  ............................................
  extroverted.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. But not today, %1$s.
    >>  ............................................
    pt  ...Talvez. Mas hoje não, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.life.young.dismiss/2
    en  That's what grown-ups say when they'd rather not answer.
    >>  ............................................
    pt  É o que os adultos dizem quando preferem não responder.
    >>  ............................................
  extroverted.dialogue.conversations.life.young.dismiss/3
    en  ...Right. I'll ask somebody who'll take it seriously.
    >>  ............................................
    pt  ...Certo. Vou perguntar pra alguém que leve a sério.
    >>  ............................................
  flirty.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. But not today, %1$s.
    >>  ............................................
    pt  ...Talvez. Mas hoje não, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.life.young.dismiss/2
    en  That's what grown-ups say when they'd rather not answer.
    >>  ............................................
    pt  É o que os adultos dizem quando preferem não responder.
    >>  ............................................
  flirty.dialogue.conversations.life.young.dismiss/3
    en  ...Right. I'll ask somebody who'll take it seriously.
    >>  ............................................
    pt  ...Certo. Vou perguntar pra alguém que leve a sério.
    >>  ............................................
  friendly.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. But not today, %1$s.
    >>  ............................................
    pt  ...Talvez. Mas hoje não, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.life.young.dismiss/2
    en  That's what grown-ups say when they'd rather not answer.
    >>  ............................................
    pt  É o que os adultos dizem quando preferem não responder.
    >>  ............................................
  friendly.dialogue.conversations.life.young.dismiss/3
    en  ...Right. I'll ask somebody who'll take it seriously.
    >>  ............................................
    pt  ...Certo. Vou perguntar pra alguém que leve a sério.
    >>  ............................................
  gloomy.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. It doesn't feel like maybe from here, %1$s.
    >>  ............................................
    pt  ...Talvez. Daqui não parece talvez, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.life.young.dismiss/2
    en  Right. Everyone says that and it never helps in the moment.
    >>  ............................................
    pt  Certo. Todo mundo diz isso e nunca ajuda na hora.
    >>  ............................................
  gloomy.dialogue.conversations.life.young.dismiss/3
    en  ...I'll wait, then. I've been waiting a while already.
    >>  ............................................
    pt  ...Vou esperar, então. Já espero faz um tempo.
    >>  ............................................
  greedy.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. But not today.
    >>  ............................................
    pt  ...Talvez. Mas hoje não.
    >>  ............................................
  greedy.dialogue.conversations.life.young.dismiss/2
    en  Right. I'll grow out of it, apparently.
    >>  ............................................
    pt  Certo. Vou superar, aparentemente.
    >>  ............................................
  greedy.dialogue.conversations.life.young.dismiss/3
    en  ...Then I'll say it again when I'm older and see if it holds.
    >>  ............................................
    pt  ...Então eu digo de novo quando for mais velho e vejo se se sustenta.
    >>  ............................................
  grumpy.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. But not today.
    >>  ............................................
    pt  ...Talvez. Mas hoje não.
    >>  ............................................
  grumpy.dialogue.conversations.life.young.dismiss/2
    en  Right. I'll grow out of it, apparently.
    >>  ............................................
    pt  Certo. Vou superar, aparentemente.
    >>  ............................................
  grumpy.dialogue.conversations.life.young.dismiss/3
    en  ...Then I'll say it again when I'm older and see if it holds.
    >>  ............................................
    pt  ...Então eu digo de novo quando for mais velho e vejo se se sustenta.
    >>  ............................................
  introverted.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. Not today.
    >>  ............................................
    pt  ...Talvez. Hoje não.
    >>  ............................................
  introverted.dialogue.conversations.life.young.dismiss/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  introverted.dialogue.conversations.life.young.dismiss/3
    en  ...I'll think about it, then.
    >>  ............................................
    pt  ...Vou pensar, então.
    >>  ............................................
  lazy.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. There's time enough to find out.
    >>  ............................................
    pt  ...Talvez. Tem tempo de sobra pra descobrir.
    >>  ............................................
  lazy.dialogue.conversations.life.young.dismiss/2
    en  Aye, maybe. Most things look different from further along.
    >>  ............................................
    pt  É, talvez. Quase tudo parece diferente de mais adiante.
    >>  ............................................
  lazy.dialogue.conversations.life.young.dismiss/3
    en  ...Right you are. I'll leave it a while.
    >>  ............................................
    pt  ...Você tem razão. Vou deixar um tempo.
    >>  ............................................
  odd.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. Not today.
    >>  ............................................
    pt  ...Talvez. Hoje não.
    >>  ............................................
  odd.dialogue.conversations.life.young.dismiss/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  odd.dialogue.conversations.life.young.dismiss/3
    en  ...I'll think about it, then.
    >>  ............................................
    pt  ...Vou pensar, então.
    >>  ............................................
  peaceful.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. There's time enough to find out.
    >>  ............................................
    pt  ...Talvez. Tem tempo de sobra pra descobrir.
    >>  ............................................
  peaceful.dialogue.conversations.life.young.dismiss/2
    en  Aye, maybe. Most things look different from further along.
    >>  ............................................
    pt  É, talvez. Quase tudo parece diferente de mais adiante.
    >>  ............................................
  peaceful.dialogue.conversations.life.young.dismiss/3
    en  ...Right you are. I'll leave it a while.
    >>  ............................................
    pt  ...Você tem razão. Vou deixar um tempo.
    >>  ............................................
  peppy.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe! But not today. Today I'm right.
    >>  ............................................
    pt  ...Talvez! Mas hoje não. Hoje eu tenho razão.
    >>  ............................................
  peppy.dialogue.conversations.life.young.dismiss/2
    en  Right, well. I'll be insufferable about it when I'm proved correct.
    >>  ............................................
    pt  Certo, bom. Vou ser insuportável quando eu for provado certo.
    >>  ............................................
  peppy.dialogue.conversations.life.young.dismiss/3
    en  ...Ha. We'll see. I've plenty of years to be right in.
    >>  ............................................
    pt  ...Ha. A gente vê. Tenho anos de sobra pra ter razão.
    >>  ............................................
  playful.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe! But not today. Today I'm right.
    >>  ............................................
    pt  ...Talvez! Mas hoje não. Hoje eu tenho razão.
    >>  ............................................
  playful.dialogue.conversations.life.young.dismiss/2
    en  Right, well. I'll be insufferable about it when I'm proved correct.
    >>  ............................................
    pt  Certo, bom. Vou ser insuportável quando eu for provado certo.
    >>  ............................................
  playful.dialogue.conversations.life.young.dismiss/3
    en  ...Ha. We'll see. I've plenty of years to be right in.
    >>  ............................................
    pt  ...Ha. A gente vê. Tenho anos de sobra pra ter razão.
    >>  ............................................
  relaxed.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. There's time enough to find out.
    >>  ............................................
    pt  ...Talvez. Tem tempo de sobra pra descobrir.
    >>  ............................................
  relaxed.dialogue.conversations.life.young.dismiss/2
    en  Aye, maybe. Most things look different from further along.
    >>  ............................................
    pt  É, talvez. Quase tudo parece diferente de mais adiante.
    >>  ............................................
  relaxed.dialogue.conversations.life.young.dismiss/3
    en  ...Right you are. I'll leave it a while.
    >>  ............................................
    pt  ...Você tem razão. Vou deixar um tempo.
    >>  ............................................
  sensitive.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. It doesn't feel like maybe from here, %1$s.
    >>  ............................................
    pt  ...Talvez. Daqui não parece talvez, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.life.young.dismiss/2
    en  Right. Everyone says that and it never helps in the moment.
    >>  ............................................
    pt  Certo. Todo mundo diz isso e nunca ajuda na hora.
    >>  ............................................
  sensitive.dialogue.conversations.life.young.dismiss/3
    en  ...I'll wait, then. I've been waiting a while already.
    >>  ............................................
    pt  ...Vou esperar, então. Já espero faz um tempo.
    >>  ............................................
  shy.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe. Not today.
    >>  ............................................
    pt  ...Talvez. Hoje não.
    >>  ............................................
  shy.dialogue.conversations.life.young.dismiss/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  shy.dialogue.conversations.life.young.dismiss/3
    en  ...I'll think about it, then.
    >>  ............................................
    pt  ...Vou pensar, então.
    >>  ............................................
  upbeat.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe! But not today. Today I'm right.
    >>  ............................................
    pt  ...Talvez! Mas hoje não. Hoje eu tenho razão.
    >>  ............................................
  upbeat.dialogue.conversations.life.young.dismiss/2
    en  Right, well. I'll be insufferable about it when I'm proved correct.
    >>  ............................................
    pt  Certo, bom. Vou ser insuportável quando eu for provado certo.
    >>  ............................................
  upbeat.dialogue.conversations.life.young.dismiss/3
    en  ...Ha. We'll see. I've plenty of years to be right in.
    >>  ............................................
    pt  ...Ha. A gente vê. Tenho anos de sobra pra ter razão.
    >>  ............................................
  witty.dialogue.conversations.life.young.dismiss/1
    en  ...Maybe! But not today. Today I'm right.
    >>  ............................................
    pt  ...Talvez! Mas hoje não. Hoje eu tenho razão.
    >>  ............................................
  witty.dialogue.conversations.life.young.dismiss/2
    en  Right, well. I'll be insufferable about it when I'm proved correct.
    >>  ............................................
    pt  Certo, bom. Vou ser insuportável quando eu for provado certo.
    >>  ............................................
  witty.dialogue.conversations.life.young.dismiss/3
    en  ...Ha. We'll see. I've plenty of years to be right in.
    >>  ............................................
    pt  ...Ha. A gente vê. Tenho anos de sobra pra ter razão.
    >>  ............................................
```

</details>


### Button `leave` — "That'll do for now. Off you go."

*stance family `exit` · tone `plain` · answers the beat(s) `life.child.to.life.young`, `life.teen.to.life.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.life.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.life.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.life.young.respond.leave   [31 chars]
    en  That'll do for now. Off you go.
    >>  ............................................
    pt  Por hoje serve. Pode ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.young.leave
WHO    VILLAGER — what the player reads after pressing "That'll do for now. Off you go."
       spoken on: conversations.topic.life.young.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.young.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.young.leave/1   [9 chars]
    en  Bye then!
    >>  ............................................
    pt  Tchau então!
    >>  ............................................
  dialogue.conversations.life.young.leave/2   [14 chars]
    en  See you, %1$s.
    >>  ............................................
    pt  Até mais, %1$s.
    >>  ............................................
  dialogue.conversations.life.young.leave/3   [10 chars]
    en  Okay. Bye.
    >>  ............................................
    pt  Tá. Tchau.
    >>  ............................................
```

---

