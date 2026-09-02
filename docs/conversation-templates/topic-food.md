# Topic: food

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `food` |
| Opened from | question `conversations.cat.chitchat`, button `food` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.chitchat` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `restraint`, `curiosity`, `self_disclosure`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.chitchat`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.food
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.chitchat.food   [31 chars]
    en  What's good to eat around here?
    >>  ............................................
    pt  O que é bom pra comer por aqui?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.food.followup`](#conversations-scene-food-followup)
- [`conversations.scene.food.the_fourteenth_time.respond`](#conversations-scene-food-the-fourteenth-time-respond)
- [`conversations.scene.food.whats_on_today.respond`](#conversations-scene-food-whats-on-today-respond)
- [`conversations.topic.food.again.respond`](#conversations-topic-food-again-respond)
- [`conversations.topic.food.debate.followup`](#conversations-topic-food-debate-followup)
- [`conversations.topic.food.normal.respond`](#conversations-topic-food-normal-respond)
- [`conversations.topic.food.pref.followup`](#conversations-topic-food-pref-followup)
- [`conversations.topic.food.recipe.followup`](#conversations-topic-food-recipe-followup)
- [`conversations.topic.food.snubbed.followup`](#conversations-topic-food-snubbed-followup)
- [`conversations.topic.food.toddler.respond`](#conversations-topic-food-toddler-respond)
- [`conversations.topic.food.trait.followup`](#conversations-topic-food-trait-followup)
- [`conversations.topic.food.trait.mocked.followup`](#conversations-topic-food-trait-mocked-followup)
- [`conversations.topic.food.trait.respond`](#conversations-topic-food-trait-respond)
- [`conversations.topic.food.young.respond`](#conversations-topic-food-young-respond)

---

## `conversations.scene.food.followup`

**Reached from 4 route(s):** `conversations.scene.food.the_fourteenth_time.respond` / `ask_how_they_vary_it`; `conversations.scene.food.the_fourteenth_time.respond` / `offer_to_eat_together`; `conversations.scene.food.whats_on_today.respond` / `ask_how_they_make_it`; `conversations.scene.food.whats_on_today.respond` / `say_it_sounds_good`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.food.the_fourteenth_time.accepted` — e.g. "Then I shall make the better version, and you will have to pretend the ordinary one is what I usually manage."
- `conversations.scene.food.the_fourteenth_time.explained` — e.g. "One new thing a week, however small. A different herb, a different shape of the same bread. It is a trick and it works."
- `conversations.scene.food.whats_on_today.explained` — e.g. "Slowly, and with less in it than people expect. Four things done properly beats eleven things thrown in."
- `conversations.scene.food.whats_on_today.pleased` — e.g. "It is better than it sounds, which is the correct direction for a meal to surprise you in."


```text
POOL   dialogue key: dialogue.conversations.scene.food.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.food.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.food.followup   [25 chars]
    en  Anything else about that?
    >>  ............................................
    pt  Mais alguma coisa sobre isso?
    >>  ............................................
```


### Button `leave` — "Enough about food."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:food.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.food.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.food.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.food.followup.leave   [18 chars]
    en  Enough about food.
    >>  ............................................
    pt  Chega de falar de comida.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.food.leaving
WHO    VILLAGER — what the player reads after pressing "Enough about food."
       spoken on: conversations.scene.food.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.scene.leaving`: the villager accepts. Subject `food.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.food.the_fourteenth_time.respond / leave; conversations.scene.food.whats_on_today.respond / leave
```

```text
  dialogue.conversations.scene.food.leaving/1   [27 chars]
    en  Go and eat something, then.
    >>  ............................................
    pt  Então vá comer alguma coisa.
    >>  ............................................
  dialogue.conversations.scene.food.leaving/2   [43 chars]
    en  Right. Do not let it go cold on my account.
    >>  ............................................
    pt  Certo. Não deixe esfriar por minha causa.
    >>  ............................................
  dialogue.conversations.scene.food.leaving/3   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

---


## `conversations.scene.food.the_fourteenth_time.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `food`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.food.the_fourteenth_time` — e.g. "The same four things in a different order, which after a while stops being a different order."


```text
POOL   dialogue key: dialogue.conversations.scene.food.the_fourteenth_time.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.food.the_fourteenth_time.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.food.the_fourteenth_time.respond   [14 chars]
    en  Winter eating.
    >>  ............................................
    pt  A comida de inverno.
    >>  ............................................
```


### Button `ask_how_they_vary_it` — "How do you keep it interesting?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `food.the_fourteenth_time.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.food.the_fourteenth_time.ask_how_they_vary_it` — accepted phrasings: "how do you keep it interesting"; "how do you keep it interesting"; "what do you do to vary it"
  - the message must contain one of: `interesting`, `vary`
  - scored words: `interesting`(1.8), `vary`(1.8), `keep`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.food.the_fourteenth_time.respond.ask_how_they_vary_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.food.the_fourteenth_time.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.food.the_fourteenth_time.respond.ask_how_they_vary_it   [31 chars]
    en  How do you keep it interesting?
    >>  ............................................
    pt  Como você mantém isso interessante?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `food.winter_store`)_
- Does: session `turn`
- Then opens: `conversations.scene.food.followup`
- …where the player's next choices will be: "Enough about food."

```text
POOL   dialogue key: dialogue.conversations.scene.food.the_fourteenth_time.explained
WHO    VILLAGER — what the player reads after pressing "How do you keep it interesting?"
       spoken on: conversations.scene.food.the_fourteenth_time.respond, button `ask_how_they_vary_it`
       leaves the player on: conversations.scene.food.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.the_fourteenth_time.open.explained`: the villager explains. Subject `food.winter_store`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:food` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.food.the_fourteenth_time.explained/1   [119 chars]
    en  One new thing a week, however small. A different herb, a different shape of the same bread. It is a trick and it works.
    >>  ............................................
    pt  Uma coisa nova por semana, por menor que seja. Outra erva, outro formato do mesmo pão. É um truque e funciona.
    >>  ............................................
  dialogue.conversations.scene.food.the_fourteenth_time.explained/2   [123 chars]
    en  I stopped trying. Instead I made one day a week better than the others, and the other six became a wait rather than a slog.
    >>  ............................................
    pt  Parei de tentar. Em vez disso, fiz um dia da semana melhor que os outros, e os outros seis viraram espera em vez de arrasto.
    >>  ............................................
  dialogue.conversations.scene.food.the_fourteenth_time.explained/3   [110 chars]
    en  Company. The same food eaten with somebody is not the same food, and I would not have believed that at twenty.
    >>  ............................................
    pt  Companhia. A mesma comida comida com alguém não é a mesma comida, e eu não acreditaria nisso aos vinte.
    >>  ............................................
```


### Button `offer_to_eat_together` — "I'll eat with you one evening."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `food.the_fourteenth_time.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.food.the_fourteenth_time.offer_to_eat_together` — accepted phrasings: "ill eat with you one evening"; "i will eat with you one evening"; "let me join you for a meal"
  - the message must contain one of: `eat`, `join`, `meal`
  - scored words: `eat`(1.8), `join`(1.8), `meal`(1.8), `ill`(0.8), `one`(0.8), `evening`(0.8), `let`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.food.the_fourteenth_time.respond.offer_to_eat_together
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.food.the_fourteenth_time.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.food.the_fourteenth_time.respond.offer_to_eat_together   [30 chars]
    en  I'll eat with you one evening.
    >>  ............................................
    pt  Eu janto com você uma noite dessas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.food.winter.shared`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, familiarity +2  _(recorded under topic `food.winter_store`)_
- Does: session `turn`
- Then opens: `conversations.scene.food.followup`
- …where the player's next choices will be: "Enough about food."

```text
POOL   dialogue key: dialogue.conversations.scene.food.the_fourteenth_time.accepted
WHO    VILLAGER — what the player reads after pressing "I'll eat with you one evening."
       spoken on: conversations.scene.food.the_fourteenth_time.respond, button `offer_to_eat_together`
       leaves the player on: conversations.scene.food.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.the_fourteenth_time.open.accepted`: the villager accepts. Subject `food.winter_store`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:food` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.food.the_fourteenth_time.accepted/1   [109 chars]
    en  Then I shall make the better version, and you will have to pretend the ordinary one is what I usually manage.
    >>  ............................................
    pt  Então eu faço a versão melhor, e você vai ter que fingir que a comum é o que eu costumo conseguir.
    >>  ............................................
  dialogue.conversations.scene.food.the_fourteenth_time.accepted/2   [94 chars]
    en  Yes. Bring nothing. People always bring something and then the evening is about the something.
    >>  ............................................
    pt  Sim. Não traga nada. As pessoas sempre trazem algo e aí a noite fica sendo sobre esse algo.
    >>  ............................................
  dialogue.conversations.scene.food.the_fourteenth_time.accepted/3   [121 chars]
    en  That is the best offer I have had this month, and I want you to know that says more about the month than about the offer.
    >>  ............................................
    pt  É a melhor oferta que eu recebi este mês, e quero que você saiba que isso diz mais do mês do que da oferta.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · answers the beat(s) `food.the_fourteenth_time.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.food.the_fourteenth_time.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.food.the_fourteenth_time.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.food.the_fourteenth_time.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Vou deixar você nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.food.leaving
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.scene.food.the_fourteenth_time.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.scene.leaving`: the villager accepts. Subject `food.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.food.followup / leave; conversations.scene.food.whats_on_today.respond / leave
```

> Written out in full under **`conversations.scene.food.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.food.whats_on_today.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `food`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.food.whats_on_today` — e.g. "Something with the last of the roots in it, which is a polite way of describing what it is."


```text
POOL   dialogue key: dialogue.conversations.scene.food.whats_on_today.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.food.whats_on_today.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.food.whats_on_today.respond   [13 chars]
    en  Today's food.
    >>  ............................................
    pt  A comida de hoje.
    >>  ............................................
```


### Button `ask_how_they_make_it` — "How do you make that?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `food.whats_on_today.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.food.whats_on_today.ask_how_they_make_it` — accepted phrasings: "how do you make that"; "how do you make that"; "what goes into it"
  - the message must contain one of: `make`, `goes`
  - scored words: `make`(1.8), `goes`(1.8), `into`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.food.whats_on_today.respond.ask_how_they_make_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.food.whats_on_today.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.food.whats_on_today.respond.ask_how_they_make_it   [21 chars]
    en  How do you make that?
    >>  ............................................
    pt  Como você faz isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `food.today`)_
- Does: session `turn`
- Then opens: `conversations.scene.food.followup`
- …where the player's next choices will be: "Enough about food."

```text
POOL   dialogue key: dialogue.conversations.scene.food.whats_on_today.explained
WHO    VILLAGER — what the player reads after pressing "How do you make that?"
       spoken on: conversations.scene.food.whats_on_today.respond, button `ask_how_they_make_it`
       leaves the player on: conversations.scene.food.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.whats_on_today.open.explained`: the villager explains. Subject `food.today`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:food` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.food.whats_on_today.explained/1   [104 chars]
    en  Slowly, and with less in it than people expect. Four things done properly beats eleven things thrown in.
    >>  ............................................
    pt  Devagar, e com menos coisas do que as pessoas imaginam. Quatro ingredientes bem feitos vencem onze jogados dentro.
    >>  ............................................
  dialogue.conversations.scene.food.whats_on_today.explained/2   [118 chars]
    en  The trick is salting it at the start and again at the end, and nobody believes me until they have eaten both versions.
    >>  ............................................
    pt  O truque é salgar no começo e de novo no fim, e ninguém acredita em mim até ter comido as duas versões.
    >>  ............................................
  dialogue.conversations.scene.food.whats_on_today.explained/3   [108 chars]
    en  Badly, by anybody's standard but mine. It tastes like my mother's, which is the only measure I actually use.
    >>  ............................................
    pt  Mal, pelo padrão de qualquer um menos o meu. Tem o gosto da comida da minha mãe, e é a única medida que eu de fato uso.
    >>  ............................................
```


### Button `say_it_sounds_good` — "That sounds good."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `food.whats_on_today.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.food.whats_on_today.say_it_sounds_good` — accepted phrasings: "that sounds good"; "that sounds good"; "sounds like a decent meal"
  - the message must contain one of: `sounds`, `meal`
  - scored words: `sounds`(1.8), `meal`(1.8), `good`(0.8), `like`(0.8), `decent`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.food.whats_on_today.respond.say_it_sounds_good
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.food.whats_on_today.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.food.whats_on_today.respond.say_it_sounds_good   [17 chars]
    en  That sounds good.
    >>  ............................................
    pt  Isso parece bom.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `food.today`)_
- Does: session `turn`
- Then opens: `conversations.scene.food.followup`
- …where the player's next choices will be: "Enough about food."

```text
POOL   dialogue key: dialogue.conversations.scene.food.whats_on_today.pleased
WHO    VILLAGER — what the player reads after pressing "That sounds good."
       spoken on: conversations.scene.food.whats_on_today.respond, button `say_it_sounds_good`
       leaves the player on: conversations.scene.food.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.whats_on_today.open.pleased`: the villager accepts. Subject `food.today`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:food` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.food.whats_on_today.pleased/1   [90 chars]
    en  It is better than it sounds, which is the correct direction for a meal to surprise you in.
    >>  ............................................
    pt  É melhor do que parece, que é a direção certa para uma refeição surpreender.
    >>  ............................................
  dialogue.conversations.scene.food.whats_on_today.pleased/2   [105 chars]
    en  Come by at the hour and there is always more than one person's worth. There always is, whatever I intend.
    >>  ............................................
    pt  Passe na hora e sempre tem mais do que dá para uma pessoa. Sempre tem, por mais que eu planeje.
    >>  ............................................
  dialogue.conversations.scene.food.whats_on_today.pleased/3   [92 chars]
    en  Thank you. Nobody says anything about food unless it is wrong, so that is genuinely unusual.
    >>  ............................................
    pt  Obrigada. Ninguém comenta comida a não ser que esteja errada, então isso é genuinamente incomum.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · answers the beat(s) `food.whats_on_today.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.food.whats_on_today.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.food.whats_on_today.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.food.whats_on_today.respond.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Vou deixar você nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.food.leaving
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.scene.food.whats_on_today.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.scene.leaving`: the villager accepts. Subject `food.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.food.followup / leave; conversations.scene.food.the_fourteenth_time.respond / leave
```

> Written out in full under **`conversations.scene.food.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.food.again.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `food`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.again` — e.g. "Still hungry from the last time you asked, honestly."


```text
POOL   dialogue key: dialogue.conversations.topic.food.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.again.respond   [21 chars]
    en  We just did this one.
    >>  ............................................
    pt  A gente acabou de falar disso.
    >>  ............................................
```


### Button `apologize` — "Sorry — we covered this."

*stance family `candor` · tone `gentle` · answers the beat(s) `food.again.to.food.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.again.apologize` — accepted phrasings: "sorry, we covered this"; "sorry, asked already"; "my mistake, we did this"
  - the message must contain one of: `covered`, `sorry`, `already`
  - scored words: `covered`(1.5), `sorry`(1.2), `already`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.again.respond.apologize   [24 chars]
    en  Sorry — we covered this.
    >>  ............................................
    pt  Desculpa — a gente já falou disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `food.again.apologize`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — we covered this."
       spoken on: conversations.topic.food.again.respond, button `apologize`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.again.apologize.terminal`: the villager accepts. Subject `food.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.again.apologize/1   [59 chars]
    en  We did. Doesn't matter — I could talk about supper all day.
    >>  ............................................
    pt  Já. Não importa — eu falaria de jantar o dia todo.
    >>  ............................................
  dialogue.conversations.food.again.apologize/2   [41 chars]
    en  Twice is fine for this subject, honestly.
    >>  ............................................
    pt  Duas vezes é aceitável para esse assunto, sinceramente.
    >>  ............................................
  dialogue.conversations.food.again.apologize/3   [55 chars]
    en  No harm. Ask me at mealtime and I'll have new opinions.
    >>  ............................................
    pt  Sem problema. Me pergunte na hora da refeição e terei opiniões novas.
    >>  ............................................
```


### Button `press` — "Humour me. What are you craving?"

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `food.again.to.food.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.again.press` — accepted phrasings: "what are you craving"; "humour me"; "tell me anyway"
  - the message must contain one of: `craving`, `humour`, `anyway`
  - scored words: `craving`(1.5), `humour`(1.5), `anyway`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.again.respond.press   [32 chars]
    en  Humour me. What are you craving?
    >>  ............................................
    pt  Me faz a vontade. Do que você está com vontade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `food.again.press`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `food.again.press`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.again.press
WHO    VILLAGER — what the player reads after pressing "Humour me. What are you craving?"
       spoken on: conversations.topic.food.again.respond, button `press`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.again.press.terminal`: the villager resists. Subject `food.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.again.press/1   [55 chars]
    en  Same as an hour ago, %1$s, and now I'm hungry about it.
    >>  ............................................
    pt  O mesmo de uma hora atrás, %1$s, e agora estou com fome por causa disso.
    >>  ............................................
  dialogue.conversations.food.again.press/2   [54 chars]
    en  You've made me think about bread again. That's on you.
    >>  ............................................
    pt  Você me fez pensar em pão de novo. A culpa é sua.
    >>  ............................................
  dialogue.conversations.food.again.press/3   [47 chars]
    en  ...Bread. It's always bread. Are you satisfied?
    >>  ............................................
    pt  ...Pão. É sempre pão. Está satisfeito?
    >>  ............................................
```


### Button `leave` — "Fair. Never mind."

*stance family `exit` · tone `plain` · answers the beat(s) `food.again.to.food.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.again.respond.leave   [17 chars]
    en  Fair. Never mind.
    >>  ............................................
    pt  Justo. Deixa para lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Never mind."
       spoken on: conversations.topic.food.again.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.again.leave.terminal`: the villager accepts. Subject `food.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.again.leave/1   [22 chars]
    en  Aye. Go eat something.
    >>  ............................................
    pt  Tá. Vá comer algo.
    >>  ............................................
  dialogue.conversations.food.again.leave/2   [14 chars]
    en  Right you are.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
  dialogue.conversations.food.again.leave/3   [34 chars]
    en  Later, then. Preferably at supper.
    >>  ............................................
    pt  Depois, então. De preferência no jantar.
    >>  ............................................
```

---


## `conversations.topic.food.debate.followup`

**Reached from 2 route(s):** `conversations.topic.food.normal.respond` / `disagree`; `conversations.topic.food.normal.respond` / `disagree`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.normal.disagree.landed` — e.g. "Would you now. Go on then — defend your position."
- `conversations.food.normal.disagree.polite` — e.g. "Everyone does. That's half the fun of the subject."


```text
POOL   dialogue key: dialogue.conversations.topic.food.debate.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.debate.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.debate.followup   [25 chars]
    en  Go on, then. Convince me.
    >>  ............................................
    pt  Então vai. Me convença.
    >>  ............................................
```


### Button `make_case` — "Mine's better, and here's why."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `food.debate.invited`, `food.debate.allowed` · offered only once the villager has actually said `debate:open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.debate.make_case` — accepted phrasings: "mine is better and here is why"; "let me make my case"; "i can argue that"
  - the message must contain one of: `better`, `argue`, `case`
  - scored words: `better`(1.2), `argue`(1.2), `case`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.debate.followup.make_case
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.debate.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.debate.followup.make_case   [30 chars]
    en  Mine's better, and here's why.
    >>  ............................................
    pt  A minha é melhor, e eu explico por quê.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.debate.make_case`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +2, respect +3  _(recorded under topic `food.debate.make_case`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.debate.make_case
WHO    VILLAGER — what the player reads after pressing "Mine's better, and here's why."
       spoken on: conversations.topic.food.debate.followup, button `make_case`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.debate.make_case`: the villager accepts. Subject `food.preference`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.debate.make_case/1   [72 chars]
    en  ...Huh. That's a better argument than I was ready for. I'll consider it.
    >>  ............................................
    pt  ...Huh. É um argumento melhor do que eu estava preparado. Vou considerar.
    >>  ............................................
  dialogue.conversations.food.debate.make_case/2   [77 chars]
    en  You've thought about this more than a reasonable person would, %1$s. Respect.
    >>  ............................................
    pt  Você pensou nisso mais do que uma pessoa razoável pensaria, %1$s. Respeito.
    >>  ............................................
  dialogue.conversations.food.debate.make_case/3   [50 chars]
    en  Wrong, but thoroughly wrong. That's the good kind.
    >>  ............................................
    pt  Errado, mas minuciosamente errado. Desse tipo eu gosto.
    >>  ............................................
```


### Button `concede` — "...Fine. You may have a point."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `food.debate.invited`, `food.debate.allowed` · offered only once the villager has actually said `debate:open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.debate.concede` — accepted phrasings: "fine, you may have a point"; "alright, you have a point"; "i concede that"
  - the message must contain one of: `fine`, `point`, `concede`
  - scored words: `fine`(1.0), `point`(1.5), `concede`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.debate.followup.concede
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.debate.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.debate.followup.concede   [30 chars]
    en  ...Fine. You may have a point.
    >>  ............................................
    pt  ...Tá. Você pode ter razão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.debate.concede`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `food.debate.concede`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.debate.concede
WHO    VILLAGER — what the player reads after pressing "...Fine. You may have a point."
       spoken on: conversations.topic.food.debate.followup, button `concede`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.debate.concede`: the villager celebrates. Subject `food.preference`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.debate.concede/1   [73 chars]
    en  Ha! Write that down. Nobody in this village concedes anything about food.
    >>  ............................................
    pt  Ha! Anote isso. Ninguém neste vilarejo cede nada sobre comida.
    >>  ............................................
  dialogue.conversations.food.debate.concede/2   [64 chars]
    en  A point, she says. I'll take the whole victory, %1$s, thank you.
    >>  ............................................
    pt  Razão, ele diz. Vou levar a vitória inteira, %1$s, obrigado.
    >>  ............................................
  dialogue.conversations.food.debate.concede/3   [55 chars]
    en  Good. Now we can both be right about the bread instead.
    >>  ............................................
    pt  Bom. Agora a gente pode estar certo os dois sobre o pão.
    >>  ............................................
```


### Button `why_care` — "Why does anyone argue about food?"

*stance family `curiosity` · tone `playful` · outcome `engaged` · answers the beat(s) `food.debate.invited`, `food.debate.allowed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.debate.why_care` — accepted phrasings: "why does anyone argue about food"; "why do people argue about food"; "why bother arguing about it"
  - the message must contain one of: `argue`, `anyone`, `bother`
  - scored words: `argue`(1.5), `anyone`(1.0), `bother`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.food.debate.followup.why_care
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.debate.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.debate.followup.why_care   [33 chars]
    en  Why does anyone argue about food?
    >>  ............................................
    pt  Por que alguém discute sobre comida?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `food.debate.why_care`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.debate.why_care
WHO    VILLAGER — what the player reads after pressing "Why does anyone argue about food?"
       spoken on: conversations.topic.food.debate.followup, button `why_care`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.debate.why_care`: the villager explains. Subject `food.preference`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.debate.why_care/1   [76 chars]
    en  Because it's the only argument where everyone's an expert and nobody's hurt.
    >>  ............................................
    pt  Porque é a única discussão em que todo mundo é especialista e ninguém se machuca.
    >>  ............................................
  dialogue.conversations.food.debate.why_care/2   [67 chars]
    en  What else would we argue about, %1$s? The weather does as it likes.
    >>  ............................................
    pt  Sobre o que mais a gente discutiria, %1$s? O tempo faz o que quer.
    >>  ............................................
  dialogue.conversations.food.debate.why_care/3   [61 chars]
    en  Because you can settle it at a table. Try that with politics.
    >>  ............................................
    pt  Porque dá pra resolver numa mesa. Tente isso com política.
    >>  ............................................
```


### Button `leave` — "I'll let you get to your supper."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `food.debate.invited`, `food.debate.allowed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.debate.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.debate.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.debate.followup.leave   [32 chars]
    en  I'll let you get to your supper.
    >>  ............................................
    pt  Vou deixar você ir jantar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.normal.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to your supper."
       spoken on: conversations.topic.food.debate.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.left`: the villager accepts. Subject `food.preference`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.food.normal.respond / leave; conversations.topic.food.pref.followup / leave; conversations.topic.food.recipe.followup / leave; conversations.topic.food.snubbed.followup / leave
```

```text
  dialogue.conversations.food.normal.leave/1   [47 chars]
    en  So it is, off you go before we both get hungry.
    >>  ............................................
    pt  É assim, pode ir antes que a gente fique com fome.
    >>  ............................................
  dialogue.conversations.food.normal.leave/2   [48 chars]
    en  Right. Mind the bakery queue at this hour, %1$s.
    >>  ............................................
    pt  Certo. Cuidado com a fila da padaria a essa hora, %1$s.
    >>  ............................................
  dialogue.conversations.food.normal.leave/3   [28 chars]
    en  Go on. Eat something for me.
    >>  ............................................
    pt  Pode ir. Coma alguma coisa por mim.
    >>  ............................................
```

---


## `conversations.topic.food.normal.respond`

**Reached from 2 route(s):** `conversations.cat.chitchat` / `food`; `conversations.cat.chitchat` / `food`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.first` — e.g. "The baker's rye, fresh, with too much butter. That's the correct answer and I'll die on it."
- `conversations.food.revisit` — e.g. "I thought about what you asked — I tried the mushroom thing. I was wrong about mushrooms."


```text
POOL   dialogue key: dialogue.conversations.topic.food.normal.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.normal.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.normal.respond   [39 chars]
    en  That's what I'd pick, given the choice.
    >>  ............................................
    pt  É o que eu escolheria, se pudesse.
    >>  ............................................
```


### Button `share_pref` — "Mine's plainer than that, I'll admit."

*stance family `self_disclosure` · tone `plain` · outcome `engaged` · answers the beat(s) `food.first_choice`, `food.revisited` · offered only once the villager has actually said `food:dish_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.normal.share_pref` — accepted phrasings: "mine is plainer than that"; "my taste is simple"; "mine is simpler"
  - the message must contain one of: `plainer`, `mine`, `simple`
  - scored words: `plainer`(1.5), `mine`(1.2), `simple`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.food.normal.respond.share_pref
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.normal.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.normal.respond.share_pref   [37 chars]
    en  Mine's plainer than that, I'll admit.
    >>  ............................................
    pt  O meu é mais simples que isso, admito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the mood is `fine`
- Does: **hearts +1** — decision id `food.normal.share_pref`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `food.normal.share_pref`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.pref.followup`
- …where the player's next choices will be: "You've got good taste." | "I'll bring you some." | "And the one thing you won't touch?" | "I'll let you get to your supper."

```text
POOL   dialogue key: dialogue.conversations.food.agree.fine
WHO    VILLAGER — what the player reads after pressing "Mine's plainer than that, I'll admit."
       spoken on: conversations.topic.food.normal.respond, button `share_pref`
       leaves the player on: conversations.topic.food.pref.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.pref.plain_is_fine`: the villager accepts. Subject `food.preference`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `food:dish_named`, `preference:plain` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.agree.fine/1   [67 chars]
    en  So I've found. It's food. Some days that's the whole of the review.
    >>  ............................................
    pt  Foi o que eu vi. É comida. Em alguns dias essa é a crítica inteira.
    >>  ............................................
  dialogue.conversations.food.agree.fine/2   [60 chars]
    en  It does the job. Not everything has to be an occasion, %1$s.
    >>  ............................................
    pt  Cumpre o papel. Nem tudo precisa ser um acontecimento, %1$s.
    >>  ............................................
  dialogue.conversations.food.agree.fine/3   [72 chars]
    en  Perfectly ordinary and perfectly sufficient. I'll not pretend otherwise.
    >>  ............................................
    pt  Perfeitamente comum e perfeitamente suficiente. Não vou fingir o contrário.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the mood is `fine`  _(chance -2000)_
- Does: **hearts +1** — decision id `food.normal.share_pref`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `food.normal.share_pref`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.pref.followup`
- …where the player's next choices will be: "You've got good taste." | "I'll bring you some." | "And the one thing you won't touch?" | "I'll let you get to your supper."

```text
POOL   dialogue key: dialogue.conversations.food.normal.share_pref
WHO    VILLAGER — what the player reads after pressing "Mine's plainer than that, I'll admit."
       spoken on: conversations.topic.food.normal.respond, button `share_pref`
       leaves the player on: conversations.topic.food.pref.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.pref.shared`: the villager accepts. Subject `food.preference`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `food:dish_named`, `preference:plain` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.normal.share_pref/1   [74 chars]
    en  Plain's underrated. Plain is what you eat every day, and every day counts.
    >>  ............................................
    pt  Simples é subestimado. Simples é o que se come todo dia, e todo dia conta.
    >>  ............................................
  dialogue.conversations.food.normal.share_pref/2   [72 chars]
    en  Then you'd get on with my mother. She thought seasoning was showing off.
    >>  ............................................
    pt  Então você se daria bem com a minha mãe. Ela achava que tempero era exibição.
    >>  ............................................
  dialogue.conversations.food.normal.share_pref/3   [68 chars]
    en  Nothing wrong with plain, %1$s. Half the village pretends otherwise.
    >>  ............................................
    pt  Nada de errado com simples, %1$s. Metade da vila finge o contrário.
    >>  ............................................
```


### Button `ask_recipe` — "How do you make it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `food.first_choice`, `food.revisited` · offered only once the villager has actually said `food:dish_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.normal.ask_recipe` — accepted phrasings: "how do you make it"; "how do you cook it"; "what is the recipe"
  - the message must contain one of: `make`, `cook`, `recipe`
  - scored words: `make`(1.5), `cook`(1.5), `recipe`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.normal.respond.ask_recipe
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.normal.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.normal.respond.ask_recipe   [19 chars]
    en  How do you make it?
    >>  ............................................
    pt  Como você faz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the season is `summer`
- Fires when: RULED OUT when the `seasons` feature is OFF  _(chance -2000)_
- Does: disposition — respect +2, familiarity +1  _(recorded under topic `food.normal.ask_recipe`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.recipe.followup`
- …where the player's next choices will be: "Where do you get it?" | "I'll try it that way." | "You make it sound worth the trouble." | "I'll let you get to your supper."

```text
POOL   dialogue key: dialogue.conversations.food.ask_recipe.season
WHO    VILLAGER — what the player reads after pressing "How do you make it?"
       spoken on: conversations.topic.food.normal.respond, button `ask_recipe`
       leaves the player on: conversations.topic.food.recipe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = season
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.recipe.seasonal`: the villager explains. Subject `food.preference`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `food:dish_named`, `food:method_told` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, self_disclosure, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.ask_recipe.season/1   [78 chars]
    en  In %2$s? Whatever's ripe, barely cooked, eaten standing up. That's the recipe.
    >>  ............................................
    pt  No %2$s? O que estiver maduro, mal cozido, comido de pé. É essa a receita.
    >>  ............................................
  dialogue.conversations.food.ask_recipe.season/2   [82 chars]
    en  There's no recipe this time of year, %1$s. There's just what came in that morning.
    >>  ............................................
    pt  Não tem receita nesta época, %1$s. Tem o que chegou de manhã.
    >>  ............................................
  dialogue.conversations.food.ask_recipe.season/3   [67 chars]
    en  The trick is to do less to it. Everything's already good right now.
    >>  ............................................
    pt  O truque é fazer menos. Tudo já está bom agora.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the season is `summer`  _(chance -2000)_
- Does: disposition — respect +2, familiarity +1  _(recorded under topic `food.normal.ask_recipe`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.recipe.followup`
- …where the player's next choices will be: "Where do you get it?" | "I'll try it that way." | "You make it sound worth the trouble." | "I'll let you get to your supper."

```text
POOL   dialogue key: dialogue.conversations.food.normal.ask_recipe
WHO    VILLAGER — what the player reads after pressing "How do you make it?"
       spoken on: conversations.topic.food.normal.respond, button `ask_recipe`
       leaves the player on: conversations.topic.food.recipe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.recipe.told`: the villager explains. Subject `food.preference`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `food:dish_named`, `food:method_told` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, self_disclosure, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.normal.ask_recipe/1   [55 chars]
    en  Badly, is how. But I'll tell you the version I aim for.
    >>  ............................................
    pt  Mal, é como. Mas te conto a versão que eu tento fazer.
    >>  ............................................
  dialogue.conversations.food.normal.ask_recipe/2   [71 chars]
    en  Ha. You'd have to stand in my kitchen and watch. It doesn't write down.
    >>  ............................................
    pt  Rá. Você teria que ficar na minha cozinha e olhar. Não dá para escrever.
    >>  ............................................
  dialogue.conversations.food.normal.ask_recipe/3   [51 chars]
    en  Slowly, and with more butter than anyone admits to.
    >>  ............................................
    pt  Devagar, e com mais manteiga do que qualquer um admite.
    >>  ............................................
```


### Button `disagree` — "I'd argue with that, honestly."

*stance family `respectful_disagreement` · tone `blunt` · outcome `resisted` · answers the beat(s) `food.first_choice`, `food.revisited` · offered only once the villager has actually said `food:dish_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.normal.disagree` — accepted phrasings: "i would argue with that"; "i disagree"; "you are wrong about that"
  - the message must contain one of: `argue`, `disagree`, `wrong`
  - scored words: `argue`(1.5), `disagree`(1.5), `wrong`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.normal.respond.disagree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.normal.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.normal.respond.disagree   [30 chars]
    en  I'd argue with that, honestly.
    >>  ............................................
    pt  Eu discordaria disso, sinceramente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`
- Does: **hearts +1** — decision id `food.normal.disagree`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `food.normal.disagree`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.debate.followup`
- …where the player's next choices will be: "Mine's better, and here's why." | "...Fine. You may have a point." | "Why does anyone argue about food?" | "I'll let you get to your supper."

```text
POOL   dialogue key: dialogue.conversations.food.normal.disagree.landed
WHO    VILLAGER — what the player reads after pressing "I'd argue with that, honestly."
       spoken on: conversations.topic.food.normal.respond, button `disagree`
       leaves the player on: conversations.topic.food.debate.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.debate.invited`: the villager asks. Subject `food.preference`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `food:dish_named`, `debate:open` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, humor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.normal.disagree.landed/1   [49 chars]
    en  Would you now. Go on then — defend your position.
    >>  ............................................
    pt  Ah é? Vai lá então — defenda sua posição.
    >>  ............................................
  dialogue.conversations.food.normal.disagree.landed/2   [66 chars]
    en  Finally, someone with an opinion. Everyone else just nods at food.
    >>  ............................................
    pt  Finalmente, alguém com opinião. O resto só concorda com a cabeça sobre comida.
    >>  ............................................
  dialogue.conversations.food.normal.disagree.landed/3   [40 chars]
    en  Ha! Wrong, but I respect the conviction.
    >>  ............................................
    pt  Rá! Errado, mas respeito a convicção.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`
- Does: **hearts -1** — decision id `food.normal.disagree`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `food.normal.disagree`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.snubbed.followup`
- …where the player's next choices will be: "You're right. It's only food." | "I only meant I liked mine too." | "Fair. I'll say no more about it." | "I'll let you get to your supper."

```text
POOL   dialogue key: dialogue.conversations.food.normal.disagree.flat
WHO    VILLAGER — what the player reads after pressing "I'd argue with that, honestly."
       spoken on: conversations.topic.food.normal.respond, button `disagree`
       leaves the player on: conversations.topic.food.snubbed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.debate.unwanted`: the villager resists. Subject `food.preference`, polarity `negative`, closes subject, outcome `resisted`.
NOTE   this is the line that establishes `food:dish_named`, `player:pressed_the_point` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.normal.disagree.flat/1   [59 chars]
    en  ...It's only food, %1$s. It didn't need a counter-argument.
    >>  ............................................
    pt  ...É só comida, %1$s. Não precisava de contra-argumento.
    >>  ............................................
  dialogue.conversations.food.normal.disagree.flat/2   [58 chars]
    en  Right. Well. I'll keep my preferences to myself next time.
    >>  ............................................
    pt  Certo. Bom. Da próxima vou guardar minhas preferências para mim.
    >>  ............................................
  dialogue.conversations.food.normal.disagree.flat/3   [28 chars]
    en  Must everything be a debate?
    >>  ............................................
    pt  Tudo precisa ser um debate?
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `food.normal.disagree`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.debate.followup`
- …where the player's next choices will be: "Mine's better, and here's why." | "...Fine. You may have a point." | "Why does anyone argue about food?" | "I'll let you get to your supper."

```text
POOL   dialogue key: dialogue.conversations.food.normal.disagree.polite
WHO    VILLAGER — what the player reads after pressing "I'd argue with that, honestly."
       spoken on: conversations.topic.food.normal.respond, button `disagree`
       leaves the player on: conversations.topic.food.debate.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.debate.allowed`: the villager accepts. Subject `food.preference`, polarity `neutral`, invites followup, outcome `accepted`.
NOTE   this is the line that establishes `food:dish_named`, `debate:open` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, humor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.normal.disagree.polite/1   [50 chars]
    en  Everyone does. That's half the fun of the subject.
    >>  ............................................
    pt  Todo mundo discorda. É metade da graça do assunto.
    >>  ............................................
  dialogue.conversations.food.normal.disagree.polite/2   [60 chars]
    en  You'd be in good company. The whole village argues about it.
    >>  ............................................
    pt  Você teria boa companhia. A vila inteira discute isso.
    >>  ............................................
  dialogue.conversations.food.normal.disagree.polite/3   [36 chars]
    en  Fair enough. Wrong, but fair enough.
    >>  ............................................
    pt  Justo. Errado, mas justo.
    >>  ............................................
```


### Button `leave` — "Now I'm hungry. I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `food.first_choice`, `food.revisited` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.normal.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.normal.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.normal.respond.leave   [24 chars]
    en  Now I'm hungry. I'll go.
    >>  ............................................
    pt  Agora estou com fome. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.normal.leave
WHO    VILLAGER — what the player reads after pressing "Now I'm hungry. I'll go."
       spoken on: conversations.topic.food.normal.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.left`: the villager accepts. Subject `food.preference`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.food.debate.followup / leave; conversations.topic.food.pref.followup / leave; conversations.topic.food.recipe.followup / leave; conversations.topic.food.snubbed.followup / leave
```

> Written out in full under **`conversations.topic.food.debate.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.food.pref.followup`

**Reached from 2 route(s):** `conversations.topic.food.normal.respond` / `share_pref`; `conversations.topic.food.normal.respond` / `share_pref`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.agree.fine` — e.g. "So I've found. It's food. Some days that's the whole of the review."
- `conversations.food.normal.share_pref` — e.g. "Plain's underrated. Plain is what you eat every day, and every day counts."


```text
POOL   dialogue key: dialogue.conversations.topic.food.pref.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.pref.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.pref.followup   [37 chars]
    en  Food's food, but some of it's better.
    >>  ............................................
    pt  Comida é comida, mas tem comida melhor.
    >>  ............................................
```


### Button `praise` — "You've got good taste."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `food.pref.plain_is_fine`, `food.pref.shared` · offered only once the villager has actually said `food:dish_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.pref.praised` — accepted phrasings: "you have got good taste"; "you have a good palate"; "good taste, that"
  - the message must contain one of: `taste`, `palate`
  - scored words: `taste`(1.5), `good`(0.6), `palate`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.pref.followup.praise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.pref.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.pref.followup.praise   [22 chars]
    en  You've got good taste.
    >>  ............................................
    pt  Você tem bom gosto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.normal.praise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `food.pref.praised`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.normal.praise
WHO    VILLAGER — what the player reads after pressing "You've got good taste."
       spoken on: conversations.topic.food.pref.followup, button `praise`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.pref.praised`: the villager accepts. Subject `food.preference`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.normal.praise/1   [53 chars]
    en  Good taste, you say. I'll be insufferable for a week.
    >>  ............................................
    pt  Bom gosto, você diz. Vou ficar insuportável por uma semana.
    >>  ............................................
  dialogue.conversations.food.normal.praise/2   [47 chars]
    en  It's the only kind of taste I can afford, %1$s.
    >>  ............................................
    pt  É o único tipo de gosto que eu posso bancar, %1$s.
    >>  ............................................
  dialogue.conversations.food.normal.praise/3   [48 chars]
    en  Hah. Tell that to my mother. She despairs of me.
    >>  ............................................
    pt  Hah. Diga isso pra minha mãe. Ela se desespera comigo.
    >>  ............................................
```


### Button `promise_bring` — "I'll bring you some."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `food.pref.plain_is_fine`, `food.pref.shared` · offered only once the villager has actually said `food:dish_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.pref.promised` — accepted phrasings: "i will bring you some"; "i will fetch you some"; "i can bring you some of that"
  - the message must contain one of: `bring`, `fetch`
  - scored words: `bring`(1.5), `some`(0.8), `fetch`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.pref.followup.promise_bring
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.pref.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.pref.followup.promise_bring   [20 chars]
    en  I'll bring you some.
    >>  ............................................
    pt  Eu te trago um pouco.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.normal.promise_bring`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +2, warmth +2  _(recorded under topic `food.pref.promised`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.normal.promise_bring
WHO    VILLAGER — what the player reads after pressing "I'll bring you some."
       spoken on: conversations.topic.food.pref.followup, button `promise_bring`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.pref.promised`: the villager accepts. Subject `food.preference`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.normal.promise_bring/1   [69 chars]
    en  ...Would you? That's the nicest thing anyone's offered me this month.
    >>  ............................................
    pt  ...Você traria? É a coisa mais gentil que alguém me ofereceu este mês.
    >>  ............................................
  dialogue.conversations.food.normal.promise_bring/2   [60 chars]
    en  I'll believe it when I'm chewing, %1$s. But I'll believe it.
    >>  ............................................
    pt  Vou acreditar quando estiver mastigando, %1$s. Mas vou acreditar.
    >>  ............................................
  dialogue.conversations.food.normal.promise_bring/3   [50 chars]
    en  Don't promise food you won't bring. I'll remember.
    >>  ............................................
    pt  Não prometa comida que você não vai trazer. Eu lembro.
    >>  ............................................
```


### Button `ask_worst` — "And the one thing you won't touch?"

*stance family `curiosity` · tone `playful` · outcome `engaged` · answers the beat(s) `food.pref.plain_is_fine`, `food.pref.shared` · offered only once the villager has actually said `preference:plain`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.pref.ask_worst` — accepted phrasings: "and the one thing you will not touch"; "what do you hate to eat"; "is there anything you refuse to eat"
  - the message must contain one of: `touch`, `refuse`, `hate`
  - scored words: `touch`(1.2), `refuse`(1.2), `hate`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.pref.followup.ask_worst
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.pref.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.pref.followup.ask_worst   [34 chars]
    en  And the one thing you won't touch?
    >>  ............................................
    pt  E a única coisa que você não encosta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `food.pref.ask_worst`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.pref.ask_worst
WHO    VILLAGER — what the player reads after pressing "And the one thing you won't touch?"
       spoken on: conversations.topic.food.pref.followup, button `ask_worst`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.pref.ask_worst`: the villager discloses. Subject `food.preference`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.pref.ask_worst/1   [80 chars]
    en  Beetroot. It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e tem gosto do chão de onde veio.
    >>  ............................................
  dialogue.conversations.food.pref.ask_worst/2   [69 chars]
    en  Anything that was still moving an hour ago. Call me particular, %1$s.
    >>  ............................................
    pt  Qualquer coisa que ainda se mexia uma hora atrás. Me chame de exigente, %1$s.
    >>  ............................................
  dialogue.conversations.food.pref.ask_worst/3   [65 chars]
    en  The inn's fish stew. I've said it publicly and I'll say it again.
    >>  ............................................
    pt  O ensopado de peixe da estalagem. Já disse em público e digo de novo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and I've never been able to be polite about it.
    >>  ............................................
    pt  Beterraba. Mancha tudo e eu nunca consegui ser educado sobre isso.
    >>  ............................................
  anxious.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I ate a great deal of it one hard winter and I've not managed it since.
    >>  ............................................
    pt  Beterraba. Comi muita num inverno duro e não consigo desde então.
    >>  ............................................
  anxious.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. It's a silly answer to a nice question and it's the honest one.
    >>  ............................................
    pt  Beterraba. É uma resposta boba a uma pergunta gentil e é a honesta.
    >>  ............................................
  athletic.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything it touches and it always has.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e sempre manchou.
    >>  ............................................
  athletic.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I've had thirty years to come round to it and I've not.
    >>  ............................................
    pt  Beterraba. Tive trinta anos pra me acostumar e não me acostumei.
    >>  ............................................
  athletic.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. Some things you simply let be, and that's one of them.
    >>  ............................................
    pt  Beterraba. Algumas coisas você simplesmente deixa em paz, e essa é uma.
    >>  ............................................
  confident.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e tem gosto da terra de onde veio.
    >>  ............................................
  confident.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I've tried it four ways and it was beetroot every time.
    >>  ............................................
    pt  Beterraba. Já provei de quatro jeitos e era beterraba todas as vezes.
    >>  ............................................
  confident.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot, and I'll not be talked round on it.
    >>  ............................................
    pt  Beterraba, e ninguém vai me convencer do contrário.
    >>  ............................................
  crabby.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e tem gosto da terra de onde veio.
    >>  ............................................
  crabby.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I've tried it four ways and it was beetroot every time.
    >>  ............................................
    pt  Beterraba. Já provei de quatro jeitos e era beterraba todas as vezes.
    >>  ............................................
  crabby.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot, and I'll not be talked round on it.
    >>  ............................................
    pt  Beterraba, e ninguém vai me convencer do contrário.
    >>  ............................................
  extroverted.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and tastes of soil, and the cook keeps trying, bless her.
    >>  ............................................
    pt  Beterraba. Mancha tudo e tem gosto de terra, e o cozinheiro continua tentando, coitado.
    >>  ............................................
  extroverted.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. Don't tell the cook. She's convinced she'll convert me one day.
    >>  ............................................
    pt  Beterraba. Não conte ao cozinheiro. Ele acha que vai me converter um dia.
    >>  ............................................
  extroverted.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot — though if you made it, I'd probably eat it and say nothing.
    >>  ............................................
    pt  Beterraba — mas se você fizesse, eu provavelmente comeria e não diria nada.
    >>  ............................................
  flirty.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and tastes of soil, and the cook keeps trying, bless her.
    >>  ............................................
    pt  Beterraba. Mancha tudo e tem gosto de terra, e o cozinheiro continua tentando, coitado.
    >>  ............................................
  flirty.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. Don't tell the cook. She's convinced she'll convert me one day.
    >>  ............................................
    pt  Beterraba. Não conte ao cozinheiro. Ele acha que vai me converter um dia.
    >>  ............................................
  flirty.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot — though if you made it, I'd probably eat it and say nothing.
    >>  ............................................
    pt  Beterraba — mas se você fizesse, eu provavelmente comeria e não diria nada.
    >>  ............................................
  friendly.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and tastes of soil, and the cook keeps trying, bless her.
    >>  ............................................
    pt  Beterraba. Mancha tudo e tem gosto de terra, e o cozinheiro continua tentando, coitado.
    >>  ............................................
  friendly.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. Don't tell the cook. She's convinced she'll convert me one day.
    >>  ............................................
    pt  Beterraba. Não conte ao cozinheiro. Ele acha que vai me converter um dia.
    >>  ............................................
  friendly.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot — though if you made it, I'd probably eat it and say nothing.
    >>  ............................................
    pt  Beterraba — mas se você fizesse, eu provavelmente comeria e não diria nada.
    >>  ............................................
  gloomy.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and I've never been able to be polite about it.
    >>  ............................................
    pt  Beterraba. Mancha tudo e eu nunca consegui ser educado sobre isso.
    >>  ............................................
  gloomy.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I ate a great deal of it one hard winter and I've not managed it since.
    >>  ............................................
    pt  Beterraba. Comi muita num inverno duro e não consigo desde então.
    >>  ............................................
  gloomy.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. It's a silly answer to a nice question and it's the honest one.
    >>  ............................................
    pt  Beterraba. É uma resposta boba a uma pergunta gentil e é a honesta.
    >>  ............................................
  greedy.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e tem gosto da terra de onde veio.
    >>  ............................................
  greedy.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I've tried it four ways and it was beetroot every time.
    >>  ............................................
    pt  Beterraba. Já provei de quatro jeitos e era beterraba todas as vezes.
    >>  ............................................
  greedy.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot, and I'll not be talked round on it.
    >>  ............................................
    pt  Beterraba, e ninguém vai me convencer do contrário.
    >>  ............................................
  grumpy.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e tem gosto da terra de onde veio.
    >>  ............................................
  grumpy.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I've tried it four ways and it was beetroot every time.
    >>  ............................................
    pt  Beterraba. Já provei de quatro jeitos e era beterraba todas as vezes.
    >>  ............................................
  grumpy.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot, and I'll not be talked round on it.
    >>  ............................................
    pt  Beterraba, e ninguém vai me convencer do contrário.
    >>  ............................................
  introverted.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and tastes of the ground.
    >>  ............................................
    pt  Beterraba. Mancha tudo e tem gosto de terra.
    >>  ............................................
  introverted.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot.
    >>  ............................................
    pt  Beterraba.
    >>  ............................................
  introverted.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. I'd rather not go into it further.
    >>  ............................................
    pt  Beterraba. Prefiro não me alongar.
    >>  ............................................
  lazy.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything it touches and it always has.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e sempre manchou.
    >>  ............................................
  lazy.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I've had thirty years to come round to it and I've not.
    >>  ............................................
    pt  Beterraba. Tive trinta anos pra me acostumar e não me acostumei.
    >>  ............................................
  lazy.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. Some things you simply let be, and that's one of them.
    >>  ............................................
    pt  Beterraba. Algumas coisas você simplesmente deixa em paz, e essa é uma.
    >>  ............................................
  odd.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and tastes of the ground.
    >>  ............................................
    pt  Beterraba. Mancha tudo e tem gosto de terra.
    >>  ............................................
  odd.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot.
    >>  ............................................
    pt  Beterraba.
    >>  ............................................
  odd.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. I'd rather not go into it further.
    >>  ............................................
    pt  Beterraba. Prefiro não me alongar.
    >>  ............................................
  peaceful.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything it touches and it always has.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e sempre manchou.
    >>  ............................................
  peaceful.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I've had thirty years to come round to it and I've not.
    >>  ............................................
    pt  Beterraba. Tive trinta anos pra me acostumar e não me acostumei.
    >>  ............................................
  peaceful.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. Some things you simply let be, and that's one of them.
    >>  ............................................
    pt  Beterraba. Algumas coisas você simplesmente deixa em paz, e essa é uma.
    >>  ............................................
  peppy.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot! It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba! Mancha tudo que toca e tem gosto da terra de onde veio.
    >>  ............................................
  peppy.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. Four preparations, four failures, one very purple apron.
    >>  ............................................
    pt  Beterraba. Quatro preparos, quatro fracassos, um avental bem roxo.
    >>  ............................................
  peppy.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. I have nothing kind to say and I've stopped looking for something.
    >>  ............................................
    pt  Beterraba. Não tenho nada gentil a dizer e parei de procurar.
    >>  ............................................
  playful.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot! It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba! Mancha tudo que toca e tem gosto da terra de onde veio.
    >>  ............................................
  playful.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. Four preparations, four failures, one very purple apron.
    >>  ............................................
    pt  Beterraba. Quatro preparos, quatro fracassos, um avental bem roxo.
    >>  ............................................
  playful.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. I have nothing kind to say and I've stopped looking for something.
    >>  ............................................
    pt  Beterraba. Não tenho nada gentil a dizer e parei de procurar.
    >>  ............................................
  relaxed.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything it touches and it always has.
    >>  ............................................
    pt  Beterraba. Mancha tudo que toca e sempre manchou.
    >>  ............................................
  relaxed.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I've had thirty years to come round to it and I've not.
    >>  ............................................
    pt  Beterraba. Tive trinta anos pra me acostumar e não me acostumei.
    >>  ............................................
  relaxed.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. Some things you simply let be, and that's one of them.
    >>  ............................................
    pt  Beterraba. Algumas coisas você simplesmente deixa em paz, e essa é uma.
    >>  ............................................
  sensitive.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and I've never been able to be polite about it.
    >>  ............................................
    pt  Beterraba. Mancha tudo e eu nunca consegui ser educado sobre isso.
    >>  ............................................
  sensitive.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. I ate a great deal of it one hard winter and I've not managed it since.
    >>  ............................................
    pt  Beterraba. Comi muita num inverno duro e não consigo desde então.
    >>  ............................................
  sensitive.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. It's a silly answer to a nice question and it's the honest one.
    >>  ............................................
    pt  Beterraba. É uma resposta boba a uma pergunta gentil e é a honesta.
    >>  ............................................
  shy.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot. It stains everything and tastes of the ground.
    >>  ............................................
    pt  Beterraba. Mancha tudo e tem gosto de terra.
    >>  ............................................
  shy.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot.
    >>  ............................................
    pt  Beterraba.
    >>  ............................................
  shy.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. I'd rather not go into it further.
    >>  ............................................
    pt  Beterraba. Prefiro não me alongar.
    >>  ............................................
  upbeat.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot! It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba! Mancha tudo que toca e tem gosto da terra de onde veio.
    >>  ............................................
  upbeat.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. Four preparations, four failures, one very purple apron.
    >>  ............................................
    pt  Beterraba. Quatro preparos, quatro fracassos, um avental bem roxo.
    >>  ............................................
  upbeat.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. I have nothing kind to say and I've stopped looking for something.
    >>  ............................................
    pt  Beterraba. Não tenho nada gentil a dizer e parei de procurar.
    >>  ............................................
  witty.dialogue.conversations.food.pref.ask_worst/1
    en  Beetroot! It stains everything it touches and tastes of the ground it came from.
    >>  ............................................
    pt  Beterraba! Mancha tudo que toca e tem gosto da terra de onde veio.
    >>  ............................................
  witty.dialogue.conversations.food.pref.ask_worst/2
    en  Beetroot. Four preparations, four failures, one very purple apron.
    >>  ............................................
    pt  Beterraba. Quatro preparos, quatro fracassos, um avental bem roxo.
    >>  ............................................
  witty.dialogue.conversations.food.pref.ask_worst/3
    en  Beetroot. I have nothing kind to say and I've stopped looking for something.
    >>  ............................................
    pt  Beterraba. Não tenho nada gentil a dizer e parei de procurar.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get to your supper."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `food.pref.plain_is_fine`, `food.pref.shared` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.pref.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.pref.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.pref.followup.leave   [32 chars]
    en  I'll let you get to your supper.
    >>  ............................................
    pt  Vou deixar você ir jantar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.normal.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to your supper."
       spoken on: conversations.topic.food.pref.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.left`: the villager accepts. Subject `food.preference`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.food.debate.followup / leave; conversations.topic.food.normal.respond / leave; conversations.topic.food.recipe.followup / leave; conversations.topic.food.snubbed.followup / leave
```

> Written out in full under **`conversations.topic.food.debate.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.food.recipe.followup`

**Reached from 2 route(s):** `conversations.topic.food.normal.respond` / `ask_recipe`; `conversations.topic.food.normal.respond` / `ask_recipe`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.ask_recipe.season` — e.g. "In %2$s? Whatever's ripe, barely cooked, eaten standing up. That's the recipe."
- `conversations.food.normal.ask_recipe` — e.g. "Badly, is how. But I'll tell you the version I aim for."


```text
POOL   dialogue key: dialogue.conversations.topic.food.recipe.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.recipe.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.recipe.followup   [34 chars]
    en  That's how it's done, near enough.
    >>  ............................................
    pt  É assim que se faz, mais ou menos.
    >>  ............................................
```


### Button `ask_where` — "Where do you get it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `food.recipe.told`, `food.recipe.seasonal` · offered only once the villager has actually said `food:dish_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.recipe.sourced` — accepted phrasings: "where do you get it"; "where do you buy it"; "which market stall"
  - the message must contain one of: `where`, `buy`, `market`
  - scored words: `where`(1.2), `buy`(1.5), `market`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.food.recipe.followup.ask_where
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.recipe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.recipe.followup.ask_where   [20 chars]
    en  Where do you get it?
    >>  ............................................
    pt  Onde você consegue?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `food.recipe.sourced`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.normal.ask_where
WHO    VILLAGER — what the player reads after pressing "Where do you get it?"
       spoken on: conversations.topic.food.recipe.followup, button `ask_where`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.recipe.sourced`: the villager explains. Subject `food.preference`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.normal.ask_where/1   [53 chars]
    en  The market, mostly. The good stall, not the loud one.
    >>  ............................................
    pt  No mercado, principalmente. A barraca boa, não a barulhenta.
    >>  ............................................
  dialogue.conversations.food.normal.ask_where/2   [58 chars]
    en  I grow half of it and beg the rest. It's an honest system.
    >>  ............................................
    pt  Eu planto metade e imploro o resto. É um sistema honesto.
    >>  ............................................
  dialogue.conversations.food.normal.ask_where/3   [70 chars]
    en  There's a place. I'll tell you when I trust you not to clear them out.
    >>  ............................................
    pt  Tem um lugar. Eu te conto quando confiar que você não vai limpar o estoque.
    >>  ............................................
```


### Button `promise_try` — "I'll try it that way."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `food.recipe.told`, `food.recipe.seasonal` · offered only once the villager has actually said `food:method_told`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.recipe.promise_try` — accepted phrasings: "i will try it that way"; "i will attempt that myself"; "i might try that"
  - the message must contain one of: `try`, `attempt`, `myself`
  - scored words: `try`(1.5), `attempt`(1.2), `myself`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.recipe.followup.promise_try
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.recipe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.recipe.followup.promise_try   [21 chars]
    en  I'll try it that way.
    >>  ............................................
    pt  Vou tentar fazer assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.recipe.promise_try`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +2, warmth +2  _(recorded under topic `food.recipe.promise_try`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.recipe.promise_try
WHO    VILLAGER — what the player reads after pressing "I'll try it that way."
       spoken on: conversations.topic.food.recipe.followup, button `promise_try`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.recipe.promise_try`: the villager invites. Subject `food.preference`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.recipe.promise_try/1   [68 chars]
    en  Do, and tell me honestly whether it worked. I'll take the criticism.
    >>  ............................................
    pt  Tente, e me diga honestamente se deu certo. Eu aceito a crítica.
    >>  ............................................
  dialogue.conversations.food.recipe.promise_try/2   [73 chars]
    en  Then you'll want twice the butter you think, %1$s. Everyone underdoes it.
    >>  ............................................
    pt  Então você vai querer o dobro da manteiga que imagina, %1$s. Todo mundo põe pouco.
    >>  ............................................
  dialogue.conversations.food.recipe.promise_try/3   [66 chars]
    en  Good. Come back and complain about it and we'll be proper friends.
    >>  ............................................
    pt  Bom. Volte pra reclamar e a gente vira amigo de verdade.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. I'd rather that than never hearing again, %1$s.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Prefiro isso a nunca mais ouvir, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. And be honest — I've had enough polite lies about my cooking to last a life.
    >>  ............................................
    pt  Tente. E seja honesto — já tive mentiras educadas sobre minha comida pra uma vida.
    >>  ............................................
  anxious.dialogue.conversations.food.recipe.promise_try/3
    en  Do. Come back either way. That's the part I'm actually asking for.
    >>  ............................................
    pt  Faça. Volte de qualquer jeito. É essa a parte que eu estou pedindo.
    >>  ............................................
  athletic.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. There's no hurry; it'll keep.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Sem pressa; fica pra depois.
    >>  ............................................
  athletic.dialogue.conversations.food.recipe.promise_try/2
    en  Try it when you've an afternoon. It doesn't go faster for being rushed and neither should you.
    >>  ............................................
    pt  Tente quando tiver uma tarde. Não anda mais rápido com pressa e você também não devia.
    >>  ............................................
  athletic.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And take your time over it. That recipe has never once rewarded a hurry.
    >>  ............................................
    pt  Faça. E leve seu tempo. Essa receita nunca recompensou pressa.
    >>  ............................................
  confident.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. I'll take the criticism.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Eu aceito a crítica.
    >>  ............................................
  confident.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. Then come back and tell me what went wrong, because something will.
    >>  ............................................
    pt  Tente. Depois volte e me diga o que deu errado, porque algo vai dar.
    >>  ............................................
  confident.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And be honest about it — I'd rather know than be flattered.
    >>  ............................................
    pt  Faça. E seja honesto — prefiro saber a ser bajulado.
    >>  ............................................
  crabby.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. I'll take the criticism.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Eu aceito a crítica.
    >>  ............................................
  crabby.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. Then come back and tell me what went wrong, because something will.
    >>  ............................................
    pt  Tente. Depois volte e me diga o que deu errado, porque algo vai dar.
    >>  ............................................
  crabby.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And be honest about it — I'd rather know than be flattered.
    >>  ............................................
    pt  Faça. E seja honesto — prefiro saber a ser bajulado.
    >>  ............................................
  extroverted.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked, %1$s. I'll take the criticism.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou, %1$s. Eu aceito a crítica.
    >>  ............................................
  extroverted.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. And come and tell me either way — I'll want to hear about it.
    >>  ............................................
    pt  Tente. E venha me contar de qualquer jeito — eu vou querer saber.
    >>  ............................................
  extroverted.dialogue.conversations.food.recipe.promise_try/3
    en  Do. Then come back and we'll fix whatever went wrong together.
    >>  ............................................
    pt  Faça. Depois volte e a gente conserta junto o que deu errado.
    >>  ............................................
  flirty.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked, %1$s. I'll take the criticism.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou, %1$s. Eu aceito a crítica.
    >>  ............................................
  flirty.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. And come and tell me either way — I'll want to hear about it.
    >>  ............................................
    pt  Tente. E venha me contar de qualquer jeito — eu vou querer saber.
    >>  ............................................
  flirty.dialogue.conversations.food.recipe.promise_try/3
    en  Do. Then come back and we'll fix whatever went wrong together.
    >>  ............................................
    pt  Faça. Depois volte e a gente conserta junto o que deu errado.
    >>  ............................................
  friendly.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked, %1$s. I'll take the criticism.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou, %1$s. Eu aceito a crítica.
    >>  ............................................
  friendly.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. And come and tell me either way — I'll want to hear about it.
    >>  ............................................
    pt  Tente. E venha me contar de qualquer jeito — eu vou querer saber.
    >>  ............................................
  friendly.dialogue.conversations.food.recipe.promise_try/3
    en  Do. Then come back and we'll fix whatever went wrong together.
    >>  ............................................
    pt  Faça. Depois volte e a gente conserta junto o que deu errado.
    >>  ............................................
  gloomy.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. I'd rather that than never hearing again, %1$s.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Prefiro isso a nunca mais ouvir, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. And be honest — I've had enough polite lies about my cooking to last a life.
    >>  ............................................
    pt  Tente. E seja honesto — já tive mentiras educadas sobre minha comida pra uma vida.
    >>  ............................................
  gloomy.dialogue.conversations.food.recipe.promise_try/3
    en  Do. Come back either way. That's the part I'm actually asking for.
    >>  ............................................
    pt  Faça. Volte de qualquer jeito. É essa a parte que eu estou pedindo.
    >>  ............................................
  greedy.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. I'll take the criticism.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Eu aceito a crítica.
    >>  ............................................
  greedy.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. Then come back and tell me what went wrong, because something will.
    >>  ............................................
    pt  Tente. Depois volte e me diga o que deu errado, porque algo vai dar.
    >>  ............................................
  greedy.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And be honest about it — I'd rather know than be flattered.
    >>  ............................................
    pt  Faça. E seja honesto — prefiro saber a ser bajulado.
    >>  ............................................
  grumpy.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. I'll take the criticism.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Eu aceito a crítica.
    >>  ............................................
  grumpy.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. Then come back and tell me what went wrong, because something will.
    >>  ............................................
    pt  Tente. Depois volte e me diga o que deu errado, porque algo vai dar.
    >>  ............................................
  grumpy.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And be honest about it — I'd rather know than be flattered.
    >>  ............................................
    pt  Faça. E seja honesto — prefiro saber a ser bajulado.
    >>  ............................................
  introverted.dialogue.conversations.food.recipe.promise_try/1
    en  Do. And tell me honestly whether it worked.
    >>  ............................................
    pt  Faça. E me diga honestamente se funcionou.
    >>  ............................................
  introverted.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. Come back and say what went wrong.
    >>  ............................................
    pt  Tente. Volte e diga o que deu errado.
    >>  ............................................
  introverted.dialogue.conversations.food.recipe.promise_try/3
    en  Do. I'll take the criticism.
    >>  ............................................
    pt  Faça. Eu aceito a crítica.
    >>  ............................................
  lazy.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. There's no hurry; it'll keep.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Sem pressa; fica pra depois.
    >>  ............................................
  lazy.dialogue.conversations.food.recipe.promise_try/2
    en  Try it when you've an afternoon. It doesn't go faster for being rushed and neither should you.
    >>  ............................................
    pt  Tente quando tiver uma tarde. Não anda mais rápido com pressa e você também não devia.
    >>  ............................................
  lazy.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And take your time over it. That recipe has never once rewarded a hurry.
    >>  ............................................
    pt  Faça. E leve seu tempo. Essa receita nunca recompensou pressa.
    >>  ............................................
  odd.dialogue.conversations.food.recipe.promise_try/1
    en  Do. And tell me honestly whether it worked.
    >>  ............................................
    pt  Faça. E me diga honestamente se funcionou.
    >>  ............................................
  odd.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. Come back and say what went wrong.
    >>  ............................................
    pt  Tente. Volte e diga o que deu errado.
    >>  ............................................
  odd.dialogue.conversations.food.recipe.promise_try/3
    en  Do. I'll take the criticism.
    >>  ............................................
    pt  Faça. Eu aceito a crítica.
    >>  ............................................
  peaceful.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. There's no hurry; it'll keep.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Sem pressa; fica pra depois.
    >>  ............................................
  peaceful.dialogue.conversations.food.recipe.promise_try/2
    en  Try it when you've an afternoon. It doesn't go faster for being rushed and neither should you.
    >>  ............................................
    pt  Tente quando tiver uma tarde. Não anda mais rápido com pressa e você também não devia.
    >>  ............................................
  peaceful.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And take your time over it. That recipe has never once rewarded a hurry.
    >>  ............................................
    pt  Faça. E leve seu tempo. Essa receita nunca recompensou pressa.
    >>  ............................................
  peppy.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked! I'll take the criticism. Probably.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou! Eu aceito a crítica. Provavelmente.
    >>  ............................................
  peppy.dialogue.conversations.food.recipe.promise_try/2
    en  Try it! Then come back and tell me what went wrong, because something always does.
    >>  ............................................
    pt  Tente! Depois volte e me diga o que deu errado, porque sempre dá.
    >>  ............................................
  peppy.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And be brutal about it. It's the only way anything in a kitchen improves.
    >>  ............................................
    pt  Faça. E seja brutal. É o único jeito de algo numa cozinha melhorar.
    >>  ............................................
  playful.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked! I'll take the criticism. Probably.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou! Eu aceito a crítica. Provavelmente.
    >>  ............................................
  playful.dialogue.conversations.food.recipe.promise_try/2
    en  Try it! Then come back and tell me what went wrong, because something always does.
    >>  ............................................
    pt  Tente! Depois volte e me diga o que deu errado, porque sempre dá.
    >>  ............................................
  playful.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And be brutal about it. It's the only way anything in a kitchen improves.
    >>  ............................................
    pt  Faça. E seja brutal. É o único jeito de algo numa cozinha melhorar.
    >>  ............................................
  relaxed.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. There's no hurry; it'll keep.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Sem pressa; fica pra depois.
    >>  ............................................
  relaxed.dialogue.conversations.food.recipe.promise_try/2
    en  Try it when you've an afternoon. It doesn't go faster for being rushed and neither should you.
    >>  ............................................
    pt  Tente quando tiver uma tarde. Não anda mais rápido com pressa e você também não devia.
    >>  ............................................
  relaxed.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And take your time over it. That recipe has never once rewarded a hurry.
    >>  ............................................
    pt  Faça. E leve seu tempo. Essa receita nunca recompensou pressa.
    >>  ............................................
  sensitive.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked. I'd rather that than never hearing again, %1$s.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou. Prefiro isso a nunca mais ouvir, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. And be honest — I've had enough polite lies about my cooking to last a life.
    >>  ............................................
    pt  Tente. E seja honesto — já tive mentiras educadas sobre minha comida pra uma vida.
    >>  ............................................
  sensitive.dialogue.conversations.food.recipe.promise_try/3
    en  Do. Come back either way. That's the part I'm actually asking for.
    >>  ............................................
    pt  Faça. Volte de qualquer jeito. É essa a parte que eu estou pedindo.
    >>  ............................................
  shy.dialogue.conversations.food.recipe.promise_try/1
    en  Do. And tell me honestly whether it worked.
    >>  ............................................
    pt  Faça. E me diga honestamente se funcionou.
    >>  ............................................
  shy.dialogue.conversations.food.recipe.promise_try/2
    en  Try it. Come back and say what went wrong.
    >>  ............................................
    pt  Tente. Volte e diga o que deu errado.
    >>  ............................................
  shy.dialogue.conversations.food.recipe.promise_try/3
    en  Do. I'll take the criticism.
    >>  ............................................
    pt  Faça. Eu aceito a crítica.
    >>  ............................................
  upbeat.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked! I'll take the criticism. Probably.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou! Eu aceito a crítica. Provavelmente.
    >>  ............................................
  upbeat.dialogue.conversations.food.recipe.promise_try/2
    en  Try it! Then come back and tell me what went wrong, because something always does.
    >>  ............................................
    pt  Tente! Depois volte e me diga o que deu errado, porque sempre dá.
    >>  ............................................
  upbeat.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And be brutal about it. It's the only way anything in a kitchen improves.
    >>  ............................................
    pt  Faça. E seja brutal. É o único jeito de algo numa cozinha melhorar.
    >>  ............................................
  witty.dialogue.conversations.food.recipe.promise_try/1
    en  Do, and tell me honestly whether it worked! I'll take the criticism. Probably.
    >>  ............................................
    pt  Faça, e me diga honestamente se funcionou! Eu aceito a crítica. Provavelmente.
    >>  ............................................
  witty.dialogue.conversations.food.recipe.promise_try/2
    en  Try it! Then come back and tell me what went wrong, because something always does.
    >>  ............................................
    pt  Tente! Depois volte e me diga o que deu errado, porque sempre dá.
    >>  ............................................
  witty.dialogue.conversations.food.recipe.promise_try/3
    en  Do. And be brutal about it. It's the only way anything in a kitchen improves.
    >>  ............................................
    pt  Faça. E seja brutal. É o único jeito de algo numa cozinha melhorar.
    >>  ............................................
```

</details>


### Button `praise_method` — "You make it sound worth the trouble."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `food.recipe.told`, `food.recipe.seasonal` · offered only once the villager has actually said `food:method_told`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.recipe.praise_method` — accepted phrasings: "you make it sound worth the trouble"; "that sounds worth the effort"; "sounds like it is worth doing properly"
  - the message must contain one of: `trouble`, `worth`, `effort`
  - scored words: `trouble`(1.5), `worth`(1.2), `effort`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.recipe.followup.praise_method
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.recipe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.recipe.followup.praise_method   [36 chars]
    en  You make it sound worth the trouble.
    >>  ............................................
    pt  Você faz parecer que vale o trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.recipe.praise_method`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +1  _(recorded under topic `food.recipe.praise_method`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.recipe.praise_method
WHO    VILLAGER — what the player reads after pressing "You make it sound worth the trouble."
       spoken on: conversations.topic.food.recipe.followup, button `praise_method`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.recipe.praise_method`: the villager accepts. Subject `food.preference`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.recipe.praise_method/1   [61 chars]
    en  It is. Everything worth eating is more trouble than it looks.
    >>  ............................................
    pt  E vale. Tudo que vale a pena comer dá mais trabalho do que parece.
    >>  ............................................
  dialogue.conversations.food.recipe.praise_method/2   [74 chars]
    en  That's the whole argument for cooking, %1$s, and nobody makes it any more.
    >>  ............................................
    pt  É o argumento inteiro a favor de cozinhar, %1$s, e ninguém mais faz esse argumento.
    >>  ............................................
  dialogue.conversations.food.recipe.praise_method/3   [65 chars]
    en  Trouble's the ingredient nobody lists. I'm glad somebody noticed.
    >>  ............................................
    pt  Trabalho é o ingrediente que ninguém lista. Que bom que alguém reparou.
    >>  ............................................
```


### Button `leave` — "I'll let you get to your supper."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `food.recipe.told`, `food.recipe.seasonal` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.recipe.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.recipe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.recipe.followup.leave   [32 chars]
    en  I'll let you get to your supper.
    >>  ............................................
    pt  Vou deixar você ir jantar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.normal.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to your supper."
       spoken on: conversations.topic.food.recipe.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.left`: the villager accepts. Subject `food.preference`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.food.debate.followup / leave; conversations.topic.food.normal.respond / leave; conversations.topic.food.pref.followup / leave; conversations.topic.food.snubbed.followup / leave
```

> Written out in full under **`conversations.topic.food.debate.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.food.snubbed.followup`

**Reached from 1 route(s):** `conversations.topic.food.normal.respond` / `disagree`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.normal.disagree.flat` — e.g. "...It's only food, %1$s. It didn't need a counter-argument."


```text
POOL   dialogue key: dialogue.conversations.topic.food.snubbed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.snubbed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.snubbed.followup   [25 chars]
    en  It was only a preference.
    >>  ............................................
    pt  Era só uma preferência.
    >>  ............................................
```


### Button `apologize` — "You're right. It's only food."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `food.debate.unwanted` · offered only once the villager has actually said `player:pressed_the_point`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.snubbed.apologize` — accepted phrasings: "you are right, it is only food"; "sorry, that was trivial"; "i am sorry, it does not matter"
  - the message must contain one of: `only`, `sorry`, `trivial`
  - scored words: `only`(1.0), `sorry`(1.2), `trivial`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.snubbed.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.snubbed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.snubbed.followup.apologize   [29 chars]
    en  You're right. It's only food.
    >>  ............................................
    pt  Você tem razão. É só comida.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `food.snubbed.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.snubbed.apologize
WHO    VILLAGER — what the player reads after pressing "You're right. It's only food."
       spoken on: conversations.topic.food.snubbed.followup, button `apologize`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.snubbed.apologize`: the villager qualifys. Subject `food.preference`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.snubbed.apologize/1   [59 chars]
    en  It is. And now I've made it into something, which is worse.
    >>  ............................................
    pt  É. E agora eu transformei numa coisa, o que é pior.
    >>  ............................................
  dialogue.conversations.food.snubbed.apologize/2   [39 chars]
    en  So it is. Let's both put it down, %1$s.
    >>  ............................................
    pt  É assim mesmo. Vamos os dois largar isso, %1$s.
    >>  ............................................
  dialogue.conversations.food.snubbed.apologize/3   [49 chars]
    en  ...Thank you. I'd rather like the bread in peace.
    >>  ............................................
    pt  ...Obrigado. Eu preferia gostar do pão em paz.
    >>  ............................................
```


### Button `explain` — "I only meant I liked mine too."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `food.debate.unwanted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.snubbed.explain` — accepted phrasings: "i only meant i liked mine too"; "i just meant i like mine"; "that is not what i meant"
  - the message must contain one of: `meant`, `liked`, `mine`
  - scored words: `meant`(1.5), `liked`(1.2), `mine`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.snubbed.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.snubbed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.snubbed.followup.explain   [30 chars]
    en  I only meant I liked mine too.
    >>  ............................................
    pt  Eu só quis dizer que gosto da minha também.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `food.snubbed.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.snubbed.explain
WHO    VILLAGER — what the player reads after pressing "I only meant I liked mine too."
       spoken on: conversations.topic.food.snubbed.followup, button `explain`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.snubbed.explain`: the villager qualifys. Subject `food.preference`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.snubbed.explain/1   [59 chars]
    en  Then say that next time and skip the 'I'd argue with that'.
    >>  ............................................
    pt  Então diga isso da próxima vez e pule o 'eu discordaria disso'.
    >>  ............................................
  dialogue.conversations.food.snubbed.explain/2   [50 chars]
    en  ...Oh. That's a different sentence entirely, %1$s.
    >>  ............................................
    pt  ...Ah. É uma frase completamente diferente, %1$s.
    >>  ............................................
  dialogue.conversations.food.snubbed.explain/3   [60 chars]
    en  Fine. Then we like different things and the world continues.
    >>  ............................................
    pt  Tudo bem. Então a gente gosta de coisas diferentes e o mundo segue.
    >>  ............................................
```


### Button `respect` — "Fair. I'll say no more about it."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `food.debate.unwanted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.snubbed.respect` — accepted phrasings: "fair, i will say no more about it"; "i will say no more"; "consider the subject closed"
  - the message must contain one of: `fair`, `silent`
  - scored words: `fair`(1.0), `more`(0.8), `silent`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.snubbed.followup.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.snubbed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.snubbed.followup.respect   [32 chars]
    en  Fair. I'll say no more about it.
    >>  ............................................
    pt  Justo. Não falo mais nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2  _(recorded under topic `food.snubbed.respect`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.snubbed.respect
WHO    VILLAGER — what the player reads after pressing "Fair. I'll say no more about it."
       spoken on: conversations.topic.food.snubbed.followup, button `respect`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.snubbed.respect`: the villager accepts. Subject `food.preference`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.snubbed.respect/1   [38 chars]
    en  Good. It'll taste the same either way.
    >>  ............................................
    pt  Bom. Vai ter o mesmo gosto de qualquer jeito.
    >>  ............................................
  dialogue.conversations.food.snubbed.respect/2   [23 chars]
    en  Right. Thank you, %1$s.
    >>  ............................................
    pt  Certo. Obrigado, %1$s.
    >>  ............................................
  dialogue.conversations.food.snubbed.respect/3   [43 chars]
    en  Mm. Let's find a subject with lower stakes.
    >>  ............................................
    pt  Mm. Vamos achar um assunto com menos em jogo.
    >>  ............................................
```


### Button `leave` — "I'll let you get to your supper."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `food.debate.unwanted` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.snubbed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.snubbed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.snubbed.followup.leave   [32 chars]
    en  I'll let you get to your supper.
    >>  ............................................
    pt  Vou deixar você ir jantar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.normal.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to your supper."
       spoken on: conversations.topic.food.snubbed.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.left`: the villager accepts. Subject `food.preference`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.food.debate.followup / leave; conversations.topic.food.normal.respond / leave; conversations.topic.food.pref.followup / leave; conversations.topic.food.recipe.followup / leave
```

> Written out in full under **`conversations.topic.food.debate.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.food.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `food`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.toddler` — e.g. "I like bread. And apples. And bread."


```text
POOL   dialogue key: dialogue.conversations.topic.food.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.toddler.respond   [21 chars]
    en  That's the good food.
    >>  ............................................
    pt  Essa é a comida boa.
    >>  ............................................
```


### Button `delight` — "That is excellent food."

*stance family `encouragement` · tone `playful` · answers the beat(s) `food.toddler.to.food.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.toddler.delight` — accepted phrasings: "that is excellent food"; "that is very good food"; "excellent food"
  - the message must contain one of: `excellent`, `good`
  - scored words: `excellent`(1.5), `food`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.toddler.respond.delight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.toddler.respond.delight   [23 chars]
    en  That is excellent food.
    >>  ............................................
    pt  Isso é uma comida excelente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.toddler.delight`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `food.toddler.delight`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.toddler.delight
WHO    VILLAGER — what the player reads after pressing "That is excellent food."
       spoken on: conversations.topic.food.toddler.respond, button `delight`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.toddler.delight.terminal`: the villager celebrates. Subject `food.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.toddler.delight/1   [52 chars]
    en  It IS! It's the best one. I said so and I was RIGHT.
    >>  ............................................
    pt  É SIM! É a melhor. Eu falei e eu estava CERTO.
    >>  ............................................
  dialogue.conversations.food.toddler.delight/2   [50 chars]
    en  You know about good food! Grown-ups don't usually.
    >>  ............................................
    pt  Você entende de comida boa! Adulto normalmente não entende.
    >>  ............................................
  dialogue.conversations.food.toddler.delight/3   [45 chars]
    en  Excellent food. That's what it is. Excellent.
    >>  ............................................
    pt  Comida excelente. É isso que é. Excelente.
    >>  ............................................
```


### Button `ask` — "Which one is the best one?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `food.toddler.to.food.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.toddler.ask` — accepted phrasings: "which one is the best one"; "which is the best"; "what is the best one"
  - the message must contain one of: `best`, `which`
  - scored words: `best`(1.5), `which`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.toddler.respond.ask   [26 chars]
    en  Which one is the best one?
    >>  ............................................
    pt  Qual é a melhor de todas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `food.toddler.ask`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.toddler.ask
WHO    VILLAGER — what the player reads after pressing "Which one is the best one?"
       spoken on: conversations.topic.food.toddler.respond, button `ask`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.toddler.ask.terminal`: the villager asks. Subject `food.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.toddler.ask/1   [57 chars]
    en  The best one is... all of them. But mostly the first one.
    >>  ............................................
    pt  A melhor é... todas. Mas principalmente a primeira.
    >>  ............................................
  dialogue.conversations.food.toddler.ask/2   [65 chars]
    en  The one you get when you've been good. That one tastes different.
    >>  ............................................
    pt  A que você ganha quando se comporta. Essa tem outro gosto.
    >>  ............................................
  dialogue.conversations.food.toddler.ask/3   [64 chars]
    en  Whichever is biggest. Bigger food tastes better, everyone knows.
    >>  ............................................
    pt  A maior. Comida maior tem gosto melhor, todo mundo sabe.
    >>  ............................................
```


### Button `leave` — "Off you go and eat, then."

*stance family `exit` · tone `plain` · answers the beat(s) `food.toddler.to.food.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.toddler.respond.leave   [25 chars]
    en  Off you go and eat, then.
    >>  ............................................
    pt  Vai comer, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go and eat, then."
       spoken on: conversations.topic.food.toddler.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.toddler.leave.terminal`: the villager accepts. Subject `food.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.toddler.leave/1   [42 chars]
    en  Okay bye! I'm going to eat now. All of it.
    >>  ............................................
    pt  Tá, tchau! Vou comer agora. Tudo.
    >>  ............................................
  dialogue.conversations.food.toddler.leave/2   [24 chars]
    en  Bye, %1$s! Save me some!
    >>  ............................................
    pt  Tchau, %1$s! Guarda um pouco pra mim!
    >>  ............................................
  dialogue.conversations.food.toddler.leave/3   [22 chars]
    en  Yep. Eating time. Bye!
    >>  ............................................
    pt  É. Hora de comer. Tchau!
    >>  ............................................
```

---


## `conversations.topic.food.trait.followup`

**Reached from 2 route(s):** `conversations.topic.food.trait.respond` / `respect`; `conversations.topic.food.trait.respond` / `ask_about`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.trait.ask_about` — e.g. "Carefully, and with a lot of reading labels I can't always read."
- `conversations.food.trait.respect` — e.g. "...Thank you. Most people want a reason, or a debate about it."


```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.trait.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.trait.followup   [27 chars]
    en  Anyway. You get used to it.
    >>  ............................................
    pt  Enfim. A gente se acostuma.
    >>  ............................................
```


### Button `offer_alternative` — "I'll find you something you can actually eat."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `food.trait.respected`, `food.trait.explained` · offered only once the villager has actually said `trait:dietary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.trait.offer_alternative` — accepted phrasings: "i will find you something you can eat"; "i will bring you something else"; "i will find something"
  - the message must contain one of: `find`, `bring`, `something`
  - scored words: `find`(1.5), `bring`(1.2), `something`(0.8), `eat`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.followup.offer_alternative
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.followup.offer_alternative   [45 chars]
    en  I'll find you something you can actually eat.
    >>  ............................................
    pt  Vou achar algo que você possa comer de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.trait.offer_alternative`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `food.trait.offer_alternative`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.trait.offer_alternative
WHO    VILLAGER — what the player reads after pressing "I'll find you something you can actually eat."
       spoken on: conversations.topic.food.trait.followup, button `offer_alternative`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.alternative`: the villager accepts. Subject `food.dietary_trait`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.offer_alternative/1   [58 chars]
    en  ...You'd actually do that? Nobody plans around me. Nobody.
    >>  ............................................
    pt  ...Você faria isso mesmo? Ninguém planeja pensando em mim. Ninguém.
    >>  ............................................
  dialogue.conversations.food.trait.offer_alternative/2   [74 chars]
    en  That's a kindness with legs on it. Most kindness about this is just words.
    >>  ............................................
    pt  Essa é uma gentileza que anda. A maioria das gentilezas sobre isso é só palavra.
    >>  ............................................
  dialogue.conversations.food.trait.offer_alternative/3   [60 chars]
    en  I'll hold you to that, %1$s. And I'll be delighted about it.
    >>  ............................................
    pt  Vou cobrar, %1$s. E vou ficar encantado com isso.
    >>  ............................................
```


### Button `share_own` — "There's food I can't touch either."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `food.trait.respected`, `food.trait.explained`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.trait.share_own` — accepted phrasings: "there is food i cannot touch either"; "same for me"; "me too actually"
  - the message must contain one of: `either`, `touch`, `too`
  - scored words: `touch`(1.2), `either`(1.5), `too`(1.0), `cannot`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.followup.share_own
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.followup.share_own   [34 chars]
    en  There's food I can't touch either.
    >>  ............................................
    pt  Também tem comida que eu não posso encostar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.trait.share_own`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `food.trait.share_own`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.trait.share_own
WHO    VILLAGER — what the player reads after pressing "There's food I can't touch either."
       spoken on: conversations.topic.food.trait.followup, button `share_own`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.company`: the villager accepts. Subject `food.dietary_trait`, polarity `mixed`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.share_own/1   [60 chars]
    en  Is there? Then you know the look people give you at a feast.
    >>  ............................................
    pt  Tem? Então você conhece o olhar que as pessoas dão num banquete.
    >>  ............................................
  dialogue.conversations.food.trait.share_own/2   [45 chars]
    en  Two of us dodging the same table. Solidarity.
    >>  ............................................
    pt  Nós dois desviando da mesma mesa. Solidariedade.
    >>  ............................................
  dialogue.conversations.food.trait.share_own/3   [60 chars]
    en  Good. Not good — you know. It's easier having company in it.
    >>  ............................................
    pt  Que bom. Não bom — você entendeu. É mais fácil ter companhia nisso.
    >>  ............................................
```


### Button `let_be` — "You don't have to explain it to me."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `food.trait.respected`, `food.trait.explained` · offered only once the villager has actually said `trait:dietary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.trait.let_be` — accepted phrasings: "you do not have to explain"; "no need to justify it"; "you owe me no explanation"
  - the message must contain one of: `explain`, `justify`, `need`
  - scored words: `explain`(1.5), `need`(0.8), `justify`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.followup.let_be
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.followup.let_be   [35 chars]
    en  You don't have to explain it to me.
    >>  ............................................
    pt  Você não precisa me explicar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2  _(recorded under topic `food.trait.let_be`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.trait.let_be
WHO    VILLAGER — what the player reads after pressing "You don't have to explain it to me."
       spoken on: conversations.topic.food.trait.followup, button `let_be`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.let_be`: the villager accepts. Subject `food.dietary_trait`, polarity `mixed`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.let_be/1   [74 chars]
    en  Thank you. I've explained it four hundred times and it never gets shorter.
    >>  ............................................
    pt  Obrigado. Já expliquei quatrocentas vezes e nunca fica mais curto.
    >>  ............................................
  dialogue.conversations.food.trait.let_be/2   [42 chars]
    en  No, I don't. That's a small freedom, that.
    >>  ............................................
    pt  Não, não preciso. Isso é uma pequena liberdade.
    >>  ............................................
  dialogue.conversations.food.trait.let_be/3   [54 chars]
    en  Appreciated. Let's talk about literally anything else.
    >>  ............................................
    pt  Agradeço. Vamos falar de literalmente qualquer outra coisa.
    >>  ............................................
```


### Button `leave` — "Right. Enough about plates."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `food.trait.respected`, `food.trait.explained` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.followup.leave   [27 chars]
    en  Right. Enough about plates.
    >>  ............................................
    pt  Certo. Chega de pratos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.trait.leave
WHO    VILLAGER — what the player reads after pressing "Right. Enough about plates."
       spoken on: conversations.topic.food.trait.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.left`: the villager accepts. Subject `food.dietary_trait`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.food.trait.mocked.followup / leave; conversations.topic.food.trait.respond / leave
```

```text
  dialogue.conversations.food.trait.leave/1   [39 chars]
    en  True enough. Not the cheeriest subject.
    >>  ............................................
    pt  Bem verdade. Não é o assunto mais animado.
    >>  ............................................
  dialogue.conversations.food.trait.leave/2   [42 chars]
    en  Right. Go and eat something I can't, %1$s.
    >>  ............................................
    pt  Certo. Vá comer algo que eu não posso, %1$s.
    >>  ............................................
  dialogue.conversations.food.trait.leave/3   [50 chars]
    en  Off you go. Mind the bakery, it's murder in there.
    >>  ............................................
    pt  Pode ir. Cuidado com a padaria, é uma tortura lá dentro.
    >>  ............................................
```

---


## `conversations.topic.food.trait.mocked.followup`

**Reached from 1 route(s):** `conversations.topic.food.trait.respond` / `mock`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.trait.mock` — e.g. "...It's not an excuse. It's my body, and it doesn't take requests."


```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.mocked.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.trait.mocked.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.trait.mocked.followup   [26 chars]
    en  So. You've had your laugh.
    >>  ............................................
    pt  Então. Você já riu.
    >>  ............................................
```


### Button `apologize` — "That was a rotten thing to say."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `food.trait.mocked` · offered only once the villager has actually said `player:mocked_trait`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.trait.mocked.apologize` — accepted phrasings: "that was a rotten thing to say"; "that was cruel of me"; "i should not have laughed"
  - the message must contain one of: `rotten`, `cruel`, `sorry`
  - scored words: `rotten`(1.5), `cruel`(1.2), `sorry`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.mocked.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.mocked.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.mocked.followup.apologize   [31 chars]
    en  That was a rotten thing to say.
    >>  ............................................
    pt  Foi uma coisa horrível de se dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4  _(recorded under topic `food.trait.mocked.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.trait.mocked.apologize
WHO    VILLAGER — what the player reads after pressing "That was a rotten thing to say."
       spoken on: conversations.topic.food.trait.mocked.followup, button `apologize`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.mocked.apologize`: the villager qualifys. Subject `food.dietary_trait`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.mocked.apologize/1   [51 chars]
    en  It was. ...But you came back to it, and most don't.
    >>  ............................................
    pt  Foi. ...Mas você voltou nisso, e a maioria não volta.
    >>  ............................................
  dialogue.conversations.food.trait.mocked.apologize/2   [68 chars]
    en  Quite. I've heard worse and I've heard it apologised for less, %1$s.
    >>  ............................................
    pt  Exato. Já ouvi pior e já ouvi menos pedido de desculpa, %1$s.
    >>  ............................................
  dialogue.conversations.food.trait.mocked.apologize/3   [40 chars]
    en  Thank you. Let's not do that part again.
    >>  ............................................
    pt  Obrigado. Vamos não repetir essa parte.
    >>  ............................................
```


### Button `ask_properly` — "Tell me what it's actually like."

*stance family `curiosity` · tone `plain` · outcome `qualified` · answers the beat(s) `food.trait.mocked` · offered only once the villager has actually said `trait:dietary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.trait.mocked.ask_properly` — accepted phrasings: "tell me what it is actually like"; "what is it really like"; "i want to understand it properly"
  - the message must contain one of: `actually`, `really`
  - scored words: `actually`(1.2), `really`(1.2), `like`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.mocked.followup.ask_properly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.mocked.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.mocked.followup.ask_properly   [32 chars]
    en  Tell me what it's actually like.
    >>  ............................................
    pt  Me conta como é de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1, trust +2  _(recorded under topic `food.trait.mocked.ask_properly`)_
- Does: session `turn`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.trait.mocked.ask_properly
WHO    VILLAGER — what the player reads after pressing "Tell me what it's actually like."
       spoken on: conversations.topic.food.trait.mocked.followup, button `ask_properly`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.mocked.ask_properly`: the villager discloses. Subject `food.dietary_trait`, polarity `mixed`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.mocked.ask_properly/1   [62 chars]
    en  ...Now you ask. Alright. It's arithmetic, every meal, forever.
    >>  ............................................
    pt  ...Agora você pergunta. Está bem. É conta, toda refeição, pra sempre.
    >>  ............................................
  dialogue.conversations.food.trait.mocked.ask_properly/2   [74 chars]
    en  It's being the difficult one at every table, %1$s. That's the whole of it.
    >>  ............................................
    pt  É ser o complicado em toda mesa, %1$s. É isso.
    >>  ............................................
  dialogue.conversations.food.trait.mocked.ask_properly/3   [67 chars]
    en  Ask me when you've not just laughed at it. But — soon. Ask me soon.
    >>  ............................................
    pt  Me pergunte quando não tiver acabado de rir. Mas — logo. Pergunte logo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  ...Now you ask. Alright. It's arithmetic, every meal, forever, %1$s.
    >>  ............................................
    pt  ...Agora você pergunta. Está bem. É aritmética, toda refeição, pra sempre, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  You asked properly, and I'd stopped expecting anybody to. Give me a moment.
    >>  ............................................
    pt  Você perguntou direito, e eu tinha parado de esperar que alguém perguntasse. Me dê um momento.
    >>  ............................................
  anxious.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  Now. Yes. It's every meal, and the counting is more tiring than the going without.
    >>  ............................................
    pt  Agora. Sim. É toda refeição, e contar cansa mais que ficar sem.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask. Alright. It's arithmetic, every meal, and I've had years to get quick at it.
    >>  ............................................
    pt  Agora você pergunta. Está bem. É aritmética, toda refeição, e eu tive anos pra ficar rápido.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Every meal, forever. It's a habit rather than a hardship, most days.
    >>  ............................................
    pt  Toda refeição, pra sempre. É hábito e não sofrimento, quase todo dia.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly. Sit down; there's no short version and no hurry.
    >>  ............................................
    pt  Você perguntou direito. Sente-se; não tem versão curta e não tem pressa.
    >>  ............................................
  confident.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask. Alright. It's arithmetic, every meal, forever.
    >>  ............................................
    pt  Agora você pergunta. Está bem. É aritmética, em toda refeição, pra sempre.
    >>  ............................................
  confident.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Now. Right. It's every meal, every day, and it does not get a day off.
    >>  ............................................
    pt  Agora. Certo. É toda refeição, todo dia, e não tira folga.
    >>  ............................................
  confident.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly, so I'll answer properly. It never stops.
    >>  ............................................
    pt  Você perguntou direito, então eu respondo direito. Nunca para.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask. Alright. It's arithmetic, every meal, forever.
    >>  ............................................
    pt  Agora você pergunta. Está bem. É aritmética, em toda refeição, pra sempre.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Now. Right. It's every meal, every day, and it does not get a day off.
    >>  ............................................
    pt  Agora. Certo. É toda refeição, todo dia, e não tira folga.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly, so I'll answer properly. It never stops.
    >>  ............................................
    pt  Você perguntou direito, então eu respondo direito. Nunca para.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask, %1$s. Alright. It's arithmetic, every meal, forever — and asking helps.
    >>  ............................................
    pt  Agora você pergunta, %1$s. Está bem. É aritmética, toda refeição, pra sempre — e perguntar ajuda.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  You asked properly. That's rarer than you'd think, and I'll tell you the whole of it.
    >>  ............................................
    pt  Você perguntou direito. É mais raro do que se imagina, e eu vou te contar tudo.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  Now. Right. Sit down and I'll explain it once, and then you'll know.
    >>  ............................................
    pt  Agora. Certo. Sente-se e eu explico uma vez, e aí você vai saber.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask, %1$s. Alright. It's arithmetic, every meal, forever — and asking helps.
    >>  ............................................
    pt  Agora você pergunta, %1$s. Está bem. É aritmética, toda refeição, pra sempre — e perguntar ajuda.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  You asked properly. That's rarer than you'd think, and I'll tell you the whole of it.
    >>  ............................................
    pt  Você perguntou direito. É mais raro do que se imagina, e eu vou te contar tudo.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  Now. Right. Sit down and I'll explain it once, and then you'll know.
    >>  ............................................
    pt  Agora. Certo. Sente-se e eu explico uma vez, e aí você vai saber.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask, %1$s. Alright. It's arithmetic, every meal, forever — and asking helps.
    >>  ............................................
    pt  Agora você pergunta, %1$s. Está bem. É aritmética, toda refeição, pra sempre — e perguntar ajuda.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  You asked properly. That's rarer than you'd think, and I'll tell you the whole of it.
    >>  ............................................
    pt  Você perguntou direito. É mais raro do que se imagina, e eu vou te contar tudo.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  Now. Right. Sit down and I'll explain it once, and then you'll know.
    >>  ............................................
    pt  Agora. Certo. Sente-se e eu explico uma vez, e aí você vai saber.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  ...Now you ask. Alright. It's arithmetic, every meal, forever, %1$s.
    >>  ............................................
    pt  ...Agora você pergunta. Está bem. É aritmética, toda refeição, pra sempre, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  You asked properly, and I'd stopped expecting anybody to. Give me a moment.
    >>  ............................................
    pt  Você perguntou direito, e eu tinha parado de esperar que alguém perguntasse. Me dê um momento.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  Now. Yes. It's every meal, and the counting is more tiring than the going without.
    >>  ............................................
    pt  Agora. Sim. É toda refeição, e contar cansa mais que ficar sem.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask. Alright. It's arithmetic, every meal, forever.
    >>  ............................................
    pt  Agora você pergunta. Está bem. É aritmética, em toda refeição, pra sempre.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Now. Right. It's every meal, every day, and it does not get a day off.
    >>  ............................................
    pt  Agora. Certo. É toda refeição, todo dia, e não tira folga.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly, so I'll answer properly. It never stops.
    >>  ............................................
    pt  Você perguntou direito, então eu respondo direito. Nunca para.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask. Alright. It's arithmetic, every meal, forever.
    >>  ............................................
    pt  Agora você pergunta. Está bem. É aritmética, em toda refeição, pra sempre.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Now. Right. It's every meal, every day, and it does not get a day off.
    >>  ............................................
    pt  Agora. Certo. É toda refeição, todo dia, e não tira folga.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly, so I'll answer properly. It never stops.
    >>  ............................................
    pt  Você perguntou direito, então eu respondo direito. Nunca para.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  ...Now you ask. Alright. It's arithmetic, every meal.
    >>  ............................................
    pt  ...Agora você pergunta. Está bem. É aritmética, toda refeição.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Every meal. Forever. That's it.
    >>  ............................................
    pt  Toda refeição. Pra sempre. É isso.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You asked properly. I'll answer properly.
    >>  ............................................
    pt  Você perguntou direito. Eu respondo direito.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask. Alright. It's arithmetic, every meal, and I've had years to get quick at it.
    >>  ............................................
    pt  Agora você pergunta. Está bem. É aritmética, toda refeição, e eu tive anos pra ficar rápido.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Every meal, forever. It's a habit rather than a hardship, most days.
    >>  ............................................
    pt  Toda refeição, pra sempre. É hábito e não sofrimento, quase todo dia.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly. Sit down; there's no short version and no hurry.
    >>  ............................................
    pt  Você perguntou direito. Sente-se; não tem versão curta e não tem pressa.
    >>  ............................................
  odd.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  ...Now you ask. Alright. It's arithmetic, every meal.
    >>  ............................................
    pt  ...Agora você pergunta. Está bem. É aritmética, toda refeição.
    >>  ............................................
  odd.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Every meal. Forever. That's it.
    >>  ............................................
    pt  Toda refeição. Pra sempre. É isso.
    >>  ............................................
  odd.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You asked properly. I'll answer properly.
    >>  ............................................
    pt  Você perguntou direito. Eu respondo direito.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask. Alright. It's arithmetic, every meal, and I've had years to get quick at it.
    >>  ............................................
    pt  Agora você pergunta. Está bem. É aritmética, toda refeição, e eu tive anos pra ficar rápido.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Every meal, forever. It's a habit rather than a hardship, most days.
    >>  ............................................
    pt  Toda refeição, pra sempre. É hábito e não sofrimento, quase todo dia.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly. Sit down; there's no short version and no hurry.
    >>  ............................................
    pt  Você perguntou direito. Sente-se; não tem versão curta e não tem pressa.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask! Alright. It's arithmetic. Every meal. Forever. Riveting stuff.
    >>  ............................................
    pt  Agora você pergunta! Está bem. É aritmética. Toda refeição. Pra sempre. Fascinante.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Now. Right. Every meal is a small sum and I've done about forty thousand of them.
    >>  ............................................
    pt  Agora. Certo. Toda refeição é uma continha e eu já fiz umas quarenta mil.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly! Good. It's arithmetic and nobody has ever wanted the details.
    >>  ............................................
    pt  Você perguntou direito! Bom. É aritmética e ninguém nunca quis os detalhes.
    >>  ............................................
  playful.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask! Alright. It's arithmetic. Every meal. Forever. Riveting stuff.
    >>  ............................................
    pt  Agora você pergunta! Está bem. É aritmética. Toda refeição. Pra sempre. Fascinante.
    >>  ............................................
  playful.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Now. Right. Every meal is a small sum and I've done about forty thousand of them.
    >>  ............................................
    pt  Agora. Certo. Toda refeição é uma continha e eu já fiz umas quarenta mil.
    >>  ............................................
  playful.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly! Good. It's arithmetic and nobody has ever wanted the details.
    >>  ............................................
    pt  Você perguntou direito! Bom. É aritmética e ninguém nunca quis os detalhes.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask. Alright. It's arithmetic, every meal, and I've had years to get quick at it.
    >>  ............................................
    pt  Agora você pergunta. Está bem. É aritmética, toda refeição, e eu tive anos pra ficar rápido.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Every meal, forever. It's a habit rather than a hardship, most days.
    >>  ............................................
    pt  Toda refeição, pra sempre. É hábito e não sofrimento, quase todo dia.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly. Sit down; there's no short version and no hurry.
    >>  ............................................
    pt  Você perguntou direito. Sente-se; não tem versão curta e não tem pressa.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  ...Now you ask. Alright. It's arithmetic, every meal, forever, %1$s.
    >>  ............................................
    pt  ...Agora você pergunta. Está bem. É aritmética, toda refeição, pra sempre, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  You asked properly, and I'd stopped expecting anybody to. Give me a moment.
    >>  ............................................
    pt  Você perguntou direito, e eu tinha parado de esperar que alguém perguntasse. Me dê um momento.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  Now. Yes. It's every meal, and the counting is more tiring than the going without.
    >>  ............................................
    pt  Agora. Sim. É toda refeição, e contar cansa mais que ficar sem.
    >>  ............................................
  shy.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  ...Now you ask. Alright. It's arithmetic, every meal.
    >>  ............................................
    pt  ...Agora você pergunta. Está bem. É aritmética, toda refeição.
    >>  ............................................
  shy.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Every meal. Forever. That's it.
    >>  ............................................
    pt  Toda refeição. Pra sempre. É isso.
    >>  ............................................
  shy.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You asked properly. I'll answer properly.
    >>  ............................................
    pt  Você perguntou direito. Eu respondo direito.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask! Alright. It's arithmetic. Every meal. Forever. Riveting stuff.
    >>  ............................................
    pt  Agora você pergunta! Está bem. É aritmética. Toda refeição. Pra sempre. Fascinante.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Now. Right. Every meal is a small sum and I've done about forty thousand of them.
    >>  ............................................
    pt  Agora. Certo. Toda refeição é uma continha e eu já fiz umas quarenta mil.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly! Good. It's arithmetic and nobody has ever wanted the details.
    >>  ............................................
    pt  Você perguntou direito! Bom. É aritmética e ninguém nunca quis os detalhes.
    >>  ............................................
  witty.dialogue.conversations.food.trait.mocked.ask_properly/1
    en  Now you ask! Alright. It's arithmetic. Every meal. Forever. Riveting stuff.
    >>  ............................................
    pt  Agora você pergunta! Está bem. É aritmética. Toda refeição. Pra sempre. Fascinante.
    >>  ............................................
  witty.dialogue.conversations.food.trait.mocked.ask_properly/2
    en  Now. Right. Every meal is a small sum and I've done about forty thousand of them.
    >>  ............................................
    pt  Agora. Certo. Toda refeição é uma continha e eu já fiz umas quarenta mil.
    >>  ............................................
  witty.dialogue.conversations.food.trait.mocked.ask_properly/3
    en  You've asked properly! Good. It's arithmetic and nobody has ever wanted the details.
    >>  ............................................
    pt  Você perguntou direito! Bom. É aritmética e ninguém nunca quis os detalhes.
    >>  ............................................
```

</details>


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `food.trait.mocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.mocked.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.mocked.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.mocked.followup.leave   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.trait.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.food.trait.mocked.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.left`: the villager accepts. Subject `food.dietary_trait`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.food.trait.followup / leave; conversations.topic.food.trait.respond / leave
```

> Written out in full under **`conversations.topic.food.trait.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.food.trait.respond`

**Reached from 5 route(s):** `conversations.cat.chitchat` / `food`; `conversations.cat.chitchat` / `food`; `conversations.cat.chitchat` / `food`; `conversations.cat.chitchat` / `food`; `conversations.cat.chitchat` / `food`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.trait.coeliac` — e.g. "Bread hates me. Actual bread. In a village that's half bakery. The gods have humor."
- `conversations.food.trait.diabetes` — e.g. "I have to watch the sweets. One cookie, the cleric says. Who bakes ONE cookie?"
- `conversations.food.trait.lactose` — e.g. "Keep the milk away from me, %1$s. Learned that lesson in front of the whole market. Twice."
- `conversations.food.trait.sirben` — e.g. "The sirben feast on the night wind, %1$s. We do not speak of the recipe. HRRK."
- `conversations.food.trait.vegetarian` — e.g. "No meat for me — never touch it. The pigs and I have an arrangement built on trust."


```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.trait.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.trait.respond   [30 chars]
    en  So that's my lot at the table.
    >>  ............................................
    pt  Então é essa a minha sina à mesa.
    >>  ............................................
```


### Button `respect` — "Then that's how it is. No fuss from me."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `food.trait.coeliac`, `food.trait.lactose`, `food.trait.diabetes`, `food.trait.vegetarian`, `food.trait.sirben` · offered only once the villager has actually said `trait:dietary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.trait.respect` — accepted phrasings: "no fuss from me"; "that is how it is"; "understood"; "fine by me"
  - the message must contain one of: `fuss`, `understood`, `fine`
  - scored words: `fuss`(1.5), `fine`(0.8), `understood`(1.2), `way`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.respond.respect   [39 chars]
    en  Then that's how it is. No fuss from me.
    >>  ............................................
    pt  Então é assim que é. Sem drama da minha parte.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.trait.respect`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `food.trait.respect`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.trait.followup`
- …where the player's next choices will be: "I'll find you something you can actually eat." | "There's food I can't touch either." | "You don't have to explain it to me." | "Right. Enough about plates."

```text
POOL   dialogue key: dialogue.conversations.food.trait.respect
WHO    VILLAGER — what the player reads after pressing "Then that's how it is. No fuss from me."
       spoken on: conversations.topic.food.trait.respond, button `respect`
       leaves the player on: conversations.topic.food.trait.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.respected`: the villager accepts. Subject `food.dietary_trait`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `trait:dietary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, self_disclosure, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.respect/1   [62 chars]
    en  ...Thank you. Most people want a reason, or a debate about it.
    >>  ............................................
    pt  ...Obrigado. A maioria quer um motivo, ou um debate sobre isso.
    >>  ............................................
  dialogue.conversations.food.trait.respect/2   [63 chars]
    en  No fuss. Do you know how rare that is at a village table, %1$s?
    >>  ............................................
    pt  Sem drama. Você sabe como isso é raro numa mesa de vila, %1$s?
    >>  ............................................
  dialogue.conversations.food.trait.respect/3   [58 chars]
    en  It is. It just is. Nice not to have to defend it for once.
    >>  ............................................
    pt  É sim. Simplesmente é. Bom não ter que defender isso, para variar.
    >>  ............................................
```


### Button `ask_about` — "How do you manage it, day to day?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `food.trait.coeliac`, `food.trait.lactose`, `food.trait.diabetes`, `food.trait.vegetarian`, `food.trait.sirben` · offered only once the villager has actually said `trait:dietary`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.trait.ask_about` — accepted phrasings: "how do you manage"; "how do you cope with it"; "how do you manage day to day"
  - the message must contain one of: `manage`, `cope`, `how`
  - scored words: `manage`(1.5), `cope`(1.5), `how`(0.5), `day`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.respond.ask_about
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.respond.ask_about   [33 chars]
    en  How do you manage it, day to day?
    >>  ............................................
    pt  Como você lida com isso, no dia a dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `food.trait.ask_about`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.trait.followup`
- …where the player's next choices will be: "I'll find you something you can actually eat." | "There's food I can't touch either." | "You don't have to explain it to me." | "Right. Enough about plates."

```text
POOL   dialogue key: dialogue.conversations.food.trait.ask_about
WHO    VILLAGER — what the player reads after pressing "How do you manage it, day to day?"
       spoken on: conversations.topic.food.trait.respond, button `ask_about`
       leaves the player on: conversations.topic.food.trait.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.explained`: the villager explains. Subject `food.dietary_trait`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `trait:dietary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, self_disclosure, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.ask_about/1   [64 chars]
    en  Carefully, and with a lot of reading labels I can't always read.
    >>  ............................................
    pt  Com cuidado, e lendo muitos rótulos que nem sempre consigo ler.
    >>  ............................................
  dialogue.conversations.food.trait.ask_about/2   [75 chars]
    en  You plan. Every meal, every feast day. It stops being a thought eventually.
    >>  ............................................
    pt  A gente planeja. Toda refeição, todo dia de festa. Em algum momento deixa de ser um pensamento.
    >>  ............................................
  dialogue.conversations.food.trait.ask_about/3   [77 chars]
    en  Badly, some weeks. Well enough, most. Thanks for asking rather than guessing.
    >>  ............................................
    pt  Mal, em algumas semanas. Bem o suficiente, na maioria. Obrigado por perguntar em vez de adivinhar.
    >>  ............................................
```


### Button `mock` — "Sounds like an excuse to be difficult."

*stance family `dismissal` · tone `hostile` · outcome `hurt` · answers the beat(s) `food.trait.coeliac`, `food.trait.lactose`, `food.trait.diabetes`, `food.trait.vegetarian`, `food.trait.sirben`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.trait.mock` — accepted phrasings: "sounds like an excuse"; "you are being difficult"; "that is just fussy"; "you are picky"
  - the message must contain one of: `excuse`, `difficult`, `fussy`, `picky`
  - scored words: `excuse`(1.5), `difficult`(1.5), `fussy`(1.5), `picky`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.respond.mock
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.respond.mock   [38 chars]
    en  Sounds like an excuse to be difficult.
    >>  ............................................
    pt  Parece desculpa para ser complicado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `food.trait.mock`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -5, tension +6, trust -3  _(recorded under topic `food.trait.mock`)_
- Does: session `turn`
- Then opens: `conversations.topic.food.trait.mocked.followup`
- …where the player's next choices will be: "That was a rotten thing to say." | "Tell me what it's actually like." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.food.trait.mock
WHO    VILLAGER — what the player reads after pressing "Sounds like an excuse to be difficult."
       spoken on: conversations.topic.food.trait.respond, button `mock`
       leaves the player on: conversations.topic.food.trait.mocked.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.mocked`: the villager hurts. Subject `food.dietary_trait`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `trait:dietary`, `player:mocked_trait` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.mock/1   [66 chars]
    en  ...It's not an excuse. It's my body, and it doesn't take requests.
    >>  ............................................
    pt  ...Não é desculpa. É o meu corpo, e ele não aceita pedidos.
    >>  ............................................
  dialogue.conversations.food.trait.mock/2   [67 chars]
    en  Difficult. Right. Come see me the night after I get it wrong, %1$s.
    >>  ............................................
    pt  Complicado. Certo. Venha me ver na noite depois que eu errar, %1$s.
    >>  ............................................
  dialogue.conversations.food.trait.mock/3   [82 chars]
    en  I'd swap with you gladly. Since I can't, I'll settle for you shutting up about it.
    >>  ............................................
    pt  Eu trocaria com você de bom grado. Como não posso, prefiro que você fique quieto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.trait.mock/1
    en  ...People say that a lot. It doesn't get easier to hear, %1$s.
    >>  ............................................
    pt  ...As pessoas dizem muito isso. Não fica mais fácil de ouvir, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.food.trait.mock/2
    en  It's not an excuse. I've been told it was for most of my life.
    >>  ............................................
    pt  Não é desculpa. Me disseram que era durante quase toda a minha vida.
    >>  ............................................
  anxious.dialogue.conversations.food.trait.mock/3
    en  ...Right. Sorry to have made it awkward.
    >>  ............................................
    pt  ...Certo. Desculpe por ter deixado a situação estranha.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.mock/1
    en  It's not an excuse. It's just how I'm put together.
    >>  ............................................
    pt  Não é desculpa. É só como eu sou montado.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.mock/2
    en  ...Aye, well. My body's had its say and I stopped arguing years ago.
    >>  ............................................
    pt  ...É, bom. Meu corpo já falou e eu parei de discutir anos atrás.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.mock/3
    en  Right. I'll eat around it, same as always.
    >>  ............................................
    pt  Certo. Vou comer em volta, como sempre.
    >>  ............................................
  confident.dialogue.conversations.food.trait.mock/1
    en  It's not an excuse. It's my body, and it doesn't take requests.
    >>  ............................................
    pt  Não é desculpa. É o meu corpo, e ele não aceita pedidos.
    >>  ............................................
  confident.dialogue.conversations.food.trait.mock/2
    en  I'm not being difficult. I'm telling you what happens if I eat it.
    >>  ............................................
    pt  Não estou sendo difícil. Estou dizendo o que acontece se eu comer.
    >>  ............................................
  confident.dialogue.conversations.food.trait.mock/3
    en  ...Right. I'll bring my own next time and say nothing about it.
    >>  ............................................
    pt  ...Certo. Da próxima eu trago o meu e não digo nada.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.mock/1
    en  It's not an excuse. It's my body, and it doesn't take requests.
    >>  ............................................
    pt  Não é desculpa. É o meu corpo, e ele não aceita pedidos.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.mock/2
    en  I'm not being difficult. I'm telling you what happens if I eat it.
    >>  ............................................
    pt  Não estou sendo difícil. Estou dizendo o que acontece se eu comer.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.mock/3
    en  ...Right. I'll bring my own next time and say nothing about it.
    >>  ............................................
    pt  ...Certo. Da próxima eu trago o meu e não digo nada.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.mock/1
    en  ...It's not an excuse, %1$s. I'd eat it if I could, gladly.
    >>  ............................................
    pt  ...Não é desculpa, %1$s. Eu comeria se pudesse, com prazer.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.mock/2
    en  That's a hard thing to hear from you, of all people.
    >>  ............................................
    pt  É duro de ouvir de você, logo de você.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.mock/3
    en  ...I'll not make a thing of it. But it isn't an excuse.
    >>  ............................................
    pt  ...Não vou fazer disso um caso. Mas não é desculpa.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.mock/1
    en  ...It's not an excuse, %1$s. I'd eat it if I could, gladly.
    >>  ............................................
    pt  ...Não é desculpa, %1$s. Eu comeria se pudesse, com prazer.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.mock/2
    en  That's a hard thing to hear from you, of all people.
    >>  ............................................
    pt  É duro de ouvir de você, logo de você.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.mock/3
    en  ...I'll not make a thing of it. But it isn't an excuse.
    >>  ............................................
    pt  ...Não vou fazer disso um caso. Mas não é desculpa.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.mock/1
    en  ...It's not an excuse, %1$s. I'd eat it if I could, gladly.
    >>  ............................................
    pt  ...Não é desculpa, %1$s. Eu comeria se pudesse, com prazer.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.mock/2
    en  That's a hard thing to hear from you, of all people.
    >>  ............................................
    pt  É duro de ouvir de você, logo de você.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.mock/3
    en  ...I'll not make a thing of it. But it isn't an excuse.
    >>  ............................................
    pt  ...Não vou fazer disso um caso. Mas não é desculpa.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.mock/1
    en  ...People say that a lot. It doesn't get easier to hear, %1$s.
    >>  ............................................
    pt  ...As pessoas dizem muito isso. Não fica mais fácil de ouvir, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.mock/2
    en  It's not an excuse. I've been told it was for most of my life.
    >>  ............................................
    pt  Não é desculpa. Me disseram que era durante quase toda a minha vida.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.mock/3
    en  ...Right. Sorry to have made it awkward.
    >>  ............................................
    pt  ...Certo. Desculpe por ter deixado a situação estranha.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.mock/1
    en  It's not an excuse. It's my body, and it doesn't take requests.
    >>  ............................................
    pt  Não é desculpa. É o meu corpo, e ele não aceita pedidos.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.mock/2
    en  I'm not being difficult. I'm telling you what happens if I eat it.
    >>  ............................................
    pt  Não estou sendo difícil. Estou dizendo o que acontece se eu comer.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.mock/3
    en  ...Right. I'll bring my own next time and say nothing about it.
    >>  ............................................
    pt  ...Certo. Da próxima eu trago o meu e não digo nada.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.mock/1
    en  It's not an excuse. It's my body, and it doesn't take requests.
    >>  ............................................
    pt  Não é desculpa. É o meu corpo, e ele não aceita pedidos.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.mock/2
    en  I'm not being difficult. I'm telling you what happens if I eat it.
    >>  ............................................
    pt  Não estou sendo difícil. Estou dizendo o que acontece se eu comer.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.mock/3
    en  ...Right. I'll bring my own next time and say nothing about it.
    >>  ............................................
    pt  ...Certo. Da próxima eu trago o meu e não digo nada.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.mock/1
    en  ...It isn't an excuse.
    >>  ............................................
    pt  ...Não é desculpa.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.mock/2
    en  No. It's what my body does. That's all.
    >>  ............................................
    pt  Não. É o que o meu corpo faz. Só isso.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.mock/3
    en  ...Right. I'll not mention it again.
    >>  ............................................
    pt  ...Certo. Não menciono de novo.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.mock/1
    en  It's not an excuse. It's just how I'm put together.
    >>  ............................................
    pt  Não é desculpa. É só como eu sou montado.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.mock/2
    en  ...Aye, well. My body's had its say and I stopped arguing years ago.
    >>  ............................................
    pt  ...É, bom. Meu corpo já falou e eu parei de discutir anos atrás.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.mock/3
    en  Right. I'll eat around it, same as always.
    >>  ............................................
    pt  Certo. Vou comer em volta, como sempre.
    >>  ............................................
  odd.dialogue.conversations.food.trait.mock/1
    en  ...It isn't an excuse.
    >>  ............................................
    pt  ...Não é desculpa.
    >>  ............................................
  odd.dialogue.conversations.food.trait.mock/2
    en  No. It's what my body does. That's all.
    >>  ............................................
    pt  Não. É o que o meu corpo faz. Só isso.
    >>  ............................................
  odd.dialogue.conversations.food.trait.mock/3
    en  ...Right. I'll not mention it again.
    >>  ............................................
    pt  ...Certo. Não menciono de novo.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.mock/1
    en  It's not an excuse. It's just how I'm put together.
    >>  ............................................
    pt  Não é desculpa. É só como eu sou montado.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.mock/2
    en  ...Aye, well. My body's had its say and I stopped arguing years ago.
    >>  ............................................
    pt  ...É, bom. Meu corpo já falou e eu parei de discutir anos atrás.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.mock/3
    en  Right. I'll eat around it, same as always.
    >>  ............................................
    pt  Certo. Vou comer em volta, como sempre.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.mock/1
    en  ...Ha. Yes. I invented it for the attention. Obviously.
    >>  ............................................
    pt  ...Ha. Sim. Eu inventei pra chamar atenção. Obviamente.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.mock/2
    en  Right! An excuse. What a treat, being told what my own stomach is up to.
    >>  ............................................
    pt  Certo! Uma desculpa. Que delícia, me dizerem o que o meu estômago está fazendo.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.mock/3
    en  ...Marvellous. I'll go and be fussy elsewhere.
    >>  ............................................
    pt  ...Maravilhoso. Vou ser chato em outro lugar.
    >>  ............................................
  playful.dialogue.conversations.food.trait.mock/1
    en  ...Ha. Yes. I invented it for the attention. Obviously.
    >>  ............................................
    pt  ...Ha. Sim. Eu inventei pra chamar atenção. Obviamente.
    >>  ............................................
  playful.dialogue.conversations.food.trait.mock/2
    en  Right! An excuse. What a treat, being told what my own stomach is up to.
    >>  ............................................
    pt  Certo! Uma desculpa. Que delícia, me dizerem o que o meu estômago está fazendo.
    >>  ............................................
  playful.dialogue.conversations.food.trait.mock/3
    en  ...Marvellous. I'll go and be fussy elsewhere.
    >>  ............................................
    pt  ...Maravilhoso. Vou ser chato em outro lugar.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.mock/1
    en  It's not an excuse. It's just how I'm put together.
    >>  ............................................
    pt  Não é desculpa. É só como eu sou montado.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.mock/2
    en  ...Aye, well. My body's had its say and I stopped arguing years ago.
    >>  ............................................
    pt  ...É, bom. Meu corpo já falou e eu parei de discutir anos atrás.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.mock/3
    en  Right. I'll eat around it, same as always.
    >>  ............................................
    pt  Certo. Vou comer em volta, como sempre.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.mock/1
    en  ...People say that a lot. It doesn't get easier to hear, %1$s.
    >>  ............................................
    pt  ...As pessoas dizem muito isso. Não fica mais fácil de ouvir, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.mock/2
    en  It's not an excuse. I've been told it was for most of my life.
    >>  ............................................
    pt  Não é desculpa. Me disseram que era durante quase toda a minha vida.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.mock/3
    en  ...Right. Sorry to have made it awkward.
    >>  ............................................
    pt  ...Certo. Desculpe por ter deixado a situação estranha.
    >>  ............................................
  shy.dialogue.conversations.food.trait.mock/1
    en  ...It isn't an excuse.
    >>  ............................................
    pt  ...Não é desculpa.
    >>  ............................................
  shy.dialogue.conversations.food.trait.mock/2
    en  No. It's what my body does. That's all.
    >>  ............................................
    pt  Não. É o que o meu corpo faz. Só isso.
    >>  ............................................
  shy.dialogue.conversations.food.trait.mock/3
    en  ...Right. I'll not mention it again.
    >>  ............................................
    pt  ...Certo. Não menciono de novo.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.mock/1
    en  ...Ha. Yes. I invented it for the attention. Obviously.
    >>  ............................................
    pt  ...Ha. Sim. Eu inventei pra chamar atenção. Obviamente.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.mock/2
    en  Right! An excuse. What a treat, being told what my own stomach is up to.
    >>  ............................................
    pt  Certo! Uma desculpa. Que delícia, me dizerem o que o meu estômago está fazendo.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.mock/3
    en  ...Marvellous. I'll go and be fussy elsewhere.
    >>  ............................................
    pt  ...Maravilhoso. Vou ser chato em outro lugar.
    >>  ............................................
  witty.dialogue.conversations.food.trait.mock/1
    en  ...Ha. Yes. I invented it for the attention. Obviously.
    >>  ............................................
    pt  ...Ha. Sim. Eu inventei pra chamar atenção. Obviamente.
    >>  ............................................
  witty.dialogue.conversations.food.trait.mock/2
    en  Right! An excuse. What a treat, being told what my own stomach is up to.
    >>  ............................................
    pt  Certo! Uma desculpa. Que delícia, me dizerem o que o meu estômago está fazendo.
    >>  ............................................
  witty.dialogue.conversations.food.trait.mock/3
    en  ...Marvellous. I'll go and be fussy elsewhere.
    >>  ............................................
    pt  ...Maravilhoso. Vou ser chato em outro lugar.
    >>  ............................................
```

</details>


### Button `leave` — "Understood. I'll leave it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `food.trait.coeliac`, `food.trait.lactose`, `food.trait.diabetes`, `food.trait.vegetarian`, `food.trait.sirben` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.trait.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.trait.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.trait.respond.leave   [26 chars]
    en  Understood. I'll leave it.
    >>  ............................................
    pt  Entendido. Deixo quieto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.trait.leave
WHO    VILLAGER — what the player reads after pressing "Understood. I'll leave it."
       spoken on: conversations.topic.food.trait.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.left`: the villager accepts. Subject `food.dietary_trait`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.food.trait.followup / leave; conversations.topic.food.trait.mocked.followup / leave
```

> Written out in full under **`conversations.topic.food.trait.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.food.young.respond`

**Reached from 2 route(s):** `conversations.cat.chitchat` / `food`; `conversations.cat.chitchat` / `food`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.food.child` — e.g. "Cookies first, THEN bread. That's the right order and nobody listens."
- `conversations.food.teen` — e.g. "Whatever's in the pantry when nobody's looking."


```text
POOL   dialogue key: dialogue.conversations.topic.food.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.food.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.food.young.respond   [19 chars]
    en  That's what I like!
    >>  ............................................
    pt  É isso que eu gosto!
    >>  ............................................
```


### Button `delight` — "That is an excellent choice."

*stance family `encouragement` · tone `playful` · answers the beat(s) `food.child.to.food.young`, `food.teen.to.food.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.young.delight` — accepted phrasings: "that is an excellent choice"; "excellent choice"; "i agree completely"
  - the message must contain one of: `excellent`, `choice`, `agree`
  - scored words: `excellent`(1.5), `choice`(1.2), `agree`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.food.young.respond.delight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.young.respond.delight   [28 chars]
    en  That is an excellent choice.
    >>  ............................................
    pt  Essa é uma escolha excelente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `food.young.delight`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `food.young.delight`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.young.delight
WHO    VILLAGER — what the player reads after pressing "That is an excellent choice."
       spoken on: conversations.topic.food.young.respond, button `delight`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.young.delight.terminal`: the villager celebrates. Subject `food.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.young.delight/1   [40 chars]
    en  It IS! Everyone says it's not but it IS.
    >>  ............................................
    pt  É MESMO! Todo mundo diz que não mas É.
    >>  ............................................
  dialogue.conversations.food.young.delight/2   [27 chars]
    en  You get it! Nobody gets it!
    >>  ............................................
    pt  Você entendeu! Ninguém entende!
    >>  ............................................
  dialogue.conversations.food.young.delight/3   [13 chars]
    en  Right? RIGHT?
    >>  ............................................
    pt  Né? NÉ?
    >>  ............................................
```


### Button `ask` — "What else do you like?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `food.child.to.food.young`, `food.teen.to.food.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.young.ask` — accepted phrasings: "what else do you like"; "what other food"; "anything else you like"
  - the message must contain one of: `else`, `other`
  - scored words: `else`(1.5), `other`(1.2), `what`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.food.young.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.young.respond.ask   [22 chars]
    en  What else do you like?
    >>  ............................................
    pt  Do que mais você gosta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `food.young.ask`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.young.ask
WHO    VILLAGER — what the player reads after pressing "What else do you like?"
       spoken on: conversations.topic.food.young.respond, button `ask`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.young.ask.terminal`: the villager asks. Subject `food.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.young.ask/1   [55 chars]
    en  Everything except the green ones. And the squishy ones.
    >>  ............................................
    pt  Tudo menos as verdes. E as moles.
    >>  ............................................
  dialogue.conversations.food.young.ask/2   [39 chars]
    en  Bread. Also bread. And sometimes bread.
    >>  ............................................
    pt  Pão. Também pão. E às vezes pão.
    >>  ............................................
  dialogue.conversations.food.young.ask/3   [39 chars]
    en  Whatever's still warm when I get there.
    >>  ............................................
    pt  O que estiver quente quando eu chegar.
    >>  ............................................
```


### Button `dismiss` — "That's not proper food."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `food.child.to.food.young`, `food.teen.to.food.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `food.young.dismiss` — accepted phrasings: "that is not proper food"; "that is not real food"; "hardly proper food"
  - the message must contain one of: `proper`, `real`
  - scored words: `proper`(1.5), `real`(1.2), `food`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.food.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.young.respond.dismiss   [23 chars]
    en  That's not proper food.
    >>  ............................................
    pt  Isso não é comida de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `food.young.dismiss`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `food.young.dismiss`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.young.dismiss
WHO    VILLAGER — what the player reads after pressing "That's not proper food."
       spoken on: conversations.topic.food.young.respond, button `dismiss`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.young.dismiss.terminal`: the villager dismisss. Subject `food.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.young.dismiss/1   [18 chars]
    en  It IS proper food!
    >>  ............................................
    pt  É comida de verdade SIM!
    >>  ............................................
  dialogue.conversations.food.young.dismiss/2   [25 chars]
    en  You sound like my mother.
    >>  ............................................
    pt  Você parece a minha mãe.
    >>  ............................................
  dialogue.conversations.food.young.dismiss/3   [21 chars]
    en  ...Fine. More for me.
    >>  ............................................
    pt  ...Tá. Sobra mais para mim.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. It's what we ate at home, %1$s.
    >>  ............................................
    pt  É comida de verdade, sim. É o que a gente comia em casa, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.food.young.dismiss/2
    en  ...Don't say that about it. Please.
    >>  ............................................
    pt  ...Não fale assim dela. Por favor.
    >>  ............................................
  anxious.dialogue.conversations.food.young.dismiss/3
    en  Right. I won't offer you any next time.
    >>  ............................................
    pt  Certo. Da próxima eu não te ofereço.
    >>  ............................................
  athletic.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. It's kept people going a long while.
    >>  ............................................
    pt  É comida de verdade, sim. Sustentou gente por muito tempo.
    >>  ............................................
  athletic.dialogue.conversations.food.young.dismiss/2
    en  ...Aye, well. Everyone's idea of proper is their mother's.
    >>  ............................................
    pt  ...É, bom. A ideia de comida de verdade de cada um é a da mãe.
    >>  ............................................
  athletic.dialogue.conversations.food.young.dismiss/3
    en  Right. There's no arguing anyone into liking a thing.
    >>  ............................................
    pt  Certo. Não se convence ninguém a gostar de nada.
    >>  ............................................
  confident.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. I eat it every day and I'm still standing.
    >>  ............................................
    pt  É comida de verdade, sim. Eu como todo dia e continuo de pé.
    >>  ............................................
  confident.dialogue.conversations.food.young.dismiss/2
    en  That's what proper food is where I come from.
    >>  ............................................
    pt  É isso que é comida de verdade de onde eu venho.
    >>  ............................................
  confident.dialogue.conversations.food.young.dismiss/3
    en  ...Right. Then you eat yours and I'll eat mine.
    >>  ............................................
    pt  ...Certo. Então você come a sua e eu como a minha.
    >>  ............................................
  crabby.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. I eat it every day and I'm still standing.
    >>  ............................................
    pt  É comida de verdade, sim. Eu como todo dia e continuo de pé.
    >>  ............................................
  crabby.dialogue.conversations.food.young.dismiss/2
    en  That's what proper food is where I come from.
    >>  ............................................
    pt  É isso que é comida de verdade de onde eu venho.
    >>  ............................................
  crabby.dialogue.conversations.food.young.dismiss/3
    en  ...Right. Then you eat yours and I'll eat mine.
    >>  ............................................
    pt  ...Certo. Então você come a sua e eu como a minha.
    >>  ............................................
  extroverted.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food, %1$s. My gran made it and I'd not hear a word against it.
    >>  ............................................
    pt  É comida de verdade, sim, %1$s. Minha avó fazia e eu não aceito uma palavra contra.
    >>  ............................................
  extroverted.dialogue.conversations.food.young.dismiss/2
    en  You'd like it if somebody made it for you properly. I'd make it for you.
    >>  ............................................
    pt  Você gostaria se alguém fizesse direito pra você. Eu faria pra você.
    >>  ............................................
  extroverted.dialogue.conversations.food.young.dismiss/3
    en  ...Right. I'll not argue. But come and eat some before you decide.
    >>  ............................................
    pt  ...Certo. Não vou discutir. Mas venha comer antes de decidir.
    >>  ............................................
  flirty.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food, %1$s. My gran made it and I'd not hear a word against it.
    >>  ............................................
    pt  É comida de verdade, sim, %1$s. Minha avó fazia e eu não aceito uma palavra contra.
    >>  ............................................
  flirty.dialogue.conversations.food.young.dismiss/2
    en  You'd like it if somebody made it for you properly. I'd make it for you.
    >>  ............................................
    pt  Você gostaria se alguém fizesse direito pra você. Eu faria pra você.
    >>  ............................................
  flirty.dialogue.conversations.food.young.dismiss/3
    en  ...Right. I'll not argue. But come and eat some before you decide.
    >>  ............................................
    pt  ...Certo. Não vou discutir. Mas venha comer antes de decidir.
    >>  ............................................
  friendly.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food, %1$s. My gran made it and I'd not hear a word against it.
    >>  ............................................
    pt  É comida de verdade, sim, %1$s. Minha avó fazia e eu não aceito uma palavra contra.
    >>  ............................................
  friendly.dialogue.conversations.food.young.dismiss/2
    en  You'd like it if somebody made it for you properly. I'd make it for you.
    >>  ............................................
    pt  Você gostaria se alguém fizesse direito pra você. Eu faria pra você.
    >>  ............................................
  friendly.dialogue.conversations.food.young.dismiss/3
    en  ...Right. I'll not argue. But come and eat some before you decide.
    >>  ............................................
    pt  ...Certo. Não vou discutir. Mas venha comer antes de decidir.
    >>  ............................................
  gloomy.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. It's what we ate at home, %1$s.
    >>  ............................................
    pt  É comida de verdade, sim. É o que a gente comia em casa, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.food.young.dismiss/2
    en  ...Don't say that about it. Please.
    >>  ............................................
    pt  ...Não fale assim dela. Por favor.
    >>  ............................................
  gloomy.dialogue.conversations.food.young.dismiss/3
    en  Right. I won't offer you any next time.
    >>  ............................................
    pt  Certo. Da próxima eu não te ofereço.
    >>  ............................................
  greedy.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. I eat it every day and I'm still standing.
    >>  ............................................
    pt  É comida de verdade, sim. Eu como todo dia e continuo de pé.
    >>  ............................................
  greedy.dialogue.conversations.food.young.dismiss/2
    en  That's what proper food is where I come from.
    >>  ............................................
    pt  É isso que é comida de verdade de onde eu venho.
    >>  ............................................
  greedy.dialogue.conversations.food.young.dismiss/3
    en  ...Right. Then you eat yours and I'll eat mine.
    >>  ............................................
    pt  ...Certo. Então você come a sua e eu como a minha.
    >>  ............................................
  grumpy.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. I eat it every day and I'm still standing.
    >>  ............................................
    pt  É comida de verdade, sim. Eu como todo dia e continuo de pé.
    >>  ............................................
  grumpy.dialogue.conversations.food.young.dismiss/2
    en  That's what proper food is where I come from.
    >>  ............................................
    pt  É isso que é comida de verdade de onde eu venho.
    >>  ............................................
  grumpy.dialogue.conversations.food.young.dismiss/3
    en  ...Right. Then you eat yours and I'll eat mine.
    >>  ............................................
    pt  ...Certo. Então você come a sua e eu como a minha.
    >>  ............................................
  introverted.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food.
    >>  ............................................
    pt  É comida de verdade, sim.
    >>  ............................................
  introverted.dialogue.conversations.food.young.dismiss/2
    en  ...I like it.
    >>  ............................................
    pt  ...Eu gosto.
    >>  ............................................
  introverted.dialogue.conversations.food.young.dismiss/3
    en  It's what we have. It's fine.
    >>  ............................................
    pt  É o que a gente tem. Está bom.
    >>  ............................................
  lazy.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. It's kept people going a long while.
    >>  ............................................
    pt  É comida de verdade, sim. Sustentou gente por muito tempo.
    >>  ............................................
  lazy.dialogue.conversations.food.young.dismiss/2
    en  ...Aye, well. Everyone's idea of proper is their mother's.
    >>  ............................................
    pt  ...É, bom. A ideia de comida de verdade de cada um é a da mãe.
    >>  ............................................
  lazy.dialogue.conversations.food.young.dismiss/3
    en  Right. There's no arguing anyone into liking a thing.
    >>  ............................................
    pt  Certo. Não se convence ninguém a gostar de nada.
    >>  ............................................
  odd.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food.
    >>  ............................................
    pt  É comida de verdade, sim.
    >>  ............................................
  odd.dialogue.conversations.food.young.dismiss/2
    en  ...I like it.
    >>  ............................................
    pt  ...Eu gosto.
    >>  ............................................
  odd.dialogue.conversations.food.young.dismiss/3
    en  It's what we have. It's fine.
    >>  ............................................
    pt  É o que a gente tem. Está bom.
    >>  ............................................
  peaceful.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. It's kept people going a long while.
    >>  ............................................
    pt  É comida de verdade, sim. Sustentou gente por muito tempo.
    >>  ............................................
  peaceful.dialogue.conversations.food.young.dismiss/2
    en  ...Aye, well. Everyone's idea of proper is their mother's.
    >>  ............................................
    pt  ...É, bom. A ideia de comida de verdade de cada um é a da mãe.
    >>  ............................................
  peaceful.dialogue.conversations.food.young.dismiss/3
    en  Right. There's no arguing anyone into liking a thing.
    >>  ............................................
    pt  Certo. Não se convence ninguém a gostar de nada.
    >>  ............................................
  peppy.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food! You've just never had it made properly.
    >>  ............................................
    pt  É comida de verdade, sim! Você é que nunca comeu bem feita.
    >>  ............................................
  peppy.dialogue.conversations.food.young.dismiss/2
    en  Right! Not proper food. Says the person who has never once cooked it.
    >>  ............................................
    pt  Certo! Não é comida de verdade. Diz quem nunca cozinhou.
    >>  ............................................
  peppy.dialogue.conversations.food.young.dismiss/3
    en  ...I'll convert you one day. See if I don't.
    >>  ............................................
    pt  ...Um dia eu te converto. Você vai ver.
    >>  ............................................
  playful.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food! You've just never had it made properly.
    >>  ............................................
    pt  É comida de verdade, sim! Você é que nunca comeu bem feita.
    >>  ............................................
  playful.dialogue.conversations.food.young.dismiss/2
    en  Right! Not proper food. Says the person who has never once cooked it.
    >>  ............................................
    pt  Certo! Não é comida de verdade. Diz quem nunca cozinhou.
    >>  ............................................
  playful.dialogue.conversations.food.young.dismiss/3
    en  ...I'll convert you one day. See if I don't.
    >>  ............................................
    pt  ...Um dia eu te converto. Você vai ver.
    >>  ............................................
  relaxed.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. It's kept people going a long while.
    >>  ............................................
    pt  É comida de verdade, sim. Sustentou gente por muito tempo.
    >>  ............................................
  relaxed.dialogue.conversations.food.young.dismiss/2
    en  ...Aye, well. Everyone's idea of proper is their mother's.
    >>  ............................................
    pt  ...É, bom. A ideia de comida de verdade de cada um é a da mãe.
    >>  ............................................
  relaxed.dialogue.conversations.food.young.dismiss/3
    en  Right. There's no arguing anyone into liking a thing.
    >>  ............................................
    pt  Certo. Não se convence ninguém a gostar de nada.
    >>  ............................................
  sensitive.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food. It's what we ate at home, %1$s.
    >>  ............................................
    pt  É comida de verdade, sim. É o que a gente comia em casa, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.food.young.dismiss/2
    en  ...Don't say that about it. Please.
    >>  ............................................
    pt  ...Não fale assim dela. Por favor.
    >>  ............................................
  sensitive.dialogue.conversations.food.young.dismiss/3
    en  Right. I won't offer you any next time.
    >>  ............................................
    pt  Certo. Da próxima eu não te ofereço.
    >>  ............................................
  shy.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food.
    >>  ............................................
    pt  É comida de verdade, sim.
    >>  ............................................
  shy.dialogue.conversations.food.young.dismiss/2
    en  ...I like it.
    >>  ............................................
    pt  ...Eu gosto.
    >>  ............................................
  shy.dialogue.conversations.food.young.dismiss/3
    en  It's what we have. It's fine.
    >>  ............................................
    pt  É o que a gente tem. Está bom.
    >>  ............................................
  upbeat.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food! You've just never had it made properly.
    >>  ............................................
    pt  É comida de verdade, sim! Você é que nunca comeu bem feita.
    >>  ............................................
  upbeat.dialogue.conversations.food.young.dismiss/2
    en  Right! Not proper food. Says the person who has never once cooked it.
    >>  ............................................
    pt  Certo! Não é comida de verdade. Diz quem nunca cozinhou.
    >>  ............................................
  upbeat.dialogue.conversations.food.young.dismiss/3
    en  ...I'll convert you one day. See if I don't.
    >>  ............................................
    pt  ...Um dia eu te converto. Você vai ver.
    >>  ............................................
  witty.dialogue.conversations.food.young.dismiss/1
    en  It IS proper food! You've just never had it made properly.
    >>  ............................................
    pt  É comida de verdade, sim! Você é que nunca comeu bem feita.
    >>  ............................................
  witty.dialogue.conversations.food.young.dismiss/2
    en  Right! Not proper food. Says the person who has never once cooked it.
    >>  ............................................
    pt  Certo! Não é comida de verdade. Diz quem nunca cozinhou.
    >>  ............................................
  witty.dialogue.conversations.food.young.dismiss/3
    en  ...I'll convert you one day. See if I don't.
    >>  ............................................
    pt  ...Um dia eu te converto. Você vai ver.
    >>  ............................................
```

</details>


### Button `leave` — "Off you go, then."

*stance family `exit` · tone `plain` · answers the beat(s) `food.child.to.food.young`, `food.teen.to.food.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.food.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.food.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.food.young.respond.leave   [17 chars]
    en  Off you go, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.young.leave
WHO    VILLAGER — what the player reads after pressing "Off you go, then."
       spoken on: conversations.topic.food.young.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.young.leave.terminal`: the villager accepts. Subject `food.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.food.young.leave/1   [25 chars]
    en  Bye! I'm going to go eat.
    >>  ............................................
    pt  Tchau! Vou comer.
    >>  ............................................
  dialogue.conversations.food.young.leave/2   [9 chars]
    en  Okay bye!
    >>  ............................................
    pt  Tá, tchau!
    >>  ............................................
  dialogue.conversations.food.young.leave/3   [14 chars]
    en  See you, %1$s!
    >>  ............................................
    pt  Até mais, %1$s!
    >>  ............................................
```

---

