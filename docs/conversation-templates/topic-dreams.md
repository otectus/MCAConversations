# Topic: dreams

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `dreams` |
| Opened from | question `conversations.cat.personal`, button `dreams` |
| Depth class (its heart budget) | `deep` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `encouragement`, `curiosity`, `respectful_disagreement`, `dismissal`, `restraint`, `exit` |
| Narrative arc | `dreams`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.dreams
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.dreams   [24 chars]
    en  What do you dream about?
    >>  ............................................
    pt  Com o que você sonha?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.dreams.resume.followup`](#conversations-arc-dreams-resume-followup)
- [`conversations.arc.dreams.resume.respond`](#conversations-arc-dreams-resume-respond)
- [`conversations.dreams`](#conversations-dreams)
- [`conversations.scene.dreams.followup`](#conversations-scene-dreams-followup)
- [`conversations.scene.dreams.the_named_one.respond`](#conversations-scene-dreams-the-named-one-respond)
- [`conversations.scene.dreams.the_small_version.respond`](#conversations-scene-dreams-the-small-version-respond)
- [`conversations.topic.dreams.close`](#conversations-topic-dreams-close)
- [`conversations.topic.dreams.close.honest`](#conversations-topic-dreams-close-honest)
- [`conversations.topic.dreams.deflated.followup`](#conversations-topic-dreams-deflated-followup)
- [`conversations.topic.dreams.followup`](#conversations-topic-dreams-followup)
- [`conversations.topic.dreams.guarded.respond`](#conversations-topic-dreams-guarded-respond)
- [`conversations.topic.dreams.mocked.close`](#conversations-topic-dreams-mocked-close)
- [`conversations.topic.dreams.respond`](#conversations-topic-dreams-respond)
- [`conversations.topic.dreams.toddler.respond`](#conversations-topic-dreams-toddler-respond)
- [`conversations.topic.dreams.young.respond`](#conversations-topic-dreams-young-respond)

---

## `conversations.arc.dreams.resume.followup`

**Reached from 5 route(s):** `conversations.arc.dreams.resume.respond` / `ask_progress`; `conversations.arc.dreams.resume.respond` / `ask_progress`; `conversations.arc.dreams.resume.respond` / `ask_progress`; `conversations.arc.dreams.resume.respond` / `ask_progress`; `conversations.arc.dreams.resume.respond` / `offer_step`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.resume.ask_progress.honest` — e.g. "You never told me it would work. You came back to ask anyway. That's why I'm still at it."
- `conversations.dreams.resume.ask_progress.lapsed` — e.g. "You said you'd help. I waited a bit, then got on with it alone. That's usually how it goes."
- `conversations.dreams.resume.ask_progress.plain` — e.g. "A little. Slowly. But you asking is more than most do."
- `conversations.dreams.resume.ask_progress.pledged` — e.g. "You said you'd help, and you came back to ask. That counts for a great deal."
- `conversations.dreams.resume.offer_step` — e.g. "The first step. Nobody breaks it down like that. ...Alright, here it is."


```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.dreams.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.dreams.resume.followup   [28 chars]
    en  So that's where it's got to.
    >>  ............................................
    pt  Então é aí que chegou.
    >>  ............................................
```


### Button `encourage` — "Keep at it. You'll get there."

*stance family `encouragement` · tone `plain` · answers the beat(s) `dreams.resume.ask_progress.honest.to.dreams`, `dreams.resume.ask_progress.lapsed.to.dreams`, `dreams.resume.ask_progress.plain.to.dreams`, `dreams.resume.ask_progress.pledged.to.dreams`, `dreams.resume.offer_step.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.resume.followup.encourage` — accepted phrasings: "keep at it, you will get there"; "keep going at it"; "you will get there, keep at it"
  - the message must contain one of: `keep`
  - scored words: `keep`(1.5), `there`(0.9)

```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.followup.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.dreams.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.dreams.resume.followup.encourage   [29 chars]
    en  Keep at it. You'll get there.
    >>  ............................................
    pt  Continue. Você vai chegar lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.resume.followup.encourage`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +1  _(recorded under topic `dreams.resume.followup.encourage`)_
- Then opens: `conversations.topic.dreams.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you what I want, too." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.followup.encourage
WHO    VILLAGER — what the player reads after pressing "Keep at it. You'll get there."
       spoken on: conversations.arc.dreams.resume.followup, button `encourage`
       leaves the player on: conversations.topic.dreams.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.followup.encourage.to.dreams`: the villager accepts. Subject `dreams`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.resume.followup.encourage/1   [81 chars]
    en  You say that like it's obvious. It isn't, but I'll borrow your version for a bit.
    >>  ............................................
    pt  Você fala como se fosse óbvio. Não é, mas vou pegar emprestada a sua versão por um tempo.
    >>  ............................................
  dialogue.conversations.dreams.resume.followup.encourage/2   [88 chars]
    en  Just so. Well. It's easier to keep at a thing when somebody's watching to see if you do.
    >>  ............................................
    pt  Pois é. Bom. É mais fácil insistir numa coisa quando alguém está olhando para ver se você insiste.
    >>  ............................................
  dialogue.conversations.dreams.resume.followup.encourage/3   [85 chars]
    en  I'll get there. ...I said that out loud and it didn't sound stupid, %1$s. That's new.
    >>  ............................................
    pt  Eu vou chegar lá. ...Falei isso em voz alta e não soou burro, %1$s. Isso é novo.
    >>  ............................................
```


### Button `practical` — "What's the next actual step?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `dreams.resume.ask_progress.honest.to.dreams`, `dreams.resume.ask_progress.lapsed.to.dreams`, `dreams.resume.ask_progress.plain.to.dreams`, `dreams.resume.ask_progress.pledged.to.dreams`, `dreams.resume.offer_step.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.resume.followup.practical` — accepted phrasings: "what is the next actual step"; "what is the next step"; "so what comes next then"
  - the message must contain one of: `step`
  - scored words: `step`(1.6), `next`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.followup.practical
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.dreams.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.dreams.resume.followup.practical   [28 chars]
    en  What's the next actual step?
    >>  ............................................
    pt  Qual é o próximo passo, de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.resume.followup.practical`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `dreams.resume.followup.practical`)_
- Then opens: `conversations.topic.dreams.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you what I want, too." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.followup.practical
WHO    VILLAGER — what the player reads after pressing "What's the next actual step?"
       spoken on: conversations.arc.dreams.resume.followup, button `practical`
       leaves the player on: conversations.topic.dreams.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.followup.practical.to.dreams`: the villager accepts. Subject `dreams`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.resume.followup.practical/1   [95 chars]
    en  The next step. Nobody asks that. They ask if I'm still doing it, which isn't the same question.
    >>  ............................................
    pt  O próximo passo. Ninguém pergunta isso. Perguntam se eu ainda estou nisso, que não é a mesma coisa.
    >>  ............................................
  dialogue.conversations.dreams.resume.followup.practical/2   [78 chars]
    en  Right — the next step is the boring one. Which is why it's still the next one.
    >>  ............................................
    pt  Certo — o próximo passo é o chato. Por isso ainda é o próximo.
    >>  ............................................
  dialogue.conversations.dreams.resume.followup.practical/3   [93 chars]
    en  Give me a moment. ...There. Written on the inside of my head. Thank you for making me say it.
    >>  ............................................
    pt  Me dá um instante. ...Pronto. Anotado do lado de dentro da cabeça. Obrigado por me fazer dizer.
    >>  ............................................
```


### Button `honest` — "It might not happen, you know."

*stance family `candor` · tone `plain` · answers the beat(s) `dreams.resume.ask_progress.honest.to.dreams`, `dreams.resume.ask_progress.lapsed.to.dreams`, `dreams.resume.ask_progress.plain.to.dreams`, `dreams.resume.ask_progress.pledged.to.dreams`, `dreams.resume.offer_step.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.resume.followup.honest` — accepted phrasings: "it might not happen you know"; "it might not come off"; "this might not happen"
  - the message must contain one of: `might`, `happen`
  - scored words: `might`(1.5), `happen`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.followup.honest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.dreams.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.dreams.resume.followup.honest   [30 chars]
    en  It might not happen, you know.
    >>  ............................................
    pt  Pode ser que não aconteça, sabe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +5, tension +2  _(recorded under topic `dreams.resume.followup.honest`)_
- Then opens: `conversations.topic.dreams.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you what I want, too." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.followup.honest
WHO    VILLAGER — what the player reads after pressing "It might not happen, you know."
       spoken on: conversations.arc.dreams.resume.followup, button `honest`
       leaves the player on: conversations.topic.dreams.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.followup.honest.to.dreams`: the villager accepts. Subject `dreams`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.resume.followup.honest/1   [93 chars]
    en  It might not. I know. I'd rather hear that from you than from myself at three in the morning.
    >>  ............................................
    pt  Pode não acontecer. Eu sei. Prefiro ouvir de você do que de mim mesmo às três da manhã.
    >>  ............................................
  dialogue.conversations.dreams.resume.followup.honest/2   [83 chars]
    en  That's the truth of it, aye. Doesn't stop me. But it's good that one of us says it.
    >>  ............................................
    pt  É a verdade, sim. Não me faz parar. Mas é bom que um de nós diga.
    >>  ............................................
  dialogue.conversations.dreams.resume.followup.honest/3   [74 chars]
    en  Not many will say that. They nod and move on. ...I'd rather be told, %1$s.
    >>  ............................................
    pt  Poucos dizem isso. Concordam com a cabeça e seguem. ...Prefiro que me digam, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.resume.ask_progress.honest.to.dreams`, `dreams.resume.ask_progress.lapsed.to.dreams`, `dreams.resume.ask_progress.plain.to.dreams`, `dreams.resume.ask_progress.pledged.to.dreams`, `dreams.resume.offer_step.to.dreams` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.dreams.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.dreams.resume.followup.leave   [28 chars]
    en  I'll let you get on with it.
    >>  ............................................
    pt  Vou deixar você tocar isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with it."
       spoken on: conversations.arc.dreams.resume.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.followup.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.resume.followup.leave/1   [67 chars]
    en  True enough, go on. It'll still be here in the morning, worse luck.
    >>  ............................................
    pt  Bem verdade, pode ir. Continua aqui de manhã, infelizmente.
    >>  ............................................
  dialogue.conversations.dreams.resume.followup.leave/2   [26 chars]
    en  Just so. Back to it, then.
    >>  ............................................
    pt  Exato. De volta ao trabalho, então.
    >>  ............................................
  dialogue.conversations.dreams.resume.followup.leave/3   [45 chars]
    en  Off you go, %1$s. Thanks for asking after it.
    >>  ............................................
    pt  Pode ir, %1$s. Obrigado por perguntar disso.
    >>  ............................................
```

---


## `conversations.arc.dreams.resume.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `dreams`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.revisit` — e.g. "Still dreaming about what I told you. It hasn't gotten smaller."


```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.dreams.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.dreams.resume.respond   [31 chars]
    en  That thing I told you I wanted.
    >>  ............................................
    pt  Aquilo que eu te disse que queria.
    >>  ............................................
```


### Button `ask_progress` — "Any closer to it?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `dreams.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.resume.ask_progress` — accepted phrasings: "any closer to it"; "any progress"; "are you any closer"
  - the message must contain one of: `closer`, `progress`
  - scored words: `closer`(1.5), `progress`(1.5), `any`(0.5)

```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.respond.ask_progress
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.dreams.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.dreams.resume.respond.ask_progress   [17 chars]
    en  Any closer to it?
    >>  ............................................
    pt  Chegou mais perto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +100 when exclusive `dreams.support` is `pledged`
- Fires when: RULED OUT when LACKS the memory `mcaconversations.pledge.dreams` (this player only)  _(chance -2000)_
- Does: **hearts +2** — decision id `dreams.resume.ask_progress`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `dreams.resume.ask_progress`)_
- Does: arc `dreams` — advance to stage 2
- Then opens: `conversations.arc.dreams.resume.followup`
- …where the player's next choices will be: "Keep at it. You'll get there." | "What's the next actual step?" | "It might not happen, you know." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.ask_progress.pledged
WHO    VILLAGER — what the player reads after pressing "Any closer to it?"
       spoken on: conversations.arc.dreams.resume.respond, button `ask_progress`
       leaves the player on: conversations.arc.dreams.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.ask_progress.pledged.to.dreams`: the villager accepts. Subject `dreams`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.resume.ask_progress.pledged/1   [76 chars]
    en  You said you'd help, and you came back to ask. That counts for a great deal.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou para perguntar. Isso conta muito.
    >>  ............................................
  dialogue.conversations.dreams.resume.ask_progress.pledged/2   [58 chars]
    en  Closer, aye — partly because somebody was going to ask me.
    >>  ............................................
    pt  Mais perto, é — em parte porque alguém ia me perguntar.
    >>  ............................................
  dialogue.conversations.dreams.resume.ask_progress.pledged/3   [60 chars]
    en  I've moved on it. I'd not have, if you hadn't offered, %1$s.
    >>  ............................................
    pt  Eu avancei. Não teria avançado se você não tivesse se oferecido, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask. I'd counted the days, %1$s.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar. Eu contei os dias, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You turned up. I had a number in my head for how long I'd wait and you were inside it.
    >>  ............................................
    pt  Você apareceu. Eu tinha um número na cabeça pra quanto eu esperaria e você chegou dentro.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Both halves. I'd braced for the half where you didn't.
    >>  ............................................
    pt  As duas metades. Eu tinha me preparado pra metade em que você não vinha.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back. Promises keep better when somebody returns to them.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou. Promessas se conservam melhor quando alguém retorna a elas.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Both halves, in order, at your own pace. That's how these things actually get done.
    >>  ............................................
    pt  As duas metades, em ordem, no seu ritmo. É assim que essas coisas se fazem.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. There's no hurry now that there are two of us at it.
    >>  ............................................
    pt  Certo. Não há pressa agora que somos dois nisso.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask. That counts.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar. Isso conta.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You offered and then you turned up. Most people manage one of those.
    >>  ............................................
    pt  Você ofereceu e depois apareceu. A maioria consegue uma das duas.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. Said and done, in that order. I'll not pretend that's usual.
    >>  ............................................
    pt  Certo. Dito e feito, nessa ordem. Não vou fingir que é comum.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask. That counts.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar. Isso conta.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You offered and then you turned up. Most people manage one of those.
    >>  ............................................
    pt  Você ofereceu e depois apareceu. A maioria consegue uma das duas.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. Said and done, in that order. I'll not pretend that's usual.
    >>  ............................................
    pt  Certo. Dito e feito, nessa ordem. Não vou fingir que é comum.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask, %1$s. That counts for a great deal.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar, %1$s. Isso conta muito.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You did both. I'd have been glad of either and you brought both.
    >>  ............................................
    pt  Você fez as duas coisas. Eu ficaria contente com qualquer uma e você trouxe as duas.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  You turned up. That's the rarer half of a promise and you did it without being reminded.
    >>  ............................................
    pt  Você apareceu. É a metade mais rara de uma promessa e você fez sem ser lembrado.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask, %1$s. That counts for a great deal.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar, %1$s. Isso conta muito.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You did both. I'd have been glad of either and you brought both.
    >>  ............................................
    pt  Você fez as duas coisas. Eu ficaria contente com qualquer uma e você trouxe as duas.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  You turned up. That's the rarer half of a promise and you did it without being reminded.
    >>  ............................................
    pt  Você apareceu. É a metade mais rara de uma promessa e você fez sem ser lembrado.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask, %1$s. That counts for a great deal.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar, %1$s. Isso conta muito.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You did both. I'd have been glad of either and you brought both.
    >>  ............................................
    pt  Você fez as duas coisas. Eu ficaria contente com qualquer uma e você trouxe as duas.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  You turned up. That's the rarer half of a promise and you did it without being reminded.
    >>  ............................................
    pt  Você apareceu. É a metade mais rara de uma promessa e você fez sem ser lembrado.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask. I'd counted the days, %1$s.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar. Eu contei os dias, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You turned up. I had a number in my head for how long I'd wait and you were inside it.
    >>  ............................................
    pt  Você apareceu. Eu tinha um número na cabeça pra quanto eu esperaria e você chegou dentro.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Both halves. I'd braced for the half where you didn't.
    >>  ............................................
    pt  As duas metades. Eu tinha me preparado pra metade em que você não vinha.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask. That counts.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar. Isso conta.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You offered and then you turned up. Most people manage one of those.
    >>  ............................................
    pt  Você ofereceu e depois apareceu. A maioria consegue uma das duas.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. Said and done, in that order. I'll not pretend that's usual.
    >>  ............................................
    pt  Certo. Dito e feito, nessa ordem. Não vou fingir que é comum.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask. That counts.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar. Isso conta.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You offered and then you turned up. Most people manage one of those.
    >>  ............................................
    pt  Você ofereceu e depois apareceu. A maioria consegue uma das duas.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. Said and done, in that order. I'll not pretend that's usual.
    >>  ............................................
    pt  Certo. Dito e feito, nessa ordem. Não vou fingir que é comum.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back. That counts.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou. Isso conta.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Offered, and then here. That's the order.
    >>  ............................................
    pt  Ofereceu, e depois aqui. É a ordem.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. You turned up.
    >>  ............................................
    pt  Certo. Você apareceu.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back. Promises keep better when somebody returns to them.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou. Promessas se conservam melhor quando alguém retorna a elas.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Both halves, in order, at your own pace. That's how these things actually get done.
    >>  ............................................
    pt  As duas metades, em ordem, no seu ritmo. É assim que essas coisas se fazem.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. There's no hurry now that there are two of us at it.
    >>  ............................................
    pt  Certo. Não há pressa agora que somos dois nisso.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back. That counts.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou. Isso conta.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Offered, and then here. That's the order.
    >>  ............................................
    pt  Ofereceu, e depois aqui. É a ordem.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. You turned up.
    >>  ............................................
    pt  Certo. Você apareceu.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back. Promises keep better when somebody returns to them.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou. Promessas se conservam melhor quando alguém retorna a elas.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Both halves, in order, at your own pace. That's how these things actually get done.
    >>  ............................................
    pt  As duas metades, em ordem, no seu ritmo. É assim que essas coisas se fazem.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. There's no hurry now that there are two of us at it.
    >>  ............................................
    pt  Certo. Não há pressa agora que somos dois nisso.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help and you came back to ask! Both halves! Do you know how rare that is?
    >>  ............................................
    pt  Você disse que ajudaria e voltou pra perguntar! As duas metades! Sabe o quão raro é?
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Offered AND turned up. I'd frame the second one if I had a frame.
    >>  ............................................
    pt  Ofereceu E apareceu. Eu emolduraria a segunda parte se tivesse moldura.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  You came back! Right. Now I have to actually make progress, which is your fault.
    >>  ............................................
    pt  Você voltou! Certo. Agora eu tenho que progredir de verdade, e a culpa é sua.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help and you came back to ask! Both halves! Do you know how rare that is?
    >>  ............................................
    pt  Você disse que ajudaria e voltou pra perguntar! As duas metades! Sabe o quão raro é?
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Offered AND turned up. I'd frame the second one if I had a frame.
    >>  ............................................
    pt  Ofereceu E apareceu. Eu emolduraria a segunda parte se tivesse moldura.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  You came back! Right. Now I have to actually make progress, which is your fault.
    >>  ............................................
    pt  Você voltou! Certo. Agora eu tenho que progredir de verdade, e a culpa é sua.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back. Promises keep better when somebody returns to them.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou. Promessas se conservam melhor quando alguém retorna a elas.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Both halves, in order, at your own pace. That's how these things actually get done.
    >>  ............................................
    pt  As duas metades, em ordem, no seu ritmo. É assim que essas coisas se fazem.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. There's no hurry now that there are two of us at it.
    >>  ............................................
    pt  Certo. Não há pressa agora que somos dois nisso.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back to ask. I'd counted the days, %1$s.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou pra perguntar. Eu contei os dias, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  You turned up. I had a number in my head for how long I'd wait and you were inside it.
    >>  ............................................
    pt  Você apareceu. Eu tinha um número na cabeça pra quanto eu esperaria e você chegou dentro.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Both halves. I'd braced for the half where you didn't.
    >>  ............................................
    pt  As duas metades. Eu tinha me preparado pra metade em que você não vinha.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help, and you came back. That counts.
    >>  ............................................
    pt  Você disse que ajudaria, e voltou. Isso conta.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Offered, and then here. That's the order.
    >>  ............................................
    pt  Ofereceu, e depois aqui. É a ordem.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  Right. You turned up.
    >>  ............................................
    pt  Certo. Você apareceu.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help and you came back to ask! Both halves! Do you know how rare that is?
    >>  ............................................
    pt  Você disse que ajudaria e voltou pra perguntar! As duas metades! Sabe o quão raro é?
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Offered AND turned up. I'd frame the second one if I had a frame.
    >>  ............................................
    pt  Ofereceu E apareceu. Eu emolduraria a segunda parte se tivesse moldura.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  You came back! Right. Now I have to actually make progress, which is your fault.
    >>  ............................................
    pt  Você voltou! Certo. Agora eu tenho que progredir de verdade, e a culpa é sua.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.pledged/1
    en  You said you'd help and you came back to ask! Both halves! Do you know how rare that is?
    >>  ............................................
    pt  Você disse que ajudaria e voltou pra perguntar! As duas metades! Sabe o quão raro é?
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.pledged/2
    en  Offered AND turned up. I'd frame the second one if I had a frame.
    >>  ............................................
    pt  Ofereceu E apareceu. Eu emolduraria a segunda parte se tivesse moldura.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.pledged/3
    en  You came back! Right. Now I have to actually make progress, which is your fault.
    >>  ............................................
    pt  Você voltou! Certo. Agora eu tenho que progredir de verdade, e a culpa é sua.
    >>  ............................................
```

</details>


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +100 when exclusive `dreams.support` is `pledged`
- Fires when: RULED OUT when has the memory `mcaconversations.pledge.dreams` (this player only)  _(chance -2000)_
- Does: disposition — trust -3, tension +4  _(recorded under topic `dreams.resume.ask_progress`)_
- Does: arc `dreams` — advance to stage 2
- Then opens: `conversations.arc.dreams.resume.followup`
- …where the player's next choices will be: "Keep at it. You'll get there." | "What's the next actual step?" | "It might not happen, you know." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.ask_progress.lapsed
WHO    VILLAGER — what the player reads after pressing "Any closer to it?"
       spoken on: conversations.arc.dreams.resume.respond, button `ask_progress`
       leaves the player on: conversations.arc.dreams.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.ask_progress.lapsed.to.dreams`: the villager accepts. Subject `dreams`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.resume.ask_progress.lapsed/1   [91 chars]
    en  You said you'd help. I waited a bit, then got on with it alone. That's usually how it goes.
    >>  ............................................
    pt  Você disse que ia ajudar. Esperei um pouco e depois toquei sozinho. Costuma ser assim.
    >>  ............................................
  dialogue.conversations.dreams.resume.ask_progress.lapsed/2   [73 chars]
    en  Some. On my own, mostly — you'd offered, and then the days went by, %1$s.
    >>  ............................................
    pt  Um pouco. Sozinho, principalmente — você tinha oferecido, e aí os dias passaram, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.resume.ask_progress.lapsed/3   [97 chars]
    en  It's moved. Not because of the offer, if I'm honest. But you're asking now, and that's something.
    >>  ............................................
    pt  Andou. Não por causa da oferta, para ser honesto. Mas você está perguntando agora, e isso é alguma coisa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited longer than I'd admit, %1$s, and then I stopped.
    >>  ............................................
    pt  Você disse que ajudaria. Esperei mais do que eu admitiria, %1$s, e depois parei.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  I'm not angry. I'd rather I were — angry passes and this just settled.
    >>  ............................................
    pt  Não estou bravo. Preferia estar — bravo passa e isso só assentou.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  You're here now, and I've spent two months learning not to need that.
    >>  ............................................
    pt  Você está aqui agora, e eu passei dois meses aprendendo a não precisar disso.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited a bit, then got on with it. That's how most offers go.
    >>  ............................................
    pt  Você disse que ajudaria. Esperei um pouco, depois toquei sozinho. É como quase toda oferta vai.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  It's been a while. Things drift. I'd not make much of it.
    >>  ............................................
    pt  Faz um tempo. As coisas escorrem. Eu não faria caso.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now, and now is the only part either of us can do anything about.
    >>  ............................................
    pt  Certo. Você está aqui agora, e agora é a única parte com que a gente pode fazer algo.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited a bit, then got on with it alone.
    >>  ............................................
    pt  Você disse que ajudaria. Eu esperei um pouco, depois toquei sozinho.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You did offer. I stopped counting on it around the second month.
    >>  ............................................
    pt  Você ofereceu. Parei de contar com isso lá pelo segundo mês.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now. That's a different thing from being here then.
    >>  ............................................
    pt  Certo. Você está aqui agora. É diferente de estar aqui na época.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited a bit, then got on with it alone.
    >>  ............................................
    pt  Você disse que ajudaria. Eu esperei um pouco, depois toquei sozinho.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You did offer. I stopped counting on it around the second month.
    >>  ............................................
    pt  Você ofereceu. Parei de contar com isso lá pelo segundo mês.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now. That's a different thing from being here then.
    >>  ............................................
    pt  Certo. Você está aqui agora. É diferente de estar aqui na época.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help, %1$s. I waited a bit, then got on with it alone.
    >>  ............................................
    pt  Você disse que ajudaria, %1$s. Eu esperei um pouco, depois toquei sozinho.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You offered and then life happened. I know how that goes. I still noticed.
    >>  ............................................
    pt  Você ofereceu e aí a vida aconteceu. Eu sei como é. Mas eu reparei.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  You're here now. I'd rather say that than pretend I hadn't been waiting.
    >>  ............................................
    pt  Você está aqui agora. Prefiro dizer isso a fingir que eu não estava esperando.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help, %1$s. I waited a bit, then got on with it alone.
    >>  ............................................
    pt  Você disse que ajudaria, %1$s. Eu esperei um pouco, depois toquei sozinho.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You offered and then life happened. I know how that goes. I still noticed.
    >>  ............................................
    pt  Você ofereceu e aí a vida aconteceu. Eu sei como é. Mas eu reparei.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  You're here now. I'd rather say that than pretend I hadn't been waiting.
    >>  ............................................
    pt  Você está aqui agora. Prefiro dizer isso a fingir que eu não estava esperando.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help, %1$s. I waited a bit, then got on with it alone.
    >>  ............................................
    pt  Você disse que ajudaria, %1$s. Eu esperei um pouco, depois toquei sozinho.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You offered and then life happened. I know how that goes. I still noticed.
    >>  ............................................
    pt  Você ofereceu e aí a vida aconteceu. Eu sei como é. Mas eu reparei.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  You're here now. I'd rather say that than pretend I hadn't been waiting.
    >>  ............................................
    pt  Você está aqui agora. Prefiro dizer isso a fingir que eu não estava esperando.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited longer than I'd admit, %1$s, and then I stopped.
    >>  ............................................
    pt  Você disse que ajudaria. Esperei mais do que eu admitiria, %1$s, e depois parei.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  I'm not angry. I'd rather I were — angry passes and this just settled.
    >>  ............................................
    pt  Não estou bravo. Preferia estar — bravo passa e isso só assentou.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  You're here now, and I've spent two months learning not to need that.
    >>  ............................................
    pt  Você está aqui agora, e eu passei dois meses aprendendo a não precisar disso.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited a bit, then got on with it alone.
    >>  ............................................
    pt  Você disse que ajudaria. Eu esperei um pouco, depois toquei sozinho.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You did offer. I stopped counting on it around the second month.
    >>  ............................................
    pt  Você ofereceu. Parei de contar com isso lá pelo segundo mês.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now. That's a different thing from being here then.
    >>  ............................................
    pt  Certo. Você está aqui agora. É diferente de estar aqui na época.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited a bit, then got on with it alone.
    >>  ............................................
    pt  Você disse que ajudaria. Eu esperei um pouco, depois toquei sozinho.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You did offer. I stopped counting on it around the second month.
    >>  ............................................
    pt  Você ofereceu. Parei de contar com isso lá pelo segundo mês.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now. That's a different thing from being here then.
    >>  ............................................
    pt  Certo. Você está aqui agora. É diferente de estar aqui na época.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited. Then I got on with it.
    >>  ............................................
    pt  Você disse que ajudaria. Eu esperei. Depois toquei sozinho.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  It's been a while. I stopped expecting it.
    >>  ............................................
    pt  Faz um tempo. Parei de esperar.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now.
    >>  ............................................
    pt  Certo. Você está aqui agora.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited a bit, then got on with it. That's how most offers go.
    >>  ............................................
    pt  Você disse que ajudaria. Esperei um pouco, depois toquei sozinho. É como quase toda oferta vai.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  It's been a while. Things drift. I'd not make much of it.
    >>  ............................................
    pt  Faz um tempo. As coisas escorrem. Eu não faria caso.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now, and now is the only part either of us can do anything about.
    >>  ............................................
    pt  Certo. Você está aqui agora, e agora é a única parte com que a gente pode fazer algo.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited. Then I got on with it.
    >>  ............................................
    pt  Você disse que ajudaria. Eu esperei. Depois toquei sozinho.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  It's been a while. I stopped expecting it.
    >>  ............................................
    pt  Faz um tempo. Parei de esperar.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now.
    >>  ............................................
    pt  Certo. Você está aqui agora.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited a bit, then got on with it. That's how most offers go.
    >>  ............................................
    pt  Você disse que ajudaria. Esperei um pouco, depois toquei sozinho. É como quase toda oferta vai.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  It's been a while. Things drift. I'd not make much of it.
    >>  ............................................
    pt  Faz um tempo. As coisas escorrem. Eu não faria caso.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now, and now is the only part either of us can do anything about.
    >>  ............................................
    pt  Certo. Você está aqui agora, e agora é a única parte com que a gente pode fazer algo.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help! I waited, then got on with it. No hard feelings, mostly.
    >>  ............................................
    pt  Você disse que ajudaria! Eu esperei, depois toquei sozinho. Sem mágoa, quase.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You did offer. I've a very good memory for offers, which is a personality flaw.
    >>  ............................................
    pt  Você ofereceu. Eu tenho memória excelente pra ofertas, o que é um defeito de caráter.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Ah, you're back! I'd filed this one under 'mine' about two months ago.
    >>  ............................................
    pt  Ah, você voltou! Eu tinha arquivado isso como 'meu' uns dois meses atrás.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help! I waited, then got on with it. No hard feelings, mostly.
    >>  ............................................
    pt  Você disse que ajudaria! Eu esperei, depois toquei sozinho. Sem mágoa, quase.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You did offer. I've a very good memory for offers, which is a personality flaw.
    >>  ............................................
    pt  Você ofereceu. Eu tenho memória excelente pra ofertas, o que é um defeito de caráter.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Ah, you're back! I'd filed this one under 'mine' about two months ago.
    >>  ............................................
    pt  Ah, você voltou! Eu tinha arquivado isso como 'meu' uns dois meses atrás.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited a bit, then got on with it. That's how most offers go.
    >>  ............................................
    pt  Você disse que ajudaria. Esperei um pouco, depois toquei sozinho. É como quase toda oferta vai.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  It's been a while. Things drift. I'd not make much of it.
    >>  ............................................
    pt  Faz um tempo. As coisas escorrem. Eu não faria caso.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now, and now is the only part either of us can do anything about.
    >>  ............................................
    pt  Certo. Você está aqui agora, e agora é a única parte com que a gente pode fazer algo.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited longer than I'd admit, %1$s, and then I stopped.
    >>  ............................................
    pt  Você disse que ajudaria. Esperei mais do que eu admitiria, %1$s, e depois parei.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  I'm not angry. I'd rather I were — angry passes and this just settled.
    >>  ............................................
    pt  Não estou bravo. Preferia estar — bravo passa e isso só assentou.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  You're here now, and I've spent two months learning not to need that.
    >>  ............................................
    pt  Você está aqui agora, e eu passei dois meses aprendendo a não precisar disso.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help. I waited. Then I got on with it.
    >>  ............................................
    pt  Você disse que ajudaria. Eu esperei. Depois toquei sozinho.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  It's been a while. I stopped expecting it.
    >>  ............................................
    pt  Faz um tempo. Parei de esperar.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Right. You're here now.
    >>  ............................................
    pt  Certo. Você está aqui agora.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help! I waited, then got on with it. No hard feelings, mostly.
    >>  ............................................
    pt  Você disse que ajudaria! Eu esperei, depois toquei sozinho. Sem mágoa, quase.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You did offer. I've a very good memory for offers, which is a personality flaw.
    >>  ............................................
    pt  Você ofereceu. Eu tenho memória excelente pra ofertas, o que é um defeito de caráter.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Ah, you're back! I'd filed this one under 'mine' about two months ago.
    >>  ............................................
    pt  Ah, você voltou! Eu tinha arquivado isso como 'meu' uns dois meses atrás.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.lapsed/1
    en  You said you'd help! I waited, then got on with it. No hard feelings, mostly.
    >>  ............................................
    pt  Você disse que ajudaria! Eu esperei, depois toquei sozinho. Sem mágoa, quase.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.lapsed/2
    en  You did offer. I've a very good memory for offers, which is a personality flaw.
    >>  ............................................
    pt  Você ofereceu. Eu tenho memória excelente pra ofertas, o que é um defeito de caráter.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.lapsed/3
    en  Ah, you're back! I'd filed this one under 'mine' about two months ago.
    >>  ............................................
    pt  Ah, você voltou! Eu tinha arquivado isso como 'meu' uns dois meses atrás.
    >>  ............................................
```

</details>


**Outcome 3 of 4** — base weight `0`

- Fires when: weighted +100 when exclusive `dreams.support` is `honest`
- Does: **hearts +1** — decision id `dreams.resume.ask_progress`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `dreams.resume.ask_progress`)_
- Does: arc `dreams` — advance to stage 2
- Then opens: `conversations.arc.dreams.resume.followup`
- …where the player's next choices will be: "Keep at it. You'll get there." | "What's the next actual step?" | "It might not happen, you know." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.ask_progress.honest
WHO    VILLAGER — what the player reads after pressing "Any closer to it?"
       spoken on: conversations.arc.dreams.resume.respond, button `ask_progress`
       leaves the player on: conversations.arc.dreams.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.ask_progress.honest.to.dreams`: the villager accepts. Subject `dreams`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.resume.ask_progress.honest/1   [89 chars]
    en  You never told me it would work. You came back to ask anyway. That's why I'm still at it.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo. E voltou para perguntar mesmo assim. Por isso eu continuo.
    >>  ............................................
  dialogue.conversations.dreams.resume.ask_progress.honest/2   [95 chars]
    en  Somebody who said the hard thing and still wants to know how it's going — aye, a little closer.
    >>  ............................................
    pt  Alguém que disse a verdade dura e ainda quer saber como vai — é, um pouco mais perto.
    >>  ............................................
  dialogue.conversations.dreams.resume.ask_progress.honest/3   [71 chars]
    en  You promised me nothing, %1$s. It makes the asking mean more, not less.
    >>  ............................................
    pt  Você não me prometeu nada, %1$s. Isso faz a pergunta valer mais, não menos.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back to ask anyway, %1$s, and I noticed both.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo. E voltou pra perguntar, %1$s, e eu reparei nas duas coisas.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  The honest answer hurt at the time. The coming back is what made it worth having.
    >>  ............................................
    pt  A resposta honesta doeu na hora. Voltar é o que fez valer.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  You didn't promise. I'd braced for the not-coming-back that usually follows that.
    >>  ............................................
    pt  Você não prometeu. Eu me preparei pro não-voltar que costuma vir depois disso.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work, and you came back to ask. Both of those wear well.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo, e voltou pra perguntar. As duas coisas se conservam bem.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  Honest, then patient. That's the pair that lasts.
    >>  ............................................
    pt  Honesto, depois paciente. É o par que dura.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. Slowly, and you're still asking. That's how a thing gets anywhere.
    >>  ............................................
    pt  Certo. Devagar, e você continua perguntando. É assim que algo chega a algum lugar.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back to ask anyway.
    >>  ............................................
    pt  Você nunca disse que ia dar certo. E voltou pra perguntar mesmo assim.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  You said it might not, and then you asked how it went. Those two together are rare.
    >>  ............................................
    pt  Você disse que podia não dar, e depois perguntou como foi. Os dois juntos são raros.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. You didn't promise and you still turned up. Noted.
    >>  ............................................
    pt  Certo. Você não prometeu e ainda apareceu. Anotado.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back to ask anyway.
    >>  ............................................
    pt  Você nunca disse que ia dar certo. E voltou pra perguntar mesmo assim.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  You said it might not, and then you asked how it went. Those two together are rare.
    >>  ............................................
    pt  Você disse que podia não dar, e depois perguntou como foi. Os dois juntos são raros.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. You didn't promise and you still turned up. Noted.
    >>  ............................................
    pt  Certo. Você não prometeu e ainda apareceu. Anotado.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work, %1$s. You came back to ask anyway. That's the part I'll keep.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo, %1$s. E voltou pra perguntar. É a parte que eu guardo.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  You were honest and then you were here. I'd take that over encouragement any day.
    >>  ............................................
    pt  Você foi honesto e depois esteve aqui. Prefiro isso a incentivo qualquer dia.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Coming back after saying it might not work — that took something.
    >>  ............................................
    pt  Voltar depois de dizer que podia não dar certo — isso exigiu algo.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work, %1$s. You came back to ask anyway. That's the part I'll keep.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo, %1$s. E voltou pra perguntar. É a parte que eu guardo.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  You were honest and then you were here. I'd take that over encouragement any day.
    >>  ............................................
    pt  Você foi honesto e depois esteve aqui. Prefiro isso a incentivo qualquer dia.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Coming back after saying it might not work — that took something.
    >>  ............................................
    pt  Voltar depois de dizer que podia não dar certo — isso exigiu algo.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work, %1$s. You came back to ask anyway. That's the part I'll keep.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo, %1$s. E voltou pra perguntar. É a parte que eu guardo.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  You were honest and then you were here. I'd take that over encouragement any day.
    >>  ............................................
    pt  Você foi honesto e depois esteve aqui. Prefiro isso a incentivo qualquer dia.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Coming back after saying it might not work — that took something.
    >>  ............................................
    pt  Voltar depois de dizer que podia não dar certo — isso exigiu algo.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back to ask anyway, %1$s, and I noticed both.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo. E voltou pra perguntar, %1$s, e eu reparei nas duas coisas.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  The honest answer hurt at the time. The coming back is what made it worth having.
    >>  ............................................
    pt  A resposta honesta doeu na hora. Voltar é o que fez valer.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  You didn't promise. I'd braced for the not-coming-back that usually follows that.
    >>  ............................................
    pt  Você não prometeu. Eu me preparei pro não-voltar que costuma vir depois disso.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back to ask anyway.
    >>  ............................................
    pt  Você nunca disse que ia dar certo. E voltou pra perguntar mesmo assim.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  You said it might not, and then you asked how it went. Those two together are rare.
    >>  ............................................
    pt  Você disse que podia não dar, e depois perguntou como foi. Os dois juntos são raros.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. You didn't promise and you still turned up. Noted.
    >>  ............................................
    pt  Certo. Você não prometeu e ainda apareceu. Anotado.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back to ask anyway.
    >>  ............................................
    pt  Você nunca disse que ia dar certo. E voltou pra perguntar mesmo assim.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  You said it might not, and then you asked how it went. Those two together are rare.
    >>  ............................................
    pt  Você disse que podia não dar, e depois perguntou como foi. Os dois juntos são raros.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. You didn't promise and you still turned up. Noted.
    >>  ............................................
    pt  Certo. Você não prometeu e ainda apareceu. Anotado.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back anyway.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo. E voltou mesmo assim.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  Honest first, and then here. That's the order that counts.
    >>  ............................................
    pt  Honesto primeiro, e depois aqui. É a ordem que conta.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. You asked. That's more than most.
    >>  ............................................
    pt  Certo. Você perguntou. É mais que a maioria.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work, and you came back to ask. Both of those wear well.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo, e voltou pra perguntar. As duas coisas se conservam bem.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  Honest, then patient. That's the pair that lasts.
    >>  ............................................
    pt  Honesto, depois paciente. É o par que dura.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. Slowly, and you're still asking. That's how a thing gets anywhere.
    >>  ............................................
    pt  Certo. Devagar, e você continua perguntando. É assim que algo chega a algum lugar.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back anyway.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo. E voltou mesmo assim.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  Honest first, and then here. That's the order that counts.
    >>  ............................................
    pt  Honesto primeiro, e depois aqui. É a ordem que conta.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. You asked. That's more than most.
    >>  ............................................
    pt  Certo. Você perguntou. É mais que a maioria.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work, and you came back to ask. Both of those wear well.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo, e voltou pra perguntar. As duas coisas se conservam bem.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  Honest, then patient. That's the pair that lasts.
    >>  ............................................
    pt  Honesto, depois paciente. É o par que dura.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. Slowly, and you're still asking. That's how a thing gets anywhere.
    >>  ............................................
    pt  Certo. Devagar, e você continua perguntando. É assim que algo chega a algum lugar.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never said it would work! And you came back to ask anyway. That's very good form.
    >>  ............................................
    pt  Você nunca disse que ia dar certo! E voltou pra perguntar mesmo assim. Isso é muita classe.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  No promises and you turned up regardless. I'd call that a better class of friend.
    >>  ............................................
    pt  Sem promessas e apareceu do mesmo jeito. Eu chamaria isso de um amigo melhor.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  You asked! After being honest about the odds. Nobody does both.
    >>  ............................................
    pt  Você perguntou! Depois de ser honesto sobre as chances. Ninguém faz os dois.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never said it would work! And you came back to ask anyway. That's very good form.
    >>  ............................................
    pt  Você nunca disse que ia dar certo! E voltou pra perguntar mesmo assim. Isso é muita classe.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  No promises and you turned up regardless. I'd call that a better class of friend.
    >>  ............................................
    pt  Sem promessas e apareceu do mesmo jeito. Eu chamaria isso de um amigo melhor.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  You asked! After being honest about the odds. Nobody does both.
    >>  ............................................
    pt  Você perguntou! Depois de ser honesto sobre as chances. Ninguém faz os dois.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work, and you came back to ask. Both of those wear well.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo, e voltou pra perguntar. As duas coisas se conservam bem.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  Honest, then patient. That's the pair that lasts.
    >>  ............................................
    pt  Honesto, depois paciente. É o par que dura.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. Slowly, and you're still asking. That's how a thing gets anywhere.
    >>  ............................................
    pt  Certo. Devagar, e você continua perguntando. É assim que algo chega a algum lugar.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back to ask anyway, %1$s, and I noticed both.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo. E voltou pra perguntar, %1$s, e eu reparei nas duas coisas.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  The honest answer hurt at the time. The coming back is what made it worth having.
    >>  ............................................
    pt  A resposta honesta doeu na hora. Voltar é o que fez valer.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  You didn't promise. I'd braced for the not-coming-back that usually follows that.
    >>  ............................................
    pt  Você não prometeu. Eu me preparei pro não-voltar que costuma vir depois disso.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never told me it would work. You came back anyway.
    >>  ............................................
    pt  Você nunca me disse que ia dar certo. E voltou mesmo assim.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  Honest first, and then here. That's the order that counts.
    >>  ............................................
    pt  Honesto primeiro, e depois aqui. É a ordem que conta.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  Right. You asked. That's more than most.
    >>  ............................................
    pt  Certo. Você perguntou. É mais que a maioria.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never said it would work! And you came back to ask anyway. That's very good form.
    >>  ............................................
    pt  Você nunca disse que ia dar certo! E voltou pra perguntar mesmo assim. Isso é muita classe.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  No promises and you turned up regardless. I'd call that a better class of friend.
    >>  ............................................
    pt  Sem promessas e apareceu do mesmo jeito. Eu chamaria isso de um amigo melhor.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  You asked! After being honest about the odds. Nobody does both.
    >>  ............................................
    pt  Você perguntou! Depois de ser honesto sobre as chances. Ninguém faz os dois.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.honest/1
    en  You never said it would work! And you came back to ask anyway. That's very good form.
    >>  ............................................
    pt  Você nunca disse que ia dar certo! E voltou pra perguntar mesmo assim. Isso é muita classe.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.honest/2
    en  No promises and you turned up regardless. I'd call that a better class of friend.
    >>  ............................................
    pt  Sem promessas e apareceu do mesmo jeito. Eu chamaria isso de um amigo melhor.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.honest/3
    en  You asked! After being honest about the odds. Nobody does both.
    >>  ............................................
    pt  Você perguntou! Depois de ser honesto sobre as chances. Ninguém faz os dois.
    >>  ............................................
```

</details>


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when exclusive `dreams.support` is `pledged`  _(chance -2000)_
- Fires when: RULED OUT when exclusive `dreams.support` is `honest`  _(chance -2000)_
- Does: disposition — warmth +2  _(recorded under topic `dreams.resume.ask_progress`)_
- Does: arc `dreams` — advance to stage 2
- Then opens: `conversations.arc.dreams.resume.followup`
- …where the player's next choices will be: "Keep at it. You'll get there." | "What's the next actual step?" | "It might not happen, you know." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.ask_progress.plain
WHO    VILLAGER — what the player reads after pressing "Any closer to it?"
       spoken on: conversations.arc.dreams.resume.respond, button `ask_progress`
       leaves the player on: conversations.arc.dreams.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.ask_progress.plain.to.dreams`: the villager accepts. Subject `dreams`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.resume.ask_progress.plain/1   [54 chars]
    en  A little. Slowly. But you asking is more than most do.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar já é mais do que a maioria faz.
    >>  ............................................
  dialogue.conversations.dreams.resume.ask_progress.plain/2   [55 chars]
    en  Not much. It's still there, though, which is something.
    >>  ............................................
    pt  Nem tanto. Mas ainda está lá, o que já é algo.
    >>  ............................................
  dialogue.conversations.dreams.resume.ask_progress.plain/3   [46 chars]
    en  Some days closer, some further. Today, closer.
    >>  ............................................
    pt  Alguns dias mais perto, outros mais longe. Hoje, mais perto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do, and I'd not expected to be asked.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz, e eu não esperava ser perguntado.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. Less than I'd hoped by now, which is why I don't bring it up.
    >>  ............................................
    pt  Algo. Menos do que eu esperava a esta altura, e por isso eu não toco no assunto.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Saying it out loud makes it sound smaller than it feels.
    >>  ............................................
    pt  Um pouco. Dizer em voz alta faz parecer menor do que é.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. Slowly is the only speed it has ever gone.
    >>  ............................................
    pt  Um pouco. Devagar. Devagar é a única velocidade que isso já teve.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. It'll take the years it takes and I've made my peace with the number.
    >>  ............................................
    pt  Algo. Vai levar os anos que levar e eu fiz as pazes com o número.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Ask me in a year and the answer will be worth more.
    >>  ............................................
    pt  Um pouco. Me pergunte em um ano e a resposta vai valer mais.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. Not much. It moves when I make it move and not otherwise.
    >>  ............................................
    pt  Algo. Não muito. Anda quando eu faço andar e não de outro jeito.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. I'd not call it progress out loud yet.
    >>  ............................................
    pt  Um pouco. Ainda não chamaria de progresso em voz alta.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. Not much. It moves when I make it move and not otherwise.
    >>  ............................................
    pt  Algo. Não muito. Anda quando eu faço andar e não de outro jeito.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. I'd not call it progress out loud yet.
    >>  ............................................
    pt  Um pouco. Ainda não chamaria de progresso em voz alta.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do, %1$s.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. And the asking helps, which I'd not have predicted.
    >>  ............................................
    pt  Algo. E perguntar ajuda, o que eu não teria previsto.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Ask me again in a month and I might have more to tell you.
    >>  ............................................
    pt  Um pouco. Me pergunte em um mês e eu posso ter mais pra contar.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do, %1$s.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. And the asking helps, which I'd not have predicted.
    >>  ............................................
    pt  Algo. E perguntar ajuda, o que eu não teria previsto.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Ask me again in a month and I might have more to tell you.
    >>  ............................................
    pt  Um pouco. Me pergunte em um mês e eu posso ter mais pra contar.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do, %1$s.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. And the asking helps, which I'd not have predicted.
    >>  ............................................
    pt  Algo. E perguntar ajuda, o que eu não teria previsto.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Ask me again in a month and I might have more to tell you.
    >>  ............................................
    pt  Um pouco. Me pergunte em um mês e eu posso ter mais pra contar.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do, and I'd not expected to be asked.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz, e eu não esperava ser perguntado.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. Less than I'd hoped by now, which is why I don't bring it up.
    >>  ............................................
    pt  Algo. Menos do que eu esperava a esta altura, e por isso eu não toco no assunto.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Saying it out loud makes it sound smaller than it feels.
    >>  ............................................
    pt  Um pouco. Dizer em voz alta faz parecer menor do que é.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. Not much. It moves when I make it move and not otherwise.
    >>  ............................................
    pt  Algo. Não muito. Anda quando eu faço andar e não de outro jeito.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. I'd not call it progress out loud yet.
    >>  ............................................
    pt  Um pouco. Ainda não chamaria de progresso em voz alta.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. Not much. It moves when I make it move and not otherwise.
    >>  ............................................
    pt  Algo. Não muito. Anda quando eu faço andar e não de outro jeito.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. I'd not call it progress out loud yet.
    >>  ............................................
    pt  Um pouco. Ainda não chamaria de progresso em voz alta.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly.
    >>  ............................................
    pt  Um pouco. Devagar.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. You asking is more than most do.
    >>  ............................................
    pt  Algo. Você perguntar é mais do que a maioria faz.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. That's all there is to say yet.
    >>  ............................................
    pt  Um pouco. É tudo que dá pra dizer ainda.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. Slowly is the only speed it has ever gone.
    >>  ............................................
    pt  Um pouco. Devagar. Devagar é a única velocidade que isso já teve.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. It'll take the years it takes and I've made my peace with the number.
    >>  ............................................
    pt  Algo. Vai levar os anos que levar e eu fiz as pazes com o número.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Ask me in a year and the answer will be worth more.
    >>  ............................................
    pt  Um pouco. Me pergunte em um ano e a resposta vai valer mais.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly.
    >>  ............................................
    pt  Um pouco. Devagar.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. You asking is more than most do.
    >>  ............................................
    pt  Algo. Você perguntar é mais do que a maioria faz.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. That's all there is to say yet.
    >>  ............................................
    pt  Um pouco. É tudo que dá pra dizer ainda.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. Slowly is the only speed it has ever gone.
    >>  ............................................
    pt  Um pouco. Devagar. Devagar é a única velocidade que isso já teve.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. It'll take the years it takes and I've made my peace with the number.
    >>  ............................................
    pt  Algo. Vai levar os anos que levar e eu fiz as pazes com o número.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Ask me in a year and the answer will be worth more.
    >>  ............................................
    pt  Um pouco. Me pergunte em um ano e a resposta vai valer mais.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little! Slowly. But you asking is more than most manage, so that's a win.
    >>  ............................................
    pt  Um pouco! Devagar. Mas você perguntar é mais do que a maioria consegue, então é vitória.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some progress. Glacial progress. Progress nonetheless, and I'll take it.
    >>  ............................................
    pt  Algum progresso. Progresso glacial. Progresso mesmo assim, e eu aceito.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit! And now somebody's asked, which counts as an event around here.
    >>  ............................................
    pt  Um pouco! E agora alguém perguntou, o que conta como acontecimento por aqui.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little! Slowly. But you asking is more than most manage, so that's a win.
    >>  ............................................
    pt  Um pouco! Devagar. Mas você perguntar é mais do que a maioria consegue, então é vitória.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some progress. Glacial progress. Progress nonetheless, and I'll take it.
    >>  ............................................
    pt  Algum progresso. Progresso glacial. Progresso mesmo assim, e eu aceito.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit! And now somebody's asked, which counts as an event around here.
    >>  ............................................
    pt  Um pouco! E agora alguém perguntou, o que conta como acontecimento por aqui.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. Slowly is the only speed it has ever gone.
    >>  ............................................
    pt  Um pouco. Devagar. Devagar é a única velocidade que isso já teve.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. It'll take the years it takes and I've made my peace with the number.
    >>  ............................................
    pt  Algo. Vai levar os anos que levar e eu fiz as pazes com o número.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Ask me in a year and the answer will be worth more.
    >>  ............................................
    pt  Um pouco. Me pergunte em um ano e a resposta vai valer mais.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly. But you asking is more than most do, and I'd not expected to be asked.
    >>  ............................................
    pt  Um pouco. Devagar. Mas você perguntar é mais do que a maioria faz, e eu não esperava ser perguntado.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. Less than I'd hoped by now, which is why I don't bring it up.
    >>  ............................................
    pt  Algo. Menos do que eu esperava a esta altura, e por isso eu não toco no assunto.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. Saying it out loud makes it sound smaller than it feels.
    >>  ............................................
    pt  Um pouco. Dizer em voz alta faz parecer menor do que é.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little. Slowly.
    >>  ............................................
    pt  Um pouco. Devagar.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some. You asking is more than most do.
    >>  ............................................
    pt  Algo. Você perguntar é mais do que a maioria faz.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit. That's all there is to say yet.
    >>  ............................................
    pt  Um pouco. É tudo que dá pra dizer ainda.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little! Slowly. But you asking is more than most manage, so that's a win.
    >>  ............................................
    pt  Um pouco! Devagar. Mas você perguntar é mais do que a maioria consegue, então é vitória.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some progress. Glacial progress. Progress nonetheless, and I'll take it.
    >>  ............................................
    pt  Algum progresso. Progresso glacial. Progresso mesmo assim, e eu aceito.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit! And now somebody's asked, which counts as an event around here.
    >>  ............................................
    pt  Um pouco! E agora alguém perguntou, o que conta como acontecimento por aqui.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.plain/1
    en  A little! Slowly. But you asking is more than most manage, so that's a win.
    >>  ............................................
    pt  Um pouco! Devagar. Mas você perguntar é mais do que a maioria consegue, então é vitória.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.plain/2
    en  Some progress. Glacial progress. Progress nonetheless, and I'll take it.
    >>  ............................................
    pt  Algum progresso. Progresso glacial. Progresso mesmo assim, e eu aceito.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.ask_progress.plain/3
    en  A bit! And now somebody's asked, which counts as an event around here.
    >>  ............................................
    pt  Um pouco! E agora alguém perguntou, o que conta como acontecimento por aqui.
    >>  ............................................
```

</details>


### Button `offer_step` — "What's the first step? I'll help with that."

*stance family `practical_help` · tone `plain` · answers the beat(s) `dreams.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.resume.offer_step` — accepted phrasings: "what is the first step"; "let us start with one step"; "the first step, i will help"
  - the message must contain one of: `step`, `first`
  - scored words: `step`(1.5), `first`(1.2), `help`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.respond.offer_step
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.dreams.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.dreams.resume.respond.offer_step   [43 chars]
    en  What's the first step? I'll help with that.
    >>  ............................................
    pt  Qual é o primeiro passo? Eu ajudo com ele.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `dreams.resume.offer_step`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `dreams.resume.offer_step`)_
- Does: arc `dreams` — advance to stage 2
- Then opens: `conversations.arc.dreams.resume.followup`
- …where the player's next choices will be: "Keep at it. You'll get there." | "What's the next actual step?" | "It might not happen, you know." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.offer_step
WHO    VILLAGER — what the player reads after pressing "What's the first step? I'll help with that."
       spoken on: conversations.arc.dreams.resume.respond, button `offer_step`
       leaves the player on: conversations.arc.dreams.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.offer_step.to.dreams`: the villager accepts. Subject `dreams`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.resume.offer_step/1   [72 chars]
    en  The first step. Nobody breaks it down like that. ...Alright, here it is.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. ...Certo, aqui está.
    >>  ............................................
  dialogue.conversations.dreams.resume.offer_step/2   [74 chars]
    en  You'd help with the small dull part? That's the part that never gets done.
    >>  ............................................
    pt  Você ajudaria com a parte pequena e chata? É a parte que nunca é feita.
    >>  ............................................
  dialogue.conversations.dreams.resume.offer_step/3   [58 chars]
    en  Noted. One step. I can manage one step with company, %1$s.
    >>  ............................................
    pt  Anotado. Um passo. Consigo dar um passo com companhia, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that, %1$s. The whole of it always defeated me.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim, %1$s. A coisa inteira sempre me derrotava.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. I could manage one. I've never been able to manage all of it, and that's why it sat.
    >>  ............................................
    pt  Um passo. Um eu consigo. Nunca consegui a coisa toda, e por isso ficou parada.
    >>  ............................................
  anxious.dialogue.conversations.dreams.resume.offer_step/3
    en  You've made it small enough that I'm frightened of it in a manageable way now.
    >>  ............................................
    pt  Você deixou pequeno o bastante pra eu ter medo de um jeito administrável agora.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. It'll take as long as it takes from there.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. Dali em diante leva o tempo que levar.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.offer_step/2
    en  One step, then another, in a year or two. That's the honest shape of it.
    >>  ............................................
    pt  Um passo, depois outro, em um ano ou dois. É o formato honesto disso.
    >>  ............................................
  athletic.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Aye. Big things only ever get done in small pieces.
    >>  ............................................
    pt  Dividir. É. Coisas grandes só se fazem em pedaços pequenos.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. Alright, here it is.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. Está bem, aqui vai.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. Right. That I can do, which the whole of it never was.
    >>  ............................................
    pt  Um passo. Certo. Isso eu consigo, o que a coisa inteira nunca foi.
    >>  ............................................
  confident.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Yes. I've been staring at the whole thing for a year.
    >>  ............................................
    pt  Dividir. Sim. Eu venho encarando a coisa inteira faz um ano.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. Alright, here it is.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. Está bem, aqui vai.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. Right. That I can do, which the whole of it never was.
    >>  ............................................
    pt  Um passo. Certo. Isso eu consigo, o que a coisa inteira nunca foi.
    >>  ............................................
  crabby.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Yes. I've been staring at the whole thing for a year.
    >>  ............................................
    pt  Dividir. Sim. Eu venho encarando a coisa inteira faz um ano.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that, %1$s. Alright — here it is.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim, %1$s. Está bem — aqui vai.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.offer_step/2
    en  One step, you say. Sit down, then, because I'd like to say it out loud to somebody.
    >>  ............................................
    pt  Um passo, você diz. Então sente-se, porque eu queria dizer em voz alta pra alguém.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.resume.offer_step/3
    en  You've made it a thing I could start. That's more use than encouragement has ever been.
    >>  ............................................
    pt  Você transformou isso em algo que eu poderia começar. Serve mais que qualquer incentivo.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that, %1$s. Alright — here it is.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim, %1$s. Está bem — aqui vai.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.offer_step/2
    en  One step, you say. Sit down, then, because I'd like to say it out loud to somebody.
    >>  ............................................
    pt  Um passo, você diz. Então sente-se, porque eu queria dizer em voz alta pra alguém.
    >>  ............................................
  flirty.dialogue.conversations.dreams.resume.offer_step/3
    en  You've made it a thing I could start. That's more use than encouragement has ever been.
    >>  ............................................
    pt  Você transformou isso em algo que eu poderia começar. Serve mais que qualquer incentivo.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that, %1$s. Alright — here it is.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim, %1$s. Está bem — aqui vai.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.offer_step/2
    en  One step, you say. Sit down, then, because I'd like to say it out loud to somebody.
    >>  ............................................
    pt  Um passo, você diz. Então sente-se, porque eu queria dizer em voz alta pra alguém.
    >>  ............................................
  friendly.dialogue.conversations.dreams.resume.offer_step/3
    en  You've made it a thing I could start. That's more use than encouragement has ever been.
    >>  ............................................
    pt  Você transformou isso em algo que eu poderia começar. Serve mais que qualquer incentivo.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that, %1$s. The whole of it always defeated me.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim, %1$s. A coisa inteira sempre me derrotava.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. I could manage one. I've never been able to manage all of it, and that's why it sat.
    >>  ............................................
    pt  Um passo. Um eu consigo. Nunca consegui a coisa toda, e por isso ficou parada.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.resume.offer_step/3
    en  You've made it small enough that I'm frightened of it in a manageable way now.
    >>  ............................................
    pt  Você deixou pequeno o bastante pra eu ter medo de um jeito administrável agora.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. Alright, here it is.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. Está bem, aqui vai.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. Right. That I can do, which the whole of it never was.
    >>  ............................................
    pt  Um passo. Certo. Isso eu consigo, o que a coisa inteira nunca foi.
    >>  ............................................
  greedy.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Yes. I've been staring at the whole thing for a year.
    >>  ............................................
    pt  Dividir. Sim. Eu venho encarando a coisa inteira faz um ano.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. Alright, here it is.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. Está bem, aqui vai.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. Right. That I can do, which the whole of it never was.
    >>  ............................................
    pt  Um passo. Certo. Isso eu consigo, o que a coisa inteira nunca foi.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Yes. I've been staring at the whole thing for a year.
    >>  ............................................
    pt  Dividir. Sim. Eu venho encarando a coisa inteira faz um ano.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. ...Alright.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. ...Está bem.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. That I can hold.
    >>  ............................................
    pt  Um passo. Isso eu consigo segurar.
    >>  ............................................
  introverted.dialogue.conversations.dreams.resume.offer_step/3
    en  Right. Here it is, then.
    >>  ............................................
    pt  Certo. Aqui vai, então.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. It'll take as long as it takes from there.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. Dali em diante leva o tempo que levar.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.offer_step/2
    en  One step, then another, in a year or two. That's the honest shape of it.
    >>  ............................................
    pt  Um passo, depois outro, em um ano ou dois. É o formato honesto disso.
    >>  ............................................
  lazy.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Aye. Big things only ever get done in small pieces.
    >>  ............................................
    pt  Dividir. É. Coisas grandes só se fazem em pedaços pequenos.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. ...Alright.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. ...Está bem.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. That I can hold.
    >>  ............................................
    pt  Um passo. Isso eu consigo segurar.
    >>  ............................................
  odd.dialogue.conversations.dreams.resume.offer_step/3
    en  Right. Here it is, then.
    >>  ............................................
    pt  Certo. Aqui vai, então.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. It'll take as long as it takes from there.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. Dali em diante leva o tempo que levar.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.offer_step/2
    en  One step, then another, in a year or two. That's the honest shape of it.
    >>  ............................................
    pt  Um passo, depois outro, em um ano ou dois. É o formato honesto disso.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Aye. Big things only ever get done in small pieces.
    >>  ............................................
    pt  Dividir. É. Coisas grandes só se fazem em pedaços pequenos.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step! Nobody breaks it down like that. Right — here it is, then.
    >>  ............................................
    pt  O primeiro passo! Ninguém divide assim. Certo — aqui vai, então.
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. ONE. Why has nobody said that to me before?
    >>  ............................................
    pt  Um passo. UM. Por que ninguém me disse isso antes?
    >>  ............................................
  peppy.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Genius. Infuriating, obvious genius.
    >>  ............................................
    pt  Dividir. Genial. Uma genialidade irritante e óbvia.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step! Nobody breaks it down like that. Right — here it is, then.
    >>  ............................................
    pt  O primeiro passo! Ninguém divide assim. Certo — aqui vai, então.
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. ONE. Why has nobody said that to me before?
    >>  ............................................
    pt  Um passo. UM. Por que ninguém me disse isso antes?
    >>  ............................................
  playful.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Genius. Infuriating, obvious genius.
    >>  ............................................
    pt  Dividir. Genial. Uma genialidade irritante e óbvia.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. It'll take as long as it takes from there.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. Dali em diante leva o tempo que levar.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.offer_step/2
    en  One step, then another, in a year or two. That's the honest shape of it.
    >>  ............................................
    pt  Um passo, depois outro, em um ano ou dois. É o formato honesto disso.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Aye. Big things only ever get done in small pieces.
    >>  ............................................
    pt  Dividir. É. Coisas grandes só se fazem em pedaços pequenos.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that, %1$s. The whole of it always defeated me.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim, %1$s. A coisa inteira sempre me derrotava.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. I could manage one. I've never been able to manage all of it, and that's why it sat.
    >>  ............................................
    pt  Um passo. Um eu consigo. Nunca consegui a coisa toda, e por isso ficou parada.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.resume.offer_step/3
    en  You've made it small enough that I'm frightened of it in a manageable way now.
    >>  ............................................
    pt  Você deixou pequeno o bastante pra eu ter medo de um jeito administrável agora.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step. Nobody breaks it down like that. ...Alright.
    >>  ............................................
    pt  O primeiro passo. Ninguém divide assim. ...Está bem.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. That I can hold.
    >>  ............................................
    pt  Um passo. Isso eu consigo segurar.
    >>  ............................................
  shy.dialogue.conversations.dreams.resume.offer_step/3
    en  Right. Here it is, then.
    >>  ............................................
    pt  Certo. Aqui vai, então.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step! Nobody breaks it down like that. Right — here it is, then.
    >>  ............................................
    pt  O primeiro passo! Ninguém divide assim. Certo — aqui vai, então.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. ONE. Why has nobody said that to me before?
    >>  ............................................
    pt  Um passo. UM. Por que ninguém me disse isso antes?
    >>  ............................................
  upbeat.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Genius. Infuriating, obvious genius.
    >>  ............................................
    pt  Dividir. Genial. Uma genialidade irritante e óbvia.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.offer_step/1
    en  The first step! Nobody breaks it down like that. Right — here it is, then.
    >>  ............................................
    pt  O primeiro passo! Ninguém divide assim. Certo — aqui vai, então.
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.offer_step/2
    en  One step. ONE. Why has nobody said that to me before?
    >>  ............................................
    pt  Um passo. UM. Por que ninguém me disse isso antes?
    >>  ............................................
  witty.dialogue.conversations.dreams.resume.offer_step/3
    en  Break it down. Genius. Infuriating, obvious genius.
    >>  ............................................
    pt  Dividir. Genial. Uma genialidade irritante e óbvia.
    >>  ............................................
```

</details>


### Button `doubt` — "Still on about that?"

*stance family `challenge` · tone `blunt` · answers the beat(s) `dreams.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.resume.doubt` — accepted phrasings: "still on about that"; "are you still on that"; "that again"
  - the message must contain one of: `still`, `again`
  - scored words: `still`(1.5), `on`(0.3), `again`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.respond.doubt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.dreams.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.dreams.resume.respond.doubt   [20 chars]
    en  Still on about that?
    >>  ............................................
    pt  Ainda com isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `dreams.resume.doubt`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `dreams.resume.doubt`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.doubt
WHO    VILLAGER — what the player reads after pressing "Still on about that?"
       spoken on: conversations.arc.dreams.resume.respond, button `doubt`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.doubt.terminal`: the villager accepts. Subject `dreams.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.resume.doubt/1   [57 chars]
    en  ...Still, yes. That's rather how wanting something works.
    >>  ............................................
    pt  ...Ainda, sim. É mais ou menos assim que querer algo funciona.
    >>  ............................................
  dialogue.conversations.dreams.resume.doubt/2   [48 chars]
    en  You asked me about it once. I thought you cared.
    >>  ............................................
    pt  Você me perguntou sobre isso uma vez. Achei que você se importava.
    >>  ............................................
  dialogue.conversations.dreams.resume.doubt/3   [67 chars]
    en  So I've found. Still on about it. I'll keep it to myself in future.
    >>  ............................................
    pt  Foi o que eu vi. Ainda com isso. Vou guardar para mim no futuro.
    >>  ............................................
```


### Button `leave` — "Keep at it."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.revisit.opens` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.dreams.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.dreams.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.dreams.resume.respond.leave   [11 chars]
    en  Keep at it.
    >>  ............................................
    pt  Continue nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.resume.leave
WHO    VILLAGER — what the player reads after pressing "Keep at it."
       spoken on: conversations.arc.dreams.resume.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.resume.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.resume.leave/1   [44 chars]
    en  I will. Thank you for remembering it at all.
    >>  ............................................
    pt  Vou continuar. Obrigado por ter lembrado.
    >>  ............................................
  dialogue.conversations.dreams.resume.leave/2   [30 chars]
    en  True enough. Off you go, %1$s.
    >>  ............................................
    pt  Bem verdade. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.resume.leave/3   [38 chars]
    en  So be it. Back to the possible things.
    >>  ............................................
    pt  Que seja. De volta às coisas possíveis.
    >>  ............................................
```

---


## `conversations.dreams`

**Reached from 1 route(s):** `conversations.cat.personal` / `dreams`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.first` — e.g. "Don't laugh. I want to see the ocean once. Just once. Smell the salt."


```text
POOL   dialogue key: dialogue.conversations.dreams
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.dreams
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.dreams   [24 chars]
    en  What do you dream about?
    >>  ............................................
    pt  Com o que você sonha?
    >>  ............................................
```


### Button `encourage` — "You should chase that."

*stance family `encouragement` · tone `plain` · answers the beat(s) `dreams.first.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.encourage` — accepted phrasings: "you should chase"; "chase that"; "go for it"; "worth pursuing"
  - the message must contain one of: `chase`, `pursue`, `worth`
  - scored words: `chase`(1.5), `pursue`(1.2), `worth`(0.8), `should`(0.4)

```text
POOL   dialogue key: dialogue.conversations.dreams.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.dreams
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.dreams.encourage   [22 chars]
    en  You should chase that.
    >>  ............................................
    pt  Você devia correr atrás disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `5`

- Fires when: weighted +4 when the personality is `peppy`
- Fires when: weighted +4 when the mood is `happy`
- Does: **hearts (raw MCA `positive` field)** = 5
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.encourage.glad
WHO    VILLAGER — what the player reads after pressing "You should chase that."
       spoken on: conversations.dreams, button `encourage`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.encourage.glad.terminal`: the villager accepts. Subject `dreams.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.encourage.glad/1   [61 chars]
    en  You think so? Then maybe I'll actually do it. Watch me, %1$s.
    >>  ............................................
    pt  Você acha? Então talvez eu faça isso mesmo. Só me observe, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.encourage.glad/2   [50 chars]
    en  Careful — encourage me and I might drag you along.
    >>  ............................................
    pt  Cuidado — me incentiva e eu ainda te arrasto junto.
    >>  ............................................
  dialogue.conversations.dreams.encourage.glad/3   [53 chars]
    en  Say that again sometime. It works better than coffee.
    >>  ............................................
    pt  Repete isso outra hora. Funciona melhor que café.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: weighted +5 when the personality is `gloomy`
- Fires when: weighted +3 when the mood is `sad`
- Does: **hearts (raw MCA `positive` field)** = 3
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.encourage.doubt
WHO    VILLAGER — what the player reads after pressing "You should chase that."
       spoken on: conversations.dreams, button `encourage`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.encourage.doubt.terminal`: the villager accepts. Subject `dreams.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.encourage.doubt/1   [64 chars]
    en  That's kind. But dreams cost coin, and mine's mostly spoken for.
    >>  ............................................
    pt  Que gentileza. Mas sonho custa moeda, e a minha já tem dono.
    >>  ............................................
  dialogue.conversations.dreams.encourage.doubt/2   [81 chars]
    en  You sound like my grandmother. She was usually right, which is the annoying part.
    >>  ............................................
    pt  Você fala igual à minha avó. Ela costumava ter razão, que é a parte irritante.
    >>  ............................................
  dialogue.conversations.dreams.encourage.doubt/3   [64 chars]
    en  Easy for you to say — you've seen the world past the wheat line.
    >>  ............................................
    pt  Fácil pra você falar — você já viu o mundo além da linha do trigo.
    >>  ............................................
```


### Button `ask_more` — "Tell me more about it."

*stance family `curiosity` · tone `plain` · answers the beat(s) `dreams.first.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.ask_more` — accepted phrasings: "tell me more"; "more about it"; "say more"
  - the message must contain one of: `more`, `elaborate`, `detail`
  - scored words: `more`(1.2), `elaborate`(1.0), `detail`(0.8), `tell`(0.4)

```text
POOL   dialogue key: dialogue.conversations.dreams.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.dreams
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.dreams.ask_more   [22 chars]
    en  Tell me more about it.
    >>  ............................................
    pt  Me conta mais sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `5`

- Fires when: weighted +4 when the personality is `upbeat`
- Fires when: weighted +4 when the personality is `confident`
- Does: **hearts (raw MCA `positive` field)** = 4
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.more
WHO    VILLAGER — what the player reads after pressing "Tell me more about it."
       spoken on: conversations.dreams, button `ask_more`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.more.terminal`: the villager accepts. Subject `dreams.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.more/1   [90 chars]
    en  More? Alright. In the dream there's a porch, and nobody needs anything from me until noon.
    >>  ............................................
    pt  Mais? Tá bom. No sonho tem uma varanda, e ninguém precisa de nada de mim até o meio-dia.
    >>  ............................................
  dialogue.conversations.dreams.more/2   [92 chars]
    en  There's a second part I don't tell everyone. There's a dog in it. A big, useless, happy one.
    >>  ............................................
    pt  Tem uma segunda parte que eu não conto pra todo mundo. Tem um cachorro nela. Grande, inútil e feliz.
    >>  ............................................
  dialogue.conversations.dreams.more/3   [78 chars]
    en  Details? In the dream nobody knocks on my door before breakfast. Pure fantasy.
    >>  ............................................
    pt  Detalhes? No sonho ninguém bate na minha porta antes do café da manhã. Pura fantasia.
    >>  ............................................
```


### Button `back` — "Let's talk about something else."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.first.opens` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.dreams.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.dreams
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.dreams.back   [32 chars]
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


## `conversations.scene.dreams.followup`

**Reached from 5 route(s):** `conversations.scene.dreams.the_named_one.respond` / `ask_the_next_step`; `conversations.scene.dreams.the_named_one.respond` / `back_them`; `conversations.scene.dreams.the_named_one.respond` / `just_listen`; `conversations.scene.dreams.the_small_version.respond` / `ask_which_two`; `conversations.scene.dreams.the_small_version.respond` / `wish_them_luck`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.dreams.the_named_one.explained` — e.g. "A dull one. It always is. The next step in anything worth doing is four months of something nobody would watch."
- `conversations.scene.dreams.the_named_one.resolved` — e.g. "This week. You have made it a thing I have said to somebody, which is the only trick that has ever worked on me."
- `conversations.scene.dreams.the_named_one.steadied` — e.g. "So am I, and I notice you did not immediately tell me how to do it, which is why I said it to you."
- `conversations.scene.dreams.the_small_version.answered` — e.g. "The roof, and the fact that I am the only person who knows how to do one part of my job."
- `conversations.scene.dreams.the_small_version.thanked` — e.g. "One of them is likely and one of them is up to me, so I shall report back in the spring on the second."


```text
POOL   dialogue key: dialogue.conversations.scene.dreams.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.dreams.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.dreams.followup   [23 chars]
    en  Anything else about it?
    >>  ............................................
    pt  Mais alguma coisa sobre isso?
    >>  ............................................
```


### Button `leave` — "We'll leave it there for now."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:dreams.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.dreams.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.dreams.followup.leave   [29 chars]
    en  We'll leave it there for now.
    >>  ............................................
    pt  Vamos deixar assim por ora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave it there for now."
       spoken on: conversations.scene.dreams.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.scene.leaving`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.dreams.the_named_one.respond / leave; conversations.scene.dreams.the_small_version.respond / leave
```

```text
  dialogue.conversations.scene.dreams.leaving/1   [23 chars]
    en  That is enough of that.
    >>  ............................................
    pt  Já chega disso.
    >>  ............................................
  dialogue.conversations.scene.dreams.leaving/2   [23 chars]
    en  Right. Back to the day.
    >>  ............................................
    pt  Certo. De volta ao dia.
    >>  ............................................
  dialogue.conversations.scene.dreams.leaving/3   [33 chars]
    en  I have said more than I meant to.
    >>  ............................................
    pt  Falei mais do que pretendia.
    >>  ............................................
```

---


## `conversations.scene.dreams.the_named_one.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `dreams`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.dreams.the_named_one` — e.g. "There is a thing I want and I have said it out loud twice, counting now, and both times to somebody I trusted."


```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.dreams.the_named_one.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.dreams.the_named_one.respond   [14 chars]
    en  What you want.
    >>  ............................................
    pt  O que você quer.
    >>  ............................................
```


### Button `ask_the_next_step` — "What's the next step?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `dreams.the_named_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.dreams.the_named_one.ask_the_next_step` — accepted phrasings: "whats the next step"; "what is the next step"; "what comes next for that"
  - the message must contain one of: `step`, `next`
  - scored words: `step`(1.8), `next`(1.8), `whats`(0.8), `comes`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one.respond.ask_the_next_step
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.dreams.the_named_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.dreams.the_named_one.respond.ask_the_next_step   [21 chars]
    en  What's the next step?
    >>  ............................................
    pt  Qual é o próximo passo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `dreams.ambition`)_
- Does: session `turn`
- Then opens: `conversations.scene.dreams.followup`
- …where the player's next choices will be: "We'll leave it there for now."

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one.explained
WHO    VILLAGER — what the player reads after pressing "What's the next step?"
       spoken on: conversations.scene.dreams.the_named_one.respond, button `ask_the_next_step`
       leaves the player on: conversations.scene.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.the_named_one.open.explained`: the villager explains. Subject `dreams.ambition`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:dreams` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.dreams.the_named_one.explained/1   [111 chars]
    en  A dull one. It always is. The next step in anything worth doing is four months of something nobody would watch.
    >>  ............................................
    pt  Um passo chato. Sempre é. O próximo passo de qualquer coisa que valha a pena são quatro meses de algo que ninguém assistiria.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_named_one.explained/2   [135 chars]
    en  Asking somebody who already does it whether they will let me stand behind them for a season. I have been putting that off since spring.
    >>  ............................................
    pt  Pedir a alguém que já faz isso para me deixar ficar atrás dela por uma estação. Venho adiando desde a primavera.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_named_one.explained/3   [117 chars]
    en  I have written it down in three parts, and the first part is finished, and I look at the list more than I work on it.
    >>  ............................................
    pt  Escrevi em três partes, e a primeira está pronta, e eu olho para a lista mais do que trabalho nela.
    >>  ............................................
```


### Button `back_them` — "Go and ask them this week."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `dreams.the_named_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.dreams.the_named_one.back_them` — accepted phrasings: "go and ask them this week"; "go and ask them this week"; "make the ask this week"
  - the message must contain one of: `ask`, `week`
  - scored words: `ask`(1.8), `week`(1.8), `make`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one.respond.back_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.dreams.the_named_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.dreams.the_named_one.respond.back_them   [26 chars]
    en  Go and ask them this week.
    >>  ............................................
    pt  Vá pedir a eles esta semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.dreams.backed`, budget `deep`, replay policy `once`
- Does: disposition — trust +3, warmth +3  _(recorded under topic `dreams.ambition`)_
- Does: session `turn`
- Then opens: `conversations.scene.dreams.followup`
- …where the player's next choices will be: "We'll leave it there for now."

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one.resolved
WHO    VILLAGER — what the player reads after pressing "Go and ask them this week."
       spoken on: conversations.scene.dreams.the_named_one.respond, button `back_them`
       leaves the player on: conversations.scene.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.the_named_one.open.resolved`: the villager accepts. Subject `dreams.ambition`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:dreams` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.dreams.the_named_one.resolved/1   [112 chars]
    en  This week. You have made it a thing I have said to somebody, which is the only trick that has ever worked on me.
    >>  ............................................
    pt  Esta semana. Você transformou isso numa coisa que eu disse a alguém, que é o único truque que já funcionou comigo.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_named_one.resolved/2   [115 chars]
    en  Right. The worst answer is no, and no leaves me exactly where I am, which is where I have been since spring anyway.
    >>  ............................................
    pt  Certo. A pior resposta é não, e não me deixa exatamente onde estou, que é onde estou desde a primavera de qualquer forma.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_named_one.resolved/3   [121 chars]
    en  I will. And if I have not by the weekend, ask me about it, and be unpleasant about it, because that is what I would want.
    >>  ............................................
    pt  Vou. E se eu não tiver ido até o fim de semana, me pergunte, e seja chata a respeito, porque é o que eu ia querer.
    >>  ............................................
```


### Button `just_listen` — "I'm glad you said it out loud."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `dreams.the_named_one.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.dreams.the_named_one.just_listen` — accepted phrasings: "im glad you said it out loud"; "i am glad you said it out loud"; "saying it aloud matters"
  - the message must contain one of: `loud`, `aloud`, `glad`
  - scored words: `loud`(1.8), `aloud`(1.8), `glad`(1.8), `said`(0.8), `out`(0.8), `saying`(0.8), `matters`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one.respond.just_listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.dreams.the_named_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.dreams.the_named_one.respond.just_listen   [30 chars]
    en  I'm glad you said it out loud.
    >>  ............................................
    pt  Que bom que você disse em voz alta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +2  _(recorded under topic `dreams.ambition`)_
- Does: session `turn`
- Then opens: `conversations.scene.dreams.followup`
- …where the player's next choices will be: "We'll leave it there for now."

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one.steadied
WHO    VILLAGER — what the player reads after pressing "I'm glad you said it out loud."
       spoken on: conversations.scene.dreams.the_named_one.respond, button `just_listen`
       leaves the player on: conversations.scene.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.the_named_one.open.steadied`: the villager accepts. Subject `dreams.ambition`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:dreams` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.dreams.the_named_one.steadied/1   [98 chars]
    en  So am I, and I notice you did not immediately tell me how to do it, which is why I said it to you.
    >>  ............................................
    pt  Eu também, e reparei que você não me disse na hora como fazer, e é por isso que eu contei a você.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_named_one.steadied/2   [98 chars]
    en  It changes shape when it is said. Smaller and more possible, both at once, which I did not expect.
    >>  ............................................
    pt  Muda de formato quando é dito. Menor e mais possível ao mesmo tempo, o que eu não esperava.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_named_one.steadied/3   [110 chars]
    en  Thank you. I will probably be embarrassed about this tomorrow and I would like it noted that I said it anyway.
    >>  ............................................
    pt  Obrigada. Provavelmente vou ficar sem graça com isso amanhã e gostaria que ficasse registrado que eu disse mesmo assim.
    >>  ............................................
```


### Button `leave` — "Thanks for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.the_named_one.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.dreams.the_named_one.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.dreams.the_named_one.respond.leave   [22 chars]
    en  Thanks for telling me.
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
POOL   dialogue key: dialogue.conversations.scene.dreams.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for telling me."
       spoken on: conversations.scene.dreams.the_named_one.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.scene.leaving`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.dreams.followup / leave; conversations.scene.dreams.the_small_version.respond / leave
```

> Written out in full under **`conversations.scene.dreams.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.dreams.the_small_version.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `dreams`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.dreams.the_small_version` — e.g. "A better roof and a quieter winter. That is what I tell people and it happens to be true, just not all of it."


```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_small_version.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.dreams.the_small_version.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.dreams.the_small_version.respond   [22 chars]
    en  Anything you're after?
    >>  ............................................
    pt  Está atrás de alguma coisa?
    >>  ............................................
```


### Button `ask_which_two` — "Which two things?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `dreams.the_small_version.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.dreams.the_small_version.ask_which_two` — accepted phrasings: "which two things"; "which two things"; "what are the two you would fix"
  - the message must contain one of: `two`, `fix`
  - scored words: `two`(1.8), `fix`(1.8), `which`(0.8), `things`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_small_version.respond.ask_which_two
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.dreams.the_small_version.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.dreams.the_small_version.respond.ask_which_two   [17 chars]
    en  Which two things?
    >>  ............................................
    pt  Quais duas coisas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `dreams.modest`)_
- Does: session `turn`
- Then opens: `conversations.scene.dreams.followup`
- …where the player's next choices will be: "We'll leave it there for now."

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_small_version.answered
WHO    VILLAGER — what the player reads after pressing "Which two things?"
       spoken on: conversations.scene.dreams.the_small_version.respond, button `ask_which_two`
       leaves the player on: conversations.scene.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.the_small_version.open.answered`: the villager explains. Subject `dreams.modest`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:dreams` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.dreams.the_small_version.answered/1   [88 chars]
    en  The roof, and the fact that I am the only person who knows how to do one part of my job.
    >>  ............................................
    pt  O telhado, e o fato de eu ser a única pessoa que sabe fazer uma parte do meu trabalho.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_small_version.answered/2   [111 chars]
    en  One is a thing I can buy and one is a thing I have to be braver about, and I would rather talk about the first.
    >>  ............................................
    pt  Uma é uma coisa que dá para comprar e a outra é uma coisa em que eu preciso ser mais corajosa, e eu prefiro falar da primeira.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_small_version.answered/3   [117 chars]
    en  Nothing you could help with, which I say kindly. Some things only get fixed by the person who has been avoiding them.
    >>  ............................................
    pt  Nada em que você possa ajudar, e digo isso com carinho. Algumas coisas só se resolvem por quem vem evitando elas.
    >>  ............................................
```


### Button `wish_them_luck` — "I hope you get both."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `dreams.the_small_version.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.dreams.the_small_version.wish_them_luck` — accepted phrasings: "i hope you get both"; "i hope you get both"; "hope both of those come off"
  - the message must contain one of: `hope`, `both`
  - scored words: `hope`(1.8), `both`(1.8), `get`(0.8), `those`(0.8), `come`(0.8), `off`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_small_version.respond.wish_them_luck
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.dreams.the_small_version.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.dreams.the_small_version.respond.wish_them_luck   [20 chars]
    en  I hope you get both.
    >>  ............................................
    pt  Espero que consiga as duas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `dreams.modest`)_
- Does: session `turn`
- Then opens: `conversations.scene.dreams.followup`
- …where the player's next choices will be: "We'll leave it there for now."

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_small_version.thanked
WHO    VILLAGER — what the player reads after pressing "I hope you get both."
       spoken on: conversations.scene.dreams.the_small_version.respond, button `wish_them_luck`
       leaves the player on: conversations.scene.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.the_small_version.open.thanked`: the villager accepts. Subject `dreams.modest`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:dreams` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.dreams.the_small_version.thanked/1   [102 chars]
    en  One of them is likely and one of them is up to me, so I shall report back in the spring on the second.
    >>  ............................................
    pt  Uma é provável e a outra depende de mim, então eu dou notícias na primavera sobre a segunda.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_small_version.thanked/2   [94 chars]
    en  Thank you. That is a decent thing to say to somebody who has just given you the short version.
    >>  ............................................
    pt  Obrigada. É uma coisa decente de se dizer a quem acabou de te dar a versão curta.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_small_version.thanked/3   [102 chars]
    en  If I get one I will call it a good year, which is the sort of arithmetic that keeps a person cheerful.
    >>  ............................................
    pt  Se eu conseguir uma, já chamo de bom ano, que é o tipo de conta que mantém a pessoa animada.
    >>  ............................................
```


### Button `leave` — "Thanks for telling me."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.the_small_version.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_small_version.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.dreams.the_small_version.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.dreams.the_small_version.respond.leave   [22 chars]
    en  Thanks for telling me.
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
POOL   dialogue key: dialogue.conversations.scene.dreams.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for telling me."
       spoken on: conversations.scene.dreams.the_small_version.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.scene.leaving`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.dreams.followup / leave; conversations.scene.dreams.the_named_one.respond / leave
```

> Written out in full under **`conversations.scene.dreams.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.dreams.close`

**Reached from 4 route(s):** `conversations.arc.dreams.resume.followup` / `encourage`; `conversations.arc.dreams.resume.followup` / `practical`; `conversations.arc.dreams.resume.followup` / `honest`; `conversations.topic.dreams.followup` / `pledge_help`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.followup.pledge_help` — e.g. "...You mean that? Then it's the first time it's been more than mine."
- `conversations.dreams.resume.followup.encourage` — e.g. "You say that like it's obvious. It isn't, but I'll borrow your version for a bit."
- `conversations.dreams.resume.followup.honest` — e.g. "It might not. I know. I'd rather hear that from you than from myself at three in the morning."
- `conversations.dreams.resume.followup.practical` — e.g. "The next step. Nobody asks that. They ask if I'm still doing it, which isn't the same question."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.close   [22 chars]
    en  Anyway. It's said now.
    >>  ............................................
    pt  Enfim. Já está dito.
    >>  ............................................
```


### Button `thank` — "Thank you for telling me."

*stance family `candor` · tone `gentle` · answers the beat(s) `dreams.followup.pledge_help.to.dreams`, `dreams.resume.followup.encourage.to.dreams`, `dreams.resume.followup.honest.to.dreams`, `dreams.resume.followup.practical.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.close.thank` — accepted phrasings: "thank you for telling me"; "thank you for the dream"; "i am grateful you told me"
  - the message must contain one of: `thank`, `telling`
  - scored words: `thank`(1.5), `telling`(1.2), `dream`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.close.thank   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.close.thank`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, trust +1  _(recorded under topic `dreams.close.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.close.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.dreams.close, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.close.thank.terminal`: the villager accepts. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.close.thank/1   [60 chars]
    en  Don't thank me yet. Thank me if it ever turns into anything.
    >>  ............................................
    pt  Não me agradeça ainda. Me agradeça se um dia isso virar alguma coisa.
    >>  ............................................
  dialogue.conversations.dreams.close.thank/2   [80 chars]
    en  You're welcome. Saying it to someone makes it feel a bit more like a plan, %1$s.
    >>  ............................................
    pt  De nada. Dizer para alguém faz parecer um pouco mais com um plano, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.close.thank/3   [95 chars]
    en  True enough, well. It cost me nothing to say and rather a lot to admit. Both of those are true.
    >>  ............................................
    pt  Bem verdade, bom. Não me custou nada dizer e me custou bastante admitir. As duas coisas são verdade.
    >>  ............................................
```


### Button `say_means` — "That took something to say."

*stance family `candor` · tone `gentle` · answers the beat(s) `dreams.followup.pledge_help.to.dreams`, `dreams.resume.followup.encourage.to.dreams`, `dreams.resume.followup.honest.to.dreams`, `dreams.resume.followup.practical.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.close.say_means` — accepted phrasings: "that took something to say"; "that was brave of you"; "that took courage"
  - the message must contain one of: `took`, `brave`, `courage`
  - scored words: `took`(1.5), `brave`(1.2), `courage`(1.5), `dream`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.close.say_means   [27 chars]
    en  That took something to say.
    >>  ............................................
    pt  Falar isso exigiu coragem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.close.say_means`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `dreams.close.say_means`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.close.say_means
WHO    VILLAGER — what the player reads after pressing "That took something to say."
       spoken on: conversations.topic.dreams.close, button `say_means`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.close.say_means.terminal`: the villager accepts. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.close.say_means/1   [80 chars]
    en  ...It did. Wanting a thing out loud is how people find out you might fail at it.
    >>  ............................................
    pt  ...Exigiu. Querer uma coisa em voz alta é como as pessoas descobrem que você pode falhar.
    >>  ............................................
  dialogue.conversations.dreams.close.say_means/2   [76 chars]
    en  Saying it makes it real enough to be disappointed by. That's the cost of it.
    >>  ............................................
    pt  Dizer torna real o bastante para decepcionar. É esse o custo.
    >>  ............................................
  dialogue.conversations.dreams.close.say_means/3   [80 chars]
    en  You noticed that. Everyone thinks a dream is the easy thing to talk about, %1$s.
    >>  ............................................
    pt  Você reparou. Todo mundo acha que sonho é o assunto fácil, %1$s.
    >>  ............................................
```


### Button `confide` — "I'll tell you what I want, too."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `dreams.followup.pledge_help.to.dreams`, `dreams.resume.followup.encourage.to.dreams`, `dreams.resume.followup.honest.to.dreams`, `dreams.resume.followup.practical.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.close.confide` — accepted phrasings: "i will tell you what i want too"; "let me tell you what i want"; "i want something too"
  - the message must contain one of: `want`
  - scored words: `want`(1.5), `too`(1.1)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.confide
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.close.confide   [31 chars]
    en  I'll tell you what I want, too.
    >>  ............................................
    pt  Eu também te digo o que eu quero.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.close.confide`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, familiarity +4  _(recorded under topic `dreams.close.confide`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.close.confide
WHO    VILLAGER — what the player reads after pressing "I'll tell you what I want, too."
       spoken on: conversations.topic.dreams.close, button `confide`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.close.confide.terminal`: the villager discloses. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.close.confide/1   [72 chars]
    en  ...Then we're two fools with plans, and that's a better number than one.
    >>  ............................................
    pt  ...Então somos dois tolos com planos, e é um número melhor que um.
    >>  ............................................
  dialogue.conversations.dreams.close.confide/2   [89 chars]
    en  You've got one too. Good. I'd started to think I was the only one still doing that, %1$s.
    >>  ............................................
    pt  Você tem um também. Bom. Eu já estava achando que era o único que ainda fazia isso, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.close.confide/3   [78 chars]
    en  Trade you. And if either of us gets there, the other one hears about it first.
    >>  ............................................
    pt  Troco com você. E se algum dos dois chegar lá, o outro fica sabendo primeiro.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.close.confide/1
    en  ...Then we're two fools with plans. I'd been the only one for a long time, %1$s.
    >>  ............................................
    pt  ...Então somos dois bobos com planos. Eu era o único fazia muito tempo, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.dreams.close.confide/2
    en  You didn't have to give me yours. It makes mine easier to hold.
    >>  ............................................
    pt  Você não precisava me dar o seu. Deixa o meu mais fácil de carregar.
    >>  ............................................
  anxious.dialogue.conversations.dreams.close.confide/3
    en  Two of us. I'll think about that tonight and probably not sleep, in the good way.
    >>  ............................................
    pt  Dois de nós. Vou pensar nisso hoje e provavelmente não dormir, do jeito bom.
    >>  ............................................
  athletic.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans. Plans keep better in pairs, I've found.
    >>  ............................................
    pt  Então somos dois bobos com planos. Planos se conservam melhor em pares, na minha experiência.
    >>  ............................................
  athletic.dialogue.conversations.dreams.close.confide/2
    en  Two of us. Neither of us is in a hurry, which is the right speed for a plan.
    >>  ............................................
    pt  Dois de nós. Nenhum com pressa, que é a velocidade certa pra um plano.
    >>  ............................................
  athletic.dialogue.conversations.dreams.close.confide/3
    en  Two fools. It'll take as long as it takes, and now there's somebody to take it with.
    >>  ............................................
    pt  Dois bobos. Vai levar o tempo que levar, e agora tem com quem levar.
    >>  ............................................
  confident.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans, and that's a better number than one.
    >>  ............................................
    pt  Então somos dois bobos com planos, e é um número melhor que um.
    >>  ............................................
  confident.dialogue.conversations.dreams.close.confide/2
    en  Right. Two of us, then. That changes the arithmetic more than you'd think.
    >>  ............................................
    pt  Certo. Dois de nós, então. Isso muda a conta mais do que se imagina.
    >>  ............................................
  confident.dialogue.conversations.dreams.close.confide/3
    en  Two fools. Good. One fool is a story and two is a plan.
    >>  ............................................
    pt  Dois bobos. Bom. Um bobo é história e dois é plano.
    >>  ............................................
  crabby.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans, and that's a better number than one.
    >>  ............................................
    pt  Então somos dois bobos com planos, e é um número melhor que um.
    >>  ............................................
  crabby.dialogue.conversations.dreams.close.confide/2
    en  Right. Two of us, then. That changes the arithmetic more than you'd think.
    >>  ............................................
    pt  Certo. Dois de nós, então. Isso muda a conta mais do que se imagina.
    >>  ............................................
  crabby.dialogue.conversations.dreams.close.confide/3
    en  Two fools. Good. One fool is a story and two is a plan.
    >>  ............................................
    pt  Dois bobos. Bom. Um bobo é história e dois é plano.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans, %1$s, and that's a much better number than one.
    >>  ............................................
    pt  Então somos dois bobos com planos, %1$s, e é um número bem melhor que um.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.close.confide/2
    en  You've given me yours. I'll not forget that, and I'll ask you about it later.
    >>  ............................................
    pt  Você me deu o seu. Eu não vou esquecer, e vou te perguntar depois.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.close.confide/3
    en  Two of us. I'd not expected that, and I'm very glad of it.
    >>  ............................................
    pt  Dois de nós. Eu não esperava isso, e estou muito contente.
    >>  ............................................
  flirty.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans, %1$s, and that's a much better number than one.
    >>  ............................................
    pt  Então somos dois bobos com planos, %1$s, e é um número bem melhor que um.
    >>  ............................................
  flirty.dialogue.conversations.dreams.close.confide/2
    en  You've given me yours. I'll not forget that, and I'll ask you about it later.
    >>  ............................................
    pt  Você me deu o seu. Eu não vou esquecer, e vou te perguntar depois.
    >>  ............................................
  flirty.dialogue.conversations.dreams.close.confide/3
    en  Two of us. I'd not expected that, and I'm very glad of it.
    >>  ............................................
    pt  Dois de nós. Eu não esperava isso, e estou muito contente.
    >>  ............................................
  friendly.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans, %1$s, and that's a much better number than one.
    >>  ............................................
    pt  Então somos dois bobos com planos, %1$s, e é um número bem melhor que um.
    >>  ............................................
  friendly.dialogue.conversations.dreams.close.confide/2
    en  You've given me yours. I'll not forget that, and I'll ask you about it later.
    >>  ............................................
    pt  Você me deu o seu. Eu não vou esquecer, e vou te perguntar depois.
    >>  ............................................
  friendly.dialogue.conversations.dreams.close.confide/3
    en  Two of us. I'd not expected that, and I'm very glad of it.
    >>  ............................................
    pt  Dois de nós. Eu não esperava isso, e estou muito contente.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.close.confide/1
    en  ...Then we're two fools with plans. I'd been the only one for a long time, %1$s.
    >>  ............................................
    pt  ...Então somos dois bobos com planos. Eu era o único fazia muito tempo, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.close.confide/2
    en  You didn't have to give me yours. It makes mine easier to hold.
    >>  ............................................
    pt  Você não precisava me dar o seu. Deixa o meu mais fácil de carregar.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.close.confide/3
    en  Two of us. I'll think about that tonight and probably not sleep, in the good way.
    >>  ............................................
    pt  Dois de nós. Vou pensar nisso hoje e provavelmente não dormir, do jeito bom.
    >>  ............................................
  greedy.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans, and that's a better number than one.
    >>  ............................................
    pt  Então somos dois bobos com planos, e é um número melhor que um.
    >>  ............................................
  greedy.dialogue.conversations.dreams.close.confide/2
    en  Right. Two of us, then. That changes the arithmetic more than you'd think.
    >>  ............................................
    pt  Certo. Dois de nós, então. Isso muda a conta mais do que se imagina.
    >>  ............................................
  greedy.dialogue.conversations.dreams.close.confide/3
    en  Two fools. Good. One fool is a story and two is a plan.
    >>  ............................................
    pt  Dois bobos. Bom. Um bobo é história e dois é plano.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans, and that's a better number than one.
    >>  ............................................
    pt  Então somos dois bobos com planos, e é um número melhor que um.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.close.confide/2
    en  Right. Two of us, then. That changes the arithmetic more than you'd think.
    >>  ............................................
    pt  Certo. Dois de nós, então. Isso muda a conta mais do que se imagina.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.close.confide/3
    en  Two fools. Good. One fool is a story and two is a plan.
    >>  ............................................
    pt  Dois bobos. Bom. Um bobo é história e dois é plano.
    >>  ............................................
  introverted.dialogue.conversations.dreams.close.confide/1
    en  ...Then we're two fools with plans.
    >>  ............................................
    pt  ...Então somos dois bobos com planos.
    >>  ............................................
  introverted.dialogue.conversations.dreams.close.confide/2
    en  Two, then. That's better than one.
    >>  ............................................
    pt  Dois, então. É melhor que um.
    >>  ............................................
  introverted.dialogue.conversations.dreams.close.confide/3
    en  ...Right. Two of us.
    >>  ............................................
    pt  ...Certo. Dois de nós.
    >>  ............................................
  lazy.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans. Plans keep better in pairs, I've found.
    >>  ............................................
    pt  Então somos dois bobos com planos. Planos se conservam melhor em pares, na minha experiência.
    >>  ............................................
  lazy.dialogue.conversations.dreams.close.confide/2
    en  Two of us. Neither of us is in a hurry, which is the right speed for a plan.
    >>  ............................................
    pt  Dois de nós. Nenhum com pressa, que é a velocidade certa pra um plano.
    >>  ............................................
  lazy.dialogue.conversations.dreams.close.confide/3
    en  Two fools. It'll take as long as it takes, and now there's somebody to take it with.
    >>  ............................................
    pt  Dois bobos. Vai levar o tempo que levar, e agora tem com quem levar.
    >>  ............................................
  odd.dialogue.conversations.dreams.close.confide/1
    en  ...Then we're two fools with plans.
    >>  ............................................
    pt  ...Então somos dois bobos com planos.
    >>  ............................................
  odd.dialogue.conversations.dreams.close.confide/2
    en  Two, then. That's better than one.
    >>  ............................................
    pt  Dois, então. É melhor que um.
    >>  ............................................
  odd.dialogue.conversations.dreams.close.confide/3
    en  ...Right. Two of us.
    >>  ............................................
    pt  ...Certo. Dois de nós.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans. Plans keep better in pairs, I've found.
    >>  ............................................
    pt  Então somos dois bobos com planos. Planos se conservam melhor em pares, na minha experiência.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.close.confide/2
    en  Two of us. Neither of us is in a hurry, which is the right speed for a plan.
    >>  ............................................
    pt  Dois de nós. Nenhum com pressa, que é a velocidade certa pra um plano.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.close.confide/3
    en  Two fools. It'll take as long as it takes, and now there's somebody to take it with.
    >>  ............................................
    pt  Dois bobos. Vai levar o tempo que levar, e agora tem com quem levar.
    >>  ............................................
  peppy.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans! Which is a much better number than one, %1$s.
    >>  ............................................
    pt  Então somos dois bobos com planos! Um número bem melhor que um, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.dreams.close.confide/2
    en  Two of us! Right. Now it's a conspiracy and I like it enormously better.
    >>  ............................................
    pt  Dois de nós! Certo. Agora é conspiração e eu gosto muito mais.
    >>  ............................................
  peppy.dialogue.conversations.dreams.close.confide/3
    en  Two fools with plans. That's practically a committee. I'm delighted.
    >>  ............................................
    pt  Dois bobos com planos. É praticamente um comitê. Estou encantado.
    >>  ............................................
  playful.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans! Which is a much better number than one, %1$s.
    >>  ............................................
    pt  Então somos dois bobos com planos! Um número bem melhor que um, %1$s.
    >>  ............................................
  playful.dialogue.conversations.dreams.close.confide/2
    en  Two of us! Right. Now it's a conspiracy and I like it enormously better.
    >>  ............................................
    pt  Dois de nós! Certo. Agora é conspiração e eu gosto muito mais.
    >>  ............................................
  playful.dialogue.conversations.dreams.close.confide/3
    en  Two fools with plans. That's practically a committee. I'm delighted.
    >>  ............................................
    pt  Dois bobos com planos. É praticamente um comitê. Estou encantado.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans. Plans keep better in pairs, I've found.
    >>  ............................................
    pt  Então somos dois bobos com planos. Planos se conservam melhor em pares, na minha experiência.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.close.confide/2
    en  Two of us. Neither of us is in a hurry, which is the right speed for a plan.
    >>  ............................................
    pt  Dois de nós. Nenhum com pressa, que é a velocidade certa pra um plano.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.close.confide/3
    en  Two fools. It'll take as long as it takes, and now there's somebody to take it with.
    >>  ............................................
    pt  Dois bobos. Vai levar o tempo que levar, e agora tem com quem levar.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.close.confide/1
    en  ...Then we're two fools with plans. I'd been the only one for a long time, %1$s.
    >>  ............................................
    pt  ...Então somos dois bobos com planos. Eu era o único fazia muito tempo, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.close.confide/2
    en  You didn't have to give me yours. It makes mine easier to hold.
    >>  ............................................
    pt  Você não precisava me dar o seu. Deixa o meu mais fácil de carregar.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.close.confide/3
    en  Two of us. I'll think about that tonight and probably not sleep, in the good way.
    >>  ............................................
    pt  Dois de nós. Vou pensar nisso hoje e provavelmente não dormir, do jeito bom.
    >>  ............................................
  shy.dialogue.conversations.dreams.close.confide/1
    en  ...Then we're two fools with plans.
    >>  ............................................
    pt  ...Então somos dois bobos com planos.
    >>  ............................................
  shy.dialogue.conversations.dreams.close.confide/2
    en  Two, then. That's better than one.
    >>  ............................................
    pt  Dois, então. É melhor que um.
    >>  ............................................
  shy.dialogue.conversations.dreams.close.confide/3
    en  ...Right. Two of us.
    >>  ............................................
    pt  ...Certo. Dois de nós.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans! Which is a much better number than one, %1$s.
    >>  ............................................
    pt  Então somos dois bobos com planos! Um número bem melhor que um, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.close.confide/2
    en  Two of us! Right. Now it's a conspiracy and I like it enormously better.
    >>  ............................................
    pt  Dois de nós! Certo. Agora é conspiração e eu gosto muito mais.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.close.confide/3
    en  Two fools with plans. That's practically a committee. I'm delighted.
    >>  ............................................
    pt  Dois bobos com planos. É praticamente um comitê. Estou encantado.
    >>  ............................................
  witty.dialogue.conversations.dreams.close.confide/1
    en  Then we're two fools with plans! Which is a much better number than one, %1$s.
    >>  ............................................
    pt  Então somos dois bobos com planos! Um número bem melhor que um, %1$s.
    >>  ............................................
  witty.dialogue.conversations.dreams.close.confide/2
    en  Two of us! Right. Now it's a conspiracy and I like it enormously better.
    >>  ............................................
    pt  Dois de nós! Certo. Agora é conspiração e eu gosto muito mais.
    >>  ............................................
  witty.dialogue.conversations.dreams.close.confide/3
    en  Two fools with plans. That's practically a committee. I'm delighted.
    >>  ............................................
    pt  Dois bobos com planos. É praticamente um comitê. Estou encantado.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.followup.pledge_help.to.dreams`, `dreams.resume.followup.encourage.to.dreams`, `dreams.resume.followup.honest.to.dreams`, `dreams.resume.followup.practical.to.dreams` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.close.leave   [16 chars]
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
POOL   dialogue key: dialogue.conversations.dreams.close.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.dreams.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.close.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.close.leave/1   [26 chars]
    en  Aye. Go on, and thank you.
    >>  ............................................
    pt  Tá. Pode ir, e obrigado.
    >>  ............................................
  dialogue.conversations.dreams.close.leave/2   [37 chars]
    en  So be it. Enough of that for one day.
    >>  ............................................
    pt  Que seja. Já chega disso por um dia.
    >>  ............................................
  dialogue.conversations.dreams.close.leave/3   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
```

---


## `conversations.topic.dreams.close.honest`

**Reached from 1 route(s):** `conversations.topic.dreams.followup` / `be_honest`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.followup.be_honest` — e.g. "That's better than a promise you'd break. Thank you for the honesty."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.honest
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.close.honest
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.close.honest   [64 chars]
    en  You didn't promise anything. That's worth more than you'd think.
    >>  ............................................
    pt  Você não prometeu nada. Isso vale mais do que parece.
    >>  ............................................
```


### Button `thank` — "Thank you for telling me anyway."

*stance family `candor` · tone `gentle` · answers the beat(s) `dreams.followup.be_honest.to.dreams.close.honest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.close.honest.thank` — accepted phrasings: "thank you for telling me anyway"; "thanks for telling me anyway"; "thank you for saying it anyway"
  - the message must contain one of: `thank`
  - scored words: `thank`(1.5), `anyway`(1.1)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.honest.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.close.honest
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.close.honest.thank   [32 chars]
    en  Thank you for telling me anyway.
    >>  ............................................
    pt  Obrigado por me contar mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.close.honest.thank`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, trust +1  _(recorded under topic `dreams.close.honest.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.close.honest.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me anyway."
       spoken on: conversations.topic.dreams.close.honest, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.close.honest.thank.terminal`: the villager accepts. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.close.honest.thank/1   [98 chars]
    en  You're welcome. And you didn't dress it up as help you weren't going to give, which I'll remember.
    >>  ............................................
    pt  De nada. E você não vestiu isso de ajuda que não ia dar, o que eu vou lembrar.
    >>  ............................................
  dialogue.conversations.dreams.close.honest.thank/2   [73 chars]
    en  Just so, well. An honest 'no' keeps better than a friendly 'maybe', %1$s.
    >>  ............................................
    pt  Pois é, bom. Um 'não' honesto se conserva melhor que um 'talvez' simpático, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.close.honest.thank/3   [69 chars]
    en  Hm. Thank you for hearing it without immediately volunteering for it.
    >>  ............................................
    pt  Hm. Obrigado por ouvir sem se voluntariar na hora.
    >>  ............................................
```


### Button `say_means` — "That took something to say."

*stance family `candor` · tone `gentle` · answers the beat(s) `dreams.followup.be_honest.to.dreams.close.honest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.close.honest.say_means` — accepted phrasings: "that took something to say"; "saying that took something"; "it took something to say that"
  - the message must contain one of: `took`
  - scored words: `took`(1.5), `something`(1.1)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.honest.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.close.honest
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.close.honest.say_means   [27 chars]
    en  That took something to say.
    >>  ............................................
    pt  Falar isso exigiu coragem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.close.honest.say_means`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `dreams.close.honest.say_means`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.close.honest.say_means
WHO    VILLAGER — what the player reads after pressing "That took something to say."
       spoken on: conversations.topic.dreams.close.honest, button `say_means`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.close.honest.say_means.terminal`: the villager accepts. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.close.honest.say_means/1   [73 chars]
    en  ...It did. More so knowing you'd not pretend to be more use than you are.
    >>  ............................................
    pt  ...Exigiu. Ainda mais sabendo que você não ia fingir ser mais útil do que é.
    >>  ............................................
  dialogue.conversations.dreams.close.honest.say_means/2   [85 chars]
    en  Saying it to somebody who won't help is the purer test. It's still a want either way.
    >>  ............................................
    pt  Dizer para quem não vai ajudar é o teste mais puro. Continua sendo um desejo de qualquer jeito.
    >>  ............................................
  dialogue.conversations.dreams.close.honest.say_means/3   [71 chars]
    en  You noticed. I'd rather be heard honestly than helped carelessly, %1$s.
    >>  ............................................
    pt  Você reparou. Prefiro ser ouvido com honestidade a ser ajudado com descuido, %1$s.
    >>  ............................................
```


### Button `confide` — "I'll tell you what I want, too."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `dreams.followup.be_honest.to.dreams.close.honest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.close.honest.confide` — accepted phrasings: "i will tell you what i want too"; "let me tell you what i want"; "i want something too"
  - the message must contain one of: `want`, `mine`
  - scored words: `want`(1.5), `mine`(1.2), `also`(0.9)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.honest.confide
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.close.honest
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.close.honest.confide   [31 chars]
    en  I'll tell you what I want, too.
    >>  ............................................
    pt  Eu também te digo o que eu quero.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.close.honest.confide`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, familiarity +4  _(recorded under topic `dreams.close.honest.confide`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.close.honest.confide
WHO    VILLAGER — what the player reads after pressing "I'll tell you what I want, too."
       spoken on: conversations.topic.dreams.close.honest, button `confide`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.close.honest.confide.terminal`: the villager discloses. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.close.honest.confide/1   [89 chars]
    en  ...Then we're two fools with plans and no obligations to each other. Perfect arrangement.
    >>  ............................................
    pt  ...Então somos dois tolos com planos e nenhuma obrigação um com o outro. Arranjo perfeito.
    >>  ............................................
  dialogue.conversations.dreams.close.honest.confide/2   [79 chars]
    en  You've one of your own and you're not asking me to carry it either. Good, %1$s.
    >>  ............................................
    pt  Você tem um seu e também não está me pedindo para carregar. Bom, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.close.honest.confide/3   [48 chars]
    en  Trade you. No promises attached, on either side.
    >>  ............................................
    pt  Troco com você. Sem promessas de nenhum dos lados.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and no obligations. The no-obligations part is why I can say it, %1$s.
    >>  ............................................
    pt  Dois bobos com planos e sem obrigações. A parte sem obrigações é por que eu consigo dizer, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.dreams.close.honest.confide/2
    en  Nothing owed. I'd have been frightened of the other kind, honestly.
    >>  ............................................
    pt  Nada devido. Sinceramente, eu teria medo do outro tipo.
    >>  ............................................
  anxious.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us, and nobody has to. That's the part that makes it safe to say.
    >>  ............................................
    pt  Dois de nós, e ninguém precisa. É a parte que torna seguro dizer.
    >>  ............................................
  athletic.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and nothing owed. That's an arrangement that lasts.
    >>  ............................................
    pt  Dois bobos com planos e nada devido. É um arranjo que dura.
    >>  ............................................
  athletic.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations either way. Nothing forced keeps as well as something chosen.
    >>  ............................................
    pt  Sem obrigações. Nada forçado se conserva tão bem quanto algo escolhido.
    >>  ............................................
  athletic.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us, freely. It'll go where it goes and neither of us has to steer.
    >>  ............................................
    pt  Dois de nós, livremente. Vai aonde for e nenhum de nós precisa pilotar.
    >>  ............................................
  confident.dialogue.conversations.dreams.close.honest.confide/1
    en  Then we're two fools with plans and no obligations to each other. Perfect arrangement.
    >>  ............................................
    pt  Então somos dois bobos com planos e sem obrigações um com o outro. Arranjo perfeito.
    >>  ............................................
  confident.dialogue.conversations.dreams.close.honest.confide/2
    en  Two of us, and nothing owed either way. I'd not improve on that.
    >>  ............................................
    pt  Dois de nós, e nada devido de lado nenhum. Eu não melhoraria isso.
    >>  ............................................
  confident.dialogue.conversations.dreams.close.honest.confide/3
    en  No obligations. Good. That's the version I can actually keep.
    >>  ............................................
    pt  Sem obrigações. Bom. É a versão que eu consigo manter.
    >>  ............................................
  crabby.dialogue.conversations.dreams.close.honest.confide/1
    en  Then we're two fools with plans and no obligations to each other. Perfect arrangement.
    >>  ............................................
    pt  Então somos dois bobos com planos e sem obrigações um com o outro. Arranjo perfeito.
    >>  ............................................
  crabby.dialogue.conversations.dreams.close.honest.confide/2
    en  Two of us, and nothing owed either way. I'd not improve on that.
    >>  ............................................
    pt  Dois de nós, e nada devido de lado nenhum. Eu não melhoraria isso.
    >>  ............................................
  crabby.dialogue.conversations.dreams.close.honest.confide/3
    en  No obligations. Good. That's the version I can actually keep.
    >>  ............................................
    pt  Sem obrigações. Bom. É a versão que eu consigo manter.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and nothing owed, %1$s. That's a rarer thing than it sounds.
    >>  ............................................
    pt  Dois bobos com planos e nada devido, %1$s. É mais raro do que parece.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations. Which means whatever comes of this comes freely, and I prefer that.
    >>  ............................................
    pt  Sem obrigações. O que significa que o que vier disso vem de graça, e eu prefiro assim.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us and no debt in it. I'd like that to keep being true.
    >>  ............................................
    pt  Dois de nós e nenhuma dívida. Eu queria que isso continuasse verdade.
    >>  ............................................
  flirty.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and nothing owed, %1$s. That's a rarer thing than it sounds.
    >>  ............................................
    pt  Dois bobos com planos e nada devido, %1$s. É mais raro do que parece.
    >>  ............................................
  flirty.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations. Which means whatever comes of this comes freely, and I prefer that.
    >>  ............................................
    pt  Sem obrigações. O que significa que o que vier disso vem de graça, e eu prefiro assim.
    >>  ............................................
  flirty.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us and no debt in it. I'd like that to keep being true.
    >>  ............................................
    pt  Dois de nós e nenhuma dívida. Eu queria que isso continuasse verdade.
    >>  ............................................
  friendly.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and nothing owed, %1$s. That's a rarer thing than it sounds.
    >>  ............................................
    pt  Dois bobos com planos e nada devido, %1$s. É mais raro do que parece.
    >>  ............................................
  friendly.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations. Which means whatever comes of this comes freely, and I prefer that.
    >>  ............................................
    pt  Sem obrigações. O que significa que o que vier disso vem de graça, e eu prefiro assim.
    >>  ............................................
  friendly.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us and no debt in it. I'd like that to keep being true.
    >>  ............................................
    pt  Dois de nós e nenhuma dívida. Eu queria que isso continuasse verdade.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and no obligations. The no-obligations part is why I can say it, %1$s.
    >>  ............................................
    pt  Dois bobos com planos e sem obrigações. A parte sem obrigações é por que eu consigo dizer, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.close.honest.confide/2
    en  Nothing owed. I'd have been frightened of the other kind, honestly.
    >>  ............................................
    pt  Nada devido. Sinceramente, eu teria medo do outro tipo.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us, and nobody has to. That's the part that makes it safe to say.
    >>  ............................................
    pt  Dois de nós, e ninguém precisa. É a parte que torna seguro dizer.
    >>  ............................................
  greedy.dialogue.conversations.dreams.close.honest.confide/1
    en  Then we're two fools with plans and no obligations to each other. Perfect arrangement.
    >>  ............................................
    pt  Então somos dois bobos com planos e sem obrigações um com o outro. Arranjo perfeito.
    >>  ............................................
  greedy.dialogue.conversations.dreams.close.honest.confide/2
    en  Two of us, and nothing owed either way. I'd not improve on that.
    >>  ............................................
    pt  Dois de nós, e nada devido de lado nenhum. Eu não melhoraria isso.
    >>  ............................................
  greedy.dialogue.conversations.dreams.close.honest.confide/3
    en  No obligations. Good. That's the version I can actually keep.
    >>  ............................................
    pt  Sem obrigações. Bom. É a versão que eu consigo manter.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.close.honest.confide/1
    en  Then we're two fools with plans and no obligations to each other. Perfect arrangement.
    >>  ............................................
    pt  Então somos dois bobos com planos e sem obrigações um com o outro. Arranjo perfeito.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.close.honest.confide/2
    en  Two of us, and nothing owed either way. I'd not improve on that.
    >>  ............................................
    pt  Dois de nós, e nada devido de lado nenhum. Eu não melhoraria isso.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.close.honest.confide/3
    en  No obligations. Good. That's the version I can actually keep.
    >>  ............................................
    pt  Sem obrigações. Bom. É a versão que eu consigo manter.
    >>  ............................................
  introverted.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans. No obligations.
    >>  ............................................
    pt  Dois bobos com planos. Sem obrigações.
    >>  ............................................
  introverted.dialogue.conversations.dreams.close.honest.confide/2
    en  Nothing owed either way. Good.
    >>  ............................................
    pt  Nada devido de lado nenhum. Bom.
    >>  ............................................
  introverted.dialogue.conversations.dreams.close.honest.confide/3
    en  ...That's the arrangement, then.
    >>  ............................................
    pt  ...Então é esse o arranjo.
    >>  ............................................
  lazy.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and nothing owed. That's an arrangement that lasts.
    >>  ............................................
    pt  Dois bobos com planos e nada devido. É um arranjo que dura.
    >>  ............................................
  lazy.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations either way. Nothing forced keeps as well as something chosen.
    >>  ............................................
    pt  Sem obrigações. Nada forçado se conserva tão bem quanto algo escolhido.
    >>  ............................................
  lazy.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us, freely. It'll go where it goes and neither of us has to steer.
    >>  ............................................
    pt  Dois de nós, livremente. Vai aonde for e nenhum de nós precisa pilotar.
    >>  ............................................
  odd.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans. No obligations.
    >>  ............................................
    pt  Dois bobos com planos. Sem obrigações.
    >>  ............................................
  odd.dialogue.conversations.dreams.close.honest.confide/2
    en  Nothing owed either way. Good.
    >>  ............................................
    pt  Nada devido de lado nenhum. Bom.
    >>  ............................................
  odd.dialogue.conversations.dreams.close.honest.confide/3
    en  ...That's the arrangement, then.
    >>  ............................................
    pt  ...Então é esse o arranjo.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and nothing owed. That's an arrangement that lasts.
    >>  ............................................
    pt  Dois bobos com planos e nada devido. É um arranjo que dura.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations either way. Nothing forced keeps as well as something chosen.
    >>  ............................................
    pt  Sem obrigações. Nada forçado se conserva tão bem quanto algo escolhido.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us, freely. It'll go where it goes and neither of us has to steer.
    >>  ............................................
    pt  Dois de nós, livremente. Vai aonde for e nenhum de nós precisa pilotar.
    >>  ............................................
  peppy.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and no obligations! That's the finest sort of arrangement there is.
    >>  ............................................
    pt  Dois bobos com planos e sem obrigações! É o melhor tipo de arranjo que existe.
    >>  ............................................
  peppy.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations either way. Marvellous. Nobody has to be disappointed on a schedule.
    >>  ............................................
    pt  Sem obrigações de lado nenhum. Maravilhoso. Ninguém precisa se decepcionar no cronograma.
    >>  ............................................
  peppy.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us and nothing owed. I'd sign that, if signing weren't an obligation.
    >>  ............................................
    pt  Dois de nós e nada devido. Eu assinaria, se assinar não fosse obrigação.
    >>  ............................................
  playful.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and no obligations! That's the finest sort of arrangement there is.
    >>  ............................................
    pt  Dois bobos com planos e sem obrigações! É o melhor tipo de arranjo que existe.
    >>  ............................................
  playful.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations either way. Marvellous. Nobody has to be disappointed on a schedule.
    >>  ............................................
    pt  Sem obrigações de lado nenhum. Maravilhoso. Ninguém precisa se decepcionar no cronograma.
    >>  ............................................
  playful.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us and nothing owed. I'd sign that, if signing weren't an obligation.
    >>  ............................................
    pt  Dois de nós e nada devido. Eu assinaria, se assinar não fosse obrigação.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and nothing owed. That's an arrangement that lasts.
    >>  ............................................
    pt  Dois bobos com planos e nada devido. É um arranjo que dura.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations either way. Nothing forced keeps as well as something chosen.
    >>  ............................................
    pt  Sem obrigações. Nada forçado se conserva tão bem quanto algo escolhido.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us, freely. It'll go where it goes and neither of us has to steer.
    >>  ............................................
    pt  Dois de nós, livremente. Vai aonde for e nenhum de nós precisa pilotar.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and no obligations. The no-obligations part is why I can say it, %1$s.
    >>  ............................................
    pt  Dois bobos com planos e sem obrigações. A parte sem obrigações é por que eu consigo dizer, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.close.honest.confide/2
    en  Nothing owed. I'd have been frightened of the other kind, honestly.
    >>  ............................................
    pt  Nada devido. Sinceramente, eu teria medo do outro tipo.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us, and nobody has to. That's the part that makes it safe to say.
    >>  ............................................
    pt  Dois de nós, e ninguém precisa. É a parte que torna seguro dizer.
    >>  ............................................
  shy.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans. No obligations.
    >>  ............................................
    pt  Dois bobos com planos. Sem obrigações.
    >>  ............................................
  shy.dialogue.conversations.dreams.close.honest.confide/2
    en  Nothing owed either way. Good.
    >>  ............................................
    pt  Nada devido de lado nenhum. Bom.
    >>  ............................................
  shy.dialogue.conversations.dreams.close.honest.confide/3
    en  ...That's the arrangement, then.
    >>  ............................................
    pt  ...Então é esse o arranjo.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and no obligations! That's the finest sort of arrangement there is.
    >>  ............................................
    pt  Dois bobos com planos e sem obrigações! É o melhor tipo de arranjo que existe.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations either way. Marvellous. Nobody has to be disappointed on a schedule.
    >>  ............................................
    pt  Sem obrigações de lado nenhum. Maravilhoso. Ninguém precisa se decepcionar no cronograma.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us and nothing owed. I'd sign that, if signing weren't an obligation.
    >>  ............................................
    pt  Dois de nós e nada devido. Eu assinaria, se assinar não fosse obrigação.
    >>  ............................................
  witty.dialogue.conversations.dreams.close.honest.confide/1
    en  Two fools with plans and no obligations! That's the finest sort of arrangement there is.
    >>  ............................................
    pt  Dois bobos com planos e sem obrigações! É o melhor tipo de arranjo que existe.
    >>  ............................................
  witty.dialogue.conversations.dreams.close.honest.confide/2
    en  No obligations either way. Marvellous. Nobody has to be disappointed on a schedule.
    >>  ............................................
    pt  Sem obrigações de lado nenhum. Maravilhoso. Ninguém precisa se decepcionar no cronograma.
    >>  ............................................
  witty.dialogue.conversations.dreams.close.honest.confide/3
    en  Two of us and nothing owed. I'd sign that, if signing weren't an obligation.
    >>  ............................................
    pt  Dois de nós e nada devido. Eu assinaria, se assinar não fosse obrigação.
    >>  ............................................
```

</details>


### Button `leave` — "I hope you get it."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.followup.be_honest.to.dreams.close.honest` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.close.honest.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.close.honest
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.close.honest.leave   [18 chars]
    en  I hope you get it.
    >>  ............................................
    pt  Espero que você consiga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.close.honest.leave
WHO    VILLAGER — what the player reads after pressing "I hope you get it."
       spoken on: conversations.topic.dreams.close.honest, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.close.honest.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.close.honest.leave/1   [58 chars]
    en  So do I. Off you go, and thank you for the honest version.
    >>  ............................................
    pt  Eu também. Pode ir, e obrigado pela versão honesta.
    >>  ............................................
  dialogue.conversations.dreams.close.honest.leave/2   [34 chars]
    en  So you are. Mind how you go, %1$s.
    >>  ............................................
    pt  Pois é. Se cuida, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.close.honest.leave/3   [91 chars]
    en  Quite. It'll happen or it won't, and you were straight with me about which you'd help with.
    >>  ............................................
    pt  Exato. Vai acontecer ou não, e você foi direto sobre com o que ajudaria.
    >>  ............................................
```

---


## `conversations.topic.dreams.deflated.followup`

**Reached from 1 route(s):** `conversations.topic.dreams.respond` / `realism`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.respond.realism.flat` — e.g. "...I know how far it is. I didn't need it measured out."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.deflated.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.deflated.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.deflated.followup   [21 chars]
    en  I know how far it is.
    >>  ............................................
    pt  Eu sei o quão longe é.
    >>  ............................................
```


### Button `apologize` — "You didn't need the distance measured."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `dreams.rebuked` · offered only once the villager has actually said `player:measured_the_dream`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.rebuked.apologize` — accepted phrasings: "you didn't need the distance measured"
  - the message must contain one of: `distance`, `measured`
  - scored words: `distance`(1.5), `measured`(1.5), `needed`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.deflated.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.deflated.followup.apologize   [38 chars]
    en  You didn't need the distance measured.
    >>  ............................................
    pt  Você não precisava que medissem a distância.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `dreams.rebuked.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.rebuked.apologize
WHO    VILLAGER — what the player reads after pressing "You didn't need the distance measured."
       spoken on: conversations.topic.dreams.deflated.followup, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.rebuked.apologize`: the villager qualifys. Subject `dreams.ambition`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.rebuked.apologize/1   [49 chars]
    en  ...No. I've a rope and a tape of my own for that.
    >>  ............................................
    pt  ...Não. Eu tenho corda e trena próprias pra isso.
    >>  ............................................
  dialogue.conversations.dreams.rebuked.apologize/2   [68 chars]
    en  I count it every night, %1$s. I don't need help with the arithmetic.
    >>  ............................................
    pt  Eu conto toda noite, %1$s. Não preciso de ajuda com a conta.
    >>  ............................................
  dialogue.conversations.dreams.rebuked.apologize/3   [55 chars]
    en  Thank you. That's the apology I'd have written for you.
    >>  ............................................
    pt  Obrigado. É esse o pedido de desculpas que eu teria escrito pra você.
    >>  ............................................
```


### Button `explain` — "I only meant I'd rather you weren't hurt by it."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `dreams.rebuked`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.rebuked.explain` — accepted phrasings: "i only meant i'd rather you weren't hurt by it"
  - the message must contain one of: `hurt`, `meant`, `rather`
  - scored words: `hurt`(1.5), `meant`(1.2), `rather`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.deflated.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.deflated.followup.explain   [47 chars]
    en  I only meant I'd rather you weren't hurt by it.
    >>  ............................................
    pt  Eu só quis dizer que prefiro que você não se machuque com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `dreams.rebuked.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.rebuked.explain
WHO    VILLAGER — what the player reads after pressing "I only meant I'd rather you weren't hurt by it."
       spoken on: conversations.topic.dreams.deflated.followup, button `explain`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.rebuked.explain`: the villager qualifys. Subject `dreams.ambition`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.rebuked.explain/1   [61 chars]
    en  ...Oh. That's a different sentence and I heard the other one.
    >>  ............................................
    pt  ...Ah. É outra frase e eu ouvi a outra.
    >>  ............................................
  dialogue.conversations.dreams.rebuked.explain/2   [65 chars]
    en  Then you're kinder than you sound, %1$s. Say the kind half first.
    >>  ............................................
    pt  Então você é mais gentil do que soa, %1$s. Diga a metade gentil primeiro.
    >>  ............................................
  dialogue.conversations.dreams.rebuked.explain/3   [59 chars]
    en  Hurt by it. Aye, well. Wanting something always costs that.
    >>  ............................................
    pt  Me machucar. É, bom. Querer algo sempre custa isso.
    >>  ............................................
```


### Button `leave` — "I'll leave it alone."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `dreams.rebuked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.deflated.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.deflated.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.deflated.followup.leave   [20 chars]
    en  I'll leave it alone.
    >>  ............................................
    pt  Vou deixar isso quieto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.rebuked.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it alone."
       spoken on: conversations.topic.dreams.deflated.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.rebuked.leave`: the villager accepts. Subject `dreams.ambition`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.rebuked.leave/1   [30 chars]
    en  Do. It keeps better untouched.
    >>  ............................................
    pt  Deixe. Se conserva melhor sem mexer.
    >>  ............................................
  dialogue.conversations.dreams.rebuked.leave/2   [30 chars]
    en  True enough. Off you go, %1$s.
    >>  ............................................
    pt  Bem verdade. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.rebuked.leave/3   [47 chars]
    en  Understood. It'll still be a long way tomorrow.
    >>  ............................................
    pt  Entendido. Amanhã ainda vai estar longe.
    >>  ............................................
```

---


## `conversations.topic.dreams.followup`

**Reached from 5 route(s):** `conversations.topic.dreams.respond` / `encourage`; `conversations.topic.dreams.respond` / `ask_more`; `conversations.topic.dreams.respond` / `realism`; `conversations.topic.dreams.respond` / `realism`; `conversations.topic.dreams.respond` / `no_words`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.no_words` — e.g. "...That's fair. It's a daft thing to want out loud and you've not laughed, which is plenty."
- `conversations.dreams.respond.ask_more` — e.g. "You want the details? Nobody wants the details."
- `conversations.dreams.respond.encourage` — e.g. "...Should I? Saying it out loud to someone makes it feel less daft."
- `conversations.dreams.respond.realism.landed` — e.g. "It is. That's the useful thing to hear and nobody says it."
- `conversations.dreams.respond.realism.polite` — e.g. "It is a long way. I've counted the steps more than once."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.followup   [21 chars]
    en  So. That's the dream.
    >>  ............................................
    pt  Então. É esse o sonho.
    >>  ............................................
```


### Button `pledge_help` — "I'll help you get there."

*stance family `practical_help` · tone `plain` · answers the beat(s) `dreams.no_words.to.dreams`, `dreams.respond.ask_more.to.dreams`, `dreams.respond.encourage.to.dreams`, `dreams.respond.realism.landed.to.dreams`, `dreams.respond.realism.polite.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.followup.pledge_help` — accepted phrasings: "i will help you get there"; "we will get there together"; "let me help you with it"
  - the message must contain one of: `help`, `together`
  - scored words: `help`(1.5), `there`(0.6), `together`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.followup.pledge_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.followup.pledge_help   [24 chars]
    en  I'll help you get there.
    >>  ............................................
    pt  Vou te ajudar a chegar lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `dreams.followup.pledge_help`, budget `deep`, replay policy `once`
- Does: disposition — trust +5, warmth +3  _(recorded under topic `dreams.followup.pledge_help`)_
- Does: arc `dreams` — advance to stage 1
- Does: exclusive `dreams.support` -> `pledged` (locks the other side out for good)
- Does: remembers `mcaconversations.pledge.dreams` (this player only) for 72000 ticks
- Then opens: `conversations.topic.dreams.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "I'll tell you what I want, too." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.dreams.followup.pledge_help
WHO    VILLAGER — what the player reads after pressing "I'll help you get there."
       spoken on: conversations.topic.dreams.followup, button `pledge_help`
       leaves the player on: conversations.topic.dreams.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.followup.pledge_help.to.dreams`: the villager accepts. Subject `dreams`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.followup.pledge_help/1   [68 chars]
    en  ...You mean that? Then it's the first time it's been more than mine.
    >>  ............................................
    pt  ...Você está falando sério? Então é a primeira vez que não é só meu.
    >>  ............................................
  dialogue.conversations.dreams.followup.pledge_help/2   [70 chars]
    en  Careful what you promise, %1$s. I'll hold you to it and I'll enjoy it.
    >>  ............................................
    pt  Cuidado com o que promete, %1$s. Vou cobrar e vou gostar de cobrar.
    >>  ............................................
  dialogue.conversations.dreams.followup.pledge_help/3   [89 chars]
    en  Help. Right. Then you can start by holding the other end of things while I swear at them.
    >>  ............................................
    pt  Ajudar. Certo. Então comece segurando a outra ponta das coisas enquanto eu xingo elas.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that? Nobody's offered before, %1$s. I'd stopped expecting it.
    >>  ............................................
    pt  ...Você fala sério? Ninguém ofereceu antes, %1$s. Eu tinha parado de esperar.
    >>  ............................................
  anxious.dialogue.conversations.dreams.followup.pledge_help/2
    en  You'd help. I don't know what to do with that and I'd like to keep it anyway.
    >>  ............................................
    pt  Você ajudaria. Não sei o que fazer com isso e quero guardar mesmo assim.
    >>  ............................................
  anxious.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then it isn't only mine. I've carried it alone so long I'd forgotten it could be otherwise.
    >>  ............................................
    pt  Então não é só meu. Carreguei sozinho tanto tempo que esqueci que podia ser diferente.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that. Then it's more than mine, and things that are shared last longer.
    >>  ............................................
    pt  Você fala sério. Então é mais que meu, e o que é dividido dura mais.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. No hurry on it — but two of us is better arithmetic than one.
    >>  ............................................
    pt  Certo. Sem pressa — mas dois é uma conta melhor que um.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then we'll get to it, in time. Time is the part I've always had plenty of.
    >>  ............................................
    pt  Então a gente chega lá, com o tempo. Tempo é o que eu sempre tive de sobra.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that? Then it's the first time it's been more than mine.
    >>  ............................................
    pt  Você fala sério? Então é a primeira vez que isso é mais que meu.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. Say it again in a month and I'll start believing it.
    >>  ............................................
    pt  Certo. Diga de novo em um mês e eu começo a acreditar.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then it isn't only my idea any more. That's a different weight.
    >>  ............................................
    pt  Então não é mais só ideia minha. É outro peso.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that? Then it's the first time it's been more than mine.
    >>  ............................................
    pt  Você fala sério? Então é a primeira vez que isso é mais que meu.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. Say it again in a month and I'll start believing it.
    >>  ............................................
    pt  Certo. Diga de novo em um mês e eu começo a acreditar.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then it isn't only my idea any more. That's a different weight.
    >>  ............................................
    pt  Então não é mais só ideia minha. É outro peso.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that, %1$s? Then it's the first time it's been more than mine.
    >>  ............................................
    pt  ...Você fala sério, %1$s? Então é a primeira vez que isso é mais que meu.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.pledge_help/2
    en  You'd help. I'd not ask for that and I'd not turn it down either.
    >>  ............................................
    pt  Você ajudaria. Eu não pediria isso e também não recusaria.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then there are two of us. I'll not pretend that doesn't change something.
    >>  ............................................
    pt  Então somos dois. Não vou fingir que isso não muda algo.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that, %1$s? Then it's the first time it's been more than mine.
    >>  ............................................
    pt  ...Você fala sério, %1$s? Então é a primeira vez que isso é mais que meu.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.pledge_help/2
    en  You'd help. I'd not ask for that and I'd not turn it down either.
    >>  ............................................
    pt  Você ajudaria. Eu não pediria isso e também não recusaria.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then there are two of us. I'll not pretend that doesn't change something.
    >>  ............................................
    pt  Então somos dois. Não vou fingir que isso não muda algo.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that, %1$s? Then it's the first time it's been more than mine.
    >>  ............................................
    pt  ...Você fala sério, %1$s? Então é a primeira vez que isso é mais que meu.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.pledge_help/2
    en  You'd help. I'd not ask for that and I'd not turn it down either.
    >>  ............................................
    pt  Você ajudaria. Eu não pediria isso e também não recusaria.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then there are two of us. I'll not pretend that doesn't change something.
    >>  ............................................
    pt  Então somos dois. Não vou fingir que isso não muda algo.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that? Nobody's offered before, %1$s. I'd stopped expecting it.
    >>  ............................................
    pt  ...Você fala sério? Ninguém ofereceu antes, %1$s. Eu tinha parado de esperar.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.pledge_help/2
    en  You'd help. I don't know what to do with that and I'd like to keep it anyway.
    >>  ............................................
    pt  Você ajudaria. Não sei o que fazer com isso e quero guardar mesmo assim.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then it isn't only mine. I've carried it alone so long I'd forgotten it could be otherwise.
    >>  ............................................
    pt  Então não é só meu. Carreguei sozinho tanto tempo que esqueci que podia ser diferente.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that? Then it's the first time it's been more than mine.
    >>  ............................................
    pt  Você fala sério? Então é a primeira vez que isso é mais que meu.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. Say it again in a month and I'll start believing it.
    >>  ............................................
    pt  Certo. Diga de novo em um mês e eu começo a acreditar.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then it isn't only my idea any more. That's a different weight.
    >>  ............................................
    pt  Então não é mais só ideia minha. É outro peso.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that? Then it's the first time it's been more than mine.
    >>  ............................................
    pt  Você fala sério? Então é a primeira vez que isso é mais que meu.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. Say it again in a month and I'll start believing it.
    >>  ............................................
    pt  Certo. Diga de novo em um mês e eu começo a acreditar.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then it isn't only my idea any more. That's a different weight.
    >>  ............................................
    pt  Então não é mais só ideia minha. É outro peso.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that. Then it's more than mine now.
    >>  ............................................
    pt  ...Você fala sério. Então agora é mais que meu.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. Two of us.
    >>  ............................................
    pt  Certo. Dois de nós.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.pledge_help/3
    en  I'd not expected that. I'll need a moment.
    >>  ............................................
    pt  Eu não esperava isso. Vou precisar de um momento.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that. Then it's more than mine, and things that are shared last longer.
    >>  ............................................
    pt  Você fala sério. Então é mais que meu, e o que é dividido dura mais.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. No hurry on it — but two of us is better arithmetic than one.
    >>  ............................................
    pt  Certo. Sem pressa — mas dois é uma conta melhor que um.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then we'll get to it, in time. Time is the part I've always had plenty of.
    >>  ............................................
    pt  Então a gente chega lá, com o tempo. Tempo é o que eu sempre tive de sobra.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that. Then it's more than mine now.
    >>  ............................................
    pt  ...Você fala sério. Então agora é mais que meu.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. Two of us.
    >>  ............................................
    pt  Certo. Dois de nós.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.pledge_help/3
    en  I'd not expected that. I'll need a moment.
    >>  ............................................
    pt  Eu não esperava isso. Vou precisar de um momento.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that. Then it's more than mine, and things that are shared last longer.
    >>  ............................................
    pt  Você fala sério. Então é mais que meu, e o que é dividido dura mais.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. No hurry on it — but two of us is better arithmetic than one.
    >>  ............................................
    pt  Certo. Sem pressa — mas dois é uma conta melhor que um.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then we'll get to it, in time. Time is the part I've always had plenty of.
    >>  ............................................
    pt  Então a gente chega lá, com o tempo. Tempo é o que eu sempre tive de sobra.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that? Then it's the first time it's been more than mine! Extraordinary day.
    >>  ............................................
    pt  Você fala sério? Então é a primeira vez que isso é mais que meu! Dia extraordinário.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.pledge_help/2
    en  Two of us on it. That's not a dream any more, that's a conspiracy. Much better.
    >>  ............................................
    pt  Dois nisso. Não é mais sonho, é conspiração. Muito melhor.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.pledge_help/3
    en  You'd help! Right. I'm going to be insufferable about this for a week.
    >>  ............................................
    pt  Você ajudaria! Certo. Vou ser insuportável sobre isso por uma semana.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that? Then it's the first time it's been more than mine! Extraordinary day.
    >>  ............................................
    pt  Você fala sério? Então é a primeira vez que isso é mais que meu! Dia extraordinário.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.pledge_help/2
    en  Two of us on it. That's not a dream any more, that's a conspiracy. Much better.
    >>  ............................................
    pt  Dois nisso. Não é mais sonho, é conspiração. Muito melhor.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.pledge_help/3
    en  You'd help! Right. I'm going to be insufferable about this for a week.
    >>  ............................................
    pt  Você ajudaria! Certo. Vou ser insuportável sobre isso por uma semana.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that. Then it's more than mine, and things that are shared last longer.
    >>  ............................................
    pt  Você fala sério. Então é mais que meu, e o que é dividido dura mais.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. No hurry on it — but two of us is better arithmetic than one.
    >>  ............................................
    pt  Certo. Sem pressa — mas dois é uma conta melhor que um.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then we'll get to it, in time. Time is the part I've always had plenty of.
    >>  ............................................
    pt  Então a gente chega lá, com o tempo. Tempo é o que eu sempre tive de sobra.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that? Nobody's offered before, %1$s. I'd stopped expecting it.
    >>  ............................................
    pt  ...Você fala sério? Ninguém ofereceu antes, %1$s. Eu tinha parado de esperar.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.pledge_help/2
    en  You'd help. I don't know what to do with that and I'd like to keep it anyway.
    >>  ............................................
    pt  Você ajudaria. Não sei o que fazer com isso e quero guardar mesmo assim.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.pledge_help/3
    en  Then it isn't only mine. I've carried it alone so long I'd forgotten it could be otherwise.
    >>  ............................................
    pt  Então não é só meu. Carreguei sozinho tanto tempo que esqueci que podia ser diferente.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.pledge_help/1
    en  ...You mean that. Then it's more than mine now.
    >>  ............................................
    pt  ...Você fala sério. Então agora é mais que meu.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.pledge_help/2
    en  Right. Two of us.
    >>  ............................................
    pt  Certo. Dois de nós.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.pledge_help/3
    en  I'd not expected that. I'll need a moment.
    >>  ............................................
    pt  Eu não esperava isso. Vou precisar de um momento.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that? Then it's the first time it's been more than mine! Extraordinary day.
    >>  ............................................
    pt  Você fala sério? Então é a primeira vez que isso é mais que meu! Dia extraordinário.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.pledge_help/2
    en  Two of us on it. That's not a dream any more, that's a conspiracy. Much better.
    >>  ............................................
    pt  Dois nisso. Não é mais sonho, é conspiração. Muito melhor.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.pledge_help/3
    en  You'd help! Right. I'm going to be insufferable about this for a week.
    >>  ............................................
    pt  Você ajudaria! Certo. Vou ser insuportável sobre isso por uma semana.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.pledge_help/1
    en  You mean that? Then it's the first time it's been more than mine! Extraordinary day.
    >>  ............................................
    pt  Você fala sério? Então é a primeira vez que isso é mais que meu! Dia extraordinário.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.pledge_help/2
    en  Two of us on it. That's not a dream any more, that's a conspiracy. Much better.
    >>  ............................................
    pt  Dois nisso. Não é mais sonho, é conspiração. Muito melhor.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.pledge_help/3
    en  You'd help! Right. I'm going to be insufferable about this for a week.
    >>  ............................................
    pt  Você ajudaria! Certo. Vou ser insuportável sobre isso por uma semana.
    >>  ............................................
```

</details>


### Button `be_honest` — "I can't promise help. But I'm glad you told me."

*stance family `candor` · tone `gentle` · answers the beat(s) `dreams.no_words.to.dreams`, `dreams.respond.ask_more.to.dreams`, `dreams.respond.encourage.to.dreams`, `dreams.respond.realism.landed.to.dreams`, `dreams.respond.realism.polite.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.followup.be_honest` — accepted phrasings: "i cannot promise help"; "i will not promise anything"; "glad you told me, but i cannot promise"
  - the message must contain one of: `cannot`, `promise`, `glad`
  - scored words: `cannot`(1.5), `promise`(1.2), `glad`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.followup.be_honest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.followup.be_honest   [47 chars]
    en  I can't promise help. But I'm glad you told me.
    >>  ............................................
    pt  Não posso prometer ajuda. Mas fico feliz que tenha me contado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.followup.be_honest`, budget `deep`, replay policy `once`
- Does: disposition — respect +5, trust +1  _(recorded under topic `dreams.followup.be_honest`)_
- Does: arc `dreams` — advance to stage 1
- Does: exclusive `dreams.support` -> `honest` (locks the other side out for good)
- Then opens: `conversations.topic.dreams.close.honest`
- …where the player's next choices will be: "Thank you for telling me anyway." | "That took something to say." | "I'll tell you what I want, too." | "I hope you get it."

```text
POOL   dialogue key: dialogue.conversations.dreams.followup.be_honest
WHO    VILLAGER — what the player reads after pressing "I can't promise help. But I'm glad you told me."
       spoken on: conversations.topic.dreams.followup, button `be_honest`
       leaves the player on: conversations.topic.dreams.close.honest
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.followup.be_honest.to.dreams.close.honest`: the villager accepts. Subject `dreams.close.honest`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.followup.be_honest/1   [68 chars]
    en  That's better than a promise you'd break. Thank you for the honesty.
    >>  ............................................
    pt  Isso é melhor que uma promessa que você quebraria. Obrigado pela honestidade.
    >>  ............................................
  dialogue.conversations.dreams.followup.be_honest/2   [53 chars]
    en  Glad I told you. That's enough on its own, most days.
    >>  ............................................
    pt  Que bom que contei. Isso já basta, na maioria dos dias.
    >>  ............................................
  dialogue.conversations.dreams.followup.be_honest/3   [64 chars]
    en  No promises. Good. I've had promises. I'd rather have the truth.
    >>  ............................................
    pt  Sem promessas. Bom. Já tive promessas. Prefiro a verdade.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.followup.be_honest/1
    en  ...That's better than a promise you'd break. I've had those, %1$s.
    >>  ............................................
    pt  ...É melhor que uma promessa que você quebraria. Eu já tive dessas, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.dreams.followup.be_honest/2
    en  Thank you for not saying the easy thing. I'd have believed it and then waited.
    >>  ............................................
    pt  Obrigado por não dizer o fácil. Eu teria acreditado e depois ficado esperando.
    >>  ............................................
  anxious.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest. It costs you something to say and it saves me something later.
    >>  ............................................
    pt  Honesto. Te custa algo dizer e me poupa algo depois.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break. Those wear thin quicker than people think.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria. Essas gastam mais rápido do que se pensa.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.be_honest/2
    en  Honest will outlast enthusiastic. It generally does.
    >>  ............................................
    pt  Honesto dura mais que entusiasmado. Costuma durar.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.be_honest/3
    en  Right. I'd rather a small true thing than a large one that isn't.
    >>  ............................................
    pt  Certo. Prefiro uma coisa pequena e verdadeira a uma grande que não seja.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break. I'll take the honesty.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria. Eu fico com a honestidade.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.be_honest/2
    en  Right. You said what you'd actually do. That's worth more than the other thing.
    >>  ............................................
    pt  Certo. Você disse o que faria de verdade. Vale mais que a outra coisa.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest is enough. I've had promises and they came to nothing.
    >>  ............................................
    pt  Honesto basta. Já tive promessas e não deram em nada.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break. I'll take the honesty.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria. Eu fico com a honestidade.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.be_honest/2
    en  Right. You said what you'd actually do. That's worth more than the other thing.
    >>  ............................................
    pt  Certo. Você disse o que faria de verdade. Vale mais que a outra coisa.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest is enough. I've had promises and they came to nothing.
    >>  ............................................
    pt  Honesto basta. Já tive promessas e não deram em nada.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.be_honest/1
    en  That's better than a promise you'd break, %1$s. Thank you for the honesty.
    >>  ............................................
    pt  Isso é melhor que uma promessa que você quebraria, %1$s. Obrigado pela honestidade.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.be_honest/2
    en  You told me the truth instead of the nice thing. I'll remember which you chose.
    >>  ............................................
    pt  Você me disse a verdade em vez da coisa bonita. Vou lembrar qual você escolheu.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest. And you stayed to say it, which is the part that mattered.
    >>  ............................................
    pt  Honesto. E você ficou pra dizer, que é a parte que importou.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.be_honest/1
    en  That's better than a promise you'd break, %1$s. Thank you for the honesty.
    >>  ............................................
    pt  Isso é melhor que uma promessa que você quebraria, %1$s. Obrigado pela honestidade.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.be_honest/2
    en  You told me the truth instead of the nice thing. I'll remember which you chose.
    >>  ............................................
    pt  Você me disse a verdade em vez da coisa bonita. Vou lembrar qual você escolheu.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest. And you stayed to say it, which is the part that mattered.
    >>  ............................................
    pt  Honesto. E você ficou pra dizer, que é a parte que importou.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.be_honest/1
    en  That's better than a promise you'd break, %1$s. Thank you for the honesty.
    >>  ............................................
    pt  Isso é melhor que uma promessa que você quebraria, %1$s. Obrigado pela honestidade.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.be_honest/2
    en  You told me the truth instead of the nice thing. I'll remember which you chose.
    >>  ............................................
    pt  Você me disse a verdade em vez da coisa bonita. Vou lembrar qual você escolheu.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest. And you stayed to say it, which is the part that mattered.
    >>  ............................................
    pt  Honesto. E você ficou pra dizer, que é a parte que importou.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.be_honest/1
    en  ...That's better than a promise you'd break. I've had those, %1$s.
    >>  ............................................
    pt  ...É melhor que uma promessa que você quebraria. Eu já tive dessas, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.be_honest/2
    en  Thank you for not saying the easy thing. I'd have believed it and then waited.
    >>  ............................................
    pt  Obrigado por não dizer o fácil. Eu teria acreditado e depois ficado esperando.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest. It costs you something to say and it saves me something later.
    >>  ............................................
    pt  Honesto. Te custa algo dizer e me poupa algo depois.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break. I'll take the honesty.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria. Eu fico com a honestidade.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.be_honest/2
    en  Right. You said what you'd actually do. That's worth more than the other thing.
    >>  ............................................
    pt  Certo. Você disse o que faria de verdade. Vale mais que a outra coisa.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest is enough. I've had promises and they came to nothing.
    >>  ............................................
    pt  Honesto basta. Já tive promessas e não deram em nada.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break. I'll take the honesty.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria. Eu fico com a honestidade.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.be_honest/2
    en  Right. You said what you'd actually do. That's worth more than the other thing.
    >>  ............................................
    pt  Certo. Você disse o que faria de verdade. Vale mais que a outra coisa.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest is enough. I've had promises and they came to nothing.
    >>  ............................................
    pt  Honesto basta. Já tive promessas e não deram em nada.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.be_honest/2
    en  Right. Honest. That's enough.
    >>  ............................................
    pt  Certo. Honesto. Basta.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.be_honest/3
    en  I'd rather have that than the other thing.
    >>  ............................................
    pt  Prefiro isso à outra coisa.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break. Those wear thin quicker than people think.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria. Essas gastam mais rápido do que se pensa.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.be_honest/2
    en  Honest will outlast enthusiastic. It generally does.
    >>  ............................................
    pt  Honesto dura mais que entusiasmado. Costuma durar.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.be_honest/3
    en  Right. I'd rather a small true thing than a large one that isn't.
    >>  ............................................
    pt  Certo. Prefiro uma coisa pequena e verdadeira a uma grande que não seja.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.be_honest/2
    en  Right. Honest. That's enough.
    >>  ............................................
    pt  Certo. Honesto. Basta.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.be_honest/3
    en  I'd rather have that than the other thing.
    >>  ............................................
    pt  Prefiro isso à outra coisa.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break. Those wear thin quicker than people think.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria. Essas gastam mais rápido do que se pensa.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.be_honest/2
    en  Honest will outlast enthusiastic. It generally does.
    >>  ............................................
    pt  Honesto dura mais que entusiasmado. Costuma durar.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.be_honest/3
    en  Right. I'd rather a small true thing than a large one that isn't.
    >>  ............................................
    pt  Certo. Prefiro uma coisa pequena e verdadeira a uma grande que não seja.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break! Honestly, that's the nicest thing anyone's done with it.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria! Sinceramente, é a coisa mais gentil que fizeram com isso.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.be_honest/2
    en  You could have said yes and vanished. You didn't. I'm delighted, in a small way.
    >>  ............................................
    pt  Você podia ter dito sim e sumido. Não sumiu. Estou encantado, de um jeito pequeno.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest! Refreshing. Everyone else offers the moon and delivers a Tuesday.
    >>  ............................................
    pt  Honesto! Refrescante. Todo mundo oferece a lua e entrega uma terça-feira.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break! Honestly, that's the nicest thing anyone's done with it.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria! Sinceramente, é a coisa mais gentil que fizeram com isso.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.be_honest/2
    en  You could have said yes and vanished. You didn't. I'm delighted, in a small way.
    >>  ............................................
    pt  Você podia ter dito sim e sumido. Não sumiu. Estou encantado, de um jeito pequeno.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest! Refreshing. Everyone else offers the moon and delivers a Tuesday.
    >>  ............................................
    pt  Honesto! Refrescante. Todo mundo oferece a lua e entrega uma terça-feira.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break. Those wear thin quicker than people think.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria. Essas gastam mais rápido do que se pensa.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.be_honest/2
    en  Honest will outlast enthusiastic. It generally does.
    >>  ............................................
    pt  Honesto dura mais que entusiasmado. Costuma durar.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.be_honest/3
    en  Right. I'd rather a small true thing than a large one that isn't.
    >>  ............................................
    pt  Certo. Prefiro uma coisa pequena e verdadeira a uma grande que não seja.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.be_honest/1
    en  ...That's better than a promise you'd break. I've had those, %1$s.
    >>  ............................................
    pt  ...É melhor que uma promessa que você quebraria. Eu já tive dessas, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.be_honest/2
    en  Thank you for not saying the easy thing. I'd have believed it and then waited.
    >>  ............................................
    pt  Obrigado por não dizer o fácil. Eu teria acreditado e depois ficado esperando.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest. It costs you something to say and it saves me something later.
    >>  ............................................
    pt  Honesto. Te custa algo dizer e me poupa algo depois.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.be_honest/2
    en  Right. Honest. That's enough.
    >>  ............................................
    pt  Certo. Honesto. Basta.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.be_honest/3
    en  I'd rather have that than the other thing.
    >>  ............................................
    pt  Prefiro isso à outra coisa.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break! Honestly, that's the nicest thing anyone's done with it.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria! Sinceramente, é a coisa mais gentil que fizeram com isso.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.be_honest/2
    en  You could have said yes and vanished. You didn't. I'm delighted, in a small way.
    >>  ............................................
    pt  Você podia ter dito sim e sumido. Não sumiu. Estou encantado, de um jeito pequeno.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest! Refreshing. Everyone else offers the moon and delivers a Tuesday.
    >>  ............................................
    pt  Honesto! Refrescante. Todo mundo oferece a lua e entrega uma terça-feira.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.be_honest/1
    en  Better than a promise you'd break! Honestly, that's the nicest thing anyone's done with it.
    >>  ............................................
    pt  Melhor que uma promessa que você quebraria! Sinceramente, é a coisa mais gentil que fizeram com isso.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.be_honest/2
    en  You could have said yes and vanished. You didn't. I'm delighted, in a small way.
    >>  ............................................
    pt  Você podia ter dito sim e sumido. Não sumiu. Estou encantado, de um jeito pequeno.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.be_honest/3
    en  Honest! Refreshing. Everyone else offers the moon and delivers a Tuesday.
    >>  ............................................
    pt  Honesto! Refrescante. Todo mundo oferece a lua e entrega uma terça-feira.
    >>  ............................................
```

</details>


### Button `mock` — "You? Doing that?"

*stance family `dismissal` · tone `hostile` · answers the beat(s) `dreams.no_words.to.dreams`, `dreams.respond.ask_more.to.dreams`, `dreams.respond.encourage.to.dreams`, `dreams.respond.realism.landed.to.dreams`, `dreams.respond.realism.polite.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.followup.mock` — accepted phrasings: "that is ridiculous"; "do not make me laugh"; "you, doing that? ridiculous"
  - the message must contain one of: `ridiculous`, `laugh`
  - scored words: `you`(0.4), `ridiculous`(1.5), `laugh`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.followup.mock
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.followup.mock   [16 chars]
    en  You? Doing that?
    >>  ............................................
    pt  Você? Fazendo isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `dreams.followup.mock`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -5, tension +6  _(recorded under topic `dreams.followup.mock`)_
- Does: arc `dreams` — advance to stage 1
- Does: session `turn`
- Then opens: `conversations.topic.dreams.mocked.close`
- …where the player's next choices will be: "Mention it again. That was mine to be wrong about." | "I laughed because it surprised me, not because it's daft." | "I'll let it be."

```text
POOL   dialogue key: dialogue.conversations.dreams.followup.mock
WHO    VILLAGER — what the player reads after pressing "You? Doing that?"
       spoken on: conversations.topic.dreams.followup, button `mock`
       leaves the player on: conversations.topic.dreams.mocked.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.mocked.open`: the villager hurts. Subject `dreams.ambition`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.followup.mock/1   [67 chars]
    en  ...Yes. Me. Doing that. Thank you for the vote of confidence, %1$s.
    >>  ............................................
    pt  ...Sim. Eu. Fazendo isso. Obrigado pelo voto de confiança, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.followup.mock/2   [42 chars]
    en  I'll not mention it again. To you, anyway.
    >>  ............................................
    pt  Não vou mencionar de novo. Para você, pelo menos.
    >>  ............................................
  dialogue.conversations.dreams.followup.mock/3   [47 chars]
    en  That's the reaction I was afraid of. Well done.
    >>  ............................................
    pt  Era essa a reação que eu temia. Parabéns.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.followup.mock/1
    en  ...I know how it sounds. That's why I don't say it, %1$s.
    >>  ............................................
    pt  ...Eu sei como soa. É por isso que eu não digo, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.dreams.followup.mock/2
    en  Right. Yes. Silly. I'd worked that out on my own.
    >>  ............................................
    pt  Certo. Sim. Bobo. Eu já tinha concluído sozinho.
    >>  ............................................
  anxious.dialogue.conversations.dreams.followup.mock/3
    en  ...You didn't have to say it out loud. I already thought it.
    >>  ............................................
    pt  ...Você não precisava dizer em voz alta. Eu já pensava isso.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.mock/1
    en  ...Aye, it's a big thing to say out loud. I'll say it again in a few years.
    >>  ............................................
    pt  ...É, é grande de dizer em voz alta. Vou dizer de novo daqui a uns anos.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.mock/2
    en  Right. It'll keep. Most things do.
    >>  ............................................
    pt  Certo. Fica pra depois. Quase tudo fica.
    >>  ............................................
  athletic.dialogue.conversations.dreams.followup.mock/3
    en  ...Fair enough. It was only a thought.
    >>  ............................................
    pt  ...Tudo bem. Era só um pensamento.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.mock/1
    en  ...Yes. Me. Doing that. Thank you for the vote of confidence.
    >>  ............................................
    pt  ...Sim. Eu. Fazendo aquilo. Obrigado pelo voto de confiança.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.mock/2
    en  Right. I'll not describe it again.
    >>  ............................................
    pt  Certo. Não descrevo de novo.
    >>  ............................................
  confident.dialogue.conversations.dreams.followup.mock/3
    en  ...Noted. It stays in my head where it's safe.
    >>  ............................................
    pt  ...Anotado. Fica na minha cabeça, onde está seguro.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.mock/1
    en  ...Yes. Me. Doing that. Thank you for the vote of confidence.
    >>  ............................................
    pt  ...Sim. Eu. Fazendo aquilo. Obrigado pelo voto de confiança.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.mock/2
    en  Right. I'll not describe it again.
    >>  ............................................
    pt  Certo. Não descrevo de novo.
    >>  ............................................
  crabby.dialogue.conversations.dreams.followup.mock/3
    en  ...Noted. It stays in my head where it's safe.
    >>  ............................................
    pt  ...Anotado. Fica na minha cabeça, onde está seguro.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.mock/1
    en  ...I told you that because it was you, %1$s.
    >>  ............................................
    pt  ...Eu te contei isso porque era você, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.mock/2
    en  That's the one thing I'd not have expected you to laugh at.
    >>  ............................................
    pt  É a única coisa de que eu não esperava que você risse.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.followup.mock/3
    en  ...Right. I'll not tell you the rest of it.
    >>  ............................................
    pt  ...Certo. Não vou te contar o resto.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.mock/1
    en  ...I told you that because it was you, %1$s.
    >>  ............................................
    pt  ...Eu te contei isso porque era você, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.mock/2
    en  That's the one thing I'd not have expected you to laugh at.
    >>  ............................................
    pt  É a única coisa de que eu não esperava que você risse.
    >>  ............................................
  flirty.dialogue.conversations.dreams.followup.mock/3
    en  ...Right. I'll not tell you the rest of it.
    >>  ............................................
    pt  ...Certo. Não vou te contar o resto.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.mock/1
    en  ...I told you that because it was you, %1$s.
    >>  ............................................
    pt  ...Eu te contei isso porque era você, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.mock/2
    en  That's the one thing I'd not have expected you to laugh at.
    >>  ............................................
    pt  É a única coisa de que eu não esperava que você risse.
    >>  ............................................
  friendly.dialogue.conversations.dreams.followup.mock/3
    en  ...Right. I'll not tell you the rest of it.
    >>  ............................................
    pt  ...Certo. Não vou te contar o resto.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.mock/1
    en  ...I know how it sounds. That's why I don't say it, %1$s.
    >>  ............................................
    pt  ...Eu sei como soa. É por isso que eu não digo, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.mock/2
    en  Right. Yes. Silly. I'd worked that out on my own.
    >>  ............................................
    pt  Certo. Sim. Bobo. Eu já tinha concluído sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.followup.mock/3
    en  ...You didn't have to say it out loud. I already thought it.
    >>  ............................................
    pt  ...Você não precisava dizer em voz alta. Eu já pensava isso.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.mock/1
    en  ...Yes. Me. Doing that. Thank you for the vote of confidence.
    >>  ............................................
    pt  ...Sim. Eu. Fazendo aquilo. Obrigado pelo voto de confiança.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.mock/2
    en  Right. I'll not describe it again.
    >>  ............................................
    pt  Certo. Não descrevo de novo.
    >>  ............................................
  greedy.dialogue.conversations.dreams.followup.mock/3
    en  ...Noted. It stays in my head where it's safe.
    >>  ............................................
    pt  ...Anotado. Fica na minha cabeça, onde está seguro.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.mock/1
    en  ...Yes. Me. Doing that. Thank you for the vote of confidence.
    >>  ............................................
    pt  ...Sim. Eu. Fazendo aquilo. Obrigado pelo voto de confiança.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.mock/2
    en  Right. I'll not describe it again.
    >>  ............................................
    pt  Certo. Não descrevo de novo.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.followup.mock/3
    en  ...Noted. It stays in my head where it's safe.
    >>  ............................................
    pt  ...Anotado. Fica na minha cabeça, onde está seguro.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.mock/1
    en  ...Right.
    >>  ............................................
    pt  ...Certo.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.mock/2
    en  I'd not said it out loud before. That's the last time.
    >>  ............................................
    pt  Eu nunca tinha dito em voz alta. Foi a última vez.
    >>  ............................................
  introverted.dialogue.conversations.dreams.followup.mock/3
    en  ...It sounded better in my head. It usually does.
    >>  ............................................
    pt  ...Soava melhor na minha cabeça. Normalmente soa.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.mock/1
    en  ...Aye, it's a big thing to say out loud. I'll say it again in a few years.
    >>  ............................................
    pt  ...É, é grande de dizer em voz alta. Vou dizer de novo daqui a uns anos.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.mock/2
    en  Right. It'll keep. Most things do.
    >>  ............................................
    pt  Certo. Fica pra depois. Quase tudo fica.
    >>  ............................................
  lazy.dialogue.conversations.dreams.followup.mock/3
    en  ...Fair enough. It was only a thought.
    >>  ............................................
    pt  ...Tudo bem. Era só um pensamento.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.mock/1
    en  ...Right.
    >>  ............................................
    pt  ...Certo.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.mock/2
    en  I'd not said it out loud before. That's the last time.
    >>  ............................................
    pt  Eu nunca tinha dito em voz alta. Foi a última vez.
    >>  ............................................
  odd.dialogue.conversations.dreams.followup.mock/3
    en  ...It sounded better in my head. It usually does.
    >>  ............................................
    pt  ...Soava melhor na minha cabeça. Normalmente soa.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.mock/1
    en  ...Aye, it's a big thing to say out loud. I'll say it again in a few years.
    >>  ............................................
    pt  ...É, é grande de dizer em voz alta. Vou dizer de novo daqui a uns anos.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.mock/2
    en  Right. It'll keep. Most things do.
    >>  ............................................
    pt  Certo. Fica pra depois. Quase tudo fica.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.followup.mock/3
    en  ...Fair enough. It was only a thought.
    >>  ............................................
    pt  ...Tudo bem. Era só um pensamento.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.mock/1
    en  ...Ha! Yes. Absurd. Me, of all people. Very funny, %1$s.
    >>  ............................................
    pt  ...Ha! Sim. Absurdo. Eu, logo eu. Muito engraçado, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.mock/2
    en  Right, well. I'll go back to keeping it to myself, then.
    >>  ............................................
    pt  Certo, bom. Vou voltar a guardar pra mim, então.
    >>  ............................................
  peppy.dialogue.conversations.dreams.followup.mock/3
    en  ...Yes, hilarious. I've laughed. We can move on.
    >>  ............................................
    pt  ...Sim, hilário. Eu ri. Podemos seguir.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.mock/1
    en  ...Ha! Yes. Absurd. Me, of all people. Very funny, %1$s.
    >>  ............................................
    pt  ...Ha! Sim. Absurdo. Eu, logo eu. Muito engraçado, %1$s.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.mock/2
    en  Right, well. I'll go back to keeping it to myself, then.
    >>  ............................................
    pt  Certo, bom. Vou voltar a guardar pra mim, então.
    >>  ............................................
  playful.dialogue.conversations.dreams.followup.mock/3
    en  ...Yes, hilarious. I've laughed. We can move on.
    >>  ............................................
    pt  ...Sim, hilário. Eu ri. Podemos seguir.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.mock/1
    en  ...Aye, it's a big thing to say out loud. I'll say it again in a few years.
    >>  ............................................
    pt  ...É, é grande de dizer em voz alta. Vou dizer de novo daqui a uns anos.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.mock/2
    en  Right. It'll keep. Most things do.
    >>  ............................................
    pt  Certo. Fica pra depois. Quase tudo fica.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.followup.mock/3
    en  ...Fair enough. It was only a thought.
    >>  ............................................
    pt  ...Tudo bem. Era só um pensamento.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.mock/1
    en  ...I know how it sounds. That's why I don't say it, %1$s.
    >>  ............................................
    pt  ...Eu sei como soa. É por isso que eu não digo, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.mock/2
    en  Right. Yes. Silly. I'd worked that out on my own.
    >>  ............................................
    pt  Certo. Sim. Bobo. Eu já tinha concluído sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.followup.mock/3
    en  ...You didn't have to say it out loud. I already thought it.
    >>  ............................................
    pt  ...Você não precisava dizer em voz alta. Eu já pensava isso.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.mock/1
    en  ...Right.
    >>  ............................................
    pt  ...Certo.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.mock/2
    en  I'd not said it out loud before. That's the last time.
    >>  ............................................
    pt  Eu nunca tinha dito em voz alta. Foi a última vez.
    >>  ............................................
  shy.dialogue.conversations.dreams.followup.mock/3
    en  ...It sounded better in my head. It usually does.
    >>  ............................................
    pt  ...Soava melhor na minha cabeça. Normalmente soa.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.mock/1
    en  ...Ha! Yes. Absurd. Me, of all people. Very funny, %1$s.
    >>  ............................................
    pt  ...Ha! Sim. Absurdo. Eu, logo eu. Muito engraçado, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.mock/2
    en  Right, well. I'll go back to keeping it to myself, then.
    >>  ............................................
    pt  Certo, bom. Vou voltar a guardar pra mim, então.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.followup.mock/3
    en  ...Yes, hilarious. I've laughed. We can move on.
    >>  ............................................
    pt  ...Sim, hilário. Eu ri. Podemos seguir.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.mock/1
    en  ...Ha! Yes. Absurd. Me, of all people. Very funny, %1$s.
    >>  ............................................
    pt  ...Ha! Sim. Absurdo. Eu, logo eu. Muito engraçado, %1$s.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.mock/2
    en  Right, well. I'll go back to keeping it to myself, then.
    >>  ............................................
    pt  Certo, bom. Vou voltar a guardar pra mim, então.
    >>  ............................................
  witty.dialogue.conversations.dreams.followup.mock/3
    en  ...Yes, hilarious. I've laughed. We can move on.
    >>  ............................................
    pt  ...Sim, hilário. Eu ri. Podemos seguir.
    >>  ............................................
```

</details>


### Button `leave` — "I hope you get it."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.no_words.to.dreams`, `dreams.respond.ask_more.to.dreams`, `dreams.respond.encourage.to.dreams`, `dreams.respond.realism.landed.to.dreams`, `dreams.respond.realism.polite.to.dreams` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.followup.leave   [18 chars]
    en  I hope you get it.
    >>  ............................................
    pt  Espero que consiga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.respond.leave
WHO    VILLAGER — what the player reads after pressing "I hope you get it."
       spoken on: conversations.topic.dreams.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.respond.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.dreams.respond / leave
```

```text
  dialogue.conversations.dreams.respond.leave/1   [42 chars]
    en  So do I. Thank you for not laughing at it.
    >>  ............................................
    pt  Eu também. Obrigado por não rir disso.
    >>  ............................................
  dialogue.conversations.dreams.respond.leave/2   [24 chars]
    en  Quite. Off you go, %1$s.
    >>  ............................................
    pt  Exato. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.respond.leave/3   [40 chars]
    en  Very well. Back to the actual day, then.
    >>  ............................................
    pt  Muito bem. De volta ao dia de verdade, então.
    >>  ............................................
```

---


## `conversations.topic.dreams.guarded.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `dreams`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.deflect.personal` — e.g. "That's... a bit close to the bone for someone I barely know."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.guarded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.guarded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.guarded.respond   [52 chars]
    en  It's half-built. I'd rather it wasn't looked at yet.
    >>  ............................................
    pt  Está pela metade. Prefiro que ninguém olhe ainda.
    >>  ............................................
```


### Button `respect` — "Then build it in peace."

*stance family `restraint` · tone `plain` · answers the beat(s) `deflect.personal.to.dreams.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.guarded.respect` — accepted phrasings: "that is yours to keep"; "keep it to yourself"; "that dream is yours"
  - the message must contain one of: `yours`, `keep`
  - scored words: `yours`(1.5), `keep`(1.2), `dream`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.guarded.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.guarded.respond.respect   [23 chars]
    en  Then build it in peace.
    >>  ............................................
    pt  Então construa em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.guarded.respect`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `dreams.guarded.respect`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.guarded.respect
WHO    VILLAGER — what the player reads after pressing "Then build it in peace."
       spoken on: conversations.topic.dreams.guarded.respond, button `respect`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.guarded.respect.terminal`: the villager deflects. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.guarded.respect/1   [89 chars]
    en  ...Thank you. It's easier to keep building when nobody's stood over it judging the shape.
    >>  ............................................
    pt  ...Obrigado. É mais fácil continuar construindo quando ninguém está julgando o formato.
    >>  ............................................
  dialogue.conversations.dreams.guarded.respect/2   [71 chars]
    en  Just so. Ask me again when there's something to actually look at, %1$s.
    >>  ............................................
    pt  Pois é. Me pergunte de novo quando tiver algo para de fato olhar, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.guarded.respect/3   [49 chars]
    en  Good. Half-built things don't survive an opinion.
    >>  ............................................
    pt  Bom. Coisa pela metade não sobrevive a uma opinião.
    >>  ............................................
```


### Button `ask_safer` — "Tell me a smaller one, then."

*stance family `curiosity` · tone `gentle` · answers the beat(s) `deflect.personal.to.dreams.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.guarded.ask_safer` — accepted phrasings: "tell me something lighter"; "something easier then"; "let us keep it light"
  - the message must contain one of: `lighter`, `easier`
  - scored words: `lighter`(1.5), `easier`(1.2), `dream`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.guarded.respond.ask_safer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.guarded.respond.ask_safer   [28 chars]
    en  Tell me a smaller one, then.
    >>  ............................................
    pt  Então me conta um menor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `dreams.guarded.ask_safer`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.guarded.ask_safer
WHO    VILLAGER — what the player reads after pressing "Tell me a smaller one, then."
       spoken on: conversations.topic.dreams.guarded.respond, button `ask_safer`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.guarded.ask_safer.terminal`: the villager deflects. Subject `dreams.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.guarded.ask_safer/1   [69 chars]
    en  Ask me what I did today. That one's free and it's mostly fence posts.
    >>  ............................................
    pt  Me pergunte o que eu fiz hoje. Essa é de graça e é quase toda mourão de cerca.
    >>  ............................................
  dialogue.conversations.dreams.guarded.ask_safer/2   [80 chars]
    en  The small wants I'll give you. It's the big one I'm keeping under a cloth, %1$s.
    >>  ............................................
    pt  Os desejos pequenos eu dou. É o grande que eu guardo sob um pano, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.guarded.ask_safer/3   [74 chars]
    en  Something I've already done, then. Those are finished and safe to look at.
    >>  ............................................
    pt  Algo que eu já fiz, então. Essas estão prontas e são seguras de olhar.
    >>  ............................................
```


### Button `press` — "Let me hear it anyway."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `deflect.personal.to.dreams.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.guarded.press` — accepted phrasings: "come on, you can tell me"; "tell me the dream"; "go on, tell me"
  - the message must contain one of: `come`, `tell`
  - scored words: `come`(1.2), `tell`(1.0), `dream`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.guarded.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.guarded.respond.press   [22 chars]
    en  Let me hear it anyway.
    >>  ............................................
    pt  Me deixa ouvir mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `dreams.guarded.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5  _(recorded under topic `dreams.guarded.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.guarded.press
WHO    VILLAGER — what the player reads after pressing "Let me hear it anyway."
       spoken on: conversations.topic.dreams.guarded.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.guarded.press.terminal`: the villager resists. Subject `dreams.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.guarded.press/1   [77 chars]
    en  Said out loud too early, a thing like that goes flat. I've watched it happen.
    >>  ............................................
    pt  Dito em voz alta cedo demais, uma coisa dessas murcha. Já vi acontecer.
    >>  ............................................
  dialogue.conversations.dreams.guarded.press/2   [89 chars]
    en  You want to hear it so you can tell me whether it's sensible. I know how this goes, %1$s.
    >>  ............................................
    pt  Você quer ouvir para me dizer se é sensato. Eu conheço esse caminho, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.guarded.press/3   [66 chars]
    en  No. It's half-built. Nobody gets shown a house with no roof on it.
    >>  ............................................
    pt  Não. Está pela metade. Ninguém mostra uma casa sem telhado.
    >>  ............................................
```


### Button `leave` — "Another time, when it's further on."

*stance family `exit` · tone `plain` · answers the beat(s) `deflect.personal.to.dreams.guarded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.guarded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.guarded.respond.leave   [35 chars]
    en  Another time, when it's further on.
    >>  ............................................
    pt  Outra hora, quando estiver mais adiantado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.guarded.leave
WHO    VILLAGER — what the player reads after pressing "Another time, when it's further on."
       spoken on: conversations.topic.dreams.guarded.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.guarded.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.guarded.leave/1   [22 chars]
    en  Aye. No hard feelings.
    >>  ............................................
    pt  Tá. Sem ressentimento.
    >>  ............................................
  dialogue.conversations.dreams.guarded.leave/2   [40 chars]
    en  Off you go. We'll get there or we won't.
    >>  ............................................
    pt  Pode ir. A gente chega lá ou não.
    >>  ............................................
  dialogue.conversations.dreams.guarded.leave/3   [14 chars]
    en  Just so, %1$s.
    >>  ............................................
    pt  Exato, %1$s.
    >>  ............................................
```

---


## `conversations.topic.dreams.mocked.close`

**Reached from 1 route(s):** `conversations.topic.dreams.followup` / `mock`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.followup.mock` — e.g. "...Yes. Me. Doing that. Thank you for the vote of confidence, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.mocked.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.mocked.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.mocked.close   [26 chars]
    en  I'll not mention it again.
    >>  ............................................
    pt  Não menciono mais.
    >>  ............................................
```


### Button `apologize` — "Mention it again. That was mine to be wrong about."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `dreams.mocked.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.mocked.apologize` — accepted phrasings: "mention it again. that was mine to be wrong about"
  - the message must contain one of: `mention`, `wrong`
  - scored words: `mention`(1.5), `wrong`(1.0), `again`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.mocked.close.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.mocked.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.mocked.close.apologize   [50 chars]
    en  Mention it again. That was mine to be wrong about.
    >>  ............................................
    pt  Mencione sim. O errado ali fui eu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `dreams.mocked.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.mocked.apologize
WHO    VILLAGER — what the player reads after pressing "Mention it again. That was mine to be wrong about."
       spoken on: conversations.topic.dreams.mocked.close, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.mocked.apologize`: the villager qualifys. Subject `dreams.ambition`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.mocked.apologize/1   [44 chars]
    en  ...Hm. Ask me in a month and I might. Might.
    >>  ............................................
    pt  ...Hm. Me pergunte daqui a um mês e talvez eu mencione. Talvez.
    >>  ............................................
  dialogue.conversations.dreams.mocked.apologize/2   [74 chars]
    en  You were wrong about it, aye. Say so once more and I'll believe you, %1$s.
    >>  ............................................
    pt  Você estava errado, sim. Diga mais uma vez e eu acredito, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.mocked.apologize/3   [73 chars]
    en  Then I'll keep it where it was and pretend the last minute didn't happen.
    >>  ............................................
    pt  Então eu guardo onde estava e finjo que o último minuto não aconteceu.
    >>  ............................................
```


### Button `explain` — "I laughed because it surprised me, not because it's daft."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `dreams.mocked.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.mocked.explain` — accepted phrasings: "i laughed because it surprised me, not because it's daft"
  - the message must contain one of: `laughed`, `surprised`, `daft`
  - scored words: `laughed`(1.5), `surprised`(1.5), `daft`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.mocked.close.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.mocked.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.mocked.close.explain   [57 chars]
    en  I laughed because it surprised me, not because it's daft.
    >>  ............................................
    pt  Eu ri porque me surpreendeu, não porque é bobo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `dreams.mocked.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.mocked.explain
WHO    VILLAGER — what the player reads after pressing "I laughed because it surprised me, not because it's daft."
       spoken on: conversations.topic.dreams.mocked.close, button `explain`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.mocked.explain`: the villager qualifys. Subject `dreams.ambition`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.mocked.explain/1   [62 chars]
    en  ...Surprised. That's a better word than the one I'd landed on.
    >>  ............................................
    pt  ...Surpresa. É uma palavra melhor que a que eu tinha escolhido.
    >>  ............................................
  dialogue.conversations.dreams.mocked.explain/2   [75 chars]
    en  There's a difference, and you'll forgive me for not hearing it at the time.
    >>  ............................................
    pt  Tem diferença, e você me perdoe por não ter ouvido na hora.
    >>  ............................................
  dialogue.conversations.dreams.mocked.explain/3   [71 chars]
    en  Then next time make the surprised noise instead of the other one, %1$s.
    >>  ............................................
    pt  Então da próxima faça o barulho de surpresa em vez do outro, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let it be."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `dreams.mocked.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.mocked.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.mocked.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.mocked.close.leave   [15 chars]
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
POOL   dialogue key: dialogue.conversations.dreams.mocked.leave
WHO    VILLAGER — what the player reads after pressing "I'll let it be."
       spoken on: conversations.topic.dreams.mocked.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.mocked.leave`: the villager accepts. Subject `dreams.ambition`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.mocked.leave/1   [13 chars]
    en  So it is. Do.
    >>  ............................................
    pt  É assim mesmo. Deixe.
    >>  ............................................
  dialogue.conversations.dreams.mocked.leave/2   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.mocked.leave/3   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
```

---


## `conversations.topic.dreams.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `dreams`; `conversations.cat.personal` / `dreams`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.first` — e.g. "Don't laugh. I want to see the ocean once. Just once. Smell the salt."
- `conversations.dreams.revisit` — e.g. "Still dreaming about what I told you. It hasn't gotten smaller."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.respond   [31 chars]
    en  That's the one. Daft, probably.
    >>  ............................................
    pt  É esse. Bobo, provavelmente.
    >>  ............................................
```


### Button `encourage` — "You should chase that."

*stance family `encouragement` · tone `plain` · answers the beat(s) `dreams.first.to.dreams`, `dreams.revisit.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.respond.encourage` — accepted phrasings: "you should chase that"; "go and pursue it"; "chase it"
  - the message must contain one of: `chase`, `pursue`, `should`
  - scored words: `chase`(1.5), `pursue`(1.5), `should`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.respond.encourage   [22 chars]
    en  You should chase that.
    >>  ............................................
    pt  Você devia correr atrás disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `dreams.respond.encourage`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, respect +1  _(recorded under topic `dreams.respond.encourage`)_
- Then opens: `conversations.topic.dreams.followup`
- …where the player's next choices will be: "I'll help you get there." | "I can't promise help. But I'm glad you told me." | "You? Doing that?" | "I hope you get it."

```text
POOL   dialogue key: dialogue.conversations.dreams.respond.encourage
WHO    VILLAGER — what the player reads after pressing "You should chase that."
       spoken on: conversations.topic.dreams.respond, button `encourage`
       leaves the player on: conversations.topic.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.respond.encourage.to.dreams`: the villager accepts. Subject `dreams`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.respond.encourage/1   [67 chars]
    en  ...Should I? Saying it out loud to someone makes it feel less daft.
    >>  ............................................
    pt  ...Devia? Dizer em voz alta para alguém faz parecer menos bobo.
    >>  ............................................
  dialogue.conversations.dreams.respond.encourage/2   [67 chars]
    en  Everyone else tells me to be sensible. You're the first who didn't.
    >>  ............................................
    pt  Todo mundo me diz para ser sensato. Você é o primeiro que não disse.
    >>  ............................................
  dialogue.conversations.dreams.respond.encourage/3   [65 chars]
    en  Chase it. Right. I'll think about what that would even look like.
    >>  ............................................
    pt  Correr atrás. Certo. Vou pensar em como isso seria.
    >>  ............................................
```


### Button `ask_more` — "Tell me more about it."

*stance family `curiosity` · tone `plain` · answers the beat(s) `dreams.first.to.dreams`, `dreams.revisit.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.respond.ask_more` — accepted phrasings: "tell me more about it"; "give me the detail"; "say more about it"
  - the message must contain one of: `more`, `detail`
  - scored words: `more`(1.2), `about`(0.5), `detail`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.respond.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.respond.ask_more   [22 chars]
    en  Tell me more about it.
    >>  ............................................
    pt  Me conta mais sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.respond.ask_more`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — familiarity +4  _(recorded under topic `dreams.respond.ask_more`)_
- Then opens: `conversations.topic.dreams.followup`
- …where the player's next choices will be: "I'll help you get there." | "I can't promise help. But I'm glad you told me." | "You? Doing that?" | "I hope you get it."

```text
POOL   dialogue key: dialogue.conversations.dreams.respond.ask_more
WHO    VILLAGER — what the player reads after pressing "Tell me more about it."
       spoken on: conversations.topic.dreams.respond, button `ask_more`
       leaves the player on: conversations.topic.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.respond.ask_more.to.dreams`: the villager explains. Subject `dreams`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.respond.ask_more/1   [47 chars]
    en  You want the details? Nobody wants the details.
    >>  ............................................
    pt  Você quer os detalhes? Ninguém quer os detalhes.
    >>  ............................................
  dialogue.conversations.dreams.respond.ask_more/2   [70 chars]
    en  Right — so it starts with the workshop, and gets ambitious from there.
    >>  ............................................
    pt  Certo — então começa com a oficina, e fica ambicioso a partir daí.
    >>  ............................................
  dialogue.conversations.dreams.respond.ask_more/3   [55 chars]
    en  Careful. Ask twice and you'll get the whole plan, %1$s.
    >>  ............................................
    pt  Cuidado. Pergunte duas vezes e você recebe o plano inteiro, %1$s.
    >>  ............................................
```


### Button `realism` — "That's a long way from here."

*stance family `candor` · tone `blunt` · answers the beat(s) `dreams.first.to.dreams`, `dreams.revisit.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.respond.realism` — accepted phrasings: "that is a long way from here"; "that is far off"; "a long way to go"
  - the message must contain one of: `far`, `long`, `way`
  - scored words: `far`(1.5), `long`(1.2), `way`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.respond.realism
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.respond.realism   [28 chars]
    en  That's a long way from here.
    >>  ............................................
    pt  Isso é muito longe daqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts +1** — decision id `dreams.respond.realism`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `dreams.respond.realism`)_
- Then opens: `conversations.topic.dreams.followup`
- …where the player's next choices will be: "I'll help you get there." | "I can't promise help. But I'm glad you told me." | "You? Doing that?" | "I hope you get it."

```text
POOL   dialogue key: dialogue.conversations.dreams.respond.realism.landed
WHO    VILLAGER — what the player reads after pressing "That's a long way from here."
       spoken on: conversations.topic.dreams.respond, button `realism`
       leaves the player on: conversations.topic.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.respond.realism.landed.to.dreams`: the villager accepts. Subject `dreams`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.respond.realism.landed/1   [58 chars]
    en  It is. That's the useful thing to hear and nobody says it.
    >>  ............................................
    pt  É. É isso que é útil ouvir e ninguém diz.
    >>  ............................................
  dialogue.conversations.dreams.respond.realism.landed/2   [74 chars]
    en  A long way. Aye. Better to know the distance than pretend there isn't one.
    >>  ............................................
    pt  Muito longe. É. Melhor saber a distância do que fingir que não existe.
    >>  ............................................
  dialogue.conversations.dreams.respond.realism.landed/3   [71 chars]
    en  Straight with me. Good. I'd rather that than encouragement I can't use.
    >>  ............................................
    pt  Sincero comigo. Bom. Prefiro isso a incentivo que não me serve.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`
- Does: **hearts -1** — decision id `dreams.respond.realism`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +4  _(recorded under topic `dreams.respond.realism`)_
- Does: session `turn`
- Then opens: `conversations.topic.dreams.deflated.followup`
- …where the player's next choices will be: "You didn't need the distance measured." | "I only meant I'd rather you weren't hurt by it." | "I'll leave it alone."

```text
POOL   dialogue key: dialogue.conversations.dreams.respond.realism.flat
WHO    VILLAGER — what the player reads after pressing "That's a long way from here."
       spoken on: conversations.topic.dreams.respond, button `realism`
       leaves the player on: conversations.topic.dreams.deflated.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.rebuked`: the villager refuses. Subject `dreams.ambition`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   this is the line that establishes `player:measured_the_dream` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.respond.realism.flat/1   [55 chars]
    en  ...I know how far it is. I didn't need it measured out.
    >>  ............................................
    pt  ...Eu sei o quão longe é. Não precisava que medissem para mim.
    >>  ............................................
  dialogue.conversations.dreams.respond.realism.flat/2   [55 chars]
    en  Thank you. I'd almost forgotten to feel small about it.
    >>  ............................................
    pt  Obrigado. Eu quase tinha esquecido de me sentir pequeno por isso.
    >>  ............................................
  dialogue.conversations.dreams.respond.realism.flat/3   [72 chars]
    en  Everything's a long way from here, %1$s. That's the point of wanting it.
    >>  ............................................
    pt  Tudo é longe daqui, %1$s. É esse o sentido de querer.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know. I know exactly how far. That's the part I don't sleep about.
    >>  ............................................
    pt  ...Eu sei. Eu sei exatamente quão longe. É a parte que me tira o sono.
    >>  ............................................
  anxious.dialogue.conversations.dreams.respond.realism.flat/2
    en  You've said the thing I say to myself at three in the morning, %1$s.
    >>  ............................................
    pt  Você disse o que eu digo pra mim às três da manhã, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. Yes. It's too far. It's always been too far.
    >>  ............................................
    pt  ...Certo. Sim. É longe demais. Sempre foi longe demais.
    >>  ............................................
  athletic.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...Aye, it's far. It'll still be far next year, and I'll still want it.
    >>  ............................................
    pt  ...É, é longe. Vai continuar longe ano que vem, e eu vou continuar querendo.
    >>  ............................................
  athletic.dialogue.conversations.dreams.respond.realism.flat/2
    en  I know the distance. Knowing it hasn't made me want it less.
    >>  ............................................
    pt  Eu sei a distância. Saber não me fez querer menos.
    >>  ............................................
  athletic.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. Far things take longer. That's all that means.
    >>  ............................................
    pt  ...Certo. Coisas longe levam mais tempo. É só isso que significa.
    >>  ............................................
  confident.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know how far it is. I didn't need it measured out.
    >>  ............................................
    pt  ...Eu sei a distância. Eu não precisava que medissem.
    >>  ............................................
  confident.dialogue.conversations.dreams.respond.realism.flat/2
    en  I've done the arithmetic. Repeating it doesn't change it.
    >>  ............................................
    pt  Já fiz a conta. Repetir não muda.
    >>  ............................................
  confident.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I know. That's why I said it quietly.
    >>  ............................................
    pt  ...Certo. Eu sei. Por isso eu disse baixo.
    >>  ............................................
  crabby.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know how far it is. I didn't need it measured out.
    >>  ............................................
    pt  ...Eu sei a distância. Eu não precisava que medissem.
    >>  ............................................
  crabby.dialogue.conversations.dreams.respond.realism.flat/2
    en  I've done the arithmetic. Repeating it doesn't change it.
    >>  ............................................
    pt  Já fiz a conta. Repetir não muda.
    >>  ............................................
  crabby.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I know. That's why I said it quietly.
    >>  ............................................
    pt  ...Certo. Eu sei. Por isso eu disse baixo.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know, %1$s. I'd hoped you'd ask about it before you measured it.
    >>  ............................................
    pt  ...Eu sei, %1$s. Eu esperava que você perguntasse antes de medir.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.respond.realism.flat/2
    en  That's the cold half. I'd been telling you the other half.
    >>  ............................................
    pt  É a metade fria. Eu estava te contando a outra metade.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I'll bring you the finished version next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu trago a versão pronta.
    >>  ............................................
  flirty.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know, %1$s. I'd hoped you'd ask about it before you measured it.
    >>  ............................................
    pt  ...Eu sei, %1$s. Eu esperava que você perguntasse antes de medir.
    >>  ............................................
  flirty.dialogue.conversations.dreams.respond.realism.flat/2
    en  That's the cold half. I'd been telling you the other half.
    >>  ............................................
    pt  É a metade fria. Eu estava te contando a outra metade.
    >>  ............................................
  flirty.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I'll bring you the finished version next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu trago a versão pronta.
    >>  ............................................
  friendly.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know, %1$s. I'd hoped you'd ask about it before you measured it.
    >>  ............................................
    pt  ...Eu sei, %1$s. Eu esperava que você perguntasse antes de medir.
    >>  ............................................
  friendly.dialogue.conversations.dreams.respond.realism.flat/2
    en  That's the cold half. I'd been telling you the other half.
    >>  ............................................
    pt  É a metade fria. Eu estava te contando a outra metade.
    >>  ............................................
  friendly.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I'll bring you the finished version next time.
    >>  ............................................
    pt  ...Certo. Da próxima eu trago a versão pronta.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know. I know exactly how far. That's the part I don't sleep about.
    >>  ............................................
    pt  ...Eu sei. Eu sei exatamente quão longe. É a parte que me tira o sono.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.respond.realism.flat/2
    en  You've said the thing I say to myself at three in the morning, %1$s.
    >>  ............................................
    pt  Você disse o que eu digo pra mim às três da manhã, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. Yes. It's too far. It's always been too far.
    >>  ............................................
    pt  ...Certo. Sim. É longe demais. Sempre foi longe demais.
    >>  ............................................
  greedy.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know how far it is. I didn't need it measured out.
    >>  ............................................
    pt  ...Eu sei a distância. Eu não precisava que medissem.
    >>  ............................................
  greedy.dialogue.conversations.dreams.respond.realism.flat/2
    en  I've done the arithmetic. Repeating it doesn't change it.
    >>  ............................................
    pt  Já fiz a conta. Repetir não muda.
    >>  ............................................
  greedy.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I know. That's why I said it quietly.
    >>  ............................................
    pt  ...Certo. Eu sei. Por isso eu disse baixo.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know how far it is. I didn't need it measured out.
    >>  ............................................
    pt  ...Eu sei a distância. Eu não precisava que medissem.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.respond.realism.flat/2
    en  I've done the arithmetic. Repeating it doesn't change it.
    >>  ............................................
    pt  Já fiz a conta. Repetir não muda.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I know. That's why I said it quietly.
    >>  ............................................
    pt  ...Certo. Eu sei. Por isso eu disse baixo.
    >>  ............................................
  introverted.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know how far it is.
    >>  ............................................
    pt  ...Eu sei a distância.
    >>  ............................................
  introverted.dialogue.conversations.dreams.respond.realism.flat/2
    en  Yes. I've counted the days it would take. More than once.
    >>  ............................................
    pt  Sim. Já contei os dias que levaria. Mais de uma vez.
    >>  ............................................
  introverted.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I'd worked that out.
    >>  ............................................
    pt  ...Certo. Eu já tinha concluído.
    >>  ............................................
  lazy.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...Aye, it's far. It'll still be far next year, and I'll still want it.
    >>  ............................................
    pt  ...É, é longe. Vai continuar longe ano que vem, e eu vou continuar querendo.
    >>  ............................................
  lazy.dialogue.conversations.dreams.respond.realism.flat/2
    en  I know the distance. Knowing it hasn't made me want it less.
    >>  ............................................
    pt  Eu sei a distância. Saber não me fez querer menos.
    >>  ............................................
  lazy.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. Far things take longer. That's all that means.
    >>  ............................................
    pt  ...Certo. Coisas longe levam mais tempo. É só isso que significa.
    >>  ............................................
  odd.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know how far it is.
    >>  ............................................
    pt  ...Eu sei a distância.
    >>  ............................................
  odd.dialogue.conversations.dreams.respond.realism.flat/2
    en  Yes. I've counted the days it would take. More than once.
    >>  ............................................
    pt  Sim. Já contei os dias que levaria. Mais de uma vez.
    >>  ............................................
  odd.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I'd worked that out.
    >>  ............................................
    pt  ...Certo. Eu já tinha concluído.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...Aye, it's far. It'll still be far next year, and I'll still want it.
    >>  ............................................
    pt  ...É, é longe. Vai continuar longe ano que vem, e eu vou continuar querendo.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.respond.realism.flat/2
    en  I know the distance. Knowing it hasn't made me want it less.
    >>  ............................................
    pt  Eu sei a distância. Saber não me fez querer menos.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. Far things take longer. That's all that means.
    >>  ............................................
    pt  ...Certo. Coisas longe levam mais tempo. É só isso que significa.
    >>  ............................................
  peppy.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...Ah, the practicalities. My favourite part of any idea.
    >>  ............................................
    pt  ...Ah, os detalhes práticos. Minha parte favorita de qualquer ideia.
    >>  ............................................
  peppy.dialogue.conversations.dreams.respond.realism.flat/2
    en  Right! Distances. Yes. I'd forgotten those existed.
    >>  ............................................
    pt  Certo! Distâncias. Sim. Eu tinha esquecido que existiam.
    >>  ............................................
  peppy.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Lovely. Now it's a sum instead of a plan.
    >>  ............................................
    pt  ...Ótimo. Agora é uma conta em vez de um plano.
    >>  ............................................
  playful.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...Ah, the practicalities. My favourite part of any idea.
    >>  ............................................
    pt  ...Ah, os detalhes práticos. Minha parte favorita de qualquer ideia.
    >>  ............................................
  playful.dialogue.conversations.dreams.respond.realism.flat/2
    en  Right! Distances. Yes. I'd forgotten those existed.
    >>  ............................................
    pt  Certo! Distâncias. Sim. Eu tinha esquecido que existiam.
    >>  ............................................
  playful.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Lovely. Now it's a sum instead of a plan.
    >>  ............................................
    pt  ...Ótimo. Agora é uma conta em vez de um plano.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...Aye, it's far. It'll still be far next year, and I'll still want it.
    >>  ............................................
    pt  ...É, é longe. Vai continuar longe ano que vem, e eu vou continuar querendo.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.respond.realism.flat/2
    en  I know the distance. Knowing it hasn't made me want it less.
    >>  ............................................
    pt  Eu sei a distância. Saber não me fez querer menos.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. Far things take longer. That's all that means.
    >>  ............................................
    pt  ...Certo. Coisas longe levam mais tempo. É só isso que significa.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know. I know exactly how far. That's the part I don't sleep about.
    >>  ............................................
    pt  ...Eu sei. Eu sei exatamente quão longe. É a parte que me tira o sono.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.respond.realism.flat/2
    en  You've said the thing I say to myself at three in the morning, %1$s.
    >>  ............................................
    pt  Você disse o que eu digo pra mim às três da manhã, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. Yes. It's too far. It's always been too far.
    >>  ............................................
    pt  ...Certo. Sim. É longe demais. Sempre foi longe demais.
    >>  ............................................
  shy.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...I know how far it is.
    >>  ............................................
    pt  ...Eu sei a distância.
    >>  ............................................
  shy.dialogue.conversations.dreams.respond.realism.flat/2
    en  Yes. I've counted the days it would take. More than once.
    >>  ............................................
    pt  Sim. Já contei os dias que levaria. Mais de uma vez.
    >>  ............................................
  shy.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Right. I'd worked that out.
    >>  ............................................
    pt  ...Certo. Eu já tinha concluído.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...Ah, the practicalities. My favourite part of any idea.
    >>  ............................................
    pt  ...Ah, os detalhes práticos. Minha parte favorita de qualquer ideia.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.respond.realism.flat/2
    en  Right! Distances. Yes. I'd forgotten those existed.
    >>  ............................................
    pt  Certo! Distâncias. Sim. Eu tinha esquecido que existiam.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Lovely. Now it's a sum instead of a plan.
    >>  ............................................
    pt  ...Ótimo. Agora é uma conta em vez de um plano.
    >>  ............................................
  witty.dialogue.conversations.dreams.respond.realism.flat/1
    en  ...Ah, the practicalities. My favourite part of any idea.
    >>  ............................................
    pt  ...Ah, os detalhes práticos. Minha parte favorita de qualquer ideia.
    >>  ............................................
  witty.dialogue.conversations.dreams.respond.realism.flat/2
    en  Right! Distances. Yes. I'd forgotten those existed.
    >>  ............................................
    pt  Certo! Distâncias. Sim. Eu tinha esquecido que existiam.
    >>  ............................................
  witty.dialogue.conversations.dreams.respond.realism.flat/3
    en  ...Lovely. Now it's a sum instead of a plan.
    >>  ............................................
    pt  ...Ótimo. Agora é uma conta em vez de um plano.
    >>  ............................................
```

</details>


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `dreams.respond.realism`)_
- Then opens: `conversations.topic.dreams.followup`
- …where the player's next choices will be: "I'll help you get there." | "I can't promise help. But I'm glad you told me." | "You? Doing that?" | "I hope you get it."

```text
POOL   dialogue key: dialogue.conversations.dreams.respond.realism.polite
WHO    VILLAGER — what the player reads after pressing "That's a long way from here."
       spoken on: conversations.topic.dreams.respond, button `realism`
       leaves the player on: conversations.topic.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.respond.realism.polite.to.dreams`: the villager accepts. Subject `dreams`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.respond.realism.polite/1   [56 chars]
    en  It is a long way. I've counted the steps more than once.
    >>  ............................................
    pt  É longe mesmo. Já contei os passos mais de uma vez.
    >>  ............................................
  dialogue.conversations.dreams.respond.realism.polite/2   [46 chars]
    en  It is. Doesn't stop it being the thing I want.
    >>  ............................................
    pt  É sim. Não impede de ser o que eu quero.
    >>  ............................................
  dialogue.conversations.dreams.respond.realism.polite/3   [40 chars]
    en  True enough. Most worthwhile things are.
    >>  ............................................
    pt  Verdade. Quase tudo que vale a pena é.
    >>  ............................................
```


### Button `no_words` — "I don't know what to say to that."

*stance family `restraint` · tone `gentle` · answers the beat(s) `dreams.first.to.dreams`, `dreams.revisit.to.dreams`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.respond.no_words` — accepted phrasings: "i do not know what to say to that"; "you have left me speechless"; "i have no words for that"
  - the message must contain one of: `speechless`, `words`
  - scored words: `speechless`(1.2), `words`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.respond.no_words
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.respond.no_words   [33 chars]
    en  I don't know what to say to that.
    >>  ............................................
    pt  Não sei o que dizer sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.no_words`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `dreams.no_words`)_
- Then opens: `conversations.topic.dreams.followup`
- …where the player's next choices will be: "I'll help you get there." | "I can't promise help. But I'm glad you told me." | "You? Doing that?" | "I hope you get it."

```text
POOL   dialogue key: dialogue.conversations.dreams.no_words
WHO    VILLAGER — what the player reads after pressing "I don't know what to say to that."
       spoken on: conversations.topic.dreams.respond, button `no_words`
       leaves the player on: conversations.topic.dreams.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.no_words.to.dreams`: the villager accepts. Subject `dreams`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.no_words/1   [91 chars]
    en  ...That's fair. It's a daft thing to want out loud and you've not laughed, which is plenty.
    >>  ............................................
    pt  ...É justo. É bobo querer isso em voz alta e você não riu, o que já é bastante.
    >>  ............................................
  dialogue.conversations.dreams.no_words/2   [80 chars]
    en  You don't have to have an opinion on it, %1$s. It's not a proposal, it's a want.
    >>  ............................................
    pt  Você não precisa ter opinião sobre isso, %1$s. Não é proposta, é vontade.
    >>  ............................................
  dialogue.conversations.dreams.no_words/3   [84 chars]
    en  Nothing to say is better than 'that's nice'. I've had a great deal of 'that's nice'.
    >>  ............................................
    pt  Não ter o que dizer é melhor que 'que legal'. Eu já recebi muito 'que legal'.
    >>  ............................................
```


### Button `leave` — "I hope you get it."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.first.to.dreams`, `dreams.revisit.to.dreams` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.respond.leave   [18 chars]
    en  I hope you get it.
    >>  ............................................
    pt  Espero que consiga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.respond.leave
WHO    VILLAGER — what the player reads after pressing "I hope you get it."
       spoken on: conversations.topic.dreams.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.respond.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.dreams.followup / leave
```

> Written out in full under **`conversations.topic.dreams.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.dreams.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `dreams`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.toddler` — e.g. "I wanna be TALL."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.toddler.respond   [28 chars]
    en  That's what I'm going to be.
    >>  ............................................
    pt  É isso que eu vou ser.
    >>  ............................................
```


### Button `delight` — "I believe you."

*stance family `encouragement` · tone `playful` · answers the beat(s) `dreams.toddler.to.dreams.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.toddler.delight` — accepted phrasings: "i believe you"; "i do believe you"; "i believe that"
  - the message must contain one of: `believe`
  - scored words: `believe`(1.5), `you`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.toddler.respond.delight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.toddler.respond.delight   [14 chars]
    en  I believe you.
    >>  ............................................
    pt  Eu acredito em você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.toddler.delight`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `dreams.toddler.delight`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.toddler.delight
WHO    VILLAGER — what the player reads after pressing "I believe you."
       spoken on: conversations.topic.dreams.toddler.respond, button `delight`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.toddler.delight.terminal`: the villager celebrates. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.toddler.delight/1   [63 chars]
    en  You BELIEVE me. Nobody says that, they just laugh and go 'aww'.
    >>  ............................................
    pt  Você ACREDITA. Ninguém fala isso, só riem e fazem 'ai que fofo'.
    >>  ............................................
  dialogue.conversations.dreams.toddler.delight/2   [68 chars]
    en  Then it's happening. It's decided. You said it and now it's decided.
    >>  ............................................
    pt  Então vai acontecer. Está decidido. Você falou e agora está decidido.
    >>  ............................................
  dialogue.conversations.dreams.toddler.delight/3   [41 chars]
    en  I KNOW. I'm going to be it so much, %1$s.
    >>  ............................................
    pt  EU SEI. Eu vou ser isso pra caramba, %1$s.
    >>  ............................................
```


### Button `ask` — "How will you do it?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `dreams.toddler.to.dreams.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.toddler.ask` — accepted phrasings: "how will you do it"; "how are you going to do it"; "how will that happen"
  - the message must contain one of: `how`
  - scored words: `how`(1.5), `do`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.toddler.respond.ask   [19 chars]
    en  How will you do it?
    >>  ............................................
    pt  Como você vai conseguir?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `dreams.toddler.ask`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.toddler.ask
WHO    VILLAGER — what the player reads after pressing "How will you do it?"
       spoken on: conversations.topic.dreams.toddler.respond, button `ask`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.toddler.ask.terminal`: the villager asks. Subject `dreams.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.toddler.ask/1   [57 chars]
    en  You just do it. You do it and then it's done. That's how.
    >>  ............................................
    pt  Você só faz. Você faz e aí está feito. É assim.
    >>  ............................................
  dialogue.conversations.dreams.toddler.ask/2   [62 chars]
    en  Eating. Eating makes you get big and then you can do anything.
    >>  ............................................
    pt  Comendo. Comer faz a gente ficar grande e aí dá pra fazer tudo.
    >>  ............................................
  dialogue.conversations.dreams.toddler.ask/3   [55 chars]
    en  Practising. Every day. Except some days 'cause of naps.
    >>  ............................................
    pt  Treinando. Todo dia. Menos alguns dias por causa do soninho.
    >>  ............................................
```


### Button `leave` — "Off you go and practise."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.toddler.to.dreams.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.toddler.respond.leave   [24 chars]
    en  Off you go and practise.
    >>  ............................................
    pt  Vai treinar, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go and practise."
       spoken on: conversations.topic.dreams.toddler.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.toddler.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.toddler.leave/1   [39 chars]
    en  Bye! I'm going to go practise being it.
    >>  ............................................
    pt  Tchau! Vou treinar ser isso.
    >>  ............................................
  dialogue.conversations.dreams.toddler.leave/2   [31 chars]
    en  Okay bye, %1$s! Watch me later!
    >>  ............................................
    pt  Tá, tchau, %1$s! Me vê depois!
    >>  ............................................
  dialogue.conversations.dreams.toddler.leave/3   [36 chars]
    en  Bye bye. You'll see. You'll ALL see.
    >>  ............................................
    pt  Tchau tchau. Você vai ver. VOCÊS todos vão ver.
    >>  ............................................
```

---


## `conversations.topic.dreams.young.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `dreams`; `conversations.cat.personal` / `dreams`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.child` — e.g. "I'm gonna be a knight AND a baker. Sword in one hand, cake in the other. It's decided."
- `conversations.dreams.teen` — e.g. "Somewhere that isn't here, doing something nobody assigned me. That's the whole dream so far."


```text
POOL   dialogue key: dialogue.conversations.topic.dreams.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.dreams.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.dreams.young.respond   [24 chars]
    en  That's the plan, anyway.
    >>  ............................................
    pt  Esse é o plano, enfim.
    >>  ............................................
```


### Button `interested` — "Go on, tell me the whole plan."

*stance family `curiosity` · tone `plain` · answers the beat(s) `dreams.child.to.dreams.young`, `dreams.teen.to.dreams.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.young.interested` — accepted phrasings: "tell me properly"; "tell me the whole dream"; "go on, properly"
  - the message must contain one of: `properly`, `whole`
  - scored words: `properly`(1.5), `whole`(1.2), `dream`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.young.respond.interested
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.young.respond.interested   [30 chars]
    en  Go on, tell me the whole plan.
    >>  ............................................
    pt  Vai, me conta o plano inteiro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.young.interested`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `dreams.young.interested`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.young.interested
WHO    VILLAGER — what the player reads after pressing "Go on, tell me the whole plan."
       spoken on: conversations.topic.dreams.young.respond, button `interested`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.young.interested.terminal`: the villager accepts. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.young.interested/1   [75 chars]
    en  Really? Alright — so it's got three parts and the third one's the good one.
    >>  ............................................
    pt  Sério? Então tá — tem três partes e a terceira é a boa.
    >>  ............................................
  dialogue.conversations.dreams.young.interested/2   [63 chars]
    en  You want the WHOLE plan? Nobody wants the whole plan. Sit down.
    >>  ............................................
    pt  Você quer o plano INTEIRO? Ninguém quer o plano inteiro. Senta aí.
    >>  ............................................
  dialogue.conversations.dreams.young.interested/3   [81 chars]
    en  Good. It starts small and it gets very big by the end. Don't laugh at the middle.
    >>  ............................................
    pt  Bom. Começa pequeno e fica bem grande no fim. Não ri do meio.
    >>  ............................................
```


### Button `encourage` — "You could do that, you know."

*stance family `encouragement` · tone `plain` · answers the beat(s) `dreams.child.to.dreams.young`, `dreams.teen.to.dreams.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.young.encourage` — accepted phrasings: "that is worth having"; "that dream is worth having"; "worth holding onto"
  - the message must contain one of: `worth`, `having`
  - scored words: `worth`(1.5), `having`(1.0), `dream`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.young.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.young.respond.encourage   [28 chars]
    en  You could do that, you know.
    >>  ............................................
    pt  Você conseguiria, sabia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `dreams.young.encourage`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, respect +2  _(recorded under topic `dreams.young.encourage`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.young.encourage
WHO    VILLAGER — what the player reads after pressing "You could do that, you know."
       spoken on: conversations.topic.dreams.young.respond, button `encourage`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.young.encourage.terminal`: the villager accepts. Subject `dreams.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.young.encourage/1   [77 chars]
    en  ...You think I could? Most people say 'we'll see' in the voice that means no.
    >>  ............................................
    pt  ...Você acha que eu conseguiria? A maioria diz 'a gente vê' naquela voz que quer dizer não.
    >>  ............................................
  dialogue.conversations.dreams.young.encourage/2   [57 chars]
    en  I COULD, couldn't I. I'm going to remember you said that.
    >>  ............................................
    pt  Eu CONSEGUIRIA, né. Vou lembrar que você falou isso.
    >>  ............................................
  dialogue.conversations.dreams.young.encourage/3   [75 chars]
    en  That's the first time anyone's said it back without the face. Thanks, %1$s.
    >>  ............................................
    pt  É a primeira vez que alguém repete sem fazer aquela cara. Obrigado, %1$s.
    >>  ............................................
```


### Button `dismiss` — "You'll grow out of that one."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `dreams.child.to.dreams.young`, `dreams.teen.to.dreams.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `dreams.young.dismiss` — accepted phrasings: "you will change your mind"; "you will grow out of it"; "you will think differently"
  - the message must contain one of: `change`, `mind`, `grow`
  - scored words: `change`(1.5), `mind`(1.2), `grow`(1.2), `dream`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.young.respond.dismiss   [28 chars]
    en  You'll grow out of that one.
    >>  ............................................
    pt  Você vai crescer e largar essa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `dreams.young.dismiss`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `dreams.young.dismiss`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.young.dismiss
WHO    VILLAGER — what the player reads after pressing "You'll grow out of that one."
       spoken on: conversations.topic.dreams.young.respond, button `dismiss`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.young.dismiss.terminal`: the villager dismisss. Subject `dreams.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.young.dismiss/1   [79 chars]
    en  ...Everyone says that. Everyone said it to everyone who ever did anything, too.
    >>  ............................................
    pt  ...Todo mundo fala isso. E falaram para todo mundo que já fez alguma coisa também.
    >>  ............................................
  dialogue.conversations.dreams.young.dismiss/2   [57 chars]
    en  You don't know that. Nobody knows that yet, including me.
    >>  ............................................
    pt  Você não sabe. Ninguém sabe ainda, nem eu.
    >>  ............................................
  dialogue.conversations.dreams.young.dismiss/3   [48 chars]
    en  Fine. Then I'll do it and not tell you about it.
    >>  ............................................
    pt  Tá bom. Então eu faço e não te conto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. It's why I don't tell people, %1$s.
    >>  ............................................
    pt  ...Todo mundo diz isso. É por isso que eu não conto pras pessoas, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.dreams.young.dismiss/2
    en  I know it sounds like nothing. It's not nothing to me.
    >>  ............................................
    pt  Eu sei que soa como nada. Pra mim não é nada.
    >>  ............................................
  anxious.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll keep it to myself from now on.
    >>  ............................................
    pt  ...Certo. De agora em diante eu guardo pra mim.
    >>  ............................................
  athletic.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. It's never yet stopped anybody who meant it.
    >>  ............................................
    pt  ...Todo mundo diz isso. E nunca parou ninguém que falava sério.
    >>  ............................................
  athletic.dialogue.conversations.dreams.young.dismiss/2
    en  Aye, they all say so. I've plenty of time to find out.
    >>  ............................................
    pt  É, todos dizem. Eu tenho tempo de sobra pra descobrir.
    >>  ............................................
  athletic.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. We'll see how it looks in ten years.
    >>  ............................................
    pt  ...Certo. A gente vê como fica em dez anos.
    >>  ............................................
  confident.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. Everyone said it to everyone who ever did anything, too.
    >>  ............................................
    pt  ...Todo mundo diz isso. Também disseram pra todos que fizeram alguma coisa.
    >>  ............................................
  confident.dialogue.conversations.dreams.young.dismiss/2
    en  Right. Then I'll be the one who did it anyway.
    >>  ............................................
    pt  Certo. Então eu vou ser quem fez mesmo assim.
    >>  ............................................
  confident.dialogue.conversations.dreams.young.dismiss/3
    en  ...Say what you like. I'm still going.
    >>  ............................................
    pt  ...Diga o que quiser. Eu ainda vou.
    >>  ............................................
  crabby.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. Everyone said it to everyone who ever did anything, too.
    >>  ............................................
    pt  ...Todo mundo diz isso. Também disseram pra todos que fizeram alguma coisa.
    >>  ............................................
  crabby.dialogue.conversations.dreams.young.dismiss/2
    en  Right. Then I'll be the one who did it anyway.
    >>  ............................................
    pt  Certo. Então eu vou ser quem fez mesmo assim.
    >>  ............................................
  crabby.dialogue.conversations.dreams.young.dismiss/3
    en  ...Say what you like. I'm still going.
    >>  ............................................
    pt  ...Diga o que quiser. Eu ainda vou.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. I'd hoped you wouldn't, %1$s.
    >>  ............................................
    pt  ...Todo mundo diz isso. Eu esperava que você não dissesse, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.young.dismiss/2
    en  That's what the grown-ups say. I thought you were different.
    >>  ............................................
    pt  É o que os adultos dizem. Achei que você fosse diferente.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll tell somebody who'd like the idea.
    >>  ............................................
    pt  ...Certo. Vou contar pra alguém que goste da ideia.
    >>  ............................................
  flirty.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. I'd hoped you wouldn't, %1$s.
    >>  ............................................
    pt  ...Todo mundo diz isso. Eu esperava que você não dissesse, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.dreams.young.dismiss/2
    en  That's what the grown-ups say. I thought you were different.
    >>  ............................................
    pt  É o que os adultos dizem. Achei que você fosse diferente.
    >>  ............................................
  flirty.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll tell somebody who'd like the idea.
    >>  ............................................
    pt  ...Certo. Vou contar pra alguém que goste da ideia.
    >>  ............................................
  friendly.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. I'd hoped you wouldn't, %1$s.
    >>  ............................................
    pt  ...Todo mundo diz isso. Eu esperava que você não dissesse, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.dreams.young.dismiss/2
    en  That's what the grown-ups say. I thought you were different.
    >>  ............................................
    pt  É o que os adultos dizem. Achei que você fosse diferente.
    >>  ............................................
  friendly.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll tell somebody who'd like the idea.
    >>  ............................................
    pt  ...Certo. Vou contar pra alguém que goste da ideia.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. It's why I don't tell people, %1$s.
    >>  ............................................
    pt  ...Todo mundo diz isso. É por isso que eu não conto pras pessoas, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.young.dismiss/2
    en  I know it sounds like nothing. It's not nothing to me.
    >>  ............................................
    pt  Eu sei que soa como nada. Pra mim não é nada.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll keep it to myself from now on.
    >>  ............................................
    pt  ...Certo. De agora em diante eu guardo pra mim.
    >>  ............................................
  greedy.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. Everyone said it to everyone who ever did anything, too.
    >>  ............................................
    pt  ...Todo mundo diz isso. Também disseram pra todos que fizeram alguma coisa.
    >>  ............................................
  greedy.dialogue.conversations.dreams.young.dismiss/2
    en  Right. Then I'll be the one who did it anyway.
    >>  ............................................
    pt  Certo. Então eu vou ser quem fez mesmo assim.
    >>  ............................................
  greedy.dialogue.conversations.dreams.young.dismiss/3
    en  ...Say what you like. I'm still going.
    >>  ............................................
    pt  ...Diga o que quiser. Eu ainda vou.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. Everyone said it to everyone who ever did anything, too.
    >>  ............................................
    pt  ...Todo mundo diz isso. Também disseram pra todos que fizeram alguma coisa.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.young.dismiss/2
    en  Right. Then I'll be the one who did it anyway.
    >>  ............................................
    pt  Certo. Então eu vou ser quem fez mesmo assim.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.young.dismiss/3
    en  ...Say what you like. I'm still going.
    >>  ............................................
    pt  ...Diga o que quiser. Eu ainda vou.
    >>  ............................................
  introverted.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that.
    >>  ............................................
    pt  ...Todo mundo diz isso.
    >>  ............................................
  introverted.dialogue.conversations.dreams.young.dismiss/2
    en  I've heard it before. From nearly everyone.
    >>  ............................................
    pt  Já ouvi antes. De quase todo mundo.
    >>  ............................................
  introverted.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll stop saying it out loud.
    >>  ............................................
    pt  ...Certo. Vou parar de dizer em voz alta.
    >>  ............................................
  lazy.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. It's never yet stopped anybody who meant it.
    >>  ............................................
    pt  ...Todo mundo diz isso. E nunca parou ninguém que falava sério.
    >>  ............................................
  lazy.dialogue.conversations.dreams.young.dismiss/2
    en  Aye, they all say so. I've plenty of time to find out.
    >>  ............................................
    pt  É, todos dizem. Eu tenho tempo de sobra pra descobrir.
    >>  ............................................
  lazy.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. We'll see how it looks in ten years.
    >>  ............................................
    pt  ...Certo. A gente vê como fica em dez anos.
    >>  ............................................
  odd.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that.
    >>  ............................................
    pt  ...Todo mundo diz isso.
    >>  ............................................
  odd.dialogue.conversations.dreams.young.dismiss/2
    en  I've heard it before. From nearly everyone.
    >>  ............................................
    pt  Já ouvi antes. De quase todo mundo.
    >>  ............................................
  odd.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll stop saying it out loud.
    >>  ............................................
    pt  ...Certo. Vou parar de dizer em voz alta.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. It's never yet stopped anybody who meant it.
    >>  ............................................
    pt  ...Todo mundo diz isso. E nunca parou ninguém que falava sério.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.young.dismiss/2
    en  Aye, they all say so. I've plenty of time to find out.
    >>  ............................................
    pt  É, todos dizem. Eu tenho tempo de sobra pra descobrir.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. We'll see how it looks in ten years.
    >>  ............................................
    pt  ...Certo. A gente vê como fica em dez anos.
    >>  ............................................
  peppy.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that! And then somebody goes and does it anyway. Might be me.
    >>  ............................................
    pt  ...Todo mundo diz isso! E aí alguém vai e faz mesmo assim. Pode ser eu.
    >>  ............................................
  peppy.dialogue.conversations.dreams.young.dismiss/2
    en  Right, well. Put it on the list of things I'll prove wrong.
    >>  ............................................
    pt  Certo, bom. Ponha na lista das coisas que eu vou desmentir.
    >>  ............................................
  peppy.dialogue.conversations.dreams.young.dismiss/3
    en  ...Ha. We'll see, won't we.
    >>  ............................................
    pt  ...Ha. A gente vai ver, não é?
    >>  ............................................
  playful.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that! And then somebody goes and does it anyway. Might be me.
    >>  ............................................
    pt  ...Todo mundo diz isso! E aí alguém vai e faz mesmo assim. Pode ser eu.
    >>  ............................................
  playful.dialogue.conversations.dreams.young.dismiss/2
    en  Right, well. Put it on the list of things I'll prove wrong.
    >>  ............................................
    pt  Certo, bom. Ponha na lista das coisas que eu vou desmentir.
    >>  ............................................
  playful.dialogue.conversations.dreams.young.dismiss/3
    en  ...Ha. We'll see, won't we.
    >>  ............................................
    pt  ...Ha. A gente vai ver, não é?
    >>  ............................................
  relaxed.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. It's never yet stopped anybody who meant it.
    >>  ............................................
    pt  ...Todo mundo diz isso. E nunca parou ninguém que falava sério.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.young.dismiss/2
    en  Aye, they all say so. I've plenty of time to find out.
    >>  ............................................
    pt  É, todos dizem. Eu tenho tempo de sobra pra descobrir.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. We'll see how it looks in ten years.
    >>  ............................................
    pt  ...Certo. A gente vê como fica em dez anos.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that. It's why I don't tell people, %1$s.
    >>  ............................................
    pt  ...Todo mundo diz isso. É por isso que eu não conto pras pessoas, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.young.dismiss/2
    en  I know it sounds like nothing. It's not nothing to me.
    >>  ............................................
    pt  Eu sei que soa como nada. Pra mim não é nada.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll keep it to myself from now on.
    >>  ............................................
    pt  ...Certo. De agora em diante eu guardo pra mim.
    >>  ............................................
  shy.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that.
    >>  ............................................
    pt  ...Todo mundo diz isso.
    >>  ............................................
  shy.dialogue.conversations.dreams.young.dismiss/2
    en  I've heard it before. From nearly everyone.
    >>  ............................................
    pt  Já ouvi antes. De quase todo mundo.
    >>  ............................................
  shy.dialogue.conversations.dreams.young.dismiss/3
    en  ...Right. I'll stop saying it out loud.
    >>  ............................................
    pt  ...Certo. Vou parar de dizer em voz alta.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that! And then somebody goes and does it anyway. Might be me.
    >>  ............................................
    pt  ...Todo mundo diz isso! E aí alguém vai e faz mesmo assim. Pode ser eu.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.young.dismiss/2
    en  Right, well. Put it on the list of things I'll prove wrong.
    >>  ............................................
    pt  Certo, bom. Ponha na lista das coisas que eu vou desmentir.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.young.dismiss/3
    en  ...Ha. We'll see, won't we.
    >>  ............................................
    pt  ...Ha. A gente vai ver, não é?
    >>  ............................................
  witty.dialogue.conversations.dreams.young.dismiss/1
    en  ...Everyone says that! And then somebody goes and does it anyway. Might be me.
    >>  ............................................
    pt  ...Todo mundo diz isso! E aí alguém vai e faz mesmo assim. Pode ser eu.
    >>  ............................................
  witty.dialogue.conversations.dreams.young.dismiss/2
    en  Right, well. Put it on the list of things I'll prove wrong.
    >>  ............................................
    pt  Certo, bom. Ponha na lista das coisas que eu vou desmentir.
    >>  ............................................
  witty.dialogue.conversations.dreams.young.dismiss/3
    en  ...Ha. We'll see, won't we.
    >>  ............................................
    pt  ...Ha. A gente vai ver, não é?
    >>  ............................................
```

</details>


### Button `leave` — "Off you go and plan, then."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.child.to.dreams.young`, `dreams.teen.to.dreams.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.dreams.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.dreams.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.dreams.young.respond.leave   [26 chars]
    en  Off you go and plan, then.
    >>  ............................................
    pt  Então vá planejar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.young.leave
WHO    VILLAGER — what the player reads after pressing "Off you go and plan, then."
       spoken on: conversations.topic.dreams.young.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.young.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.young.leave/1   [9 chars]
    en  Bye then!
    >>  ............................................
    pt  Tchau então!
    >>  ............................................
  dialogue.conversations.dreams.young.leave/2   [14 chars]
    en  See you, %1$s.
    >>  ............................................
    pt  Até mais, %1$s.
    >>  ............................................
  dialogue.conversations.dreams.young.leave/3   [10 chars]
    en  Okay. Bye.
    >>  ............................................
    pt  Tá. Tchau.
    >>  ............................................
```

---

