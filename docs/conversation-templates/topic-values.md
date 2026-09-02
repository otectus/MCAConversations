# Topic: values

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `values` |
| Opened from | question `conversations.cat.personal`, button `values` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | adult |
| Stance families it must offer | `curiosity`, `candor`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.values
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.values   [20 chars]
    en  What matters to you?
    >>  ............................................
    pt  O que importa para você?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.values.followup`](#conversations-scene-values-followup)
- [`conversations.scene.values.the_one_i_put_down.respond`](#conversations-scene-values-the-one-i-put-down-respond)
- [`conversations.scene.values.what_it_cost_lately.respond`](#conversations-scene-values-what-it-cost-lately-respond)
- [`conversations.topic.values.more.respond`](#conversations-topic-values-more-respond)
- [`conversations.topic.values.open.respond`](#conversations-topic-values-open-respond)

---

## `conversations.scene.values.followup`

**Reached from 4 route(s):** `conversations.scene.values.the_one_i_put_down.respond` / `ask_what_changed`; `conversations.scene.values.the_one_i_put_down.respond` / `respect_the_change`; `conversations.scene.values.what_it_cost_lately.respond` / `say_it_was_worth_it`; `conversations.scene.values.what_it_cost_lately.respond` / `ask_if_they_would_again`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.values.the_one_i_put_down.acknowledged` — e.g. "It does, and nobody praises it, because from outside it looks exactly like having no principles at all."
- `conversations.scene.values.the_one_i_put_down.explained` — e.g. "I watched it hurt somebody who had done nothing, and the rule had no answer for that, so the rule went."
- `conversations.scene.values.what_it_cost_lately.answered` — e.g. "Yes, and more quietly. The rule was right and the volume was me enjoying being right."
- `conversations.scene.values.what_it_cost_lately.steadied` — e.g. "That is the sentence I have been saying to myself all month and it lands differently from somebody else."


```text
POOL   dialogue key: dialogue.conversations.scene.values.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.values.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.values.followup   [17 chars]
    en  Press me further?
    >>  ............................................
    pt  Quer insistir mais?
    >>  ............................................
```


### Button `leave` — "We shall leave the sermon there."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:values.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.values.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.values.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.values.followup.leave   [32 chars]
    en  We shall leave the sermon there.
    >>  ............................................
    pt  Paramos o sermão por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.values.leaving
WHO    VILLAGER — what the player reads after pressing "We shall leave the sermon there."
       spoken on: conversations.scene.values.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.scene.leaving`: the villager accepts. Subject `values.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.values.the_one_i_put_down.respond / leave; conversations.scene.values.what_it_cost_lately.respond / leave; conversations.topic.values.more.respond / leave; conversations.topic.values.open.respond / leave
```

```text
  dialogue.conversations.scene.values.leaving/1   [30 chars]
    en  That is where I stand, anyway.
    >>  ............................................
    pt  É onde eu me firmo, pelo menos.
    >>  ............................................
  dialogue.conversations.scene.values.leaving/2   [26 chars]
    en  Right. Enough sermonising.
    >>  ............................................
    pt  Certo. Chega de sermão.
    >>  ............................................
  dialogue.conversations.scene.values.leaving/3   [29 chars]
    en  You will make your own rules.
    >>  ............................................
    pt  Você vai fazer suas próprias regras.
    >>  ............................................
```

---


## `conversations.scene.values.the_one_i_put_down.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `values`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.values.the_one_i_put_down` — e.g. "I was raised on a rule I no longer keep, and putting it down took longer than learning it did."


```text
POOL   dialogue key: dialogue.conversations.scene.values.the_one_i_put_down.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.values.the_one_i_put_down.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.values.the_one_i_put_down.respond   [24 chars]
    en  One you stopped keeping.
    >>  ............................................
    pt  Uma que você deixou de seguir.
    >>  ............................................
```


### Button `ask_what_changed` — "What changed your reckoning?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `values.the_one_i_put_down.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.the_one_i_put_down.ask_what_changed` — accepted phrasings: "what changed your reckoning"; "what changed your reckoning"; "what made you put it down"
  - the message must contain one of: `reckoning`, `changed`, `down`
  - scored words: `reckoning`(1.8), `changed`(1.8), `down`(1.8), `made`(0.8), `put`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.values.the_one_i_put_down.respond.ask_what_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.values.the_one_i_put_down.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.values.the_one_i_put_down.respond.ask_what_changed   [28 chars]
    en  What changed your reckoning?
    >>  ............................................
    pt  O que mudou sua conta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `values.abandoned`)_
- Does: session `turn`
- Then opens: `conversations.scene.values.followup`
- …where the player's next choices will be: "We shall leave the sermon there."

```text
POOL   dialogue key: dialogue.conversations.scene.values.the_one_i_put_down.explained
WHO    VILLAGER — what the player reads after pressing "What changed your reckoning?"
       spoken on: conversations.scene.values.the_one_i_put_down.respond, button `ask_what_changed`
       leaves the player on: conversations.scene.values.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.the_one_i_put_down.open.explained`: the villager explains. Subject `values.abandoned`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.values.the_one_i_put_down.explained/1   [103 chars]
    en  I watched it hurt somebody who had done nothing, and the rule had no answer for that, so the rule went.
    >>  ............................................
    pt  Vi aquilo machucar alguém que não tinha feito nada, e a regra não tinha resposta para isso, então a regra foi embora.
    >>  ............................................
  dialogue.conversations.scene.values.the_one_i_put_down.explained/2   [107 chars]
    en  I met somebody the rule said I should not trust. I trusted them for four years and was never once wrong to.
    >>  ............................................
    pt  Conheci alguém em quem a regra dizia para não confiar. Confiei por quatro anos e nunca estive errada.
    >>  ............................................
  dialogue.conversations.scene.values.the_one_i_put_down.explained/3   [107 chars]
    en  Nothing dramatic. I simply noticed I had been following it out of habit for a decade and could not say why.
    >>  ............................................
    pt  Nada dramático. Só percebi que vinha seguindo por hábito havia uma década e não sabia dizer por quê.
    >>  ............................................
```


### Button `respect_the_change` — "Putting one down takes more than keeping it."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `values.the_one_i_put_down.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.the_one_i_put_down.respect_the_change` — accepted phrasings: "putting one down takes more than keeping it"; "putting one down takes more than keeping it"; "letting go of one is harder"
  - the message must contain one of: `putting`, `letting`, `keeping`
  - scored words: `putting`(1.8), `letting`(1.8), `keeping`(1.8), `one`(0.8), `takes`(0.8), `more`(0.8), `than`(0.8), `harder`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.values.the_one_i_put_down.respond.respect_the_change
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.values.the_one_i_put_down.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.values.the_one_i_put_down.respond.respect_the_change   [44 chars]
    en  Putting one down takes more than keeping it.
    >>  ............................................
    pt  Largar uma exige mais que mantê-la.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `values.abandoned`)_
- Does: session `turn`
- Then opens: `conversations.scene.values.followup`
- …where the player's next choices will be: "We shall leave the sermon there."

```text
POOL   dialogue key: dialogue.conversations.scene.values.the_one_i_put_down.acknowledged
WHO    VILLAGER — what the player reads after pressing "Putting one down takes more than keeping it."
       spoken on: conversations.scene.values.the_one_i_put_down.respond, button `respect_the_change`
       leaves the player on: conversations.scene.values.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.the_one_i_put_down.open.acknowledged`: the villager accepts. Subject `values.abandoned`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.values.the_one_i_put_down.acknowledged/1   [103 chars]
    en  It does, and nobody praises it, because from outside it looks exactly like having no principles at all.
    >>  ............................................
    pt  Exige, e ninguém elogia, porque de fora parece exatamente não ter princípio nenhum.
    >>  ............................................
  dialogue.conversations.scene.values.the_one_i_put_down.acknowledged/2   [104 chars]
    en  Thank you. The people who taught me would call it drift and I have decided to be able to live with that.
    >>  ............................................
    pt  Obrigada. Quem me ensinou chamaria isso de relaxamento e eu decidi conseguir conviver com isso.
    >>  ............................................
  dialogue.conversations.scene.values.the_one_i_put_down.acknowledged/3   [108 chars]
    en  I keep a note of why I dropped it, so that in ten years I can check whether it was reasoning or convenience.
    >>  ............................................
    pt  Guardo uma anotação do motivo, para que em dez anos eu possa conferir se foi raciocínio ou conveniência.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · answers the beat(s) `values.the_one_i_put_down.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.values.the_one_i_put_down.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.values.the_one_i_put_down.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.values.the_one_i_put_down.respond.leave   [11 chars]
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
POOL   dialogue key: dialogue.conversations.scene.values.leaving
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.scene.values.the_one_i_put_down.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.scene.leaving`: the villager accepts. Subject `values.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.values.followup / leave; conversations.scene.values.what_it_cost_lately.respond / leave; conversations.topic.values.more.respond / leave; conversations.topic.values.open.respond / leave
```

> Written out in full under **`conversations.scene.values.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.values.what_it_cost_lately.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `values`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.values.what_it_cost_lately` — e.g. "It cost me a customer this season, and I would do it again, and I have been quietly cross about it for a month."


```text
POOL   dialogue key: dialogue.conversations.scene.values.what_it_cost_lately.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.values.what_it_cost_lately.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.values.what_it_cost_lately.respond   [17 chars]
    en  What it cost you.
    >>  ............................................
    pt  O que isso te custou.
    >>  ............................................
```


### Button `say_it_was_worth_it` — "A rule that costs nothing isn't one."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `values.what_it_cost_lately.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.what_it_cost_lately.say_it_was_worth_it` — accepted phrasings: "a rule that costs nothing isnt one"; "a rule that costs nothing is worth nothing"; "the cost is what makes it real"
  - the message must contain one of: `costs`, `cost`, `real`
  - scored words: `costs`(1.8), `cost`(1.8), `real`(1.8), `rule`(0.8), `nothing`(0.8), `isnt`(0.8), `one`(0.8), `worth`(0.8), `makes`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.values.what_it_cost_lately.respond.say_it_was_worth_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.values.what_it_cost_lately.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.values.what_it_cost_lately.respond.say_it_was_worth_it   [36 chars]
    en  A rule that costs nothing isn't one.
    >>  ............................................
    pt  Uma regra que não custa nada não é regra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.values.cost.backed`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `values.recent_cost`)_
- Does: session `turn`
- Then opens: `conversations.scene.values.followup`
- …where the player's next choices will be: "We shall leave the sermon there."

```text
POOL   dialogue key: dialogue.conversations.scene.values.what_it_cost_lately.steadied
WHO    VILLAGER — what the player reads after pressing "A rule that costs nothing isn't one."
       spoken on: conversations.scene.values.what_it_cost_lately.respond, button `say_it_was_worth_it`
       leaves the player on: conversations.scene.values.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.what_it_cost_lately.open.steadied`: the villager accepts. Subject `values.recent_cost`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.values.what_it_cost_lately.steadied/1   [104 chars]
    en  That is the sentence I have been saying to myself all month and it lands differently from somebody else.
    >>  ............................................
    pt  É a frase que eu venho dizendo a mim mesma o mês inteiro e ela cai diferente vinda de outra pessoa.
    >>  ............................................
  dialogue.conversations.scene.values.what_it_cost_lately.steadied/2   [97 chars]
    en  Thank you. I did not want to be told I was right. I wanted somebody to know it had a price on it.
    >>  ............................................
    pt  Obrigada. Eu não queria ouvir que estava certa. Queria que alguém soubesse que teve preço.
    >>  ............................................
  dialogue.conversations.scene.values.what_it_cost_lately.steadied/3   [112 chars]
    en  It helps. Not enough to get the customer back, and enough to stop me arguing with myself at four in the morning.
    >>  ............................................
    pt  Ajuda. Não o bastante para trazer o cliente de volta, e o bastante para eu parar de discutir comigo às quatro da manhã.
    >>  ............................................
```


### Button `ask_if_they_would_again` — "Would you do it again?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `values.what_it_cost_lately.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.what_it_cost_lately.ask_if_they_would_again` — accepted phrasings: "would you do it again"; "would you do it again"; "would you make the same choice"
  - the message must contain one of: `again`, `choice`
  - scored words: `again`(1.8), `choice`(1.8), `make`(0.8), `same`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.values.what_it_cost_lately.respond.ask_if_they_would_again
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.values.what_it_cost_lately.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.values.what_it_cost_lately.respond.ask_if_they_would_again   [22 chars]
    en  Would you do it again?
    >>  ............................................
    pt  Você faria de novo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `values.recent_cost`)_
- Does: session `turn`
- Then opens: `conversations.scene.values.followup`
- …where the player's next choices will be: "We shall leave the sermon there."

```text
POOL   dialogue key: dialogue.conversations.scene.values.what_it_cost_lately.answered
WHO    VILLAGER — what the player reads after pressing "Would you do it again?"
       spoken on: conversations.scene.values.what_it_cost_lately.respond, button `ask_if_they_would_again`
       leaves the player on: conversations.scene.values.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.what_it_cost_lately.open.answered`: the villager explains. Subject `values.recent_cost`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.values.what_it_cost_lately.answered/1   [85 chars]
    en  Yes, and more quietly. The rule was right and the volume was me enjoying being right.
    >>  ............................................
    pt  Sim, e mais em silêncio. A regra estava certa e o volume era eu gostando de estar certa.
    >>  ............................................
  dialogue.conversations.scene.values.what_it_cost_lately.answered/2   [89 chars]
    en  Yes. I have asked myself that about ninety times this month and the answer has not moved.
    >>  ............................................
    pt  Sim. Já me perguntei isso umas noventa vezes neste mês e a resposta não mudou.
    >>  ............................................
  dialogue.conversations.scene.values.what_it_cost_lately.answered/3   [118 chars]
    en  Ask me in the winter when the money is short. I would like to say yes and I would rather be honest about the question.
    >>  ............................................
    pt  Me pergunte no inverno, quando o dinheiro estiver curto. Eu gostaria de dizer sim e prefiro ser honesta sobre a pergunta.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · answers the beat(s) `values.what_it_cost_lately.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.values.what_it_cost_lately.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.values.what_it_cost_lately.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.values.what_it_cost_lately.respond.leave   [11 chars]
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
POOL   dialogue key: dialogue.conversations.scene.values.leaving
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.scene.values.what_it_cost_lately.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.scene.leaving`: the villager accepts. Subject `values.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.values.followup / leave; conversations.scene.values.the_one_i_put_down.respond / leave; conversations.topic.values.more.respond / leave; conversations.topic.values.open.respond / leave
```

> Written out in full under **`conversations.scene.values.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.values.more.respond`

**Reached from 2 route(s):** `conversations.topic.values.open.respond` / `ask_the_rule`; `conversations.topic.values.open.respond` / `say_it_holds_up`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.values.open.agreed` — e.g. "It is. A long list is a thing people recite and a short one is a thing they have to live in front of witnesses."
- `conversations.values.open.named` — e.g. "Never make somebody else carry a thing I could have carried. It sounds simple and it costs about a day a week."


```text
POOL   dialogue key: dialogue.conversations.topic.values.more.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.values.more.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.values.more.respond   [19 chars]
    en  Where it came from.
    >>  ............................................
    pt  De onde veio.
    >>  ............................................
```


### Button `ask_about_the_person` — "Who was it you watched?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `values.open.named`, `values.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.more.ask_about_the_person` — accepted phrasings: "who was it you watched"; "who was it you watched"; "tell me about that person"
  - the message must contain one of: `watched`, `person`
  - scored words: `watched`(1.8), `person`(1.8), `who`(0.8), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.values.more.respond.ask_about_the_person
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.values.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.values.more.respond.ask_about_the_person   [23 chars]
    en  Who was it you watched?
    >>  ............................................
    pt  Quem foi que você observou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.values.asked_after`, budget `standard`, replay policy `once`
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `values.where_it_came_from`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.values.more.told
WHO    VILLAGER — what the player reads after pressing "Who was it you watched?"
       spoken on: conversations.topic.values.more.respond, button `ask_about_the_person`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.more.told`: the villager reminisces. Subject `values.where_it_came_from`, polarity `positive`, ends conversation, outcome `engaged`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.values.more.told/1   [98 chars]
    en  A woman who taught me the trade and never once said a word about principles, which is why it took.
    >>  ............................................
    pt  Uma mulher que me ensinou o ofício e nunca disse uma palavra sobre princípios, e é por isso que pegou.
    >>  ............................................
  dialogue.conversations.values.more.told/2   [103 chars]
    en  My father's neighbour, of all people. Four minutes on a Tuesday and I have been carrying it ever since.
    >>  ............................................
    pt  O vizinho do meu pai, de todas as pessoas. Quatro minutos numa terça e eu carrego isso desde então.
    >>  ............................................
  dialogue.conversations.values.more.told/3   [105 chars]
    en  Somebody who is dead now and who never knew, and I have thought about writing that down for their family.
    >>  ............................................
    pt  Alguém que já morreu e nunca soube, e eu já pensei em escrever isso para a família dela.
    >>  ............................................
```


### Button `say_it_shows` — "It shows in how you work."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `values.open.named`, `values.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.more.say_it_shows` — accepted phrasings: "it shows in how you work"; "it shows in how you work"; "that shows in what you do"
  - the message must contain one of: `shows`, `work`
  - scored words: `shows`(1.8), `work`(1.8)

```text
POOL   dialogue key: dialogue.conversations.topic.values.more.respond.say_it_shows
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.values.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.values.more.respond.say_it_shows   [25 chars]
    en  It shows in how you work.
    >>  ............................................
    pt  Dá para ver no seu trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +1  _(recorded under topic `values.where_it_came_from`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.values.more.acknowledged
WHO    VILLAGER — what the player reads after pressing "It shows in how you work."
       spoken on: conversations.topic.values.more.respond, button `say_it_shows`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.more.acknowledged`: the villager accepts. Subject `values.where_it_came_from`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.values.more.acknowledged/1   [88 chars]
    en  That is the only place it is allowed to show. A value you have to announce is a costume.
    >>  ............................................
    pt  É o único lugar em que isso tem permissão de aparecer. Um valor que precisa ser anunciado é fantasia.
    >>  ............................................
  dialogue.conversations.values.more.acknowledged/2   [95 chars]
    en  Thank you. It is the sort of thing you never hear about, because when it works nothing happens.
    >>  ............................................
    pt  Obrigada. É o tipo de coisa sobre a qual nunca se ouve nada, porque quando funciona não acontece nada.
    >>  ............................................
  dialogue.conversations.values.more.acknowledged/3   [97 chars]
    en  I hope so. There are four days a year where I am fairly sure it did not, and I remember all four.
    >>  ............................................
    pt  Espero que sim. Existem quatro dias por ano em que eu tenho quase certeza de que não apareceu, e lembro dos quatro.
    >>  ............................................
```


### Button `brush_it_off` — "That is a long story about a small rule."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `values.open.named`, `values.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.more.brush_it_off` — accepted phrasings: "that is a long story about a small rule"; "that is a long story about a small rule"; "a great deal of story for one rule"
  - the message must contain one of: `story`, `rule`
  - scored words: `story`(1.8), `rule`(1.8), `long`(0.8), `small`(0.8), `great`(0.8), `deal`(0.8), `one`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.values.more.respond.brush_it_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.values.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.values.more.respond.brush_it_off   [40 chars]
    en  That is a long story about a small rule.
    >>  ............................................
    pt  É uma história longa para uma regra pequena.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.values.cut_short`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `values.where_it_came_from`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.values.more.closed
WHO    VILLAGER — what the player reads after pressing "That is a long story about a small rule."
       spoken on: conversations.topic.values.more.respond, button `brush_it_off`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.more.closed`: the villager deflects. Subject `values.where_it_came_from`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.values.more.closed/1   [88 chars]
    en  It is. Small rules take the longest to explain because the reasons are all in the years.
    >>  ............................................
    pt  É. Regras pequenas levam mais tempo para explicar porque os motivos estão todos nos anos.
    >>  ............................................
  dialogue.conversations.values.more.closed/2   [64 chars]
    en  Right. I shall keep the rest of it for somebody who asked twice.
    >>  ............................................
    pt  Certo. Guardo o resto para quem perguntar duas vezes.
    >>  ............................................
  dialogue.conversations.values.more.closed/3   [109 chars]
    en  Fair enough. It is a small rule. It is also the one I have been most consistent about, so it earns the story.
    >>  ............................................
    pt  Tudo bem. É uma regra pequena. Também é aquela em que eu fui mais constante, então merece a história.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · answers the beat(s) `values.open.named`, `values.open.agreed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.values.more.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.values.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.values.more.respond.leave   [11 chars]
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
POOL   dialogue key: dialogue.conversations.scene.values.leaving
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.topic.values.more.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.scene.leaving`: the villager accepts. Subject `values.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.values.followup / leave; conversations.scene.values.the_one_i_put_down.respond / leave; conversations.scene.values.what_it_cost_lately.respond / leave; conversations.topic.values.open.respond / leave
```

> Written out in full under **`conversations.scene.values.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.values.open.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `values`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.values.open` — e.g. "One rule, really. Everything else I do is that rule with different clothes on."


```text
POOL   dialogue key: dialogue.conversations.topic.values.open.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.values.open.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.values.open.respond   [17 chars]
    en  What you hold to.
    >>  ............................................
    pt  Ao que você se apega.
    >>  ............................................
```


### Button `ask_the_rule` — "Name the rule."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `values.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.open.ask_the_rule` — accepted phrasings: "name the rule"; "name the rule"; "what is the rule you keep"
  - the message must contain one of: `rule`
  - scored words: `rule`(1.8), `name`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.values.open.respond.ask_the_rule
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.values.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.values.open.respond.ask_the_rule   [14 chars]
    en  Name the rule.
    >>  ............................................
    pt  Diga qual é a regra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `values.what_i_hold`)_
- Does: session `turn`
- Then opens: `conversations.topic.values.more.respond`
- …where the player's next choices will be: "Who was it you watched?" | "It shows in how you work." | "That is a long story about a small rule." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.values.open.named
WHO    VILLAGER — what the player reads after pressing "Name the rule."
       spoken on: conversations.topic.values.open.respond, button `ask_the_rule`
       leaves the player on: conversations.topic.values.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.open.named`: the villager explains. Subject `values.what_i_hold`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.values.open.named/1   [110 chars]
    en  Never make somebody else carry a thing I could have carried. It sounds simple and it costs about a day a week.
    >>  ............................................
    pt  Nunca fazer outra pessoa carregar o que eu poderia carregar. Parece simples e custa uns um dia por semana.
    >>  ............................................
  dialogue.conversations.values.open.named/2   [116 chars]
    en  Say the difficult sentence on the day, not the week after. I have broken that one four times and regretted all four.
    >>  ............................................
    pt  Dizer a frase difícil no dia, não na semana seguinte. Já quebrei essa quatro vezes e me arrependi das quatro.
    >>  ............................................
  dialogue.conversations.values.open.named/3   [112 chars]
    en  Do the part nobody checks as well as the part they do. That is the whole of it and it took a long time to learn.
    >>  ............................................
    pt  Fazer a parte que ninguém confere tão bem quanto a que conferem. É tudo, e levou muito tempo para aprender.
    >>  ............................................
```


### Button `say_it_holds_up` — "A short list is the honest kind."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `values.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.open.say_it_holds_up` — accepted phrasings: "a short list is the honest kind"; "a short list is the honest kind"; "short lists are the ones people keep"
  - the message must contain one of: `short`, `list`
  - scored words: `short`(1.8), `list`(1.8), `honest`(0.8), `kind`(0.8), `lists`(0.8), `ones`(0.8), `people`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.values.open.respond.say_it_holds_up
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.values.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.values.open.respond.say_it_holds_up   [32 chars]
    en  A short list is the honest kind.
    >>  ............................................
    pt  Uma lista curta é a honesta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2  _(recorded under topic `values.what_i_hold`)_
- Does: session `turn`
- Then opens: `conversations.topic.values.more.respond`
- …where the player's next choices will be: "Who was it you watched?" | "It shows in how you work." | "That is a long story about a small rule." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.values.open.agreed
WHO    VILLAGER — what the player reads after pressing "A short list is the honest kind."
       spoken on: conversations.topic.values.open.respond, button `say_it_holds_up`
       leaves the player on: conversations.topic.values.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.open.agreed`: the villager accepts. Subject `values.what_i_hold`, polarity `positive`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.values.open.agreed/1   [111 chars]
    en  It is. A long list is a thing people recite and a short one is a thing they have to live in front of witnesses.
    >>  ............................................
    pt  É. Lista longa é coisa que se recita e lista curta é coisa que se vive diante de testemunhas.
    >>  ............................................
  dialogue.conversations.values.open.agreed/2   [89 chars]
    en  Mine got shorter every decade, which I used to think was surrender and now think was aim.
    >>  ............................................
    pt  A minha encurtou a cada década, o que eu achava que era rendição e agora acho que era pontaria.
    >>  ............................................
  dialogue.conversations.values.open.agreed/3   [82 chars]
    en  Anybody with eleven principles has never been asked to choose between two of them.
    >>  ............................................
    pt  Quem tem onze princípios nunca precisou escolher entre dois deles.
    >>  ............................................
```


### Button `call_it_posturing` — "Everybody says that until it costs them."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `values.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.values.open.call_it_posturing` — accepted phrasings: "everybody says that until it costs them"; "everybody says that until it costs them"; "talk is cheap until it costs something"
  - the message must contain one of: `costs`, `cheap`
  - scored words: `costs`(1.8), `cheap`(1.8), `everybody`(0.8), `says`(0.8), `until`(0.8), `talk`(0.8), `something`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.values.open.respond.call_it_posturing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.values.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.values.open.respond.call_it_posturing   [40 chars]
    en  Everybody says that until it costs them.
    >>  ............................................
    pt  Todo mundo diz isso até custar caro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.values.dismissed`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `values.what_i_hold`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.values.open.closed
WHO    VILLAGER — what the player reads after pressing "Everybody says that until it costs them."
       spoken on: conversations.topic.values.open.respond, button `call_it_posturing`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.open.closed`: the villager qualifys. Subject `values.what_i_hold`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.values.open.closed/1   [97 chars]
    en  It has cost me, twice, and I am not going to list the occasions to somebody who opened with that.
    >>  ............................................
    pt  Já me custou, duas vezes, e eu não vou listar as ocasiões para quem começou assim.
    >>  ............................................
  dialogue.conversations.values.open.closed/2   [96 chars]
    en  Fair. You will find out which sort I am by watching, and that is the only way anybody finds out.
    >>  ............................................
    pt  Justo. Você vai descobrir de que tipo eu sou observando, e é o único jeito de alguém descobrir.
    >>  ............................................
  dialogue.conversations.values.open.closed/3   [96 chars]
    en  You may be right about most people. I would rather be judged in four years than argued with now.
    >>  ............................................
    pt  Talvez você tenha razão sobre a maioria. Prefiro ser julgada em quatro anos a discutir agora.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · answers the beat(s) `values.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.values.open.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.values.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.values.open.respond.leave   [11 chars]
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
POOL   dialogue key: dialogue.conversations.scene.values.leaving
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.topic.values.open.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.scene.leaving`: the villager accepts. Subject `values.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.values.followup / leave; conversations.scene.values.the_one_i_put_down.respond / leave; conversations.scene.values.what_it_cost_lately.respond / leave; conversations.topic.values.more.respond / leave
```

> Written out in full under **`conversations.scene.values.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

