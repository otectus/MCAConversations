# Topic: secret

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `secret` |
| Opened from | question `conversations.cat.personal`, button `secret` |
| Depth class (its heart budget) | `deep` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | adult |
| Stance families it must offer | `restraint`, `curiosity`, `self_disclosure`, `dismissal`, `exit` |
| Narrative arc | `secret`, max stage 2 |
| Milestones it can set | `secret.entrusted` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.secret
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.secret   [17 chars]
    en  Tell me a secret.
    >>  ............................................
    pt  Me conta um segredo.
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.secret.resume.followup`](#conversations-arc-secret-resume-followup)
- [`conversations.arc.secret.resume.respond`](#conversations-arc-secret-resume-respond)
- [`conversations.scene.secret.deciding_whether_to_say.respond`](#conversations-scene-secret-deciding-whether-to-say-respond)
- [`conversations.scene.secret.followup`](#conversations-scene-secret-followup)
- [`conversations.scene.secret.holding_somebody_elses.respond`](#conversations-scene-secret-holding-somebody-elses-respond)
- [`conversations.topic.secret.close`](#conversations-topic-secret-close)
- [`conversations.topic.secret.declined`](#conversations-topic-secret-declined)
- [`conversations.topic.secret.followup`](#conversations-topic-secret-followup)
- [`conversations.topic.secret.guarded.respond`](#conversations-topic-secret-guarded-respond)
- [`conversations.topic.secret.respond`](#conversations-topic-secret-respond)
- [`conversations.topic.secret.slighted.close`](#conversations-topic-secret-slighted-close)

---

## `conversations.arc.secret.resume.followup`

**Reached from 6 route(s):** `conversations.arc.secret.resume.respond` / `reference`; `conversations.arc.secret.resume.respond` / `reference`; `conversations.arc.secret.resume.respond` / `reference`; `conversations.arc.secret.resume.respond` / `check_in`; `conversations.arc.secret.resume.respond` / `check_in`; `conversations.arc.secret.resume.respond` / `ask_who_knows`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.secret.resume.ask_who_knows` — e.g. "Nobody. You're the whole list, %1$s. That's why I check the door when I say it."
- `conversations.secret.resume.check_in` — e.g. "...You asked after ME, not the secret. Nobody does that."
- `conversations.secret.resume.check_in.entrusted` — e.g. "...You asked after me, not the secret. You always were the right person to tell."
- `conversations.secret.resume.reference.declined` — e.g. "You wouldn't promise, and you've kept it anyway. That says more."
- `conversations.secret.resume.reference.kept` — e.g. "You gave your word and you've kept it. I checked, in my way. Thank you."
- `conversations.secret.resume.reference.plain` — e.g. "It's still between us, then. Good."


```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.secret.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.secret.resume.followup   [27 chars]
    en  That's the shape of it now.
    >>  ............................................
    pt  É essa a forma disso agora.
    >>  ............................................
```


### Button `reassure` — "It's safe. It stays safe."

*stance family `restraint` · tone `gentle` · answers the beat(s) `secret.resume.ask_who_knows.to.secret`, `secret.resume.check_in.entrusted.to.secret`, `secret.resume.check_in.to.secret`, `secret.resume.reference.declined.to.secret`, `secret.resume.reference.kept.to.secret`, `secret.resume.reference.plain.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.resume.followup.reassure` — accepted phrasings: "it is safe and it stays safe"; "it stays safe with me"; "it is safe"
  - the message must contain one of: `safe`
  - scored words: `safe`(1.6), `stays`(1.1)

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.followup.reassure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.followup.reassure   [25 chars]
    en  It's safe. It stays safe.
    >>  ............................................
    pt  Está seguro. Continua seguro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.resume.followup.reassure`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +5, tension -3  _(recorded under topic `secret.resume.followup.reassure`)_
- Then opens: `conversations.topic.secret.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Then you should have one of mine." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.followup.reassure
WHO    VILLAGER — what the player reads after pressing "It's safe. It stays safe."
       spoken on: conversations.arc.secret.resume.followup, button `reassure`
       leaves the player on: conversations.topic.secret.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.followup.reassure.to.secret`: the villager accepts. Subject `secret`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.followup.reassure/1   [77 chars]
    en  I know. I check anyway — not on you, on the world. It's a habit, not a doubt.
    >>  ............................................
    pt  Eu sei. Eu confiro assim mesmo — não você, o mundo. É hábito, não desconfiança.
    >>  ............................................
  dialogue.conversations.secret.resume.followup.reassure/2   [94 chars]
    en  Says the only person who could break it. ...And hasn't. That's the whole of my evidence, %1$s.
    >>  ............................................
    pt  Diz a única pessoa que poderia quebrar. ...E não quebrou. É toda a minha prova, %1$s.
    >>  ............................................
  dialogue.conversations.secret.resume.followup.reassure/3   [80 chars]
    en  Safe. Right. I'll put that sentence somewhere I can get at it on the bad nights.
    >>  ............................................
    pt  Seguro. Certo. Vou guardar essa frase onde eu alcance nas noites ruins.
    >>  ............................................
```


### Button `ask_burden` — "Is it heavy, still?"

*stance family `curiosity` · tone `gentle` · answers the beat(s) `secret.resume.ask_who_knows.to.secret`, `secret.resume.check_in.entrusted.to.secret`, `secret.resume.check_in.to.secret`, `secret.resume.reference.declined.to.secret`, `secret.resume.reference.kept.to.secret`, `secret.resume.reference.plain.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.resume.followup.ask_burden` — accepted phrasings: "is it heavy still"; "is it still heavy"; "does it still weigh on you"
  - the message must contain one of: `heavy`, `still`, `weigh`
  - scored words: `heavy`(1.6), `still`(1.0), `weigh`(1.4)

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.followup.ask_burden
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.followup.ask_burden   [19 chars]
    en  Is it heavy, still?
    >>  ............................................
    pt  Ainda pesa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.resume.followup.ask_burden`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, familiarity +2  _(recorded under topic `secret.resume.followup.ask_burden`)_
- Then opens: `conversations.topic.secret.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Then you should have one of mine." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.followup.ask_burden
WHO    VILLAGER — what the player reads after pressing "Is it heavy, still?"
       spoken on: conversations.arc.secret.resume.followup, button `ask_burden`
       leaves the player on: conversations.topic.secret.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.followup.ask_burden.to.secret`: the villager accepts. Subject `secret`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.followup.ask_burden/1   [87 chars]
    en  Lighter than it was. Heavy things don't stop being heavy; they just get better handles.
    >>  ............................................
    pt  Menos do que pesava. Coisa pesada não deixa de ser pesada; só ganha alça melhor.
    >>  ............................................
  dialogue.conversations.secret.resume.followup.ask_burden/2   [71 chars]
    en  Some weeks. Not this one. You asking is part of why not this one, %1$s.
    >>  ............................................
    pt  Algumas semanas. Esta não. Você perguntar é parte do motivo de esta não, %1$s.
    >>  ............................................
  dialogue.conversations.secret.resume.followup.ask_burden/3   [77 chars]
    en  It is. But it's a shared heavy now, and that's a completely different weight.
    >>  ............................................
    pt  Pesa. Mas agora é um peso dividido, e isso é um peso completamente diferente.
    >>  ............................................
```


### Button `plan` — "What happens if it does get out?"

*stance family `practical_help` · tone `plain` · answers the beat(s) `secret.resume.ask_who_knows.to.secret`, `secret.resume.check_in.entrusted.to.secret`, `secret.resume.check_in.to.secret`, `secret.resume.reference.declined.to.secret`, `secret.resume.reference.kept.to.secret`, `secret.resume.reference.plain.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.resume.followup.plan` — accepted phrasings: "what happens if it does get out"; "what if it gets out"; "and if it gets out"
  - the message must contain one of: `gets`, `out`
  - scored words: `gets`(1.4), `out`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.followup.plan
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.followup.plan   [32 chars]
    en  What happens if it does get out?
    >>  ............................................
    pt  E se vazar, o que acontece?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +3, familiarity +3  _(recorded under topic `secret.resume.followup.plan`)_
- Then opens: `conversations.topic.secret.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Then you should have one of mine." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.followup.plan
WHO    VILLAGER — what the player reads after pressing "What happens if it does get out?"
       spoken on: conversations.arc.secret.resume.followup, button `plan`
       leaves the player on: conversations.topic.secret.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.followup.plan.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.followup.plan/1   [91 chars]
    en  If it gets out... I'd know it wasn't you, first. Then I'd survive it. I've worked that far.
    >>  ............................................
    pt  Se vazar... eu saberia primeiro que não foi você. Depois eu sobreviveria. Cheguei até aí.
    >>  ............................................
  dialogue.conversations.secret.resume.followup.plan/2   [86 chars]
    en  I'd deny it once, badly, and then I'd stop denying it. That's as far as the plan goes.
    >>  ............................................
    pt  Eu negaria uma vez, mal, e depois pararia de negar. O plano vai até aí.
    >>  ............................................
  dialogue.conversations.secret.resume.followup.plan/3   [97 chars]
    en  Hm. ...It would be a bad week, and then it would be a Tuesday, I expect. Ask me something easier.
    >>  ............................................
    pt  Hm. ...Seria uma semana ruim, e depois seria uma terça-feira, eu acho. Me pergunte algo mais fácil.
    >>  ............................................
```


### Button `leave` — "It's safe. I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.resume.ask_who_knows.to.secret`, `secret.resume.check_in.entrusted.to.secret`, `secret.resume.check_in.to.secret`, `secret.resume.reference.declined.to.secret`, `secret.resume.reference.kept.to.secret`, `secret.resume.reference.plain.to.secret` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.followup.leave   [19 chars]
    en  It's safe. I'll go.
    >>  ............................................
    pt  Está seguro. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.followup.leave
WHO    VILLAGER — what the player reads after pressing "It's safe. I'll go."
       spoken on: conversations.arc.secret.resume.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.followup.leave.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.resume.followup.leave/1   [52 chars]
    en  Just so. Off you go, and thank you for the checking.
    >>  ............................................
    pt  Pois é. Pode ir, e obrigado por conferir.
    >>  ............................................
  dialogue.conversations.secret.resume.followup.leave/2   [26 chars]
    en  Right you are. Still ours.
    >>  ............................................
    pt  Isso mesmo. Ainda nosso.
    >>  ............................................
  dialogue.conversations.secret.resume.followup.leave/3   [59 chars]
    en  Go on, %1$s. I sleep better for these little conversations.
    >>  ............................................
    pt  Vai lá, %1$s. Eu durmo melhor por causa dessas conversinhas.
    >>  ............................................
```

---


## `conversations.arc.secret.resume.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `secret`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.secret.revisit` — e.g. "Still keeping what I told you? ...Good. Then maybe there's another where that came from, someday."


```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.secret.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.secret.resume.respond   [30 chars]
    en  That thing I trusted you with.
    >>  ............................................
    pt  Aquilo que eu confiei a você.
    >>  ............................................
```


### Button `reference` — "I've told nobody, you know."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `secret.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.resume.reference` — accepted phrasings: "i have told nobody"; "i kept it"; "nobody has heard it from me"
  - the message must contain one of: `nobody`, `kept`, `told`
  - scored words: `nobody`(1.5), `told`(1.0), `kept`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.respond.reference
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.respond.reference   [27 chars]
    en  I've told nobody, you know.
    >>  ............................................
    pt  Não contei a ninguém, sabia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when exclusive `secret.promise` is `kept`
- Does: **hearts +2** — decision id `secret.resume.reference`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +5, warmth +2  _(recorded under topic `secret.resume.reference`)_
- Does: arc `secret` — advance to stage 2
- Then opens: `conversations.arc.secret.resume.followup`
- …where the player's next choices will be: "It's safe. It stays safe." | "Is it heavy, still?" | "What happens if it does get out?" | "It's safe. I'll go."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.reference.kept
WHO    VILLAGER — what the player reads after pressing "I've told nobody, you know."
       spoken on: conversations.arc.secret.resume.respond, button `reference`
       leaves the player on: conversations.arc.secret.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.reference.kept.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.reference.kept/1   [71 chars]
    en  You gave your word and you've kept it. I checked, in my way. Thank you.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu verifiquei, do meu jeito. Obrigado.
    >>  ............................................
  dialogue.conversations.secret.resume.reference.kept/2   [63 chars]
    en  Nobody's looked at me strangely since. That's how I know, %1$s.
    >>  ............................................
    pt  Ninguém me olhou estranho desde então. É assim que eu sei, %1$s.
    >>  ............................................
  dialogue.conversations.secret.resume.reference.kept/3   [59 chars]
    en  A promise kept. It's rarer than you'd think, and I noticed.
    >>  ............................................
    pt  Uma promessa cumprida. É mais raro do que se pensa, e eu notei.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way, %1$s, and I'm sorry that I did.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito, %1$s, e me desculpe por ter conferido.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I've been waiting to find out and I'd rather not have been.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu vinha esperando descobrir e preferia não ter esperado.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. I'd braced for the other outcome for a month.
    >>  ............................................
    pt  Você disse que faria e fez. Eu me preparei pro outro resultado por um mês.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. Months of it. That's what makes a word worth anything.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Meses disso. É o que faz uma palavra valer.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. It'll go on being kept, I expect, and that's the point.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Vai continuar sendo, eu imagino, e é essa a questão.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. Slowly proved, which is the only proof that counts.
    >>  ............................................
    pt  Você disse que faria e fez. Provado devagar, que é a única prova que conta.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. That's the whole audit.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. É toda a auditoria.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. Noted, and thank you.
    >>  ............................................
    pt  Você disse que faria e fez. Anotado, e obrigado.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. That's the whole audit.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. É toda a auditoria.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. Noted, and thank you.
    >>  ............................................
    pt  Você disse que faria e fez. Anotado, e obrigado.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it, %1$s. I checked, in my way. Thank you.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu, %1$s. Eu conferi, do meu jeito. Obrigado.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I'd not have held it against you and I'm glad I don't have to.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu não guardaria mágoa e fico contente de não precisar.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. That's most of what I mean when I say I trust you.
    >>  ............................................
    pt  Você disse que faria e fez. É quase tudo que eu quero dizer quando digo que confio em você.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it, %1$s. I checked, in my way. Thank you.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu, %1$s. Eu conferi, do meu jeito. Obrigado.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I'd not have held it against you and I'm glad I don't have to.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu não guardaria mágoa e fico contente de não precisar.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. That's most of what I mean when I say I trust you.
    >>  ............................................
    pt  Você disse que faria e fez. É quase tudo que eu quero dizer quando digo que confio em você.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it, %1$s. I checked, in my way. Thank you.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu, %1$s. Eu conferi, do meu jeito. Obrigado.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I'd not have held it against you and I'm glad I don't have to.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu não guardaria mágoa e fico contente de não precisar.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. That's most of what I mean when I say I trust you.
    >>  ............................................
    pt  Você disse que faria e fez. É quase tudo que eu quero dizer quando digo que confio em você.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way, %1$s, and I'm sorry that I did.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito, %1$s, e me desculpe por ter conferido.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I've been waiting to find out and I'd rather not have been.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu vinha esperando descobrir e preferia não ter esperado.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. I'd braced for the other outcome for a month.
    >>  ............................................
    pt  Você disse que faria e fez. Eu me preparei pro outro resultado por um mês.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. That's the whole audit.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. É toda a auditoria.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. Noted, and thank you.
    >>  ............................................
    pt  Você disse que faria e fez. Anotado, e obrigado.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. That's the whole audit.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. É toda a auditoria.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. Noted, and thank you.
    >>  ............................................
    pt  Você disse que faria e fez. Anotado, e obrigado.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept.
    >>  ............................................
    pt  Palavra dada, palavra cumprida.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did.
    >>  ............................................
    pt  Você disse que faria e fez.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. Months of it. That's what makes a word worth anything.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Meses disso. É o que faz uma palavra valer.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. It'll go on being kept, I expect, and that's the point.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Vai continuar sendo, eu imagino, e é essa a questão.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. Slowly proved, which is the only proof that counts.
    >>  ............................................
    pt  Você disse que faria e fez. Provado devagar, que é a única prova que conta.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept.
    >>  ............................................
    pt  Palavra dada, palavra cumprida.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did.
    >>  ............................................
    pt  Você disse que faria e fez.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. Months of it. That's what makes a word worth anything.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Meses disso. É o que faz uma palavra valer.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. It'll go on being kept, I expect, and that's the point.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Vai continuar sendo, eu imagino, e é essa a questão.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. Slowly proved, which is the only proof that counts.
    >>  ............................................
    pt  Você disse que faria e fez. Provado devagar, que é a única prova que conta.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it! I checked, in my way. Thoroughly.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu! Eu conferi, do meu jeito. Minuciosamente.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I ran a small and entirely unnecessary investigation.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu conduzi uma investigação pequena e desnecessária.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did! Extraordinary. Nobody does both.
    >>  ............................................
    pt  Você disse que faria e fez! Extraordinário. Ninguém faz os dois.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it! I checked, in my way. Thoroughly.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu! Eu conferi, do meu jeito. Minuciosamente.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I ran a small and entirely unnecessary investigation.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu conduzi uma investigação pequena e desnecessária.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did! Extraordinary. Nobody does both.
    >>  ............................................
    pt  Você disse que faria e fez! Extraordinário. Ninguém faz os dois.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. Months of it. That's what makes a word worth anything.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Meses disso. É o que faz uma palavra valer.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. It'll go on being kept, I expect, and that's the point.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Vai continuar sendo, eu imagino, e é essa a questão.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. Slowly proved, which is the only proof that counts.
    >>  ............................................
    pt  Você disse que faria e fez. Provado devagar, que é a única prova que conta.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way, %1$s, and I'm sorry that I did.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito, %1$s, e me desculpe por ter conferido.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I've been waiting to find out and I'd rather not have been.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu vinha esperando descobrir e preferia não ter esperado.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did. I'd braced for the other outcome for a month.
    >>  ............................................
    pt  Você disse que faria e fez. Eu me preparei pro outro resultado por um mês.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it. I checked, in my way.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu. Eu conferi, do meu jeito.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept.
    >>  ............................................
    pt  Palavra dada, palavra cumprida.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did.
    >>  ............................................
    pt  Você disse que faria e fez.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it! I checked, in my way. Thoroughly.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu! Eu conferi, do meu jeito. Minuciosamente.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I ran a small and entirely unnecessary investigation.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu conduzi uma investigação pequena e desnecessária.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did! Extraordinary. Nobody does both.
    >>  ............................................
    pt  Você disse que faria e fez! Extraordinário. Ninguém faz os dois.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.kept/1
    en  You gave your word and you've kept it! I checked, in my way. Thoroughly.
    >>  ............................................
    pt  Você deu sua palavra e cumpriu! Eu conferi, do meu jeito. Minuciosamente.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.kept/2
    en  Word given, word kept. I ran a small and entirely unnecessary investigation.
    >>  ............................................
    pt  Palavra dada, palavra cumprida. Eu conduzi uma investigação pequena e desnecessária.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.kept/3
    en  You said you would and you did! Extraordinary. Nobody does both.
    >>  ............................................
    pt  Você disse que faria e fez! Extraordinário. Ninguém faz os dois.
    >>  ............................................
```

</details>


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when exclusive `secret.promise` is `declined`
- Does: **hearts +1** — decision id `secret.resume.reference`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `secret.resume.reference`)_
- Does: arc `secret` — advance to stage 2
- Then opens: `conversations.arc.secret.resume.followup`
- …where the player's next choices will be: "It's safe. It stays safe." | "Is it heavy, still?" | "What happens if it does get out?" | "It's safe. I'll go."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.reference.declined
WHO    VILLAGER — what the player reads after pressing "I've told nobody, you know."
       spoken on: conversations.arc.secret.resume.respond, button `reference`
       leaves the player on: conversations.arc.secret.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.reference.declined.to.secret`: the villager accepts. Subject `secret`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.reference.declined/1   [64 chars]
    en  You wouldn't promise, and you've kept it anyway. That says more.
    >>  ............................................
    pt  Você não prometeu, e mesmo assim guardou. Isso diz mais.
    >>  ............................................
  dialogue.conversations.secret.resume.reference.declined/2   [63 chars]
    en  No oath from you. And still not a word out. I think about that.
    >>  ............................................
    pt  Nenhum juramento seu. E mesmo assim nem uma palavra. Eu penso nisso.
    >>  ............................................
  dialogue.conversations.secret.resume.reference.declined/3   [53 chars]
    en  You only said you'd try. You tried, %1$s. That'll do.
    >>  ............................................
    pt  Você só disse que tentaria. Você tentou, %1$s. Já basta.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway, %1$s. That says more than a promise could.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim, %1$s. Isso diz mais que uma promessa.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. I'd have believed a promise and then waited to be wrong.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. Eu teria acreditado numa promessa e esperado errar.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. I've had sworn oaths broken. I've never had this.
    >>  ............................................
    pt  Você se recusou a jurar. Já tive juramentos quebrados. Nunca tive isto.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway. Habits outlast oaths.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim. Hábitos duram mais que juramentos.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak, months on. That's the only proof there is.
    >>  ............................................
    pt  Sem promessa, sem vazamento, meses depois. É a única prova que existe.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. Time has said the rest better than you could have.
    >>  ............................................
    pt  Você se recusou a jurar. O tempo disse o resto melhor do que você poderia.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway. That says more.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim. Isso diz mais.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. That's the version I trust.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. É a versão em que eu confio.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. Then you did it. Right.
    >>  ............................................
    pt  Você se recusou a jurar. Depois fez. Certo.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway. That says more.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim. Isso diz mais.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. That's the version I trust.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. É a versão em que eu confio.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. Then you did it. Right.
    >>  ............................................
    pt  Você se recusou a jurar. Depois fez. Certo.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, %1$s, and you've kept it anyway. That says more.
    >>  ............................................
    pt  Você não quis prometer, %1$s, e guardou mesmo assim. Isso diz mais.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. I'd rather that than a vow from anybody else.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. Prefiro isso a um juramento de qualquer outro.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. That's why I believed the rest of you.
    >>  ............................................
    pt  Você se recusou a jurar. Por isso eu acreditei no resto de você.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, %1$s, and you've kept it anyway. That says more.
    >>  ............................................
    pt  Você não quis prometer, %1$s, e guardou mesmo assim. Isso diz mais.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. I'd rather that than a vow from anybody else.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. Prefiro isso a um juramento de qualquer outro.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. That's why I believed the rest of you.
    >>  ............................................
    pt  Você se recusou a jurar. Por isso eu acreditei no resto de você.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, %1$s, and you've kept it anyway. That says more.
    >>  ............................................
    pt  Você não quis prometer, %1$s, e guardou mesmo assim. Isso diz mais.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. I'd rather that than a vow from anybody else.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. Prefiro isso a um juramento de qualquer outro.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. That's why I believed the rest of you.
    >>  ............................................
    pt  Você se recusou a jurar. Por isso eu acreditei no resto de você.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway, %1$s. That says more than a promise could.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim, %1$s. Isso diz mais que uma promessa.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. I'd have believed a promise and then waited to be wrong.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. Eu teria acreditado numa promessa e esperado errar.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. I've had sworn oaths broken. I've never had this.
    >>  ............................................
    pt  Você se recusou a jurar. Já tive juramentos quebrados. Nunca tive isto.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway. That says more.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim. Isso diz mais.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. That's the version I trust.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. É a versão em que eu confio.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. Then you did it. Right.
    >>  ............................................
    pt  Você se recusou a jurar. Depois fez. Certo.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway. That says more.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim. Isso diz mais.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. That's the version I trust.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. É a versão em que eu confio.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. Then you did it. Right.
    >>  ............................................
    pt  Você se recusou a jurar. Depois fez. Certo.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak.
    >>  ............................................
    pt  Sem promessa, sem vazamento.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. That says more.
    >>  ............................................
    pt  Você se recusou a jurar. Isso diz mais.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway. Habits outlast oaths.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim. Hábitos duram mais que juramentos.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak, months on. That's the only proof there is.
    >>  ............................................
    pt  Sem promessa, sem vazamento, meses depois. É a única prova que existe.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. Time has said the rest better than you could have.
    >>  ............................................
    pt  Você se recusou a jurar. O tempo disse o resto melhor do que você poderia.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak.
    >>  ............................................
    pt  Sem promessa, sem vazamento.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. That says more.
    >>  ............................................
    pt  Você se recusou a jurar. Isso diz mais.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway. Habits outlast oaths.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim. Hábitos duram mais que juramentos.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak, months on. That's the only proof there is.
    >>  ............................................
    pt  Sem promessa, sem vazamento, meses depois. É a única prova que existe.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. Time has said the rest better than you could have.
    >>  ............................................
    pt  Você se recusou a jurar. O tempo disse o resto melhor do que você poderia.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway! That says more than the promise would have.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim! Isso diz mais que a promessa diria.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak. That's the good kind of person and there are about four of them.
    >>  ............................................
    pt  Sem promessa, sem vazamento. É o bom tipo de pessoa e existem umas quatro.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it and then you did it. Delightful. Infuriating. Delightful.
    >>  ............................................
    pt  Você se recusou a jurar e depois fez. Encantador. Irritante. Encantador.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway! That says more than the promise would have.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim! Isso diz mais que a promessa diria.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak. That's the good kind of person and there are about four of them.
    >>  ............................................
    pt  Sem promessa, sem vazamento. É o bom tipo de pessoa e existem umas quatro.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it and then you did it. Delightful. Infuriating. Delightful.
    >>  ............................................
    pt  Você se recusou a jurar e depois fez. Encantador. Irritante. Encantador.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway. Habits outlast oaths.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim. Hábitos duram mais que juramentos.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak, months on. That's the only proof there is.
    >>  ............................................
    pt  Sem promessa, sem vazamento, meses depois. É a única prova que existe.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. Time has said the rest better than you could have.
    >>  ............................................
    pt  Você se recusou a jurar. O tempo disse o resto melhor do que você poderia.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway, %1$s. That says more than a promise could.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim, %1$s. Isso diz mais que uma promessa.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, and no leak. I'd have believed a promise and then waited to be wrong.
    >>  ............................................
    pt  Sem promessa, e sem vazamento. Eu teria acreditado numa promessa e esperado errar.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. I've had sworn oaths broken. I've never had this.
    >>  ............................................
    pt  Você se recusou a jurar. Já tive juramentos quebrados. Nunca tive isto.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak.
    >>  ............................................
    pt  Sem promessa, sem vazamento.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it. That says more.
    >>  ............................................
    pt  Você se recusou a jurar. Isso diz mais.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway! That says more than the promise would have.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim! Isso diz mais que a promessa diria.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak. That's the good kind of person and there are about four of them.
    >>  ............................................
    pt  Sem promessa, sem vazamento. É o bom tipo de pessoa e existem umas quatro.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it and then you did it. Delightful. Infuriating. Delightful.
    >>  ............................................
    pt  Você se recusou a jurar e depois fez. Encantador. Irritante. Encantador.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.declined/1
    en  You wouldn't promise, and you've kept it anyway! That says more than the promise would have.
    >>  ............................................
    pt  Você não quis prometer, e guardou mesmo assim! Isso diz mais que a promessa diria.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.declined/2
    en  No promise, no leak. That's the good kind of person and there are about four of them.
    >>  ............................................
    pt  Sem promessa, sem vazamento. É o bom tipo de pessoa e existem umas quatro.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.declined/3
    en  You refused to swear to it and then you did it. Delightful. Infuriating. Delightful.
    >>  ............................................
    pt  Você se recusou a jurar e depois fez. Encantador. Irritante. Encantador.
    >>  ............................................
```

</details>


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when exclusive `secret.promise` is `kept`  _(chance -2000)_
- Fires when: RULED OUT when exclusive `secret.promise` is `declined`  _(chance -2000)_
- Does: **hearts +1** — decision id `secret.resume.reference`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — familiarity +3  _(recorded under topic `secret.resume.reference`)_
- Does: arc `secret` — advance to stage 2
- Then opens: `conversations.arc.secret.resume.followup`
- …where the player's next choices will be: "It's safe. It stays safe." | "Is it heavy, still?" | "What happens if it does get out?" | "It's safe. I'll go."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.reference.plain
WHO    VILLAGER — what the player reads after pressing "I've told nobody, you know."
       spoken on: conversations.arc.secret.resume.respond, button `reference`
       leaves the player on: conversations.arc.secret.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.reference.plain.to.secret`: the villager accepts. Subject `secret`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.reference.plain/1   [34 chars]
    en  It's still between us, then. Good.
    >>  ............................................
    pt  Então ainda está entre nós. Bom.
    >>  ............................................
  dialogue.conversations.secret.resume.reference.plain/2   [48 chars]
    en  Nobody's mentioned it. I take that as an answer.
    >>  ............................................
    pt  Ninguém mencionou. Tomo isso como resposta.
    >>  ............................................
  dialogue.conversations.secret.resume.reference.plain/3   [40 chars]
    en  Still ours. That's all I wanted to know.
    >>  ............................................
    pt  Ainda nosso. Era só isso que eu queria saber.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then, %1$s. Good. I'd been listening for it in the square.
    >>  ............................................
    pt  Continua entre nós, então, %1$s. Bom. Eu vinha escutando na praça.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. I'd not have blamed you. I'd have known, but I'd not have blamed you.
    >>  ............................................
    pt  Ainda nosso. Eu não te culparia. Eu saberia, mas não culparia.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. That's a month of quiet worry I can put down now.
    >>  ............................................
    pt  Nada se moveu. É um mês de preocupação silenciosa que eu posso largar.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good. It'll still be between us next year, I expect.
    >>  ............................................
    pt  Continua entre nós, então. Bom. Vai continuar entre nós ano que vem, eu imagino.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. These things either hold from the start or they never do.
    >>  ............................................
    pt  Ainda nosso. Essas coisas ou se sustentam desde o começo ou nunca.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. Months of nothing moving is how a secret becomes safe.
    >>  ............................................
    pt  Nada se moveu. Meses de nada se mover é como um segredo fica seguro.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good.
    >>  ............................................
    pt  Continua entre nós, então. Bom.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. Right.
    >>  ............................................
    pt  Ainda nosso. Certo.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. That's the report I wanted.
    >>  ............................................
    pt  Nada se moveu. É o relatório que eu queria.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good.
    >>  ............................................
    pt  Continua entre nós, então. Bom.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. Right.
    >>  ............................................
    pt  Ainda nosso. Certo.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. That's the report I wanted.
    >>  ............................................
    pt  Nada se moveu. É o relatório que eu queria.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then, %1$s. Good.
    >>  ............................................
    pt  Continua entre nós, então, %1$s. Bom.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. I'd not doubted it and I'm glad to have it said out loud.
    >>  ............................................
    pt  Ainda nosso. Eu não duvidei e fico contente de ouvir dito em voz alta.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. Thank you. That's more of a gift than it sounds.
    >>  ............................................
    pt  Nada se moveu. Obrigado. É mais presente do que parece.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then, %1$s. Good.
    >>  ............................................
    pt  Continua entre nós, então, %1$s. Bom.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. I'd not doubted it and I'm glad to have it said out loud.
    >>  ............................................
    pt  Ainda nosso. Eu não duvidei e fico contente de ouvir dito em voz alta.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. Thank you. That's more of a gift than it sounds.
    >>  ............................................
    pt  Nada se moveu. Obrigado. É mais presente do que parece.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then, %1$s. Good.
    >>  ............................................
    pt  Continua entre nós, então, %1$s. Bom.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. I'd not doubted it and I'm glad to have it said out loud.
    >>  ............................................
    pt  Ainda nosso. Eu não duvidei e fico contente de ouvir dito em voz alta.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. Thank you. That's more of a gift than it sounds.
    >>  ............................................
    pt  Nada se moveu. Obrigado. É mais presente do que parece.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then, %1$s. Good. I'd been listening for it in the square.
    >>  ............................................
    pt  Continua entre nós, então, %1$s. Bom. Eu vinha escutando na praça.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. I'd not have blamed you. I'd have known, but I'd not have blamed you.
    >>  ............................................
    pt  Ainda nosso. Eu não te culparia. Eu saberia, mas não culparia.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. That's a month of quiet worry I can put down now.
    >>  ............................................
    pt  Nada se moveu. É um mês de preocupação silenciosa que eu posso largar.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good.
    >>  ............................................
    pt  Continua entre nós, então. Bom.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. Right.
    >>  ............................................
    pt  Ainda nosso. Certo.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. That's the report I wanted.
    >>  ............................................
    pt  Nada se moveu. É o relatório que eu queria.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good.
    >>  ............................................
    pt  Continua entre nós, então. Bom.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. Right.
    >>  ............................................
    pt  Ainda nosso. Certo.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. That's the report I wanted.
    >>  ............................................
    pt  Nada se moveu. É o relatório que eu queria.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good.
    >>  ............................................
    pt  Continua entre nós, então. Bom.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours.
    >>  ............................................
    pt  Ainda nosso.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved.
    >>  ............................................
    pt  Nada se moveu.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good. It'll still be between us next year, I expect.
    >>  ............................................
    pt  Continua entre nós, então. Bom. Vai continuar entre nós ano que vem, eu imagino.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. These things either hold from the start or they never do.
    >>  ............................................
    pt  Ainda nosso. Essas coisas ou se sustentam desde o começo ou nunca.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. Months of nothing moving is how a secret becomes safe.
    >>  ............................................
    pt  Nada se moveu. Meses de nada se mover é como um segredo fica seguro.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good.
    >>  ............................................
    pt  Continua entre nós, então. Bom.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours.
    >>  ............................................
    pt  Ainda nosso.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved.
    >>  ............................................
    pt  Nada se moveu.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good. It'll still be between us next year, I expect.
    >>  ............................................
    pt  Continua entre nós, então. Bom. Vai continuar entre nós ano que vem, eu imagino.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. These things either hold from the start or they never do.
    >>  ............................................
    pt  Ainda nosso. Essas coisas ou se sustentam desde o começo ou nunca.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. Months of nothing moving is how a secret becomes safe.
    >>  ............................................
    pt  Nada se moveu. Meses de nada se mover é como um segredo fica seguro.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then! Good. Excellent. Wonderful.
    >>  ............................................
    pt  Continua entre nós, então! Bom. Excelente. Maravilhoso.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. Right. I shall stop checking the square for a fortnight.
    >>  ............................................
    pt  Ainda nosso. Certo. Vou parar de conferir a praça por quinze dias.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved! That is genuinely the best news I've had this week.
    >>  ............................................
    pt  Nada se moveu! É genuinamente a melhor notícia da minha semana.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then! Good. Excellent. Wonderful.
    >>  ............................................
    pt  Continua entre nós, então! Bom. Excelente. Maravilhoso.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. Right. I shall stop checking the square for a fortnight.
    >>  ............................................
    pt  Ainda nosso. Certo. Vou parar de conferir a praça por quinze dias.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved! That is genuinely the best news I've had this week.
    >>  ............................................
    pt  Nada se moveu! É genuinamente a melhor notícia da minha semana.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good. It'll still be between us next year, I expect.
    >>  ............................................
    pt  Continua entre nós, então. Bom. Vai continuar entre nós ano que vem, eu imagino.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. These things either hold from the start or they never do.
    >>  ............................................
    pt  Ainda nosso. Essas coisas ou se sustentam desde o começo ou nunca.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. Months of nothing moving is how a secret becomes safe.
    >>  ............................................
    pt  Nada se moveu. Meses de nada se mover é como um segredo fica seguro.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then, %1$s. Good. I'd been listening for it in the square.
    >>  ............................................
    pt  Continua entre nós, então, %1$s. Bom. Eu vinha escutando na praça.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. I'd not have blamed you. I'd have known, but I'd not have blamed you.
    >>  ............................................
    pt  Ainda nosso. Eu não te culparia. Eu saberia, mas não culparia.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved. That's a month of quiet worry I can put down now.
    >>  ............................................
    pt  Nada se moveu. É um mês de preocupação silenciosa que eu posso largar.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then. Good.
    >>  ............................................
    pt  Continua entre nós, então. Bom.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours.
    >>  ............................................
    pt  Ainda nosso.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved.
    >>  ............................................
    pt  Nada se moveu.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then! Good. Excellent. Wonderful.
    >>  ............................................
    pt  Continua entre nós, então! Bom. Excelente. Maravilhoso.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. Right. I shall stop checking the square for a fortnight.
    >>  ............................................
    pt  Ainda nosso. Certo. Vou parar de conferir a praça por quinze dias.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved! That is genuinely the best news I've had this week.
    >>  ............................................
    pt  Nada se moveu! É genuinamente a melhor notícia da minha semana.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.plain/1
    en  It's still between us, then! Good. Excellent. Wonderful.
    >>  ............................................
    pt  Continua entre nós, então! Bom. Excelente. Maravilhoso.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.plain/2
    en  Still ours. Right. I shall stop checking the square for a fortnight.
    >>  ............................................
    pt  Ainda nosso. Certo. Vou parar de conferir a praça por quinze dias.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.reference.plain/3
    en  Nothing has moved! That is genuinely the best news I've had this week.
    >>  ............................................
    pt  Nada se moveu! É genuinamente a melhor notícia da minha semana.
    >>  ............................................
```

</details>


### Button `check_in` — "How are you carrying it?"

*stance family `curiosity` · tone `gentle` · answers the beat(s) `secret.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.resume.check_in` — accepted phrasings: "how are you carrying it"; "how are you bearing it"; "how is it sitting with you"
  - the message must contain one of: `carrying`, `bearing`, `how`
  - scored words: `carrying`(1.5), `how`(0.5), `bearing`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.respond.check_in
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.respond.check_in   [24 chars]
    en  How are you carrying it?
    >>  ............................................
    pt  Como você está carregando isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when milestone `secret.entrusted` is set
- Does: **hearts +2** — decision id `secret.resume.check_in`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `secret.resume.check_in`)_
- Does: arc `secret` — advance to stage 2
- Then opens: `conversations.arc.secret.resume.followup`
- …where the player's next choices will be: "It's safe. It stays safe." | "Is it heavy, still?" | "What happens if it does get out?" | "It's safe. I'll go."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.check_in.entrusted
WHO    VILLAGER — what the player reads after pressing "How are you carrying it?"
       spoken on: conversations.arc.secret.resume.respond, button `check_in`
       leaves the player on: conversations.arc.secret.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.check_in.entrusted.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.check_in.entrusted/1   [80 chars]
    en  ...You asked after me, not the secret. You always were the right person to tell.
    >>  ............................................
    pt  ...Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa para contar.
    >>  ............................................
  dialogue.conversations.secret.resume.check_in.entrusted/2   [75 chars]
    en  Lighter, since you know. That's the whole use of having told someone, %1$s.
    >>  ............................................
    pt  Mais leve, desde que você sabe. É essa a utilidade de ter contado a alguém, %1$s.
    >>  ............................................
  dialogue.conversations.secret.resume.check_in.entrusted/3   [71 chars]
    en  Better. Knowing one person carries it with me changes the weight of it.
    >>  ............................................
    pt  Melhor. Saber que uma pessoa carrega junto muda o peso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, %1$s. Not the secret. You always were the right person, and I'd been checking.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Você sempre foi a pessoa certa, e eu vinha conferindo.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. I'd told myself the first time was luck. Twice isn't luck.
    >>  ............................................
    pt  De mim primeiro, de novo. Eu dizia que a primeira foi sorte. Duas não é sorte.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. That has made the last few months a great deal lighter.
    >>  ............................................
    pt  Ainda a pessoa certa. Isso tornou os últimos meses muito mais leves.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret. You always were the right person, and time has borne it out.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa, e o tempo confirmou.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. Twice, months apart. That's what makes it something I can rely on.
    >>  ............................................
    pt  De mim primeiro, de novo. Duas vezes, com meses de diferença. É isso que faz eu poder contar.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. These things prove themselves slowly and this one has.
    >>  ............................................
    pt  Ainda a pessoa certa. Essas coisas se provam devagar e esta se provou.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret. You always were the right person.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. That's twice, and twice is a pattern.
    >>  ............................................
    pt  De mim primeiro, de novo. Foram duas vezes, e duas vezes é um padrão.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Right. Still the right person. I'll stop being surprised eventually.
    >>  ............................................
    pt  Certo. Ainda a pessoa certa. Uma hora eu paro de me surpreender.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret. You always were the right person.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. That's twice, and twice is a pattern.
    >>  ............................................
    pt  De mim primeiro, de novo. Foram duas vezes, e duas vezes é um padrão.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Right. Still the right person. I'll stop being surprised eventually.
    >>  ............................................
    pt  Certo. Ainda a pessoa certa. Uma hora eu paro de me surpreender.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, %1$s. Not the secret. You always were the right person.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. I'd like you to know I noticed both times.
    >>  ............................................
    pt  De mim primeiro, de novo. Queria que você soubesse que eu reparei nas duas vezes.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. That's the whole of why this has been easy to carry since.
    >>  ............................................
    pt  Ainda a pessoa certa. É toda a razão de isso ter sido fácil de carregar desde então.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, %1$s. Not the secret. You always were the right person.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. I'd like you to know I noticed both times.
    >>  ............................................
    pt  De mim primeiro, de novo. Queria que você soubesse que eu reparei nas duas vezes.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. That's the whole of why this has been easy to carry since.
    >>  ............................................
    pt  Ainda a pessoa certa. É toda a razão de isso ter sido fácil de carregar desde então.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, %1$s. Not the secret. You always were the right person.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. I'd like you to know I noticed both times.
    >>  ............................................
    pt  De mim primeiro, de novo. Queria que você soubesse que eu reparei nas duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. That's the whole of why this has been easy to carry since.
    >>  ............................................
    pt  Ainda a pessoa certa. É toda a razão de isso ter sido fácil de carregar desde então.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, %1$s. Not the secret. You always were the right person, and I'd been checking.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Você sempre foi a pessoa certa, e eu vinha conferindo.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. I'd told myself the first time was luck. Twice isn't luck.
    >>  ............................................
    pt  De mim primeiro, de novo. Eu dizia que a primeira foi sorte. Duas não é sorte.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. That has made the last few months a great deal lighter.
    >>  ............................................
    pt  Ainda a pessoa certa. Isso tornou os últimos meses muito mais leves.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret. You always were the right person.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. That's twice, and twice is a pattern.
    >>  ............................................
    pt  De mim primeiro, de novo. Foram duas vezes, e duas vezes é um padrão.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Right. Still the right person. I'll stop being surprised eventually.
    >>  ............................................
    pt  Certo. Ainda a pessoa certa. Uma hora eu paro de me surpreender.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret. You always were the right person.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. That's twice, and twice is a pattern.
    >>  ............................................
    pt  De mim primeiro, de novo. Foram duas vezes, e duas vezes é um padrão.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Right. Still the right person. I'll stop being surprised eventually.
    >>  ............................................
    pt  Certo. Ainda a pessoa certa. Uma hora eu paro de me surpreender.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, not the secret. You always were the right person.
    >>  ............................................
    pt  ...Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again.
    >>  ............................................
    pt  De mim primeiro, de novo.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person.
    >>  ............................................
    pt  Ainda a pessoa certa.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret. You always were the right person, and time has borne it out.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa, e o tempo confirmou.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. Twice, months apart. That's what makes it something I can rely on.
    >>  ............................................
    pt  De mim primeiro, de novo. Duas vezes, com meses de diferença. É isso que faz eu poder contar.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. These things prove themselves slowly and this one has.
    >>  ............................................
    pt  Ainda a pessoa certa. Essas coisas se provam devagar e esta se provou.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, not the secret. You always were the right person.
    >>  ............................................
    pt  ...Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again.
    >>  ............................................
    pt  De mim primeiro, de novo.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person.
    >>  ............................................
    pt  Ainda a pessoa certa.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret. You always were the right person, and time has borne it out.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa, e o tempo confirmou.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. Twice, months apart. That's what makes it something I can rely on.
    >>  ............................................
    pt  De mim primeiro, de novo. Duas vezes, com meses de diferença. É isso que faz eu poder contar.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. These things prove themselves slowly and this one has.
    >>  ............................................
    pt  Ainda a pessoa certa. Essas coisas se provam devagar e esta se provou.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret! You always were the right person for this.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo! Você sempre foi a pessoa certa pra isso.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again! That's twice. Twice is officially a pattern and I'm delighted.
    >>  ............................................
    pt  De mim primeiro, de novo! Foram duas. Duas é oficialmente um padrão e eu estou encantado.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Right — still the right person. I'd chosen well and I'm insufferable about it.
    >>  ............................................
    pt  Certo — ainda a pessoa certa. Eu escolhi bem e sou insuportável sobre isso.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret! You always were the right person for this.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo! Você sempre foi a pessoa certa pra isso.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again! That's twice. Twice is officially a pattern and I'm delighted.
    >>  ............................................
    pt  De mim primeiro, de novo! Foram duas. Duas é oficialmente um padrão e eu estou encantado.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Right — still the right person. I'd chosen well and I'm insufferable about it.
    >>  ............................................
    pt  Certo — ainda a pessoa certa. Eu escolhi bem e sou insuportável sobre isso.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret. You always were the right person, and time has borne it out.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa, e o tempo confirmou.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. Twice, months apart. That's what makes it something I can rely on.
    >>  ............................................
    pt  De mim primeiro, de novo. Duas vezes, com meses de diferença. É isso que faz eu poder contar.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. These things prove themselves slowly and this one has.
    >>  ............................................
    pt  Ainda a pessoa certa. Essas coisas se provam devagar e esta se provou.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, %1$s. Not the secret. You always were the right person, and I'd been checking.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Você sempre foi a pessoa certa, e eu vinha conferindo.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again. I'd told myself the first time was luck. Twice isn't luck.
    >>  ............................................
    pt  De mim primeiro, de novo. Eu dizia que a primeira foi sorte. Duas não é sorte.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person. That has made the last few months a great deal lighter.
    >>  ............................................
    pt  Ainda a pessoa certa. Isso tornou os últimos meses muito mais leves.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  ...You asked after me, not the secret. You always were the right person.
    >>  ............................................
    pt  ...Você perguntou de mim, não do segredo. Você sempre foi a pessoa certa.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again.
    >>  ............................................
    pt  De mim primeiro, de novo.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Still the right person.
    >>  ............................................
    pt  Ainda a pessoa certa.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret! You always were the right person for this.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo! Você sempre foi a pessoa certa pra isso.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again! That's twice. Twice is officially a pattern and I'm delighted.
    >>  ............................................
    pt  De mim primeiro, de novo! Foram duas. Duas é oficialmente um padrão e eu estou encantado.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Right — still the right person. I'd chosen well and I'm insufferable about it.
    >>  ............................................
    pt  Certo — ainda a pessoa certa. Eu escolhi bem e sou insuportável sobre isso.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.check_in.entrusted/1
    en  You asked after me, not the secret! You always were the right person for this.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo! Você sempre foi a pessoa certa pra isso.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.check_in.entrusted/2
    en  Me first, again! That's twice. Twice is officially a pattern and I'm delighted.
    >>  ............................................
    pt  De mim primeiro, de novo! Foram duas. Duas é oficialmente um padrão e eu estou encantado.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.check_in.entrusted/3
    en  Right — still the right person. I'd chosen well and I'm insufferable about it.
    >>  ............................................
    pt  Certo — ainda a pessoa certa. Eu escolhi bem e sou insuportável sobre isso.
    >>  ............................................
```

</details>


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when milestone `secret.entrusted` is set  _(chance -2000)_
- Does: **hearts +2** — decision id `secret.resume.check_in`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `secret.resume.check_in`)_
- Does: arc `secret` — advance to stage 2
- Then opens: `conversations.arc.secret.resume.followup`
- …where the player's next choices will be: "It's safe. It stays safe." | "Is it heavy, still?" | "What happens if it does get out?" | "It's safe. I'll go."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.check_in
WHO    VILLAGER — what the player reads after pressing "How are you carrying it?"
       spoken on: conversations.arc.secret.resume.respond, button `check_in`
       leaves the player on: conversations.arc.secret.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.check_in.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.check_in/1   [56 chars]
    en  ...You asked after ME, not the secret. Nobody does that.
    >>  ............................................
    pt  ...Você perguntou de MIM, não do segredo. Ninguém faz isso.
    >>  ............................................
  dialogue.conversations.secret.resume.check_in/2   [68 chars]
    en  Lighter, since somebody else knows. That's the whole use of telling.
    >>  ............................................
    pt  Mais leve, desde que outra pessoa sabe. É essa a utilidade de contar.
    >>  ............................................
  dialogue.conversations.secret.resume.check_in/3   [56 chars]
    en  Some days it's heavy. Today, less so, because you asked.
    >>  ............................................
    pt  Alguns dias é pesado. Hoje, menos, porque você perguntou.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, %1$s. Not the secret. Nobody does that, and I'd counted on nobody doing it.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Ninguém faz isso, e eu contava com isso.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Give me a moment. I'd braced for the other question all week.
    >>  ............................................
    pt  De mim, não dele. Me dê um momento. Eu me preparei pra outra pergunta a semana toda.
    >>  ............................................
  anxious.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. That's undone something I'd been holding rather tightly.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Isso desfez algo que eu segurava com força.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.check_in/1
    en  You asked after me, not the secret. That's the order that keeps a thing safe for years.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. É a ordem que mantém algo seguro por anos.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. That's how I know it'll still be safe next winter.
    >>  ............................................
    pt  De mim, não dele. Certo. É assim que eu sei que vai continuar seguro no próximo inverno.
    >>  ............................................
  athletic.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. Slowly, that's how trust actually gets built.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Devagar, é assim que a confiança se constrói.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.check_in/1
    en  You asked after me, not the secret. Nobody does that.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Ninguém faz isso.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. That's the correct order and nobody uses it.
    >>  ............................................
    pt  De mim, não dele. Certo. É a ordem correta e ninguém usa.
    >>  ............................................
  confident.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. Noted.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Anotado.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.check_in/1
    en  You asked after me, not the secret. Nobody does that.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Ninguém faz isso.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. That's the correct order and nobody uses it.
    >>  ............................................
    pt  De mim, não dele. Certo. É a ordem correta e ninguém usa.
    >>  ............................................
  crabby.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. Noted.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Anotado.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, %1$s. Not the secret. Nobody does that.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Ninguém faz isso.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. That's the whole reason I told you in the first place, and here's the proof.
    >>  ............................................
    pt  De mim, não dele. É toda a razão de eu ter contado, e aqui está a prova.
    >>  ............................................
  extroverted.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. I'd hoped you would and I'd not have blamed you if you hadn't.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Eu esperava e não te culparia se não perguntasse.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, %1$s. Not the secret. Nobody does that.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Ninguém faz isso.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. That's the whole reason I told you in the first place, and here's the proof.
    >>  ............................................
    pt  De mim, não dele. É toda a razão de eu ter contado, e aqui está a prova.
    >>  ............................................
  flirty.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. I'd hoped you would and I'd not have blamed you if you hadn't.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Eu esperava e não te culparia se não perguntasse.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, %1$s. Not the secret. Nobody does that.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Ninguém faz isso.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. That's the whole reason I told you in the first place, and here's the proof.
    >>  ............................................
    pt  De mim, não dele. É toda a razão de eu ter contado, e aqui está a prova.
    >>  ............................................
  friendly.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. I'd hoped you would and I'd not have blamed you if you hadn't.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Eu esperava e não te culparia se não perguntasse.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, %1$s. Not the secret. Nobody does that, and I'd counted on nobody doing it.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Ninguém faz isso, e eu contava com isso.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Give me a moment. I'd braced for the other question all week.
    >>  ............................................
    pt  De mim, não dele. Me dê um momento. Eu me preparei pra outra pergunta a semana toda.
    >>  ............................................
  gloomy.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. That's undone something I'd been holding rather tightly.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Isso desfez algo que eu segurava com força.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.check_in/1
    en  You asked after me, not the secret. Nobody does that.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Ninguém faz isso.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. That's the correct order and nobody uses it.
    >>  ............................................
    pt  De mim, não dele. Certo. É a ordem correta e ninguém usa.
    >>  ............................................
  greedy.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. Noted.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Anotado.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.check_in/1
    en  You asked after me, not the secret. Nobody does that.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. Ninguém faz isso.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. That's the correct order and nobody uses it.
    >>  ............................................
    pt  De mim, não dele. Certo. É a ordem correta e ninguém usa.
    >>  ............................................
  grumpy.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. Noted.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Anotado.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, not the secret.
    >>  ............................................
    pt  ...Você perguntou de mim, não do segredo.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Nobody does that.
    >>  ............................................
    pt  De mim, não dele. Ninguém faz isso.
    >>  ............................................
  introverted.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first.
    >>  ............................................
    pt  Você perguntou de mim primeiro.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.check_in/1
    en  You asked after me, not the secret. That's the order that keeps a thing safe for years.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. É a ordem que mantém algo seguro por anos.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. That's how I know it'll still be safe next winter.
    >>  ............................................
    pt  De mim, não dele. Certo. É assim que eu sei que vai continuar seguro no próximo inverno.
    >>  ............................................
  lazy.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. Slowly, that's how trust actually gets built.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Devagar, é assim que a confiança se constrói.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, not the secret.
    >>  ............................................
    pt  ...Você perguntou de mim, não do segredo.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Nobody does that.
    >>  ............................................
    pt  De mim, não dele. Ninguém faz isso.
    >>  ............................................
  odd.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first.
    >>  ............................................
    pt  Você perguntou de mim primeiro.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.check_in/1
    en  You asked after me, not the secret. That's the order that keeps a thing safe for years.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. É a ordem que mantém algo seguro por anos.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. That's how I know it'll still be safe next winter.
    >>  ............................................
    pt  De mim, não dele. Certo. É assim que eu sei que vai continuar seguro no próximo inverno.
    >>  ............................................
  peaceful.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. Slowly, that's how trust actually gets built.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Devagar, é assim que a confiança se constrói.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.check_in/1
    en  You asked after ME, not the secret! Nobody does that. Nobody!
    >>  ............................................
    pt  Você perguntou de MIM, não do segredo! Ninguém faz isso. Ninguém!
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. Everyone else opens with 'so, has anyone...' — every time.
    >>  ............................................
    pt  De mim, não dele. Certo. Todo mundo abre com 'e aí, alguém...' — toda vez.
    >>  ............................................
  peppy.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. I'm going to think about that all afternoon.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Vou pensar nisso a tarde toda.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.check_in/1
    en  You asked after ME, not the secret! Nobody does that. Nobody!
    >>  ............................................
    pt  Você perguntou de MIM, não do segredo! Ninguém faz isso. Ninguém!
    >>  ............................................
  playful.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. Everyone else opens with 'so, has anyone...' — every time.
    >>  ............................................
    pt  De mim, não dele. Certo. Todo mundo abre com 'e aí, alguém...' — toda vez.
    >>  ............................................
  playful.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. I'm going to think about that all afternoon.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Vou pensar nisso a tarde toda.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.check_in/1
    en  You asked after me, not the secret. That's the order that keeps a thing safe for years.
    >>  ............................................
    pt  Você perguntou de mim, não do segredo. É a ordem que mantém algo seguro por anos.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. That's how I know it'll still be safe next winter.
    >>  ............................................
    pt  De mim, não dele. Certo. É assim que eu sei que vai continuar seguro no próximo inverno.
    >>  ............................................
  relaxed.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. Slowly, that's how trust actually gets built.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Devagar, é assim que a confiança se constrói.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, %1$s. Not the secret. Nobody does that, and I'd counted on nobody doing it.
    >>  ............................................
    pt  ...Você perguntou de mim, %1$s. Não do segredo. Ninguém faz isso, e eu contava com isso.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Give me a moment. I'd braced for the other question all week.
    >>  ............................................
    pt  De mim, não dele. Me dê um momento. Eu me preparei pra outra pergunta a semana toda.
    >>  ............................................
  sensitive.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. That's undone something I'd been holding rather tightly.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Isso desfez algo que eu segurava com força.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.check_in/1
    en  ...You asked after me, not the secret.
    >>  ............................................
    pt  ...Você perguntou de mim, não do segredo.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Nobody does that.
    >>  ............................................
    pt  De mim, não dele. Ninguém faz isso.
    >>  ............................................
  shy.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first.
    >>  ............................................
    pt  Você perguntou de mim primeiro.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.check_in/1
    en  You asked after ME, not the secret! Nobody does that. Nobody!
    >>  ............................................
    pt  Você perguntou de MIM, não do segredo! Ninguém faz isso. Ninguém!
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. Everyone else opens with 'so, has anyone...' — every time.
    >>  ............................................
    pt  De mim, não dele. Certo. Todo mundo abre com 'e aí, alguém...' — toda vez.
    >>  ............................................
  upbeat.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. I'm going to think about that all afternoon.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Vou pensar nisso a tarde toda.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.check_in/1
    en  You asked after ME, not the secret! Nobody does that. Nobody!
    >>  ............................................
    pt  Você perguntou de MIM, não do segredo! Ninguém faz isso. Ninguém!
    >>  ............................................
  witty.dialogue.conversations.secret.resume.check_in/2
    en  Me, not it. Right. Everyone else opens with 'so, has anyone...' — every time.
    >>  ............................................
    pt  De mim, não dele. Certo. Todo mundo abre com 'e aí, alguém...' — toda vez.
    >>  ............................................
  witty.dialogue.conversations.secret.resume.check_in/3
    en  You asked about me first. I'm going to think about that all afternoon.
    >>  ............................................
    pt  Você perguntou de mim primeiro. Vou pensar nisso a tarde toda.
    >>  ............................................
```

</details>


### Button `ask_who_knows` — "Does anyone else know?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `secret.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.resume.ask_who_knows` — accepted phrasings: "does anyone else know"; "who else knows"; "has anyone else been told"
  - the message must contain one of: `knows`, `anyone`
  - scored words: `else`(1.4), `knows`(1.5), `anyone`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.respond.ask_who_knows
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.respond.ask_who_knows   [22 chars]
    en  Does anyone else know?
    >>  ............................................
    pt  Mais alguém sabe?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `secret.resume.ask_who_knows`)_
- Then opens: `conversations.arc.secret.resume.followup`
- …where the player's next choices will be: "It's safe. It stays safe." | "Is it heavy, still?" | "What happens if it does get out?" | "It's safe. I'll go."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.ask_who_knows
WHO    VILLAGER — what the player reads after pressing "Does anyone else know?"
       spoken on: conversations.arc.secret.resume.respond, button `ask_who_knows`
       leaves the player on: conversations.arc.secret.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.ask_who_knows.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.resume.ask_who_knows/1   [79 chars]
    en  Nobody. You're the whole list, %1$s. That's why I check the door when I say it.
    >>  ............................................
    pt  Ninguém. Você é a lista inteira, %1$s. Por isso eu olho a porta quando falo disso.
    >>  ............................................
  dialogue.conversations.secret.resume.ask_who_knows/2   [79 chars]
    en  Not a soul. I've had chances — I've had drink and company and chances — and no.
    >>  ............................................
    pt  Nem uma alma. Tive chances — tive bebida, companhia e chances — e não.
    >>  ............................................
  dialogue.conversations.secret.resume.ask_who_knows/3   [121 chars]
    en  Just you. Which means if it ever gets out, I'll know exactly where to look. ...That's not a threat. It's just arithmetic.
    >>  ............................................
    pt  Só você. O que quer dizer que, se vazar, vou saber exatamente onde procurar. ...Não é uma ameaça. É só aritmética.
    >>  ............................................
```


### Button `hint_publicly` — "I've been thinking of letting it slip."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `secret.revisit.opens`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.resume.hint_publicly` — accepted phrasings: "i have been thinking of letting it slip"; "i might mention it to the others"; "maybe the others should know"
  - the message must contain one of: `slip`, `mention`
  - scored words: `slip`(1.5), `mention`(1.2), `others`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.respond.hint_publicly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.respond.hint_publicly   [38 chars]
    en  I've been thinking of letting it slip.
    >>  ............................................
    pt  Ando pensando em deixar escapar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -3** — decision id `secret.resume.hint_publicly`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust -8, tension +8  _(recorded under topic `secret.resume.hint_publicly`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.hint_publicly
WHO    VILLAGER — what the player reads after pressing "I've been thinking of letting it slip."
       spoken on: conversations.arc.secret.resume.respond, button `hint_publicly`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.hint_publicly.terminal`: the villager accepts. Subject `secret.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.resume.hint_publicly/1   [53 chars]
    en  ...Say that again and I'll know exactly what you are.
    >>  ............................................
    pt  ...Diga isso de novo e vou saber exatamente o que você é.
    >>  ............................................
  dialogue.conversations.secret.resume.hint_publicly/2   [43 chars]
    en  You're joking. Tell me you're joking, %1$s.
    >>  ............................................
    pt  Você está brincando. Diga que está brincando, %1$s.
    >>  ............................................
  dialogue.conversations.secret.resume.hint_publicly/3   [45 chars]
    en  That is the one thing. The ONE thing I asked.
    >>  ............................................
    pt  É a única coisa. A ÚNICA coisa que eu pedi.
    >>  ............................................
```


### Button `leave` — "It's safe. I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.revisit.opens` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.secret.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.secret.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.secret.resume.respond.leave   [19 chars]
    en  It's safe. I'll go.
    >>  ............................................
    pt  Está seguro. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.resume.leave
WHO    VILLAGER — what the player reads after pressing "It's safe. I'll go."
       spoken on: conversations.arc.secret.resume.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.resume.leave.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.resume.leave/1   [43 chars]
    en  So it is. Thank you for saying so out loud.
    >>  ............................................
    pt  É assim mesmo. Obrigado por dizer isso em voz alta.
    >>  ............................................
  dialogue.conversations.secret.resume.leave/2   [32 chars]
    en  Off you go, %1$s. And thank you.
    >>  ............................................
    pt  Pode ir, %1$s. E obrigado.
    >>  ............................................
  dialogue.conversations.secret.resume.leave/3   [33 chars]
    en  Right you are. It's safe. I know.
    >>  ............................................
    pt  Isso mesmo. Está seguro. Eu sei.
    >>  ............................................
```

---


## `conversations.scene.secret.deciding_whether_to_say.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `secret`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.secret.deciding_whether_to_say` — e.g. "There is one thing I have never said in this village, and I have been sitting on the edge of saying it to you for a month."


```text
POOL   dialogue key: dialogue.conversations.scene.secret.deciding_whether_to_say.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.secret.deciding_whether_to_say.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.secret.deciding_whether_to_say.respond   [26 chars]
    en  Something you've not said.
    >>  ............................................
    pt  Algo que você não disse.
    >>  ............................................
```


### Button `let_it_wait` — "Tell me when you're ready."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `secret.deciding_whether_to_say.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.secret.deciding_whether_to_say.let_it_wait` — accepted phrasings: "tell me when youre ready"; "tell me when you are ready"; "in your own time"
  - the message must contain one of: `ready`, `time`
  - scored words: `ready`(1.8), `time`(1.8), `tell`(0.8), `when`(0.8), `youre`(0.8), `own`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.secret.deciding_whether_to_say.respond.let_it_wait
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.secret.deciding_whether_to_say.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.secret.deciding_whether_to_say.respond.let_it_wait   [26 chars]
    en  Tell me when you're ready.
    >>  ............................................
    pt  Me conte quando estiver pronta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.secret.patient`, budget `deep`, replay policy `once`
- Does: disposition — trust +5, warmth +3  _(recorded under topic `secret.mine`)_
- Does: session `turn`
- Then opens: `conversations.scene.secret.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.secret.deciding_whether_to_say.steadied
WHO    VILLAGER — what the player reads after pressing "Tell me when you're ready."
       spoken on: conversations.scene.secret.deciding_whether_to_say.respond, button `let_it_wait`
       leaves the player on: conversations.scene.secret.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.deciding_whether_to_say.open.steadied`: the villager accepts. Subject `secret.mine`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:secret` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.secret.deciding_whether_to_say.steadied/1   [102 chars]
    en  That answer is the reason it will get said. Somebody who waits is somebody it is safe to be slow with.
    >>  ............................................
    pt  Essa resposta é o motivo de isso um dia ser dito. Quem espera é alguém com quem dá para ser lenta em segurança.
    >>  ............................................
  dialogue.conversations.scene.secret.deciding_whether_to_say.steadied/2   [116 chars]
    en  Thank you. Four people have asked me straight out over the years and none of them got it, and I doubt they know why.
    >>  ............................................
    pt  Obrigada. Quatro pessoas me perguntaram de frente ao longo dos anos e nenhuma recebeu, e duvido que saibam por quê.
    >>  ............................................
  dialogue.conversations.scene.secret.deciding_whether_to_say.steadied/3   [113 chars]
    en  Then it will be a Tuesday, probably, and it will take about a minute, and you will wonder what the month was for.
    >>  ............................................
    pt  Então vai ser numa terça, provavelmente, e vai levar um minuto, e você vai se perguntar para que serviu o mês.
    >>  ............................................
```


### Button `ask_what_it_costs` — "What would saying it cost you?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `secret.deciding_whether_to_say.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.secret.deciding_whether_to_say.ask_what_it_costs` — accepted phrasings: "what would saying it cost you"; "what would saying it cost you"; "what is the price of telling"
  - the message must contain one of: `cost`, `price`, `telling`
  - scored words: `cost`(1.8), `price`(1.8), `telling`(1.8), `saying`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.secret.deciding_whether_to_say.respond.ask_what_it_costs
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.secret.deciding_whether_to_say.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.secret.deciding_whether_to_say.respond.ask_what_it_costs   [30 chars]
    en  What would saying it cost you?
    >>  ............................................
    pt  O que dizer custaria a você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +2  _(recorded under topic `secret.mine`)_
- Does: session `turn`
- Then opens: `conversations.scene.secret.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.secret.deciding_whether_to_say.answered
WHO    VILLAGER — what the player reads after pressing "What would saying it cost you?"
       spoken on: conversations.scene.secret.deciding_whether_to_say.respond, button `ask_what_it_costs`
       leaves the player on: conversations.scene.secret.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.deciding_whether_to_say.open.answered`: the villager explains. Subject `secret.mine`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:secret` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.secret.deciding_whether_to_say.answered/1   [107 chars]
    en  Being looked at differently for about a year, by one person, and that person is the one I would be telling.
    >>  ............................................
    pt  Ser olhada de outro jeito por uns um ano, por uma pessoa, e essa pessoa é justamente a quem eu contaria.
    >>  ............................................
  dialogue.conversations.scene.secret.deciding_whether_to_say.answered/2   [131 chars]
    en  Nothing, if I am right about you. Everything, if I am wrong, and I have been wrong twice and both times about people I was sure of.
    >>  ............................................
    pt  Nada, se eu estiver certa sobre você. Tudo, se eu estiver errada, e eu já errei duas vezes e as duas sobre pessoas de quem eu tinha certeza.
    >>  ............................................
  dialogue.conversations.scene.secret.deciding_whether_to_say.answered/3   [111 chars]
    en  It stops being mine. That is the actual cost and it is not recoverable, and it is why the answer takes a month.
    >>  ............................................
    pt  Deixa de ser meu. É esse o custo real e não se recupera, e é por isso que a resposta leva um mês.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.deciding_whether_to_say.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.secret.deciding_whether_to_say.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.secret.deciding_whether_to_say.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.secret.deciding_whether_to_say.respond.leave   [11 chars]
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.secret.leaving
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.scene.secret.deciding_whether_to_say.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.scene.leaving`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.secret.followup / leave; conversations.scene.secret.holding_somebody_elses.respond / leave
```

```text
  dialogue.conversations.scene.secret.leaving/1   [28 chars]
    en  That is as far as that goes.
    >>  ............................................
    pt  É até aí que isso vai.
    >>  ............................................
  dialogue.conversations.scene.secret.leaving/2   [28 chars]
    en  Right. It stays where it is.
    >>  ............................................
    pt  Certo. Fica onde está.
    >>  ............................................
  dialogue.conversations.scene.secret.leaving/3   [28 chars]
    en  I have said what I meant to.
    >>  ............................................
    pt  Já disse o que pretendia.
    >>  ............................................
```

---


## `conversations.scene.secret.followup`

**Reached from 4 route(s):** `conversations.scene.secret.deciding_whether_to_say.respond` / `let_it_wait`; `conversations.scene.secret.deciding_whether_to_say.respond` / `ask_what_it_costs`; `conversations.scene.secret.holding_somebody_elses.respond` / `respect_it`; `conversations.scene.secret.holding_somebody_elses.respond` / `ask_if_it_weighs`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.secret.deciding_whether_to_say.answered` — e.g. "Being looked at differently for about a year, by one person, and that person is the one I would be telling."
- `conversations.scene.secret.deciding_whether_to_say.steadied` — e.g. "That answer is the reason it will get said. Somebody who waits is somebody it is safe to be slow with."
- `conversations.scene.secret.holding_somebody_elses.acknowledged` — e.g. "That is the correct answer and about half of the people I have said this to have tried the other one."
- `conversations.scene.secret.holding_somebody_elses.answered` — e.g. "Only when I am tired, and only in the two minutes before I fall asleep, and never during a day."


```text
POOL   dialogue key: dialogue.conversations.scene.secret.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.secret.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.secret.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `leave` — "We'll leave it there."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:secret.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.secret.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.secret.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.secret.followup.leave   [21 chars]
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
POOL   dialogue key: dialogue.conversations.scene.secret.leaving
WHO    VILLAGER — what the player reads after pressing "We'll leave it there."
       spoken on: conversations.scene.secret.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.scene.leaving`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.secret.deciding_whether_to_say.respond / leave; conversations.scene.secret.holding_somebody_elses.respond / leave
```

> Written out in full under **`conversations.scene.secret.deciding_whether_to_say.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.secret.holding_somebody_elses.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `secret`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.secret.holding_somebody_elses` — e.g. "I am holding something for somebody and I will not be handing it over, and I am telling you that so you stop wondering why I change the subject."


```text
POOL   dialogue key: dialogue.conversations.scene.secret.holding_somebody_elses.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.secret.holding_somebody_elses.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.secret.holding_somebody_elses.respond   [25 chars]
    en  Something you're keeping.
    >>  ............................................
    pt  Algo que você guarda.
    >>  ............................................
```


### Button `respect_it` — "Good. Keep it."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `secret.holding_somebody_elses.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.secret.holding_somebody_elses.respect_it` — accepted phrasings: "good keep it"; "keep holding it then"; "that is yours to keep"
  - the message must contain one of: `keep`, `holding`, `yours`
  - scored words: `keep`(1.8), `holding`(1.8), `yours`(1.8), `good`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.secret.holding_somebody_elses.respond.respect_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.secret.holding_somebody_elses.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.secret.holding_somebody_elses.respond.respect_it   [14 chars]
    en  Good. Keep it.
    >>  ............................................
    pt  Ótimo. Guarde.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.secret.respected`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +3  _(recorded under topic `secret.borrowed`)_
- Does: session `turn`
- Then opens: `conversations.scene.secret.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.secret.holding_somebody_elses.acknowledged
WHO    VILLAGER — what the player reads after pressing "Good. Keep it."
       spoken on: conversations.scene.secret.holding_somebody_elses.respond, button `respect_it`
       leaves the player on: conversations.scene.secret.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.holding_somebody_elses.open.acknowledged`: the villager accepts. Subject `secret.borrowed`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:secret` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.secret.holding_somebody_elses.acknowledged/1   [101 chars]
    en  That is the correct answer and about half of the people I have said this to have tried the other one.
    >>  ............................................
    pt  É a resposta certa, e umas metade das pessoas a quem eu disse isso tentou a outra.
    >>  ............................................
  dialogue.conversations.scene.secret.holding_somebody_elses.acknowledged/2   [102 chars]
    en  I intend to. It has been four years and it gets easier, in the way that carrying anything gets easier.
    >>  ............................................
    pt  Pretendo. Já são quatro anos e fica mais fácil, do jeito que carregar qualquer coisa fica mais fácil.
    >>  ............................................
  dialogue.conversations.scene.secret.holding_somebody_elses.acknowledged/3   [122 chars]
    en  Thank you. Now you also know that whatever you tell me goes in the same place, which is rather the point of mentioning it.
    >>  ............................................
    pt  Obrigada. Agora você também sabe que o que você me contar vai para o mesmo lugar, que é justamente o motivo de eu mencionar.
    >>  ............................................
```


### Button `ask_if_it_weighs` — "Does holding it weigh on you?"

*stance family `empathy` · tone `gentle` · outcome `engaged` · answers the beat(s) `secret.holding_somebody_elses.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.secret.holding_somebody_elses.ask_if_it_weighs` — accepted phrasings: "does holding it weigh on you"; "does holding it weigh on you"; "is that a burden to carry"
  - the message must contain one of: `weigh`, `burden`
  - scored words: `weigh`(1.8), `burden`(1.8), `does`(0.8), `carry`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.secret.holding_somebody_elses.respond.ask_if_it_weighs
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.secret.holding_somebody_elses.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.secret.holding_somebody_elses.respond.ask_if_it_weighs   [29 chars]
    en  Does holding it weigh on you?
    >>  ............................................
    pt  Guardar isso pesa em você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, familiarity +1  _(recorded under topic `secret.borrowed`)_
- Does: session `turn`
- Then opens: `conversations.scene.secret.followup`
- …where the player's next choices will be: "We'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.scene.secret.holding_somebody_elses.answered
WHO    VILLAGER — what the player reads after pressing "Does holding it weigh on you?"
       spoken on: conversations.scene.secret.holding_somebody_elses.respond, button `ask_if_it_weighs`
       leaves the player on: conversations.scene.secret.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.holding_somebody_elses.open.answered`: the villager explains. Subject `secret.borrowed`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:secret` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.secret.holding_somebody_elses.answered/1   [95 chars]
    en  Only when I am tired, and only in the two minutes before I fall asleep, and never during a day.
    >>  ............................................
    pt  Só quando eu estou cansada, e só nos dois minutos antes de dormir, e nunca durante o dia.
    >>  ............................................
  dialogue.conversations.scene.secret.holding_somebody_elses.answered/2   [125 chars]
    en  Less than the alternative would. I have watched somebody hand one over and I have watched what it did to the lane afterwards.
    >>  ............................................
    pt  Menos do que a alternativa pesaria. Já vi alguém entregar uma dessas e já vi o que aquilo fez com a viela depois.
    >>  ............................................
  dialogue.conversations.scene.secret.holding_somebody_elses.answered/3   [117 chars]
    en  It weighs something. So does a bucket. You pick it up because it needs carrying and you do not talk about the weight.
    >>  ............................................
    pt  Pesa alguma coisa. Um balde também. A gente pega porque precisa ser carregado e não fica falando do peso.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.holding_somebody_elses.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.secret.holding_somebody_elses.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.secret.holding_somebody_elses.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.secret.holding_somebody_elses.respond.leave   [11 chars]
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.secret.leaving
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.scene.secret.holding_somebody_elses.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.scene.leaving`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.secret.deciding_whether_to_say.respond / leave; conversations.scene.secret.followup / leave
```

> Written out in full under **`conversations.scene.secret.deciding_whether_to_say.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.secret.close`

**Reached from 6 route(s):** `conversations.arc.secret.resume.followup` / `reassure`; `conversations.arc.secret.resume.followup` / `ask_burden`; `conversations.arc.secret.resume.followup` / `plan`; `conversations.topic.secret.followup` / `promise`; `conversations.topic.secret.followup` / `promise`; `conversations.topic.secret.followup` / `no_promise`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.secret.followup.no_promise` — e.g. "...You won't promise. That's more honest than a promise, in truth."
- `conversations.secret.followup.promise` — e.g. "...Your word. Then I'll sleep tonight for the first time in a while."
- `conversations.secret.followup.promise.trusted` — e.g. "...I never doubted it. I asked because saying it out loud makes it a thing between us."
- `conversations.secret.resume.followup.ask_burden` — e.g. "Lighter than it was. Heavy things don't stop being heavy; they just get better handles."
- `conversations.secret.resume.followup.plan` — e.g. "If it gets out... I'd know it wasn't you, first. Then I'd survive it. I've worked that far."
- `conversations.secret.resume.followup.reassure` — e.g. "I know. I check anyway — not on you, on the world. It's a habit, not a doubt."


```text
POOL   dialogue key: dialogue.conversations.topic.secret.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.secret.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.secret.close   [22 chars]
    en  Anyway. It's said now.
    >>  ............................................
    pt  Enfim. Já está dito.
    >>  ............................................
```


### Button `thank` — "Thank you for telling me."

*stance family `candor` · tone `gentle` · answers the beat(s) `secret.followup.no_promise.to.secret`, `secret.followup.promise.to.secret`, `secret.followup.promise.trusted.to.secret`, `secret.resume.followup.ask_burden.to.secret`, `secret.resume.followup.plan.to.secret`, `secret.resume.followup.reassure.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.close.thank` — accepted phrasings: "thank you for telling me"; "thank you for the secret"; "i am grateful you told me"
  - the message must contain one of: `thank`, `telling`
  - scored words: `thank`(1.5), `telling`(1.2), `secret`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.close.thank   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.close.thank`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, trust +1  _(recorded under topic `secret.close.thank`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.close.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.secret.close, button `thank`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.close.thank.terminal`: the villager accepts. Subject `secret.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.close.thank/1   [69 chars]
    en  You don't have to thank me for it. ...But thank you for the thanking.
    >>  ............................................
    pt  Você não precisa me agradecer. ...Mas obrigado pelo agradecimento.
    >>  ............................................
  dialogue.conversations.secret.close.thank/2   [51 chars]
    en  It's not a gift, %1$s. Still — aye. You're welcome.
    >>  ............................................
    pt  Não é um presente, %1$s. Mesmo assim — é. De nada.
    >>  ............................................
  dialogue.conversations.secret.close.thank/3   [46 chars]
    en  Strange thing to be thanked for. I'll take it.
    >>  ............................................
    pt  Coisa estranha de se agradecer. Mas aceito.
    >>  ............................................
```


### Button `say_means` — "That took something to say."

*stance family `candor` · tone `gentle` · answers the beat(s) `secret.followup.no_promise.to.secret`, `secret.followup.promise.to.secret`, `secret.followup.promise.trusted.to.secret`, `secret.resume.followup.ask_burden.to.secret`, `secret.resume.followup.plan.to.secret`, `secret.resume.followup.reassure.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.close.say_means` — accepted phrasings: "that took something to say"; "that was brave of you"; "that took courage"
  - the message must contain one of: `took`, `brave`, `courage`
  - scored words: `took`(1.5), `brave`(1.2), `courage`(1.5), `secret`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.close.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.close.say_means   [27 chars]
    en  That took something to say.
    >>  ............................................
    pt  Falar isso exigiu coragem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.close.say_means`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `secret.close.say_means`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.close.say_means
WHO    VILLAGER — what the player reads after pressing "That took something to say."
       spoken on: conversations.topic.secret.close, button `say_means`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.close.say_means.terminal`: the villager accepts. Subject `secret.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.close.say_means/1   [45 chars]
    en  ...It did. More than I'd admit standing here.
    >>  ............................................
    pt  ...Exigiu. Mais do que eu admitiria aqui de pé.
    >>  ............................................
  dialogue.conversations.secret.close.say_means/2   [57 chars]
    en  Saying it out loud was the hard part. You made it easier.
    >>  ............................................
    pt  Falar em voz alta era a parte difícil. Você facilitou.
    >>  ............................................
  dialogue.conversations.secret.close.say_means/3   [35 chars]
    en  You noticed that. Most don't, %1$s.
    >>  ............................................
    pt  Você notou. A maioria não nota, %1$s.
    >>  ............................................
```


### Button `confide` — "Then you should have one of mine."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `secret.followup.no_promise.to.secret`, `secret.followup.promise.to.secret`, `secret.followup.promise.trusted.to.secret`, `secret.resume.followup.ask_burden.to.secret`, `secret.resume.followup.plan.to.secret`, `secret.resume.followup.reassure.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.close.confide` — accepted phrasings: "then you should have one of mine"; "you should have one of mine"; "take one of mine in return"
  - the message must contain one of: `mine`
  - scored words: `mine`(1.5), `should`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.close.confide
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.close.confide   [33 chars]
    en  Then you should have one of mine.
    >>  ............................................
    pt  Então você merece um meu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.close.confide`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, familiarity +4  _(recorded under topic `secret.close.confide`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.close.confide
WHO    VILLAGER — what the player reads after pressing "Then you should have one of mine."
       spoken on: conversations.topic.secret.close, button `confide`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.close.confide.terminal`: the villager discloses. Subject `secret.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.close.confide/1   [102 chars]
    en  ...You'd trade. Right. Now we've each got a rope round the other's ankle, and that's how it should be.
    >>  ............................................
    pt  ...Você trocaria. Certo. Agora cada um tem uma corda no tornozelo do outro, e é assim que deve ser.
    >>  ............................................
  dialogue.conversations.secret.close.confide/2   [106 chars]
    en  Secrets are traded, not given — I've said that to people for years and nobody ever took me up on it, %1$s.
    >>  ............................................
    pt  Segredo se troca, não se dá — eu digo isso há anos e ninguém nunca aceitou, %1$s.
    >>  ............................................
  dialogue.conversations.secret.close.confide/3   [59 chars]
    en  Go on, then. Quietly. ...There. Now neither of us can talk.
    >>  ............................................
    pt  Pode falar, então. Baixinho. ...Pronto. Agora nenhum de nós pode abrir a boca.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.close.confide/1
    en  ...You'd trade. Then we've each a rope round the other's ankle, and that's the only way I could bear it.
    >>  ............................................
    pt  ...Você trocaria. Então cada um tem uma corda no tornozelo do outro, e é o único jeito de eu suportar.
    >>  ............................................
  anxious.dialogue.conversations.secret.close.confide/2
    en  A trade. I'd been terrified since I said mine and now I'm not, and that was your doing.
    >>  ............................................
    pt  Uma troca. Eu estava aterrorizado desde que disse o meu e agora não estou, e foi obra sua.
    >>  ............................................
  anxious.dialogue.conversations.secret.close.confide/3
    en  Yours for mine, %1$s. I'll keep it. You know I'll keep it.
    >>  ............................................
    pt  O seu pelo meu, %1$s. Eu vou guardar. Você sabe que eu vou guardar.
    >>  ............................................
  athletic.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Then we've a rope round each other's ankle, and those hold for years.
    >>  ............................................
    pt  Você trocaria. Então temos uma corda no tornozelo um do outro, e essas duram anos.
    >>  ............................................
  athletic.dialogue.conversations.secret.close.confide/2
    en  A trade. It levels it, and level things last. That's most of what I know about people.
    >>  ............................................
    pt  Uma troca. Nivela, e coisas niveladas duram. É quase tudo que eu sei sobre pessoas.
    >>  ............................................
  athletic.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. Neither of us will speak, and in twenty years that'll still be true.
    >>  ............................................
    pt  O seu pelo meu. Nenhum de nós vai falar, e em vinte anos ainda vai ser verdade.
    >>  ............................................
  confident.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Right. Now we've each got a rope round the other's ankle, and that's how it should be.
    >>  ............................................
    pt  Você trocaria. Certo. Agora cada um tem uma corda no tornozelo do outro, e é como deve ser.
    >>  ............................................
  confident.dialogue.conversations.secret.close.confide/2
    en  A trade, then. That levels it, and level is the only way I can carry this.
    >>  ............................................
    pt  Uma troca, então. Isso nivela, e nivelado é o único jeito de eu carregar isso.
    >>  ............................................
  confident.dialogue.conversations.secret.close.confide/3
    en  Right. Yours for mine. Neither of us can speak now, which suits me.
    >>  ............................................
    pt  Certo. O seu pelo meu. Nenhum de nós pode falar agora, o que me serve.
    >>  ............................................
  crabby.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Right. Now we've each got a rope round the other's ankle, and that's how it should be.
    >>  ............................................
    pt  Você trocaria. Certo. Agora cada um tem uma corda no tornozelo do outro, e é como deve ser.
    >>  ............................................
  crabby.dialogue.conversations.secret.close.confide/2
    en  A trade, then. That levels it, and level is the only way I can carry this.
    >>  ............................................
    pt  Uma troca, então. Isso nivela, e nivelado é o único jeito de eu carregar isso.
    >>  ............................................
  crabby.dialogue.conversations.secret.close.confide/3
    en  Right. Yours for mine. Neither of us can speak now, which suits me.
    >>  ............................................
    pt  Certo. O seu pelo meu. Nenhum de nós pode falar agora, o que me serve.
    >>  ............................................
  extroverted.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Right. Now we've each a rope round the other's ankle, %1$s, and that's how it should be.
    >>  ............................................
    pt  Você trocaria. Certo. Agora cada um tem uma corda no tornozelo do outro, %1$s, e é como deve ser.
    >>  ............................................
  extroverted.dialogue.conversations.secret.close.confide/2
    en  A trade. You didn't have to and I'll not forget that you did.
    >>  ............................................
    pt  Uma troca. Você não precisava e eu não vou esquecer que fez.
    >>  ............................................
  extroverted.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. That's not a burden you owed me and it's the reason I can breathe.
    >>  ............................................
    pt  O seu pelo meu. Não era um peso que você me devia e é a razão de eu conseguir respirar.
    >>  ............................................
  flirty.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Right. Now we've each a rope round the other's ankle, %1$s, and that's how it should be.
    >>  ............................................
    pt  Você trocaria. Certo. Agora cada um tem uma corda no tornozelo do outro, %1$s, e é como deve ser.
    >>  ............................................
  flirty.dialogue.conversations.secret.close.confide/2
    en  A trade. You didn't have to and I'll not forget that you did.
    >>  ............................................
    pt  Uma troca. Você não precisava e eu não vou esquecer que fez.
    >>  ............................................
  flirty.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. That's not a burden you owed me and it's the reason I can breathe.
    >>  ............................................
    pt  O seu pelo meu. Não era um peso que você me devia e é a razão de eu conseguir respirar.
    >>  ............................................
  friendly.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Right. Now we've each a rope round the other's ankle, %1$s, and that's how it should be.
    >>  ............................................
    pt  Você trocaria. Certo. Agora cada um tem uma corda no tornozelo do outro, %1$s, e é como deve ser.
    >>  ............................................
  friendly.dialogue.conversations.secret.close.confide/2
    en  A trade. You didn't have to and I'll not forget that you did.
    >>  ............................................
    pt  Uma troca. Você não precisava e eu não vou esquecer que fez.
    >>  ............................................
  friendly.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. That's not a burden you owed me and it's the reason I can breathe.
    >>  ............................................
    pt  O seu pelo meu. Não era um peso que você me devia e é a razão de eu conseguir respirar.
    >>  ............................................
  gloomy.dialogue.conversations.secret.close.confide/1
    en  ...You'd trade. Then we've each a rope round the other's ankle, and that's the only way I could bear it.
    >>  ............................................
    pt  ...Você trocaria. Então cada um tem uma corda no tornozelo do outro, e é o único jeito de eu suportar.
    >>  ............................................
  gloomy.dialogue.conversations.secret.close.confide/2
    en  A trade. I'd been terrified since I said mine and now I'm not, and that was your doing.
    >>  ............................................
    pt  Uma troca. Eu estava aterrorizado desde que disse o meu e agora não estou, e foi obra sua.
    >>  ............................................
  gloomy.dialogue.conversations.secret.close.confide/3
    en  Yours for mine, %1$s. I'll keep it. You know I'll keep it.
    >>  ............................................
    pt  O seu pelo meu, %1$s. Eu vou guardar. Você sabe que eu vou guardar.
    >>  ............................................
  greedy.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Right. Now we've each got a rope round the other's ankle, and that's how it should be.
    >>  ............................................
    pt  Você trocaria. Certo. Agora cada um tem uma corda no tornozelo do outro, e é como deve ser.
    >>  ............................................
  greedy.dialogue.conversations.secret.close.confide/2
    en  A trade, then. That levels it, and level is the only way I can carry this.
    >>  ............................................
    pt  Uma troca, então. Isso nivela, e nivelado é o único jeito de eu carregar isso.
    >>  ............................................
  greedy.dialogue.conversations.secret.close.confide/3
    en  Right. Yours for mine. Neither of us can speak now, which suits me.
    >>  ............................................
    pt  Certo. O seu pelo meu. Nenhum de nós pode falar agora, o que me serve.
    >>  ............................................
  grumpy.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Right. Now we've each got a rope round the other's ankle, and that's how it should be.
    >>  ............................................
    pt  Você trocaria. Certo. Agora cada um tem uma corda no tornozelo do outro, e é como deve ser.
    >>  ............................................
  grumpy.dialogue.conversations.secret.close.confide/2
    en  A trade, then. That levels it, and level is the only way I can carry this.
    >>  ............................................
    pt  Uma troca, então. Isso nivela, e nivelado é o único jeito de eu carregar isso.
    >>  ............................................
  grumpy.dialogue.conversations.secret.close.confide/3
    en  Right. Yours for mine. Neither of us can speak now, which suits me.
    >>  ............................................
    pt  Certo. O seu pelo meu. Nenhum de nós pode falar agora, o que me serve.
    >>  ............................................
  introverted.dialogue.conversations.secret.close.confide/1
    en  ...You'd trade. Right. A rope round each other's ankle, then.
    >>  ............................................
    pt  ...Você trocaria. Certo. Uma corda no tornozelo de cada um, então.
    >>  ............................................
  introverted.dialogue.conversations.secret.close.confide/2
    en  A trade. That levels it.
    >>  ............................................
    pt  Uma troca. Isso nivela.
    >>  ............................................
  introverted.dialogue.conversations.secret.close.confide/3
    en  ...Yours for mine. Good.
    >>  ............................................
    pt  ...O seu pelo meu. Bom.
    >>  ............................................
  lazy.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Then we've a rope round each other's ankle, and those hold for years.
    >>  ............................................
    pt  Você trocaria. Então temos uma corda no tornozelo um do outro, e essas duram anos.
    >>  ............................................
  lazy.dialogue.conversations.secret.close.confide/2
    en  A trade. It levels it, and level things last. That's most of what I know about people.
    >>  ............................................
    pt  Uma troca. Nivela, e coisas niveladas duram. É quase tudo que eu sei sobre pessoas.
    >>  ............................................
  lazy.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. Neither of us will speak, and in twenty years that'll still be true.
    >>  ............................................
    pt  O seu pelo meu. Nenhum de nós vai falar, e em vinte anos ainda vai ser verdade.
    >>  ............................................
  odd.dialogue.conversations.secret.close.confide/1
    en  ...You'd trade. Right. A rope round each other's ankle, then.
    >>  ............................................
    pt  ...Você trocaria. Certo. Uma corda no tornozelo de cada um, então.
    >>  ............................................
  odd.dialogue.conversations.secret.close.confide/2
    en  A trade. That levels it.
    >>  ............................................
    pt  Uma troca. Isso nivela.
    >>  ............................................
  odd.dialogue.conversations.secret.close.confide/3
    en  ...Yours for mine. Good.
    >>  ............................................
    pt  ...O seu pelo meu. Bom.
    >>  ............................................
  peaceful.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Then we've a rope round each other's ankle, and those hold for years.
    >>  ............................................
    pt  Você trocaria. Então temos uma corda no tornozelo um do outro, e essas duram anos.
    >>  ............................................
  peaceful.dialogue.conversations.secret.close.confide/2
    en  A trade. It levels it, and level things last. That's most of what I know about people.
    >>  ............................................
    pt  Uma troca. Nivela, e coisas niveladas duram. É quase tudo que eu sei sobre pessoas.
    >>  ............................................
  peaceful.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. Neither of us will speak, and in twenty years that'll still be true.
    >>  ............................................
    pt  O seu pelo meu. Nenhum de nós vai falar, e em vinte anos ainda vai ser verdade.
    >>  ............................................
  peppy.dialogue.conversations.secret.close.confide/1
    en  You'd trade! Right. Now we've each got a rope round the other's ankle, and that's exactly right.
    >>  ............................................
    pt  Você trocaria! Certo. Agora cada um tem uma corda no tornozelo do outro, e é exatamente certo.
    >>  ............................................
  peppy.dialogue.conversations.secret.close.confide/2
    en  A trade! Excellent. Mutually assured silence. The finest kind of friendship.
    >>  ............................................
    pt  Uma troca! Excelente. Silêncio mutuamente garantido. O melhor tipo de amizade.
    >>  ............................................
  peppy.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. Now neither of us can say a word, and I find that enormously restful.
    >>  ............................................
    pt  O seu pelo meu. Agora nenhum de nós pode dizer uma palavra, e eu acho isso imensamente tranquilizador.
    >>  ............................................
  playful.dialogue.conversations.secret.close.confide/1
    en  You'd trade! Right. Now we've each got a rope round the other's ankle, and that's exactly right.
    >>  ............................................
    pt  Você trocaria! Certo. Agora cada um tem uma corda no tornozelo do outro, e é exatamente certo.
    >>  ............................................
  playful.dialogue.conversations.secret.close.confide/2
    en  A trade! Excellent. Mutually assured silence. The finest kind of friendship.
    >>  ............................................
    pt  Uma troca! Excelente. Silêncio mutuamente garantido. O melhor tipo de amizade.
    >>  ............................................
  playful.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. Now neither of us can say a word, and I find that enormously restful.
    >>  ............................................
    pt  O seu pelo meu. Agora nenhum de nós pode dizer uma palavra, e eu acho isso imensamente tranquilizador.
    >>  ............................................
  relaxed.dialogue.conversations.secret.close.confide/1
    en  You'd trade. Then we've a rope round each other's ankle, and those hold for years.
    >>  ............................................
    pt  Você trocaria. Então temos uma corda no tornozelo um do outro, e essas duram anos.
    >>  ............................................
  relaxed.dialogue.conversations.secret.close.confide/2
    en  A trade. It levels it, and level things last. That's most of what I know about people.
    >>  ............................................
    pt  Uma troca. Nivela, e coisas niveladas duram. É quase tudo que eu sei sobre pessoas.
    >>  ............................................
  relaxed.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. Neither of us will speak, and in twenty years that'll still be true.
    >>  ............................................
    pt  O seu pelo meu. Nenhum de nós vai falar, e em vinte anos ainda vai ser verdade.
    >>  ............................................
  sensitive.dialogue.conversations.secret.close.confide/1
    en  ...You'd trade. Then we've each a rope round the other's ankle, and that's the only way I could bear it.
    >>  ............................................
    pt  ...Você trocaria. Então cada um tem uma corda no tornozelo do outro, e é o único jeito de eu suportar.
    >>  ............................................
  sensitive.dialogue.conversations.secret.close.confide/2
    en  A trade. I'd been terrified since I said mine and now I'm not, and that was your doing.
    >>  ............................................
    pt  Uma troca. Eu estava aterrorizado desde que disse o meu e agora não estou, e foi obra sua.
    >>  ............................................
  sensitive.dialogue.conversations.secret.close.confide/3
    en  Yours for mine, %1$s. I'll keep it. You know I'll keep it.
    >>  ............................................
    pt  O seu pelo meu, %1$s. Eu vou guardar. Você sabe que eu vou guardar.
    >>  ............................................
  shy.dialogue.conversations.secret.close.confide/1
    en  ...You'd trade. Right. A rope round each other's ankle, then.
    >>  ............................................
    pt  ...Você trocaria. Certo. Uma corda no tornozelo de cada um, então.
    >>  ............................................
  shy.dialogue.conversations.secret.close.confide/2
    en  A trade. That levels it.
    >>  ............................................
    pt  Uma troca. Isso nivela.
    >>  ............................................
  shy.dialogue.conversations.secret.close.confide/3
    en  ...Yours for mine. Good.
    >>  ............................................
    pt  ...O seu pelo meu. Bom.
    >>  ............................................
  upbeat.dialogue.conversations.secret.close.confide/1
    en  You'd trade! Right. Now we've each got a rope round the other's ankle, and that's exactly right.
    >>  ............................................
    pt  Você trocaria! Certo. Agora cada um tem uma corda no tornozelo do outro, e é exatamente certo.
    >>  ............................................
  upbeat.dialogue.conversations.secret.close.confide/2
    en  A trade! Excellent. Mutually assured silence. The finest kind of friendship.
    >>  ............................................
    pt  Uma troca! Excelente. Silêncio mutuamente garantido. O melhor tipo de amizade.
    >>  ............................................
  upbeat.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. Now neither of us can say a word, and I find that enormously restful.
    >>  ............................................
    pt  O seu pelo meu. Agora nenhum de nós pode dizer uma palavra, e eu acho isso imensamente tranquilizador.
    >>  ............................................
  witty.dialogue.conversations.secret.close.confide/1
    en  You'd trade! Right. Now we've each got a rope round the other's ankle, and that's exactly right.
    >>  ............................................
    pt  Você trocaria! Certo. Agora cada um tem uma corda no tornozelo do outro, e é exatamente certo.
    >>  ............................................
  witty.dialogue.conversations.secret.close.confide/2
    en  A trade! Excellent. Mutually assured silence. The finest kind of friendship.
    >>  ............................................
    pt  Uma troca! Excelente. Silêncio mutuamente garantido. O melhor tipo de amizade.
    >>  ............................................
  witty.dialogue.conversations.secret.close.confide/3
    en  Yours for mine. Now neither of us can say a word, and I find that enormously restful.
    >>  ............................................
    pt  O seu pelo meu. Agora nenhum de nós pode dizer uma palavra, e eu acho isso imensamente tranquilizador.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.followup.no_promise.to.secret`, `secret.followup.promise.to.secret`, `secret.followup.promise.trusted.to.secret`, `secret.resume.followup.ask_burden.to.secret`, `secret.resume.followup.plan.to.secret`, `secret.resume.followup.reassure.to.secret` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.secret.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.close.leave   [16 chars]
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
POOL   dialogue key: dialogue.conversations.secret.close.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.secret.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.close.leave.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.close.leave/1   [26 chars]
    en  Aye. Go on, and thank you.
    >>  ............................................
    pt  Tá. Pode ir, e obrigado.
    >>  ............................................
  dialogue.conversations.secret.close.leave/2   [34 chars]
    en  Right. Enough of that for one day.
    >>  ............................................
    pt  Certo. Já chega disso por um dia.
    >>  ............................................
  dialogue.conversations.secret.close.leave/3   [27 chars]
    en  That'll do for today, %1$s.
    >>  ............................................
    pt  Por hoje está bom, %1$s.
    >>  ............................................
```

---


## `conversations.topic.secret.declined`

**Reached from 1 route(s):** `conversations.topic.secret.respond` / `decline`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.secret.respond.decline` — e.g. "...That's a kind thing to say to someone about to make a mistake. Right. It stays in, then."


```text
POOL   dialogue key: dialogue.conversations.topic.secret.declined
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.secret.declined
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.secret.declined   [34 chars]
    en  Right. It stays where it is, then.
    >>  ............................................
    pt  Certo. Então fica onde está.
    >>  ............................................
```


### Button `offer_later` — "Tell me when you're ready. I'll be about."

*stance family `restraint` · tone `gentle` · answers the beat(s) `secret.respond.decline.to.secret.declined`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.declined.offer_later` — accepted phrasings: "tell me when you are ready"; "whenever you are ready"; "another day then, when you want to"
  - the message must contain one of: `ready`, `later`, `whenever`
  - scored words: `ready`(1.5), `later`(1.2), `whenever`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.declined.offer_later
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.declined
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.declined.offer_later   [41 chars]
    en  Tell me when you're ready. I'll be about.
    >>  ............................................
    pt  Me diga quando estiver pronto. Eu estarei por perto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.declined.offer_later`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `secret.declined.offer_later`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.declined.offer_later
WHO    VILLAGER — what the player reads after pressing "Tell me when you're ready. I'll be about."
       spoken on: conversations.topic.secret.declined, button `offer_later`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.declined.offer_later.terminal`: the villager accepts. Subject `secret.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.declined.offer_later/1   [100 chars]
    en  ...When I'm ready. That's a door left open, not a hand pulling at it. I'll remember which you chose.
    >>  ............................................
    pt  ...Quando eu estiver pronto. Isso é uma porta deixada aberta, não uma mão puxando. Vou lembrar do que você escolheu.
    >>  ............................................
  dialogue.conversations.secret.declined.offer_later/2   [85 chars]
    en  You'll be about. Aye. That's worth more to me than the telling would have been, %1$s.
    >>  ............................................
    pt  Você estará por perto. Sim. Isso vale mais para mim do que o contar teria valido, %1$s.
    >>  ............................................
  dialogue.conversations.secret.declined.offer_later/3   [87 chars]
    en  Then I'll know where to find you when it gets too big to hold. That's a comfort, oddly.
    >>  ............................................
    pt  Então vou saber onde te achar quando ficar grande demais para segurar. É um consolo, estranhamente.
    >>  ............................................
```


### Button `change_subject` — "Come on — talk to me about something easier."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.respond.decline.to.secret.declined` · **this is the graceful way out of the node***

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.declined.change_subject` — accepted phrasings: "talk to me about something easier"; "let us talk about something else"; "something lighter instead"
  - the message must contain one of: `easier`, `lighter`, `else`
  - scored words: `easier`(1.5), `lighter`(1.2), `else`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.declined.change_subject
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.declined
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.declined.change_subject   [44 chars]
    en  Come on — talk to me about something easier.
    >>  ............................................
    pt  Vem cá — fala comigo de algo mais leve.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `secret.declined.change_subject`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.declined.change_subject
WHO    VILLAGER — what the player reads after pressing "Come on — talk to me about something easier."
       spoken on: conversations.topic.secret.declined, button `change_subject`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.declined.change_subject.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.declined.change_subject/1   [75 chars]
    en  Gladly. Ask me about the fence, the weather, anything with no weight to it.
    >>  ............................................
    pt  Com prazer. Me pergunte da cerca, do tempo, qualquer coisa sem peso.
    >>  ............................................
  dialogue.conversations.secret.declined.change_subject/2   [81 chars]
    en  Something easier. Aye, let's. My head's had enough of the hard thing for one day.
    >>  ............................................
    pt  Algo mais leve. Isso, vamos. Minha cabeça já teve coisa difícil demais por hoje.
    >>  ............................................
  dialogue.conversations.secret.declined.change_subject/3   [41 chars]
    en  Right you are. Easier things it is, %1$s.
    >>  ............................................
    pt  Isso mesmo. Coisas mais leves, então, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.respond.decline.to.secret.declined` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.secret.declined.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.declined
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.declined.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.declined.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.secret.declined, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.declined.leave.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.declined.leave/1   [41 chars]
    en  Off you go. It'll be here, unfortunately.
    >>  ............................................
    pt  Pode ir. Ela vai continuar aqui, infelizmente.
    >>  ............................................
  dialogue.conversations.secret.declined.leave/2   [29 chars]
    en  Quite. Mind how you go, %1$s.
    >>  ............................................
    pt  Exato. Se cuida, %1$s.
    >>  ............................................
  dialogue.conversations.secret.declined.leave/3   [58 chars]
    en  Go on. I'll get back to pretending there's nothing to say.
    >>  ............................................
    pt  Vá lá. Volto a fingir que não tem nada para dizer.
    >>  ............................................
```

---


## `conversations.topic.secret.followup`

**Reached from 2 route(s):** `conversations.topic.secret.respond` / `accept`; `conversations.topic.secret.respond` / `ask_why_me`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.secret.respond.accept` — e.g. "...Alright. The 'famous' pie I bring to every festival? The trader sells it to me. Every year. I've been thanked for it eleven times."
- `conversations.secret.respond.ask_why_me` — e.g. "Because you've never repeated a thing I've said. I've been counting. ...So: the festival pie. I buy it from the trader. I have never baked one in my life."


```text
POOL   dialogue key: dialogue.conversations.topic.secret.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.secret.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.secret.followup   [30 chars]
    en  So now you're carrying it too.
    >>  ............................................
    pt  Então agora você também está carregando.
    >>  ............................................
```


### Button `promise` — "It stays with me. You have my word."

*stance family `restraint` · tone `plain` · answers the beat(s) `secret.respond.accept.to.secret`, `secret.respond.ask_why_me.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.followup.promise` — accepted phrasings: "it stays with me, you have my word"; "i promise"; "you have my word"
  - the message must contain one of: `word`, `stays`, `promise`
  - scored words: `word`(1.5), `stays`(1.5), `promise`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.followup.promise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.followup.promise   [35 chars]
    en  It stays with me. You have my word.
    >>  ............................................
    pt  Fica comigo. Você tem minha palavra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when disposition trust >= 55
- Fires when: RULED OUT when the `dispositions` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `secret.followup.promise`, budget `deep`, replay policy `once`
- Does: disposition — trust +6, warmth +2  _(recorded under topic `secret.followup.promise`)_
- Does: exclusive `secret.promise` -> `kept` (locks the other side out for good)
- Then opens: `conversations.topic.secret.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Then you should have one of mine." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.secret.followup.promise.trusted
WHO    VILLAGER — what the player reads after pressing "It stays with me. You have my word."
       spoken on: conversations.topic.secret.followup, button `promise`
       leaves the player on: conversations.topic.secret.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.followup.promise.trusted.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.followup.promise.trusted/1   [86 chars]
    en  ...I never doubted it. I asked because saying it out loud makes it a thing between us.
    >>  ............................................
    pt  ...Eu nunca duvidei. Perguntei porque dizer em voz alta torna isso uma coisa entre nós.
    >>  ............................................
  dialogue.conversations.secret.followup.promise.trusted/2   [86 chars]
    en  Your word. I'd already have staked the whole thing on it, %1$s, but it's good to hear.
    >>  ............................................
    pt  Sua palavra. Eu já apostaria tudo nela, %1$s, mas é bom ouvir.
    >>  ............................................
  dialogue.conversations.secret.followup.promise.trusted/3   [89 chars]
    en  Then it's safe, and I knew it was before I opened my mouth. That's why I opened my mouth.
    >>  ............................................
    pt  Então está seguro, e eu já sabia antes de abrir a boca. Por isso abri.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when disposition trust >= 55  _(chance -2000)_
- Does: **hearts +2** — decision id `secret.followup.promise`, budget `deep`, replay policy `once`
- Does: disposition — trust +6, warmth +2  _(recorded under topic `secret.followup.promise`)_
- Does: exclusive `secret.promise` -> `kept` (locks the other side out for good)
- Then opens: `conversations.topic.secret.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Then you should have one of mine." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.secret.followup.promise
WHO    VILLAGER — what the player reads after pressing "It stays with me. You have my word."
       spoken on: conversations.topic.secret.followup, button `promise`
       leaves the player on: conversations.topic.secret.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.followup.promise.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.followup.promise/1   [68 chars]
    en  ...Your word. Then I'll sleep tonight for the first time in a while.
    >>  ............................................
    pt  ...Sua palavra. Então vou dormir hoje pela primeira vez em muito tempo.
    >>  ............................................
  dialogue.conversations.secret.followup.promise/2   [69 chars]
    en  I'm holding you to that, %1$s. It's the only thing I've asked of you.
    >>  ............................................
    pt  Vou cobrar isso, %1$s. É a única coisa que já te pedi.
    >>  ............................................
  dialogue.conversations.secret.followup.promise/3   [44 chars]
    en  Good. Then it's two of us and no more, ever.
    >>  ............................................
    pt  Bom. Então somos dois e mais ninguém, nunca.
    >>  ............................................
```


### Button `no_promise` — "I won't promise. But I'll try."

*stance family `candor` · tone `plain` · answers the beat(s) `secret.respond.accept.to.secret`, `secret.respond.ask_why_me.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.followup.no_promise` — accepted phrasings: "i will not promise, but i will try"; "i can only try"; "no promises, but i will try"
  - the message must contain one of: `wont`, `try`
  - scored words: `wont`(1.2), `try`(1.5), `promise`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.followup.no_promise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.followup.no_promise   [30 chars]
    en  I won't promise. But I'll try.
    >>  ............................................
    pt  Não vou prometer. Mas vou tentar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.followup.no_promise`, budget `deep`, replay policy `once`
- Does: disposition — respect +5  _(recorded under topic `secret.followup.no_promise`)_
- Does: exclusive `secret.promise` -> `declined` (locks the other side out for good)
- Then opens: `conversations.topic.secret.close`
- …where the player's next choices will be: "Thank you for telling me." | "That took something to say." | "Then you should have one of mine." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.secret.followup.no_promise
WHO    VILLAGER — what the player reads after pressing "I won't promise. But I'll try."
       spoken on: conversations.topic.secret.followup, button `no_promise`
       leaves the player on: conversations.topic.secret.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.followup.no_promise.to.secret`: the villager accepts. Subject `secret`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.followup.no_promise/1   [66 chars]
    en  ...You won't promise. That's more honest than a promise, in truth.
    >>  ............................................
    pt  ...Você não promete. Na verdade isso é mais honesto que uma promessa.
    >>  ............................................
  dialogue.conversations.secret.followup.no_promise/2   [62 chars]
    en  Trying is what people actually do. Promising is what they say.
    >>  ............................................
    pt  Tentar é o que as pessoas fazem. Prometer é o que elas dizem.
    >>  ............................................
  dialogue.conversations.secret.followup.no_promise/3   [63 chars]
    en  I'd rather have your 'I'll try' than someone else's oath, %1$s.
    >>  ............................................
    pt  Prefiro o seu 'vou tentar' ao juramento de outra pessoa, %1$s.
    >>  ............................................
```


### Button `trivialise` — "Is that all it was?"

*stance family `dismissal` · tone `blunt` · answers the beat(s) `secret.respond.accept.to.secret`, `secret.respond.ask_why_me.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.followup.trivialise` — accepted phrasings: "is that all it was"; "hardly worth the secrecy"; "that is nothing"
  - the message must contain one of: `all`, `hardly`, `nothing`
  - scored words: `all`(1.2), `that`(0.3), `hardly`(1.5), `nothing`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.followup.trivialise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.followup.trivialise   [19 chars]
    en  Is that all it was?
    >>  ............................................
    pt  Era só isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -3** — decision id `secret.followup.trivialise`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust -6, tension +7, warmth -3  _(recorded under topic `secret.followup.trivialise`)_
- Does: session `turn`
- Then opens: `conversations.topic.secret.slighted.close`
- …where the player's next choices will be: "That was a stupid thing to say. It wasn't nothing." | "It's still safe with me." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.secret.followup.trivialise
WHO    VILLAGER — what the player reads after pressing "Is that all it was?"
       spoken on: conversations.topic.secret.followup, button `trivialise`
       leaves the player on: conversations.topic.secret.slighted.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.slighted`: the villager hurts. Subject `secret.disclosure`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `secret:heard`, `player:trivialised_it` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.secret.followup.trivialise/1   [29 chars]
    en  ...That's all it was. To you.
    >>  ............................................
    pt  ...Era só isso. Para você.
    >>  ............................................
  dialogue.conversations.secret.followup.trivialise/2   [73 chars]
    en  I've carried that for eleven years, %1$s, and you weighed it in a breath.
    >>  ............................................
    pt  Carreguei isso por onze anos, %1$s, e você pesou num sopro.
    >>  ............................................
  dialogue.conversations.secret.followup.trivialise/3   [36 chars]
    en  Forget I said it. Truly — forget it.
    >>  ............................................
    pt  Esquece que eu falei. Sério — esquece.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was. To you. I'd been afraid of saying it for four years, %1$s.
    >>  ............................................
    pt  ...Era só isso. Pra você. Eu tive medo de dizer aquilo por quatro anos, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.secret.followup.trivialise/2
    en  Right. Yes. Small. I'd told myself it was small too, and I still couldn't say it.
    >>  ............................................
    pt  Certo. Sim. Pequeno. Eu também dizia a mim que era pequeno, e mesmo assim não conseguia falar.
    >>  ............................................
  anxious.dialogue.conversations.secret.followup.trivialise/3
    en  ...I shouldn't have. I knew I shouldn't have while I was doing it.
    >>  ............................................
    pt  ...Eu não devia ter contado. Eu sabia enquanto contava.
    >>  ............................................
  athletic.dialogue.conversations.secret.followup.trivialise/1
    en  That's all it was, to you. Things weigh different depending where you stand.
    >>  ............................................
    pt  Era só isso, pra você. As coisas pesam diferente dependendo de onde você está.
    >>  ............................................
  athletic.dialogue.conversations.secret.followup.trivialise/2
    en  ...Aye. Small from over there. It's been heavy over here a long while.
    >>  ............................................
    pt  ...É. Pequeno daí. Aqui está pesado faz tempo.
    >>  ............................................
  athletic.dialogue.conversations.secret.followup.trivialise/3
    en  Right. No matter. It's said now and it can't be unsaid.
    >>  ............................................
    pt  Certo. Tanto faz. Já foi dito e não desdiz.
    >>  ............................................
  confident.dialogue.conversations.secret.followup.trivialise/1
    en  That's all it was. To you.
    >>  ............................................
    pt  Era só isso. Pra você.
    >>  ............................................
  confident.dialogue.conversations.secret.followup.trivialise/2
    en  Right. Small to you. I'll remember the difference.
    >>  ............................................
    pt  Certo. Pequeno pra você. Vou lembrar da diferença.
    >>  ............................................
  confident.dialogue.conversations.secret.followup.trivialise/3
    en  ...I'll not hand you another one.
    >>  ............................................
    pt  ...Não te entrego outro.
    >>  ............................................
  crabby.dialogue.conversations.secret.followup.trivialise/1
    en  That's all it was. To you.
    >>  ............................................
    pt  Era só isso. Pra você.
    >>  ............................................
  crabby.dialogue.conversations.secret.followup.trivialise/2
    en  Right. Small to you. I'll remember the difference.
    >>  ............................................
    pt  Certo. Pequeno pra você. Vou lembrar da diferença.
    >>  ............................................
  crabby.dialogue.conversations.secret.followup.trivialise/3
    en  ...I'll not hand you another one.
    >>  ............................................
    pt  ...Não te entrego outro.
    >>  ............................................
  extroverted.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was, to you, %1$s. I'd carried it for four years.
    >>  ............................................
    pt  ...Era só isso, pra você, %1$s. Eu carreguei aquilo por quatro anos.
    >>  ............................................
  extroverted.dialogue.conversations.secret.followup.trivialise/2
    en  I gave you that because it was you. That's what makes this sting.
    >>  ............................................
    pt  Eu te dei aquilo porque era você. É isso que faz doer.
    >>  ............................................
  extroverted.dialogue.conversations.secret.followup.trivialise/3
    en  ...Right. I'll not go further than that with anyone again.
    >>  ............................................
    pt  ...Certo. Não vou além disso com ninguém de novo.
    >>  ............................................
  flirty.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was, to you, %1$s. I'd carried it for four years.
    >>  ............................................
    pt  ...Era só isso, pra você, %1$s. Eu carreguei aquilo por quatro anos.
    >>  ............................................
  flirty.dialogue.conversations.secret.followup.trivialise/2
    en  I gave you that because it was you. That's what makes this sting.
    >>  ............................................
    pt  Eu te dei aquilo porque era você. É isso que faz doer.
    >>  ............................................
  flirty.dialogue.conversations.secret.followup.trivialise/3
    en  ...Right. I'll not go further than that with anyone again.
    >>  ............................................
    pt  ...Certo. Não vou além disso com ninguém de novo.
    >>  ............................................
  friendly.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was, to you, %1$s. I'd carried it for four years.
    >>  ............................................
    pt  ...Era só isso, pra você, %1$s. Eu carreguei aquilo por quatro anos.
    >>  ............................................
  friendly.dialogue.conversations.secret.followup.trivialise/2
    en  I gave you that because it was you. That's what makes this sting.
    >>  ............................................
    pt  Eu te dei aquilo porque era você. É isso que faz doer.
    >>  ............................................
  friendly.dialogue.conversations.secret.followup.trivialise/3
    en  ...Right. I'll not go further than that with anyone again.
    >>  ............................................
    pt  ...Certo. Não vou além disso com ninguém de novo.
    >>  ............................................
  gloomy.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was. To you. I'd been afraid of saying it for four years, %1$s.
    >>  ............................................
    pt  ...Era só isso. Pra você. Eu tive medo de dizer aquilo por quatro anos, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.secret.followup.trivialise/2
    en  Right. Yes. Small. I'd told myself it was small too, and I still couldn't say it.
    >>  ............................................
    pt  Certo. Sim. Pequeno. Eu também dizia a mim que era pequeno, e mesmo assim não conseguia falar.
    >>  ............................................
  gloomy.dialogue.conversations.secret.followup.trivialise/3
    en  ...I shouldn't have. I knew I shouldn't have while I was doing it.
    >>  ............................................
    pt  ...Eu não devia ter contado. Eu sabia enquanto contava.
    >>  ............................................
  greedy.dialogue.conversations.secret.followup.trivialise/1
    en  That's all it was. To you.
    >>  ............................................
    pt  Era só isso. Pra você.
    >>  ............................................
  greedy.dialogue.conversations.secret.followup.trivialise/2
    en  Right. Small to you. I'll remember the difference.
    >>  ............................................
    pt  Certo. Pequeno pra você. Vou lembrar da diferença.
    >>  ............................................
  greedy.dialogue.conversations.secret.followup.trivialise/3
    en  ...I'll not hand you another one.
    >>  ............................................
    pt  ...Não te entrego outro.
    >>  ............................................
  grumpy.dialogue.conversations.secret.followup.trivialise/1
    en  That's all it was. To you.
    >>  ............................................
    pt  Era só isso. Pra você.
    >>  ............................................
  grumpy.dialogue.conversations.secret.followup.trivialise/2
    en  Right. Small to you. I'll remember the difference.
    >>  ............................................
    pt  Certo. Pequeno pra você. Vou lembrar da diferença.
    >>  ............................................
  grumpy.dialogue.conversations.secret.followup.trivialise/3
    en  ...I'll not hand you another one.
    >>  ............................................
    pt  ...Não te entrego outro.
    >>  ............................................
  introverted.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was. To you.
    >>  ............................................
    pt  ...Era só isso. Pra você.
    >>  ............................................
  introverted.dialogue.conversations.secret.followup.trivialise/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  introverted.dialogue.conversations.secret.followup.trivialise/3
    en  ...I'll say nothing else.
    >>  ............................................
    pt  ...Não digo mais nada.
    >>  ............................................
  lazy.dialogue.conversations.secret.followup.trivialise/1
    en  That's all it was, to you. Things weigh different depending where you stand.
    >>  ............................................
    pt  Era só isso, pra você. As coisas pesam diferente dependendo de onde você está.
    >>  ............................................
  lazy.dialogue.conversations.secret.followup.trivialise/2
    en  ...Aye. Small from over there. It's been heavy over here a long while.
    >>  ............................................
    pt  ...É. Pequeno daí. Aqui está pesado faz tempo.
    >>  ............................................
  lazy.dialogue.conversations.secret.followup.trivialise/3
    en  Right. No matter. It's said now and it can't be unsaid.
    >>  ............................................
    pt  Certo. Tanto faz. Já foi dito e não desdiz.
    >>  ............................................
  odd.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was. To you.
    >>  ............................................
    pt  ...Era só isso. Pra você.
    >>  ............................................
  odd.dialogue.conversations.secret.followup.trivialise/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  odd.dialogue.conversations.secret.followup.trivialise/3
    en  ...I'll say nothing else.
    >>  ............................................
    pt  ...Não digo mais nada.
    >>  ............................................
  peaceful.dialogue.conversations.secret.followup.trivialise/1
    en  That's all it was, to you. Things weigh different depending where you stand.
    >>  ............................................
    pt  Era só isso, pra você. As coisas pesam diferente dependendo de onde você está.
    >>  ............................................
  peaceful.dialogue.conversations.secret.followup.trivialise/2
    en  ...Aye. Small from over there. It's been heavy over here a long while.
    >>  ............................................
    pt  ...É. Pequeno daí. Aqui está pesado faz tempo.
    >>  ............................................
  peaceful.dialogue.conversations.secret.followup.trivialise/3
    en  Right. No matter. It's said now and it can't be unsaid.
    >>  ............................................
    pt  Certo. Tanto faz. Já foi dito e não desdiz.
    >>  ............................................
  peppy.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was! To you. To me it was the whole of last year.
    >>  ............................................
    pt  ...Era só isso! Pra você. Pra mim foi o ano passado inteiro.
    >>  ............................................
  peppy.dialogue.conversations.secret.followup.trivialise/2
    en  Right, well. Filed under nothing. Excellent.
    >>  ............................................
    pt  Certo, bom. Arquivado como nada. Excelente.
    >>  ............................................
  peppy.dialogue.conversations.secret.followup.trivialise/3
    en  ...Ha. Fine. I'll keep the big ones and give you the weather.
    >>  ............................................
    pt  ...Ha. Tudo bem. Eu guardo os grandes e te dou o tempo.
    >>  ............................................
  playful.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was! To you. To me it was the whole of last year.
    >>  ............................................
    pt  ...Era só isso! Pra você. Pra mim foi o ano passado inteiro.
    >>  ............................................
  playful.dialogue.conversations.secret.followup.trivialise/2
    en  Right, well. Filed under nothing. Excellent.
    >>  ............................................
    pt  Certo, bom. Arquivado como nada. Excelente.
    >>  ............................................
  playful.dialogue.conversations.secret.followup.trivialise/3
    en  ...Ha. Fine. I'll keep the big ones and give you the weather.
    >>  ............................................
    pt  ...Ha. Tudo bem. Eu guardo os grandes e te dou o tempo.
    >>  ............................................
  relaxed.dialogue.conversations.secret.followup.trivialise/1
    en  That's all it was, to you. Things weigh different depending where you stand.
    >>  ............................................
    pt  Era só isso, pra você. As coisas pesam diferente dependendo de onde você está.
    >>  ............................................
  relaxed.dialogue.conversations.secret.followup.trivialise/2
    en  ...Aye. Small from over there. It's been heavy over here a long while.
    >>  ............................................
    pt  ...É. Pequeno daí. Aqui está pesado faz tempo.
    >>  ............................................
  relaxed.dialogue.conversations.secret.followup.trivialise/3
    en  Right. No matter. It's said now and it can't be unsaid.
    >>  ............................................
    pt  Certo. Tanto faz. Já foi dito e não desdiz.
    >>  ............................................
  sensitive.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was. To you. I'd been afraid of saying it for four years, %1$s.
    >>  ............................................
    pt  ...Era só isso. Pra você. Eu tive medo de dizer aquilo por quatro anos, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.secret.followup.trivialise/2
    en  Right. Yes. Small. I'd told myself it was small too, and I still couldn't say it.
    >>  ............................................
    pt  Certo. Sim. Pequeno. Eu também dizia a mim que era pequeno, e mesmo assim não conseguia falar.
    >>  ............................................
  sensitive.dialogue.conversations.secret.followup.trivialise/3
    en  ...I shouldn't have. I knew I shouldn't have while I was doing it.
    >>  ............................................
    pt  ...Eu não devia ter contado. Eu sabia enquanto contava.
    >>  ............................................
  shy.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was. To you.
    >>  ............................................
    pt  ...Era só isso. Pra você.
    >>  ............................................
  shy.dialogue.conversations.secret.followup.trivialise/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  shy.dialogue.conversations.secret.followup.trivialise/3
    en  ...I'll say nothing else.
    >>  ............................................
    pt  ...Não digo mais nada.
    >>  ............................................
  upbeat.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was! To you. To me it was the whole of last year.
    >>  ............................................
    pt  ...Era só isso! Pra você. Pra mim foi o ano passado inteiro.
    >>  ............................................
  upbeat.dialogue.conversations.secret.followup.trivialise/2
    en  Right, well. Filed under nothing. Excellent.
    >>  ............................................
    pt  Certo, bom. Arquivado como nada. Excelente.
    >>  ............................................
  upbeat.dialogue.conversations.secret.followup.trivialise/3
    en  ...Ha. Fine. I'll keep the big ones and give you the weather.
    >>  ............................................
    pt  ...Ha. Tudo bem. Eu guardo os grandes e te dou o tempo.
    >>  ............................................
  witty.dialogue.conversations.secret.followup.trivialise/1
    en  ...That's all it was! To you. To me it was the whole of last year.
    >>  ............................................
    pt  ...Era só isso! Pra você. Pra mim foi o ano passado inteiro.
    >>  ............................................
  witty.dialogue.conversations.secret.followup.trivialise/2
    en  Right, well. Filed under nothing. Excellent.
    >>  ............................................
    pt  Certo, bom. Arquivado como nada. Excelente.
    >>  ............................................
  witty.dialogue.conversations.secret.followup.trivialise/3
    en  ...Ha. Fine. I'll keep the big ones and give you the weather.
    >>  ............................................
    pt  ...Ha. Tudo bem. Eu guardo os grandes e te dou o tempo.
    >>  ............................................
```

</details>


### Button `leave` — "Keep it, for now."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.respond.accept.to.secret`, `secret.respond.ask_why_me.to.secret` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.secret.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.followup.leave   [17 chars]
    en  Keep it, for now.
    >>  ............................................
    pt  Guarde, por enquanto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.respond.leave
WHO    VILLAGER — what the player reads after pressing "Keep it, for now."
       spoken on: conversations.topic.secret.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.respond.leave.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.secret.respond / leave
```

```text
  dialogue.conversations.secret.respond.leave/1   [36 chars]
    en  So I've found. Perhaps that's wiser.
    >>  ............................................
    pt  Foi o que eu vi. Talvez seja mais sábio.
    >>  ............................................
  dialogue.conversations.secret.respond.leave/2   [33 chars]
    en  Right. It's kept this long, %1$s.
    >>  ............................................
    pt  Certo. Já foi guardado por tanto tempo, %1$s.
    >>  ............................................
  dialogue.conversations.secret.respond.leave/3   [41 chars]
    en  Off you go. I'll not hold it against you.
    >>  ............................................
    pt  Pode ir. Não vou levar a mal.
    >>  ............................................
```

---


## `conversations.topic.secret.guarded.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `secret`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.deflect.secret` — e.g. "Secrets are traded, not given. Trust me with something of yours first."


```text
POOL   dialogue key: dialogue.conversations.topic.secret.guarded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.secret.guarded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.secret.guarded.respond   [35 chars]
    en  I've one. You've not earned it yet.
    >>  ............................................
    pt  Eu tenho um. Você ainda não mereceu.
    >>  ............................................
```


### Button `respect` — "Then keep it until I have."

*stance family `restraint` · tone `plain` · answers the beat(s) `deflect.secret.to.secret.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.guarded.respect` — accepted phrasings: "that is yours to keep"; "keep it to yourself"; "that secret is yours"
  - the message must contain one of: `yours`, `keep`
  - scored words: `yours`(1.5), `keep`(1.2), `secret`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.guarded.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.guarded.respond.respect   [26 chars]
    en  Then keep it until I have.
    >>  ............................................
    pt  Então guarde até eu merecer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.guarded.respect`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `secret.guarded.respect`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.guarded.respect
WHO    VILLAGER — what the player reads after pressing "Then keep it until I have."
       spoken on: conversations.topic.secret.guarded.respond, button `respect`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.guarded.respect.terminal`: the villager deflects. Subject `secret.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.guarded.respect/1   [61 chars]
    en  ...Thank you. People usually take a 'not yet' as a challenge.
    >>  ............................................
    pt  ...Obrigado. As pessoas geralmente tratam um 'ainda não' como desafio.
    >>  ............................................
  dialogue.conversations.secret.guarded.respect/2   [61 chars]
    en  It is. Ask me again when we've more winters between us, %1$s.
    >>  ............................................
    pt  É sim. Me pergunte de novo quando tivermos mais invernos juntos, %1$s.
    >>  ............................................
  dialogue.conversations.secret.guarded.respect/3   [46 chars]
    en  Good. That's how it stays worth telling later.
    >>  ............................................
    pt  Bom. É assim que continua valendo a pena contar depois.
    >>  ............................................
```


### Button `ask_safer` — "Tell me something that isn't buried, then."

*stance family `curiosity` · tone `gentle` · answers the beat(s) `deflect.secret.to.secret.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.guarded.ask_safer` — accepted phrasings: "tell me something lighter"; "something easier then"; "let us keep it light"
  - the message must contain one of: `lighter`, `easier`
  - scored words: `lighter`(1.5), `easier`(1.2), `secret`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.guarded.respond.ask_safer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.guarded.respond.ask_safer   [42 chars]
    en  Tell me something that isn't buried, then.
    >>  ............................................
    pt  Então me conta algo que não esteja enterrado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `secret.guarded.ask_safer`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.guarded.ask_safer
WHO    VILLAGER — what the player reads after pressing "Tell me something that isn't buried, then."
       spoken on: conversations.topic.secret.guarded.respond, button `ask_safer`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.guarded.ask_safer.terminal`: the villager deflects. Subject `secret.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.guarded.ask_safer/1   [57 chars]
    en  Now that I can do. Ask me anything that isn't underneath.
    >>  ............................................
    pt  Isso eu posso. Me pergunte qualquer coisa que não seja funda.
    >>  ............................................
  dialogue.conversations.secret.guarded.ask_safer/2   [52 chars]
    en  Something lighter. Aye — we'll build up to the rest.
    >>  ............................................
    pt  Algo mais leve. É — a gente chega no resto aos poucos.
    >>  ............................................
  dialogue.conversations.secret.guarded.ask_safer/3   [38 chars]
    en  Sensible, %1$s. The shallow end first.
    >>  ............................................
    pt  Sensato, %1$s. Primeiro a parte rasa.
    >>  ............................................
```


### Button `press` — "You can tell me. Really."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `deflect.secret.to.secret.guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.guarded.press` — accepted phrasings: "come on, you can tell me"; "tell me the secret"; "go on, tell me"
  - the message must contain one of: `come`, `tell`
  - scored words: `come`(1.2), `tell`(1.0), `secret`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.guarded.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.guarded.respond.press   [24 chars]
    en  You can tell me. Really.
    >>  ............................................
    pt  Você pode me contar. Sério.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `secret.guarded.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +5  _(recorded under topic `secret.guarded.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.guarded.press
WHO    VILLAGER — what the player reads after pressing "You can tell me. Really."
       spoken on: conversations.topic.secret.guarded.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.guarded.press.terminal`: the villager resists. Subject `secret.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.guarded.press/1   [51 chars]
    en  I could. I'm choosing not to. There's a difference.
    >>  ............................................
    pt  Eu poderia. Estou escolhendo não. Tem diferença.
    >>  ............................................
  dialogue.conversations.secret.guarded.press/2   [65 chars]
    en  Pushing at that door tells me more about you than it opens, %1$s.
    >>  ............................................
    pt  Empurrar essa porta me diz mais sobre você do que abre, %1$s.
    >>  ............................................
  dialogue.conversations.secret.guarded.press/3   [49 chars]
    en  No. Not for the asking, and not for asking twice.
    >>  ............................................
    pt  Não. Não por pedir, e nem por pedir duas vezes.
    >>  ............................................
```


### Button `leave` — "Then I'll earn it. Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `deflect.secret.to.secret.guarded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.secret.guarded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.guarded.respond.leave   [32 chars]
    en  Then I'll earn it. Another time.
    >>  ............................................
    pt  Então eu mereço. Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.guarded.leave
WHO    VILLAGER — what the player reads after pressing "Then I'll earn it. Another time."
       spoken on: conversations.topic.secret.guarded.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.guarded.leave.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.guarded.leave/1   [22 chars]
    en  Aye. No hard feelings.
    >>  ............................................
    pt  Tá. Sem ressentimento.
    >>  ............................................
  dialogue.conversations.secret.guarded.leave/2   [40 chars]
    en  Off you go. We'll get there or we won't.
    >>  ............................................
    pt  Pode ir. A gente chega lá ou não.
    >>  ............................................
  dialogue.conversations.secret.guarded.leave/3   [18 chars]
    en  Enough said, %1$s.
    >>  ............................................
    pt  Já foi dito, %1$s.
    >>  ............................................
```

---


## `conversations.topic.secret.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `secret`; `conversations.cat.personal` / `secret`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.secret.first` — e.g. "There's a thing I've never said out loud in this village. I can hand it to you now, or we can talk about the weather and both pretend I never offered."
- `conversations.secret.revisit` — e.g. "Still keeping what I told you? ...Good. Then maybe there's another where that came from, someday."


```text
POOL   dialogue key: dialogue.conversations.topic.secret.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.secret.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.secret.respond   [32 chars]
    en  I've something I've told nobody.
    >>  ............................................
    pt  Tenho algo que nunca contei a ninguém.
    >>  ............................................
```


### Button `accept` — "I'll hear it."

*stance family `restraint` · tone `plain` · answers the beat(s) `secret.first.to.secret`, `secret.revisit.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.respond.accept` — accepted phrasings: "i will hear it"; "i am listening"; "go on, i will hear it"
  - the message must contain one of: `hear`, `listening`
  - scored words: `hear`(1.5), `listening`(1.2), `tell`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.respond.accept
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.respond.accept   [13 chars]
    en  I'll hear it.
    >>  ............................................
    pt  Vou ouvir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `secret.respond.accept`, budget `deep`, replay policy `once`
- Does: disposition — trust +6, familiarity +3  _(recorded under topic `secret.respond.accept`)_
- Does: arc `secret` — advance to stage 1
- Does: milestone `secret.entrusted` set (fires once, ever)
- Then opens: `conversations.topic.secret.followup`
- …where the player's next choices will be: "It stays with me. You have my word." | "I won't promise. But I'll try." | "Is that all it was?" | "Keep it, for now."

```text
POOL   dialogue key: dialogue.conversations.secret.respond.accept
WHO    VILLAGER — what the player reads after pressing "I'll hear it."
       spoken on: conversations.topic.secret.respond, button `accept`
       leaves the player on: conversations.topic.secret.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.respond.accept.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.respond.accept/1   [133 chars]
    en  ...Alright. The 'famous' pie I bring to every festival? The trader sells it to me. Every year. I've been thanked for it eleven times.
    >>  ............................................
    pt  ...Tudo bem. Aquela torta 'famosa' que eu levo em toda festa? O mercador vende para mim. Todo ano. Já me agradeceram por ela onze vezes.
    >>  ............................................
  dialogue.conversations.secret.respond.accept/2   [137 chars]
    en  You said that without hesitating. That's why it's you. ...I have never once liked the mayor's speeches. I clap because the baker watches.
    >>  ............................................
    pt  Você disse isso sem hesitar. É por isso que é você. ...Eu nunca gostei de um discurso do prefeito. Aplaudo porque o padeiro observa.
    >>  ............................................
  dialogue.conversations.secret.respond.accept/3   [135 chars]
    en  Right. Once it's out it's out. ...There's a loose brick behind the well. My rainy-day emeralds live under it. Now two of us know where.
    >>  ............................................
    pt  Certo. Depois que sai, saiu. ...Tem um tijolo solto atrás do poço. Minhas esmeraldas de emergência moram embaixo dele. Agora somos dois que sabem onde.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.respond.accept/1
    en  ...Alright. The 'famous' pie I bring to every festival, %1$s? The trader sells them.
    >>  ............................................
    pt  ...Está bem. A torta 'famosa' que eu levo em toda festa, %1$s? O comerciante vende.
    >>  ............................................
  anxious.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. I've been carrying it about for years and it's very silly and it isn't.
    >>  ............................................
    pt  Certo. Aqui vai. Carrego isso há anos e é muito bobo e não é.
    >>  ............................................
  anxious.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Please don't laugh yet — give me a moment first.
    >>  ............................................
    pt  Tudo bem. É seu. Por favor não ria ainda — me dê um momento primeiro.
    >>  ............................................
  athletic.dialogue.conversations.secret.respond.accept/1
    en  Alright. The 'famous' pie I bring to every festival? The trader sells them. Has for years.
    >>  ............................................
    pt  Está bem. A torta 'famosa' que eu levo em toda festa? O comerciante vende. Há anos.
    >>  ............................................
  athletic.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. It's an old one and it'll keep being true whatever you do with it.
    >>  ............................................
    pt  Certo. Aqui vai. É antigo e vai continuar verdade faça você o que fizer.
    >>  ............................................
  athletic.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. It's been mine a long while; it can be ours now.
    >>  ............................................
    pt  Tudo bem. É seu. Foi meu por muito tempo; agora pode ser nosso.
    >>  ............................................
  confident.dialogue.conversations.secret.respond.accept/1
    en  Alright. The 'famous' pie I bring to every festival? The trader sells them.
    >>  ............................................
    pt  Está bem. A torta 'famosa' que eu levo em toda festa? O comerciante vende.
    >>  ............................................
  confident.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is, then, and I'll say it once.
    >>  ............................................
    pt  Certo. Então aqui vai, e eu digo uma vez.
    >>  ............................................
  confident.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Don't make me regret the arithmetic.
    >>  ............................................
    pt  Tudo bem. É seu. Não me faça lamentar a conta.
    >>  ............................................
  crabby.dialogue.conversations.secret.respond.accept/1
    en  Alright. The 'famous' pie I bring to every festival? The trader sells them.
    >>  ............................................
    pt  Está bem. A torta 'famosa' que eu levo em toda festa? O comerciante vende.
    >>  ............................................
  crabby.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is, then, and I'll say it once.
    >>  ............................................
    pt  Certo. Então aqui vai, e eu digo uma vez.
    >>  ............................................
  crabby.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Don't make me regret the arithmetic.
    >>  ............................................
    pt  Tudo bem. É seu. Não me faça lamentar a conta.
    >>  ............................................
  extroverted.dialogue.conversations.secret.respond.accept/1
    en  ...Alright, %1$s. The 'famous' pie I bring to every festival? The trader sells them.
    >>  ............................................
    pt  ...Está bem, %1$s. A torta 'famosa' que eu levo em toda festa? O comerciante vende.
    >>  ............................................
  extroverted.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. I'm telling you because it's you and for no other reason.
    >>  ............................................
    pt  Certo. Aqui vai. Eu conto porque é você e por nenhum outro motivo.
    >>  ............................................
  extroverted.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Sit down, there's a bit more to the story.
    >>  ............................................
    pt  Tudo bem. É seu. Sente-se, tem mais um pouco da história.
    >>  ............................................
  flirty.dialogue.conversations.secret.respond.accept/1
    en  ...Alright, %1$s. The 'famous' pie I bring to every festival? The trader sells them.
    >>  ............................................
    pt  ...Está bem, %1$s. A torta 'famosa' que eu levo em toda festa? O comerciante vende.
    >>  ............................................
  flirty.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. I'm telling you because it's you and for no other reason.
    >>  ............................................
    pt  Certo. Aqui vai. Eu conto porque é você e por nenhum outro motivo.
    >>  ............................................
  flirty.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Sit down, there's a bit more to the story.
    >>  ............................................
    pt  Tudo bem. É seu. Sente-se, tem mais um pouco da história.
    >>  ............................................
  friendly.dialogue.conversations.secret.respond.accept/1
    en  ...Alright, %1$s. The 'famous' pie I bring to every festival? The trader sells them.
    >>  ............................................
    pt  ...Está bem, %1$s. A torta 'famosa' que eu levo em toda festa? O comerciante vende.
    >>  ............................................
  friendly.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. I'm telling you because it's you and for no other reason.
    >>  ............................................
    pt  Certo. Aqui vai. Eu conto porque é você e por nenhum outro motivo.
    >>  ............................................
  friendly.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Sit down, there's a bit more to the story.
    >>  ............................................
    pt  Tudo bem. É seu. Sente-se, tem mais um pouco da história.
    >>  ............................................
  gloomy.dialogue.conversations.secret.respond.accept/1
    en  ...Alright. The 'famous' pie I bring to every festival, %1$s? The trader sells them.
    >>  ............................................
    pt  ...Está bem. A torta 'famosa' que eu levo em toda festa, %1$s? O comerciante vende.
    >>  ............................................
  gloomy.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. I've been carrying it about for years and it's very silly and it isn't.
    >>  ............................................
    pt  Certo. Aqui vai. Carrego isso há anos e é muito bobo e não é.
    >>  ............................................
  gloomy.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Please don't laugh yet — give me a moment first.
    >>  ............................................
    pt  Tudo bem. É seu. Por favor não ria ainda — me dê um momento primeiro.
    >>  ............................................
  greedy.dialogue.conversations.secret.respond.accept/1
    en  Alright. The 'famous' pie I bring to every festival? The trader sells them.
    >>  ............................................
    pt  Está bem. A torta 'famosa' que eu levo em toda festa? O comerciante vende.
    >>  ............................................
  greedy.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is, then, and I'll say it once.
    >>  ............................................
    pt  Certo. Então aqui vai, e eu digo uma vez.
    >>  ............................................
  greedy.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Don't make me regret the arithmetic.
    >>  ............................................
    pt  Tudo bem. É seu. Não me faça lamentar a conta.
    >>  ............................................
  grumpy.dialogue.conversations.secret.respond.accept/1
    en  Alright. The 'famous' pie I bring to every festival? The trader sells them.
    >>  ............................................
    pt  Está bem. A torta 'famosa' que eu levo em toda festa? O comerciante vende.
    >>  ............................................
  grumpy.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is, then, and I'll say it once.
    >>  ............................................
    pt  Certo. Então aqui vai, e eu digo uma vez.
    >>  ............................................
  grumpy.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Don't make me regret the arithmetic.
    >>  ............................................
    pt  Tudo bem. É seu. Não me faça lamentar a conta.
    >>  ............................................
  introverted.dialogue.conversations.secret.respond.accept/1
    en  ...Alright. The 'famous' pie? The trader sells them.
    >>  ............................................
    pt  ...Está bem. A torta 'famosa'? O comerciante vende.
    >>  ............................................
  introverted.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is.
    >>  ............................................
    pt  Certo. Aqui vai.
    >>  ............................................
  introverted.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it.
    >>  ............................................
    pt  Tudo bem. É seu.
    >>  ............................................
  lazy.dialogue.conversations.secret.respond.accept/1
    en  Alright. The 'famous' pie I bring to every festival? The trader sells them. Has for years.
    >>  ............................................
    pt  Está bem. A torta 'famosa' que eu levo em toda festa? O comerciante vende. Há anos.
    >>  ............................................
  lazy.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. It's an old one and it'll keep being true whatever you do with it.
    >>  ............................................
    pt  Certo. Aqui vai. É antigo e vai continuar verdade faça você o que fizer.
    >>  ............................................
  lazy.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. It's been mine a long while; it can be ours now.
    >>  ............................................
    pt  Tudo bem. É seu. Foi meu por muito tempo; agora pode ser nosso.
    >>  ............................................
  odd.dialogue.conversations.secret.respond.accept/1
    en  ...Alright. The 'famous' pie? The trader sells them.
    >>  ............................................
    pt  ...Está bem. A torta 'famosa'? O comerciante vende.
    >>  ............................................
  odd.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is.
    >>  ............................................
    pt  Certo. Aqui vai.
    >>  ............................................
  odd.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it.
    >>  ............................................
    pt  Tudo bem. É seu.
    >>  ............................................
  peaceful.dialogue.conversations.secret.respond.accept/1
    en  Alright. The 'famous' pie I bring to every festival? The trader sells them. Has for years.
    >>  ............................................
    pt  Está bem. A torta 'famosa' que eu levo em toda festa? O comerciante vende. Há anos.
    >>  ............................................
  peaceful.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. It's an old one and it'll keep being true whatever you do with it.
    >>  ............................................
    pt  Certo. Aqui vai. É antigo e vai continuar verdade faça você o que fizer.
    >>  ............................................
  peaceful.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. It's been mine a long while; it can be ours now.
    >>  ............................................
    pt  Tudo bem. É seu. Foi meu por muito tempo; agora pode ser nosso.
    >>  ............................................
  peppy.dialogue.conversations.secret.respond.accept/1
    en  Alright! The 'famous' pie I bring to every festival? The trader sells them. By the dozen.
    >>  ............................................
    pt  Está bem! A torta 'famosa' que eu levo em toda festa? O comerciante vende. Por dúzia.
    >>  ............................................
  peppy.dialogue.conversations.secret.respond.accept/2
    en  Right — here it is. Brace yourself. It's extremely mundane and extremely mortifying.
    >>  ............................................
    pt  Certo — aqui vai. Prepare-se. É extremamente banal e extremamente mortificante.
    >>  ............................................
  peppy.dialogue.conversations.secret.respond.accept/3
    en  Fine! You've got it. I feel lighter and considerably more exposed.
    >>  ............................................
    pt  Tudo bem! É seu. Me sinto mais leve e consideravelmente mais exposto.
    >>  ............................................
  playful.dialogue.conversations.secret.respond.accept/1
    en  Alright! The 'famous' pie I bring to every festival? The trader sells them. By the dozen.
    >>  ............................................
    pt  Está bem! A torta 'famosa' que eu levo em toda festa? O comerciante vende. Por dúzia.
    >>  ............................................
  playful.dialogue.conversations.secret.respond.accept/2
    en  Right — here it is. Brace yourself. It's extremely mundane and extremely mortifying.
    >>  ............................................
    pt  Certo — aqui vai. Prepare-se. É extremamente banal e extremamente mortificante.
    >>  ............................................
  playful.dialogue.conversations.secret.respond.accept/3
    en  Fine! You've got it. I feel lighter and considerably more exposed.
    >>  ............................................
    pt  Tudo bem! É seu. Me sinto mais leve e consideravelmente mais exposto.
    >>  ............................................
  relaxed.dialogue.conversations.secret.respond.accept/1
    en  Alright. The 'famous' pie I bring to every festival? The trader sells them. Has for years.
    >>  ............................................
    pt  Está bem. A torta 'famosa' que eu levo em toda festa? O comerciante vende. Há anos.
    >>  ............................................
  relaxed.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. It's an old one and it'll keep being true whatever you do with it.
    >>  ............................................
    pt  Certo. Aqui vai. É antigo e vai continuar verdade faça você o que fizer.
    >>  ............................................
  relaxed.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. It's been mine a long while; it can be ours now.
    >>  ............................................
    pt  Tudo bem. É seu. Foi meu por muito tempo; agora pode ser nosso.
    >>  ............................................
  sensitive.dialogue.conversations.secret.respond.accept/1
    en  ...Alright. The 'famous' pie I bring to every festival, %1$s? The trader sells them.
    >>  ............................................
    pt  ...Está bem. A torta 'famosa' que eu levo em toda festa, %1$s? O comerciante vende.
    >>  ............................................
  sensitive.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is. I've been carrying it about for years and it's very silly and it isn't.
    >>  ............................................
    pt  Certo. Aqui vai. Carrego isso há anos e é muito bobo e não é.
    >>  ............................................
  sensitive.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it. Please don't laugh yet — give me a moment first.
    >>  ............................................
    pt  Tudo bem. É seu. Por favor não ria ainda — me dê um momento primeiro.
    >>  ............................................
  shy.dialogue.conversations.secret.respond.accept/1
    en  ...Alright. The 'famous' pie? The trader sells them.
    >>  ............................................
    pt  ...Está bem. A torta 'famosa'? O comerciante vende.
    >>  ............................................
  shy.dialogue.conversations.secret.respond.accept/2
    en  Right. Here it is.
    >>  ............................................
    pt  Certo. Aqui vai.
    >>  ............................................
  shy.dialogue.conversations.secret.respond.accept/3
    en  Fine. You've got it.
    >>  ............................................
    pt  Tudo bem. É seu.
    >>  ............................................
  upbeat.dialogue.conversations.secret.respond.accept/1
    en  Alright! The 'famous' pie I bring to every festival? The trader sells them. By the dozen.
    >>  ............................................
    pt  Está bem! A torta 'famosa' que eu levo em toda festa? O comerciante vende. Por dúzia.
    >>  ............................................
  upbeat.dialogue.conversations.secret.respond.accept/2
    en  Right — here it is. Brace yourself. It's extremely mundane and extremely mortifying.
    >>  ............................................
    pt  Certo — aqui vai. Prepare-se. É extremamente banal e extremamente mortificante.
    >>  ............................................
  upbeat.dialogue.conversations.secret.respond.accept/3
    en  Fine! You've got it. I feel lighter and considerably more exposed.
    >>  ............................................
    pt  Tudo bem! É seu. Me sinto mais leve e consideravelmente mais exposto.
    >>  ............................................
  witty.dialogue.conversations.secret.respond.accept/1
    en  Alright! The 'famous' pie I bring to every festival? The trader sells them. By the dozen.
    >>  ............................................
    pt  Está bem! A torta 'famosa' que eu levo em toda festa? O comerciante vende. Por dúzia.
    >>  ............................................
  witty.dialogue.conversations.secret.respond.accept/2
    en  Right — here it is. Brace yourself. It's extremely mundane and extremely mortifying.
    >>  ............................................
    pt  Certo — aqui vai. Prepare-se. É extremamente banal e extremamente mortificante.
    >>  ............................................
  witty.dialogue.conversations.secret.respond.accept/3
    en  Fine! You've got it. I feel lighter and considerably more exposed.
    >>  ............................................
    pt  Tudo bem! É seu. Me sinto mais leve e consideravelmente mais exposto.
    >>  ............................................
```

</details>


### Button `ask_why_me` — "Why me?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `secret.first.to.secret`, `secret.revisit.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.respond.ask_why_me` — accepted phrasings: "why me"; "why choose me"; "why tell me"
  - the message must contain one of: `why`, `me`, `choose`
  - scored words: `why`(1.5), `me`(1.0), `choose`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.respond.ask_why_me
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.respond.ask_why_me   [7 chars]
    en  Why me?
    >>  ............................................
    pt  Por que eu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `secret.respond.ask_why_me`, budget `deep`, replay policy `once`
- Does: disposition — trust +4, familiarity +4  _(recorded under topic `secret.respond.ask_why_me`)_
- Does: arc `secret` — advance to stage 1
- Does: milestone `secret.entrusted` set (fires once, ever)
- Then opens: `conversations.topic.secret.followup`
- …where the player's next choices will be: "It stays with me. You have my word." | "I won't promise. But I'll try." | "Is that all it was?" | "Keep it, for now."

```text
POOL   dialogue key: dialogue.conversations.secret.respond.ask_why_me
WHO    VILLAGER — what the player reads after pressing "Why me?"
       spoken on: conversations.topic.secret.respond, button `ask_why_me`
       leaves the player on: conversations.topic.secret.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.respond.ask_why_me.to.secret`: the villager accepts. Subject `secret`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.respond.ask_why_me/1   [154 chars]
    en  Because you've never repeated a thing I've said. I've been counting. ...So: the festival pie. I buy it from the trader. I have never baked one in my life.
    >>  ............................................
    pt  Porque você nunca repetiu nada do que eu disse. Eu venho contando. ...Então: a torta da festa. Compro do mercador. Nunca assei uma na vida.
    >>  ............................................
  dialogue.conversations.secret.respond.ask_why_me/2   [162 chars]
    en  Because you asked why instead of leaning in. That's the answer, %1$s. ...And the answer to the other question is that I clap at the mayor to keep the baker happy.
    >>  ............................................
    pt  Porque você perguntou por quê em vez de se aproximar. Essa é a resposta, %1$s. ...E a resposta da outra pergunta é que eu aplaudo o prefeito para agradar o padeiro.
    >>  ............................................
  dialogue.conversations.secret.respond.ask_why_me/3   [143 chars]
    en  Because there's nobody else. Less flattering, but honest. ...Loose brick, behind the well. Everything I've saved is under it. That's the thing.
    >>  ............................................
    pt  Porque não tem mais ninguém. Menos lisonjeiro, mas honesto. ...Tijolo solto, atrás do poço. Tudo que eu juntei está embaixo. É essa a coisa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said, %1$s. I've been counting for a year.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse, %1$s. Venho contando há um ano.
    >>  ............................................
  anxious.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. I've been waiting to be sure of that.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Eu esperava ter certeza disso.
    >>  ............................................
  anxious.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. I needed that to be true before I could say any of this.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu precisava que isso fosse verdade antes de dizer qualquer coisa.
    >>  ............................................
  athletic.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said. A year of that is worth something.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse. Um ano disso vale algo.
    >>  ............................................
  athletic.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. That kind of habit shows over time.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Esse tipo de hábito aparece com o tempo.
    >>  ............................................
  athletic.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never passed anything on. I've had long enough to notice, which is the point.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu tive tempo de reparar, e é essa a questão.
    >>  ............................................
  confident.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said. I've been counting.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse. Eu venho contando.
    >>  ............................................
  confident.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. That's the whole reason.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. É toda a razão.
    >>  ............................................
  confident.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. That's rarer here than you'd think.
    >>  ............................................
    pt  Você nunca passou nada adiante. É mais raro aqui do que se imagina.
    >>  ............................................
  crabby.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said. I've been counting.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse. Eu venho contando.
    >>  ............................................
  crabby.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. That's the whole reason.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. É toda a razão.
    >>  ............................................
  crabby.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. That's rarer here than you'd think.
    >>  ............................................
    pt  Você nunca passou nada adiante. É mais raro aqui do que se imagina.
    >>  ............................................
  extroverted.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said, %1$s. I've been counting.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse, %1$s. Eu venho contando.
    >>  ............................................
  extroverted.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. I've watched, and I'd not have told you otherwise.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Eu observei, e senão eu não teria contado.
    >>  ............................................
  extroverted.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. That's why it's you and not anybody else.
    >>  ............................................
    pt  Você nunca passou nada adiante. É por isso que é você e não outra pessoa.
    >>  ............................................
  flirty.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said, %1$s. I've been counting.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse, %1$s. Eu venho contando.
    >>  ............................................
  flirty.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. I've watched, and I'd not have told you otherwise.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Eu observei, e senão eu não teria contado.
    >>  ............................................
  flirty.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. That's why it's you and not anybody else.
    >>  ............................................
    pt  Você nunca passou nada adiante. É por isso que é você e não outra pessoa.
    >>  ............................................
  friendly.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said, %1$s. I've been counting.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse, %1$s. Eu venho contando.
    >>  ............................................
  friendly.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. I've watched, and I'd not have told you otherwise.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Eu observei, e senão eu não teria contado.
    >>  ............................................
  friendly.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. That's why it's you and not anybody else.
    >>  ............................................
    pt  Você nunca passou nada adiante. É por isso que é você e não outra pessoa.
    >>  ............................................
  gloomy.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said, %1$s. I've been counting for a year.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse, %1$s. Venho contando há um ano.
    >>  ............................................
  gloomy.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. I've been waiting to be sure of that.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Eu esperava ter certeza disso.
    >>  ............................................
  gloomy.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. I needed that to be true before I could say any of this.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu precisava que isso fosse verdade antes de dizer qualquer coisa.
    >>  ............................................
  greedy.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said. I've been counting.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse. Eu venho contando.
    >>  ............................................
  greedy.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. That's the whole reason.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. É toda a razão.
    >>  ............................................
  greedy.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. That's rarer here than you'd think.
    >>  ............................................
    pt  Você nunca passou nada adiante. É mais raro aqui do que se imagina.
    >>  ............................................
  grumpy.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said. I've been counting.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse. Eu venho contando.
    >>  ............................................
  grumpy.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. That's the whole reason.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. É toda a razão.
    >>  ............................................
  grumpy.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. That's rarer here than you'd think.
    >>  ............................................
    pt  Você nunca passou nada adiante. É mais raro aqui do que se imagina.
    >>  ............................................
  introverted.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse.
    >>  ............................................
  introverted.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square.
    >>  ............................................
    pt  Porque você não leva coisas pra praça.
    >>  ............................................
  introverted.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never passed anything on. I've been counting.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu venho contando.
    >>  ............................................
  lazy.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said. A year of that is worth something.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse. Um ano disso vale algo.
    >>  ............................................
  lazy.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. That kind of habit shows over time.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Esse tipo de hábito aparece com o tempo.
    >>  ............................................
  lazy.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never passed anything on. I've had long enough to notice, which is the point.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu tive tempo de reparar, e é essa a questão.
    >>  ............................................
  odd.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse.
    >>  ............................................
  odd.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square.
    >>  ............................................
    pt  Porque você não leva coisas pra praça.
    >>  ............................................
  odd.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never passed anything on. I've been counting.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu venho contando.
    >>  ............................................
  peaceful.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said. A year of that is worth something.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse. Um ano disso vale algo.
    >>  ............................................
  peaceful.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. That kind of habit shows over time.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Esse tipo de hábito aparece com o tempo.
    >>  ............................................
  peaceful.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never passed anything on. I've had long enough to notice, which is the point.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu tive tempo de reparar, e é essa a questão.
    >>  ............................................
  peppy.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said! I've been counting. Obsessively.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse! Eu venho contando. Obsessivamente.
    >>  ............................................
  peppy.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. Do you know how few people manage that?
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Sabe quantas pessoas conseguem isso?
    >>  ............................................
  peppy.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. I keep a tally. It's a very short list.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu mantenho uma conta. É uma lista bem curta.
    >>  ............................................
  playful.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said! I've been counting. Obsessively.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse! Eu venho contando. Obsessivamente.
    >>  ............................................
  playful.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. Do you know how few people manage that?
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Sabe quantas pessoas conseguem isso?
    >>  ............................................
  playful.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. I keep a tally. It's a very short list.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu mantenho uma conta. É uma lista bem curta.
    >>  ............................................
  relaxed.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said. A year of that is worth something.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse. Um ano disso vale algo.
    >>  ............................................
  relaxed.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. That kind of habit shows over time.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Esse tipo de hábito aparece com o tempo.
    >>  ............................................
  relaxed.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never passed anything on. I've had long enough to notice, which is the point.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu tive tempo de reparar, e é essa a questão.
    >>  ............................................
  sensitive.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said, %1$s. I've been counting for a year.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse, %1$s. Venho contando há um ano.
    >>  ............................................
  sensitive.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. I've been waiting to be sure of that.
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Eu esperava ter certeza disso.
    >>  ............................................
  sensitive.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. I needed that to be true before I could say any of this.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu precisava que isso fosse verdade antes de dizer qualquer coisa.
    >>  ............................................
  shy.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse.
    >>  ............................................
  shy.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square.
    >>  ............................................
    pt  Porque você não leva coisas pra praça.
    >>  ............................................
  shy.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never passed anything on. I've been counting.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu venho contando.
    >>  ............................................
  upbeat.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said! I've been counting. Obsessively.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse! Eu venho contando. Obsessivamente.
    >>  ............................................
  upbeat.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. Do you know how few people manage that?
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Sabe quantas pessoas conseguem isso?
    >>  ............................................
  upbeat.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. I keep a tally. It's a very short list.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu mantenho uma conta. É uma lista bem curta.
    >>  ............................................
  witty.dialogue.conversations.secret.respond.ask_why_me/1
    en  Because you've never repeated a thing I've said! I've been counting. Obsessively.
    >>  ............................................
    pt  Porque você nunca repetiu nada que eu disse! Eu venho contando. Obsessivamente.
    >>  ............................................
  witty.dialogue.conversations.secret.respond.ask_why_me/2
    en  Because you don't carry things to the square. Do you know how few people manage that?
    >>  ............................................
    pt  Porque você não leva coisas pra praça. Sabe quantas pessoas conseguem isso?
    >>  ............................................
  witty.dialogue.conversations.secret.respond.ask_why_me/3
    en  You've never once passed anything on. I keep a tally. It's a very short list.
    >>  ............................................
    pt  Você nunca passou nada adiante. Eu mantenho uma conta. É uma lista bem curta.
    >>  ............................................
```

</details>


### Button `decline` — "Don't tell me something you'll regret."

*stance family `candor` · tone `gentle` · answers the beat(s) `secret.first.to.secret`, `secret.revisit.to.secret`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.respond.decline` — accepted phrasings: "do not tell me something you will regret"; "are you sure you want to tell me"; "you might regret telling me"
  - the message must contain one of: `regret`, `sure`
  - scored words: `regret`(1.5), `dont`(0.6), `sure`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.respond.decline
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.respond.decline   [38 chars]
    en  Don't tell me something you'll regret.
    >>  ............................................
    pt  Não me conte algo de que vai se arrepender.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4  _(recorded under topic `secret.respond.decline`)_
- Then opens: `conversations.topic.secret.declined`
- …where the player's next choices will be: "Tell me when you're ready. I'll be about." | "Come on — talk to me about something easier." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.secret.respond.decline
WHO    VILLAGER — what the player reads after pressing "Don't tell me something you'll regret."
       spoken on: conversations.topic.secret.respond, button `decline`
       leaves the player on: conversations.topic.secret.declined
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.respond.decline.to.secret.declined`: the villager accepts. Subject `secret.declined`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.respond.decline/1   [91 chars]
    en  ...That's a kind thing to say to someone about to make a mistake. Right. It stays in, then.
    >>  ............................................
    pt  ...Isso é uma gentileza para quem está prestes a cometer um erro. Certo. Então fica guardado.
    >>  ............................................
  dialogue.conversations.secret.respond.decline/2   [92 chars]
    en  You'd turn it down to protect me from myself. Noted, %1$s. ...It keeps. It's kept this long.
    >>  ............................................
    pt  Você recusaria para me proteger de mim mesmo. Anotado, %1$s. ...Ela guarda. Já guardou até aqui.
    >>  ............................................
  dialogue.conversations.secret.respond.decline/3   [72 chars]
    en  Mm. Perhaps you're right. It's waited years; it can wait a while longer.
    >>  ............................................
    pt  Hm. Talvez você tenha razão. Esperou anos; pode esperar mais um pouco.
    >>  ............................................
```


### Button `leave` — "Keep it, for now."

*stance family `exit` · tone `plain` · answers the beat(s) `secret.first.to.secret`, `secret.revisit.to.secret` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.secret.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.respond.leave   [17 chars]
    en  Keep it, for now.
    >>  ............................................
    pt  Guarde, por enquanto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.respond.leave
WHO    VILLAGER — what the player reads after pressing "Keep it, for now."
       spoken on: conversations.topic.secret.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.respond.leave.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.secret.followup / leave
```

> Written out in full under **`conversations.topic.secret.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.secret.slighted.close`

**Reached from 1 route(s):** `conversations.topic.secret.followup` / `trivialise`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.secret.followup.trivialise` — e.g. "...That's all it was. To you."


```text
POOL   dialogue key: dialogue.conversations.topic.secret.slighted.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.secret.slighted.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.secret.slighted.close   [26 chars]
    en  That's all it was. To you.
    >>  ............................................
    pt  Era só isso. Pra você.
    >>  ............................................
```


### Button `apologize` — "That was a stupid thing to say. It wasn't nothing."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `secret.slighted` · offered only once the villager has actually said `player:trivialised_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.hurt.apologize` — accepted phrasings: "that was a stupid thing to say. it wasn't nothing"
  - the message must contain one of: `stupid`, `nothing`, `dismissive`
  - scored words: `stupid`(1.5), `nothing`(1.2), `dismissive`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.slighted.close.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.slighted.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.slighted.close.apologize   [50 chars]
    en  That was a stupid thing to say. It wasn't nothing.
    >>  ............................................
    pt  Foi uma burrice dizer isso. Não era nada não.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4  _(recorded under topic `secret.slighted.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.slighted.apologize
WHO    VILLAGER — what the player reads after pressing "That was a stupid thing to say. It wasn't nothing."
       spoken on: conversations.topic.secret.slighted.close, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.slighted.apologize`: the villager qualifys. Subject `secret.disclosure`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.secret.slighted.apologize/1   [60 chars]
    en  ...No. It wasn't. Thank you for turning round and saying so.
    >>  ............................................
    pt  ...Não. Não era. Obrigado por voltar e admitir.
    >>  ............................................
  dialogue.conversations.secret.slighted.apologize/2   [83 chars]
    en  It took me nine years to say it and you nine words to file it, %1$s. But — alright.
    >>  ............................................
    pt  Levei nove anos pra dizer e você nove palavras pra arquivar, %1$s. Mas — está bem.
    >>  ............................................
  dialogue.conversations.secret.slighted.apologize/3   [62 chars]
    en  Then we'll leave it where it landed and I'll not take it back.
    >>  ............................................
    pt  Então deixamos onde caiu e eu não retiro.
    >>  ............................................
```


### Button `soften` — "It's still safe with me."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `secret.slighted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `secret.hurt.soften` — accepted phrasings: "it's still safe with me"
  - the message must contain one of: `safe`, `keep`
  - scored words: `safe`(1.5), `keep`(1.0), `still`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.secret.slighted.close.soften
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.slighted.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.slighted.close.soften   [24 chars]
    en  It's still safe with me.
    >>  ............................................
    pt  Continua seguro comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `secret.hurt.soften`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2, warmth +3  _(recorded under topic `secret.slighted.soften`)_
- Does: session `turn`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.slighted.soften
WHO    VILLAGER — what the player reads after pressing "It's still safe with me."
       spoken on: conversations.topic.secret.slighted.close, button `soften`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.slighted.soften`: the villager accepts. Subject `secret.disclosure`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.secret.slighted.soften/1   [55 chars]
    en  ...That's the part that mattered anyway. Keep it, then.
    >>  ............................................
    pt  ...É essa a parte que importava mesmo. Então guarde.
    >>  ............................................
  dialogue.conversations.secret.slighted.soften/2   [63 chars]
    en  Safe. Aye. That's more use than impressed, if I'm honest, %1$s.
    >>  ............................................
    pt  Seguro. É. Isso é mais útil que impressionado, se for honesto, %1$s.
    >>  ............................................
  dialogue.conversations.secret.slighted.soften/3   [62 chars]
    en  Then it wasn't wasted. Which is what I was actually afraid of.
    >>  ............................................
    pt  Então não foi desperdiçado. Que era o que eu realmente temia.
    >>  ............................................
```


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `secret.slighted` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.secret.slighted.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.secret.slighted.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.secret.slighted.close.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.secret.slighted.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.secret.slighted.close, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.slighted.leave`: the villager accepts. Subject `secret.disclosure`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.secret.slighted.leave/1   [16 chars]
    en  True enough. Do.
    >>  ............................................
    pt  Bem verdade. Pode ir.
    >>  ............................................
  dialogue.conversations.secret.slighted.leave/2   [18 chars]
    en  Away you go, %1$s.
    >>  ............................................
    pt  Pode seguir, %1$s.
    >>  ............................................
  dialogue.conversations.secret.slighted.leave/3   [22 chars]
    en  Take care of yourself.
    >>  ............................................
    pt  Se cuide.
    >>  ............................................
```

---

