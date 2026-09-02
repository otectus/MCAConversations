# Topic: ask_parent

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `ask_parent` |
| Opened from | question `conversations.family`, button `ask_parent` |
| Depth class (its heart budget) | `relationship` |
| Returns to | `conversations.family` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `candor`, `empathy`, `curiosity`, `dismissal`, `exit` |
| Narrative arc | `family`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.family`, which is written out in **topic-family.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.family.ask_parent
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.family
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in topic-family*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.family.ask_parent   [41 chars]
    en  Can I ask you something? Parent to child?
    >>  ............................................
    pt  Posso te perguntar uma coisa? De pai pra filho?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.topic.ask_parent.event`](#conversations-topic-ask-parent-event)
- [`conversations.topic.ask_parent.followup`](#conversations-topic-ask-parent-followup)
- [`conversations.topic.ask_parent.pride`](#conversations-topic-ask-parent-pride)
- [`conversations.topic.ask_parent.rebuffed.followup`](#conversations-topic-ask-parent-rebuffed-followup)
- [`conversations.topic.ask_parent.respond`](#conversations-topic-ask-parent-respond)

---

## `conversations.topic.ask_parent.event`

**Reached from 1 route(s):** `conversations.topic.ask_parent.followup` / `ask_event`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.ask_parent.event` — e.g. "Everyone stopped talking when I came in. That's how I knew there was something."


```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.event
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.ask_parent.event
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.ask_parent.event   [36 chars]
    en  That's what I've been trying to ask.
    >>  ............................................
    pt  É isso que eu venho tentando perguntar.
    >>  ............................................
```


### Button `tell_them_plainly` — "I'll tell you plainly, then."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `ask_parent.event`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.event.plain` — accepted phrasings: "i will tell you plainly"; "i will tell you straight"; "you deserve the plain truth"
  - the message must contain one of: `plainly`
  - scored words: `plainly`(1.5), `straight`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.event.tell_them_plainly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.event
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.event.tell_them_plainly   [28 chars]
    en  I'll tell you plainly, then.
    >>  ............................................
    pt  Então eu conto sem rodeios.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `ask_parent.event.plain`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `ask_parent.event.plain`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.event.plain
WHO    VILLAGER — what the player reads after pressing "I'll tell you plainly, then."
       spoken on: conversations.topic.ask_parent.event, button `tell_them_plainly`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.event.plain`: the villager accepts. Subject `ask_parent.event`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.ask_parent.event.plain/1   [66 chars]
    en  Thank you. The guessing was worse than any of it turned out to be.
    >>  ............................................
    pt  Obrigado. Adivinhar era pior do que qualquer parte acabou sendo.
    >>  ............................................
  dialogue.conversations.ask_parent.event.plain/2   [75 chars]
    en  Fair. I'll not ask anything else today — that's enough to be going on with.
    >>  ............................................
    pt  Justo. Não pergunto mais nada hoje — já dá pra seguir com isso.
    >>  ............................................
  dialogue.conversations.ask_parent.event.plain/3   [64 chars]
    en  You're the only one who did. I'll remember which of you told me.
    >>  ............................................
    pt  Você foi o único. Vou lembrar quem me contou.
    >>  ............................................
```


### Button `together_next_time` — "Next time you come with us."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `ask_parent.event`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.event.together` — accepted phrasings: "next time you come with us"; "you will come with us next time"; "you can come along next time"
  - the message must contain one of: `next`
  - scored words: `next`(1.0), `come`(0.6), `with`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.event.together_next_time
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.event
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.event.together_next_time   [27 chars]
    en  Next time you come with us.
    >>  ............................................
    pt  Da próxima vez você vem com a gente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `ask_parent.event.together`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +3  _(recorded under topic `ask_parent.event.together`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.event.together
WHO    VILLAGER — what the player reads after pressing "Next time you come with us."
       spoken on: conversations.topic.ask_parent.event, button `together_next_time`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.event.together`: the villager accepts. Subject `ask_parent.event`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.ask_parent.event.together/1   [68 chars]
    en  Do you mean that? People say next time and then there's a next time.
    >>  ............................................
    pt  Você fala sério? As pessoas dizem 'próxima vez' e aí a próxima vez chega.
    >>  ............................................
  dialogue.conversations.ask_parent.event.together/2   [72 chars]
    en  Good. Standing at the neighbours' window is the worst part of any of it.
    >>  ............................................
    pt  Bom. Ficar na janela dos vizinhos é a pior parte de tudo.
    >>  ............................................
  dialogue.conversations.ask_parent.event.together/3   [63 chars]
    en  Then I'll hold you to it, and I'll be unbearable if you forget.
    >>  ............................................
    pt  Então vou cobrar, e vou ser insuportável se você esquecer.
    >>  ............................................
```


### Button `leave` — "Not now."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `ask_parent.event` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.event.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.event
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.event.leave   [8 chars]
    en  Not now.
    >>  ............................................
    pt  Agora não.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.event.leave
WHO    VILLAGER — what the player reads after pressing "Not now."
       spoken on: conversations.topic.ask_parent.event, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.event.leave`: the villager accepts. Subject `ask_parent.event`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.ask_parent.event.leave/1   [11 chars]
    en  ...Alright.
    >>  ............................................
    pt  ...Está bem.
    >>  ............................................
  dialogue.conversations.ask_parent.event.leave/2   [20 chars]
    en  You always say that.
    >>  ............................................
    pt  Você sempre diz isso.
    >>  ............................................
  dialogue.conversations.ask_parent.event.leave/3   [5 chars]
    en  Fine.
    >>  ............................................
    pt  Tudo bem.
    >>  ............................................
```

---


## `conversations.topic.ask_parent.followup`

**Reached from 2 route(s):** `conversations.topic.ask_parent.respond` / `answer_honestly`; `conversations.topic.ask_parent.respond` / `reassure`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.parent.answer_honestly` — e.g. "...Thank you. You've never fobbed me off, and I notice."
- `conversations.family.parent.reassure` — e.g. "I'll worry anyway. That's the arrangement, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.ask_parent.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.ask_parent.followup   [15 chars]
    en  So there it is.
    >>  ............................................
    pt  Então é isso.
    >>  ............................................
```


### Button `ask_worries` — "What are you worried about?"

*stance family `curiosity` · tone `gentle` · answers the beat(s) `family.parent.answer_honestly.to.ask_parent`, `family.parent.reassure.to.ask_parent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.followup.ask_worries` — accepted phrasings: "what are you worried about"; "what is worrying you"; "tell me what you are worried about"
  - the message must contain one of: `worried`, `worrying`
  - scored words: `worried`(1.5), `worrying`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.followup.ask_worries
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.followup.ask_worries   [27 chars]
    en  What are you worried about?
    >>  ............................................
    pt  Com o que você está preocupado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.parent.ask_worries`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +5, familiarity +2  _(recorded under topic `family.parent.ask_worries`)_
- Does: arc `family` — advance to stage 1
- Then opens: `conversations.topic.family.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.family.parent.ask_worries
WHO    VILLAGER — what the player reads after pressing "What are you worried about?"
       spoken on: conversations.topic.ask_parent.followup, button `ask_worries`
       leaves the player on: conversations.topic.family.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.ask_worries.to.family`: the villager accepts. Subject `family`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.parent.ask_worries/1   [63 chars]
    en  ...You want to know what I worry about? Nobody's asked me that.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo? Ninguém me perguntou isso.
    >>  ............................................
  dialogue.conversations.family.parent.ask_worries/2   [49 chars]
    en  Everything. Mostly you. That's the honest answer.
    >>  ............................................
    pt  Tudo. Principalmente você. É a resposta honesta.
    >>  ............................................
  dialogue.conversations.family.parent.ask_worries/3   [61 chars]
    en  The usual things a person worries about, and one or two more.
    >>  ............................................
    pt  As coisas comuns que se preocupa, e mais uma ou duas.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about, %1$s? Nobody's asked me that. Not once.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo, %1$s? Ninguém me perguntou. Nem uma vez.
    >>  ............................................
  anxious.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. I'd stopped noticing that it didn't.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Eu tinha parado de reparar que não se fazia.
    >>  ............................................
  anxious.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment. I've not had to say it out loud before.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento. Eu nunca precisei dizer em voz alta.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that in twenty years.
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me pergunta isso há vinte anos.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Sit down; it'll take a while.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Sente-se; vai levar um tempo.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. I'll get there. Give me the time and I'll get there.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Eu chego lá. Me dê o tempo e eu chego.
    >>  ............................................
  confident.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that.
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me perguntou isso.
    >>  ............................................
  confident.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Right. Sit down.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Certo. Sente-se.
    >>  ............................................
  confident.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. Give me a moment to find the words.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Me dê um momento pra achar as palavras.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that.
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me perguntou isso.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Right. Sit down.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Certo. Sente-se.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. Give me a moment to find the words.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Me dê um momento pra achar as palavras.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about, %1$s? Nobody's asked me that.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo, %1$s? Ninguém me perguntou isso.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Sit down. I'll try to answer it properly.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Sente-se. Vou tentar responder direito.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment — I'd like to get this one right.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento — eu queria acertar nesta.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about, %1$s? Nobody's asked me that.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo, %1$s? Ninguém me perguntou isso.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Sit down. I'll try to answer it properly.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Sente-se. Vou tentar responder direito.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment — I'd like to get this one right.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento — eu queria acertar nesta.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about, %1$s? Nobody's asked me that.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo, %1$s? Ninguém me perguntou isso.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Sit down. I'll try to answer it properly.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Sente-se. Vou tentar responder direito.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment — I'd like to get this one right.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento — eu queria acertar nesta.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about, %1$s? Nobody's asked me that. Not once.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo, %1$s? Ninguém me perguntou. Nem uma vez.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. I'd stopped noticing that it didn't.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Eu tinha parado de reparar que não se fazia.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment. I've not had to say it out loud before.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento. Eu nunca precisei dizer em voz alta.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that.
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me perguntou isso.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Right. Sit down.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Certo. Sente-se.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. Give me a moment to find the words.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Me dê um momento pra achar as palavras.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that.
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me perguntou isso.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Right. Sit down.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Certo. Sente-se.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. Give me a moment to find the words.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Me dê um momento pra achar as palavras.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about? Nobody's asked me that.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo? Ninguém me perguntou isso.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents.
    >>  ............................................
    pt  Essa pergunta não se faz a pais.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that in twenty years.
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me pergunta isso há vinte anos.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Sit down; it'll take a while.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Sente-se; vai levar um tempo.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. I'll get there. Give me the time and I'll get there.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Eu chego lá. Me dê o tempo e eu chego.
    >>  ............................................
  odd.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about? Nobody's asked me that.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo? Ninguém me perguntou isso.
    >>  ............................................
  odd.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents.
    >>  ............................................
    pt  Essa pergunta não se faz a pais.
    >>  ............................................
  odd.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that in twenty years.
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me pergunta isso há vinte anos.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Sit down; it'll take a while.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Sente-se; vai levar um tempo.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. I'll get there. Give me the time and I'll get there.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Eu chego lá. Me dê o tempo e eu chego.
    >>  ............................................
  peppy.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that! Ever!
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me perguntou isso! Nunca!
    >>  ............................................
  peppy.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. We're meant to be the answer, not the question.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. A gente é pra ser a resposta, não a pergunta.
    >>  ............................................
  peppy.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. Nobody's opened that door. Mind the mess.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Ninguém abriu essa porta. Cuidado com a bagunça.
    >>  ............................................
  playful.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that! Ever!
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me perguntou isso! Nunca!
    >>  ............................................
  playful.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. We're meant to be the answer, not the question.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. A gente é pra ser a resposta, não a pergunta.
    >>  ............................................
  playful.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. Nobody's opened that door. Mind the mess.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Ninguém abriu essa porta. Cuidado com a bagunça.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that in twenty years.
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me pergunta isso há vinte anos.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. Sit down; it'll take a while.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Sente-se; vai levar um tempo.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. I'll get there. Give me the time and I'll get there.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Eu chego lá. Me dê o tempo e eu chego.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about, %1$s? Nobody's asked me that. Not once.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo, %1$s? Ninguém me perguntou. Nem uma vez.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. I'd stopped noticing that it didn't.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. Eu tinha parado de reparar que não se fazia.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment. I've not had to say it out loud before.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento. Eu nunca precisei dizer em voz alta.
    >>  ............................................
  shy.dialogue.conversations.family.parent.ask_worries/1
    en  ...You want to know what I worry about? Nobody's asked me that.
    >>  ............................................
    pt  ...Você quer saber com o que eu me preocupo? Ninguém me perguntou isso.
    >>  ............................................
  shy.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents.
    >>  ............................................
    pt  Essa pergunta não se faz a pais.
    >>  ............................................
  shy.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Give me a moment.
    >>  ............................................
    pt  Com o que eu me preocupo. Me dê um momento.
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that! Ever!
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me perguntou isso! Nunca!
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. We're meant to be the answer, not the question.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. A gente é pra ser a resposta, não a pergunta.
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. Nobody's opened that door. Mind the mess.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Ninguém abriu essa porta. Cuidado com a bagunça.
    >>  ............................................
  witty.dialogue.conversations.family.parent.ask_worries/1
    en  You want to know what I worry about? Nobody's asked me that! Ever!
    >>  ............................................
    pt  Você quer saber com o que eu me preocupo? Ninguém me perguntou isso! Nunca!
    >>  ............................................
  witty.dialogue.conversations.family.parent.ask_worries/2
    en  That question doesn't get asked of parents. We're meant to be the answer, not the question.
    >>  ............................................
    pt  Essa pergunta não se faz a pais. A gente é pra ser a resposta, não a pergunta.
    >>  ............................................
  witty.dialogue.conversations.family.parent.ask_worries/3
    en  What I worry about. Huh. Nobody's opened that door. Mind the mess.
    >>  ............................................
    pt  Com o que eu me preocupo. Huh. Ninguém abriu essa porta. Cuidado com a bagunça.
    >>  ............................................
```

</details>


### Button `thank_them` — "Thank you for asking."

*stance family `empathy` · tone `gentle` · answers the beat(s) `family.parent.answer_honestly.to.ask_parent`, `family.parent.reassure.to.ask_parent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.followup.thank_them` — accepted phrasings: "thank you for asking"; "thanks for asking me"; "it was kind of you to ask"
  - the message must contain one of: `asking`
  - scored words: `asking`(1.5), `thank`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.followup.thank_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.followup.thank_them   [21 chars]
    en  Thank you for asking.
    >>  ............................................
    pt  Obrigado por perguntar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.parent.thank_them`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5  _(recorded under topic `family.parent.thank_them`)_
- Does: arc `family` — advance to stage 1
- Then opens: `conversations.topic.family.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.family.parent.thank_them
WHO    VILLAGER — what the player reads after pressing "Thank you for asking."
       spoken on: conversations.topic.ask_parent.followup, button `thank_them`
       leaves the player on: conversations.topic.family.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.thank_them.to.family`: the villager accepts. Subject `family`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.parent.thank_them/1   [51 chars]
    en  ...You're welcome. It's the only useful thing I do.
    >>  ............................................
    pt  ...De nada. É a única coisa útil que eu faço.
    >>  ............................................
  dialogue.conversations.family.parent.thank_them/2   [43 chars]
    en  Thanked for asking. Well. That's new, %1$s.
    >>  ............................................
    pt  Agradecido por perguntar. Bom. Isso é novo, %1$s.
    >>  ............................................
  dialogue.conversations.family.parent.thank_them/3   [27 chars]
    en  Any time. Truly — any time.
    >>  ............................................
    pt  Quando quiser. Sério — quando quiser.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome, %1$s. It's the only useful thing I do, and I'd not been sure it counted.
    >>  ............................................
    pt  ...De nada, %1$s. É a única coisa útil que eu faço, e eu não tinha certeza se contava.
    >>  ............................................
  anxious.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing. Being thanked for it has undone something and I'm not sorry.
    >>  ............................................
    pt  Certo. Precisa ser feito. Ser agradecido desfez algo e eu não lamento.
    >>  ............................................
  anxious.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. Nobody says that. I'd built a whole way of thinking around nobody saying it.
    >>  ............................................
    pt  Obrigado. Ninguém diz isso. Eu construí um jeito inteiro de pensar em volta de ninguém dizer.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome. It's the only useful thing I do and it's kept me busy for years.
    >>  ............................................
    pt  De nada. É a única coisa útil que eu faço e me manteve ocupado por anos.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and it'll need doing tomorrow. That's the whole of it.
    >>  ............................................
    pt  Certo. Precisa ser feito e vai precisar amanhã. É tudo.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. It's not said often. It doesn't need to be said often.
    >>  ............................................
    pt  Obrigado. Não se diz muito. Não precisa ser dito muito.
    >>  ............................................
  confident.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome. It's the only useful thing I do.
    >>  ............................................
    pt  De nada. É a única coisa útil que eu faço.
    >>  ............................................
  confident.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and I'm the one here.
    >>  ............................................
    pt  Certo. Precisa ser feito e eu sou quem está aqui.
    >>  ............................................
  confident.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. That's not said often in this house.
    >>  ............................................
    pt  Obrigado. Isso não se diz muito nesta casa.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome. It's the only useful thing I do.
    >>  ............................................
    pt  De nada. É a única coisa útil que eu faço.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and I'm the one here.
    >>  ............................................
    pt  Certo. Precisa ser feito e eu sou quem está aqui.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. That's not said often in this house.
    >>  ............................................
    pt  Obrigado. Isso não se diz muito nesta casa.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome, %1$s. It's the only useful thing I do.
    >>  ............................................
    pt  ...De nada, %1$s. É a única coisa útil que eu faço.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and I'm glad it's noticed, which it usually isn't.
    >>  ............................................
    pt  Certo. Precisa ser feito e fico contente que se repare, o que não costuma acontecer.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. Say it to them sometime as well — they'd not expect it either.
    >>  ............................................
    pt  Obrigado. Diga a eles um dia também — eles também não esperariam.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome, %1$s. It's the only useful thing I do.
    >>  ............................................
    pt  ...De nada, %1$s. É a única coisa útil que eu faço.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and I'm glad it's noticed, which it usually isn't.
    >>  ............................................
    pt  Certo. Precisa ser feito e fico contente que se repare, o que não costuma acontecer.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. Say it to them sometime as well — they'd not expect it either.
    >>  ............................................
    pt  Obrigado. Diga a eles um dia também — eles também não esperariam.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome, %1$s. It's the only useful thing I do.
    >>  ............................................
    pt  ...De nada, %1$s. É a única coisa útil que eu faço.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and I'm glad it's noticed, which it usually isn't.
    >>  ............................................
    pt  Certo. Precisa ser feito e fico contente que se repare, o que não costuma acontecer.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. Say it to them sometime as well — they'd not expect it either.
    >>  ............................................
    pt  Obrigado. Diga a eles um dia também — eles também não esperariam.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome, %1$s. It's the only useful thing I do, and I'd not been sure it counted.
    >>  ............................................
    pt  ...De nada, %1$s. É a única coisa útil que eu faço, e eu não tinha certeza se contava.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing. Being thanked for it has undone something and I'm not sorry.
    >>  ............................................
    pt  Certo. Precisa ser feito. Ser agradecido desfez algo e eu não lamento.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. Nobody says that. I'd built a whole way of thinking around nobody saying it.
    >>  ............................................
    pt  Obrigado. Ninguém diz isso. Eu construí um jeito inteiro de pensar em volta de ninguém dizer.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome. It's the only useful thing I do.
    >>  ............................................
    pt  De nada. É a única coisa útil que eu faço.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and I'm the one here.
    >>  ............................................
    pt  Certo. Precisa ser feito e eu sou quem está aqui.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. That's not said often in this house.
    >>  ............................................
    pt  Obrigado. Isso não se diz muito nesta casa.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome. It's the only useful thing I do.
    >>  ............................................
    pt  De nada. É a única coisa útil que eu faço.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and I'm the one here.
    >>  ............................................
    pt  Certo. Precisa ser feito e eu sou quem está aqui.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. That's not said often in this house.
    >>  ............................................
    pt  Obrigado. Isso não se diz muito nesta casa.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome. It's the only useful thing I do.
    >>  ............................................
    pt  ...De nada. É a única coisa útil que eu faço.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing.
    >>  ............................................
    pt  Certo. Precisa ser feito.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. That's not said often.
    >>  ............................................
    pt  Obrigado. Isso não se diz muito.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome. It's the only useful thing I do and it's kept me busy for years.
    >>  ............................................
    pt  De nada. É a única coisa útil que eu faço e me manteve ocupado por anos.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and it'll need doing tomorrow. That's the whole of it.
    >>  ............................................
    pt  Certo. Precisa ser feito e vai precisar amanhã. É tudo.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. It's not said often. It doesn't need to be said often.
    >>  ............................................
    pt  Obrigado. Não se diz muito. Não precisa ser dito muito.
    >>  ............................................
  odd.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome. It's the only useful thing I do.
    >>  ............................................
    pt  ...De nada. É a única coisa útil que eu faço.
    >>  ............................................
  odd.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing.
    >>  ............................................
    pt  Certo. Precisa ser feito.
    >>  ............................................
  odd.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. That's not said often.
    >>  ............................................
    pt  Obrigado. Isso não se diz muito.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome. It's the only useful thing I do and it's kept me busy for years.
    >>  ............................................
    pt  De nada. É a única coisa útil que eu faço e me manteve ocupado por anos.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and it'll need doing tomorrow. That's the whole of it.
    >>  ............................................
    pt  Certo. Precisa ser feito e vai precisar amanhã. É tudo.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. It's not said often. It doesn't need to be said often.
    >>  ............................................
    pt  Obrigado. Não se diz muito. Não precisa ser dito muito.
    >>  ............................................
  peppy.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome! It's the only useful thing I do, so I do rather cling to it.
    >>  ............................................
    pt  De nada! É a única coisa útil que eu faço, então eu me agarro a ela.
    >>  ............................................
  peppy.dialogue.conversations.family.parent.thank_them/2
    en  Right — it needs doing and I'm the one here. Marvellous system.
    >>  ............................................
    pt  Certo — precisa ser feito e eu sou quem está aqui. Sistema maravilhoso.
    >>  ............................................
  peppy.dialogue.conversations.family.parent.thank_them/3
    en  Thank you! That's not said often in this house and I'll be smug for a week.
    >>  ............................................
    pt  Obrigado! Isso não se diz muito nesta casa e eu vou me achar por uma semana.
    >>  ............................................
  playful.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome! It's the only useful thing I do, so I do rather cling to it.
    >>  ............................................
    pt  De nada! É a única coisa útil que eu faço, então eu me agarro a ela.
    >>  ............................................
  playful.dialogue.conversations.family.parent.thank_them/2
    en  Right — it needs doing and I'm the one here. Marvellous system.
    >>  ............................................
    pt  Certo — precisa ser feito e eu sou quem está aqui. Sistema maravilhoso.
    >>  ............................................
  playful.dialogue.conversations.family.parent.thank_them/3
    en  Thank you! That's not said often in this house and I'll be smug for a week.
    >>  ............................................
    pt  Obrigado! Isso não se diz muito nesta casa e eu vou me achar por uma semana.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome. It's the only useful thing I do and it's kept me busy for years.
    >>  ............................................
    pt  De nada. É a única coisa útil que eu faço e me manteve ocupado por anos.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing and it'll need doing tomorrow. That's the whole of it.
    >>  ............................................
    pt  Certo. Precisa ser feito e vai precisar amanhã. É tudo.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. It's not said often. It doesn't need to be said often.
    >>  ............................................
    pt  Obrigado. Não se diz muito. Não precisa ser dito muito.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome, %1$s. It's the only useful thing I do, and I'd not been sure it counted.
    >>  ............................................
    pt  ...De nada, %1$s. É a única coisa útil que eu faço, e eu não tinha certeza se contava.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing. Being thanked for it has undone something and I'm not sorry.
    >>  ............................................
    pt  Certo. Precisa ser feito. Ser agradecido desfez algo e eu não lamento.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. Nobody says that. I'd built a whole way of thinking around nobody saying it.
    >>  ............................................
    pt  Obrigado. Ninguém diz isso. Eu construí um jeito inteiro de pensar em volta de ninguém dizer.
    >>  ............................................
  shy.dialogue.conversations.family.parent.thank_them/1
    en  ...You're welcome. It's the only useful thing I do.
    >>  ............................................
    pt  ...De nada. É a única coisa útil que eu faço.
    >>  ............................................
  shy.dialogue.conversations.family.parent.thank_them/2
    en  Right. It needs doing.
    >>  ............................................
    pt  Certo. Precisa ser feito.
    >>  ............................................
  shy.dialogue.conversations.family.parent.thank_them/3
    en  Thank you. That's not said often.
    >>  ............................................
    pt  Obrigado. Isso não se diz muito.
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome! It's the only useful thing I do, so I do rather cling to it.
    >>  ............................................
    pt  De nada! É a única coisa útil que eu faço, então eu me agarro a ela.
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.thank_them/2
    en  Right — it needs doing and I'm the one here. Marvellous system.
    >>  ............................................
    pt  Certo — precisa ser feito e eu sou quem está aqui. Sistema maravilhoso.
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.thank_them/3
    en  Thank you! That's not said often in this house and I'll be smug for a week.
    >>  ............................................
    pt  Obrigado! Isso não se diz muito nesta casa e eu vou me achar por uma semana.
    >>  ............................................
  witty.dialogue.conversations.family.parent.thank_them/1
    en  You're welcome! It's the only useful thing I do, so I do rather cling to it.
    >>  ............................................
    pt  De nada! É a única coisa útil que eu faço, então eu me agarro a ela.
    >>  ............................................
  witty.dialogue.conversations.family.parent.thank_them/2
    en  Right — it needs doing and I'm the one here. Marvellous system.
    >>  ............................................
    pt  Certo — precisa ser feito e eu sou quem está aqui. Sistema maravilhoso.
    >>  ............................................
  witty.dialogue.conversations.family.parent.thank_them/3
    en  Thank you! That's not said often in this house and I'll be smug for a week.
    >>  ............................................
    pt  Obrigado! Isso não se diz muito nesta casa e eu vou me achar por uma semana.
    >>  ............................................
```

</details>


### Button `deflect` — "It's nothing. Really."

*stance family `candor` · tone `gentle` · answers the beat(s) `family.parent.answer_honestly.to.ask_parent`, `family.parent.reassure.to.ask_parent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.followup.deflect` — accepted phrasings: "it is nothing really"; "honestly it is nothing"; "there is nothing to it"
  - the message must contain one of: `nothing`
  - scored words: `nothing`(1.2), `really`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.followup.deflect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.followup.deflect   [21 chars]
    en  It's nothing. Really.
    >>  ............................................
    pt  Não é nada. Sério.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `family.parent.deflect`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust -3, tension +3  _(recorded under topic `family.parent.deflect`)_
- Then opens: `conversations.topic.family.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.family.parent.deflect
WHO    VILLAGER — what the player reads after pressing "It's nothing. Really."
       spoken on: conversations.topic.ask_parent.followup, button `deflect`
       leaves the player on: conversations.topic.family.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.deflect.to.family`: the villager deflects. Subject `family`, polarity `negative`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.parent.deflect/1   [34 chars]
    en  Nothing. It's never nothing, %1$s.
    >>  ............................................
    pt  Nada. Nunca é nada, %1$s.
    >>  ............................................
  dialogue.conversations.family.parent.deflect/2   [25 chars]
    en  ...Right. I'll not press.
    >>  ............................................
    pt  ...Certo. Não vou insistir.
    >>  ............................................
  dialogue.conversations.family.parent.deflect/3   [29 chars]
    en  Mm. I'll ask again next week.
    >>  ............................................
    pt  Hm. Vou perguntar de novo semana que vem.
    >>  ............................................
```


### Button `ask_event` — "Is this about what happened at home?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `family.parent.answer_honestly.to.ask_parent`, `family.parent.reassure.to.ask_parent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.event` — accepted phrasings: "is this about what happened at home"; "is this about the thing at home"; "is it about what happened"
  - scored words: `about`(0.3), `happened`(0.8), `home`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.followup.ask_event
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.followup.ask_event   [36 chars]
    en  Is this about what happened at home?
    >>  ............................................
    pt  Isso é sobre o que aconteceu em casa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.ask_parent.event`
- …where the player's next choices will be: "I'll tell you plainly, then." | "Next time you come with us." | "Not now."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.event
WHO    VILLAGER — what the player reads after pressing "Is this about what happened at home?"
       spoken on: conversations.topic.ask_parent.followup, button `ask_event`
       leaves the player on: conversations.topic.ask_parent.event
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.event`: the villager explains. Subject `ask_parent.event`, polarity `negative`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, exit
```

```text
  dialogue.conversations.ask_parent.event/1   [79 chars]
    en  Everyone stopped talking when I came in. That's how I knew there was something.
    >>  ............................................
    pt  Todo mundo parou de falar quando eu entrei. Foi assim que soube que tinha algo.
    >>  ............................................
  dialogue.conversations.ask_parent.event/2   [76 chars]
    en  You all went to the thing and I stayed with the neighbours. Nobody said why.
    >>  ............................................
    pt  Vocês todos foram lá e eu fiquei com os vizinhos. Ninguém disse por quê.
    >>  ............................................
  dialogue.conversations.ask_parent.event/3   [81 chars]
    en  It is. I'm not a baby. I'd rather be told the bad version than guess a worse one.
    >>  ............................................
    pt  É sim. Não sou bebê. Prefiro a versão ruim a adivinhar uma pior.
    >>  ............................................
```


### Button `leave` — "I should go."

*stance family `exit` · tone `plain` · answers the beat(s) `family.parent.answer_honestly.to.ask_parent`, `family.parent.reassure.to.ask_parent` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.followup.leave   [12 chars]
    en  I should go.
    >>  ............................................
    pt  Preciso ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.parent.leave
WHO    VILLAGER — what the player reads after pressing "I should go."
       spoken on: conversations.topic.ask_parent.followup, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.leave.terminal`: the villager accepts. Subject `family.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.ask_parent.respond / leave
```

```text
  dialogue.conversations.family.parent.leave/1   [26 chars]
    en  So it is. Mind how you go.
    >>  ............................................
    pt  É assim mesmo. Se cuida.
    >>  ............................................
  dialogue.conversations.family.parent.leave/2   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.family.parent.leave/3   [11 chars]
    en  So you are.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
```

---


## `conversations.topic.ask_parent.pride`

**Reached from 1 route(s):** `conversations.topic.ask_parent.respond` / `ask_pride`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.ask_parent.pride` — e.g. "I mended the fence myself. Nobody asked me to and nobody's noticed but you."


```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.pride
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.ask_parent.pride
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.ask_parent.pride   [33 chars]
    en  That's what I wanted to tell you.
    >>  ............................................
    pt  Era isso que eu queria te contar.
    >>  ............................................
```


### Button `say_it_back` — "That's a real thing to be proud of."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `ask_parent.pride`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.pride.said` — accepted phrasings: "that is a real thing to be proud of"; "you should be proud of that"; "that is worth being proud of"
  - the message must contain one of: `proud`
  - scored words: `proud`(1.2), `real`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.pride.say_it_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.pride
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.pride.say_it_back   [35 chars]
    en  That's a real thing to be proud of.
    >>  ............................................
    pt  Isso é motivo de verdade pra se orgulhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `ask_parent.pride.said`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `ask_parent.pride.said`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.pride.said
WHO    VILLAGER — what the player reads after pressing "That's a real thing to be proud of."
       spoken on: conversations.topic.ask_parent.pride, button `say_it_back`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.pride.said`: the villager accepts. Subject `ask_parent.pride`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.ask_parent.pride.said/1   [65 chars]
    en  You mean it? People say things to children. You don't say things.
    >>  ............................................
    pt  Sério? As pessoas dizem coisas pra criança. Você não diz por dizer.
    >>  ............................................
  dialogue.conversations.ask_parent.pride.said/2   [67 chars]
    en  Then I'll do the gate next. Don't watch — I want it finished first.
    >>  ............................................
    pt  Então faço o portão em seguida. Não olhe — quero terminar antes.
    >>  ............................................
  dialogue.conversations.ask_parent.pride.said/3   [71 chars]
    en  I've been carrying that around all week waiting for somebody to say so.
    >>  ............................................
    pt  Carreguei isso a semana toda esperando alguém dizer isso.
    >>  ............................................
```


### Button `show_me` — "Show me."

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `ask_parent.pride`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.pride.show` — accepted phrasings: "show me"; "let me see it"; "i would like to see it"
  - the message must contain one of: `show`
  - scored words: `show`(1.5), `see`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.pride.show_me
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.pride
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.pride.show_me   [8 chars]
    en  Show me.
    >>  ............................................
    pt  Me mostra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `ask_parent.pride.show`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +2  _(recorded under topic `ask_parent.pride.show`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.pride.show
WHO    VILLAGER — what the player reads after pressing "Show me."
       spoken on: conversations.topic.ask_parent.pride, button `show_me`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.pride.show`: the villager accepts. Subject `ask_parent.pride`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.ask_parent.pride.show/1   [78 chars]
    en  Now? ...Alright. Don't say anything about the corner, I know about the corner.
    >>  ............................................
    pt  Agora? ...Está bem. Não fale nada do canto, eu sei do canto.
    >>  ............................................
  dialogue.conversations.ask_parent.pride.show/2   [66 chars]
    en  You'll have to come out to the yard. And you'll have to not laugh.
    >>  ............................................
    pt  Você vai ter que ir até o quintal. E vai ter que não rir.
    >>  ............................................
  dialogue.conversations.ask_parent.pride.show/3   [62 chars]
    en  You're the first to ask to see it. Wait there — I'll fetch it.
    >>  ............................................
    pt  Você é o primeiro a pedir pra ver. Espere aí — vou buscar.
    >>  ............................................
```


### Button `leave` — "We'll look at it later."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `ask_parent.pride` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.pride.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.pride
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.pride.leave   [23 chars]
    en  We'll look at it later.
    >>  ............................................
    pt  A gente vê isso depois.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.pride.leave
WHO    VILLAGER — what the player reads after pressing "We'll look at it later."
       spoken on: conversations.topic.ask_parent.pride, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.pride.leave`: the villager accepts. Subject `ask_parent.pride`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.ask_parent.pride.leave/1   [12 chars]
    en  Later, then.
    >>  ............................................
    pt  Depois, então.
    >>  ............................................
  dialogue.conversations.ask_parent.pride.leave/2   [14 chars]
    en  You'll forget.
    >>  ............................................
    pt  Você vai esquecer.
    >>  ............................................
  dialogue.conversations.ask_parent.pride.leave/3   [15 chars]
    en  Alright. Later.
    >>  ............................................
    pt  Está bem. Depois.
    >>  ............................................
```

---


## `conversations.topic.ask_parent.rebuffed.followup`

**Reached from 1 route(s):** `conversations.topic.ask_parent.respond` / `snap`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.parent.snap` — e.g. "...I'll stop asking, then."


```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.rebuffed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.ask_parent.rebuffed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.ask_parent.rebuffed.followup   [23 chars]
    en  I'll stop asking, then.
    >>  ............................................
    pt  Então eu paro de perguntar.
    >>  ............................................
```


### Button `apologize` — "Ask away. I snapped and you didn't deserve it."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `ask_parent.rebuffed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.rebuffed.apologize` — accepted phrasings: "ask away. i snapped and you didn't deserve it"
  - the message must contain one of: `snapped`, `deserve`, `harsh`
  - scored words: `snapped`(1.5), `deserve`(1.2), `harsh`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.rebuffed.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.rebuffed.followup.apologize   [46 chars]
    en  Ask away. I snapped and you didn't deserve it.
    >>  ............................................
    pt  Pode perguntar. Eu fui ríspido e você não merecia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `ask_parent.rebuffed.apologize`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.rebuffed.apologize
WHO    VILLAGER — what the player reads after pressing "Ask away. I snapped and you didn't deserve it."
       spoken on: conversations.topic.ask_parent.rebuffed.followup, button `apologize`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.rebuffed.apologize`: the villager qualifys. Subject `ask_parent.worry`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.ask_parent.rebuffed.apologize/1   [47 chars]
    en  ...Then I'll ask again tomorrow. Quieter, mind.
    >>  ............................................
    pt  ...Então eu pergunto de novo amanhã. Mas mais baixo.
    >>  ............................................
  dialogue.conversations.ask_parent.rebuffed.apologize/2   [59 chars]
    en  You'd be surprised how rarely anyone says that to me, %1$s.
    >>  ............................................
    pt  Você ficaria surpreso de saber como raramente alguém me diz isso, %1$s.
    >>  ............................................
  dialogue.conversations.ask_parent.rebuffed.apologize/3   [69 chars]
    en  Alright. But you'll answer properly next time, or I really will stop.
    >>  ............................................
    pt  Está bem. Mas da próxima você responde direito, ou aí eu paro mesmo.
    >>  ............................................
```


### Button `explain` — "It's not the asking. It's how often."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `ask_parent.rebuffed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.rebuffed.explain` — accepted phrasings: "it's not the asking. it's how often"
  - the message must contain one of: `often`, `asking`, `frequency`
  - scored words: `often`(1.5), `asking`(1.2), `frequency`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.rebuffed.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.rebuffed.followup.explain   [36 chars]
    en  It's not the asking. It's how often.
    >>  ............................................
    pt  Não é o perguntar. É a frequência.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `ask_parent.rebuffed.explain`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.rebuffed.explain
WHO    VILLAGER — what the player reads after pressing "It's not the asking. It's how often."
       spoken on: conversations.topic.ask_parent.rebuffed.followup, button `explain`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.rebuffed.explain`: the villager qualifys. Subject `ask_parent.worry`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.ask_parent.rebuffed.explain/1   [61 chars]
    en  ...That's a fair distinction, and I'll try to hear it as one.
    >>  ............................................
    pt  ...É uma distinção justa, e vou tentar ouvir como tal.
    >>  ............................................
  dialogue.conversations.ask_parent.rebuffed.explain/2   [52 chars]
    en  How often. Right. I'd not counted, %1$s. Now I will.
    >>  ............................................
    pt  A frequência. Certo. Eu não tinha contado, %1$s. Agora vou contar.
    >>  ............................................
  dialogue.conversations.ask_parent.rebuffed.explain/3   [66 chars]
    en  Then tell me the number and I'll keep to it. I'm not unreasonable.
    >>  ............................................
    pt  Então me diga o número e eu respeito. Eu não sou irracional.
    >>  ............................................
```


### Button `leave` — "I'll leave it for today."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `ask_parent.rebuffed.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.rebuffed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.rebuffed.followup.leave   [24 chars]
    en  I'll leave it for today.
    >>  ............................................
    pt  Vou deixar pra hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.rebuffed.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it for today."
       spoken on: conversations.topic.ask_parent.rebuffed.followup, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.rebuffed.leave`: the villager accepts. Subject `ask_parent.worry`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.ask_parent.rebuffed.leave/1   [17 chars]
    en  Just so. Do that.
    >>  ............................................
    pt  Pois é. Faça isso.
    >>  ............................................
  dialogue.conversations.ask_parent.rebuffed.leave/2   [17 chars]
    en  Off you go, %1$s.
    >>  ............................................
    pt  Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.ask_parent.rebuffed.leave/3   [19 chars]
    en  Mm. Tomorrow, then.
    >>  ............................................
    pt  Mm. Amanhã, então.
    >>  ............................................
```

---


## `conversations.topic.ask_parent.respond`

**Reached from 3 route(s):** `conversations.family` / `ask_parent`; `conversations.family` / `ask_parent`; `conversations.family` / `ask_parent`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.parent.again` — e.g. "You've had one question out of me today. Save the next for when it's heavier."
- `conversations.family.parent.proud` — e.g. "You turned out well, you know. I don't say it enough. I'm saying it now."
- `conversations.family.parent.worried` — e.g. "I worry, that's all. It's the job. You never stop being someone's parent."


```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.ask_parent.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.ask_parent.respond   [29 chars]
    en  That's what I wanted to know.
    >>  ............................................
    pt  É isso que eu queria saber.
    >>  ............................................
```


### Button `answer_honestly` — "I'll tell you the truth."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `family.parent.again.to.ask_parent`, `family.parent.proud.to.ask_parent`, `family.parent.worried.to.ask_parent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.answer_honestly` — accepted phrasings: "i will tell you the truth"; "you will get the truth from me"; "i will be honest with you"
  - the message must contain one of: `truth`
  - scored words: `tell`(0.4), `truth`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.respond.answer_honestly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.respond.answer_honestly   [24 chars]
    en  I'll tell you the truth.
    >>  ............................................
    pt  Vou te dizer a verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.parent.answer_honestly`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +5, warmth +2  _(recorded under topic `family.parent.answer_honestly`)_
- Then opens: `conversations.topic.ask_parent.followup`
- …where the player's next choices will be: "What are you worried about?" | "Thank you for asking." | "It's nothing. Really." | "Is this about what happened at home?" | "I should go."

```text
POOL   dialogue key: dialogue.conversations.family.parent.answer_honestly
WHO    VILLAGER — what the player reads after pressing "I'll tell you the truth."
       spoken on: conversations.topic.ask_parent.respond, button `answer_honestly`
       leaves the player on: conversations.topic.ask_parent.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.answer_honestly.to.ask_parent`: the villager accepts. Subject `ask_parent`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.parent.answer_honestly/1   [55 chars]
    en  ...Thank you. You've never fobbed me off, and I notice.
    >>  ............................................
    pt  ...Obrigado. Você nunca me enrolou, e eu percebo.
    >>  ............................................
  dialogue.conversations.family.parent.answer_honestly/2   [41 chars]
    en  The truth. Right. That's all I was after.
    >>  ............................................
    pt  A verdade. Certo. Era só isso que eu queria.
    >>  ............................................
  dialogue.conversations.family.parent.answer_honestly/3   [57 chars]
    en  Good. I'd rather a hard true thing than a soft false one.
    >>  ............................................
    pt  Bom. Prefiro uma verdade dura a uma mentira macia.
    >>  ............................................
```


### Button `reassure` — "You don't need to worry about me."

*stance family `restraint` · tone `gentle` · answers the beat(s) `family.parent.again.to.ask_parent`, `family.parent.proud.to.ask_parent`, `family.parent.worried.to.ask_parent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.reassure` — accepted phrasings: "you do not need to worry about me"; "there is no need to worry"; "do not worry about me"
  - the message must contain one of: `worry`
  - scored words: `need`(0.4), `worry`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.respond.reassure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.respond.reassure   [33 chars]
    en  You don't need to worry about me.
    >>  ............................................
    pt  Você não precisa se preocupar comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.parent.reassure`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +1  _(recorded under topic `family.parent.reassure`)_
- Then opens: `conversations.topic.ask_parent.followup`
- …where the player's next choices will be: "What are you worried about?" | "Thank you for asking." | "It's nothing. Really." | "Is this about what happened at home?" | "I should go."

```text
POOL   dialogue key: dialogue.conversations.family.parent.reassure
WHO    VILLAGER — what the player reads after pressing "You don't need to worry about me."
       spoken on: conversations.topic.ask_parent.respond, button `reassure`
       leaves the player on: conversations.topic.ask_parent.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.reassure.to.ask_parent`: the villager accepts. Subject `ask_parent`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.parent.reassure/1   [48 chars]
    en  I'll worry anyway. That's the arrangement, %1$s.
    >>  ............................................
    pt  Vou me preocupar mesmo assim. É o combinado, %1$s.
    >>  ............................................
  dialogue.conversations.family.parent.reassure/2   [48 chars]
    en  ...Alright. I'll try to worry less. No promises.
    >>  ............................................
    pt  ...Certo. Vou tentar me preocupar menos. Sem promessas.
    >>  ............................................
  dialogue.conversations.family.parent.reassure/3   [54 chars]
    en  It helps to hear it. It doesn't stop it, but it helps.
    >>  ............................................
    pt  Ajuda ouvir isso. Não faz parar, mas ajuda.
    >>  ............................................
```


### Button `snap` — "Stop fussing over me."

*stance family `dismissal` · tone `hostile` · answers the beat(s) `family.parent.again.to.ask_parent`, `family.parent.proud.to.ask_parent`, `family.parent.worried.to.ask_parent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.snap` — accepted phrasings: "stop fussing over me"; "stop making a fuss"; "leave off fussing"
  - the message must contain one of: `fuss`, `fussing`
  - scored words: `fuss`(1.2), `fussing`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.respond.snap
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.respond.snap   [21 chars]
    en  Stop fussing over me.
    >>  ............................................
    pt  Para de ficar em cima de mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `family.parent.snap`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -4, tension +6  _(recorded under topic `family.parent.snap`)_
- Does: session `turn`
- Then opens: `conversations.topic.ask_parent.rebuffed.followup`
- …where the player's next choices will be: "Ask away. I snapped and you didn't deserve it." | "It's not the asking. It's how often." | "I'll leave it for today."

```text
POOL   dialogue key: dialogue.conversations.family.parent.snap
WHO    VILLAGER — what the player reads after pressing "Stop fussing over me."
       spoken on: conversations.topic.ask_parent.respond, button `snap`
       leaves the player on: conversations.topic.ask_parent.rebuffed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.rebuffed.open`: the villager hurts. Subject `ask_parent.worry`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.family.parent.snap/1   [26 chars]
    en  ...I'll stop asking, then.
    >>  ............................................
    pt  ...Então paro de perguntar.
    >>  ............................................
  dialogue.conversations.family.parent.snap/2   [47 chars]
    en  Fussing. Is that what it looks like from there?
    >>  ............................................
    pt  Ficar em cima. É assim que parece daí?
    >>  ............................................
  dialogue.conversations.family.parent.snap/3   [35 chars]
    en  Very well. I'll leave you be, %1$s.
    >>  ............................................
    pt  Muito bem. Vou te deixar em paz, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.parent.snap/1
    en  ...Sorry. I shouldn't have kept on, %1$s.
    >>  ............................................
    pt  ...Desculpe. Eu não devia ter insistido, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.family.parent.snap/2
    en  I'll stop. I didn't mean to press on it.
    >>  ............................................
    pt  Eu paro. Não queria pressionar nisso.
    >>  ............................................
  anxious.dialogue.conversations.family.parent.snap/3
    en  ...Right. That was mine to have noticed sooner.
    >>  ............................................
    pt  ...Certo. Era coisa que eu devia ter percebido antes.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.snap/1
    en  ...All right. Another time, perhaps.
    >>  ............................................
    pt  ...Está bem. Outra hora, talvez.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.snap/2
    en  Fair enough. It'll keep.
    >>  ............................................
    pt  Tudo bem. Fica pra depois.
    >>  ............................................
  athletic.dialogue.conversations.family.parent.snap/3
    en  ...Right you are. I'll leave it there.
    >>  ............................................
    pt  ...Você tem razão. Deixo aí.
    >>  ............................................
  confident.dialogue.conversations.family.parent.snap/1
    en  ...I'll stop asking, then.
    >>  ............................................
    pt  ...Então eu paro de perguntar.
    >>  ............................................
  confident.dialogue.conversations.family.parent.snap/2
    en  Right. That subject is closed and I closed it.
    >>  ............................................
    pt  Certo. Esse assunto está encerrado e fui eu que encerrei.
    >>  ............................................
  confident.dialogue.conversations.family.parent.snap/3
    en  ...We'll talk about something else.
    >>  ............................................
    pt  ...Vamos falar de outra coisa.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.snap/1
    en  ...I'll stop asking, then.
    >>  ............................................
    pt  ...Então eu paro de perguntar.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.snap/2
    en  Right. That subject is closed and I closed it.
    >>  ............................................
    pt  Certo. Esse assunto está encerrado e fui eu que encerrei.
    >>  ............................................
  crabby.dialogue.conversations.family.parent.snap/3
    en  ...We'll talk about something else.
    >>  ............................................
    pt  ...Vamos falar de outra coisa.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.snap/1
    en  ...I'll stop asking, %1$s. I only wanted to know how they were.
    >>  ............................................
    pt  ...Eu paro de perguntar, %1$s. Eu só queria saber como eles estavam.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.snap/2
    en  Right. I'll not raise them again unless you do.
    >>  ............................................
    pt  Certo. Não levanto de novo a menos que você levante.
    >>  ............................................
  extroverted.dialogue.conversations.family.parent.snap/3
    en  ...That's fine. It was your family, not mine to open.
    >>  ............................................
    pt  ...Tudo bem. É a sua família, não era minha pra abrir.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.snap/1
    en  ...I'll stop asking, %1$s. I only wanted to know how they were.
    >>  ............................................
    pt  ...Eu paro de perguntar, %1$s. Eu só queria saber como eles estavam.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.snap/2
    en  Right. I'll not raise them again unless you do.
    >>  ............................................
    pt  Certo. Não levanto de novo a menos que você levante.
    >>  ............................................
  flirty.dialogue.conversations.family.parent.snap/3
    en  ...That's fine. It was your family, not mine to open.
    >>  ............................................
    pt  ...Tudo bem. É a sua família, não era minha pra abrir.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.snap/1
    en  ...I'll stop asking, %1$s. I only wanted to know how they were.
    >>  ............................................
    pt  ...Eu paro de perguntar, %1$s. Eu só queria saber como eles estavam.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.snap/2
    en  Right. I'll not raise them again unless you do.
    >>  ............................................
    pt  Certo. Não levanto de novo a menos que você levante.
    >>  ............................................
  friendly.dialogue.conversations.family.parent.snap/3
    en  ...That's fine. It was your family, not mine to open.
    >>  ............................................
    pt  ...Tudo bem. É a sua família, não era minha pra abrir.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.snap/1
    en  ...Sorry. I shouldn't have kept on, %1$s.
    >>  ............................................
    pt  ...Desculpe. Eu não devia ter insistido, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.snap/2
    en  I'll stop. I didn't mean to press on it.
    >>  ............................................
    pt  Eu paro. Não queria pressionar nisso.
    >>  ............................................
  gloomy.dialogue.conversations.family.parent.snap/3
    en  ...Right. That was mine to have noticed sooner.
    >>  ............................................
    pt  ...Certo. Era coisa que eu devia ter percebido antes.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.snap/1
    en  ...I'll stop asking, then.
    >>  ............................................
    pt  ...Então eu paro de perguntar.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.snap/2
    en  Right. That subject is closed and I closed it.
    >>  ............................................
    pt  Certo. Esse assunto está encerrado e fui eu que encerrei.
    >>  ............................................
  greedy.dialogue.conversations.family.parent.snap/3
    en  ...We'll talk about something else.
    >>  ............................................
    pt  ...Vamos falar de outra coisa.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.snap/1
    en  ...I'll stop asking, then.
    >>  ............................................
    pt  ...Então eu paro de perguntar.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.snap/2
    en  Right. That subject is closed and I closed it.
    >>  ............................................
    pt  Certo. Esse assunto está encerrado e fui eu que encerrei.
    >>  ............................................
  grumpy.dialogue.conversations.family.parent.snap/3
    en  ...We'll talk about something else.
    >>  ............................................
    pt  ...Vamos falar de outra coisa.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.snap/1
    en  ...Right. I'll stop.
    >>  ............................................
    pt  ...Certo. Eu paro.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.snap/2
    en  That's closed, then.
    >>  ............................................
    pt  Então está encerrado.
    >>  ............................................
  introverted.dialogue.conversations.family.parent.snap/3
    en  ...I'll not ask again.
    >>  ............................................
    pt  ...Não pergunto de novo.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.snap/1
    en  ...All right. Another time, perhaps.
    >>  ............................................
    pt  ...Está bem. Outra hora, talvez.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.snap/2
    en  Fair enough. It'll keep.
    >>  ............................................
    pt  Tudo bem. Fica pra depois.
    >>  ............................................
  lazy.dialogue.conversations.family.parent.snap/3
    en  ...Right you are. I'll leave it there.
    >>  ............................................
    pt  ...Você tem razão. Deixo aí.
    >>  ............................................
  odd.dialogue.conversations.family.parent.snap/1
    en  ...Right. I'll stop.
    >>  ............................................
    pt  ...Certo. Eu paro.
    >>  ............................................
  odd.dialogue.conversations.family.parent.snap/2
    en  That's closed, then.
    >>  ............................................
    pt  Então está encerrado.
    >>  ............................................
  odd.dialogue.conversations.family.parent.snap/3
    en  ...I'll not ask again.
    >>  ............................................
    pt  ...Não pergunto de novo.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.snap/1
    en  ...All right. Another time, perhaps.
    >>  ............................................
    pt  ...Está bem. Outra hora, talvez.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.snap/2
    en  Fair enough. It'll keep.
    >>  ............................................
    pt  Tudo bem. Fica pra depois.
    >>  ............................................
  peaceful.dialogue.conversations.family.parent.snap/3
    en  ...Right you are. I'll leave it there.
    >>  ............................................
    pt  ...Você tem razão. Deixo aí.
    >>  ............................................
  peppy.dialogue.conversations.family.parent.snap/1
    en  ...Right! Different subject. Plenty of those about.
    >>  ............................................
    pt  ...Certo! Outro assunto. Tem bastante por aí.
    >>  ............................................
  peppy.dialogue.conversations.family.parent.snap/2
    en  Well, that door's shut. I'll find another one.
    >>  ............................................
    pt  Bom, essa porta fechou. Vou achar outra.
    >>  ............................................
  peppy.dialogue.conversations.family.parent.snap/3
    en  ...Fine. Ask me about the weather instead.
    >>  ............................................
    pt  ...Tudo bem. Me pergunte do tempo, então.
    >>  ............................................
  playful.dialogue.conversations.family.parent.snap/1
    en  ...Right! Different subject. Plenty of those about.
    >>  ............................................
    pt  ...Certo! Outro assunto. Tem bastante por aí.
    >>  ............................................
  playful.dialogue.conversations.family.parent.snap/2
    en  Well, that door's shut. I'll find another one.
    >>  ............................................
    pt  Bom, essa porta fechou. Vou achar outra.
    >>  ............................................
  playful.dialogue.conversations.family.parent.snap/3
    en  ...Fine. Ask me about the weather instead.
    >>  ............................................
    pt  ...Tudo bem. Me pergunte do tempo, então.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.snap/1
    en  ...All right. Another time, perhaps.
    >>  ............................................
    pt  ...Está bem. Outra hora, talvez.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.snap/2
    en  Fair enough. It'll keep.
    >>  ............................................
    pt  Tudo bem. Fica pra depois.
    >>  ............................................
  relaxed.dialogue.conversations.family.parent.snap/3
    en  ...Right you are. I'll leave it there.
    >>  ............................................
    pt  ...Você tem razão. Deixo aí.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.snap/1
    en  ...Sorry. I shouldn't have kept on, %1$s.
    >>  ............................................
    pt  ...Desculpe. Eu não devia ter insistido, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.snap/2
    en  I'll stop. I didn't mean to press on it.
    >>  ............................................
    pt  Eu paro. Não queria pressionar nisso.
    >>  ............................................
  sensitive.dialogue.conversations.family.parent.snap/3
    en  ...Right. That was mine to have noticed sooner.
    >>  ............................................
    pt  ...Certo. Era coisa que eu devia ter percebido antes.
    >>  ............................................
  shy.dialogue.conversations.family.parent.snap/1
    en  ...Right. I'll stop.
    >>  ............................................
    pt  ...Certo. Eu paro.
    >>  ............................................
  shy.dialogue.conversations.family.parent.snap/2
    en  That's closed, then.
    >>  ............................................
    pt  Então está encerrado.
    >>  ............................................
  shy.dialogue.conversations.family.parent.snap/3
    en  ...I'll not ask again.
    >>  ............................................
    pt  ...Não pergunto de novo.
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.snap/1
    en  ...Right! Different subject. Plenty of those about.
    >>  ............................................
    pt  ...Certo! Outro assunto. Tem bastante por aí.
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.snap/2
    en  Well, that door's shut. I'll find another one.
    >>  ............................................
    pt  Bom, essa porta fechou. Vou achar outra.
    >>  ............................................
  upbeat.dialogue.conversations.family.parent.snap/3
    en  ...Fine. Ask me about the weather instead.
    >>  ............................................
    pt  ...Tudo bem. Me pergunte do tempo, então.
    >>  ............................................
  witty.dialogue.conversations.family.parent.snap/1
    en  ...Right! Different subject. Plenty of those about.
    >>  ............................................
    pt  ...Certo! Outro assunto. Tem bastante por aí.
    >>  ............................................
  witty.dialogue.conversations.family.parent.snap/2
    en  Well, that door's shut. I'll find another one.
    >>  ............................................
    pt  Bom, essa porta fechou. Vou achar outra.
    >>  ............................................
  witty.dialogue.conversations.family.parent.snap/3
    en  ...Fine. Ask me about the weather instead.
    >>  ............................................
    pt  ...Tudo bem. Me pergunte do tempo, então.
    >>  ............................................
```

</details>


### Button `ask_pride` — "Is there something you're proud of?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `family.parent.again.to.ask_parent`, `family.parent.proud.to.ask_parent`, `family.parent.worried.to.ask_parent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `ask_parent.pride` — accepted phrasings: "is there something you are proud of"; "what are you proud of"; "tell me something you did well"
  - the message must contain one of: `pleased`, `proud`
  - scored words: `pleased`(1.0), `proud`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.respond.ask_pride
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.respond.ask_pride   [35 chars]
    en  Is there something you're proud of?
    >>  ............................................
    pt  Tem alguma coisa que te deixa orgulhoso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.ask_parent.pride`
- …where the player's next choices will be: "That's a real thing to be proud of." | "Show me." | "We'll look at it later."

```text
POOL   dialogue key: dialogue.conversations.ask_parent.pride
WHO    VILLAGER — what the player reads after pressing "Is there something you're proud of?"
       spoken on: conversations.topic.ask_parent.respond, button `ask_pride`
       leaves the player on: conversations.topic.ask_parent.pride
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `ask_parent.pride`: the villager reports. Subject `ask_parent.pride`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, exit
```

```text
  dialogue.conversations.ask_parent.pride/1   [75 chars]
    en  I mended the fence myself. Nobody asked me to and nobody's noticed but you.
    >>  ............................................
    pt  Consertei a cerca sozinho. Ninguém me pediu e ninguém notou, só você.
    >>  ............................................
  dialogue.conversations.ask_parent.pride/2   [74 chars]
    en  I can read the long words now. All of them, not only the ones on the sign.
    >>  ............................................
    pt  Já consigo ler as palavras compridas. Todas, não só as da placa.
    >>  ............................................
  dialogue.conversations.ask_parent.pride/3   [78 chars]
    en  ...I didn't cry when the old dog went. I wanted to. I thought you should know.
    >>  ............................................
    pt  ...Eu não chorei quando o cachorro velho se foi. Eu queria. Achei que você devia saber.
    >>  ............................................
```


### Button `leave` — "I should go."

*stance family `exit` · tone `plain` · answers the beat(s) `family.parent.again.to.ask_parent`, `family.parent.proud.to.ask_parent`, `family.parent.worried.to.ask_parent` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.ask_parent.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.ask_parent.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.ask_parent.respond.leave   [12 chars]
    en  I should go.
    >>  ............................................
    pt  Preciso ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.parent.leave
WHO    VILLAGER — what the player reads after pressing "I should go."
       spoken on: conversations.topic.ask_parent.respond, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.leave.terminal`: the villager accepts. Subject `family.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.ask_parent.followup / leave
```

> Written out in full under **`conversations.topic.ask_parent.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

