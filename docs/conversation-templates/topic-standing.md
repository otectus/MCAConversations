# Topic: standing

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `standing` |
| Opened from | question `conversations.cat.village`, button `standing` |
| Depth class (its heart budget) | `standard` |
| Returns to | `conversations.cat.village` |
| Ages that can reach it | child, teen, adult |
| Stance families it must offer | `curiosity`, `practical_help`, `exit` |
| Narrative arc | `standing`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.village`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.village.standing
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.village.standing   [39 chars]
    en  What do people think of me around here?
    >>  ............................................
    pt  O que as pessoas acham de mim por aqui?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.standing.resume.followup`](#conversations-arc-standing-resume-followup)
- [`conversations.arc.standing.resume.respond`](#conversations-arc-standing-resume-respond)
- [`conversations.scene.standing.followup`](#conversations-scene-standing-followup)
- [`conversations.scene.standing.settled_opinion.respond`](#conversations-scene-standing-settled-opinion-respond)
- [`conversations.scene.standing.still_being_read.respond`](#conversations-scene-standing-still-being-read-respond)
- [`conversations.topic.standing.again.respond`](#conversations-topic-standing-again-respond)
- [`conversations.topic.standing.bad.followup`](#conversations-topic-standing-bad-followup)
- [`conversations.topic.standing.bad.respond`](#conversations-topic-standing-bad-respond)
- [`conversations.topic.standing.good.followup`](#conversations-topic-standing-good-followup)
- [`conversations.topic.standing.good.respond`](#conversations-topic-standing-good-respond)
- [`conversations.topic.standing.incident.followup`](#conversations-topic-standing-incident-followup)
- [`conversations.topic.standing.incident.respond`](#conversations-topic-standing-incident-respond)
- [`conversations.topic.standing.mixed.followup`](#conversations-topic-standing-mixed-followup)
- [`conversations.topic.standing.mixed.respond`](#conversations-topic-standing-mixed-respond)
- [`conversations.topic.standing.neutral.followup`](#conversations-topic-standing-neutral-followup)
- [`conversations.topic.standing.neutral.respond`](#conversations-topic-standing-neutral-respond)
- [`conversations.topic.standing.praise.followup`](#conversations-topic-standing-praise-followup)
- [`conversations.topic.standing.praise.respond`](#conversations-topic-standing-praise-respond)
- [`conversations.topic.standing.unknown.followup`](#conversations-topic-standing-unknown-followup)
- [`conversations.topic.standing.unknown.respond`](#conversations-topic-standing-unknown-respond)

---

## `conversations.arc.standing.resume.followup`

**Reached from 3 route(s):** `conversations.arc.standing.resume.respond` / `who_changed`; `conversations.arc.standing.resume.respond` / `keep_going`; `conversations.arc.standing.resume.respond` / `tired_of_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.resume.keep_going` — e.g. "Do. And don't perform it — performed decency is worse than none in a village."
- `conversations.standing.resume.tired_of_it` — e.g. "I'd be tired too. That doesn't stop them measuring, and it doesn't stop me telling you."
- `conversations.standing.resume.who_changed` — e.g. "The baker, and she'll deny it. That's how you know it's a real change."


```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.standing.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.standing.resume.followup   [29 chars]
    en  And that's where we leave it.
    >>  ............................................
    pt  E é aí que a gente para.
    >>  ............................................
```


### Button `thank_you_for_telling` — "Thank you for keeping me in it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `standing.resume.who_changed`, `standing.resume.keep_going`, `standing.resume.tired_of_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.resume.thank_you_for_telling` — accepted phrasings: "thank you for keeping me in it"; "thanks for keeping me informed"; "i am glad you told me how it went"
  - the message must contain one of: `keeping`
  - scored words: `halves`(0.3), `keeping`(1.2), `telling`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.followup.thank_you_for_telling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.standing.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.standing.resume.followup.thank_you_for_telling   [31 chars]
    en  Thank you for keeping me in it.
    >>  ............................................
    pt  Obrigado por me manter por dentro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.resume.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `standing.resume.thank_you_for_telling`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.resume.thank_you_for_telling
WHO    VILLAGER — what the player reads after pressing "Thank you for keeping me in it."
       spoken on: conversations.arc.standing.resume.followup, button `thank_you_for_telling`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.resume.thank_you_for_telling`: the villager accepts. Subject `standing.mixed`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.resume.thank_you_for_telling/1   [72 chars]
    en  You asked. People who ask get told; it isn't more complicated than that.
    >>  ............................................
    pt  Você perguntou. Quem pergunta é informado; não é mais complicado que isso.
    >>  ............................................
  dialogue.conversations.standing.resume.thank_you_for_telling/2   [76 chars]
    en  It costs me nothing and it seems to be worth something. I'll go on doing it.
    >>  ............................................
    pt  Não me custa nada e parece valer algo. Vou continuar fazendo.
    >>  ............................................
  dialogue.conversations.standing.resume.thank_you_for_telling/3   [74 chars]
    en  That's the second time you've thanked me for a thing I'd have done anyway.
    >>  ............................................
    pt  É a segunda vez que você me agradece por algo que eu faria de qualquer jeito.
    >>  ............................................
```


### Button `leave_it_with_you` — "I'll leave it with you."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `standing.resume.who_changed`, `standing.resume.keep_going`, `standing.resume.tired_of_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.resume.leave_it_with_you` — accepted phrasings: "i will leave it with you"; "that is yours to handle"; "i will let you carry it from here"
  - the message must contain one of: `yours`
  - scored words: `halves`(0.3), `leave`(0.6), `with`(0.3), `yours`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.followup.leave_it_with_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.standing.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.standing.resume.followup.leave_it_with_you   [23 chars]
    en  I'll leave it with you.
    >>  ............................................
    pt  Vou deixar isso com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.resume.leave_it_with_you
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.standing.resume.followup, button `leave_it_with_you`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.resume.leave_it_with_you`: the villager accepts. Subject `standing.mixed`, polarity `neutral`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.resume.leave_it_with_you/1   [49 chars]
    en  Do. I'll say if it changes, and I'll say plainly.
    >>  ............................................
    pt  Deixe. Eu aviso se mudar, e aviso sem rodeios.
    >>  ............................................
  dialogue.conversations.standing.resume.leave_it_with_you/2   [73 chars]
    en  Right. It's mine to carry and it's lighter for having been said out loud.
    >>  ............................................
    pt  Certo. É meu pra carregar e está mais leve por ter sido dito em voz alta.
    >>  ............................................
  dialogue.conversations.standing.resume.leave_it_with_you/3   [74 chars]
    en  Then it's mine again. That's how it should be, and thank you for the loan.
    >>  ............................................
    pt  Então volta a ser meu. É como deve ser, e obrigado pelo empréstimo.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.resume.who_changed`, `standing.resume.keep_going`, `standing.resume.tired_of_it` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.standing.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.standing.resume.followup.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.standing.resume.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.resume.leave`: the villager accepts. Subject `standing.mixed`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.standing.resume.respond / leave
```

```text
  dialogue.conversations.standing.resume.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.standing.resume.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.standing.resume.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.arc.standing.resume.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.resume` — e.g. "You said you'd keep at it. I've watched, and I'm telling you what I saw."


```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.standing.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.standing.resume.respond   [36 chars]
    en  That's the reckoning, such as it is.
    >>  ............................................
    pt  É esse o balanço, tal como está.
    >>  ............................................
```


### Button `who_changed` — "Who changed their mind?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.resume.who_changed` — accepted phrasings: "who changed their mind"; "who came round"; "which of them changed"
  - the message must contain one of: `changed`
  - scored words: `changed`(1.2), `mind`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.respond.who_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.standing.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.standing.resume.respond.who_changed   [23 chars]
    en  Who changed their mind?
    >>  ............................................
    pt  Quem mudou de ideia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.standing.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.standing.resume.who_changed
WHO    VILLAGER — what the player reads after pressing "Who changed their mind?"
       spoken on: conversations.arc.standing.resume.respond, button `who_changed`
       leaves the player on: conversations.arc.standing.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.resume.who_changed`: the villager reports. Subject `standing.mixed`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.resume.who_changed/1   [70 chars]
    en  The baker, and she'll deny it. That's how you know it's a real change.
    >>  ............................................
    pt  A padeira, e ela vai negar. É assim que se sabe que a mudança é real.
    >>  ............................................
  dialogue.conversations.standing.resume.who_changed/2   [81 chars]
    en  Two of the ones who were loudest. Loud opinions turn over faster than quiet ones.
    >>  ............................................
    pt  Dois dos que eram mais barulhentos. Opinião barulhenta vira mais rápido que a quieta.
    >>  ............................................
  dialogue.conversations.standing.resume.who_changed/3   [76 chars]
    en  I'll not name them. Naming them would make it a scoreboard and it isn't one.
    >>  ............................................
    pt  Não vou dizer nomes. Nomear viraria um placar e não é isso.
    >>  ............................................
```


### Button `keep_going` — "Then I'll keep at it."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `standing.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.resume.keep_going` — accepted phrasings: "then i will keep at it"; "i will keep going"; "i will carry on with it"
  - the message must contain one of: `continue`
  - scored words: `continue`(1.0), `keep`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.respond.keep_going
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.standing.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.standing.resume.respond.keep_going   [21 chars]
    en  Then I'll keep at it.
    >>  ............................................
    pt  Então eu continuo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.resume.keep`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `standing.resume.keep_going`)_
- Does: session `turn`
- Then opens: `conversations.arc.standing.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.standing.resume.keep_going
WHO    VILLAGER — what the player reads after pressing "Then I'll keep at it."
       spoken on: conversations.arc.standing.resume.respond, button `keep_going`
       leaves the player on: conversations.arc.standing.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.resume.keep_going`: the villager accepts. Subject `standing.mixed`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.resume.keep_going/1   [77 chars]
    en  Do. And don't perform it — performed decency is worse than none in a village.
    >>  ............................................
    pt  Continue. E não faça teatro — decência encenada é pior que nenhuma aqui.
    >>  ............................................
  dialogue.conversations.standing.resume.keep_going/2   [80 chars]
    en  Right. Another year and it'll be one answer, and nobody will remember it wasn't.
    >>  ............................................
    pt  Certo. Mais um ano e vira uma resposta só, e ninguém vai lembrar que não era.
    >>  ............................................
  dialogue.conversations.standing.resume.keep_going/3   [62 chars]
    en  Good. I'll keep watching, which is the only useful thing I do.
    >>  ............................................
    pt  Bom. Vou continuar observando, que é a única coisa útil que eu faço.
    >>  ............................................
```


### Button `tired_of_it` — "I'm tired of being measured."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `standing.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.resume.tired_of_it` — accepted phrasings: "i am tired of being measured"; "i am tired of being judged"; "i have had enough of being weighed up"
  - the message must contain one of: `measured`
  - scored words: `measured`(1.5), `tired`(0.7)

```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.respond.tired_of_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.standing.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.standing.resume.respond.tired_of_it   [28 chars]
    en  I'm tired of being measured.
    >>  ............................................
    pt  Estou cansado de ser medido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.standing.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.standing.resume.tired_of_it
WHO    VILLAGER — what the player reads after pressing "I'm tired of being measured."
       spoken on: conversations.arc.standing.resume.respond, button `tired_of_it`
       leaves the player on: conversations.arc.standing.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.resume.tired_of_it`: the villager qualifys. Subject `standing.mixed`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.resume.tired_of_it/1   [87 chars]
    en  I'd be tired too. That doesn't stop them measuring, and it doesn't stop me telling you.
    >>  ............................................
    pt  Eu também estaria cansado. Isso não os impede de medir, nem me impede de te contar.
    >>  ............................................
  dialogue.conversations.standing.resume.tired_of_it/2   [85 chars]
    en  Then stop asking me and it stops. That's a real option and I'd not think less of you.
    >>  ............................................
    pt  Então pare de me perguntar e para. É uma opção real e eu não pensaria menos de você.
    >>  ............................................
  dialogue.conversations.standing.resume.tired_of_it/3   [78 chars]
    en  Fair. I'd say give it one more season, and I'd say that whatever you answered.
    >>  ............................................
    pt  Justo. Eu diria pra dar mais uma estação, e diria isso qualquer que fosse sua resposta.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.standing.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.standing.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.standing.resume.respond.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.standing.resume.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.resume.leave`: the villager accepts. Subject `standing.mixed`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.standing.resume.followup / leave
```

> Written out in full under **`conversations.arc.standing.resume.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.standing.followup`

**Reached from 4 route(s):** `conversations.scene.standing.settled_opinion.respond` / `ask_about_the_household`; `conversations.scene.standing.settled_opinion.respond` / `thank_them_for_saying`; `conversations.scene.standing.still_being_read.respond` / `ask_how_to_earn_it`; `conversations.scene.standing.still_being_read.respond` / `take_it_well`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.standing.settled_opinion.acknowledged` — e.g. "Somebody should. A person can live here four years without ever being told where they actually stand."
- `conversations.scene.standing.settled_opinion.answered` — e.g. "The one at the top of the lane, and it is about a thing you did not do rather than a thing you did, which makes it harder to mend."
- `conversations.scene.standing.still_being_read.acknowledged` — e.g. "Then you will do well here eventually, because that is the whole disposition the place rewards."
- `conversations.scene.standing.still_being_read.explained` — e.g. "By turning up at the same hour for a season and doing an ordinary thing well while nobody watches."


```text
POOL   dialogue key: dialogue.conversations.scene.standing.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.standing.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.standing.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `leave` — "That's the reckoning, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:standing.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.standing.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.standing.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.standing.followup.leave   [27 chars]
    en  That's the reckoning, then.
    >>  ............................................
    pt  É a conta, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.leaving
WHO    VILLAGER — what the player reads after pressing "That's the reckoning, then."
       spoken on: conversations.scene.standing.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.scene.leaving`: the villager accepts. Subject `standing.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.standing.settled_opinion.respond / leave; conversations.scene.standing.still_being_read.respond / leave
```

```text
  dialogue.conversations.scene.standing.leaving/1   [24 chars]
    en  That is where you stand.
    >>  ............................................
    pt  É aí que você está.
    >>  ............................................
  dialogue.conversations.scene.standing.leaving/2   [32 chars]
    en  Right. Make of it what you like.
    >>  ............................................
    pt  Certo. Faça o que quiser com isso.
    >>  ............................................
  dialogue.conversations.scene.standing.leaving/3   [30 chars]
    en  It changes. It always changes.
    >>  ............................................
    pt  Muda. Sempre muda.
    >>  ............................................
```

---


## `conversations.scene.standing.settled_opinion.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.standing.settled_opinion` — e.g. "Settled, and settled in your favour, and I will tell you the one household where it is not, because you would want to know."


```text
POOL   dialogue key: dialogue.conversations.scene.standing.settled_opinion.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.standing.settled_opinion.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.standing.settled_opinion.respond   [18 chars]
    en  Where I stand now.
    >>  ............................................
    pt  Onde eu estou agora.
    >>  ............................................
```


### Button `ask_about_the_household` — "Which household, and why?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.settled_opinion.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.standing.settled_opinion.ask_about_the_household` — accepted phrasings: "which household and why"; "which household and why"; "who is the exception"
  - the message must contain one of: `household`, `exception`
  - scored words: `household`(1.8), `exception`(1.8), `which`(0.8), `why`(0.8), `who`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.standing.settled_opinion.respond.ask_about_the_household
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.standing.settled_opinion.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.standing.settled_opinion.respond.ask_about_the_household   [25 chars]
    en  Which household, and why?
    >>  ............................................
    pt  Qual casa, e por quê?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `standing.established`)_
- Does: session `turn`
- Then opens: `conversations.scene.standing.followup`
- …where the player's next choices will be: "That's the reckoning, then."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.settled_opinion.answered
WHO    VILLAGER — what the player reads after pressing "Which household, and why?"
       spoken on: conversations.scene.standing.settled_opinion.respond, button `ask_about_the_household`
       leaves the player on: conversations.scene.standing.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.settled_opinion.open.answered`: the villager explains. Subject `standing.established`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:standing` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.standing.settled_opinion.answered/1   [130 chars]
    en  The one at the top of the lane, and it is about a thing you did not do rather than a thing you did, which makes it harder to mend.
    >>  ............................................
    pt  A do fim da viela, e é sobre uma coisa que você não fez em vez de uma que fez, o que dificulta o conserto.
    >>  ............................................
  dialogue.conversations.scene.standing.settled_opinion.answered/2   [116 chars]
    en  I will tell you the household and not the reason, because the reason is theirs. Ask them and they will probably say.
    >>  ............................................
    pt  Vou te dizer a casa e não o motivo, porque o motivo é deles. Pergunte a eles e provavelmente vão dizer.
    >>  ............................................
  dialogue.conversations.scene.standing.settled_opinion.answered/3   [116 chars]
    en  It is four months old and it will keep. Do nothing dramatic about it. Dramatic is how a coolness becomes a position.
    >>  ............................................
    pt  Tem quatro meses e não estraga. Não faça nada dramático. Dramático é como uma frieza vira uma posição.
    >>  ............................................
```


### Button `thank_them_for_saying` — "Thank you for telling me plainly."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `standing.settled_opinion.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.standing.settled_opinion.thank_them_for_saying` — accepted phrasings: "thank you for telling me plainly"; "thank you for telling me plainly"; "i appreciate the plain answer"
  - the message must contain one of: `plainly`, `plain`
  - scored words: `plainly`(1.8), `plain`(1.8), `thank`(0.8), `telling`(0.8), `appreciate`(0.8), `answer`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.standing.settled_opinion.respond.thank_them_for_saying
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.standing.settled_opinion.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.standing.settled_opinion.respond.thank_them_for_saying   [33 chars]
    en  Thank you for telling me plainly.
    >>  ............................................
    pt  Obrigado por dizer com franqueza.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +2  _(recorded under topic `standing.established`)_
- Does: session `turn`
- Then opens: `conversations.scene.standing.followup`
- …where the player's next choices will be: "That's the reckoning, then."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.settled_opinion.acknowledged
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me plainly."
       spoken on: conversations.scene.standing.settled_opinion.respond, button `thank_them_for_saying`
       leaves the player on: conversations.scene.standing.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.settled_opinion.open.acknowledged`: the villager accepts. Subject `standing.established`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:standing` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.standing.settled_opinion.acknowledged/1   [101 chars]
    en  Somebody should. A person can live here four years without ever being told where they actually stand.
    >>  ............................................
    pt  Alguém deveria. Uma pessoa pode viver aqui quatro anos sem nunca ser informada de onde de fato está.
    >>  ............................................
  dialogue.conversations.scene.standing.settled_opinion.acknowledged/2   [116 chars]
    en  It is easier to say to somebody who takes it well, and you have taken it well, so I shall do it again in the spring.
    >>  ............................................
    pt  É mais fácil dizer a quem recebe bem, e você recebeu bem, então eu faço de novo na primavera.
    >>  ............................................
  dialogue.conversations.scene.standing.settled_opinion.acknowledged/3   [107 chars]
    en  Thank me by telling somebody else theirs. It costs one awkward minute and it is worth a season of guessing.
    >>  ............................................
    pt  Me agradeça contando a outra pessoa a dela. Custa um minuto constrangedor e vale uma estação de adivinhação.
    >>  ............................................
```


### Button `leave` — "Thanks for being straight."

*stance family `exit` · tone `plain` · answers the beat(s) `standing.settled_opinion.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.standing.settled_opinion.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.standing.settled_opinion.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.standing.settled_opinion.respond.leave   [26 chars]
    en  Thanks for being straight.
    >>  ............................................
    pt  Obrigado pela franqueza.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for being straight."
       spoken on: conversations.scene.standing.settled_opinion.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.scene.leaving`: the villager accepts. Subject `standing.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.standing.followup / leave; conversations.scene.standing.still_being_read.respond / leave
```

> Written out in full under **`conversations.scene.standing.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.standing.still_being_read.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.standing.still_being_read` — e.g. "Undecided, which is the honest answer and the one nobody wants. You have been here weeks, not seasons."


```text
POOL   dialogue key: dialogue.conversations.scene.standing.still_being_read.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.standing.still_being_read.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.standing.still_being_read.respond   [19 chars]
    en  How I'm taken here.
    >>  ............................................
    pt  Como me veem aqui.
    >>  ............................................
```


### Button `ask_how_to_earn_it` — "How does anyone earn it here?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.still_being_read.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.standing.still_being_read.ask_how_to_earn_it` — accepted phrasings: "how does anyone earn it here"; "how does anyone earn it here"; "what earns standing in this village"
  - the message must contain one of: `earn`, `standing`
  - scored words: `earn`(1.8), `standing`(1.8), `does`(0.8), `anyone`(0.8), `here`(0.8), `earns`(0.8), `village`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.standing.still_being_read.respond.ask_how_to_earn_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.standing.still_being_read.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.standing.still_being_read.respond.ask_how_to_earn_it   [29 chars]
    en  How does anyone earn it here?
    >>  ............................................
    pt  Como se conquista isso aqui?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `standing.early`)_
- Does: session `turn`
- Then opens: `conversations.scene.standing.followup`
- …where the player's next choices will be: "That's the reckoning, then."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.still_being_read.explained
WHO    VILLAGER — what the player reads after pressing "How does anyone earn it here?"
       spoken on: conversations.scene.standing.still_being_read.respond, button `ask_how_to_earn_it`
       leaves the player on: conversations.scene.standing.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.still_being_read.open.explained`: the villager explains. Subject `standing.early`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:standing` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.standing.still_being_read.explained/1   [98 chars]
    en  By turning up at the same hour for a season and doing an ordinary thing well while nobody watches.
    >>  ............................................
    pt  Aparecendo na mesma hora por uma estação e fazendo uma coisa comum bem feita enquanto ninguém olha.
    >>  ............................................
  dialogue.conversations.scene.standing.still_being_read.explained/2   [107 chars]
    en  Not by favours. A favour is a transaction. What counts is being reliably present when nothing is happening.
    >>  ............................................
    pt  Não com favores. Favor é transação. O que conta é estar confiavelmente presente quando nada está acontecendo.
    >>  ............................................
  dialogue.conversations.scene.standing.still_being_read.explained/3   [114 chars]
    en  One winter. That is the actual measure and nobody says it out loud. Anybody who is here in the spring is somebody.
    >>  ............................................
    pt  Um inverno. É essa a medida real e ninguém diz em voz alta. Quem está aqui na primavera é alguém.
    >>  ............................................
```


### Button `take_it_well` — "Fair. I'd rather hear it straight."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `standing.still_being_read.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.standing.still_being_read.take_it_well` — accepted phrasings: "fair id rather hear it straight"; "i would rather hear it straight"; "straight is better than polite"
  - the message must contain one of: `straight`
  - scored words: `straight`(1.8), `fair`(0.8), `rather`(0.8), `hear`(0.8), `better`(0.8), `than`(0.8), `polite`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.standing.still_being_read.respond.take_it_well
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.standing.still_being_read.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.standing.still_being_read.respond.take_it_well   [34 chars]
    en  Fair. I'd rather hear it straight.
    >>  ............................................
    pt  Justo. Prefiro ouvir a verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +1  _(recorded under topic `standing.early`)_
- Does: session `turn`
- Then opens: `conversations.scene.standing.followup`
- …where the player's next choices will be: "That's the reckoning, then."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.still_being_read.acknowledged
WHO    VILLAGER — what the player reads after pressing "Fair. I'd rather hear it straight."
       spoken on: conversations.scene.standing.still_being_read.respond, button `take_it_well`
       leaves the player on: conversations.scene.standing.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.still_being_read.open.acknowledged`: the villager accepts. Subject `standing.early`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:standing` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.standing.still_being_read.acknowledged/1   [95 chars]
    en  Then you will do well here eventually, because that is the whole disposition the place rewards.
    >>  ............................................
    pt  Então você vai se dar bem aqui com o tempo, porque é essa a disposição inteira que o lugar recompensa.
    >>  ............................................
  dialogue.conversations.scene.standing.still_being_read.acknowledged/2   [98 chars]
    en  Good. I have given that answer four times and two of them went badly, so I appreciate an easy one.
    >>  ............................................
    pt  Ótimo. Já dei essa resposta quatro vezes e duas correram mal, então eu agradeço uma fácil.
    >>  ............................................
  dialogue.conversations.scene.standing.still_being_read.acknowledged/3   [110 chars]
    en  Ask me again after the winter and the answer will be different, and I will tell you that one straight as well.
    >>  ............................................
    pt  Me pergunte depois do inverno e a resposta vai ser outra, e eu vou te dar essa com franqueza também.
    >>  ............................................
```


### Button `leave` — "Thanks for being straight."

*stance family `exit` · tone `plain` · answers the beat(s) `standing.still_being_read.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.standing.still_being_read.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.standing.still_being_read.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.standing.still_being_read.respond.leave   [26 chars]
    en  Thanks for being straight.
    >>  ............................................
    pt  Obrigado pela franqueza.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.leaving
WHO    VILLAGER — what the player reads after pressing "Thanks for being straight."
       spoken on: conversations.scene.standing.still_being_read.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.scene.leaving`: the villager accepts. Subject `standing.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.standing.followup / leave; conversations.scene.standing.settled_opinion.respond / leave
```

> Written out in full under **`conversations.scene.standing.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.standing.again.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.again` — e.g. "Asking again? Standing doesn't shift by the hour, %1$s. But go on."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.again.respond   [35 chars]
    en  Standing doesn't shift by the hour.
    >>  ............................................
    pt  Reputação não muda de hora em hora.
    >>  ............................................
```


### Button `press` — "Tell me anyway."

*stance family `curiosity` · tone `plain` · outcome `accepted` · answers the beat(s) `standing.again.open` · offered only once the villager has actually said `standing:asked_before`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.again.press` — accepted phrasings: "tell me anyway"; "tell me again"; "humour me"
  - the message must contain one of: `anyway`, `again`, `tell`
  - scored words: `anyway`(1.5), `again`(1.2), `tell`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.again.respond.press   [15 chars]
    en  Tell me anyway.
    >>  ............................................
    pt  Me diga mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me anyway."
       spoken on: conversations.topic.standing.again.respond, button `press`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.again.press`: the villager reports. Subject `standing.repeat`, polarity `neutral`, closes subject, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.again.press/1   [82 chars]
    en  Same as this morning: it moves slower than you'd like and faster than you'd think.
    >>  ............................................
    pt  Igual a hoje de manhã: se move mais devagar do que você queria e mais rápido do que imagina.
    >>  ............................................
  dialogue.conversations.standing.again.press/2   [57 chars]
    en  Nothing's shifted, %1$s. That in itself is worth knowing.
    >>  ............................................
    pt  Nada mudou, %1$s. Isso por si só já vale saber.
    >>  ............................................
  dialogue.conversations.standing.again.press/3   [61 chars]
    en  Ask the village, not me. They'll tell you the same twice too.
    >>  ............................................
    pt  Pergunte ao vilarejo, não a mim. Eles vão te dizer o mesmo duas vezes também.
    >>  ............................................
```


### Button `apologize` — "Sorry — you've told me."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `standing.again.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.again.apologize` — accepted phrasings: "sorry, you have told me"; "i forgot you already told me"; "my mistake, you said"
  - the message must contain one of: `sorry`, `told`, `forgot`
  - scored words: `sorry`(1.2), `told`(1.5), `forgot`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.again.respond.apologize   [23 chars]
    en  Sorry — you've told me.
    >>  ............................................
    pt  Desculpe — você já me disse.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2  _(recorded under topic `standing.again.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — you've told me."
       spoken on: conversations.topic.standing.again.respond, button `apologize`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.again.apologize`: the villager accepts. Subject `standing.repeat`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.again.apologize/1   [56 chars]
    en  No harm. It's a thing worth worrying at, I'll grant you.
    >>  ............................................
    pt  Sem problema. É uma coisa que dá pra ficar remoendo, admito.
    >>  ............................................
  dialogue.conversations.standing.again.apologize/2   [57 chars]
    en  You have a lot on your mind, %1$s. So does everyone here.
    >>  ............................................
    pt  Você tem muita coisa na cabeça, %1$s. Todo mundo aqui também.
    >>  ............................................
  dialogue.conversations.standing.again.apologize/3   [57 chars]
    en  Quite. Come back tomorrow and I might have something new.
    >>  ............................................
    pt  Exato. Volte amanhã e talvez eu tenha algo novo.
    >>  ............................................
```


### Button `back` — "Another time, then."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.again.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.again.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.again.respond.back   [19 chars]
    en  Another time, then.
    >>  ............................................
    pt  Fica pra outra hora, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.again.back
WHO    VILLAGER — what the player reads after pressing "Another time, then."
       spoken on: conversations.topic.standing.again.respond, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.again.back`: the villager accepts. Subject `standing.repeat`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.again.back/1   [20 chars]
    en  It is. Another time.
    >>  ............................................
    pt  É sim. Fica pra outra.
    >>  ............................................
  dialogue.conversations.standing.again.back/2   [21 chars]
    en  Leave it there, %1$s.
    >>  ............................................
    pt  Deixe por aí, %1$s.
    >>  ............................................
  dialogue.conversations.standing.again.back/3   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

---


## `conversations.topic.standing.bad.followup`

**Reached from 3 route(s):** `conversations.topic.standing.bad.respond` / `ask_deed`; `conversations.topic.standing.bad.respond` / `ask_mend`; `conversations.topic.standing.bad.respond` / `protest`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.bad.deed` — e.g. "Well — %2$s. That's the one that comes up."
- `conversations.standing.bad.mend` — e.g. "Time and a dozen small kindnesses nobody thanks you for. There's no shortcut."
- `conversations.standing.bad.protest` — e.g. "Maybe not. A village judges what it sees, not what you are."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.bad.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.bad.followup   [49 chars]
    en  So that's where you stand. What do you say to it?
    >>  ............................................
    pt  Então é aí que você está. O que me diz?
    >>  ............................................
```


### Button `thanks` — "Thank you for being straight with me."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `standing.bad.deed`, `standing.bad.mend`, `standing.bad.protest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.bad.followup.thanks` — accepted phrasings: "thank you for being straight with me"; "thank you for telling me"; "i needed to hear it"
  - the message must contain one of: `straight`, `thank`, `told`
  - scored words: `straight`(1.5), `thank`(1.2), `told`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.bad.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.bad.followup.thanks   [37 chars]
    en  Thank you for being straight with me.
    >>  ............................................
    pt  Obrigado por ser franco comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.bad.followup.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `standing.bad.followup.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.bad.followup.thanks
WHO    VILLAGER — what the player reads after pressing "Thank you for being straight with me."
       spoken on: conversations.topic.standing.bad.followup, button `thanks`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.followup.thanks`: the villager accepts. Subject `standing.bad`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.bad.followup.thanks/1   [65 chars]
    en  You took that better than most. It'll be remembered kindly, %1$s.
    >>  ............................................
    pt  Você levou isso melhor que a maioria. Vai ser lembrado com carinho, %1$s.
    >>  ............................................
  dialogue.conversations.standing.bad.followup.thanks/2   [64 chars]
    en  Straight talk deserves straight thanks. That's a start, that is.
    >>  ............................................
    pt  Fala franca merece agradecimento franco. Já é um começo.
    >>  ............................................
  dialogue.conversations.standing.bad.followup.thanks/3   [48 chars]
    en  That's all anyone can do — hear it and carry on.
    >>  ............................................
    pt  É tudo que alguém pode fazer — ouvir e seguir em frente.
    >>  ............................................
```


### Button `brush` — "I didn't ask for a lecture."

*stance family `dismissal` · tone `hostile` · outcome `rebuffed` · answers the beat(s) `standing.bad.deed`, `standing.bad.mend`, `standing.bad.protest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.bad.followup.brush` — accepted phrasings: "i did not ask for a lecture"; "spare me the sermon"; "i did not come here for a lecture"
  - the message must contain one of: `lecture`, `sermon`
  - scored words: `lecture`(1.5), `sermon`(1.5), `ask`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.followup.brush
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.bad.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.bad.followup.brush   [27 chars]
    en  I didn't ask for a lecture.
    >>  ............................................
    pt  Eu não pedi um sermão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.bad.followup.brush`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `standing.bad.followup.brush`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.bad.followup.brush
WHO    VILLAGER — what the player reads after pressing "I didn't ask for a lecture."
       spoken on: conversations.topic.standing.bad.followup, button `brush`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.followup.brush`: the villager refuses. Subject `standing.bad`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.bad.followup.brush/1   [54 chars]
    en  A lecture, was it? Then I'll save my breath next time.
    >>  ............................................
    pt  Sermão, é? Então da próxima vez eu poupo o fôlego.
    >>  ............................................
  dialogue.conversations.standing.bad.followup.brush/2   [78 chars]
    en  You asked, %1$s. Snapping at the answer is a fine way to prove the talk right.
    >>  ............................................
    pt  Você perguntou, %1$s. Se irritar com a resposta é um jeito ótimo de provar que falam a verdade.
    >>  ............................................
  dialogue.conversations.standing.bad.followup.brush/3   [58 chars]
    en  As you like. Don't come asking again if the answer stings.
    >>  ............................................
    pt  Como quiser. Não venha perguntar de novo se a resposta arde.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.standing.bad.followup.brush/1
    en  ...A lecture. Sorry. I go on when it matters to me, %1$s.
    >>  ............................................
    pt  ...Um sermão. Desculpe. Eu me alongo quando me importa, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll keep it shorter. Or keep it entirely.
    >>  ............................................
    pt  Certo. Vou encurtar. Ou guardar inteiro.
    >>  ............................................
  anxious.dialogue.conversations.standing.bad.followup.brush/3
    en  ...I shouldn't have gone on. I know I do that.
    >>  ............................................
    pt  ...Eu não devia ter me alongado. Eu sei que eu faço isso.
    >>  ............................................
  athletic.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. Well. It's a long subject and I've a long acquaintance with it.
    >>  ............................................
    pt  Um sermão, era? Bom. É um assunto longo e eu tenho longa convivência com ele.
    >>  ............................................
  athletic.dialogue.conversations.standing.bad.followup.brush/2
    en  ...Right you are. I'll give you the short version another day.
    >>  ............................................
    pt  ...Você tem razão. Dou a versão curta outro dia.
    >>  ............................................
  athletic.dialogue.conversations.standing.bad.followup.brush/3
    en  Fair enough. It'll all still be true next week.
    >>  ............................................
    pt  Tudo bem. Vai continuar tudo verdade semana que vem.
    >>  ............................................
  confident.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. Then I'll save my breath next time.
    >>  ............................................
    pt  Um sermão, era? Então eu poupo meu fôlego da próxima.
    >>  ............................................
  confident.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  confident.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Ask somebody else about the village, then.
    >>  ............................................
    pt  ...Pergunte a outra pessoa sobre o vilarejo, então.
    >>  ............................................
  crabby.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. Then I'll save my breath next time.
    >>  ............................................
    pt  Um sermão, era? Então eu poupo meu fôlego da próxima.
    >>  ............................................
  crabby.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  crabby.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Ask somebody else about the village, then.
    >>  ............................................
    pt  ...Pergunte a outra pessoa sobre o vilarejo, então.
    >>  ............................................
  extroverted.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. I'd thought you wanted to know, %1$s.
    >>  ............................................
    pt  Um sermão, era? Achei que você quisesse saber, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll keep it to a sentence next time.
    >>  ............................................
    pt  Certo. Da próxima eu resumo numa frase.
    >>  ............................................
  extroverted.dialogue.conversations.standing.bad.followup.brush/3
    en  ...I only said it because it mattered to me that you knew.
    >>  ............................................
    pt  ...Eu só disse porque me importava que você soubesse.
    >>  ............................................
  flirty.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. I'd thought you wanted to know, %1$s.
    >>  ............................................
    pt  Um sermão, era? Achei que você quisesse saber, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll keep it to a sentence next time.
    >>  ............................................
    pt  Certo. Da próxima eu resumo numa frase.
    >>  ............................................
  flirty.dialogue.conversations.standing.bad.followup.brush/3
    en  ...I only said it because it mattered to me that you knew.
    >>  ............................................
    pt  ...Eu só disse porque me importava que você soubesse.
    >>  ............................................
  friendly.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. I'd thought you wanted to know, %1$s.
    >>  ............................................
    pt  Um sermão, era? Achei que você quisesse saber, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll keep it to a sentence next time.
    >>  ............................................
    pt  Certo. Da próxima eu resumo numa frase.
    >>  ............................................
  friendly.dialogue.conversations.standing.bad.followup.brush/3
    en  ...I only said it because it mattered to me that you knew.
    >>  ............................................
    pt  ...Eu só disse porque me importava que você soubesse.
    >>  ............................................
  gloomy.dialogue.conversations.standing.bad.followup.brush/1
    en  ...A lecture. Sorry. I go on when it matters to me, %1$s.
    >>  ............................................
    pt  ...Um sermão. Desculpe. Eu me alongo quando me importa, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll keep it shorter. Or keep it entirely.
    >>  ............................................
    pt  Certo. Vou encurtar. Ou guardar inteiro.
    >>  ............................................
  gloomy.dialogue.conversations.standing.bad.followup.brush/3
    en  ...I shouldn't have gone on. I know I do that.
    >>  ............................................
    pt  ...Eu não devia ter me alongado. Eu sei que eu faço isso.
    >>  ............................................
  greedy.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. Then I'll save my breath next time.
    >>  ............................................
    pt  Um sermão, era? Então eu poupo meu fôlego da próxima.
    >>  ............................................
  greedy.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  greedy.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Ask somebody else about the village, then.
    >>  ............................................
    pt  ...Pergunte a outra pessoa sobre o vilarejo, então.
    >>  ............................................
  grumpy.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. Then I'll save my breath next time.
    >>  ............................................
    pt  Um sermão, era? Então eu poupo meu fôlego da próxima.
    >>  ............................................
  grumpy.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  grumpy.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Ask somebody else about the village, then.
    >>  ............................................
    pt  ...Pergunte a outra pessoa sobre o vilarejo, então.
    >>  ............................................
  introverted.dialogue.conversations.standing.bad.followup.brush/1
    en  ...A lecture. Right.
    >>  ............................................
    pt  ...Um sermão. Certo.
    >>  ............................................
  introverted.dialogue.conversations.standing.bad.followup.brush/2
    en  I'll save my breath.
    >>  ............................................
    pt  Vou poupar meu fôlego.
    >>  ............................................
  introverted.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Nothing further, then.
    >>  ............................................
    pt  ...Nada mais, então.
    >>  ............................................
  lazy.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. Well. It's a long subject and I've a long acquaintance with it.
    >>  ............................................
    pt  Um sermão, era? Bom. É um assunto longo e eu tenho longa convivência com ele.
    >>  ............................................
  lazy.dialogue.conversations.standing.bad.followup.brush/2
    en  ...Right you are. I'll give you the short version another day.
    >>  ............................................
    pt  ...Você tem razão. Dou a versão curta outro dia.
    >>  ............................................
  lazy.dialogue.conversations.standing.bad.followup.brush/3
    en  Fair enough. It'll all still be true next week.
    >>  ............................................
    pt  Tudo bem. Vai continuar tudo verdade semana que vem.
    >>  ............................................
  odd.dialogue.conversations.standing.bad.followup.brush/1
    en  ...A lecture. Right.
    >>  ............................................
    pt  ...Um sermão. Certo.
    >>  ............................................
  odd.dialogue.conversations.standing.bad.followup.brush/2
    en  I'll save my breath.
    >>  ............................................
    pt  Vou poupar meu fôlego.
    >>  ............................................
  odd.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Nothing further, then.
    >>  ............................................
    pt  ...Nada mais, então.
    >>  ............................................
  peaceful.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. Well. It's a long subject and I've a long acquaintance with it.
    >>  ............................................
    pt  Um sermão, era? Bom. É um assunto longo e eu tenho longa convivência com ele.
    >>  ............................................
  peaceful.dialogue.conversations.standing.bad.followup.brush/2
    en  ...Right you are. I'll give you the short version another day.
    >>  ............................................
    pt  ...Você tem razão. Dou a versão curta outro dia.
    >>  ............................................
  peaceful.dialogue.conversations.standing.bad.followup.brush/3
    en  Fair enough. It'll all still be true next week.
    >>  ............................................
    pt  Tudo bem. Vai continuar tudo verdade semana que vem.
    >>  ............................................
  peppy.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture! Right. I'll shorten it to three words next time, %1$s.
    >>  ............................................
    pt  Um sermão! Certo. Da próxima eu resumo em três palavras, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.standing.bad.followup.brush/2
    en  Well, that's my civic duty discharged and unappreciated.
    >>  ............................................
    pt  Bom, meu dever cívico está cumprido e não apreciado.
    >>  ............................................
  peppy.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Ha. Fine. Next time it's just 'fine' and you can work out the rest.
    >>  ............................................
    pt  ...Ha. Tudo bem. Da próxima é só 'tudo bem' e você descobre o resto.
    >>  ............................................
  playful.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture! Right. I'll shorten it to three words next time, %1$s.
    >>  ............................................
    pt  Um sermão! Certo. Da próxima eu resumo em três palavras, %1$s.
    >>  ............................................
  playful.dialogue.conversations.standing.bad.followup.brush/2
    en  Well, that's my civic duty discharged and unappreciated.
    >>  ............................................
    pt  Bom, meu dever cívico está cumprido e não apreciado.
    >>  ............................................
  playful.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Ha. Fine. Next time it's just 'fine' and you can work out the rest.
    >>  ............................................
    pt  ...Ha. Tudo bem. Da próxima é só 'tudo bem' e você descobre o resto.
    >>  ............................................
  relaxed.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture, was it. Well. It's a long subject and I've a long acquaintance with it.
    >>  ............................................
    pt  Um sermão, era? Bom. É um assunto longo e eu tenho longa convivência com ele.
    >>  ............................................
  relaxed.dialogue.conversations.standing.bad.followup.brush/2
    en  ...Right you are. I'll give you the short version another day.
    >>  ............................................
    pt  ...Você tem razão. Dou a versão curta outro dia.
    >>  ............................................
  relaxed.dialogue.conversations.standing.bad.followup.brush/3
    en  Fair enough. It'll all still be true next week.
    >>  ............................................
    pt  Tudo bem. Vai continuar tudo verdade semana que vem.
    >>  ............................................
  sensitive.dialogue.conversations.standing.bad.followup.brush/1
    en  ...A lecture. Sorry. I go on when it matters to me, %1$s.
    >>  ............................................
    pt  ...Um sermão. Desculpe. Eu me alongo quando me importa, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.standing.bad.followup.brush/2
    en  Right. I'll keep it shorter. Or keep it entirely.
    >>  ............................................
    pt  Certo. Vou encurtar. Ou guardar inteiro.
    >>  ............................................
  sensitive.dialogue.conversations.standing.bad.followup.brush/3
    en  ...I shouldn't have gone on. I know I do that.
    >>  ............................................
    pt  ...Eu não devia ter me alongado. Eu sei que eu faço isso.
    >>  ............................................
  shy.dialogue.conversations.standing.bad.followup.brush/1
    en  ...A lecture. Right.
    >>  ............................................
    pt  ...Um sermão. Certo.
    >>  ............................................
  shy.dialogue.conversations.standing.bad.followup.brush/2
    en  I'll save my breath.
    >>  ............................................
    pt  Vou poupar meu fôlego.
    >>  ............................................
  shy.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Nothing further, then.
    >>  ............................................
    pt  ...Nada mais, então.
    >>  ............................................
  upbeat.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture! Right. I'll shorten it to three words next time, %1$s.
    >>  ............................................
    pt  Um sermão! Certo. Da próxima eu resumo em três palavras, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.standing.bad.followup.brush/2
    en  Well, that's my civic duty discharged and unappreciated.
    >>  ............................................
    pt  Bom, meu dever cívico está cumprido e não apreciado.
    >>  ............................................
  upbeat.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Ha. Fine. Next time it's just 'fine' and you can work out the rest.
    >>  ............................................
    pt  ...Ha. Tudo bem. Da próxima é só 'tudo bem' e você descobre o resto.
    >>  ............................................
  witty.dialogue.conversations.standing.bad.followup.brush/1
    en  A lecture! Right. I'll shorten it to three words next time, %1$s.
    >>  ............................................
    pt  Um sermão! Certo. Da próxima eu resumo em três palavras, %1$s.
    >>  ............................................
  witty.dialogue.conversations.standing.bad.followup.brush/2
    en  Well, that's my civic duty discharged and unappreciated.
    >>  ............................................
    pt  Bom, meu dever cívico está cumprido e não apreciado.
    >>  ............................................
  witty.dialogue.conversations.standing.bad.followup.brush/3
    en  ...Ha. Fine. Next time it's just 'fine' and you can work out the rest.
    >>  ............................................
    pt  ...Ha. Tudo bem. Da próxima é só 'tudo bem' e você descobre o resto.
    >>  ............................................
```

</details>


### Button `back` — "I'll think on it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.bad.deed`, `standing.bad.mend`, `standing.bad.protest` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.followup.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.bad.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.bad.followup.back   [17 chars]
    en  I'll think on it.
    >>  ............................................
    pt  Vou pensar nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.bad.followup.back
WHO    VILLAGER — what the player reads after pressing "I'll think on it."
       spoken on: conversations.topic.standing.bad.followup, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.followup.back`: the villager accepts. Subject `standing.bad`, polarity `mixed`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.bad.followup.back/1   [32 chars]
    en  Do that. It's worth thinking on.
    >>  ............................................
    pt  Pense. Vale pensar.
    >>  ............................................
  dialogue.conversations.standing.bad.followup.back/2   [26 chars]
    en  Just so. Off you go, %1$s.
    >>  ............................................
    pt  Pois é. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.standing.bad.followup.back/3   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
```

---


## `conversations.topic.standing.bad.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.bad` — e.g. "You'd hear it kindlier from me than most: %2$s is the word for you here, and it wasn't given lightly."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.bad.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.bad.respond   [52 chars]
    en  That's how you stand with us, near as I can tell it.
    >>  ............................................
    pt  É assim que você está com a gente, pelo que eu consigo dizer.
    >>  ............................................
```


### Button `ask_deed` — "What have they heard, exactly?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.bad.open` · offered only once the villager has actually said `standing:bad`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.bad.deed` — accepted phrasings: "what have they heard exactly"; "what do they hold against me"; "what exactly is held against me"
  - the message must contain one of: `heard`, `against`, `exactly`
  - scored words: `heard`(1.5), `against`(1.5), `exactly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.respond.ask_deed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.bad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.bad.respond.ask_deed   [30 chars]
    en  What have they heard, exactly?
    >>  ............................................
    pt  O que exatamente eles ouviram?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.bad.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I didn't ask for a lecture." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.bad.deed
WHO    VILLAGER — what the player reads after pressing "What have they heard, exactly?"
       spoken on: conversations.topic.standing.bad.respond, button `ask_deed`
       leaves the player on: conversations.topic.standing.bad.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = reputation_recent_deed
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.deed`: the villager reports. Subject `standing.bad`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `standing:bad`, `deed:named` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, dismissal, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.bad.deed/1   [42 chars]
    en  Well — %2$s. That's the one that comes up.
    >>  ............................................
    pt  Bom — %2$s. É essa que sempre aparece.
    >>  ............................................
  dialogue.conversations.standing.bad.deed/2   [59 chars]
    en  The thing people mention? %2$s. Make of that what you will.
    >>  ............................................
    pt  A coisa que as pessoas mencionam? %2$s. Tire suas conclusões.
    >>  ............................................
  dialogue.conversations.standing.bad.deed/3   [51 chars]
    en  It's %2$s they talk about, when your name comes up.
    >>  ............................................
    pt  É de %2$s que falam, quando seu nome surge.
    >>  ............................................
```


### Button `ask_mend` — "What would mend it?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.bad.open` · offered only once the villager has actually said `standing:bad`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.bad.mend` — accepted phrasings: "what would mend it"; "how do i repair that"; "what would make it better"
  - the message must contain one of: `mend`, `repair`, `better`
  - scored words: `mend`(1.5), `repair`(1.5), `better`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.respond.ask_mend
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.bad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.bad.respond.ask_mend   [19 chars]
    en  What would mend it?
    >>  ............................................
    pt  O que consertaria isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.bad.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I didn't ask for a lecture." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.bad.mend
WHO    VILLAGER — what the player reads after pressing "What would mend it?"
       spoken on: conversations.topic.standing.bad.respond, button `ask_mend`
       leaves the player on: conversations.topic.standing.bad.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.mend`: the villager explains. Subject `standing.bad`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `standing:bad`, `advice:given` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, dismissal, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.bad.mend/1   [77 chars]
    en  Time and a dozen small kindnesses nobody thanks you for. There's no shortcut.
    >>  ............................................
    pt  Tempo e uma dúzia de gentilezas pequenas que ninguém agradece. Não tem atalho.
    >>  ............................................
  dialogue.conversations.standing.bad.mend/2   [77 chars]
    en  Be useful where it costs you something, %1$s. People can tell the difference.
    >>  ............................................
    pt  Seja útil onde custe algo, %1$s. As pessoas percebem a diferença.
    >>  ............................................
  dialogue.conversations.standing.bad.mend/3   [62 chars]
    en  Stop giving them new material. That alone would do half of it.
    >>  ............................................
    pt  Pare de dar assunto novo. Só isso já resolveria metade.
    >>  ............................................
```


### Button `protest` — "That's not who I am."

*stance family `respectful_disagreement` · tone `plain` · outcome `qualified` · answers the beat(s) `standing.bad.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.bad.protest` — accepted phrasings: "that is not who i am"; "that is unfair"; "they have me wrong"
  - the message must contain one of: `who`, `unfair`
  - scored words: `not`(0.4), `who`(1.0), `unfair`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.respond.protest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.bad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.bad.respond.protest   [20 chars]
    en  That's not who I am.
    >>  ............................................
    pt  Não é isso que eu sou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.bad.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I didn't ask for a lecture." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.bad.protest
WHO    VILLAGER — what the player reads after pressing "That's not who I am."
       spoken on: conversations.topic.standing.bad.respond, button `protest`
       leaves the player on: conversations.topic.standing.bad.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.protest`: the villager qualifys. Subject `standing.bad`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `standing:bad` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, dismissal, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.bad.protest/1   [59 chars]
    en  Maybe not. A village judges what it sees, not what you are.
    >>  ............................................
    pt  Talvez não. Um vilarejo julga o que vê, não o que você é.
    >>  ............................................
  dialogue.conversations.standing.bad.protest/2   [81 chars]
    en  I've thought that myself about my own name, %1$s. It changed nothing until I did.
    >>  ............................................
    pt  Já pensei isso do meu próprio nome, %1$s. Não mudou nada até eu mudar.
    >>  ............................................
  dialogue.conversations.standing.bad.protest/3   [75 chars]
    en  Then you've the harder job: being the other thing where people are looking.
    >>  ............................................
    pt  Então você tem o trabalho mais difícil: ser a outra coisa onde as pessoas estão olhando.
    >>  ............................................
```


### Button `dismiss` — "Their opinions are their problem."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `standing.bad.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.bad.dismissed` — accepted phrasings: "their opinions are their problem"; "i do not care for their judgement"; "i do not care what they think"
  - the message must contain one of: `opinions`, `judgement`, `care`
  - scored words: `opinions`(1.5), `judgement`(1.5), `care`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.bad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.bad.respond.dismiss   [33 chars]
    en  Their opinions are their problem.
    >>  ............................................
    pt  A opinião deles é problema deles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.bad.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `standing.bad.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.bad.dismissed
WHO    VILLAGER — what the player reads after pressing "Their opinions are their problem."
       spoken on: conversations.topic.standing.bad.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.dismissed`: the villager resists. Subject `standing.bad`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.bad.dismissed/1   [72 chars]
    en  Suit yourself, %1$s. But a village's memory is longer than its patience.
    >>  ............................................
    pt  Como quiser, %1$s. Mas a memória de um vilarejo é mais longa que a paciência dele.
    >>  ............................................
  dialogue.conversations.standing.bad.dismissed/2   [96 chars]
    en  If you say so. The people whose opinions 'don't matter' are the ones you'll trade with tomorrow.
    >>  ............................................
    pt  Se você diz. As pessoas cujas opiniões 'não importam' são com quem você vai negociar amanhã.
    >>  ............................................
  dialogue.conversations.standing.bad.dismissed/3   [60 chars]
    en  Mm. You asked, mind. Don't ask if you don't want to hear it.
    >>  ............................................
    pt  Mm. Você que perguntou. Não pergunte se não quer ouvir.
    >>  ............................................
```


### Button `back` — "Enough about me."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.bad.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.bad.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.bad.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.bad.respond.back   [16 chars]
    en  Enough about me.
    >>  ............................................
    pt  Chega de falar de mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.bad.back
WHO    VILLAGER — what the player reads after pressing "Enough about me."
       spoken on: conversations.topic.standing.bad.respond, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.back`: the villager accepts. Subject `standing.bad`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.bad.back/1   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
  dialogue.conversations.standing.bad.back/2   [31 chars]
    en  So I've found. What else, then?
    >>  ............................................
    pt  Foi o que eu vi. O que mais, então?
    >>  ............................................
  dialogue.conversations.standing.bad.back/3   [16 chars]
    en  Go safely, %1$s.
    >>  ............................................
    pt  Vá com cuidado, %1$s.
    >>  ............................................
```

---


## `conversations.topic.standing.good.followup`

**Reached from 3 route(s):** `conversations.topic.standing.good.respond` / `ask_deed`; `conversations.topic.standing.good.respond` / `ask_keep`; `conversations.topic.standing.good.respond` / `deflect`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.good.deed` — e.g. "Well — %2$s. That's the one that comes up."
- `conversations.standing.good.deflect` — e.g. "No. The ones who would say it are rarely the ones we mean it about."
- `conversations.standing.good.keep` — e.g. "Turning up. That's most of it, %1$s — turning up when it's inconvenient."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.good.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.good.followup   [31 chars]
    en  That's the shape of it, anyway.
    >>  ............................................
    pt  É mais ou menos isso.
    >>  ............................................
```


### Button `thanks` — "Thank you for being straight with me."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `standing.good.deed`, `standing.good.keep`, `standing.good.deflect`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.good.followup.thanks` — accepted phrasings: "thank you for being straight with me"; "thanks for being honest"; "i appreciate the straight answer"
  - the message must contain one of: `straight`, `thank`, `honest`
  - scored words: `straight`(1.5), `thank`(1.2), `honest`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.good.followup.thanks   [37 chars]
    en  Thank you for being straight with me.
    >>  ............................................
    pt  Obrigado por ser franco comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.good.followup.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `standing.good.followup.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.good.followup.thanks
WHO    VILLAGER — what the player reads after pressing "Thank you for being straight with me."
       spoken on: conversations.topic.standing.good.followup, button `thanks`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.followup.thanks`: the villager accepts. Subject `standing.good`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.good.followup.thanks/1   [62 chars]
    en  Straight talk deserves straight thanks. We'll get along, %1$s.
    >>  ............................................
    pt  Fala franca merece agradecimento franco. A gente vai se dar bem, %1$s.
    >>  ............................................
  dialogue.conversations.standing.good.followup.thanks/2   [72 chars]
    en  You took that as well as you took the bad news you've never had from me.
    >>  ............................................
    pt  Você levou isso tão bem quanto levaria a má notícia que nunca precisei te dar.
    >>  ............................................
  dialogue.conversations.standing.good.followup.thanks/3   [48 chars]
    en  That's all anyone can do — hear it and carry on.
    >>  ............................................
    pt  É tudo que alguém pode fazer — ouvir e seguir em frente.
    >>  ............................................
```


### Button `keep_deserving` — "I'll try to keep deserving it."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `standing.good.deed`, `standing.good.keep`, `standing.good.deflect` · offered only once the villager has actually said `standing:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.good.followup.keep_deserving` — accepted phrasings: "i will try to keep deserving it"; "i will keep earning it"; "i intend to keep it that way"
  - the message must contain one of: `deserving`, `earn`, `keep`
  - scored words: `deserving`(1.5), `earn`(1.2), `keep`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.followup.keep_deserving
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.good.followup.keep_deserving   [30 chars]
    en  I'll try to keep deserving it.
    >>  ............................................
    pt  Vou tentar continuar merecendo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.good.followup.keep_deserving`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `standing.good.followup.keep_deserving`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.good.followup.keep_deserving
WHO    VILLAGER — what the player reads after pressing "I'll try to keep deserving it."
       spoken on: conversations.topic.standing.good.followup, button `keep_deserving`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.followup.keep_deserving`: the villager accepts. Subject `standing.good`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.good.followup.keep_deserving/1   [61 chars]
    en  That's the only way it's ever held, %1$s. Nobody coasts here.
    >>  ............................................
    pt  É o único jeito de se manter, %1$s. Ninguém vive de renda aqui.
    >>  ............................................
  dialogue.conversations.standing.good.followup.keep_deserving/2   [68 chars]
    en  Say that where the others can hear and you'll have half the village.
    >>  ............................................
    pt  Diga isso onde os outros ouçam e você terá metade do vilarejo.
    >>  ............................................
  dialogue.conversations.standing.good.followup.keep_deserving/3   [34 chars]
    en  Good. A name's a loan, not a gift.
    >>  ............................................
    pt  Bom. Um nome é um empréstimo, não um presente.
    >>  ............................................
```


### Button `back` — "I'll think on it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.good.deed`, `standing.good.keep`, `standing.good.deflect` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.followup.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.good.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.good.followup.back   [17 chars]
    en  I'll think on it.
    >>  ............................................
    pt  Vou pensar nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.good.followup.back
WHO    VILLAGER — what the player reads after pressing "I'll think on it."
       spoken on: conversations.topic.standing.good.followup, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.followup.back`: the villager accepts. Subject `standing.good`, polarity `positive`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.good.followup.back/1   [8 chars]
    en  Do that.
    >>  ............................................
    pt  Pense.
    >>  ............................................
  dialogue.conversations.standing.good.followup.back/2   [30 chars]
    en  True enough. Off you go, %1$s.
    >>  ............................................
    pt  Bem verdade. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.standing.good.followup.back/3   [21 chars]
    en  That'll do for today.
    >>  ............................................
    pt  Por hoje está bom.
    >>  ............................................
```

---


## `conversations.topic.standing.good.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.good` — e.g. "You're well thought of, %1$s. %2$s, people say, and they say it warmly."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.good.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.good.respond   [52 chars]
    en  That's how you stand with us, near as I can tell it.
    >>  ............................................
    pt  É assim que você está com a gente, pelo que eu consigo dizer.
    >>  ............................................
```


### Button `ask_deed` — "What have they heard, exactly?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.good.open` · offered only once the villager has actually said `standing:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.good.deed` — accepted phrasings: "what have they heard exactly"; "what praise have they heard"; "what exactly have they heard about me"
  - the message must contain one of: `heard`, `exactly`, `praise`
  - scored words: `heard`(1.5), `exactly`(1.2), `praise`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.respond.ask_deed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.good.respond.ask_deed   [30 chars]
    en  What have they heard, exactly?
    >>  ............................................
    pt  O que exatamente eles ouviram?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.good.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I'll try to keep deserving it." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.good.deed
WHO    VILLAGER — what the player reads after pressing "What have they heard, exactly?"
       spoken on: conversations.topic.standing.good.respond, button `ask_deed`
       leaves the player on: conversations.topic.standing.good.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = reputation_recent_deed
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.deed`: the villager reports. Subject `standing.good`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `standing:good`, `deed:named` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.good.deed/1   [42 chars]
    en  Well — %2$s. That's the one that comes up.
    >>  ............................................
    pt  Bom — %2$s. É essa que sempre aparece.
    >>  ............................................
  dialogue.conversations.standing.good.deed/2   [61 chars]
    en  The thing people mention? %2$s. It's done you no harm at all.
    >>  ............................................
    pt  A coisa que as pessoas mencionam? %2$s. Não te fez mal nenhum.
    >>  ............................................
  dialogue.conversations.standing.good.deed/3   [57 chars]
    en  It's %2$s they talk about, and they talk about it kindly.
    >>  ............................................
    pt  É de %2$s que falam, e falam com carinho.
    >>  ............................................
```


### Button `ask_keep` — "What keeps it that way?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.good.open` · offered only once the villager has actually said `standing:good`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.good.keep` — accepted phrasings: "what keeps it that way"; "how do i stay well thought of"; "how do i maintain that"
  - the message must contain one of: `keeps`, `stay`, `maintain`
  - scored words: `keeps`(1.5), `stay`(1.2), `maintain`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.respond.ask_keep
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.good.respond.ask_keep   [23 chars]
    en  What keeps it that way?
    >>  ............................................
    pt  O que mantém isso assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.good.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I'll try to keep deserving it." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.good.keep
WHO    VILLAGER — what the player reads after pressing "What keeps it that way?"
       spoken on: conversations.topic.standing.good.respond, button `ask_keep`
       leaves the player on: conversations.topic.standing.good.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.keep`: the villager explains. Subject `standing.good`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `standing:good` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.good.keep/1   [72 chars]
    en  Turning up. That's most of it, %1$s — turning up when it's inconvenient.
    >>  ............................................
    pt  Aparecer. É a maior parte, %1$s — aparecer quando é inconveniente.
    >>  ............................................
  dialogue.conversations.standing.good.keep/2   [78 chars]
    en  Not spending it. A good name goes fastest when its owner starts leaning on it.
    >>  ............................................
    pt  Não gastar. Um bom nome vai embora mais rápido quando o dono começa a se apoiar nele.
    >>  ............................................
  dialogue.conversations.standing.good.keep/3   [59 chars]
    en  Being the same person in the square as you are on the road.
    >>  ............................................
    pt  Ser a mesma pessoa na praça e na estrada.
    >>  ............................................
```


### Button `deflect` — "I'd not have said so myself."

*stance family `self_disclosure` · tone `gentle` · outcome `qualified` · answers the beat(s) `standing.good.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.good.deflect` — accepted phrasings: "i would not have said so myself"; "i am not sure i would say that"; "that seems generous"
  - the message must contain one of: `myself`, `said`, `modest`
  - scored words: `myself`(1.5), `said`(1.0), `modest`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.respond.deflect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.good.respond.deflect   [28 chars]
    en  I'd not have said so myself.
    >>  ............................................
    pt  Eu mesmo não teria dito isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.good.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I'll try to keep deserving it." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.good.deflect
WHO    VILLAGER — what the player reads after pressing "I'd not have said so myself."
       spoken on: conversations.topic.standing.good.respond, button `deflect`
       leaves the player on: conversations.topic.standing.good.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.deflect`: the villager qualifys. Subject `standing.good`, polarity `positive`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `standing:good` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.good.deflect/1   [67 chars]
    en  No. The ones who would say it are rarely the ones we mean it about.
    >>  ............................................
    pt  Não. Quem diria isso raramente é de quem a gente fala.
    >>  ............................................
  dialogue.conversations.standing.good.deflect/2   [65 chars]
    en  That's rather the point of it being other people's opinion, %1$s.
    >>  ............................................
    pt  É meio que o ponto de ser opinião dos outros, %1$s.
    >>  ............................................
  dialogue.conversations.standing.good.deflect/3   [60 chars]
    en  Then it's a good thing you're not the one keeping the tally.
    >>  ............................................
    pt  Então é bom que não seja você quem faz a contagem.
    >>  ............................................
```


### Button `dismiss` — "Their opinions are their problem."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `standing.good.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.good.dismissed` — accepted phrasings: "their opinions are their problem"; "i do not need the flattery"; "that is their problem, not mine"
  - the message must contain one of: `opinions`, `problem`, `flattery`
  - scored words: `opinions`(1.5), `problem`(1.2), `flattery`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.good.respond.dismiss   [33 chars]
    en  Their opinions are their problem.
    >>  ............................................
    pt  A opinião deles é problema deles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.good.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `standing.good.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.good.dismissed
WHO    VILLAGER — what the player reads after pressing "Their opinions are their problem."
       spoken on: conversations.topic.standing.good.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.dismissed`: the villager resists. Subject `standing.good`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.good.dismissed/1   [62 chars]
    en  Suit yourself, %1$s. Though it's a strange thing to shrug off.
    >>  ............................................
    pt  Como quiser, %1$s. Mas é estranho ignorar isso.
    >>  ............................................
  dialogue.conversations.standing.good.dismissed/2   [62 chars]
    en  If you say so. It's the only inheritance most people here get.
    >>  ............................................
    pt  Se você diz. É a única herança que a maioria daqui recebe.
    >>  ............................................
  dialogue.conversations.standing.good.dismissed/3   [20 chars]
    en  Mm. You asked, mind.
    >>  ............................................
    pt  Mm. Você que perguntou.
    >>  ............................................
```


### Button `back` — "Enough about me."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.good.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.good.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.good.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.good.respond.back   [16 chars]
    en  Enough about me.
    >>  ............................................
    pt  Chega de falar de mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.good.back
WHO    VILLAGER — what the player reads after pressing "Enough about me."
       spoken on: conversations.topic.standing.good.respond, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.back`: the villager accepts. Subject `standing.good`, polarity `positive`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.good.back/1   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
  dialogue.conversations.standing.good.back/2   [26 chars]
    en  So it is. What else, then?
    >>  ............................................
    pt  É assim mesmo. O que mais, então?
    >>  ............................................
  dialogue.conversations.standing.good.back/3   [20 chars]
    en  Mind the road, %1$s.
    >>  ............................................
    pt  Cuidado na estrada, %1$s.
    >>  ............................................
```

---


## `conversations.topic.standing.incident.followup`

**Reached from 3 route(s):** `conversations.topic.standing.incident.respond` / `ask_deed`; `conversations.topic.standing.incident.respond` / `amends`; `conversations.topic.standing.incident.respond` / `deny`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.amends.done` — e.g. "Saying it where people can hear — that's a start, %1$s. I'll make sure it's known you said it."
- `conversations.standing.incident.deed` — e.g. "%2$s. That's the matter, and nobody's called it settled."
- `conversations.standing.incident.deny` — e.g. "It rarely is. That's never yet stopped a village from settling on a version."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.incident.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.incident.followup   [51 chars]
    en  So that's what stands between you and this village.
    >>  ............................................
    pt  Então é isso que está entre você e este vilarejo.
    >>  ............................................
```


### Button `thanks` — "Thank you for being straight with me."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `standing.incident.deed`, `standing.amends.done`, `standing.incident.deny`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.incident.followup.thanks` — accepted phrasings: "thank you for being straight with me"; "thank you for saying it to my face"; "i am glad you told me"
  - the message must contain one of: `straight`, `face`, `thank`
  - scored words: `straight`(1.5), `face`(1.2), `thank`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.incident.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.incident.followup.thanks   [37 chars]
    en  Thank you for being straight with me.
    >>  ............................................
    pt  Obrigado por ser franco comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.incident.followup.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `standing.incident.followup.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.incident.followup.thanks
WHO    VILLAGER — what the player reads after pressing "Thank you for being straight with me."
       spoken on: conversations.topic.standing.incident.followup, button `thanks`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.incident.followup.thanks`: the villager accepts. Subject `standing.incident`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.incident.followup.thanks/1   [65 chars]
    en  You took that better than most. It'll be remembered kindly, %1$s.
    >>  ............................................
    pt  Você levou isso melhor que a maioria. Vai ser lembrado com carinho, %1$s.
    >>  ............................................
  dialogue.conversations.standing.incident.followup.thanks/2   [74 chars]
    en  Somebody had to say it to your face. I'd rather it were me than the crowd.
    >>  ............................................
    pt  Alguém tinha que dizer na sua cara. Prefiro que tenha sido eu e não a multidão.
    >>  ............................................
  dialogue.conversations.standing.incident.followup.thanks/3   [48 chars]
    en  That's all anyone can do — hear it and carry on.
    >>  ............................................
    pt  É tudo que alguém pode fazer — ouvir e seguir em frente.
    >>  ............................................
```


### Button `brush` — "I didn't ask for a lecture."

*stance family `dismissal` · tone `hostile` · outcome `rebuffed` · answers the beat(s) `standing.incident.deed`, `standing.amends.done`, `standing.incident.deny`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.incident.followup.brush` — accepted phrasings: "i did not ask for a lecture"; "stop preaching at me"; "i did not come here to be told off"
  - the message must contain one of: `lecture`, `preaching`
  - scored words: `lecture`(1.5), `preaching`(1.5), `came`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.followup.brush
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.incident.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.incident.followup.brush   [27 chars]
    en  I didn't ask for a lecture.
    >>  ............................................
    pt  Eu não pedi um sermão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.incident.followup.brush`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `standing.incident.followup.brush`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.incident.followup.brush
WHO    VILLAGER — what the player reads after pressing "I didn't ask for a lecture."
       spoken on: conversations.topic.standing.incident.followup, button `brush`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.incident.followup.brush`: the villager refuses. Subject `standing.incident`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.incident.followup.brush/1   [54 chars]
    en  A lecture, was it? Then I'll save my breath next time.
    >>  ............................................
    pt  Sermão, é? Então da próxima vez eu poupo o fôlego.
    >>  ............................................
  dialogue.conversations.standing.incident.followup.brush/2   [78 chars]
    en  You asked, %1$s. Snapping at the answer is a fine way to prove the talk right.
    >>  ............................................
    pt  Você perguntou, %1$s. Se irritar com a resposta é um jeito ótimo de provar que falam a verdade.
    >>  ............................................
  dialogue.conversations.standing.incident.followup.brush/3   [58 chars]
    en  As you like. Don't come asking again if the answer stings.
    >>  ............................................
    pt  Como quiser. Não venha perguntar de novo se a resposta arde.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.standing.incident.followup.brush/1
    en  ...A lecture. Sorry. I go on when it matters to me, %1$s.
    >>  ............................................
    pt  ...Um sermão. Desculpe. Eu me alongo quando me importa, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll keep it shorter. Or keep it entirely.
    >>  ............................................
    pt  Certo. Vou encurtar. Ou guardar inteiro.
    >>  ............................................
  anxious.dialogue.conversations.standing.incident.followup.brush/3
    en  ...I shouldn't have gone on. I know I do that.
    >>  ............................................
    pt  ...Eu não devia ter me alongado. Eu sei que eu faço isso.
    >>  ............................................
  athletic.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. Well. It's a long subject and I've a long acquaintance with it.
    >>  ............................................
    pt  Um sermão, era? Bom. É um assunto longo e eu tenho longa convivência com ele.
    >>  ............................................
  athletic.dialogue.conversations.standing.incident.followup.brush/2
    en  ...Right you are. I'll give you the short version another day.
    >>  ............................................
    pt  ...Você tem razão. Dou a versão curta outro dia.
    >>  ............................................
  athletic.dialogue.conversations.standing.incident.followup.brush/3
    en  Fair enough. It'll all still be true next week.
    >>  ............................................
    pt  Tudo bem. Vai continuar tudo verdade semana que vem.
    >>  ............................................
  confident.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. Then I'll save my breath next time.
    >>  ............................................
    pt  Um sermão, era? Então eu poupo meu fôlego da próxima.
    >>  ............................................
  confident.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  confident.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Ask somebody else about the village, then.
    >>  ............................................
    pt  ...Pergunte a outra pessoa sobre o vilarejo, então.
    >>  ............................................
  crabby.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. Then I'll save my breath next time.
    >>  ............................................
    pt  Um sermão, era? Então eu poupo meu fôlego da próxima.
    >>  ............................................
  crabby.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  crabby.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Ask somebody else about the village, then.
    >>  ............................................
    pt  ...Pergunte a outra pessoa sobre o vilarejo, então.
    >>  ............................................
  extroverted.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. I'd thought you wanted to know, %1$s.
    >>  ............................................
    pt  Um sermão, era? Achei que você quisesse saber, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll keep it to a sentence next time.
    >>  ............................................
    pt  Certo. Da próxima eu resumo numa frase.
    >>  ............................................
  extroverted.dialogue.conversations.standing.incident.followup.brush/3
    en  ...I only said it because it mattered to me that you knew.
    >>  ............................................
    pt  ...Eu só disse porque me importava que você soubesse.
    >>  ............................................
  flirty.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. I'd thought you wanted to know, %1$s.
    >>  ............................................
    pt  Um sermão, era? Achei que você quisesse saber, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll keep it to a sentence next time.
    >>  ............................................
    pt  Certo. Da próxima eu resumo numa frase.
    >>  ............................................
  flirty.dialogue.conversations.standing.incident.followup.brush/3
    en  ...I only said it because it mattered to me that you knew.
    >>  ............................................
    pt  ...Eu só disse porque me importava que você soubesse.
    >>  ............................................
  friendly.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. I'd thought you wanted to know, %1$s.
    >>  ............................................
    pt  Um sermão, era? Achei que você quisesse saber, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll keep it to a sentence next time.
    >>  ............................................
    pt  Certo. Da próxima eu resumo numa frase.
    >>  ............................................
  friendly.dialogue.conversations.standing.incident.followup.brush/3
    en  ...I only said it because it mattered to me that you knew.
    >>  ............................................
    pt  ...Eu só disse porque me importava que você soubesse.
    >>  ............................................
  gloomy.dialogue.conversations.standing.incident.followup.brush/1
    en  ...A lecture. Sorry. I go on when it matters to me, %1$s.
    >>  ............................................
    pt  ...Um sermão. Desculpe. Eu me alongo quando me importa, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll keep it shorter. Or keep it entirely.
    >>  ............................................
    pt  Certo. Vou encurtar. Ou guardar inteiro.
    >>  ............................................
  gloomy.dialogue.conversations.standing.incident.followup.brush/3
    en  ...I shouldn't have gone on. I know I do that.
    >>  ............................................
    pt  ...Eu não devia ter me alongado. Eu sei que eu faço isso.
    >>  ............................................
  greedy.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. Then I'll save my breath next time.
    >>  ............................................
    pt  Um sermão, era? Então eu poupo meu fôlego da próxima.
    >>  ............................................
  greedy.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  greedy.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Ask somebody else about the village, then.
    >>  ............................................
    pt  ...Pergunte a outra pessoa sobre o vilarejo, então.
    >>  ............................................
  grumpy.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. Then I'll save my breath next time.
    >>  ............................................
    pt  Um sermão, era? Então eu poupo meu fôlego da próxima.
    >>  ............................................
  grumpy.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  grumpy.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Ask somebody else about the village, then.
    >>  ............................................
    pt  ...Pergunte a outra pessoa sobre o vilarejo, então.
    >>  ............................................
  introverted.dialogue.conversations.standing.incident.followup.brush/1
    en  ...A lecture. Right.
    >>  ............................................
    pt  ...Um sermão. Certo.
    >>  ............................................
  introverted.dialogue.conversations.standing.incident.followup.brush/2
    en  I'll save my breath.
    >>  ............................................
    pt  Vou poupar meu fôlego.
    >>  ............................................
  introverted.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Nothing further, then.
    >>  ............................................
    pt  ...Nada mais, então.
    >>  ............................................
  lazy.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. Well. It's a long subject and I've a long acquaintance with it.
    >>  ............................................
    pt  Um sermão, era? Bom. É um assunto longo e eu tenho longa convivência com ele.
    >>  ............................................
  lazy.dialogue.conversations.standing.incident.followup.brush/2
    en  ...Right you are. I'll give you the short version another day.
    >>  ............................................
    pt  ...Você tem razão. Dou a versão curta outro dia.
    >>  ............................................
  lazy.dialogue.conversations.standing.incident.followup.brush/3
    en  Fair enough. It'll all still be true next week.
    >>  ............................................
    pt  Tudo bem. Vai continuar tudo verdade semana que vem.
    >>  ............................................
  odd.dialogue.conversations.standing.incident.followup.brush/1
    en  ...A lecture. Right.
    >>  ............................................
    pt  ...Um sermão. Certo.
    >>  ............................................
  odd.dialogue.conversations.standing.incident.followup.brush/2
    en  I'll save my breath.
    >>  ............................................
    pt  Vou poupar meu fôlego.
    >>  ............................................
  odd.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Nothing further, then.
    >>  ............................................
    pt  ...Nada mais, então.
    >>  ............................................
  peaceful.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. Well. It's a long subject and I've a long acquaintance with it.
    >>  ............................................
    pt  Um sermão, era? Bom. É um assunto longo e eu tenho longa convivência com ele.
    >>  ............................................
  peaceful.dialogue.conversations.standing.incident.followup.brush/2
    en  ...Right you are. I'll give you the short version another day.
    >>  ............................................
    pt  ...Você tem razão. Dou a versão curta outro dia.
    >>  ............................................
  peaceful.dialogue.conversations.standing.incident.followup.brush/3
    en  Fair enough. It'll all still be true next week.
    >>  ............................................
    pt  Tudo bem. Vai continuar tudo verdade semana que vem.
    >>  ............................................
  peppy.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture! Right. I'll shorten it to three words next time, %1$s.
    >>  ............................................
    pt  Um sermão! Certo. Da próxima eu resumo em três palavras, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.standing.incident.followup.brush/2
    en  Well, that's my civic duty discharged and unappreciated.
    >>  ............................................
    pt  Bom, meu dever cívico está cumprido e não apreciado.
    >>  ............................................
  peppy.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Ha. Fine. Next time it's just 'fine' and you can work out the rest.
    >>  ............................................
    pt  ...Ha. Tudo bem. Da próxima é só 'tudo bem' e você descobre o resto.
    >>  ............................................
  playful.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture! Right. I'll shorten it to three words next time, %1$s.
    >>  ............................................
    pt  Um sermão! Certo. Da próxima eu resumo em três palavras, %1$s.
    >>  ............................................
  playful.dialogue.conversations.standing.incident.followup.brush/2
    en  Well, that's my civic duty discharged and unappreciated.
    >>  ............................................
    pt  Bom, meu dever cívico está cumprido e não apreciado.
    >>  ............................................
  playful.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Ha. Fine. Next time it's just 'fine' and you can work out the rest.
    >>  ............................................
    pt  ...Ha. Tudo bem. Da próxima é só 'tudo bem' e você descobre o resto.
    >>  ............................................
  relaxed.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture, was it. Well. It's a long subject and I've a long acquaintance with it.
    >>  ............................................
    pt  Um sermão, era? Bom. É um assunto longo e eu tenho longa convivência com ele.
    >>  ............................................
  relaxed.dialogue.conversations.standing.incident.followup.brush/2
    en  ...Right you are. I'll give you the short version another day.
    >>  ............................................
    pt  ...Você tem razão. Dou a versão curta outro dia.
    >>  ............................................
  relaxed.dialogue.conversations.standing.incident.followup.brush/3
    en  Fair enough. It'll all still be true next week.
    >>  ............................................
    pt  Tudo bem. Vai continuar tudo verdade semana que vem.
    >>  ............................................
  sensitive.dialogue.conversations.standing.incident.followup.brush/1
    en  ...A lecture. Sorry. I go on when it matters to me, %1$s.
    >>  ............................................
    pt  ...Um sermão. Desculpe. Eu me alongo quando me importa, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.standing.incident.followup.brush/2
    en  Right. I'll keep it shorter. Or keep it entirely.
    >>  ............................................
    pt  Certo. Vou encurtar. Ou guardar inteiro.
    >>  ............................................
  sensitive.dialogue.conversations.standing.incident.followup.brush/3
    en  ...I shouldn't have gone on. I know I do that.
    >>  ............................................
    pt  ...Eu não devia ter me alongado. Eu sei que eu faço isso.
    >>  ............................................
  shy.dialogue.conversations.standing.incident.followup.brush/1
    en  ...A lecture. Right.
    >>  ............................................
    pt  ...Um sermão. Certo.
    >>  ............................................
  shy.dialogue.conversations.standing.incident.followup.brush/2
    en  I'll save my breath.
    >>  ............................................
    pt  Vou poupar meu fôlego.
    >>  ............................................
  shy.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Nothing further, then.
    >>  ............................................
    pt  ...Nada mais, então.
    >>  ............................................
  upbeat.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture! Right. I'll shorten it to three words next time, %1$s.
    >>  ............................................
    pt  Um sermão! Certo. Da próxima eu resumo em três palavras, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.standing.incident.followup.brush/2
    en  Well, that's my civic duty discharged and unappreciated.
    >>  ............................................
    pt  Bom, meu dever cívico está cumprido e não apreciado.
    >>  ............................................
  upbeat.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Ha. Fine. Next time it's just 'fine' and you can work out the rest.
    >>  ............................................
    pt  ...Ha. Tudo bem. Da próxima é só 'tudo bem' e você descobre o resto.
    >>  ............................................
  witty.dialogue.conversations.standing.incident.followup.brush/1
    en  A lecture! Right. I'll shorten it to three words next time, %1$s.
    >>  ............................................
    pt  Um sermão! Certo. Da próxima eu resumo em três palavras, %1$s.
    >>  ............................................
  witty.dialogue.conversations.standing.incident.followup.brush/2
    en  Well, that's my civic duty discharged and unappreciated.
    >>  ............................................
    pt  Bom, meu dever cívico está cumprido e não apreciado.
    >>  ............................................
  witty.dialogue.conversations.standing.incident.followup.brush/3
    en  ...Ha. Fine. Next time it's just 'fine' and you can work out the rest.
    >>  ............................................
    pt  ...Ha. Tudo bem. Da próxima é só 'tudo bem' e você descobre o resto.
    >>  ............................................
```

</details>


### Button `back` — "I'll think on it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.incident.deed`, `standing.amends.done`, `standing.incident.deny` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.followup.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.incident.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.incident.followup.back   [17 chars]
    en  I'll think on it.
    >>  ............................................
    pt  Vou pensar nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.incident.followup.back
WHO    VILLAGER — what the player reads after pressing "I'll think on it."
       spoken on: conversations.topic.standing.incident.followup, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.incident.followup.back`: the villager accepts. Subject `standing.incident`, polarity `mixed`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.incident.followup.back/1   [26 chars]
    en  Do that. There's time yet.
    >>  ............................................
    pt  Pense. Ainda dá tempo.
    >>  ............................................
  dialogue.conversations.standing.incident.followup.back/2   [24 chars]
    en  It is. Off you go, %1$s.
    >>  ............................................
    pt  É sim. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.standing.incident.followup.back/3   [12 chars]
    en  Away you go.
    >>  ............................................
    pt  Pode seguir.
    >>  ............................................
```

---


## `conversations.topic.standing.incident.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.trouble` — e.g. "You want it honest, %1$s? There's a matter people haven't let go of. It sits between you and this village."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.incident.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.incident.respond   [42 chars]
    en  There's a matter people haven't let go of.
    >>  ............................................
    pt  Tem um assunto que as pessoas não largaram.
    >>  ............................................
```


### Button `ask_deed` — "What is it they haven't let go of?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.incident.open` · offered only once the villager has actually said `incident:active`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.incident.deed` — accepted phrasings: "what is it they have not let go of"; "what matter"; "what is it they think happened"
  - the message must contain one of: `matter`, `happened`
  - scored words: `let`(0.6), `matter`(1.5), `happened`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.respond.ask_deed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.incident.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.incident.respond.ask_deed   [34 chars]
    en  What is it they haven't let go of?
    >>  ............................................
    pt  O que exatamente eles não largaram?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.incident.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I didn't ask for a lecture." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.incident.deed
WHO    VILLAGER — what the player reads after pressing "What is it they haven't let go of?"
       spoken on: conversations.topic.standing.incident.respond, button `ask_deed`
       leaves the player on: conversations.topic.standing.incident.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = reputation_recent_deed
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.incident.deed`: the villager reports. Subject `standing.incident`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `standing:bad`, `incident:active`, `deed:named` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, dismissal, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.incident.deed/1   [56 chars]
    en  %2$s. That's the matter, and nobody's called it settled.
    >>  ............................................
    pt  %2$s. É esse o assunto, e ninguém disse que está resolvido.
    >>  ............................................
  dialogue.conversations.standing.incident.deed/2   [55 chars]
    en  It's %2$s, %1$s. You'll not have needed me to tell you.
    >>  ............................................
    pt  É %2$s, %1$s. Você não precisava que eu dissesse.
    >>  ............................................
  dialogue.conversations.standing.incident.deed/3   [53 chars]
    en  The word on it is %2$s. Said quietly, but said often.
    >>  ............................................
    pt  O que se fala é %2$s. Baixinho, mas com frequência.
    >>  ............................................
```


### Button `amends` — "How could I make things right?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `standing.incident.open` · offered only once the villager has actually said `incident:active`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.amends.done` — accepted phrasings: "how could i make things right"; "how do i make amends"; "what would settle it"
  - the message must contain one of: `right`, `amends`, `settle`
  - scored words: `right`(1.5), `amends`(1.5), `settle`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.respond.amends
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.incident.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.incident.respond.amends   [30 chars]
    en  How could I make things right?
    >>  ............................................
    pt  Como eu poderia consertar isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.amends`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2  _(recorded under topic `standing.amends.done`)_
- Does: session `turn`
- Does: `conversations_reputation_signal` = {"incident": "mcareputation:public_apology", "visibility": "village", "decision": "standing.amends.public_apology"}
- Then opens: `conversations.topic.standing.incident.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I didn't ask for a lecture." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.amends.done
WHO    VILLAGER — what the player reads after pressing "How could I make things right?"
       spoken on: conversations.topic.standing.incident.respond, button `amends`
       leaves the player on: conversations.topic.standing.incident.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.amends.done`: the villager accepts. Subject `standing.amends`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `standing:bad`, `incident:active`, `player:apologized_publicly` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, dismissal, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.amends.done/1   [94 chars]
    en  Saying it where people can hear — that's a start, %1$s. I'll make sure it's known you said it.
    >>  ............................................
    pt  Dizer isso onde as pessoas ouçam — já é um começo, %1$s. Vou garantir que saibam que você disse.
    >>  ............................................
  dialogue.conversations.standing.amends.done/2   [89 chars]
    en  That took something. Words don't undo it, but they open the door. Folk will hear of this.
    >>  ............................................
    pt  Isso custou algo. Palavras não desfazem, mas abrem a porta. O povo vai ficar sabendo.
    >>  ............................................
  dialogue.conversations.standing.amends.done/3   [90 chars]
    en  All right. An apology, given in the open. It won't settle everything, %1$s, but it counts.
    >>  ............................................
    pt  Está bem. Um pedido de desculpas, feito em público. Não resolve tudo, %1$s, mas conta.
    >>  ............................................
```


### Button `deny` — "It isn't what they say it is."

*stance family `respectful_disagreement` · tone `blunt` · outcome `resisted` · answers the beat(s) `standing.incident.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.incident.deny` — accepted phrasings: "it is not what they say it is"; "they have the story wrong"; "that is not what happened"
  - the message must contain one of: `wrong`, `story`
  - scored words: `say`(0.6), `wrong`(1.5), `story`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.respond.deny
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.incident.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.incident.respond.deny   [29 chars]
    en  It isn't what they say it is.
    >>  ............................................
    pt  Não é o que dizem que é.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.incident.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "I didn't ask for a lecture." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.incident.deny
WHO    VILLAGER — what the player reads after pressing "It isn't what they say it is."
       spoken on: conversations.topic.standing.incident.respond, button `deny`
       leaves the player on: conversations.topic.standing.incident.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.incident.deny`: the villager resists. Subject `standing.incident`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `standing:bad`, `incident:active` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, dismissal, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.incident.deny/1   [76 chars]
    en  It rarely is. That's never yet stopped a village from settling on a version.
    >>  ............................................
    pt  Raramente é. Isso nunca impediu um vilarejo de fixar uma versão.
    >>  ............................................
  dialogue.conversations.standing.incident.deny/2   [69 chars]
    en  Then say so where they can hear it, %1$s. Telling me changes nothing.
    >>  ............................................
    pt  Então diga onde eles possam ouvir, %1$s. Me dizer não muda nada.
    >>  ............................................
  dialogue.conversations.standing.incident.deny/3   [76 chars]
    en  Possibly. But nobody's asked for your telling of it, and that's the trouble.
    >>  ............................................
    pt  Possivelmente. Mas ninguém pediu a sua versão, e é esse o problema.
    >>  ............................................
```


### Button `dismiss` — "Their opinions are their problem."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `standing.incident.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.incident.dismissed` — accepted phrasings: "their opinions are their problem"; "they can keep their grudge"; "let them think what they like"
  - the message must contain one of: `opinions`, `grudge`, `keep`
  - scored words: `opinions`(1.5), `grudge`(1.5), `keep`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.incident.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.incident.respond.dismiss   [33 chars]
    en  Their opinions are their problem.
    >>  ............................................
    pt  A opinião deles é problema deles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.incident.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `standing.incident.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.incident.dismissed
WHO    VILLAGER — what the player reads after pressing "Their opinions are their problem."
       spoken on: conversations.topic.standing.incident.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.incident.dismissed`: the villager resists. Subject `standing.incident`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.incident.dismissed/1   [72 chars]
    en  Suit yourself, %1$s. But a village's memory is longer than its patience.
    >>  ............................................
    pt  Como quiser, %1$s. Mas a memória de um vilarejo é mais longa que a paciência dele.
    >>  ............................................
  dialogue.conversations.standing.incident.dismissed/2   [72 chars]
    en  If you say so. It'll be waiting for you the next time you need a favour.
    >>  ............................................
    pt  Se você diz. Vai estar te esperando na próxima vez que precisar de um favor.
    >>  ............................................
  dialogue.conversations.standing.incident.dismissed/3   [60 chars]
    en  Mm. You asked, mind. Don't ask if you don't want to hear it.
    >>  ............................................
    pt  Mm. Você que perguntou. Não pergunte se não quer ouvir.
    >>  ............................................
```


### Button `back` — "Enough about me."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.incident.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.incident.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.incident.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.incident.respond.back   [16 chars]
    en  Enough about me.
    >>  ............................................
    pt  Chega de falar de mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.incident.back
WHO    VILLAGER — what the player reads after pressing "Enough about me."
       spoken on: conversations.topic.standing.incident.respond, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.incident.back`: the villager accepts. Subject `standing.incident`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.incident.back/1   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
  dialogue.conversations.standing.incident.back/2   [23 chars]
    en  Quite. What else, then?
    >>  ............................................
    pt  Exato. O que mais, então?
    >>  ............................................
  dialogue.conversations.standing.incident.back/3   [25 chars]
    en  I'll see you about, %1$s.
    >>  ............................................
    pt  A gente se vê por aí, %1$s.
    >>  ............................................
```

---


## `conversations.topic.standing.mixed.followup`

**Reached from 3 route(s):** `conversations.topic.standing.mixed.respond` / `which_half`; `conversations.topic.standing.mixed.respond` / `what_held`; `conversations.topic.standing.mixed.respond` / `let_them`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.mixed.let_them` — e.g. "They will, with or without your permission. It's the village's favourite pastime."
- `conversations.standing.mixed.what_held` — e.g. "The one bad day. It's the one they tell, because it's the one with a shape to it."
- `conversations.standing.mixed.which_half` — e.g. "The forgiving one, though I got there later than I'd like to admit."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.mixed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.mixed.followup   [25 chars]
    en  That's both halves of it.
    >>  ............................................
    pt  São as duas metades.
    >>  ............................................
```


### Button `both_sides` — "I'd rather have both halves than a comfortable one."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `standing.mixed.which_half`, `standing.mixed.what_held`, `standing.mixed.let_them`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.mixed.followup.both` — accepted phrasings: "i would rather have both halves"; "give me both sides of it"; "i want the uncomfortable half too"
  - the message must contain one of: `halves`, `comfortable`
  - scored words: `halves`(1.5), `comfortable`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.followup.both_sides
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.mixed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.mixed.followup.both_sides   [51 chars]
    en  I'd rather have both halves than a comfortable one.
    >>  ............................................
    pt  Prefiro as duas metades a uma confortável.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.mixed.followup.both`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `standing.mixed.followup.both`)_
- Does: arc `standing` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed.followup.both
WHO    VILLAGER — what the player reads after pressing "I'd rather have both halves than a comfortable one."
       spoken on: conversations.topic.standing.mixed.followup, button `both_sides`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.followup.both`: the villager accepts. Subject `standing.mixed`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed.followup.both/1   [76 chars]
    en  That's why I gave you both. A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso te dei as duas. Uma resposta confortável te custaria um ano.
    >>  ............................................
  dialogue.conversations.standing.mixed.followup.both/2   [72 chars]
    en  Then you'll get the truth from me every time, which not everybody wants.
    >>  ............................................
    pt  Então você vai receber a verdade de mim sempre, o que nem todos querem.
    >>  ............................................
  dialogue.conversations.standing.mixed.followup.both/3   [69 chars]
    en  Good. The ones who only want the warm half never mend anything, %1$s.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. The comfortable answer was on my tongue and I swallowed it.
    >>  ............................................
    pt  Por isso te dei as duas. A resposta confortável estava na ponta da língua e eu engoli.
    >>  ............................................
  anxious.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, and I hope you still want that in a year.
    >>  ............................................
    pt  Então você recebe a verdade sempre, e espero que ainda queira daqui a um ano.
    >>  ............................................
  anxious.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything, and I've been one.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada, e eu já fui assim.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. Comfortable answers have cost people here whole years.
    >>  ............................................
    pt  Por isso te dei as duas. Respostas confortáveis já custaram anos inteiros a gente daqui.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time; it's the only thing I still do quickly.
    >>  ............................................
    pt  Então você recebe a verdade sempre; é a única coisa que ainda faço rápido.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. In thirty years the ones who wanted only the warm half mended nothing at all.
    >>  ............................................
    pt  Bom. Em trinta anos, quem só queria a metade morna não consertou nada.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso te dei as duas. Uma resposta confortável te custaria um ano.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, which not everybody wants.
    >>  ............................................
    pt  Então você vai receber a verdade de mim sempre, o que nem todos querem.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso te dei as duas. Uma resposta confortável te custaria um ano.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, which not everybody wants.
    >>  ............................................
    pt  Então você vai receber a verdade de mim sempre, o que nem todos querem.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both, %1$s. A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso te dei as duas, %1$s. Uma resposta confortável te custaria um ano.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time. Not everybody wants that from me.
    >>  ............................................
    pt  Então você recebe a verdade de mim sempre. Nem todos querem isso de mim.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything, %1$s.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both, %1$s. A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso te dei as duas, %1$s. Uma resposta confortável te custaria um ano.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time. Not everybody wants that from me.
    >>  ............................................
    pt  Então você recebe a verdade de mim sempre. Nem todos querem isso de mim.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything, %1$s.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both, %1$s. A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso te dei as duas, %1$s. Uma resposta confortável te custaria um ano.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time. Not everybody wants that from me.
    >>  ............................................
    pt  Então você recebe a verdade de mim sempre. Nem todos querem isso de mim.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything, %1$s.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. The comfortable answer was on my tongue and I swallowed it.
    >>  ............................................
    pt  Por isso te dei as duas. A resposta confortável estava na ponta da língua e eu engoli.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, and I hope you still want that in a year.
    >>  ............................................
    pt  Então você recebe a verdade sempre, e espero que ainda queira daqui a um ano.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything, and I've been one.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada, e eu já fui assim.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso te dei as duas. Uma resposta confortável te custaria um ano.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, which not everybody wants.
    >>  ............................................
    pt  Então você vai receber a verdade de mim sempre, o que nem todos querem.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso te dei as duas. Uma resposta confortável te custaria um ano.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, which not everybody wants.
    >>  ............................................
    pt  Então você vai receber a verdade de mim sempre, o que nem todos querem.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both.
    >>  ............................................
    pt  Por isso te dei as duas.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.followup.both/2
    en  You'll get the truth from me. Every time.
    >>  ............................................
    pt  Você recebe a verdade de mim. Sempre.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.followup.both/3
    en  The warm half alone mends nothing.
    >>  ............................................
    pt  Só a metade morna não conserta nada.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. Comfortable answers have cost people here whole years.
    >>  ............................................
    pt  Por isso te dei as duas. Respostas confortáveis já custaram anos inteiros a gente daqui.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time; it's the only thing I still do quickly.
    >>  ............................................
    pt  Então você recebe a verdade sempre; é a única coisa que ainda faço rápido.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. In thirty years the ones who wanted only the warm half mended nothing at all.
    >>  ............................................
    pt  Bom. Em trinta anos, quem só queria a metade morna não consertou nada.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both.
    >>  ............................................
    pt  Por isso te dei as duas.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.followup.both/2
    en  You'll get the truth from me. Every time.
    >>  ............................................
    pt  Você recebe a verdade de mim. Sempre.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.followup.both/3
    en  The warm half alone mends nothing.
    >>  ............................................
    pt  Só a metade morna não conserta nada.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. Comfortable answers have cost people here whole years.
    >>  ............................................
    pt  Por isso te dei as duas. Respostas confortáveis já custaram anos inteiros a gente daqui.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time; it's the only thing I still do quickly.
    >>  ............................................
    pt  Então você recebe a verdade sempre; é a única coisa que ainda faço rápido.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. In thirty years the ones who wanted only the warm half mended nothing at all.
    >>  ............................................
    pt  Bom. Em trinta anos, quem só queria a metade morna não consertou nada.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.followup.both/1
    en  That's exactly why I gave you both! A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso mesmo te dei as duas! Uma resposta confortável te custaria um ano.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, which not everybody enjoys.
    >>  ............................................
    pt  Então você recebe a verdade de mim sempre, o que nem todos apreciam.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.followup.both/3
    en  Good! The ones who only want the warm half never mend a single thing.
    >>  ............................................
    pt  Bom! Quem só quer a metade morna nunca conserta coisa nenhuma.
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.followup.both/1
    en  That's exactly why I gave you both! A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso mesmo te dei as duas! Uma resposta confortável te custaria um ano.
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, which not everybody enjoys.
    >>  ............................................
    pt  Então você recebe a verdade de mim sempre, o que nem todos apreciam.
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.followup.both/3
    en  Good! The ones who only want the warm half never mend a single thing.
    >>  ............................................
    pt  Bom! Quem só quer a metade morna nunca conserta coisa nenhuma.
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. Comfortable answers have cost people here whole years.
    >>  ............................................
    pt  Por isso te dei as duas. Respostas confortáveis já custaram anos inteiros a gente daqui.
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time; it's the only thing I still do quickly.
    >>  ............................................
    pt  Então você recebe a verdade sempre; é a única coisa que ainda faço rápido.
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. In thirty years the ones who wanted only the warm half mended nothing at all.
    >>  ............................................
    pt  Bom. Em trinta anos, quem só queria a metade morna não consertou nada.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both. The comfortable answer was on my tongue and I swallowed it.
    >>  ............................................
    pt  Por isso te dei as duas. A resposta confortável estava na ponta da língua e eu engoli.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, and I hope you still want that in a year.
    >>  ............................................
    pt  Então você recebe a verdade sempre, e espero que ainda queira daqui a um ano.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.followup.both/3
    en  Good. The ones who only want the warm half never mend anything, and I've been one.
    >>  ............................................
    pt  Bom. Quem só quer a metade morna nunca conserta nada, e eu já fui assim.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.followup.both/1
    en  That's why I gave you both.
    >>  ............................................
    pt  Por isso te dei as duas.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.followup.both/2
    en  You'll get the truth from me. Every time.
    >>  ............................................
    pt  Você recebe a verdade de mim. Sempre.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.followup.both/3
    en  The warm half alone mends nothing.
    >>  ............................................
    pt  Só a metade morna não conserta nada.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.followup.both/1
    en  That's exactly why I gave you both! A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso mesmo te dei as duas! Uma resposta confortável te custaria um ano.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, which not everybody enjoys.
    >>  ............................................
    pt  Então você recebe a verdade de mim sempre, o que nem todos apreciam.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.followup.both/3
    en  Good! The ones who only want the warm half never mend a single thing.
    >>  ............................................
    pt  Bom! Quem só quer a metade morna nunca conserta coisa nenhuma.
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.followup.both/1
    en  That's exactly why I gave you both! A comfortable answer would have cost you a year.
    >>  ............................................
    pt  Por isso mesmo te dei as duas! Uma resposta confortável te custaria um ano.
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.followup.both/2
    en  Then you'll get the truth from me every time, which not everybody enjoys.
    >>  ............................................
    pt  Então você recebe a verdade de mim sempre, o que nem todos apreciam.
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.followup.both/3
    en  Good! The ones who only want the warm half never mend a single thing.
    >>  ............................................
    pt  Bom! Quem só quer a metade morna nunca conserta coisa nenhuma.
    >>  ............................................
```

</details>


### Button `one_answer` — "I'll keep at it until it's one answer."

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.mixed.which_half`, `standing.mixed.what_held`, `standing.mixed.let_them`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.mixed.followup.keep` — accepted phrasings: "i will keep at it until it is one answer"; "i will keep working at it"; "then it is on me to change it"
  - the message must contain one of: `until`
  - scored words: `until`(1.2), `keep`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.followup.one_answer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.mixed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.mixed.followup.one_answer   [38 chars]
    en  I'll keep at it until it's one answer.
    >>  ............................................
    pt  Vou insistir até virar uma resposta só.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.mixed.followup.keep`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +1  _(recorded under topic `standing.mixed.followup.keep`)_
- Does: arc `standing` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed.followup.keep
WHO    VILLAGER — what the player reads after pressing "I'll keep at it until it's one answer."
       spoken on: conversations.topic.standing.mixed.followup, button `one_answer`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.followup.keep`: the villager explains. Subject `standing.mixed`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed.followup.keep/1   [78 chars]
    en  That's the work, and it's slow. Years, not weeks, and no thanks along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas, e sem agradecimentos no caminho.
    >>  ............................................
  dialogue.conversations.standing.mixed.followup.keep/2   [69 chars]
    en  Then start with the ones who won't look at you. The rest follow them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto segue.
    >>  ............................................
  dialogue.conversations.standing.mixed.followup.keep/3   [71 chars]
    en  It can be done. I've watched it done twice here and both took patience.
    >>  ............................................
    pt  Dá pra fazer. Vi acontecer duas vezes aqui e as duas exigiram paciência.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years. I'd rather warn you than watch you give up.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos. Prefiro te avisar a ver você desistir.
    >>  ............................................
  anxious.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. That's the part that will hurt.
    >>  ............................................
    pt  Então comece pelos que não te olham. É essa a parte que vai doer.
    >>  ............................................
  anxious.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it twice, and both of them nearly stopped halfway.
    >>  ............................................
    pt  Dá pra fazer. Vi duas vezes, e as duas quase pararam no meio.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years, not weeks; I've watched the whole of one.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas; vi um inteiro acontecer.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. In thirty years that has never changed.
    >>  ............................................
    pt  Então comece pelos que não te olham. Em trinta anos isso nunca mudou.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. Twice here, and both took more patience than either of them had.
    >>  ............................................
    pt  Dá pra fazer. Duas vezes aqui, e as duas exigiram mais paciência do que tinham.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years, not weeks, and no thanks along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas, e sem agradecimentos no caminho.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. The rest follow them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto segue.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it done twice here and both took patience.
    >>  ............................................
    pt  Dá pra fazer. Vi acontecer duas vezes aqui e as duas exigiram paciência.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years, not weeks, and no thanks along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas, e sem agradecimentos no caminho.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. The rest follow them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto segue.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it done twice here and both took patience.
    >>  ............................................
    pt  Dá pra fazer. Vi acontecer duas vezes aqui e as duas exigiram paciência.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, %1$s, and it's slow. Years, not weeks, and no thanks along the way.
    >>  ............................................
    pt  É esse o trabalho, %1$s, e é lento. Anos, não semanas, e sem agradecimentos.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. I'll tell you who they are.
    >>  ............................................
    pt  Então comece pelos que não te olham. Eu te digo quem são.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it twice, and I'd like to watch it a third time.
    >>  ............................................
    pt  Dá pra fazer. Vi duas vezes, e eu gostaria de ver uma terceira.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, %1$s, and it's slow. Years, not weeks, and no thanks along the way.
    >>  ............................................
    pt  É esse o trabalho, %1$s, e é lento. Anos, não semanas, e sem agradecimentos.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. I'll tell you who they are.
    >>  ............................................
    pt  Então comece pelos que não te olham. Eu te digo quem são.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it twice, and I'd like to watch it a third time.
    >>  ............................................
    pt  Dá pra fazer. Vi duas vezes, e eu gostaria de ver uma terceira.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, %1$s, and it's slow. Years, not weeks, and no thanks along the way.
    >>  ............................................
    pt  É esse o trabalho, %1$s, e é lento. Anos, não semanas, e sem agradecimentos.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. I'll tell you who they are.
    >>  ............................................
    pt  Então comece pelos que não te olham. Eu te digo quem são.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it twice, and I'd like to watch it a third time.
    >>  ............................................
    pt  Dá pra fazer. Vi duas vezes, e eu gostaria de ver uma terceira.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years. I'd rather warn you than watch you give up.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos. Prefiro te avisar a ver você desistir.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. That's the part that will hurt.
    >>  ............................................
    pt  Então comece pelos que não te olham. É essa a parte que vai doer.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it twice, and both of them nearly stopped halfway.
    >>  ............................................
    pt  Dá pra fazer. Vi duas vezes, e as duas quase pararam no meio.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years, not weeks, and no thanks along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas, e sem agradecimentos no caminho.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. The rest follow them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto segue.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it done twice here and both took patience.
    >>  ............................................
    pt  Dá pra fazer. Vi acontecer duas vezes aqui e as duas exigiram paciência.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years, not weeks, and no thanks along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas, e sem agradecimentos no caminho.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. The rest follow them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto segue.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it done twice here and both took patience.
    >>  ............................................
    pt  Dá pra fazer. Vi acontecer duas vezes aqui e as duas exigiram paciência.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work. Slow. Years.
    >>  ............................................
    pt  É esse o trabalho. Lento. Anos.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.followup.keep/2
    en  Start with the ones who won't look at you.
    >>  ............................................
    pt  Comece pelos que não te olham.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. Twice, that I've seen.
    >>  ............................................
    pt  Dá pra fazer. Duas vezes, que eu vi.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years, not weeks; I've watched the whole of one.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas; vi um inteiro acontecer.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. In thirty years that has never changed.
    >>  ............................................
    pt  Então comece pelos que não te olham. Em trinta anos isso nunca mudou.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. Twice here, and both took more patience than either of them had.
    >>  ............................................
    pt  Dá pra fazer. Duas vezes aqui, e as duas exigiram mais paciência do que tinham.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work. Slow. Years.
    >>  ............................................
    pt  É esse o trabalho. Lento. Anos.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.followup.keep/2
    en  Start with the ones who won't look at you.
    >>  ............................................
    pt  Comece pelos que não te olham.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. Twice, that I've seen.
    >>  ............................................
    pt  Dá pra fazer. Duas vezes, que eu vi.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years, not weeks; I've watched the whole of one.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas; vi um inteiro acontecer.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. In thirty years that has never changed.
    >>  ............................................
    pt  Então comece pelos que não te olham. Em trinta anos isso nunca mudou.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. Twice here, and both took more patience than either of them had.
    >>  ............................................
    pt  Dá pra fazer. Duas vezes aqui, e as duas exigiram mais paciência do que tinham.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow! Years, not weeks, and not one thank you along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento! Anos, não semanas, e nem um obrigado no caminho.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. The rest are followers, all of them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto é seguidor, todos eles.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done! I've watched it done twice here, and both took a great deal of patience.
    >>  ............................................
    pt  Dá pra fazer! Vi acontecer duas vezes aqui, e as duas exigiram muita paciência.
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow! Years, not weeks, and not one thank you along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento! Anos, não semanas, e nem um obrigado no caminho.
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. The rest are followers, all of them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto é seguidor, todos eles.
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done! I've watched it done twice here, and both took a great deal of patience.
    >>  ............................................
    pt  Dá pra fazer! Vi acontecer duas vezes aqui, e as duas exigiram muita paciência.
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years, not weeks; I've watched the whole of one.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos, não semanas; vi um inteiro acontecer.
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. In thirty years that has never changed.
    >>  ............................................
    pt  Então comece pelos que não te olham. Em trinta anos isso nunca mudou.
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. Twice here, and both took more patience than either of them had.
    >>  ............................................
    pt  Dá pra fazer. Duas vezes aqui, e as duas exigiram mais paciência do que tinham.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow. Years. I'd rather warn you than watch you give up.
    >>  ............................................
    pt  É esse o trabalho, e é lento. Anos. Prefiro te avisar a ver você desistir.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. That's the part that will hurt.
    >>  ............................................
    pt  Então comece pelos que não te olham. É essa a parte que vai doer.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. I've watched it twice, and both of them nearly stopped halfway.
    >>  ............................................
    pt  Dá pra fazer. Vi duas vezes, e as duas quase pararam no meio.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work. Slow. Years.
    >>  ............................................
    pt  É esse o trabalho. Lento. Anos.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.followup.keep/2
    en  Start with the ones who won't look at you.
    >>  ............................................
    pt  Comece pelos que não te olham.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done. Twice, that I've seen.
    >>  ............................................
    pt  Dá pra fazer. Duas vezes, que eu vi.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow! Years, not weeks, and not one thank you along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento! Anos, não semanas, e nem um obrigado no caminho.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. The rest are followers, all of them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto é seguidor, todos eles.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done! I've watched it done twice here, and both took a great deal of patience.
    >>  ............................................
    pt  Dá pra fazer! Vi acontecer duas vezes aqui, e as duas exigiram muita paciência.
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.followup.keep/1
    en  That's the work, and it's slow! Years, not weeks, and not one thank you along the way.
    >>  ............................................
    pt  É esse o trabalho, e é lento! Anos, não semanas, e nem um obrigado no caminho.
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.followup.keep/2
    en  Then start with the ones who won't look at you. The rest are followers, all of them.
    >>  ............................................
    pt  Então comece pelos que não te olham. O resto é seguidor, todos eles.
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.followup.keep/3
    en  It can be done! I've watched it done twice here, and both took a great deal of patience.
    >>  ............................................
    pt  Dá pra fazer! Vi acontecer duas vezes aqui, e as duas exigiram muita paciência.
    >>  ............................................
```

</details>


### Button `back` — "Understood."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.mixed.which_half`, `standing.mixed.what_held`, `standing.mixed.let_them` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.followup.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.mixed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.mixed.followup.back   [11 chars]
    en  Understood.
    >>  ............................................
    pt  Entendido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed.followup.back
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.topic.standing.mixed.followup, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.followup.back`: the villager accepts. Subject `standing.mixed`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed.followup.back/1   [8 chars]
    en  Just so.
    >>  ............................................
    pt  Pois é.
    >>  ............................................
  dialogue.conversations.standing.mixed.followup.back/2   [6 chars]
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  dialogue.conversations.standing.mixed.followup.back/3   [10 chars]
    en  On you go.
    >>  ............................................
    pt  Siga em frente.
    >>  ............................................
```

---


## `conversations.topic.standing.mixed.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.mixed` — e.g. "You'll get two answers depending who you ask, %1$s. Half the village has let it go and half hasn't."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.mixed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.mixed.respond   [50 chars]
    en  You'll get two answers here depending who you ask.
    >>  ............................................
    pt  Aqui você vai receber duas respostas, dependendo de quem perguntar.
    >>  ............................................
```


### Button `which_half` — "Which half are you in?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.mixed.open` · offered only once the villager has actually said `standing:mixed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.mixed.which_half` — accepted phrasings: "which half are you in"; "which side are you on"; "and which half is that"
  - the message must contain one of: `half`
  - scored words: `half`(1.5), `side`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.respond.which_half
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.mixed.respond.which_half   [22 chars]
    en  Which half are you in?
    >>  ............................................
    pt  Em qual metade você está?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.mixed.followup`
- …where the player's next choices will be: "I'd rather have both halves than a comfortable one." | "I'll keep at it until it's one answer." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed.which_half
WHO    VILLAGER — what the player reads after pressing "Which half are you in?"
       spoken on: conversations.topic.standing.mixed.respond, button `which_half`
       leaves the player on: conversations.topic.standing.mixed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.which_half`: the villager discloses. Subject `standing.mixed`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed.which_half/1   [67 chars]
    en  The forgiving one, though I got there later than I'd like to admit.
    >>  ............................................
    pt  A que perdoa, embora eu tenha chegado lá mais tarde do que gostaria de admitir.
    >>  ............................................
  dialogue.conversations.standing.mixed.which_half/2   [75 chars]
    en  Neither, honestly. I've been waiting to see which way you go before I pick.
    >>  ............................................
    pt  Nenhuma, sinceramente. Estou esperando pra ver que rumo você toma.
    >>  ............................................
  dialogue.conversations.standing.mixed.which_half/3   [72 chars]
    en  Yours, %1$s. That's why you're getting this instead of a polite nothing.
    >>  ............................................
    pt  A sua, %1$s. Por isso você está recebendo isto e não uma educada nada.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one. I was in the other half once and I'd rather you never knew how long.
    >>  ............................................
    pt  A que perdoa. Já estive na outra metade e prefiro que você nunca saiba por quanto tempo.
    >>  ............................................
  anxious.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and it troubles me. I don't like being the sort who waits to see.
    >>  ............................................
    pt  Nenhuma, e isso me incomoda. Não gosto de ser do tipo que espera pra ver.
    >>  ............................................
  anxious.dialogue.conversations.standing.mixed.which_half/3
    en  Yours, %1$s. Saying it out loud costs me something, so take it as meant.
    >>  ............................................
    pt  A sua, %1$s. Dizer em voz alta me custa algo, então leve a sério.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one. Long enough here to know that most of these splits close by themselves.
    >>  ............................................
    pt  A que perdoa. Tempo suficiente aqui pra saber que essas divisões se fecham sozinhas.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and there's no hurry in it. A year settles more arguments than a speech does.
    >>  ............................................
    pt  Nenhuma, e não há pressa. Um ano resolve mais discussões que um discurso.
    >>  ............................................
  athletic.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. I've picked wrong before and I've learned to pick on what people do twice.
    >>  ............................................
    pt  A sua. Já escolhi errado e aprendi a escolher pelo que as pessoas fazem duas vezes.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one, though I got there later than I'd care to admit.
    >>  ............................................
    pt  A que perdoa, embora eu tenha chegado lá mais tarde do que gostaria de admitir.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.which_half/2
    en  Neither. I've been waiting to see which way you go before I pick.
    >>  ............................................
    pt  Nenhuma. Estou esperando ver que rumo você toma antes de escolher.
    >>  ............................................
  confident.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. That's why you're getting this instead of a polite nothing.
    >>  ............................................
    pt  A sua. Por isso está recebendo isto e não uma educada nada.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one, though I got there later than I'd care to admit.
    >>  ............................................
    pt  A que perdoa, embora eu tenha chegado lá mais tarde do que gostaria de admitir.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.which_half/2
    en  Neither. I've been waiting to see which way you go before I pick.
    >>  ............................................
    pt  Nenhuma. Estou esperando ver que rumo você toma antes de escolher.
    >>  ............................................
  crabby.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. That's why you're getting this instead of a polite nothing.
    >>  ............................................
    pt  A sua. Por isso está recebendo isto e não uma educada nada.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one, %1$s. I got there late and I'm sorry it took me that long.
    >>  ............................................
    pt  A que perdoa, %1$s. Cheguei tarde e sinto muito ter demorado tanto.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.which_half/2
    en  Neither yet. I'd rather choose you properly than choose you quickly.
    >>  ............................................
    pt  Nenhuma ainda. Prefiro te escolher direito a te escolher rápido.
    >>  ............................................
  extroverted.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. I'd have thought that was plain by now.
    >>  ............................................
    pt  A sua. Eu achei que já estivesse claro.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one, %1$s. I got there late and I'm sorry it took me that long.
    >>  ............................................
    pt  A que perdoa, %1$s. Cheguei tarde e sinto muito ter demorado tanto.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.which_half/2
    en  Neither yet. I'd rather choose you properly than choose you quickly.
    >>  ............................................
    pt  Nenhuma ainda. Prefiro te escolher direito a te escolher rápido.
    >>  ............................................
  flirty.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. I'd have thought that was plain by now.
    >>  ............................................
    pt  A sua. Eu achei que já estivesse claro.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one, %1$s. I got there late and I'm sorry it took me that long.
    >>  ............................................
    pt  A que perdoa, %1$s. Cheguei tarde e sinto muito ter demorado tanto.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.which_half/2
    en  Neither yet. I'd rather choose you properly than choose you quickly.
    >>  ............................................
    pt  Nenhuma ainda. Prefiro te escolher direito a te escolher rápido.
    >>  ............................................
  friendly.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. I'd have thought that was plain by now.
    >>  ............................................
    pt  A sua. Eu achei que já estivesse claro.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one. I was in the other half once and I'd rather you never knew how long.
    >>  ............................................
    pt  A que perdoa. Já estive na outra metade e prefiro que você nunca saiba por quanto tempo.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and it troubles me. I don't like being the sort who waits to see.
    >>  ............................................
    pt  Nenhuma, e isso me incomoda. Não gosto de ser do tipo que espera pra ver.
    >>  ............................................
  gloomy.dialogue.conversations.standing.mixed.which_half/3
    en  Yours, %1$s. Saying it out loud costs me something, so take it as meant.
    >>  ............................................
    pt  A sua, %1$s. Dizer em voz alta me custa algo, então leve a sério.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one, though I got there later than I'd care to admit.
    >>  ............................................
    pt  A que perdoa, embora eu tenha chegado lá mais tarde do que gostaria de admitir.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.which_half/2
    en  Neither. I've been waiting to see which way you go before I pick.
    >>  ............................................
    pt  Nenhuma. Estou esperando ver que rumo você toma antes de escolher.
    >>  ............................................
  greedy.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. That's why you're getting this instead of a polite nothing.
    >>  ............................................
    pt  A sua. Por isso está recebendo isto e não uma educada nada.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one, though I got there later than I'd care to admit.
    >>  ............................................
    pt  A que perdoa, embora eu tenha chegado lá mais tarde do que gostaria de admitir.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.which_half/2
    en  Neither. I've been waiting to see which way you go before I pick.
    >>  ............................................
    pt  Nenhuma. Estou esperando ver que rumo você toma antes de escolher.
    >>  ............................................
  grumpy.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. That's why you're getting this instead of a polite nothing.
    >>  ............................................
    pt  A sua. Por isso está recebendo isto e não uma educada nada.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one.
    >>  ............................................
    pt  A que perdoa.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.which_half/2
    en  Neither. Not yet.
    >>  ............................................
    pt  Nenhuma. Ainda não.
    >>  ............................................
  introverted.dialogue.conversations.standing.mixed.which_half/3
    en  Yours.
    >>  ............................................
    pt  A sua.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one. Long enough here to know that most of these splits close by themselves.
    >>  ............................................
    pt  A que perdoa. Tempo suficiente aqui pra saber que essas divisões se fecham sozinhas.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and there's no hurry in it. A year settles more arguments than a speech does.
    >>  ............................................
    pt  Nenhuma, e não há pressa. Um ano resolve mais discussões que um discurso.
    >>  ............................................
  lazy.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. I've picked wrong before and I've learned to pick on what people do twice.
    >>  ............................................
    pt  A sua. Já escolhi errado e aprendi a escolher pelo que as pessoas fazem duas vezes.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one.
    >>  ............................................
    pt  A que perdoa.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.which_half/2
    en  Neither. Not yet.
    >>  ............................................
    pt  Nenhuma. Ainda não.
    >>  ............................................
  odd.dialogue.conversations.standing.mixed.which_half/3
    en  Yours.
    >>  ............................................
    pt  A sua.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one. Long enough here to know that most of these splits close by themselves.
    >>  ............................................
    pt  A que perdoa. Tempo suficiente aqui pra saber que essas divisões se fecham sozinhas.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and there's no hurry in it. A year settles more arguments than a speech does.
    >>  ............................................
    pt  Nenhuma, e não há pressa. Um ano resolve mais discussões que um discurso.
    >>  ............................................
  peaceful.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. I've picked wrong before and I've learned to pick on what people do twice.
    >>  ............................................
    pt  A sua. Já escolhi errado e aprendi a escolher pelo que as pessoas fazem duas vezes.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one! Took me a while, mind, and I'm not proud of the while.
    >>  ............................................
    pt  A que perdoa! Demorei, veja bem, e não me orgulho da demora.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and I'm enjoying the suspense far more than I ought to be.
    >>  ............................................
    pt  Nenhuma, e estou gostando do suspense muito mais do que deveria.
    >>  ............................................
  peppy.dialogue.conversations.standing.mixed.which_half/3
    en  Yours, obviously. Why else would I be stood here telling you the awkward part?
    >>  ............................................
    pt  A sua, claro. Por que mais eu estaria aqui te contando a parte constrangedora?
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one! Took me a while, mind, and I'm not proud of the while.
    >>  ............................................
    pt  A que perdoa! Demorei, veja bem, e não me orgulho da demora.
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and I'm enjoying the suspense far more than I ought to be.
    >>  ............................................
    pt  Nenhuma, e estou gostando do suspense muito mais do que deveria.
    >>  ............................................
  playful.dialogue.conversations.standing.mixed.which_half/3
    en  Yours, obviously. Why else would I be stood here telling you the awkward part?
    >>  ............................................
    pt  A sua, claro. Por que mais eu estaria aqui te contando a parte constrangedora?
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one. Long enough here to know that most of these splits close by themselves.
    >>  ............................................
    pt  A que perdoa. Tempo suficiente aqui pra saber que essas divisões se fecham sozinhas.
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and there's no hurry in it. A year settles more arguments than a speech does.
    >>  ............................................
    pt  Nenhuma, e não há pressa. Um ano resolve mais discussões que um discurso.
    >>  ............................................
  relaxed.dialogue.conversations.standing.mixed.which_half/3
    en  Yours. I've picked wrong before and I've learned to pick on what people do twice.
    >>  ............................................
    pt  A sua. Já escolhi errado e aprendi a escolher pelo que as pessoas fazem duas vezes.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one. I was in the other half once and I'd rather you never knew how long.
    >>  ............................................
    pt  A que perdoa. Já estive na outra metade e prefiro que você nunca saiba por quanto tempo.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and it troubles me. I don't like being the sort who waits to see.
    >>  ............................................
    pt  Nenhuma, e isso me incomoda. Não gosto de ser do tipo que espera pra ver.
    >>  ............................................
  sensitive.dialogue.conversations.standing.mixed.which_half/3
    en  Yours, %1$s. Saying it out loud costs me something, so take it as meant.
    >>  ............................................
    pt  A sua, %1$s. Dizer em voz alta me custa algo, então leve a sério.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one.
    >>  ............................................
    pt  A que perdoa.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.which_half/2
    en  Neither. Not yet.
    >>  ............................................
    pt  Nenhuma. Ainda não.
    >>  ............................................
  shy.dialogue.conversations.standing.mixed.which_half/3
    en  Yours.
    >>  ............................................
    pt  A sua.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one! Took me a while, mind, and I'm not proud of the while.
    >>  ............................................
    pt  A que perdoa! Demorei, veja bem, e não me orgulho da demora.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and I'm enjoying the suspense far more than I ought to be.
    >>  ............................................
    pt  Nenhuma, e estou gostando do suspense muito mais do que deveria.
    >>  ............................................
  upbeat.dialogue.conversations.standing.mixed.which_half/3
    en  Yours, obviously. Why else would I be stood here telling you the awkward part?
    >>  ............................................
    pt  A sua, claro. Por que mais eu estaria aqui te contando a parte constrangedora?
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.which_half/1
    en  The forgiving one! Took me a while, mind, and I'm not proud of the while.
    >>  ............................................
    pt  A que perdoa! Demorei, veja bem, e não me orgulho da demora.
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.which_half/2
    en  Neither, and I'm enjoying the suspense far more than I ought to be.
    >>  ............................................
    pt  Nenhuma, e estou gostando do suspense muito mais do que deveria.
    >>  ............................................
  witty.dialogue.conversations.standing.mixed.which_half/3
    en  Yours, obviously. Why else would I be stood here telling you the awkward part?
    >>  ............................................
    pt  A sua, claro. Por que mais eu estaria aqui te contando a parte constrangedora?
    >>  ............................................
```

</details>


### Button `what_held` — "What are they still holding on to?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.mixed.open` · offered only once the villager has actually said `standing:mixed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.mixed.what_held` — accepted phrasings: "what are they still holding on to"; "what have they not forgiven"; "what is it they still remember"
  - the message must contain one of: `holding`, `forgiven`
  - scored words: `holding`(1.5), `forgiven`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.respond.what_held
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.mixed.respond.what_held   [34 chars]
    en  What are they still holding on to?
    >>  ............................................
    pt  O que eles ainda estão guardando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.mixed.followup`
- …where the player's next choices will be: "I'd rather have both halves than a comfortable one." | "I'll keep at it until it's one answer." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed.what_held
WHO    VILLAGER — what the player reads after pressing "What are they still holding on to?"
       spoken on: conversations.topic.standing.mixed.respond, button `what_held`
       leaves the player on: conversations.topic.standing.mixed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.what_held`: the villager reports. Subject `standing.mixed`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed.what_held/1   [81 chars]
    en  The one bad day. It's the one they tell, because it's the one with a shape to it.
    >>  ............................................
    pt  O dia ruim. É o que eles contam, porque é o que tem forma.
    >>  ............................................
  dialogue.conversations.standing.mixed.what_held/2   [97 chars]
    en  Nothing you could fix by asking. They want to see a second year of you, not hear about the first.
    >>  ............................................
    pt  Nada que você conserte perguntando. Querem ver um segundo ano, não ouvir sobre o primeiro.
    >>  ............................................
  dialogue.conversations.standing.mixed.what_held/3   [76 chars]
    en  That you arrived and things changed. Some people can't tell those two apart.
    >>  ............................................
    pt  Que você chegou e as coisas mudaram. Alguns não separam essas duas coisas.
    >>  ............................................
```


### Button `let_them` — "Then they can argue it out between them."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `standing.mixed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.mixed.let_them` — accepted phrasings: "then they can argue it out"; "let them argue about it"; "they can settle it between them"
  - the message must contain one of: `argue`
  - scored words: `argue`(1.5), `between`(0.7)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.respond.let_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.mixed.respond.let_them   [40 chars]
    en  Then they can argue it out between them.
    >>  ............................................
    pt  Então que eles resolvam entre si.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `standing.mixed.let_them`)_
- Does: session `turn`
- Then opens: `conversations.topic.standing.mixed.followup`
- …where the player's next choices will be: "I'd rather have both halves than a comfortable one." | "I'll keep at it until it's one answer." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed.let_them
WHO    VILLAGER — what the player reads after pressing "Then they can argue it out between them."
       spoken on: conversations.topic.standing.mixed.respond, button `let_them`
       leaves the player on: conversations.topic.standing.mixed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.let_them`: the villager qualifys. Subject `standing.mixed`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed.let_them/1   [81 chars]
    en  They will, with or without your permission. It's the village's favourite pastime.
    >>  ............................................
    pt  Vão resolver, com ou sem a sua permissão. É o passatempo favorito do vilarejo.
    >>  ............................................
  dialogue.conversations.standing.mixed.let_them/2   [64 chars]
    en  That's healthier than begging either half, I'll say that for it.
    >>  ............................................
    pt  É mais saudável do que implorar a qualquer uma das metades, isso eu admito.
    >>  ............................................
  dialogue.conversations.standing.mixed.let_them/3   [73 chars]
    en  Careful. Left alone, an argument settles on whichever side talks loudest.
    >>  ............................................
    pt  Cuidado. Sozinha, uma discussão fica com o lado que fala mais alto.
    >>  ............................................
```


### Button `dismiss` — "Half of them can think whatever they like."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `standing.mixed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.mixed.dismissed` — accepted phrasings: "half of them can think whatever they like"; "they can think whatever they want"; "i do not care what half of them think"
  - the message must contain one of: `whatever`
  - scored words: `whatever`(1.5), `like`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.mixed.respond.dismiss   [42 chars]
    en  Half of them can think whatever they like.
    >>  ............................................
    pt  Metade deles pode pensar o que quiser.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.mixed.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `standing.mixed.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed.dismissed
WHO    VILLAGER — what the player reads after pressing "Half of them can think whatever they like."
       spoken on: conversations.topic.standing.mixed.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.dismissed`: the villager resists. Subject `standing.mixed`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed.dismissed/1   [66 chars]
    en  They do. And you'll go on wondering which half you're speaking to.
    >>  ............................................
    pt  E pensam. E você vai continuar sem saber com qual metade está falando.
    >>  ............................................
  dialogue.conversations.standing.mixed.dismissed/2   [61 chars]
    en  That half buys bread from the same baker you do. Think on it.
    >>  ............................................
    pt  Essa metade compra pão do mesmo padeiro que você. Pense nisso.
    >>  ............................................
  dialogue.conversations.standing.mixed.dismissed/3   [69 chars]
    en  Then I've told you for nothing, and I'll keep the next one to myself.
    >>  ............................................
    pt  Então te contei à toa, e vou guardar a próxima pra mim.
    >>  ............................................
```


### Button `back` — "That's enough for today."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.mixed.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.mixed.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.mixed.respond.back   [24 chars]
    en  That's enough for today.
    >>  ............................................
    pt  Já basta por hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed.back
WHO    VILLAGER — what the player reads after pressing "That's enough for today."
       spoken on: conversations.topic.standing.mixed.respond, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.back`: the villager accepts. Subject `standing.mixed`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed.back/1   [13 chars]
    en  Quite, it is.
    >>  ............................................
    pt  Exato, basta.
    >>  ............................................
  dialogue.conversations.standing.mixed.back/2   [16 chars]
    en  It generally is.
    >>  ............................................
    pt  Costuma bastar.
    >>  ............................................
  dialogue.conversations.standing.mixed.back/3   [16 chars]
    en  Safe home, %1$s.
    >>  ............................................
    pt  Volte bem, %1$s.
    >>  ............................................
```

---


## `conversations.topic.standing.neutral.followup`

**Reached from 3 route(s):** `conversations.topic.standing.neutral.respond` / `ask_how`; `conversations.topic.standing.neutral.respond` / `ask_you`; `conversations.topic.standing.neutral.respond` / `content`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.neutral.how` — e.g. "Be seen doing something dull and necessary. That's how everyone here started."
- `conversations.standing.neutral.mine` — e.g. "Me? You ask questions nobody else asks. I've not decided if that's good yet."
- `conversations.standing.neutral.suits_me` — e.g. "It does have its comforts. Nobody expects anything of a stranger."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.neutral.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.neutral.followup   [29 chars]
    en  That's where it sits for now.
    >>  ............................................
    pt  É aí que está, por enquanto.
    >>  ............................................
```


### Button `thanks` — "Thank you for being straight with me."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `standing.neutral.how`, `standing.neutral.mine`, `standing.neutral.suits_me`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.neutral.followup.thanks` — accepted phrasings: "thank you for being straight with me"; "thanks for putting it plainly"; "i appreciate you saying so"
  - the message must contain one of: `straight`, `thanks`, `plainly`
  - scored words: `straight`(1.5), `thanks`(1.2), `plainly`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.neutral.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.neutral.followup.thanks   [37 chars]
    en  Thank you for being straight with me.
    >>  ............................................
    pt  Obrigado por ser franco comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.neutral.followup.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `standing.neutral.followup.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral.followup.thanks
WHO    VILLAGER — what the player reads after pressing "Thank you for being straight with me."
       spoken on: conversations.topic.standing.neutral.followup, button `thanks`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.followup.thanks`: the villager accepts. Subject `standing.neutral`, polarity `neutral`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.neutral.followup.thanks/1   [65 chars]
    en  It's not much of a verdict to be thanked for, but you're welcome.
    >>  ............................................
    pt  Não é lá um veredito que mereça agradecimento, mas de nada.
    >>  ............................................
  dialogue.conversations.standing.neutral.followup.thanks/2   [50 chars]
    en  Straight's all I've got, %1$s. Glad it was useful.
    >>  ............................................
    pt  Franco é tudo que eu tenho, %1$s. Que bom que serviu.
    >>  ............................................
  dialogue.conversations.standing.neutral.followup.thanks/3   [53 chars]
    en  Ask me again in a season and there'll be more to say.
    >>  ............................................
    pt  Me pergunte de novo daqui a uma estação e haverá mais o que dizer.
    >>  ............................................
```


### Button `set_to_work` — "Then I'd best give them something to say."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `standing.neutral.how`, `standing.neutral.mine`, `standing.neutral.suits_me` · offered only once the villager has actually said `standing:unknown`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.neutral.followup.set_to_work` — accepted phrasings: "then i had best give them something to say"; "i will earn it then"; "i had better earn myself a name"
  - the message must contain one of: `something`, `give`, `earn`
  - scored words: `something`(1.2), `give`(1.0), `earn`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.followup.set_to_work
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.neutral.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.neutral.followup.set_to_work   [41 chars]
    en  Then I'd best give them something to say.
    >>  ............................................
    pt  Então é melhor eu dar o que falar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.neutral.followup.set_to_work`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `standing.neutral.followup.set_to_work`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral.followup.set_to_work
WHO    VILLAGER — what the player reads after pressing "Then I'd best give them something to say."
       spoken on: conversations.topic.standing.neutral.followup, button `set_to_work`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.followup.set_to_work`: the villager accepts. Subject `standing.neutral`, polarity `neutral`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.neutral.followup.set_to_work/1   [63 chars]
    en  That's the spirit of it. Start small and start where it's seen.
    >>  ............................................
    pt  É esse o espírito. Comece pequeno e onde possam ver.
    >>  ............................................
  dialogue.conversations.standing.neutral.followup.set_to_work/2   [59 chars]
    en  Careful what, mind. They'll say something either way, %1$s.
    >>  ............................................
    pt  Cuidado com o quê, hein. Eles vão falar de um jeito ou de outro, %1$s.
    >>  ............................................
  dialogue.conversations.standing.neutral.followup.set_to_work/3   [61 chars]
    en  Good. Nothing wins a village like being useful twice running.
    >>  ............................................
    pt  Bom. Nada conquista um vilarejo como ser útil duas vezes seguidas.
    >>  ............................................
```


### Button `back` — "I'll think on it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.neutral.how`, `standing.neutral.mine`, `standing.neutral.suits_me` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.followup.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.neutral.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.neutral.followup.back   [17 chars]
    en  I'll think on it.
    >>  ............................................
    pt  Vou pensar nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral.followup.back
WHO    VILLAGER — what the player reads after pressing "I'll think on it."
       spoken on: conversations.topic.standing.neutral.followup, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.followup.back`: the villager accepts. Subject `standing.neutral`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.neutral.followup.back/1   [8 chars]
    en  Do that.
    >>  ............................................
    pt  Pense.
    >>  ............................................
  dialogue.conversations.standing.neutral.followup.back/2   [30 chars]
    en  True enough. Off you go, %1$s.
    >>  ............................................
    pt  Bem verdade. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.standing.neutral.followup.back/3   [10 chars]
    en  Safe home.
    >>  ............................................
    pt  Volte bem.
    >>  ............................................
```

---


## `conversations.topic.standing.neutral.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.neutral` — e.g. "Truthfully? Most people don't know you well enough to have an opinion yet, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.neutral.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.neutral.respond   [52 chars]
    en  That's how you stand with us, near as I can tell it.
    >>  ............................................
    pt  É assim que você está com a gente, pelo que eu consigo dizer.
    >>  ............................................
```


### Button `ask_how` — "What would give them a reason?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.neutral.open` · offered only once the villager has actually said `standing:unknown`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.neutral.how` — accepted phrasings: "what would give them a reason"; "how do i get them to notice"; "what would make them form an opinion"
  - the message must contain one of: `reason`, `opinion`, `notice`
  - scored words: `reason`(1.5), `opinion`(1.2), `notice`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.respond.ask_how
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.neutral.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.neutral.respond.ask_how   [30 chars]
    en  What would give them a reason?
    >>  ............................................
    pt  O que daria um motivo a eles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.neutral.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "Then I'd best give them something to say." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral.how
WHO    VILLAGER — what the player reads after pressing "What would give them a reason?"
       spoken on: conversations.topic.standing.neutral.respond, button `ask_how`
       leaves the player on: conversations.topic.standing.neutral.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.how`: the villager explains. Subject `standing.neutral`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `standing:unknown`, `advice:given` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.neutral.how/1   [77 chars]
    en  Be seen doing something dull and necessary. That's how everyone here started.
    >>  ............................................
    pt  Seja visto fazendo algo chato e necessário. Foi assim que todo mundo aqui começou.
    >>  ............................................
  dialogue.conversations.standing.neutral.how/2   [76 chars]
    en  Help one person publicly, %1$s. The village does the rest of the arithmetic.
    >>  ............................................
    pt  Ajude uma pessoa em público, %1$s. O vilarejo faz o resto da conta.
    >>  ............................................
  dialogue.conversations.standing.neutral.how/3   [84 chars]
    en  Show up twice for the same thing. Twice is what makes it a habit in people's mouths.
    >>  ............................................
    pt  Apareça duas vezes pela mesma coisa. Duas vezes é o que vira hábito na boca das pessoas.
    >>  ............................................
```


### Button `ask_you` — "What do you make of me, then?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.neutral.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.neutral.mine` — accepted phrasings: "what do you make of me"; "what do you think of me"; "and you, what is your opinion"
  - the message must contain one of: `make`, `think`
  - scored words: `make`(1.2), `you`(0.4), `think`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.respond.ask_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.neutral.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.neutral.respond.ask_you   [29 chars]
    en  What do you make of me, then?
    >>  ............................................
    pt  E você, o que acha de mim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.neutral.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "Then I'd best give them something to say." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral.mine
WHO    VILLAGER — what the player reads after pressing "What do you make of me, then?"
       spoken on: conversations.topic.standing.neutral.respond, button `ask_you`
       leaves the player on: conversations.topic.standing.neutral.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.mine`: the villager discloses. Subject `standing.neutral`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `standing:unknown` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.neutral.mine/1   [76 chars]
    en  Me? You ask questions nobody else asks. I've not decided if that's good yet.
    >>  ............................................
    pt  Eu? Você faz perguntas que ninguém mais faz. Ainda não decidi se isso é bom.
    >>  ............................................
  dialogue.conversations.standing.neutral.mine/2   [89 chars]
    en  I'd say you're easier to talk to than most who pass through, %1$s. Take that as you like.
    >>  ............................................
    pt  Eu diria que você é mais fácil de conversar que a maioria que passa por aqui, %1$s. Tire suas conclusões.
    >>  ............................................
  dialogue.conversations.standing.neutral.mine/3   [73 chars]
    en  I've an opinion. It isn't the village's, and I'll keep it a while longer.
    >>  ............................................
    pt  Tenho uma opinião. Não é a do vilarejo, e vou guardar mais um pouco.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good yet, %1$s.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.standing.neutral.mine/2
    en  You're new. I've been wrong about new people before and I'm being careful.
    >>  ............................................
    pt  Você é novo. Já errei sobre gente nova antes e estou tomando cuidado.
    >>  ............................................
  anxious.dialogue.conversations.standing.neutral.mine/3
    en  Undecided. I'd rather say that honestly than say something warmer I don't yet mean.
    >>  ............................................
    pt  Indeciso. Prefiro dizer isso honestamente a dizer algo mais caloroso que eu ainda não sinto.
    >>  ............................................
  athletic.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided yet, and there's no hurry to.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi, e não há pressa.
    >>  ............................................
  athletic.dialogue.conversations.standing.neutral.mine/2
    en  Undecided. Give it a winter; that's usually how long it takes me.
    >>  ............................................
    pt  Indeciso. Dê um inverno; costuma ser o tempo que eu levo.
    >>  ............................................
  athletic.dialogue.conversations.standing.neutral.mine/3
    en  Still working it out. Most opinions worth having take a year or so.
    >>  ............................................
    pt  Ainda descobrindo. Quase toda opinião que vale leva um ano ou mais.
    >>  ............................................
  confident.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good yet.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom.
    >>  ............................................
  confident.dialogue.conversations.standing.neutral.mine/2
    en  You're an unknown quantity. I'll tell you when I've worked out which kind.
    >>  ............................................
    pt  Você é uma incógnita. Eu te digo quando descobrir de que tipo.
    >>  ............................................
  confident.dialogue.conversations.standing.neutral.mine/3
    en  Undecided. That's better than most people get out of me in a season.
    >>  ............................................
    pt  Indeciso. É melhor do que quase todo mundo consegue de mim numa estação.
    >>  ............................................
  crabby.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good yet.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom.
    >>  ............................................
  crabby.dialogue.conversations.standing.neutral.mine/2
    en  You're an unknown quantity. I'll tell you when I've worked out which kind.
    >>  ............................................
    pt  Você é uma incógnita. Eu te digo quando descobrir de que tipo.
    >>  ............................................
  crabby.dialogue.conversations.standing.neutral.mine/3
    en  Undecided. That's better than most people get out of me in a season.
    >>  ............................................
    pt  Indeciso. É melhor do que quase todo mundo consegue de mim numa estação.
    >>  ............................................
  extroverted.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks, %1$s. I've not decided if that's good yet.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz, %1$s. Ainda não decidi se é bom.
    >>  ............................................
  extroverted.dialogue.conversations.standing.neutral.mine/2
    en  You're new and you've been kinder than new people usually are. I'm watching to see if it lasts.
    >>  ............................................
    pt  Você é novo e foi mais gentil do que gente nova costuma ser. Estou vendo se dura.
    >>  ............................................
  extroverted.dialogue.conversations.standing.neutral.mine/3
    en  Undecided, and I mean that hopefully rather than otherwise.
    >>  ............................................
    pt  Indeciso, e eu digo isso com esperança e não o contrário.
    >>  ............................................
  flirty.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks, %1$s. I've not decided if that's good yet.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz, %1$s. Ainda não decidi se é bom.
    >>  ............................................
  flirty.dialogue.conversations.standing.neutral.mine/2
    en  You're new and you've been kinder than new people usually are. I'm watching to see if it lasts.
    >>  ............................................
    pt  Você é novo e foi mais gentil do que gente nova costuma ser. Estou vendo se dura.
    >>  ............................................
  flirty.dialogue.conversations.standing.neutral.mine/3
    en  Undecided, and I mean that hopefully rather than otherwise.
    >>  ............................................
    pt  Indeciso, e eu digo isso com esperança e não o contrário.
    >>  ............................................
  friendly.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks, %1$s. I've not decided if that's good yet.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz, %1$s. Ainda não decidi se é bom.
    >>  ............................................
  friendly.dialogue.conversations.standing.neutral.mine/2
    en  You're new and you've been kinder than new people usually are. I'm watching to see if it lasts.
    >>  ............................................
    pt  Você é novo e foi mais gentil do que gente nova costuma ser. Estou vendo se dura.
    >>  ............................................
  friendly.dialogue.conversations.standing.neutral.mine/3
    en  Undecided, and I mean that hopefully rather than otherwise.
    >>  ............................................
    pt  Indeciso, e eu digo isso com esperança e não o contrário.
    >>  ............................................
  gloomy.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good yet, %1$s.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.standing.neutral.mine/2
    en  You're new. I've been wrong about new people before and I'm being careful.
    >>  ............................................
    pt  Você é novo. Já errei sobre gente nova antes e estou tomando cuidado.
    >>  ............................................
  gloomy.dialogue.conversations.standing.neutral.mine/3
    en  Undecided. I'd rather say that honestly than say something warmer I don't yet mean.
    >>  ............................................
    pt  Indeciso. Prefiro dizer isso honestamente a dizer algo mais caloroso que eu ainda não sinto.
    >>  ............................................
  greedy.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good yet.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom.
    >>  ............................................
  greedy.dialogue.conversations.standing.neutral.mine/2
    en  You're an unknown quantity. I'll tell you when I've worked out which kind.
    >>  ............................................
    pt  Você é uma incógnita. Eu te digo quando descobrir de que tipo.
    >>  ............................................
  greedy.dialogue.conversations.standing.neutral.mine/3
    en  Undecided. That's better than most people get out of me in a season.
    >>  ............................................
    pt  Indeciso. É melhor do que quase todo mundo consegue de mim numa estação.
    >>  ............................................
  grumpy.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good yet.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom.
    >>  ............................................
  grumpy.dialogue.conversations.standing.neutral.mine/2
    en  You're an unknown quantity. I'll tell you when I've worked out which kind.
    >>  ............................................
    pt  Você é uma incógnita. Eu te digo quando descobrir de que tipo.
    >>  ............................................
  grumpy.dialogue.conversations.standing.neutral.mine/3
    en  Undecided. That's better than most people get out of me in a season.
    >>  ............................................
    pt  Indeciso. É melhor do que quase todo mundo consegue de mim numa estação.
    >>  ............................................
  introverted.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom.
    >>  ............................................
  introverted.dialogue.conversations.standing.neutral.mine/2
    en  Undecided.
    >>  ............................................
    pt  Indeciso.
    >>  ............................................
  introverted.dialogue.conversations.standing.neutral.mine/3
    en  I'm still watching. That's not a bad sign.
    >>  ............................................
    pt  Ainda estou observando. Não é mau sinal.
    >>  ............................................
  lazy.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided yet, and there's no hurry to.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi, e não há pressa.
    >>  ............................................
  lazy.dialogue.conversations.standing.neutral.mine/2
    en  Undecided. Give it a winter; that's usually how long it takes me.
    >>  ............................................
    pt  Indeciso. Dê um inverno; costuma ser o tempo que eu levo.
    >>  ............................................
  lazy.dialogue.conversations.standing.neutral.mine/3
    en  Still working it out. Most opinions worth having take a year or so.
    >>  ............................................
    pt  Ainda descobrindo. Quase toda opinião que vale leva um ano ou mais.
    >>  ............................................
  odd.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom.
    >>  ............................................
  odd.dialogue.conversations.standing.neutral.mine/2
    en  Undecided.
    >>  ............................................
    pt  Indeciso.
    >>  ............................................
  odd.dialogue.conversations.standing.neutral.mine/3
    en  I'm still watching. That's not a bad sign.
    >>  ............................................
    pt  Ainda estou observando. Não é mau sinal.
    >>  ............................................
  peaceful.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided yet, and there's no hurry to.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi, e não há pressa.
    >>  ............................................
  peaceful.dialogue.conversations.standing.neutral.mine/2
    en  Undecided. Give it a winter; that's usually how long it takes me.
    >>  ............................................
    pt  Indeciso. Dê um inverno; costuma ser o tempo que eu levo.
    >>  ............................................
  peaceful.dialogue.conversations.standing.neutral.mine/3
    en  Still working it out. Most opinions worth having take a year or so.
    >>  ............................................
    pt  Ainda descobrindo. Quase toda opinião que vale leva um ano ou mais.
    >>  ............................................
  peppy.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks! I've not decided if that's good yet. It's certainly novel.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz! Ainda não decidi se é bom. Mas é novidade.
    >>  ............................................
  peppy.dialogue.conversations.standing.neutral.mine/2
    en  You're an unknown quantity and I'm enjoying not knowing, which is unusual for me.
    >>  ............................................
    pt  Você é uma incógnita e eu estou gostando de não saber, o que é incomum pra mim.
    >>  ............................................
  peppy.dialogue.conversations.standing.neutral.mine/3
    en  Undecided! Which from me is practically a compliment, %1$s.
    >>  ............................................
    pt  Indeciso! O que vindo de mim é praticamente um elogio, %1$s.
    >>  ............................................
  playful.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks! I've not decided if that's good yet. It's certainly novel.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz! Ainda não decidi se é bom. Mas é novidade.
    >>  ............................................
  playful.dialogue.conversations.standing.neutral.mine/2
    en  You're an unknown quantity and I'm enjoying not knowing, which is unusual for me.
    >>  ............................................
    pt  Você é uma incógnita e eu estou gostando de não saber, o que é incomum pra mim.
    >>  ............................................
  playful.dialogue.conversations.standing.neutral.mine/3
    en  Undecided! Which from me is practically a compliment, %1$s.
    >>  ............................................
    pt  Indeciso! O que vindo de mim é praticamente um elogio, %1$s.
    >>  ............................................
  relaxed.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided yet, and there's no hurry to.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi, e não há pressa.
    >>  ............................................
  relaxed.dialogue.conversations.standing.neutral.mine/2
    en  Undecided. Give it a winter; that's usually how long it takes me.
    >>  ............................................
    pt  Indeciso. Dê um inverno; costuma ser o tempo que eu levo.
    >>  ............................................
  relaxed.dialogue.conversations.standing.neutral.mine/3
    en  Still working it out. Most opinions worth having take a year or so.
    >>  ............................................
    pt  Ainda descobrindo. Quase toda opinião que vale leva um ano ou mais.
    >>  ............................................
  sensitive.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good yet, %1$s.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.standing.neutral.mine/2
    en  You're new. I've been wrong about new people before and I'm being careful.
    >>  ............................................
    pt  Você é novo. Já errei sobre gente nova antes e estou tomando cuidado.
    >>  ............................................
  sensitive.dialogue.conversations.standing.neutral.mine/3
    en  Undecided. I'd rather say that honestly than say something warmer I don't yet mean.
    >>  ............................................
    pt  Indeciso. Prefiro dizer isso honestamente a dizer algo mais caloroso que eu ainda não sinto.
    >>  ............................................
  shy.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks. I've not decided if that's good.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz. Ainda não decidi se é bom.
    >>  ............................................
  shy.dialogue.conversations.standing.neutral.mine/2
    en  Undecided.
    >>  ............................................
    pt  Indeciso.
    >>  ............................................
  shy.dialogue.conversations.standing.neutral.mine/3
    en  I'm still watching. That's not a bad sign.
    >>  ............................................
    pt  Ainda estou observando. Não é mau sinal.
    >>  ............................................
  upbeat.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks! I've not decided if that's good yet. It's certainly novel.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz! Ainda não decidi se é bom. Mas é novidade.
    >>  ............................................
  upbeat.dialogue.conversations.standing.neutral.mine/2
    en  You're an unknown quantity and I'm enjoying not knowing, which is unusual for me.
    >>  ............................................
    pt  Você é uma incógnita e eu estou gostando de não saber, o que é incomum pra mim.
    >>  ............................................
  upbeat.dialogue.conversations.standing.neutral.mine/3
    en  Undecided! Which from me is practically a compliment, %1$s.
    >>  ............................................
    pt  Indeciso! O que vindo de mim é praticamente um elogio, %1$s.
    >>  ............................................
  witty.dialogue.conversations.standing.neutral.mine/1
    en  You ask questions nobody else asks! I've not decided if that's good yet. It's certainly novel.
    >>  ............................................
    pt  Você faz perguntas que mais ninguém faz! Ainda não decidi se é bom. Mas é novidade.
    >>  ............................................
  witty.dialogue.conversations.standing.neutral.mine/2
    en  You're an unknown quantity and I'm enjoying not knowing, which is unusual for me.
    >>  ............................................
    pt  Você é uma incógnita e eu estou gostando de não saber, o que é incomum pra mim.
    >>  ............................................
  witty.dialogue.conversations.standing.neutral.mine/3
    en  Undecided! Which from me is practically a compliment, %1$s.
    >>  ............................................
    pt  Indeciso! O que vindo de mim é praticamente um elogio, %1$s.
    >>  ............................................
```

</details>


### Button `content` — "Being no one in particular suits me."

*stance family `restraint` · tone `plain` · outcome `qualified` · answers the beat(s) `standing.neutral.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.neutral.suits_me` — accepted phrasings: "being no one in particular suits me"; "i am happy being anonymous"; "that suits me fine"
  - the message must contain one of: `particular`, `suits`, `anonymous`
  - scored words: `particular`(1.5), `suits`(1.5), `anonymous`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.respond.content
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.neutral.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.neutral.respond.content   [36 chars]
    en  Being no one in particular suits me.
    >>  ............................................
    pt  Ser ninguém em especial me serve bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.neutral.followup`
- …where the player's next choices will be: "Thank you for being straight with me." | "Then I'd best give them something to say." | "I'll think on it."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral.suits_me
WHO    VILLAGER — what the player reads after pressing "Being no one in particular suits me."
       spoken on: conversations.topic.standing.neutral.respond, button `content`
       leaves the player on: conversations.topic.standing.neutral.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.suits_me`: the villager qualifys. Subject `standing.neutral`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `standing:unknown` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.neutral.suits_me/1   [65 chars]
    en  It does have its comforts. Nobody expects anything of a stranger.
    >>  ............................................
    pt  Tem suas vantagens. Ninguém espera nada de um estranho.
    >>  ............................................
  dialogue.conversations.standing.neutral.suits_me/2   [77 chars]
    en  Plenty here would trade with you for that, %1$s. Reputation is mostly weight.
    >>  ............................................
    pt  Muita gente aqui trocaria com você, %1$s. Reputação é principalmente peso.
    >>  ............................................
  dialogue.conversations.standing.neutral.suits_me/3   [70 chars]
    en  Then you've the rarest thing in a village: a clean start you can keep.
    >>  ............................................
    pt  Então você tem a coisa mais rara num vilarejo: um começo limpo que dá pra manter.
    >>  ............................................
```


### Button `dismiss` — "Their opinions are their problem."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `standing.neutral.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.neutral.dismissed` — accepted phrasings: "their opinions are their problem"; "it does not bother me"; "let them place me how they like"
  - the message must contain one of: `opinions`, `bother`, `placed`
  - scored words: `opinions`(1.5), `bother`(1.2), `placed`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.neutral.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.neutral.respond.dismiss   [33 chars]
    en  Their opinions are their problem.
    >>  ............................................
    pt  A opinião deles é problema deles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.neutral.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `standing.neutral.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral.dismissed
WHO    VILLAGER — what the player reads after pressing "Their opinions are their problem."
       spoken on: conversations.topic.standing.neutral.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.dismissed`: the villager resists. Subject `standing.neutral`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.neutral.dismissed/1   [61 chars]
    en  Suit yourself. Though 'nobody' is a hard name to trade under.
    >>  ............................................
    pt  Como quiser. Mas 'ninguém' é um nome difícil pra negociar.
    >>  ............................................
  dialogue.conversations.standing.neutral.dismissed/2   [68 chars]
    en  If you say so. It'll matter the first time you need something, %1$s.
    >>  ............................................
    pt  Se você diz. Vai importar na primeira vez que você precisar de algo, %1$s.
    >>  ............................................
  dialogue.conversations.standing.neutral.dismissed/3   [20 chars]
    en  Mm. You asked, mind.
    >>  ............................................
    pt  Mm. Você que perguntou.
    >>  ............................................
```


### Button `back` — "Enough about me."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.neutral.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.neutral.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.neutral.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.neutral.respond.back   [16 chars]
    en  Enough about me.
    >>  ............................................
    pt  Chega de falar de mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral.back
WHO    VILLAGER — what the player reads after pressing "Enough about me."
       spoken on: conversations.topic.standing.neutral.respond, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.back`: the villager accepts. Subject `standing.neutral`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.neutral.back/1   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
  dialogue.conversations.standing.neutral.back/2   [26 chars]
    en  So it is. What else, then?
    >>  ............................................
    pt  É assim mesmo. O que mais, então?
    >>  ............................................
  dialogue.conversations.standing.neutral.back/3   [28 chars]
    en  Take care of yourself, %1$s.
    >>  ............................................
    pt  Se cuide, %1$s.
    >>  ............................................
```

---


## `conversations.topic.standing.praise.followup`

**Reached from 3 route(s):** `conversations.topic.standing.praise.respond` / `who`; `conversations.topic.standing.praise.respond` / `what_said`; `conversations.topic.standing.praise.respond` / `awkward`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.praise.awkward` — e.g. "It is. We're none of us built to hear ourselves described kindly."
- `conversations.standing.praise.what_said` — e.g. "That %2$s was the sort of thing they'd not have thought to do themselves."
- `conversations.standing.praise.who` — e.g. "I'll not name them. They said it freely because they thought it stayed here."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.praise.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.praise.followup   [16 chars]
    en  So now you know.
    >>  ............................................
    pt  Agora você sabe.
    >>  ............................................
```


### Button `thank_you_for_passing` — "Thank you for passing it on."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `standing.praise.who`, `standing.praise.what_said`, `standing.praise.awkward`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.praise.followup.thanks` — accepted phrasings: "thank you for passing it on"; "thanks for carrying that to me"; "i am glad somebody passed it on"
  - the message must contain one of: `passing`, `carrying`
  - scored words: `passing`(1.5), `carrying`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.followup.thank_you_for_passing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.praise.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.praise.followup.thank_you_for_passing   [28 chars]
    en  Thank you for passing it on.
    >>  ............................................
    pt  Obrigado por passar adiante.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.praise.followup.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +2  _(recorded under topic `standing.praise.followup.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.praise.followup.thanks
WHO    VILLAGER — what the player reads after pressing "Thank you for passing it on."
       spoken on: conversations.topic.standing.praise.followup, button `thank_you_for_passing`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.followup.thanks`: the villager accepts. Subject `standing.praise`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.praise.followup.thanks/1   [69 chars]
    en  Somebody should. Kind words die in the street if nobody carries them.
    >>  ............................................
    pt  Alguém deveria. Palavras gentis morrem na rua se ninguém as carrega.
    >>  ............................................
  dialogue.conversations.standing.praise.followup.thanks/2   [73 chars]
    en  It cost me nothing and it seems to have been worth something. Good trade.
    >>  ............................................
    pt  Não me custou nada e parece ter valido algo. Bom negócio.
    >>  ............................................
  dialogue.conversations.standing.praise.followup.thanks/3   [59 chars]
    en  I'd want it done for me, %1$s, so I do it for other people.
    >>  ............................................
    pt  Eu ia querer isso pra mim, %1$s, então faço pelos outros.
    >>  ............................................
```


### Button `send_it_back` — "Tell them I said thank you."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `standing.praise.who`, `standing.praise.what_said`, `standing.praise.awkward`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.praise.followup.back` — accepted phrasings: "tell them i said thank you"; "pass my thanks back to them"; "let them know i appreciated it"
  - the message must contain one of: `errand`
  - scored words: `errand`(1.0), `them`(0.4), `thank`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.followup.send_it_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.praise.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.praise.followup.send_it_back   [27 chars]
    en  Tell them I said thank you.
    >>  ............................................
    pt  Diga a eles que eu agradeci.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.praise.followup.back`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +1  _(recorded under topic `standing.praise.followup.back`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.praise.followup.back
WHO    VILLAGER — what the player reads after pressing "Tell them I said thank you."
       spoken on: conversations.topic.standing.praise.followup, button `send_it_back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.followup.back`: the villager accepts. Subject `standing.praise`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.standing.praise.followup / back
```

```text
  dialogue.conversations.standing.praise.followup.back/1   [3 chars]
    en  Do.
    >>  ............................................
    pt  Leve.
    >>  ............................................
  dialogue.conversations.standing.praise.followup.back/2   [13 chars]
    en  Aye, do that.
    >>  ............................................
    pt  É, leve mesmo.
    >>  ............................................
  dialogue.conversations.standing.praise.followup.back/3   [12 chars]
    en  Go on, then.
    >>  ............................................
    pt  Vá lá, então.
    >>  ............................................
```


### Button `back` — "I'll carry that with me."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.praise.who`, `standing.praise.what_said`, `standing.praise.awkward` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.followup.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.praise.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.praise.followup.back   [24 chars]
    en  I'll carry that with me.
    >>  ............................................
    pt  Vou levar isso comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.praise.followup.back
WHO    VILLAGER — what the player reads after pressing "I'll carry that with me."
       spoken on: conversations.topic.standing.praise.followup, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.followup.back`: the villager accepts. Subject `standing.praise`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.standing.praise.followup / send_it_back
```

> Written out in full under **`conversations.topic.standing.praise.followup` / button `send_it_back`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.standing.praise.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.praise` — e.g. "Someone spoke well of you where you couldn't hear it, %1$s. I thought you ought to know it happened."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.praise.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.praise.respond   [49 chars]
    en  You weren't there to hear it, so I'm telling you.
    >>  ............................................
    pt  Você não estava lá pra ouvir, então eu estou te contando.
    >>  ............................................
```


### Button `who` — "Who said it?"

*stance family `curiosity` · tone `plain` · outcome `qualified` · answers the beat(s) `standing.praise.open` · offered only once the villager has actually said `standing:praised`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.praise.who` — accepted phrasings: "who said it"; "who was it that said it"; "who spoke well of me"
  - the message must contain one of: `said`
  - scored words: `said`(1.2), `who`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.respond.who
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.praise.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.praise.respond.who   [12 chars]
    en  Who said it?
    >>  ............................................
    pt  Quem disse isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.praise.followup`
- …where the player's next choices will be: "Thank you for passing it on." | "Tell them I said thank you." | "I'll carry that with me."

```text
POOL   dialogue key: dialogue.conversations.standing.praise.who
WHO    VILLAGER — what the player reads after pressing "Who said it?"
       spoken on: conversations.topic.standing.praise.respond, button `who`
       leaves the player on: conversations.topic.standing.praise.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.who`: the villager deflects. Subject `standing.praise`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.praise.who/1   [76 chars]
    en  I'll not name them. They said it freely because they thought it stayed here.
    >>  ............................................
    pt  Não vou dizer o nome. Falaram à vontade porque acharam que ficaria aqui.
    >>  ............................................
  dialogue.conversations.standing.praise.who/2   [71 chars]
    en  Somebody who'd deny it if you asked. That's how you know they meant it.
    >>  ............................................
    pt  Alguém que negaria se você perguntasse. É assim que se sabe que era sincero.
    >>  ............................................
  dialogue.conversations.standing.praise.who/3   [60 chars]
    en  More than one, which is why I bothered to mention it at all.
    >>  ............................................
    pt  Mais de uma pessoa, e é por isso que eu me dei ao trabalho de mencionar.
    >>  ............................................
```


### Button `what_said` — "What did they say, exactly?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.praise.open` · offered only once the villager has actually said `standing:praised`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.praise.what_said` — accepted phrasings: "what did they say exactly"; "what were their words"; "what exactly was said about me"
  - the message must contain one of: `exactly`, `words`
  - scored words: `exactly`(1.2), `words`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.respond.what_said
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.praise.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.praise.respond.what_said   [27 chars]
    en  What did they say, exactly?
    >>  ............................................
    pt  O que exatamente eles disseram?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.praise.followup`
- …where the player's next choices will be: "Thank you for passing it on." | "Tell them I said thank you." | "I'll carry that with me."

```text
POOL   dialogue key: dialogue.conversations.standing.praise.what_said
WHO    VILLAGER — what the player reads after pressing "What did they say, exactly?"
       spoken on: conversations.topic.standing.praise.respond, button `what_said`
       leaves the player on: conversations.topic.standing.praise.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = reputation_recent_deed
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.what_said`: the villager reports. Subject `standing.praise`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.praise.what_said/1   [73 chars]
    en  That %2$s was the sort of thing they'd not have thought to do themselves.
    >>  ............................................
    pt  Que %2$s era o tipo de coisa que eles não teriam pensado em fazer.
    >>  ............................................
  dialogue.conversations.standing.praise.what_said/2   [81 chars]
    en  They brought up %2$s, and then they said your name the way you say a neighbour's.
    >>  ............................................
    pt  Falaram de %2$s, e depois disseram seu nome como se diz o de um vizinho.
    >>  ............................................
  dialogue.conversations.standing.praise.what_said/3   [65 chars]
    en  %2$s came up. What stuck with me is that nobody argued with them.
    >>  ............................................
    pt  %2$s foi mencionado. O que me marcou é que ninguém discordou.
    >>  ............................................
```


### Button `awkward` — "That's an odd thing to hear about yourself."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `standing.praise.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.praise.awkward` — accepted phrasings: "that is an odd thing to hear"; "that is strange to hear about myself"; "i do not know what to do with that"
  - the message must contain one of: `odd`, `strange`
  - scored words: `odd`(1.5), `strange`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.respond.awkward
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.praise.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.praise.respond.awkward   [43 chars]
    en  That's an odd thing to hear about yourself.
    >>  ............................................
    pt  É estranho ouvir isso sobre si mesmo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.praise.awkward`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +2  _(recorded under topic `standing.praise.awkward`)_
- Does: session `turn`
- Then opens: `conversations.topic.standing.praise.followup`
- …where the player's next choices will be: "Thank you for passing it on." | "Tell them I said thank you." | "I'll carry that with me."

```text
POOL   dialogue key: dialogue.conversations.standing.praise.awkward
WHO    VILLAGER — what the player reads after pressing "That's an odd thing to hear about yourself."
       spoken on: conversations.topic.standing.praise.respond, button `awkward`
       leaves the player on: conversations.topic.standing.praise.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.awkward`: the villager accepts. Subject `standing.praise`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.praise.awkward/1   [65 chars]
    en  It is. We're none of us built to hear ourselves described kindly.
    >>  ............................................
    pt  É mesmo. Nenhum de nós foi feito pra se ouvir descrito com carinho.
    >>  ............................................
  dialogue.conversations.standing.praise.awkward/2   [70 chars]
    en  Quite. Take a moment with it — you don't have to have an answer ready.
    >>  ............................................
    pt  Exato. Leve um momento — você não precisa ter uma resposta pronta.
    >>  ............................................
  dialogue.conversations.standing.praise.awkward/3   [65 chars]
    en  Odd and good at once. Sit in it a while before you argue with it.
    >>  ............................................
    pt  Estranho e bom ao mesmo tempo. Fique um pouco nisso antes de discordar.
    >>  ............................................
```


### Button `dismiss` — "People will say anything."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `standing.praise.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.praise.dismissed` — accepted phrasings: "people will say anything"; "talk is cheap"; "words are cheap"
  - the message must contain one of: `anything`, `cheap`
  - scored words: `anything`(1.2), `cheap`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.praise.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.praise.respond.dismiss   [25 chars]
    en  People will say anything.
    >>  ............................................
    pt  As pessoas dizem qualquer coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.praise.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, warmth -2  _(recorded under topic `standing.praise.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.praise.dismissed
WHO    VILLAGER — what the player reads after pressing "People will say anything."
       spoken on: conversations.topic.standing.praise.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.dismissed`: the villager resists. Subject `standing.praise`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.praise.dismissed/1   [61 chars]
    en  Not here they won't. It took something out of them to say it.
    >>  ............................................
    pt  Aqui não dizem. Custou algo pra elas dizerem aquilo.
    >>  ............................................
  dialogue.conversations.standing.praise.dismissed/2   [73 chars]
    en  Then I've wasted a kindness on you, and I'll not make that mistake twice.
    >>  ............................................
    pt  Então desperdiçei uma gentileza com você, e não erro assim duas vezes.
    >>  ............................................
  dialogue.conversations.standing.praise.dismissed/3   [85 chars]
    en  They said it about you when you weren't stood there. That's the one kind that counts.
    >>  ............................................
    pt  Disseram sobre você quando você não estava. É o único tipo que vale.
    >>  ............................................
```


### Button `back` — "Right. Thank you."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.praise.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.praise.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.praise.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.praise.respond.back   [17 chars]
    en  Right. Thank you.
    >>  ............................................
    pt  Certo. Obrigado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.praise.back
WHO    VILLAGER — what the player reads after pressing "Right. Thank you."
       spoken on: conversations.topic.standing.praise.respond, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.back`: the villager accepts. Subject `standing.praise`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.praise.back/1   [6 chars]
    en  It is.
    >>  ............................................
    pt  É sim.
    >>  ............................................
  dialogue.conversations.standing.praise.back/2   [6 chars]
    en  Go on.
    >>  ............................................
    pt  Vá lá.
    >>  ............................................
  dialogue.conversations.standing.praise.back/3   [22 chars]
    en  Mind how you go, %1$s.
    >>  ............................................
    pt  Se cuide, %1$s.
    >>  ............................................
```

---


## `conversations.topic.standing.unknown.followup`

**Reached from 3 route(s):** `conversations.topic.standing.unknown.respond` / `your_read`; `conversations.topic.standing.unknown.respond` / `no_ledger`; `conversations.topic.standing.unknown.respond` / `who_would`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.unknown.no_ledger` — e.g. "Nor would I, and I've lived here forty years without being measured once."
- `conversations.standing.unknown.who_would` — e.g. "Whoever you've done a bad turn to, and they'd not be fair about it."
- `conversations.standing.unknown.your_read` — e.g. "Steady enough. You turn up, you don't take what isn't yours, you leave the gate shut."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.unknown.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.unknown.followup   [26 chars]
    en  That's all there is to it.
    >>  ............................................
    pt  É só isso.
    >>  ............................................
```


### Button `better_than_a_number` — "That's a better answer than a number would have been."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `standing.unknown.your_read`, `standing.unknown.no_ledger`, `standing.unknown.who_would`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.unknown.followup.better` — accepted phrasings: "that is a better answer than a number"; "better than a number"; "i would rather have that than a number"
  - the message must contain one of: `number`
  - scored words: `number`(1.5), `better`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.followup.better_than_a_number
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.unknown.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.unknown.followup.better_than_a_number   [53 chars]
    en  That's a better answer than a number would have been.
    >>  ............................................
    pt  Essa é uma resposta melhor do que um número seria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.unknown.followup.better`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `standing.unknown.followup.better`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown.followup.better
WHO    VILLAGER — what the player reads after pressing "That's a better answer than a number would have been."
       spoken on: conversations.topic.standing.unknown.followup, button `better_than_a_number`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.followup.better`: the villager accepts. Subject `standing.unknown`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.unknown.followup.better/1   [79 chars]
    en  It's the only honest one I had. A number would have been a guess with a hat on.
    >>  ............................................
    pt  É a única honesta que eu tinha. Um número seria um palpite de chapéu.
    >>  ............................................
  dialogue.conversations.standing.unknown.followup.better/2   [76 chars]
    en  I'd not know how to give you a number, %1$s, and I'd not trust one if I did.
    >>  ............................................
    pt  Eu não saberia te dar um número, %1$s, e não confiaria nele se soubesse.
    >>  ............................................
  dialogue.conversations.standing.unknown.followup.better/3   [69 chars]
    en  Then we're of a mind. Counting people is a strange habit to get into.
    >>  ............................................
    pt  Então pensamos igual. Contar pessoas é um hábito estranho de se pegar.
    >>  ............................................
```


### Button `same_of_myself` — "I'd say much the same about myself."

*stance family `self_disclosure` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.unknown.your_read`, `standing.unknown.no_ledger`, `standing.unknown.who_would`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.unknown.followup.same` — accepted phrasings: "i would say much the same about myself"; "i see myself the same way"; "that is how i see myself too"
  - the message must contain one of: `myself`
  - scored words: `myself`(1.5), `same`(0.7)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.followup.same_of_myself
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.unknown.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.unknown.followup.same_of_myself   [35 chars]
    en  I'd say much the same about myself.
    >>  ............................................
    pt  Eu diria quase o mesmo sobre mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.unknown.followup.same`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +2  _(recorded under topic `standing.unknown.followup.same`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown.followup.same
WHO    VILLAGER — what the player reads after pressing "I'd say much the same about myself."
       spoken on: conversations.topic.standing.unknown.followup, button `same_of_myself`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.followup.same`: the villager accepts. Subject `standing.unknown`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.unknown.followup.same/1   [68 chars]
    en  Most of us would, if we were asked plainly. Not everybody admits it.
    >>  ............................................
    pt  Quase todos diríamos, se perguntassem sem rodeios. Nem todo mundo admite.
    >>  ............................................
  dialogue.conversations.standing.unknown.followup.same/2   [68 chars]
    en  That's a fair thing to own up to. It costs a little to say out loud.
    >>  ............................................
    pt  É justo assumir isso. Custa um pouco dizer em voz alta.
    >>  ............................................
  dialogue.conversations.standing.unknown.followup.same/3   [74 chars]
    en  Then you're no mystery to yourself either, which is more than some manage.
    >>  ............................................
    pt  Então você também não é um mistério pra si, o que já é mais do que muitos.
    >>  ............................................
```


### Button `back` — "Fair enough."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.unknown.your_read`, `standing.unknown.no_ledger`, `standing.unknown.who_would` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.followup.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.unknown.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.unknown.followup.back   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown.followup.back
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.topic.standing.unknown.followup, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.followup.back`: the villager accepts. Subject `standing.unknown`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.unknown.followup.back/1   [4 chars]
    en  Aye.
    >>  ............................................
    pt  É.
    >>  ............................................
  dialogue.conversations.standing.unknown.followup.back/2   [17 chars]
    en  Off you go, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
  dialogue.conversations.standing.unknown.followup.back/3   [22 chars]
    en  Until next time, %1$s.
    >>  ............................................
    pt  Até a próxima, %1$s.
    >>  ............................................
```

---


## `conversations.topic.standing.unknown.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `standing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.standing.no_tally` — e.g. "Nobody keeps a tally here, %1$s. There's no ledger to look you up in."


```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.standing.unknown.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.standing.unknown.respond   [66 chars]
    en  There's no list with your name on it, if that's what you're after.
    >>  ............................................
    pt  Não existe nenhuma lista com o seu nome, se é isso que você procura.
    >>  ............................................
```


### Button `your_read` — "Then how do you read me?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.unknown.open` · offered only once the villager has actually said `standing:no_tally`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.unknown.your_read` — accepted phrasings: "then how do you read me"; "how do you read me yourself"; "what is your own read on me"
  - the message must contain one of: `read`
  - scored words: `read`(1.5), `yourself`(0.7)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.respond.your_read
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.unknown.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.unknown.respond.your_read   [24 chars]
    en  Then how do you read me?
    >>  ............................................
    pt  Então como você me lê?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.unknown.followup`
- …where the player's next choices will be: "That's a better answer than a number would have been." | "I'd say much the same about myself." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown.your_read
WHO    VILLAGER — what the player reads after pressing "Then how do you read me?"
       spoken on: conversations.topic.standing.unknown.respond, button `your_read`
       leaves the player on: conversations.topic.standing.unknown.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.your_read`: the villager discloses. Subject `standing.unknown`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, self_disclosure, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.unknown.your_read/1   [85 chars]
    en  Steady enough. You turn up, you don't take what isn't yours, you leave the gate shut.
    >>  ............................................
    pt  Firme o bastante. Você aparece, não pega o que não é seu, deixa o portão fechado.
    >>  ............................................
  dialogue.conversations.standing.unknown.your_read/2   [85 chars]
    en  Hard to say and easy to like, which is an odd pair to be. I've settled on liking you.
    >>  ............................................
    pt  Difícil de dizer e fácil de gostar, um par estranho de ser. Eu fiquei no gostar.
    >>  ............................................
  dialogue.conversations.standing.unknown.your_read/3   [74 chars]
    en  You listen longer than you talk. That's rarer here than you'd think, %1$s.
    >>  ............................................
    pt  Você escuta mais do que fala. Isso é mais raro aqui do que você imagina, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.standing.unknown.your_read/1
    en  Steady — and I don't say that lightly, %1$s, because I've been wrong about people before.
    >>  ............................................
    pt  Firme — e não digo isso à toa, %1$s, porque já errei sobre pessoas antes.
    >>  ............................................
  anxious.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read, and I've been careful about deciding. I'd rather be slow than unfair.
    >>  ............................................
    pt  Difícil de ler, e eu fui cuidadoso ao decidir. Prefiro ser lento a ser injusto.
    >>  ............................................
  anxious.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. That has mattered to me more than I've said.
    >>  ............................................
    pt  Você escuta mais do que fala. Isso me importou mais do que eu disse.
    >>  ............................................
  athletic.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. Forty years of watching people arrive teaches you what steady looks like.
    >>  ............................................
    pt  Firme. Quarenta anos vendo gente chegar ensinam como é ser firme.
    >>  ............................................
  athletic.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read at first, easy to like by the second winter. That's the usual shape.
    >>  ............................................
    pt  Difícil de ler no começo, fácil de gostar no segundo inverno. É a forma de sempre.
    >>  ............................................
  athletic.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. The ones who last here generally do.
    >>  ............................................
    pt  Você escuta mais do que fala. Quem dura aqui geralmente escuta.
    >>  ............................................
  confident.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. You turn up, you don't take what isn't yours, you leave the gate shut.
    >>  ............................................
    pt  Firme. Você aparece, não pega o que não é seu, deixa o portão fechado.
    >>  ............................................
  confident.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy enough to like. I've settled on the liking.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar. Eu fiquei no gostar.
    >>  ............................................
  confident.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. That's rarer here than you'd think.
    >>  ............................................
    pt  Você escuta mais do que fala. É mais raro aqui do que você imagina.
    >>  ............................................
  crabby.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. You turn up, you don't take what isn't yours, you leave the gate shut.
    >>  ............................................
    pt  Firme. Você aparece, não pega o que não é seu, deixa o portão fechado.
    >>  ............................................
  crabby.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy enough to like. I've settled on the liking.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar. Eu fiquei no gostar.
    >>  ............................................
  crabby.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. That's rarer here than you'd think.
    >>  ............................................
    pt  Você escuta mais do que fala. É mais raro aqui do que você imagina.
    >>  ............................................
  extroverted.dialogue.conversations.standing.unknown.your_read/1
    en  Steady, %1$s. You turn up, and turning up is most of it.
    >>  ............................................
    pt  Firme, %1$s. Você aparece, e aparecer já é quase tudo.
    >>  ............................................
  extroverted.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy to like. I got to the liking quickly, if you want the truth.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar. Cheguei no gostar rápido, se quer a verdade.
    >>  ............................................
  extroverted.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. I noticed it the first week and I still notice it.
    >>  ............................................
    pt  Você escuta mais do que fala. Notei na primeira semana e ainda noto.
    >>  ............................................
  flirty.dialogue.conversations.standing.unknown.your_read/1
    en  Steady, %1$s. You turn up, and turning up is most of it.
    >>  ............................................
    pt  Firme, %1$s. Você aparece, e aparecer já é quase tudo.
    >>  ............................................
  flirty.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy to like. I got to the liking quickly, if you want the truth.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar. Cheguei no gostar rápido, se quer a verdade.
    >>  ............................................
  flirty.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. I noticed it the first week and I still notice it.
    >>  ............................................
    pt  Você escuta mais do que fala. Notei na primeira semana e ainda noto.
    >>  ............................................
  friendly.dialogue.conversations.standing.unknown.your_read/1
    en  Steady, %1$s. You turn up, and turning up is most of it.
    >>  ............................................
    pt  Firme, %1$s. Você aparece, e aparecer já é quase tudo.
    >>  ............................................
  friendly.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy to like. I got to the liking quickly, if you want the truth.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar. Cheguei no gostar rápido, se quer a verdade.
    >>  ............................................
  friendly.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. I noticed it the first week and I still notice it.
    >>  ............................................
    pt  Você escuta mais do que fala. Notei na primeira semana e ainda noto.
    >>  ............................................
  gloomy.dialogue.conversations.standing.unknown.your_read/1
    en  Steady — and I don't say that lightly, %1$s, because I've been wrong about people before.
    >>  ............................................
    pt  Firme — e não digo isso à toa, %1$s, porque já errei sobre pessoas antes.
    >>  ............................................
  gloomy.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read, and I've been careful about deciding. I'd rather be slow than unfair.
    >>  ............................................
    pt  Difícil de ler, e eu fui cuidadoso ao decidir. Prefiro ser lento a ser injusto.
    >>  ............................................
  gloomy.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. That has mattered to me more than I've said.
    >>  ............................................
    pt  Você escuta mais do que fala. Isso me importou mais do que eu disse.
    >>  ............................................
  greedy.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. You turn up, you don't take what isn't yours, you leave the gate shut.
    >>  ............................................
    pt  Firme. Você aparece, não pega o que não é seu, deixa o portão fechado.
    >>  ............................................
  greedy.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy enough to like. I've settled on the liking.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar. Eu fiquei no gostar.
    >>  ............................................
  greedy.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. That's rarer here than you'd think.
    >>  ............................................
    pt  Você escuta mais do que fala. É mais raro aqui do que você imagina.
    >>  ............................................
  grumpy.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. You turn up, you don't take what isn't yours, you leave the gate shut.
    >>  ............................................
    pt  Firme. Você aparece, não pega o que não é seu, deixa o portão fechado.
    >>  ............................................
  grumpy.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy enough to like. I've settled on the liking.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar. Eu fiquei no gostar.
    >>  ............................................
  grumpy.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. That's rarer here than you'd think.
    >>  ............................................
    pt  Você escuta mais do que fala. É mais raro aqui do que você imagina.
    >>  ............................................
  introverted.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. You turn up.
    >>  ............................................
    pt  Firme. Você aparece.
    >>  ............................................
  introverted.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read. Easy enough to like.
    >>  ............................................
    pt  Difícil de ler. Fácil de gostar.
    >>  ............................................
  introverted.dialogue.conversations.standing.unknown.your_read/3
    en  You listen more than you talk.
    >>  ............................................
    pt  Você escuta mais do que fala.
    >>  ............................................
  lazy.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. Forty years of watching people arrive teaches you what steady looks like.
    >>  ............................................
    pt  Firme. Quarenta anos vendo gente chegar ensinam como é ser firme.
    >>  ............................................
  lazy.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read at first, easy to like by the second winter. That's the usual shape.
    >>  ............................................
    pt  Difícil de ler no começo, fácil de gostar no segundo inverno. É a forma de sempre.
    >>  ............................................
  lazy.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. The ones who last here generally do.
    >>  ............................................
    pt  Você escuta mais do que fala. Quem dura aqui geralmente escuta.
    >>  ............................................
  odd.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. You turn up.
    >>  ............................................
    pt  Firme. Você aparece.
    >>  ............................................
  odd.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read. Easy enough to like.
    >>  ............................................
    pt  Difícil de ler. Fácil de gostar.
    >>  ............................................
  odd.dialogue.conversations.standing.unknown.your_read/3
    en  You listen more than you talk.
    >>  ............................................
    pt  Você escuta mais do que fala.
    >>  ............................................
  peaceful.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. Forty years of watching people arrive teaches you what steady looks like.
    >>  ............................................
    pt  Firme. Quarenta anos vendo gente chegar ensinam como é ser firme.
    >>  ............................................
  peaceful.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read at first, easy to like by the second winter. That's the usual shape.
    >>  ............................................
    pt  Difícil de ler no começo, fácil de gostar no segundo inverno. É a forma de sempre.
    >>  ............................................
  peaceful.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. The ones who last here generally do.
    >>  ............................................
    pt  Você escuta mais do que fala. Quem dura aqui geralmente escuta.
    >>  ............................................
  peppy.dialogue.conversations.standing.unknown.your_read/1
    en  Steady, and better company than the last three who asked me that question!
    >>  ............................................
    pt  Firme, e melhor companhia que os três últimos que me fizeram essa pergunta!
    >>  ............................................
  peppy.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy to like — an odd pair, but I've enjoyed working it out.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar — um par estranho, mas gostei de descobrir.
    >>  ............................................
  peppy.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk, %1$s, which makes you my favourite kind of person.
    >>  ............................................
    pt  Você escuta mais do que fala, %1$s, o que te faz meu tipo favorito de pessoa.
    >>  ............................................
  playful.dialogue.conversations.standing.unknown.your_read/1
    en  Steady, and better company than the last three who asked me that question!
    >>  ............................................
    pt  Firme, e melhor companhia que os três últimos que me fizeram essa pergunta!
    >>  ............................................
  playful.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy to like — an odd pair, but I've enjoyed working it out.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar — um par estranho, mas gostei de descobrir.
    >>  ............................................
  playful.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk, %1$s, which makes you my favourite kind of person.
    >>  ............................................
    pt  Você escuta mais do que fala, %1$s, o que te faz meu tipo favorito de pessoa.
    >>  ............................................
  relaxed.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. Forty years of watching people arrive teaches you what steady looks like.
    >>  ............................................
    pt  Firme. Quarenta anos vendo gente chegar ensinam como é ser firme.
    >>  ............................................
  relaxed.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read at first, easy to like by the second winter. That's the usual shape.
    >>  ............................................
    pt  Difícil de ler no começo, fácil de gostar no segundo inverno. É a forma de sempre.
    >>  ............................................
  relaxed.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. The ones who last here generally do.
    >>  ............................................
    pt  Você escuta mais do que fala. Quem dura aqui geralmente escuta.
    >>  ............................................
  sensitive.dialogue.conversations.standing.unknown.your_read/1
    en  Steady — and I don't say that lightly, %1$s, because I've been wrong about people before.
    >>  ............................................
    pt  Firme — e não digo isso à toa, %1$s, porque já errei sobre pessoas antes.
    >>  ............................................
  sensitive.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read, and I've been careful about deciding. I'd rather be slow than unfair.
    >>  ............................................
    pt  Difícil de ler, e eu fui cuidadoso ao decidir. Prefiro ser lento a ser injusto.
    >>  ............................................
  sensitive.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk. That has mattered to me more than I've said.
    >>  ............................................
    pt  Você escuta mais do que fala. Isso me importou mais do que eu disse.
    >>  ............................................
  shy.dialogue.conversations.standing.unknown.your_read/1
    en  Steady. You turn up.
    >>  ............................................
    pt  Firme. Você aparece.
    >>  ............................................
  shy.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read. Easy enough to like.
    >>  ............................................
    pt  Difícil de ler. Fácil de gostar.
    >>  ............................................
  shy.dialogue.conversations.standing.unknown.your_read/3
    en  You listen more than you talk.
    >>  ............................................
    pt  Você escuta mais do que fala.
    >>  ............................................
  upbeat.dialogue.conversations.standing.unknown.your_read/1
    en  Steady, and better company than the last three who asked me that question!
    >>  ............................................
    pt  Firme, e melhor companhia que os três últimos que me fizeram essa pergunta!
    >>  ............................................
  upbeat.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy to like — an odd pair, but I've enjoyed working it out.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar — um par estranho, mas gostei de descobrir.
    >>  ............................................
  upbeat.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk, %1$s, which makes you my favourite kind of person.
    >>  ............................................
    pt  Você escuta mais do que fala, %1$s, o que te faz meu tipo favorito de pessoa.
    >>  ............................................
  witty.dialogue.conversations.standing.unknown.your_read/1
    en  Steady, and better company than the last three who asked me that question!
    >>  ............................................
    pt  Firme, e melhor companhia que os três últimos que me fizeram essa pergunta!
    >>  ............................................
  witty.dialogue.conversations.standing.unknown.your_read/2
    en  Hard to read and easy to like — an odd pair, but I've enjoyed working it out.
    >>  ............................................
    pt  Difícil de ler e fácil de gostar — um par estranho, mas gostei de descobrir.
    >>  ............................................
  witty.dialogue.conversations.standing.unknown.your_read/3
    en  You listen longer than you talk, %1$s, which makes you my favourite kind of person.
    >>  ............................................
    pt  Você escuta mais do que fala, %1$s, o que te faz meu tipo favorito de pessoa.
    >>  ............................................
```

</details>


### Button `no_ledger` — "Good. I'd rather not be measured."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `standing.unknown.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.unknown.no_ledger` — accepted phrasings: "i would rather not be measured"; "no ledger suits me fine"; "i am glad there is no tally"
  - the message must contain one of: `measured`, `ledger`
  - scored words: `measured`(1.5), `ledger`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.respond.no_ledger
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.unknown.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.unknown.respond.no_ledger   [33 chars]
    en  Good. I'd rather not be measured.
    >>  ............................................
    pt  Bom. Prefiro não ser medido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `standing.unknown.no_ledger`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2  _(recorded under topic `standing.unknown.no_ledger`)_
- Does: session `turn`
- Then opens: `conversations.topic.standing.unknown.followup`
- …where the player's next choices will be: "That's a better answer than a number would have been." | "I'd say much the same about myself." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown.no_ledger
WHO    VILLAGER — what the player reads after pressing "Good. I'd rather not be measured."
       spoken on: conversations.topic.standing.unknown.respond, button `no_ledger`
       leaves the player on: conversations.topic.standing.unknown.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.no_ledger`: the villager accepts. Subject `standing.unknown`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, self_disclosure, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.unknown.no_ledger/1   [73 chars]
    en  Nor would I, and I've lived here forty years without being measured once.
    >>  ............................................
    pt  Nem eu, e vivi aqui quarenta anos sem ser medido uma vez sequer.
    >>  ............................................
  dialogue.conversations.standing.unknown.no_ledger/2   [77 chars]
    en  Then you'll do well here. The places that count you are the places you leave.
    >>  ............................................
    pt  Então você vai se dar bem aqui. Os lugares que te contam são os que você deixa.
    >>  ............................................
  dialogue.conversations.standing.unknown.no_ledger/3   [83 chars]
    en  A sound instinct. The moment there's a list, somebody's always at the bottom of it.
    >>  ............................................
    pt  Bom instinto. No momento em que há uma lista, alguém sempre fica no fim dela.
    >>  ............................................
```


### Button `who_would` — "Who would know, if anyone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `standing.unknown.open` · offered only once the villager has actually said `standing:no_tally`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.unknown.who_would` — accepted phrasings: "who would know if anyone"; "who keeps track then"; "is there anyone who would know"
  - the message must contain one of: `anyone`, `tally`
  - scored words: `anyone`(1.2), `tally`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.respond.who_would
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.unknown.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.unknown.respond.who_would   [26 chars]
    en  Who would know, if anyone?
    >>  ............................................
    pt  Quem saberia, se é que alguém sabe?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.standing.unknown.followup`
- …where the player's next choices will be: "That's a better answer than a number would have been." | "I'd say much the same about myself." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown.who_would
WHO    VILLAGER — what the player reads after pressing "Who would know, if anyone?"
       spoken on: conversations.topic.standing.unknown.respond, button `who_would`
       leaves the player on: conversations.topic.standing.unknown.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.who_would`: the villager explains. Subject `standing.unknown`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, self_disclosure, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.unknown.who_would/1   [67 chars]
    en  Whoever you've done a bad turn to, and they'd not be fair about it.
    >>  ............................................
    pt  Quem você tiver prejudicado, e essa pessoa não seria justa.
    >>  ............................................
  dialogue.conversations.standing.unknown.who_would/2   [81 chars]
    en  Nobody with a whole picture. We each hold a corner of you and none of us compare.
    >>  ............................................
    pt  Ninguém com o quadro inteiro. Cada um segura um canto de você e não comparamos.
    >>  ............................................
  dialogue.conversations.standing.unknown.who_would/3   [80 chars]
    en  The children, probably. They watch harder than we do and they say what they see.
    >>  ............................................
    pt  As crianças, provavelmente. Observam mais do que nós e dizem o que veem.
    >>  ............................................
```


### Button `dismiss` — "Then this was a waste of breath."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `standing.unknown.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `standing.unknown.dismissed` — accepted phrasings: "then this was a waste of breath"; "that was a waste of my time"; "what a useless answer"
  - the message must contain one of: `breath`, `waste`
  - scored words: `breath`(1.5), `waste`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.unknown.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.unknown.respond.dismiss   [32 chars]
    en  Then this was a waste of breath.
    >>  ............................................
    pt  Então isso foi um desperdício de fôlego.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `standing.unknown.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `standing.unknown.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown.dismissed
WHO    VILLAGER — what the player reads after pressing "Then this was a waste of breath."
       spoken on: conversations.topic.standing.unknown.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.dismissed`: the villager resists. Subject `standing.unknown`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.unknown.dismissed/1   [52 chars]
    en  Mine or yours? I answered the question you asked me.
    >>  ............................................
    pt  Meu ou seu? Eu respondi a pergunta que você fez.
    >>  ............................................
  dialogue.conversations.standing.unknown.dismissed/2   [71 chars]
    en  You asked a village with no scribes for a score. That's not my failing.
    >>  ............................................
    pt  Você pediu uma nota a um vilarejo sem escribas. A falha não é minha.
    >>  ............................................
  dialogue.conversations.standing.unknown.dismissed/3   [36 chars]
    en  Then don't spend the next one on me.
    >>  ............................................
    pt  Então não gaste o próximo comigo.
    >>  ............................................
```


### Button `back` — "Never mind, then."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `standing.unknown.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.standing.unknown.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.standing.unknown.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.standing.unknown.respond.back   [17 chars]
    en  Never mind, then.
    >>  ............................................
    pt  Deixa pra lá, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown.back
WHO    VILLAGER — what the player reads after pressing "Never mind, then."
       spoken on: conversations.topic.standing.unknown.respond, button `back`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.back`: the villager accepts. Subject `standing.unknown`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.unknown.back/1   [14 chars]
    en  Suit yourself.
    >>  ............................................
    pt  Como quiser.
    >>  ............................................
  dialogue.conversations.standing.unknown.back/2   [18 chars]
    en  As you like, %1$s.
    >>  ............................................
    pt  Como preferir, %1$s.
    >>  ............................................
  dialogue.conversations.standing.unknown.back/3   [25 chars]
    en  Mind the step on the way.
    >>  ............................................
    pt  Cuidado com o degrau na saída.
    >>  ............................................
```

---

