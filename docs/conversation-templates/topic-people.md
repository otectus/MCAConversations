# Topic: people

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `people` |
| Opened from | question `conversations.cat.village`, button `people` |
| Depth class (its heart budget) | `standard` |
| Returns to | `conversations.cat.village` |
| Ages that can reach it | child, teen, adult |
| Stance families it must offer | `empathy`, `curiosity`, `respectful_disagreement`, `boundary_push`, `humor`, `self_disclosure`, `candor`, `exit` |
| Narrative arc | `people`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.village`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.village.people
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.village.people   [35 chars]
    en  What do you make of your neighbors?
    >>  ............................................
    pt  O que você acha dos seus vizinhos?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.people.resume.followup`](#conversations-arc-people-resume-followup)
- [`conversations.arc.people.resume.respond`](#conversations-arc-people-resume-respond)
- [`conversations.scene.people.changed_my_mind.respond`](#conversations-scene-people-changed-my-mind-respond)
- [`conversations.scene.people.followup`](#conversations-scene-people-followup)
- [`conversations.scene.people.the_newcomer.respond`](#conversations-scene-people-the-newcomer-respond)
- [`conversations.topic.people.again.respond`](#conversations-topic-people-again-respond)
- [`conversations.topic.people.fond.followup`](#conversations-topic-people-fond-followup)
- [`conversations.topic.people.fond.respond`](#conversations-topic-people-fond-respond)
- [`conversations.topic.people.gossip.followup`](#conversations-topic-people-gossip-followup)
- [`conversations.topic.people.grumble.followup`](#conversations-topic-people-grumble-followup)
- [`conversations.topic.people.mixed.respond`](#conversations-topic-people-mixed-respond)
- [`conversations.topic.people.rebuffed.followup`](#conversations-topic-people-rebuffed-followup)
- [`conversations.topic.people.softened.followup`](#conversations-topic-people-softened-followup)
- [`conversations.topic.people.sour.respond`](#conversations-topic-people-sour-respond)
- [`conversations.topic.people.young.respond`](#conversations-topic-people-young-respond)

---

## `conversations.arc.people.resume.followup`

**Reached from 3 route(s):** `conversations.arc.people.resume.respond` / `glad`; `conversations.arc.people.resume.respond` / `no_hurry_people`; `conversations.arc.people.resume.respond` / `ask_what_said`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.resume.ask_what_said` — e.g. "Less than I'd planned. The planned version was a speech and speeches don't mend."
- `conversations.people.resume.glad` — e.g. "So am I, now. I was not glad at the time and I'd like that on record."
- `conversations.people.resume.no_hurry_people` — e.g. "There isn't, and that's exactly how a season becomes four."


```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.people.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.people.resume.followup   [29 chars]
    en  And that's where we leave it.
    >>  ............................................
    pt  E é aí que a gente para.
    >>  ............................................
```


### Button `thank_you_for_telling` — "Thank you for keeping me in it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `people.resume.glad`, `people.resume.no_hurry_people`, `people.resume.ask_what_said`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.resume.thank_you_for_telling` — accepted phrasings: "thank you for keeping me in it"; "thanks for keeping me informed"; "i am glad you told me how it went"
  - the message must contain one of: `keeping`
  - scored words: `grudge`(0.3), `keeping`(1.2), `telling`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.followup.thank_you_for_telling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.people.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.people.resume.followup.thank_you_for_telling   [31 chars]
    en  Thank you for keeping me in it.
    >>  ............................................
    pt  Obrigado por me manter por dentro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.resume.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `people.resume.thank_you_for_telling`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.resume.thank_you_for_telling
WHO    VILLAGER — what the player reads after pressing "Thank you for keeping me in it."
       spoken on: conversations.arc.people.resume.followup, button `thank_you_for_telling`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.resume.thank_you_for_telling`: the villager accepts. Subject `people.repair`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.resume.thank_you_for_telling/1   [72 chars]
    en  You asked. People who ask get told; it isn't more complicated than that.
    >>  ............................................
    pt  Você perguntou. Quem pergunta é informado; não é mais complicado que isso.
    >>  ............................................
  dialogue.conversations.people.resume.thank_you_for_telling/2   [76 chars]
    en  It costs me nothing and it seems to be worth something. I'll go on doing it.
    >>  ............................................
    pt  Não me custa nada e parece valer algo. Vou continuar fazendo.
    >>  ............................................
  dialogue.conversations.people.resume.thank_you_for_telling/3   [74 chars]
    en  That's the second time you've thanked me for a thing I'd have done anyway.
    >>  ............................................
    pt  É a segunda vez que você me agradece por algo que eu faria de qualquer jeito.
    >>  ............................................
```


### Button `leave_it_with_you` — "I'll leave it with you."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `people.resume.glad`, `people.resume.no_hurry_people`, `people.resume.ask_what_said`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.resume.leave_it_with_you` — accepted phrasings: "i will leave it with you"; "that is yours to handle"; "i will let you carry it from here"
  - the message must contain one of: `yours`
  - scored words: `grudge`(0.3), `leave`(0.6), `with`(0.3), `yours`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.followup.leave_it_with_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.people.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.people.resume.followup.leave_it_with_you   [23 chars]
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
POOL   dialogue key: dialogue.conversations.people.resume.leave_it_with_you
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.people.resume.followup, button `leave_it_with_you`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.resume.leave_it_with_you`: the villager accepts. Subject `people.repair`, polarity `neutral`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.resume.leave_it_with_you/1   [49 chars]
    en  Do. I'll say if it changes, and I'll say plainly.
    >>  ............................................
    pt  Deixe. Eu aviso se mudar, e aviso sem rodeios.
    >>  ............................................
  dialogue.conversations.people.resume.leave_it_with_you/2   [73 chars]
    en  Right. It's mine to carry and it's lighter for having been said out loud.
    >>  ............................................
    pt  Certo. É meu pra carregar e está mais leve por ter sido dito em voz alta.
    >>  ............................................
  dialogue.conversations.people.resume.leave_it_with_you/3   [74 chars]
    en  Then it's mine again. That's how it should be, and thank you for the loan.
    >>  ............................................
    pt  Então volta a ser meu. É como deve ser, e obrigado pelo empréstimo.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.resume.glad`, `people.resume.no_hurry_people`, `people.resume.ask_what_said` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.people.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.people.resume.followup.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.people.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.people.resume.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.resume.leave`: the villager accepts. Subject `people.repair`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.people.resume.respond / leave
```

```text
  dialogue.conversations.people.resume.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.people.resume.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.people.resume.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.arc.people.resume.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `people`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.resume` — e.g. "I spoke to them. You'll want to know it was awkward and it was worth it."


```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.people.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.people.resume.respond   [23 chars]
    en  That's where it got to.
    >>  ............................................
    pt  Foi até aí que chegou.
    >>  ............................................
```


### Button `glad` — "I'm glad you went."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `people.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.resume.glad` — accepted phrasings: "i am glad you went"; "good that you went"; "i am pleased you spoke to them"
  - the message must contain one of: `went`
  - scored words: `glad`(0.5), `went`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.respond.glad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.people.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.people.resume.respond.glad   [18 chars]
    en  I'm glad you went.
    >>  ............................................
    pt  Que bom que você foi.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.resume.glad`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `people.resume.glad`)_
- Does: session `turn`
- Then opens: `conversations.arc.people.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.people.resume.glad
WHO    VILLAGER — what the player reads after pressing "I'm glad you went."
       spoken on: conversations.arc.people.resume.respond, button `glad`
       leaves the player on: conversations.arc.people.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.resume.glad`: the villager accepts. Subject `people.repair`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.resume.glad/1   [69 chars]
    en  So am I, now. I was not glad at the time and I'd like that on record.
    >>  ............................................
    pt  Eu também, agora. Não estava contente na hora e quero isso registrado.
    >>  ............................................
  dialogue.conversations.people.resume.glad/2   [60 chars]
    en  It took a fortnight of deciding and eleven minutes of doing.
    >>  ............................................
    pt  Levou quinze dias pra decidir e onze minutos pra fazer.
    >>  ............................................
  dialogue.conversations.people.resume.glad/3   [73 chars]
    en  Don't be too pleased. I've one conversation left and it's the harder one.
    >>  ............................................
    pt  Não fique muito contente. Falta uma conversa e é a mais difícil.
    >>  ............................................
```


### Button `no_hurry_people` — "There's no deadline on it."

*stance family `restraint` · tone `plain` · outcome `qualified` · answers the beat(s) `people.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.resume.no_hurry_people` — accepted phrasings: "there is no deadline on it"; "you can take your time with them"; "no need to hurry that"
  - the message must contain one of: `deadline`
  - scored words: `deadline`(1.5), `rush`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.respond.no_hurry_people
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.people.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.people.resume.respond.no_hurry_people   [26 chars]
    en  There's no deadline on it.
    >>  ............................................
    pt  Não há prazo pra isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.people.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.people.resume.no_hurry_people
WHO    VILLAGER — what the player reads after pressing "There's no deadline on it."
       spoken on: conversations.arc.people.resume.respond, button `no_hurry_people`
       leaves the player on: conversations.arc.people.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.resume.no_hurry_people`: the villager qualifys. Subject `people.repair`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.resume.no_hurry_people/1   [58 chars]
    en  There isn't, and that's exactly how a season becomes four.
    >>  ............................................
    pt  Não há, e é exatamente assim que uma estação vira quatro.
    >>  ............................................
  dialogue.conversations.people.resume.no_hurry_people/2   [59 chars]
    en  Kind. Wrong, but kind, and I'll take the kind part with me.
    >>  ............................................
    pt  Gentil. Errado, mas gentil, e eu levo a parte gentil comigo.
    >>  ............................................
  dialogue.conversations.people.resume.no_hurry_people/3   [74 chars]
    en  No. But there's a point where waiting becomes the answer, and I'm near it.
    >>  ............................................
    pt  Não. Mas há um ponto em que esperar vira a resposta, e estou perto dele.
    >>  ............................................
```


### Button `ask_what_said` — "What did you say to them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `people.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.resume.ask_what_said` — accepted phrasings: "what did you say to them"; "how did you put it"; "what were your words to them"
  - scored words: `say`(0.5), `them`(0.4), `words`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.respond.ask_what_said
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.people.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.people.resume.respond.ask_what_said   [25 chars]
    en  What did you say to them?
    >>  ............................................
    pt  O que você disse a eles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.people.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.people.resume.ask_what_said
WHO    VILLAGER — what the player reads after pressing "What did you say to them?"
       spoken on: conversations.arc.people.resume.respond, button `ask_what_said`
       leaves the player on: conversations.arc.people.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.resume.ask_what_said`: the villager discloses. Subject `people.repair`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.resume.ask_what_said/1   [80 chars]
    en  Less than I'd planned. The planned version was a speech and speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei. A versão planejada era discurso e discurso não conserta.
    >>  ............................................
  dialogue.conversations.people.resume.ask_what_said/2   [78 chars]
    en  That I'd been unfair. Two words of it were true and the rest were scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  dialogue.conversations.people.resume.ask_what_said/3   [80 chars]
    en  Nothing clever. 'I've been avoiding you.' That did more than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Isso fez mais que um ano de pensamento.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. The speech was armour and I put it down at the door.
    >>  ............................................
    pt  Menos do que eu planejei. O discurso era armadura e eu larguei na porta.
    >>  ............................................
  anxious.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Saying it took longer to reach than it took to say.
    >>  ............................................
    pt  Que eu tinha sido injusto. Dizer levou mais tempo pra chegar do que pra sair.
    >>  ............................................
  anxious.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' I'd been rehearsing cleverer things for a year.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Eu vinha ensaiando coisas mais espertas há um ano.
    >>  ............................................
  athletic.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. Sixty years teaches you that speeches mend nothing.
    >>  ............................................
    pt  Menos do que eu planejei. Sessenta anos ensinam que discurso não conserta nada.
    >>  ............................................
  athletic.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. At my age admitting it costs less than it used to.
    >>  ............................................
    pt  Que eu tinha sido injusto. Na minha idade admitir custa menos do que custava.
    >>  ............................................
  athletic.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' The plain sentences are the ones that work.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' As frases simples são as que funcionam.
    >>  ............................................
  confident.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. The planned version was a speech and speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei. A versão planejada era discurso e discurso não conserta.
    >>  ............................................
  confident.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words of it were true and the rest were scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  confident.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' That did more than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Isso fez mais que um ano de pensamento.
    >>  ............................................
  crabby.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. The planned version was a speech and speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei. A versão planejada era discurso e discurso não conserta.
    >>  ............................................
  crabby.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words of it were true and the rest were scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  crabby.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' That did more than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Isso fez mais que um ano de pensamento.
    >>  ............................................
  extroverted.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned, %1$s. The planned version was a speech and speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei, %1$s. A versão planejada era discurso e discurso não conserta.
    >>  ............................................
  extroverted.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words were true and the rest was scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  extroverted.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' It did more than a year of thinking had.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Fez mais que um ano de pensamento.
    >>  ............................................
  flirty.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned, %1$s. The planned version was a speech and speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei, %1$s. A versão planejada era discurso e discurso não conserta.
    >>  ............................................
  flirty.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words were true and the rest was scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  flirty.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' It did more than a year of thinking had.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Fez mais que um ano de pensamento.
    >>  ............................................
  friendly.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned, %1$s. The planned version was a speech and speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei, %1$s. A versão planejada era discurso e discurso não conserta.
    >>  ............................................
  friendly.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words were true and the rest was scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  friendly.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' It did more than a year of thinking had.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Fez mais que um ano de pensamento.
    >>  ............................................
  gloomy.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. The speech was armour and I put it down at the door.
    >>  ............................................
    pt  Menos do que eu planejei. O discurso era armadura e eu larguei na porta.
    >>  ............................................
  gloomy.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Saying it took longer to reach than it took to say.
    >>  ............................................
    pt  Que eu tinha sido injusto. Dizer levou mais tempo pra chegar do que pra sair.
    >>  ............................................
  gloomy.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' I'd been rehearsing cleverer things for a year.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Eu vinha ensaiando coisas mais espertas há um ano.
    >>  ............................................
  greedy.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. The planned version was a speech and speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei. A versão planejada era discurso e discurso não conserta.
    >>  ............................................
  greedy.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words of it were true and the rest were scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  greedy.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' That did more than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Isso fez mais que um ano de pensamento.
    >>  ............................................
  grumpy.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. The planned version was a speech and speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei. A versão planejada era discurso e discurso não conserta.
    >>  ............................................
  grumpy.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words of it were true and the rest were scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  grumpy.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' That did more than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Isso fez mais que um ano de pensamento.
    >>  ............................................
  introverted.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. Speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei. Discurso não conserta.
    >>  ............................................
  introverted.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair.
    >>  ............................................
    pt  Que eu tinha sido injusto.
    >>  ............................................
  introverted.dialogue.conversations.people.resume.ask_what_said/3
    en  'I've been avoiding you.' That was all of it.
    >>  ............................................
    pt  'Ando te evitando.' Foi tudo.
    >>  ............................................
  lazy.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. Sixty years teaches you that speeches mend nothing.
    >>  ............................................
    pt  Menos do que eu planejei. Sessenta anos ensinam que discurso não conserta nada.
    >>  ............................................
  lazy.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. At my age admitting it costs less than it used to.
    >>  ............................................
    pt  Que eu tinha sido injusto. Na minha idade admitir custa menos do que custava.
    >>  ............................................
  lazy.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' The plain sentences are the ones that work.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' As frases simples são as que funcionam.
    >>  ............................................
  odd.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. Speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei. Discurso não conserta.
    >>  ............................................
  odd.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair.
    >>  ............................................
    pt  Que eu tinha sido injusto.
    >>  ............................................
  odd.dialogue.conversations.people.resume.ask_what_said/3
    en  'I've been avoiding you.' That was all of it.
    >>  ............................................
    pt  'Ando te evitando.' Foi tudo.
    >>  ............................................
  peaceful.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. Sixty years teaches you that speeches mend nothing.
    >>  ............................................
    pt  Menos do que eu planejei. Sessenta anos ensinam que discurso não conserta nada.
    >>  ............................................
  peaceful.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. At my age admitting it costs less than it used to.
    >>  ............................................
    pt  Que eu tinha sido injusto. Na minha idade admitir custa menos do que custava.
    >>  ............................................
  peaceful.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' The plain sentences are the ones that work.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' As frases simples são as que funcionam.
    >>  ............................................
  peppy.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned! The planned version was a speech, and speeches mend nothing.
    >>  ............................................
    pt  Menos do que eu planejei! A versão planejada era discurso, e discurso não conserta nada.
    >>  ............................................
  peppy.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words of it were true and the rest was scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  peppy.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever at all. 'I've been avoiding you.' More use than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Mais útil que um ano de pensamento.
    >>  ............................................
  playful.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned! The planned version was a speech, and speeches mend nothing.
    >>  ............................................
    pt  Menos do que eu planejei! A versão planejada era discurso, e discurso não conserta nada.
    >>  ............................................
  playful.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words of it were true and the rest was scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  playful.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever at all. 'I've been avoiding you.' More use than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Mais útil que um ano de pensamento.
    >>  ............................................
  relaxed.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. Sixty years teaches you that speeches mend nothing.
    >>  ............................................
    pt  Menos do que eu planejei. Sessenta anos ensinam que discurso não conserta nada.
    >>  ............................................
  relaxed.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. At my age admitting it costs less than it used to.
    >>  ............................................
    pt  Que eu tinha sido injusto. Na minha idade admitir custa menos do que custava.
    >>  ............................................
  relaxed.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' The plain sentences are the ones that work.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' As frases simples são as que funcionam.
    >>  ............................................
  sensitive.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. The speech was armour and I put it down at the door.
    >>  ............................................
    pt  Menos do que eu planejei. O discurso era armadura e eu larguei na porta.
    >>  ............................................
  sensitive.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Saying it took longer to reach than it took to say.
    >>  ............................................
    pt  Que eu tinha sido injusto. Dizer levou mais tempo pra chegar do que pra sair.
    >>  ............................................
  sensitive.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever. 'I've been avoiding you.' I'd been rehearsing cleverer things for a year.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Eu vinha ensaiando coisas mais espertas há um ano.
    >>  ............................................
  shy.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned. Speeches don't mend.
    >>  ............................................
    pt  Menos do que eu planejei. Discurso não conserta.
    >>  ............................................
  shy.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair.
    >>  ............................................
    pt  Que eu tinha sido injusto.
    >>  ............................................
  shy.dialogue.conversations.people.resume.ask_what_said/3
    en  'I've been avoiding you.' That was all of it.
    >>  ............................................
    pt  'Ando te evitando.' Foi tudo.
    >>  ............................................
  upbeat.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned! The planned version was a speech, and speeches mend nothing.
    >>  ............................................
    pt  Menos do que eu planejei! A versão planejada era discurso, e discurso não conserta nada.
    >>  ............................................
  upbeat.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words of it were true and the rest was scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  upbeat.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever at all. 'I've been avoiding you.' More use than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Mais útil que um ano de pensamento.
    >>  ............................................
  witty.dialogue.conversations.people.resume.ask_what_said/1
    en  Less than I'd planned! The planned version was a speech, and speeches mend nothing.
    >>  ............................................
    pt  Menos do que eu planejei! A versão planejada era discurso, e discurso não conserta nada.
    >>  ............................................
  witty.dialogue.conversations.people.resume.ask_what_said/2
    en  That I'd been unfair. Two words of it were true and the rest was scaffolding.
    >>  ............................................
    pt  Que eu tinha sido injusto. Duas palavras eram verdade e o resto era andaime.
    >>  ............................................
  witty.dialogue.conversations.people.resume.ask_what_said/3
    en  Nothing clever at all. 'I've been avoiding you.' More use than a year of thinking.
    >>  ............................................
    pt  Nada esperto. 'Ando te evitando.' Mais útil que um ano de pensamento.
    >>  ............................................
```

</details>


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.people.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.people.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.people.resume.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.people.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.people.resume.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.resume.leave`: the villager accepts. Subject `people.repair`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.people.resume.followup / leave
```

> Written out in full under **`conversations.arc.people.resume.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.people.changed_my_mind.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `people`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.people.changed_my_mind` — e.g. "There is somebody here I disliked for two years on the strength of one afternoon, and I was wrong."


```text
POOL   dialogue key: dialogue.conversations.scene.people.changed_my_mind.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.people.changed_my_mind.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.people.changed_my_mind.respond   [23 chars]
    en  Somebody you misjudged.
    >>  ............................................
    pt  Alguém que você julgou mal.
    >>  ............................................
```


### Button `ask_what_changed_it` — "What changed your mind?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `people.changed_my_mind.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.people.changed_my_mind.ask_what_changed_it` — accepted phrasings: "what changed your mind"; "what changed your mind"; "what turned that around"
  - the message must contain one of: `changed`, `turned`
  - scored words: `changed`(1.8), `turned`(1.8), `around`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.people.changed_my_mind.respond.ask_what_changed_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.people.changed_my_mind.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.people.changed_my_mind.respond.ask_what_changed_it   [23 chars]
    en  What changed your mind?
    >>  ............................................
    pt  O que mudou sua opinião?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `people.reappraisal`)_
- Does: session `turn`
- Then opens: `conversations.scene.people.followup`
- …where the player's next choices will be: "That's the people, then."

```text
POOL   dialogue key: dialogue.conversations.scene.people.changed_my_mind.explained
WHO    VILLAGER — what the player reads after pressing "What changed your mind?"
       spoken on: conversations.scene.people.changed_my_mind.respond, button `ask_what_changed_it`
       leaves the player on: conversations.scene.people.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.changed_my_mind.open.explained`: the villager explains. Subject `people.reappraisal`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:people` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.people.changed_my_mind.explained/1   [103 chars]
    en  They told me why, unprompted, and did not ask me to feel differently about it, and that is what did it.
    >>  ............................................
    pt  Me contaram o porquê, sem eu pedir, e não me pediram para sentir diferente, e foi isso que virou.
    >>  ............................................
  dialogue.conversations.scene.people.changed_my_mind.explained/2   [110 chars]
    en  I watched them be kind to somebody who could do nothing for them, on a day nobody was looking, including them.
    >>  ............................................
    pt  Vi essa pessoa ser gentil com alguém que não podia fazer nada por ela, num dia em que ninguém olhava, nem ela.
    >>  ............................................
  dialogue.conversations.scene.people.changed_my_mind.explained/3   [76 chars]
    en  Nothing they did. I got tired myself, for a season, and recognised the walk.
    >>  ............................................
    pt  Nada que fizeram. Eu mesma me cansei, por uma estação, e reconheci o jeito de andar.
    >>  ............................................
```


### Button `say_that_takes_something` — "Changing your mind takes something."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `people.changed_my_mind.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.people.changed_my_mind.say_that_takes_something` — accepted phrasings: "changing your mind takes something"; "changing your mind takes something"; "that took some doing"
  - the message must contain one of: `changing`, `doing`, `takes`
  - scored words: `changing`(1.8), `doing`(1.8), `takes`(1.8), `something`(0.8), `took`(0.8), `some`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.people.changed_my_mind.respond.say_that_takes_something
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.people.changed_my_mind.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.people.changed_my_mind.respond.say_that_takes_something   [35 chars]
    en  Changing your mind takes something.
    >>  ............................................
    pt  Mudar de ideia exige algo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +1  _(recorded under topic `people.reappraisal`)_
- Does: session `turn`
- Then opens: `conversations.scene.people.followup`
- …where the player's next choices will be: "That's the people, then."

```text
POOL   dialogue key: dialogue.conversations.scene.people.changed_my_mind.acknowledged
WHO    VILLAGER — what the player reads after pressing "Changing your mind takes something."
       spoken on: conversations.scene.people.changed_my_mind.respond, button `say_that_takes_something`
       leaves the player on: conversations.scene.people.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.changed_my_mind.open.acknowledged`: the villager accepts. Subject `people.reappraisal`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:people` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.people.changed_my_mind.acknowledged/1   [91 chars]
    en  It takes admitting the two years, which is the expensive half and the half nobody mentions.
    >>  ............................................
    pt  Exige admitir os dois anos, que é a metade cara e a metade que ninguém menciona.
    >>  ............................................
  dialogue.conversations.scene.people.changed_my_mind.acknowledged/2   [93 chars]
    en  Thank you. I have not said any of this to them and I have thought about it about forty times.
    >>  ............................................
    pt  Obrigada. Eu não disse nada disso a essa pessoa e já pensei nisso umas quarenta vezes.
    >>  ............................................
  dialogue.conversations.scene.people.changed_my_mind.acknowledged/3   [101 chars]
    en  It is the only part of getting older I would recommend to anybody. Everything else about it is knees.
    >>  ............................................
    pt  É a única parte de envelhecer que eu recomendaria a alguém. Todo o resto é joelho.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `people.changed_my_mind.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.people.changed_my_mind.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.people.changed_my_mind.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.people.changed_my_mind.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.people.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.people.changed_my_mind.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.scene.leaving`: the villager accepts. Subject `people.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.people.followup / leave; conversations.scene.people.the_newcomer.respond / leave
```

```text
  dialogue.conversations.scene.people.leaving/1   [23 chars]
    en  They are what they are.
    >>  ............................................
    pt  São o que são.
    >>  ............................................
  dialogue.conversations.scene.people.leaving/2   [31 chars]
    en  Right. That is the lot of them.
    >>  ............................................
    pt  Certo. É essa a turma.
    >>  ............................................
  dialogue.conversations.scene.people.leaving/3   [31 chars]
    en  You will make your own mind up.
    >>  ............................................
    pt  Você vai formar sua própria opinião.
    >>  ............................................
```

---


## `conversations.scene.people.followup`

**Reached from 4 route(s):** `conversations.scene.people.changed_my_mind.respond` / `ask_what_changed_it`; `conversations.scene.people.changed_my_mind.respond` / `say_that_takes_something`; `conversations.scene.people.the_newcomer.respond` / `urge_the_invitation`; `conversations.scene.people.the_newcomer.respond` / `ask_what_they_are_like`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.people.changed_my_mind.acknowledged` — e.g. "It takes admitting the two years, which is the expensive half and the half nobody mentions."
- `conversations.scene.people.changed_my_mind.explained` — e.g. "They told me why, unprompted, and did not ask me to feel differently about it, and that is what did it."
- `conversations.scene.people.the_newcomer.described` — e.g. "Quiet in the way that is being careful rather than being unfriendly, and the lane has read it as the second."
- `conversations.scene.people.the_newcomer.resolved` — e.g. "This week. It is four months late and four months late is better than the alternative, which is a fifth month."


```text
POOL   dialogue key: dialogue.conversations.scene.people.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.people.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.people.followup   [25 chars]
    en  Anything else about them?
    >>  ............................................
    pt  Mais alguma coisa sobre eles?
    >>  ............................................
```


### Button `leave` — "That's the people, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:people.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.people.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.people.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.people.followup.leave   [24 chars]
    en  That's the people, then.
    >>  ............................................
    pt  É o pessoal, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.people.leaving
WHO    VILLAGER — what the player reads after pressing "That's the people, then."
       spoken on: conversations.scene.people.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.scene.leaving`: the villager accepts. Subject `people.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.people.changed_my_mind.respond / leave; conversations.scene.people.the_newcomer.respond / leave
```

> Written out in full under **`conversations.scene.people.changed_my_mind.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.people.the_newcomer.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `people`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.people.the_newcomer` — e.g. "Somebody arrived in the spring and the lane has been very polite to them for four months, which is its own kind of cold."


```text
POOL   dialogue key: dialogue.conversations.scene.people.the_newcomer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.people.the_newcomer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.people.the_newcomer.respond   [12 chars]
    en  The new one.
    >>  ............................................
    pt  A pessoa nova.
    >>  ............................................
```


### Button `urge_the_invitation` — "Then ask them to supper."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `people.the_newcomer.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.people.the_newcomer.urge_the_invitation` — accepted phrasings: "then ask them to supper"; "then ask them to supper"; "invite them round yourself"
  - the message must contain one of: `supper`, `invite`
  - scored words: `supper`(1.8), `invite`(1.8), `ask`(0.8), `round`(0.8), `yourself`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.people.the_newcomer.respond.urge_the_invitation
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.people.the_newcomer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.people.the_newcomer.respond.urge_the_invitation   [24 chars]
    en  Then ask them to supper.
    >>  ............................................
    pt  Então convide para jantar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.people.newcomer.urged`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, warmth +3  _(recorded under topic `people.new_arrival`)_
- Does: session `turn`
- Then opens: `conversations.scene.people.followup`
- …where the player's next choices will be: "That's the people, then."

```text
POOL   dialogue key: dialogue.conversations.scene.people.the_newcomer.resolved
WHO    VILLAGER — what the player reads after pressing "Then ask them to supper."
       spoken on: conversations.scene.people.the_newcomer.respond, button `urge_the_invitation`
       leaves the player on: conversations.scene.people.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.the_newcomer.open.resolved`: the villager accepts. Subject `people.new_arrival`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:people` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.people.the_newcomer.resolved/1   [110 chars]
    en  This week. It is four months late and four months late is better than the alternative, which is a fifth month.
    >>  ............................................
    pt  Esta semana. Está quatro meses atrasado, e quatro meses atrasado é melhor que a alternativa, que é um quinto mês.
    >>  ............................................
  dialogue.conversations.scene.people.the_newcomer.resolved/2   [117 chars]
    en  Yes. Somebody has to be first and everybody has been waiting to be second, which is how a lane stays cold for a year.
    >>  ............................................
    pt  Sim. Alguém tem que ser o primeiro e todo mundo está esperando ser o segundo, que é como uma viela fica fria por um ano.
    >>  ............................................
  dialogue.conversations.scene.people.the_newcomer.resolved/3   [120 chars]
    en  I will, and I will not make it a welcome. A welcome is a performance. Supper is just supper and that is the whole point.
    >>  ............................................
    pt  Vou, e não vou fazer disso boas-vindas. Boas-vindas é encenação. Jantar é só jantar e é esse o ponto.
    >>  ............................................
```


### Button `ask_what_they_are_like` — "What are they like?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `people.the_newcomer.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.people.the_newcomer.ask_what_they_are_like` — accepted phrasings: "what are they like"; "what are they like"; "what sort of person are they"
  - the message must contain one of: `like`, `sort`
  - scored words: `like`(1.8), `sort`(1.8), `person`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.people.the_newcomer.respond.ask_what_they_are_like
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.people.the_newcomer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.people.the_newcomer.respond.ask_what_they_are_like   [19 chars]
    en  What are they like?
    >>  ............................................
    pt  Como eles são?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `people.new_arrival`)_
- Does: session `turn`
- Then opens: `conversations.scene.people.followup`
- …where the player's next choices will be: "That's the people, then."

```text
POOL   dialogue key: dialogue.conversations.scene.people.the_newcomer.described
WHO    VILLAGER — what the player reads after pressing "What are they like?"
       spoken on: conversations.scene.people.the_newcomer.respond, button `ask_what_they_are_like`
       leaves the player on: conversations.scene.people.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.the_newcomer.open.described`: the villager reports. Subject `people.new_arrival`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:people` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.people.the_newcomer.described/1   [108 chars]
    en  Quiet in the way that is being careful rather than being unfriendly, and the lane has read it as the second.
    >>  ............................................
    pt  Reservados do jeito de quem está sendo cuidadoso, não de quem é antipático, e a viela leu como a segunda coisa.
    >>  ............................................
  dialogue.conversations.scene.people.the_newcomer.described/2   [108 chars]
    en  Competent, and slightly too eager to prove it, which is what everybody is like in their first year anywhere.
    >>  ............................................
    pt  Competentes, e um pouco ansiosos demais para provar, que é como todo mundo é no primeiro ano em qualquer lugar.
    >>  ............................................
  dialogue.conversations.scene.people.the_newcomer.described/3   [106 chars]
    en  I do not know yet, and that is rather my point. Four months and nobody in this lane could tell you either.
    >>  ............................................
    pt  Eu ainda não sei, e é justamente esse o meu ponto. Quatro meses e ninguém nesta viela saberia dizer também.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `people.the_newcomer.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.people.the_newcomer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.people.the_newcomer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.people.the_newcomer.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.people.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.people.the_newcomer.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.scene.leaving`: the villager accepts. Subject `people.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.people.changed_my_mind.respond / leave; conversations.scene.people.followup / leave
```

> Written out in full under **`conversations.scene.people.changed_my_mind.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.people.again.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `people`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.again` — e.g. "I said my piece about the neighbors. Any more and it becomes gossip, which is a different question."


```text
POOL   dialogue key: dialogue.conversations.topic.people.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.again.respond   [29 chars]
    en  We talked about them already.
    >>  ............................................
    pt  Já falamos deles.
    >>  ............................................
```


### Button `apologize` — "Sorry — asked already."

*stance family `candor` · tone `gentle` · answers the beat(s) `people.again.to.people.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.again.apologize` — accepted phrasings: "sorry, asked already"; "sorry, i already asked"; "my mistake"
  - the message must contain one of: `already`, `sorry`, `asked`
  - scored words: `already`(1.5), `sorry`(1.2), `asked`(0.8), `neighbours`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.people.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.again.respond.apologize   [22 chars]
    en  Sorry — asked already.
    >>  ............................................
    pt  Desculpa — já perguntei.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `people.again.apologize`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — asked already."
       spoken on: conversations.topic.people.again.respond, button `apologize`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.again.apologize.terminal`: the villager accepts. Subject `people.repeat`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.people.again.apologize/1   [13 chars]
    en  No harm done.
    >>  ............................................
    pt  Sem problema.
    >>  ............................................
  dialogue.conversations.people.again.apologize/2   [40 chars]
    en  You did, aye. They've not changed since.
    >>  ............................................
    pt  Você já, é. Eles não mudaram desde então.
    >>  ............................................
  dialogue.conversations.people.again.apologize/3   [19 chars]
    en  It's alright, %1$s.
    >>  ............................................
    pt  Tudo bem, %1$s.
    >>  ............................................
```


### Button `press` — "Tell me anyway."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `people.again.to.people.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.again.press` — accepted phrasings: "tell me anyway"; "go on, again"; "again, please"
  - the message must contain one of: `anyway`, `again`
  - scored words: `anyway`(1.5), `again`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.people.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.again.respond.press   [15 chars]
    en  Tell me anyway.
    >>  ............................................
    pt  Me conta mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `people.again.press`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `people.again.press`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me anyway."
       spoken on: conversations.topic.people.again.respond, button `press`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.again.press.terminal`: the villager resists. Subject `people.repeat`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.people.again.press/1   [33 chars]
    en  Same neighbours, same complaints.
    >>  ............................................
    pt  Os mesmos vizinhos, as mesmas reclamações.
    >>  ............................................
  dialogue.conversations.people.again.press/2   [42 chars]
    en  Twice in a day is how rumours start, %1$s.
    >>  ............................................
    pt  Duas vezes num dia é assim que os boatos começam, %1$s.
    >>  ............................................
  dialogue.conversations.people.again.press/3   [52 chars]
    en  ...Fine. Still difficult. Still mine to live beside.
    >>  ............................................
    pt  ...Tá. Ainda difíceis. Ainda meus vizinhos.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `people.again.to.people.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.again.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.people.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.topic.people.again.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.again.leave.terminal`: the villager accepts. Subject `people.repeat`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.people.again.leave/1   [17 chars]
    en  Aye. Another day.
    >>  ............................................
    pt  Tá. Outro dia.
    >>  ............................................
  dialogue.conversations.people.again.leave/2   [10 chars]
    en  Safe home.
    >>  ............................................
    pt  Volte bem.
    >>  ............................................
  dialogue.conversations.people.again.leave/3   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

---


## `conversations.topic.people.fond.followup`

**Reached from 3 route(s):** `conversations.topic.people.fond.respond` / `agree`; `conversations.topic.people.fond.respond` / `ask_favourite`; `conversations.topic.people.fond.respond` / `tease`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.fond.agree` — e.g. "Then you've had the proper welcome. Not everyone does."
- `conversations.people.fond.favourite` — e.g. "The baker, and I'll deny it if it gets back to the miller."
- `conversations.people.fond.tease` — e.g. "Especially those. A returned tool means nothing; a borrowed one means trust."


```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.fond.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.fond.followup   [32 chars]
    en  They're worth the noise, anyway.
    >>  ............................................
    pt  Eles valem o barulho, enfim.
    >>  ............................................
```


### Button `share_own` — "I've been made welcome here too."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `people.fond.agreed`, `people.fond.favourite`, `people.fond.teased` · offered only once the villager has actually said `people:fond`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.fond.share_own` — accepted phrasings: "i have been made welcome here too"; "they made me welcome as well"; "i have been treated kindly here"
  - the message must contain one of: `welcome`, `welcomed`, `kindly`
  - scored words: `welcome`(1.5), `welcomed`(1.5), `kindly`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.followup.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.fond.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.fond.followup.share_own   [32 chars]
    en  I've been made welcome here too.
    >>  ............................................
    pt  Eu também fui bem recebido aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.fond.share_own`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +2, warmth +3  _(recorded under topic `people.fond.share_own`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.fond.share_own
WHO    VILLAGER — what the player reads after pressing "I've been made welcome here too."
       spoken on: conversations.topic.people.fond.followup, button `share_own`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.fond.share_own`: the villager accepts. Subject `people.affection`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.fond.share_own/1   [49 chars]
    en  Of course you have. That's what the place is for.
    >>  ............................................
    pt  Claro que foi. É pra isso que o lugar serve.
    >>  ............................................
  dialogue.conversations.people.fond.share_own/2   [80 chars]
    en  Good. Then somebody did their job, and I'd like to know who so I can thank them.
    >>  ............................................
    pt  Bom. Então alguém fez o trabalho, e eu quero saber quem pra agradecer.
    >>  ............................................
  dialogue.conversations.people.fond.share_own/3   [72 chars]
    en  You have, %1$s. I've heard three people say your name kindly this month.
    >>  ............................................
    pt  Foi, %1$s. Ouvi três pessoas falarem seu nome com carinho este mês.
    >>  ............................................
```


### Button `ask_how_long` — "How long have you known them all?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `people.fond.agreed`, `people.fond.favourite`, `people.fond.teased` · offered only once the villager has actually said `people:fond`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.fond.how_long` — accepted phrasings: "how long have you known them all"; "how long have you lived here"; "how long have you known everyone"
  - the message must contain one of: `known`, `long`, `lived`
  - scored words: `known`(1.5), `long`(1.0), `lived`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.followup.ask_how_long
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.fond.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.fond.followup.ask_how_long   [33 chars]
    en  How long have you known them all?
    >>  ............................................
    pt  Faz quanto tempo que você conhece todos eles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `people.fond.how_long`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.fond.how_long
WHO    VILLAGER — what the player reads after pressing "How long have you known them all?"
       spoken on: conversations.topic.people.fond.followup, button `ask_how_long`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.fond.how_long`: the villager reminisces. Subject `people.affection`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.fond.how_long/1   [69 chars]
    en  All my life, most of them. I watched half this village learn to walk.
    >>  ............................................
    pt  A vida toda, a maioria. Eu vi metade deste vilarejo aprender a andar.
    >>  ............................................
  dialogue.conversations.people.fond.how_long/2   [68 chars]
    en  Long enough to have forgiven every one of them at least twice, %1$s.
    >>  ............................................
    pt  Tempo bastante pra ter perdoado cada um deles pelo menos duas vezes, %1$s.
    >>  ............................................
  dialogue.conversations.people.fond.how_long/3   [71 chars]
    en  Twenty years. You stop counting and start belonging somewhere in there.
    >>  ............................................
    pt  Vinte anos. Em algum ponto você para de contar e começa a pertencer.
    >>  ............................................
```


### Button `leave` — "I'll leave you to them."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.fond.agreed`, `people.fond.favourite`, `people.fond.teased` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.fond.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.fond.followup.leave   [23 chars]
    en  I'll leave you to them.
    >>  ............................................
    pt  Vou deixar você com eles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.fond.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to them."
       spoken on: conversations.topic.people.fond.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.fond.left`: the villager accepts. Subject `people.affection`, polarity `positive`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.fond.respond / leave
```

```text
  dialogue.conversations.people.fond.leave/1   [58 chars]
    en  So it is. One of them will want something within the hour.
    >>  ............................................
    pt  É assim mesmo. Um deles vai querer algo dentro de uma hora.
    >>  ............................................
  dialogue.conversations.people.fond.leave/2   [48 chars]
    en  Off you go, %1$s. Say hello to whoever you pass.
    >>  ............................................
    pt  Pode ir, %1$s. Diga oi pra quem você cruzar.
    >>  ............................................
  dialogue.conversations.people.fond.leave/3   [18 chars]
    en  We'll speak again.
    >>  ............................................
    pt  A gente se fala.
    >>  ............................................
```

---


## `conversations.topic.people.fond.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `people`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.warm` — e.g. "I love them. All of them. Even the loud ones. ESPECIALLY the loud ones."


```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.fond.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.fond.respond   [45 chars]
    en  That's the neighbours, and I'd not swap them.
    >>  ............................................
    pt  São esses os vizinhos, e eu não trocaria.
    >>  ............................................
```


### Button `agree` — "They've been good to me as well."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `people.warm_view` · offered only once the villager has actually said `people:fond`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.fond.agreed` — accepted phrasings: "they have been good to me as well"; "they have been kind to me too"; "i have had a good welcome here"
  - the message must contain one of: `kind`, `welcome`
  - scored words: `good`(0.6), `kind`(1.2), `welcome`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.respond.agree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.fond.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.fond.respond.agree   [32 chars]
    en  They've been good to me as well.
    >>  ............................................
    pt  Eles também foram bons comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.fond.agree`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +2  _(recorded under topic `people.fond.agreed`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.fond.followup`
- …where the player's next choices will be: "I've been made welcome here too." | "How long have you known them all?" | "I'll leave you to them."

```text
POOL   dialogue key: dialogue.conversations.people.fond.agree
WHO    VILLAGER — what the player reads after pressing "They've been good to me as well."
       spoken on: conversations.topic.people.fond.respond, button `agree`
       leaves the player on: conversations.topic.people.fond.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.fond.agreed`: the villager accepts. Subject `people.affection`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `people:fond` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.fond.agree/1   [54 chars]
    en  Then you've had the proper welcome. Not everyone does.
    >>  ............................................
    pt  Então você teve a recepção adequada. Nem todo mundo tem.
    >>  ............................................
  dialogue.conversations.people.fond.agree/2   [76 chars]
    en  Good. They've had practice — you're not the first stray we've adopted, %1$s.
    >>  ............................................
    pt  Bom. Eles têm prática — você não é o primeiro perdido que a gente adota, %1$s.
    >>  ............................................
  dialogue.conversations.people.fond.agree/3   [73 chars]
    en  Of course they have. It's the one thing this place is unarguably good at.
    >>  ............................................
    pt  Claro que foram. É a única coisa em que este lugar é inegavelmente bom.
    >>  ............................................
```


### Button `ask_favourite` — "Who's your favourite, then?"

*stance family `curiosity` · tone `playful` · outcome `engaged` · answers the beat(s) `people.warm_view` · offered only once the villager has actually said `people:fond`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.fond.favourite` — accepted phrasings: "who is your favourite then"; "who do you like best"; "which of them is your favourite"
  - the message must contain one of: `favourite`, `favorite`
  - scored words: `favourite`(1.5), `favorite`(1.5), `best`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.respond.ask_favourite
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.fond.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.fond.respond.ask_favourite   [27 chars]
    en  Who's your favourite, then?
    >>  ............................................
    pt  E qual é o seu preferido?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `people.fond.favourite`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.fond.followup`
- …where the player's next choices will be: "I've been made welcome here too." | "How long have you known them all?" | "I'll leave you to them."

```text
POOL   dialogue key: dialogue.conversations.people.fond.favourite
WHO    VILLAGER — what the player reads after pressing "Who's your favourite, then?"
       spoken on: conversations.topic.people.fond.respond, button `ask_favourite`
       leaves the player on: conversations.topic.people.fond.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.fond.favourite`: the villager discloses. Subject `people.affection`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `people:fond` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.fond.favourite/1   [58 chars]
    en  The baker, and I'll deny it if it gets back to the miller.
    >>  ............................................
    pt  O padeiro, e eu vou negar se chegar aos ouvidos do moleiro.
    >>  ............................................
  dialogue.conversations.people.fond.favourite/2   [62 chars]
    en  That's a trap and I'm not walking into it, %1$s. ...The baker.
    >>  ............................................
    pt  Isso é uma armadilha e eu não vou cair nela, %1$s. ...O padeiro.
    >>  ............................................
  dialogue.conversations.people.fond.favourite/3   [65 chars]
    en  Whoever's least trouble that week. It rotates. Currently: nobody.
    >>  ............................................
    pt  Quem der menos trabalho na semana. Vai revezando. Atualmente: ninguém.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller. She was kind when nobody was.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro. Ela foi gentil quando ninguém era.
    >>  ............................................
  anxious.dialogue.conversations.people.fond.favourite/2
    en  The baker. It sounds like nothing. It was not nothing, that winter.
    >>  ............................................
    pt  A padeira. Soa como nada. Não era nada, naquele inverno.
    >>  ............................................
  anxious.dialogue.conversations.people.fond.favourite/3
    en  The baker. I've not said that out loud before and I feel oddly exposed, %1$s.
    >>  ............................................
    pt  A padeira. Nunca disse isso em voz alta e me sinto estranhamente exposto, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller. Some things are best left unsaid at the mill.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro. Algumas coisas é melhor não dizer no moinho.
    >>  ............................................
  athletic.dialogue.conversations.people.fond.favourite/2
    en  The baker. Twenty years of the same answer and I see no reason to change it.
    >>  ............................................
    pt  A padeira. Vinte anos da mesma resposta e eu não vejo motivo pra mudar.
    >>  ............................................
  athletic.dialogue.conversations.people.fond.favourite/3
    en  The baker. It's an old fondness and old ones wear well.
    >>  ............................................
    pt  A padeira. É um carinho antigo e os antigos se conservam bem.
    >>  ............................................
  confident.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro.
    >>  ............................................
  confident.dialogue.conversations.people.fond.favourite/2
    en  The baker. Don't repeat that.
    >>  ............................................
    pt  A padeira. Não repita.
    >>  ............................................
  confident.dialogue.conversations.people.fond.favourite/3
    en  The baker. That's the answer and it's not for general circulation.
    >>  ............................................
    pt  A padeira. É a resposta e não é pra circulação geral.
    >>  ............................................
  crabby.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro.
    >>  ............................................
  crabby.dialogue.conversations.people.fond.favourite/2
    en  The baker. Don't repeat that.
    >>  ............................................
    pt  A padeira. Não repita.
    >>  ............................................
  crabby.dialogue.conversations.people.fond.favourite/3
    en  The baker. That's the answer and it's not for general circulation.
    >>  ............................................
    pt  A padeira. É a resposta e não é pra circulação geral.
    >>  ............................................
  extroverted.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller, %1$s.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.people.fond.favourite/2
    en  The baker. She kept me fed one bad winter and never mentioned it once.
    >>  ............................................
    pt  A padeira. Ela me alimentou num inverno ruim e nunca mencionou.
    >>  ............................................
  extroverted.dialogue.conversations.people.fond.favourite/3
    en  The baker. Ask me again in a year and it'll still be the baker.
    >>  ............................................
    pt  A padeira. Me pergunte em um ano e ainda vai ser a padeira.
    >>  ............................................
  flirty.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller, %1$s.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.people.fond.favourite/2
    en  The baker. She kept me fed one bad winter and never mentioned it once.
    >>  ............................................
    pt  A padeira. Ela me alimentou num inverno ruim e nunca mencionou.
    >>  ............................................
  flirty.dialogue.conversations.people.fond.favourite/3
    en  The baker. Ask me again in a year and it'll still be the baker.
    >>  ............................................
    pt  A padeira. Me pergunte em um ano e ainda vai ser a padeira.
    >>  ............................................
  friendly.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller, %1$s.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.people.fond.favourite/2
    en  The baker. She kept me fed one bad winter and never mentioned it once.
    >>  ............................................
    pt  A padeira. Ela me alimentou num inverno ruim e nunca mencionou.
    >>  ............................................
  friendly.dialogue.conversations.people.fond.favourite/3
    en  The baker. Ask me again in a year and it'll still be the baker.
    >>  ............................................
    pt  A padeira. Me pergunte em um ano e ainda vai ser a padeira.
    >>  ............................................
  gloomy.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller. She was kind when nobody was.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro. Ela foi gentil quando ninguém era.
    >>  ............................................
  gloomy.dialogue.conversations.people.fond.favourite/2
    en  The baker. It sounds like nothing. It was not nothing, that winter.
    >>  ............................................
    pt  A padeira. Soa como nada. Não era nada, naquele inverno.
    >>  ............................................
  gloomy.dialogue.conversations.people.fond.favourite/3
    en  The baker. I've not said that out loud before and I feel oddly exposed, %1$s.
    >>  ............................................
    pt  A padeira. Nunca disse isso em voz alta e me sinto estranhamente exposto, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro.
    >>  ............................................
  greedy.dialogue.conversations.people.fond.favourite/2
    en  The baker. Don't repeat that.
    >>  ............................................
    pt  A padeira. Não repita.
    >>  ............................................
  greedy.dialogue.conversations.people.fond.favourite/3
    en  The baker. That's the answer and it's not for general circulation.
    >>  ............................................
    pt  A padeira. É a resposta e não é pra circulação geral.
    >>  ............................................
  grumpy.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro.
    >>  ............................................
  grumpy.dialogue.conversations.people.fond.favourite/2
    en  The baker. Don't repeat that.
    >>  ............................................
    pt  A padeira. Não repita.
    >>  ............................................
  grumpy.dialogue.conversations.people.fond.favourite/3
    en  The baker. That's the answer and it's not for general circulation.
    >>  ............................................
    pt  A padeira. É a resposta e não é pra circulação geral.
    >>  ............................................
  introverted.dialogue.conversations.people.fond.favourite/1
    en  The baker. I'll deny it if it gets back to the miller.
    >>  ............................................
    pt  A padeira. Eu nego se chegar ao moleiro.
    >>  ............................................
  introverted.dialogue.conversations.people.fond.favourite/2
    en  The baker.
    >>  ............................................
    pt  A padeira.
    >>  ............................................
  introverted.dialogue.conversations.people.fond.favourite/3
    en  The baker. She doesn't talk much either.
    >>  ............................................
    pt  A padeira. Ela também não fala muito.
    >>  ............................................
  lazy.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller. Some things are best left unsaid at the mill.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro. Algumas coisas é melhor não dizer no moinho.
    >>  ............................................
  lazy.dialogue.conversations.people.fond.favourite/2
    en  The baker. Twenty years of the same answer and I see no reason to change it.
    >>  ............................................
    pt  A padeira. Vinte anos da mesma resposta e eu não vejo motivo pra mudar.
    >>  ............................................
  lazy.dialogue.conversations.people.fond.favourite/3
    en  The baker. It's an old fondness and old ones wear well.
    >>  ............................................
    pt  A padeira. É um carinho antigo e os antigos se conservam bem.
    >>  ............................................
  odd.dialogue.conversations.people.fond.favourite/1
    en  The baker. I'll deny it if it gets back to the miller.
    >>  ............................................
    pt  A padeira. Eu nego se chegar ao moleiro.
    >>  ............................................
  odd.dialogue.conversations.people.fond.favourite/2
    en  The baker.
    >>  ............................................
    pt  A padeira.
    >>  ............................................
  odd.dialogue.conversations.people.fond.favourite/3
    en  The baker. She doesn't talk much either.
    >>  ............................................
    pt  A padeira. Ela também não fala muito.
    >>  ............................................
  peaceful.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller. Some things are best left unsaid at the mill.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro. Algumas coisas é melhor não dizer no moinho.
    >>  ............................................
  peaceful.dialogue.conversations.people.fond.favourite/2
    en  The baker. Twenty years of the same answer and I see no reason to change it.
    >>  ............................................
    pt  A padeira. Vinte anos da mesma resposta e eu não vejo motivo pra mudar.
    >>  ............................................
  peaceful.dialogue.conversations.people.fond.favourite/3
    en  The baker. It's an old fondness and old ones wear well.
    >>  ............................................
    pt  A padeira. É um carinho antigo e os antigos se conservam bem.
    >>  ............................................
  peppy.dialogue.conversations.people.fond.favourite/1
    en  The baker! And I'll deny it absolutely if it gets back to the miller.
    >>  ............................................
    pt  A padeira! E eu nego categoricamente se chegar ao moleiro.
    >>  ............................................
  peppy.dialogue.conversations.people.fond.favourite/2
    en  The baker. Don't tell the miller. Actually, do — I'd enjoy watching that.
    >>  ............................................
    pt  A padeira. Não conte ao moleiro. Na verdade, conte — eu adoraria assistir.
    >>  ............................................
  peppy.dialogue.conversations.people.fond.favourite/3
    en  The baker, obviously. Everyone says the miller and everyone is wrong.
    >>  ............................................
    pt  A padeira, obviamente. Todos dizem o moleiro e todos estão errados.
    >>  ............................................
  playful.dialogue.conversations.people.fond.favourite/1
    en  The baker! And I'll deny it absolutely if it gets back to the miller.
    >>  ............................................
    pt  A padeira! E eu nego categoricamente se chegar ao moleiro.
    >>  ............................................
  playful.dialogue.conversations.people.fond.favourite/2
    en  The baker. Don't tell the miller. Actually, do — I'd enjoy watching that.
    >>  ............................................
    pt  A padeira. Não conte ao moleiro. Na verdade, conte — eu adoraria assistir.
    >>  ............................................
  playful.dialogue.conversations.people.fond.favourite/3
    en  The baker, obviously. Everyone says the miller and everyone is wrong.
    >>  ............................................
    pt  A padeira, obviamente. Todos dizem o moleiro e todos estão errados.
    >>  ............................................
  relaxed.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller. Some things are best left unsaid at the mill.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro. Algumas coisas é melhor não dizer no moinho.
    >>  ............................................
  relaxed.dialogue.conversations.people.fond.favourite/2
    en  The baker. Twenty years of the same answer and I see no reason to change it.
    >>  ............................................
    pt  A padeira. Vinte anos da mesma resposta e eu não vejo motivo pra mudar.
    >>  ............................................
  relaxed.dialogue.conversations.people.fond.favourite/3
    en  The baker. It's an old fondness and old ones wear well.
    >>  ............................................
    pt  A padeira. É um carinho antigo e os antigos se conservam bem.
    >>  ............................................
  sensitive.dialogue.conversations.people.fond.favourite/1
    en  The baker, and I'll deny it if it gets back to the miller. She was kind when nobody was.
    >>  ............................................
    pt  A padeira, e eu nego se chegar ao moleiro. Ela foi gentil quando ninguém era.
    >>  ............................................
  sensitive.dialogue.conversations.people.fond.favourite/2
    en  The baker. It sounds like nothing. It was not nothing, that winter.
    >>  ............................................
    pt  A padeira. Soa como nada. Não era nada, naquele inverno.
    >>  ............................................
  sensitive.dialogue.conversations.people.fond.favourite/3
    en  The baker. I've not said that out loud before and I feel oddly exposed, %1$s.
    >>  ............................................
    pt  A padeira. Nunca disse isso em voz alta e me sinto estranhamente exposto, %1$s.
    >>  ............................................
  shy.dialogue.conversations.people.fond.favourite/1
    en  The baker. I'll deny it if it gets back to the miller.
    >>  ............................................
    pt  A padeira. Eu nego se chegar ao moleiro.
    >>  ............................................
  shy.dialogue.conversations.people.fond.favourite/2
    en  The baker.
    >>  ............................................
    pt  A padeira.
    >>  ............................................
  shy.dialogue.conversations.people.fond.favourite/3
    en  The baker. She doesn't talk much either.
    >>  ............................................
    pt  A padeira. Ela também não fala muito.
    >>  ............................................
  upbeat.dialogue.conversations.people.fond.favourite/1
    en  The baker! And I'll deny it absolutely if it gets back to the miller.
    >>  ............................................
    pt  A padeira! E eu nego categoricamente se chegar ao moleiro.
    >>  ............................................
  upbeat.dialogue.conversations.people.fond.favourite/2
    en  The baker. Don't tell the miller. Actually, do — I'd enjoy watching that.
    >>  ............................................
    pt  A padeira. Não conte ao moleiro. Na verdade, conte — eu adoraria assistir.
    >>  ............................................
  upbeat.dialogue.conversations.people.fond.favourite/3
    en  The baker, obviously. Everyone says the miller and everyone is wrong.
    >>  ............................................
    pt  A padeira, obviamente. Todos dizem o moleiro e todos estão errados.
    >>  ............................................
  witty.dialogue.conversations.people.fond.favourite/1
    en  The baker! And I'll deny it absolutely if it gets back to the miller.
    >>  ............................................
    pt  A padeira! E eu nego categoricamente se chegar ao moleiro.
    >>  ............................................
  witty.dialogue.conversations.people.fond.favourite/2
    en  The baker. Don't tell the miller. Actually, do — I'd enjoy watching that.
    >>  ............................................
    pt  A padeira. Não conte ao moleiro. Na verdade, conte — eu adoraria assistir.
    >>  ............................................
  witty.dialogue.conversations.people.fond.favourite/3
    en  The baker, obviously. Everyone says the miller and everyone is wrong.
    >>  ............................................
    pt  A padeira, obviamente. Todos dizem o moleiro e todos estão errados.
    >>  ............................................
```

</details>


### Button `tease` — "Even the ones who borrow your tools?"

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `people.warm_view` · offered only once the villager has actually said `people:fond`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.fond.teased` — accepted phrasings: "even the ones who borrow your tools"; "even the ones who never give things back"; "even the borrowers"
  - the message must contain one of: `borrow`, `tools`
  - scored words: `borrow`(1.5), `tools`(1.5), `even`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.respond.tease
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.fond.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.fond.respond.tease   [36 chars]
    en  Even the ones who borrow your tools?
    >>  ............................................
    pt  Até os que pegam suas ferramentas emprestadas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.fond.tease`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -1, warmth +3  _(recorded under topic `people.fond.teased`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.fond.followup`
- …where the player's next choices will be: "I've been made welcome here too." | "How long have you known them all?" | "I'll leave you to them."

```text
POOL   dialogue key: dialogue.conversations.people.fond.tease
WHO    VILLAGER — what the player reads after pressing "Even the ones who borrow your tools?"
       spoken on: conversations.topic.people.fond.respond, button `tease`
       leaves the player on: conversations.topic.people.fond.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.fond.teased`: the villager accepts. Subject `people.affection`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `people:fond` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.fond.tease/1   [76 chars]
    en  Especially those. A returned tool means nothing; a borrowed one means trust.
    >>  ............................................
    pt  Especialmente esses. Uma ferramenta devolvida não diz nada; uma emprestada diz confiança.
    >>  ............................................
  dialogue.conversations.people.fond.tease/2   [32 chars]
    en  Ha! You've met the miller, then.
    >>  ............................................
    pt  Ha! Então você conheceu o moleiro.
    >>  ............................................
  dialogue.conversations.people.fond.tease/3   [68 chars]
    en  Even them, %1$s. I've a shovel out there living a whole second life.
    >>  ............................................
    pt  Até esses, %1$s. Tenho uma pá por aí vivendo uma segunda vida inteira.
    >>  ............................................
```


### Button `leave` — "I'll not stir it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.warm_view` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.fond.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.fond.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.fond.respond.leave   [17 chars]
    en  I'll not stir it.
    >>  ............................................
    pt  Não vou remexer nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.fond.leave
WHO    VILLAGER — what the player reads after pressing "I'll not stir it."
       spoken on: conversations.topic.people.fond.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.fond.left`: the villager accepts. Subject `people.affection`, polarity `positive`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.fond.followup / leave
```

> Written out in full under **`conversations.topic.people.fond.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.people.gossip.followup`

**Reached from 2 route(s):** `conversations.topic.people.mixed.respond` / `ask_example`; `conversations.topic.people.sour.respond` / `ask_example`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.respond.ask_example` — e.g. "You want the specifics. Alright — but this stays here."


```text
POOL   dialogue key: dialogue.conversations.topic.people.gossip.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.gossip.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.gossip.followup   [21 chars]
    en  ...But it stays here.
    >>  ............................................
    pt  ...Mas fica por aqui.
    >>  ............................................
```


### Button `push_gossip` — "Go on — who's the worst?"

*stance family `boundary_push` · tone `blunt` · outcome `rebuffed` · answers the beat(s) `people.example_offered` · offered only once the villager has actually said `gossip:offered`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.gossip.refused` — accepted phrasings: "go on, who is the worst"; "name names then"; "who is the worst of them"
  - the message must contain one of: `worst`, `names`
  - scored words: `worst`(1.5), `names`(1.2), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.people.gossip.followup.push_gossip
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.gossip.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.gossip.followup.push_gossip   [24 chars]
    en  Go on — who's the worst?
    >>  ............................................
    pt  Vai — quem é o pior?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `people.followup.push_gossip`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +5, trust -2  _(recorded under topic `people.gossip.refused`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.followup.push_gossip
WHO    VILLAGER — what the player reads after pressing "Go on — who's the worst?"
       spoken on: conversations.topic.people.gossip.followup, button `push_gossip`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.gossip.refused`: the villager refuses. Subject `people.gossip`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.followup.push_gossip/1   [46 chars]
    en  ...No. I've said more than I meant to already.
    >>  ............................................
    pt  ...Não. Já falei mais do que pretendia.
    >>  ............................................
  dialogue.conversations.people.followup.push_gossip/2   [56 chars]
    en  I'll not name names to someone who asks like that, %1$s.
    >>  ............................................
    pt  Não vou dar nomes pra alguém que pergunta assim, %1$s.
    >>  ............................................
  dialogue.conversations.people.followup.push_gossip/3   [37 chars]
    en  That's not what this was. Let it lie.
    >>  ............................................
    pt  Não era isso. Deixa quieto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.followup.push_gossip/1
    en  ...No. I've already said more than I should have, %1$s.
    >>  ............................................
    pt  ...Não. Eu já disse mais do que devia, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.people.followup.push_gossip/2
    en  Please stop asking. I'll say it if you keep on and then I'll hate myself.
    >>  ............................................
    pt  Por favor pare de perguntar. Se insistir eu digo e depois me odeio.
    >>  ............................................
  anxious.dialogue.conversations.people.followup.push_gossip/3
    en  ...That's enough. Let me stop before I do real harm.
    >>  ............................................
    pt  ...Já basta. Me deixe parar antes de causar dano de verdade.
    >>  ............................................
  athletic.dialogue.conversations.people.followup.push_gossip/1
    en  No. It'll get about without me, and it can take its time doing it.
    >>  ............................................
    pt  Não. Vai se espalhar sem mim, e pode levar o tempo que quiser.
    >>  ............................................
  athletic.dialogue.conversations.people.followup.push_gossip/2
    en  ...I've said my share. The rest can wait for whoever wants to say it.
    >>  ............................................
    pt  ...Eu disse a minha parte. O resto pode esperar quem quiser dizer.
    >>  ............................................
  athletic.dialogue.conversations.people.followup.push_gossip/3
    en  Right. That's where I stop, and it's where I always stop.
    >>  ............................................
    pt  Certo. É onde eu paro, e é onde eu sempre paro.
    >>  ............................................
  confident.dialogue.conversations.people.followup.push_gossip/1
    en  No. I've said more than I meant to already.
    >>  ............................................
    pt  Não. Eu já disse mais do que pretendia.
    >>  ............................................
  confident.dialogue.conversations.people.followup.push_gossip/2
    en  That's the end of it. The rest belongs to them.
    >>  ............................................
    pt  É o fim. O resto pertence a eles.
    >>  ............................................
  confident.dialogue.conversations.people.followup.push_gossip/3
    en  ...I'll not be drawn further.
    >>  ............................................
    pt  ...Não vou ser puxado mais.
    >>  ............................................
  crabby.dialogue.conversations.people.followup.push_gossip/1
    en  No. I've said more than I meant to already.
    >>  ............................................
    pt  Não. Eu já disse mais do que pretendia.
    >>  ............................................
  crabby.dialogue.conversations.people.followup.push_gossip/2
    en  That's the end of it. The rest belongs to them.
    >>  ............................................
    pt  É o fim. O resto pertence a eles.
    >>  ............................................
  crabby.dialogue.conversations.people.followup.push_gossip/3
    en  ...I'll not be drawn further.
    >>  ............................................
    pt  ...Não vou ser puxado mais.
    >>  ............................................
  extroverted.dialogue.conversations.people.followup.push_gossip/1
    en  No, %1$s. I like them, and I've already gone further than I should.
    >>  ............................................
    pt  Não, %1$s. Eu gosto deles, e já fui além do que devia.
    >>  ............................................
  extroverted.dialogue.conversations.people.followup.push_gossip/2
    en  That's as much as I'll say. They'd not thank me for the rest.
    >>  ............................................
    pt  É tudo que eu digo. Eles não me agradeceriam pelo resto.
    >>  ............................................
  extroverted.dialogue.conversations.people.followup.push_gossip/3
    en  ...I'd rather you heard the rest from them.
    >>  ............................................
    pt  ...Prefiro que você ouça o resto deles.
    >>  ............................................
  flirty.dialogue.conversations.people.followup.push_gossip/1
    en  No, %1$s. I like them, and I've already gone further than I should.
    >>  ............................................
    pt  Não, %1$s. Eu gosto deles, e já fui além do que devia.
    >>  ............................................
  flirty.dialogue.conversations.people.followup.push_gossip/2
    en  That's as much as I'll say. They'd not thank me for the rest.
    >>  ............................................
    pt  É tudo que eu digo. Eles não me agradeceriam pelo resto.
    >>  ............................................
  flirty.dialogue.conversations.people.followup.push_gossip/3
    en  ...I'd rather you heard the rest from them.
    >>  ............................................
    pt  ...Prefiro que você ouça o resto deles.
    >>  ............................................
  friendly.dialogue.conversations.people.followup.push_gossip/1
    en  No, %1$s. I like them, and I've already gone further than I should.
    >>  ............................................
    pt  Não, %1$s. Eu gosto deles, e já fui além do que devia.
    >>  ............................................
  friendly.dialogue.conversations.people.followup.push_gossip/2
    en  That's as much as I'll say. They'd not thank me for the rest.
    >>  ............................................
    pt  É tudo que eu digo. Eles não me agradeceriam pelo resto.
    >>  ............................................
  friendly.dialogue.conversations.people.followup.push_gossip/3
    en  ...I'd rather you heard the rest from them.
    >>  ............................................
    pt  ...Prefiro que você ouça o resto deles.
    >>  ............................................
  gloomy.dialogue.conversations.people.followup.push_gossip/1
    en  ...No. I've already said more than I should have, %1$s.
    >>  ............................................
    pt  ...Não. Eu já disse mais do que devia, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.people.followup.push_gossip/2
    en  Please stop asking. I'll say it if you keep on and then I'll hate myself.
    >>  ............................................
    pt  Por favor pare de perguntar. Se insistir eu digo e depois me odeio.
    >>  ............................................
  gloomy.dialogue.conversations.people.followup.push_gossip/3
    en  ...That's enough. Let me stop before I do real harm.
    >>  ............................................
    pt  ...Já basta. Me deixe parar antes de causar dano de verdade.
    >>  ............................................
  greedy.dialogue.conversations.people.followup.push_gossip/1
    en  No. I've said more than I meant to already.
    >>  ............................................
    pt  Não. Eu já disse mais do que pretendia.
    >>  ............................................
  greedy.dialogue.conversations.people.followup.push_gossip/2
    en  That's the end of it. The rest belongs to them.
    >>  ............................................
    pt  É o fim. O resto pertence a eles.
    >>  ............................................
  greedy.dialogue.conversations.people.followup.push_gossip/3
    en  ...I'll not be drawn further.
    >>  ............................................
    pt  ...Não vou ser puxado mais.
    >>  ............................................
  grumpy.dialogue.conversations.people.followup.push_gossip/1
    en  No. I've said more than I meant to already.
    >>  ............................................
    pt  Não. Eu já disse mais do que pretendia.
    >>  ............................................
  grumpy.dialogue.conversations.people.followup.push_gossip/2
    en  That's the end of it. The rest belongs to them.
    >>  ............................................
    pt  É o fim. O resto pertence a eles.
    >>  ............................................
  grumpy.dialogue.conversations.people.followup.push_gossip/3
    en  ...I'll not be drawn further.
    >>  ............................................
    pt  ...Não vou ser puxado mais.
    >>  ............................................
  introverted.dialogue.conversations.people.followup.push_gossip/1
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  introverted.dialogue.conversations.people.followup.push_gossip/2
    en  I've said enough.
    >>  ............................................
    pt  Eu já disse o bastante.
    >>  ............................................
  introverted.dialogue.conversations.people.followup.push_gossip/3
    en  ...The rest isn't mine.
    >>  ............................................
    pt  ...O resto não é meu.
    >>  ............................................
  lazy.dialogue.conversations.people.followup.push_gossip/1
    en  No. It'll get about without me, and it can take its time doing it.
    >>  ............................................
    pt  Não. Vai se espalhar sem mim, e pode levar o tempo que quiser.
    >>  ............................................
  lazy.dialogue.conversations.people.followup.push_gossip/2
    en  ...I've said my share. The rest can wait for whoever wants to say it.
    >>  ............................................
    pt  ...Eu disse a minha parte. O resto pode esperar quem quiser dizer.
    >>  ............................................
  lazy.dialogue.conversations.people.followup.push_gossip/3
    en  Right. That's where I stop, and it's where I always stop.
    >>  ............................................
    pt  Certo. É onde eu paro, e é onde eu sempre paro.
    >>  ............................................
  odd.dialogue.conversations.people.followup.push_gossip/1
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  odd.dialogue.conversations.people.followup.push_gossip/2
    en  I've said enough.
    >>  ............................................
    pt  Eu já disse o bastante.
    >>  ............................................
  odd.dialogue.conversations.people.followup.push_gossip/3
    en  ...The rest isn't mine.
    >>  ............................................
    pt  ...O resto não é meu.
    >>  ............................................
  peaceful.dialogue.conversations.people.followup.push_gossip/1
    en  No. It'll get about without me, and it can take its time doing it.
    >>  ............................................
    pt  Não. Vai se espalhar sem mim, e pode levar o tempo que quiser.
    >>  ............................................
  peaceful.dialogue.conversations.people.followup.push_gossip/2
    en  ...I've said my share. The rest can wait for whoever wants to say it.
    >>  ............................................
    pt  ...Eu disse a minha parte. O resto pode esperar quem quiser dizer.
    >>  ............................................
  peaceful.dialogue.conversations.people.followup.push_gossip/3
    en  Right. That's where I stop, and it's where I always stop.
    >>  ............................................
    pt  Certo. É onde eu paro, e é onde eu sempre paro.
    >>  ............................................
  peppy.dialogue.conversations.people.followup.push_gossip/1
    en  ...No! I've already said far too much and I'm enjoying it far too little.
    >>  ............................................
    pt  ...Não! Eu já disse demais e estou gostando de menos.
    >>  ............................................
  peppy.dialogue.conversations.people.followup.push_gossip/2
    en  Right, that's my lot. I've a reputation to fail to maintain, %1$s.
    >>  ............................................
    pt  Certo, é o que eu tenho. Tenho uma reputação a não manter, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.people.followup.push_gossip/3
    en  ...Ha. No. Ask them yourself and see how far you get.
    >>  ............................................
    pt  ...Ha. Não. Pergunte a eles e veja até onde você chega.
    >>  ............................................
  playful.dialogue.conversations.people.followup.push_gossip/1
    en  ...No! I've already said far too much and I'm enjoying it far too little.
    >>  ............................................
    pt  ...Não! Eu já disse demais e estou gostando de menos.
    >>  ............................................
  playful.dialogue.conversations.people.followup.push_gossip/2
    en  Right, that's my lot. I've a reputation to fail to maintain, %1$s.
    >>  ............................................
    pt  Certo, é o que eu tenho. Tenho uma reputação a não manter, %1$s.
    >>  ............................................
  playful.dialogue.conversations.people.followup.push_gossip/3
    en  ...Ha. No. Ask them yourself and see how far you get.
    >>  ............................................
    pt  ...Ha. Não. Pergunte a eles e veja até onde você chega.
    >>  ............................................
  relaxed.dialogue.conversations.people.followup.push_gossip/1
    en  No. It'll get about without me, and it can take its time doing it.
    >>  ............................................
    pt  Não. Vai se espalhar sem mim, e pode levar o tempo que quiser.
    >>  ............................................
  relaxed.dialogue.conversations.people.followup.push_gossip/2
    en  ...I've said my share. The rest can wait for whoever wants to say it.
    >>  ............................................
    pt  ...Eu disse a minha parte. O resto pode esperar quem quiser dizer.
    >>  ............................................
  relaxed.dialogue.conversations.people.followup.push_gossip/3
    en  Right. That's where I stop, and it's where I always stop.
    >>  ............................................
    pt  Certo. É onde eu paro, e é onde eu sempre paro.
    >>  ............................................
  sensitive.dialogue.conversations.people.followup.push_gossip/1
    en  ...No. I've already said more than I should have, %1$s.
    >>  ............................................
    pt  ...Não. Eu já disse mais do que devia, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.people.followup.push_gossip/2
    en  Please stop asking. I'll say it if you keep on and then I'll hate myself.
    >>  ............................................
    pt  Por favor pare de perguntar. Se insistir eu digo e depois me odeio.
    >>  ............................................
  sensitive.dialogue.conversations.people.followup.push_gossip/3
    en  ...That's enough. Let me stop before I do real harm.
    >>  ............................................
    pt  ...Já basta. Me deixe parar antes de causar dano de verdade.
    >>  ............................................
  shy.dialogue.conversations.people.followup.push_gossip/1
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  shy.dialogue.conversations.people.followup.push_gossip/2
    en  I've said enough.
    >>  ............................................
    pt  Eu já disse o bastante.
    >>  ............................................
  shy.dialogue.conversations.people.followup.push_gossip/3
    en  ...The rest isn't mine.
    >>  ............................................
    pt  ...O resto não é meu.
    >>  ............................................
  upbeat.dialogue.conversations.people.followup.push_gossip/1
    en  ...No! I've already said far too much and I'm enjoying it far too little.
    >>  ............................................
    pt  ...Não! Eu já disse demais e estou gostando de menos.
    >>  ............................................
  upbeat.dialogue.conversations.people.followup.push_gossip/2
    en  Right, that's my lot. I've a reputation to fail to maintain, %1$s.
    >>  ............................................
    pt  Certo, é o que eu tenho. Tenho uma reputação a não manter, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.people.followup.push_gossip/3
    en  ...Ha. No. Ask them yourself and see how far you get.
    >>  ............................................
    pt  ...Ha. Não. Pergunte a eles e veja até onde você chega.
    >>  ............................................
  witty.dialogue.conversations.people.followup.push_gossip/1
    en  ...No! I've already said far too much and I'm enjoying it far too little.
    >>  ............................................
    pt  ...Não! Eu já disse demais e estou gostando de menos.
    >>  ............................................
  witty.dialogue.conversations.people.followup.push_gossip/2
    en  Right, that's my lot. I've a reputation to fail to maintain, %1$s.
    >>  ............................................
    pt  Certo, é o que eu tenho. Tenho uma reputação a não manter, %1$s.
    >>  ............................................
  witty.dialogue.conversations.people.followup.push_gossip/3
    en  ...Ha. No. Ask them yourself and see how far you get.
    >>  ............................................
    pt  ...Ha. Não. Pergunte a eles e veja até onde você chega.
    >>  ............................................
```

</details>


### Button `promise_discretion` — "It stays with me."

*stance family `restraint` · tone `plain` · outcome `engaged` · answers the beat(s) `people.example_offered` · offered only once the villager has actually said `gossip:offered`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.gossip.discretion` — accepted phrasings: "it stays with me"; "i will not repeat it"; "you have my discretion"
  - the message must contain one of: `stays`, `discretion`, `repeat`
  - scored words: `stays`(1.5), `discretion`(1.5), `repeat`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.people.gossip.followup.promise_discretion
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.gossip.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.gossip.followup.promise_discretion   [17 chars]
    en  It stays with me.
    >>  ............................................
    pt  Fica comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.gossip.discretion`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +4  _(recorded under topic `people.gossip.discretion`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.gossip.discretion
WHO    VILLAGER — what the player reads after pressing "It stays with me."
       spoken on: conversations.topic.people.gossip.followup, button `promise_discretion`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.gossip.discretion`: the villager discloses. Subject `people.gossip`, polarity `mixed`, guarded, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.gossip.discretion/1   [71 chars]
    en  ...Then here it is. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  ...Então lá vai. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  dialogue.conversations.people.gossip.discretion/2   [83 chars]
    en  I'll hold you to that, %1$s. Right — you know the well? Two of them fought over it.
    >>  ............................................
    pt  Vou cobrar isso, %1$s. Certo — sabe o poço? Dois deles brigaram por ele.
    >>  ............................................
  dialogue.conversations.people.gossip.discretion/3   [63 chars]
    en  Good. Then you'll understand why I've stopped lending anything.
    >>  ............................................
    pt  Bom. Então você vai entender por que eu parei de emprestar qualquer coisa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood, and I've said nothing for a month.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha, e eu não disse nada por um mês.
    >>  ............................................
  anxious.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. I hate having to say it and I've nobody else to say it to.
    >>  ............................................
    pt  Certo. O irmão do moleiro. Odeio ter que dizer e não tenho mais ninguém a quem dizer.
    >>  ............................................
  anxious.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: my woodpile. It's small and it has been keeping me awake, %1$s.
    >>  ............................................
    pt  Já que você guarda: minha pilha de lenha. É pequeno e vem me tirando o sono, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother counts my firewood. It'll come to nothing, most likely.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro conta minha lenha. Não vai dar em nada, provavelmente.
    >>  ............................................
  athletic.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother and my woodpile. I've watched it a season before mentioning it.
    >>  ............................................
    pt  Certo. O irmão do moleiro e minha pilha de lenha. Observei uma estação antes de mencionar.
    >>  ............................................
  athletic.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the woodpile. I'll say something to him myself, in my own time.
    >>  ............................................
    pt  Já que você guarda — a pilha de lenha. Eu mesmo vou falar com ele, no meu tempo.
    >>  ............................................
  confident.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  confident.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother counts my firewood when he thinks nobody's looking.
    >>  ............................................
    pt  Certo. O irmão do moleiro conta minha lenha quando acha que ninguém vê.
    >>  ............................................
  confident.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: the miller's brother has taken an interest in my woodpile.
    >>  ............................................
    pt  Já que você guarda: o irmão do moleiro se interessou pela minha pilha de lenha.
    >>  ............................................
  crabby.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  crabby.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother counts my firewood when he thinks nobody's looking.
    >>  ............................................
    pt  Certo. O irmão do moleiro conta minha lenha quando acha que ninguém vê.
    >>  ............................................
  crabby.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: the miller's brother has taken an interest in my woodpile.
    >>  ............................................
    pt  Já que você guarda: o irmão do moleiro se interessou pela minha pilha de lenha.
    >>  ............................................
  extroverted.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is, %1$s. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai, %1$s. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  extroverted.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. And this stays between us, because he's not a bad man.
    >>  ............................................
    pt  Certo. O irmão do moleiro. E isso fica entre nós, porque ele não é um homem ruim.
    >>  ............................................
  extroverted.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: my woodpile has a very attentive admirer, and he's family to the miller.
    >>  ............................................
    pt  Já que você guarda: minha pilha de lenha tem um admirador atento, e ele é da família do moleiro.
    >>  ............................................
  flirty.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is, %1$s. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai, %1$s. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  flirty.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. And this stays between us, because he's not a bad man.
    >>  ............................................
    pt  Certo. O irmão do moleiro. E isso fica entre nós, porque ele não é um homem ruim.
    >>  ............................................
  flirty.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: my woodpile has a very attentive admirer, and he's family to the miller.
    >>  ............................................
    pt  Já que você guarda: minha pilha de lenha tem um admirador atento, e ele é da família do moleiro.
    >>  ............................................
  friendly.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is, %1$s. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai, %1$s. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  friendly.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. And this stays between us, because he's not a bad man.
    >>  ............................................
    pt  Certo. O irmão do moleiro. E isso fica entre nós, porque ele não é um homem ruim.
    >>  ............................................
  friendly.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: my woodpile has a very attentive admirer, and he's family to the miller.
    >>  ............................................
    pt  Já que você guarda: minha pilha de lenha tem um admirador atento, e ele é da família do moleiro.
    >>  ............................................
  gloomy.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood, and I've said nothing for a month.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha, e eu não disse nada por um mês.
    >>  ............................................
  gloomy.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. I hate having to say it and I've nobody else to say it to.
    >>  ............................................
    pt  Certo. O irmão do moleiro. Odeio ter que dizer e não tenho mais ninguém a quem dizer.
    >>  ............................................
  gloomy.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: my woodpile. It's small and it has been keeping me awake, %1$s.
    >>  ............................................
    pt  Já que você guarda: minha pilha de lenha. É pequeno e vem me tirando o sono, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  greedy.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother counts my firewood when he thinks nobody's looking.
    >>  ............................................
    pt  Certo. O irmão do moleiro conta minha lenha quando acha que ninguém vê.
    >>  ............................................
  greedy.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: the miller's brother has taken an interest in my woodpile.
    >>  ............................................
    pt  Já que você guarda: o irmão do moleiro se interessou pela minha pilha de lenha.
    >>  ............................................
  grumpy.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  grumpy.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother counts my firewood when he thinks nobody's looking.
    >>  ............................................
    pt  Certo. O irmão do moleiro conta minha lenha quando acha que ninguém vê.
    >>  ............................................
  grumpy.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: the miller's brother has taken an interest in my woodpile.
    >>  ............................................
    pt  Já que você guarda: o irmão do moleiro se interessou pela minha pilha de lenha.
    >>  ............................................
  introverted.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  introverted.dialogue.conversations.people.gossip.discretion/2
    en  The miller's brother. My woodpile. That's all.
    >>  ............................................
    pt  O irmão do moleiro. Minha pilha de lenha. É só isso.
    >>  ............................................
  introverted.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the woodpile. He counts it.
    >>  ............................................
    pt  Já que você guarda — a pilha de lenha. Ele conta.
    >>  ............................................
  lazy.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother counts my firewood. It'll come to nothing, most likely.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro conta minha lenha. Não vai dar em nada, provavelmente.
    >>  ............................................
  lazy.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother and my woodpile. I've watched it a season before mentioning it.
    >>  ............................................
    pt  Certo. O irmão do moleiro e minha pilha de lenha. Observei uma estação antes de mencionar.
    >>  ............................................
  lazy.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the woodpile. I'll say something to him myself, in my own time.
    >>  ............................................
    pt  Já que você guarda — a pilha de lenha. Eu mesmo vou falar com ele, no meu tempo.
    >>  ............................................
  odd.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  odd.dialogue.conversations.people.gossip.discretion/2
    en  The miller's brother. My woodpile. That's all.
    >>  ............................................
    pt  O irmão do moleiro. Minha pilha de lenha. É só isso.
    >>  ............................................
  odd.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the woodpile. He counts it.
    >>  ............................................
    pt  Já que você guarda — a pilha de lenha. Ele conta.
    >>  ............................................
  peaceful.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother counts my firewood. It'll come to nothing, most likely.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro conta minha lenha. Não vai dar em nada, provavelmente.
    >>  ............................................
  peaceful.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother and my woodpile. I've watched it a season before mentioning it.
    >>  ............................................
    pt  Certo. O irmão do moleiro e minha pilha de lenha. Observei uma estação antes de mencionar.
    >>  ............................................
  peaceful.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the woodpile. I'll say something to him myself, in my own time.
    >>  ............................................
    pt  Já que você guarda — a pilha de lenha. Eu mesmo vou falar com ele, no meu tempo.
    >>  ............................................
  peppy.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is! The miller's brother has been counting my firewood. COUNTING it.
    >>  ............................................
    pt  Então aqui vai! O irmão do moleiro anda contando minha lenha. CONTANDO.
    >>  ............................................
  peppy.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. My woodpile. He thinks I haven't noticed.
    >>  ............................................
    pt  Certo. O irmão do moleiro. Minha pilha de lenha. Ele acha que eu não reparei.
    >>  ............................................
  peppy.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the miller's brother has developed a professional interest in my logs.
    >>  ............................................
    pt  Já que você guarda — o irmão do moleiro criou interesse profissional pelas minhas toras.
    >>  ............................................
  playful.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is! The miller's brother has been counting my firewood. COUNTING it.
    >>  ............................................
    pt  Então aqui vai! O irmão do moleiro anda contando minha lenha. CONTANDO.
    >>  ............................................
  playful.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. My woodpile. He thinks I haven't noticed.
    >>  ............................................
    pt  Certo. O irmão do moleiro. Minha pilha de lenha. Ele acha que eu não reparei.
    >>  ............................................
  playful.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the miller's brother has developed a professional interest in my logs.
    >>  ............................................
    pt  Já que você guarda — o irmão do moleiro criou interesse profissional pelas minhas toras.
    >>  ............................................
  relaxed.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother counts my firewood. It'll come to nothing, most likely.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro conta minha lenha. Não vai dar em nada, provavelmente.
    >>  ............................................
  relaxed.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother and my woodpile. I've watched it a season before mentioning it.
    >>  ............................................
    pt  Certo. O irmão do moleiro e minha pilha de lenha. Observei uma estação antes de mencionar.
    >>  ............................................
  relaxed.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the woodpile. I'll say something to him myself, in my own time.
    >>  ............................................
    pt  Já que você guarda — a pilha de lenha. Eu mesmo vou falar com ele, no meu tempo.
    >>  ............................................
  sensitive.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood, and I've said nothing for a month.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha, e eu não disse nada por um mês.
    >>  ............................................
  sensitive.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. I hate having to say it and I've nobody else to say it to.
    >>  ............................................
    pt  Certo. O irmão do moleiro. Odeio ter que dizer e não tenho mais ninguém a quem dizer.
    >>  ............................................
  sensitive.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it: my woodpile. It's small and it has been keeping me awake, %1$s.
    >>  ............................................
    pt  Já que você guarda: minha pilha de lenha. É pequeno e vem me tirando o sono, %1$s.
    >>  ............................................
  shy.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is. The miller's brother has been counting my firewood.
    >>  ............................................
    pt  Então aqui vai. O irmão do moleiro anda contando minha lenha.
    >>  ............................................
  shy.dialogue.conversations.people.gossip.discretion/2
    en  The miller's brother. My woodpile. That's all.
    >>  ............................................
    pt  O irmão do moleiro. Minha pilha de lenha. É só isso.
    >>  ............................................
  shy.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the woodpile. He counts it.
    >>  ............................................
    pt  Já que você guarda — a pilha de lenha. Ele conta.
    >>  ............................................
  upbeat.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is! The miller's brother has been counting my firewood. COUNTING it.
    >>  ............................................
    pt  Então aqui vai! O irmão do moleiro anda contando minha lenha. CONTANDO.
    >>  ............................................
  upbeat.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. My woodpile. He thinks I haven't noticed.
    >>  ............................................
    pt  Certo. O irmão do moleiro. Minha pilha de lenha. Ele acha que eu não reparei.
    >>  ............................................
  upbeat.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the miller's brother has developed a professional interest in my logs.
    >>  ............................................
    pt  Já que você guarda — o irmão do moleiro criou interesse profissional pelas minhas toras.
    >>  ............................................
  witty.dialogue.conversations.people.gossip.discretion/1
    en  Then here it is! The miller's brother has been counting my firewood. COUNTING it.
    >>  ............................................
    pt  Então aqui vai! O irmão do moleiro anda contando minha lenha. CONTANDO.
    >>  ............................................
  witty.dialogue.conversations.people.gossip.discretion/2
    en  Right. The miller's brother. My woodpile. He thinks I haven't noticed.
    >>  ............................................
    pt  Certo. O irmão do moleiro. Minha pilha de lenha. Ele acha que eu não reparei.
    >>  ............................................
  witty.dialogue.conversations.people.gossip.discretion/3
    en  Since you'll keep it — the miller's brother has developed a professional interest in my logs.
    >>  ............................................
    pt  Já que você guarda — o irmão do moleiro criou interesse profissional pelas minhas toras.
    >>  ............................................
```

</details>


### Button `back_off` — "Actually — don't tell me."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `people.example_offered` · offered only once the villager has actually said `gossip:offered`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.gossip.back_off` — accepted phrasings: "actually, do not tell me"; "i would rather not hear it"; "on second thought, keep it"
  - the message must contain one of: `actually`, `rather`, `hear`
  - scored words: `actually`(1.0), `rather`(1.2), `hear`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.people.gossip.followup.back_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.gossip.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.gossip.followup.back_off   [25 chars]
    en  Actually — don't tell me.
    >>  ............................................
    pt  Na verdade — não me conte.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, trust +2  _(recorded under topic `people.gossip.back_off`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.gossip.back_off
WHO    VILLAGER — what the player reads after pressing "Actually — don't tell me."
       spoken on: conversations.topic.people.gossip.followup, button `back_off`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.gossip.back_off`: the villager accepts. Subject `people.gossip`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.gossip.back_off/1   [68 chars]
    en  ...That's the most decent thing anybody's done in this conversation.
    >>  ............................................
    pt  ...É a coisa mais decente que alguém fez nesta conversa.
    >>  ............................................
  dialogue.conversations.people.gossip.back_off/2   [64 chars]
    en  Ha. You've saved us both, %1$s. I'd have regretted it by supper.
    >>  ............................................
    pt  Ha. Você salvou nós dois, %1$s. Eu ia me arrepender até o jantar.
    >>  ............................................
  dialogue.conversations.people.gossip.back_off/3   [63 chars]
    en  Right. Good. Let's talk about the weather like sensible people.
    >>  ............................................
    pt  Certo. Bom. Vamos falar do tempo como gente sensata.
    >>  ............................................
```


### Button `leave` — "Best I stay out of it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.example_offered` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.gossip.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.gossip.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.gossip.followup.leave   [22 chars]
    en  Best I stay out of it.
    >>  ............................................
    pt  Melhor eu ficar fora disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.respond.leave
WHO    VILLAGER — what the player reads after pressing "Best I stay out of it."
       spoken on: conversations.topic.people.gossip.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.left`: the villager accepts. Subject `people.first`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.grumble.followup / leave; conversations.topic.people.mixed.respond / leave; conversations.topic.people.rebuffed.followup / leave; conversations.topic.people.softened.followup / leave; conversations.topic.people.sour.respond / leave
```

```text
  dialogue.conversations.people.respond.leave/1   [40 chars]
    en  Wise. Nothing good comes of stirring it.
    >>  ............................................
    pt  Sábio. Nada de bom vem de remexer nisso.
    >>  ............................................
  dialogue.conversations.people.respond.leave/2   [31 chars]
    en  So I've found. Best left alone.
    >>  ............................................
    pt  Foi o que eu vi. Melhor deixar quieto.
    >>  ............................................
  dialogue.conversations.people.respond.leave/3   [45 chars]
    en  Right you are. Mind who you repeat things to.
    >>  ............................................
    pt  Isso mesmo. Cuidado com quem você repete as coisas.
    >>  ............................................
```

---


## `conversations.topic.people.grumble.followup`

**Reached from 2 route(s):** `conversations.topic.people.mixed.respond` / `agree`; `conversations.topic.people.sour.respond` / `agree`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.respond.agree` — e.g. "Then it isn't just me. That's a relief, honestly."


```text
POOL   dialogue key: dialogue.conversations.topic.people.grumble.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.grumble.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.grumble.followup   [35 chars]
    en  You live alongside them either way.
    >>  ............................................
    pt  Você vive ao lado deles de qualquer jeito.
    >>  ............................................
```


### Button `empathise` — "That sounds exhausting."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `people.agreed` · offered only once the villager has actually said `grievance:shared`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.grumble.empathise` — accepted phrasings: "that sounds exhausting"; "that must be exhausting"; "that sounds tiring to live with"
  - the message must contain one of: `exhausting`, `tiring`
  - scored words: `exhausting`(1.5), `tiring`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.people.grumble.followup.empathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.grumble.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.grumble.followup.empathise   [23 chars]
    en  That sounds exhausting.
    >>  ............................................
    pt  Isso parece exaustivo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the village rank is `outlaw`
- Does: **hearts +2** — decision id `people.followup.empathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `people.followup.empathise`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.empathise.outlaw
WHO    VILLAGER — what the player reads after pressing "That sounds exhausting."
       spoken on: conversations.topic.people.grumble.followup, button `empathise`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.empathised.outlaw`: the villager discloses. Subject `people.empathy`, polarity `negative`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.empathise.outlaw/1   [72 chars]
    en  Exhausting. You've no idea. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo. Você não faz ideia. Atravessam a rua quando me veem chegando.
    >>  ............................................
  dialogue.conversations.people.empathise.outlaw/2   [79 chars]
    en  It is. Though I'll grant they've their reasons, %1$s, which is the tiring part.
    >>  ............................................
    pt  É. Embora eu admita que têm seus motivos, %1$s, e essa é a parte cansativa.
    >>  ............................................
  dialogue.conversations.people.empathise.outlaw/3   [73 chars]
    en  Just so. Try living alongside people who've decided what you are already.
    >>  ............................................
    pt  Pois é. Tente viver ao lado de gente que já decidiu o que você é.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane, %1$s. Their children learn it by watching, and that's the worst part.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua, %1$s. Os filhos deles aprendem olhando, e é a pior parte.
    >>  ............................................
  anxious.dialogue.conversations.people.empathise.outlaw/2
    en  It wears in a quiet way that doesn't look like anything from outside.
    >>  ............................................
    pt  Desgasta de um jeito silencioso que não parece nada de fora.
    >>  ............................................
  anxious.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. I've stopped expecting it to change and I've not stopped noticing it.
    >>  ............................................
    pt  Cansativo. Parei de esperar que mude e não parei de reparar.
    >>  ............................................
  athletic.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. Less each year, mind. Four years wears a path, even a grudging one.
    >>  ............................................
    pt  Exaustivo. Mas menos a cada ano. Quatro anos abrem um caminho, mesmo a contragosto.
    >>  ............................................
  athletic.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. It's worn less lately, which may be healing or may be numbness.
    >>  ............................................
    pt  Desgasta. Desgastou menos ultimamente, o que pode ser cura ou dormência.
    >>  ............................................
  athletic.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. It'll ease. Villages forget slowly, but they do forget.
    >>  ............................................
    pt  Cansativo. Vai aliviar. Vilarejos esquecem devagar, mas esquecem.
    >>  ............................................
  confident.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando.
    >>  ............................................
  confident.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Nobody says anything; they just take the long way round.
    >>  ............................................
    pt  Desgasta. Ninguém diz nada; só pegam o caminho mais longo.
    >>  ............................................
  confident.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And I can't complain about it, which is the other half.
    >>  ............................................
    pt  Cansativo. E eu não posso reclamar, que é a outra metade.
    >>  ............................................
  crabby.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando.
    >>  ............................................
  crabby.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Nobody says anything; they just take the long way round.
    >>  ............................................
    pt  Desgasta. Ninguém diz nada; só pegam o caminho mais longo.
    >>  ............................................
  crabby.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And I can't complain about it, which is the other half.
    >>  ............................................
    pt  Cansativo. E eu não posso reclamar, que é a outra metade.
    >>  ............................................
  extroverted.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming, %1$s. You didn't.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando, %1$s. Você não atravessou.
    >>  ............................................
  extroverted.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. You're the first to ask what it's like rather than what I did.
    >>  ............................................
    pt  Desgasta. Você é o primeiro a perguntar como é em vez do que eu fiz.
    >>  ............................................
  extroverted.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And you've stood here for a whole conversation, which is more than most.
    >>  ............................................
    pt  Cansativo. E você ficou aqui uma conversa inteira, que é mais que a maioria.
    >>  ............................................
  flirty.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming, %1$s. You didn't.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando, %1$s. Você não atravessou.
    >>  ............................................
  flirty.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. You're the first to ask what it's like rather than what I did.
    >>  ............................................
    pt  Desgasta. Você é o primeiro a perguntar como é em vez do que eu fiz.
    >>  ............................................
  flirty.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And you've stood here for a whole conversation, which is more than most.
    >>  ............................................
    pt  Cansativo. E você ficou aqui uma conversa inteira, que é mais que a maioria.
    >>  ............................................
  friendly.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming, %1$s. You didn't.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando, %1$s. Você não atravessou.
    >>  ............................................
  friendly.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. You're the first to ask what it's like rather than what I did.
    >>  ............................................
    pt  Desgasta. Você é o primeiro a perguntar como é em vez do que eu fiz.
    >>  ............................................
  friendly.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And you've stood here for a whole conversation, which is more than most.
    >>  ............................................
    pt  Cansativo. E você ficou aqui uma conversa inteira, que é mais que a maioria.
    >>  ............................................
  gloomy.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane, %1$s. Their children learn it by watching, and that's the worst part.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua, %1$s. Os filhos deles aprendem olhando, e é a pior parte.
    >>  ............................................
  gloomy.dialogue.conversations.people.empathise.outlaw/2
    en  It wears in a quiet way that doesn't look like anything from outside.
    >>  ............................................
    pt  Desgasta de um jeito silencioso que não parece nada de fora.
    >>  ............................................
  gloomy.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. I've stopped expecting it to change and I've not stopped noticing it.
    >>  ............................................
    pt  Cansativo. Parei de esperar que mude e não parei de reparar.
    >>  ............................................
  greedy.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando.
    >>  ............................................
  greedy.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Nobody says anything; they just take the long way round.
    >>  ............................................
    pt  Desgasta. Ninguém diz nada; só pegam o caminho mais longo.
    >>  ............................................
  greedy.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And I can't complain about it, which is the other half.
    >>  ............................................
    pt  Cansativo. E eu não posso reclamar, que é a outra metade.
    >>  ............................................
  grumpy.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando.
    >>  ............................................
  grumpy.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Nobody says anything; they just take the long way round.
    >>  ............................................
    pt  Desgasta. Ninguém diz nada; só pegam o caminho mais longo.
    >>  ............................................
  grumpy.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And I can't complain about it, which is the other half.
    >>  ............................................
    pt  Cansativo. E eu não posso reclamar, que é a outra metade.
    >>  ............................................
  introverted.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando.
    >>  ............................................
  introverted.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Quietly. Nothing you'd see from outside.
    >>  ............................................
    pt  Desgasta. Em silêncio. Nada que se veja de fora.
    >>  ............................................
  introverted.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. That's all.
    >>  ............................................
    pt  Cansativo. Só isso.
    >>  ............................................
  lazy.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. Less each year, mind. Four years wears a path, even a grudging one.
    >>  ............................................
    pt  Exaustivo. Mas menos a cada ano. Quatro anos abrem um caminho, mesmo a contragosto.
    >>  ............................................
  lazy.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. It's worn less lately, which may be healing or may be numbness.
    >>  ............................................
    pt  Desgasta. Desgastou menos ultimamente, o que pode ser cura ou dormência.
    >>  ............................................
  lazy.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. It'll ease. Villages forget slowly, but they do forget.
    >>  ............................................
    pt  Cansativo. Vai aliviar. Vilarejos esquecem devagar, mas esquecem.
    >>  ............................................
  odd.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando.
    >>  ............................................
  odd.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Quietly. Nothing you'd see from outside.
    >>  ............................................
    pt  Desgasta. Em silêncio. Nada que se veja de fora.
    >>  ............................................
  odd.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. That's all.
    >>  ............................................
    pt  Cansativo. Só isso.
    >>  ............................................
  peaceful.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. Less each year, mind. Four years wears a path, even a grudging one.
    >>  ............................................
    pt  Exaustivo. Mas menos a cada ano. Quatro anos abrem um caminho, mesmo a contragosto.
    >>  ............................................
  peaceful.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. It's worn less lately, which may be healing or may be numbness.
    >>  ............................................
    pt  Desgasta. Desgastou menos ultimamente, o que pode ser cura ou dormência.
    >>  ............................................
  peaceful.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. It'll ease. Villages forget slowly, but they do forget.
    >>  ............................................
    pt  Cansativo. Vai aliviar. Vilarejos esquecem devagar, mas esquecem.
    >>  ............................................
  peppy.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting! You've no idea. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo! Você não faz ideia. Atravessam a rua quando me veem chegando.
    >>  ............................................
  peppy.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Nobody says a word — they simply develop urgent business elsewhere.
    >>  ............................................
    pt  Desgasta. Ninguém diz uma palavra — só descobrem assuntos urgentes em outro lugar.
    >>  ............................................
  peppy.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And the joke is I'd do the same in their place, which spoils the complaint.
    >>  ............................................
    pt  Cansativo. E a piada é que eu faria o mesmo no lugar deles, o que estraga a queixa.
    >>  ............................................
  playful.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting! You've no idea. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo! Você não faz ideia. Atravessam a rua quando me veem chegando.
    >>  ............................................
  playful.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Nobody says a word — they simply develop urgent business elsewhere.
    >>  ............................................
    pt  Desgasta. Ninguém diz uma palavra — só descobrem assuntos urgentes em outro lugar.
    >>  ............................................
  playful.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And the joke is I'd do the same in their place, which spoils the complaint.
    >>  ............................................
    pt  Cansativo. E a piada é que eu faria o mesmo no lugar deles, o que estraga a queixa.
    >>  ............................................
  relaxed.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. Less each year, mind. Four years wears a path, even a grudging one.
    >>  ............................................
    pt  Exaustivo. Mas menos a cada ano. Quatro anos abrem um caminho, mesmo a contragosto.
    >>  ............................................
  relaxed.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. It's worn less lately, which may be healing or may be numbness.
    >>  ............................................
    pt  Desgasta. Desgastou menos ultimamente, o que pode ser cura ou dormência.
    >>  ............................................
  relaxed.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. It'll ease. Villages forget slowly, but they do forget.
    >>  ............................................
    pt  Cansativo. Vai aliviar. Vilarejos esquecem devagar, mas esquecem.
    >>  ............................................
  sensitive.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane, %1$s. Their children learn it by watching, and that's the worst part.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua, %1$s. Os filhos deles aprendem olhando, e é a pior parte.
    >>  ............................................
  sensitive.dialogue.conversations.people.empathise.outlaw/2
    en  It wears in a quiet way that doesn't look like anything from outside.
    >>  ............................................
    pt  Desgasta de um jeito silencioso que não parece nada de fora.
    >>  ............................................
  sensitive.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. I've stopped expecting it to change and I've not stopped noticing it.
    >>  ............................................
    pt  Cansativo. Parei de esperar que mude e não parei de reparar.
    >>  ............................................
  shy.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo. Atravessam a rua quando me veem chegando.
    >>  ............................................
  shy.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Quietly. Nothing you'd see from outside.
    >>  ............................................
    pt  Desgasta. Em silêncio. Nada que se veja de fora.
    >>  ............................................
  shy.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. That's all.
    >>  ............................................
    pt  Cansativo. Só isso.
    >>  ............................................
  upbeat.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting! You've no idea. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo! Você não faz ideia. Atravessam a rua quando me veem chegando.
    >>  ............................................
  upbeat.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Nobody says a word — they simply develop urgent business elsewhere.
    >>  ............................................
    pt  Desgasta. Ninguém diz uma palavra — só descobrem assuntos urgentes em outro lugar.
    >>  ............................................
  upbeat.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And the joke is I'd do the same in their place, which spoils the complaint.
    >>  ............................................
    pt  Cansativo. E a piada é que eu faria o mesmo no lugar deles, o que estraga a queixa.
    >>  ............................................
  witty.dialogue.conversations.people.empathise.outlaw/1
    en  Exhausting! You've no idea. They cross the lane when they see me coming.
    >>  ............................................
    pt  Exaustivo! Você não faz ideia. Atravessam a rua quando me veem chegando.
    >>  ............................................
  witty.dialogue.conversations.people.empathise.outlaw/2
    en  It wears. Nobody says a word — they simply develop urgent business elsewhere.
    >>  ............................................
    pt  Desgasta. Ninguém diz uma palavra — só descobrem assuntos urgentes em outro lugar.
    >>  ............................................
  witty.dialogue.conversations.people.empathise.outlaw/3
    en  Tiring. And the joke is I'd do the same in their place, which spoils the complaint.
    >>  ............................................
    pt  Cansativo. E a piada é que eu faria o mesmo no lugar deles, o que estraga a queixa.
    >>  ............................................
```

</details>


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the village rank is `outlaw`  _(chance -2000)_
- Does: **hearts +2** — decision id `people.followup.empathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `people.followup.empathise`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.followup.empathise
WHO    VILLAGER — what the player reads after pressing "That sounds exhausting."
       spoken on: conversations.topic.people.grumble.followup, button `empathise`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.empathised`: the villager accepts. Subject `people.empathy`, polarity `mixed`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.followup.empathise/1   [60 chars]
    en  ...It is. Living beside people is the whole job, some weeks.
    >>  ............................................
    pt  ...É. Viver ao lado das pessoas é o trabalho inteiro, em algumas semanas.
    >>  ............................................
  dialogue.conversations.people.followup.empathise/2   [67 chars]
    en  Exhausting is the word. Thank you for not telling me to be patient.
    >>  ............................................
    pt  Exaustivo é a palavra. Obrigado por não me mandar ter paciência.
    >>  ............................................
  dialogue.conversations.people.followup.empathise/3   [58 chars]
    en  You'd think a small village would be less work. It's more.
    >>  ............................................
    pt  Você pensaria que uma vila pequena daria menos trabalho. Dá mais.
    >>  ............................................
```


### Button `encourage_repair` — "Have you tried talking to them?"

*stance family `respectful_disagreement` · tone `plain` · outcome `accepted`/`rebuffed` · answers the beat(s) `people.agreed` · offered only once the villager has actually said `grievance:shared`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.grumble.encourage_repair` — accepted phrasings: "have you tried talking to them"; "could you not talk to them"; "have you spoken to them about it"
  - the message must contain one of: `tried`
  - scored words: `talking`(0.8), `tried`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.people.grumble.followup.encourage_repair
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.grumble.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.grumble.followup.encourage_repair   [31 chars]
    en  Have you tried talking to them?
    >>  ............................................
    pt  Você já tentou conversar com eles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `people.encourage_repair` lands on tier **crit** (axis respect, difficulty 45, stance respectful_disagreement)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `people.encourage_repair.crit`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +6, trust +3  _(recorded under topic `people.encourage_repair`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.encourage_repair.crit
WHO    VILLAGER — what the player reads after pressing "Have you tried talking to them?"
       spoken on: conversations.topic.people.grumble.followup, button `encourage_repair`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.repair.crit`: the villager accepts. Subject `people.repair`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.encourage_repair.crit/1   [110 chars]
    en  ...Talk to them. Out loud. Like people. ...I've been avoiding that for two years and you said it in six words.
    >>  ............................................
    pt  ...Falar com eles. Em voz alta. Feito gente. ...Venho evitando isso há dois anos e você disse em seis palavras.
    >>  ............................................
  dialogue.conversations.people.encourage_repair.crit/2   [79 chars]
    en  You're right and I hate it, %1$s. I'll go round tomorrow. Don't watch me do it.
    >>  ............................................
    pt  Você tem razão e eu detesto isso, %1$s. Vou lá amanhã. Não fique olhando.
    >>  ............................................
```


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `people.encourage_repair` lands on tier **success** (axis respect, difficulty 45, stance respectful_disagreement)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `people.encourage_repair.success`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `people.encourage_repair`)_
- Does: arc `people` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.encourage_repair.success
WHO    VILLAGER — what the player reads after pressing "Have you tried talking to them?"
       spoken on: conversations.topic.people.grumble.followup, button `encourage_repair`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.repair.success`: the villager accepts. Subject `people.repair`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.encourage_repair.success/1   [81 chars]
    en  ...I could. I've thought about it. Hearing somebody else say it makes it smaller.
    >>  ............................................
    pt  ...Eu poderia. Já pensei nisso. Ouvir de outra pessoa deixa menor.
    >>  ............................................
  dialogue.conversations.people.encourage_repair.success/2   [57 chars]
    en  Talking. Aye. It's the obvious thing and the hardest one.
    >>  ............................................
    pt  Conversar. É. É o óbvio e o mais difícil.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Hearing somebody else say it makes it smaller, and I needed it smaller.
    >>  ............................................
    pt  ...Eu poderia. Ouvir de outra pessoa deixa menor, e eu precisava que ficasse menor.
    >>  ............................................
  anxious.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. The obvious thing and the hardest, and I've been calling that a coincidence.
    >>  ............................................
    pt  Conversar. A coisa óbvia e a mais difícil, e eu venho chamando isso de coincidência.
    >>  ............................................
  athletic.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Fifty years and hearing somebody else say a thing still shrinks it.
    >>  ............................................
    pt  ...Eu poderia. Cinquenta anos e ouvir de outra pessoa ainda encolhe a coisa.
    >>  ............................................
  athletic.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. The obvious thing and the hardest; that pairing has never once changed.
    >>  ............................................
    pt  Conversar. A coisa óbvia e a mais difícil; esse par nunca mudou.
    >>  ............................................
  confident.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. I've thought about it. Hearing somebody else say it makes it smaller.
    >>  ............................................
    pt  ...Eu poderia. Já pensei nisso. Ouvir outra pessoa dizer deixa menor.
    >>  ............................................
  confident.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. Aye. It's the obvious thing and the hardest one.
    >>  ............................................
    pt  Conversar. É. É a coisa óbvia e a mais difícil.
    >>  ............................................
  crabby.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. I've thought about it. Hearing somebody else say it makes it smaller.
    >>  ............................................
    pt  ...Eu poderia. Já pensei nisso. Ouvir outra pessoa dizer deixa menor.
    >>  ............................................
  crabby.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. Aye. It's the obvious thing and the hardest one.
    >>  ............................................
    pt  Conversar. É. É a coisa óbvia e a mais difícil.
    >>  ............................................
  extroverted.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could, %1$s. Hearing somebody else say it makes it smaller than it was.
    >>  ............................................
    pt  ...Eu poderia, %1$s. Ouvir outra pessoa dizer deixa menor do que era.
    >>  ............................................
  extroverted.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. It's the obvious thing and the hardest one, and you knew that when you said it.
    >>  ............................................
    pt  Conversar. É a coisa óbvia e a mais difícil, e você sabia quando disse.
    >>  ............................................
  flirty.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could, %1$s. Hearing somebody else say it makes it smaller than it was.
    >>  ............................................
    pt  ...Eu poderia, %1$s. Ouvir outra pessoa dizer deixa menor do que era.
    >>  ............................................
  flirty.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. It's the obvious thing and the hardest one, and you knew that when you said it.
    >>  ............................................
    pt  Conversar. É a coisa óbvia e a mais difícil, e você sabia quando disse.
    >>  ............................................
  friendly.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could, %1$s. Hearing somebody else say it makes it smaller than it was.
    >>  ............................................
    pt  ...Eu poderia, %1$s. Ouvir outra pessoa dizer deixa menor do que era.
    >>  ............................................
  friendly.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. It's the obvious thing and the hardest one, and you knew that when you said it.
    >>  ............................................
    pt  Conversar. É a coisa óbvia e a mais difícil, e você sabia quando disse.
    >>  ............................................
  gloomy.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Hearing somebody else say it makes it smaller, and I needed it smaller.
    >>  ............................................
    pt  ...Eu poderia. Ouvir de outra pessoa deixa menor, e eu precisava que ficasse menor.
    >>  ............................................
  gloomy.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. The obvious thing and the hardest, and I've been calling that a coincidence.
    >>  ............................................
    pt  Conversar. A coisa óbvia e a mais difícil, e eu venho chamando isso de coincidência.
    >>  ............................................
  greedy.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. I've thought about it. Hearing somebody else say it makes it smaller.
    >>  ............................................
    pt  ...Eu poderia. Já pensei nisso. Ouvir outra pessoa dizer deixa menor.
    >>  ............................................
  greedy.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. Aye. It's the obvious thing and the hardest one.
    >>  ............................................
    pt  Conversar. É. É a coisa óbvia e a mais difícil.
    >>  ............................................
  grumpy.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. I've thought about it. Hearing somebody else say it makes it smaller.
    >>  ............................................
    pt  ...Eu poderia. Já pensei nisso. Ouvir outra pessoa dizer deixa menor.
    >>  ............................................
  grumpy.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. Aye. It's the obvious thing and the hardest one.
    >>  ............................................
    pt  Conversar. É. É a coisa óbvia e a mais difícil.
    >>  ............................................
  introverted.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Hearing it said makes it smaller.
    >>  ............................................
    pt  ...Eu poderia. Ouvir dito deixa menor.
    >>  ............................................
  introverted.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. Obvious and hardest.
    >>  ............................................
    pt  Conversar. Óbvio e mais difícil.
    >>  ............................................
  lazy.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Fifty years and hearing somebody else say a thing still shrinks it.
    >>  ............................................
    pt  ...Eu poderia. Cinquenta anos e ouvir de outra pessoa ainda encolhe a coisa.
    >>  ............................................
  lazy.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. The obvious thing and the hardest; that pairing has never once changed.
    >>  ............................................
    pt  Conversar. A coisa óbvia e a mais difícil; esse par nunca mudou.
    >>  ............................................
  odd.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Hearing it said makes it smaller.
    >>  ............................................
    pt  ...Eu poderia. Ouvir dito deixa menor.
    >>  ............................................
  odd.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. Obvious and hardest.
    >>  ............................................
    pt  Conversar. Óbvio e mais difícil.
    >>  ............................................
  peaceful.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Fifty years and hearing somebody else say a thing still shrinks it.
    >>  ............................................
    pt  ...Eu poderia. Cinquenta anos e ouvir de outra pessoa ainda encolhe a coisa.
    >>  ............................................
  peaceful.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. The obvious thing and the hardest; that pairing has never once changed.
    >>  ............................................
    pt  Conversar. A coisa óbvia e a mais difícil; esse par nunca mudou.
    >>  ............................................
  peppy.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could! I've thought about it. Hearing somebody else say it shrinks it considerably.
    >>  ............................................
    pt  ...Eu poderia! Já pensei nisso. Ouvir de outra pessoa encolhe bastante.
    >>  ............................................
  peppy.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. It's the obvious thing and the hardest one, which is very inconvenient.
    >>  ............................................
    pt  Conversar. É a coisa óbvia e a mais difícil, o que é bem inconveniente.
    >>  ............................................
  playful.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could! I've thought about it. Hearing somebody else say it shrinks it considerably.
    >>  ............................................
    pt  ...Eu poderia! Já pensei nisso. Ouvir de outra pessoa encolhe bastante.
    >>  ............................................
  playful.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. It's the obvious thing and the hardest one, which is very inconvenient.
    >>  ............................................
    pt  Conversar. É a coisa óbvia e a mais difícil, o que é bem inconveniente.
    >>  ............................................
  relaxed.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Fifty years and hearing somebody else say a thing still shrinks it.
    >>  ............................................
    pt  ...Eu poderia. Cinquenta anos e ouvir de outra pessoa ainda encolhe a coisa.
    >>  ............................................
  relaxed.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. The obvious thing and the hardest; that pairing has never once changed.
    >>  ............................................
    pt  Conversar. A coisa óbvia e a mais difícil; esse par nunca mudou.
    >>  ............................................
  sensitive.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Hearing somebody else say it makes it smaller, and I needed it smaller.
    >>  ............................................
    pt  ...Eu poderia. Ouvir de outra pessoa deixa menor, e eu precisava que ficasse menor.
    >>  ............................................
  sensitive.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. The obvious thing and the hardest, and I've been calling that a coincidence.
    >>  ............................................
    pt  Conversar. A coisa óbvia e a mais difícil, e eu venho chamando isso de coincidência.
    >>  ............................................
  shy.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could. Hearing it said makes it smaller.
    >>  ............................................
    pt  ...Eu poderia. Ouvir dito deixa menor.
    >>  ............................................
  shy.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. Obvious and hardest.
    >>  ............................................
    pt  Conversar. Óbvio e mais difícil.
    >>  ............................................
  upbeat.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could! I've thought about it. Hearing somebody else say it shrinks it considerably.
    >>  ............................................
    pt  ...Eu poderia! Já pensei nisso. Ouvir de outra pessoa encolhe bastante.
    >>  ............................................
  upbeat.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. It's the obvious thing and the hardest one, which is very inconvenient.
    >>  ............................................
    pt  Conversar. É a coisa óbvia e a mais difícil, o que é bem inconveniente.
    >>  ............................................
  witty.dialogue.conversations.people.encourage_repair.success/1
    en  ...I could! I've thought about it. Hearing somebody else say it shrinks it considerably.
    >>  ............................................
    pt  ...Eu poderia! Já pensei nisso. Ouvir de outra pessoa encolhe bastante.
    >>  ............................................
  witty.dialogue.conversations.people.encourage_repair.success/2
    en  Talking. It's the obvious thing and the hardest one, which is very inconvenient.
    >>  ............................................
    pt  Conversar. É a coisa óbvia e a mais difícil, o que é bem inconveniente.
    >>  ............................................
```

</details>


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `people.encourage_repair` lands on tier **partial** (axis respect, difficulty 45, stance respectful_disagreement)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `people.encourage_repair.partial`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +1, tension +1  _(recorded under topic `people.encourage_repair`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.encourage_repair.partial
WHO    VILLAGER — what the player reads after pressing "Have you tried talking to them?"
       spoken on: conversations.topic.people.grumble.followup, button `encourage_repair`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.repair.partial`: the villager resists. Subject `people.repair`, polarity `negative`, ends conversation, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.encourage_repair.partial/1   [48 chars]
    en  You've not met them. Talking is what started it.
    >>  ............................................
    pt  Você não os conhece. Conversar foi o que começou isso.
    >>  ............................................
  dialogue.conversations.people.encourage_repair.partial/2   [34 chars]
    en  Easy to say from outside it, %1$s.
    >>  ............................................
    pt  Fácil dizer de fora, %1$s.
    >>  ............................................
```


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `people.encourage_repair` lands on tier **rebuff** (axis respect, difficulty 45, stance respectful_disagreement)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts -1** — decision id `people.encourage_repair.rebuff`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +5, respect -3  _(recorded under topic `people.encourage_repair`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.encourage_repair.rebuff
WHO    VILLAGER — what the player reads after pressing "Have you tried talking to them?"
       spoken on: conversations.topic.people.grumble.followup, button `encourage_repair`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.repair.rebuff`: the villager refuses. Subject `people.repair`, polarity `negative`, ends conversation, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.encourage_repair.rebuff/1   [83 chars]
    en  Don't. You've heard one side of it for two minutes and you're handing out remedies.
    >>  ............................................
    pt  Não. Você ouviu um lado por dois minutos e já está distribuindo remédios.
    >>  ............................................
  dialogue.conversations.people.encourage_repair.rebuff/2   [86 chars]
    en  Have I tried talking to them. Yes. Remarkably, that occurred to me before you arrived.
    >>  ............................................
    pt  Se eu já tentei conversar. Sim. Surpreendentemente isso me ocorreu antes de você chegar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Please don't. I've tried the obvious things and they made it worse.
    >>  ............................................
    pt  Por favor, não. Eu já tentei o óbvio e piorou.
    >>  ............................................
  anxious.dialogue.conversations.people.encourage_repair.rebuff/2
    en  Don't. It sounds easy from there and it has cost me a great deal from here.
    >>  ............................................
    pt  Não. Soa fácil daí e me custou muito daqui.
    >>  ............................................
  athletic.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. It'll come round or it won't, and it won't be advice that does it.
    >>  ............................................
    pt  Não. Vai se resolver ou não, e não vai ser conselho que resolve.
    >>  ............................................
  athletic.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. These things mend at their own pace, and pushing has never once helped.
    >>  ............................................
    pt  Não. Essas coisas se ajeitam no ritmo delas, e empurrar nunca ajudou.
    >>  ............................................
  confident.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. You've heard one side of it for two minutes and you're handing out remedies.
    >>  ............................................
    pt  Não. Você ouviu um lado por dois minutos e já está distribuindo receitas.
    >>  ............................................
  confident.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. You don't know enough about it to be advising anybody.
    >>  ............................................
    pt  Não. Você não sabe o bastante pra estar aconselhando ninguém.
    >>  ............................................
  crabby.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. You've heard one side of it for two minutes and you're handing out remedies.
    >>  ............................................
    pt  Não. Você ouviu um lado por dois minutos e já está distribuindo receitas.
    >>  ............................................
  crabby.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. You don't know enough about it to be advising anybody.
    >>  ............................................
    pt  Não. Você não sabe o bastante pra estar aconselhando ninguém.
    >>  ............................................
  extroverted.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't, %1$s. I told you about it; I didn't ask you to fix it.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te contei; não pedi pra você consertar.
    >>  ............................................
  extroverted.dialogue.conversations.people.encourage_repair.rebuff/2
    en  That's kindly meant and it's too fast. There's more to it than I've said.
    >>  ............................................
    pt  É bem-intencionado e é rápido demais. Tem mais coisa do que eu disse.
    >>  ............................................
  flirty.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't, %1$s. I told you about it; I didn't ask you to fix it.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te contei; não pedi pra você consertar.
    >>  ............................................
  flirty.dialogue.conversations.people.encourage_repair.rebuff/2
    en  That's kindly meant and it's too fast. There's more to it than I've said.
    >>  ............................................
    pt  É bem-intencionado e é rápido demais. Tem mais coisa do que eu disse.
    >>  ............................................
  friendly.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't, %1$s. I told you about it; I didn't ask you to fix it.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu te contei; não pedi pra você consertar.
    >>  ............................................
  friendly.dialogue.conversations.people.encourage_repair.rebuff/2
    en  That's kindly meant and it's too fast. There's more to it than I've said.
    >>  ............................................
    pt  É bem-intencionado e é rápido demais. Tem mais coisa do que eu disse.
    >>  ............................................
  gloomy.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Please don't. I've tried the obvious things and they made it worse.
    >>  ............................................
    pt  Por favor, não. Eu já tentei o óbvio e piorou.
    >>  ............................................
  gloomy.dialogue.conversations.people.encourage_repair.rebuff/2
    en  Don't. It sounds easy from there and it has cost me a great deal from here.
    >>  ............................................
    pt  Não. Soa fácil daí e me custou muito daqui.
    >>  ............................................
  greedy.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. You've heard one side of it for two minutes and you're handing out remedies.
    >>  ............................................
    pt  Não. Você ouviu um lado por dois minutos e já está distribuindo receitas.
    >>  ............................................
  greedy.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. You don't know enough about it to be advising anybody.
    >>  ............................................
    pt  Não. Você não sabe o bastante pra estar aconselhando ninguém.
    >>  ............................................
  grumpy.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. You've heard one side of it for two minutes and you're handing out remedies.
    >>  ............................................
    pt  Não. Você ouviu um lado por dois minutos e já está distribuindo receitas.
    >>  ............................................
  grumpy.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. You don't know enough about it to be advising anybody.
    >>  ............................................
    pt  Não. Você não sabe o bastante pra estar aconselhando ninguém.
    >>  ............................................
  introverted.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. You've heard two minutes of it.
    >>  ............................................
    pt  Não. Você ouviu dois minutos disso.
    >>  ............................................
  introverted.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. It isn't that simple and I'd rather not explain why.
    >>  ............................................
    pt  Não. Não é tão simples e eu prefiro não explicar por quê.
    >>  ............................................
  lazy.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. It'll come round or it won't, and it won't be advice that does it.
    >>  ............................................
    pt  Não. Vai se resolver ou não, e não vai ser conselho que resolve.
    >>  ............................................
  lazy.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. These things mend at their own pace, and pushing has never once helped.
    >>  ............................................
    pt  Não. Essas coisas se ajeitam no ritmo delas, e empurrar nunca ajudou.
    >>  ............................................
  odd.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. You've heard two minutes of it.
    >>  ............................................
    pt  Não. Você ouviu dois minutos disso.
    >>  ............................................
  odd.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. It isn't that simple and I'd rather not explain why.
    >>  ............................................
    pt  Não. Não é tão simples e eu prefiro não explicar por quê.
    >>  ............................................
  peaceful.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. It'll come round or it won't, and it won't be advice that does it.
    >>  ............................................
    pt  Não. Vai se resolver ou não, e não vai ser conselho que resolve.
    >>  ............................................
  peaceful.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. These things mend at their own pace, and pushing has never once helped.
    >>  ............................................
    pt  Não. Essas coisas se ajeitam no ritmo delas, e empurrar nunca ajudou.
    >>  ............................................
  peppy.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Ah — no. Two minutes and a solution. Very impressive, %1$s.
    >>  ............................................
    pt  Ah — não. Dois minutos e uma solução. Muito impressionante, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.people.encourage_repair.rebuff/2
    en  Right, stop there. That knot took eleven years to tie.
    >>  ............................................
    pt  Certo, pare aí. Esse nó levou onze anos pra ser dado.
    >>  ............................................
  playful.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Ah — no. Two minutes and a solution. Very impressive, %1$s.
    >>  ............................................
    pt  Ah — não. Dois minutos e uma solução. Muito impressionante, %1$s.
    >>  ............................................
  playful.dialogue.conversations.people.encourage_repair.rebuff/2
    en  Right, stop there. That knot took eleven years to tie.
    >>  ............................................
    pt  Certo, pare aí. Esse nó levou onze anos pra ser dado.
    >>  ............................................
  relaxed.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. It'll come round or it won't, and it won't be advice that does it.
    >>  ............................................
    pt  Não. Vai se resolver ou não, e não vai ser conselho que resolve.
    >>  ............................................
  relaxed.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. These things mend at their own pace, and pushing has never once helped.
    >>  ............................................
    pt  Não. Essas coisas se ajeitam no ritmo delas, e empurrar nunca ajudou.
    >>  ............................................
  sensitive.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Please don't. I've tried the obvious things and they made it worse.
    >>  ............................................
    pt  Por favor, não. Eu já tentei o óbvio e piorou.
    >>  ............................................
  sensitive.dialogue.conversations.people.encourage_repair.rebuff/2
    en  Don't. It sounds easy from there and it has cost me a great deal from here.
    >>  ............................................
    pt  Não. Soa fácil daí e me custou muito daqui.
    >>  ............................................
  shy.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Don't. You've heard two minutes of it.
    >>  ............................................
    pt  Não. Você ouviu dois minutos disso.
    >>  ............................................
  shy.dialogue.conversations.people.encourage_repair.rebuff/2
    en  No. It isn't that simple and I'd rather not explain why.
    >>  ............................................
    pt  Não. Não é tão simples e eu prefiro não explicar por quê.
    >>  ............................................
  upbeat.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Ah — no. Two minutes and a solution. Very impressive, %1$s.
    >>  ............................................
    pt  Ah — não. Dois minutos e uma solução. Muito impressionante, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.people.encourage_repair.rebuff/2
    en  Right, stop there. That knot took eleven years to tie.
    >>  ............................................
    pt  Certo, pare aí. Esse nó levou onze anos pra ser dado.
    >>  ............................................
  witty.dialogue.conversations.people.encourage_repair.rebuff/1
    en  Ah — no. Two minutes and a solution. Very impressive, %1$s.
    >>  ............................................
    pt  Ah — não. Dois minutos e uma solução. Muito impressionante, %1$s.
    >>  ............................................
  witty.dialogue.conversations.people.encourage_repair.rebuff/2
    en  Right, stop there. That knot took eleven years to tie.
    >>  ............................................
    pt  Certo, pare aí. Esse nó levou onze anos pra ser dado.
    >>  ............................................
```

</details>


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: **hearts +1** — decision id `people.followup.encourage_repair`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +1  _(recorded under topic `people.followup.encourage_repair`)_
- Does: arc `people` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.followup.encourage_repair
WHO    VILLAGER — what the player reads after pressing "Have you tried talking to them?"
       spoken on: conversations.topic.people.grumble.followup, button `encourage_repair`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.repair.plain`: the villager accepts. Subject `people.repair`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.followup.encourage_repair/1   [58 chars]
    en  ...No. I've been avoiding it for a season. Maybe I should.
    >>  ............................................
    pt  ...Não. Venho evitando faz uma estação. Talvez eu deva.
    >>  ............................................
  dialogue.conversations.people.followup.encourage_repair/2   [59 chars]
    en  Talking. There's a thought. Everyone here prefers a grudge.
    >>  ............................................
    pt  Conversar. Que ideia. Todo mundo aqui prefere uma mágoa.
    >>  ............................................
  dialogue.conversations.people.followup.encourage_repair/3   [43 chars]
    en  You make it sound simple. It might even be.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. I've been avoiding it for a season and I know exactly why.
    >>  ............................................
    pt  ...Não. Venho evitando uma estação e sei exatamente por quê.
    >>  ............................................
  anxious.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge because a grudge doesn't answer back.
    >>  ............................................
    pt  Conversar. Todos aqui preferem rancor porque rancor não responde.
    >>  ............................................
  anxious.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It isn't, and I'd like to try anyway.
    >>  ............................................
    pt  Você faz parecer simples. Não é, e eu quero tentar mesmo assim.
    >>  ............................................
  athletic.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. A season of avoiding it, and I've avoided longer than that before.
    >>  ............................................
    pt  ...Não. Uma estação evitando, e já evitei por mais tempo que isso.
    >>  ............................................
  athletic.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge; I've watched two families do forty years of it.
    >>  ............................................
    pt  Conversar. Todos preferem rancor; vi duas famílias fazerem quarenta anos disso.
    >>  ............................................
  athletic.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. At my age simple and easy stopped being the same word.
    >>  ............................................
    pt  Você faz parecer simples. Na minha idade simples e fácil deixaram de ser a mesma palavra.
    >>  ............................................
  confident.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. I've been avoiding it for a season. Maybe I should.
    >>  ............................................
    pt  ...Não. Venho evitando faz uma estação. Talvez eu devesse.
    >>  ............................................
  confident.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. There's a thought. Everyone here prefers a grudge.
    >>  ............................................
    pt  Conversar. Que ideia. Todo mundo aqui prefere um rancor.
    >>  ............................................
  confident.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It might even be.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja.
    >>  ............................................
  crabby.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. I've been avoiding it for a season. Maybe I should.
    >>  ............................................
    pt  ...Não. Venho evitando faz uma estação. Talvez eu devesse.
    >>  ............................................
  crabby.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. There's a thought. Everyone here prefers a grudge.
    >>  ............................................
    pt  Conversar. Que ideia. Todo mundo aqui prefere um rancor.
    >>  ............................................
  crabby.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It might even be.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja.
    >>  ............................................
  extroverted.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No, %1$s. I've been avoiding it for a season and you've noticed.
    >>  ............................................
    pt  ...Não, %1$s. Venho evitando uma estação e você notou.
    >>  ............................................
  extroverted.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. There's a thought. Everyone here prefers a grudge, myself included.
    >>  ............................................
    pt  Conversar. Que ideia. Todo mundo aqui prefere um rancor, eu inclusive.
    >>  ............................................
  extroverted.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. Coming from you it might even be.
    >>  ............................................
    pt  Você faz parecer simples. Vindo de você talvez até seja.
    >>  ............................................
  flirty.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No, %1$s. I've been avoiding it for a season and you've noticed.
    >>  ............................................
    pt  ...Não, %1$s. Venho evitando uma estação e você notou.
    >>  ............................................
  flirty.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. There's a thought. Everyone here prefers a grudge, myself included.
    >>  ............................................
    pt  Conversar. Que ideia. Todo mundo aqui prefere um rancor, eu inclusive.
    >>  ............................................
  flirty.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. Coming from you it might even be.
    >>  ............................................
    pt  Você faz parecer simples. Vindo de você talvez até seja.
    >>  ............................................
  friendly.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No, %1$s. I've been avoiding it for a season and you've noticed.
    >>  ............................................
    pt  ...Não, %1$s. Venho evitando uma estação e você notou.
    >>  ............................................
  friendly.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. There's a thought. Everyone here prefers a grudge, myself included.
    >>  ............................................
    pt  Conversar. Que ideia. Todo mundo aqui prefere um rancor, eu inclusive.
    >>  ............................................
  friendly.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. Coming from you it might even be.
    >>  ............................................
    pt  Você faz parecer simples. Vindo de você talvez até seja.
    >>  ............................................
  gloomy.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. I've been avoiding it for a season and I know exactly why.
    >>  ............................................
    pt  ...Não. Venho evitando uma estação e sei exatamente por quê.
    >>  ............................................
  gloomy.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge because a grudge doesn't answer back.
    >>  ............................................
    pt  Conversar. Todos aqui preferem rancor porque rancor não responde.
    >>  ............................................
  gloomy.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It isn't, and I'd like to try anyway.
    >>  ............................................
    pt  Você faz parecer simples. Não é, e eu quero tentar mesmo assim.
    >>  ............................................
  greedy.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. I've been avoiding it for a season. Maybe I should.
    >>  ............................................
    pt  ...Não. Venho evitando faz uma estação. Talvez eu devesse.
    >>  ............................................
  greedy.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. There's a thought. Everyone here prefers a grudge.
    >>  ............................................
    pt  Conversar. Que ideia. Todo mundo aqui prefere um rancor.
    >>  ............................................
  greedy.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It might even be.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja.
    >>  ............................................
  grumpy.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. I've been avoiding it for a season. Maybe I should.
    >>  ............................................
    pt  ...Não. Venho evitando faz uma estação. Talvez eu devesse.
    >>  ............................................
  grumpy.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. There's a thought. Everyone here prefers a grudge.
    >>  ............................................
    pt  Conversar. Que ideia. Todo mundo aqui prefere um rancor.
    >>  ............................................
  grumpy.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It might even be.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja.
    >>  ............................................
  introverted.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. A season of avoiding it.
    >>  ............................................
    pt  ...Não. Uma estação evitando.
    >>  ............................................
  introverted.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge.
    >>  ............................................
    pt  Conversar. Todo mundo aqui prefere um rancor.
    >>  ............................................
  introverted.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple.
    >>  ............................................
    pt  Você faz parecer simples.
    >>  ............................................
  lazy.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. A season of avoiding it, and I've avoided longer than that before.
    >>  ............................................
    pt  ...Não. Uma estação evitando, e já evitei por mais tempo que isso.
    >>  ............................................
  lazy.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge; I've watched two families do forty years of it.
    >>  ............................................
    pt  Conversar. Todos preferem rancor; vi duas famílias fazerem quarenta anos disso.
    >>  ............................................
  lazy.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. At my age simple and easy stopped being the same word.
    >>  ............................................
    pt  Você faz parecer simples. Na minha idade simples e fácil deixaram de ser a mesma palavra.
    >>  ............................................
  odd.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. A season of avoiding it.
    >>  ............................................
    pt  ...Não. Uma estação evitando.
    >>  ............................................
  odd.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge.
    >>  ............................................
    pt  Conversar. Todo mundo aqui prefere um rancor.
    >>  ............................................
  odd.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple.
    >>  ............................................
    pt  Você faz parecer simples.
    >>  ............................................
  peaceful.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. A season of avoiding it, and I've avoided longer than that before.
    >>  ............................................
    pt  ...Não. Uma estação evitando, e já evitei por mais tempo que isso.
    >>  ............................................
  peaceful.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge; I've watched two families do forty years of it.
    >>  ............................................
    pt  Conversar. Todos preferem rancor; vi duas famílias fazerem quarenta anos disso.
    >>  ............................................
  peaceful.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. At my age simple and easy stopped being the same word.
    >>  ............................................
    pt  Você faz parecer simples. Na minha idade simples e fácil deixaram de ser a mesma palavra.
    >>  ............................................
  peppy.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No! I've been avoiding it for a whole season. Maybe I should, at that.
    >>  ............................................
    pt  ...Não! Venho evitando uma estação inteira. Talvez eu devesse mesmo.
    >>  ............................................
  peppy.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking! There's a thought. Everyone here would rather keep a good grudge.
    >>  ............................................
    pt  Conversar! Que ideia. Todo mundo aqui prefere manter um bom rancor.
    >>  ............................................
  peppy.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It might even be, which is annoying.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja, o que é irritante.
    >>  ............................................
  playful.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No! I've been avoiding it for a whole season. Maybe I should, at that.
    >>  ............................................
    pt  ...Não! Venho evitando uma estação inteira. Talvez eu devesse mesmo.
    >>  ............................................
  playful.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking! There's a thought. Everyone here would rather keep a good grudge.
    >>  ............................................
    pt  Conversar! Que ideia. Todo mundo aqui prefere manter um bom rancor.
    >>  ............................................
  playful.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It might even be, which is annoying.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja, o que é irritante.
    >>  ............................................
  relaxed.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. A season of avoiding it, and I've avoided longer than that before.
    >>  ............................................
    pt  ...Não. Uma estação evitando, e já evitei por mais tempo que isso.
    >>  ............................................
  relaxed.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge; I've watched two families do forty years of it.
    >>  ............................................
    pt  Conversar. Todos preferem rancor; vi duas famílias fazerem quarenta anos disso.
    >>  ............................................
  relaxed.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. At my age simple and easy stopped being the same word.
    >>  ............................................
    pt  Você faz parecer simples. Na minha idade simples e fácil deixaram de ser a mesma palavra.
    >>  ............................................
  sensitive.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. I've been avoiding it for a season and I know exactly why.
    >>  ............................................
    pt  ...Não. Venho evitando uma estação e sei exatamente por quê.
    >>  ............................................
  sensitive.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge because a grudge doesn't answer back.
    >>  ............................................
    pt  Conversar. Todos aqui preferem rancor porque rancor não responde.
    >>  ............................................
  sensitive.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It isn't, and I'd like to try anyway.
    >>  ............................................
    pt  Você faz parecer simples. Não é, e eu quero tentar mesmo assim.
    >>  ............................................
  shy.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No. A season of avoiding it.
    >>  ............................................
    pt  ...Não. Uma estação evitando.
    >>  ............................................
  shy.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking. Everyone here prefers a grudge.
    >>  ............................................
    pt  Conversar. Todo mundo aqui prefere um rancor.
    >>  ............................................
  shy.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple.
    >>  ............................................
    pt  Você faz parecer simples.
    >>  ............................................
  upbeat.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No! I've been avoiding it for a whole season. Maybe I should, at that.
    >>  ............................................
    pt  ...Não! Venho evitando uma estação inteira. Talvez eu devesse mesmo.
    >>  ............................................
  upbeat.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking! There's a thought. Everyone here would rather keep a good grudge.
    >>  ............................................
    pt  Conversar! Que ideia. Todo mundo aqui prefere manter um bom rancor.
    >>  ............................................
  upbeat.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It might even be, which is annoying.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja, o que é irritante.
    >>  ............................................
  witty.dialogue.conversations.people.followup.encourage_repair/1
    en  ...No! I've been avoiding it for a whole season. Maybe I should, at that.
    >>  ............................................
    pt  ...Não! Venho evitando uma estação inteira. Talvez eu devesse mesmo.
    >>  ............................................
  witty.dialogue.conversations.people.followup.encourage_repair/2
    en  Talking! There's a thought. Everyone here would rather keep a good grudge.
    >>  ............................................
    pt  Conversar! Que ideia. Todo mundo aqui prefere manter um bom rancor.
    >>  ............................................
  witty.dialogue.conversations.people.followup.encourage_repair/3
    en  You make it sound simple. It might even be, which is annoying.
    >>  ............................................
    pt  Você faz parecer simples. Talvez até seja, o que é irritante.
    >>  ............................................
```

</details>


### Button `joke` — "You'd all be lost without somebody to complain about."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `people.agreed` · offered only once the villager has actually said `grievance:shared`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.grumble.joke` — accepted phrasings: "you would all be lost without someone to complain about"; "you would miss having someone to complain about"; "what would you do without someone to grumble about"
  - the message must contain one of: `complain`, `lost`
  - scored words: `complain`(1.0), `lost`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.people.grumble.followup.joke
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.grumble.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.grumble.followup.joke   [53 chars]
    en  You'd all be lost without somebody to complain about.
    >>  ............................................
    pt  Vocês estariam perdidos sem alguém de quem reclamar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.followup.joke`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, tension -2  _(recorded under topic `people.followup.joke`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.followup.joke
WHO    VILLAGER — what the player reads after pressing "You'd all be lost without somebody to complain about."
       spoken on: conversations.topic.people.grumble.followup, button `joke`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.joked`: the villager accepts. Subject `people.wry`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.followup.joke/1   [83 chars]
    en  Ha! We would. Take away the complaining and there'd be nothing to do of an evening.
    >>  ............................................
    pt  Ha! Estaríamos. Tire a reclamação e não sobra nada para fazer à noite.
    >>  ............................................
  dialogue.conversations.people.followup.joke/2   [82 chars]
    en  That's uncomfortably close to the truth, %1$s. I'd like it noted that I resent it.
    >>  ............................................
    pt  Isso é desconfortavelmente perto da verdade, %1$s. Faço questão de registrar que me incomoda.
    >>  ............................................
  dialogue.conversations.people.followup.joke/3   [80 chars]
    en  Lost entirely. It's the one thing that keeps us all turning up to the same well.
    >>  ............................................
    pt  Totalmente perdidos. É a única coisa que faz todo mundo aparecer no mesmo poço.
    >>  ............................................
```


### Button `leave` — "Best I stay out of it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.agreed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.grumble.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.grumble.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.grumble.followup.leave   [22 chars]
    en  Best I stay out of it.
    >>  ............................................
    pt  Melhor eu ficar fora disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.respond.leave
WHO    VILLAGER — what the player reads after pressing "Best I stay out of it."
       spoken on: conversations.topic.people.grumble.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.left`: the villager accepts. Subject `people.first`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.gossip.followup / leave; conversations.topic.people.mixed.respond / leave; conversations.topic.people.rebuffed.followup / leave; conversations.topic.people.softened.followup / leave; conversations.topic.people.sour.respond / leave
```

> Written out in full under **`conversations.topic.people.gossip.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.people.mixed.respond`

**Reached from 4 route(s):** `conversations.cat.village` / `people`; `conversations.cat.village` / `people`; `conversations.cat.village` / `people`; `conversations.cat.village` / `people`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.first` — e.g. "Good folk, mostly. We share fences, weather, and opinions about each other. It works."
- `conversations.people.revisit` — e.g. "You asked about the neighbors once. I've warmed up on a couple of them since. Slightly."
- `conversations.people.shy` — e.g. "They're... a lot. Kind, but a lot. I like them best from my window, waving."
- `conversations.people.wry` — e.g. "A finer collection of characters was never assembled on purpose. Note: it wasn't on purpose."


```text
POOL   dialogue key: dialogue.conversations.topic.people.mixed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.mixed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.mixed.respond   [30 chars]
    en  That's the neighbours for you.
    >>  ............................................
    pt  São esses os vizinhos.
    >>  ............................................
```


### Button `agree` — "I've noticed the same."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `people.first_view`, `people.revisited`, `people.wry_view`, `people.guarded_view` · offered only once the villager has actually said `people:mixed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.mixed.agree` — accepted phrasings: "i have noticed the same"; "i had noticed that too"; "that matches what i have seen"
  - the message must contain one of: `noticed`
  - scored words: `noticed`(1.0), `same`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.people.mixed.respond.agree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.mixed.respond.agree   [22 chars]
    en  I've noticed the same.
    >>  ............................................
    pt  Notei o mesmo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.mixed.agree`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `people.respond.agree`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.grumble.followup`
- …where the player's next choices will be: "That sounds exhausting." | "Have you tried talking to them?" | "You'd all be lost without somebody to complain about." | "Best I stay out of it."

```text
POOL   dialogue key: dialogue.conversations.people.respond.agree
WHO    VILLAGER — what the player reads after pressing "I've noticed the same."
       spoken on: conversations.topic.people.mixed.respond, button `agree`
       leaves the player on: conversations.topic.people.grumble.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.agreed`: the villager accepts. Subject `people.affection`, polarity `mixed`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `people:mixed`, `grievance:shared` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, respectful_disagreement, humor, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.sour.respond / agree
```

```text
  dialogue.conversations.people.respond.agree/1   [49 chars]
    en  Then it isn't just me. That's a relief, honestly.
    >>  ............................................
    pt  Então não sou só eu. É um alívio, sinceramente.
    >>  ............................................
  dialogue.conversations.people.respond.agree/2   [56 chars]
    en  You've seen it too. Nobody says it out loud around here.
    >>  ............................................
    pt  Você também viu. Ninguém fala em voz alta por aqui.
    >>  ............................................
  dialogue.conversations.people.respond.agree/3   [75 chars]
    en  Quite. Good neighbours, mostly. Mostly is doing some work in that sentence.
    >>  ............................................
    pt  Exato. Bons vizinhos, na maioria. 'Na maioria' está trabalhando bastante nessa frase.
    >>  ............................................
```


### Button `ask_example` — "Give me an example."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `people.first_view`, `people.revisited`, `people.wry_view`, `people.guarded_view` · offered only once the villager has actually said `people:mixed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.mixed.ask_example` — accepted phrasings: "give me an example"; "can you give me an example"; "what is an example of that"
  - the message must contain one of: `example`, `instance`
  - scored words: `example`(1.5), `instance`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.people.mixed.respond.ask_example
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.mixed.respond.ask_example   [19 chars]
    en  Give me an example.
    >>  ............................................
    pt  Me dê um exemplo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `people.respond.ask_example`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.gossip.followup`
- …where the player's next choices will be: "Go on — who's the worst?" | "It stays with me." | "Actually — don't tell me." | "Best I stay out of it."

```text
POOL   dialogue key: dialogue.conversations.people.respond.ask_example
WHO    VILLAGER — what the player reads after pressing "Give me an example."
       spoken on: conversations.topic.people.mixed.respond, button `ask_example`
       leaves the player on: conversations.topic.people.gossip.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.example_offered`: the villager invites. Subject `people.guarded`, polarity `mixed`, guarded, outcome `engaged`.
NOTE   this is the line that establishes `people:mixed`, `gossip:offered` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: boundary_push, restraint, candor, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.sour.respond / ask_example
```

```text
  dialogue.conversations.people.respond.ask_example/1   [54 chars]
    en  You want the specifics. Alright — but this stays here.
    >>  ............................................
    pt  Você quer os detalhes. Certo — mas isso fica aqui.
    >>  ............................................
  dialogue.conversations.people.respond.ask_example/2   [50 chars]
    en  One example. Just one, or we'll be here till dark.
    >>  ............................................
    pt  Um exemplo. Só um, senão ficamos aqui até escurecer.
    >>  ............................................
  dialogue.conversations.people.respond.ask_example/3   [32 chars]
    en  Ha. How long have you got, %1$s?
    >>  ............................................
    pt  Rá. Quanto tempo você tem, %1$s?
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here, %1$s. Please.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui, %1$s. Por favor.
    >>  ............................................
  anxious.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I'll regret this by tonight, so let me get through it quickly.
    >>  ............................................
    pt  Detalhes, então. Vou me arrepender até a noite, então me deixe terminar rápido.
    >>  ............................................
  anxious.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. And then I'd like to talk about something else entirely.
    >>  ............................................
    pt  Certo. Um exemplo. E depois eu queria falar de outra coisa completamente.
    >>  ............................................
  athletic.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here, and it'll stay here for years.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui, e vai ficar aqui por anos.
    >>  ............................................
  athletic.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I've sat on this a long while; a little longer wouldn't have hurt.
    >>  ............................................
    pt  Detalhes, então. Eu guardei isso muito tempo; um pouco mais não teria doído.
    >>  ............................................
  athletic.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. Told slowly, and it goes no further than this room.
    >>  ............................................
    pt  Certo. Um exemplo. Contado devagar, e não vai além desta sala.
    >>  ............................................
  confident.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui.
    >>  ............................................
  confident.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. And it goes no further than the two of us.
    >>  ............................................
    pt  Detalhes, então. E não vai além de nós dois.
    >>  ............................................
  confident.dialogue.conversations.people.respond.ask_example/3
    en  Right. I'll give you one. Only one, and it stays with you.
    >>  ............................................
    pt  Certo. Vou te dar um. Só um, e fica com você.
    >>  ............................................
  crabby.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui.
    >>  ............................................
  crabby.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. And it goes no further than the two of us.
    >>  ............................................
    pt  Detalhes, então. E não vai além de nós dois.
    >>  ............................................
  crabby.dialogue.conversations.people.respond.ask_example/3
    en  Right. I'll give you one. Only one, and it stays with you.
    >>  ............................................
    pt  Certo. Vou te dar um. Só um, e fica com você.
    >>  ............................................
  extroverted.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright, %1$s — but this stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem, %1$s — mas isso fica aqui.
    >>  ............................................
  extroverted.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I'm telling you because I trust you with it, and that's not nothing.
    >>  ............................................
    pt  Detalhes, então. Estou contando porque eu confio em você, e isso não é nada.
    >>  ............................................
  extroverted.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. And you'll understand why I've not said it before.
    >>  ............................................
    pt  Certo. Um exemplo. E você vai entender por que eu não disse antes.
    >>  ............................................
  flirty.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright, %1$s — but this stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem, %1$s — mas isso fica aqui.
    >>  ............................................
  flirty.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I'm telling you because I trust you with it, and that's not nothing.
    >>  ............................................
    pt  Detalhes, então. Estou contando porque eu confio em você, e isso não é nada.
    >>  ............................................
  flirty.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. And you'll understand why I've not said it before.
    >>  ............................................
    pt  Certo. Um exemplo. E você vai entender por que eu não disse antes.
    >>  ............................................
  friendly.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright, %1$s — but this stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem, %1$s — mas isso fica aqui.
    >>  ............................................
  friendly.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I'm telling you because I trust you with it, and that's not nothing.
    >>  ............................................
    pt  Detalhes, então. Estou contando porque eu confio em você, e isso não é nada.
    >>  ............................................
  friendly.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. And you'll understand why I've not said it before.
    >>  ............................................
    pt  Certo. Um exemplo. E você vai entender por que eu não disse antes.
    >>  ............................................
  gloomy.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here, %1$s. Please.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui, %1$s. Por favor.
    >>  ............................................
  gloomy.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I'll regret this by tonight, so let me get through it quickly.
    >>  ............................................
    pt  Detalhes, então. Vou me arrepender até a noite, então me deixe terminar rápido.
    >>  ............................................
  gloomy.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. And then I'd like to talk about something else entirely.
    >>  ............................................
    pt  Certo. Um exemplo. E depois eu queria falar de outra coisa completamente.
    >>  ............................................
  greedy.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui.
    >>  ............................................
  greedy.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. And it goes no further than the two of us.
    >>  ............................................
    pt  Detalhes, então. E não vai além de nós dois.
    >>  ............................................
  greedy.dialogue.conversations.people.respond.ask_example/3
    en  Right. I'll give you one. Only one, and it stays with you.
    >>  ............................................
    pt  Certo. Vou te dar um. Só um, e fica com você.
    >>  ............................................
  grumpy.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui.
    >>  ............................................
  grumpy.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. And it goes no further than the two of us.
    >>  ............................................
    pt  Detalhes, então. E não vai além de nós dois.
    >>  ............................................
  grumpy.dialogue.conversations.people.respond.ask_example/3
    en  Right. I'll give you one. Only one, and it stays with you.
    >>  ............................................
    pt  Certo. Vou te dar um. Só um, e fica com você.
    >>  ............................................
  introverted.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright. This stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem. Isso fica aqui.
    >>  ............................................
  introverted.dialogue.conversations.people.respond.ask_example/2
    en  Specifics. It goes no further.
    >>  ............................................
    pt  Detalhes. Não vai adiante.
    >>  ............................................
  introverted.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. That's all.
    >>  ............................................
    pt  Certo. Um exemplo. Só isso.
    >>  ............................................
  lazy.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here, and it'll stay here for years.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui, e vai ficar aqui por anos.
    >>  ............................................
  lazy.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I've sat on this a long while; a little longer wouldn't have hurt.
    >>  ............................................
    pt  Detalhes, então. Eu guardei isso muito tempo; um pouco mais não teria doído.
    >>  ............................................
  lazy.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. Told slowly, and it goes no further than this room.
    >>  ............................................
    pt  Certo. Um exemplo. Contado devagar, e não vai além desta sala.
    >>  ............................................
  odd.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright. This stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem. Isso fica aqui.
    >>  ............................................
  odd.dialogue.conversations.people.respond.ask_example/2
    en  Specifics. It goes no further.
    >>  ............................................
    pt  Detalhes. Não vai adiante.
    >>  ............................................
  odd.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. That's all.
    >>  ............................................
    pt  Certo. Um exemplo. Só isso.
    >>  ............................................
  peaceful.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here, and it'll stay here for years.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui, e vai ficar aqui por anos.
    >>  ............................................
  peaceful.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I've sat on this a long while; a little longer wouldn't have hurt.
    >>  ............................................
    pt  Detalhes, então. Eu guardei isso muito tempo; um pouco mais não teria doído.
    >>  ............................................
  peaceful.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. Told slowly, and it goes no further than this room.
    >>  ............................................
    pt  Certo. Um exemplo. Contado devagar, e não vai além desta sala.
    >>  ............................................
  peppy.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics! Alright — but this stays here, on pain of never hearing anything again.
    >>  ............................................
    pt  Você quer os detalhes! Está bem — mas isso fica aqui, sob pena de nunca mais ouvir nada.
    >>  ............................................
  peppy.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then! And if this comes back to me I shall be extremely disappointed and very loud.
    >>  ............................................
    pt  Detalhes, então! E se isso voltar pra mim eu vou ficar extremamente decepcionado e muito barulhento.
    >>  ............................................
  peppy.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. It stays with you or I'm finished telling you things.
    >>  ............................................
    pt  Certo. Um exemplo. Fica com você ou eu paro de te contar coisas.
    >>  ............................................
  playful.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics! Alright — but this stays here, on pain of never hearing anything again.
    >>  ............................................
    pt  Você quer os detalhes! Está bem — mas isso fica aqui, sob pena de nunca mais ouvir nada.
    >>  ............................................
  playful.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then! And if this comes back to me I shall be extremely disappointed and very loud.
    >>  ............................................
    pt  Detalhes, então! E se isso voltar pra mim eu vou ficar extremamente decepcionado e muito barulhento.
    >>  ............................................
  playful.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. It stays with you or I'm finished telling you things.
    >>  ............................................
    pt  Certo. Um exemplo. Fica com você ou eu paro de te contar coisas.
    >>  ............................................
  relaxed.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here, and it'll stay here for years.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui, e vai ficar aqui por anos.
    >>  ............................................
  relaxed.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I've sat on this a long while; a little longer wouldn't have hurt.
    >>  ............................................
    pt  Detalhes, então. Eu guardei isso muito tempo; um pouco mais não teria doído.
    >>  ............................................
  relaxed.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. Told slowly, and it goes no further than this room.
    >>  ............................................
    pt  Certo. Um exemplo. Contado devagar, e não vai além desta sala.
    >>  ............................................
  sensitive.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright — but this stays here, %1$s. Please.
    >>  ............................................
    pt  Você quer os detalhes. Está bem — mas isso fica aqui, %1$s. Por favor.
    >>  ............................................
  sensitive.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then. I'll regret this by tonight, so let me get through it quickly.
    >>  ............................................
    pt  Detalhes, então. Vou me arrepender até a noite, então me deixe terminar rápido.
    >>  ............................................
  sensitive.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. And then I'd like to talk about something else entirely.
    >>  ............................................
    pt  Certo. Um exemplo. E depois eu queria falar de outra coisa completamente.
    >>  ............................................
  shy.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics. Alright. This stays here.
    >>  ............................................
    pt  Você quer os detalhes. Está bem. Isso fica aqui.
    >>  ............................................
  shy.dialogue.conversations.people.respond.ask_example/2
    en  Specifics. It goes no further.
    >>  ............................................
    pt  Detalhes. Não vai adiante.
    >>  ............................................
  shy.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. That's all.
    >>  ............................................
    pt  Certo. Um exemplo. Só isso.
    >>  ............................................
  upbeat.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics! Alright — but this stays here, on pain of never hearing anything again.
    >>  ............................................
    pt  Você quer os detalhes! Está bem — mas isso fica aqui, sob pena de nunca mais ouvir nada.
    >>  ............................................
  upbeat.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then! And if this comes back to me I shall be extremely disappointed and very loud.
    >>  ............................................
    pt  Detalhes, então! E se isso voltar pra mim eu vou ficar extremamente decepcionado e muito barulhento.
    >>  ............................................
  upbeat.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. It stays with you or I'm finished telling you things.
    >>  ............................................
    pt  Certo. Um exemplo. Fica com você ou eu paro de te contar coisas.
    >>  ............................................
  witty.dialogue.conversations.people.respond.ask_example/1
    en  You want the specifics! Alright — but this stays here, on pain of never hearing anything again.
    >>  ............................................
    pt  Você quer os detalhes! Está bem — mas isso fica aqui, sob pena de nunca mais ouvir nada.
    >>  ............................................
  witty.dialogue.conversations.people.respond.ask_example/2
    en  Specifics, then! And if this comes back to me I shall be extremely disappointed and very loud.
    >>  ............................................
    pt  Detalhes, então! E se isso voltar pra mim eu vou ficar extremamente decepcionado e muito barulhento.
    >>  ............................................
  witty.dialogue.conversations.people.respond.ask_example/3
    en  Right. One example. It stays with you or I'm finished telling you things.
    >>  ............................................
    pt  Certo. Um exemplo. Fica com você ou eu paro de te contar coisas.
    >>  ............................................
```

</details>


### Button `blunt` — "Some of that sounds like your side of it."

*stance family `challenge` · tone `blunt` · outcome `qualified` · answers the beat(s) `people.first_view`, `people.revisited`, `people.wry_view`, `people.guarded_view` · offered only once the villager has actually said `people:mixed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.mixed.blunt` — accepted phrasings: "some of that sounds like your side of it"; "that sounds like your half of the story"; "some of that is your doing"
  - the message must contain one of: `side`
  - scored words: `side`(1.2), `sounds`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.people.mixed.respond.blunt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.mixed.respond.blunt   [41 chars]
    en  Some of that sounds like your side of it.
    >>  ............................................
    pt  Parte disso soa como a sua versão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.mixed.blunt`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, tension +2  _(recorded under topic `people.blunt`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.blunt
WHO    VILLAGER — what the player reads after pressing "Some of that sounds like your side of it."
       spoken on: conversations.topic.people.mixed.respond, button `blunt`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.blunted`: the villager qualifys. Subject `people.conflict`, polarity `mixed`, ends conversation, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.blunt/1   [93 chars]
    en  ...It is my side of it. That's what a side is. But — aye. I'd not thought to say so out loud.
    >>  ............................................
    pt  ...É a minha versão. É isso que uma versão é. Mas — é. Não tinha pensado em dizer em voz alta.
    >>  ............................................
  dialogue.conversations.people.blunt/2   [87 chars]
    en  Careful, %1$s. ...No, you're right. It's the version that flatters me and I know it is.
    >>  ............................................
    pt  Cuidado, %1$s. ...Não, você tem razão. É a versão que me favorece e eu sei disso.
    >>  ............................................
  dialogue.conversations.people.blunt/3   [91 chars]
    en  Hm. It's easier to agree with whoever's in front of you. You didn't. I'll think about that.
    >>  ............................................
    pt  Hm. É mais fácil concordar com quem está na sua frente. Você não concordou. Vou pensar nisso.
    >>  ............................................
```


### Button `leave` — "I'll not stir it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.first_view`, `people.revisited`, `people.wry_view`, `people.guarded_view` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.mixed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.mixed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.mixed.respond.leave   [17 chars]
    en  I'll not stir it.
    >>  ............................................
    pt  Não vou remexer nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll not stir it."
       spoken on: conversations.topic.people.mixed.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.left`: the villager accepts. Subject `people.first`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.gossip.followup / leave; conversations.topic.people.grumble.followup / leave; conversations.topic.people.rebuffed.followup / leave; conversations.topic.people.softened.followup / leave; conversations.topic.people.sour.respond / leave
```

> Written out in full under **`conversations.topic.people.gossip.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.people.rebuffed.followup`

**Reached from 1 route(s):** `conversations.topic.people.sour.respond` / `defend`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.respond.defend.flat` — e.g. "You've lived here five minutes, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.people.rebuffed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.rebuffed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.rebuffed.followup   [34 chars]
    en  Give it a winter and ask me again.
    >>  ............................................
    pt  Espere um inverno e me pergunte de novo.
    >>  ............................................
```


### Button `concede` — "Fair. I've not earned an opinion yet."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `people.defend_rebuffed` · offered only once the villager has actually said `player:too_new`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.rebuffed.concede` — accepted phrasings: "fair, i have not earned an opinion yet"; "you are right, i am new here"; "fair enough, i have not been here long"
  - the message must contain one of: `earned`, `opinion`
  - scored words: `earned`(1.5), `opinion`(1.2), `fair`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.people.rebuffed.followup.concede
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.rebuffed.followup.concede   [37 chars]
    en  Fair. I've not earned an opinion yet.
    >>  ............................................
    pt  Justo. Ainda não ganhei o direito de opinar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, tension -2  _(recorded under topic `people.rebuffed.concede`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.rebuffed.concede
WHO    VILLAGER — what the player reads after pressing "Fair. I've not earned an opinion yet."
       spoken on: conversations.topic.people.rebuffed.followup, button `concede`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.rebuffed.concede`: the villager accepts. Subject `people.conflict`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.rebuffed.concede/1   [72 chars]
    en  ...That's a better answer than I deserved for that. Come back in spring.
    >>  ............................................
    pt  ...É uma resposta melhor do que eu merecia. Volte na primavera.
    >>  ............................................
  dialogue.conversations.people.rebuffed.concede/2   [45 chars]
    en  Few people say that. Most argue harder, %1$s.
    >>  ............................................
    pt  Pouca gente diz isso. A maioria discute mais, %1$s.
    >>  ............................................
  dialogue.conversations.people.rebuffed.concede/3   [53 chars]
    en  True enough. Earn one and I'll listen to it properly.
    >>  ............................................
    pt  Bem verdade. Ganhe uma e eu escuto direito.
    >>  ............................................
```


### Button `hold` — "I'll keep my opinion anyway."

*stance family `restraint` · tone `plain` · outcome `resisted` · answers the beat(s) `people.defend_rebuffed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.rebuffed.hold` — accepted phrasings: "i will keep my opinion anyway"; "my opinion stands"; "i stand by what i said"
  - the message must contain one of: `keep`, `anyway`, `stand`
  - scored words: `keep`(1.2), `anyway`(1.5), `stand`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.people.rebuffed.followup.hold
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.rebuffed.followup.hold   [28 chars]
    en  I'll keep my opinion anyway.
    >>  ............................................
    pt  Vou manter minha opinião mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1, tension +1  _(recorded under topic `people.rebuffed.hold`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.rebuffed.hold
WHO    VILLAGER — what the player reads after pressing "I'll keep my opinion anyway."
       spoken on: conversations.topic.people.rebuffed.followup, button `hold`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.rebuffed.hold`: the villager resists. Subject `people.conflict`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.rebuffed.hold/1   [37 chars]
    en  Keep it, then. Quietly, if you would.
    >>  ............................................
    pt  Então mantenha. Em silêncio, se puder.
    >>  ............................................
  dialogue.conversations.people.rebuffed.hold/2   [49 chars]
    en  You would. Everyone does, %1$s, for about a year.
    >>  ............................................
    pt  Claro que vai. Todo mundo mantém, %1$s, por uns um ano.
    >>  ............................................
  dialogue.conversations.people.rebuffed.hold/3   [46 chars]
    en  Mm. We'll see which of us is right about them.
    >>  ............................................
    pt  Mm. A gente vê qual de nós está certo sobre eles.
    >>  ............................................
```


### Button `leave` — "Best I stay out of it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.defend_rebuffed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.rebuffed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.rebuffed.followup.leave   [22 chars]
    en  Best I stay out of it.
    >>  ............................................
    pt  Melhor eu ficar fora disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.respond.leave
WHO    VILLAGER — what the player reads after pressing "Best I stay out of it."
       spoken on: conversations.topic.people.rebuffed.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.left`: the villager accepts. Subject `people.first`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.gossip.followup / leave; conversations.topic.people.grumble.followup / leave; conversations.topic.people.mixed.respond / leave; conversations.topic.people.softened.followup / leave; conversations.topic.people.sour.respond / leave
```

> Written out in full under **`conversations.topic.people.gossip.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.people.softened.followup`

**Reached from 3 route(s):** `conversations.topic.people.sour.respond` / `defend`; `conversations.topic.people.sour.respond` / `defend`; `conversations.topic.people.sour.respond` / `defend`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.defend.low` — e.g. "...Perhaps. I'm in no state to be fair about them today, and you're probably right anyway."
- `conversations.people.respond.defend.landed` — e.g. "...You're right. I'm being unfair and you said so kindly."
- `conversations.people.respond.defend.polite` — e.g. "They're not, mostly. I'm just tired of them this week."


```text
POOL   dialogue key: dialogue.conversations.topic.people.softened.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.softened.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.softened.followup   [40 chars]
    en  ...Fine. Perhaps I'm being hard on them.
    >>  ............................................
    pt  ...Tá. Talvez eu esteja sendo duro com eles.
    >>  ............................................
```


### Button `reassure` — "We're all difficult to live beside."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `people.softened.landed`, `people.softened.polite`, `people.softened.low` · offered only once the villager has actually said `villager:softened`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.softened.reassure` — accepted phrasings: "we are all difficult to live beside"; "nobody is easy to live beside"; "that is only human"
  - the message must contain one of: `difficult`, `beside`
  - scored words: `difficult`(1.5), `beside`(1.5), `all`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.people.softened.followup.reassure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.softened.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.softened.followup.reassure   [35 chars]
    en  We're all difficult to live beside.
    >>  ............................................
    pt  Todos nós somos difíceis de conviver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.softened.reassure`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2, warmth +3  _(recorded under topic `people.softened.reassure`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.softened.reassure
WHO    VILLAGER — what the player reads after pressing "We're all difficult to live beside."
       spoken on: conversations.topic.people.softened.followup, button `reassure`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.softened.reassure`: the villager accepts. Subject `people.repair`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.softened.reassure/1   [74 chars]
    en  Just so. It's the price of living close enough to hear each other's doors.
    >>  ............................................
    pt  Pois é. É o preço de morar perto o bastante pra ouvir a porta do outro.
    >>  ............................................
  dialogue.conversations.people.softened.reassure/2   [70 chars]
    en  That's a kind way to let me off, %1$s. I'll take it and behave better.
    >>  ............................................
    pt  É um jeito gentil de me livrar dessa, %1$s. Vou aceitar e me comportar melhor.
    >>  ............................................
  dialogue.conversations.people.softened.reassure/3   [69 chars]
    en  True. And they're all being hard on me somewhere, which is only fair.
    >>  ............................................
    pt  Verdade. E eles estão sendo duros comigo em algum lugar, o que é justo.
    >>  ............................................
```


### Button `leave` — "Best I stay out of it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.softened.landed`, `people.softened.polite`, `people.softened.low` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.softened.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.softened.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.softened.followup.leave   [22 chars]
    en  Best I stay out of it.
    >>  ............................................
    pt  Melhor eu ficar fora disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.respond.leave
WHO    VILLAGER — what the player reads after pressing "Best I stay out of it."
       spoken on: conversations.topic.people.softened.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.left`: the villager accepts. Subject `people.first`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.gossip.followup / leave; conversations.topic.people.grumble.followup / leave; conversations.topic.people.mixed.respond / leave; conversations.topic.people.rebuffed.followup / leave; conversations.topic.people.sour.respond / leave
```

> Written out in full under **`conversations.topic.people.gossip.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.people.sour.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `people`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.judgy` — e.g. "Half of them owe me favors and the other half owe me apologies. I keep a list."


```text
POOL   dialogue key: dialogue.conversations.topic.people.sour.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.sour.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.sour.respond   [23 chars]
    en  That's my list, anyway.
    >>  ............................................
    pt  É a minha lista, enfim.
    >>  ............................................
```


### Button `agree` — "I've noticed the same."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `people.sour_view` · offered only once the villager has actually said `people:sour`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.sour.agree` — accepted phrasings: "i have noticed that too"; "i have seen that as well"; "i have thought the same myself"
  - the message must contain one of: `noticed`
  - scored words: `noticed`(1.0), `too`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.people.sour.respond.agree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.sour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.sour.respond.agree   [22 chars]
    en  I've noticed the same.
    >>  ............................................
    pt  Notei o mesmo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.sour.agree`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `people.respond.agree`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.grumble.followup`
- …where the player's next choices will be: "That sounds exhausting." | "Have you tried talking to them?" | "You'd all be lost without somebody to complain about." | "Best I stay out of it."

```text
POOL   dialogue key: dialogue.conversations.people.respond.agree
WHO    VILLAGER — what the player reads after pressing "I've noticed the same."
       spoken on: conversations.topic.people.sour.respond, button `agree`
       leaves the player on: conversations.topic.people.grumble.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.agreed`: the villager accepts. Subject `people.affection`, polarity `mixed`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `people:mixed`, `grievance:shared` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, respectful_disagreement, humor, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.mixed.respond / agree
```

> Written out in full under **`conversations.topic.people.mixed.respond` / button `agree`** earlier in this file. Fill it in there, once.


### Button `defend` — "They're not so bad, most of them."

*stance family `respectful_disagreement` · tone `plain` · outcome `accepted` · answers the beat(s) `people.sour_view` · offered only once the villager has actually said `people:sour`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.sour.defend` — accepted phrasings: "they are not so bad"; "they are not as bad as that"; "most of them are decent enough"
  - the message must contain one of: `bad`
  - scored words: `bad`(1.0), `not`(0.2), `them`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.people.sour.respond.defend
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.sour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.sour.respond.defend   [33 chars]
    en  They're not so bad, most of them.
    >>  ............................................
    pt  Eles não são tão ruins, a maioria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +100 when the mood is `unhappy`
- Does: **hearts +1** — decision id `people.sour.defend`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `people.respond.defend`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.softened.followup`
- …where the player's next choices will be: "We're all difficult to live beside." | "Best I stay out of it."

```text
POOL   dialogue key: dialogue.conversations.people.defend.low
WHO    VILLAGER — what the player reads after pressing "They're not so bad, most of them."
       spoken on: conversations.topic.people.sour.respond, button `defend`
       leaves the player on: conversations.topic.people.softened.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.softened.low`: the villager qualifys. Subject `people.repair`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `people:sour`, `villager:softened` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: respectful_disagreement, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.defend.low/1   [90 chars]
    en  ...Perhaps. I'm in no state to be fair about them today, and you're probably right anyway.
    >>  ............................................
    pt  ...Talvez. Não estou em condição de ser justo com eles hoje, e você deve ter razão de qualquer forma.
    >>  ............................................
  dialogue.conversations.people.defend.low/2   [83 chars]
    en  You'd defend them. Of course you would. I'd defend them too, on a better afternoon.
    >>  ............................................
    pt  Você os defenderia. Claro que sim. Eu também defenderia, numa tarde melhor.
    >>  ............................................
  dialogue.conversations.people.defend.low/3   [63 chars]
    en  Mm. Ask me tomorrow, %1$s, and I might agree with you properly.
    >>  ............................................
    pt  Hm. Me pergunte amanhã, %1$s, e talvez eu concorde de verdade.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: RULED OUT when the mood is `unhappy`  _(chance -2000)_
- Fires when: weighted +100 when the personality is `peaceful`, `friendly`, `confident`, `upbeat`
- Does: **hearts +1** — decision id `people.sour.defend`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `people.respond.defend`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.softened.followup`
- …where the player's next choices will be: "We're all difficult to live beside." | "Best I stay out of it."

```text
POOL   dialogue key: dialogue.conversations.people.respond.defend.landed
WHO    VILLAGER — what the player reads after pressing "They're not so bad, most of them."
       spoken on: conversations.topic.people.sour.respond, button `defend`
       leaves the player on: conversations.topic.people.softened.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.softened.landed`: the villager accepts. Subject `people.repair`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `people:sour`, `villager:softened` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: respectful_disagreement, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.respond.defend.landed/1   [57 chars]
    en  ...You're right. I'm being unfair and you said so kindly.
    >>  ............................................
    pt  ...Você tem razão. Estou sendo injusto e você disse isso com gentileza.
    >>  ............................................
  dialogue.conversations.people.respond.defend.landed/2   [54 chars]
    en  Somebody had to say it back to me. They're not so bad.
    >>  ............................................
    pt  Alguém tinha que me responder isso. Eles não são tão ruins.
    >>  ............................................
  dialogue.conversations.people.respond.defend.landed/3   [65 chars]
    en  Fair. I'd rather be corrected than agreed with out of politeness.
    >>  ............................................
    pt  Justo. Prefiro ser corrigido a receber concordância por educação.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `0`

- Fires when: weighted +100 when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`
- Does: **hearts -1** — decision id `people.sour.defend`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `people.respond.defend`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.rebuffed.followup`
- …where the player's next choices will be: "Fair. I've not earned an opinion yet." | "I'll keep my opinion anyway." | "Best I stay out of it."

```text
POOL   dialogue key: dialogue.conversations.people.respond.defend.flat
WHO    VILLAGER — what the player reads after pressing "They're not so bad, most of them."
       spoken on: conversations.topic.people.sour.respond, button `defend`
       leaves the player on: conversations.topic.people.rebuffed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.defend_rebuffed`: the villager refuses. Subject `people.conflict`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   this is the line that establishes `people:sour`, `player:too_new` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.respond.defend.flat/1   [37 chars]
    en  You've lived here five minutes, %1$s.
    >>  ............................................
    pt  Você mora aqui há cinco minutos, %1$s.
    >>  ............................................
  dialogue.conversations.people.respond.defend.flat/2   [47 chars]
    en  Easy to defend people who've never crossed you.
    >>  ............................................
    pt  Fácil defender gente que nunca te fez nada.
    >>  ............................................
  dialogue.conversations.people.respond.defend.flat/3   [34 chars]
    en  ...Mm. You'll see, given a winter.
    >>  ............................................
    pt  ...Hm. Você vai ver, depois de um inverno.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes. They've had a hard eleven years, %1$s.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos. Eles tiveram onze anos duros, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.people.respond.defend.flat/2
    en  That's an easy thing to say and a hard thing to be on the end of.
    >>  ............................................
    pt  É fácil de dizer e difícil de estar do outro lado.
    >>  ............................................
  anxious.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. I'd rather not have heard that about them.
    >>  ............................................
    pt  ...Certo. Eu preferia não ter ouvido isso deles.
    >>  ............................................
  athletic.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes. Places take longer than that to read.
    >>  ............................................
    pt  Você mora aqui há cinco minutos. Lugares levam mais que isso pra se ler.
    >>  ............................................
  athletic.dialogue.conversations.people.respond.defend.flat/2
    en  ...Aye, well. Ask me again after a few winters and we'll see if you still think so.
    >>  ............................................
    pt  ...É, bom. Me pergunte depois de uns invernos e a gente vê se você ainda acha.
    >>  ............................................
  athletic.dialogue.conversations.people.respond.defend.flat/3
    en  Right. There's no rush to have an opinion about people.
    >>  ............................................
    pt  Certo. Não há pressa de ter opinião sobre gente.
    >>  ............................................
  confident.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes.
    >>  ............................................
    pt  Você mora aqui há cinco minutos.
    >>  ............................................
  confident.dialogue.conversations.people.respond.defend.flat/2
    en  Right. You've met them twice and you've made up your mind.
    >>  ............................................
    pt  Certo. Você os viu duas vezes e já decidiu.
    >>  ............................................
  confident.dialogue.conversations.people.respond.defend.flat/3
    en  ...I'll take my own view, thanks.
    >>  ............................................
    pt  ...Vou ficar com a minha opinião, obrigado.
    >>  ............................................
  crabby.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes.
    >>  ............................................
    pt  Você mora aqui há cinco minutos.
    >>  ............................................
  crabby.dialogue.conversations.people.respond.defend.flat/2
    en  Right. You've met them twice and you've made up your mind.
    >>  ............................................
    pt  Certo. Você os viu duas vezes e já decidiu.
    >>  ............................................
  crabby.dialogue.conversations.people.respond.defend.flat/3
    en  ...I'll take my own view, thanks.
    >>  ............................................
    pt  ...Vou ficar com a minha opinião, obrigado.
    >>  ............................................
  extroverted.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes, %1$s. I've lived here with them.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos, %1$s. Eu morei aqui com eles.
    >>  ............................................
  extroverted.dialogue.conversations.people.respond.defend.flat/2
    en  There's more to them than you've seen. I'd tell you, if you'd ask instead.
    >>  ............................................
    pt  Tem mais neles do que você viu. Eu te contaria, se você perguntasse.
    >>  ............................................
  extroverted.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. I'll not defend them at you. You'll see it yourself in time.
    >>  ............................................
    pt  ...Certo. Não vou defendê-los contra você. Você vai ver com o tempo.
    >>  ............................................
  flirty.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes, %1$s. I've lived here with them.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos, %1$s. Eu morei aqui com eles.
    >>  ............................................
  flirty.dialogue.conversations.people.respond.defend.flat/2
    en  There's more to them than you've seen. I'd tell you, if you'd ask instead.
    >>  ............................................
    pt  Tem mais neles do que você viu. Eu te contaria, se você perguntasse.
    >>  ............................................
  flirty.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. I'll not defend them at you. You'll see it yourself in time.
    >>  ............................................
    pt  ...Certo. Não vou defendê-los contra você. Você vai ver com o tempo.
    >>  ............................................
  friendly.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes, %1$s. I've lived here with them.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos, %1$s. Eu morei aqui com eles.
    >>  ............................................
  friendly.dialogue.conversations.people.respond.defend.flat/2
    en  There's more to them than you've seen. I'd tell you, if you'd ask instead.
    >>  ............................................
    pt  Tem mais neles do que você viu. Eu te contaria, se você perguntasse.
    >>  ............................................
  friendly.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. I'll not defend them at you. You'll see it yourself in time.
    >>  ............................................
    pt  ...Certo. Não vou defendê-los contra você. Você vai ver com o tempo.
    >>  ............................................
  gloomy.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes. They've had a hard eleven years, %1$s.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos. Eles tiveram onze anos duros, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.people.respond.defend.flat/2
    en  That's an easy thing to say and a hard thing to be on the end of.
    >>  ............................................
    pt  É fácil de dizer e difícil de estar do outro lado.
    >>  ............................................
  gloomy.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. I'd rather not have heard that about them.
    >>  ............................................
    pt  ...Certo. Eu preferia não ter ouvido isso deles.
    >>  ............................................
  greedy.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes.
    >>  ............................................
    pt  Você mora aqui há cinco minutos.
    >>  ............................................
  greedy.dialogue.conversations.people.respond.defend.flat/2
    en  Right. You've met them twice and you've made up your mind.
    >>  ............................................
    pt  Certo. Você os viu duas vezes e já decidiu.
    >>  ............................................
  greedy.dialogue.conversations.people.respond.defend.flat/3
    en  ...I'll take my own view, thanks.
    >>  ............................................
    pt  ...Vou ficar com a minha opinião, obrigado.
    >>  ............................................
  grumpy.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes.
    >>  ............................................
    pt  Você mora aqui há cinco minutos.
    >>  ............................................
  grumpy.dialogue.conversations.people.respond.defend.flat/2
    en  Right. You've met them twice and you've made up your mind.
    >>  ............................................
    pt  Certo. Você os viu duas vezes e já decidiu.
    >>  ............................................
  grumpy.dialogue.conversations.people.respond.defend.flat/3
    en  ...I'll take my own view, thanks.
    >>  ............................................
    pt  ...Vou ficar com a minha opinião, obrigado.
    >>  ............................................
  introverted.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos.
    >>  ............................................
  introverted.dialogue.conversations.people.respond.defend.flat/2
    en  You don't know them.
    >>  ............................................
    pt  Você não os conhece.
    >>  ............................................
  introverted.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. Leave it.
    >>  ............................................
    pt  ...Certo. Deixe.
    >>  ............................................
  lazy.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes. Places take longer than that to read.
    >>  ............................................
    pt  Você mora aqui há cinco minutos. Lugares levam mais que isso pra se ler.
    >>  ............................................
  lazy.dialogue.conversations.people.respond.defend.flat/2
    en  ...Aye, well. Ask me again after a few winters and we'll see if you still think so.
    >>  ............................................
    pt  ...É, bom. Me pergunte depois de uns invernos e a gente vê se você ainda acha.
    >>  ............................................
  lazy.dialogue.conversations.people.respond.defend.flat/3
    en  Right. There's no rush to have an opinion about people.
    >>  ............................................
    pt  Certo. Não há pressa de ter opinião sobre gente.
    >>  ............................................
  odd.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos.
    >>  ............................................
  odd.dialogue.conversations.people.respond.defend.flat/2
    en  You don't know them.
    >>  ............................................
    pt  Você não os conhece.
    >>  ............................................
  odd.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. Leave it.
    >>  ............................................
    pt  ...Certo. Deixe.
    >>  ............................................
  peaceful.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes. Places take longer than that to read.
    >>  ............................................
    pt  Você mora aqui há cinco minutos. Lugares levam mais que isso pra se ler.
    >>  ............................................
  peaceful.dialogue.conversations.people.respond.defend.flat/2
    en  ...Aye, well. Ask me again after a few winters and we'll see if you still think so.
    >>  ............................................
    pt  ...É, bom. Me pergunte depois de uns invernos e a gente vê se você ainda acha.
    >>  ............................................
  peaceful.dialogue.conversations.people.respond.defend.flat/3
    en  Right. There's no rush to have an opinion about people.
    >>  ............................................
    pt  Certo. Não há pressa de ter opinião sobre gente.
    >>  ............................................
  peppy.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes, %1$s! Give it a year and ask me again.
    >>  ............................................
    pt  Você mora aqui há cinco minutos, %1$s! Espere um ano e me pergunte de novo.
    >>  ............................................
  peppy.dialogue.conversations.people.respond.defend.flat/2
    en  Right! Confident, aren't we. Come back after a winter.
    >>  ............................................
    pt  Certo! Confiante, não? Volte depois de um inverno.
    >>  ............................................
  peppy.dialogue.conversations.people.respond.defend.flat/3
    en  ...Ha. Bold from somebody who's met them twice.
    >>  ............................................
    pt  ...Ha. Ousado, pra quem os viu duas vezes.
    >>  ............................................
  playful.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes, %1$s! Give it a year and ask me again.
    >>  ............................................
    pt  Você mora aqui há cinco minutos, %1$s! Espere um ano e me pergunte de novo.
    >>  ............................................
  playful.dialogue.conversations.people.respond.defend.flat/2
    en  Right! Confident, aren't we. Come back after a winter.
    >>  ............................................
    pt  Certo! Confiante, não? Volte depois de um inverno.
    >>  ............................................
  playful.dialogue.conversations.people.respond.defend.flat/3
    en  ...Ha. Bold from somebody who's met them twice.
    >>  ............................................
    pt  ...Ha. Ousado, pra quem os viu duas vezes.
    >>  ............................................
  relaxed.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes. Places take longer than that to read.
    >>  ............................................
    pt  Você mora aqui há cinco minutos. Lugares levam mais que isso pra se ler.
    >>  ............................................
  relaxed.dialogue.conversations.people.respond.defend.flat/2
    en  ...Aye, well. Ask me again after a few winters and we'll see if you still think so.
    >>  ............................................
    pt  ...É, bom. Me pergunte depois de uns invernos e a gente vê se você ainda acha.
    >>  ............................................
  relaxed.dialogue.conversations.people.respond.defend.flat/3
    en  Right. There's no rush to have an opinion about people.
    >>  ............................................
    pt  Certo. Não há pressa de ter opinião sobre gente.
    >>  ............................................
  sensitive.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes. They've had a hard eleven years, %1$s.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos. Eles tiveram onze anos duros, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.people.respond.defend.flat/2
    en  That's an easy thing to say and a hard thing to be on the end of.
    >>  ............................................
    pt  É fácil de dizer e difícil de estar do outro lado.
    >>  ............................................
  sensitive.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. I'd rather not have heard that about them.
    >>  ............................................
    pt  ...Certo. Eu preferia não ter ouvido isso deles.
    >>  ............................................
  shy.dialogue.conversations.people.respond.defend.flat/1
    en  ...You've lived here five minutes.
    >>  ............................................
    pt  ...Você mora aqui há cinco minutos.
    >>  ............................................
  shy.dialogue.conversations.people.respond.defend.flat/2
    en  You don't know them.
    >>  ............................................
    pt  Você não os conhece.
    >>  ............................................
  shy.dialogue.conversations.people.respond.defend.flat/3
    en  ...Right. Leave it.
    >>  ............................................
    pt  ...Certo. Deixe.
    >>  ............................................
  upbeat.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes, %1$s! Give it a year and ask me again.
    >>  ............................................
    pt  Você mora aqui há cinco minutos, %1$s! Espere um ano e me pergunte de novo.
    >>  ............................................
  upbeat.dialogue.conversations.people.respond.defend.flat/2
    en  Right! Confident, aren't we. Come back after a winter.
    >>  ............................................
    pt  Certo! Confiante, não? Volte depois de um inverno.
    >>  ............................................
  upbeat.dialogue.conversations.people.respond.defend.flat/3
    en  ...Ha. Bold from somebody who's met them twice.
    >>  ............................................
    pt  ...Ha. Ousado, pra quem os viu duas vezes.
    >>  ............................................
  witty.dialogue.conversations.people.respond.defend.flat/1
    en  You've lived here five minutes, %1$s! Give it a year and ask me again.
    >>  ............................................
    pt  Você mora aqui há cinco minutos, %1$s! Espere um ano e me pergunte de novo.
    >>  ............................................
  witty.dialogue.conversations.people.respond.defend.flat/2
    en  Right! Confident, aren't we. Come back after a winter.
    >>  ............................................
    pt  Certo! Confiante, não? Volte depois de um inverno.
    >>  ............................................
  witty.dialogue.conversations.people.respond.defend.flat/3
    en  ...Ha. Bold from somebody who's met them twice.
    >>  ............................................
    pt  ...Ha. Ousado, pra quem os viu duas vezes.
    >>  ............................................
```

</details>


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `peaceful`, `friendly`, `confident`, `upbeat`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`  _(chance -2000)_
- Does: disposition — respect +1  _(recorded under topic `people.respond.defend`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.softened.followup`
- …where the player's next choices will be: "We're all difficult to live beside." | "Best I stay out of it."

```text
POOL   dialogue key: dialogue.conversations.people.respond.defend.polite
WHO    VILLAGER — what the player reads after pressing "They're not so bad, most of them."
       spoken on: conversations.topic.people.sour.respond, button `defend`
       leaves the player on: conversations.topic.people.softened.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.softened.polite`: the villager qualifys. Subject `people.repair`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `people:sour`, `villager:softened` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: respectful_disagreement, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.respond.defend.polite/1   [54 chars]
    en  They're not, mostly. I'm just tired of them this week.
    >>  ............................................
    pt  Não são, na maioria. Só estou cansado deles esta semana.
    >>  ............................................
  dialogue.conversations.people.respond.defend.polite/2   [54 chars]
    en  True enough. Everyone's someone's difficult neighbour.
    >>  ............................................
    pt  Verdade. Todo mundo é o vizinho difícil de alguém.
    >>  ............................................
  dialogue.conversations.people.respond.defend.polite/3   [31 chars]
    en  It is. I say worse than I mean.
    >>  ............................................
    pt  É sim. Falo pior do que penso.
    >>  ............................................
```


### Button `ask_example` — "Give me an example."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `people.sour_view` · offered only once the villager has actually said `people:sour`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.sour.ask_example` — accepted phrasings: "what for instance"; "such as what"; "what sort of thing do you mean"
  - the message must contain one of: `instance`
  - scored words: `instance`(1.2), `like`(0.3), `what`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.people.sour.respond.ask_example
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.sour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.sour.respond.ask_example   [19 chars]
    en  Give me an example.
    >>  ............................................
    pt  Me dê um exemplo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `people.respond.ask_example`)_
- Does: session `turn`
- Then opens: `conversations.topic.people.gossip.followup`
- …where the player's next choices will be: "Go on — who's the worst?" | "It stays with me." | "Actually — don't tell me." | "Best I stay out of it."

```text
POOL   dialogue key: dialogue.conversations.people.respond.ask_example
WHO    VILLAGER — what the player reads after pressing "Give me an example."
       spoken on: conversations.topic.people.sour.respond, button `ask_example`
       leaves the player on: conversations.topic.people.gossip.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.example_offered`: the villager invites. Subject `people.guarded`, polarity `mixed`, guarded, outcome `engaged`.
NOTE   this is the line that establishes `people:mixed`, `gossip:offered` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: boundary_push, restraint, candor, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.mixed.respond / ask_example
```

> Written out in full under **`conversations.topic.people.mixed.respond` / button `ask_example`** earlier in this file. Fill it in there, once.


### Button `leave` — "I'll not stir it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `people.sour_view` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.sour.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.sour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.sour.respond.leave   [17 chars]
    en  I'll not stir it.
    >>  ............................................
    pt  Não vou remexer nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll not stir it."
       spoken on: conversations.topic.people.sour.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.left`: the villager accepts. Subject `people.first`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.people.gossip.followup / leave; conversations.topic.people.grumble.followup / leave; conversations.topic.people.mixed.respond / leave; conversations.topic.people.rebuffed.followup / leave; conversations.topic.people.softened.followup / leave
```

> Written out in full under **`conversations.topic.people.gossip.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.people.young.respond`

**Reached from 2 route(s):** `conversations.cat.village` / `people`; `conversations.cat.village` / `people`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.people.child` — e.g. "The miller's kid is my best friend except on race days."
- `conversations.people.teen` — e.g. "They're fine. Everybody's got opinions about my hair, though."


```text
POOL   dialogue key: dialogue.conversations.topic.people.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.people.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.people.young.respond   [19 chars]
    en  That's who's about.
    >>  ............................................
    pt  É esse o pessoal daqui.
    >>  ............................................
```


### Button `listen` — "Who do you get on with?"

*stance family `restraint` · tone `gentle` · answers the beat(s) `people.child.to.people.young`, `people.teen.to.people.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.young.listen` — accepted phrasings: "who do you get on with"; "who are your friends"; "who do you like here"
  - the message must contain one of: `friends`, `get`
  - scored words: `friends`(1.5), `get`(0.5), `on`(0.3), `with`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.people.young.respond.listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.young.respond.listen   [23 chars]
    en  Who do you get on with?
    >>  ............................................
    pt  Com quem você se dá bem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `people.young.listen`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `people.young.listen`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.young.listen
WHO    VILLAGER — what the player reads after pressing "Who do you get on with?"
       spoken on: conversations.topic.people.young.respond, button `listen`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.young.listen.terminal`: the villager accepts. Subject `people.young`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.people.young.listen/1   [53 chars]
    en  The baker's lad, mostly. And the dog. The dog counts.
    >>  ............................................
    pt  O filho do padeiro, principalmente. E o cachorro. O cachorro conta.
    >>  ............................................
  dialogue.conversations.people.young.listen/2   [48 chars]
    en  Most people. Not the one who shouts at the well.
    >>  ............................................
    pt  Quase todo mundo. Menos o que grita no poço.
    >>  ............................................
  dialogue.conversations.people.young.listen/3   [52 chars]
    en  Nobody my age, really. That's the trouble with here.
    >>  ............................................
    pt  Ninguém da minha idade, na real. É esse o problema daqui.
    >>  ............................................
```


### Button `ask` — "Is anyone unkind to you?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `people.child.to.people.young`, `people.teen.to.people.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.young.ask` — accepted phrasings: "is anyone unkind to you"; "is anyone mean to you"; "does anyone bully you"
  - the message must contain one of: `unkind`, `mean`, `bully`
  - scored words: `unkind`(1.5), `mean`(1.2), `bully`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.people.young.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.young.respond.ask   [24 chars]
    en  Is anyone unkind to you?
    >>  ............................................
    pt  Alguém é rude com você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `people.young.ask`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.young.ask
WHO    VILLAGER — what the player reads after pressing "Is anyone unkind to you?"
       spoken on: conversations.topic.people.young.respond, button `ask`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.young.ask.terminal`: the villager asks. Subject `people.young`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.people.young.ask/1   [54 chars]
    en  ...Not unkind. Just busy. It's a bit the same, though.
    >>  ............................................
    pt  ...Não rude. Só ocupados. Mas dá quase na mesma.
    >>  ............................................
  dialogue.conversations.people.young.ask/2   [33 chars]
    en  No. Everyone's fine. Mostly fine.
    >>  ............................................
    pt  Não. Todo mundo é legal. Quase todo mundo.
    >>  ............................................
  dialogue.conversations.people.young.ask/3   [35 chars]
    en  One person. But I'm not saying who.
    >>  ............................................
    pt  Uma pessoa. Mas não vou dizer quem.
    >>  ............................................
```


### Button `dismiss` — "You'll grow out of caring."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `people.child.to.people.young`, `people.teen.to.people.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `people.young.dismiss` — accepted phrasings: "you will grow out of caring"; "it will not matter when you are older"; "you will stop caring"
  - the message must contain one of: `grow`, `caring`, `matter`
  - scored words: `grow`(1.5), `caring`(1.5), `matter`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.people.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.young.respond.dismiss   [26 chars]
    en  You'll grow out of caring.
    >>  ............................................
    pt  Você vai parar de se importar quando crescer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `people.young.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `people.young.dismiss`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.young.dismiss
WHO    VILLAGER — what the player reads after pressing "You'll grow out of caring."
       spoken on: conversations.topic.people.young.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.young.dismiss.terminal`: the villager dismisss. Subject `people.young`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.people.young.dismiss/1   [34 chars]
    en  ...That's a horrible thing to say.
    >>  ............................................
    pt  ...Isso é uma coisa horrível de se dizer.
    >>  ............................................
  dialogue.conversations.people.young.dismiss/2   [11 chars]
    en  I hope not.
    >>  ............................................
    pt  Espero que não.
    >>  ............................................
  dialogue.conversations.people.young.dismiss/3   [29 chars]
    en  Fine. Forget I said anything.
    >>  ............................................
    pt  Tá. Esquece que eu falei.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.young.dismiss/1
    en  ...That's a horrible thing to say. People have said things like that about me.
    >>  ............................................
    pt  ...Isso é horrível de se dizer. Já disseram coisas assim de mim.
    >>  ............................................
  anxious.dialogue.conversations.people.young.dismiss/2
    en  Don't. Please. You don't know what it does to hear it, %1$s.
    >>  ............................................
    pt  Não. Por favor. Você não sabe o que é ouvir isso, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.people.young.dismiss/3
    en  ...I wish you hadn't said that.
    >>  ............................................
    pt  ...Eu queria que você não tivesse dito.
    >>  ............................................
  athletic.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say. I'd give it back to you and let you try again.
    >>  ............................................
    pt  Isso é horrível de se dizer. Eu devolveria e deixaria você tentar de novo.
    >>  ............................................
  athletic.dialogue.conversations.people.young.dismiss/2
    en  ...No. Let's have that unsaid and carry on.
    >>  ............................................
    pt  ...Não. Vamos considerar não dito e seguir.
    >>  ............................................
  athletic.dialogue.conversations.people.young.dismiss/3
    en  Right. I'll put that down to a bad day and leave it.
    >>  ............................................
    pt  Certo. Vou pôr na conta de um dia ruim e deixar pra lá.
    >>  ............................................
  confident.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say.
    >>  ............................................
    pt  Isso é uma coisa horrível de se dizer.
    >>  ............................................
  confident.dialogue.conversations.people.young.dismiss/2
    en  No. I'll not have that said about them.
    >>  ............................................
    pt  Não. Não vou deixar dizerem isso deles.
    >>  ............................................
  confident.dialogue.conversations.people.young.dismiss/3
    en  ...Take it back or say it somewhere else.
    >>  ............................................
    pt  ...Retire ou diga em outro lugar.
    >>  ............................................
  crabby.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say.
    >>  ............................................
    pt  Isso é uma coisa horrível de se dizer.
    >>  ............................................
  crabby.dialogue.conversations.people.young.dismiss/2
    en  No. I'll not have that said about them.
    >>  ............................................
    pt  Não. Não vou deixar dizerem isso deles.
    >>  ............................................
  crabby.dialogue.conversations.people.young.dismiss/3
    en  ...Take it back or say it somewhere else.
    >>  ............................................
    pt  ...Retire ou diga em outro lugar.
    >>  ............................................
  extroverted.dialogue.conversations.people.young.dismiss/1
    en  ...That's a horrible thing to say, %1$s. They've been kind to me.
    >>  ............................................
    pt  ...Isso é horrível de se dizer, %1$s. Eles foram bons comigo.
    >>  ............................................
  extroverted.dialogue.conversations.people.young.dismiss/2
    en  Don't. I like them, and now I have to decide what to do about liking you.
    >>  ............................................
    pt  Não. Eu gosto deles, e agora eu tenho que decidir o que fazer com gostar de você.
    >>  ............................................
  extroverted.dialogue.conversations.people.young.dismiss/3
    en  ...Right. I'll pretend I didn't hear it. Once.
    >>  ............................................
    pt  ...Certo. Vou fingir que não ouvi. Uma vez.
    >>  ............................................
  flirty.dialogue.conversations.people.young.dismiss/1
    en  ...That's a horrible thing to say, %1$s. They've been kind to me.
    >>  ............................................
    pt  ...Isso é horrível de se dizer, %1$s. Eles foram bons comigo.
    >>  ............................................
  flirty.dialogue.conversations.people.young.dismiss/2
    en  Don't. I like them, and now I have to decide what to do about liking you.
    >>  ............................................
    pt  Não. Eu gosto deles, e agora eu tenho que decidir o que fazer com gostar de você.
    >>  ............................................
  flirty.dialogue.conversations.people.young.dismiss/3
    en  ...Right. I'll pretend I didn't hear it. Once.
    >>  ............................................
    pt  ...Certo. Vou fingir que não ouvi. Uma vez.
    >>  ............................................
  friendly.dialogue.conversations.people.young.dismiss/1
    en  ...That's a horrible thing to say, %1$s. They've been kind to me.
    >>  ............................................
    pt  ...Isso é horrível de se dizer, %1$s. Eles foram bons comigo.
    >>  ............................................
  friendly.dialogue.conversations.people.young.dismiss/2
    en  Don't. I like them, and now I have to decide what to do about liking you.
    >>  ............................................
    pt  Não. Eu gosto deles, e agora eu tenho que decidir o que fazer com gostar de você.
    >>  ............................................
  friendly.dialogue.conversations.people.young.dismiss/3
    en  ...Right. I'll pretend I didn't hear it. Once.
    >>  ............................................
    pt  ...Certo. Vou fingir que não ouvi. Uma vez.
    >>  ............................................
  gloomy.dialogue.conversations.people.young.dismiss/1
    en  ...That's a horrible thing to say. People have said things like that about me.
    >>  ............................................
    pt  ...Isso é horrível de se dizer. Já disseram coisas assim de mim.
    >>  ............................................
  gloomy.dialogue.conversations.people.young.dismiss/2
    en  Don't. Please. You don't know what it does to hear it, %1$s.
    >>  ............................................
    pt  Não. Por favor. Você não sabe o que é ouvir isso, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.people.young.dismiss/3
    en  ...I wish you hadn't said that.
    >>  ............................................
    pt  ...Eu queria que você não tivesse dito.
    >>  ............................................
  greedy.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say.
    >>  ............................................
    pt  Isso é uma coisa horrível de se dizer.
    >>  ............................................
  greedy.dialogue.conversations.people.young.dismiss/2
    en  No. I'll not have that said about them.
    >>  ............................................
    pt  Não. Não vou deixar dizerem isso deles.
    >>  ............................................
  greedy.dialogue.conversations.people.young.dismiss/3
    en  ...Take it back or say it somewhere else.
    >>  ............................................
    pt  ...Retire ou diga em outro lugar.
    >>  ............................................
  grumpy.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say.
    >>  ............................................
    pt  Isso é uma coisa horrível de se dizer.
    >>  ............................................
  grumpy.dialogue.conversations.people.young.dismiss/2
    en  No. I'll not have that said about them.
    >>  ............................................
    pt  Não. Não vou deixar dizerem isso deles.
    >>  ............................................
  grumpy.dialogue.conversations.people.young.dismiss/3
    en  ...Take it back or say it somewhere else.
    >>  ............................................
    pt  ...Retire ou diga em outro lugar.
    >>  ............................................
  introverted.dialogue.conversations.people.young.dismiss/1
    en  ...That's horrible.
    >>  ............................................
    pt  ...Isso é horrível.
    >>  ............................................
  introverted.dialogue.conversations.people.young.dismiss/2
    en  Don't say that.
    >>  ............................................
    pt  Não diga isso.
    >>  ............................................
  introverted.dialogue.conversations.people.young.dismiss/3
    en  ...I'm not going to answer that.
    >>  ............................................
    pt  ...Eu não vou responder isso.
    >>  ............................................
  lazy.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say. I'd give it back to you and let you try again.
    >>  ............................................
    pt  Isso é horrível de se dizer. Eu devolveria e deixaria você tentar de novo.
    >>  ............................................
  lazy.dialogue.conversations.people.young.dismiss/2
    en  ...No. Let's have that unsaid and carry on.
    >>  ............................................
    pt  ...Não. Vamos considerar não dito e seguir.
    >>  ............................................
  lazy.dialogue.conversations.people.young.dismiss/3
    en  Right. I'll put that down to a bad day and leave it.
    >>  ............................................
    pt  Certo. Vou pôr na conta de um dia ruim e deixar pra lá.
    >>  ............................................
  odd.dialogue.conversations.people.young.dismiss/1
    en  ...That's horrible.
    >>  ............................................
    pt  ...Isso é horrível.
    >>  ............................................
  odd.dialogue.conversations.people.young.dismiss/2
    en  Don't say that.
    >>  ............................................
    pt  Não diga isso.
    >>  ............................................
  odd.dialogue.conversations.people.young.dismiss/3
    en  ...I'm not going to answer that.
    >>  ............................................
    pt  ...Eu não vou responder isso.
    >>  ............................................
  peaceful.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say. I'd give it back to you and let you try again.
    >>  ............................................
    pt  Isso é horrível de se dizer. Eu devolveria e deixaria você tentar de novo.
    >>  ............................................
  peaceful.dialogue.conversations.people.young.dismiss/2
    en  ...No. Let's have that unsaid and carry on.
    >>  ............................................
    pt  ...Não. Vamos considerar não dito e seguir.
    >>  ............................................
  peaceful.dialogue.conversations.people.young.dismiss/3
    en  Right. I'll put that down to a bad day and leave it.
    >>  ............................................
    pt  Certo. Vou pôr na conta de um dia ruim e deixar pra lá.
    >>  ............................................
  peppy.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say! And you're normally quite good fun, %1$s.
    >>  ............................................
    pt  Isso é uma coisa horrível de se dizer! E você normalmente é bem divertido, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.people.young.dismiss/2
    en  Right, no. Even I wouldn't, and I say most things.
    >>  ............................................
    pt  Certo, não. Nem eu diria, e eu digo quase tudo.
    >>  ............................................
  peppy.dialogue.conversations.people.young.dismiss/3
    en  ...Ha. No. That one isn't funny from any angle.
    >>  ............................................
    pt  ...Ha. Não. Essa não tem graça de ângulo nenhum.
    >>  ............................................
  playful.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say! And you're normally quite good fun, %1$s.
    >>  ............................................
    pt  Isso é uma coisa horrível de se dizer! E você normalmente é bem divertido, %1$s.
    >>  ............................................
  playful.dialogue.conversations.people.young.dismiss/2
    en  Right, no. Even I wouldn't, and I say most things.
    >>  ............................................
    pt  Certo, não. Nem eu diria, e eu digo quase tudo.
    >>  ............................................
  playful.dialogue.conversations.people.young.dismiss/3
    en  ...Ha. No. That one isn't funny from any angle.
    >>  ............................................
    pt  ...Ha. Não. Essa não tem graça de ângulo nenhum.
    >>  ............................................
  relaxed.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say. I'd give it back to you and let you try again.
    >>  ............................................
    pt  Isso é horrível de se dizer. Eu devolveria e deixaria você tentar de novo.
    >>  ............................................
  relaxed.dialogue.conversations.people.young.dismiss/2
    en  ...No. Let's have that unsaid and carry on.
    >>  ............................................
    pt  ...Não. Vamos considerar não dito e seguir.
    >>  ............................................
  relaxed.dialogue.conversations.people.young.dismiss/3
    en  Right. I'll put that down to a bad day and leave it.
    >>  ............................................
    pt  Certo. Vou pôr na conta de um dia ruim e deixar pra lá.
    >>  ............................................
  sensitive.dialogue.conversations.people.young.dismiss/1
    en  ...That's a horrible thing to say. People have said things like that about me.
    >>  ............................................
    pt  ...Isso é horrível de se dizer. Já disseram coisas assim de mim.
    >>  ............................................
  sensitive.dialogue.conversations.people.young.dismiss/2
    en  Don't. Please. You don't know what it does to hear it, %1$s.
    >>  ............................................
    pt  Não. Por favor. Você não sabe o que é ouvir isso, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.people.young.dismiss/3
    en  ...I wish you hadn't said that.
    >>  ............................................
    pt  ...Eu queria que você não tivesse dito.
    >>  ............................................
  shy.dialogue.conversations.people.young.dismiss/1
    en  ...That's horrible.
    >>  ............................................
    pt  ...Isso é horrível.
    >>  ............................................
  shy.dialogue.conversations.people.young.dismiss/2
    en  Don't say that.
    >>  ............................................
    pt  Não diga isso.
    >>  ............................................
  shy.dialogue.conversations.people.young.dismiss/3
    en  ...I'm not going to answer that.
    >>  ............................................
    pt  ...Eu não vou responder isso.
    >>  ............................................
  upbeat.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say! And you're normally quite good fun, %1$s.
    >>  ............................................
    pt  Isso é uma coisa horrível de se dizer! E você normalmente é bem divertido, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.people.young.dismiss/2
    en  Right, no. Even I wouldn't, and I say most things.
    >>  ............................................
    pt  Certo, não. Nem eu diria, e eu digo quase tudo.
    >>  ............................................
  upbeat.dialogue.conversations.people.young.dismiss/3
    en  ...Ha. No. That one isn't funny from any angle.
    >>  ............................................
    pt  ...Ha. Não. Essa não tem graça de ângulo nenhum.
    >>  ............................................
  witty.dialogue.conversations.people.young.dismiss/1
    en  That's a horrible thing to say! And you're normally quite good fun, %1$s.
    >>  ............................................
    pt  Isso é uma coisa horrível de se dizer! E você normalmente é bem divertido, %1$s.
    >>  ............................................
  witty.dialogue.conversations.people.young.dismiss/2
    en  Right, no. Even I wouldn't, and I say most things.
    >>  ............................................
    pt  Certo, não. Nem eu diria, e eu digo quase tudo.
    >>  ............................................
  witty.dialogue.conversations.people.young.dismiss/3
    en  ...Ha. No. That one isn't funny from any angle.
    >>  ............................................
    pt  ...Ha. Não. Essa não tem graça de ângulo nenhum.
    >>  ............................................
```

</details>


### Button `leave` — "Off you go."

*stance family `exit` · tone `plain` · answers the beat(s) `people.child.to.people.young`, `people.teen.to.people.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.people.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.people.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.people.young.respond.leave   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.young.leave
WHO    VILLAGER — what the player reads after pressing "Off you go."
       spoken on: conversations.topic.people.young.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.young.leave.terminal`: the villager accepts. Subject `people.young`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.people.young.leave/1   [9 chars]
    en  Bye then.
    >>  ............................................
    pt  Tchau então.
    >>  ............................................
  dialogue.conversations.people.young.leave/2   [14 chars]
    en  See you, %1$s.
    >>  ............................................
    pt  Até mais, %1$s.
    >>  ............................................
  dialogue.conversations.people.young.leave/3   [10 chars]
    en  Okay. Bye.
    >>  ............................................
    pt  Tá. Tchau.
    >>  ............................................
```

---

