# Topic: happy

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `happy` |
| Opened from | question `conversations.us`, button `happy` |
| Depth class (its heart budget) | `relationship` |
| Returns to | `conversations.us` |
| Ages that can reach it | adult |
| Stance families it must offer | `empathy`, `curiosity`, `encouragement`, `dismissal`, `exit` |
| Narrative arc | `us`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.us`, which is written out in **topic-us.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.us.happy
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.us
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in topic-us*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.us.happy   [22 chars]
    en  Are you happy with us?
    >>  ............................................
    pt  Você está feliz com a gente?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.happy.a_good_stretch.respond`](#conversations-scene-happy-a-good-stretch-respond)
- [`conversations.scene.happy.followup`](#conversations-scene-happy-followup)
- [`conversations.scene.happy.glad_of_you.respond`](#conversations-scene-happy-glad-of-you-respond)
- [`conversations.topic.happy.followup`](#conversations-topic-happy-followup)
- [`conversations.topic.happy.gratitude`](#conversations-topic-happy-gratitude)
- [`conversations.topic.happy.respond`](#conversations-topic-happy-respond)
- [`conversations.topic.happy.unspoken`](#conversations-topic-happy-unspoken)

---

## `conversations.scene.happy.a_good_stretch.respond`

**Reached from 1 route(s):** `conversations.us` / `happy`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.happy.a_good_stretch` — e.g. "Four good days in a row, which at my age counts as a run and I am refusing to examine it."


```text
POOL   dialogue key: dialogue.conversations.scene.happy.a_good_stretch.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.happy.a_good_stretch.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.happy.a_good_stretch.respond   [21 chars]
    en  Something going well.
    >>  ............................................
    pt  Algo indo bem.
    >>  ............................................
```


### Button `share_the_pleasure` — "Good. Take the run while it lasts."

*stance family `encouragement` · tone `playful` · outcome `appreciated` · answers the beat(s) `happy.a_good_stretch.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.happy.a_good_stretch.share_the_pleasure` — accepted phrasings: "good take the run while it lasts"; "take the run while it lasts"; "enjoy the good stretch"
  - the message must contain one of: `run`, `stretch`, `lasts`
  - scored words: `run`(1.8), `stretch`(1.8), `lasts`(1.8), `good`(0.8), `take`(0.8), `while`(0.8), `enjoy`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.happy.a_good_stretch.respond.share_the_pleasure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.happy.a_good_stretch.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.happy.a_good_stretch.respond.share_the_pleasure   [34 chars]
    en  Good. Take the run while it lasts.
    >>  ............................................
    pt  Ótimo. Aproveite a sequência.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `happy.ordinary`)_
- Does: session `turn`
- Then opens: `conversations.scene.happy.followup`
- …where the player's next choices will be: "That's the good of it."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.a_good_stretch.delighted
WHO    VILLAGER — what the player reads after pressing "Good. Take the run while it lasts."
       spoken on: conversations.scene.happy.a_good_stretch.respond, button `share_the_pleasure`
       leaves the player on: conversations.scene.happy.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.a_good_stretch.open.delighted`: the villager celebrates. Subject `happy.ordinary`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:happy` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.happy.a_good_stretch.delighted/1   [110 chars]
    en  I intend to, and I have already started planning something for the fifth day, which is exactly how a run ends.
    >>  ............................................
    pt  Pretendo, e já comecei a planejar algo para o quinto dia, que é exatamente como uma sequência acaba.
    >>  ............................................
  dialogue.conversations.scene.happy.a_good_stretch.delighted/2   [89 chars]
    en  That is the right instruction. Half the village would have told me not to get used to it.
    >>  ............................................
    pt  É a instrução certa. Metade da vila teria me dito para não me acostumar.
    >>  ............................................
  dialogue.conversations.scene.happy.a_good_stretch.delighted/3   [101 chars]
    en  Four days. I am writing it down, which sounds sad and is actually the reason I know it has been four.
    >>  ............................................
    pt  Quatro dias. Estou anotando, o que soa triste e é justamente por isso que eu sei que foram quatro.
    >>  ............................................
```


### Button `ask_what_did_it` — "What turned it around?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `happy.a_good_stretch.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.happy.a_good_stretch.ask_what_did_it` — accepted phrasings: "what turned it around"; "what turned it around"; "what made the difference this week"
  - the message must contain one of: `turned`, `difference`
  - scored words: `turned`(1.8), `difference`(1.8), `around`(0.8), `made`(0.8), `week`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.happy.a_good_stretch.respond.ask_what_did_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.happy.a_good_stretch.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.happy.a_good_stretch.respond.ask_what_did_it   [22 chars]
    en  What turned it around?
    >>  ............................................
    pt  O que virou o jogo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `happy.ordinary`)_
- Does: session `turn`
- Then opens: `conversations.scene.happy.followup`
- …where the player's next choices will be: "That's the good of it."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.a_good_stretch.answered
WHO    VILLAGER — what the player reads after pressing "What turned it around?"
       spoken on: conversations.scene.happy.a_good_stretch.respond, button `ask_what_did_it`
       leaves the player on: conversations.scene.happy.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.a_good_stretch.open.answered`: the villager explains. Subject `happy.ordinary`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:happy` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.happy.a_good_stretch.answered/1   [95 chars]
    en  Sleep, probably. It is always sleep and I always look for a more interesting explanation first.
    >>  ............................................
    pt  Sono, provavelmente. É sempre sono e eu sempre procuro uma explicação mais interessante primeiro.
    >>  ............................................
  dialogue.conversations.scene.happy.a_good_stretch.answered/2   [111 chars]
    en  One conversation went better than I had rehearsed it, and everything after that was downhill in the good sense.
    >>  ............................................
    pt  Uma conversa correu melhor do que eu tinha ensaiado, e tudo depois foi ladeira abaixo no bom sentido.
    >>  ............................................
  dialogue.conversations.scene.happy.a_good_stretch.answered/3   [109 chars]
    en  I stopped putting off a small thing. That is the whole of it and it works every time and I forget every time.
    >>  ............................................
    pt  Parei de adiar uma coisinha. É tudo, funciona toda vez, e eu esqueço toda vez.
    >>  ............................................
```


### Button `leave` — "Glad to hear it."

*stance family `exit` · tone `plain` · answers the beat(s) `happy.a_good_stretch.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.happy.a_good_stretch.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.happy.a_good_stretch.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.happy.a_good_stretch.respond.leave   [16 chars]
    en  Glad to hear it.
    >>  ............................................
    pt  Que bom ouvir isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.leaving
WHO    VILLAGER — what the player reads after pressing "Glad to hear it."
       spoken on: conversations.scene.happy.a_good_stretch.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.scene.leaving`: the villager accepts. Subject `happy.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.happy.followup / leave; conversations.scene.happy.glad_of_you.respond / leave
```

```text
  dialogue.conversations.scene.happy.leaving/1   [20 chars]
    en  Anyway. There it is.
    >>  ............................................
    pt  Enfim. É isso.
    >>  ............................................
  dialogue.conversations.scene.happy.leaving/2   [22 chars]
    en  Right. Enough of that.
    >>  ............................................
    pt  Certo. Chega disso.
    >>  ............................................
  dialogue.conversations.scene.happy.leaving/3   [29 chars]
    en  Off you go before I say more.
    >>  ............................................
    pt  Vá antes que eu fale mais.
    >>  ............................................
```

---


## `conversations.scene.happy.followup`

**Reached from 4 route(s):** `conversations.scene.happy.a_good_stretch.respond` / `share_the_pleasure`; `conversations.scene.happy.a_good_stretch.respond` / `ask_what_did_it`; `conversations.scene.happy.glad_of_you.respond` / `say_it_back`; `conversations.scene.happy.glad_of_you.respond` / `ask_what_prompted_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.happy.a_good_stretch.answered` — e.g. "Sleep, probably. It is always sleep and I always look for a more interesting explanation first."
- `conversations.scene.happy.a_good_stretch.delighted` — e.g. "I intend to, and I have already started planning something for the fifth day, which is exactly how a run ends."
- `conversations.scene.happy.glad_of_you.explained` — e.g. "Somebody in this village died last winter having never been told, by anybody, and I have been unable to stop thinking about it."
- `conversations.scene.happy.glad_of_you.moved` — e.g. "Good. Now we have both been awkward and neither of us has to do it again for at least a year."


```text
POOL   dialogue key: dialogue.conversations.scene.happy.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.happy.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.happy.followup   [19 chars]
    en  Anything else good?
    >>  ............................................
    pt  Mais alguma coisa boa?
    >>  ............................................
```


### Button `leave` — "That's the good of it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:happy.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.happy.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.happy.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.happy.followup.leave   [22 chars]
    en  That's the good of it.
    >>  ............................................
    pt  É o que tem de bom.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.leaving
WHO    VILLAGER — what the player reads after pressing "That's the good of it."
       spoken on: conversations.scene.happy.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.scene.leaving`: the villager accepts. Subject `happy.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.happy.a_good_stretch.respond / leave; conversations.scene.happy.glad_of_you.respond / leave
```

> Written out in full under **`conversations.scene.happy.a_good_stretch.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.happy.glad_of_you.respond`

**Reached from 1 route(s):** `conversations.us` / `happy`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.happy.glad_of_you` — e.g. "I am glad you turned up here. I have been meaning to say that for about two months and have been putting it off because it is awkward."


```text
POOL   dialogue key: dialogue.conversations.scene.happy.glad_of_you.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.happy.glad_of_you.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.happy.glad_of_you.respond   [27 chars]
    en  Something she wants to say.
    >>  ............................................
    pt  Algo que ela quer dizer.
    >>  ............................................
```


### Button `say_it_back` — "The same is true from here."

*stance family `self_disclosure` · tone `gentle` · outcome `appreciated` · answers the beat(s) `happy.glad_of_you.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.happy.glad_of_you.say_it_back` — accepted phrasings: "the same is true from here"; "the same is true from here"; "i feel the same about you"
  - the message must contain one of: `same`, `true`
  - scored words: `same`(1.8), `true`(1.8), `from`(0.8), `here`(0.8), `feel`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.happy.glad_of_you.respond.say_it_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.happy.glad_of_you.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.happy.glad_of_you.respond.say_it_back   [27 chars]
    en  The same is true from here.
    >>  ............................................
    pt  O mesmo vale daqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `topic.happy.mutual`, budget `deep`, replay policy `once`
- Does: disposition — warmth +4, trust +3  _(recorded under topic `happy.of_the_player`)_
- Does: session `turn`
- Then opens: `conversations.scene.happy.followup`
- …where the player's next choices will be: "That's the good of it."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.glad_of_you.moved
WHO    VILLAGER — what the player reads after pressing "The same is true from here."
       spoken on: conversations.scene.happy.glad_of_you.respond, button `say_it_back`
       leaves the player on: conversations.scene.happy.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.glad_of_you.open.moved`: the villager accepts. Subject `happy.of_the_player`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:happy` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.happy.glad_of_you.moved/1   [93 chars]
    en  Good. Now we have both been awkward and neither of us has to do it again for at least a year.
    >>  ............................................
    pt  Ótimo. Agora nós dois passamos vergonha e nenhum precisa repetir por pelo menos um ano.
    >>  ............................................
  dialogue.conversations.scene.happy.glad_of_you.moved/2   [120 chars]
    en  Thank you. I had four versions of what you might say and that was the one I was hoping for and the one I expected least.
    >>  ............................................
    pt  Obrigada. Eu tinha quatro versões do que você poderia dizer e essa era a que eu esperava e a que eu menos previa.
    >>  ............................................
  dialogue.conversations.scene.happy.glad_of_you.moved/3   [114 chars]
    en  Right. That is that said. I am going to go and do something with my hands now, immediately, and you should let me.
    >>  ............................................
    pt  Certo. Está dito. Vou fazer alguma coisa com as mãos agora, imediatamente, e você deveria me deixar.
    >>  ............................................
```


### Button `ask_what_prompted_it` — "What brought that on?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `happy.glad_of_you.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.happy.glad_of_you.ask_what_prompted_it` — accepted phrasings: "what brought that on"; "what brought that on"; "what prompted saying it now"
  - the message must contain one of: `brought`, `prompted`
  - scored words: `brought`(1.8), `prompted`(1.8), `saying`(0.8), `now`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.happy.glad_of_you.respond.ask_what_prompted_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.happy.glad_of_you.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.happy.glad_of_you.respond.ask_what_prompted_it   [21 chars]
    en  What brought that on?
    >>  ............................................
    pt  O que trouxe isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `happy.of_the_player`)_
- Does: session `turn`
- Then opens: `conversations.scene.happy.followup`
- …where the player's next choices will be: "That's the good of it."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.glad_of_you.explained
WHO    VILLAGER — what the player reads after pressing "What brought that on?"
       spoken on: conversations.scene.happy.glad_of_you.respond, button `ask_what_prompted_it`
       leaves the player on: conversations.scene.happy.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.glad_of_you.open.explained`: the villager explains. Subject `happy.of_the_player`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:happy` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.happy.glad_of_you.explained/1   [127 chars]
    en  Somebody in this village died last winter having never been told, by anybody, and I have been unable to stop thinking about it.
    >>  ............................................
    pt  Alguém desta vila morreu no inverno passado sem nunca ter ouvido isso de ninguém, e eu não consigo parar de pensar nisso.
    >>  ............................................
  dialogue.conversations.scene.happy.glad_of_you.explained/2   [114 chars]
    en  Nothing brought it on. That is rather the point — I did not want it to arrive attached to a favour or an occasion.
    >>  ............................................
    pt  Nada trouxe. É justamente esse o ponto — eu não queria que viesse grudado num favor ou numa ocasião.
    >>  ............................................
  dialogue.conversations.scene.happy.glad_of_you.explained/3   [128 chars]
    en  I nearly said it three times and stopped, and this time I did not stop, and I could not tell you what was different about today.
    >>  ............................................
    pt  Quase disse três vezes e parei, e desta vez não parei, e eu não saberia dizer o que havia de diferente hoje.
    >>  ............................................
```


### Button `leave` — "Glad to hear it."

*stance family `exit` · tone `plain` · answers the beat(s) `happy.glad_of_you.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.happy.glad_of_you.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.happy.glad_of_you.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.happy.glad_of_you.respond.leave   [16 chars]
    en  Glad to hear it.
    >>  ............................................
    pt  Que bom ouvir isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.leaving
WHO    VILLAGER — what the player reads after pressing "Glad to hear it."
       spoken on: conversations.scene.happy.glad_of_you.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.scene.leaving`: the villager accepts. Subject `happy.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.happy.a_good_stretch.respond / leave; conversations.scene.happy.followup / leave
```

> Written out in full under **`conversations.scene.happy.a_good_stretch.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.happy.followup`

**Reached from 4 route(s):** `conversations.topic.happy.respond` / `listen`; `conversations.topic.happy.respond` / `listen`; `conversations.topic.happy.respond` / `affirm`; `conversations.topic.happy.respond` / `defensive`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.happy.affirm` — e.g. "Are you? Good. Say that more often, %1$s."
- `conversations.us.happy.defensive` — e.g. "...It wasn't an accusation. It was a question."
- `conversations.us.happy.listen` — e.g. "...Honestly, then. Mostly yes. There's a thing, but mostly yes."
- `conversations.us.happy.listen.again` — e.g. "We've talked about this before, you and I. It's better than it was. Mostly."


```text
POOL   dialogue key: dialogue.conversations.topic.happy.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.happy.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.happy.followup   [15 chars]
    en  So there it is.
    >>  ............................................
    pt  Então é isso.
    >>  ............................................
```


### Button `ask_improve` — "What could we do better?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `us.happy.affirm.to.happy`, `us.happy.defensive.to.happy`, `us.happy.listen.again.to.happy`, `us.happy.listen.to.happy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.followup.ask_improve` — accepted phrasings: "what could we do better"; "what would make it better"; "is there anything we could do better"
  - the message must contain one of: `better`
  - scored words: `better`(1.2), `could`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.followup.ask_improve
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.followup.ask_improve   [24 chars]
    en  What could we do better?
    >>  ............................................
    pt  O que a gente podia melhorar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.happy.ask_improve`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +5, respect +3  _(recorded under topic `us.happy.ask_improve`)_
- Does: arc `us` — advance to stage 1
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.happy.ask_improve
WHO    VILLAGER — what the player reads after pressing "What could we do better?"
       spoken on: conversations.topic.happy.followup, button `ask_improve`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.ask_improve.to.us`: the villager accepts. Subject `us`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.ask_improve/1   [79 chars]
    en  ...You want to know. Right. Small things — and you asking is one of them fixed.
    >>  ............................................
    pt  ...Você quer saber. Certo. Coisas pequenas — e você perguntar já resolve uma.
    >>  ............................................
  dialogue.conversations.us.happy.ask_improve/2   [60 chars]
    en  Nobody asks that. Give me a day and I'll have a real answer.
    >>  ............................................
    pt  Ninguém pergunta isso. Me dá um dia e eu terei uma resposta de verdade.
    >>  ............................................
  dialogue.conversations.us.happy.ask_improve/3   [58 chars]
    en  Better. Aye — there is a better. Thank you for wanting it.
    >>  ............................................
    pt  Melhor. É — dá para melhorar. Obrigado por querer isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know, %1$s. Right. Small things — and you asking is one of them.
    >>  ............................................
    pt  ...Você quer saber, %1$s. Certo. Coisas pequenas — e você perguntar é uma delas.
    >>  ............................................
  anxious.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine, and I'd been saving these up alone.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem, e eu vinha guardando isso sozinho.
    >>  ............................................
  anxious.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. I'd been frightened it would sound like ingratitude.
    >>  ............................................
    pt  Certo. Tem uma lista. Eu tinha medo de soar como ingratidão.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know. Right. Small things. They stay small if they get asked about.
    >>  ............................................
    pt  Você quer saber. Certo. Coisas pequenas. Elas ficam pequenas se forem perguntadas.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine, and that's how fine stops being fine.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem, e é assim que o bem para de ser bem.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. Nothing on it is urgent and all of it is worth saying.
    >>  ............................................
    pt  Certo. Tem uma lista. Nada nela é urgente e tudo vale ser dito.
    >>  ............................................
  confident.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know. Right. Small things, and you asking is one of them.
    >>  ............................................
    pt  Você quer saber. Certo. Coisas pequenas, e você perguntar é uma delas.
    >>  ............................................
  confident.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine. Small things, then.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem. Coisas pequenas, então.
    >>  ............................................
  confident.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. It's short and it's mostly small.
    >>  ............................................
    pt  Certo. Tem uma lista. É curta e é quase toda pequena.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know. Right. Small things, and you asking is one of them.
    >>  ............................................
    pt  Você quer saber. Certo. Coisas pequenas, e você perguntar é uma delas.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine. Small things, then.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem. Coisas pequenas, então.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. It's short and it's mostly small.
    >>  ............................................
    pt  Certo. Tem uma lista. É curta e é quase toda pequena.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know, %1$s. Right. Small things — and you asking is one of them.
    >>  ............................................
    pt  ...Você quer saber, %1$s. Certo. Coisas pequenas — e você perguntar é uma delas.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine. Sit down; it's a short and easy list.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem. Sente-se; é uma lista curta e fácil.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list, and every item on it is smaller than you'd fear.
    >>  ............................................
    pt  Certo. Tem uma lista, e cada item é menor do que você teme.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know, %1$s. Right. Small things — and you asking is one of them.
    >>  ............................................
    pt  ...Você quer saber, %1$s. Certo. Coisas pequenas — e você perguntar é uma delas.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine. Sit down; it's a short and easy list.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem. Sente-se; é uma lista curta e fácil.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list, and every item on it is smaller than you'd fear.
    >>  ............................................
    pt  Certo. Tem uma lista, e cada item é menor do que você teme.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know, %1$s. Right. Small things — and you asking is one of them.
    >>  ............................................
    pt  ...Você quer saber, %1$s. Certo. Coisas pequenas — e você perguntar é uma delas.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine. Sit down; it's a short and easy list.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem. Sente-se; é uma lista curta e fácil.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list, and every item on it is smaller than you'd fear.
    >>  ............................................
    pt  Certo. Tem uma lista, e cada item é menor do que você teme.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know, %1$s. Right. Small things — and you asking is one of them.
    >>  ............................................
    pt  ...Você quer saber, %1$s. Certo. Coisas pequenas — e você perguntar é uma delas.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine, and I'd been saving these up alone.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem, e eu vinha guardando isso sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. I'd been frightened it would sound like ingratitude.
    >>  ............................................
    pt  Certo. Tem uma lista. Eu tinha medo de soar como ingratidão.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know. Right. Small things, and you asking is one of them.
    >>  ............................................
    pt  Você quer saber. Certo. Coisas pequenas, e você perguntar é uma delas.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine. Small things, then.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem. Coisas pequenas, então.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. It's short and it's mostly small.
    >>  ............................................
    pt  Certo. Tem uma lista. É curta e é quase toda pequena.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know. Right. Small things, and you asking is one of them.
    >>  ............................................
    pt  Você quer saber. Certo. Coisas pequenas, e você perguntar é uma delas.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine. Small things, then.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem. Coisas pequenas, então.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. It's short and it's mostly small.
    >>  ............................................
    pt  Certo. Tem uma lista. É curta e é quase toda pequena.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know. Small things. You asking is one of them.
    >>  ............................................
    pt  ...Você quer saber. Coisas pequenas. Você perguntar é uma delas.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. It's short.
    >>  ............................................
    pt  Certo. Tem uma lista. É curta.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know. Right. Small things. They stay small if they get asked about.
    >>  ............................................
    pt  Você quer saber. Certo. Coisas pequenas. Elas ficam pequenas se forem perguntadas.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine, and that's how fine stops being fine.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem, e é assim que o bem para de ser bem.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. Nothing on it is urgent and all of it is worth saying.
    >>  ............................................
    pt  Certo. Tem uma lista. Nada nela é urgente e tudo vale ser dito.
    >>  ............................................
  odd.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know. Small things. You asking is one of them.
    >>  ............................................
    pt  ...Você quer saber. Coisas pequenas. Você perguntar é uma delas.
    >>  ............................................
  odd.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem.
    >>  ............................................
  odd.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. It's short.
    >>  ............................................
    pt  Certo. Tem uma lista. É curta.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know. Right. Small things. They stay small if they get asked about.
    >>  ............................................
    pt  Você quer saber. Certo. Coisas pequenas. Elas ficam pequenas se forem perguntadas.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine, and that's how fine stops being fine.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem, e é assim que o bem para de ser bem.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. Nothing on it is urgent and all of it is worth saying.
    >>  ............................................
    pt  Certo. Tem uma lista. Nada nela é urgente e tudo vale ser dito.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know! Right. Small things — and you asking is one of them, so that's one solved.
    >>  ............................................
    pt  Você quer saber! Certo. Coisas pequenas — e você perguntar é uma delas, então uma foi resolvida.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.ask_improve/2
    en  You asked! Nobody asks when things are fine. It's the fine times that quietly go wrong.
    >>  ............................................
    pt  Você perguntou! Ninguém pergunta quando está bem. São os tempos bons que estragam calado.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.ask_improve/3
    en  Right — there's a list. It's short. It's mostly about washing-up.
    >>  ............................................
    pt  Certo — tem uma lista. É curta. É quase toda sobre a louça.
    >>  ............................................
  playful.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know! Right. Small things — and you asking is one of them, so that's one solved.
    >>  ............................................
    pt  Você quer saber! Certo. Coisas pequenas — e você perguntar é uma delas, então uma foi resolvida.
    >>  ............................................
  playful.dialogue.conversations.us.happy.ask_improve/2
    en  You asked! Nobody asks when things are fine. It's the fine times that quietly go wrong.
    >>  ............................................
    pt  Você perguntou! Ninguém pergunta quando está bem. São os tempos bons que estragam calado.
    >>  ............................................
  playful.dialogue.conversations.us.happy.ask_improve/3
    en  Right — there's a list. It's short. It's mostly about washing-up.
    >>  ............................................
    pt  Certo — tem uma lista. É curta. É quase toda sobre a louça.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know. Right. Small things. They stay small if they get asked about.
    >>  ............................................
    pt  Você quer saber. Certo. Coisas pequenas. Elas ficam pequenas se forem perguntadas.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine, and that's how fine stops being fine.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem, e é assim que o bem para de ser bem.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. Nothing on it is urgent and all of it is worth saying.
    >>  ............................................
    pt  Certo. Tem uma lista. Nada nela é urgente e tudo vale ser dito.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know, %1$s. Right. Small things — and you asking is one of them.
    >>  ............................................
    pt  ...Você quer saber, %1$s. Certo. Coisas pequenas — e você perguntar é uma delas.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine, and I'd been saving these up alone.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem, e eu vinha guardando isso sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. I'd been frightened it would sound like ingratitude.
    >>  ............................................
    pt  Certo. Tem uma lista. Eu tinha medo de soar como ingratidão.
    >>  ............................................
  shy.dialogue.conversations.us.happy.ask_improve/1
    en  ...You want to know. Small things. You asking is one of them.
    >>  ............................................
    pt  ...Você quer saber. Coisas pequenas. Você perguntar é uma delas.
    >>  ............................................
  shy.dialogue.conversations.us.happy.ask_improve/2
    en  You asked. Nobody asks when things are fine.
    >>  ............................................
    pt  Você perguntou. Ninguém pergunta quando está bem.
    >>  ............................................
  shy.dialogue.conversations.us.happy.ask_improve/3
    en  Right. There's a list. It's short.
    >>  ............................................
    pt  Certo. Tem uma lista. É curta.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know! Right. Small things — and you asking is one of them, so that's one solved.
    >>  ............................................
    pt  Você quer saber! Certo. Coisas pequenas — e você perguntar é uma delas, então uma foi resolvida.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.ask_improve/2
    en  You asked! Nobody asks when things are fine. It's the fine times that quietly go wrong.
    >>  ............................................
    pt  Você perguntou! Ninguém pergunta quando está bem. São os tempos bons que estragam calado.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.ask_improve/3
    en  Right — there's a list. It's short. It's mostly about washing-up.
    >>  ............................................
    pt  Certo — tem uma lista. É curta. É quase toda sobre a louça.
    >>  ............................................
  witty.dialogue.conversations.us.happy.ask_improve/1
    en  You want to know! Right. Small things — and you asking is one of them, so that's one solved.
    >>  ............................................
    pt  Você quer saber! Certo. Coisas pequenas — e você perguntar é uma delas, então uma foi resolvida.
    >>  ............................................
  witty.dialogue.conversations.us.happy.ask_improve/2
    en  You asked! Nobody asks when things are fine. It's the fine times that quietly go wrong.
    >>  ............................................
    pt  Você perguntou! Ninguém pergunta quando está bem. São os tempos bons que estragam calado.
    >>  ............................................
  witty.dialogue.conversations.us.happy.ask_improve/3
    en  Right — there's a list. It's short. It's mostly about washing-up.
    >>  ............................................
    pt  Certo — tem uma lista. É curta. É quase toda sobre a louça.
    >>  ............................................
```

</details>


### Button `reassure` — "We'll be alright."

*stance family `restraint` · tone `gentle` · answers the beat(s) `us.happy.affirm.to.happy`, `us.happy.defensive.to.happy`, `us.happy.listen.again.to.happy`, `us.happy.listen.to.happy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.followup.reassure` — accepted phrasings: "we will be alright"; "we will be fine"; "the two of us will be alright"
  - the message must contain one of: `alright`
  - scored words: `alright`(1.5), `fine`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.followup.reassure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.followup.reassure   [17 chars]
    en  We'll be alright.
    >>  ............................................
    pt  A gente vai ficar bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.happy.reassure`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +4  _(recorded under topic `us.happy.reassure`)_
- Does: arc `us` — advance to stage 1
- Then opens: `conversations.topic.us.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.us.happy.reassure
WHO    VILLAGER — what the player reads after pressing "We'll be alright."
       spoken on: conversations.topic.happy.followup, button `reassure`
       leaves the player on: conversations.topic.us.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.reassure.to.us`: the villager accepts. Subject `us`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.reassure/1   [39 chars]
    en  We will. I believe you when you say it.
    >>  ............................................
    pt  Vamos ficar. Acredito em você quando diz isso.
    >>  ............................................
  dialogue.conversations.us.happy.reassure/2   [45 chars]
    en  Aye. We've been through worse than this week.
    >>  ............................................
    pt  É. Já passamos por coisa pior que esta semana.
    >>  ............................................
  dialogue.conversations.us.happy.reassure/3   [48 chars]
    en  That helps more than solving it would, honestly.
    >>  ............................................
    pt  Isso ajuda mais do que resolver, sinceramente.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it, %1$s, and I've not been able to believe much lately.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz, %1$s, e ultimamente eu não consigo acreditar em muito.
    >>  ............................................
  anxious.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. I'll hold on to that on the evenings when I can't.
    >>  ............................................
    pt  Certo. Então a gente vai. Vou me segurar nisso nas noites em que eu não conseguir.
    >>  ............................................
  anxious.dialogue.conversations.us.happy.reassure/3
    en  I believe you. Give me a moment — that's a heavier sentence than it sounds.
    >>  ............................................
    pt  Eu acredito em você. Me dê um momento — é uma frase mais pesada do que parece.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it, and I've had years to learn whether to.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz, e eu tive anos pra aprender se devia.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. There's no rush on any of it.
    >>  ............................................
    pt  Certo. Então a gente vai. Não há pressa em nada disso.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.reassure/3
    en  I believe you. That's taken a while to be able to say, and now it's easy.
    >>  ............................................
    pt  Eu acredito em você. Levou um tempo pra eu conseguir dizer, e agora é fácil.
    >>  ............................................
  confident.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz.
    >>  ............................................
  confident.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. That's settled as far as I'm concerned.
    >>  ............................................
    pt  Certo. Então a gente vai. Pra mim está resolvido.
    >>  ............................................
  confident.dialogue.conversations.us.happy.reassure/3
    en  I believe you. That's not a small thing for me to say.
    >>  ............................................
    pt  Eu acredito em você. Não é pouca coisa eu dizer isso.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. That's settled as far as I'm concerned.
    >>  ............................................
    pt  Certo. Então a gente vai. Pra mim está resolvido.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.reassure/3
    en  I believe you. That's not a small thing for me to say.
    >>  ............................................
    pt  Eu acredito em você. Não é pouca coisa eu dizer isso.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.reassure/1
    en  We will, %1$s. I believe you when you say it.
    >>  ............................................
    pt  A gente vai, %1$s. Eu acredito em você quando você diz.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. I'd not have believed that from anybody else, mind.
    >>  ............................................
    pt  Certo. Então a gente vai. Eu não acreditaria nisso de mais ninguém, veja bem.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.reassure/3
    en  I believe you. Come here. That's the end of the conversation and the best part of it.
    >>  ............................................
    pt  Eu acredito em você. Venha cá. É o fim da conversa e a melhor parte.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.reassure/1
    en  We will, %1$s. I believe you when you say it.
    >>  ............................................
    pt  A gente vai, %1$s. Eu acredito em você quando você diz.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. I'd not have believed that from anybody else, mind.
    >>  ............................................
    pt  Certo. Então a gente vai. Eu não acreditaria nisso de mais ninguém, veja bem.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.reassure/3
    en  I believe you. Come here. That's the end of the conversation and the best part of it.
    >>  ............................................
    pt  Eu acredito em você. Venha cá. É o fim da conversa e a melhor parte.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.reassure/1
    en  We will, %1$s. I believe you when you say it.
    >>  ............................................
    pt  A gente vai, %1$s. Eu acredito em você quando você diz.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. I'd not have believed that from anybody else, mind.
    >>  ............................................
    pt  Certo. Então a gente vai. Eu não acreditaria nisso de mais ninguém, veja bem.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.reassure/3
    en  I believe you. Come here. That's the end of the conversation and the best part of it.
    >>  ............................................
    pt  Eu acredito em você. Venha cá. É o fim da conversa e a melhor parte.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it, %1$s, and I've not been able to believe much lately.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz, %1$s, e ultimamente eu não consigo acreditar em muito.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. I'll hold on to that on the evenings when I can't.
    >>  ............................................
    pt  Certo. Então a gente vai. Vou me segurar nisso nas noites em que eu não conseguir.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.reassure/3
    en  I believe you. Give me a moment — that's a heavier sentence than it sounds.
    >>  ............................................
    pt  Eu acredito em você. Me dê um momento — é uma frase mais pesada do que parece.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. That's settled as far as I'm concerned.
    >>  ............................................
    pt  Certo. Então a gente vai. Pra mim está resolvido.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.reassure/3
    en  I believe you. That's not a small thing for me to say.
    >>  ............................................
    pt  Eu acredito em você. Não é pouca coisa eu dizer isso.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. That's settled as far as I'm concerned.
    >>  ............................................
    pt  Certo. Então a gente vai. Pra mim está resolvido.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.reassure/3
    en  I believe you. That's not a small thing for me to say.
    >>  ............................................
    pt  Eu acredito em você. Não é pouca coisa eu dizer isso.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will.
    >>  ............................................
    pt  Certo. Então a gente vai.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.reassure/3
    en  I believe you.
    >>  ............................................
    pt  Eu acredito em você.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it, and I've had years to learn whether to.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz, e eu tive anos pra aprender se devia.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. There's no rush on any of it.
    >>  ............................................
    pt  Certo. Então a gente vai. Não há pressa em nada disso.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.reassure/3
    en  I believe you. That's taken a while to be able to say, and now it's easy.
    >>  ............................................
    pt  Eu acredito em você. Levou um tempo pra eu conseguir dizer, e agora é fácil.
    >>  ............................................
  odd.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz.
    >>  ............................................
  odd.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will.
    >>  ............................................
    pt  Certo. Então a gente vai.
    >>  ............................................
  odd.dialogue.conversations.us.happy.reassure/3
    en  I believe you.
    >>  ............................................
    pt  Eu acredito em você.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it, and I've had years to learn whether to.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz, e eu tive anos pra aprender se devia.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. There's no rush on any of it.
    >>  ............................................
    pt  Certo. Então a gente vai. Não há pressa em nada disso.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.reassure/3
    en  I believe you. That's taken a while to be able to say, and now it's easy.
    >>  ............................................
    pt  Eu acredito em você. Levou um tempo pra eu conseguir dizer, e agora é fácil.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.reassure/1
    en  We will! I believe you when you say it, which is not something I say lightly.
    >>  ............................................
    pt  A gente vai! Eu acredito em você quando você diz, e eu não digo isso à toa.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.reassure/2
    en  Right — then we will. Settled. I'm going to stop worrying about it immediately.
    >>  ............................................
    pt  Certo — então a gente vai. Resolvido. Vou parar de me preocupar imediatamente.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.reassure/3
    en  I believe you! That's a whole speech from me, that is.
    >>  ............................................
    pt  Eu acredito em você! Isso é um discurso inteiro, vindo de mim.
    >>  ............................................
  playful.dialogue.conversations.us.happy.reassure/1
    en  We will! I believe you when you say it, which is not something I say lightly.
    >>  ............................................
    pt  A gente vai! Eu acredito em você quando você diz, e eu não digo isso à toa.
    >>  ............................................
  playful.dialogue.conversations.us.happy.reassure/2
    en  Right — then we will. Settled. I'm going to stop worrying about it immediately.
    >>  ............................................
    pt  Certo — então a gente vai. Resolvido. Vou parar de me preocupar imediatamente.
    >>  ............................................
  playful.dialogue.conversations.us.happy.reassure/3
    en  I believe you! That's a whole speech from me, that is.
    >>  ............................................
    pt  Eu acredito em você! Isso é um discurso inteiro, vindo de mim.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it, and I've had years to learn whether to.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz, e eu tive anos pra aprender se devia.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. There's no rush on any of it.
    >>  ............................................
    pt  Certo. Então a gente vai. Não há pressa em nada disso.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.reassure/3
    en  I believe you. That's taken a while to be able to say, and now it's easy.
    >>  ............................................
    pt  Eu acredito em você. Levou um tempo pra eu conseguir dizer, e agora é fácil.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it, %1$s, and I've not been able to believe much lately.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz, %1$s, e ultimamente eu não consigo acreditar em muito.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will. I'll hold on to that on the evenings when I can't.
    >>  ............................................
    pt  Certo. Então a gente vai. Vou me segurar nisso nas noites em que eu não conseguir.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.reassure/3
    en  I believe you. Give me a moment — that's a heavier sentence than it sounds.
    >>  ............................................
    pt  Eu acredito em você. Me dê um momento — é uma frase mais pesada do que parece.
    >>  ............................................
  shy.dialogue.conversations.us.happy.reassure/1
    en  We will. I believe you when you say it.
    >>  ............................................
    pt  A gente vai. Eu acredito em você quando você diz.
    >>  ............................................
  shy.dialogue.conversations.us.happy.reassure/2
    en  Right. Then we will.
    >>  ............................................
    pt  Certo. Então a gente vai.
    >>  ............................................
  shy.dialogue.conversations.us.happy.reassure/3
    en  I believe you.
    >>  ............................................
    pt  Eu acredito em você.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.reassure/1
    en  We will! I believe you when you say it, which is not something I say lightly.
    >>  ............................................
    pt  A gente vai! Eu acredito em você quando você diz, e eu não digo isso à toa.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.reassure/2
    en  Right — then we will. Settled. I'm going to stop worrying about it immediately.
    >>  ............................................
    pt  Certo — então a gente vai. Resolvido. Vou parar de me preocupar imediatamente.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.reassure/3
    en  I believe you! That's a whole speech from me, that is.
    >>  ............................................
    pt  Eu acredito em você! Isso é um discurso inteiro, vindo de mim.
    >>  ............................................
  witty.dialogue.conversations.us.happy.reassure/1
    en  We will! I believe you when you say it, which is not something I say lightly.
    >>  ............................................
    pt  A gente vai! Eu acredito em você quando você diz, e eu não digo isso à toa.
    >>  ............................................
  witty.dialogue.conversations.us.happy.reassure/2
    en  Right — then we will. Settled. I'm going to stop worrying about it immediately.
    >>  ............................................
    pt  Certo — então a gente vai. Resolvido. Vou parar de me preocupar imediatamente.
    >>  ............................................
  witty.dialogue.conversations.us.happy.reassure/3
    en  I believe you! That's a whole speech from me, that is.
    >>  ............................................
    pt  Eu acredito em você! Isso é um discurso inteiro, vindo de mim.
    >>  ............................................
```

</details>


### Button `dismiss` — "You worry too much."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `us.happy.affirm.to.happy`, `us.happy.defensive.to.happy`, `us.happy.listen.again.to.happy`, `us.happy.listen.to.happy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.followup.dismiss` — accepted phrasings: "you worry too much"; "you fret over nothing"; "you are worrying about nothing"
  - scored words: `too`(0.8), `worry`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.followup.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.followup.dismiss   [19 chars]
    en  You worry too much.
    >>  ............................................
    pt  Você se preocupa demais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `us.happy.dismiss`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -5, tension +5  _(recorded under topic `us.happy.dismiss`)_
- Does: session `turn`
- Then opens: `conversations.topic.us.hurt.close`
- …where the player's next choices will be: "That came out harder than I meant it." | "You matter to me. That part is true." | "I'll give you the evening."

```text
POOL   dialogue key: dialogue.conversations.us.happy.dismiss
WHO    VILLAGER — what the player reads after pressing "You worry too much."
       spoken on: conversations.topic.happy.followup, button `dismiss`
       leaves the player on: conversations.topic.us.hurt.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.brushed_off`: the villager hurts. Subject `us.relationship`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `relationship:strained`, `player:brushed_off_partner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.happy.dismiss/1   [40 chars]
    en  ...I asked you a serious question, %1$s.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s.
    >>  ............................................
  dialogue.conversations.us.happy.dismiss/2   [38 chars]
    en  Worry too much. Right. That's me told.
    >>  ............................................
    pt  Me preocupo demais. Certo. Fui avisado.
    >>  ............................................
  dialogue.conversations.us.happy.dismiss/3   [51 chars]
    en  Then I'll worry quietly and not bother you with it.
    >>  ............................................
    pt  Então vou me preocupar em silêncio e não te incomodar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s. It took me a while to get it out.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s. Levei um tempo pra conseguir dizer.
    >>  ............................................
  anxious.dialogue.conversations.us.happy.dismiss/2
    en  Right. Yes. I should have expected that and I hadn't.
    >>  ............................................
    pt  Certo. Sim. Eu devia ter esperado isso e não esperei.
    >>  ............................................
  anxious.dialogue.conversations.us.happy.dismiss/3
    en  ...Sorry. I'll not put you on the spot again.
    >>  ............................................
    pt  ...Desculpe. Não te ponho na parede de novo.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.dismiss/1
    en  I asked you a serious question. It'll keep till you're ready to answer it.
    >>  ............................................
    pt  Eu te fiz uma pergunta séria. Ela espera até você estar pronto pra responder.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.dismiss/2
    en  ...Right. No hurry on it. I've waited longer for smaller things.
    >>  ............................................
    pt  ...Certo. Sem pressa. Já esperei mais por coisas menores.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.dismiss/3
    en  Fair enough. I'll ask again another year.
    >>  ............................................
    pt  Tudo bem. Pergunto de novo em outro ano.
    >>  ............................................
  confident.dialogue.conversations.us.happy.dismiss/1
    en  I asked you a serious question.
    >>  ............................................
    pt  Eu te fiz uma pergunta séria.
    >>  ............................................
  confident.dialogue.conversations.us.happy.dismiss/2
    en  Right. Then I'll not ask you serious ones.
    >>  ............................................
    pt  Certo. Então não te faço perguntas sérias.
    >>  ............................................
  confident.dialogue.conversations.us.happy.dismiss/3
    en  ...That's the answer, is it. Noted.
    >>  ............................................
    pt  ...É essa a resposta, é? Anotado.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.dismiss/1
    en  I asked you a serious question.
    >>  ............................................
    pt  Eu te fiz uma pergunta séria.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.dismiss/2
    en  Right. Then I'll not ask you serious ones.
    >>  ............................................
    pt  Certo. Então não te faço perguntas sérias.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.dismiss/3
    en  ...That's the answer, is it. Noted.
    >>  ............................................
    pt  ...É essa a resposta, é? Anotado.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s. I'd been working up to it.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s. Eu vinha me preparando pra ela.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.dismiss/2
    en  Right. I'll take that as a no and not press it.
    >>  ............................................
    pt  Certo. Vou tomar como um não e não insistir.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.dismiss/3
    en  ...It mattered to me that I asked. That part still counts.
    >>  ............................................
    pt  ...Pra mim importava ter perguntado. Essa parte ainda conta.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s. I'd been working up to it.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s. Eu vinha me preparando pra ela.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.dismiss/2
    en  Right. I'll take that as a no and not press it.
    >>  ............................................
    pt  Certo. Vou tomar como um não e não insistir.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.dismiss/3
    en  ...It mattered to me that I asked. That part still counts.
    >>  ............................................
    pt  ...Pra mim importava ter perguntado. Essa parte ainda conta.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s. I'd been working up to it.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s. Eu vinha me preparando pra ela.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.dismiss/2
    en  Right. I'll take that as a no and not press it.
    >>  ............................................
    pt  Certo. Vou tomar como um não e não insistir.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.dismiss/3
    en  ...It mattered to me that I asked. That part still counts.
    >>  ............................................
    pt  ...Pra mim importava ter perguntado. Essa parte ainda conta.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s. It took me a while to get it out.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s. Levei um tempo pra conseguir dizer.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.dismiss/2
    en  Right. Yes. I should have expected that and I hadn't.
    >>  ............................................
    pt  Certo. Sim. Eu devia ter esperado isso e não esperei.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.dismiss/3
    en  ...Sorry. I'll not put you on the spot again.
    >>  ............................................
    pt  ...Desculpe. Não te ponho na parede de novo.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.dismiss/1
    en  I asked you a serious question.
    >>  ............................................
    pt  Eu te fiz uma pergunta séria.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.dismiss/2
    en  Right. Then I'll not ask you serious ones.
    >>  ............................................
    pt  Certo. Então não te faço perguntas sérias.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.dismiss/3
    en  ...That's the answer, is it. Noted.
    >>  ............................................
    pt  ...É essa a resposta, é? Anotado.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.dismiss/1
    en  I asked you a serious question.
    >>  ............................................
    pt  Eu te fiz uma pergunta séria.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.dismiss/2
    en  Right. Then I'll not ask you serious ones.
    >>  ............................................
    pt  Certo. Então não te faço perguntas sérias.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.dismiss/3
    en  ...That's the answer, is it. Noted.
    >>  ............................................
    pt  ...É essa a resposta, é? Anotado.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.dismiss/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.dismiss/3
    en  ...Never mind.
    >>  ............................................
    pt  ...Deixa pra lá.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.dismiss/1
    en  I asked you a serious question. It'll keep till you're ready to answer it.
    >>  ............................................
    pt  Eu te fiz uma pergunta séria. Ela espera até você estar pronto pra responder.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.dismiss/2
    en  ...Right. No hurry on it. I've waited longer for smaller things.
    >>  ............................................
    pt  ...Certo. Sem pressa. Já esperei mais por coisas menores.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.dismiss/3
    en  Fair enough. I'll ask again another year.
    >>  ............................................
    pt  Tudo bem. Pergunto de novo em outro ano.
    >>  ............................................
  odd.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria.
    >>  ............................................
  odd.dialogue.conversations.us.happy.dismiss/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  odd.dialogue.conversations.us.happy.dismiss/3
    en  ...Never mind.
    >>  ............................................
    pt  ...Deixa pra lá.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.dismiss/1
    en  I asked you a serious question. It'll keep till you're ready to answer it.
    >>  ............................................
    pt  Eu te fiz uma pergunta séria. Ela espera até você estar pronto pra responder.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.dismiss/2
    en  ...Right. No hurry on it. I've waited longer for smaller things.
    >>  ............................................
    pt  ...Certo. Sem pressa. Já esperei mais por coisas menores.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.dismiss/3
    en  Fair enough. I'll ask again another year.
    >>  ............................................
    pt  Tudo bem. Pergunto de novo em outro ano.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s! One a year, and you've spent it.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s! Uma por ano, e você gastou.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.dismiss/2
    en  Right, well. Back to the weather, then.
    >>  ............................................
    pt  Certo, bom. De volta ao tempo, então.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.dismiss/3
    en  ...Ha. Fine. I'll be serious at somebody else.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ser sério com outra pessoa.
    >>  ............................................
  playful.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s! One a year, and you've spent it.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s! Uma por ano, e você gastou.
    >>  ............................................
  playful.dialogue.conversations.us.happy.dismiss/2
    en  Right, well. Back to the weather, then.
    >>  ............................................
    pt  Certo, bom. De volta ao tempo, então.
    >>  ............................................
  playful.dialogue.conversations.us.happy.dismiss/3
    en  ...Ha. Fine. I'll be serious at somebody else.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ser sério com outra pessoa.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.dismiss/1
    en  I asked you a serious question. It'll keep till you're ready to answer it.
    >>  ............................................
    pt  Eu te fiz uma pergunta séria. Ela espera até você estar pronto pra responder.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.dismiss/2
    en  ...Right. No hurry on it. I've waited longer for smaller things.
    >>  ............................................
    pt  ...Certo. Sem pressa. Já esperei mais por coisas menores.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.dismiss/3
    en  Fair enough. I'll ask again another year.
    >>  ............................................
    pt  Tudo bem. Pergunto de novo em outro ano.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s. It took me a while to get it out.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s. Levei um tempo pra conseguir dizer.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.dismiss/2
    en  Right. Yes. I should have expected that and I hadn't.
    >>  ............................................
    pt  Certo. Sim. Eu devia ter esperado isso e não esperei.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.dismiss/3
    en  ...Sorry. I'll not put you on the spot again.
    >>  ............................................
    pt  ...Desculpe. Não te ponho na parede de novo.
    >>  ............................................
  shy.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria.
    >>  ............................................
  shy.dialogue.conversations.us.happy.dismiss/2
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  shy.dialogue.conversations.us.happy.dismiss/3
    en  ...Never mind.
    >>  ............................................
    pt  ...Deixa pra lá.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s! One a year, and you've spent it.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s! Uma por ano, e você gastou.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.dismiss/2
    en  Right, well. Back to the weather, then.
    >>  ............................................
    pt  Certo, bom. De volta ao tempo, então.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.dismiss/3
    en  ...Ha. Fine. I'll be serious at somebody else.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ser sério com outra pessoa.
    >>  ............................................
  witty.dialogue.conversations.us.happy.dismiss/1
    en  ...I asked you a serious question, %1$s! One a year, and you've spent it.
    >>  ............................................
    pt  ...Eu te fiz uma pergunta séria, %1$s! Uma por ano, e você gastou.
    >>  ............................................
  witty.dialogue.conversations.us.happy.dismiss/2
    en  Right, well. Back to the weather, then.
    >>  ............................................
    pt  Certo, bom. De volta ao tempo, então.
    >>  ............................................
  witty.dialogue.conversations.us.happy.dismiss/3
    en  ...Ha. Fine. I'll be serious at somebody else.
    >>  ............................................
    pt  ...Ha. Tudo bem. Vou ser sério com outra pessoa.
    >>  ............................................
```

</details>


### Button `ask_unspoken` — "Is there something you haven't said?"

*stance family `empathy` · tone `gentle` · outcome `engaged` · answers the beat(s) `us.happy.affirm.to.happy`, `us.happy.defensive.to.happy`, `us.happy.listen.again.to.happy`, `us.happy.listen.to.happy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.unspoken` — accepted phrasings: "is there something you have not said"; "is there anything left unsaid"; "is there something you are holding back"
  - the message must contain one of: `unsaid`
  - scored words: `said`(0.5), `unsaid`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.followup.ask_unspoken
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.followup.ask_unspoken   [36 chars]
    en  Is there something you haven't said?
    >>  ............................................
    pt  Tem algo que você não disse?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.happy.unspoken`
- …where the player's next choices will be: "Say it now. I'd rather know." | "You don't have to say it today." | "I'll leave it with you."

```text
POOL   dialogue key: dialogue.conversations.happy.unspoken
WHO    VILLAGER — what the player reads after pressing "Is there something you haven't said?"
       spoken on: conversations.topic.happy.followup, button `ask_unspoken`
       leaves the player on: conversations.topic.happy.unspoken
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.unspoken`: the villager disclose_problems. Subject `happy.unspoken`, polarity `negative`, guarded, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, candor, restraint, practical_help, exit
```

```text
  dialogue.conversations.happy.unspoken/1   [81 chars]
    en  ...There is. I've been happy and lonely in the same house and I can't explain it.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa e não consigo explicar.
    >>  ............................................
  dialogue.conversations.happy.unspoken/2   [82 chars]
    en  One thing. You're away more than you were, and I've been saying it doesn't matter.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes, e venho dizendo que não faz diferença.
    >>  ............................................
  dialogue.conversations.happy.unspoken/3   [69 chars]
    en  Yes. And I've decided four times to say it and gone quiet four times.
    >>  ............................................
    pt  Sim. E decidi quatro vezes dizer e me calei quatro vezes.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house and I'm ashamed of both halves.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa e me envergonho das duas metades.
    >>  ............................................
  anxious.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've been saying it doesn't matter and it does.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Venho dizendo que não importa e importa.
    >>  ............................................
  anxious.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it. This is the fifth and I'm shaking a little.
    >>  ............................................
    pt  Sim. Quatro vezes decidi dizer. Esta é a quinta e estou tremendo um pouco.
    >>  ............................................
  athletic.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house, and I know how that ends.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa, e eu sei como isso termina.
    >>  ............................................
  athletic.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've watched that go badly for other people.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Já vi isso acabar mal com outras pessoas.
    >>  ............................................
  athletic.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it. At my age four times is a long delay.
    >>  ............................................
    pt  Sim. Quatro vezes decidi dizer. Na minha idade, quatro vezes é uma longa demora.
    >>  ............................................
  confident.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house and I can't explain it.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa e não consigo explicar.
    >>  ............................................
  confident.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were, and I've been saying it doesn't matter.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes, e venho dizendo que não faz diferença.
    >>  ............................................
  confident.dialogue.conversations.happy.unspoken/3
    en  Yes. And I've decided four times to say it and gone quiet four times.
    >>  ............................................
    pt  Sim. E decidi quatro vezes dizer e me calei quatro vezes.
    >>  ............................................
  crabby.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house and I can't explain it.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa e não consigo explicar.
    >>  ............................................
  crabby.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were, and I've been saying it doesn't matter.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes, e venho dizendo que não faz diferença.
    >>  ............................................
  crabby.dialogue.conversations.happy.unspoken/3
    en  Yes. And I've decided four times to say it and gone quiet four times.
    >>  ............................................
    pt  Sim. E decidi quatro vezes dizer e me calei quatro vezes.
    >>  ............................................
  extroverted.dialogue.conversations.happy.unspoken/1
    en  ...There is, %1$s. I've been happy and lonely in the same house and I can't explain it.
    >>  ............................................
    pt  ...Tem, %1$s. Fiquei feliz e sozinho na mesma casa e não consigo explicar.
    >>  ............................................
  extroverted.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were, and I keep telling myself it doesn't matter.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes, e vivo dizendo a mim que não faz diferença.
    >>  ............................................
  extroverted.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it to you, and four times I've lost my nerve.
    >>  ............................................
    pt  Sim. Quatro vezes decidi te dizer, e quatro vezes perdi a coragem.
    >>  ............................................
  flirty.dialogue.conversations.happy.unspoken/1
    en  ...There is, %1$s. I've been happy and lonely in the same house and I can't explain it.
    >>  ............................................
    pt  ...Tem, %1$s. Fiquei feliz e sozinho na mesma casa e não consigo explicar.
    >>  ............................................
  flirty.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were, and I keep telling myself it doesn't matter.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes, e vivo dizendo a mim que não faz diferença.
    >>  ............................................
  flirty.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it to you, and four times I've lost my nerve.
    >>  ............................................
    pt  Sim. Quatro vezes decidi te dizer, e quatro vezes perdi a coragem.
    >>  ............................................
  friendly.dialogue.conversations.happy.unspoken/1
    en  ...There is, %1$s. I've been happy and lonely in the same house and I can't explain it.
    >>  ............................................
    pt  ...Tem, %1$s. Fiquei feliz e sozinho na mesma casa e não consigo explicar.
    >>  ............................................
  friendly.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were, and I keep telling myself it doesn't matter.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes, e vivo dizendo a mim que não faz diferença.
    >>  ............................................
  friendly.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it to you, and four times I've lost my nerve.
    >>  ............................................
    pt  Sim. Quatro vezes decidi te dizer, e quatro vezes perdi a coragem.
    >>  ............................................
  gloomy.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house and I'm ashamed of both halves.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa e me envergonho das duas metades.
    >>  ............................................
  gloomy.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've been saying it doesn't matter and it does.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Venho dizendo que não importa e importa.
    >>  ............................................
  gloomy.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it. This is the fifth and I'm shaking a little.
    >>  ............................................
    pt  Sim. Quatro vezes decidi dizer. Esta é a quinta e estou tremendo um pouco.
    >>  ............................................
  greedy.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house and I can't explain it.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa e não consigo explicar.
    >>  ............................................
  greedy.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were, and I've been saying it doesn't matter.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes, e venho dizendo que não faz diferença.
    >>  ............................................
  greedy.dialogue.conversations.happy.unspoken/3
    en  Yes. And I've decided four times to say it and gone quiet four times.
    >>  ............................................
    pt  Sim. E decidi quatro vezes dizer e me calei quatro vezes.
    >>  ............................................
  grumpy.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house and I can't explain it.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa e não consigo explicar.
    >>  ............................................
  grumpy.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were, and I've been saying it doesn't matter.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes, e venho dizendo que não faz diferença.
    >>  ............................................
  grumpy.dialogue.conversations.happy.unspoken/3
    en  Yes. And I've decided four times to say it and gone quiet four times.
    >>  ............................................
    pt  Sim. E decidi quatro vezes dizer e me calei quatro vezes.
    >>  ............................................
  introverted.dialogue.conversations.happy.unspoken/1
    en  ...There is.
    >>  ............................................
    pt  ...Tem.
    >>  ............................................
  introverted.dialogue.conversations.happy.unspoken/2
    en  You're away more than you were.
    >>  ............................................
    pt  Você viaja mais do que antes.
    >>  ............................................
  introverted.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've meant to say it.
    >>  ............................................
    pt  Sim. Quatro vezes eu quis dizer.
    >>  ............................................
  lazy.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house, and I know how that ends.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa, e eu sei como isso termina.
    >>  ............................................
  lazy.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've watched that go badly for other people.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Já vi isso acabar mal com outras pessoas.
    >>  ............................................
  lazy.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it. At my age four times is a long delay.
    >>  ............................................
    pt  Sim. Quatro vezes decidi dizer. Na minha idade, quatro vezes é uma longa demora.
    >>  ............................................
  odd.dialogue.conversations.happy.unspoken/1
    en  ...There is.
    >>  ............................................
    pt  ...Tem.
    >>  ............................................
  odd.dialogue.conversations.happy.unspoken/2
    en  You're away more than you were.
    >>  ............................................
    pt  Você viaja mais do que antes.
    >>  ............................................
  odd.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've meant to say it.
    >>  ............................................
    pt  Sim. Quatro vezes eu quis dizer.
    >>  ............................................
  peaceful.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house, and I know how that ends.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa, e eu sei como isso termina.
    >>  ............................................
  peaceful.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've watched that go badly for other people.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Já vi isso acabar mal com outras pessoas.
    >>  ............................................
  peaceful.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it. At my age four times is a long delay.
    >>  ............................................
    pt  Sim. Quatro vezes decidi dizer. Na minha idade, quatro vezes é uma longa demora.
    >>  ............................................
  peppy.dialogue.conversations.happy.unspoken/1
    en  ...There is, and I've been very cheerful about it, which is how you know it's bad.
    >>  ............................................
    pt  ...Tem, e eu venho sendo muito alegre sobre isso, que é como se sabe que é ruim.
    >>  ............................................
  peppy.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've been laughing that off since spring.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Venho rindo disso desde a primavera.
    >>  ............................................
  peppy.dialogue.conversations.happy.unspoken/3
    en  Yes. I've decided four times to say it and made a joke instead four times.
    >>  ............................................
    pt  Sim. Decidi quatro vezes dizer e fiz piada em vez disso quatro vezes.
    >>  ............................................
  playful.dialogue.conversations.happy.unspoken/1
    en  ...There is, and I've been very cheerful about it, which is how you know it's bad.
    >>  ............................................
    pt  ...Tem, e eu venho sendo muito alegre sobre isso, que é como se sabe que é ruim.
    >>  ............................................
  playful.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've been laughing that off since spring.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Venho rindo disso desde a primavera.
    >>  ............................................
  playful.dialogue.conversations.happy.unspoken/3
    en  Yes. I've decided four times to say it and made a joke instead four times.
    >>  ............................................
    pt  Sim. Decidi quatro vezes dizer e fiz piada em vez disso quatro vezes.
    >>  ............................................
  relaxed.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house, and I know how that ends.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa, e eu sei como isso termina.
    >>  ............................................
  relaxed.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've watched that go badly for other people.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Já vi isso acabar mal com outras pessoas.
    >>  ............................................
  relaxed.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it. At my age four times is a long delay.
    >>  ............................................
    pt  Sim. Quatro vezes decidi dizer. Na minha idade, quatro vezes é uma longa demora.
    >>  ............................................
  sensitive.dialogue.conversations.happy.unspoken/1
    en  ...There is. I've been happy and lonely in the same house and I'm ashamed of both halves.
    >>  ............................................
    pt  ...Tem. Fiquei feliz e sozinho na mesma casa e me envergonho das duas metades.
    >>  ............................................
  sensitive.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've been saying it doesn't matter and it does.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Venho dizendo que não importa e importa.
    >>  ............................................
  sensitive.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've decided to say it. This is the fifth and I'm shaking a little.
    >>  ............................................
    pt  Sim. Quatro vezes decidi dizer. Esta é a quinta e estou tremendo um pouco.
    >>  ............................................
  shy.dialogue.conversations.happy.unspoken/1
    en  ...There is.
    >>  ............................................
    pt  ...Tem.
    >>  ............................................
  shy.dialogue.conversations.happy.unspoken/2
    en  You're away more than you were.
    >>  ............................................
    pt  Você viaja mais do que antes.
    >>  ............................................
  shy.dialogue.conversations.happy.unspoken/3
    en  Yes. Four times I've meant to say it.
    >>  ............................................
    pt  Sim. Quatro vezes eu quis dizer.
    >>  ............................................
  upbeat.dialogue.conversations.happy.unspoken/1
    en  ...There is, and I've been very cheerful about it, which is how you know it's bad.
    >>  ............................................
    pt  ...Tem, e eu venho sendo muito alegre sobre isso, que é como se sabe que é ruim.
    >>  ............................................
  upbeat.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've been laughing that off since spring.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Venho rindo disso desde a primavera.
    >>  ............................................
  upbeat.dialogue.conversations.happy.unspoken/3
    en  Yes. I've decided four times to say it and made a joke instead four times.
    >>  ............................................
    pt  Sim. Decidi quatro vezes dizer e fiz piada em vez disso quatro vezes.
    >>  ............................................
  witty.dialogue.conversations.happy.unspoken/1
    en  ...There is, and I've been very cheerful about it, which is how you know it's bad.
    >>  ............................................
    pt  ...Tem, e eu venho sendo muito alegre sobre isso, que é como se sabe que é ruim.
    >>  ............................................
  witty.dialogue.conversations.happy.unspoken/2
    en  One thing. You're away more than you were. I've been laughing that off since spring.
    >>  ............................................
    pt  Uma coisa. Você viaja mais do que antes. Venho rindo disso desde a primavera.
    >>  ............................................
  witty.dialogue.conversations.happy.unspoken/3
    en  Yes. I've decided four times to say it and made a joke instead four times.
    >>  ............................................
    pt  Sim. Decidi quatro vezes dizer e fiz piada em vez disso quatro vezes.
    >>  ............................................
```

</details>


### Button `leave` — "Let's talk later."

*stance family `exit` · tone `plain` · answers the beat(s) `us.happy.affirm.to.happy`, `us.happy.defensive.to.happy`, `us.happy.listen.again.to.happy`, `us.happy.listen.to.happy` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.happy.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.followup.leave   [17 chars]
    en  Let's talk later.
    >>  ............................................
    pt  Vamos conversar depois.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.happy.leave
WHO    VILLAGER — what the player reads after pressing "Let's talk later."
       spoken on: conversations.topic.happy.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.happy.respond / leave
```

```text
  dialogue.conversations.us.happy.leave/1   [29 chars]
    en  Aye. It'll keep till evening.
    >>  ............................................
    pt  Tá. Espera até a noite.
    >>  ............................................
  dialogue.conversations.us.happy.leave/2   [27 chars]
    en  Go on, %1$s. We're alright.
    >>  ............................................
    pt  Pode ir, %1$s. Estamos bem.
    >>  ............................................
  dialogue.conversations.us.happy.leave/3   [19 chars]
    en  Right. Later, then.
    >>  ............................................
    pt  Certo. Depois, então.
    >>  ............................................
```

---


## `conversations.topic.happy.gratitude`

**Reached from 1 route(s):** `conversations.topic.happy.respond` / `ask_grateful`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.happy.gratitude` — e.g. "That you come back. It sounds small said out loud and it isn't."


```text
POOL   dialogue key: dialogue.conversations.topic.happy.gratitude
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.happy.gratitude
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.happy.gratitude   [25 chars]
    en  That's the honest answer.
    >>  ............................................
    pt  Essa é a resposta honesta.
    >>  ............................................
```


### Button `same_from_me` — "I'd say the same about you."

*stance family `self_disclosure` · tone `gentle` · outcome `appreciated` · answers the beat(s) `happy.gratitude`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.gratitude.same` — accepted phrasings: "i would say the same about you"; "i feel the same about you"; "that goes both ways"
  - scored words: `same`(0.8), `about`(0.3), `you`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.gratitude.same_from_me
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.gratitude
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.gratitude.same_from_me   [27 chars]
    en  I'd say the same about you.
    >>  ............................................
    pt  Eu diria o mesmo de você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `happy.gratitude.same`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +3  _(recorded under topic `happy.gratitude.same`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.happy.gratitude.same
WHO    VILLAGER — what the player reads after pressing "I'd say the same about you."
       spoken on: conversations.topic.happy.gratitude, button `same_from_me`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.gratitude.same`: the villager accepts. Subject `happy.gratitude`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.happy.gratitude.same/1   [68 chars]
    en  Then we've both been thinking it and neither of us said it. Typical.
    >>  ............................................
    pt  Então nós dois pensávamos e nenhum disse. Típico.
    >>  ............................................
  dialogue.conversations.happy.gratitude.same/2   [64 chars]
    en  Say it more often. I'll not get tired of it, whatever I pretend.
    >>  ............................................
    pt  Diga mais vezes. Não vou me cansar, por mais que eu finja.
    >>  ............................................
  dialogue.conversations.happy.gratitude.same/3   [53 chars]
    en  ...Right. I'll be no use for the rest of the day now.
    >>  ............................................
    pt  ...Certo. Agora não sirvo pra nada pelo resto do dia.
    >>  ............................................
```


### Button `didnt_know` — "I didn't know that mattered to you."

*stance family `candor` · tone `gentle` · outcome `engaged` · answers the beat(s) `happy.gratitude`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.gratitude.didnt_know` — accepted phrasings: "i did not know that mattered to you"; "i had no idea that mattered"; "i never knew that"
  - the message must contain one of: `mattered`
  - scored words: `mattered`(1.5), `knew`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.gratitude.didnt_know
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.gratitude
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.gratitude.didnt_know   [35 chars]
    en  I didn't know that mattered to you.
    >>  ............................................
    pt  Eu não sabia que isso importava pra você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `happy.gratitude.didnt_know`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.happy.gratitude.didnt_know
WHO    VILLAGER — what the player reads after pressing "I didn't know that mattered to you."
       spoken on: conversations.topic.happy.gratitude, button `didnt_know`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.gratitude.didnt_know`: the villager discloses. Subject `happy.gratitude`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.happy.gratitude.didnt_know/1   [72 chars]
    en  You weren't meant to. I'm telling you because not telling you got heavy.
    >>  ............................................
    pt  Não era pra saber. Estou dizendo porque não dizer ficou pesado.
    >>  ............................................
  dialogue.conversations.happy.gratitude.didnt_know/2   [75 chars]
    en  It does. I've a habit of keeping the good things quieter than the bad ones.
    >>  ............................................
    pt  Importa. Tenho o hábito de guardar as coisas boas mais que as ruins.
    >>  ............................................
  dialogue.conversations.happy.gratitude.didnt_know/3   [55 chars]
    en  Now you do. That's the whole reason I said it out loud.
    >>  ............................................
    pt  Agora sabe. É esse o motivo inteiro de eu ter dito em voz alta.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I've held it a long time and holding it stopped working.
    >>  ............................................
    pt  Não era pra saber. Segurei muito tempo e segurar parou de funcionar.
    >>  ............................................
  anxious.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I keep the good things quiet because saying them makes them losable.
    >>  ............................................
    pt  Importa. Guardo as coisas boas porque dizê-las as torna perdíveis.
    >>  ............................................
  anxious.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. Please don't make a thing of it — I only wanted it said once.
    >>  ............................................
    pt  Agora sabe. Por favor não faça alarde — eu só queria dizer uma vez.
    >>  ............................................
  athletic.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I've been quiet about the good things for forty years.
    >>  ............................................
    pt  Não era pra saber. Fiquei calado sobre as coisas boas por quarenta anos.
    >>  ............................................
  athletic.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I learned young to keep the good things quiet, and it was poor teaching.
    >>  ............................................
    pt  Importa. Aprendi cedo a guardar as coisas boas, e foi um ensino ruim.
    >>  ............................................
  athletic.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. There's less time left to be saying these things late, so I said it.
    >>  ............................................
    pt  Agora sabe. Sobra menos tempo pra dizer essas coisas tarde, então eu disse.
    >>  ............................................
  confident.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I'm telling you because not telling you got heavy.
    >>  ............................................
    pt  Não era pra saber. Estou dizendo porque não dizer ficou pesado.
    >>  ............................................
  confident.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I keep the good things quieter than the bad ones.
    >>  ............................................
    pt  Importa. Guardo as coisas boas mais que as ruins.
    >>  ............................................
  confident.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. That's the whole reason I said it out loud.
    >>  ............................................
    pt  Agora sabe. É esse o motivo inteiro de eu ter dito em voz alta.
    >>  ............................................
  crabby.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I'm telling you because not telling you got heavy.
    >>  ............................................
    pt  Não era pra saber. Estou dizendo porque não dizer ficou pesado.
    >>  ............................................
  crabby.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I keep the good things quieter than the bad ones.
    >>  ............................................
    pt  Importa. Guardo as coisas boas mais que as ruins.
    >>  ............................................
  crabby.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. That's the whole reason I said it out loud.
    >>  ............................................
    pt  Agora sabe. É esse o motivo inteiro de eu ter dito em voz alta.
    >>  ............................................
  extroverted.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to, %1$s. I'm telling you because not telling you got heavy.
    >>  ............................................
    pt  Não era pra saber, %1$s. Estou dizendo porque não dizer ficou pesado.
    >>  ............................................
  extroverted.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I've a habit of keeping the good things quiet and I'm trying to stop.
    >>  ............................................
    pt  Importa. Tenho o hábito de guardar as coisas boas e estou tentando parar.
    >>  ............................................
  extroverted.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. I'd rather you knew it from me than worked it out on your own.
    >>  ............................................
    pt  Agora sabe. Prefiro que saiba por mim a que descubra sozinho.
    >>  ............................................
  flirty.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to, %1$s. I'm telling you because not telling you got heavy.
    >>  ............................................
    pt  Não era pra saber, %1$s. Estou dizendo porque não dizer ficou pesado.
    >>  ............................................
  flirty.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I've a habit of keeping the good things quiet and I'm trying to stop.
    >>  ............................................
    pt  Importa. Tenho o hábito de guardar as coisas boas e estou tentando parar.
    >>  ............................................
  flirty.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. I'd rather you knew it from me than worked it out on your own.
    >>  ............................................
    pt  Agora sabe. Prefiro que saiba por mim a que descubra sozinho.
    >>  ............................................
  friendly.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to, %1$s. I'm telling you because not telling you got heavy.
    >>  ............................................
    pt  Não era pra saber, %1$s. Estou dizendo porque não dizer ficou pesado.
    >>  ............................................
  friendly.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I've a habit of keeping the good things quiet and I'm trying to stop.
    >>  ............................................
    pt  Importa. Tenho o hábito de guardar as coisas boas e estou tentando parar.
    >>  ............................................
  friendly.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. I'd rather you knew it from me than worked it out on your own.
    >>  ............................................
    pt  Agora sabe. Prefiro que saiba por mim a que descubra sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I've held it a long time and holding it stopped working.
    >>  ............................................
    pt  Não era pra saber. Segurei muito tempo e segurar parou de funcionar.
    >>  ............................................
  gloomy.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I keep the good things quiet because saying them makes them losable.
    >>  ............................................
    pt  Importa. Guardo as coisas boas porque dizê-las as torna perdíveis.
    >>  ............................................
  gloomy.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. Please don't make a thing of it — I only wanted it said once.
    >>  ............................................
    pt  Agora sabe. Por favor não faça alarde — eu só queria dizer uma vez.
    >>  ............................................
  greedy.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I'm telling you because not telling you got heavy.
    >>  ............................................
    pt  Não era pra saber. Estou dizendo porque não dizer ficou pesado.
    >>  ............................................
  greedy.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I keep the good things quieter than the bad ones.
    >>  ............................................
    pt  Importa. Guardo as coisas boas mais que as ruins.
    >>  ............................................
  greedy.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. That's the whole reason I said it out loud.
    >>  ............................................
    pt  Agora sabe. É esse o motivo inteiro de eu ter dito em voz alta.
    >>  ............................................
  grumpy.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I'm telling you because not telling you got heavy.
    >>  ............................................
    pt  Não era pra saber. Estou dizendo porque não dizer ficou pesado.
    >>  ............................................
  grumpy.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I keep the good things quieter than the bad ones.
    >>  ............................................
    pt  Importa. Guardo as coisas boas mais que as ruins.
    >>  ............................................
  grumpy.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. That's the whole reason I said it out loud.
    >>  ............................................
    pt  Agora sabe. É esse o motivo inteiro de eu ter dito em voz alta.
    >>  ............................................
  introverted.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to.
    >>  ............................................
    pt  Não era pra saber.
    >>  ............................................
  introverted.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does.
    >>  ............................................
    pt  Importa.
    >>  ............................................
  introverted.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do.
    >>  ............................................
    pt  Agora sabe.
    >>  ............................................
  lazy.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I've been quiet about the good things for forty years.
    >>  ............................................
    pt  Não era pra saber. Fiquei calado sobre as coisas boas por quarenta anos.
    >>  ............................................
  lazy.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I learned young to keep the good things quiet, and it was poor teaching.
    >>  ............................................
    pt  Importa. Aprendi cedo a guardar as coisas boas, e foi um ensino ruim.
    >>  ............................................
  lazy.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. There's less time left to be saying these things late, so I said it.
    >>  ............................................
    pt  Agora sabe. Sobra menos tempo pra dizer essas coisas tarde, então eu disse.
    >>  ............................................
  odd.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to.
    >>  ............................................
    pt  Não era pra saber.
    >>  ............................................
  odd.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does.
    >>  ............................................
    pt  Importa.
    >>  ............................................
  odd.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do.
    >>  ............................................
    pt  Agora sabe.
    >>  ............................................
  peaceful.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I've been quiet about the good things for forty years.
    >>  ............................................
    pt  Não era pra saber. Fiquei calado sobre as coisas boas por quarenta anos.
    >>  ............................................
  peaceful.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I learned young to keep the good things quiet, and it was poor teaching.
    >>  ............................................
    pt  Importa. Aprendi cedo a guardar as coisas boas, e foi um ensino ruim.
    >>  ............................................
  peaceful.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. There's less time left to be saying these things late, so I said it.
    >>  ............................................
    pt  Agora sabe. Sobra menos tempo pra dizer essas coisas tarde, então eu disse.
    >>  ............................................
  peppy.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to! I'm telling you because keeping it in got heavier than saying it.
    >>  ............................................
    pt  Não era pra saber! Estou dizendo porque guardar ficou mais pesado que falar.
    >>  ............................................
  peppy.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I'm loud about everything except the things I actually mean, apparently.
    >>  ............................................
    pt  Importa. Sou barulhento com tudo, menos com o que realmente sinto, aparentemente.
    >>  ............................................
  peppy.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do, and I've no idea what either of us is meant to do next.
    >>  ............................................
    pt  Agora sabe, e não faço ideia do que nós dois devemos fazer agora.
    >>  ............................................
  playful.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to! I'm telling you because keeping it in got heavier than saying it.
    >>  ............................................
    pt  Não era pra saber! Estou dizendo porque guardar ficou mais pesado que falar.
    >>  ............................................
  playful.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I'm loud about everything except the things I actually mean, apparently.
    >>  ............................................
    pt  Importa. Sou barulhento com tudo, menos com o que realmente sinto, aparentemente.
    >>  ............................................
  playful.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do, and I've no idea what either of us is meant to do next.
    >>  ............................................
    pt  Agora sabe, e não faço ideia do que nós dois devemos fazer agora.
    >>  ............................................
  relaxed.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I've been quiet about the good things for forty years.
    >>  ............................................
    pt  Não era pra saber. Fiquei calado sobre as coisas boas por quarenta anos.
    >>  ............................................
  relaxed.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I learned young to keep the good things quiet, and it was poor teaching.
    >>  ............................................
    pt  Importa. Aprendi cedo a guardar as coisas boas, e foi um ensino ruim.
    >>  ............................................
  relaxed.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. There's less time left to be saying these things late, so I said it.
    >>  ............................................
    pt  Agora sabe. Sobra menos tempo pra dizer essas coisas tarde, então eu disse.
    >>  ............................................
  sensitive.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to. I've held it a long time and holding it stopped working.
    >>  ............................................
    pt  Não era pra saber. Segurei muito tempo e segurar parou de funcionar.
    >>  ............................................
  sensitive.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I keep the good things quiet because saying them makes them losable.
    >>  ............................................
    pt  Importa. Guardo as coisas boas porque dizê-las as torna perdíveis.
    >>  ............................................
  sensitive.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do. Please don't make a thing of it — I only wanted it said once.
    >>  ............................................
    pt  Agora sabe. Por favor não faça alarde — eu só queria dizer uma vez.
    >>  ............................................
  shy.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to.
    >>  ............................................
    pt  Não era pra saber.
    >>  ............................................
  shy.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does.
    >>  ............................................
    pt  Importa.
    >>  ............................................
  shy.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do.
    >>  ............................................
    pt  Agora sabe.
    >>  ............................................
  upbeat.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to! I'm telling you because keeping it in got heavier than saying it.
    >>  ............................................
    pt  Não era pra saber! Estou dizendo porque guardar ficou mais pesado que falar.
    >>  ............................................
  upbeat.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I'm loud about everything except the things I actually mean, apparently.
    >>  ............................................
    pt  Importa. Sou barulhento com tudo, menos com o que realmente sinto, aparentemente.
    >>  ............................................
  upbeat.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do, and I've no idea what either of us is meant to do next.
    >>  ............................................
    pt  Agora sabe, e não faço ideia do que nós dois devemos fazer agora.
    >>  ............................................
  witty.dialogue.conversations.happy.gratitude.didnt_know/1
    en  You weren't meant to! I'm telling you because keeping it in got heavier than saying it.
    >>  ............................................
    pt  Não era pra saber! Estou dizendo porque guardar ficou mais pesado que falar.
    >>  ............................................
  witty.dialogue.conversations.happy.gratitude.didnt_know/2
    en  It does. I'm loud about everything except the things I actually mean, apparently.
    >>  ............................................
    pt  Importa. Sou barulhento com tudo, menos com o que realmente sinto, aparentemente.
    >>  ............................................
  witty.dialogue.conversations.happy.gratitude.didnt_know/3
    en  Now you do, and I've no idea what either of us is meant to do next.
    >>  ............................................
    pt  Agora sabe, e não faço ideia do que nós dois devemos fazer agora.
    >>  ............................................
```

</details>


### Button `leave` — "I'll carry that with me."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `happy.gratitude` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.happy.gratitude.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.gratitude
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.gratitude.leave   [24 chars]
    en  I'll carry that with me.
    >>  ............................................
    pt  Vou levar isso comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.happy.gratitude.leave
WHO    VILLAGER — what the player reads after pressing "I'll carry that with me."
       spoken on: conversations.topic.happy.gratitude, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.gratitude.leave`: the villager accepts. Subject `happy.gratitude`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.happy.gratitude.leave/1   [3 chars]
    en  Do.
    >>  ............................................
    pt  Leve.
    >>  ............................................
  dialogue.conversations.happy.gratitude.leave/2   [20 chars]
    en  Just so. Off you go.
    >>  ............................................
    pt  Pois é. Pode ir.
    >>  ............................................
  dialogue.conversations.happy.gratitude.leave/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.happy.respond`

**Reached from 4 route(s):** `conversations.us` / `happy`; `conversations.us` / `happy`; `conversations.us` / `happy`; `conversations.us` / `happy`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.happy.again` — e.g. "You asked me that this morning, love, and the answer hasn't had time to change."
- `conversations.us.happy.grateful` — e.g. "Happy? You brought me %2$s and asked me that with a straight face. Yes, love. Yes."
- `conversations.us.happy.low` — e.g. "I'm happy with you. It's everything else that's been heavy lately."
- `conversations.us.happy.yes` — e.g. "With you? Yes. Even when you track mud in. Especially then, somehow."


```text
POOL   dialogue key: dialogue.conversations.topic.happy.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.happy.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.happy.respond   [27 chars]
    en  That's how we are, I think.
    >>  ............................................
    pt  É assim que estamos, eu acho.
    >>  ............................................
```


### Button `listen` — "Tell me honestly."

*stance family `restraint` · tone `gentle` · answers the beat(s) `us.happy.again.to.happy`, `us.happy.grateful.to.happy`, `us.happy.low.to.happy`, `us.happy.yes.to.happy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.listen` — accepted phrasings: "tell me honestly"; "give it to me honestly"; "i want the honest answer"
  - the message must contain one of: `honestly`
  - scored words: `honestly`(1.5), `tell`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.respond.listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.respond.listen   [17 chars]
    en  Tell me honestly.
    >>  ............................................
    pt  Me diz com sinceridade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when arc `us` is at stage 1..2
- Does: **hearts +1** — decision id `us.happy.listen`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +5, warmth +2  _(recorded under topic `us.happy.listen`)_
- Then opens: `conversations.topic.happy.followup`
- …where the player's next choices will be: "What could we do better?" | "We'll be alright." | "You worry too much." | "Is there something you haven't said?" | "Let's talk later."

```text
POOL   dialogue key: dialogue.conversations.us.happy.listen.again
WHO    VILLAGER — what the player reads after pressing "Tell me honestly."
       spoken on: conversations.topic.happy.respond, button `listen`
       leaves the player on: conversations.topic.happy.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.listen.again.to.happy`: the villager accepts. Subject `happy`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.listen.again/1   [75 chars]
    en  We've talked about this before, you and I. It's better than it was. Mostly.
    >>  ............................................
    pt  A gente já falou disso antes, você e eu. Está melhor do que estava. Na maior parte.
    >>  ............................................
  dialogue.conversations.us.happy.listen.again/2   [66 chars]
    en  You keep coming back to ask. That's most of why it's better, %1$s.
    >>  ............................................
    pt  Você continua voltando para perguntar. É esse o principal motivo de estar melhor, %1$s.
    >>  ............................................
  dialogue.conversations.us.happy.listen.again/3   [65 chars]
    en  Same question, and a different answer this time. That's progress.
    >>  ............................................
    pt  Mesma pergunta, resposta diferente desta vez. Isso é progresso.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when arc `us` is at stage 1..2  _(chance -2000)_
- Does: **hearts +1** — decision id `us.happy.listen`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — trust +4, warmth +1  _(recorded under topic `us.happy.listen`)_
- Then opens: `conversations.topic.happy.followup`
- …where the player's next choices will be: "What could we do better?" | "We'll be alright." | "You worry too much." | "Is there something you haven't said?" | "Let's talk later."

```text
POOL   dialogue key: dialogue.conversations.us.happy.listen
WHO    VILLAGER — what the player reads after pressing "Tell me honestly."
       spoken on: conversations.topic.happy.respond, button `listen`
       leaves the player on: conversations.topic.happy.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.listen.to.happy`: the villager accepts. Subject `happy`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.listen/1   [63 chars]
    en  ...Honestly, then. Mostly yes. There's a thing, but mostly yes.
    >>  ............................................
    pt  ...Com sinceridade, então. Na maior parte, sim. Tem uma coisa, mas na maior parte sim.
    >>  ............................................
  dialogue.conversations.us.happy.listen/2   [52 chars]
    en  You want the true answer, not the easy one. Alright.
    >>  ............................................
    pt  Você quer a resposta verdadeira, não a fácil. Certo.
    >>  ............................................
  dialogue.conversations.us.happy.listen/3   [51 chars]
    en  Nobody asks and waits. You waited. So — here it is.
    >>  ............................................
    pt  Ninguém pergunta e espera. Você esperou. Então — aqui está.
    >>  ............................................
```


### Button `affirm` — "I'm happy with us."

*stance family `encouragement` · tone `plain` · answers the beat(s) `us.happy.again.to.happy`, `us.happy.grateful.to.happy`, `us.happy.low.to.happy`, `us.happy.yes.to.happy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.affirm` — accepted phrasings: "i am content with how we are"; "things are good between us"; "i have no complaints about us"
  - the message must contain one of: `complaints`, `content`
  - scored words: `complaints`(1.0), `content`(1.2), `good`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.respond.affirm
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.respond.affirm   [18 chars]
    en  I'm happy with us.
    >>  ............................................
    pt  Estou feliz com a gente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.happy.affirm`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5  _(recorded under topic `us.happy.affirm`)_
- Then opens: `conversations.topic.happy.followup`
- …where the player's next choices will be: "What could we do better?" | "We'll be alright." | "You worry too much." | "Is there something you haven't said?" | "Let's talk later."

```text
POOL   dialogue key: dialogue.conversations.us.happy.affirm
WHO    VILLAGER — what the player reads after pressing "I'm happy with us."
       spoken on: conversations.topic.happy.respond, button `affirm`
       leaves the player on: conversations.topic.happy.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.affirm.to.happy`: the villager accepts. Subject `happy`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.affirm/1   [41 chars]
    en  Are you? Good. Say that more often, %1$s.
    >>  ............................................
    pt  Está? Bom. Diga isso mais vezes, %1$s.
    >>  ............................................
  dialogue.conversations.us.happy.affirm/2   [39 chars]
    en  So am I. It's easy to forget to say it.
    >>  ............................................
    pt  Eu também. É fácil esquecer de dizer.
    >>  ............................................
  dialogue.conversations.us.happy.affirm/3   [61 chars]
    en  Then we're both alright. That's not nothing, after this long.
    >>  ............................................
    pt  Então nós dois estamos bem. Isso não é pouco, depois de tanto tempo.
    >>  ............................................
```


### Button `defensive` — "What's that supposed to mean?"

*stance family `candor` · tone `blunt` · answers the beat(s) `us.happy.again.to.happy`, `us.happy.grateful.to.happy`, `us.happy.low.to.happy`, `us.happy.yes.to.happy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.defensive` — accepted phrasings: "what is that supposed to mean"; "what do you mean by that"; "and what is that meant to mean"
  - the message must contain one of: `supposed`
  - scored words: `mean`(0.6), `supposed`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.respond.defensive
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.respond.defensive   [29 chars]
    en  What's that supposed to mean?
    >>  ............................................
    pt  O que isso quer dizer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `us.happy.defensive`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — tension +6, warmth -3  _(recorded under topic `us.happy.defensive`)_
- Then opens: `conversations.topic.happy.followup`
- …where the player's next choices will be: "What could we do better?" | "We'll be alright." | "You worry too much." | "Is there something you haven't said?" | "Let's talk later."

```text
POOL   dialogue key: dialogue.conversations.us.happy.defensive
WHO    VILLAGER — what the player reads after pressing "What's that supposed to mean?"
       spoken on: conversations.topic.happy.respond, button `defensive`
       leaves the player on: conversations.topic.happy.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.defensive.to.happy`: the villager accepts. Subject `happy`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.defensive/1   [46 chars]
    en  ...It wasn't an accusation. It was a question.
    >>  ............................................
    pt  ...Não era acusação. Era pergunta.
    >>  ............................................
  dialogue.conversations.us.happy.defensive/2   [50 chars]
    en  That's exactly the tone I was worried about, %1$s.
    >>  ............................................
    pt  É exatamente esse o tom que me preocupava, %1$s.
    >>  ............................................
  dialogue.conversations.us.happy.defensive/3   [24 chars]
    en  Nothing. Forget I asked.
    >>  ............................................
    pt  Nada. Esquece que eu perguntei.
    >>  ............................................
```


### Button `ask_grateful` — "Is there anything you're grateful for?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `us.happy.again.to.happy`, `us.happy.grateful.to.happy`, `us.happy.low.to.happy`, `us.happy.yes.to.happy`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.gratitude` — accepted phrasings: "is there anything you are grateful for"; "what are you grateful for"; "is there something you are thankful for"
  - the message must contain one of: `grateful`, `thankful`
  - scored words: `grateful`(1.5), `thankful`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.respond.ask_grateful
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.respond.ask_grateful   [38 chars]
    en  Is there anything you're grateful for?
    >>  ............................................
    pt  Tem alguma coisa pela qual você é grato?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.happy.gratitude`
- …where the player's next choices will be: "I'd say the same about you." | "I didn't know that mattered to you." | "I'll carry that with me."

```text
POOL   dialogue key: dialogue.conversations.happy.gratitude
WHO    VILLAGER — what the player reads after pressing "Is there anything you're grateful for?"
       spoken on: conversations.topic.happy.respond, button `ask_grateful`
       leaves the player on: conversations.topic.happy.gratitude
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.gratitude`: the villager celebrates. Subject `happy.gratitude`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, self_disclosure, exit
```

```text
  dialogue.conversations.happy.gratitude/1   [63 chars]
    en  That you come back. It sounds small said out loud and it isn't.
    >>  ............................................
    pt  Que você volta. Soa pequeno dito em voz alta e não é.
    >>  ............................................
  dialogue.conversations.happy.gratitude/2   [75 chars]
    en  The ordinary evenings. Everyone waits for the big ones and misses the rest.
    >>  ............................................
    pt  As noites comuns. Todos esperam as grandes e perdem o resto.
    >>  ............................................
  dialogue.conversations.happy.gratitude/3   [70 chars]
    en  That you ask me things. Nobody asked me anything for years before you.
    >>  ............................................
    pt  Que você me pergunta coisas. Ninguém me perguntava nada há anos antes de você.
    >>  ............................................
```


### Button `leave` — "Let's talk later."

*stance family `exit` · tone `plain` · answers the beat(s) `us.happy.again.to.happy`, `us.happy.grateful.to.happy`, `us.happy.low.to.happy`, `us.happy.yes.to.happy` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.happy.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.respond.leave   [17 chars]
    en  Let's talk later.
    >>  ............................................
    pt  Vamos conversar depois.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.happy.leave
WHO    VILLAGER — what the player reads after pressing "Let's talk later."
       spoken on: conversations.topic.happy.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.happy.followup / leave
```

> Written out in full under **`conversations.topic.happy.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.happy.unspoken`

**Reached from 1 route(s):** `conversations.topic.happy.followup` / `ask_unspoken`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.happy.unspoken` — e.g. "...There is. I've been happy and lonely in the same house and I can't explain it."


```text
POOL   dialogue key: dialogue.conversations.topic.happy.unspoken
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.happy.unspoken
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.happy.unspoken   [28 chars]
    en  So now it's said, or nearly.
    >>  ............................................
    pt  Então agora foi dito, ou quase.
    >>  ............................................
```


### Button `say_it_now` — "Say it now. I'd rather know."

*stance family `empathy` · tone `gentle` · outcome `engaged` · answers the beat(s) `happy.unspoken`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.unspoken.said` — accepted phrasings: "say it now i would rather know"; "tell me now"; "i would rather hear it"
  - the message must contain one of: `rather`
  - scored words: `now`(0.5), `rather`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.unspoken.say_it_now
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.unspoken
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.unspoken.say_it_now   [28 chars]
    en  Say it now. I'd rather know.
    >>  ............................................
    pt  Diga agora. Prefiro saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `happy.unspoken.said`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `happy.unspoken.said`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.happy.unspoken.said
WHO    VILLAGER — what the player reads after pressing "Say it now. I'd rather know."
       spoken on: conversations.topic.happy.unspoken, button `say_it_now`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.unspoken.said`: the villager discloses. Subject `happy.unspoken`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.happy.unspoken.said/1   [77 chars]
    en  Then: I'd like more of the ordinary hours. Not the grand ones. The dull ones.
    >>  ............................................
    pt  Então: eu queria mais das horas comuns. Não as grandiosas. As sem graça.
    >>  ............................................
  dialogue.conversations.happy.unspoken.said/2   [78 chars]
    en  Right. I want to be asked, not managed. That's the whole of it and it's small.
    >>  ............................................
    pt  Certo. Quero ser perguntado, não administrado. É tudo, e é pouco.
    >>  ............................................
  dialogue.conversations.happy.unspoken.said/3   [83 chars]
    en  ...I'm frightened you'll go and be happier. There. It's out and I'm still standing.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Saiu e eu continuo de pé.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours. I know how small that sounds. It isn't small to me.
    >>  ............................................
    pt  Então: mais das horas comuns. Sei o quão pouco isso soa. Pra mim não é pouco.
    >>  ............................................
  anxious.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. Saying that has taken me most of a year.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Dizer isso me tomou quase um ano.
    >>  ............................................
  anxious.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. I've never said that to anyone and I feel ill.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Nunca disse isso a ninguém e me sinto mal.
    >>  ............................................
  athletic.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours. They're the ones you miss afterwards, not the grand ones.
    >>  ............................................
    pt  Então: mais das horas comuns. São essas que fazem falta depois, não as grandiosas.
    >>  ............................................
  athletic.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. I've been managed before and I know the difference.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Já fui administrado e conheço a diferença.
    >>  ............................................
  athletic.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. I've seen that happen to two couples here.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Vi isso acontecer com dois casais aqui.
    >>  ............................................
  confident.dialogue.conversations.happy.unspoken.said/1
    en  Then: I'd like more of the ordinary hours. Not the grand ones. The dull ones.
    >>  ............................................
    pt  Então: eu queria mais das horas comuns. Não as grandiosas. As sem graça.
    >>  ............................................
  confident.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. That's the whole of it and it's small.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. É tudo, e é pouco.
    >>  ............................................
  confident.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There. It's out.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Saiu.
    >>  ............................................
  crabby.dialogue.conversations.happy.unspoken.said/1
    en  Then: I'd like more of the ordinary hours. Not the grand ones. The dull ones.
    >>  ............................................
    pt  Então: eu queria mais das horas comuns. Não as grandiosas. As sem graça.
    >>  ............................................
  crabby.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. That's the whole of it and it's small.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. É tudo, e é pouco.
    >>  ............................................
  crabby.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There. It's out.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Saiu.
    >>  ............................................
  extroverted.dialogue.conversations.happy.unspoken.said/1
    en  Then: I'd like more of the ordinary hours with you. Not the grand ones. The dull ones.
    >>  ............................................
    pt  Então: eu queria mais das horas comuns com você. Não as grandiosas. As sem graça.
    >>  ............................................
  extroverted.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. You do it kindly, %1$s, and it's still managing.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Você faz com carinho, %1$s, e ainda é administrar.
    >>  ............................................
  extroverted.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There it is, and I still love you, and both are true.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto, e eu ainda te amo, e as duas são verdade.
    >>  ............................................
  flirty.dialogue.conversations.happy.unspoken.said/1
    en  Then: I'd like more of the ordinary hours with you. Not the grand ones. The dull ones.
    >>  ............................................
    pt  Então: eu queria mais das horas comuns com você. Não as grandiosas. As sem graça.
    >>  ............................................
  flirty.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. You do it kindly, %1$s, and it's still managing.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Você faz com carinho, %1$s, e ainda é administrar.
    >>  ............................................
  flirty.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There it is, and I still love you, and both are true.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto, e eu ainda te amo, e as duas são verdade.
    >>  ............................................
  friendly.dialogue.conversations.happy.unspoken.said/1
    en  Then: I'd like more of the ordinary hours with you. Not the grand ones. The dull ones.
    >>  ............................................
    pt  Então: eu queria mais das horas comuns com você. Não as grandiosas. As sem graça.
    >>  ............................................
  friendly.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. You do it kindly, %1$s, and it's still managing.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Você faz com carinho, %1$s, e ainda é administrar.
    >>  ............................................
  friendly.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There it is, and I still love you, and both are true.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto, e eu ainda te amo, e as duas são verdade.
    >>  ............................................
  gloomy.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours. I know how small that sounds. It isn't small to me.
    >>  ............................................
    pt  Então: mais das horas comuns. Sei o quão pouco isso soa. Pra mim não é pouco.
    >>  ............................................
  gloomy.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. Saying that has taken me most of a year.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Dizer isso me tomou quase um ano.
    >>  ............................................
  gloomy.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. I've never said that to anyone and I feel ill.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Nunca disse isso a ninguém e me sinto mal.
    >>  ............................................
  greedy.dialogue.conversations.happy.unspoken.said/1
    en  Then: I'd like more of the ordinary hours. Not the grand ones. The dull ones.
    >>  ............................................
    pt  Então: eu queria mais das horas comuns. Não as grandiosas. As sem graça.
    >>  ............................................
  greedy.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. That's the whole of it and it's small.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. É tudo, e é pouco.
    >>  ............................................
  greedy.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There. It's out.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Saiu.
    >>  ............................................
  grumpy.dialogue.conversations.happy.unspoken.said/1
    en  Then: I'd like more of the ordinary hours. Not the grand ones. The dull ones.
    >>  ............................................
    pt  Então: eu queria mais das horas comuns. Não as grandiosas. As sem graça.
    >>  ............................................
  grumpy.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. That's the whole of it and it's small.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. É tudo, e é pouco.
    >>  ............................................
  grumpy.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There. It's out.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Saiu.
    >>  ............................................
  introverted.dialogue.conversations.happy.unspoken.said/1
    en  More of the ordinary hours. Not the grand ones.
    >>  ............................................
    pt  Mais das horas comuns. Não as grandiosas.
    >>  ............................................
  introverted.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed.
    >>  ............................................
    pt  Quero ser perguntado, não administrado.
    >>  ............................................
  introverted.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz.
    >>  ............................................
  lazy.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours. They're the ones you miss afterwards, not the grand ones.
    >>  ............................................
    pt  Então: mais das horas comuns. São essas que fazem falta depois, não as grandiosas.
    >>  ............................................
  lazy.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. I've been managed before and I know the difference.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Já fui administrado e conheço a diferença.
    >>  ............................................
  lazy.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. I've seen that happen to two couples here.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Vi isso acontecer com dois casais aqui.
    >>  ............................................
  odd.dialogue.conversations.happy.unspoken.said/1
    en  More of the ordinary hours. Not the grand ones.
    >>  ............................................
    pt  Mais das horas comuns. Não as grandiosas.
    >>  ............................................
  odd.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed.
    >>  ............................................
    pt  Quero ser perguntado, não administrado.
    >>  ............................................
  odd.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz.
    >>  ............................................
  peaceful.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours. They're the ones you miss afterwards, not the grand ones.
    >>  ............................................
    pt  Então: mais das horas comuns. São essas que fazem falta depois, não as grandiosas.
    >>  ............................................
  peaceful.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. I've been managed before and I know the difference.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Já fui administrado e conheço a diferença.
    >>  ............................................
  peaceful.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. I've seen that happen to two couples here.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Vi isso acontecer com dois casais aqui.
    >>  ............................................
  peppy.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours, please. Not the grand ones. The gloriously dull ones.
    >>  ............................................
    pt  Então: mais das horas comuns, por favor. Não as grandiosas. As gloriosamente sem graça.
    >>  ............................................
  peppy.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed! That's the whole of it, and it's embarrassingly small.
    >>  ............................................
    pt  Quero ser perguntado, não administrado! É tudo, e é constrangedoramente pouco.
    >>  ............................................
  peppy.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There. Said it. Shall we both pretend I didn't?
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Falei. Fingimos que não?
    >>  ............................................
  playful.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours, please. Not the grand ones. The gloriously dull ones.
    >>  ............................................
    pt  Então: mais das horas comuns, por favor. Não as grandiosas. As gloriosamente sem graça.
    >>  ............................................
  playful.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed! That's the whole of it, and it's embarrassingly small.
    >>  ............................................
    pt  Quero ser perguntado, não administrado! É tudo, e é constrangedoramente pouco.
    >>  ............................................
  playful.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There. Said it. Shall we both pretend I didn't?
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Falei. Fingimos que não?
    >>  ............................................
  relaxed.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours. They're the ones you miss afterwards, not the grand ones.
    >>  ............................................
    pt  Então: mais das horas comuns. São essas que fazem falta depois, não as grandiosas.
    >>  ............................................
  relaxed.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. I've been managed before and I know the difference.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Já fui administrado e conheço a diferença.
    >>  ............................................
  relaxed.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. I've seen that happen to two couples here.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Vi isso acontecer com dois casais aqui.
    >>  ............................................
  sensitive.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours. I know how small that sounds. It isn't small to me.
    >>  ............................................
    pt  Então: mais das horas comuns. Sei o quão pouco isso soa. Pra mim não é pouco.
    >>  ............................................
  sensitive.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed. Saying that has taken me most of a year.
    >>  ............................................
    pt  Quero ser perguntado, não administrado. Dizer isso me tomou quase um ano.
    >>  ............................................
  sensitive.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. I've never said that to anyone and I feel ill.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Nunca disse isso a ninguém e me sinto mal.
    >>  ............................................
  shy.dialogue.conversations.happy.unspoken.said/1
    en  More of the ordinary hours. Not the grand ones.
    >>  ............................................
    pt  Mais das horas comuns. Não as grandiosas.
    >>  ............................................
  shy.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed.
    >>  ............................................
    pt  Quero ser perguntado, não administrado.
    >>  ............................................
  shy.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier.
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz.
    >>  ............................................
  upbeat.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours, please. Not the grand ones. The gloriously dull ones.
    >>  ............................................
    pt  Então: mais das horas comuns, por favor. Não as grandiosas. As gloriosamente sem graça.
    >>  ............................................
  upbeat.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed! That's the whole of it, and it's embarrassingly small.
    >>  ............................................
    pt  Quero ser perguntado, não administrado! É tudo, e é constrangedoramente pouco.
    >>  ............................................
  upbeat.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There. Said it. Shall we both pretend I didn't?
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Falei. Fingimos que não?
    >>  ............................................
  witty.dialogue.conversations.happy.unspoken.said/1
    en  Then: more of the ordinary hours, please. Not the grand ones. The gloriously dull ones.
    >>  ............................................
    pt  Então: mais das horas comuns, por favor. Não as grandiosas. As gloriosamente sem graça.
    >>  ............................................
  witty.dialogue.conversations.happy.unspoken.said/2
    en  I want to be asked, not managed! That's the whole of it, and it's embarrassingly small.
    >>  ............................................
    pt  Quero ser perguntado, não administrado! É tudo, e é constrangedoramente pouco.
    >>  ............................................
  witty.dialogue.conversations.happy.unspoken.said/3
    en  ...I'm frightened you'll go and be happier. There. Said it. Shall we both pretend I didn't?
    >>  ............................................
    pt  ...Tenho medo de você ir e ser mais feliz. Pronto. Falei. Fingimos que não?
    >>  ............................................
```

</details>


### Button `not_ready` — "You don't have to say it today."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `happy.unspoken`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `happy.unspoken.waited` — accepted phrasings: "you do not have to say it today"; "there is no hurry"; "tell me when you are ready"
  - the message must contain one of: `today`
  - scored words: `today`(1.2), `have`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.happy.unspoken.not_ready
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.unspoken
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.unspoken.not_ready   [31 chars]
    en  You don't have to say it today.
    >>  ............................................
    pt  Você não precisa dizer isso hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `happy.unspoken.waited`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +1  _(recorded under topic `happy.unspoken.waited`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.happy.unspoken.waited
WHO    VILLAGER — what the player reads after pressing "You don't have to say it today."
       spoken on: conversations.topic.happy.unspoken, button `not_ready`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.unspoken.waited`: the villager accepts. Subject `happy.unspoken`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.happy.unspoken.waited/1   [62 chars]
    en  Thank you. Knowing I could is most of what I needed from this.
    >>  ............................................
    pt  Obrigado. Saber que eu poderia já é quase tudo que eu precisava.
    >>  ............................................
  dialogue.conversations.happy.unspoken.waited/2   [71 chars]
    en  Then I'll say it on a Tuesday, badly, and you'll have to be ready then.
    >>  ............................................
    pt  Então digo numa terça, mal, e você vai ter que estar pronto.
    >>  ............................................
  dialogue.conversations.happy.unspoken.waited/3   [82 chars]
    en  ...Good. I'd got as far as the door of it and that turns out to be enough for now.
    >>  ............................................
    pt  ...Bom. Cheguei até a porta disso e por ora parece ser o bastante.
    >>  ............................................
```


### Button `leave` — "I'll leave it with you."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `happy.unspoken` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.happy.unspoken.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.happy.unspoken
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.happy.unspoken.leave   [23 chars]
    en  I'll leave it with you.
    >>  ............................................
    pt  Vou deixar isso com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.happy.unspoken.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.topic.happy.unspoken, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.unspoken.leave`: the villager accepts. Subject `happy.unspoken`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.happy.unspoken.leave/1   [7 chars]
    en  ...Aye.
    >>  ............................................
    pt  ...É.
    >>  ............................................
  dialogue.conversations.happy.unspoken.leave/2   [6 chars]
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  dialogue.conversations.happy.unspoken.leave/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---

