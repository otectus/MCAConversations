# Topic: regrets

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `regrets` |
| Opened from | question `conversations.cat.personal`, button `regrets` |
| Depth class (its heart budget) | `deep` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | adult |
| Stance families it must offer | `empathy`, `restraint`, `challenge`, `boundary_push`, `self_disclosure`, `exit` |
| Narrative arc | `regrets`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.regrets
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.regrets   [24 chars]
    en  Do you have any regrets?
    >>  ............................................
    pt  Você se arrepende de alguma coisa?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.regrets.resume.followup`](#conversations-arc-regrets-resume-followup)
- [`conversations.arc.regrets.resume.respond`](#conversations-arc-regrets-resume-respond)
- [`conversations.scene.regrets.followup`](#conversations-scene-regrets-followup)
- [`conversations.scene.regrets.the_old_one.respond`](#conversations-scene-regrets-the-old-one-respond)
- [`conversations.scene.regrets.the_small_one.respond`](#conversations-scene-regrets-the-small-one-respond)
- [`conversations.topic.regrets.close`](#conversations-topic-regrets-close)
- [`conversations.topic.regrets.followup`](#conversations-topic-regrets-followup)
- [`conversations.topic.regrets.guarded.respond`](#conversations-topic-regrets-guarded-respond)
- [`conversations.topic.regrets.pried.followup`](#conversations-topic-regrets-pried-followup)
- [`conversations.topic.regrets.respond`](#conversations-topic-regrets-respond)
- [`conversations.topic.regrets.sit_with_it`](#conversations-topic-regrets-sit-with-it)

---

## `conversations.arc.regrets.resume.followup`

**Reached from 4 route(s):** `conversations.arc.regrets.resume.respond` / `ask_since`; `conversations.arc.regrets.resume.respond` / `ask_since`; `conversations.arc.regrets.resume.respond` / `ask_since`; `conversations.arc.regrets.resume.respond` / `encourage_repair`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.regrets.resume.ask_since.forgave` — e.g. "It has, a little. You told me I wasn't a bad person and it stuck."
- `conversations.regrets.resume.ask_since.listened` — e.g. "A little. You didn't tell me it was fine, and that's why I believed the rest."
- `conversations.regrets.resume.ask_since.plain` — e.g. "A little. Time does some of it. Being asked after does the rest."
- `conversations.regrets.resume.encourage_repair` — e.g. "...I have, since you asked last. Thinking's most of the way there."


```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.regrets.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.regrets.resume.followup   [21 chars]
    en  That's where it sits.
    >>  ............................................
    pt  É aí que isso fica.
    >>  ............................................
```


### Button `absolve` — "You've paid enough for it."

*stance family `empathy` · tone `gentle` · answers the beat(s) `regrets.resume.ask_since.forgave.to.regrets`, `regrets.resume.ask_since.listened.to.regrets`, `regrets.resume.ask_since.plain.to.regrets`, `regrets.resume.encourage_repair.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.resume.followup.absolve` — accepted phrasings: "you have paid enough for it"; "you have paid enough already"; "that is paid for enough"
  - the message must contain one of: `paid`, `enough`
  - scored words: `paid`(1.6), `enough`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.followup.absolve
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.regrets.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.regrets.resume.followup.absolve   [26 chars]
    en  You've paid enough for it.
    >>  ............................................
    pt  Você já pagou o bastante por isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.resume.followup.absolve`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +3  _(recorded under topic `regrets.resume.followup.absolve`)_
- Then opens: `conversations.topic.regrets.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I've one of my own, if it helps." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.followup.absolve
WHO    VILLAGER — what the player reads after pressing "You've paid enough for it."
       spoken on: conversations.arc.regrets.resume.followup, button `absolve`
       leaves the player on: conversations.topic.regrets.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.followup.absolve.to.regrets`: the villager accepts. Subject `regrets`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.resume.followup.absolve/1   [84 chars]
    en  Enough. Who decides that, though? ...You, apparently. Alright. I'll try it your way.
    >>  ............................................
    pt  O bastante. Mas quem decide isso? ...Você, pelo visto. Tudo bem. Vou tentar do seu jeito.
    >>  ............................................
  dialogue.conversations.regrets.resume.followup.absolve/2   [92 chars]
    en  I've been paying by instalments for years. Somebody calling it settled is a strange feeling.
    >>  ............................................
    pt  Venho pagando em parcelas há anos. Alguém dizer que está quitado é uma sensação estranha.
    >>  ............................................
  dialogue.conversations.regrets.resume.followup.absolve/3   [54 chars]
    en  That's not yours to say, %1$s. ...Say it again anyway.
    >>  ............................................
    pt  Não é você quem diz isso, %1$s. ...Diga de novo mesmo assim.
    >>  ............................................
```


### Button `practical` — "What would putting it right look like?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `regrets.resume.ask_since.forgave.to.regrets`, `regrets.resume.ask_since.listened.to.regrets`, `regrets.resume.ask_since.plain.to.regrets`, `regrets.resume.encourage_repair.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.resume.followup.practical` — accepted phrasings: "what would putting it right look like"; "what would that look like put right"; "how would putting it right look"
  - the message must contain one of: `look`
  - scored words: `right`(1.2), `look`(1.4)

```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.followup.practical
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.regrets.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.regrets.resume.followup.practical   [38 chars]
    en  What would putting it right look like?
    >>  ............................................
    pt  Como seria consertar isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.resume.followup.practical`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `regrets.resume.followup.practical`)_
- Then opens: `conversations.topic.regrets.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I've one of my own, if it helps." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.followup.practical
WHO    VILLAGER — what the player reads after pressing "What would putting it right look like?"
       spoken on: conversations.arc.regrets.resume.followup, button `practical`
       leaves the player on: conversations.topic.regrets.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.followup.practical.to.regrets`: the villager accepts. Subject `regrets`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.resume.followup.practical/1   [89 chars]
    en  What it'd look like. Aye — that's the question I've been walking around for a long while.
    >>  ............................................
    pt  Como seria. É — essa é a pergunta que eu venho rodeando faz tempo.
    >>  ............................................
  dialogue.conversations.regrets.resume.followup.practical/2   [72 chars]
    en  Knocking on a door, mostly. It's a very short list and a very heavy one.
    >>  ............................................
    pt  Bater numa porta, principalmente. É uma lista muito curta e muito pesada.
    >>  ............................................
  dialogue.conversations.regrets.resume.followup.practical/3   [96 chars]
    en  Saying it to the right person instead of to you. ...No offence meant. You've been good practice.
    >>  ............................................
    pt  Dizer para a pessoa certa em vez de dizer para você. ...Sem ofensa. Você foi um bom ensaio.
    >>  ............................................
```


### Button `hold` — "It's not mine to forgive."

*stance family `restraint` · tone `gentle` · answers the beat(s) `regrets.resume.ask_since.forgave.to.regrets`, `regrets.resume.ask_since.listened.to.regrets`, `regrets.resume.ask_since.plain.to.regrets`, `regrets.resume.encourage_repair.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.resume.followup.hold` — accepted phrasings: "it is not mine to forgive"; "that is not mine to forgive"; "i am not the one who forgives that"
  - the message must contain one of: `mine`, `forgive`
  - scored words: `mine`(1.6), `forgive`(1.3)

```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.followup.hold
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.regrets.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.regrets.resume.followup.hold   [25 chars]
    en  It's not mine to forgive.
    >>  ............................................
    pt  Não sou eu quem perdoa isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, tension +3  _(recorded under topic `regrets.resume.followup.hold`)_
- Then opens: `conversations.topic.regrets.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I've one of my own, if it helps." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.followup.hold
WHO    VILLAGER — what the player reads after pressing "It's not mine to forgive."
       spoken on: conversations.arc.regrets.resume.followup, button `hold`
       leaves the player on: conversations.topic.regrets.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.followup.hold.to.regrets`: the villager accepts. Subject `regrets`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.resume.followup.hold/1   [86 chars]
    en  No. It isn't. Plenty take the forgiving job just to end the conversation — you didn't.
    >>  ............................................
    pt  Não. Não é. Muita gente aceita o cargo de perdoar só para encerrar a conversa — você não.
    >>  ............................................
  dialogue.conversations.regrets.resume.followup.hold/2   [91 chars]
    en  That's honest, and it leaves the thing where it belongs. I respect it and I don't enjoy it.
    >>  ............................................
    pt  É honesto, e deixa a coisa onde ela pertence. Eu respeito e não gosto.
    >>  ............................................
  dialogue.conversations.regrets.resume.followup.hold/3   [74 chars]
    en  You're right, %1$s. It's theirs. I'd been hoping you'd hand it over cheap.
    >>  ............................................
    pt  Você tem razão, %1$s. É deles. Eu estava torcendo para você entregar barato.
    >>  ............................................
```


### Button `leave` — "I'll leave it be."

*stance family `exit` · tone `plain` · answers the beat(s) `regrets.resume.ask_since.forgave.to.regrets`, `regrets.resume.ask_since.listened.to.regrets`, `regrets.resume.ask_since.plain.to.regrets`, `regrets.resume.encourage_repair.to.regrets` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.regrets.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.regrets.resume.followup.leave   [17 chars]
    en  I'll leave it be.
    >>  ............................................
    pt  Vou deixar quieto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it be."
       spoken on: conversations.arc.regrets.resume.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.followup.leave.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.resume.followup.leave/1   [47 chars]
    en  True enough. Leave it be. It's good at waiting.
    >>  ............................................
    pt  Bem verdade. Deixa quieto. Isso sabe esperar bem.
    >>  ............................................
  dialogue.conversations.regrets.resume.followup.leave/2   [26 chars]
    en  Right you are. Off you go.
    >>  ............................................
    pt  Isso mesmo. Pode ir.
    >>  ............................................
  dialogue.conversations.regrets.resume.followup.leave/3   [51 chars]
    en  Go on, %1$s. Thank you for not making a meal of it.
    >>  ............................................
    pt  Vai lá, %1$s. Obrigado por não fazer disso um drama.
    >>  ............................................
```

---


## `conversations.arc.regrets.resume.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `regrets`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.regrets.revisit` — e.g. "That thing I told you about. It's still there, mostly."


```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.regrets.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.regrets.resume.respond   [31 chars]
    en  That thing I told you I regret.
    >>  ............................................
    pt  Aquilo de que eu te disse que me arrependo.
    >>  ............................................
```


### Button `ask_since` — "Has it sat any easier since?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `regrets.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.resume.ask_since` — accepted phrasings: "has it sat any easier since"; "is it easier now"; "any easier since we spoke"
  - the message must contain one of: `easier`, `sat`, `since`
  - scored words: `easier`(1.5), `sat`(1.2), `since`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.respond.ask_since
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.regrets.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.regrets.resume.respond.ask_since   [28 chars]
    en  Has it sat any easier since?
    >>  ............................................
    pt  Ficou mais leve desde então?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when exclusive `regrets.stance` is `forgave`
- Does: **hearts +2** — decision id `regrets.resume.ask_since`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `regrets.resume.ask_since`)_
- Does: arc `regrets` — advance to stage 2
- Then opens: `conversations.arc.regrets.resume.followup`
- …where the player's next choices will be: "You've paid enough for it." | "What would putting it right look like?" | "It's not mine to forgive." | "I'll leave it be."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.ask_since.forgave
WHO    VILLAGER — what the player reads after pressing "Has it sat any easier since?"
       spoken on: conversations.arc.regrets.resume.respond, button `ask_since`
       leaves the player on: conversations.arc.regrets.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.ask_since.forgave.to.regrets`: the villager accepts. Subject `regrets`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.resume.ask_since.forgave/1   [65 chars]
    en  It has, a little. You told me I wasn't a bad person and it stuck.
    >>  ............................................
    pt  Ficou, um pouco. Você me disse que eu não era má pessoa e isso ficou.
    >>  ............................................
  dialogue.conversations.regrets.resume.ask_since.forgave/2   [62 chars]
    en  Easier, aye. Somebody deciding I was worth forgiving did that.
    >>  ............................................
    pt  Mais leve, é. Alguém decidir que eu valia perdão fez isso.
    >>  ............................................
  dialogue.conversations.regrets.resume.ask_since.forgave/3   [67 chars]
    en  Since you said what you said — yes. Odd how much a sentence weighs.
    >>  ............................................
    pt  Desde que você disse o que disse — sim. Estranho como uma frase pesa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. You told me I wasn't a bad person and it stuck, %1$s.
    >>  ............................................
    pt  Um pouco. Você me disse que eu não era má pessoa e ficou, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. I take it out and look at it on the bad evenings, which is more use than you'd think.
    >>  ............................................
    pt  Algo. Eu tiro e olho pra isso nas noites ruins, o que serve mais do que se imagina.
    >>  ............................................
  anxious.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. One sentence, kept somewhere safe. That's what the difference has been made of.
    >>  ............................................
    pt  Um pouco. Uma frase, guardada em lugar seguro. É disso que a diferença foi feita.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. What you said stuck, and things that stick tend to keep sticking.
    >>  ............................................
    pt  Um pouco. O que você disse ficou, e o que fica costuma continuar ficando.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. Slowly. That's the only rate at which this sort of thing moves.
    >>  ............................................
    pt  Algo. Devagar. É a única velocidade em que esse tipo de coisa anda.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. Ask me again in a year and there may be a little more.
    >>  ............................................
    pt  Um pouco. Me pergunte em um ano e pode ter um pouco mais.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. You told me I wasn't a bad person and it stuck.
    >>  ............................................
    pt  Um pouco. Você me disse que eu não era má pessoa e ficou.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed, which I'd not have predicted.
    >>  ............................................
    pt  Algo. O que você disse ficou, o que eu não teria previsto.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. The thing you said has held better than I expected.
    >>  ............................................
    pt  Um pouco. O que você disse se sustentou melhor do que eu esperava.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. You told me I wasn't a bad person and it stuck.
    >>  ............................................
    pt  Um pouco. Você me disse que eu não era má pessoa e ficou.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed, which I'd not have predicted.
    >>  ............................................
    pt  Algo. O que você disse ficou, o que eu não teria previsto.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. The thing you said has held better than I expected.
    >>  ............................................
    pt  Um pouco. O que você disse se sustentou melhor do que eu esperava.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little, %1$s. You told me I wasn't a bad person and it stuck.
    >>  ............................................
    pt  Um pouco, %1$s. Você me disse que eu não era má pessoa e ficou.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed with me, and I'd like you to know that it did.
    >>  ............................................
    pt  Algo. O que você disse ficou comigo, e eu queria que você soubesse.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. It's mostly the one sentence, and it was yours.
    >>  ............................................
    pt  Um pouco. É quase toda uma frase só, e era sua.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little, %1$s. You told me I wasn't a bad person and it stuck.
    >>  ............................................
    pt  Um pouco, %1$s. Você me disse que eu não era má pessoa e ficou.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed with me, and I'd like you to know that it did.
    >>  ............................................
    pt  Algo. O que você disse ficou comigo, e eu queria que você soubesse.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. It's mostly the one sentence, and it was yours.
    >>  ............................................
    pt  Um pouco. É quase toda uma frase só, e era sua.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little, %1$s. You told me I wasn't a bad person and it stuck.
    >>  ............................................
    pt  Um pouco, %1$s. Você me disse que eu não era má pessoa e ficou.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed with me, and I'd like you to know that it did.
    >>  ............................................
    pt  Algo. O que você disse ficou comigo, e eu queria que você soubesse.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. It's mostly the one sentence, and it was yours.
    >>  ............................................
    pt  Um pouco. É quase toda uma frase só, e era sua.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. You told me I wasn't a bad person and it stuck, %1$s.
    >>  ............................................
    pt  Um pouco. Você me disse que eu não era má pessoa e ficou, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. I take it out and look at it on the bad evenings, which is more use than you'd think.
    >>  ............................................
    pt  Algo. Eu tiro e olho pra isso nas noites ruins, o que serve mais do que se imagina.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. One sentence, kept somewhere safe. That's what the difference has been made of.
    >>  ............................................
    pt  Um pouco. Uma frase, guardada em lugar seguro. É disso que a diferença foi feita.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. You told me I wasn't a bad person and it stuck.
    >>  ............................................
    pt  Um pouco. Você me disse que eu não era má pessoa e ficou.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed, which I'd not have predicted.
    >>  ............................................
    pt  Algo. O que você disse ficou, o que eu não teria previsto.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. The thing you said has held better than I expected.
    >>  ............................................
    pt  Um pouco. O que você disse se sustentou melhor do que eu esperava.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. You told me I wasn't a bad person and it stuck.
    >>  ............................................
    pt  Um pouco. Você me disse que eu não era má pessoa e ficou.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed, which I'd not have predicted.
    >>  ............................................
    pt  Algo. O que você disse ficou, o que eu não teria previsto.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. The thing you said has held better than I expected.
    >>  ............................................
    pt  Um pouco. O que você disse se sustentou melhor do que eu esperava.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. What you said stuck.
    >>  ............................................
    pt  Um pouco. O que você disse ficou.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. It stayed.
    >>  ............................................
    pt  Algo. Ficou.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. Mostly the one sentence.
    >>  ............................................
    pt  Um pouco. Quase toda uma frase só.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. What you said stuck, and things that stick tend to keep sticking.
    >>  ............................................
    pt  Um pouco. O que você disse ficou, e o que fica costuma continuar ficando.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. Slowly. That's the only rate at which this sort of thing moves.
    >>  ............................................
    pt  Algo. Devagar. É a única velocidade em que esse tipo de coisa anda.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. Ask me again in a year and there may be a little more.
    >>  ............................................
    pt  Um pouco. Me pergunte em um ano e pode ter um pouco mais.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. What you said stuck.
    >>  ............................................
    pt  Um pouco. O que você disse ficou.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. It stayed.
    >>  ............................................
    pt  Algo. Ficou.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. Mostly the one sentence.
    >>  ............................................
    pt  Um pouco. Quase toda uma frase só.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. What you said stuck, and things that stick tend to keep sticking.
    >>  ............................................
    pt  Um pouco. O que você disse ficou, e o que fica costuma continuar ficando.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. Slowly. That's the only rate at which this sort of thing moves.
    >>  ............................................
    pt  Algo. Devagar. É a única velocidade em que esse tipo de coisa anda.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. Ask me again in a year and there may be a little more.
    >>  ............................................
    pt  Um pouco. Me pergunte em um ano e pode ter um pouco mais.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little! You told me I wasn't a bad person and it stuck. Annoyingly.
    >>  ............................................
    pt  Um pouco! Você me disse que eu não era má pessoa e ficou. Irritantemente.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed. I'd expected it to wear off by Tuesday.
    >>  ............................................
    pt  Algo. O que você disse ficou. Eu esperava que passasse até terça.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little! Your sentence has outlasted several of mine.
    >>  ............................................
    pt  Um pouco! A sua frase durou mais que várias das minhas.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little! You told me I wasn't a bad person and it stuck. Annoyingly.
    >>  ............................................
    pt  Um pouco! Você me disse que eu não era má pessoa e ficou. Irritantemente.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed. I'd expected it to wear off by Tuesday.
    >>  ............................................
    pt  Algo. O que você disse ficou. Eu esperava que passasse até terça.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little! Your sentence has outlasted several of mine.
    >>  ............................................
    pt  Um pouco! A sua frase durou mais que várias das minhas.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. What you said stuck, and things that stick tend to keep sticking.
    >>  ............................................
    pt  Um pouco. O que você disse ficou, e o que fica costuma continuar ficando.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. Slowly. That's the only rate at which this sort of thing moves.
    >>  ............................................
    pt  Algo. Devagar. É a única velocidade em que esse tipo de coisa anda.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. Ask me again in a year and there may be a little more.
    >>  ............................................
    pt  Um pouco. Me pergunte em um ano e pode ter um pouco mais.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. You told me I wasn't a bad person and it stuck, %1$s.
    >>  ............................................
    pt  Um pouco. Você me disse que eu não era má pessoa e ficou, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. I take it out and look at it on the bad evenings, which is more use than you'd think.
    >>  ............................................
    pt  Algo. Eu tiro e olho pra isso nas noites ruins, o que serve mais do que se imagina.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. One sentence, kept somewhere safe. That's what the difference has been made of.
    >>  ............................................
    pt  Um pouco. Uma frase, guardada em lugar seguro. É disso que a diferença foi feita.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little. What you said stuck.
    >>  ............................................
    pt  Um pouco. O que você disse ficou.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. It stayed.
    >>  ............................................
    pt  Algo. Ficou.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little. Mostly the one sentence.
    >>  ............................................
    pt  Um pouco. Quase toda uma frase só.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little! You told me I wasn't a bad person and it stuck. Annoyingly.
    >>  ............................................
    pt  Um pouco! Você me disse que eu não era má pessoa e ficou. Irritantemente.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed. I'd expected it to wear off by Tuesday.
    >>  ............................................
    pt  Algo. O que você disse ficou. Eu esperava que passasse até terça.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little! Your sentence has outlasted several of mine.
    >>  ............................................
    pt  Um pouco! A sua frase durou mais que várias das minhas.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.forgave/1
    en  It has, a little! You told me I wasn't a bad person and it stuck. Annoyingly.
    >>  ............................................
    pt  Um pouco! Você me disse que eu não era má pessoa e ficou. Irritantemente.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.forgave/2
    en  Some. What you said stayed. I'd expected it to wear off by Tuesday.
    >>  ............................................
    pt  Algo. O que você disse ficou. Eu esperava que passasse até terça.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.forgave/3
    en  A little! Your sentence has outlasted several of mine.
    >>  ............................................
    pt  Um pouco! A sua frase durou mais que várias das minhas.
    >>  ............................................
```

</details>


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when exclusive `regrets.stance` is `listened`
- Does: **hearts +2** — decision id `regrets.resume.ask_since`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `regrets.resume.ask_since`)_
- Does: arc `regrets` — advance to stage 2
- Then opens: `conversations.arc.regrets.resume.followup`
- …where the player's next choices will be: "You've paid enough for it." | "What would putting it right look like?" | "It's not mine to forgive." | "I'll leave it be."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.ask_since.listened
WHO    VILLAGER — what the player reads after pressing "Has it sat any easier since?"
       spoken on: conversations.arc.regrets.resume.respond, button `ask_since`
       leaves the player on: conversations.arc.regrets.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.ask_since.listened.to.regrets`: the villager accepts. Subject `regrets`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.resume.ask_since.listened/1   [77 chars]
    en  A little. You didn't tell me it was fine, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem, e por isso acreditei no resto.
    >>  ............................................
  dialogue.conversations.regrets.resume.ask_since.listened/2   [67 chars]
    en  Easier. Not lighter — easier. There's a difference and you knew it.
    >>  ............................................
    pt  Mais fácil. Não mais leve — mais fácil. Tem diferença e você sabia.
    >>  ............................................
  dialogue.conversations.regrets.resume.ask_since.listened/3   [64 chars]
    en  You sat with it instead of fixing it. That's what let it settle.
    >>  ............................................
    pt  Você ficou com isso em vez de consertar. Foi isso que deixou assentar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine, %1$s, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem, %1$s, e por isso eu acreditei no resto.
    >>  ............................................
  anxious.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. Everyone else says it's fine. It isn't fine, and being told so would have ended it.
    >>  ............................................
    pt  Algo. Todo mundo diz que está tudo bem. Não está, e ouvir isso teria encerrado.
    >>  ............................................
  anxious.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. You let it be as bad as it was, and that's what made it smaller.
    >>  ............................................
    pt  Um pouco. Você deixou ser tão ruim quanto era, e foi isso que deixou menor.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine. That kind of honesty keeps working.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem. Esse tipo de honestidade continua funcionando.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. Slowly, over months, which is the only way it goes.
    >>  ............................................
    pt  Algo. Devagar, ao longo de meses, que é o único jeito.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. It'll be a little more next year. That's how it has always gone.
    >>  ............................................
    pt  Um pouco. Vai ser um pouco mais ano que vem. Sempre foi assim.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened instead of ruling on it. That was the useful part.
    >>  ............................................
    pt  Algo. Você escutou em vez de julgar. Foi a parte útil.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything, which is what let it move.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada, e foi isso que deixou andar.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened instead of ruling on it. That was the useful part.
    >>  ............................................
    pt  Algo. Você escutou em vez de julgar. Foi a parte útil.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything, which is what let it move.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada, e foi isso que deixou andar.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little, %1$s. You didn't tell me it was fine, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco, %1$s. Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened. I've thought about that evening more than the thing itself.
    >>  ............................................
    pt  Algo. Você escutou. Pensei mais naquela noite do que na coisa em si.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. And it's because of how you took it, not what you said.
    >>  ............................................
    pt  Um pouco. E é por como você recebeu, não pelo que você disse.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little, %1$s. You didn't tell me it was fine, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco, %1$s. Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened. I've thought about that evening more than the thing itself.
    >>  ............................................
    pt  Algo. Você escutou. Pensei mais naquela noite do que na coisa em si.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. And it's because of how you took it, not what you said.
    >>  ............................................
    pt  Um pouco. E é por como você recebeu, não pelo que você disse.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little, %1$s. You didn't tell me it was fine, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco, %1$s. Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened. I've thought about that evening more than the thing itself.
    >>  ............................................
    pt  Algo. Você escutou. Pensei mais naquela noite do que na coisa em si.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. And it's because of how you took it, not what you said.
    >>  ............................................
    pt  Um pouco. E é por como você recebeu, não pelo que você disse.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine, %1$s, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem, %1$s, e por isso eu acreditei no resto.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. Everyone else says it's fine. It isn't fine, and being told so would have ended it.
    >>  ............................................
    pt  Algo. Todo mundo diz que está tudo bem. Não está, e ouvir isso teria encerrado.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. You let it be as bad as it was, and that's what made it smaller.
    >>  ............................................
    pt  Um pouco. Você deixou ser tão ruim quanto era, e foi isso que deixou menor.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened instead of ruling on it. That was the useful part.
    >>  ............................................
    pt  Algo. Você escutou em vez de julgar. Foi a parte útil.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything, which is what let it move.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada, e foi isso que deixou andar.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened instead of ruling on it. That was the useful part.
    >>  ............................................
    pt  Algo. Você escutou em vez de julgar. Foi a parte útil.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything, which is what let it move.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada, e foi isso que deixou andar.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened.
    >>  ............................................
    pt  Algo. Você escutou.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine. That kind of honesty keeps working.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem. Esse tipo de honestidade continua funcionando.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. Slowly, over months, which is the only way it goes.
    >>  ............................................
    pt  Algo. Devagar, ao longo de meses, que é o único jeito.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. It'll be a little more next year. That's how it has always gone.
    >>  ............................................
    pt  Um pouco. Vai ser um pouco mais ano que vem. Sempre foi assim.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened.
    >>  ............................................
    pt  Algo. Você escutou.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine. That kind of honesty keeps working.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem. Esse tipo de honestidade continua funcionando.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. Slowly, over months, which is the only way it goes.
    >>  ............................................
    pt  Algo. Devagar, ao longo de meses, que é o único jeito.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. It'll be a little more next year. That's how it has always gone.
    >>  ............................................
    pt  Um pouco. Vai ser um pouco mais ano que vem. Sempre foi assim.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little! You didn't tell me it was fine, which is why I believed the rest of it.
    >>  ............................................
    pt  Um pouco! Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened rather than ruling on it. Refreshingly restrained of you.
    >>  ............................................
    pt  Algo. Você escutou em vez de julgar. Refrescantemente contido da sua parte.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything and somehow that's what let it shift.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada e de algum jeito foi isso que deixou mudar.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little! You didn't tell me it was fine, which is why I believed the rest of it.
    >>  ............................................
    pt  Um pouco! Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened rather than ruling on it. Refreshingly restrained of you.
    >>  ............................................
    pt  Algo. Você escutou em vez de julgar. Refrescantemente contido da sua parte.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything and somehow that's what let it shift.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada e de algum jeito foi isso que deixou mudar.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine. That kind of honesty keeps working.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem. Esse tipo de honestidade continua funcionando.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. Slowly, over months, which is the only way it goes.
    >>  ............................................
    pt  Algo. Devagar, ao longo de meses, que é o único jeito.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. It'll be a little more next year. That's how it has always gone.
    >>  ............................................
    pt  Um pouco. Vai ser um pouco mais ano que vem. Sempre foi assim.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine, %1$s, and that's why I believed the rest.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem, %1$s, e por isso eu acreditei no resto.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. Everyone else says it's fine. It isn't fine, and being told so would have ended it.
    >>  ............................................
    pt  Algo. Todo mundo diz que está tudo bem. Não está, e ouvir isso teria encerrado.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. You let it be as bad as it was, and that's what made it smaller.
    >>  ............................................
    pt  Um pouco. Você deixou ser tão ruim quanto era, e foi isso que deixou menor.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little. You didn't tell me it was fine.
    >>  ............................................
    pt  Um pouco. Você não disse que estava tudo bem.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened.
    >>  ............................................
    pt  Algo. Você escutou.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little! You didn't tell me it was fine, which is why I believed the rest of it.
    >>  ............................................
    pt  Um pouco! Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened rather than ruling on it. Refreshingly restrained of you.
    >>  ............................................
    pt  Algo. Você escutou em vez de julgar. Refrescantemente contido da sua parte.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything and somehow that's what let it shift.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada e de algum jeito foi isso que deixou mudar.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.listened/1
    en  A little! You didn't tell me it was fine, which is why I believed the rest of it.
    >>  ............................................
    pt  Um pouco! Você não disse que estava tudo bem, e por isso eu acreditei no resto.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.listened/2
    en  Some. You listened rather than ruling on it. Refreshingly restrained of you.
    >>  ............................................
    pt  Algo. Você escutou em vez de julgar. Refrescantemente contido da sua parte.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.listened/3
    en  A little. Nobody fixed anything and somehow that's what let it shift.
    >>  ............................................
    pt  Um pouco. Ninguém consertou nada e de algum jeito foi isso que deixou mudar.
    >>  ............................................
```

</details>


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when exclusive `regrets.stance` is `forgave`  _(chance -2000)_
- Fires when: RULED OUT when exclusive `regrets.stance` is `listened`  _(chance -2000)_
- Does: disposition — warmth +2  _(recorded under topic `regrets.resume.ask_since`)_
- Does: arc `regrets` — advance to stage 2
- Then opens: `conversations.arc.regrets.resume.followup`
- …where the player's next choices will be: "You've paid enough for it." | "What would putting it right look like?" | "It's not mine to forgive." | "I'll leave it be."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.ask_since.plain
WHO    VILLAGER — what the player reads after pressing "Has it sat any easier since?"
       spoken on: conversations.arc.regrets.resume.respond, button `ask_since`
       leaves the player on: conversations.arc.regrets.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.ask_since.plain.to.regrets`: the villager accepts. Subject `regrets`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.resume.ask_since.plain/1   [64 chars]
    en  A little. Time does some of it. Being asked after does the rest.
    >>  ............................................
    pt  Um pouco. O tempo faz uma parte. Alguém perguntar faz o resto.
    >>  ............................................
  dialogue.conversations.regrets.resume.ask_since.plain/2   [72 chars]
    en  Some days. It's the sort of thing that goes quiet rather than goes away.
    >>  ............................................
    pt  Tem dia. É o tipo de coisa que fica quieta em vez de ir embora.
    >>  ............................................
  dialogue.conversations.regrets.resume.ask_since.plain/3   [67 chars]
    en  Easier than it was, %1$s. I'd not have noticed if you hadn't asked.
    >>  ............................................
    pt  Mais fácil do que era, %1$s. Eu não teria reparado se você não tivesse perguntado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it, %1$s. Being asked after does the rest, and nobody asks.
    >>  ............................................
    pt  Um pouco. O tempo faz parte, %1$s. Ser perguntado faz o resto, e ninguém pergunta.
    >>  ............................................
  anxious.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Less than I'd hoped by now. That's why I don't bring it up unprompted.
    >>  ............................................
    pt  Algo. Menos do que eu esperava a esta altura. Por isso eu não toco no assunto sozinho.
    >>  ............................................
  anxious.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It sits differently on the days somebody has asked.
    >>  ............................................
    pt  Um pouco. Fica diferente nos dias em que alguém perguntou.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it, and time is the part I have plenty of.
    >>  ............................................
    pt  Um pouco. O tempo faz parte, e tempo é o que eu tenho de sobra.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. It'll be some more next year. That's the honest rate of it.
    >>  ............................................
    pt  Algo. Vai ser mais algo ano que vem. É a taxa honesta.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Slowly, and slowly is the only speed grief and guilt have ever had.
    >>  ............................................
    pt  Um pouco. Devagar, e devagar é a única velocidade que luto e culpa já tiveram.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it. Being asked after does the rest.
    >>  ............................................
    pt  Um pouco. O tempo faz parte. Ser perguntado faz o resto.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Mostly time. The asking helps more than I'd have said.
    >>  ............................................
    pt  Algo. Principalmente tempo. Perguntar ajuda mais do que eu diria.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It's not the sort of thing that moves quickly.
    >>  ............................................
    pt  Um pouco. Não é o tipo de coisa que anda rápido.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it. Being asked after does the rest.
    >>  ............................................
    pt  Um pouco. O tempo faz parte. Ser perguntado faz o resto.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Mostly time. The asking helps more than I'd have said.
    >>  ............................................
    pt  Algo. Principalmente tempo. Perguntar ajuda mais do que eu diria.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It's not the sort of thing that moves quickly.
    >>  ............................................
    pt  Um pouco. Não é o tipo de coisa que anda rápido.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little, %1$s. Time does some of it. Being asked after does the rest.
    >>  ............................................
    pt  Um pouco, %1$s. O tempo faz parte. Ser perguntado faz o resto.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. And you asking is genuinely part of it, which I'd not have expected.
    >>  ............................................
    pt  Algo. E você perguntar é genuinamente parte disso, o que eu não esperava.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Ask me again some time — apparently that's how it moves.
    >>  ............................................
    pt  Um pouco. Me pergunte de novo um dia — aparentemente é assim que anda.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little, %1$s. Time does some of it. Being asked after does the rest.
    >>  ............................................
    pt  Um pouco, %1$s. O tempo faz parte. Ser perguntado faz o resto.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. And you asking is genuinely part of it, which I'd not have expected.
    >>  ............................................
    pt  Algo. E você perguntar é genuinamente parte disso, o que eu não esperava.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Ask me again some time — apparently that's how it moves.
    >>  ............................................
    pt  Um pouco. Me pergunte de novo um dia — aparentemente é assim que anda.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little, %1$s. Time does some of it. Being asked after does the rest.
    >>  ............................................
    pt  Um pouco, %1$s. O tempo faz parte. Ser perguntado faz o resto.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. And you asking is genuinely part of it, which I'd not have expected.
    >>  ............................................
    pt  Algo. E você perguntar é genuinamente parte disso, o que eu não esperava.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Ask me again some time — apparently that's how it moves.
    >>  ............................................
    pt  Um pouco. Me pergunte de novo um dia — aparentemente é assim que anda.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it, %1$s. Being asked after does the rest, and nobody asks.
    >>  ............................................
    pt  Um pouco. O tempo faz parte, %1$s. Ser perguntado faz o resto, e ninguém pergunta.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Less than I'd hoped by now. That's why I don't bring it up unprompted.
    >>  ............................................
    pt  Algo. Menos do que eu esperava a esta altura. Por isso eu não toco no assunto sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It sits differently on the days somebody has asked.
    >>  ............................................
    pt  Um pouco. Fica diferente nos dias em que alguém perguntou.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it. Being asked after does the rest.
    >>  ............................................
    pt  Um pouco. O tempo faz parte. Ser perguntado faz o resto.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Mostly time. The asking helps more than I'd have said.
    >>  ............................................
    pt  Algo. Principalmente tempo. Perguntar ajuda mais do que eu diria.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It's not the sort of thing that moves quickly.
    >>  ............................................
    pt  Um pouco. Não é o tipo de coisa que anda rápido.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it. Being asked after does the rest.
    >>  ............................................
    pt  Um pouco. O tempo faz parte. Ser perguntado faz o resto.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Mostly time. The asking helps more than I'd have said.
    >>  ............................................
    pt  Algo. Principalmente tempo. Perguntar ajuda mais do que eu diria.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It's not the sort of thing that moves quickly.
    >>  ............................................
    pt  Um pouco. Não é o tipo de coisa que anda rápido.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it.
    >>  ............................................
    pt  Um pouco. O tempo faz parte.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Being asked after does the rest.
    >>  ............................................
    pt  Algo. Ser perguntado faz o resto.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Slowly.
    >>  ............................................
    pt  Um pouco. Devagar.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it, and time is the part I have plenty of.
    >>  ............................................
    pt  Um pouco. O tempo faz parte, e tempo é o que eu tenho de sobra.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. It'll be some more next year. That's the honest rate of it.
    >>  ............................................
    pt  Algo. Vai ser mais algo ano que vem. É a taxa honesta.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Slowly, and slowly is the only speed grief and guilt have ever had.
    >>  ............................................
    pt  Um pouco. Devagar, e devagar é a única velocidade que luto e culpa já tiveram.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it.
    >>  ............................................
    pt  Um pouco. O tempo faz parte.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Being asked after does the rest.
    >>  ............................................
    pt  Algo. Ser perguntado faz o resto.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Slowly.
    >>  ............................................
    pt  Um pouco. Devagar.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it, and time is the part I have plenty of.
    >>  ............................................
    pt  Um pouco. O tempo faz parte, e tempo é o que eu tenho de sobra.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. It'll be some more next year. That's the honest rate of it.
    >>  ............................................
    pt  Algo. Vai ser mais algo ano que vem. É a taxa honesta.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Slowly, and slowly is the only speed grief and guilt have ever had.
    >>  ............................................
    pt  Um pouco. Devagar, e devagar é a única velocidade que luto e culpa já tiveram.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little! Time does some of it. Being asked after does the rest, apparently.
    >>  ............................................
    pt  Um pouco! O tempo faz parte. Ser perguntado faz o resto, aparentemente.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Mostly time. But the asking counts, and nobody asks.
    >>  ............................................
    pt  Algo. Principalmente tempo. Mas perguntar conta, e ninguém pergunta.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It moves at the speed of a glacier and a glacier does get there.
    >>  ............................................
    pt  Um pouco. Anda na velocidade de uma geleira e uma geleira chega lá.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little! Time does some of it. Being asked after does the rest, apparently.
    >>  ............................................
    pt  Um pouco! O tempo faz parte. Ser perguntado faz o resto, aparentemente.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Mostly time. But the asking counts, and nobody asks.
    >>  ............................................
    pt  Algo. Principalmente tempo. Mas perguntar conta, e ninguém pergunta.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It moves at the speed of a glacier and a glacier does get there.
    >>  ............................................
    pt  Um pouco. Anda na velocidade de uma geleira e uma geleira chega lá.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it, and time is the part I have plenty of.
    >>  ............................................
    pt  Um pouco. O tempo faz parte, e tempo é o que eu tenho de sobra.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. It'll be some more next year. That's the honest rate of it.
    >>  ............................................
    pt  Algo. Vai ser mais algo ano que vem. É a taxa honesta.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Slowly, and slowly is the only speed grief and guilt have ever had.
    >>  ............................................
    pt  Um pouco. Devagar, e devagar é a única velocidade que luto e culpa já tiveram.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it, %1$s. Being asked after does the rest, and nobody asks.
    >>  ............................................
    pt  Um pouco. O tempo faz parte, %1$s. Ser perguntado faz o resto, e ninguém pergunta.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Less than I'd hoped by now. That's why I don't bring it up unprompted.
    >>  ............................................
    pt  Algo. Menos do que eu esperava a esta altura. Por isso eu não toco no assunto sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It sits differently on the days somebody has asked.
    >>  ............................................
    pt  Um pouco. Fica diferente nos dias em que alguém perguntou.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little. Time does some of it.
    >>  ............................................
    pt  Um pouco. O tempo faz parte.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Being asked after does the rest.
    >>  ............................................
    pt  Algo. Ser perguntado faz o resto.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. Slowly.
    >>  ............................................
    pt  Um pouco. Devagar.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little! Time does some of it. Being asked after does the rest, apparently.
    >>  ............................................
    pt  Um pouco! O tempo faz parte. Ser perguntado faz o resto, aparentemente.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Mostly time. But the asking counts, and nobody asks.
    >>  ............................................
    pt  Algo. Principalmente tempo. Mas perguntar conta, e ninguém pergunta.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It moves at the speed of a glacier and a glacier does get there.
    >>  ............................................
    pt  Um pouco. Anda na velocidade de uma geleira e uma geleira chega lá.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.plain/1
    en  A little! Time does some of it. Being asked after does the rest, apparently.
    >>  ............................................
    pt  Um pouco! O tempo faz parte. Ser perguntado faz o resto, aparentemente.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.plain/2
    en  Some. Mostly time. But the asking counts, and nobody asks.
    >>  ............................................
    pt  Algo. Principalmente tempo. Mas perguntar conta, e ninguém pergunta.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.ask_since.plain/3
    en  A little. It moves at the speed of a glacier and a glacier does get there.
    >>  ............................................
    pt  Um pouco. Anda na velocidade de uma geleira e uma geleira chega lá.
    >>  ............................................
```

</details>


### Button `encourage_repair` — "Have you thought any more about putting it right?"

*stance family `encouragement` · tone `plain` · answers the beat(s) `regrets.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.resume.encourage_repair` — accepted phrasings: "have you thought any more about putting it right"; "thought more about it"; "any more thought about fixing it"
  - the message must contain one of: `thought`, `putting`
  - scored words: `thought`(1.5), `putting`(1.2), `more`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.respond.encourage_repair
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.regrets.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.regrets.resume.respond.encourage_repair   [49 chars]
    en  Have you thought any more about putting it right?
    >>  ............................................
    pt  Você pensou mais em consertar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `regrets.resume.encourage_repair`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `regrets.resume.encourage_repair`)_
- Does: arc `regrets` — advance to stage 2
- Then opens: `conversations.arc.regrets.resume.followup`
- …where the player's next choices will be: "You've paid enough for it." | "What would putting it right look like?" | "It's not mine to forgive." | "I'll leave it be."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.encourage_repair
WHO    VILLAGER — what the player reads after pressing "Have you thought any more about putting it right?"
       spoken on: conversations.arc.regrets.resume.respond, button `encourage_repair`
       leaves the player on: conversations.arc.regrets.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.encourage_repair.to.regrets`: the villager accepts. Subject `regrets`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.resume.encourage_repair/1   [66 chars]
    en  ...I have, since you asked last. Thinking's most of the way there.
    >>  ............................................
    pt  ...Pensei, desde que você perguntou. Pensar já é metade do caminho.
    >>  ............................................
  dialogue.conversations.regrets.resume.encourage_repair/2   [65 chars]
    en  You're going to keep asking, aren't you. Good. I need someone to.
    >>  ............................................
    pt  Você vai continuar perguntando, né. Bom. Preciso que alguém pergunte.
    >>  ............................................
  dialogue.conversations.regrets.resume.encourage_repair/3   [50 chars]
    en  A little. It's closer to a plan than it was, %1$s.
    >>  ............................................
    pt  Um pouco. Está mais perto de um plano do que estava, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last, %1$s. Thinking's most of the way there, or so I tell myself.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou, %1$s. Pensar é quase o caminho todo, ou é o que eu digo.
    >>  ............................................
  anxious.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Doing it is a different country and I've not booked passage.
    >>  ............................................
    pt  Eu pensei nisso. Fazer é outro país e eu não comprei passagem.
    >>  ............................................
  anxious.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. That's the only reason. I'd have let it lie otherwise.
    >>  ............................................
    pt  Desde que você perguntou, sim. É a única razão. Senão eu teria deixado quieto.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last. Thinking's most of the way there and the rest takes years.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou. Pensar é quase o caminho e o resto leva anos.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. It'll get done when it's ready to be done and not before.
    >>  ............................................
    pt  Eu pensei nisso. Vai ser feito quando estiver pronto e não antes.
    >>  ............................................
  athletic.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. Slowly. That's the only way this one can go.
    >>  ............................................
    pt  Desde que você perguntou, sim. Devagar. É o único jeito deste caso.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last. Thinking's most of the way there.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou. Pensar é quase o caminho todo.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. That's not doing it, and it isn't nothing either.
    >>  ............................................
    pt  Eu pensei nisso. Não é fazer, e também não é nada.
    >>  ............................................
  confident.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. I've got as far as thinking.
    >>  ............................................
    pt  Desde que você perguntou, sim. Cheguei até o pensar.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last. Thinking's most of the way there.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou. Pensar é quase o caminho todo.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. That's not doing it, and it isn't nothing either.
    >>  ............................................
    pt  Eu pensei nisso. Não é fazer, e também não é nada.
    >>  ............................................
  crabby.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. I've got as far as thinking.
    >>  ............................................
    pt  Desde que você perguntou, sim. Cheguei até o pensar.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last, %1$s. Thinking's most of the way there.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou, %1$s. Pensar é quase o caminho todo.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Your asking is why. I'd not have got there on my own.
    >>  ............................................
    pt  Eu pensei nisso. Você perguntar é o motivo. Eu não teria chegado lá sozinho.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. Ask me once more and I might have done something.
    >>  ............................................
    pt  Desde que você perguntou, sim. Pergunte mais uma vez e eu posso ter feito algo.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last, %1$s. Thinking's most of the way there.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou, %1$s. Pensar é quase o caminho todo.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Your asking is why. I'd not have got there on my own.
    >>  ............................................
    pt  Eu pensei nisso. Você perguntar é o motivo. Eu não teria chegado lá sozinho.
    >>  ............................................
  flirty.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. Ask me once more and I might have done something.
    >>  ............................................
    pt  Desde que você perguntou, sim. Pergunte mais uma vez e eu posso ter feito algo.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last, %1$s. Thinking's most of the way there.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou, %1$s. Pensar é quase o caminho todo.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Your asking is why. I'd not have got there on my own.
    >>  ............................................
    pt  Eu pensei nisso. Você perguntar é o motivo. Eu não teria chegado lá sozinho.
    >>  ............................................
  friendly.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. Ask me once more and I might have done something.
    >>  ............................................
    pt  Desde que você perguntou, sim. Pergunte mais uma vez e eu posso ter feito algo.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last, %1$s. Thinking's most of the way there, or so I tell myself.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou, %1$s. Pensar é quase o caminho todo, ou é o que eu digo.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Doing it is a different country and I've not booked passage.
    >>  ............................................
    pt  Eu pensei nisso. Fazer é outro país e eu não comprei passagem.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. That's the only reason. I'd have let it lie otherwise.
    >>  ............................................
    pt  Desde que você perguntou, sim. É a única razão. Senão eu teria deixado quieto.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last. Thinking's most of the way there.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou. Pensar é quase o caminho todo.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. That's not doing it, and it isn't nothing either.
    >>  ............................................
    pt  Eu pensei nisso. Não é fazer, e também não é nada.
    >>  ............................................
  greedy.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. I've got as far as thinking.
    >>  ............................................
    pt  Desde que você perguntou, sim. Cheguei até o pensar.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last. Thinking's most of the way there.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou. Pensar é quase o caminho todo.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. That's not doing it, and it isn't nothing either.
    >>  ............................................
    pt  Eu pensei nisso. Não é fazer, e também não é nada.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. I've got as far as thinking.
    >>  ............................................
    pt  Desde que você perguntou, sim. Cheguei até o pensar.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. That's as far as I've got.
    >>  ............................................
    pt  Eu pensei nisso. É até onde eu cheguei.
    >>  ............................................
  introverted.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes.
    >>  ............................................
    pt  Desde que você perguntou, sim.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last. Thinking's most of the way there and the rest takes years.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou. Pensar é quase o caminho e o resto leva anos.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. It'll get done when it's ready to be done and not before.
    >>  ............................................
    pt  Eu pensei nisso. Vai ser feito quando estiver pronto e não antes.
    >>  ............................................
  lazy.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. Slowly. That's the only way this one can go.
    >>  ............................................
    pt  Desde que você perguntou, sim. Devagar. É o único jeito deste caso.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. That's as far as I've got.
    >>  ............................................
    pt  Eu pensei nisso. É até onde eu cheguei.
    >>  ............................................
  odd.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes.
    >>  ............................................
    pt  Desde que você perguntou, sim.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last. Thinking's most of the way there and the rest takes years.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou. Pensar é quase o caminho e o resto leva anos.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. It'll get done when it's ready to be done and not before.
    >>  ............................................
    pt  Eu pensei nisso. Vai ser feito quando estiver pronto e não antes.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. Slowly. That's the only way this one can go.
    >>  ............................................
    pt  Desde que você perguntou, sim. Devagar. É o único jeito deste caso.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last! Thinking's most of the way there. Allegedly.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou! Pensar é quase o caminho todo. Supostamente.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Extensively. Thinking is my finest skill and my worst habit.
    >>  ............................................
    pt  Eu pensei nisso. Extensivamente. Pensar é minha melhor habilidade e meu pior hábito.
    >>  ............................................
  peppy.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes! I've got as far as thinking and no further, which is very me.
    >>  ............................................
    pt  Desde que você perguntou, sim! Cheguei até o pensar e não além, o que é bem eu.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last! Thinking's most of the way there. Allegedly.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou! Pensar é quase o caminho todo. Supostamente.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Extensively. Thinking is my finest skill and my worst habit.
    >>  ............................................
    pt  Eu pensei nisso. Extensivamente. Pensar é minha melhor habilidade e meu pior hábito.
    >>  ............................................
  playful.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes! I've got as far as thinking and no further, which is very me.
    >>  ............................................
    pt  Desde que você perguntou, sim! Cheguei até o pensar e não além, o que é bem eu.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last. Thinking's most of the way there and the rest takes years.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou. Pensar é quase o caminho e o resto leva anos.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. It'll get done when it's ready to be done and not before.
    >>  ............................................
    pt  Eu pensei nisso. Vai ser feito quando estiver pronto e não antes.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. Slowly. That's the only way this one can go.
    >>  ............................................
    pt  Desde que você perguntou, sim. Devagar. É o único jeito deste caso.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last, %1$s. Thinking's most of the way there, or so I tell myself.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou, %1$s. Pensar é quase o caminho todo, ou é o que eu digo.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Doing it is a different country and I've not booked passage.
    >>  ............................................
    pt  Eu pensei nisso. Fazer é outro país e eu não comprei passagem.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes. That's the only reason. I'd have let it lie otherwise.
    >>  ............................................
    pt  Desde que você perguntou, sim. É a única razão. Senão eu teria deixado quieto.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. That's as far as I've got.
    >>  ............................................
    pt  Eu pensei nisso. É até onde eu cheguei.
    >>  ............................................
  shy.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes.
    >>  ............................................
    pt  Desde que você perguntou, sim.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last! Thinking's most of the way there. Allegedly.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou! Pensar é quase o caminho todo. Supostamente.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Extensively. Thinking is my finest skill and my worst habit.
    >>  ............................................
    pt  Eu pensei nisso. Extensivamente. Pensar é minha melhor habilidade e meu pior hábito.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes! I've got as far as thinking and no further, which is very me.
    >>  ............................................
    pt  Desde que você perguntou, sim! Cheguei até o pensar e não além, o que é bem eu.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.encourage_repair/1
    en  I have, since you asked last! Thinking's most of the way there. Allegedly.
    >>  ............................................
    pt  Eu pensei, desde a última vez que você perguntou! Pensar é quase o caminho todo. Supostamente.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.encourage_repair/2
    en  I've thought about it. Extensively. Thinking is my finest skill and my worst habit.
    >>  ............................................
    pt  Eu pensei nisso. Extensivamente. Pensar é minha melhor habilidade e meu pior hábito.
    >>  ............................................
  witty.dialogue.conversations.regrets.resume.encourage_repair/3
    en  Since you asked, yes! I've got as far as thinking and no further, which is very me.
    >>  ............................................
    pt  Desde que você perguntou, sim! Cheguei até o pensar e não além, o que é bem eu.
    >>  ............................................
```

</details>


### Button `bring_it_up` — "So about that thing you did."

*stance family `curiosity` · tone `blunt` · answers the beat(s) `regrets.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.resume.bring_it_up` — accepted phrasings: "about that thing you did"; "remember that thing you did"; "that thing you did"
  - the message must contain one of: `thing`, `did`, `remember`
  - scored words: `about`(0.4), `thing`(1.0), `did`(1.2), `remember`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.respond.bring_it_up
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.regrets.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.regrets.resume.respond.bring_it_up   [28 chars]
    en  So about that thing you did.
    >>  ............................................
    pt  Sobre aquela coisa que você fez.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `regrets.resume.bring_it_up`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +6, trust -4  _(recorded under topic `regrets.resume.bring_it_up`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.bring_it_up
WHO    VILLAGER — what the player reads after pressing "So about that thing you did."
       spoken on: conversations.arc.regrets.resume.respond, button `bring_it_up`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.bring_it_up.terminal`: the villager accepts. Subject `regrets.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.resume.bring_it_up/1   [40 chars]
    en  ...You said you'd not use it against me.
    >>  ............................................
    pt  ...Você disse que não usaria isso contra mim.
    >>  ............................................
  dialogue.conversations.regrets.resume.bring_it_up/2   [44 chars]
    en  That was told to you, %1$s. Not handed over.
    >>  ............................................
    pt  Aquilo foi contado a você, %1$s. Não entregue.
    >>  ............................................
  dialogue.conversations.regrets.resume.bring_it_up/3   [60 chars]
    en  I regret telling you more than I regret the thing, just now.
    >>  ............................................
    pt  Neste momento me arrependo mais de ter contado do que da coisa em si.
    >>  ............................................
```


### Button `leave` — "I'll leave it be."

*stance family `exit` · tone `plain` · answers the beat(s) `regrets.revisit.opens` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.regrets.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.regrets.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.regrets.resume.respond.leave   [17 chars]
    en  I'll leave it be.
    >>  ............................................
    pt  Vou deixar quieto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it be."
       spoken on: conversations.arc.regrets.resume.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.resume.leave.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.resume.leave/1   [29 chars]
    en  Quite. Best left where it is.
    >>  ............................................
    pt  Exato. Melhor deixar onde está.
    >>  ............................................
  dialogue.conversations.regrets.resume.leave/2   [50 chars]
    en  Thank you, %1$s. For coming back and for stopping.
    >>  ............................................
    pt  Obrigado, %1$s. Por ter voltado e por ter parado.
    >>  ............................................
  dialogue.conversations.regrets.resume.leave/3   [12 chars]
    en  Enough said.
    >>  ............................................
    pt  Já foi dito.
    >>  ............................................
```

---


## `conversations.scene.regrets.followup`

**Reached from 4 route(s):** `conversations.scene.regrets.the_old_one.respond` / `sit_with_it`; `conversations.scene.regrets.the_old_one.respond` / `ask_what_they_would_do`; `conversations.scene.regrets.the_small_one.respond` / `say_it_is_fixable`; `conversations.scene.regrets.the_small_one.respond` / `ask_about_the_friendship`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.regrets.the_old_one.answered` — e.g. "Stay in the room four minutes longer. That is the whole of it and I have run it about a thousand times."
- `conversations.scene.regrets.the_old_one.steadied` — e.g. "It is, and it has got lighter about twice, both times because somebody heard it and left it where it was."
- `conversations.scene.regrets.the_small_one.considered` — e.g. "The swimming is. I have thought that every summer for twenty years and this is the first time anybody has said it to me."
- `conversations.scene.regrets.the_small_one.explained` — e.g. "We speak. We speak about weather and prices and we have not spoken about anything else in six years."


```text
POOL   dialogue key: dialogue.conversations.scene.regrets.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.regrets.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.regrets.followup   [25 chars]
    en  Anything else about that?
    >>  ............................................
    pt  Mais alguma coisa sobre isso?
    >>  ............................................
```


### Button `leave` — "We'll leave the rest alone."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:regrets.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.regrets.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.regrets.followup.leave   [27 chars]
    en  We'll leave the rest alone.
    >>  ............................................
    pt  Deixamos o resto quieto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave the rest alone."
       spoken on: conversations.scene.regrets.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.scene.leaving`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.regrets.the_old_one.respond / leave; conversations.scene.regrets.the_small_one.respond / leave
```

```text
  dialogue.conversations.scene.regrets.leaving/1   [29 chars]
    en  It is done and it stays done.
    >>  ............................................
    pt  Está feito e continua feito.
    >>  ............................................
  dialogue.conversations.scene.regrets.leaving/2   [36 chars]
    en  Right. No use turning it over again.
    >>  ............................................
    pt  Certo. Não adianta remoer de novo.
    >>  ............................................
  dialogue.conversations.scene.regrets.leaving/3   [25 chars]
    en  That is where I leave it.
    >>  ............................................
    pt  É aí que eu deixo.
    >>  ............................................
```

---


## `conversations.scene.regrets.the_old_one.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `regrets`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.regrets.the_old_one` — e.g. "One conversation, about nine years ago, that I ended too well. Ending it badly would have left a door."


```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_old_one.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.regrets.the_old_one.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.regrets.the_old_one.respond   [26 chars]
    en  Something you'd take back.
    >>  ............................................
    pt  Algo que você desfaria.
    >>  ............................................
```


### Button `sit_with_it` — "That's a heavy one to carry."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `regrets.the_old_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.regrets.the_old_one.sit_with_it` — accepted phrasings: "thats a heavy one to carry"; "that is a heavy one to carry"; "that has weight to it"
  - the message must contain one of: `heavy`, `weight`, `carry`
  - scored words: `heavy`(1.8), `weight`(1.8), `carry`(1.8), `thats`(0.8), `one`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_old_one.respond.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.regrets.the_old_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.regrets.the_old_one.respond.sit_with_it   [28 chars]
    en  That's a heavy one to carry.
    >>  ............................................
    pt  Essa é pesada de carregar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.regrets.held`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, warmth +4  _(recorded under topic `regrets.long_carried`)_
- Does: session `turn`
- Then opens: `conversations.scene.regrets.followup`
- …where the player's next choices will be: "We'll leave the rest alone."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_old_one.steadied
WHO    VILLAGER — what the player reads after pressing "That's a heavy one to carry."
       spoken on: conversations.scene.regrets.the_old_one.respond, button `sit_with_it`
       leaves the player on: conversations.scene.regrets.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.the_old_one.open.steadied`: the villager accepts. Subject `regrets.long_carried`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:regrets` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.regrets.the_old_one.steadied/1   [105 chars]
    en  It is, and it has got lighter about twice, both times because somebody heard it and left it where it was.
    >>  ............................................
    pt  É, e ficou mais leve umas duas vezes, as duas porque alguém ouviu e deixou onde estava.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_old_one.steadied/2   [98 chars]
    en  Thank you. The version where you tell me it was for the best is the version I have had four times.
    >>  ............................................
    pt  Obrigada. A versão em que você me diz que foi melhor assim é a versão que eu já ouvi quatro vezes.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_old_one.steadied/3   [103 chars]
    en  I do not want it fixed. I want it to have been said in a room with somebody else in it, and now it has.
    >>  ............................................
    pt  Não quero que seja consertado. Quero que tenha sido dito numa sala com outra pessoa dentro, e agora foi.
    >>  ............................................
```


### Button `ask_what_they_would_do` — "What would you do differently?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `regrets.the_old_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.regrets.the_old_one.ask_what_they_would_do` — accepted phrasings: "what would you do differently"; "what would you do differently"; "how would you do it now"
  - the message must contain one of: `differently`, `now`
  - scored words: `differently`(1.8), `now`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_old_one.respond.ask_what_they_would_do
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.regrets.the_old_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.regrets.the_old_one.respond.ask_what_they_would_do   [30 chars]
    en  What would you do differently?
    >>  ............................................
    pt  O que você faria diferente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `regrets.long_carried`)_
- Does: session `turn`
- Then opens: `conversations.scene.regrets.followup`
- …where the player's next choices will be: "We'll leave the rest alone."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_old_one.answered
WHO    VILLAGER — what the player reads after pressing "What would you do differently?"
       spoken on: conversations.scene.regrets.the_old_one.respond, button `ask_what_they_would_do`
       leaves the player on: conversations.scene.regrets.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.the_old_one.open.answered`: the villager explains. Subject `regrets.long_carried`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:regrets` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.regrets.the_old_one.answered/1   [103 chars]
    en  Stay in the room four minutes longer. That is the whole of it and I have run it about a thousand times.
    >>  ............................................
    pt  Ficar na sala mais quatro minutos. É tudo, e eu já refiz isso umas mil vezes.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_old_one.answered/2   [114 chars]
    en  Ask one question instead of assuming the answer. I was twenty-nine and I thought assuming was the same as knowing.
    >>  ............................................
    pt  Fazer uma pergunta em vez de supor a resposta. Eu tinha vinte e nove anos e achava que supor era o mesmo que saber.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_old_one.answered/3   [116 chars]
    en  Nothing, honestly. I would do it again with the same information and that is the part that is actually hard to hold.
    >>  ............................................
    pt  Nada, sinceramente. Eu faria de novo com a mesma informação, e é essa a parte de fato difícil de segurar.
    >>  ............................................
```


### Button `leave` — "Thank you for saying it."

*stance family `exit` · tone `plain` · answers the beat(s) `regrets.the_old_one.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_old_one.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.regrets.the_old_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.regrets.the_old_one.respond.leave   [24 chars]
    en  Thank you for saying it.
    >>  ............................................
    pt  Obrigado por dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for saying it."
       spoken on: conversations.scene.regrets.the_old_one.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.scene.leaving`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.regrets.followup / leave; conversations.scene.regrets.the_small_one.respond / leave
```

> Written out in full under **`conversations.scene.regrets.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.regrets.the_small_one.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `regrets`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.regrets.the_small_one` — e.g. "I should have learned to swim. Everybody in this village can and I made a joke of it at fifteen and here we are."


```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_small_one.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.regrets.the_small_one.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.regrets.the_small_one.respond   [22 chars]
    en  Anything you'd change?
    >>  ............................................
    pt  Alguma coisa que mudaria?
    >>  ............................................
```


### Button `say_it_is_fixable` — "Some of that is still fixable."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `regrets.the_small_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.regrets.the_small_one.say_it_is_fixable` — accepted phrasings: "some of that is still fixable"; "some of that is still fixable"; "you could still put that right"
  - the message must contain one of: `fixable`, `right`, `still`
  - scored words: `fixable`(1.8), `right`(1.8), `still`(1.8), `some`(0.8), `put`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_small_one.respond.say_it_is_fixable
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.regrets.the_small_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.regrets.the_small_one.respond.say_it_is_fixable   [30 chars]
    en  Some of that is still fixable.
    >>  ............................................
    pt  Parte disso ainda tem conserto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, respect +1  _(recorded under topic `regrets.ordinary`)_
- Does: session `turn`
- Then opens: `conversations.scene.regrets.followup`
- …where the player's next choices will be: "We'll leave the rest alone."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_small_one.considered
WHO    VILLAGER — what the player reads after pressing "Some of that is still fixable."
       spoken on: conversations.scene.regrets.the_small_one.respond, button `say_it_is_fixable`
       leaves the player on: conversations.scene.regrets.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.the_small_one.open.considered`: the villager accepts. Subject `regrets.ordinary`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:regrets` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.regrets.the_small_one.considered/1   [120 chars]
    en  The swimming is. I have thought that every summer for twenty years and this is the first time anybody has said it to me.
    >>  ............................................
    pt  A natação tem. Eu penso isso todo verão há vinte anos e é a primeira vez que alguém me diz.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_small_one.considered/2   [86 chars]
    en  One of the three, maybe. That is a better ratio than I usually give myself credit for.
    >>  ............................................
    pt  Uma das três, talvez. É uma proporção melhor do que eu costumo me dar crédito.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_small_one.considered/3   [115 chars]
    en  You are right and I would rather you were wrong, because being right makes it a thing I have to do something about.
    >>  ............................................
    pt  Você tem razão e eu preferia que estivesse errada, porque estar certa transforma isso em coisa que eu tenho que resolver.
    >>  ............................................
```


### Button `ask_about_the_friendship` — "Could you mend the friendship?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `regrets.the_small_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.regrets.the_small_one.ask_about_the_friendship` — accepted phrasings: "could you mend the friendship"; "could you mend the friendship"; "is the friendship recoverable"
  - the message must contain one of: `friendship`, `mend`, `recoverable`
  - scored words: `friendship`(1.8), `mend`(1.8), `recoverable`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_small_one.respond.ask_about_the_friendship
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.regrets.the_small_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.regrets.the_small_one.respond.ask_about_the_friendship   [30 chars]
    en  Could you mend the friendship?
    >>  ............................................
    pt  Dá para remendar a amizade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `regrets.ordinary`)_
- Does: session `turn`
- Then opens: `conversations.scene.regrets.followup`
- …where the player's next choices will be: "We'll leave the rest alone."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_small_one.explained
WHO    VILLAGER — what the player reads after pressing "Could you mend the friendship?"
       spoken on: conversations.scene.regrets.the_small_one.respond, button `ask_about_the_friendship`
       leaves the player on: conversations.scene.regrets.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.the_small_one.open.explained`: the villager explains. Subject `regrets.ordinary`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:regrets` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.regrets.the_small_one.explained/1   [100 chars]
    en  We speak. We speak about weather and prices and we have not spoken about anything else in six years.
    >>  ............................................
    pt  A gente se fala. Falamos de tempo e de preços e não falamos de mais nada há seis anos.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_small_one.explained/2   [108 chars]
    en  It would take one of us saying so, and both of us are waiting for the other, and I know that and still wait.
    >>  ............................................
    pt  Bastaria um de nós dizer, e nós dois esperamos o outro, e eu sei disso e ainda espero.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_small_one.explained/3   [142 chars]
    en  Probably. The awkwardness of trying is four minutes and the awkwardness of the last six years is six years, so the arithmetic is embarrassing.
    >>  ............................................
    pt  Provavelmente. O constrangimento de tentar são quatro minutos e o constrangimento dos últimos seis anos são seis anos, então a conta é vergonhosa.
    >>  ............................................
```


### Button `leave` — "Thank you for saying it."

*stance family `exit` · tone `plain` · answers the beat(s) `regrets.the_small_one.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_small_one.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.regrets.the_small_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.regrets.the_small_one.respond.leave   [24 chars]
    en  Thank you for saying it.
    >>  ............................................
    pt  Obrigado por dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.leaving
WHO    VILLAGER — what the player reads after pressing "Thank you for saying it."
       spoken on: conversations.scene.regrets.the_small_one.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.scene.leaving`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.regrets.followup / leave; conversations.scene.regrets.the_old_one.respond / leave
```

> Written out in full under **`conversations.scene.regrets.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.regrets.close`

**Reached from 8 route(s):** `conversations.arc.regrets.resume.followup` / `absolve`; `conversations.arc.regrets.resume.followup` / `practical`; `conversations.arc.regrets.resume.followup` / `hold`; `conversations.topic.regrets.followup` / `forgive`; `conversations.topic.regrets.followup` / `forgive`; `conversations.topic.regrets.followup` / `forgive`; `conversations.topic.regrets.followup` / `forgive`; `conversations.topic.regrets.followup` / `sit_with_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.regrets.followup.forgive` — e.g. "...You don't. Nobody's said that. I'm not sure I believe it, but I'll keep it."
- `conversations.regrets.followup.sit_with_it` — e.g. "...That's better. Absolution I'd not have trusted. This I do."
- `conversations.regrets.forgive.crit` — e.g. "...You don't get to decide that. ...And yet something just came loose. I'll not pretend it didn't."
- `conversations.regrets.forgive.partial` — e.g. "Kind. It doesn't reach the part of me that decides these things, but it's kind."
- `conversations.regrets.forgive.success` — e.g. "...You don't. Hm. I'll keep it and see whether it holds by morning."
- `conversations.regrets.resume.followup.absolve` — e.g. "Enough. Who decides that, though? ...You, apparently. Alright. I'll try it your way."
- `conversations.regrets.resume.followup.hold` — e.g. "No. It isn't. Plenty take the forgiving job just to end the conversation — you didn't."
- `conversations.regrets.resume.followup.practical` — e.g. "What it'd look like. Aye — that's the question I've been walking around for a long while."


```text
POOL   dialogue key: dialogue.conversations.topic.regrets.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.regrets.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.regrets.close   [22 chars]
    en  Anyway. It's said now.
    >>  ............................................
    pt  Enfim. Já está dito.
    >>  ............................................
```


### Button `thank` — "Thank you for telling me."

*stance family `candor` · tone `gentle` · answers the beat(s) `regrets.followup.forgive.to.regrets`, `regrets.followup.sit_with_it.to.regrets`, `regrets.forgive.crit.to.regrets`, `regrets.forgive.partial.to.regrets`, `regrets.forgive.success.to.regrets`, `regrets.resume.followup.absolve.to.regrets`, `regrets.resume.followup.hold.to.regrets`, `regrets.resume.followup.practical.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.close.thank` — accepted phrasings: "thank you for telling me"; "thank you for the regret"; "i am grateful you told me"
  - the message must contain one of: `thank`, `telling`
  - scored words: `thank`(1.5), `telling`(1.2), `regret`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.close.thank   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.close.thank`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, trust +1  _(recorded under topic `regrets.close.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.close.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.regrets.close, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.close.thank.terminal`: the villager accepts. Subject `regrets.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.close.thank/1   [79 chars]
    en  Thanking me for that is an odd thing to do. ...But I'd rather odd than nothing.
    >>  ............................................
    pt  Agradecer por isso é uma coisa esquisita de se fazer. ...Mas prefiro esquisito a nada.
    >>  ............................................
  dialogue.conversations.regrets.close.thank/2   [80 chars]
    en  You're welcome. It's the first time I've said it and not felt worse after, %1$s.
    >>  ............................................
    pt  De nada. É a primeira vez que eu digo e não me sinto pior depois, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.close.thank/3   [57 chars]
    en  Hm. Thank you for hearing it without arranging your face.
    >>  ............................................
    pt  Hm. Obrigado por ouvir sem arrumar o rosto.
    >>  ............................................
```


### Button `say_means` — "That took something to say."

*stance family `candor` · tone `gentle` · answers the beat(s) `regrets.followup.forgive.to.regrets`, `regrets.followup.sit_with_it.to.regrets`, `regrets.forgive.crit.to.regrets`, `regrets.forgive.partial.to.regrets`, `regrets.forgive.success.to.regrets`, `regrets.resume.followup.absolve.to.regrets`, `regrets.resume.followup.hold.to.regrets`, `regrets.resume.followup.practical.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.close.say_means` — accepted phrasings: "that took something to say"; "that was brave of you"; "that took courage"
  - the message must contain one of: `took`, `brave`, `courage`
  - scored words: `took`(1.5), `brave`(1.2), `courage`(1.5), `regret`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.close.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.close.say_means   [27 chars]
    en  That took something to say.
    >>  ............................................
    pt  Falar isso exigiu coragem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.close.say_means`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `regrets.close.say_means`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.close.say_means
WHO    VILLAGER — what the player reads after pressing "That took something to say."
       spoken on: conversations.topic.regrets.close, button `say_means`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.close.say_means.terminal`: the villager accepts. Subject `regrets.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.close.say_means/1   [78 chars]
    en  ...It did. Saying it out loud stops you pretending it was smaller than it was.
    >>  ............................................
    pt  ...Exigiu. Dizer em voz alta impede a gente de fingir que foi menor do que foi.
    >>  ............................................
  dialogue.conversations.regrets.close.say_means/2   [69 chars]
    en  Every telling I've rehearsed came out kinder to me than that one did.
    >>  ............................................
    pt  Toda versão que eu ensaiei saiu mais gentil comigo do que essa saiu.
    >>  ............................................
  dialogue.conversations.regrets.close.say_means/3   [84 chars]
    en  You noticed that. I've spent years arranging that story so it wasn't my fault, %1$s.
    >>  ............................................
    pt  Você reparou. Passei anos arrumando essa história para não ser culpa minha, %1$s.
    >>  ............................................
```


### Button `confide` — "I've one of my own, if it helps."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `regrets.followup.forgive.to.regrets`, `regrets.followup.sit_with_it.to.regrets`, `regrets.forgive.crit.to.regrets`, `regrets.forgive.partial.to.regrets`, `regrets.forgive.success.to.regrets`, `regrets.resume.followup.absolve.to.regrets`, `regrets.resume.followup.hold.to.regrets`, `regrets.resume.followup.practical.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.close.confide` — accepted phrasings: "i have one of my own if it helps"; "i have my own if that helps"; "i carry one of my own"
  - the message must contain one of: `own`, `helps`
  - scored words: `own`(1.5), `helps`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.close.confide
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.close.confide   [32 chars]
    en  I've one of my own, if it helps.
    >>  ............................................
    pt  Eu tenho um meu, se ajudar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.close.confide`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, familiarity +4  _(recorded under topic `regrets.close.confide`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.close.confide
WHO    VILLAGER — what the player reads after pressing "I've one of my own, if it helps."
       spoken on: conversations.topic.regrets.close, button `confide`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.close.confide.terminal`: the villager discloses. Subject `regrets.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.close.confide/1   [85 chars]
    en  ...You didn't owe me that. It does help. It helps more than the forgiving would have.
    >>  ............................................
    pt  ...Você não me devia isso. Ajuda, sim. Ajuda mais do que o perdão teria ajudado.
    >>  ............................................
  dialogue.conversations.regrets.close.confide/2   [92 chars]
    en  So there's two of us carrying something. That's not comfort exactly, but it's company, %1$s.
    >>  ............................................
    pt  Então somos dois carregando alguma coisa. Não é bem conforto, mas é companhia, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.close.confide/3   [75 chars]
    en  Hm. You put yours down next to mine instead of stepping over it. I noticed.
    >>  ............................................
    pt  Hm. Você pôs o seu ao lado do meu em vez de passar por cima. Eu reparei.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.close.confide/1
    en  ...You didn't owe me that. It does help, %1$s. More than the forgiving would have.
    >>  ............................................
    pt  ...Você não me devia isso. Mas ajuda, %1$s. Mais do que o perdão ajudaria.
    >>  ............................................
  anxious.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back instead of absolving me. I don't think you know what that's worth.
    >>  ............................................
    pt  Você me deu um de volta em vez de me absolver. Acho que você não sabe o quanto isso vale.
    >>  ............................................
  anxious.dialogue.conversations.regrets.close.confide/3
    en  That helps. I've been carrying it alone and it's a fraction lighter now.
    >>  ............................................
    pt  Isso ajuda. Eu vinha carregando sozinho e está um pouquinho mais leve.
    >>  ............................................
  athletic.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that. It helps, and it'll go on helping, which forgiving wouldn't have.
    >>  ............................................
    pt  Você não me devia isso. Ajuda, e vai continuar ajudando, o que perdoar não faria.
    >>  ............................................
  athletic.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's how these things level out over years.
    >>  ............................................
    pt  Você me deu um de volta. É assim que essas coisas se nivelam ao longo dos anos.
    >>  ............................................
  athletic.dialogue.conversations.regrets.close.confide/3
    en  That helps. Slowly, and slowly is the only way this sort of thing helps at all.
    >>  ............................................
    pt  Isso ajuda. Devagar, e devagar é o único jeito que esse tipo de coisa ajuda.
    >>  ............................................
  confident.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that. It does help. It helps more than the forgiving would have.
    >>  ............................................
    pt  Você não me devia isso. Mas ajuda. Ajuda mais do que o perdão ajudaria.
    >>  ............................................
  confident.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's worth more than absolution and I'll say so once.
    >>  ............................................
    pt  Você me deu um de volta. Vale mais que absolvição e eu digo isso uma vez.
    >>  ............................................
  confident.dialogue.conversations.regrets.close.confide/3
    en  Right. That helps. Not the way you'd expect, but it helps.
    >>  ............................................
    pt  Certo. Isso ajuda. Não do jeito que você esperaria, mas ajuda.
    >>  ............................................
  crabby.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that. It does help. It helps more than the forgiving would have.
    >>  ............................................
    pt  Você não me devia isso. Mas ajuda. Ajuda mais do que o perdão ajudaria.
    >>  ............................................
  crabby.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's worth more than absolution and I'll say so once.
    >>  ............................................
    pt  Você me deu um de volta. Vale mais que absolvição e eu digo isso uma vez.
    >>  ............................................
  crabby.dialogue.conversations.regrets.close.confide/3
    en  Right. That helps. Not the way you'd expect, but it helps.
    >>  ............................................
    pt  Certo. Isso ajuda. Não do jeito que você esperaria, mas ajuda.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that, %1$s. It does help. More than the forgiving would have.
    >>  ............................................
    pt  Você não me devia isso, %1$s. Mas ajuda. Mais do que o perdão ajudaria.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's the kindest possible way to have answered me.
    >>  ............................................
    pt  Você me deu um de volta. É o jeito mais gentil possível de ter me respondido.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.close.confide/3
    en  That helps. And I'll ask you about yours another day, if you'll let me.
    >>  ............................................
    pt  Isso ajuda. E eu te pergunto do seu outro dia, se você deixar.
    >>  ............................................
  flirty.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that, %1$s. It does help. More than the forgiving would have.
    >>  ............................................
    pt  Você não me devia isso, %1$s. Mas ajuda. Mais do que o perdão ajudaria.
    >>  ............................................
  flirty.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's the kindest possible way to have answered me.
    >>  ............................................
    pt  Você me deu um de volta. É o jeito mais gentil possível de ter me respondido.
    >>  ............................................
  flirty.dialogue.conversations.regrets.close.confide/3
    en  That helps. And I'll ask you about yours another day, if you'll let me.
    >>  ............................................
    pt  Isso ajuda. E eu te pergunto do seu outro dia, se você deixar.
    >>  ............................................
  friendly.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that, %1$s. It does help. More than the forgiving would have.
    >>  ............................................
    pt  Você não me devia isso, %1$s. Mas ajuda. Mais do que o perdão ajudaria.
    >>  ............................................
  friendly.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's the kindest possible way to have answered me.
    >>  ............................................
    pt  Você me deu um de volta. É o jeito mais gentil possível de ter me respondido.
    >>  ............................................
  friendly.dialogue.conversations.regrets.close.confide/3
    en  That helps. And I'll ask you about yours another day, if you'll let me.
    >>  ............................................
    pt  Isso ajuda. E eu te pergunto do seu outro dia, se você deixar.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.close.confide/1
    en  ...You didn't owe me that. It does help, %1$s. More than the forgiving would have.
    >>  ............................................
    pt  ...Você não me devia isso. Mas ajuda, %1$s. Mais do que o perdão ajudaria.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back instead of absolving me. I don't think you know what that's worth.
    >>  ............................................
    pt  Você me deu um de volta em vez de me absolver. Acho que você não sabe o quanto isso vale.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.close.confide/3
    en  That helps. I've been carrying it alone and it's a fraction lighter now.
    >>  ............................................
    pt  Isso ajuda. Eu vinha carregando sozinho e está um pouquinho mais leve.
    >>  ............................................
  greedy.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that. It does help. It helps more than the forgiving would have.
    >>  ............................................
    pt  Você não me devia isso. Mas ajuda. Ajuda mais do que o perdão ajudaria.
    >>  ............................................
  greedy.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's worth more than absolution and I'll say so once.
    >>  ............................................
    pt  Você me deu um de volta. Vale mais que absolvição e eu digo isso uma vez.
    >>  ............................................
  greedy.dialogue.conversations.regrets.close.confide/3
    en  Right. That helps. Not the way you'd expect, but it helps.
    >>  ............................................
    pt  Certo. Isso ajuda. Não do jeito que você esperaria, mas ajuda.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that. It does help. It helps more than the forgiving would have.
    >>  ............................................
    pt  Você não me devia isso. Mas ajuda. Ajuda mais do que o perdão ajudaria.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's worth more than absolution and I'll say so once.
    >>  ............................................
    pt  Você me deu um de volta. Vale mais que absolvição e eu digo isso uma vez.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.close.confide/3
    en  Right. That helps. Not the way you'd expect, but it helps.
    >>  ............................................
    pt  Certo. Isso ajuda. Não do jeito que você esperaria, mas ajuda.
    >>  ............................................
  introverted.dialogue.conversations.regrets.close.confide/1
    en  ...You didn't owe me that. It does help.
    >>  ............................................
    pt  ...Você não me devia isso. Mas ajuda.
    >>  ............................................
  introverted.dialogue.conversations.regrets.close.confide/2
    en  More than forgiving would have.
    >>  ............................................
    pt  Mais do que perdoar ajudaria.
    >>  ............................................
  introverted.dialogue.conversations.regrets.close.confide/3
    en  ...Right. That helps.
    >>  ............................................
    pt  ...Certo. Isso ajuda.
    >>  ............................................
  lazy.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that. It helps, and it'll go on helping, which forgiving wouldn't have.
    >>  ............................................
    pt  Você não me devia isso. Ajuda, e vai continuar ajudando, o que perdoar não faria.
    >>  ............................................
  lazy.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's how these things level out over years.
    >>  ............................................
    pt  Você me deu um de volta. É assim que essas coisas se nivelam ao longo dos anos.
    >>  ............................................
  lazy.dialogue.conversations.regrets.close.confide/3
    en  That helps. Slowly, and slowly is the only way this sort of thing helps at all.
    >>  ............................................
    pt  Isso ajuda. Devagar, e devagar é o único jeito que esse tipo de coisa ajuda.
    >>  ............................................
  odd.dialogue.conversations.regrets.close.confide/1
    en  ...You didn't owe me that. It does help.
    >>  ............................................
    pt  ...Você não me devia isso. Mas ajuda.
    >>  ............................................
  odd.dialogue.conversations.regrets.close.confide/2
    en  More than forgiving would have.
    >>  ............................................
    pt  Mais do que perdoar ajudaria.
    >>  ............................................
  odd.dialogue.conversations.regrets.close.confide/3
    en  ...Right. That helps.
    >>  ............................................
    pt  ...Certo. Isso ajuda.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that. It helps, and it'll go on helping, which forgiving wouldn't have.
    >>  ............................................
    pt  Você não me devia isso. Ajuda, e vai continuar ajudando, o que perdoar não faria.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's how these things level out over years.
    >>  ............................................
    pt  Você me deu um de volta. É assim que essas coisas se nivelam ao longo dos anos.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.close.confide/3
    en  That helps. Slowly, and slowly is the only way this sort of thing helps at all.
    >>  ............................................
    pt  Isso ajuda. Devagar, e devagar é o único jeito que esse tipo de coisa ajuda.
    >>  ............................................
  peppy.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that! It does help. It helps considerably more than forgiving would have.
    >>  ............................................
    pt  Você não me devia isso! Mas ajuda. Ajuda consideravelmente mais que perdoar ajudaria.
    >>  ............................................
  peppy.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. Now we're both slightly worse people and I feel much better.
    >>  ............................................
    pt  Você me deu um de volta. Agora nós dois somos gente um pouco pior e eu me sinto bem melhor.
    >>  ............................................
  peppy.dialogue.conversations.regrets.close.confide/3
    en  Right. That helps. I did not expect it to and it does.
    >>  ............................................
    pt  Certo. Isso ajuda. Eu não esperava e ajuda.
    >>  ............................................
  playful.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that! It does help. It helps considerably more than forgiving would have.
    >>  ............................................
    pt  Você não me devia isso! Mas ajuda. Ajuda consideravelmente mais que perdoar ajudaria.
    >>  ............................................
  playful.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. Now we're both slightly worse people and I feel much better.
    >>  ............................................
    pt  Você me deu um de volta. Agora nós dois somos gente um pouco pior e eu me sinto bem melhor.
    >>  ............................................
  playful.dialogue.conversations.regrets.close.confide/3
    en  Right. That helps. I did not expect it to and it does.
    >>  ............................................
    pt  Certo. Isso ajuda. Eu não esperava e ajuda.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that. It helps, and it'll go on helping, which forgiving wouldn't have.
    >>  ............................................
    pt  Você não me devia isso. Ajuda, e vai continuar ajudando, o que perdoar não faria.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. That's how these things level out over years.
    >>  ............................................
    pt  Você me deu um de volta. É assim que essas coisas se nivelam ao longo dos anos.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.close.confide/3
    en  That helps. Slowly, and slowly is the only way this sort of thing helps at all.
    >>  ............................................
    pt  Isso ajuda. Devagar, e devagar é o único jeito que esse tipo de coisa ajuda.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.close.confide/1
    en  ...You didn't owe me that. It does help, %1$s. More than the forgiving would have.
    >>  ............................................
    pt  ...Você não me devia isso. Mas ajuda, %1$s. Mais do que o perdão ajudaria.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back instead of absolving me. I don't think you know what that's worth.
    >>  ............................................
    pt  Você me deu um de volta em vez de me absolver. Acho que você não sabe o quanto isso vale.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.close.confide/3
    en  That helps. I've been carrying it alone and it's a fraction lighter now.
    >>  ............................................
    pt  Isso ajuda. Eu vinha carregando sozinho e está um pouquinho mais leve.
    >>  ............................................
  shy.dialogue.conversations.regrets.close.confide/1
    en  ...You didn't owe me that. It does help.
    >>  ............................................
    pt  ...Você não me devia isso. Mas ajuda.
    >>  ............................................
  shy.dialogue.conversations.regrets.close.confide/2
    en  More than forgiving would have.
    >>  ............................................
    pt  Mais do que perdoar ajudaria.
    >>  ............................................
  shy.dialogue.conversations.regrets.close.confide/3
    en  ...Right. That helps.
    >>  ............................................
    pt  ...Certo. Isso ajuda.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that! It does help. It helps considerably more than forgiving would have.
    >>  ............................................
    pt  Você não me devia isso! Mas ajuda. Ajuda consideravelmente mais que perdoar ajudaria.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. Now we're both slightly worse people and I feel much better.
    >>  ............................................
    pt  Você me deu um de volta. Agora nós dois somos gente um pouco pior e eu me sinto bem melhor.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.close.confide/3
    en  Right. That helps. I did not expect it to and it does.
    >>  ............................................
    pt  Certo. Isso ajuda. Eu não esperava e ajuda.
    >>  ............................................
  witty.dialogue.conversations.regrets.close.confide/1
    en  You didn't owe me that! It does help. It helps considerably more than forgiving would have.
    >>  ............................................
    pt  Você não me devia isso! Mas ajuda. Ajuda consideravelmente mais que perdoar ajudaria.
    >>  ............................................
  witty.dialogue.conversations.regrets.close.confide/2
    en  You gave me one back. Now we're both slightly worse people and I feel much better.
    >>  ............................................
    pt  Você me deu um de volta. Agora nós dois somos gente um pouco pior e eu me sinto bem melhor.
    >>  ............................................
  witty.dialogue.conversations.regrets.close.confide/3
    en  Right. That helps. I did not expect it to and it does.
    >>  ............................................
    pt  Certo. Isso ajuda. Eu não esperava e ajuda.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `regrets.followup.forgive.to.regrets`, `regrets.followup.sit_with_it.to.regrets`, `regrets.forgive.crit.to.regrets`, `regrets.forgive.partial.to.regrets`, `regrets.forgive.success.to.regrets`, `regrets.resume.followup.absolve.to.regrets`, `regrets.resume.followup.hold.to.regrets`, `regrets.resume.followup.practical.to.regrets` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.close.leave   [16 chars]
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
POOL   dialogue key: dialogue.conversations.regrets.close.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.regrets.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.close.leave.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.close.leave/1   [26 chars]
    en  Aye. Go on, and thank you.
    >>  ............................................
    pt  Tá. Pode ir, e obrigado.
    >>  ............................................
  dialogue.conversations.regrets.close.leave/2   [34 chars]
    en  Right. Enough of that for one day.
    >>  ............................................
    pt  Certo. Já chega disso por um dia.
    >>  ............................................
  dialogue.conversations.regrets.close.leave/3   [20 chars]
    en  Mind the road, %1$s.
    >>  ............................................
    pt  Cuidado na estrada, %1$s.
    >>  ............................................
```

---


## `conversations.topic.regrets.followup`

**Reached from 1 route(s):** `conversations.topic.regrets.sit_with_it` / `stay`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.regrets.sit_with_it.stay` — e.g. "...Right. Thank you. Most of the way through that I was waiting for the sound of you leaving."


```text
POOL   dialogue key: dialogue.conversations.topic.regrets.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.regrets.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.regrets.followup   [30 chars]
    en  So there it is. Said out loud.
    >>  ............................................
    pt  Então é isso. Dito em voz alta.
    >>  ............................................
```


### Button `forgive` — "I don't think you're a bad person."

*stance family `empathy` · tone `gentle` · answers the beat(s) `regrets.sit_with_it.stay.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.followup.forgive` — accepted phrasings: "i do not think you are a bad person"; "i forgive you"; "you are not a bad person"
  - the message must contain one of: `bad`, `person`, `forgive`
  - scored words: `bad`(1.2), `person`(1.2), `forgive`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.followup.forgive
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.followup.forgive   [34 chars]
    en  I don't think you're a bad person.
    >>  ............................................
    pt  Não acho que você seja má pessoa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `regrets.forgive` lands on tier **crit** (axis trust, difficulty 40, stance empathy)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `regrets.forgive.crit`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +7, warmth +4  _(recorded under topic `regrets.forgive`)_
- Does: arc `regrets` — advance to stage 1
- Does: exclusive `regrets.stance` -> `forgave` (locks the other side out for good)
- Then opens: `conversations.topic.regrets.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I've one of my own, if it helps." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.regrets.forgive.crit
WHO    VILLAGER — what the player reads after pressing "I don't think you're a bad person."
       spoken on: conversations.topic.regrets.followup, button `forgive`
       leaves the player on: conversations.topic.regrets.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.forgive.crit.to.regrets`: the villager accepts. Subject `regrets`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.forgive.crit/1   [98 chars]
    en  ...You don't get to decide that. ...And yet something just came loose. I'll not pretend it didn't.
    >>  ............................................
    pt  ...Não é você quem decide isso. ...E mesmo assim alguma coisa se soltou. Não vou fingir que não.
    >>  ............................................
  dialogue.conversations.regrets.forgive.crit/2   [79 chars]
    en  Say that again slowly, %1$s, because I want to remember exactly how it sounded.
    >>  ............................................
    pt  Repete devagar, %1$s, porque eu quero lembrar exatamente como soou.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that. ...And yet something just came loose, %1$s.
    >>  ............................................
    pt  ...Não cabe a você decidir isso. ...E mesmo assim algo se soltou, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give and I've wanted it from somebody for eleven years.
    >>  ............................................
    pt  Não é seu pra dar e eu queria isso de alguém há onze anos.
    >>  ............................................
  athletic.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that. And yet something came loose, and I'll let it.
    >>  ............................................
    pt  Não cabe a você decidir isso. E mesmo assim algo se soltou, e eu vou deixar.
    >>  ............................................
  athletic.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. It has helped anyway, and I'll not argue with what helps.
    >>  ............................................
    pt  Não é seu pra dar. Ajudou mesmo assim, e eu não discuto com o que ajuda.
    >>  ............................................
  confident.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that. And yet something just came loose.
    >>  ............................................
    pt  Não cabe a você decidir isso. E mesmo assim algo se soltou.
    >>  ............................................
  confident.dialogue.conversations.regrets.forgive.crit/2
    en  That isn't yours to give. It landed anyway, which I'll be cross about later.
    >>  ............................................
    pt  Não é seu pra dar. Mas pegou, e eu vou ficar bravo com isso depois.
    >>  ............................................
  crabby.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that. And yet something just came loose.
    >>  ............................................
    pt  Não cabe a você decidir isso. E mesmo assim algo se soltou.
    >>  ............................................
  crabby.dialogue.conversations.regrets.forgive.crit/2
    en  That isn't yours to give. It landed anyway, which I'll be cross about later.
    >>  ............................................
    pt  Não é seu pra dar. Mas pegou, e eu vou ficar bravo com isso depois.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that, %1$s. And yet something just came loose.
    >>  ............................................
    pt  ...Não cabe a você decidir isso, %1$s. E mesmo assim algo se soltou.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. Coming from you it went in anyway, and I'll not fight it.
    >>  ............................................
    pt  Não é seu pra dar. Vindo de você entrou do mesmo jeito, e eu não vou brigar.
    >>  ............................................
  flirty.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that, %1$s. And yet something just came loose.
    >>  ............................................
    pt  ...Não cabe a você decidir isso, %1$s. E mesmo assim algo se soltou.
    >>  ............................................
  flirty.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. Coming from you it went in anyway, and I'll not fight it.
    >>  ............................................
    pt  Não é seu pra dar. Vindo de você entrou do mesmo jeito, e eu não vou brigar.
    >>  ............................................
  friendly.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that, %1$s. And yet something just came loose.
    >>  ............................................
    pt  ...Não cabe a você decidir isso, %1$s. E mesmo assim algo se soltou.
    >>  ............................................
  friendly.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. Coming from you it went in anyway, and I'll not fight it.
    >>  ............................................
    pt  Não é seu pra dar. Vindo de você entrou do mesmo jeito, e eu não vou brigar.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that. ...And yet something just came loose, %1$s.
    >>  ............................................
    pt  ...Não cabe a você decidir isso. ...E mesmo assim algo se soltou, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give and I've wanted it from somebody for eleven years.
    >>  ............................................
    pt  Não é seu pra dar e eu queria isso de alguém há onze anos.
    >>  ............................................
  greedy.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that. And yet something just came loose.
    >>  ............................................
    pt  Não cabe a você decidir isso. E mesmo assim algo se soltou.
    >>  ............................................
  greedy.dialogue.conversations.regrets.forgive.crit/2
    en  That isn't yours to give. It landed anyway, which I'll be cross about later.
    >>  ............................................
    pt  Não é seu pra dar. Mas pegou, e eu vou ficar bravo com isso depois.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that. And yet something just came loose.
    >>  ............................................
    pt  Não cabe a você decidir isso. E mesmo assim algo se soltou.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.forgive.crit/2
    en  That isn't yours to give. It landed anyway, which I'll be cross about later.
    >>  ............................................
    pt  Não é seu pra dar. Mas pegou, e eu vou ficar bravo com isso depois.
    >>  ............................................
  introverted.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that. ...And yet something came loose.
    >>  ............................................
    pt  ...Não cabe a você decidir isso. ...E mesmo assim algo se soltou.
    >>  ............................................
  introverted.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. It landed anyway.
    >>  ............................................
    pt  Não é seu pra dar. Pegou mesmo assim.
    >>  ............................................
  lazy.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that. And yet something came loose, and I'll let it.
    >>  ............................................
    pt  Não cabe a você decidir isso. E mesmo assim algo se soltou, e eu vou deixar.
    >>  ............................................
  lazy.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. It has helped anyway, and I'll not argue with what helps.
    >>  ............................................
    pt  Não é seu pra dar. Ajudou mesmo assim, e eu não discuto com o que ajuda.
    >>  ............................................
  odd.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that. ...And yet something came loose.
    >>  ............................................
    pt  ...Não cabe a você decidir isso. ...E mesmo assim algo se soltou.
    >>  ............................................
  odd.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. It landed anyway.
    >>  ............................................
    pt  Não é seu pra dar. Pegou mesmo assim.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that. And yet something came loose, and I'll let it.
    >>  ............................................
    pt  Não cabe a você decidir isso. E mesmo assim algo se soltou, e eu vou deixar.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. It has helped anyway, and I'll not argue with what helps.
    >>  ............................................
    pt  Não é seu pra dar. Ajudou mesmo assim, e eu não discuto com o que ajuda.
    >>  ............................................
  peppy.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that! ...And yet something just came loose. How annoying.
    >>  ............................................
    pt  Não cabe a você decidir isso! ...E mesmo assim algo se soltou. Que irritante.
    >>  ............................................
  peppy.dialogue.conversations.regrets.forgive.crit/2
    en  That isn't yours to give. It worked regardless. I'd like a word with my own head.
    >>  ............................................
    pt  Não é seu pra dar. Funcionou mesmo assim. Eu queria falar com a minha própria cabeça.
    >>  ............................................
  playful.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that! ...And yet something just came loose. How annoying.
    >>  ............................................
    pt  Não cabe a você decidir isso! ...E mesmo assim algo se soltou. Que irritante.
    >>  ............................................
  playful.dialogue.conversations.regrets.forgive.crit/2
    en  That isn't yours to give. It worked regardless. I'd like a word with my own head.
    >>  ............................................
    pt  Não é seu pra dar. Funcionou mesmo assim. Eu queria falar com a minha própria cabeça.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that. And yet something came loose, and I'll let it.
    >>  ............................................
    pt  Não cabe a você decidir isso. E mesmo assim algo se soltou, e eu vou deixar.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. It has helped anyway, and I'll not argue with what helps.
    >>  ............................................
    pt  Não é seu pra dar. Ajudou mesmo assim, e eu não discuto com o que ajuda.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that. ...And yet something just came loose, %1$s.
    >>  ............................................
    pt  ...Não cabe a você decidir isso. ...E mesmo assim algo se soltou, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give and I've wanted it from somebody for eleven years.
    >>  ............................................
    pt  Não é seu pra dar e eu queria isso de alguém há onze anos.
    >>  ............................................
  shy.dialogue.conversations.regrets.forgive.crit/1
    en  ...You don't get to decide that. ...And yet something came loose.
    >>  ............................................
    pt  ...Não cabe a você decidir isso. ...E mesmo assim algo se soltou.
    >>  ............................................
  shy.dialogue.conversations.regrets.forgive.crit/2
    en  It isn't yours to give. It landed anyway.
    >>  ............................................
    pt  Não é seu pra dar. Pegou mesmo assim.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that! ...And yet something just came loose. How annoying.
    >>  ............................................
    pt  Não cabe a você decidir isso! ...E mesmo assim algo se soltou. Que irritante.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.forgive.crit/2
    en  That isn't yours to give. It worked regardless. I'd like a word with my own head.
    >>  ............................................
    pt  Não é seu pra dar. Funcionou mesmo assim. Eu queria falar com a minha própria cabeça.
    >>  ............................................
  witty.dialogue.conversations.regrets.forgive.crit/1
    en  You don't get to decide that! ...And yet something just came loose. How annoying.
    >>  ............................................
    pt  Não cabe a você decidir isso! ...E mesmo assim algo se soltou. Que irritante.
    >>  ............................................
  witty.dialogue.conversations.regrets.forgive.crit/2
    en  That isn't yours to give. It worked regardless. I'd like a word with my own head.
    >>  ............................................
    pt  Não é seu pra dar. Funcionou mesmo assim. Eu queria falar com a minha própria cabeça.
    >>  ............................................
```

</details>


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `regrets.forgive` lands on tier **success** (axis trust, difficulty 40, stance empathy)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `regrets.forgive.success`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +5, warmth +2  _(recorded under topic `regrets.forgive`)_
- Does: arc `regrets` — advance to stage 1
- Does: exclusive `regrets.stance` -> `forgave` (locks the other side out for good)
- Then opens: `conversations.topic.regrets.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I've one of my own, if it helps." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.regrets.forgive.success
WHO    VILLAGER — what the player reads after pressing "I don't think you're a bad person."
       spoken on: conversations.topic.regrets.followup, button `forgive`
       leaves the player on: conversations.topic.regrets.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.forgive.success.to.regrets`: the villager accepts. Subject `regrets`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.forgive.success/1   [67 chars]
    en  ...You don't. Hm. I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  ...Não é. Hm. Vou guardar e ver se ainda vale de manhã.
    >>  ............................................
  dialogue.conversations.regrets.forgive.success/2   [47 chars]
    en  That's not yours to give. I'm taking it anyway.
    >>  ............................................
    pt  Não é sua para dar. Vou pegar mesmo assim.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't. Hm. I'll keep it and see whether it holds by morning, %1$s.
    >>  ............................................
    pt  ...Você não acha. Hm. Vou guardar e ver se se sustenta até de manhã, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it. I've taken these before and lost them by dawn, so — carefully.
    >>  ............................................
    pt  Certo. Eu aceito. Já aceitei antes e perdi até o amanhecer, então — com cuidado.
    >>  ............................................
  athletic.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm. I'll keep it and see whether it holds by the spring.
    >>  ............................................
    pt  Você não acha. Hm. Vou guardar e ver se se sustenta até a primavera.
    >>  ............................................
  athletic.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it slowly. Things like this are decided over months, not evenings.
    >>  ............................................
    pt  Certo. Vou devagar. Coisas assim se decidem em meses, não em noites.
    >>  ............................................
  confident.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm. I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  Você não acha. Hm. Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  confident.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it as far as it goes and check it again tomorrow.
    >>  ............................................
    pt  Certo. Vou levar até onde der e conferir de novo amanhã.
    >>  ............................................
  crabby.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm. I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  Você não acha. Hm. Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  crabby.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it as far as it goes and check it again tomorrow.
    >>  ............................................
    pt  Certo. Vou levar até onde der e conferir de novo amanhã.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't, %1$s. Hm. I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  ...Você não acha, %1$s. Hm. Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it, and I'll tell you tomorrow whether it stayed.
    >>  ............................................
    pt  Certo. Eu aceito, e amanhã eu te digo se ficou.
    >>  ............................................
  flirty.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't, %1$s. Hm. I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  ...Você não acha, %1$s. Hm. Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  flirty.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it, and I'll tell you tomorrow whether it stayed.
    >>  ............................................
    pt  Certo. Eu aceito, e amanhã eu te digo se ficou.
    >>  ............................................
  friendly.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't, %1$s. Hm. I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  ...Você não acha, %1$s. Hm. Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  friendly.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it, and I'll tell you tomorrow whether it stayed.
    >>  ............................................
    pt  Certo. Eu aceito, e amanhã eu te digo se ficou.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't. Hm. I'll keep it and see whether it holds by morning, %1$s.
    >>  ............................................
    pt  ...Você não acha. Hm. Vou guardar e ver se se sustenta até de manhã, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it. I've taken these before and lost them by dawn, so — carefully.
    >>  ............................................
    pt  Certo. Eu aceito. Já aceitei antes e perdi até o amanhecer, então — com cuidado.
    >>  ............................................
  greedy.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm. I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  Você não acha. Hm. Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  greedy.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it as far as it goes and check it again tomorrow.
    >>  ............................................
    pt  Certo. Vou levar até onde der e conferir de novo amanhã.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm. I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  Você não acha. Hm. Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it as far as it goes and check it again tomorrow.
    >>  ............................................
    pt  Certo. Vou levar até onde der e conferir de novo amanhã.
    >>  ............................................
  introverted.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't. Hm. I'll keep it.
    >>  ............................................
    pt  ...Você não acha. Hm. Vou guardar.
    >>  ............................................
  introverted.dialogue.conversations.regrets.forgive.success/2
    en  Right. We'll see if it holds.
    >>  ............................................
    pt  Certo. Vamos ver se se sustenta.
    >>  ............................................
  lazy.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm. I'll keep it and see whether it holds by the spring.
    >>  ............................................
    pt  Você não acha. Hm. Vou guardar e ver se se sustenta até a primavera.
    >>  ............................................
  lazy.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it slowly. Things like this are decided over months, not evenings.
    >>  ............................................
    pt  Certo. Vou devagar. Coisas assim se decidem em meses, não em noites.
    >>  ............................................
  odd.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't. Hm. I'll keep it.
    >>  ............................................
    pt  ...Você não acha. Hm. Vou guardar.
    >>  ............................................
  odd.dialogue.conversations.regrets.forgive.success/2
    en  Right. We'll see if it holds.
    >>  ............................................
    pt  Certo. Vamos ver se se sustenta.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm. I'll keep it and see whether it holds by the spring.
    >>  ............................................
    pt  Você não acha. Hm. Vou guardar e ver se se sustenta até a primavera.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it slowly. Things like this are decided over months, not evenings.
    >>  ............................................
    pt  Certo. Vou devagar. Coisas assim se decidem em meses, não em noites.
    >>  ............................................
  peppy.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm! I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  Você não acha. Hm! Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  peppy.dialogue.conversations.regrets.forgive.success/2
    en  Right — I'll take it. Provisionally. With a receipt.
    >>  ............................................
    pt  Certo — eu aceito. Provisoriamente. Com recibo.
    >>  ............................................
  playful.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm! I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  Você não acha. Hm! Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  playful.dialogue.conversations.regrets.forgive.success/2
    en  Right — I'll take it. Provisionally. With a receipt.
    >>  ............................................
    pt  Certo — eu aceito. Provisoriamente. Com recibo.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm. I'll keep it and see whether it holds by the spring.
    >>  ............................................
    pt  Você não acha. Hm. Vou guardar e ver se se sustenta até a primavera.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it slowly. Things like this are decided over months, not evenings.
    >>  ............................................
    pt  Certo. Vou devagar. Coisas assim se decidem em meses, não em noites.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't. Hm. I'll keep it and see whether it holds by morning, %1$s.
    >>  ............................................
    pt  ...Você não acha. Hm. Vou guardar e ver se se sustenta até de manhã, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.forgive.success/2
    en  Right. I'll take it. I've taken these before and lost them by dawn, so — carefully.
    >>  ............................................
    pt  Certo. Eu aceito. Já aceitei antes e perdi até o amanhecer, então — com cuidado.
    >>  ............................................
  shy.dialogue.conversations.regrets.forgive.success/1
    en  ...You don't. Hm. I'll keep it.
    >>  ............................................
    pt  ...Você não acha. Hm. Vou guardar.
    >>  ............................................
  shy.dialogue.conversations.regrets.forgive.success/2
    en  Right. We'll see if it holds.
    >>  ............................................
    pt  Certo. Vamos ver se se sustenta.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm! I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  Você não acha. Hm! Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.forgive.success/2
    en  Right — I'll take it. Provisionally. With a receipt.
    >>  ............................................
    pt  Certo — eu aceito. Provisoriamente. Com recibo.
    >>  ............................................
  witty.dialogue.conversations.regrets.forgive.success/1
    en  You don't. Hm! I'll keep it and see whether it holds by morning.
    >>  ............................................
    pt  Você não acha. Hm! Vou guardar e ver se se sustenta até de manhã.
    >>  ............................................
  witty.dialogue.conversations.regrets.forgive.success/2
    en  Right — I'll take it. Provisionally. With a receipt.
    >>  ............................................
    pt  Certo — eu aceito. Provisoriamente. Com recibo.
    >>  ............................................
```

</details>


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `regrets.forgive` lands on tier **partial** (axis trust, difficulty 40, stance empathy)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `regrets.forgive.partial`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +2  _(recorded under topic `regrets.forgive`)_
- Does: arc `regrets` — advance to stage 1
- Does: exclusive `regrets.stance` -> `forgave` (locks the other side out for good)
- Then opens: `conversations.topic.regrets.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I've one of my own, if it helps." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.regrets.forgive.partial
WHO    VILLAGER — what the player reads after pressing "I don't think you're a bad person."
       spoken on: conversations.topic.regrets.followup, button `forgive`
       leaves the player on: conversations.topic.regrets.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.forgive.partial.to.regrets`: the villager accepts. Subject `regrets`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.forgive.partial/1   [79 chars]
    en  Kind. It doesn't reach the part of me that decides these things, but it's kind.
    >>  ............................................
    pt  Gentil. Não chega na parte de mim que decide essas coisas, mas é gentil.
    >>  ............................................
  dialogue.conversations.regrets.forgive.partial/2   [53 chars]
    en  Mm. You'd say that to anybody with a long face, %1$s.
    >>  ............................................
    pt  Hm. Você diria isso para qualquer um de cara fechada, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part of me that decides these things, %1$s. I wish it did.
    >>  ............................................
    pt  Gentil. Não chega na parte de mim que decide essas coisas, %1$s. Eu queria que chegasse.
    >>  ............................................
  anxious.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. I've had kind before. Kind sits on the surface and this is under it.
    >>  ............................................
    pt  Bem-intencionado. Já tive gentil antes. Gentil fica na superfície e isto está embaixo.
    >>  ............................................
  athletic.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the deciding part, but few things do, and it's not nothing.
    >>  ............................................
    pt  Gentil. Não chega na parte que decide, mas poucas coisas chegam, e não é nada.
    >>  ............................................
  athletic.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. Give it a year and it may reach further than it does tonight.
    >>  ............................................
    pt  Bem-intencionado. Dê um ano e pode chegar mais longe do que chega hoje.
    >>  ............................................
  confident.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part of me that decides these things.
    >>  ............................................
    pt  Gentil. Não chega na parte de mim que decide essas coisas.
    >>  ............................................
  confident.dialogue.conversations.regrets.forgive.partial/2
    en  That's kindly meant and it stops short of where it would need to go.
    >>  ............................................
    pt  É bem-intencionado e para antes de onde precisaria chegar.
    >>  ............................................
  crabby.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part of me that decides these things.
    >>  ............................................
    pt  Gentil. Não chega na parte de mim que decide essas coisas.
    >>  ............................................
  crabby.dialogue.conversations.regrets.forgive.partial/2
    en  That's kindly meant and it stops short of where it would need to go.
    >>  ............................................
    pt  É bem-intencionado e para antes de onde precisaria chegar.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.forgive.partial/1
    en  Kind, %1$s. It doesn't reach the part of me that decides these things, but it helps a little.
    >>  ............................................
    pt  Gentil, %1$s. Não chega na parte de mim que decide essas coisas, mas ajuda um pouco.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.forgive.partial/2
    en  That's kindly meant and I'd rather have it than not. It just doesn't go all the way in.
    >>  ............................................
    pt  É bem-intencionado e eu prefiro ter a não ter. Só não entra até o fim.
    >>  ............................................
  flirty.dialogue.conversations.regrets.forgive.partial/1
    en  Kind, %1$s. It doesn't reach the part of me that decides these things, but it helps a little.
    >>  ............................................
    pt  Gentil, %1$s. Não chega na parte de mim que decide essas coisas, mas ajuda um pouco.
    >>  ............................................
  flirty.dialogue.conversations.regrets.forgive.partial/2
    en  That's kindly meant and I'd rather have it than not. It just doesn't go all the way in.
    >>  ............................................
    pt  É bem-intencionado e eu prefiro ter a não ter. Só não entra até o fim.
    >>  ............................................
  friendly.dialogue.conversations.regrets.forgive.partial/1
    en  Kind, %1$s. It doesn't reach the part of me that decides these things, but it helps a little.
    >>  ............................................
    pt  Gentil, %1$s. Não chega na parte de mim que decide essas coisas, mas ajuda um pouco.
    >>  ............................................
  friendly.dialogue.conversations.regrets.forgive.partial/2
    en  That's kindly meant and I'd rather have it than not. It just doesn't go all the way in.
    >>  ............................................
    pt  É bem-intencionado e eu prefiro ter a não ter. Só não entra até o fim.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part of me that decides these things, %1$s. I wish it did.
    >>  ............................................
    pt  Gentil. Não chega na parte de mim que decide essas coisas, %1$s. Eu queria que chegasse.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. I've had kind before. Kind sits on the surface and this is under it.
    >>  ............................................
    pt  Bem-intencionado. Já tive gentil antes. Gentil fica na superfície e isto está embaixo.
    >>  ............................................
  greedy.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part of me that decides these things.
    >>  ............................................
    pt  Gentil. Não chega na parte de mim que decide essas coisas.
    >>  ............................................
  greedy.dialogue.conversations.regrets.forgive.partial/2
    en  That's kindly meant and it stops short of where it would need to go.
    >>  ............................................
    pt  É bem-intencionado e para antes de onde precisaria chegar.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part of me that decides these things.
    >>  ............................................
    pt  Gentil. Não chega na parte de mim que decide essas coisas.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.forgive.partial/2
    en  That's kindly meant and it stops short of where it would need to go.
    >>  ............................................
    pt  É bem-intencionado e para antes de onde precisaria chegar.
    >>  ............................................
  introverted.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part that decides.
    >>  ............................................
    pt  Gentil. Não chega na parte que decide.
    >>  ............................................
  introverted.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. Not far enough in.
    >>  ............................................
    pt  Bem-intencionado. Não entra o bastante.
    >>  ............................................
  lazy.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the deciding part, but few things do, and it's not nothing.
    >>  ............................................
    pt  Gentil. Não chega na parte que decide, mas poucas coisas chegam, e não é nada.
    >>  ............................................
  lazy.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. Give it a year and it may reach further than it does tonight.
    >>  ............................................
    pt  Bem-intencionado. Dê um ano e pode chegar mais longe do que chega hoje.
    >>  ............................................
  odd.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part that decides.
    >>  ............................................
    pt  Gentil. Não chega na parte que decide.
    >>  ............................................
  odd.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. Not far enough in.
    >>  ............................................
    pt  Bem-intencionado. Não entra o bastante.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the deciding part, but few things do, and it's not nothing.
    >>  ............................................
    pt  Gentil. Não chega na parte que decide, mas poucas coisas chegam, e não é nada.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. Give it a year and it may reach further than it does tonight.
    >>  ............................................
    pt  Bem-intencionado. Dê um ano e pode chegar mais longe do que chega hoje.
    >>  ............................................
  peppy.dialogue.conversations.regrets.forgive.partial/1
    en  Kind! It doesn't reach the part of me that decides these things, mind.
    >>  ............................................
    pt  Gentil! Mas não chega na parte de mim que decide essas coisas.
    >>  ............................................
  peppy.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. It's stopped about a foot short of where it needed to land.
    >>  ............................................
    pt  Bem-intencionado. Parou uns trinta centímetros antes de onde precisava cair.
    >>  ............................................
  playful.dialogue.conversations.regrets.forgive.partial/1
    en  Kind! It doesn't reach the part of me that decides these things, mind.
    >>  ............................................
    pt  Gentil! Mas não chega na parte de mim que decide essas coisas.
    >>  ............................................
  playful.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. It's stopped about a foot short of where it needed to land.
    >>  ............................................
    pt  Bem-intencionado. Parou uns trinta centímetros antes de onde precisava cair.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the deciding part, but few things do, and it's not nothing.
    >>  ............................................
    pt  Gentil. Não chega na parte que decide, mas poucas coisas chegam, e não é nada.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. Give it a year and it may reach further than it does tonight.
    >>  ............................................
    pt  Bem-intencionado. Dê um ano e pode chegar mais longe do que chega hoje.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part of me that decides these things, %1$s. I wish it did.
    >>  ............................................
    pt  Gentil. Não chega na parte de mim que decide essas coisas, %1$s. Eu queria que chegasse.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. I've had kind before. Kind sits on the surface and this is under it.
    >>  ............................................
    pt  Bem-intencionado. Já tive gentil antes. Gentil fica na superfície e isto está embaixo.
    >>  ............................................
  shy.dialogue.conversations.regrets.forgive.partial/1
    en  Kind. It doesn't reach the part that decides.
    >>  ............................................
    pt  Gentil. Não chega na parte que decide.
    >>  ............................................
  shy.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. Not far enough in.
    >>  ............................................
    pt  Bem-intencionado. Não entra o bastante.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.forgive.partial/1
    en  Kind! It doesn't reach the part of me that decides these things, mind.
    >>  ............................................
    pt  Gentil! Mas não chega na parte de mim que decide essas coisas.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. It's stopped about a foot short of where it needed to land.
    >>  ............................................
    pt  Bem-intencionado. Parou uns trinta centímetros antes de onde precisava cair.
    >>  ............................................
  witty.dialogue.conversations.regrets.forgive.partial/1
    en  Kind! It doesn't reach the part of me that decides these things, mind.
    >>  ............................................
    pt  Gentil! Mas não chega na parte de mim que decide essas coisas.
    >>  ............................................
  witty.dialogue.conversations.regrets.forgive.partial/2
    en  Kindly meant. It's stopped about a foot short of where it needed to land.
    >>  ............................................
    pt  Bem-intencionado. Parou uns trinta centímetros antes de onde precisava cair.
    >>  ............................................
```

</details>


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `regrets.forgive` lands on tier **rebuff** (axis trust, difficulty 40, stance empathy)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts -1** — decision id `regrets.forgive.rebuff`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5, trust -2  _(recorded under topic `regrets.forgive`)_
- Does: arc `regrets` — advance to stage 1
- Does: exclusive `regrets.stance` -> `forgave` (locks the other side out for good)
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.forgive.rebuff
WHO    VILLAGER — what the player reads after pressing "I don't think you're a bad person."
       spoken on: conversations.topic.regrets.followup, button `forgive`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.forgive.rebuff.terminal`: the villager refuses. Subject `regrets.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.forgive.rebuff/1   [74 chars]
    en  Don't. Absolution from somebody who wasn't there is worth exactly nothing.
    >>  ............................................
    pt  Não. Absolvição de quem não estava lá não vale absolutamente nada.
    >>  ............................................
  dialogue.conversations.regrets.forgive.rebuff/2   [63 chars]
    en  You weren't in the room. You don't get to close the door on it.
    >>  ............................................
    pt  Você não estava na sala. Não é você quem fecha essa porta.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.forgive.rebuff/1
    en  Please don't. If I took that from you I'd only have to earn it twice.
    >>  ............................................
    pt  Por favor, não. Se eu aceitasse isso de você eu teria que merecer duas vezes.
    >>  ............................................
  anxious.dialogue.conversations.regrets.forgive.rebuff/2
    en  Don't. I've wanted to be forgiven for years and not by somebody who wasn't there, %1$s.
    >>  ............................................
    pt  Não. Faz anos que eu quero ser perdoado e não por quem não estava lá, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. It'll settle in its own time or not at all, and it won't be your doing.
    >>  ............................................
    pt  Não. Vai assentar no tempo dele ou não, e não vai ser obra sua.
    >>  ............................................
  athletic.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. That's not a thing that gets handed over. It has to wear down.
    >>  ............................................
    pt  Não. Isso não é coisa que se entregue. Tem que se desgastar.
    >>  ............................................
  confident.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. Absolution from somebody who wasn't there is worth exactly nothing.
    >>  ............................................
    pt  Não. Absolvição de quem não estava lá não vale nada.
    >>  ............................................
  confident.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. You can't forgive me for a thing that wasn't done to you.
    >>  ............................................
    pt  Não. Você não pode me perdoar por algo que não foi feito a você.
    >>  ............................................
  crabby.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. Absolution from somebody who wasn't there is worth exactly nothing.
    >>  ............................................
    pt  Não. Absolvição de quem não estava lá não vale nada.
    >>  ............................................
  crabby.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. You can't forgive me for a thing that wasn't done to you.
    >>  ............................................
    pt  Não. Você não pode me perdoar por algo que não foi feito a você.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't, %1$s. I know you mean it, and it isn't yours to give.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu sei que você é sincero, e não é seu pra dar.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.forgive.rebuff/2
    en  That's the kindest wrong thing anyone's said to me about it.
    >>  ............................................
    pt  É a coisa errada mais gentil que alguém já me disse sobre isso.
    >>  ............................................
  flirty.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't, %1$s. I know you mean it, and it isn't yours to give.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu sei que você é sincero, e não é seu pra dar.
    >>  ............................................
  flirty.dialogue.conversations.regrets.forgive.rebuff/2
    en  That's the kindest wrong thing anyone's said to me about it.
    >>  ............................................
    pt  É a coisa errada mais gentil que alguém já me disse sobre isso.
    >>  ............................................
  friendly.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't, %1$s. I know you mean it, and it isn't yours to give.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu sei que você é sincero, e não é seu pra dar.
    >>  ............................................
  friendly.dialogue.conversations.regrets.forgive.rebuff/2
    en  That's the kindest wrong thing anyone's said to me about it.
    >>  ............................................
    pt  É a coisa errada mais gentil que alguém já me disse sobre isso.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.forgive.rebuff/1
    en  Please don't. If I took that from you I'd only have to earn it twice.
    >>  ............................................
    pt  Por favor, não. Se eu aceitasse isso de você eu teria que merecer duas vezes.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.forgive.rebuff/2
    en  Don't. I've wanted to be forgiven for years and not by somebody who wasn't there, %1$s.
    >>  ............................................
    pt  Não. Faz anos que eu quero ser perdoado e não por quem não estava lá, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. Absolution from somebody who wasn't there is worth exactly nothing.
    >>  ............................................
    pt  Não. Absolvição de quem não estava lá não vale nada.
    >>  ............................................
  greedy.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. You can't forgive me for a thing that wasn't done to you.
    >>  ............................................
    pt  Não. Você não pode me perdoar por algo que não foi feito a você.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. Absolution from somebody who wasn't there is worth exactly nothing.
    >>  ............................................
    pt  Não. Absolvição de quem não estava lá não vale nada.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. You can't forgive me for a thing that wasn't done to you.
    >>  ............................................
    pt  Não. Você não pode me perdoar por algo que não foi feito a você.
    >>  ............................................
  introverted.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. You weren't there.
    >>  ............................................
    pt  Não. Você não estava lá.
    >>  ............................................
  introverted.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. It isn't yours to give me.
    >>  ............................................
    pt  Não. Não é seu pra me dar.
    >>  ............................................
  lazy.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. It'll settle in its own time or not at all, and it won't be your doing.
    >>  ............................................
    pt  Não. Vai assentar no tempo dele ou não, e não vai ser obra sua.
    >>  ............................................
  lazy.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. That's not a thing that gets handed over. It has to wear down.
    >>  ............................................
    pt  Não. Isso não é coisa que se entregue. Tem que se desgastar.
    >>  ............................................
  odd.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. You weren't there.
    >>  ............................................
    pt  Não. Você não estava lá.
    >>  ............................................
  odd.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. It isn't yours to give me.
    >>  ............................................
    pt  Não. Não é seu pra me dar.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. It'll settle in its own time or not at all, and it won't be your doing.
    >>  ............................................
    pt  Não. Vai assentar no tempo dele ou não, e não vai ser obra sua.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. That's not a thing that gets handed over. It has to wear down.
    >>  ............................................
    pt  Não. Isso não é coisa que se entregue. Tem que se desgastar.
    >>  ............................................
  peppy.dialogue.conversations.regrets.forgive.rebuff/1
    en  Ah — no. Kind of you, and entirely outside your jurisdiction.
    >>  ............................................
    pt  Ah — não. Gentil da sua parte, e completamente fora da sua jurisdição.
    >>  ............................................
  peppy.dialogue.conversations.regrets.forgive.rebuff/2
    en  Right, stop. You weren't there, %1$s. It's not yours to hand out.
    >>  ............................................
    pt  Certo, pare. Você não estava lá, %1$s. Não é seu pra distribuir.
    >>  ............................................
  playful.dialogue.conversations.regrets.forgive.rebuff/1
    en  Ah — no. Kind of you, and entirely outside your jurisdiction.
    >>  ............................................
    pt  Ah — não. Gentil da sua parte, e completamente fora da sua jurisdição.
    >>  ............................................
  playful.dialogue.conversations.regrets.forgive.rebuff/2
    en  Right, stop. You weren't there, %1$s. It's not yours to hand out.
    >>  ............................................
    pt  Certo, pare. Você não estava lá, %1$s. Não é seu pra distribuir.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. It'll settle in its own time or not at all, and it won't be your doing.
    >>  ............................................
    pt  Não. Vai assentar no tempo dele ou não, e não vai ser obra sua.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. That's not a thing that gets handed over. It has to wear down.
    >>  ............................................
    pt  Não. Isso não é coisa que se entregue. Tem que se desgastar.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.forgive.rebuff/1
    en  Please don't. If I took that from you I'd only have to earn it twice.
    >>  ............................................
    pt  Por favor, não. Se eu aceitasse isso de você eu teria que merecer duas vezes.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.forgive.rebuff/2
    en  Don't. I've wanted to be forgiven for years and not by somebody who wasn't there, %1$s.
    >>  ............................................
    pt  Não. Faz anos que eu quero ser perdoado e não por quem não estava lá, %1$s.
    >>  ............................................
  shy.dialogue.conversations.regrets.forgive.rebuff/1
    en  Don't. You weren't there.
    >>  ............................................
    pt  Não. Você não estava lá.
    >>  ............................................
  shy.dialogue.conversations.regrets.forgive.rebuff/2
    en  No. It isn't yours to give me.
    >>  ............................................
    pt  Não. Não é seu pra me dar.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.forgive.rebuff/1
    en  Ah — no. Kind of you, and entirely outside your jurisdiction.
    >>  ............................................
    pt  Ah — não. Gentil da sua parte, e completamente fora da sua jurisdição.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.forgive.rebuff/2
    en  Right, stop. You weren't there, %1$s. It's not yours to hand out.
    >>  ............................................
    pt  Certo, pare. Você não estava lá, %1$s. Não é seu pra distribuir.
    >>  ............................................
  witty.dialogue.conversations.regrets.forgive.rebuff/1
    en  Ah — no. Kind of you, and entirely outside your jurisdiction.
    >>  ............................................
    pt  Ah — não. Gentil da sua parte, e completamente fora da sua jurisdição.
    >>  ............................................
  witty.dialogue.conversations.regrets.forgive.rebuff/2
    en  Right, stop. You weren't there, %1$s. It's not yours to hand out.
    >>  ............................................
    pt  Certo, pare. Você não estava lá, %1$s. Não é seu pra distribuir.
    >>  ............................................
```

</details>


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: **hearts +2** — decision id `regrets.followup.forgive`, budget `deep`, replay policy `once`
- Does: disposition — warmth +5, trust +2  _(recorded under topic `regrets.followup.forgive`)_
- Does: arc `regrets` — advance to stage 1
- Does: exclusive `regrets.stance` -> `forgave` (locks the other side out for good)
- Then opens: `conversations.topic.regrets.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I've one of my own, if it helps." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.regrets.followup.forgive
WHO    VILLAGER — what the player reads after pressing "I don't think you're a bad person."
       spoken on: conversations.topic.regrets.followup, button `forgive`
       leaves the player on: conversations.topic.regrets.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.followup.forgive.to.regrets`: the villager accepts. Subject `regrets`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.followup.forgive/1   [78 chars]
    en  ...You don't. Nobody's said that. I'm not sure I believe it, but I'll keep it.
    >>  ............................................
    pt  ...Você não acha. Ninguém disse isso. Não sei se acredito, mas vou guardar.
    >>  ............................................
  dialogue.conversations.regrets.followup.forgive/2   [59 chars]
    en  That's a generous reading of me, %1$s. I'll try to earn it.
    >>  ............................................
    pt  É uma leitura generosa de mim, %1$s. Vou tentar merecer.
    >>  ............................................
  dialogue.conversations.regrets.followup.forgive/3   [49 chars]
    en  Careful. Say that and I might start believing it.
    >>  ............................................
    pt  Cuidado. Diga isso e eu posso começar a acreditar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't. Nobody's said that, %1$s. I'm not sure I believe it, and I want to.
    >>  ............................................
    pt  ...Você não acha. Ninguém disse isso, %1$s. Não sei se acredito, e eu quero.
    >>  ............................................
  anxious.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll hold it carefully and see whether it survives the morning.
    >>  ............................................
    pt  Certo. Vou segurar com cuidado e ver se sobrevive até de manhã.
    >>  ............................................
  anxious.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has ever said that to me. Give me a moment — I'd built a great deal on the opposite.
    >>  ............................................
    pt  Ninguém nunca me disse isso. Me dê um momento — eu construí muito sobre o contrário.
    >>  ............................................
  athletic.dialogue.conversations.regrets.followup.forgive/1
    en  You don't. Nobody's said that. I'll keep it and let it settle in its own time.
    >>  ............................................
    pt  Você não acha. Ninguém disse isso. Vou guardar e deixar assentar no tempo dele.
    >>  ............................................
  athletic.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll not decide about it tonight. These things take a season.
    >>  ............................................
    pt  Certo. Não vou decidir hoje. Essas coisas levam uma estação.
    >>  ............................................
  athletic.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud. I'll be a while working out what to do with it.
    >>  ............................................
    pt  Ninguém disse isso em voz alta. Vou levar um tempo pra saber o que fazer com isso.
    >>  ............................................
  confident.dialogue.conversations.regrets.followup.forgive/1
    en  You don't. Nobody's said that. I'm not sure I believe it.
    >>  ............................................
    pt  Você não acha. Ninguém disse isso. Não sei se eu acredito.
    >>  ............................................
  confident.dialogue.conversations.regrets.followup.forgive/2
    en  Right. You've said it. I'll hold it and see whether it holds.
    >>  ............................................
    pt  Certo. Você disse. Vou segurar e ver se se sustenta.
    >>  ............................................
  confident.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has ever said that out loud to me. I'll need to sit with it.
    >>  ............................................
    pt  Ninguém nunca me disse isso em voz alta. Vou precisar ficar com isso.
    >>  ............................................
  crabby.dialogue.conversations.regrets.followup.forgive/1
    en  You don't. Nobody's said that. I'm not sure I believe it.
    >>  ............................................
    pt  Você não acha. Ninguém disse isso. Não sei se eu acredito.
    >>  ............................................
  crabby.dialogue.conversations.regrets.followup.forgive/2
    en  Right. You've said it. I'll hold it and see whether it holds.
    >>  ............................................
    pt  Certo. Você disse. Vou segurar e ver se se sustenta.
    >>  ............................................
  crabby.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has ever said that out loud to me. I'll need to sit with it.
    >>  ............................................
    pt  Ninguém nunca me disse isso em voz alta. Vou precisar ficar com isso.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't, %1$s. Nobody's said that. I'm not sure I believe it, but I'll keep it.
    >>  ............................................
    pt  ...Você não acha, %1$s. Ninguém disse isso. Não sei se acredito, mas eu guardo.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.followup.forgive/2
    en  Right. From you it's harder to dismiss, which is inconvenient of you.
    >>  ............................................
    pt  Certo. Vindo de você é mais difícil de descartar, o que é inconveniente da sua parte.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud. I'd not known I was waiting to hear it.
    >>  ............................................
    pt  Ninguém disse isso em voz alta. Eu não sabia que estava esperando ouvir.
    >>  ............................................
  flirty.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't, %1$s. Nobody's said that. I'm not sure I believe it, but I'll keep it.
    >>  ............................................
    pt  ...Você não acha, %1$s. Ninguém disse isso. Não sei se acredito, mas eu guardo.
    >>  ............................................
  flirty.dialogue.conversations.regrets.followup.forgive/2
    en  Right. From you it's harder to dismiss, which is inconvenient of you.
    >>  ............................................
    pt  Certo. Vindo de você é mais difícil de descartar, o que é inconveniente da sua parte.
    >>  ............................................
  flirty.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud. I'd not known I was waiting to hear it.
    >>  ............................................
    pt  Ninguém disse isso em voz alta. Eu não sabia que estava esperando ouvir.
    >>  ............................................
  friendly.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't, %1$s. Nobody's said that. I'm not sure I believe it, but I'll keep it.
    >>  ............................................
    pt  ...Você não acha, %1$s. Ninguém disse isso. Não sei se acredito, mas eu guardo.
    >>  ............................................
  friendly.dialogue.conversations.regrets.followup.forgive/2
    en  Right. From you it's harder to dismiss, which is inconvenient of you.
    >>  ............................................
    pt  Certo. Vindo de você é mais difícil de descartar, o que é inconveniente da sua parte.
    >>  ............................................
  friendly.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud. I'd not known I was waiting to hear it.
    >>  ............................................
    pt  Ninguém disse isso em voz alta. Eu não sabia que estava esperando ouvir.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't. Nobody's said that, %1$s. I'm not sure I believe it, and I want to.
    >>  ............................................
    pt  ...Você não acha. Ninguém disse isso, %1$s. Não sei se acredito, e eu quero.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll hold it carefully and see whether it survives the morning.
    >>  ............................................
    pt  Certo. Vou segurar com cuidado e ver se sobrevive até de manhã.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has ever said that to me. Give me a moment — I'd built a great deal on the opposite.
    >>  ............................................
    pt  Ninguém nunca me disse isso. Me dê um momento — eu construí muito sobre o contrário.
    >>  ............................................
  greedy.dialogue.conversations.regrets.followup.forgive/1
    en  You don't. Nobody's said that. I'm not sure I believe it.
    >>  ............................................
    pt  Você não acha. Ninguém disse isso. Não sei se eu acredito.
    >>  ............................................
  greedy.dialogue.conversations.regrets.followup.forgive/2
    en  Right. You've said it. I'll hold it and see whether it holds.
    >>  ............................................
    pt  Certo. Você disse. Vou segurar e ver se se sustenta.
    >>  ............................................
  greedy.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has ever said that out loud to me. I'll need to sit with it.
    >>  ............................................
    pt  Ninguém nunca me disse isso em voz alta. Vou precisar ficar com isso.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.followup.forgive/1
    en  You don't. Nobody's said that. I'm not sure I believe it.
    >>  ............................................
    pt  Você não acha. Ninguém disse isso. Não sei se eu acredito.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.followup.forgive/2
    en  Right. You've said it. I'll hold it and see whether it holds.
    >>  ............................................
    pt  Certo. Você disse. Vou segurar e ver se se sustenta.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has ever said that out loud to me. I'll need to sit with it.
    >>  ............................................
    pt  Ninguém nunca me disse isso em voz alta. Vou precisar ficar com isso.
    >>  ............................................
  introverted.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't. Nobody's said that.
    >>  ............................................
    pt  ...Você não acha. Ninguém disse isso.
    >>  ............................................
  introverted.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll keep it.
    >>  ............................................
    pt  Certo. Vou guardar.
    >>  ............................................
  introverted.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud before.
    >>  ............................................
    pt  Ninguém tinha dito isso em voz alta.
    >>  ............................................
  lazy.dialogue.conversations.regrets.followup.forgive/1
    en  You don't. Nobody's said that. I'll keep it and let it settle in its own time.
    >>  ............................................
    pt  Você não acha. Ninguém disse isso. Vou guardar e deixar assentar no tempo dele.
    >>  ............................................
  lazy.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll not decide about it tonight. These things take a season.
    >>  ............................................
    pt  Certo. Não vou decidir hoje. Essas coisas levam uma estação.
    >>  ............................................
  lazy.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud. I'll be a while working out what to do with it.
    >>  ............................................
    pt  Ninguém disse isso em voz alta. Vou levar um tempo pra saber o que fazer com isso.
    >>  ............................................
  odd.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't. Nobody's said that.
    >>  ............................................
    pt  ...Você não acha. Ninguém disse isso.
    >>  ............................................
  odd.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll keep it.
    >>  ............................................
    pt  Certo. Vou guardar.
    >>  ............................................
  odd.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud before.
    >>  ............................................
    pt  Ninguém tinha dito isso em voz alta.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.followup.forgive/1
    en  You don't. Nobody's said that. I'll keep it and let it settle in its own time.
    >>  ............................................
    pt  Você não acha. Ninguém disse isso. Vou guardar e deixar assentar no tempo dele.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll not decide about it tonight. These things take a season.
    >>  ............................................
    pt  Certo. Não vou decidir hoje. Essas coisas levam uma estação.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud. I'll be a while working out what to do with it.
    >>  ............................................
    pt  Ninguém disse isso em voz alta. Vou levar um tempo pra saber o que fazer com isso.
    >>  ............................................
  peppy.dialogue.conversations.regrets.followup.forgive/1
    en  You don't! Nobody's said that. I'm not sure I believe it, but I'll keep it anyway.
    >>  ............................................
    pt  Você não acha! Ninguém disse isso. Não sei se acredito, mas eu guardo mesmo assim.
    >>  ............................................
  peppy.dialogue.conversations.regrets.followup.forgive/2
    en  Right. Said out loud. I'd not have predicted that as this evening's development.
    >>  ............................................
    pt  Certo. Dito em voz alta. Eu não teria previsto isso como o rumo da noite.
    >>  ............................................
  peppy.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody's ever said that to me. I'm going to be strange about it for a week.
    >>  ............................................
    pt  Ninguém nunca me disse isso. Vou ficar estranho por uma semana.
    >>  ............................................
  playful.dialogue.conversations.regrets.followup.forgive/1
    en  You don't! Nobody's said that. I'm not sure I believe it, but I'll keep it anyway.
    >>  ............................................
    pt  Você não acha! Ninguém disse isso. Não sei se acredito, mas eu guardo mesmo assim.
    >>  ............................................
  playful.dialogue.conversations.regrets.followup.forgive/2
    en  Right. Said out loud. I'd not have predicted that as this evening's development.
    >>  ............................................
    pt  Certo. Dito em voz alta. Eu não teria previsto isso como o rumo da noite.
    >>  ............................................
  playful.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody's ever said that to me. I'm going to be strange about it for a week.
    >>  ............................................
    pt  Ninguém nunca me disse isso. Vou ficar estranho por uma semana.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.followup.forgive/1
    en  You don't. Nobody's said that. I'll keep it and let it settle in its own time.
    >>  ............................................
    pt  Você não acha. Ninguém disse isso. Vou guardar e deixar assentar no tempo dele.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll not decide about it tonight. These things take a season.
    >>  ............................................
    pt  Certo. Não vou decidir hoje. Essas coisas levam uma estação.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud. I'll be a while working out what to do with it.
    >>  ............................................
    pt  Ninguém disse isso em voz alta. Vou levar um tempo pra saber o que fazer com isso.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't. Nobody's said that, %1$s. I'm not sure I believe it, and I want to.
    >>  ............................................
    pt  ...Você não acha. Ninguém disse isso, %1$s. Não sei se acredito, e eu quero.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll hold it carefully and see whether it survives the morning.
    >>  ............................................
    pt  Certo. Vou segurar com cuidado e ver se sobrevive até de manhã.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has ever said that to me. Give me a moment — I'd built a great deal on the opposite.
    >>  ............................................
    pt  Ninguém nunca me disse isso. Me dê um momento — eu construí muito sobre o contrário.
    >>  ............................................
  shy.dialogue.conversations.regrets.followup.forgive/1
    en  ...You don't. Nobody's said that.
    >>  ............................................
    pt  ...Você não acha. Ninguém disse isso.
    >>  ............................................
  shy.dialogue.conversations.regrets.followup.forgive/2
    en  Right. I'll keep it.
    >>  ............................................
    pt  Certo. Vou guardar.
    >>  ............................................
  shy.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody has said that out loud before.
    >>  ............................................
    pt  Ninguém tinha dito isso em voz alta.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.followup.forgive/1
    en  You don't! Nobody's said that. I'm not sure I believe it, but I'll keep it anyway.
    >>  ............................................
    pt  Você não acha! Ninguém disse isso. Não sei se acredito, mas eu guardo mesmo assim.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.followup.forgive/2
    en  Right. Said out loud. I'd not have predicted that as this evening's development.
    >>  ............................................
    pt  Certo. Dito em voz alta. Eu não teria previsto isso como o rumo da noite.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody's ever said that to me. I'm going to be strange about it for a week.
    >>  ............................................
    pt  Ninguém nunca me disse isso. Vou ficar estranho por uma semana.
    >>  ............................................
  witty.dialogue.conversations.regrets.followup.forgive/1
    en  You don't! Nobody's said that. I'm not sure I believe it, but I'll keep it anyway.
    >>  ............................................
    pt  Você não acha! Ninguém disse isso. Não sei se acredito, mas eu guardo mesmo assim.
    >>  ............................................
  witty.dialogue.conversations.regrets.followup.forgive/2
    en  Right. Said out loud. I'd not have predicted that as this evening's development.
    >>  ............................................
    pt  Certo. Dito em voz alta. Eu não teria previsto isso como o rumo da noite.
    >>  ............................................
  witty.dialogue.conversations.regrets.followup.forgive/3
    en  Nobody's ever said that to me. I'm going to be strange about it for a week.
    >>  ............................................
    pt  Ninguém nunca me disse isso. Vou ficar estranho por uma semana.
    >>  ............................................
```

</details>


### Button `sit_with_it` — "I won't tell you it was fine. But I'm here."

*stance family `restraint` · tone `gentle` · answers the beat(s) `regrets.sit_with_it.stay.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.followup.sit_with_it` — accepted phrasings: "i will not tell you it was fine"; "i am here anyway"; "i will not pretend it was fine"
  - the message must contain one of: `fine`, `here`, `wont`
  - scored words: `fine`(1.2), `here`(1.0), `wont`(1.0), `tell`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.followup.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.followup.sit_with_it   [43 chars]
    en  I won't tell you it was fine. But I'm here.
    >>  ............................................
    pt  Não vou dizer que foi tudo bem. Mas estou aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `regrets.followup.sit_with_it`, budget `deep`, replay policy `once`
- Does: disposition — trust +6, respect +2  _(recorded under topic `regrets.followup.sit_with_it`)_
- Does: arc `regrets` — advance to stage 1
- Does: exclusive `regrets.stance` -> `listened` (locks the other side out for good)
- Then opens: `conversations.topic.regrets.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I've one of my own, if it helps." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.regrets.followup.sit_with_it
WHO    VILLAGER — what the player reads after pressing "I won't tell you it was fine. But I'm here."
       spoken on: conversations.topic.regrets.followup, button `sit_with_it`
       leaves the player on: conversations.topic.regrets.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.followup.sit_with_it.to.regrets`: the villager accepts. Subject `regrets`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.followup.sit_with_it/1   [61 chars]
    en  ...That's better. Absolution I'd not have trusted. This I do.
    >>  ............................................
    pt  ...Isso é melhor. Absolvição eu não teria confiado. Nisso eu confio.
    >>  ............................................
  dialogue.conversations.regrets.followup.sit_with_it/2   [49 chars]
    en  You didn't tidy it up for me. Thank you for that.
    >>  ............................................
    pt  Você não arrumou isso para mim. Obrigado por isso.
    >>  ............................................
  dialogue.conversations.regrets.followup.sit_with_it/3   [59 chars]
    en  Not fine, and not alone either. I can live with that, %1$s.
    >>  ............................................
    pt  Não está tudo bem, e nem estou sozinho. Consigo viver com isso, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better. Absolution I'd not have trusted, %1$s. This I do.
    >>  ............................................
    pt  ...Isso é melhor. Absolvição eu não confiaria, %1$s. Nisto eu confio.
    >>  ............................................
  anxious.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't reach for the easy thing. I'd braced for the easy thing.
    >>  ............................................
    pt  Certo. Você não pegou o caminho fácil. Eu tinha me preparado pro caminho fácil.
    >>  ............................................
  anxious.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. I'll probably not say much for a while. That's all right, isn't it.
    >>  ............................................
    pt  Ficar com isso. Eu provavelmente não vou falar muito por um tempo. Tudo bem, né?
    >>  ............................................
  athletic.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better. Absolution I'd not have trusted. This wears better.
    >>  ............................................
    pt  Isso é melhor. Absolvição eu não confiaria. Isto se conserva melhor.
    >>  ............................................
  athletic.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. Nothing fixed, nothing hurried. That's the only way it ever eases.
    >>  ............................................
    pt  Certo. Nada consertado, nada apressado. É o único jeito de aliviar.
    >>  ............................................
  athletic.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. It'll take the years it takes and you've made them easier.
    >>  ............................................
    pt  Ficar com isso. Vai levar os anos que levar e você os tornou mais fáceis.
    >>  ............................................
  confident.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better. Absolution I'd not have trusted. This I do.
    >>  ............................................
    pt  Isso é melhor. Absolvição eu não confiaria. Nisto eu confio.
    >>  ............................................
  confident.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't try to fix it. That's why it helps.
    >>  ............................................
    pt  Certo. Você não tentou consertar. É por isso que ajuda.
    >>  ............................................
  confident.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Fine. That's the only thing that's ever worked.
    >>  ............................................
    pt  Ficar com isso. Tudo bem. É a única coisa que já funcionou.
    >>  ............................................
  crabby.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better. Absolution I'd not have trusted. This I do.
    >>  ............................................
    pt  Isso é melhor. Absolvição eu não confiaria. Nisto eu confio.
    >>  ............................................
  crabby.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't try to fix it. That's why it helps.
    >>  ............................................
    pt  Certo. Você não tentou consertar. É por isso que ajuda.
    >>  ............................................
  crabby.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Fine. That's the only thing that's ever worked.
    >>  ............................................
    pt  Ficar com isso. Tudo bem. É a única coisa que já funcionou.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better, %1$s. Absolution I'd not have trusted. This I do.
    >>  ............................................
    pt  ...Isso é melhor, %1$s. Absolvição eu não confiaria. Nisto eu confio.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You stayed and said nothing useful, and that was the useful thing.
    >>  ............................................
    pt  Certo. Você ficou e não disse nada útil, e essa foi a coisa útil.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Thank you. That's what I'd have asked for if I'd known how.
    >>  ............................................
    pt  Ficar com isso. Obrigado. É o que eu teria pedido se soubesse como.
    >>  ............................................
  flirty.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better, %1$s. Absolution I'd not have trusted. This I do.
    >>  ............................................
    pt  ...Isso é melhor, %1$s. Absolvição eu não confiaria. Nisto eu confio.
    >>  ............................................
  flirty.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You stayed and said nothing useful, and that was the useful thing.
    >>  ............................................
    pt  Certo. Você ficou e não disse nada útil, e essa foi a coisa útil.
    >>  ............................................
  flirty.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Thank you. That's what I'd have asked for if I'd known how.
    >>  ............................................
    pt  Ficar com isso. Obrigado. É o que eu teria pedido se soubesse como.
    >>  ............................................
  friendly.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better, %1$s. Absolution I'd not have trusted. This I do.
    >>  ............................................
    pt  ...Isso é melhor, %1$s. Absolvição eu não confiaria. Nisto eu confio.
    >>  ............................................
  friendly.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You stayed and said nothing useful, and that was the useful thing.
    >>  ............................................
    pt  Certo. Você ficou e não disse nada útil, e essa foi a coisa útil.
    >>  ............................................
  friendly.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Thank you. That's what I'd have asked for if I'd known how.
    >>  ............................................
    pt  Ficar com isso. Obrigado. É o que eu teria pedido se soubesse como.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better. Absolution I'd not have trusted, %1$s. This I do.
    >>  ............................................
    pt  ...Isso é melhor. Absolvição eu não confiaria, %1$s. Nisto eu confio.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't reach for the easy thing. I'd braced for the easy thing.
    >>  ............................................
    pt  Certo. Você não pegou o caminho fácil. Eu tinha me preparado pro caminho fácil.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. I'll probably not say much for a while. That's all right, isn't it.
    >>  ............................................
    pt  Ficar com isso. Eu provavelmente não vou falar muito por um tempo. Tudo bem, né?
    >>  ............................................
  greedy.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better. Absolution I'd not have trusted. This I do.
    >>  ............................................
    pt  Isso é melhor. Absolvição eu não confiaria. Nisto eu confio.
    >>  ............................................
  greedy.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't try to fix it. That's why it helps.
    >>  ............................................
    pt  Certo. Você não tentou consertar. É por isso que ajuda.
    >>  ............................................
  greedy.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Fine. That's the only thing that's ever worked.
    >>  ............................................
    pt  Ficar com isso. Tudo bem. É a única coisa que já funcionou.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better. Absolution I'd not have trusted. This I do.
    >>  ............................................
    pt  Isso é melhor. Absolvição eu não confiaria. Nisto eu confio.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't try to fix it. That's why it helps.
    >>  ............................................
    pt  Certo. Você não tentou consertar. É por isso que ajuda.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Fine. That's the only thing that's ever worked.
    >>  ............................................
    pt  Ficar com isso. Tudo bem. É a única coisa que já funcionou.
    >>  ............................................
  introverted.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better. Absolution I'd not have trusted.
    >>  ............................................
    pt  ...Isso é melhor. Absolvição eu não confiaria.
    >>  ............................................
  introverted.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't try to fix it.
    >>  ............................................
    pt  Certo. Você não tentou consertar.
    >>  ............................................
  introverted.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Yes.
    >>  ............................................
    pt  Ficar com isso. Sim.
    >>  ............................................
  lazy.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better. Absolution I'd not have trusted. This wears better.
    >>  ............................................
    pt  Isso é melhor. Absolvição eu não confiaria. Isto se conserva melhor.
    >>  ............................................
  lazy.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. Nothing fixed, nothing hurried. That's the only way it ever eases.
    >>  ............................................
    pt  Certo. Nada consertado, nada apressado. É o único jeito de aliviar.
    >>  ............................................
  lazy.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. It'll take the years it takes and you've made them easier.
    >>  ............................................
    pt  Ficar com isso. Vai levar os anos que levar e você os tornou mais fáceis.
    >>  ............................................
  odd.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better. Absolution I'd not have trusted.
    >>  ............................................
    pt  ...Isso é melhor. Absolvição eu não confiaria.
    >>  ............................................
  odd.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't try to fix it.
    >>  ............................................
    pt  Certo. Você não tentou consertar.
    >>  ............................................
  odd.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Yes.
    >>  ............................................
    pt  Ficar com isso. Sim.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better. Absolution I'd not have trusted. This wears better.
    >>  ............................................
    pt  Isso é melhor. Absolvição eu não confiaria. Isto se conserva melhor.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. Nothing fixed, nothing hurried. That's the only way it ever eases.
    >>  ............................................
    pt  Certo. Nada consertado, nada apressado. É o único jeito de aliviar.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. It'll take the years it takes and you've made them easier.
    >>  ............................................
    pt  Ficar com isso. Vai levar os anos que levar e você os tornou mais fáceis.
    >>  ............................................
  peppy.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better! Absolution I'd not have trusted. This I do, oddly.
    >>  ............................................
    pt  Isso é melhor! Absolvição eu não confiaria. Nisto eu confio, curiosamente.
    >>  ............................................
  peppy.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right — you didn't try to fix it. Everyone tries to fix it. It's exhausting.
    >>  ............................................
    pt  Certo — você não tentou consertar. Todo mundo tenta consertar. É exaustivo.
    >>  ............................................
  peppy.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Splendid. Nobody offers that; they all bring a toolkit.
    >>  ............................................
    pt  Ficar com isso. Esplêndido. Ninguém oferece; todos trazem uma caixa de ferramentas.
    >>  ............................................
  playful.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better! Absolution I'd not have trusted. This I do, oddly.
    >>  ............................................
    pt  Isso é melhor! Absolvição eu não confiaria. Nisto eu confio, curiosamente.
    >>  ............................................
  playful.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right — you didn't try to fix it. Everyone tries to fix it. It's exhausting.
    >>  ............................................
    pt  Certo — você não tentou consertar. Todo mundo tenta consertar. É exaustivo.
    >>  ............................................
  playful.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Splendid. Nobody offers that; they all bring a toolkit.
    >>  ............................................
    pt  Ficar com isso. Esplêndido. Ninguém oferece; todos trazem uma caixa de ferramentas.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better. Absolution I'd not have trusted. This wears better.
    >>  ............................................
    pt  Isso é melhor. Absolvição eu não confiaria. Isto se conserva melhor.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. Nothing fixed, nothing hurried. That's the only way it ever eases.
    >>  ............................................
    pt  Certo. Nada consertado, nada apressado. É o único jeito de aliviar.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. It'll take the years it takes and you've made them easier.
    >>  ............................................
    pt  Ficar com isso. Vai levar os anos que levar e você os tornou mais fáceis.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better. Absolution I'd not have trusted, %1$s. This I do.
    >>  ............................................
    pt  ...Isso é melhor. Absolvição eu não confiaria, %1$s. Nisto eu confio.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't reach for the easy thing. I'd braced for the easy thing.
    >>  ............................................
    pt  Certo. Você não pegou o caminho fácil. Eu tinha me preparado pro caminho fácil.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. I'll probably not say much for a while. That's all right, isn't it.
    >>  ............................................
    pt  Ficar com isso. Eu provavelmente não vou falar muito por um tempo. Tudo bem, né?
    >>  ............................................
  shy.dialogue.conversations.regrets.followup.sit_with_it/1
    en  ...That's better. Absolution I'd not have trusted.
    >>  ............................................
    pt  ...Isso é melhor. Absolvição eu não confiaria.
    >>  ............................................
  shy.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right. You didn't try to fix it.
    >>  ............................................
    pt  Certo. Você não tentou consertar.
    >>  ............................................
  shy.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Yes.
    >>  ............................................
    pt  Ficar com isso. Sim.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better! Absolution I'd not have trusted. This I do, oddly.
    >>  ............................................
    pt  Isso é melhor! Absolvição eu não confiaria. Nisto eu confio, curiosamente.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right — you didn't try to fix it. Everyone tries to fix it. It's exhausting.
    >>  ............................................
    pt  Certo — você não tentou consertar. Todo mundo tenta consertar. É exaustivo.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Splendid. Nobody offers that; they all bring a toolkit.
    >>  ............................................
    pt  Ficar com isso. Esplêndido. Ninguém oferece; todos trazem uma caixa de ferramentas.
    >>  ............................................
  witty.dialogue.conversations.regrets.followup.sit_with_it/1
    en  That's better! Absolution I'd not have trusted. This I do, oddly.
    >>  ............................................
    pt  Isso é melhor! Absolvição eu não confiaria. Nisto eu confio, curiosamente.
    >>  ............................................
  witty.dialogue.conversations.regrets.followup.sit_with_it/2
    en  Right — you didn't try to fix it. Everyone tries to fix it. It's exhausting.
    >>  ............................................
    pt  Certo — você não tentou consertar. Todo mundo tenta consertar. É exaustivo.
    >>  ............................................
  witty.dialogue.conversations.regrets.followup.sit_with_it/3
    en  Sitting with it. Splendid. Nobody offers that; they all bring a toolkit.
    >>  ............................................
    pt  Ficar com isso. Esplêndido. Ninguém oferece; todos trazem uma caixa de ferramentas.
    >>  ............................................
```

</details>


### Button `challenge` — "You could still make it right."

*stance family `challenge` · tone `blunt` · answers the beat(s) `regrets.sit_with_it.stay.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.followup.challenge` — accepted phrasings: "you could still make it right"; "it is not too late to make it right"; "you can still put it right"
  - the message must contain one of: `still`, `make`, `right`
  - scored words: `still`(1.2), `make`(1.0), `right`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.followup.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.followup.challenge   [30 chars]
    en  You could still make it right.
    >>  ............................................
    pt  Você ainda pode consertar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts +1** — decision id `regrets.followup.challenge`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `regrets.followup.challenge`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.followup.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You could still make it right."
       spoken on: conversations.topic.regrets.followup, button `challenge`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.followup.challenge.landed.terminal`: the villager accepts. Subject `regrets.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.followup.challenge.landed/1   [65 chars]
    en  ...I could. You're right, and I've been telling myself otherwise.
    >>  ............................................
    pt  ...Eu poderia. Você tem razão, e eu venho me dizendo o contrário.
    >>  ............................................
  dialogue.conversations.regrets.followup.challenge.landed/2   [51 chars]
    en  Blunt. Correct. That's the pair of things I needed.
    >>  ............................................
    pt  Direto. Correto. É essa a dupla que eu precisava.
    >>  ............................................
  dialogue.conversations.regrets.followup.challenge.landed/3   [59 chars]
    en  Still time, you reckon. Alright. I'll think on it properly.
    >>  ............................................
    pt  Ainda dá tempo, você acha. Certo. Vou pensar direito nisso.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`
- Does: **hearts -1** — decision id `regrets.followup.challenge`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5, trust -2  _(recorded under topic `regrets.followup.challenge`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.followup.challenge.flat
WHO    VILLAGER — what the player reads after pressing "You could still make it right."
       spoken on: conversations.topic.regrets.followup, button `challenge`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.followup.challenge.flat.terminal`: the villager accepts. Subject `regrets.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.followup.challenge.flat/1   [43 chars]
    en  ...You've no idea what it would cost, %1$s.
    >>  ............................................
    pt  ...Você não faz ideia do que custaria, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.followup.challenge.flat/2   [68 chars]
    en  Make it right. As though I hadn't thought of that every night since.
    >>  ............................................
    pt  Consertar. Como se eu não tivesse pensado nisso toda noite desde então.
    >>  ............................................
  dialogue.conversations.regrets.followup.challenge.flat/3   [73 chars]
    en  That's a lot to say to someone who just told you the worst of themselves.
    >>  ............................................
    pt  É muita coisa para dizer a alguém que acabou de te contar o pior de si.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `regrets.followup.challenge`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.followup.challenge.polite
WHO    VILLAGER — what the player reads after pressing "You could still make it right."
       spoken on: conversations.topic.regrets.followup, button `challenge`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.followup.challenge.polite.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.followup.challenge.polite/1   [52 chars]
    en  Maybe. Maybe it's too late for that particular door.
    >>  ............................................
    pt  Talvez. Talvez seja tarde demais para essa porta específica.
    >>  ............................................
  dialogue.conversations.regrets.followup.challenge.polite/2   [55 chars]
    en  You might be right. I'd want to be sure before I tried.
    >>  ............................................
    pt  Você pode ter razão. Eu ia querer ter certeza antes de tentar.
    >>  ............................................
  dialogue.conversations.regrets.followup.challenge.polite/3   [42 chars]
    en  Perhaps. I'll not decide it standing here.
    >>  ............................................
    pt  Talvez. Não vou decidir isso aqui de pé.
    >>  ............................................
```


### Button `leave` — "That's a lot to carry. I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `regrets.sit_with_it.stay.to.regrets` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.followup.leave   [31 chars]
    en  That's a lot to carry. I'll go.
    >>  ............................................
    pt  Isso é muito para carregar. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.respond.leave
WHO    VILLAGER — what the player reads after pressing "That's a lot to carry. I'll go."
       spoken on: conversations.topic.regrets.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.respond.leave.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.regrets.respond / leave
```

```text
  dialogue.conversations.regrets.respond.leave/1   [51 chars]
    en  So it is. Thank you for not making a face about it.
    >>  ............................................
    pt  É assim mesmo. Obrigado por não fazer careta.
    >>  ............................................
  dialogue.conversations.regrets.respond.leave/2   [42 chars]
    en  Go on, %1$s. It'll still be mine tomorrow.
    >>  ............................................
    pt  Pode ir, %1$s. Ainda vai ser meu amanhã.
    >>  ............................................
  dialogue.conversations.regrets.respond.leave/3   [34 chars]
    en  Right. Enough of that for one day.
    >>  ............................................
    pt  Certo. Já chega disso por um dia.
    >>  ............................................
```

---


## `conversations.topic.regrets.guarded.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `regrets`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.deflect.intimate` — e.g. "That question belongs to someone I trust with everything. We're not there."


```text
POOL   dialogue key: dialogue.conversations.topic.regrets.guarded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.regrets.guarded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.regrets.guarded.respond   [31 chars]
    en  That one I put down on purpose.
    >>  ............................................
    pt  Essa eu larguei de propósito.
    >>  ............................................
```


### Button `respect` — "Then leave it where it is."

*stance family `restraint` · tone `plain` · answers the beat(s) `deflect.intimate.to.regrets.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.guarded.respect` — accepted phrasings: "that is yours to keep"; "keep it to yourself"; "that regret is yours"
  - the message must contain one of: `yours`, `keep`
  - scored words: `yours`(1.5), `keep`(1.2), `regret`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.guarded.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.guarded.respond.respect   [26 chars]
    en  Then leave it where it is.
    >>  ............................................
    pt  Então deixe onde está.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.guarded.respect`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `regrets.guarded.respect`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.guarded.respect
WHO    VILLAGER — what the player reads after pressing "Then leave it where it is."
       spoken on: conversations.topic.regrets.guarded.respond, button `respect`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.guarded.respect.terminal`: the villager deflects. Subject `regrets.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.guarded.respect/1   [62 chars]
    en  ...Thank you. It isn't a thing that improves with an audience.
    >>  ............................................
    pt  ...Obrigado. Não é coisa que melhore com plateia.
    >>  ............................................
  dialogue.conversations.regrets.guarded.respect/2   [79 chars]
    en  So it is. You'll get it the day I can say it without my voice doing that, %1$s.
    >>  ............................................
    pt  É assim mesmo. Você vai ouvir no dia em que eu conseguir falar sem a voz fazer aquilo, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.guarded.respect/3   [54 chars]
    en  Good. Not everything wants digging up to be looked at.
    >>  ............................................
    pt  Bom. Nem tudo quer ser desenterrado para ser olhado.
    >>  ............................................
```


### Button `ask_safer` — "Tell me something you don't regret, then."

*stance family `curiosity` · tone `gentle` · answers the beat(s) `deflect.intimate.to.regrets.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.guarded.ask_safer` — accepted phrasings: "tell me something lighter"; "something easier then"; "let us keep it light"
  - the message must contain one of: `lighter`, `easier`
  - scored words: `lighter`(1.5), `easier`(1.2), `regret`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.guarded.respond.ask_safer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.guarded.respond.ask_safer   [41 chars]
    en  Tell me something you don't regret, then.
    >>  ............................................
    pt  Então me conta algo de que você não se arrepende.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `regrets.guarded.ask_safer`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.guarded.ask_safer
WHO    VILLAGER — what the player reads after pressing "Tell me something you don't regret, then."
       spoken on: conversations.topic.regrets.guarded.respond, button `ask_safer`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.guarded.ask_safer.terminal`: the villager deflects. Subject `regrets.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.guarded.ask_safer/1   [78 chars]
    en  Anything that isn't behind me. The front of my life is perfectly good company.
    >>  ............................................
    pt  Qualquer coisa que não esteja atrás de mim. A frente da minha vida é boa companhia.
    >>  ............................................
  dialogue.conversations.regrets.guarded.ask_safer/2   [52 chars]
    en  Ask me about now. Now I've made my peace with, %1$s.
    >>  ............................................
    pt  Me pergunte do agora. Com o agora eu já fiz as pazes, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.guarded.ask_safer/3   [83 chars]
    en  Something I'd not have to apologise for, then. That narrows it, but not to nothing.
    >>  ............................................
    pt  Algo pelo que eu não tivesse que pedir desculpa, então. Estreita, mas não zera.
    >>  ............................................
```


### Button `press` — "Lift it for me, just once."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `deflect.intimate.to.regrets.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.guarded.press` — accepted phrasings: "come on, you can tell me"; "tell me the regret"; "go on, tell me"
  - the message must contain one of: `come`, `tell`
  - scored words: `come`(1.2), `tell`(1.0), `regret`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.guarded.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.guarded.respond.press   [26 chars]
    en  Lift it for me, just once.
    >>  ............................................
    pt  Levanta essa pedra pra mim, só uma vez.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `regrets.guarded.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5  _(recorded under topic `regrets.guarded.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.guarded.press
WHO    VILLAGER — what the player reads after pressing "Lift it for me, just once."
       spoken on: conversations.topic.regrets.guarded.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.guarded.press.terminal`: the villager resists. Subject `regrets.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.guarded.press/1   [77 chars]
    en  You're asking me to lift a stone I put down on purpose. Leave it where it is.
    >>  ............................................
    pt  Você está me pedindo para levantar uma pedra que eu larguei de propósito. Deixe onde está.
    >>  ............................................
  dialogue.conversations.regrets.guarded.press/2   [72 chars]
    en  That door stays shut, %1$s, and it isn't shut because I forgot about it.
    >>  ............................................
    pt  Essa porta fica fechada, %1$s, e não é por esquecimento.
    >>  ............................................
  dialogue.conversations.regrets.guarded.press/3   [91 chars]
    en  No. Some things you tell people once they've seen you at your worst and stayed. Not before.
    >>  ............................................
    pt  Não. Certas coisas a gente conta a quem já viu o pior e ficou. Não antes.
    >>  ............................................
```


### Button `leave` — "I'll not dig. Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `deflect.intimate.to.regrets.guarded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.guarded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.guarded.respond.leave   [27 chars]
    en  I'll not dig. Another time.
    >>  ............................................
    pt  Não vou cavar. Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.guarded.leave
WHO    VILLAGER — what the player reads after pressing "I'll not dig. Another time."
       spoken on: conversations.topic.regrets.guarded.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.guarded.leave.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.guarded.leave/1   [22 chars]
    en  Aye. No hard feelings.
    >>  ............................................
    pt  Tá. Sem ressentimento.
    >>  ............................................
  dialogue.conversations.regrets.guarded.leave/2   [40 chars]
    en  Off you go. We'll get there or we won't.
    >>  ............................................
    pt  Pode ir. A gente chega lá ou não.
    >>  ............................................
  dialogue.conversations.regrets.guarded.leave/3   [19 chars]
    en  Get on, then, %1$s.
    >>  ............................................
    pt  Então vá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.regrets.pried.followup`

**Reached from 1 route(s):** `conversations.topic.regrets.respond` / `pry`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.regrets.respond.pry` — e.g. "...That's not what I offered you, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.regrets.pried.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.regrets.pried.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.regrets.pried.followup   [36 chars]
    en  That I regret it is what I gave you.
    >>  ............................................
    pt  Eu te dei o arrependimento, não o resto.
    >>  ............................................
```


### Button `apologize` — "You're right. I was after the story."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `regrets.rebuked` · offered only once the villager has actually said `player:pried`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.rebuked.apologize` — accepted phrasings: "you're right. i was after the story"
  - the message must contain one of: `story`, `prying`
  - scored words: `story`(1.5), `after`(0.8), `prying`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.pried.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.pried.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.pried.followup.apologize   [36 chars]
    en  You're right. I was after the story.
    >>  ............................................
    pt  Você tem razão. Eu queria a história.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `regrets.rebuked.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.rebuked.apologize
WHO    VILLAGER — what the player reads after pressing "You're right. I was after the story."
       spoken on: conversations.topic.regrets.pried.followup, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.rebuked.apologize`: the villager qualifys. Subject `regrets.past`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.regrets.rebuked.apologize/1   [59 chars]
    en  ...Nearly everyone is. Few of them admit it standing there.
    >>  ............................................
    pt  ...Quase todo mundo quer. Poucos admitem ali de pé.
    >>  ............................................
  dialogue.conversations.regrets.rebuked.apologize/2   [58 chars]
    en  True enough. And now you've said it, I mind it less, %1$s.
    >>  ............................................
    pt  Bem verdade. E agora que você disse, me incomoda menos, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.rebuked.apologize/3   [56 chars]
    en  Then we're square, and the rest of it stays where it is.
    >>  ............................................
    pt  Então estamos quites, e o resto fica onde está.
    >>  ............................................
```


### Button `explain` — "I asked badly. I did want to understand."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `regrets.rebuked`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.rebuked.explain` — accepted phrasings: "i asked badly. i did want to understand"
  - the message must contain one of: `badly`, `understand`
  - scored words: `badly`(1.5), `understand`(1.2), `asked`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.pried.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.pried.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.pried.followup.explain   [40 chars]
    en  I asked badly. I did want to understand.
    >>  ............................................
    pt  Perguntei mal. Eu queria entender de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `regrets.rebuked.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.rebuked.explain
WHO    VILLAGER — what the player reads after pressing "I asked badly. I did want to understand."
       spoken on: conversations.topic.regrets.pried.followup, button `explain`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.rebuked.explain`: the villager qualifys. Subject `regrets.past`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.regrets.rebuked.explain/1   [63 chars]
    en  ...Then ask about the person and not the deed. That's the door.
    >>  ............................................
    pt  ...Então pergunte da pessoa e não do ato. É essa a porta.
    >>  ............................................
  dialogue.conversations.regrets.rebuked.explain/2   [78 chars]
    en  Understanding I'll give you. The details are for the person I did it to, %1$s.
    >>  ............................................
    pt  Entendimento eu te dou. Os detalhes são pra pessoa a quem eu fiz aquilo, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.rebuked.explain/3   [68 chars]
    en  Badly asked, aye. But asked. I'll take that over the polite silence.
    >>  ............................................
    pt  Mal perguntado, é. Mas perguntado. Prefiro isso ao silêncio educado.
    >>  ............................................
```


### Button `leave` — "I'll not ask again."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `regrets.rebuked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.pried.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.pried.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.pried.followup.leave   [19 chars]
    en  I'll not ask again.
    >>  ............................................
    pt  Não vou perguntar de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.rebuked.leave
WHO    VILLAGER — what the player reads after pressing "I'll not ask again."
       spoken on: conversations.topic.regrets.pried.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.rebuked.leave`: the villager accepts. Subject `regrets.past`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.regrets.rebuked.leave/1   [40 chars]
    en  Good. That's the whole of what I wanted.
    >>  ............................................
    pt  Bom. É tudo que eu queria.
    >>  ............................................
  dialogue.conversations.regrets.rebuked.leave/2   [26 chars]
    en  Just so. Off you go, %1$s.
    >>  ............................................
    pt  Pois é. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.rebuked.leave/3   [36 chars]
    en  Right. Thank you for hearing the no.
    >>  ............................................
    pt  Certo. Obrigado por ouvir o não.
    >>  ............................................
```

---


## `conversations.topic.regrets.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `regrets`; `conversations.cat.personal` / `regrets`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.regrets.first` — e.g. "One or two. There was a door I didn't knock on, years ago. I still walk past it."
- `conversations.regrets.revisit` — e.g. "That thing I told you about. It's still there, mostly."


```text
POOL   dialogue key: dialogue.conversations.topic.regrets.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.regrets.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.regrets.respond   [34 chars]
    en  That's the one that stays with me.
    >>  ............................................
    pt  É esse que fica comigo.
    >>  ............................................
```


### Button `listen` — "I'm not going anywhere. Go on."

*stance family `restraint` · tone `gentle` · answers the beat(s) `regrets.first.to.regrets`, `regrets.revisit.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.respond.listen` — accepted phrasings: "i am not going anywhere"; "go on, i am listening"; "i am staying right here"
  - the message must contain one of: `nowhere`, `going`, `listening`
  - scored words: `going`(1.2), `nowhere`(1.5), `listening`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.respond.listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.respond.listen   [30 chars]
    en  I'm not going anywhere. Go on.
    >>  ............................................
    pt  Não vou a lugar nenhum. Continue.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `regrets.respond.listen`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +5, warmth +1  _(recorded under topic `regrets.respond.listen`)_
- Then opens: `conversations.topic.regrets.sit_with_it`
- …where the player's next choices will be: "I'm still here." | "I'll give you the room."

```text
POOL   dialogue key: dialogue.conversations.regrets.respond.listen
WHO    VILLAGER — what the player reads after pressing "I'm not going anywhere. Go on."
       spoken on: conversations.topic.regrets.respond, button `listen`
       leaves the player on: conversations.topic.regrets.sit_with_it
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.respond.listen.to.regrets.sit_with_it`: the villager accepts. Subject `regrets.sit_with_it`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.respond.listen/1   [64 chars]
    en  ...Alright. Nobody stays for this part. They remember an errand.
    >>  ............................................
    pt  ...Certo. Ninguém fica para esta parte. Lembram de um compromisso.
    >>  ............................................
  dialogue.conversations.regrets.respond.listen/2   [66 chars]
    en  You'll stand there and hear it. That's the whole of what I wanted.
    >>  ............................................
    pt  Você vai ficar aí e ouvir. Era só isso que eu queria.
    >>  ............................................
  dialogue.conversations.regrets.respond.listen/3   [70 chars]
    en  Then I'll say the rest, and you can decide what you think of me after.
    >>  ............................................
    pt  Então vou dizer o resto, e você decide o que pensa de mim depois.
    >>  ............................................
```


### Button `ask_repair` — "Is there anything left to put right?"

*stance family `curiosity` · tone `gentle` · answers the beat(s) `regrets.first.to.regrets`, `regrets.revisit.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.respond.ask_repair` — accepted phrasings: "is there anything left to put right"; "can it be fixed"; "anything left to make right"
  - the message must contain one of: `right`, `fix`, `left`, `put`
  - scored words: `right`(1.2), `put`(1.0), `fix`(1.5), `left`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.respond.ask_repair
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.respond.ask_repair   [36 chars]
    en  Is there anything left to put right?
    >>  ............................................
    pt  Ainda dá para consertar alguma coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.respond.ask_repair`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +1  _(recorded under topic `regrets.respond.ask_repair`)_
- Then opens: `conversations.topic.regrets.sit_with_it`
- …where the player's next choices will be: "I'm still here." | "I'll give you the room."

```text
POOL   dialogue key: dialogue.conversations.regrets.respond.ask_repair
WHO    VILLAGER — what the player reads after pressing "Is there anything left to put right?"
       spoken on: conversations.topic.regrets.respond, button `ask_repair`
       leaves the player on: conversations.topic.regrets.sit_with_it
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.respond.ask_repair.to.regrets.sit_with_it`: the villager accepts. Subject `regrets.sit_with_it`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.respond.ask_repair/1   [61 chars]
    en  ...Maybe. I've not let myself look at that question properly.
    >>  ............................................
    pt  ...Talvez. Não me permiti olhar essa pergunta direito.
    >>  ............................................
  dialogue.conversations.regrets.respond.ask_repair/2   [66 chars]
    en  Put right. Huh. Everyone else asks what happened, not what's left.
    >>  ............................................
    pt  Consertar. Hm. Todo mundo pergunta o que aconteceu, não o que sobrou.
    >>  ............................................
  dialogue.conversations.regrets.respond.ask_repair/3   [60 chars]
    en  There might be. It'd take more courage than I've had lately.
    >>  ............................................
    pt  Pode ser que sim. Exigiria mais coragem do que eu tive ultimamente.
    >>  ............................................
```


### Button `pry` — "What exactly did you do?"

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `regrets.first.to.regrets`, `regrets.revisit.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.respond.pry` — accepted phrasings: "what exactly did you do"; "give me the details"; "what did you do exactly"
  - the message must contain one of: `exactly`, `details`
  - scored words: `exactly`(1.5), `did`(0.5), `details`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.respond.pry
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.respond.pry   [24 chars]
    en  What exactly did you do?
    >>  ............................................
    pt  O que exatamente você fez?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `regrets.respond.pry`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +6, trust -3  _(recorded under topic `regrets.respond.pry`)_
- Does: session `turn`
- Then opens: `conversations.topic.regrets.pried.followup`
- …where the player's next choices will be: "You're right. I was after the story." | "I asked badly. I did want to understand." | "I'll not ask again."

```text
POOL   dialogue key: dialogue.conversations.regrets.respond.pry
WHO    VILLAGER — what the player reads after pressing "What exactly did you do?"
       spoken on: conversations.topic.regrets.respond, button `pry`
       leaves the player on: conversations.topic.regrets.pried.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.rebuked`: the villager refuses. Subject `regrets.past`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   this is the line that establishes `player:pried` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.regrets.respond.pry/1   [39 chars]
    en  ...That's not what I offered you, %1$s.
    >>  ............................................
    pt  ...Não foi isso que eu te ofereci, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.respond.pry/2   [64 chars]
    en  Exactly what I did is mine. That I regret it is what I gave you.
    >>  ............................................
    pt  O que exatamente eu fiz é meu. Que eu me arrependo é o que eu te dei.
    >>  ............................................
  dialogue.conversations.regrets.respond.pry/3   [67 chars]
    en  You want the detail more than the person. I've noticed that before.
    >>  ............................................
    pt  Você quer o detalhe mais que a pessoa. Já notei isso antes.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered you, %1$s. I gave you what I could carry saying.
    >>  ............................................
    pt  ...Não foi isso que eu te ofereci, %1$s. Eu dei o que eu aguentava dizer.
    >>  ............................................
  anxious.dialogue.conversations.regrets.respond.pry/2
    en  Please. The rest of it isn't something I can put into a sentence yet.
    >>  ............................................
    pt  Por favor. O resto ainda não é algo que eu consiga pôr numa frase.
    >>  ............................................
  anxious.dialogue.conversations.regrets.respond.pry/3
    en  ...I shouldn't have started. I always go a little too far and then this happens.
    >>  ............................................
    pt  ...Eu não devia ter começado. Eu sempre vou um pouco longe demais e aí acontece isso.
    >>  ............................................
  athletic.dialogue.conversations.regrets.respond.pry/1
    en  That's not what I offered. The rest keeps, and it's kept a long while already.
    >>  ............................................
    pt  Não foi isso que eu ofereci. O resto espera, e já espera faz tempo.
    >>  ............................................
  athletic.dialogue.conversations.regrets.respond.pry/2
    en  ...No. Some of it I'll tell you eventually and some of it I won't.
    >>  ............................................
    pt  ...Não. Parte eu te conto uma hora e parte não.
    >>  ............................................
  athletic.dialogue.conversations.regrets.respond.pry/3
    en  Right. There's no hurry on any of it, least of all that part.
    >>  ............................................
    pt  Certo. Não há pressa em nada disso, muito menos nessa parte.
    >>  ............................................
  confident.dialogue.conversations.regrets.respond.pry/1
    en  That's not what I offered you.
    >>  ............................................
    pt  Não foi isso que eu te ofereci.
    >>  ............................................
  confident.dialogue.conversations.regrets.respond.pry/2
    en  No. I gave you the shape of it, not the details.
    >>  ............................................
    pt  Não. Eu te dei o formato, não os detalhes.
    >>  ............................................
  confident.dialogue.conversations.regrets.respond.pry/3
    en  ...I'll not fill that in for you.
    >>  ............................................
    pt  ...Não vou preencher isso pra você.
    >>  ............................................
  crabby.dialogue.conversations.regrets.respond.pry/1
    en  That's not what I offered you.
    >>  ............................................
    pt  Não foi isso que eu te ofereci.
    >>  ............................................
  crabby.dialogue.conversations.regrets.respond.pry/2
    en  No. I gave you the shape of it, not the details.
    >>  ............................................
    pt  Não. Eu te dei o formato, não os detalhes.
    >>  ............................................
  crabby.dialogue.conversations.regrets.respond.pry/3
    en  ...I'll not fill that in for you.
    >>  ............................................
    pt  ...Não vou preencher isso pra você.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered, %1$s. I'd have said if it were.
    >>  ............................................
    pt  ...Não foi isso que eu ofereci, %1$s. Eu teria dito se fosse.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.respond.pry/2
    en  I trusted you with the outline. The rest isn't ready to be handled.
    >>  ............................................
    pt  Eu te confiei o contorno. O resto não está pronto pra ser tocado.
    >>  ............................................
  extroverted.dialogue.conversations.regrets.respond.pry/3
    en  ...Right. Ask me again in a year and it might be different.
    >>  ............................................
    pt  ...Certo. Me pergunte em um ano e pode ser diferente.
    >>  ............................................
  flirty.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered, %1$s. I'd have said if it were.
    >>  ............................................
    pt  ...Não foi isso que eu ofereci, %1$s. Eu teria dito se fosse.
    >>  ............................................
  flirty.dialogue.conversations.regrets.respond.pry/2
    en  I trusted you with the outline. The rest isn't ready to be handled.
    >>  ............................................
    pt  Eu te confiei o contorno. O resto não está pronto pra ser tocado.
    >>  ............................................
  flirty.dialogue.conversations.regrets.respond.pry/3
    en  ...Right. Ask me again in a year and it might be different.
    >>  ............................................
    pt  ...Certo. Me pergunte em um ano e pode ser diferente.
    >>  ............................................
  friendly.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered, %1$s. I'd have said if it were.
    >>  ............................................
    pt  ...Não foi isso que eu ofereci, %1$s. Eu teria dito se fosse.
    >>  ............................................
  friendly.dialogue.conversations.regrets.respond.pry/2
    en  I trusted you with the outline. The rest isn't ready to be handled.
    >>  ............................................
    pt  Eu te confiei o contorno. O resto não está pronto pra ser tocado.
    >>  ............................................
  friendly.dialogue.conversations.regrets.respond.pry/3
    en  ...Right. Ask me again in a year and it might be different.
    >>  ............................................
    pt  ...Certo. Me pergunte em um ano e pode ser diferente.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered you, %1$s. I gave you what I could carry saying.
    >>  ............................................
    pt  ...Não foi isso que eu te ofereci, %1$s. Eu dei o que eu aguentava dizer.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.respond.pry/2
    en  Please. The rest of it isn't something I can put into a sentence yet.
    >>  ............................................
    pt  Por favor. O resto ainda não é algo que eu consiga pôr numa frase.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.respond.pry/3
    en  ...I shouldn't have started. I always go a little too far and then this happens.
    >>  ............................................
    pt  ...Eu não devia ter começado. Eu sempre vou um pouco longe demais e aí acontece isso.
    >>  ............................................
  greedy.dialogue.conversations.regrets.respond.pry/1
    en  That's not what I offered you.
    >>  ............................................
    pt  Não foi isso que eu te ofereci.
    >>  ............................................
  greedy.dialogue.conversations.regrets.respond.pry/2
    en  No. I gave you the shape of it, not the details.
    >>  ............................................
    pt  Não. Eu te dei o formato, não os detalhes.
    >>  ............................................
  greedy.dialogue.conversations.regrets.respond.pry/3
    en  ...I'll not fill that in for you.
    >>  ............................................
    pt  ...Não vou preencher isso pra você.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.respond.pry/1
    en  That's not what I offered you.
    >>  ............................................
    pt  Não foi isso que eu te ofereci.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.respond.pry/2
    en  No. I gave you the shape of it, not the details.
    >>  ............................................
    pt  Não. Eu te dei o formato, não os detalhes.
    >>  ............................................
  grumpy.dialogue.conversations.regrets.respond.pry/3
    en  ...I'll not fill that in for you.
    >>  ............................................
    pt  ...Não vou preencher isso pra você.
    >>  ............................................
  introverted.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered.
    >>  ............................................
    pt  ...Não foi isso que eu ofereci.
    >>  ............................................
  introverted.dialogue.conversations.regrets.respond.pry/2
    en  No. Not that part.
    >>  ............................................
    pt  Não. Essa parte não.
    >>  ............................................
  introverted.dialogue.conversations.regrets.respond.pry/3
    en  ...I'll stop there.
    >>  ............................................
    pt  ...Eu paro aí.
    >>  ............................................
  lazy.dialogue.conversations.regrets.respond.pry/1
    en  That's not what I offered. The rest keeps, and it's kept a long while already.
    >>  ............................................
    pt  Não foi isso que eu ofereci. O resto espera, e já espera faz tempo.
    >>  ............................................
  lazy.dialogue.conversations.regrets.respond.pry/2
    en  ...No. Some of it I'll tell you eventually and some of it I won't.
    >>  ............................................
    pt  ...Não. Parte eu te conto uma hora e parte não.
    >>  ............................................
  lazy.dialogue.conversations.regrets.respond.pry/3
    en  Right. There's no hurry on any of it, least of all that part.
    >>  ............................................
    pt  Certo. Não há pressa em nada disso, muito menos nessa parte.
    >>  ............................................
  odd.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered.
    >>  ............................................
    pt  ...Não foi isso que eu ofereci.
    >>  ............................................
  odd.dialogue.conversations.regrets.respond.pry/2
    en  No. Not that part.
    >>  ............................................
    pt  Não. Essa parte não.
    >>  ............................................
  odd.dialogue.conversations.regrets.respond.pry/3
    en  ...I'll stop there.
    >>  ............................................
    pt  ...Eu paro aí.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.respond.pry/1
    en  That's not what I offered. The rest keeps, and it's kept a long while already.
    >>  ............................................
    pt  Não foi isso que eu ofereci. O resto espera, e já espera faz tempo.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.respond.pry/2
    en  ...No. Some of it I'll tell you eventually and some of it I won't.
    >>  ............................................
    pt  ...Não. Parte eu te conto uma hora e parte não.
    >>  ............................................
  peaceful.dialogue.conversations.regrets.respond.pry/3
    en  Right. There's no hurry on any of it, least of all that part.
    >>  ............................................
    pt  Certo. Não há pressa em nada disso, muito menos nessa parte.
    >>  ............................................
  peppy.dialogue.conversations.regrets.respond.pry/1
    en  ...Ah, no. You've gone straight past the door I opened, %1$s.
    >>  ............................................
    pt  ...Ah, não. Você passou direto pela porta que eu abri, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.regrets.respond.pry/2
    en  Right! That's not on offer. Lovely try, though.
    >>  ............................................
    pt  Certo! Isso não está à venda. Boa tentativa, no entanto.
    >>  ............................................
  peppy.dialogue.conversations.regrets.respond.pry/3
    en  ...Ha. No. I gave you an inch and you've measured the room.
    >>  ............................................
    pt  ...Ha. Não. Eu dei um dedo e você mediu a sala.
    >>  ............................................
  playful.dialogue.conversations.regrets.respond.pry/1
    en  ...Ah, no. You've gone straight past the door I opened, %1$s.
    >>  ............................................
    pt  ...Ah, não. Você passou direto pela porta que eu abri, %1$s.
    >>  ............................................
  playful.dialogue.conversations.regrets.respond.pry/2
    en  Right! That's not on offer. Lovely try, though.
    >>  ............................................
    pt  Certo! Isso não está à venda. Boa tentativa, no entanto.
    >>  ............................................
  playful.dialogue.conversations.regrets.respond.pry/3
    en  ...Ha. No. I gave you an inch and you've measured the room.
    >>  ............................................
    pt  ...Ha. Não. Eu dei um dedo e você mediu a sala.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.respond.pry/1
    en  That's not what I offered. The rest keeps, and it's kept a long while already.
    >>  ............................................
    pt  Não foi isso que eu ofereci. O resto espera, e já espera faz tempo.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.respond.pry/2
    en  ...No. Some of it I'll tell you eventually and some of it I won't.
    >>  ............................................
    pt  ...Não. Parte eu te conto uma hora e parte não.
    >>  ............................................
  relaxed.dialogue.conversations.regrets.respond.pry/3
    en  Right. There's no hurry on any of it, least of all that part.
    >>  ............................................
    pt  Certo. Não há pressa em nada disso, muito menos nessa parte.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered you, %1$s. I gave you what I could carry saying.
    >>  ............................................
    pt  ...Não foi isso que eu te ofereci, %1$s. Eu dei o que eu aguentava dizer.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.respond.pry/2
    en  Please. The rest of it isn't something I can put into a sentence yet.
    >>  ............................................
    pt  Por favor. O resto ainda não é algo que eu consiga pôr numa frase.
    >>  ............................................
  sensitive.dialogue.conversations.regrets.respond.pry/3
    en  ...I shouldn't have started. I always go a little too far and then this happens.
    >>  ............................................
    pt  ...Eu não devia ter começado. Eu sempre vou um pouco longe demais e aí acontece isso.
    >>  ............................................
  shy.dialogue.conversations.regrets.respond.pry/1
    en  ...That's not what I offered.
    >>  ............................................
    pt  ...Não foi isso que eu ofereci.
    >>  ............................................
  shy.dialogue.conversations.regrets.respond.pry/2
    en  No. Not that part.
    >>  ............................................
    pt  Não. Essa parte não.
    >>  ............................................
  shy.dialogue.conversations.regrets.respond.pry/3
    en  ...I'll stop there.
    >>  ............................................
    pt  ...Eu paro aí.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.respond.pry/1
    en  ...Ah, no. You've gone straight past the door I opened, %1$s.
    >>  ............................................
    pt  ...Ah, não. Você passou direto pela porta que eu abri, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.respond.pry/2
    en  Right! That's not on offer. Lovely try, though.
    >>  ............................................
    pt  Certo! Isso não está à venda. Boa tentativa, no entanto.
    >>  ............................................
  upbeat.dialogue.conversations.regrets.respond.pry/3
    en  ...Ha. No. I gave you an inch and you've measured the room.
    >>  ............................................
    pt  ...Ha. Não. Eu dei um dedo e você mediu a sala.
    >>  ............................................
  witty.dialogue.conversations.regrets.respond.pry/1
    en  ...Ah, no. You've gone straight past the door I opened, %1$s.
    >>  ............................................
    pt  ...Ah, não. Você passou direto pela porta que eu abri, %1$s.
    >>  ............................................
  witty.dialogue.conversations.regrets.respond.pry/2
    en  Right! That's not on offer. Lovely try, though.
    >>  ............................................
    pt  Certo! Isso não está à venda. Boa tentativa, no entanto.
    >>  ............................................
  witty.dialogue.conversations.regrets.respond.pry/3
    en  ...Ha. No. I gave you an inch and you've measured the room.
    >>  ............................................
    pt  ...Ha. Não. Eu dei um dedo e você mediu a sala.
    >>  ............................................
```

</details>


### Button `no_words` — "I don't know what to say to that."

*stance family `restraint` · tone `gentle` · answers the beat(s) `regrets.first.to.regrets`, `regrets.revisit.to.regrets`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.respond.no_words` — accepted phrasings: "i have no reply to that"; "there is no reply to that"; "i cannot reply to that"
  - the message must contain one of: `reply`, `words`
  - scored words: `reply`(1.2), `words`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.respond.no_words
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.respond.no_words   [33 chars]
    en  I don't know what to say to that.
    >>  ............................................
    pt  Não sei o que dizer sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.no_words`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `regrets.no_words`)_
- Then opens: `conversations.topic.regrets.sit_with_it`
- …where the player's next choices will be: "I'm still here." | "I'll give you the room."

```text
POOL   dialogue key: dialogue.conversations.regrets.no_words
WHO    VILLAGER — what the player reads after pressing "I don't know what to say to that."
       spoken on: conversations.topic.regrets.respond, button `no_words`
       leaves the player on: conversations.topic.regrets.sit_with_it
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.no_words.to.regrets.sit_with_it`: the villager accepts. Subject `regrets.sit_with_it`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.no_words/1   [79 chars]
    en  ...Then say nothing. Everyone who has something ready has usually not listened.
    >>  ............................................
    pt  ...Então não diga nada. Quem tem resposta pronta geralmente não escutou.
    >>  ............................................
  dialogue.conversations.regrets.no_words/2   [77 chars]
    en  Good. The people with the quick answers are the ones I stopped telling, %1$s.
    >>  ............................................
    pt  Bom. As pessoas com respostas rápidas são as que eu parei de procurar, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.no_words/3   [59 chars]
    en  That's the right amount to say. I'd been braced for advice.
    >>  ............................................
    pt  É a quantidade certa de coisa a dizer. Eu estava preparado para conselho.
    >>  ............................................
```


### Button `leave` — "That's a lot to carry. I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `regrets.first.to.regrets`, `regrets.revisit.to.regrets` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.respond.leave   [31 chars]
    en  That's a lot to carry. I'll go.
    >>  ............................................
    pt  Isso é muito para carregar. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.respond.leave
WHO    VILLAGER — what the player reads after pressing "That's a lot to carry. I'll go."
       spoken on: conversations.topic.regrets.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.respond.leave.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.regrets.followup / leave
```

> Written out in full under **`conversations.topic.regrets.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.regrets.sit_with_it`

**Reached from 3 route(s):** `conversations.topic.regrets.respond` / `listen`; `conversations.topic.regrets.respond` / `ask_repair`; `conversations.topic.regrets.respond` / `no_words`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.regrets.no_words` — e.g. "...Then say nothing. Everyone who has something ready has usually not listened."
- `conversations.regrets.respond.ask_repair` — e.g. "...Maybe. I've not let myself look at that question properly."
- `conversations.regrets.respond.listen` — e.g. "...Alright. Nobody stays for this part. They remember an errand."


```text
POOL   dialogue key: dialogue.conversations.topic.regrets.sit_with_it
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.regrets.sit_with_it
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.regrets.sit_with_it   [3 chars]
    en  ...
    >>  ............................................
    pt  ...
    >>  ............................................
```


### Button `stay` — "I'm still here."

*stance family `restraint` · tone `gentle` · answers the beat(s) `regrets.no_words.to.regrets.sit_with_it`, `regrets.respond.ask_repair.to.regrets.sit_with_it`, `regrets.respond.listen.to.regrets.sit_with_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `regrets.sit_with_it.stay` — accepted phrasings: "i am still here"; "i have not gone anywhere"; "i am not going anywhere"
  - the message must contain one of: `here`, `gone`, `anywhere`
  - scored words: `here`(1.6), `gone`(1.3), `anywhere`(1.1)

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.sit_with_it.stay
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.sit_with_it
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.sit_with_it.stay   [15 chars]
    en  I'm still here.
    >>  ............................................
    pt  Eu continuo aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `regrets.sit_with_it.stay`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +5, warmth +3  _(recorded under topic `regrets.sit_with_it.stay`)_
- Then opens: `conversations.topic.regrets.followup`
- …where the player's next choices will be: "I don't think you're a bad person." | "I won't tell you it was fine. But I'm here." | "You could still make it right." | "That's a lot to carry. I'll go."

```text
POOL   dialogue key: dialogue.conversations.regrets.sit_with_it.stay
WHO    VILLAGER — what the player reads after pressing "I'm still here."
       spoken on: conversations.topic.regrets.sit_with_it, button `stay`
       leaves the player on: conversations.topic.regrets.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.sit_with_it.stay.to.regrets`: the villager accepts. Subject `regrets`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.sit_with_it.stay/1   [93 chars]
    en  ...Right. Thank you. Most of the way through that I was waiting for the sound of you leaving.
    >>  ............................................
    pt  ...Certo. Obrigado. Metade do tempo eu estava esperando o barulho de você indo embora.
    >>  ............................................
  dialogue.conversations.regrets.sit_with_it.stay/2   [80 chars]
    en  You are, aren't you. ...Give me a moment and then I'll say the rest of it, %1$s.
    >>  ............................................
    pt  Você está mesmo, né. ...Me dá um instante e eu digo o resto, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.sit_with_it.stay/3   [84 chars]
    en  Still here. Aye. That's the whole of what I needed and I couldn't have asked for it.
    >>  ............................................
    pt  Continua aqui. É. Era só isso que eu precisava e eu não conseguiria pedir.
    >>  ............................................
```


### Button `step_away` — "I'll give you the room."

*stance family `exit` · tone `gentle` · answers the beat(s) `regrets.no_words.to.regrets.sit_with_it`, `regrets.respond.ask_repair.to.regrets.sit_with_it`, `regrets.respond.listen.to.regrets.sit_with_it` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.regrets.sit_with_it.step_away
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.regrets.sit_with_it
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.regrets.sit_with_it.step_away   [23 chars]
    en  I'll give you the room.
    >>  ............................................
    pt  Vou te dar espaço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.sit_with_it.step_away
WHO    VILLAGER — what the player reads after pressing "I'll give you the room."
       spoken on: conversations.topic.regrets.sit_with_it, button `step_away`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.sit_with_it.step_away.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.sit_with_it.step_away/1   [43 chars]
    en  ...Aye. That's kind too, in its way. Go on.
    >>  ............................................
    pt  ...É. Isso também é gentileza, do jeito dele. Pode ir.
    >>  ............................................
  dialogue.conversations.regrets.sit_with_it.step_away/2   [76 chars]
    en  Room. Right. I'll take the room, %1$s, and thank you for not dressing it up.
    >>  ............................................
    pt  Espaço. Certo. Eu aceito o espaço, %1$s, e obrigado por não enfeitar.
    >>  ............................................
  dialogue.conversations.regrets.sit_with_it.step_away/3   [55 chars]
    en  Fair enough. It's a lot to put on somebody's afternoon.
    >>  ............................................
    pt  Justo. É muita coisa para jogar na tarde de alguém.
    >>  ............................................
```

---

