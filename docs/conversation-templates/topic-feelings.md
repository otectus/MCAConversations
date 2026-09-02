# Topic: feelings

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `feelings` |
| Opened from | question `conversations.cat.personal`, button `feelings` |
| Depth class (its heart budget) | `relationship` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `curiosity`, `candor`, `restraint`, `exit` |
| Narrative arc | `feelings`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.feelings
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.feelings   [32 chars]
    en  How do you really feel about me?
    >>  ............................................
    pt  O que você realmente sente por mim?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.feelings.resume.followup`](#conversations-arc-feelings-resume-followup)
- [`conversations.arc.feelings.resume.respond`](#conversations-arc-feelings-resume-respond)
- [`conversations.feelings`](#conversations-feelings)
- [`conversations.scene.feelings.a_flat_stretch.respond`](#conversations-scene-feelings-a-flat-stretch-respond)
- [`conversations.scene.feelings.followup`](#conversations-scene-feelings-followup)
- [`conversations.scene.feelings.ordinary_answer.respond`](#conversations-scene-feelings-ordinary-answer-respond)
- [`conversations.topic.feelings.again.respond`](#conversations-topic-feelings-again-respond)
- [`conversations.topic.feelings.close`](#conversations-topic-feelings-close)
- [`conversations.topic.feelings.guarded.respond`](#conversations-topic-feelings-guarded-respond)
- [`conversations.topic.feelings.hurt.close`](#conversations-topic-feelings-hurt-close)
- [`conversations.topic.feelings.platonic.followup`](#conversations-topic-feelings-platonic-followup)
- [`conversations.topic.feelings.platonic.respond`](#conversations-topic-feelings-platonic-respond)
- [`conversations.topic.feelings.romantic.followup`](#conversations-topic-feelings-romantic-followup)
- [`conversations.topic.feelings.romantic.respond`](#conversations-topic-feelings-romantic-respond)
- [`conversations.topic.feelings.toddler.respond`](#conversations-topic-feelings-toddler-respond)
- [`conversations.topic.feelings.young.respond`](#conversations-topic-feelings-young-respond)

---

## `conversations.arc.feelings.resume.followup`

**Reached from 2 route(s):** `conversations.arc.feelings.resume.respond` / `check_in`; `conversations.arc.feelings.resume.respond` / `reaffirm`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.resume.check_in` — e.g. "Changed? No. If anything it's settled into something steadier."
- `conversations.feelings.resume.reaffirm` — e.g. "...You meant it. People say things once and hope you forget."


```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.feelings.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.feelings.resume.followup   [29 chars]
    en  So that's where I am with it.
    >>  ............................................
    pt  Então é aí que eu estou com isso.
    >>  ............................................
```


### Button `warm` — "I'm glad you told me."

*stance family `empathy` · tone `gentle` · answers the beat(s) `feelings.resume.check_in.to.feelings`, `feelings.resume.reaffirm.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.resume.followup.warm` — accepted phrasings: "i am glad you told me"; "glad you told me that"; "i am glad you said it"
  - the message must contain one of: `glad`
  - scored words: `glad`(1.6), `told`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.followup.warm
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.feelings.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.feelings.resume.followup.warm   [21 chars]
    en  I'm glad you told me.
    >>  ............................................
    pt  Fico feliz que você tenha me contado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.resume.followup.warm`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `feelings.resume.followup.warm`)_
- Then opens: `conversations.topic.feelings.close`
- …where the player's next choices will be: "Thank you for saying it." | "That wasn't easy to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.feelings.resume.followup.warm
WHO    VILLAGER — what the player reads after pressing "I'm glad you told me."
       spoken on: conversations.arc.feelings.resume.followup, button `warm`
       leaves the player on: conversations.topic.feelings.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.resume.followup.warm.to.feelings`: the villager accepts. Subject `feelings`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.resume.followup.warm/1   [86 chars]
    en  You're glad. ...I'd spent a week deciding whether telling you would cost me something.
    >>  ............................................
    pt  Você fica feliz. ...Passei uma semana decidindo se contar ia me custar alguma coisa.
    >>  ............................................
  dialogue.conversations.feelings.resume.followup.warm/2   [62 chars]
    en  Then it was worth the saying. I wasn't sure it would be, %1$s.
    >>  ............................................
    pt  Então valeu a pena dizer. Eu não tinha certeza que valeria, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.resume.followup.warm/3   [72 chars]
    en  Good. That's a weight off, and I'd been carrying it about like a bucket.
    >>  ............................................
    pt  Bom. Saiu um peso, e eu vinha carregando ele feito um balde.
    >>  ............................................
```


### Button `ask_more` — "Is there more of it than that?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `feelings.resume.check_in.to.feelings`, `feelings.resume.reaffirm.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.resume.followup.ask_more` — accepted phrasings: "is there more of it than that"; "is there more to it"; "is that all of it or is there more"
  - the message must contain one of: `more`
  - scored words: `more`(1.5), `than`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.feelings.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.feelings.resume.followup.ask_more   [30 chars]
    en  Is there more of it than that?
    >>  ............................................
    pt  Tem mais do que isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +4, trust +1  _(recorded under topic `feelings.resume.followup.ask_more`)_
- Then opens: `conversations.topic.feelings.close`
- …where the player's next choices will be: "Thank you for saying it." | "That wasn't easy to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.feelings.resume.followup.ask_more
WHO    VILLAGER — what the player reads after pressing "Is there more of it than that?"
       spoken on: conversations.arc.feelings.resume.followup, button `ask_more`
       leaves the player on: conversations.topic.feelings.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.resume.followup.ask_more.to.feelings`: the villager explains. Subject `feelings`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.resume.followup.ask_more/1   [72 chars]
    en  There's more. There's always more. But that's the part with words on it.
    >>  ............................................
    pt  Tem mais. Sempre tem mais. Mas essa é a parte que tem palavras.
    >>  ............................................
  dialogue.conversations.feelings.resume.followup.ask_more/2   [72 chars]
    en  Some. The rest hasn't got a shape yet — ask me in a season and it might.
    >>  ............................................
    pt  Um pouco. O resto ainda não tem forma — me pergunte daqui a uma estação e talvez tenha.
    >>  ............................................
  dialogue.conversations.feelings.resume.followup.ask_more/3   [92 chars]
    en  You're the only one who's asked the follow-up question, %1$s. Most stop at the first answer.
    >>  ............................................
    pt  Você é o único que fez a segunda pergunta, %1$s. A maioria para na primeira resposta.
    >>  ............................................
```


### Button `boundary` — "I should be honest about where I am."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.resume.check_in.to.feelings`, `feelings.resume.reaffirm.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.resume.followup.boundary` — accepted phrasings: "i should be honest about where i am"; "let me be honest about where i stand"; "i want to be honest about where i am"
  - the message must contain one of: `honest`, `where`
  - scored words: `honest`(1.5), `where`(1.1)

```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.followup.boundary
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.feelings.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.feelings.resume.followup.boundary   [36 chars]
    en  I should be honest about where I am.
    >>  ............................................
    pt  Preciso ser honesto sobre onde eu estou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +5, tension +2  _(recorded under topic `feelings.resume.followup.boundary`)_
- Then opens: `conversations.topic.feelings.close`
- …where the player's next choices will be: "Thank you for saying it." | "That wasn't easy to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.feelings.resume.followup.boundary
WHO    VILLAGER — what the player reads after pressing "I should be honest about where I am."
       spoken on: conversations.arc.feelings.resume.followup, button `boundary`
       leaves the player on: conversations.topic.feelings.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.resume.followup.boundary.to.feelings`: the villager accepts. Subject `feelings`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.resume.followup.boundary/1   [93 chars]
    en  ...Thank you for saying it straight. I'd rather know the shape of the ground I'm standing on.
    >>  ............................................
    pt  ...Obrigado por dizer direto. Prefiro saber a forma do chão em que estou pisando.
    >>  ............................................
  dialogue.conversations.feelings.resume.followup.boundary/2   [77 chars]
    en  That's fair. It stings a little, and it's fair, and I'd have it no other way.
    >>  ............................................
    pt  É justo. Arde um pouco, e é justo, e eu não ia querer de outro jeito.
    >>  ............................................
  dialogue.conversations.feelings.resume.followup.boundary/3   [63 chars]
    en  Honest is better. It's not what I hoped, but it's better, %1$s.
    >>  ............................................
    pt  Honesto é melhor. Não é o que eu esperava, mas é melhor, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let it sit."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.resume.check_in.to.feelings`, `feelings.resume.reaffirm.to.feelings` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.feelings.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.feelings.resume.followup.leave   [16 chars]
    en  I'll let it sit.
    >>  ............................................
    pt  Vou deixar isso assentar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.resume.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll let it sit."
       spoken on: conversations.arc.feelings.resume.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.resume.followup.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.resume.followup.leave/1   [71 chars]
    en  So I've found. Let it sit. Things settle better when nobody stirs them.
    >>  ............................................
    pt  Foi o que eu vi. Deixa assentar. As coisas assentam melhor quando ninguém mexe.
    >>  ............................................
  dialogue.conversations.feelings.resume.followup.leave/2   [34 chars]
    en  Right. We'll leave it where it is.
    >>  ............................................
    pt  Certo. Deixamos onde está.
    >>  ............................................
  dialogue.conversations.feelings.resume.followup.leave/3   [35 chars]
    en  Off you go, %1$s. Nothing's broken.
    >>  ............................................
    pt  Pode ir, %1$s. Nada quebrou.
    >>  ............................................
```

---


## `conversations.arc.feelings.resume.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.revisit` — e.g. "I've been thinking about what I said to you. I meant it, in case you wondered."


```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.feelings.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.feelings.resume.respond   [25 chars]
    en  About what I said to you.
    >>  ............................................
    pt  Sobre o que eu te disse.
    >>  ............................................
```


### Button `check_in` — "Has anything changed for you?"

*stance family `curiosity` · tone `gentle` · answers the beat(s) `feelings.revisit.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.resume.check_in` — accepted phrasings: "has anything changed for you"; "has anything changed since then"; "is it any different now"
  - the message must contain one of: `changed`
  - scored words: `anything`(0.5), `changed`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.respond.check_in
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.feelings.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.feelings.resume.respond.check_in   [29 chars]
    en  Has anything changed for you?
    >>  ............................................
    pt  Mudou alguma coisa para você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `feelings.resume.check_in`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `feelings.resume.check_in`)_
- Does: arc `feelings` — advance to stage 2
- Then opens: `conversations.arc.feelings.resume.followup`
- …where the player's next choices will be: "I'm glad you told me." | "Is there more of it than that?" | "I should be honest about where I am." | "I'll let it sit."

```text
POOL   dialogue key: dialogue.conversations.feelings.resume.check_in
WHO    VILLAGER — what the player reads after pressing "Has anything changed for you?"
       spoken on: conversations.arc.feelings.resume.respond, button `check_in`
       leaves the player on: conversations.arc.feelings.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.resume.check_in.to.feelings`: the villager accepts. Subject `feelings`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.resume.check_in/1   [62 chars]
    en  Changed? No. If anything it's settled into something steadier.
    >>  ............................................
    pt  Mudou? Não. Se mudou, virou algo mais firme.
    >>  ............................................
  dialogue.conversations.feelings.resume.check_in/2   [61 chars]
    en  Not changed. Asked after, though — that's new, and I like it.
    >>  ............................................
    pt  Não mudou. Mas foi perguntado — isso é novo, e eu gosto.
    >>  ............................................
  dialogue.conversations.feelings.resume.check_in/3   [54 chars]
    en  It has, a little. For the better, since you're asking.
    >>  ............................................
    pt  Mudou, um pouco. Para melhor, já que você perguntou.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No, %1$s. If anything it's settled into something steadier, and that frightened me.
    >>  ............................................
    pt  Mudou? Não, %1$s. Se mudou, assentou em algo mais firme, e isso me assustou.
    >>  ............................................
  anxious.dialogue.conversations.feelings.resume.check_in/2
    en  The same. I'd half hoped it would have faded, and it hasn't, and I'm glad.
    >>  ............................................
    pt  Igual. Eu meio que esperava que tivesse passado, e não passou, e eu fico contente.
    >>  ............................................
  anxious.dialogue.conversations.feelings.resume.check_in/3
    en  No change. I checked, more than once, in case checking would change it.
    >>  ............................................
    pt  Sem mudança. Eu conferi, mais de uma vez, caso conferir mudasse.
    >>  ............................................
  athletic.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. Settled, rather. Things do, given a few seasons.
    >>  ............................................
    pt  Mudou? Não. Assentou, isso sim. As coisas assentam, com algumas estações.
    >>  ............................................
  athletic.dialogue.conversations.feelings.resume.check_in/2
    en  The same, and steadier for the time. That's how I'd want it.
    >>  ............................................
    pt  Igual, e mais firme com o tempo. É como eu queria.
    >>  ............................................
  athletic.dialogue.conversations.feelings.resume.check_in/3
    en  No change. It'll be the same next year and that's the point.
    >>  ............................................
    pt  Sem mudança. Vai ser igual ano que vem e é essa a questão.
    >>  ............................................
  confident.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. If anything it's settled into something steadier.
    >>  ............................................
    pt  Mudou? Não. Se mudou, assentou em algo mais firme.
    >>  ............................................
  confident.dialogue.conversations.feelings.resume.check_in/2
    en  Same as it was. Steadier, if I'm being exact.
    >>  ............................................
    pt  Igual. Mais firme, se for exato.
    >>  ............................................
  confident.dialogue.conversations.feelings.resume.check_in/3
    en  No change. That's the answer and I've checked it.
    >>  ............................................
    pt  Sem mudança. É a resposta e eu conferi.
    >>  ............................................
  crabby.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. If anything it's settled into something steadier.
    >>  ............................................
    pt  Mudou? Não. Se mudou, assentou em algo mais firme.
    >>  ............................................
  crabby.dialogue.conversations.feelings.resume.check_in/2
    en  Same as it was. Steadier, if I'm being exact.
    >>  ............................................
    pt  Igual. Mais firme, se for exato.
    >>  ............................................
  crabby.dialogue.conversations.feelings.resume.check_in/3
    en  No change. That's the answer and I've checked it.
    >>  ............................................
    pt  Sem mudança. É a resposta e eu conferi.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No, %1$s. If anything it's settled into something steadier.
    >>  ............................................
    pt  Mudou? Não, %1$s. Se mudou, assentou em algo mais firme.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.resume.check_in/2
    en  The same, and thank you for coming back to check rather than assuming.
    >>  ............................................
    pt  Igual, e obrigado por voltar pra conferir em vez de supor.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.resume.check_in/3
    en  No change. Ask me every few months, though. I'd like to be asked.
    >>  ............................................
    pt  Sem mudança. Mas me pergunte a cada uns meses. Eu gosto de ser perguntado.
    >>  ............................................
  flirty.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No, %1$s. If anything it's settled into something steadier.
    >>  ............................................
    pt  Mudou? Não, %1$s. Se mudou, assentou em algo mais firme.
    >>  ............................................
  flirty.dialogue.conversations.feelings.resume.check_in/2
    en  The same, and thank you for coming back to check rather than assuming.
    >>  ............................................
    pt  Igual, e obrigado por voltar pra conferir em vez de supor.
    >>  ............................................
  flirty.dialogue.conversations.feelings.resume.check_in/3
    en  No change. Ask me every few months, though. I'd like to be asked.
    >>  ............................................
    pt  Sem mudança. Mas me pergunte a cada uns meses. Eu gosto de ser perguntado.
    >>  ............................................
  friendly.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No, %1$s. If anything it's settled into something steadier.
    >>  ............................................
    pt  Mudou? Não, %1$s. Se mudou, assentou em algo mais firme.
    >>  ............................................
  friendly.dialogue.conversations.feelings.resume.check_in/2
    en  The same, and thank you for coming back to check rather than assuming.
    >>  ............................................
    pt  Igual, e obrigado por voltar pra conferir em vez de supor.
    >>  ............................................
  friendly.dialogue.conversations.feelings.resume.check_in/3
    en  No change. Ask me every few months, though. I'd like to be asked.
    >>  ............................................
    pt  Sem mudança. Mas me pergunte a cada uns meses. Eu gosto de ser perguntado.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No, %1$s. If anything it's settled into something steadier, and that frightened me.
    >>  ............................................
    pt  Mudou? Não, %1$s. Se mudou, assentou em algo mais firme, e isso me assustou.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.resume.check_in/2
    en  The same. I'd half hoped it would have faded, and it hasn't, and I'm glad.
    >>  ............................................
    pt  Igual. Eu meio que esperava que tivesse passado, e não passou, e eu fico contente.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.resume.check_in/3
    en  No change. I checked, more than once, in case checking would change it.
    >>  ............................................
    pt  Sem mudança. Eu conferi, mais de uma vez, caso conferir mudasse.
    >>  ............................................
  greedy.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. If anything it's settled into something steadier.
    >>  ............................................
    pt  Mudou? Não. Se mudou, assentou em algo mais firme.
    >>  ............................................
  greedy.dialogue.conversations.feelings.resume.check_in/2
    en  Same as it was. Steadier, if I'm being exact.
    >>  ............................................
    pt  Igual. Mais firme, se for exato.
    >>  ............................................
  greedy.dialogue.conversations.feelings.resume.check_in/3
    en  No change. That's the answer and I've checked it.
    >>  ............................................
    pt  Sem mudança. É a resposta e eu conferi.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. If anything it's settled into something steadier.
    >>  ............................................
    pt  Mudou? Não. Se mudou, assentou em algo mais firme.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.resume.check_in/2
    en  Same as it was. Steadier, if I'm being exact.
    >>  ............................................
    pt  Igual. Mais firme, se for exato.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.resume.check_in/3
    en  No change. That's the answer and I've checked it.
    >>  ............................................
    pt  Sem mudança. É a resposta e eu conferi.
    >>  ............................................
  introverted.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. Steadier, if anything.
    >>  ............................................
    pt  Mudou? Não. Mais firme, se tanto.
    >>  ............................................
  introverted.dialogue.conversations.feelings.resume.check_in/2
    en  The same.
    >>  ............................................
    pt  Igual.
    >>  ............................................
  introverted.dialogue.conversations.feelings.resume.check_in/3
    en  No change.
    >>  ............................................
    pt  Sem mudança.
    >>  ............................................
  lazy.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. Settled, rather. Things do, given a few seasons.
    >>  ............................................
    pt  Mudou? Não. Assentou, isso sim. As coisas assentam, com algumas estações.
    >>  ............................................
  lazy.dialogue.conversations.feelings.resume.check_in/2
    en  The same, and steadier for the time. That's how I'd want it.
    >>  ............................................
    pt  Igual, e mais firme com o tempo. É como eu queria.
    >>  ............................................
  lazy.dialogue.conversations.feelings.resume.check_in/3
    en  No change. It'll be the same next year and that's the point.
    >>  ............................................
    pt  Sem mudança. Vai ser igual ano que vem e é essa a questão.
    >>  ............................................
  odd.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. Steadier, if anything.
    >>  ............................................
    pt  Mudou? Não. Mais firme, se tanto.
    >>  ............................................
  odd.dialogue.conversations.feelings.resume.check_in/2
    en  The same.
    >>  ............................................
    pt  Igual.
    >>  ............................................
  odd.dialogue.conversations.feelings.resume.check_in/3
    en  No change.
    >>  ............................................
    pt  Sem mudança.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. Settled, rather. Things do, given a few seasons.
    >>  ............................................
    pt  Mudou? Não. Assentou, isso sim. As coisas assentam, com algumas estações.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.resume.check_in/2
    en  The same, and steadier for the time. That's how I'd want it.
    >>  ............................................
    pt  Igual, e mais firme com o tempo. É como eu queria.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.resume.check_in/3
    en  No change. It'll be the same next year and that's the point.
    >>  ............................................
    pt  Sem mudança. Vai ser igual ano que vem e é essa a questão.
    >>  ............................................
  peppy.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No! If anything it's settled into something steadier, which is a relief.
    >>  ............................................
    pt  Mudou? Não! Se mudou, assentou em algo mais firme, o que é um alívio.
    >>  ............................................
  peppy.dialogue.conversations.feelings.resume.check_in/2
    en  Same as it was. Duller and steadier. I'd not trade it.
    >>  ............................................
    pt  Igual. Mais sem graça e mais firme. Eu não trocaria.
    >>  ............................................
  peppy.dialogue.conversations.feelings.resume.check_in/3
    en  No change! And I'm delighted to be able to say that.
    >>  ............................................
    pt  Sem mudança! E estou encantado de poder dizer isso.
    >>  ............................................
  playful.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No! If anything it's settled into something steadier, which is a relief.
    >>  ............................................
    pt  Mudou? Não! Se mudou, assentou em algo mais firme, o que é um alívio.
    >>  ............................................
  playful.dialogue.conversations.feelings.resume.check_in/2
    en  Same as it was. Duller and steadier. I'd not trade it.
    >>  ............................................
    pt  Igual. Mais sem graça e mais firme. Eu não trocaria.
    >>  ............................................
  playful.dialogue.conversations.feelings.resume.check_in/3
    en  No change! And I'm delighted to be able to say that.
    >>  ............................................
    pt  Sem mudança! E estou encantado de poder dizer isso.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. Settled, rather. Things do, given a few seasons.
    >>  ............................................
    pt  Mudou? Não. Assentou, isso sim. As coisas assentam, com algumas estações.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.resume.check_in/2
    en  The same, and steadier for the time. That's how I'd want it.
    >>  ............................................
    pt  Igual, e mais firme com o tempo. É como eu queria.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.resume.check_in/3
    en  No change. It'll be the same next year and that's the point.
    >>  ............................................
    pt  Sem mudança. Vai ser igual ano que vem e é essa a questão.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No, %1$s. If anything it's settled into something steadier, and that frightened me.
    >>  ............................................
    pt  Mudou? Não, %1$s. Se mudou, assentou em algo mais firme, e isso me assustou.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.resume.check_in/2
    en  The same. I'd half hoped it would have faded, and it hasn't, and I'm glad.
    >>  ............................................
    pt  Igual. Eu meio que esperava que tivesse passado, e não passou, e eu fico contente.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.resume.check_in/3
    en  No change. I checked, more than once, in case checking would change it.
    >>  ............................................
    pt  Sem mudança. Eu conferi, mais de uma vez, caso conferir mudasse.
    >>  ............................................
  shy.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No. Steadier, if anything.
    >>  ............................................
    pt  Mudou? Não. Mais firme, se tanto.
    >>  ............................................
  shy.dialogue.conversations.feelings.resume.check_in/2
    en  The same.
    >>  ............................................
    pt  Igual.
    >>  ............................................
  shy.dialogue.conversations.feelings.resume.check_in/3
    en  No change.
    >>  ............................................
    pt  Sem mudança.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No! If anything it's settled into something steadier, which is a relief.
    >>  ............................................
    pt  Mudou? Não! Se mudou, assentou em algo mais firme, o que é um alívio.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.resume.check_in/2
    en  Same as it was. Duller and steadier. I'd not trade it.
    >>  ............................................
    pt  Igual. Mais sem graça e mais firme. Eu não trocaria.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.resume.check_in/3
    en  No change! And I'm delighted to be able to say that.
    >>  ............................................
    pt  Sem mudança! E estou encantado de poder dizer isso.
    >>  ............................................
  witty.dialogue.conversations.feelings.resume.check_in/1
    en  Changed? No! If anything it's settled into something steadier, which is a relief.
    >>  ............................................
    pt  Mudou? Não! Se mudou, assentou em algo mais firme, o que é um alívio.
    >>  ............................................
  witty.dialogue.conversations.feelings.resume.check_in/2
    en  Same as it was. Duller and steadier. I'd not trade it.
    >>  ............................................
    pt  Igual. Mais sem graça e mais firme. Eu não trocaria.
    >>  ............................................
  witty.dialogue.conversations.feelings.resume.check_in/3
    en  No change! And I'm delighted to be able to say that.
    >>  ............................................
    pt  Sem mudança! E estou encantado de poder dizer isso.
    >>  ............................................
```

</details>


### Button `reaffirm` — "I meant what I said."

*stance family `encouragement` · tone `plain` · answers the beat(s) `feelings.revisit.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.resume.reaffirm` — accepted phrasings: "i meant what i said"; "i still mean it"; "everything i said still stands"
  - the message must contain one of: `meant`
  - scored words: `meant`(1.5), `said`(0.5)

```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.respond.reaffirm
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.feelings.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.feelings.resume.respond.reaffirm   [20 chars]
    en  I meant what I said.
    >>  ............................................
    pt  Eu falei sério.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `feelings.resume.reaffirm`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +4, familiarity +2  _(recorded under topic `feelings.resume.reaffirm`)_
- Does: arc `feelings` — advance to stage 2
- Then opens: `conversations.arc.feelings.resume.followup`
- …where the player's next choices will be: "I'm glad you told me." | "Is there more of it than that?" | "I should be honest about where I am." | "I'll let it sit."

```text
POOL   dialogue key: dialogue.conversations.feelings.resume.reaffirm
WHO    VILLAGER — what the player reads after pressing "I meant what I said."
       spoken on: conversations.arc.feelings.resume.respond, button `reaffirm`
       leaves the player on: conversations.arc.feelings.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.resume.reaffirm.to.feelings`: the villager accepts. Subject `feelings`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.resume.reaffirm/1   [60 chars]
    en  ...You meant it. People say things once and hope you forget.
    >>  ............................................
    pt  ...Você falou sério. As pessoas dizem uma vez e torcem para você esquecer.
    >>  ............................................
  dialogue.conversations.feelings.resume.reaffirm/2   [57 chars]
    en  Said twice. That's how I know it was true the first time.
    >>  ............................................
    pt  Dito duas vezes. É assim que eu sei que era verdade da primeira vez.
    >>  ............................................
  dialogue.conversations.feelings.resume.reaffirm/3   [20 chars]
    en  Then so did I, %1$s.
    >>  ............................................
    pt  Então eu também, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it, %1$s. People say things once and hope you forget. I'd been forgetting on purpose.
    >>  ............................................
    pt  ...Você falou sério, %1$s. As pessoas dizem uma vez e torcem pra você esquecer. Eu esquecia de propósito.
    >>  ............................................
  anxious.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice. I'd decided the first time was the moment talking, so that it couldn't be taken back.
    >>  ............................................
    pt  Duas vezes. Eu tinha decidido que a primeira foi o momento falando, pra não poder ser retirado.
    >>  ............................................
  anxious.dialogue.conversations.feelings.resume.reaffirm/3
    en  Said again. Give me a moment. I'd built something rather careful around it not being said again.
    >>  ............................................
    pt  Dito de novo. Me dê um momento. Eu tinha construído algo cuidadoso em volta de não ser repetido.
    >>  ............................................
  athletic.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it. Twice, months apart. That's how a thing proves itself.
    >>  ............................................
    pt  Você falou sério. Duas vezes, com meses de diferença. É assim que algo se prova.
    >>  ............................................
  athletic.dialogue.conversations.feelings.resume.reaffirm/2
    en  Said again, and nothing about it has changed in between. That's the test.
    >>  ............................................
    pt  Dito de novo, e nada mudou no meio. É esse o teste.
    >>  ............................................
  athletic.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Some things need saying twice before they settle. This one has.
    >>  ............................................
    pt  Certo. Algumas coisas precisam ser ditas duas vezes pra assentar. Esta assentou.
    >>  ............................................
  confident.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it. People say things once and hope you forget.
    >>  ............................................
    pt  Você falou sério. As pessoas dizem uma vez e torcem pra você esquecer.
    >>  ............................................
  confident.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice, then. That's how I know it wasn't the moment talking.
    >>  ............................................
    pt  Duas vezes, então. É assim que eu sei que não foi o momento falando.
    >>  ............................................
  confident.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Said again. I'll believe it now.
    >>  ............................................
    pt  Certo. Dito de novo. Agora eu acredito.
    >>  ............................................
  crabby.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it. People say things once and hope you forget.
    >>  ............................................
    pt  Você falou sério. As pessoas dizem uma vez e torcem pra você esquecer.
    >>  ............................................
  crabby.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice, then. That's how I know it wasn't the moment talking.
    >>  ............................................
    pt  Duas vezes, então. É assim que eu sei que não foi o momento falando.
    >>  ............................................
  crabby.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Said again. I'll believe it now.
    >>  ............................................
    pt  Certo. Dito de novo. Agora eu acredito.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it, %1$s. People say things once and hope you forget.
    >>  ............................................
    pt  ...Você falou sério, %1$s. As pessoas dizem uma vez e torcem pra você esquecer.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice. I'd not have asked you to say it again and I'm glad you did.
    >>  ............................................
    pt  Duas vezes. Eu não teria pedido pra você repetir e fico contente que tenha.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.resume.reaffirm/3
    en  Said again, unprompted. That's the part I'll be thinking about tonight.
    >>  ............................................
    pt  Dito de novo, sem eu pedir. É a parte em que eu vou pensar hoje à noite.
    >>  ............................................
  flirty.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it, %1$s. People say things once and hope you forget.
    >>  ............................................
    pt  ...Você falou sério, %1$s. As pessoas dizem uma vez e torcem pra você esquecer.
    >>  ............................................
  flirty.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice. I'd not have asked you to say it again and I'm glad you did.
    >>  ............................................
    pt  Duas vezes. Eu não teria pedido pra você repetir e fico contente que tenha.
    >>  ............................................
  flirty.dialogue.conversations.feelings.resume.reaffirm/3
    en  Said again, unprompted. That's the part I'll be thinking about tonight.
    >>  ............................................
    pt  Dito de novo, sem eu pedir. É a parte em que eu vou pensar hoje à noite.
    >>  ............................................
  friendly.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it, %1$s. People say things once and hope you forget.
    >>  ............................................
    pt  ...Você falou sério, %1$s. As pessoas dizem uma vez e torcem pra você esquecer.
    >>  ............................................
  friendly.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice. I'd not have asked you to say it again and I'm glad you did.
    >>  ............................................
    pt  Duas vezes. Eu não teria pedido pra você repetir e fico contente que tenha.
    >>  ............................................
  friendly.dialogue.conversations.feelings.resume.reaffirm/3
    en  Said again, unprompted. That's the part I'll be thinking about tonight.
    >>  ............................................
    pt  Dito de novo, sem eu pedir. É a parte em que eu vou pensar hoje à noite.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it, %1$s. People say things once and hope you forget. I'd been forgetting on purpose.
    >>  ............................................
    pt  ...Você falou sério, %1$s. As pessoas dizem uma vez e torcem pra você esquecer. Eu esquecia de propósito.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice. I'd decided the first time was the moment talking, so that it couldn't be taken back.
    >>  ............................................
    pt  Duas vezes. Eu tinha decidido que a primeira foi o momento falando, pra não poder ser retirado.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.resume.reaffirm/3
    en  Said again. Give me a moment. I'd built something rather careful around it not being said again.
    >>  ............................................
    pt  Dito de novo. Me dê um momento. Eu tinha construído algo cuidadoso em volta de não ser repetido.
    >>  ............................................
  greedy.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it. People say things once and hope you forget.
    >>  ............................................
    pt  Você falou sério. As pessoas dizem uma vez e torcem pra você esquecer.
    >>  ............................................
  greedy.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice, then. That's how I know it wasn't the moment talking.
    >>  ............................................
    pt  Duas vezes, então. É assim que eu sei que não foi o momento falando.
    >>  ............................................
  greedy.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Said again. I'll believe it now.
    >>  ............................................
    pt  Certo. Dito de novo. Agora eu acredito.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it. People say things once and hope you forget.
    >>  ............................................
    pt  Você falou sério. As pessoas dizem uma vez e torcem pra você esquecer.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice, then. That's how I know it wasn't the moment talking.
    >>  ............................................
    pt  Duas vezes, então. É assim que eu sei que não foi o momento falando.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Said again. I'll believe it now.
    >>  ............................................
    pt  Certo. Dito de novo. Agora eu acredito.
    >>  ............................................
  introverted.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it.
    >>  ............................................
    pt  ...Você falou sério.
    >>  ............................................
  introverted.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice, then.
    >>  ............................................
    pt  Duas vezes, então.
    >>  ............................................
  introverted.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Said again.
    >>  ............................................
    pt  Certo. Dito de novo.
    >>  ............................................
  lazy.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it. Twice, months apart. That's how a thing proves itself.
    >>  ............................................
    pt  Você falou sério. Duas vezes, com meses de diferença. É assim que algo se prova.
    >>  ............................................
  lazy.dialogue.conversations.feelings.resume.reaffirm/2
    en  Said again, and nothing about it has changed in between. That's the test.
    >>  ............................................
    pt  Dito de novo, e nada mudou no meio. É esse o teste.
    >>  ............................................
  lazy.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Some things need saying twice before they settle. This one has.
    >>  ............................................
    pt  Certo. Algumas coisas precisam ser ditas duas vezes pra assentar. Esta assentou.
    >>  ............................................
  odd.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it.
    >>  ............................................
    pt  ...Você falou sério.
    >>  ............................................
  odd.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice, then.
    >>  ............................................
    pt  Duas vezes, então.
    >>  ............................................
  odd.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Said again.
    >>  ............................................
    pt  Certo. Dito de novo.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it. Twice, months apart. That's how a thing proves itself.
    >>  ............................................
    pt  Você falou sério. Duas vezes, com meses de diferença. É assim que algo se prova.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.resume.reaffirm/2
    en  Said again, and nothing about it has changed in between. That's the test.
    >>  ............................................
    pt  Dito de novo, e nada mudou no meio. É esse o teste.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Some things need saying twice before they settle. This one has.
    >>  ............................................
    pt  Certo. Algumas coisas precisam ser ditas duas vezes pra assentar. Esta assentou.
    >>  ............................................
  peppy.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it! People say things once and hope you forget. You said it twice.
    >>  ............................................
    pt  Você falou sério! As pessoas dizem uma vez e torcem pra você esquecer. Você disse duas.
    >>  ............................................
  peppy.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice! That's practically a contract around here.
    >>  ............................................
    pt  Duas vezes! Por aqui isso é praticamente um contrato.
    >>  ............................................
  peppy.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right — said again. I'm going to be unbearable about this.
    >>  ............................................
    pt  Certo — dito de novo. Vou ser insuportável sobre isso.
    >>  ............................................
  playful.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it! People say things once and hope you forget. You said it twice.
    >>  ............................................
    pt  Você falou sério! As pessoas dizem uma vez e torcem pra você esquecer. Você disse duas.
    >>  ............................................
  playful.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice! That's practically a contract around here.
    >>  ............................................
    pt  Duas vezes! Por aqui isso é praticamente um contrato.
    >>  ............................................
  playful.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right — said again. I'm going to be unbearable about this.
    >>  ............................................
    pt  Certo — dito de novo. Vou ser insuportável sobre isso.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it. Twice, months apart. That's how a thing proves itself.
    >>  ............................................
    pt  Você falou sério. Duas vezes, com meses de diferença. É assim que algo se prova.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.resume.reaffirm/2
    en  Said again, and nothing about it has changed in between. That's the test.
    >>  ............................................
    pt  Dito de novo, e nada mudou no meio. É esse o teste.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Some things need saying twice before they settle. This one has.
    >>  ............................................
    pt  Certo. Algumas coisas precisam ser ditas duas vezes pra assentar. Esta assentou.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it, %1$s. People say things once and hope you forget. I'd been forgetting on purpose.
    >>  ............................................
    pt  ...Você falou sério, %1$s. As pessoas dizem uma vez e torcem pra você esquecer. Eu esquecia de propósito.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice. I'd decided the first time was the moment talking, so that it couldn't be taken back.
    >>  ............................................
    pt  Duas vezes. Eu tinha decidido que a primeira foi o momento falando, pra não poder ser retirado.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.resume.reaffirm/3
    en  Said again. Give me a moment. I'd built something rather careful around it not being said again.
    >>  ............................................
    pt  Dito de novo. Me dê um momento. Eu tinha construído algo cuidadoso em volta de não ser repetido.
    >>  ............................................
  shy.dialogue.conversations.feelings.resume.reaffirm/1
    en  ...You meant it.
    >>  ............................................
    pt  ...Você falou sério.
    >>  ............................................
  shy.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice, then.
    >>  ............................................
    pt  Duas vezes, então.
    >>  ............................................
  shy.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right. Said again.
    >>  ............................................
    pt  Certo. Dito de novo.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it! People say things once and hope you forget. You said it twice.
    >>  ............................................
    pt  Você falou sério! As pessoas dizem uma vez e torcem pra você esquecer. Você disse duas.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice! That's practically a contract around here.
    >>  ............................................
    pt  Duas vezes! Por aqui isso é praticamente um contrato.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right — said again. I'm going to be unbearable about this.
    >>  ............................................
    pt  Certo — dito de novo. Vou ser insuportável sobre isso.
    >>  ............................................
  witty.dialogue.conversations.feelings.resume.reaffirm/1
    en  You meant it! People say things once and hope you forget. You said it twice.
    >>  ............................................
    pt  Você falou sério! As pessoas dizem uma vez e torcem pra você esquecer. Você disse duas.
    >>  ............................................
  witty.dialogue.conversations.feelings.resume.reaffirm/2
    en  Twice! That's practically a contract around here.
    >>  ............................................
    pt  Duas vezes! Por aqui isso é praticamente um contrato.
    >>  ............................................
  witty.dialogue.conversations.feelings.resume.reaffirm/3
    en  Right — said again. I'm going to be unbearable about this.
    >>  ............................................
    pt  Certo — dito de novo. Vou ser insuportável sobre isso.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let it be."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.revisit.to.feelings` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.feelings.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.feelings.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.feelings.resume.respond.leave   [15 chars]
    en  I'll let it be.
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
POOL   dialogue key: dialogue.conversations.feelings.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll let it be."
       spoken on: conversations.arc.feelings.resume.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.resume.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.resume.leave/1   [28 chars]
    en  Just so. Let it be, for now.
    >>  ............................................
    pt  Pois é. Deixa quieto, por enquanto.
    >>  ............................................
  dialogue.conversations.feelings.resume.leave/2   [14 chars]
    en  Right you are.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
  dialogue.conversations.feelings.resume.leave/3   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
```

---


## `conversations.feelings`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.first` — e.g. "You're really asking? Fine. You're the part of my day I don't complain about."


```text
POOL   dialogue key: dialogue.conversations.feelings
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.feelings
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.feelings   [32 chars]
    en  How do you really feel about me?
    >>  ............................................
    pt  O que você realmente sente por mim?
    >>  ............................................
```


### Button `same` — "I feel the same way."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `feelings.first.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.same` — accepted phrasings: "i feel the same"; "same way"; "feel the same"; "likewise"
  - the message must contain one of: `same`, `likewise`, `mutual`
  - scored words: `same`(1.5), `likewise`(1.2), `mutual`(1.0), `feel`(0.4)

```text
POOL   dialogue key: dialogue.conversations.feelings.same
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.feelings
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.feelings.same   [20 chars]
    en  I feel the same way.
    >>  ............................................
    pt  Sinto o mesmo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `5`

- Fires when: weighted +5 when the personality is `flirty`
- Fires when: weighted +4 when the personality is `sensitive`
- Fires when: weighted +4 when the mood is `happy`
- Does: **hearts (raw MCA `positive` field)** = 8
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.same.happy
WHO    VILLAGER — what the player reads after pressing "I feel the same way."
       spoken on: conversations.feelings, button `same`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.same.happy.terminal`: the villager accepts. Subject `feelings.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.same.happy/1   [69 chars]
    en  Then say it more often, %1$s. Words like that keep better than bread.
    >>  ............................................
    pt  Então diga isso mais vezes, %1$s. Palavras assim se conservam melhor que pão.
    >>  ............................................
  dialogue.conversations.feelings.same.happy/2   [75 chars]
    en  Well. Now I have to go tell the chickens. They gossip worse than the baker.
    >>  ............................................
    pt  Bom. Agora eu preciso ir contar pras galinhas. Elas fofocam pior que o padeiro.
    >>  ............................................
  dialogue.conversations.feelings.same.happy/3   [68 chars]
    en  Good. GOOD. I mean — good. Very casual about this. Extremely casual.
    >>  ............................................
    pt  Ótimo. ÓTIMO. Quer dizer — ótimo. Muito tranquilo quanto a isso. Extremamente tranquilo.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: weighted +5 when the personality is `introverted`
- Does: **hearts (raw MCA `positive` field)** = 6
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.same.shy
WHO    VILLAGER — what the player reads after pressing "I feel the same way."
       spoken on: conversations.feelings, button `same`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.same.shy.terminal`: the villager accepts. Subject `feelings.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.same.shy/1   [61 chars]
    en  Oh. I... need to go stand somewhere else and smile for a bit.
    >>  ............................................
    pt  Ah. Eu... preciso ir ficar em outro canto e sorrir um pouco.
    >>  ............................................
  dialogue.conversations.feelings.same.shy/2   [47 chars]
    en  I— yes. Same. I need to go count something now.
    >>  ............................................
    pt  Eu... sim. Igual. Preciso ir contar alguma coisa agora.
    >>  ............................................
  dialogue.conversations.feelings.same.shy/3   [69 chars]
    en  Oh no. Oh no, that's wonderful. Excuse me while my face catches fire.
    >>  ............................................
    pt  Ai não. Ai não, isso é maravilhoso. Com licença enquanto meu rosto pega fogo.
    >>  ............................................
```


### Button `unsure` — "I'm not sure how I feel yet."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.first.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.unsure` — accepted phrasings: "not sure"; "dont know"; "need time"; "cant say"
  - the message must contain one of: `unsure`, `maybe`, `confused`, `uncertain`
  - scored words: `unsure`(1.5), `maybe`(1.0), `confused`(0.8), `uncertain`(1.0)

```text
POOL   dialogue key: dialogue.conversations.feelings.unsure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.feelings
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.feelings.unsure   [28 chars]
    en  I'm not sure how I feel yet.
    >>  ............................................
    pt  Ainda não sei bem o que eu sinto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `5`

- Fires when: weighted +4 when the personality is `sensitive`
- Fires when: weighted +3 when the mood is `sad`
- Does: **hearts (raw MCA `negative` field)** = 2
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.unsure.hurt
WHO    VILLAGER — what the player reads after pressing "I'm not sure how I feel yet."
       spoken on: conversations.feelings, button `unsure`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.unsure.hurt.terminal`: the villager accepts. Subject `feelings.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.unsure.hurt/1   [50 chars]
    en  Right. Well. Thank you for the honesty, I suppose.
    >>  ............................................
    pt  Certo. Bom. Obrigado pela sinceridade, eu acho.
    >>  ............................................
  dialogue.conversations.feelings.unsure.hurt/2   [65 chars]
    en  Mm. That lands about as soft as an anvil, but I asked for honest.
    >>  ............................................
    pt  Mm. Isso cai tão macio quanto uma bigorna, mas eu pedi sinceridade.
    >>  ............................................
  dialogue.conversations.feelings.unsure.hurt/3   [69 chars]
    en  I see. Well. The turnips still need me, even if the answer's a maybe.
    >>  ............................................
    pt  Entendi. Bom. Os nabos ainda precisam de mim, mesmo que a resposta seja um talvez.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: weighted +5 when the personality is `confident`
- Does: **hearts (raw MCA `positive` field)** = 1
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.unsure.patient
WHO    VILLAGER — what the player reads after pressing "I'm not sure how I feel yet."
       spoken on: conversations.feelings, button `unsure`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.unsure.patient.terminal`: the villager accepts. Subject `feelings.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.unsure.patient/1   [52 chars]
    en  That's fair. I'm not going anywhere. Take your time.
    >>  ............................................
    pt  É justo. Não vou a lugar nenhum. Vá no seu tempo.
    >>  ............................................
  dialogue.conversations.feelings.unsure.patient/2   [68 chars]
    en  Hearts keep their own calendars. I can wait through a season or two.
    >>  ............................................
    pt  Corações têm calendário próprio. Eu consigo esperar uma estação ou duas.
    >>  ............................................
  dialogue.conversations.feelings.unsure.patient/3   [65 chars]
    en  Fair enough. I'm patient like winter — I always come back around.
    >>  ............................................
    pt  Justo. Sou paciente que nem inverno — sempre volto.
    >>  ............................................
```


### Button `back` — "Let's talk about something else."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.first.to.feelings` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.feelings.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.feelings
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.feelings.back   [32 chars]
    en  Let's talk about something else.
    >>  ............................................
    pt  Vamos falar de outra coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.

---


## `conversations.scene.feelings.a_flat_stretch.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.feelings.a_flat_stretch` — e.g. "Flat. Not sad, which people always want it to be, because sad has a shape and this does not."


```text
POOL   dialogue key: dialogue.conversations.scene.feelings.a_flat_stretch.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.feelings.a_flat_stretch.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.feelings.a_flat_stretch.respond   [12 chars]
    en  How you are.
    >>  ............................................
    pt  Como você está.
    >>  ............................................
```


### Button `just_hear_it` — "That sounds wearing."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `feelings.a_flat_stretch.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.feelings.a_flat_stretch.just_hear_it` — accepted phrasings: "that sounds wearing"; "that sounds wearing"; "eleven days of that is wearing"
  - the message must contain one of: `wearing`, `days`
  - scored words: `wearing`(1.8), `days`(1.8), `sounds`(0.8), `eleven`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.a_flat_stretch.respond.just_hear_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.feelings.a_flat_stretch.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.feelings.a_flat_stretch.respond.just_hear_it   [20 chars]
    en  That sounds wearing.
    >>  ............................................
    pt  Isso parece desgastante.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.feelings.heard`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +4  _(recorded under topic `feelings.low`)_
- Does: session `turn`
- Then opens: `conversations.scene.feelings.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.a_flat_stretch.steadied
WHO    VILLAGER — what the player reads after pressing "That sounds wearing."
       spoken on: conversations.scene.feelings.a_flat_stretch.respond, button `just_hear_it`
       leaves the player on: conversations.scene.feelings.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.a_flat_stretch.open.steadied`: the villager accepts. Subject `feelings.low`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:feelings` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.feelings.a_flat_stretch.steadied/1   [118 chars]
    en  It is, and you are the first person to call it wearing rather than ask what is wrong, and there is a large difference.
    >>  ............................................
    pt  É, e você é a primeira pessoa a chamar de desgastante em vez de perguntar o que há de errado, e a diferença é enorme.
    >>  ............................................
  dialogue.conversations.scene.feelings.a_flat_stretch.steadied/2   [88 chars]
    en  Thank you. I did not want a solution and I did want somebody to know the number of days.
    >>  ............................................
    pt  Obrigada. Eu não queria solução e queria que alguém soubesse o número de dias.
    >>  ............................................
  dialogue.conversations.scene.feelings.a_flat_stretch.steadied/3   [123 chars]
    en  That helps more than it should. I have said it four times this fortnight and this is the first time it has landed anywhere.
    >>  ............................................
    pt  Isso ajuda mais do que deveria. Já disse quatro vezes nestas duas semanas e é a primeira vez que aterrissa em algum lugar.
    >>  ............................................
```


### Button `ask_what_helps` — "What helps, when it's like this?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `feelings.a_flat_stretch.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.feelings.a_flat_stretch.ask_what_helps` — accepted phrasings: "what helps when its like this"; "what helps when it is like this"; "what makes it easier for you"
  - the message must contain one of: `helps`, `easier`
  - scored words: `helps`(1.8), `easier`(1.8), `when`(0.8), `its`(0.8), `like`(0.8), `makes`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.a_flat_stretch.respond.ask_what_helps
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.feelings.a_flat_stretch.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.feelings.a_flat_stretch.respond.ask_what_helps   [32 chars]
    en  What helps, when it's like this?
    >>  ............................................
    pt  O que ajuda quando está assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `feelings.low`)_
- Does: session `turn`
- Then opens: `conversations.scene.feelings.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.a_flat_stretch.answered
WHO    VILLAGER — what the player reads after pressing "What helps, when it's like this?"
       spoken on: conversations.scene.feelings.a_flat_stretch.respond, button `ask_what_helps`
       leaves the player on: conversations.scene.feelings.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.a_flat_stretch.open.answered`: the villager explains. Subject `feelings.low`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:feelings` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.feelings.a_flat_stretch.answered/1   [89 chars]
    en  Work, oddly. Not rest. Rest gives it room. Something with my hands and a clear end to it.
    >>  ............................................
    pt  Trabalho, curiosamente. Não descanso. O descanso dá espaço para isso. Alguma coisa com as mãos e com um fim claro.
    >>  ............................................
  dialogue.conversations.scene.feelings.a_flat_stretch.answered/2   [91 chars]
    en  Being outside before the light. It has never once failed and I forget it every single time.
    >>  ............................................
    pt  Ficar do lado de fora antes de clarear. Nunca falhou uma vez e eu esqueço todas as vezes.
    >>  ............................................
  dialogue.conversations.scene.feelings.a_flat_stretch.answered/3   [114 chars]
    en  Somebody talking about something else entirely. Ask me about your week and it will do more than asking about mine.
    >>  ............................................
    pt  Alguém falando de outra coisa completamente. Me pergunte sobre a sua semana e vai fazer mais do que perguntar sobre a minha.
    >>  ............................................
```


### Button `leave` — "Thanks for saying."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.a_flat_stretch.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.a_flat_stretch.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.feelings.a_flat_stretch.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.feelings.a_flat_stretch.respond.leave   [18 chars]
    en  Thanks for saying.
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
POOL   dialogue key: dialogue.conversations.scene.feelings.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for saying."
       spoken on: conversations.scene.feelings.a_flat_stretch.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.scene.leaving`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.feelings.followup / leave; conversations.scene.feelings.ordinary_answer.respond / leave
```

```text
  dialogue.conversations.scene.feelings.leaving/1   [27 chars]
    en  That is where I am, anyway.
    >>  ............................................
    pt  É onde eu estou, pelo menos.
    >>  ............................................
  dialogue.conversations.scene.feelings.leaving/2   [22 chars]
    en  Right. Enough of that.
    >>  ............................................
    pt  Certo. Chega disso.
    >>  ............................................
  dialogue.conversations.scene.feelings.leaving/3   [29 chars]
    en  It passes. Most of it passes.
    >>  ............................................
    pt  Passa. Quase tudo passa.
    >>  ............................................
```

---


## `conversations.scene.feelings.followup`

**Reached from 4 route(s):** `conversations.scene.feelings.a_flat_stretch.respond` / `just_hear_it`; `conversations.scene.feelings.a_flat_stretch.respond` / `ask_what_helps`; `conversations.scene.feelings.ordinary_answer.respond` / `ask_a_real_question`; `conversations.scene.feelings.ordinary_answer.respond` / `leave_it_polite`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.feelings.a_flat_stretch.answered` — e.g. "Work, oddly. Not rest. Rest gives it room. Something with my hands and a clear end to it."
- `conversations.scene.feelings.a_flat_stretch.steadied` — e.g. "It is, and you are the first person to call it wearing rather than ask what is wrong, and there is a large difference."
- `conversations.scene.feelings.ordinary_answer.acknowledged` — e.g. "Good. That is the whole of that exchange and there is nothing wrong with it."
- `conversations.scene.feelings.ordinary_answer.answered` — e.g. "One thing went right early and the whole day arranged itself around that, which is how it usually works."


```text
POOL   dialogue key: dialogue.conversations.scene.feelings.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.feelings.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.feelings.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `leave` — "We'll leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:feelings.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.feelings.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.feelings.followup.leave   [21 chars]
    en  We'll leave it there.
    >>  ............................................
    pt  Vamos deixar assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave it there."
       spoken on: conversations.scene.feelings.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.scene.leaving`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.feelings.a_flat_stretch.respond / leave; conversations.scene.feelings.ordinary_answer.respond / leave
```

> Written out in full under **`conversations.scene.feelings.a_flat_stretch.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.feelings.ordinary_answer.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.feelings.ordinary_answer` — e.g. "Middling, which is where I like to be. The interesting weeks have all been the bad ones."


```text
POOL   dialogue key: dialogue.conversations.scene.feelings.ordinary_answer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.feelings.ordinary_answer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.feelings.ordinary_answer.respond   [12 chars]
    en  How are you?
    >>  ............................................
    pt  Como você está?
    >>  ............................................
```


### Button `ask_a_real_question` — "What made today good?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `feelings.ordinary_answer.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.feelings.ordinary_answer.ask_a_real_question` — accepted phrasings: "what made today good"; "what made today good"; "what was good about today"
  - the message must contain one of: `today`, `good`
  - scored words: `today`(1.8), `good`(1.8), `made`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.ordinary_answer.respond.ask_a_real_question
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.feelings.ordinary_answer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.feelings.ordinary_answer.respond.ask_a_real_question   [21 chars]
    en  What made today good?
    >>  ............................................
    pt  O que fez hoje ser bom?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `feelings.steady`)_
- Does: session `turn`
- Then opens: `conversations.scene.feelings.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.ordinary_answer.answered
WHO    VILLAGER — what the player reads after pressing "What made today good?"
       spoken on: conversations.scene.feelings.ordinary_answer.respond, button `ask_a_real_question`
       leaves the player on: conversations.scene.feelings.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.ordinary_answer.open.answered`: the villager explains. Subject `feelings.steady`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:feelings` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.feelings.ordinary_answer.answered/1   [104 chars]
    en  One thing went right early and the whole day arranged itself around that, which is how it usually works.
    >>  ............................................
    pt  Uma coisa deu certo cedo e o dia inteiro se organizou em volta disso, que é como costuma funcionar.
    >>  ............................................
  dialogue.conversations.scene.feelings.ordinary_answer.answered/2   [112 chars]
    en  Nothing did. That is what good means most of the time and it took me years to stop expecting more from the word.
    >>  ............................................
    pt  Nada fez. É isso que bom significa na maioria das vezes, e levei anos para parar de esperar mais da palavra.
    >>  ............................................
  dialogue.conversations.scene.feelings.ordinary_answer.answered/3   [109 chars]
    en  Somebody asked me a real question instead of the polite one, which has now happened twice today counting you.
    >>  ............................................
    pt  Alguém me fez uma pergunta de verdade em vez da educada, o que já aconteceu duas vezes hoje contando você.
    >>  ............................................
```


### Button `leave_it_polite` — "Glad to hear it."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `feelings.ordinary_answer.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.feelings.ordinary_answer.leave_it_polite` — accepted phrasings: "glad to hear it"; "glad to hear it"; "good to hear that"
  - the message must contain one of: `glad`, `hear`
  - scored words: `glad`(1.8), `hear`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.ordinary_answer.respond.leave_it_polite
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.feelings.ordinary_answer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.feelings.ordinary_answer.respond.leave_it_polite   [16 chars]
    en  Glad to hear it.
    >>  ............................................
    pt  Fico feliz em saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1  _(recorded under topic `feelings.steady`)_
- Does: session `turn`
- Then opens: `conversations.scene.feelings.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.ordinary_answer.acknowledged
WHO    VILLAGER — what the player reads after pressing "Glad to hear it."
       spoken on: conversations.scene.feelings.ordinary_answer.respond, button `leave_it_polite`
       leaves the player on: conversations.scene.feelings.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.ordinary_answer.open.acknowledged`: the villager accepts. Subject `feelings.steady`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:feelings` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.feelings.ordinary_answer.acknowledged/1   [76 chars]
    en  Good. That is the whole of that exchange and there is nothing wrong with it.
    >>  ............................................
    pt  Ótimo. É a troca inteira e não há nada de errado com ela.
    >>  ............................................
  dialogue.conversations.scene.feelings.ordinary_answer.acknowledged/2   [96 chars]
    en  And you. I mean it as more than the formula, though I accept it sounds exactly like the formula.
    >>  ............................................
    pt  Você também. Digo com mais peso que a fórmula, embora eu aceite que soa exatamente como a fórmula.
    >>  ............................................
  dialogue.conversations.scene.feelings.ordinary_answer.acknowledged/3   [57 chars]
    en  Right. Off you go, then, and mind the puddle by the gate.
    >>  ............................................
    pt  Certo. Pode ir, então, e cuidado com a poça perto do portão.
    >>  ............................................
```


### Button `leave` — "Thanks for saying."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.ordinary_answer.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.ordinary_answer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.feelings.ordinary_answer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.feelings.ordinary_answer.respond.leave   [18 chars]
    en  Thanks for saying.
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
POOL   dialogue key: dialogue.conversations.scene.feelings.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for saying."
       spoken on: conversations.scene.feelings.ordinary_answer.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.scene.leaving`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.feelings.a_flat_stretch.respond / leave; conversations.scene.feelings.followup / leave
```

> Written out in full under **`conversations.scene.feelings.a_flat_stretch.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.feelings.again.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.again` — e.g. "I laid my heart on the table already. Give it a day to recover."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.again.respond   [18 chars]
    en  We were just here.
    >>  ............................................
    pt  A gente acabou de falar disso.
    >>  ............................................
```


### Button `apologize` — "Sorry — I've asked already."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.again.to.feelings.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.again.apologize` — accepted phrasings: "sorry i have asked already"; "i asked you that already"; "forgive me i have asked before"
  - the message must contain one of: `already`
  - scored words: `already`(1.5), `asked`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.again.respond.apologize   [27 chars]
    en  Sorry — I've asked already.
    >>  ............................................
    pt  Desculpa — já perguntei.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `feelings.again.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I've asked already."
       spoken on: conversations.topic.feelings.again.respond, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.again.apologize.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.again.apologize/1   [51 chars]
    en  It's alright. It's not a thing that changes hourly.
    >>  ............................................
    pt  Tudo bem. Não é uma coisa que muda de hora em hora.
    >>  ............................................
  dialogue.conversations.feelings.again.apologize/2   [14 chars]
    en  No harm, %1$s.
    >>  ............................................
    pt  Sem problema, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.again.apologize/3   [28 chars]
    en  Happens. Ask me another day.
    >>  ............................................
    pt  Acontece. Pergunte outro dia.
    >>  ............................................
```


### Button `press` — "Say it again anyway."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `feelings.again.to.feelings.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.again.press` — accepted phrasings: "say it again anyway"; "tell me again anyway"; "i would like to hear it again"
  - the message must contain one of: `again`
  - scored words: `again`(1.2), `hear`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.again.respond.press   [20 chars]
    en  Say it again anyway.
    >>  ............................................
    pt  Diga de novo mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `feelings.again.press`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `feelings.again.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.again.press
WHO    VILLAGER — what the player reads after pressing "Say it again anyway."
       spoken on: conversations.topic.feelings.again.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.again.press.terminal`: the villager resists. Subject `feelings.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.again.press/1   [49 chars]
    en  The same as an hour ago, and just as hard to say.
    >>  ............................................
    pt  O mesmo de uma hora atrás, e igualmente difícil de dizer.
    >>  ............................................
  dialogue.conversations.feelings.again.press/2   [35 chars]
    en  Twice? Once was quite enough, %1$s.
    >>  ............................................
    pt  Duas vezes? Uma já bastou, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.again.press/3   [30 chars]
    en  ...Fine. Same answer. Shorter.
    >>  ............................................
    pt  ...Tá. Mesma resposta. Mais curta.
    >>  ............................................
```


### Button `leave` — "Fair. Another day."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.again.to.feelings.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.again.respond.leave   [18 chars]
    en  Fair. Another day.
    >>  ............................................
    pt  Justo. Outro dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.feelings.again.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.again.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.again.leave/1   [17 chars]
    en  Aye. Another day.
    >>  ............................................
    pt  Tá. Outro dia.
    >>  ............................................
  dialogue.conversations.feelings.again.leave/2   [14 chars]
    en  Right you are.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
  dialogue.conversations.feelings.again.leave/3   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

---


## `conversations.topic.feelings.close`

**Reached from 7 route(s):** `conversations.arc.feelings.resume.followup` / `warm`; `conversations.arc.feelings.resume.followup` / `ask_more`; `conversations.arc.feelings.resume.followup` / `boundary`; `conversations.topic.feelings.platonic.followup` / `call_them_friend`; `conversations.topic.feelings.platonic.followup` / `be_honest`; `conversations.topic.feelings.romantic.followup` / `say_it_plainly`; `conversations.topic.feelings.romantic.followup` / `set_boundary`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.platonic.be_honest` — e.g. "And that's plenty. Honestly, %1$s, that's plenty."
- `conversations.feelings.platonic.call_them_friend` — e.g. "...A real one. That's a word people use loosely. Not you, I think."
- `conversations.feelings.resume.followup.ask_more` — e.g. "There's more. There's always more. But that's the part with words on it."
- `conversations.feelings.resume.followup.boundary` — e.g. "...Thank you for saying it straight. I'd rather know the shape of the ground I'm standing on."
- `conversations.feelings.resume.followup.warm` — e.g. "You're glad. ...I'd spent a week deciding whether telling you would cost me something."
- `conversations.feelings.romantic.say_it_plainly` — e.g. "...Plainly. After all this time you say it plainly. I love you too."
- `conversations.feelings.romantic.set_boundary` — e.g. "...Slower. Alright. I'd rather have you at your pace than not at all."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.close   [18 chars]
    en  Anyway. It's said.
    >>  ............................................
    pt  Enfim. Está dito.
    >>  ............................................
```


### Button `thank` — "Thank you for saying it."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.platonic.be_honest.to.feelings`, `feelings.platonic.call_them_friend.to.feelings`, `feelings.resume.followup.ask_more.to.feelings`, `feelings.resume.followup.boundary.to.feelings`, `feelings.resume.followup.warm.to.feelings`, `feelings.romantic.say_it_plainly.to.feelings`, `feelings.romantic.set_boundary.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.close.thank` — accepted phrasings: "thank you for saying it"; "thanks for saying that"; "i am glad you said it"
  - the message must contain one of: `saying`
  - scored words: `saying`(1.5), `thank`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.close.thank   [24 chars]
    en  Thank you for saying it.
    >>  ............................................
    pt  Obrigado por dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.close.thank`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +2, trust +1  _(recorded under topic `feelings.close.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.close.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for saying it."
       spoken on: conversations.topic.feelings.close, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.close.thank.terminal`: the villager accepts. Subject `feelings.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.close.thank/1   [50 chars]
    en  You don't have to thank me. ...But you're welcome.
    >>  ............................................
    pt  Você não precisa agradecer. ...Mas de nada.
    >>  ............................................
  dialogue.conversations.feelings.close.thank/2   [34 chars]
    en  Saying it was the hard part, %1$s.
    >>  ............................................
    pt  Dizer era a parte difícil, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.close.thank/3   [36 chars]
    en  True enough. Well. Now we both know.
    >>  ............................................
    pt  Bem verdade. Bom. Agora nós dois sabemos.
    >>  ............................................
```


### Button `say_means` — "That wasn't easy to say."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.platonic.be_honest.to.feelings`, `feelings.platonic.call_them_friend.to.feelings`, `feelings.resume.followup.ask_more.to.feelings`, `feelings.resume.followup.boundary.to.feelings`, `feelings.resume.followup.warm.to.feelings`, `feelings.romantic.say_it_plainly.to.feelings`, `feelings.romantic.set_boundary.to.feelings`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.close.say_means` — accepted phrasings: "that was not easy to say"; "i know that was hard to say"; "that was hard to say i know"
  - the message must contain one of: `easy`, `hard`
  - scored words: `easy`(1.2), `hard`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.close.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.close.say_means   [24 chars]
    en  That wasn't easy to say.
    >>  ............................................
    pt  Não foi fácil dizer isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.close.say_means`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `feelings.close.say_means`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.close.say_means
WHO    VILLAGER — what the player reads after pressing "That wasn't easy to say."
       spoken on: conversations.topic.feelings.close, button `say_means`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.close.say_means.terminal`: the villager accepts. Subject `feelings.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.close.say_means/1   [27 chars]
    en  It wasn't. I nearly didn't.
    >>  ............................................
    pt  Não foi. Eu quase não disse.
    >>  ............................................
  dialogue.conversations.feelings.close.say_means/2   [32 chars]
    en  You noticed that. Most wouldn't.
    >>  ............................................
    pt  Você notou. A maioria não notaria.
    >>  ............................................
  dialogue.conversations.feelings.close.say_means/3   [50 chars]
    en  No. But I'd have regretted the silence more, %1$s.
    >>  ............................................
    pt  Não. Mas eu me arrependeria mais do silêncio, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.platonic.be_honest.to.feelings`, `feelings.platonic.call_them_friend.to.feelings`, `feelings.resume.followup.ask_more.to.feelings`, `feelings.resume.followup.boundary.to.feelings`, `feelings.resume.followup.warm.to.feelings`, `feelings.romantic.say_it_plainly.to.feelings`, `feelings.romantic.set_boundary.to.feelings` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.close.leave   [16 chars]
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
POOL   dialogue key: dialogue.conversations.feelings.close.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.feelings.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.close.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.close.leave/1   [16 chars]
    en  So it is. Go on.
    >>  ............................................
    pt  É assim mesmo. Pode ir.
    >>  ............................................
  dialogue.conversations.feelings.close.leave/2   [24 chars]
    en  Right. Off you go, %1$s.
    >>  ............................................
    pt  Certo. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.close.leave/3   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Olhe por onde anda.
    >>  ............................................
```

---


## `conversations.topic.feelings.guarded.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.deflect.intimate` — e.g. "That question belongs to someone I trust with everything. We're not there."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.guarded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.guarded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.guarded.respond   [27 chars]
    en  Some things need more time.
    >>  ............................................
    pt  Algumas coisas precisam de mais tempo.
    >>  ............................................
```


### Button `respect` — "Fair. We're not there yet."

*stance family `restraint` · tone `plain` · answers the beat(s) `deflect.intimate.to.feelings.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.guarded.respect` — accepted phrasings: "fair we are not there yet"; "we are not there yet"; "that is fair enough for now"
  - the message must contain one of: `yet`
  - scored words: `there`(0.6), `yet`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.guarded.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.guarded.respond.respect   [26 chars]
    en  Fair. We're not there yet.
    >>  ............................................
    pt  Justo. Ainda não chegamos lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.guarded.respect`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `feelings.guarded.respect`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.guarded.respect
WHO    VILLAGER — what the player reads after pressing "Fair. We're not there yet."
       spoken on: conversations.topic.feelings.guarded.respond, button `respect`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.guarded.respect.terminal`: the villager deflects. Subject `feelings.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.guarded.respect/1   [49 chars]
    en  ...Thank you. Most people take a 'not yet' badly.
    >>  ............................................
    pt  ...Obrigado. A maioria leva mal um 'ainda não'.
    >>  ............................................
  dialogue.conversations.feelings.guarded.respect/2   [29 chars]
    en  Not yet. But not never, %1$s.
    >>  ............................................
    pt  Ainda não. Mas não nunca, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.guarded.respect/3   [39 chars]
    en  Good. These things want their own time.
    >>  ............................................
    pt  Bom. Essas coisas têm o seu tempo.
    >>  ............................................
```


### Button `ask_safer` — "Then something easier."

*stance family `curiosity` · tone `gentle` · answers the beat(s) `deflect.intimate.to.feelings.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.guarded.ask_safer` — accepted phrasings: "then something easier"; "let us talk about something easier"; "ask me something easier instead"
  - the message must contain one of: `easier`
  - scored words: `easier`(1.5), `something`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.guarded.respond.ask_safer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.guarded.respond.ask_safer   [22 chars]
    en  Then something easier.
    >>  ............................................
    pt  Então algo mais fácil.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `feelings.guarded.ask_safer`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.guarded.ask_safer
WHO    VILLAGER — what the player reads after pressing "Then something easier."
       spoken on: conversations.topic.feelings.guarded.respond, button `ask_safer`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.guarded.ask_safer.terminal`: the villager deflects. Subject `feelings.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.guarded.ask_safer/1   [32 chars]
    en  Now that I can answer. Ask away.
    >>  ............................................
    pt  Isso eu posso responder. Pergunte.
    >>  ............................................
  dialogue.conversations.feelings.guarded.ask_safer/2   [39 chars]
    en  Something easier. Aye — we'll build up.
    >>  ............................................
    pt  Algo mais fácil. É — a gente constrói aos poucos.
    >>  ............................................
  dialogue.conversations.feelings.guarded.ask_safer/3   [15 chars]
    en  Sensible, %1$s.
    >>  ............................................
    pt  Sensato, %1$s.
    >>  ............................................
```


### Button `press` — "You can tell me, though."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `deflect.intimate.to.feelings.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.guarded.press` — accepted phrasings: "you can tell me though"; "you know you can tell me"; "you could tell me if you wanted"
  - the message must contain one of: `though`
  - scored words: `tell`(0.5), `though`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.guarded.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.guarded.respond.press   [24 chars]
    en  You can tell me, though.
    >>  ............................................
    pt  Mas você pode me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `feelings.guarded.press`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — tension +5  _(recorded under topic `feelings.guarded.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.guarded.press
WHO    VILLAGER — what the player reads after pressing "You can tell me, though."
       spoken on: conversations.topic.feelings.guarded.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.guarded.press.terminal`: the villager resists. Subject `feelings.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.guarded.press/1   [51 chars]
    en  I could. I'd rather not, and that should be enough.
    >>  ............................................
    pt  Eu poderia. Prefiro não, e isso deveria bastar.
    >>  ............................................
  dialogue.conversations.feelings.guarded.press/2   [49 chars]
    en  Pressing on that won't make it true sooner, %1$s.
    >>  ............................................
    pt  Insistir nisso não vai tornar verdade mais cedo, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.guarded.press/3   [37 chars]
    en  No. Ask me when we've more behind us.
    >>  ............................................
    pt  Não. Me pergunte quando tivermos mais história.
    >>  ............................................
```


### Button `leave` — "Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `deflect.intimate.to.feelings.guarded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.guarded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.guarded.respond.leave   [13 chars]
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
POOL   dialogue key: dialogue.conversations.feelings.guarded.leave
WHO    VILLAGER — what the player reads after pressing "Another time."
       spoken on: conversations.topic.feelings.guarded.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.guarded.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.guarded.leave/1   [22 chars]
    en  Aye. No hard feelings.
    >>  ............................................
    pt  Tá. Sem ressentimento.
    >>  ............................................
  dialogue.conversations.feelings.guarded.leave/2   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.guarded.leave/3   [14 chars]
    en  Right you are.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
```

---


## `conversations.topic.feelings.hurt.close`

**Reached from 2 route(s):** `conversations.topic.feelings.platonic.followup` / `dismiss`; `conversations.topic.feelings.romantic.followup` / `deflect`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.platonic.dismiss` — e.g. "...I wasn't. You brought it up, %1$s."
- `conversations.feelings.romantic.deflect` — e.g. "...A whole thing. Right. Forget I brought it up."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.hurt.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.hurt.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.hurt.close   [26 chars]
    en  ...Forget I brought it up.
    >>  ............................................
    pt  ...Esqueça que eu toquei no assunto.
    >>  ............................................
```


### Button `apologize` — "No. Don't take it back. That was my fault."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `feelings.platonic.corrected`, `feelings.romantic.withdrawn` · offered only once the villager has actually said `player:brushed_it_off`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.hurt.apologize` — accepted phrasings: "no. don't take it back. that was my fault"
  - the message must contain one of: `fault`, `retract`
  - scored words: `fault`(1.5), `back`(0.8), `retract`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.hurt.close.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.hurt.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.hurt.close.apologize   [42 chars]
    en  No. Don't take it back. That was my fault.
    >>  ............................................
    pt  Não. Não retire. A culpa foi minha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4  _(recorded under topic `feelings.hurt.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.hurt.apologize
WHO    VILLAGER — what the player reads after pressing "No. Don't take it back. That was my fault."
       spoken on: conversations.topic.feelings.hurt.close, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.hurt.apologize`: the villager qualifys. Subject `feelings.declared`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.feelings.hurt.apologize/1   [69 chars]
    en  ...Your fault. Alright. Then it's still said, and I'll leave it said.
    >>  ............................................
    pt  ...Culpa sua. Está bem. Então continua dito, e eu deixo dito.
    >>  ............................................
  dialogue.conversations.feelings.hurt.apologize/2   [75 chars]
    en  Don't take it back, he says, after taking it badly. ...But thank you, %1$s.
    >>  ............................................
    pt  Não retire, ele diz, depois de receber mal. ...Mas obrigado, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.hurt.apologize/3   [50 chars]
    en  Then I'll not forget it. I'd only just got it out.
    >>  ............................................
    pt  Então eu não esqueço. Eu tinha acabado de conseguir dizer.
    >>  ............................................
```


### Button `soften` — "I heard you. I just didn't answer well."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `feelings.platonic.corrected`, `feelings.romantic.withdrawn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.hurt.soften` — accepted phrasings: "i heard you. i just didn't answer well"
  - the message must contain one of: `heard`, `answer`, `badly`
  - scored words: `heard`(1.5), `answer`(1.2), `badly`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.hurt.close.soften
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.hurt.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.hurt.close.soften   [39 chars]
    en  I heard you. I just didn't answer well.
    >>  ............................................
    pt  Eu te ouvi. Só não respondi bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.hurt.soften`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2, warmth +3  _(recorded under topic `feelings.hurt.soften`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.hurt.soften
WHO    VILLAGER — what the player reads after pressing "I heard you. I just didn't answer well."
       spoken on: conversations.topic.feelings.hurt.close, button `soften`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.hurt.soften`: the villager accepts. Subject `feelings.declared`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.feelings.hurt.soften/1   [53 chars]
    en  ...Heard is most of it. The answering can come later.
    >>  ............................................
    pt  ...Ouvir é a maior parte. A resposta pode vir depois.
    >>  ............................................
  dialogue.conversations.feelings.hurt.soften/2   [65 chars]
    en  That's a distinction I'll take, %1$s. Few would bother making it.
    >>  ............................................
    pt  É uma distinção que eu aceito, %1$s. Poucos se dariam ao trabalho.
    >>  ............................................
  dialogue.conversations.feelings.hurt.soften/3   [60 chars]
    en  Then we'll say it was badly answered and not badly received.
    >>  ............................................
    pt  Então dizemos que foi mal respondido e não mal recebido.
    >>  ............................................
```


### Button `leave` — "I'll let it be."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `feelings.platonic.corrected`, `feelings.romantic.withdrawn` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.hurt.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.hurt.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.hurt.close.leave   [15 chars]
    en  I'll let it be.
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
POOL   dialogue key: dialogue.conversations.feelings.hurt.leave
WHO    VILLAGER — what the player reads after pressing "I'll let it be."
       spoken on: conversations.topic.feelings.hurt.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.hurt.leave`: the villager accepts. Subject `feelings.declared`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.feelings.hurt.leave/1   [15 chars]
    en  ...Aye. Let it.
    >>  ............................................
    pt  ...É. Deixe.
    >>  ............................................
  dialogue.conversations.feelings.hurt.leave/2   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.hurt.leave/3   [30 chars]
    en  Right. It'll keep, apparently.
    >>  ............................................
    pt  Certo. Pelo visto pode esperar.
    >>  ............................................
```

---


## `conversations.topic.feelings.platonic.followup`

**Reached from 3 route(s):** `conversations.topic.feelings.platonic.respond` / `value_them`; `conversations.topic.feelings.platonic.respond` / `ask_theirs`; `conversations.topic.feelings.platonic.respond` / `keep_distance`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.platonic.ask_theirs` — e.g. "Fondly. Straightforwardly. You're one of the good ones."
- `conversations.feelings.platonic.keep_distance` — e.g. "Fair enough. Not everything needs a speech."
- `conversations.feelings.platonic.value_them` — e.g. "...Do I? People don't say that out loud round here."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.platonic.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.platonic.followup   [23 chars]
    en  That's the truth of it.
    >>  ............................................
    pt  É essa a verdade.
    >>  ............................................
```


### Button `call_them_friend` — "You're a friend. A real one."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.platonic.ask_theirs.to.feelings.platonic`, `feelings.platonic.keep_distance.to.feelings.platonic`, `feelings.platonic.value_them.to.feelings.platonic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.platonic.followup.call_them_friend` — accepted phrasings: "you are a friend a real one"; "you are a real friend"; "i would call you a true friend"
  - the message must contain one of: `friend`
  - scored words: `friend`(1.5), `real`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.followup.call_them_friend
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.platonic.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.platonic.followup.call_them_friend   [28 chars]
    en  You're a friend. A real one.
    >>  ............................................
    pt  Você é um amigo. De verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `feelings.platonic.call_them_friend`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5, trust +3  _(recorded under topic `feelings.platonic.call_them_friend`)_
- Does: arc `feelings` — advance to stage 1
- Then opens: `conversations.topic.feelings.close`
- …where the player's next choices will be: "Thank you for saying it." | "That wasn't easy to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.feelings.platonic.call_them_friend
WHO    VILLAGER — what the player reads after pressing "You're a friend. A real one."
       spoken on: conversations.topic.feelings.platonic.followup, button `call_them_friend`
       leaves the player on: conversations.topic.feelings.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.platonic.call_them_friend.to.feelings`: the villager accepts. Subject `feelings`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.platonic.call_them_friend/1   [66 chars]
    en  ...A real one. That's a word people use loosely. Not you, I think.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa. Você não, eu acho.
    >>  ............................................
  dialogue.conversations.feelings.platonic.call_them_friend/2   [40 chars]
    en  Friend. Aye. I'll take that and keep it.
    >>  ............................................
    pt  Amigo. É. Vou aceitar e guardar.
    >>  ............................................
  dialogue.conversations.feelings.platonic.call_them_friend/3   [46 chars]
    en  Then that's what we are. Good. That's settled.
    >>  ............................................
    pt  Então é isso que somos. Bom. Está resolvido.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely, %1$s. I'd assumed you were being kind.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa, %1$s. Achei que você era gentil.
    >>  ............................................
  anxious.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. I've been careful not to use that word about you in case I was wrong.
    >>  ............................................
    pt  Amigo. Eu tomava cuidado pra não usar essa palavra sobre você, caso eu estivesse errado.
    >>  ............................................
  anxious.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it out loud. Give me a moment — I'd not planned for that.
    >>  ............................................
    pt  Você disse em voz alta. Me dê um momento — eu não tinha planejado isso.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one. Words like that mean more when they're used slowly.
    >>  ............................................
    pt  De verdade. Palavras assim valem mais quando usadas devagar.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right. It'll still be true in ten years, which is what the word is for.
    >>  ............................................
    pt  Amigo. Certo. Vai continuar verdade em dez anos, e é pra isso que serve a palavra.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly, and plainly is how these things last.
    >>  ............................................
    pt  Você disse sem rodeio, e é sem rodeio que essas coisas duram.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one. That's a word people use loosely. Not you, I think.
    >>  ............................................
    pt  De verdade. É uma palavra que as pessoas usam à toa. Você não, eu acho.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right. I'll hold you to the word.
    >>  ............................................
    pt  Amigo. Certo. Vou te cobrar a palavra.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. I'll take it plainly.
    >>  ............................................
    pt  Você disse sem rodeio. Eu aceito sem rodeio.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one. That's a word people use loosely. Not you, I think.
    >>  ............................................
    pt  De verdade. É uma palavra que as pessoas usam à toa. Você não, eu acho.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right. I'll hold you to the word.
    >>  ............................................
    pt  Amigo. Certo. Vou te cobrar a palavra.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. I'll take it plainly.
    >>  ............................................
    pt  Você disse sem rodeio. Eu aceito sem rodeio.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely, %1$s. Not you, I think.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa, %1$s. Você não, eu acho.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. I'd hoped so and I'd not have been the one to say it first.
    >>  ............................................
    pt  Amigo. Eu esperava, e não seria eu a dizer primeiro.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it out loud. That's the whole difference between thinking it and having it.
    >>  ............................................
    pt  Você disse em voz alta. É toda a diferença entre pensar e ter.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely, %1$s. Not you, I think.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa, %1$s. Você não, eu acho.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. I'd hoped so and I'd not have been the one to say it first.
    >>  ............................................
    pt  Amigo. Eu esperava, e não seria eu a dizer primeiro.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it out loud. That's the whole difference between thinking it and having it.
    >>  ............................................
    pt  Você disse em voz alta. É toda a diferença entre pensar e ter.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely, %1$s. Not you, I think.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa, %1$s. Você não, eu acho.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. I'd hoped so and I'd not have been the one to say it first.
    >>  ............................................
    pt  Amigo. Eu esperava, e não seria eu a dizer primeiro.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it out loud. That's the whole difference between thinking it and having it.
    >>  ............................................
    pt  Você disse em voz alta. É toda a diferença entre pensar e ter.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely, %1$s. I'd assumed you were being kind.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa, %1$s. Achei que você era gentil.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. I've been careful not to use that word about you in case I was wrong.
    >>  ............................................
    pt  Amigo. Eu tomava cuidado pra não usar essa palavra sobre você, caso eu estivesse errado.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it out loud. Give me a moment — I'd not planned for that.
    >>  ............................................
    pt  Você disse em voz alta. Me dê um momento — eu não tinha planejado isso.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one. That's a word people use loosely. Not you, I think.
    >>  ............................................
    pt  De verdade. É uma palavra que as pessoas usam à toa. Você não, eu acho.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right. I'll hold you to the word.
    >>  ............................................
    pt  Amigo. Certo. Vou te cobrar a palavra.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. I'll take it plainly.
    >>  ............................................
    pt  Você disse sem rodeio. Eu aceito sem rodeio.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one. That's a word people use loosely. Not you, I think.
    >>  ............................................
    pt  De verdade. É uma palavra que as pessoas usam à toa. Você não, eu acho.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right. I'll hold you to the word.
    >>  ............................................
    pt  Amigo. Certo. Vou te cobrar a palavra.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. I'll take it plainly.
    >>  ............................................
    pt  Você disse sem rodeio. Eu aceito sem rodeio.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right.
    >>  ............................................
    pt  Amigo. Certo.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. Good.
    >>  ............................................
    pt  Você disse sem rodeio. Bom.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one. Words like that mean more when they're used slowly.
    >>  ............................................
    pt  De verdade. Palavras assim valem mais quando usadas devagar.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right. It'll still be true in ten years, which is what the word is for.
    >>  ............................................
    pt  Amigo. Certo. Vai continuar verdade em dez anos, e é pra isso que serve a palavra.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly, and plainly is how these things last.
    >>  ............................................
    pt  Você disse sem rodeio, e é sem rodeio que essas coisas duram.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right.
    >>  ............................................
    pt  Amigo. Certo.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. Good.
    >>  ............................................
    pt  Você disse sem rodeio. Bom.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one. Words like that mean more when they're used slowly.
    >>  ............................................
    pt  De verdade. Palavras assim valem mais quando usadas devagar.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right. It'll still be true in ten years, which is what the word is for.
    >>  ............................................
    pt  Amigo. Certo. Vai continuar verdade em dez anos, e é pra isso que serve a palavra.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly, and plainly is how these things last.
    >>  ............................................
    pt  Você disse sem rodeio, e é sem rodeio que essas coisas duram.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one! That's a word people use loosely. You don't, which is why it landed.
    >>  ............................................
    pt  De verdade! É uma palavra que as pessoas usam à toa. Você não, e por isso pegou.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend! Right. I'm going to be quietly pleased about this for days.
    >>  ............................................
    pt  Amigo! Certo. Vou ficar secretamente contente com isso por dias.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. Nobody says it plainly. I've checked.
    >>  ............................................
    pt  Você disse sem rodeio. Ninguém diz sem rodeio. Eu conferi.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one! That's a word people use loosely. You don't, which is why it landed.
    >>  ............................................
    pt  De verdade! É uma palavra que as pessoas usam à toa. Você não, e por isso pegou.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend! Right. I'm going to be quietly pleased about this for days.
    >>  ............................................
    pt  Amigo! Certo. Vou ficar secretamente contente com isso por dias.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. Nobody says it plainly. I've checked.
    >>  ............................................
    pt  Você disse sem rodeio. Ninguém diz sem rodeio. Eu conferi.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one. Words like that mean more when they're used slowly.
    >>  ............................................
    pt  De verdade. Palavras assim valem mais quando usadas devagar.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right. It'll still be true in ten years, which is what the word is for.
    >>  ............................................
    pt  Amigo. Certo. Vai continuar verdade em dez anos, e é pra isso que serve a palavra.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly, and plainly is how these things last.
    >>  ............................................
    pt  Você disse sem rodeio, e é sem rodeio que essas coisas duram.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely, %1$s. I'd assumed you were being kind.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa, %1$s. Achei que você era gentil.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. I've been careful not to use that word about you in case I was wrong.
    >>  ............................................
    pt  Amigo. Eu tomava cuidado pra não usar essa palavra sobre você, caso eu estivesse errado.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it out loud. Give me a moment — I'd not planned for that.
    >>  ............................................
    pt  Você disse em voz alta. Me dê um momento — eu não tinha planejado isso.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  ...A real one. That's a word people use loosely.
    >>  ............................................
    pt  ...De verdade. É uma palavra que as pessoas usam à toa.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend. Right.
    >>  ............................................
    pt  Amigo. Certo.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. Good.
    >>  ............................................
    pt  Você disse sem rodeio. Bom.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one! That's a word people use loosely. You don't, which is why it landed.
    >>  ............................................
    pt  De verdade! É uma palavra que as pessoas usam à toa. Você não, e por isso pegou.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend! Right. I'm going to be quietly pleased about this for days.
    >>  ............................................
    pt  Amigo! Certo. Vou ficar secretamente contente com isso por dias.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. Nobody says it plainly. I've checked.
    >>  ............................................
    pt  Você disse sem rodeio. Ninguém diz sem rodeio. Eu conferi.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.call_them_friend/1
    en  A real one! That's a word people use loosely. You don't, which is why it landed.
    >>  ............................................
    pt  De verdade! É uma palavra que as pessoas usam à toa. Você não, e por isso pegou.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.call_them_friend/2
    en  Friend! Right. I'm going to be quietly pleased about this for days.
    >>  ............................................
    pt  Amigo! Certo. Vou ficar secretamente contente com isso por dias.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.call_them_friend/3
    en  You said it plainly. Nobody says it plainly. I've checked.
    >>  ............................................
    pt  Você disse sem rodeio. Ninguém diz sem rodeio. Eu conferi.
    >>  ............................................
```

</details>


### Button `be_honest` — "I'm fond of you, and that's all."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.platonic.ask_theirs.to.feelings.platonic`, `feelings.platonic.keep_distance.to.feelings.platonic`, `feelings.platonic.value_them.to.feelings.platonic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.platonic.followup.be_honest` — accepted phrasings: "i am fond of you and that is all"; "i am fond of you nothing more"; "it is fondness and nothing else"
  - the message must contain one of: `fond`
  - scored words: `all`(0.3), `fond`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.followup.be_honest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.platonic.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.platonic.followup.be_honest   [32 chars]
    en  I'm fond of you, and that's all.
    >>  ............................................
    pt  Gosto de você, e é só isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.platonic.be_honest`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — respect +5  _(recorded under topic `feelings.platonic.be_honest`)_
- Does: arc `feelings` — advance to stage 1
- Then opens: `conversations.topic.feelings.close`
- …where the player's next choices will be: "Thank you for saying it." | "That wasn't easy to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.feelings.platonic.be_honest
WHO    VILLAGER — what the player reads after pressing "I'm fond of you, and that's all."
       spoken on: conversations.topic.feelings.platonic.followup, button `be_honest`
       leaves the player on: conversations.topic.feelings.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.platonic.be_honest.to.feelings`: the villager accepts. Subject `feelings`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.platonic.be_honest/1   [49 chars]
    en  And that's plenty. Honestly, %1$s, that's plenty.
    >>  ............................................
    pt  E já é muito. Sinceramente, %1$s, já é muito.
    >>  ............................................
  dialogue.conversations.feelings.platonic.be_honest/2   [58 chars]
    en  Fond, and no more. Good — I'd rather know the shape of it.
    >>  ............................................
    pt  Carinho, e nada além. Bom — prefiro saber o formato disso.
    >>  ............................................
  dialogue.conversations.feelings.platonic.be_honest/3   [41 chars]
    en  Clear is kind. Thank you for being clear.
    >>  ............................................
    pt  Claro é gentil. Obrigado por ser claro.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Honestly, %1$s, that's plenty.
    >>  ............................................
    pt  E isso basta. Sinceramente, %1$s, basta.
    >>  ............................................
  anxious.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd braced for less and I'd have taken less.
    >>  ............................................
    pt  Basta. Eu esperava menos e teria aceitado menos.
    >>  ............................................
  anxious.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. I'd rather be told the true size of a thing than a kind one.
    >>  ............................................
    pt  É o bastante. Prefiro saber o tamanho verdadeiro a um tamanho gentil.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Plenty wears better than a great deal, in my experience.
    >>  ............................................
    pt  E isso basta. Bastar dura mais que muito, na minha experiência.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. It'll still be plenty next year, which is the point of it.
    >>  ............................................
    pt  Basta. Vai continuar bastando ano que vem, e é essa a questão.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. Enough is a good place to stop.
    >>  ............................................
    pt  É o bastante. Bastante é um bom lugar pra parar.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Honestly, that's plenty.
    >>  ............................................
    pt  E isso basta. Sinceramente, basta.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd not ask for more than what's true.
    >>  ............................................
    pt  Basta. Eu não pediria mais do que é verdade.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. It's more than most of what I'm offered.
    >>  ............................................
    pt  É o bastante. É mais do que quase tudo que me oferecem.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Honestly, that's plenty.
    >>  ............................................
    pt  E isso basta. Sinceramente, basta.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd not ask for more than what's true.
    >>  ............................................
    pt  Basta. Eu não pediria mais do que é verdade.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. It's more than most of what I'm offered.
    >>  ............................................
    pt  É o bastante. É mais do que quase tudo que me oferecem.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty, %1$s. Honestly, that's plenty.
    >>  ............................................
    pt  E isso basta, %1$s. Sinceramente, basta.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd rather have the true amount from you than a larger one from anyone.
    >>  ............................................
    pt  Basta. Prefiro a quantidade verdadeira de você a uma maior de qualquer um.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. And you said it to my face, which is the part that counts.
    >>  ............................................
    pt  É o bastante. E você disse na minha cara, que é a parte que conta.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty, %1$s. Honestly, that's plenty.
    >>  ............................................
    pt  E isso basta, %1$s. Sinceramente, basta.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd rather have the true amount from you than a larger one from anyone.
    >>  ............................................
    pt  Basta. Prefiro a quantidade verdadeira de você a uma maior de qualquer um.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. And you said it to my face, which is the part that counts.
    >>  ............................................
    pt  É o bastante. E você disse na minha cara, que é a parte que conta.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty, %1$s. Honestly, that's plenty.
    >>  ............................................
    pt  E isso basta, %1$s. Sinceramente, basta.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd rather have the true amount from you than a larger one from anyone.
    >>  ............................................
    pt  Basta. Prefiro a quantidade verdadeira de você a uma maior de qualquer um.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. And you said it to my face, which is the part that counts.
    >>  ............................................
    pt  É o bastante. E você disse na minha cara, que é a parte que conta.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Honestly, %1$s, that's plenty.
    >>  ............................................
    pt  E isso basta. Sinceramente, %1$s, basta.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd braced for less and I'd have taken less.
    >>  ............................................
    pt  Basta. Eu esperava menos e teria aceitado menos.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. I'd rather be told the true size of a thing than a kind one.
    >>  ............................................
    pt  É o bastante. Prefiro saber o tamanho verdadeiro a um tamanho gentil.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Honestly, that's plenty.
    >>  ............................................
    pt  E isso basta. Sinceramente, basta.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd not ask for more than what's true.
    >>  ............................................
    pt  Basta. Eu não pediria mais do que é verdade.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. It's more than most of what I'm offered.
    >>  ............................................
    pt  É o bastante. É mais do que quase tudo que me oferecem.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Honestly, that's plenty.
    >>  ............................................
    pt  E isso basta. Sinceramente, basta.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd not ask for more than what's true.
    >>  ............................................
    pt  Basta. Eu não pediria mais do que é verdade.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. It's more than most of what I'm offered.
    >>  ............................................
    pt  É o bastante. É mais do que quase tudo que me oferecem.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty.
    >>  ............................................
    pt  E isso basta.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. Honestly.
    >>  ............................................
    pt  Basta. Sinceramente.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough.
    >>  ............................................
    pt  É o bastante.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Plenty wears better than a great deal, in my experience.
    >>  ............................................
    pt  E isso basta. Bastar dura mais que muito, na minha experiência.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. It'll still be plenty next year, which is the point of it.
    >>  ............................................
    pt  Basta. Vai continuar bastando ano que vem, e é essa a questão.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. Enough is a good place to stop.
    >>  ............................................
    pt  É o bastante. Bastante é um bom lugar pra parar.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty.
    >>  ............................................
    pt  E isso basta.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. Honestly.
    >>  ............................................
    pt  Basta. Sinceramente.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough.
    >>  ............................................
    pt  É o bastante.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Plenty wears better than a great deal, in my experience.
    >>  ............................................
    pt  E isso basta. Bastar dura mais que muito, na minha experiência.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. It'll still be plenty next year, which is the point of it.
    >>  ............................................
    pt  Basta. Vai continuar bastando ano que vem, e é essa a questão.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. Enough is a good place to stop.
    >>  ............................................
    pt  É o bastante. Bastante é um bom lugar pra parar.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty! Honestly, that's plenty and then some.
    >>  ............................................
    pt  E isso basta! Sinceramente, basta e sobra.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. More than plenty. I'd have settled for considerably less.
    >>  ............................................
    pt  Basta. Mais que basta. Eu teria me contentado com bem menos.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough! I'll take honest over flattering any day of the week.
    >>  ............................................
    pt  É o bastante! Prefiro honesto a lisonjeiro qualquer dia.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty! Honestly, that's plenty and then some.
    >>  ............................................
    pt  E isso basta! Sinceramente, basta e sobra.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. More than plenty. I'd have settled for considerably less.
    >>  ............................................
    pt  Basta. Mais que basta. Eu teria me contentado com bem menos.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough! I'll take honest over flattering any day of the week.
    >>  ............................................
    pt  É o bastante! Prefiro honesto a lisonjeiro qualquer dia.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Plenty wears better than a great deal, in my experience.
    >>  ............................................
    pt  E isso basta. Bastar dura mais que muito, na minha experiência.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. It'll still be plenty next year, which is the point of it.
    >>  ............................................
    pt  Basta. Vai continuar bastando ano que vem, e é essa a questão.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. Enough is a good place to stop.
    >>  ............................................
    pt  É o bastante. Bastante é um bom lugar pra parar.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty. Honestly, %1$s, that's plenty.
    >>  ............................................
    pt  E isso basta. Sinceramente, %1$s, basta.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. I'd braced for less and I'd have taken less.
    >>  ............................................
    pt  Basta. Eu esperava menos e teria aceitado menos.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough. I'd rather be told the true size of a thing than a kind one.
    >>  ............................................
    pt  É o bastante. Prefiro saber o tamanho verdadeiro a um tamanho gentil.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty.
    >>  ............................................
    pt  E isso basta.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. Honestly.
    >>  ............................................
    pt  Basta. Sinceramente.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough.
    >>  ............................................
    pt  É o bastante.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty! Honestly, that's plenty and then some.
    >>  ............................................
    pt  E isso basta! Sinceramente, basta e sobra.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. More than plenty. I'd have settled for considerably less.
    >>  ............................................
    pt  Basta. Mais que basta. Eu teria me contentado com bem menos.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough! I'll take honest over flattering any day of the week.
    >>  ............................................
    pt  É o bastante! Prefiro honesto a lisonjeiro qualquer dia.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.be_honest/1
    en  And that's plenty! Honestly, that's plenty and then some.
    >>  ............................................
    pt  E isso basta! Sinceramente, basta e sobra.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.be_honest/2
    en  Plenty. More than plenty. I'd have settled for considerably less.
    >>  ............................................
    pt  Basta. Mais que basta. Eu teria me contentado com bem menos.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.be_honest/3
    en  That's enough! I'll take honest over flattering any day of the week.
    >>  ............................................
    pt  É o bastante! Prefiro honesto a lisonjeiro qualquer dia.
    >>  ............................................
```

</details>


### Button `dismiss` — "Don't read too much into it."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `feelings.platonic.ask_theirs.to.feelings.platonic`, `feelings.platonic.keep_distance.to.feelings.platonic`, `feelings.platonic.value_them.to.feelings.platonic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.platonic.followup.dismiss` — accepted phrasings: "do not read too much into it"; "do not make more of it than it is"; "there is nothing to read into it"
  - the message must contain one of: `read`
  - scored words: `much`(0.5), `read`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.followup.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.platonic.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.platonic.followup.dismiss   [28 chars]
    en  Don't read too much into it.
    >>  ............................................
    pt  Não leia muita coisa nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `feelings.platonic.dismiss`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -5, tension +5  _(recorded under topic `feelings.platonic.dismiss`)_
- Does: session `turn`
- Then opens: `conversations.topic.feelings.hurt.close`
- …where the player's next choices will be: "No. Don't take it back. That was my fault." | "I heard you. I just didn't answer well." | "I'll let it be."

```text
POOL   dialogue key: dialogue.conversations.feelings.platonic.dismiss
WHO    VILLAGER — what the player reads after pressing "Don't read too much into it."
       spoken on: conversations.topic.feelings.platonic.followup, button `dismiss`
       leaves the player on: conversations.topic.feelings.hurt.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.platonic.corrected`: the villager hurts. Subject `feelings.declared`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `feelings:declared`, `player:brushed_it_off` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.feelings.platonic.dismiss/1   [37 chars]
    en  ...I wasn't. You brought it up, %1$s.
    >>  ............................................
    pt  ...Eu não estava. Você que puxou o assunto, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.platonic.dismiss/2   [62 chars]
    en  Read too much into it. Right. I'll read nothing into anything.
    >>  ............................................
    pt  Ler demais nisso. Certo. Não vou ler nada em nada.
    >>  ............................................
  dialogue.conversations.feelings.platonic.dismiss/3   [37 chars]
    en  Noted. I'll keep my regard to myself.
    >>  ............................................
    pt  Anotado. Vou guardar minha consideração para mim.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. Now I'm embarrassed and I hadn't been, %1$s.
    >>  ............................................
    pt  ...Eu não estava. Agora eu estou constrangido e não estava, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what I meant and I don't know how to say it better.
    >>  ............................................
    pt  Não é o que eu quis dizer e eu não sei como dizer melhor.
    >>  ............................................
  anxious.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. I'll say less next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu falo menos.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. No harm done either way.
    >>  ............................................
    pt  ...Eu não estava. Sem mal nenhum de todo jeito.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what it was. It'll sort itself out.
    >>  ............................................
    pt  Não era isso. Vai se resolver.
    >>  ............................................
  athletic.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. We'll leave that where it fell.
    >>  ............................................
    pt  ...Certo. Vamos deixar onde caiu.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. You brought it up.
    >>  ............................................
    pt  ...Eu não estava. Você que levantou.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what I said and you know it isn't.
    >>  ............................................
    pt  Não foi o que eu disse e você sabe que não foi.
    >>  ............................................
  confident.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. I'll be clearer next time, since clarity is wanted.
    >>  ............................................
    pt  ...Certo. Da próxima eu sou mais claro, já que querem clareza.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. You brought it up.
    >>  ............................................
    pt  ...Eu não estava. Você que levantou.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what I said and you know it isn't.
    >>  ............................................
    pt  Não foi o que eu disse e você sabe que não foi.
    >>  ............................................
  crabby.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. I'll be clearer next time, since clarity is wanted.
    >>  ............................................
    pt  ...Certo. Da próxima eu sou mais claro, já que querem clareza.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't, %1$s. I'd have said if I were.
    >>  ............................................
    pt  ...Eu não estava, %1$s. Eu teria dito se estivesse.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not it at all, and I'd hate you to go away thinking it was.
    >>  ............................................
    pt  Não é nada disso, e eu odiaria você ir embora achando que era.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. Let's put that down and start again.
    >>  ............................................
    pt  ...Certo. Vamos largar isso e começar de novo.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't, %1$s. I'd have said if I were.
    >>  ............................................
    pt  ...Eu não estava, %1$s. Eu teria dito se estivesse.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not it at all, and I'd hate you to go away thinking it was.
    >>  ............................................
    pt  Não é nada disso, e eu odiaria você ir embora achando que era.
    >>  ............................................
  flirty.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. Let's put that down and start again.
    >>  ............................................
    pt  ...Certo. Vamos largar isso e começar de novo.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't, %1$s. I'd have said if I were.
    >>  ............................................
    pt  ...Eu não estava, %1$s. Eu teria dito se estivesse.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not it at all, and I'd hate you to go away thinking it was.
    >>  ............................................
    pt  Não é nada disso, e eu odiaria você ir embora achando que era.
    >>  ............................................
  friendly.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. Let's put that down and start again.
    >>  ............................................
    pt  ...Certo. Vamos largar isso e começar de novo.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. Now I'm embarrassed and I hadn't been, %1$s.
    >>  ............................................
    pt  ...Eu não estava. Agora eu estou constrangido e não estava, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what I meant and I don't know how to say it better.
    >>  ............................................
    pt  Não é o que eu quis dizer e eu não sei como dizer melhor.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. I'll say less next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu falo menos.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. You brought it up.
    >>  ............................................
    pt  ...Eu não estava. Você que levantou.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what I said and you know it isn't.
    >>  ............................................
    pt  Não foi o que eu disse e você sabe que não foi.
    >>  ............................................
  greedy.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. I'll be clearer next time, since clarity is wanted.
    >>  ............................................
    pt  ...Certo. Da próxima eu sou mais claro, já que querem clareza.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. You brought it up.
    >>  ............................................
    pt  ...Eu não estava. Você que levantou.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what I said and you know it isn't.
    >>  ............................................
    pt  Não foi o que eu disse e você sabe que não foi.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. I'll be clearer next time, since clarity is wanted.
    >>  ............................................
    pt  ...Certo. Da próxima eu sou mais claro, já que querem clareza.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't.
    >>  ............................................
    pt  ...Eu não estava.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.dismiss/2
    en  No. That's not what that was.
    >>  ............................................
    pt  Não. Não era isso.
    >>  ............................................
  introverted.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...You misread it.
    >>  ............................................
    pt  ...Você entendeu errado.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. No harm done either way.
    >>  ............................................
    pt  ...Eu não estava. Sem mal nenhum de todo jeito.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what it was. It'll sort itself out.
    >>  ............................................
    pt  Não era isso. Vai se resolver.
    >>  ............................................
  lazy.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. We'll leave that where it fell.
    >>  ............................................
    pt  ...Certo. Vamos deixar onde caiu.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't.
    >>  ............................................
    pt  ...Eu não estava.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.dismiss/2
    en  No. That's not what that was.
    >>  ............................................
    pt  Não. Não era isso.
    >>  ............................................
  odd.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...You misread it.
    >>  ............................................
    pt  ...Você entendeu errado.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. No harm done either way.
    >>  ............................................
    pt  ...Eu não estava. Sem mal nenhum de todo jeito.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what it was. It'll sort itself out.
    >>  ............................................
    pt  Não era isso. Vai se resolver.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. We'll leave that where it fell.
    >>  ............................................
    pt  ...Certo. Vamos deixar onde caiu.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't! You did that. That was entirely you, %1$s.
    >>  ............................................
    pt  ...Eu não estava! Você que fez isso. Foi inteiramente você, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.dismiss/2
    en  Right, well. Let's both pretend that didn't happen.
    >>  ............................................
    pt  Certo, bom. Vamos os dois fingir que não aconteceu.
    >>  ............................................
  peppy.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Ha. No. You've got the wrong end of it entirely.
    >>  ............................................
    pt  ...Ha. Não. Você entendeu tudo ao contrário.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't! You did that. That was entirely you, %1$s.
    >>  ............................................
    pt  ...Eu não estava! Você que fez isso. Foi inteiramente você, %1$s.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.dismiss/2
    en  Right, well. Let's both pretend that didn't happen.
    >>  ............................................
    pt  Certo, bom. Vamos os dois fingir que não aconteceu.
    >>  ............................................
  playful.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Ha. No. You've got the wrong end of it entirely.
    >>  ............................................
    pt  ...Ha. Não. Você entendeu tudo ao contrário.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. No harm done either way.
    >>  ............................................
    pt  ...Eu não estava. Sem mal nenhum de todo jeito.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what it was. It'll sort itself out.
    >>  ............................................
    pt  Não era isso. Vai se resolver.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. We'll leave that where it fell.
    >>  ............................................
    pt  ...Certo. Vamos deixar onde caiu.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't. Now I'm embarrassed and I hadn't been, %1$s.
    >>  ............................................
    pt  ...Eu não estava. Agora eu estou constrangido e não estava, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.dismiss/2
    en  That's not what I meant and I don't know how to say it better.
    >>  ............................................
    pt  Não é o que eu quis dizer e eu não sei como dizer melhor.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Right. I'll say less next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu falo menos.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't.
    >>  ............................................
    pt  ...Eu não estava.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.dismiss/2
    en  No. That's not what that was.
    >>  ............................................
    pt  Não. Não era isso.
    >>  ............................................
  shy.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...You misread it.
    >>  ............................................
    pt  ...Você entendeu errado.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't! You did that. That was entirely you, %1$s.
    >>  ............................................
    pt  ...Eu não estava! Você que fez isso. Foi inteiramente você, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.dismiss/2
    en  Right, well. Let's both pretend that didn't happen.
    >>  ............................................
    pt  Certo, bom. Vamos os dois fingir que não aconteceu.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Ha. No. You've got the wrong end of it entirely.
    >>  ............................................
    pt  ...Ha. Não. Você entendeu tudo ao contrário.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.dismiss/1
    en  ...I wasn't! You did that. That was entirely you, %1$s.
    >>  ............................................
    pt  ...Eu não estava! Você que fez isso. Foi inteiramente você, %1$s.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.dismiss/2
    en  Right, well. Let's both pretend that didn't happen.
    >>  ............................................
    pt  Certo, bom. Vamos os dois fingir que não aconteceu.
    >>  ............................................
  witty.dialogue.conversations.feelings.platonic.dismiss/3
    en  ...Ha. No. You've got the wrong end of it entirely.
    >>  ............................................
    pt  ...Ha. Não. Você entendeu tudo ao contrário.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.platonic.ask_theirs.to.feelings.platonic`, `feelings.platonic.keep_distance.to.feelings.platonic`, `feelings.platonic.value_them.to.feelings.platonic` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.platonic.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.platonic.followup.leave   [20 chars]
    en  I'll leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.platonic.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.feelings.platonic.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.platonic.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.feelings.platonic.respond / leave
```

```text
  dialogue.conversations.feelings.platonic.leave/1   [42 chars]
    en  It is. Some things don't need dwelling on.
    >>  ............................................
    pt  É sim. Algumas coisas não precisam de tanta demora.
    >>  ............................................
  dialogue.conversations.feelings.platonic.leave/2   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.platonic.leave/3   [36 chars]
    en  Right. Good to have said it, though.
    >>  ............................................
    pt  Certo. Mas foi bom ter dito.
    >>  ............................................
```

---


## `conversations.topic.feelings.platonic.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.first` — e.g. "You're really asking? Fine. You're the part of my day I don't complain about."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.platonic.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.platonic.respond   [26 chars]
    en  That's what you are to me.
    >>  ............................................
    pt  É isso que você é para mim.
    >>  ............................................
```


### Button `value_them` — "You matter to me."

*stance family `empathy` · tone `intimate` · answers the beat(s) `feelings.first.to.feelings.platonic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.platonic.value_them` — accepted phrasings: "you matter to me"; "you mean a great deal to me"; "you are important to me"
  - the message must contain one of: `matter`
  - scored words: `matter`(1.2), `me`(0.3), `to`(0.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.respond.value_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.platonic.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.platonic.respond.value_them   [17 chars]
    en  You matter to me.
    >>  ............................................
    pt  Você é importante para mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `feelings.platonic.value_them`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5, trust +2  _(recorded under topic `feelings.platonic.value_them`)_
- Then opens: `conversations.topic.feelings.platonic.followup`
- …where the player's next choices will be: "You're a friend. A real one." | "I'm fond of you, and that's all." | "Don't read too much into it." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.feelings.platonic.value_them
WHO    VILLAGER — what the player reads after pressing "You matter to me."
       spoken on: conversations.topic.feelings.platonic.respond, button `value_them`
       leaves the player on: conversations.topic.feelings.platonic.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.platonic.value_them.to.feelings.platonic`: the villager accepts. Subject `feelings.platonic`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.platonic.value_them/1   [51 chars]
    en  ...Do I? People don't say that out loud round here.
    >>  ............................................
    pt  ...Sou? As pessoas não dizem isso em voz alta por aqui.
    >>  ............................................
  dialogue.conversations.feelings.platonic.value_them/2   [50 chars]
    en  That's a good thing to be told on an ordinary day.
    >>  ............................................
    pt  É uma coisa boa de ouvir num dia comum.
    >>  ............................................
  dialogue.conversations.feelings.platonic.value_them/3   [55 chars]
    en  You matter to me too, %1$s. There — we've both said it.
    >>  ............................................
    pt  Você também é importante para mim, %1$s. Pronto — nós dois dissemos.
    >>  ............................................
```


### Button `ask_theirs` — "And how do you feel about me?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `feelings.first.to.feelings.platonic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.platonic.ask_theirs` — accepted phrasings: "and how do you feel about me"; "how do you feel about me then"; "what do you feel about me"
  - the message must contain one of: `feel`
  - scored words: `about`(0.3), `feel`(1.0), `me`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.respond.ask_theirs
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.platonic.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.platonic.respond.ask_theirs   [29 chars]
    en  And how do you feel about me?
    >>  ............................................
    pt  E você, o que sente por mim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.platonic.ask_theirs`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — familiarity +4, trust +1  _(recorded under topic `feelings.platonic.ask_theirs`)_
- Then opens: `conversations.topic.feelings.platonic.followup`
- …where the player's next choices will be: "You're a friend. A real one." | "I'm fond of you, and that's all." | "Don't read too much into it." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.feelings.platonic.ask_theirs
WHO    VILLAGER — what the player reads after pressing "And how do you feel about me?"
       spoken on: conversations.topic.feelings.platonic.respond, button `ask_theirs`
       leaves the player on: conversations.topic.feelings.platonic.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.platonic.ask_theirs.to.feelings.platonic`: the villager accepts. Subject `feelings.platonic`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.platonic.ask_theirs/1   [55 chars]
    en  Fondly. Straightforwardly. You're one of the good ones.
    >>  ............................................
    pt  Com carinho. Simplesmente. Você é das pessoas boas.
    >>  ............................................
  dialogue.conversations.feelings.platonic.ask_theirs/2   [50 chars]
    en  Like someone I'd notice if they stopped coming by.
    >>  ............................................
    pt  Como alguém de quem eu sentiria falta se parasse de aparecer.
    >>  ............................................
  dialogue.conversations.feelings.platonic.ask_theirs/3   [62 chars]
    en  Warmly, and without complication. That's rarer than it sounds.
    >>  ............................................
    pt  Com afeto, e sem complicação. É mais raro do que parece.
    >>  ............................................
```


### Button `keep_distance` — "Let's not get sentimental."

*stance family `restraint` · tone `plain` · answers the beat(s) `feelings.first.to.feelings.platonic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.platonic.keep_distance` — accepted phrasings: "let us not get sentimental"; "no need to get sentimental"; "let us keep it unsentimental"
  - the message must contain one of: `sentimental`
  - scored words: `sentimental`(1.5), `soft`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.respond.keep_distance
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.platonic.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.platonic.respond.keep_distance   [26 chars]
    en  Let's not get sentimental.
    >>  ............................................
    pt  Vamos não ficar sentimentais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `feelings.platonic.keep_distance`)_
- Then opens: `conversations.topic.feelings.platonic.followup`
- …where the player's next choices will be: "You're a friend. A real one." | "I'm fond of you, and that's all." | "Don't read too much into it." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.feelings.platonic.keep_distance
WHO    VILLAGER — what the player reads after pressing "Let's not get sentimental."
       spoken on: conversations.topic.feelings.platonic.respond, button `keep_distance`
       leaves the player on: conversations.topic.feelings.platonic.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.platonic.keep_distance.to.feelings.platonic`: the villager accepts. Subject `feelings.platonic`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.platonic.keep_distance/1   [43 chars]
    en  Fair enough. Not everything needs a speech.
    >>  ............................................
    pt  Justo. Nem tudo precisa de discurso.
    >>  ............................................
  dialogue.conversations.feelings.platonic.keep_distance/2   [59 chars]
    en  True enough, let's not. It stands whether we say it or not.
    >>  ............................................
    pt  Bem verdade, vamos não. Vale mesmo sem a gente dizer.
    >>  ............................................
  dialogue.conversations.feelings.platonic.keep_distance/3   [37 chars]
    en  Suits me, %1$s. I'd only get awkward.
    >>  ............................................
    pt  Por mim tudo bem, %1$s. Eu só ficaria sem jeito.
    >>  ............................................
```


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.first.to.feelings.platonic` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.platonic.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.platonic.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.platonic.respond.leave   [20 chars]
    en  I'll leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.platonic.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.feelings.platonic.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.platonic.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.feelings.platonic.followup / leave
```

> Written out in full under **`conversations.topic.feelings.platonic.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.feelings.romantic.followup`

**Reached from 3 route(s):** `conversations.topic.feelings.romantic.respond` / `mutual`; `conversations.topic.feelings.romantic.respond` / `ask_needs`; `conversations.topic.feelings.romantic.respond` / `candid_concern`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.romantic.ask_needs` — e.g. "What I need. Nobody's ever asked it that way round."
- `conversations.feelings.romantic.candid_concern` — e.g. "...Alright. I'd rather hear it than have you carry it quietly."
- `conversations.feelings.romantic.mutual` — e.g. "...Say that again. I've waited a long while to hear it back."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.romantic.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.romantic.followup   [30 chars]
    en  So now you know where I stand.
    >>  ............................................
    pt  Então agora você sabe onde eu estou.
    >>  ............................................
```


### Button `say_it_plainly` — "I love you. Plainly."

*stance family `candor` · tone `intimate` · answers the beat(s) `feelings.romantic.ask_needs.to.feelings.romantic`, `feelings.romantic.candid_concern.to.feelings.romantic`, `feelings.romantic.mutual.to.feelings.romantic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.romantic.followup.say_it_plainly` — accepted phrasings: "i love you plainly"; "i love you and there it is"; "i am in love with you"
  - the message must contain one of: `love`, `plainly`
  - scored words: `love`(1.5), `plainly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.followup.say_it_plainly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.romantic.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.romantic.followup.say_it_plainly   [20 chars]
    en  I love you. Plainly.
    >>  ............................................
    pt  Eu te amo. Simples assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `feelings.romantic.say_it_plainly`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — attraction +6, trust +3  _(recorded under topic `feelings.romantic.say_it_plainly`)_
- Does: arc `feelings` — advance to stage 1
- Then opens: `conversations.topic.feelings.close`
- …where the player's next choices will be: "Thank you for saying it." | "That wasn't easy to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.feelings.romantic.say_it_plainly
WHO    VILLAGER — what the player reads after pressing "I love you. Plainly."
       spoken on: conversations.topic.feelings.romantic.followup, button `say_it_plainly`
       leaves the player on: conversations.topic.feelings.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.romantic.say_it_plainly.to.feelings`: the villager accepts. Subject `feelings`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.romantic.say_it_plainly/1   [67 chars]
    en  ...Plainly. After all this time you say it plainly. I love you too.
    >>  ............................................
    pt  ...Simples assim. Depois de todo esse tempo você diz simples assim. Eu também te amo.
    >>  ............................................
  dialogue.conversations.feelings.romantic.say_it_plainly/2   [55 chars]
    en  You don't say things you don't mean. That's how I know.
    >>  ............................................
    pt  Você não diz coisas que não sente. É assim que eu sei.
    >>  ............................................
  dialogue.conversations.feelings.romantic.say_it_plainly/3   [54 chars]
    en  Then say it every so often, %1$s. I'll not tire of it.
    >>  ............................................
    pt  Então diga de vez em quando, %1$s. Não vou me cansar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time you say it plainly, %1$s. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo você diz sem rodeio, %1$s. Eu também te amo.
    >>  ............................................
  anxious.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. I've had that sentence ready for a year and no way to start it.
    >>  ............................................
    pt  Eu te amo. Tenho essa frase pronta há um ano e nenhum jeito de começar.
    >>  ............................................
  anxious.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it first. I'd been certain I'd have to, and certain I couldn't.
    >>  ............................................
    pt  Você disse primeiro. Eu tinha certeza que teria que ser eu, e certeza que eu não conseguiria.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly. After all this time. I love you too, and I'd have said so eventually.
    >>  ............................................
    pt  Sem rodeio. Depois de todo esse tempo. Eu também te amo, e eu teria dito uma hora.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. It's been true a good while; saying it changes very little and I'm glad it's said.
    >>  ............................................
    pt  Eu te amo. É verdade faz um bom tempo; dizer muda pouco e eu fico contente que esteja dito.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it plainly. Good. There's no rush in it and never was.
    >>  ............................................
    pt  Você disse sem rodeio. Bom. Não há pressa nisso e nunca houve.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly. After all this time you say it plainly. I love you too.
    >>  ............................................
    pt  Sem rodeio. Depois de todo esse tempo você diz sem rodeio. Eu também te amo.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  Right. Plainly, then. I love you. There it is.
    >>  ............................................
    pt  Certo. Sem rodeio, então. Eu te amo. Pronto.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it without dressing it up. So will I. I love you.
    >>  ............................................
    pt  Você disse sem enfeite. Eu também. Eu te amo.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly. After all this time you say it plainly. I love you too.
    >>  ............................................
    pt  Sem rodeio. Depois de todo esse tempo você diz sem rodeio. Eu também te amo.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  Right. Plainly, then. I love you. There it is.
    >>  ............................................
    pt  Certo. Sem rodeio, então. Eu te amo. Pronto.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it without dressing it up. So will I. I love you.
    >>  ............................................
    pt  Você disse sem enfeite. Eu também. Eu te amo.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time you say it plainly, %1$s. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo você diz sem rodeio, %1$s. Eu também te amo.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. I'd been saying it in every other way for a year and hoping you'd hear it.
    >>  ............................................
    pt  Eu te amo. Eu vinha dizendo de todos os outros jeitos há um ano esperando que você ouvisse.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it first. I'd have got there. Slower, but I'd have got there.
    >>  ............................................
    pt  Você disse primeiro. Eu teria chegado lá. Mais devagar, mas teria.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time you say it plainly, %1$s. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo você diz sem rodeio, %1$s. Eu também te amo.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. I'd been saying it in every other way for a year and hoping you'd hear it.
    >>  ............................................
    pt  Eu te amo. Eu vinha dizendo de todos os outros jeitos há um ano esperando que você ouvisse.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it first. I'd have got there. Slower, but I'd have got there.
    >>  ............................................
    pt  Você disse primeiro. Eu teria chegado lá. Mais devagar, mas teria.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time you say it plainly, %1$s. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo você diz sem rodeio, %1$s. Eu também te amo.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. I'd been saying it in every other way for a year and hoping you'd hear it.
    >>  ............................................
    pt  Eu te amo. Eu vinha dizendo de todos os outros jeitos há um ano esperando que você ouvisse.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it first. I'd have got there. Slower, but I'd have got there.
    >>  ............................................
    pt  Você disse primeiro. Eu teria chegado lá. Mais devagar, mas teria.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time you say it plainly, %1$s. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo você diz sem rodeio, %1$s. Eu também te amo.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. I've had that sentence ready for a year and no way to start it.
    >>  ............................................
    pt  Eu te amo. Tenho essa frase pronta há um ano e nenhum jeito de começar.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it first. I'd been certain I'd have to, and certain I couldn't.
    >>  ............................................
    pt  Você disse primeiro. Eu tinha certeza que teria que ser eu, e certeza que eu não conseguiria.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly. After all this time you say it plainly. I love you too.
    >>  ............................................
    pt  Sem rodeio. Depois de todo esse tempo você diz sem rodeio. Eu também te amo.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  Right. Plainly, then. I love you. There it is.
    >>  ............................................
    pt  Certo. Sem rodeio, então. Eu te amo. Pronto.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it without dressing it up. So will I. I love you.
    >>  ............................................
    pt  Você disse sem enfeite. Eu também. Eu te amo.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly. After all this time you say it plainly. I love you too.
    >>  ............................................
    pt  Sem rodeio. Depois de todo esse tempo você diz sem rodeio. Eu também te amo.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  Right. Plainly, then. I love you. There it is.
    >>  ............................................
    pt  Certo. Sem rodeio, então. Eu te amo. Pronto.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it without dressing it up. So will I. I love you.
    >>  ............................................
    pt  Você disse sem enfeite. Eu também. Eu te amo.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo. Eu também te amo.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you.
    >>  ............................................
    pt  Eu te amo.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it. So will I.
    >>  ............................................
    pt  Você disse. Eu também digo.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly. After all this time. I love you too, and I'd have said so eventually.
    >>  ............................................
    pt  Sem rodeio. Depois de todo esse tempo. Eu também te amo, e eu teria dito uma hora.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. It's been true a good while; saying it changes very little and I'm glad it's said.
    >>  ............................................
    pt  Eu te amo. É verdade faz um bom tempo; dizer muda pouco e eu fico contente que esteja dito.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it plainly. Good. There's no rush in it and never was.
    >>  ............................................
    pt  Você disse sem rodeio. Bom. Não há pressa nisso e nunca houve.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo. Eu também te amo.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you.
    >>  ............................................
    pt  Eu te amo.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it. So will I.
    >>  ............................................
    pt  Você disse. Eu também digo.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly. After all this time. I love you too, and I'd have said so eventually.
    >>  ............................................
    pt  Sem rodeio. Depois de todo esse tempo. Eu também te amo, e eu teria dito uma hora.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. It's been true a good while; saying it changes very little and I'm glad it's said.
    >>  ............................................
    pt  Eu te amo. É verdade faz um bom tempo; dizer muda pouco e eu fico contente que esteja dito.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it plainly. Good. There's no rush in it and never was.
    >>  ............................................
    pt  Você disse sem rodeio. Bom. Não há pressa nisso e nunca houve.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly! After all this time you say it plainly. I love you too, obviously.
    >>  ............................................
    pt  Sem rodeio! Depois de todo esse tempo você diz sem rodeio. Eu também te amo, obviamente.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  Right — plainly. I love you. There. That was easier than the last four months.
    >>  ............................................
    pt  Certo — sem rodeio. Eu te amo. Pronto. Foi mais fácil que os últimos quatro meses.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it! Plainly! I love you too and I've been terrible at hiding it.
    >>  ............................................
    pt  Você disse! Sem rodeio! Eu também te amo e eu era péssimo em esconder.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly! After all this time you say it plainly. I love you too, obviously.
    >>  ............................................
    pt  Sem rodeio! Depois de todo esse tempo você diz sem rodeio. Eu também te amo, obviamente.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  Right — plainly. I love you. There. That was easier than the last four months.
    >>  ............................................
    pt  Certo — sem rodeio. Eu te amo. Pronto. Foi mais fácil que os últimos quatro meses.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it! Plainly! I love you too and I've been terrible at hiding it.
    >>  ............................................
    pt  Você disse! Sem rodeio! Eu também te amo e eu era péssimo em esconder.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly. After all this time. I love you too, and I'd have said so eventually.
    >>  ............................................
    pt  Sem rodeio. Depois de todo esse tempo. Eu também te amo, e eu teria dito uma hora.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. It's been true a good while; saying it changes very little and I'm glad it's said.
    >>  ............................................
    pt  Eu te amo. É verdade faz um bom tempo; dizer muda pouco e eu fico contente que esteja dito.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it plainly. Good. There's no rush in it and never was.
    >>  ............................................
    pt  Você disse sem rodeio. Bom. Não há pressa nisso e nunca houve.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time you say it plainly, %1$s. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo você diz sem rodeio, %1$s. Eu também te amo.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you. I've had that sentence ready for a year and no way to start it.
    >>  ............................................
    pt  Eu te amo. Tenho essa frase pronta há um ano e nenhum jeito de começar.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it first. I'd been certain I'd have to, and certain I couldn't.
    >>  ............................................
    pt  Você disse primeiro. Eu tinha certeza que teria que ser eu, e certeza que eu não conseguiria.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  ...Plainly. After all this time. I love you too.
    >>  ............................................
    pt  ...Sem rodeio. Depois de todo esse tempo. Eu também te amo.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  I love you.
    >>  ............................................
    pt  Eu te amo.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it. So will I.
    >>  ............................................
    pt  Você disse. Eu também digo.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly! After all this time you say it plainly. I love you too, obviously.
    >>  ............................................
    pt  Sem rodeio! Depois de todo esse tempo você diz sem rodeio. Eu também te amo, obviamente.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  Right — plainly. I love you. There. That was easier than the last four months.
    >>  ............................................
    pt  Certo — sem rodeio. Eu te amo. Pronto. Foi mais fácil que os últimos quatro meses.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it! Plainly! I love you too and I've been terrible at hiding it.
    >>  ............................................
    pt  Você disse! Sem rodeio! Eu também te amo e eu era péssimo em esconder.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.say_it_plainly/1
    en  Plainly! After all this time you say it plainly. I love you too, obviously.
    >>  ............................................
    pt  Sem rodeio! Depois de todo esse tempo você diz sem rodeio. Eu também te amo, obviamente.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.say_it_plainly/2
    en  Right — plainly. I love you. There. That was easier than the last four months.
    >>  ............................................
    pt  Certo — sem rodeio. Eu te amo. Pronto. Foi mais fácil que os últimos quatro meses.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.say_it_plainly/3
    en  You said it! Plainly! I love you too and I've been terrible at hiding it.
    >>  ............................................
    pt  Você disse! Sem rodeio! Eu também te amo e eu era péssimo em esconder.
    >>  ............................................
```

</details>


### Button `set_boundary` — "I need us to go slower."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.romantic.ask_needs.to.feelings.romantic`, `feelings.romantic.candid_concern.to.feelings.romantic`, `feelings.romantic.mutual.to.feelings.romantic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.romantic.followup.set_boundary` — accepted phrasings: "i need us to go slower"; "can we take this slower"; "i would like to slow down"
  - the message must contain one of: `slow`, `slower`
  - scored words: `slow`(1.2), `slower`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.followup.set_boundary
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.romantic.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.romantic.followup.set_boundary   [23 chars]
    en  I need us to go slower.
    >>  ............................................
    pt  Preciso que a gente vá mais devagar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.romantic.set_boundary`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — respect +5, trust +2  _(recorded under topic `feelings.romantic.set_boundary`)_
- Does: arc `feelings` — advance to stage 1
- Then opens: `conversations.topic.feelings.close`
- …where the player's next choices will be: "Thank you for saying it." | "That wasn't easy to say." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.feelings.romantic.set_boundary
WHO    VILLAGER — what the player reads after pressing "I need us to go slower."
       spoken on: conversations.topic.feelings.romantic.followup, button `set_boundary`
       leaves the player on: conversations.topic.feelings.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.romantic.set_boundary.to.feelings`: the villager accepts. Subject `feelings`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.romantic.set_boundary/1   [69 chars]
    en  ...Slower. Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  ...Mais devagar. Certo. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  dialogue.conversations.feelings.romantic.set_boundary/2   [58 chars]
    en  Thank you for saying it instead of just going quiet on me.
    >>  ............................................
    pt  Obrigado por dizer em vez de simplesmente ficar em silêncio.
    >>  ............................................
  dialogue.conversations.feelings.romantic.set_boundary/3   [60 chars]
    en  Slower it is. You've not changed your mind about me, though?
    >>  ............................................
    pt  Mais devagar, então. Mas você não mudou de ideia sobre mim, né?
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright, %1$s. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  ...Mais devagar. Está bem, %1$s. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  anxious.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. I'll be frightened about it privately and I'll not put that on you.
    >>  ............................................
    pt  Seu ritmo. Vou ter medo em particular e não vou pôr isso em você.
    >>  ............................................
  anxious.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower. Thank you for saying it rather than simply going quiet, which is what I'd expected.
    >>  ............................................
    pt  Mais devagar. Obrigado por dizer em vez de simplesmente calar, que é o que eu esperava.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower. Alright. There's no calendar on this and there never was.
    >>  ............................................
    pt  Mais devagar. Está bem. Não tem calendário nisso e nunca teve.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. I've a great deal of patience and nowhere else to be.
    >>  ............................................
    pt  Seu ritmo. Eu tenho muita paciência e nenhum outro lugar pra estar.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is. Ask me in a year and I'll still be saying the same thing.
    >>  ............................................
    pt  Mais devagar, então. Me pergunte em um ano e eu vou dizer o mesmo.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower. Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  Mais devagar. Está bem. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Right. Your pace. I'll not push and I'll not pretend I wasn't hoping.
    >>  ............................................
    pt  Certo. Seu ritmo. Não vou empurrar e não vou fingir que não esperava.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is. That's a clear answer and I'd rather have one.
    >>  ............................................
    pt  Mais devagar, então. É uma resposta clara e eu prefiro ter uma.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower. Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  Mais devagar. Está bem. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Right. Your pace. I'll not push and I'll not pretend I wasn't hoping.
    >>  ............................................
    pt  Certo. Seu ritmo. Não vou empurrar e não vou fingir que não esperava.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is. That's a clear answer and I'd rather have one.
    >>  ............................................
    pt  Mais devagar, então. É uma resposta clara e eu prefiro ter uma.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright, %1$s. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  ...Mais devagar. Está bem, %1$s. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. Tell me when it changes, or tell me when it doesn't. Either is fine.
    >>  ............................................
    pt  Seu ritmo. Me diga quando mudar, ou me diga quando não mudar. Tanto faz.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower. I'll not raise it. You know where I stand, and standing still is allowed.
    >>  ............................................
    pt  Mais devagar. Não vou levantar. Você sabe onde eu estou, e ficar parado é permitido.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright, %1$s. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  ...Mais devagar. Está bem, %1$s. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. Tell me when it changes, or tell me when it doesn't. Either is fine.
    >>  ............................................
    pt  Seu ritmo. Me diga quando mudar, ou me diga quando não mudar. Tanto faz.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower. I'll not raise it. You know where I stand, and standing still is allowed.
    >>  ............................................
    pt  Mais devagar. Não vou levantar. Você sabe onde eu estou, e ficar parado é permitido.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright, %1$s. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  ...Mais devagar. Está bem, %1$s. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. Tell me when it changes, or tell me when it doesn't. Either is fine.
    >>  ............................................
    pt  Seu ritmo. Me diga quando mudar, ou me diga quando não mudar. Tanto faz.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower. I'll not raise it. You know where I stand, and standing still is allowed.
    >>  ............................................
    pt  Mais devagar. Não vou levantar. Você sabe onde eu estou, e ficar parado é permitido.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright, %1$s. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  ...Mais devagar. Está bem, %1$s. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. I'll be frightened about it privately and I'll not put that on you.
    >>  ............................................
    pt  Seu ritmo. Vou ter medo em particular e não vou pôr isso em você.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower. Thank you for saying it rather than simply going quiet, which is what I'd expected.
    >>  ............................................
    pt  Mais devagar. Obrigado por dizer em vez de simplesmente calar, que é o que eu esperava.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower. Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  Mais devagar. Está bem. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Right. Your pace. I'll not push and I'll not pretend I wasn't hoping.
    >>  ............................................
    pt  Certo. Seu ritmo. Não vou empurrar e não vou fingir que não esperava.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is. That's a clear answer and I'd rather have one.
    >>  ............................................
    pt  Mais devagar, então. É uma resposta clara e eu prefiro ter uma.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower. Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  Mais devagar. Está bem. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Right. Your pace. I'll not push and I'll not pretend I wasn't hoping.
    >>  ............................................
    pt  Certo. Seu ritmo. Não vou empurrar e não vou fingir que não esperava.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is. That's a clear answer and I'd rather have one.
    >>  ............................................
    pt  Mais devagar, então. É uma resposta clara e eu prefiro ter uma.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright.
    >>  ............................................
    pt  ...Mais devagar. Está bem.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. Right.
    >>  ............................................
    pt  Seu ritmo. Certo.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.set_boundary/3
    en  I'd rather have you slowly than not at all.
    >>  ............................................
    pt  Prefiro te ter devagar a não te ter.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower. Alright. There's no calendar on this and there never was.
    >>  ............................................
    pt  Mais devagar. Está bem. Não tem calendário nisso e nunca teve.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. I've a great deal of patience and nowhere else to be.
    >>  ............................................
    pt  Seu ritmo. Eu tenho muita paciência e nenhum outro lugar pra estar.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is. Ask me in a year and I'll still be saying the same thing.
    >>  ............................................
    pt  Mais devagar, então. Me pergunte em um ano e eu vou dizer o mesmo.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright.
    >>  ............................................
    pt  ...Mais devagar. Está bem.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. Right.
    >>  ............................................
    pt  Seu ritmo. Certo.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.set_boundary/3
    en  I'd rather have you slowly than not at all.
    >>  ............................................
    pt  Prefiro te ter devagar a não te ter.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower. Alright. There's no calendar on this and there never was.
    >>  ............................................
    pt  Mais devagar. Está bem. Não tem calendário nisso e nunca teve.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. I've a great deal of patience and nowhere else to be.
    >>  ............................................
    pt  Seu ritmo. Eu tenho muita paciência e nenhum outro lugar pra estar.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is. Ask me in a year and I'll still be saying the same thing.
    >>  ............................................
    pt  Mais devagar, então. Me pergunte em um ano e eu vou dizer o mesmo.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower! Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  Mais devagar! Está bem. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Right, your pace. I'll be here, being patient, which is a new hobby for me.
    >>  ............................................
    pt  Certo, seu ritmo. Vou estar aqui, sendo paciente, um passatempo novo pra mim.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is! I shall busy myself with something else entirely. Probably.
    >>  ............................................
    pt  Mais devagar, então! Vou me ocupar com outra coisa completamente. Provavelmente.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower! Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  Mais devagar! Está bem. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Right, your pace. I'll be here, being patient, which is a new hobby for me.
    >>  ............................................
    pt  Certo, seu ritmo. Vou estar aqui, sendo paciente, um passatempo novo pra mim.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is! I shall busy myself with something else entirely. Probably.
    >>  ............................................
    pt  Mais devagar, então! Vou me ocupar com outra coisa completamente. Provavelmente.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower. Alright. There's no calendar on this and there never was.
    >>  ............................................
    pt  Mais devagar. Está bem. Não tem calendário nisso e nunca teve.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. I've a great deal of patience and nowhere else to be.
    >>  ............................................
    pt  Seu ritmo. Eu tenho muita paciência e nenhum outro lugar pra estar.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is. Ask me in a year and I'll still be saying the same thing.
    >>  ............................................
    pt  Mais devagar, então. Me pergunte em um ano e eu vou dizer o mesmo.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright, %1$s. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  ...Mais devagar. Está bem, %1$s. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. I'll be frightened about it privately and I'll not put that on you.
    >>  ............................................
    pt  Seu ritmo. Vou ter medo em particular e não vou pôr isso em você.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower. Thank you for saying it rather than simply going quiet, which is what I'd expected.
    >>  ............................................
    pt  Mais devagar. Obrigado por dizer em vez de simplesmente calar, que é o que eu esperava.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.set_boundary/1
    en  ...Slower. Alright.
    >>  ............................................
    pt  ...Mais devagar. Está bem.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Your pace. Right.
    >>  ............................................
    pt  Seu ritmo. Certo.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.set_boundary/3
    en  I'd rather have you slowly than not at all.
    >>  ............................................
    pt  Prefiro te ter devagar a não te ter.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower! Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  Mais devagar! Está bem. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Right, your pace. I'll be here, being patient, which is a new hobby for me.
    >>  ............................................
    pt  Certo, seu ritmo. Vou estar aqui, sendo paciente, um passatempo novo pra mim.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is! I shall busy myself with something else entirely. Probably.
    >>  ............................................
    pt  Mais devagar, então! Vou me ocupar com outra coisa completamente. Provavelmente.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.set_boundary/1
    en  Slower! Alright. I'd rather have you at your pace than not at all.
    >>  ............................................
    pt  Mais devagar! Está bem. Prefiro você no seu ritmo a não te ter.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.set_boundary/2
    en  Right, your pace. I'll be here, being patient, which is a new hobby for me.
    >>  ............................................
    pt  Certo, seu ritmo. Vou estar aqui, sendo paciente, um passatempo novo pra mim.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.set_boundary/3
    en  Slower it is! I shall busy myself with something else entirely. Probably.
    >>  ............................................
    pt  Mais devagar, então! Vou me ocupar com outra coisa completamente. Provavelmente.
    >>  ............................................
```

</details>


### Button `deflect` — "Let's not make this a whole thing."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.romantic.ask_needs.to.feelings.romantic`, `feelings.romantic.candid_concern.to.feelings.romantic`, `feelings.romantic.mutual.to.feelings.romantic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.romantic.followup.deflect` — accepted phrasings: "let us not make this a whole thing"; "no need to make a thing of it"; "let us not turn this into something"
  - the message must contain one of: `whole`
  - scored words: `thing`(0.5), `whole`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.followup.deflect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.romantic.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.romantic.followup.deflect   [34 chars]
    en  Let's not make this a whole thing.
    >>  ............................................
    pt  Vamos não transformar isso num caso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `feelings.romantic.deflect`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `feelings.romantic.deflect`)_
- Does: session `turn`
- Then opens: `conversations.topic.feelings.hurt.close`
- …where the player's next choices will be: "No. Don't take it back. That was my fault." | "I heard you. I just didn't answer well." | "I'll let it be."

```text
POOL   dialogue key: dialogue.conversations.feelings.romantic.deflect
WHO    VILLAGER — what the player reads after pressing "Let's not make this a whole thing."
       spoken on: conversations.topic.feelings.romantic.followup, button `deflect`
       leaves the player on: conversations.topic.feelings.hurt.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.romantic.withdrawn`: the villager hurts. Subject `feelings.declared`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `feelings:declared`, `player:brushed_it_off` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.feelings.romantic.deflect/1   [48 chars]
    en  ...A whole thing. Right. Forget I brought it up.
    >>  ............................................
    pt  ...Um caso. Certo. Esquece que eu falei.
    >>  ............................................
  dialogue.conversations.feelings.romantic.deflect/2   [33 chars]
    en  It was a whole thing to me, %1$s.
    >>  ............................................
    pt  Para mim era um caso, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.romantic.deflect/3   [35 chars]
    en  Mm. I'll keep it smaller next time.
    >>  ............................................
    pt  Hm. Vou fazer menor da próxima vez.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Sorry. Forget I brought it up, %1$s.
    >>  ............................................
    pt  ...Certo. Desculpe. Esqueça que eu levantei, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.feelings.romantic.deflect/2
    en  I shouldn't have said anything. I knew that while I was saying it.
    >>  ............................................
    pt  Eu não devia ter dito nada. Eu já sabia enquanto dizia.
    >>  ............................................
  anxious.dialogue.conversations.feelings.romantic.deflect/3
    en  ...That's fine. Truly. I'd rather have asked than wondered.
    >>  ............................................
    pt  ...Tudo bem. Sério. Prefiro ter perguntado a ficar imaginando.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. It'll keep, or it won't. Either's fine.
    >>  ............................................
    pt  ...Certo. Fica pra depois, ou não. Tanto faz.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.deflect/2
    en  Fair enough. No sense pushing a thing that isn't there.
    >>  ............................................
    pt  Tudo bem. Não adianta empurrar o que não existe.
    >>  ............................................
  athletic.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Consider it set down. No hard feelings in it.
    >>  ............................................
    pt  ...Considere largado. Sem mágoa nenhuma.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.deflect/1
    en  ...A whole thing. Right. Forget I brought it up.
    >>  ............................................
    pt  ...Uma coisa toda. Certo. Esqueça que eu levantei.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.deflect/2
    en  That's a no, said the long way round. I'll take the short version.
    >>  ............................................
    pt  Isso é um não, dito pelo caminho longo. Eu aceito a versão curta.
    >>  ............................................
  confident.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Fine. It's put away.
    >>  ............................................
    pt  ...Tudo bem. Está guardado.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.deflect/1
    en  ...A whole thing. Right. Forget I brought it up.
    >>  ............................................
    pt  ...Uma coisa toda. Certo. Esqueça que eu levantei.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.deflect/2
    en  That's a no, said the long way round. I'll take the short version.
    >>  ............................................
    pt  Isso é um não, dito pelo caminho longo. Eu aceito a versão curta.
    >>  ............................................
  crabby.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Fine. It's put away.
    >>  ............................................
    pt  ...Tudo bem. Está guardado.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Forget I said it, %1$s. I'd rather keep what we have.
    >>  ............................................
    pt  ...Certo. Esqueça que eu disse, %1$s. Prefiro manter o que a gente tem.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.deflect/2
    en  That's fair. I'd sooner have you as you are than have said nothing right.
    >>  ............................................
    pt  É justo. Prefiro te ter como você é a ter dito tudo certo.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.romantic.deflect/3
    en  ...I'll not raise it again. We're all right, you and I.
    >>  ............................................
    pt  ...Não levanto de novo. A gente está bem, você e eu.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Forget I said it, %1$s. I'd rather keep what we have.
    >>  ............................................
    pt  ...Certo. Esqueça que eu disse, %1$s. Prefiro manter o que a gente tem.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.deflect/2
    en  That's fair. I'd sooner have you as you are than have said nothing right.
    >>  ............................................
    pt  É justo. Prefiro te ter como você é a ter dito tudo certo.
    >>  ............................................
  flirty.dialogue.conversations.feelings.romantic.deflect/3
    en  ...I'll not raise it again. We're all right, you and I.
    >>  ............................................
    pt  ...Não levanto de novo. A gente está bem, você e eu.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Forget I said it, %1$s. I'd rather keep what we have.
    >>  ............................................
    pt  ...Certo. Esqueça que eu disse, %1$s. Prefiro manter o que a gente tem.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.deflect/2
    en  That's fair. I'd sooner have you as you are than have said nothing right.
    >>  ............................................
    pt  É justo. Prefiro te ter como você é a ter dito tudo certo.
    >>  ............................................
  friendly.dialogue.conversations.feelings.romantic.deflect/3
    en  ...I'll not raise it again. We're all right, you and I.
    >>  ............................................
    pt  ...Não levanto de novo. A gente está bem, você e eu.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Sorry. Forget I brought it up, %1$s.
    >>  ............................................
    pt  ...Certo. Desculpe. Esqueça que eu levantei, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.deflect/2
    en  I shouldn't have said anything. I knew that while I was saying it.
    >>  ............................................
    pt  Eu não devia ter dito nada. Eu já sabia enquanto dizia.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.romantic.deflect/3
    en  ...That's fine. Truly. I'd rather have asked than wondered.
    >>  ............................................
    pt  ...Tudo bem. Sério. Prefiro ter perguntado a ficar imaginando.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.deflect/1
    en  ...A whole thing. Right. Forget I brought it up.
    >>  ............................................
    pt  ...Uma coisa toda. Certo. Esqueça que eu levantei.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.deflect/2
    en  That's a no, said the long way round. I'll take the short version.
    >>  ............................................
    pt  Isso é um não, dito pelo caminho longo. Eu aceito a versão curta.
    >>  ............................................
  greedy.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Fine. It's put away.
    >>  ............................................
    pt  ...Tudo bem. Está guardado.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.deflect/1
    en  ...A whole thing. Right. Forget I brought it up.
    >>  ............................................
    pt  ...Uma coisa toda. Certo. Esqueça que eu levantei.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.deflect/2
    en  That's a no, said the long way round. I'll take the short version.
    >>  ............................................
    pt  Isso é um não, dito pelo caminho longo. Eu aceito a versão curta.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Fine. It's put away.
    >>  ............................................
    pt  ...Tudo bem. Está guardado.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Forget it.
    >>  ............................................
    pt  ...Certo. Esqueça.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.deflect/2
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
  introverted.dialogue.conversations.feelings.romantic.deflect/3
    en  ...I'll not mention it again.
    >>  ............................................
    pt  ...Não menciono de novo.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. It'll keep, or it won't. Either's fine.
    >>  ............................................
    pt  ...Certo. Fica pra depois, ou não. Tanto faz.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.deflect/2
    en  Fair enough. No sense pushing a thing that isn't there.
    >>  ............................................
    pt  Tudo bem. Não adianta empurrar o que não existe.
    >>  ............................................
  lazy.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Consider it set down. No hard feelings in it.
    >>  ............................................
    pt  ...Considere largado. Sem mágoa nenhuma.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Forget it.
    >>  ............................................
    pt  ...Certo. Esqueça.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.deflect/2
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
  odd.dialogue.conversations.feelings.romantic.deflect/3
    en  ...I'll not mention it again.
    >>  ............................................
    pt  ...Não menciono de novo.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. It'll keep, or it won't. Either's fine.
    >>  ............................................
    pt  ...Certo. Fica pra depois, ou não. Tanto faz.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.deflect/2
    en  Fair enough. No sense pushing a thing that isn't there.
    >>  ............................................
    pt  Tudo bem. Não adianta empurrar o que não existe.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Consider it set down. No hard feelings in it.
    >>  ............................................
    pt  ...Considere largado. Sem mágoa nenhuma.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right! Forget it. Splendid. Moving on at speed.
    >>  ............................................
    pt  ...Certo! Esqueça. Esplêndido. Seguindo em alta velocidade.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.deflect/2
    en  Ha. Well. That's that thoroughly buried, then.
    >>  ............................................
    pt  Ha. Bom. Então isso foi bem enterrado.
    >>  ............................................
  peppy.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Consider it unbrought-up. Entirely. Gone.
    >>  ............................................
    pt  ...Considere não levantado. Completamente. Sumiu.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right! Forget it. Splendid. Moving on at speed.
    >>  ............................................
    pt  ...Certo! Esqueça. Esplêndido. Seguindo em alta velocidade.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.deflect/2
    en  Ha. Well. That's that thoroughly buried, then.
    >>  ............................................
    pt  Ha. Bom. Então isso foi bem enterrado.
    >>  ............................................
  playful.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Consider it unbrought-up. Entirely. Gone.
    >>  ............................................
    pt  ...Considere não levantado. Completamente. Sumiu.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. It'll keep, or it won't. Either's fine.
    >>  ............................................
    pt  ...Certo. Fica pra depois, ou não. Tanto faz.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.deflect/2
    en  Fair enough. No sense pushing a thing that isn't there.
    >>  ............................................
    pt  Tudo bem. Não adianta empurrar o que não existe.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Consider it set down. No hard feelings in it.
    >>  ............................................
    pt  ...Considere largado. Sem mágoa nenhuma.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Sorry. Forget I brought it up, %1$s.
    >>  ............................................
    pt  ...Certo. Desculpe. Esqueça que eu levantei, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.deflect/2
    en  I shouldn't have said anything. I knew that while I was saying it.
    >>  ............................................
    pt  Eu não devia ter dito nada. Eu já sabia enquanto dizia.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.romantic.deflect/3
    en  ...That's fine. Truly. I'd rather have asked than wondered.
    >>  ............................................
    pt  ...Tudo bem. Sério. Prefiro ter perguntado a ficar imaginando.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right. Forget it.
    >>  ............................................
    pt  ...Certo. Esqueça.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.deflect/2
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
  shy.dialogue.conversations.feelings.romantic.deflect/3
    en  ...I'll not mention it again.
    >>  ............................................
    pt  ...Não menciono de novo.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right! Forget it. Splendid. Moving on at speed.
    >>  ............................................
    pt  ...Certo! Esqueça. Esplêndido. Seguindo em alta velocidade.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.deflect/2
    en  Ha. Well. That's that thoroughly buried, then.
    >>  ............................................
    pt  Ha. Bom. Então isso foi bem enterrado.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Consider it unbrought-up. Entirely. Gone.
    >>  ............................................
    pt  ...Considere não levantado. Completamente. Sumiu.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.deflect/1
    en  ...Right! Forget it. Splendid. Moving on at speed.
    >>  ............................................
    pt  ...Certo! Esqueça. Esplêndido. Seguindo em alta velocidade.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.deflect/2
    en  Ha. Well. That's that thoroughly buried, then.
    >>  ............................................
    pt  Ha. Bom. Então isso foi bem enterrado.
    >>  ............................................
  witty.dialogue.conversations.feelings.romantic.deflect/3
    en  ...Consider it unbrought-up. Entirely. Gone.
    >>  ............................................
    pt  ...Considere não levantado. Completamente. Sumiu.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let that sit."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.romantic.ask_needs.to.feelings.romantic`, `feelings.romantic.candid_concern.to.feelings.romantic`, `feelings.romantic.mutual.to.feelings.romantic` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.romantic.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.romantic.followup.leave   [18 chars]
    en  I'll let that sit.
    >>  ............................................
    pt  Vou deixar isso assentar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.romantic.leave
WHO    VILLAGER — what the player reads after pressing "I'll let that sit."
       spoken on: conversations.topic.feelings.romantic.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.romantic.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.feelings.romantic.respond / leave
```

```text
  dialogue.conversations.feelings.romantic.leave/1   [47 chars]
    en  So it is. Some things want a moment afterwards.
    >>  ............................................
    pt  É assim mesmo. Algumas coisas precisam de um momento depois.
    >>  ............................................
  dialogue.conversations.feelings.romantic.leave/2   [26 chars]
    en  Go on. I'll be here, %1$s.
    >>  ............................................
    pt  Pode ir. Vou estar aqui, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.romantic.leave/3   [47 chars]
    en  Right. That's enough honesty for one afternoon.
    >>  ............................................
    pt  Certo. Já chega de sinceridade por uma tarde.
    >>  ............................................
```

---


## `conversations.topic.feelings.romantic.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.spouse` — e.g. "You're my whole ledger, %1$s. Every column. Even the messy ones."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.romantic.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.romantic.respond   [28 chars]
    en  That's how I feel about you.
    >>  ............................................
    pt  É assim que me sinto em relação a você.
    >>  ............................................
```


### Button `mutual` — "I feel the same about you."

*stance family `self_disclosure` · tone `intimate` · answers the beat(s) `feelings.spouse.to.feelings.romantic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.romantic.mutual` — accepted phrasings: "i feel the same about you"; "i feel just the same"; "that goes for me as well"
  - scored words: `feel`(0.6), `same`(0.8), `too`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.respond.mutual
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.romantic.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.romantic.respond.mutual   [26 chars]
    en  I feel the same about you.
    >>  ............................................
    pt  Sinto o mesmo por você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `feelings.romantic.mutual`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — attraction +6, warmth +4  _(recorded under topic `feelings.romantic.mutual`)_
- Then opens: `conversations.topic.feelings.romantic.followup`
- …where the player's next choices will be: "I love you. Plainly." | "I need us to go slower." | "Let's not make this a whole thing." | "I'll let that sit."

```text
POOL   dialogue key: dialogue.conversations.feelings.romantic.mutual
WHO    VILLAGER — what the player reads after pressing "I feel the same about you."
       spoken on: conversations.topic.feelings.romantic.respond, button `mutual`
       leaves the player on: conversations.topic.feelings.romantic.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.romantic.mutual.to.feelings.romantic`: the villager accepts. Subject `feelings.romantic`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.romantic.mutual/1   [60 chars]
    en  ...Say that again. I've waited a long while to hear it back.
    >>  ............................................
    pt  ...Diz de novo. Esperei muito tempo para ouvir isso de volta.
    >>  ............................................
  dialogue.conversations.feelings.romantic.mutual/2   [37 chars]
    en  The same. You're sure? ...Good. Good.
    >>  ............................................
    pt  O mesmo. Você tem certeza? ...Bom. Que bom.
    >>  ............................................
  dialogue.conversations.feelings.romantic.mutual/3   [57 chars]
    en  Then we've both been carrying it. Idiots, the pair of us.
    >>  ............................................
    pt  Então nós dois estávamos carregando isso. Bobos, nós dois.
    >>  ............................................
```


### Button `ask_needs` — "What do you need from me?"

*stance family `curiosity` · tone `gentle` · answers the beat(s) `feelings.spouse.to.feelings.romantic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.romantic.ask_needs` — accepted phrasings: "what do you need from me"; "what would you like from me"; "tell me what you need from me"
  - scored words: `from`(0.4), `need`(0.8), `you`(0.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.respond.ask_needs
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.romantic.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.romantic.respond.ask_needs   [25 chars]
    en  What do you need from me?
    >>  ............................................
    pt  O que você precisa de mim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `feelings.romantic.ask_needs`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +5, respect +2  _(recorded under topic `feelings.romantic.ask_needs`)_
- Then opens: `conversations.topic.feelings.romantic.followup`
- …where the player's next choices will be: "I love you. Plainly." | "I need us to go slower." | "Let's not make this a whole thing." | "I'll let that sit."

```text
POOL   dialogue key: dialogue.conversations.feelings.romantic.ask_needs
WHO    VILLAGER — what the player reads after pressing "What do you need from me?"
       spoken on: conversations.topic.feelings.romantic.respond, button `ask_needs`
       leaves the player on: conversations.topic.feelings.romantic.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.romantic.ask_needs.to.feelings.romantic`: the villager accepts. Subject `feelings.romantic`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.romantic.ask_needs/1   [51 chars]
    en  What I need. Nobody's ever asked it that way round.
    >>  ............................................
    pt  O que eu preciso. Ninguém nunca perguntou desse jeito.
    >>  ............................................
  dialogue.conversations.feelings.romantic.ask_needs/2   [58 chars]
    en  ...Time, mostly. And for you to keep asking that question.
    >>  ............................................
    pt  ...Tempo, principalmente. E que você continue fazendo essa pergunta.
    >>  ............................................
  dialogue.conversations.feelings.romantic.ask_needs/3   [67 chars]
    en  Less than you'd think, and more than I say. Ask me again sometimes.
    >>  ............................................
    pt  Menos do que você imagina, e mais do que eu digo. Me pergunte de novo às vezes.
    >>  ............................................
```


### Button `candid_concern` — "There's something we should talk about."

*stance family `candor` · tone `gentle` · answers the beat(s) `feelings.spouse.to.feelings.romantic`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.romantic.candid_concern` — accepted phrasings: "there is something we should talk about"; "we need to talk about something"; "there is a thing we should discuss"
  - scored words: `should`(0.6), `something`(0.5), `talk`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.respond.candid_concern
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.romantic.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.romantic.respond.candid_concern   [39 chars]
    en  There's something we should talk about.
    >>  ............................................
    pt  Tem algo que a gente precisa conversar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.romantic.candid_concern`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +4, tension +2  _(recorded under topic `feelings.romantic.candid_concern`)_
- Then opens: `conversations.topic.feelings.romantic.followup`
- …where the player's next choices will be: "I love you. Plainly." | "I need us to go slower." | "Let's not make this a whole thing." | "I'll let that sit."

```text
POOL   dialogue key: dialogue.conversations.feelings.romantic.candid_concern
WHO    VILLAGER — what the player reads after pressing "There's something we should talk about."
       spoken on: conversations.topic.feelings.romantic.respond, button `candid_concern`
       leaves the player on: conversations.topic.feelings.romantic.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.romantic.candid_concern.to.feelings.romantic`: the villager accepts. Subject `feelings.romantic`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.romantic.candid_concern/1   [62 chars]
    en  ...Alright. I'd rather hear it than have you carry it quietly.
    >>  ............................................
    pt  ...Certo. Prefiro ouvir a te ver carregando isso em silêncio.
    >>  ............................................
  dialogue.conversations.feelings.romantic.candid_concern/2   [63 chars]
    en  Say it, then. We've never been the sort to leave things unsaid.
    >>  ............................................
    pt  Fala, então. Nunca fomos do tipo que deixa coisas por dizer.
    >>  ............................................
  dialogue.conversations.feelings.romantic.candid_concern/3   [49 chars]
    en  That tone. Right — go on, I'm listening properly.
    >>  ............................................
    pt  Esse tom. Certo — fala, estou ouvindo de verdade.
    >>  ............................................
```


### Button `leave` — "I'll let that sit."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.spouse.to.feelings.romantic` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.romantic.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.romantic.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.romantic.respond.leave   [18 chars]
    en  I'll let that sit.
    >>  ............................................
    pt  Vou deixar isso assentar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.romantic.leave
WHO    VILLAGER — what the player reads after pressing "I'll let that sit."
       spoken on: conversations.topic.feelings.romantic.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.romantic.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.feelings.romantic.followup / leave
```

> Written out in full under **`conversations.topic.feelings.romantic.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.feelings.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.toddler` — e.g. "I feel... hungry. And a little like dancing."


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.toddler.respond   [18 chars]
    en  That's how I feel.
    >>  ............................................
    pt  É assim que eu me sinto.
    >>  ............................................
```


### Button `delight` — "Those are good feelings to have."

*stance family `encouragement` · tone `playful` · answers the beat(s) `feelings.toddler.to.feelings.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.toddler.delight` — accepted phrasings: "those are good feelings to have"; "good feelings to have"; "those are good feelings"
  - the message must contain one of: `feelings`, `good`
  - scored words: `feelings`(1.5), `good`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.toddler.respond.delight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.toddler.respond.delight   [32 chars]
    en  Those are good feelings to have.
    >>  ............................................
    pt  Esses são sentimentos bons de ter.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.toddler.delight`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `feelings.toddler.delight`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.toddler.delight
WHO    VILLAGER — what the player reads after pressing "Those are good feelings to have."
       spoken on: conversations.topic.feelings.toddler.respond, button `delight`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.toddler.delight.terminal`: the villager celebrates. Subject `feelings.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.toddler.delight/1   [49 chars]
    en  They ARE good ones. I've got the good ones today.
    >>  ............................................
    pt  São bons MESMO. Hoje eu peguei os bons.
    >>  ............................................
  dialogue.conversations.feelings.toddler.delight/2   [58 chars]
    en  You're allowed lots at once. I checked. Nobody stopped me.
    >>  ............................................
    pt  Pode ter vários ao mesmo tempo. Eu conferi. Ninguém me impediu.
    >>  ............................................
  dialogue.conversations.feelings.toddler.delight/3   [49 chars]
    en  Good ones. Yep. Good ones all the way down, %1$s.
    >>  ............................................
    pt  Os bons. É. Só os bons até lá embaixo, %1$s.
    >>  ............................................
```


### Button `ask` — "What does it feel like?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `feelings.toddler.to.feelings.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.toddler.ask` — accepted phrasings: "what does it feel like"; "how does that feel"; "what is that like"
  - the message must contain one of: `feel`, `like`
  - scored words: `feel`(1.5), `like`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.toddler.respond.ask   [23 chars]
    en  What does it feel like?
    >>  ............................................
    pt  Como é essa sensação?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `feelings.toddler.ask`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.toddler.ask
WHO    VILLAGER — what the player reads after pressing "What does it feel like?"
       spoken on: conversations.topic.feelings.toddler.respond, button `ask`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.toddler.ask.terminal`: the villager asks. Subject `feelings.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.toddler.ask/1   [56 chars]
    en  Like when you run down a hill but you're standing still.
    >>  ............................................
    pt  Tipo descer uma ladeira correndo mas parado.
    >>  ............................................
  dialogue.conversations.feelings.toddler.ask/2   [47 chars]
    en  Like bread. But inside. That's the closest one.
    >>  ............................................
    pt  Tipo pão. Mas por dentro. É o mais parecido.
    >>  ............................................
  dialogue.conversations.feelings.toddler.ask/3   [57 chars]
    en  Um. Wiggly? It's a wiggly one. I don't know the word yet.
    >>  ............................................
    pt  Hã. Mexida? É uma mexida. Ainda não sei a palavra.
    >>  ............................................
```


### Button `leave` — "Off you go and feel it, then."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.toddler.to.feelings.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.toddler.respond.leave   [29 chars]
    en  Off you go and feel it, then.
    >>  ............................................
    pt  Vai sentir, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go and feel it, then."
       spoken on: conversations.topic.feelings.toddler.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.toddler.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.toddler.leave/1   [44 chars]
    en  Bye! I'm going to go and feel it over there.
    >>  ............................................
    pt  Tchau! Vou sentir isso ali.
    >>  ............................................
  dialogue.conversations.feelings.toddler.leave/2   [15 chars]
    en  Okay bye, %1$s!
    >>  ............................................
    pt  Tá, tchau, %1$s!
    >>  ............................................
  dialogue.conversations.feelings.toddler.leave/3   [24 chars]
    en  Bye bye. Feel yours too!
    >>  ............................................
    pt  Tchau tchau. Sente o seu também!
    >>  ............................................
```

---


## `conversations.topic.feelings.young.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `feelings`; `conversations.cat.personal` / `feelings`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.feelings.child` — e.g. "Good! Unless it's bath day. Then betrayed."
- `conversations.feelings.teen` — e.g. "That's kind of personal. ...Fine. Bit stormy lately, alright?"


```text
POOL   dialogue key: dialogue.conversations.topic.feelings.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.feelings.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.feelings.young.respond   [27 chars]
    en  That's what I think of you.
    >>  ............................................
    pt  É isso que eu acho de você.
    >>  ............................................
```


### Button `kind` — "You're a good sort, you know."

*stance family `empathy` · tone `gentle` · answers the beat(s) `feelings.child.to.feelings.young`, `feelings.teen.to.feelings.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.young.kind` — accepted phrasings: "you are a good sort"; "you are a good sort you know"; "you are all right you are"
  - the message must contain one of: `sort`
  - scored words: `good`(0.5), `sort`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.young.respond.kind
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.young.respond.kind   [29 chars]
    en  You're a good sort, you know.
    >>  ............................................
    pt  Você é gente boa, sabia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.young.kind`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `feelings.young.kind`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.young.kind
WHO    VILLAGER — what the player reads after pressing "You're a good sort, you know."
       spoken on: conversations.topic.feelings.young.respond, button `kind`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.young.kind.terminal`: the villager accepts. Subject `feelings.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.young.kind/1   [32 chars]
    en  ...Am I? Nobody says that to me.
    >>  ............................................
    pt  ...Sou? Ninguém me diz isso.
    >>  ............................................
  dialogue.conversations.feelings.young.kind/2   [26 chars]
    en  Good sort. I'll take that.
    >>  ............................................
    pt  Gente boa. Vou aceitar.
    >>  ............................................
  dialogue.conversations.feelings.young.kind/3   [27 chars]
    en  Thanks. You're alright too.
    >>  ............................................
    pt  Valeu. Você também é gente boa.
    >>  ............................................
```


### Button `proud` — "I'm glad you're around."

*stance family `encouragement` · tone `plain` · answers the beat(s) `feelings.child.to.feelings.young`, `feelings.teen.to.feelings.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.young.proud` — accepted phrasings: "i am glad you are around"; "glad to have you around"; "it is good having you about"
  - the message must contain one of: `around`
  - scored words: `around`(1.2), `glad`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.young.respond.proud
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.young.respond.proud   [23 chars]
    en  I'm glad you're around.
    >>  ............................................
    pt  Fico feliz que você esteja por perto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `feelings.young.proud`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +2  _(recorded under topic `feelings.young.proud`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.young.proud
WHO    VILLAGER — what the player reads after pressing "I'm glad you're around."
       spoken on: conversations.topic.feelings.young.respond, button `proud`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.young.proud.terminal`: the villager accepts. Subject `feelings.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.young.proud/1   [31 chars]
    en  ...That's a nice thing to hear.
    >>  ............................................
    pt  ...É uma coisa boa de ouvir.
    >>  ............................................
  dialogue.conversations.feelings.young.proud/2   [75 chars]
    en  Glad you're around. Ha. Don't get soppy about it. ...That was nice, though.
    >>  ............................................
    pt  Feliz que você está por aqui. Ha. Não fica meloso. ...Mas foi legal.
    >>  ............................................
  dialogue.conversations.feelings.young.proud/3   [39 chars]
    en  Me too. About you being around, I mean.
    >>  ............................................
    pt  Eu também. De você estar por perto, quero dizer.
    >>  ............................................
```


### Button `brush_off` — "That's a bit much."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `feelings.child.to.feelings.young`, `feelings.teen.to.feelings.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `feelings.young.brush_off` — accepted phrasings: "that is a bit much"; "that is rather much"; "steady on that is a lot"
  - the message must contain one of: `bit`
  - scored words: `bit`(1.2), `much`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.young.respond.brush_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.young.respond.brush_off   [18 chars]
    en  That's a bit much.
    >>  ............................................
    pt  Isso é demais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `feelings.young.brush_off`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `feelings.young.brush_off`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.young.brush_off
WHO    VILLAGER — what the player reads after pressing "That's a bit much."
       spoken on: conversations.topic.feelings.young.respond, button `brush_off`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.young.brush_off.terminal`: the villager refuses. Subject `feelings.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.young.brush_off/1   [19 chars]
    en  ...Fine. Forget it.
    >>  ............................................
    pt  ...Tá. Esquece.
    >>  ............................................
  dialogue.conversations.feelings.young.brush_off/2   [18 chars]
    en  You asked, though.
    >>  ............................................
    pt  Mas você perguntou.
    >>  ............................................
  dialogue.conversations.feelings.young.brush_off/3   [33 chars]
    en  Right. Sorry for saying anything.
    >>  ............................................
    pt  Certo. Desculpa por ter falado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. Sorry. That was silly of me.
    >>  ............................................
    pt  ...Tudo bem. Desculpe. Foi bobagem minha.
    >>  ............................................
  anxious.dialogue.conversations.feelings.young.brush_off/2
    en  Right. I shouldn't have said it, %1$s.
    >>  ............................................
    pt  Certo. Eu não devia ter dito, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.feelings.young.brush_off/3
    en  ...Forget it. Please.
    >>  ............................................
    pt  ...Esqueça. Por favor.
    >>  ............................................
  athletic.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. It'll keep.
    >>  ............................................
    pt  ...Tudo bem. Fica pra depois.
    >>  ............................................
  athletic.dialogue.conversations.feelings.young.brush_off/2
    en  All right. Another day, maybe.
    >>  ............................................
    pt  Está bem. Outro dia, talvez.
    >>  ............................................
  athletic.dialogue.conversations.feelings.young.brush_off/3
    en  ...Right you are. Never mind it.
    >>  ............................................
    pt  ...Você tem razão. Deixa pra lá.
    >>  ............................................
  confident.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. Forget it.
    >>  ............................................
    pt  ...Tudo bem. Esqueça.
    >>  ............................................
  confident.dialogue.conversations.feelings.young.brush_off/2
    en  Right. I'll not say that sort of thing again.
    >>  ............................................
    pt  Certo. Não vou dizer esse tipo de coisa de novo.
    >>  ............................................
  confident.dialogue.conversations.feelings.young.brush_off/3
    en  ...Then I'll keep it to myself.
    >>  ............................................
    pt  ...Então eu guardo pra mim.
    >>  ............................................
  crabby.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. Forget it.
    >>  ............................................
    pt  ...Tudo bem. Esqueça.
    >>  ............................................
  crabby.dialogue.conversations.feelings.young.brush_off/2
    en  Right. I'll not say that sort of thing again.
    >>  ............................................
    pt  Certo. Não vou dizer esse tipo de coisa de novo.
    >>  ............................................
  crabby.dialogue.conversations.feelings.young.brush_off/3
    en  ...Then I'll keep it to myself.
    >>  ............................................
    pt  ...Então eu guardo pra mim.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. I only said it because it was true, %1$s.
    >>  ............................................
    pt  ...Tudo bem. Eu só disse porque era verdade, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.young.brush_off/2
    en  Right. Forget it. I'd not have said it to just anyone, mind.
    >>  ............................................
    pt  Certo. Esqueça. Mas eu não teria dito pra qualquer um.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.young.brush_off/3
    en  ...I'll not say it again. But I meant it.
    >>  ............................................
    pt  ...Não digo de novo. Mas eu falava sério.
    >>  ............................................
  flirty.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. I only said it because it was true, %1$s.
    >>  ............................................
    pt  ...Tudo bem. Eu só disse porque era verdade, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.feelings.young.brush_off/2
    en  Right. Forget it. I'd not have said it to just anyone, mind.
    >>  ............................................
    pt  Certo. Esqueça. Mas eu não teria dito pra qualquer um.
    >>  ............................................
  flirty.dialogue.conversations.feelings.young.brush_off/3
    en  ...I'll not say it again. But I meant it.
    >>  ............................................
    pt  ...Não digo de novo. Mas eu falava sério.
    >>  ............................................
  friendly.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. I only said it because it was true, %1$s.
    >>  ............................................
    pt  ...Tudo bem. Eu só disse porque era verdade, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.feelings.young.brush_off/2
    en  Right. Forget it. I'd not have said it to just anyone, mind.
    >>  ............................................
    pt  Certo. Esqueça. Mas eu não teria dito pra qualquer um.
    >>  ............................................
  friendly.dialogue.conversations.feelings.young.brush_off/3
    en  ...I'll not say it again. But I meant it.
    >>  ............................................
    pt  ...Não digo de novo. Mas eu falava sério.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. Sorry. That was silly of me.
    >>  ............................................
    pt  ...Tudo bem. Desculpe. Foi bobagem minha.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.young.brush_off/2
    en  Right. I shouldn't have said it, %1$s.
    >>  ............................................
    pt  Certo. Eu não devia ter dito, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.young.brush_off/3
    en  ...Forget it. Please.
    >>  ............................................
    pt  ...Esqueça. Por favor.
    >>  ............................................
  greedy.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. Forget it.
    >>  ............................................
    pt  ...Tudo bem. Esqueça.
    >>  ............................................
  greedy.dialogue.conversations.feelings.young.brush_off/2
    en  Right. I'll not say that sort of thing again.
    >>  ............................................
    pt  Certo. Não vou dizer esse tipo de coisa de novo.
    >>  ............................................
  greedy.dialogue.conversations.feelings.young.brush_off/3
    en  ...Then I'll keep it to myself.
    >>  ............................................
    pt  ...Então eu guardo pra mim.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. Forget it.
    >>  ............................................
    pt  ...Tudo bem. Esqueça.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.young.brush_off/2
    en  Right. I'll not say that sort of thing again.
    >>  ............................................
    pt  Certo. Não vou dizer esse tipo de coisa de novo.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.young.brush_off/3
    en  ...Then I'll keep it to myself.
    >>  ............................................
    pt  ...Então eu guardo pra mim.
    >>  ............................................
  introverted.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine.
    >>  ............................................
    pt  ...Tudo bem.
    >>  ............................................
  introverted.dialogue.conversations.feelings.young.brush_off/2
    en  Forget it, then.
    >>  ............................................
    pt  Esqueça, então.
    >>  ............................................
  introverted.dialogue.conversations.feelings.young.brush_off/3
    en  ...Right. Nothing.
    >>  ............................................
    pt  ...Certo. Nada.
    >>  ............................................
  lazy.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. It'll keep.
    >>  ............................................
    pt  ...Tudo bem. Fica pra depois.
    >>  ............................................
  lazy.dialogue.conversations.feelings.young.brush_off/2
    en  All right. Another day, maybe.
    >>  ............................................
    pt  Está bem. Outro dia, talvez.
    >>  ............................................
  lazy.dialogue.conversations.feelings.young.brush_off/3
    en  ...Right you are. Never mind it.
    >>  ............................................
    pt  ...Você tem razão. Deixa pra lá.
    >>  ............................................
  odd.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine.
    >>  ............................................
    pt  ...Tudo bem.
    >>  ............................................
  odd.dialogue.conversations.feelings.young.brush_off/2
    en  Forget it, then.
    >>  ............................................
    pt  Esqueça, então.
    >>  ............................................
  odd.dialogue.conversations.feelings.young.brush_off/3
    en  ...Right. Nothing.
    >>  ............................................
    pt  ...Certo. Nada.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. It'll keep.
    >>  ............................................
    pt  ...Tudo bem. Fica pra depois.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.young.brush_off/2
    en  All right. Another day, maybe.
    >>  ............................................
    pt  Está bem. Outro dia, talvez.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.young.brush_off/3
    en  ...Right you are. Never mind it.
    >>  ............................................
    pt  ...Você tem razão. Deixa pra lá.
    >>  ............................................
  peppy.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine! Forget it. Completely. Gone from my head.
    >>  ............................................
    pt  ...Tudo bem! Esqueça. Completamente. Sumiu da minha cabeça.
    >>  ............................................
  peppy.dialogue.conversations.feelings.young.brush_off/2
    en  Right, well. That's embarrassing. Onwards!
    >>  ............................................
    pt  Certo, bom. Que constrangedor. Adiante!
    >>  ............................................
  peppy.dialogue.conversations.feelings.young.brush_off/3
    en  ...Never mind. I'll go and be dignified somewhere else.
    >>  ............................................
    pt  ...Deixa pra lá. Vou ser digno em outro lugar.
    >>  ............................................
  playful.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine! Forget it. Completely. Gone from my head.
    >>  ............................................
    pt  ...Tudo bem! Esqueça. Completamente. Sumiu da minha cabeça.
    >>  ............................................
  playful.dialogue.conversations.feelings.young.brush_off/2
    en  Right, well. That's embarrassing. Onwards!
    >>  ............................................
    pt  Certo, bom. Que constrangedor. Adiante!
    >>  ............................................
  playful.dialogue.conversations.feelings.young.brush_off/3
    en  ...Never mind. I'll go and be dignified somewhere else.
    >>  ............................................
    pt  ...Deixa pra lá. Vou ser digno em outro lugar.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. It'll keep.
    >>  ............................................
    pt  ...Tudo bem. Fica pra depois.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.young.brush_off/2
    en  All right. Another day, maybe.
    >>  ............................................
    pt  Está bem. Outro dia, talvez.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.young.brush_off/3
    en  ...Right you are. Never mind it.
    >>  ............................................
    pt  ...Você tem razão. Deixa pra lá.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine. Sorry. That was silly of me.
    >>  ............................................
    pt  ...Tudo bem. Desculpe. Foi bobagem minha.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.young.brush_off/2
    en  Right. I shouldn't have said it, %1$s.
    >>  ............................................
    pt  Certo. Eu não devia ter dito, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.young.brush_off/3
    en  ...Forget it. Please.
    >>  ............................................
    pt  ...Esqueça. Por favor.
    >>  ............................................
  shy.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine.
    >>  ............................................
    pt  ...Tudo bem.
    >>  ............................................
  shy.dialogue.conversations.feelings.young.brush_off/2
    en  Forget it, then.
    >>  ............................................
    pt  Esqueça, então.
    >>  ............................................
  shy.dialogue.conversations.feelings.young.brush_off/3
    en  ...Right. Nothing.
    >>  ............................................
    pt  ...Certo. Nada.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine! Forget it. Completely. Gone from my head.
    >>  ............................................
    pt  ...Tudo bem! Esqueça. Completamente. Sumiu da minha cabeça.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.young.brush_off/2
    en  Right, well. That's embarrassing. Onwards!
    >>  ............................................
    pt  Certo, bom. Que constrangedor. Adiante!
    >>  ............................................
  upbeat.dialogue.conversations.feelings.young.brush_off/3
    en  ...Never mind. I'll go and be dignified somewhere else.
    >>  ............................................
    pt  ...Deixa pra lá. Vou ser digno em outro lugar.
    >>  ............................................
  witty.dialogue.conversations.feelings.young.brush_off/1
    en  ...Fine! Forget it. Completely. Gone from my head.
    >>  ............................................
    pt  ...Tudo bem! Esqueça. Completamente. Sumiu da minha cabeça.
    >>  ............................................
  witty.dialogue.conversations.feelings.young.brush_off/2
    en  Right, well. That's embarrassing. Onwards!
    >>  ............................................
    pt  Certo, bom. Que constrangedor. Adiante!
    >>  ............................................
  witty.dialogue.conversations.feelings.young.brush_off/3
    en  ...Never mind. I'll go and be dignified somewhere else.
    >>  ............................................
    pt  ...Deixa pra lá. Vou ser digno em outro lugar.
    >>  ............................................
```

</details>


### Button `leave` — "Off you go."

*stance family `exit` · tone `plain` · answers the beat(s) `feelings.child.to.feelings.young`, `feelings.teen.to.feelings.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.feelings.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.feelings.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.feelings.young.respond.leave   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.young.leave
WHO    VILLAGER — what the player reads after pressing "Off you go."
       spoken on: conversations.topic.feelings.young.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.young.leave.terminal`: the villager accepts. Subject `feelings.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.feelings.young.leave/1   [9 chars]
    en  Bye then.
    >>  ............................................
    pt  Tchau então.
    >>  ............................................
  dialogue.conversations.feelings.young.leave/2   [14 chars]
    en  See you, %1$s.
    >>  ............................................
    pt  Até mais, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.young.leave/3   [10 chars]
    en  Okay. Bye.
    >>  ............................................
    pt  Tá. Tchau.
    >>  ............................................
```

---

