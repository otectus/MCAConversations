# Topic: village

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `village` |
| Opened from | question `conversations.cat.village`, button `village` |
| Depth class (its heart budget) | `standard` |
| Returns to | `conversations.cat.village` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `curiosity`, `practical_help`, `dismissal`, `humor`, `self_disclosure`, `exit` |
| Narrative arc | `village`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.village`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.village.village
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.village.village   [27 chars]
    en  What's it like living here?
    >>  ............................................
    pt  Como é morar aqui?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.village.resume.followup`](#conversations-arc-village-resume-followup)
- [`conversations.arc.village.resume.respond`](#conversations-arc-village-resume-respond)
- [`conversations.scene.village.after_something_happened.respond`](#conversations-scene-village-after-something-happened-respond)
- [`conversations.scene.village.change_settled.respond`](#conversations-scene-village-change-settled-respond)
- [`conversations.scene.village.followup`](#conversations-scene-village-followup)
- [`conversations.scene.village.getting_bigger.respond`](#conversations-scene-village-getting-bigger-respond)
- [`conversations.scene.village.still_placing_it.named_it.respond`](#conversations-scene-village-still-placing-it-named-it-respond)
- [`conversations.scene.village.still_placing_it.respond`](#conversations-scene-village-still-placing-it-respond)
- [`conversations.topic.village.again.respond`](#conversations-topic-village-again-respond)
- [`conversations.topic.village.fault.followup`](#conversations-topic-village-fault-followup)
- [`conversations.topic.village.insulted.followup`](#conversations-topic-village-insulted-followup)
- [`conversations.topic.village.none.respond`](#conversations-topic-village-none-respond)
- [`conversations.topic.village.praised.followup`](#conversations-topic-village-praised-followup)
- [`conversations.topic.village.respond`](#conversations-topic-village-respond)
- [`conversations.topic.village.settled.followup`](#conversations-topic-village-settled-followup)
- [`conversations.topic.village.toddler.respond`](#conversations-topic-village-toddler-respond)
- [`conversations.topic.village.young.respond`](#conversations-topic-village-young-respond)

---

## `conversations.arc.village.resume.followup`

**Reached from 3 route(s):** `conversations.arc.village.resume.respond` / `when`; `conversations.arc.village.resume.respond` / `still_stands`; `conversations.arc.village.resume.respond` / `cannot`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.resume.cannot` — e.g. "Then say so and we're square. It's the not-saying that costs people here."
- `conversations.village.resume.still_stands` — e.g. "Then I'll stop being surprised about it and start counting on it."
- `conversations.village.resume.when` — e.g. "Any morning that isn't market day. I'll have the tools out either way."


```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.village.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.village.resume.followup   [29 chars]
    en  And that's where we leave it.
    >>  ............................................
    pt  E é aí que a gente para.
    >>  ............................................
```


### Button `thank_you_for_telling` — "Thank you for keeping me in it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `village.resume.when`, `village.resume.still_stands`, `village.resume.cannot`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.resume.thank_you_for_telling` — accepted phrasings: "thank you for keeping me in it"; "thanks for keeping me informed"; "i am glad you told me how it went"
  - the message must contain one of: `keeping`
  - scored words: `keeping`(1.2), `telling`(0.6), `wall`(0.3)

```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.followup.thank_you_for_telling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.village.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.village.resume.followup.thank_you_for_telling   [31 chars]
    en  Thank you for keeping me in it.
    >>  ............................................
    pt  Obrigado por me manter por dentro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.resume.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `village.resume.thank_you_for_telling`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.resume.thank_you_for_telling
WHO    VILLAGER — what the player reads after pressing "Thank you for keeping me in it."
       spoken on: conversations.arc.village.resume.followup, button `thank_you_for_telling`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.resume.thank_you_for_telling`: the villager accepts. Subject `village.help`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.resume.thank_you_for_telling/1   [72 chars]
    en  You asked. People who ask get told; it isn't more complicated than that.
    >>  ............................................
    pt  Você perguntou. Quem pergunta é informado; não é mais complicado que isso.
    >>  ............................................
  dialogue.conversations.village.resume.thank_you_for_telling/2   [76 chars]
    en  It costs me nothing and it seems to be worth something. I'll go on doing it.
    >>  ............................................
    pt  Não me custa nada e parece valer algo. Vou continuar fazendo.
    >>  ............................................
  dialogue.conversations.village.resume.thank_you_for_telling/3   [74 chars]
    en  That's the second time you've thanked me for a thing I'd have done anyway.
    >>  ............................................
    pt  É a segunda vez que você me agradece por algo que eu faria de qualquer jeito.
    >>  ............................................
```


### Button `leave_it_with_you` — "I'll leave it with you."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `village.resume.when`, `village.resume.still_stands`, `village.resume.cannot`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.resume.leave_it_with_you` — accepted phrasings: "i will leave it with you"; "that is yours to handle"; "i will let you carry it from here"
  - the message must contain one of: `yours`
  - scored words: `leave`(0.6), `wall`(0.3), `with`(0.3), `yours`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.followup.leave_it_with_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.village.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.village.resume.followup.leave_it_with_you   [23 chars]
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
POOL   dialogue key: dialogue.conversations.village.resume.leave_it_with_you
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.village.resume.followup, button `leave_it_with_you`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.resume.leave_it_with_you`: the villager accepts. Subject `village.help`, polarity `neutral`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.resume.leave_it_with_you/1   [49 chars]
    en  Do. I'll say if it changes, and I'll say plainly.
    >>  ............................................
    pt  Deixe. Eu aviso se mudar, e aviso sem rodeios.
    >>  ............................................
  dialogue.conversations.village.resume.leave_it_with_you/2   [73 chars]
    en  Right. It's mine to carry and it's lighter for having been said out loud.
    >>  ............................................
    pt  Certo. É meu pra carregar e está mais leve por ter sido dito em voz alta.
    >>  ............................................
  dialogue.conversations.village.resume.leave_it_with_you/3   [74 chars]
    en  Then it's mine again. That's how it should be, and thank you for the loan.
    >>  ............................................
    pt  Então volta a ser meu. É como deve ser, e obrigado pelo empréstimo.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `village.resume.when`, `village.resume.still_stands`, `village.resume.cannot` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.village.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.village.resume.followup.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.village.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.village.resume.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.resume.leave`: the villager accepts. Subject `village.help`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.village.resume.respond / leave
```

```text
  dialogue.conversations.village.resume.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.village.resume.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.village.resume.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.arc.village.resume.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.resume` — e.g. "That fault you said you'd put a hand to. It's still there, and so are you."


```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.village.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.village.resume.respond   [28 chars]
    en  So it stands where it stood.
    >>  ............................................
    pt  Então está onde estava.
    >>  ............................................
```


### Button `when` — "When would suit?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `village.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.resume.when` — accepted phrasings: "when would suit"; "when should i come"; "name a day and i will be there"
  - the message must contain one of: `suit`
  - scored words: `suit`(1.2), `when`(0.5)

```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.respond.when
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.village.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.village.resume.respond.when   [16 chars]
    en  When would suit?
    >>  ............................................
    pt  Quando seria bom?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.village.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.village.resume.when
WHO    VILLAGER — what the player reads after pressing "When would suit?"
       spoken on: conversations.arc.village.resume.respond, button `when`
       leaves the player on: conversations.arc.village.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.resume.when`: the villager explains. Subject `village.help`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.resume.when/1   [70 chars]
    en  Any morning that isn't market day. I'll have the tools out either way.
    >>  ............................................
    pt  Qualquer manhã que não seja dia de feira. Deixo as ferramentas prontas.
    >>  ............................................
  dialogue.conversations.village.resume.when/2   [55 chars]
    en  Before the frost, or it's a spring job and a worse one.
    >>  ............................................
    pt  Antes da geada, ou vira serviço de primavera e bem pior.
    >>  ............................................
  dialogue.conversations.village.resume.when/3   [77 chars]
    en  Whenever. I've said that to four people and none of them came, so — whenever.
    >>  ............................................
    pt  Quando for. Disse isso a quatro pessoas e nenhuma veio, então — quando for.
    >>  ............................................
```


### Button `still_stands` — "The offer still stands."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `village.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.resume.still_stands` — accepted phrasings: "the offer still stands"; "i meant what i offered"; "my offer has not changed"
  - the message must contain one of: `stands`
  - scored words: `offer`(0.6), `stands`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.respond.still_stands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.village.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.village.resume.respond.still_stands   [23 chars]
    en  The offer still stands.
    >>  ............................................
    pt  A oferta continua de pé.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.resume.stands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `village.resume.still_stands`)_
- Does: session `turn`
- Then opens: `conversations.arc.village.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.village.resume.still_stands
WHO    VILLAGER — what the player reads after pressing "The offer still stands."
       spoken on: conversations.arc.village.resume.respond, button `still_stands`
       leaves the player on: conversations.arc.village.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.resume.still_stands`: the villager accepts. Subject `village.help`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.resume.still_stands/1   [65 chars]
    en  Then I'll stop being surprised about it and start counting on it.
    >>  ............................................
    pt  Então paro de me surpreender e começo a contar com isso.
    >>  ............................................
  dialogue.conversations.village.resume.still_stands/2   [84 chars]
    en  Good. Half the offers this village makes are made to be remembered fondly, not kept.
    >>  ............................................
    pt  Bom. Metade das ofertas deste vilarejo é pra ser lembrada com carinho, não cumprida.
    >>  ............................................
  dialogue.conversations.village.resume.still_stands/3   [62 chars]
    en  I'll hold you to that, and I'll be pleasant about the holding.
    >>  ............................................
    pt  Vou cobrar, e vou cobrar com educação.
    >>  ............................................
```


### Button `cannot` — "I can't, as it turns out."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `village.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.resume.cannot` — accepted phrasings: "i cannot as it turns out"; "i will not be able to after all"; "i have to take that back"
  - the message must contain one of: `cannot`, `turns`
  - scored words: `cannot`(1.0), `turns`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.respond.cannot
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.village.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.village.resume.respond.cannot   [25 chars]
    en  I can't, as it turns out.
    >>  ............................................
    pt  Não vou poder, no fim das contas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.village.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.village.resume.cannot
WHO    VILLAGER — what the player reads after pressing "I can't, as it turns out."
       spoken on: conversations.arc.village.resume.respond, button `cannot`
       leaves the player on: conversations.arc.village.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.resume.cannot`: the villager accepts. Subject `village.help`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.resume.cannot/1   [73 chars]
    en  Then say so and we're square. It's the not-saying that costs people here.
    >>  ............................................
    pt  Então diga e estamos quites. É o não dizer que custa caro por aqui.
    >>  ............................................
  dialogue.conversations.village.resume.cannot/2   [59 chars]
    en  Right. I'd rather a plain no now than a maybe until spring.
    >>  ............................................
    pt  Certo. Prefiro um não claro agora a um talvez até a primavera.
    >>  ............................................
  dialogue.conversations.village.resume.cannot/3   [53 chars]
    en  That's a shame and it's honest, and I'll take honest.
    >>  ............................................
    pt  É uma pena e é honesto, e eu aceito honesto.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `village.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.village.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.village.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.village.resume.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.village.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.village.resume.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.resume.leave`: the villager accepts. Subject `village.help`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.village.resume.followup / leave
```

> Written out in full under **`conversations.arc.village.resume.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.village.after_something_happened.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.village.after_something_happened` — e.g. "Quietly, and quietly is not the same as well. Ask again in a fortnight and you will get the real answer."


```text
POOL   dialogue key: dialogue.conversations.scene.village.after_something_happened.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.village.after_something_happened.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.village.after_something_happened.respond   [29 chars]
    en  How the village is taking it.
    >>  ............................................
    pt  Como a vila está levando.
    >>  ............................................
```


### Button `ask_who_needs_help` — "Who needs a hand?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `village.after_something_happened.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.after_something_happened.ask_who_needs_help` — accepted phrasings: "who needs a hand"; "who needs a hand"; "which household needs help"
  - the message must contain one of: `hand`, `household`, `help`
  - scored words: `hand`(1.8), `household`(1.8), `help`(1.8), `who`(0.8), `needs`(0.8), `which`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.after_something_happened.respond.ask_who_needs_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.after_something_happened.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.after_something_happened.respond.ask_who_needs_help   [17 chars]
    en  Who needs a hand?
    >>  ............................................
    pt  Quem precisa de ajuda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.village.offered_help`, budget `standard`, replay policy `once`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `village.recent_event`)_
- Does: session `turn`
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.after_something_happened.answered
WHO    VILLAGER — what the player reads after pressing "Who needs a hand?"
       spoken on: conversations.scene.village.after_something_happened.respond, button `ask_who_needs_help`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.after_something_happened.open.answered`: the villager reports. Subject `village.recent_event`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.after_something_happened.answered/1   [110 chars]
    en  Two houses, and one of them will say no, and the trick is to leave the thing on the step rather than offer it.
    >>  ............................................
    pt  Duas casas, e uma delas vai dizer não, e o truque é deixar a coisa no degrau em vez de oferecer.
    >>  ............................................
  dialogue.conversations.scene.village.after_something_happened.answered/2   [114 chars]
    en  That is the first useful question anybody has asked me all week. Come by in the morning and I will walk you round.
    >>  ............................................
    pt  É a primeira pergunta útil que alguém me fez a semana inteira. Passe de manhã e eu te levo nas casas.
    >>  ............................................
  dialogue.conversations.scene.village.after_something_happened.answered/3   [120 chars]
    en  Nobody is asking, which is exactly when help is needed. Asking starts about three weeks after it would have helped most.
    >>  ............................................
    pt  Ninguém está pedindo, que é exatamente quando a ajuda é necessária. As pessoas pedem umas três semanas depois de quando teria ajudado mais.
    >>  ............................................
```


### Button `acknowledge_it` — "It's been a hard stretch here."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `village.after_something_happened.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.after_something_happened.acknowledge_it` — accepted phrasings: "its been a hard stretch here"; "it has been a hard stretch here"; "this has been difficult for everyone"
  - the message must contain one of: `hard`, `difficult`, `stretch`
  - scored words: `hard`(1.8), `difficult`(1.8), `stretch`(1.8), `its`(0.8), `been`(0.8), `here`(0.8), `everyone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.after_something_happened.respond.acknowledge_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.after_something_happened.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.after_something_happened.respond.acknowledge_it   [30 chars]
    en  It's been a hard stretch here.
    >>  ............................................
    pt  Tem sido um período difícil aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `village.recent_event`)_
- Does: session `turn`
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.after_something_happened.agreed
WHO    VILLAGER — what the player reads after pressing "It's been a hard stretch here."
       spoken on: conversations.scene.village.after_something_happened.respond, button `acknowledge_it`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.after_something_happened.open.agreed`: the villager accepts. Subject `village.recent_event`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.after_something_happened.agreed/1   [112 chars]
    en  It has, and thank you for saying so out loud. Everybody has been being brave at each other and it is exhausting.
    >>  ............................................
    pt  Tem, e obrigada por dizer em voz alta. Todo mundo tem sido corajoso um para o outro e isso cansa.
    >>  ............................................
  dialogue.conversations.scene.village.after_something_happened.agreed/2   [90 chars]
    en  Yes. And it will be over, and the being over will happen without anybody noticing the day.
    >>  ............................................
    pt  Sim. E vai passar, e o passar vai acontecer sem ninguém notar o dia.
    >>  ............................................
  dialogue.conversations.scene.village.after_something_happened.agreed/3   [86 chars]
    en  The hardest part is that nothing is anybody's fault, so there is nowhere for it to go.
    >>  ............................................
    pt  A parte mais difícil é que a culpa não é de ninguém, então não tem para onde isso ir.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `village.after_something_happened.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.village.after_something_happened.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.after_something_happened.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.after_something_happened.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.village.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.village.after_something_happened.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.scene.leaving`: the villager accepts. Subject `village.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.village.change_settled.respond / leave; conversations.scene.village.followup / leave; conversations.scene.village.getting_bigger.respond / leave; conversations.scene.village.still_placing_it.named_it.respond / leave; conversations.scene.village.still_placing_it.respond / leave
```

```text
  dialogue.conversations.scene.village.leaving/1   [22 chars]
    en  It goes on either way.
    >>  ............................................
    pt  Segue de qualquer jeito.
    >>  ............................................
  dialogue.conversations.scene.village.leaving/2   [25 chars]
    en  Right. That is the place.
    >>  ............................................
    pt  Certo. É o lugar.
    >>  ............................................
  dialogue.conversations.scene.village.leaving/3   [31 chars]
    en  It will still be here tomorrow.
    >>  ............................................
    pt  Ainda vai estar aqui amanhã.
    >>  ............................................
```

---


## `conversations.scene.village.change_settled.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.village.change_settled` — e.g. "%2$s, and the village closed over it the way water does. I have made my peace."


```text
POOL   dialogue key: dialogue.conversations.scene.village.change_settled.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.village.change_settled.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.village.change_settled.respond   [11 chars]
    en  Since then.
    >>  ............................................
    pt  Desde então.
    >>  ............................................
```


### Button `glad_of_it` — "I'm glad it sits easier now."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `village.change_settled.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.change_settled.glad_of_it` — accepted phrasings: "im glad it sits easier now"; "glad it sits easier"; "glad that is behind you"
  - the message must contain one of: `glad`, `easier`
  - scored words: `glad`(1.8), `easier`(1.8), `sits`(0.8), `now`(0.8), `behind`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.change_settled.respond.glad_of_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.change_settled.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.change_settled.respond.glad_of_it   [28 chars]
    en  I'm glad it sits easier now.
    >>  ............................................
    pt  Fico feliz que isso pese menos agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `village.change`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "village.change"}
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.change_settled.warmed
WHO    VILLAGER — what the player reads after pressing "I'm glad it sits easier now."
       spoken on: conversations.scene.village.change_settled.respond, button `glad_of_it`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.change_settled.open.warmed`: the villager accepts. Subject `village.change`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.change_settled.warmed/1   [80 chars]
    en  So am I. You asked about it when it was still sharp, which I have not forgotten.
    >>  ............................................
    pt  Eu também. Você perguntou quando ainda estava em carne viva, e eu não esqueci.
    >>  ............................................
  dialogue.conversations.scene.village.change_settled.warmed/2   [77 chars]
    en  It does. Some of that is time and some of it is having said it out loud once.
    >>  ............................................
    pt  Pesa menos. Parte disso é tempo e parte é ter dito em voz alta uma vez.
    >>  ............................................
  dialogue.conversations.scene.village.change_settled.warmed/3   [53 chars]
    en  Easier, yes. Not gone. I do not think I want it gone.
    >>  ............................................
    pt  Mais leve, sim. Não sumiu. Acho que não quero que suma.
    >>  ............................................
```


### Button `ask_what_helped` — "What made the difference?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `village.change_settled.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.change_settled.ask_what_helped` — accepted phrasings: "what made the difference"; "what made the difference"; "what helped in the end"
  - the message must contain one of: `difference`, `helped`
  - scored words: `difference`(1.8), `helped`(1.8), `made`(0.8), `end`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.change_settled.respond.ask_what_helped
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.change_settled.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.change_settled.respond.ask_what_helped   [25 chars]
    en  What made the difference?
    >>  ............................................
    pt  O que fez diferença?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `village.change`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "village.change"}
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.change_settled.explained
WHO    VILLAGER — what the player reads after pressing "What made the difference?"
       spoken on: conversations.scene.village.change_settled.respond, button `ask_what_helped`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.change_settled.open.explained`: the villager explains. Subject `village.change`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.change_settled.explained/1   [82 chars]
    en  Nothing clever. It stopped being the newest thing and something else took the job.
    >>  ............................................
    pt  Nada esperto. Deixou de ser a novidade e outra coisa assumiu o posto.
    >>  ............................................
  dialogue.conversations.scene.village.change_settled.explained/2   [56 chars]
    en  Ordinary days, one after another, none of them about it.
    >>  ............................................
    pt  Dias comuns, um atrás do outro, nenhum deles sobre isso.
    >>  ............................................
  dialogue.conversations.scene.village.change_settled.explained/3   [70 chars]
    en  Somebody asked me a plain question about it and I heard my own answer.
    >>  ............................................
    pt  Alguém me fez uma pergunta simples sobre isso e eu ouvi a minha própria resposta.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `village.change_settled.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.village.change_settled.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.change_settled.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.change_settled.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.village.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.village.change_settled.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.scene.leaving`: the villager accepts. Subject `village.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.village.after_something_happened.respond / leave; conversations.scene.village.followup / leave; conversations.scene.village.getting_bigger.respond / leave; conversations.scene.village.still_placing_it.named_it.respond / leave; conversations.scene.village.still_placing_it.respond / leave
```

> Written out in full under **`conversations.scene.village.after_something_happened.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.village.followup`

**Reached from 10 route(s):** `conversations.scene.village.after_something_happened.respond` / `ask_who_needs_help`; `conversations.scene.village.after_something_happened.respond` / `acknowledge_it`; `conversations.scene.village.change_settled.respond` / `glad_of_it`; `conversations.scene.village.change_settled.respond` / `ask_what_helped`; `conversations.scene.village.getting_bigger.respond` / `ask_if_its_better`; `conversations.scene.village.getting_bigger.respond` / `say_you_like_it`; `conversations.scene.village.still_placing_it.named_it.respond` / `sit_with_it`; `conversations.scene.village.still_placing_it.named_it.respond` / `press_for_the_line`; `conversations.scene.village.still_placing_it.respond` / `offer_to_carry_it`; `conversations.scene.village.still_placing_it.respond` / `shrug_it_off`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.village.after_something_happened.agreed` — e.g. "It has, and thank you for saying so out loud. Everybody has been being brave at each other and it is exhausting."
- `conversations.scene.village.after_something_happened.answered` — e.g. "Two houses, and one of them will say no, and the trick is to leave the thing on the step rather than offer it."
- `conversations.scene.village.change_settled.explained` — e.g. "Nothing clever. It stopped being the newest thing and something else took the job."
- `conversations.scene.village.change_settled.warmed` — e.g. "So am I. You asked about it when it was still sharp, which I have not forgotten."
- `conversations.scene.village.getting_bigger.explained` — e.g. "Safer, and less kind. In a village of nine everybody is your problem. In a village of thirty, somebody else's."
- `conversations.scene.village.getting_bigger.pleased` — e.g. "It is, and that is not an accident. Four people work quite hard at it and none of them would say so."
- `conversations.scene.village.still_placing_it.closed` — e.g. "They do. I will keep the rest of it to myself, then."
- `conversations.scene.village.still_placing_it.named_it.eased` — e.g. "No. I suppose I do not. That is a kinder way round than the one I had."
- `conversations.scene.village.still_placing_it.named_it.said_it` — e.g. "The one where I am the older one now. There. You made me say it."
- `conversations.scene.village.still_placing_it.practical` — e.g. "Not yet. Ask me in a few days and there will be a list, there always is."


```text
POOL   dialogue key: dialogue.conversations.scene.village.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.village.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.village.followup   [32 chars]
    en  Anything else about the village?
    >>  ............................................
    pt  Mais alguma coisa sobre a vila?
    >>  ............................................
```


### Button `leave` — "That's the place, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:village.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.village.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.followup.leave   [23 chars]
    en  That's the place, then.
    >>  ............................................
    pt  É o lugar, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.village.leaving
WHO    VILLAGER — what the player reads after pressing "That's the place, then."
       spoken on: conversations.scene.village.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.scene.leaving`: the villager accepts. Subject `village.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.village.after_something_happened.respond / leave; conversations.scene.village.change_settled.respond / leave; conversations.scene.village.getting_bigger.respond / leave; conversations.scene.village.still_placing_it.named_it.respond / leave; conversations.scene.village.still_placing_it.respond / leave
```

> Written out in full under **`conversations.scene.village.after_something_happened.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.village.getting_bigger.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.village.getting_bigger` — e.g. "It has got large enough that I do not know every face, and I am still deciding how I feel about that."


```text
POOL   dialogue key: dialogue.conversations.scene.village.getting_bigger.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.village.getting_bigger.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.village.getting_bigger.respond   [18 chars]
    en  This place lately.
    >>  ............................................
    pt  Este lugar ultimamente.
    >>  ............................................
```


### Button `ask_if_its_better` — "Is bigger better?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `village.getting_bigger.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.getting_bigger.ask_if_its_better` — accepted phrasings: "is bigger better"; "is bigger better"; "has growing helped the place"
  - the message must contain one of: `bigger`, `growing`
  - scored words: `bigger`(1.8), `growing`(1.8), `better`(0.8), `helped`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.getting_bigger.respond.ask_if_its_better
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.getting_bigger.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.getting_bigger.respond.ask_if_its_better   [17 chars]
    en  Is bigger better?
    >>  ............................................
    pt  Maior é melhor?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `village.growth`)_
- Does: session `turn`
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.getting_bigger.explained
WHO    VILLAGER — what the player reads after pressing "Is bigger better?"
       spoken on: conversations.scene.village.getting_bigger.respond, button `ask_if_its_better`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.getting_bigger.open.explained`: the villager explains. Subject `village.growth`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.getting_bigger.explained/1   [110 chars]
    en  Safer, and less kind. In a village of nine everybody is your problem. In a village of thirty, somebody else's.
    >>  ............................................
    pt  Mais seguro, e menos gentil. Numa vila de nove, todo mundo é problema seu. Numa de trinta, é de outro.
    >>  ............................................
  dialogue.conversations.scene.village.getting_bigger.explained/2   [114 chars]
    en  For the young ones, yes. For me it is the same place with more strangers in it, and I am aware that is my failing.
    >>  ............................................
    pt  Para os jovens, sim. Para mim é o mesmo lugar com mais estranhos dentro, e eu sei que a falha é minha.
    >>  ............................................
  dialogue.conversations.scene.village.getting_bigger.explained/3   [101 chars]
    en  Ask the person who mends the well. They will tell you the honest answer and it will be about buckets.
    >>  ............................................
    pt  Pergunte a quem conserta o poço. Vão te dar a resposta honesta e ela vai ser sobre baldes.
    >>  ............................................
```


### Button `say_you_like_it` — "It's a good place to arrive in."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `village.getting_bigger.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.getting_bigger.say_you_like_it` — accepted phrasings: "its a good place to arrive in"; "it is a good place to arrive in"; "this is a welcoming place"
  - the message must contain one of: `arrive`, `welcoming`, `place`
  - scored words: `arrive`(1.8), `welcoming`(1.8), `place`(1.8), `its`(0.8), `good`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.getting_bigger.respond.say_you_like_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.getting_bigger.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.getting_bigger.respond.say_you_like_it   [31 chars]
    en  It's a good place to arrive in.
    >>  ............................................
    pt  É um bom lugar para se chegar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `village.growth`)_
- Does: session `turn`
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.getting_bigger.pleased
WHO    VILLAGER — what the player reads after pressing "It's a good place to arrive in."
       spoken on: conversations.scene.village.getting_bigger.respond, button `say_you_like_it`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.getting_bigger.open.pleased`: the villager accepts. Subject `village.growth`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.getting_bigger.pleased/1   [100 chars]
    en  It is, and that is not an accident. Four people work quite hard at it and none of them would say so.
    >>  ............................................
    pt  É, e não é por acaso. Quatro pessoas trabalham bastante nisso e nenhuma delas diria.
    >>  ............................................
  dialogue.conversations.scene.village.getting_bigger.pleased/2   [100 chars]
    en  Thank you. Somebody arriving and saying that is worth more than anybody who was born here saying it.
    >>  ............................................
    pt  Obrigada. Alguém que chegou dizer isso vale mais do que qualquer nascido aqui dizer.
    >>  ............................................
  dialogue.conversations.scene.village.getting_bigger.pleased/3   [105 chars]
    en  I hope so. I have been on the other side of that door and I remember exactly how long the first month is.
    >>  ............................................
    pt  Espero que sim. Já estive do outro lado dessa porta e lembro exatamente o quanto o primeiro mês é longo.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `village.getting_bigger.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.village.getting_bigger.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.getting_bigger.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.getting_bigger.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.village.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.village.getting_bigger.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.scene.leaving`: the villager accepts. Subject `village.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.village.after_something_happened.respond / leave; conversations.scene.village.change_settled.respond / leave; conversations.scene.village.followup / leave; conversations.scene.village.still_placing_it.named_it.respond / leave; conversations.scene.village.still_placing_it.respond / leave
```

> Written out in full under **`conversations.scene.village.after_something_happened.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.village.still_placing_it.named_it.respond`

**Reached from 1 route(s):** `conversations.scene.village.still_placing_it.respond` / `ask_what_changed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.village.still_placing_it.named_it` — e.g. "Everything and nothing. The bread is the same. The room I walk into is not."


```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.named_it.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.village.still_placing_it.named_it.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.village.still_placing_it.named_it.respond   [29 chars]
    en  So where does that leave you?
    >>  ............................................
    pt  E onde isso te deixa?
    >>  ............................................
```


### Button `sit_with_it` — "Take your time with it."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `village.still_placing_it.open.named_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.still_placing_it.sit_with_it` — accepted phrasings: "take your time with it"; "take your time with it"; "give yourself time to place it"
  - the message must contain one of: `time`
  - scored words: `time`(1.8), `take`(0.8), `give`(0.8), `yourself`(0.8), `place`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.named_it.respond.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.still_placing_it.named_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.still_placing_it.named_it.respond.sit_with_it   [23 chars]
    en  Take your time with it.
    >>  ............................................
    pt  Vá com calma com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `village.change.sit`, budget `standard`, replay policy `once_per_day`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `village.change`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "village.change"}
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.named_it.eased
WHO    VILLAGER — what the player reads after pressing "Take your time with it."
       spoken on: conversations.scene.village.still_placing_it.named_it.respond, button `sit_with_it`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.still_placing_it.open.named_it.eased`: the villager accepts. Subject `village.change`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.still_placing_it.named_it.eased/1   [70 chars]
    en  No. I suppose I do not. That is a kinder way round than the one I had.
    >>  ............................................
    pt  Não. Acho que não preciso. É um jeito mais gentil do que o meu.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.named_it.eased/2   [57 chars]
    en  Thank you. I had been treating it as a sum I was failing.
    >>  ............................................
    pt  Obrigado. Eu estava tratando isso como uma conta que eu não conseguia fechar.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.named_it.eased/3   [63 chars]
    en  Right. Then I will stop trying to land on an opinion by supper.
    >>  ............................................
    pt  Certo. Então vou parar de tentar chegar a uma opinião até o jantar.
    >>  ............................................
```


### Button `press_for_the_line` — "What line? Say the actual thing."

*stance family `challenge` · tone `blunt` · outcome `qualified` · answers the beat(s) `village.still_placing_it.open.named_it`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.still_placing_it.press_for_the_line` — accepted phrasings: "what line say the actual thing"; "what line"; "say the actual thing"; "what do you really mean"
  - the message must contain one of: `line`, `actual`, `mean`
  - scored words: `line`(1.8), `actual`(1.8), `mean`(1.8), `say`(0.8), `thing`(0.8), `really`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.named_it.respond.press_for_the_line
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.still_placing_it.named_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.still_placing_it.named_it.respond.press_for_the_line   [32 chars]
    en  What line? Say the actual thing.
    >>  ............................................
    pt  Que fila? Diga a coisa de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, tension +2  _(recorded under topic `village.change`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "village.change"}
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.named_it.said_it
WHO    VILLAGER — what the player reads after pressing "What line? Say the actual thing."
       spoken on: conversations.scene.village.still_placing_it.named_it.respond, button `press_for_the_line`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.still_placing_it.open.named_it.said_it`: the villager explains. Subject `village.change`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.still_placing_it.named_it.said_it/1   [64 chars]
    en  The one where I am the older one now. There. You made me say it.
    >>  ............................................
    pt  Aquela em que agora eu sou o mais velho. Pronto. Você me fez dizer.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.named_it.said_it/2   [74 chars]
    en  That I am further from the start of my life than the end of it. Satisfied?
    >>  ............................................
    pt  Que estou mais longe do começo da minha vida do que do fim. Satisfeito?
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.named_it.said_it/3   [77 chars]
    en  That the village will do this again and one of the times it will be about me.
    >>  ............................................
    pt  Que a vila vai fazer isso de novo e numa dessas vezes vai ser sobre mim.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `village.still_placing_it.open.named_it` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.named_it.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.still_placing_it.named_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.still_placing_it.named_it.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.village.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.village.still_placing_it.named_it.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.scene.leaving`: the villager accepts. Subject `village.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.village.after_something_happened.respond / leave; conversations.scene.village.change_settled.respond / leave; conversations.scene.village.followup / leave; conversations.scene.village.getting_bigger.respond / leave; conversations.scene.village.still_placing_it.respond / leave
```

> Written out in full under **`conversations.scene.village.after_something_happened.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.village.still_placing_it.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.village.still_placing_it` — e.g. "There has been %2$s and I have not worked out yet what I think about it."


```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.village.still_placing_it.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.village.still_placing_it.respond   [12 chars]
    en  The village.
    >>  ............................................
    pt  A vila.
    >>  ............................................
```


### Button `ask_what_changed` — "What has it changed, for you?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `village.still_placing_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.still_placing_it.ask_what_changed` — accepted phrasings: "what has it changed for you"; "what has it changed for you"; "how has that changed things"
  - the message must contain one of: `changed`, `different`
  - scored words: `changed`(1.8), `different`(1.8), `things`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.respond.ask_what_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.still_placing_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.still_placing_it.respond.ask_what_changed   [29 chars]
    en  What has it changed, for you?
    >>  ............................................
    pt  O que isso mudou, para você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `village.change`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "village.change"}
- Then opens: `conversations.scene.village.still_placing_it.named_it.respond`
- …where the player's next choices will be: "Take your time with it." | "What line? Say the actual thing." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.named_it
WHO    VILLAGER — what the player reads after pressing "What has it changed, for you?"
       spoken on: conversations.scene.village.still_placing_it.respond, button `ask_what_changed`
       leaves the player on: conversations.scene.village.still_placing_it.named_it.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.still_placing_it.open.named_it`: the villager explains. Subject `village.change`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, challenge, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.still_placing_it.named_it/1   [75 chars]
    en  Everything and nothing. The bread is the same. The room I walk into is not.
    >>  ............................................
    pt  Tudo e nada. O pão é o mesmo. A sala em que eu entro não é.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.named_it/2   [105 chars]
    en  I find I was counting on things staying arranged the way they were, which is a foolish thing to count on.
    >>  ............................................
    pt  Descobri que eu contava com as coisas continuarem arrumadas como estavam, o que é uma bobagem para se contar.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.named_it/3   [72 chars]
    en  It has moved me one place along a line I did not know I was standing in.
    >>  ............................................
    pt  Isso me moveu um lugar numa fila em que eu não sabia que estava.
    >>  ............................................
```


### Button `offer_to_carry_it` — "Anything that needs doing about it?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `village.still_placing_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.still_placing_it.offer_to_carry_it` — accepted phrasings: "anything that needs doing about it"; "anything that needs doing"; "is there something to be done"
  - the message must contain one of: `doing`, `done`
  - scored words: `doing`(1.8), `done`(1.8), `anything`(0.8), `needs`(0.8), `something`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.respond.offer_to_carry_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.still_placing_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.still_placing_it.respond.offer_to_carry_it   [35 chars]
    en  Anything that needs doing about it?
    >>  ............................................
    pt  Tem algo que precise ser feito por causa disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, trust +2  _(recorded under topic `village.change`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "village.change"}
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.practical
WHO    VILLAGER — what the player reads after pressing "Anything that needs doing about it?"
       spoken on: conversations.scene.village.still_placing_it.respond, button `offer_to_carry_it`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.still_placing_it.open.practical`: the villager qualifys. Subject `village.change`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.still_placing_it.practical/1   [72 chars]
    en  Not yet. Ask me in a few days and there will be a list, there always is.
    >>  ............................................
    pt  Ainda não. Me pergunte daqui a uns dias e vai ter uma lista, sempre tem.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.practical/2   [71 chars]
    en  Kind. Nothing today. The doing part comes after the sitting-about part.
    >>  ............................................
    pt  Gentil. Hoje nada. A parte de fazer vem depois da parte de ficar sentado.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.practical/3   [75 chars]
    en  There will be. Nobody has admitted it out loud yet, so nobody can be asked.
    >>  ............................................
    pt  Vai ter. Ninguém admitiu em voz alta ainda, então ninguém pode ser chamado.
    >>  ............................................
```


### Button `shrug_it_off` — "Villages change. That's all."

*stance family `dismissal` · tone `blunt` · outcome `resisted` · answers the beat(s) `village.still_placing_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.village.still_placing_it.shrug_it_off` — accepted phrasings: "villages change thats all"; "villages change"; "that is just how it goes"
  - the message must contain one of: `change`, `goes`
  - scored words: `change`(1.8), `goes`(1.8), `villages`(0.8), `thats`(0.8), `all`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.respond.shrug_it_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.still_placing_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.still_placing_it.respond.shrug_it_off   [28 chars]
    en  Villages change. That's all.
    >>  ............................................
    pt  Vilas mudam. É só isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `village.change.shrug`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `village.change`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "village.change"}
- Then opens: `conversations.scene.village.followup`
- …where the player's next choices will be: "That's the place, then."

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.closed
WHO    VILLAGER — what the player reads after pressing "Villages change. That's all."
       spoken on: conversations.scene.village.still_placing_it.respond, button `shrug_it_off`
       leaves the player on: conversations.scene.village.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.still_placing_it.open.closed`: the villager deflects. Subject `village.change`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.still_placing_it.closed/1   [52 chars]
    en  They do. I will keep the rest of it to myself, then.
    >>  ............................................
    pt  Mudam. Então vou guardar o resto para mim.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.closed/2   [51 chars]
    en  That is true and it is not what I said. Never mind.
    >>  ............................................
    pt  Isso é verdade e não é o que eu disse. Deixa para lá.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it.closed/3   [52 chars]
    en  Yes. Well. I will not trouble you with the next one.
    >>  ............................................
    pt  Sim. Bom. Não vou te incomodar com a próxima.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `village.still_placing_it.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.village.still_placing_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.village.still_placing_it.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.village.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.village.still_placing_it.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.scene.leaving`: the villager accepts. Subject `village.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.village.after_something_happened.respond / leave; conversations.scene.village.change_settled.respond / leave; conversations.scene.village.followup / leave; conversations.scene.village.getting_bigger.respond / leave; conversations.scene.village.still_placing_it.named_it.respond / leave
```

> Written out in full under **`conversations.scene.village.after_something_happened.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.village.again.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.again` — e.g. "Still the same village, %1$s. The fences haven't moved."


```text
POOL   dialogue key: dialogue.conversations.topic.village.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.again.respond   [27 chars]
    en  We did the village already.
    >>  ............................................
    pt  Já falamos da vila.
    >>  ............................................
```


### Button `apologize` — "Sorry — you've told me."

*stance family `candor` · tone `gentle` · answers the beat(s) `village.again.to.village.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.again.apologize` — accepted phrasings: "sorry, you have told me"; "sorry, i already asked"; "my mistake"
  - the message must contain one of: `told`, `sorry`, `already`
  - scored words: `told`(1.5), `sorry`(1.2), `already`(1.0), `village`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.village.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.again.respond.apologize   [23 chars]
    en  Sorry — you've told me.
    >>  ............................................
    pt  Desculpa — você já me contou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `village.again.apologize`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — you've told me."
       spoken on: conversations.topic.village.again.respond, button `apologize`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.again.apologize.terminal`: the villager accepts. Subject `village.repeat`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.again.apologize/1   [43 chars]
    en  No harm. I'd talk about this place all day.
    >>  ............................................
    pt  Sem problema. Eu falaria deste lugar o dia todo.
    >>  ............................................
  dialogue.conversations.village.again.apologize/2   [36 chars]
    en  You did, aye. It hasn't moved since.
    >>  ............................................
    pt  Você já, é. Ela não se mexeu desde então.
    >>  ............................................
  dialogue.conversations.village.again.apologize/3   [19 chars]
    en  It's alright, %1$s.
    >>  ............................................
    pt  Tudo bem, %1$s.
    >>  ............................................
```


### Button `press` — "Tell me again anyway."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `village.again.to.village.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.again.press` — accepted phrasings: "tell me again anyway"; "go on, again"; "again, please"
  - the message must contain one of: `again`, `anyway`
  - scored words: `again`(1.5), `anyway`(1.2), `village`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.village.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.again.respond.press   [21 chars]
    en  Tell me again anyway.
    >>  ............................................
    pt  Me conta de novo mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `village.again.press`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `village.again.press`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.village.again.respond, button `press`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.again.press.terminal`: the villager resists. Subject `village.repeat`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.again.press/1   [33 chars]
    en  Same village it was this morning.
    >>  ............................................
    pt  A mesma vila de hoje de manhã.
    >>  ............................................
  dialogue.conversations.village.again.press/2   [36 chars]
    en  It hasn't improved in an hour, %1$s.
    >>  ............................................
    pt  Não melhorou em uma hora, %1$s.
    >>  ............................................
  dialogue.conversations.village.again.press/3   [33 chars]
    en  ...Fine. Still muddy. Still ours.
    >>  ............................................
    pt  ...Tá. Ainda lamacenta. Ainda nossa.
    >>  ............................................
```


### Button `leave` — "Fair. Another day."

*stance family `exit` · tone `plain` · answers the beat(s) `village.again.to.village.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.again.respond.leave   [18 chars]
    en  Fair. Another day.
    >>  ............................................
    pt  Justo. Outro dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.village.again.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.again.leave.terminal`: the villager accepts. Subject `village.repeat`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.again.leave/1   [17 chars]
    en  Aye. Another day.
    >>  ............................................
    pt  Tá. Outro dia.
    >>  ............................................
  dialogue.conversations.village.again.leave/2   [12 chars]
    en  Enough said.
    >>  ............................................
    pt  Já foi dito.
    >>  ............................................
  dialogue.conversations.village.again.leave/3   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

---


## `conversations.topic.village.fault.followup`

**Reached from 2 route(s):** `conversations.topic.village.respond` / `ask_improve`; `conversations.topic.village.respond` / `ask_improve`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.ask_improve.smith` — e.g. "We've a smith, which puts us ahead of most. What we've not got is a decent road to reach it by."
- `conversations.village.respond.ask_improve` — e.g. "The well, first. Then the road. Then whoever decided where the pigs go."


```text
POOL   dialogue key: dialogue.conversations.topic.village.fault.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.fault.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.fault.followup   [24 chars]
    en  That's the list, anyway.
    >>  ............................................
    pt  É a lista, enfim.
    >>  ............................................
```


### Button `offer_help` — "Tell me and I'll help fix it."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `village.fault_named`, `village.fault_smith` · offered only once the villager has actually said `village:fault_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.fault.offer_help` — accepted phrasings: "tell me and i will help fix it"; "i will lend a hand"; "let me help with it"
  - the message must contain one of: `fix`, `hand`, `help`
  - scored words: `fix`(1.5), `hand`(1.2), `help`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.village.fault.followup.offer_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.fault.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.fault.followup.offer_help   [29 chars]
    en  Tell me and I'll help fix it.
    >>  ............................................
    pt  Me diga e eu ajudo a consertar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `village.offer_help` lands on tier **crit** (axis respect, difficulty 30, stance practical_help)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `village.offer_help.crit`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +6, warmth +3  _(recorded under topic `village.offer_help`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.offer_help.crit
WHO    VILLAGER — what the player reads after pressing "Tell me and I'll help fix it."
       spoken on: conversations.topic.village.fault.followup, button `offer_help`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.help.crit`: the villager accepts. Subject `village.home`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.offer_help.crit/1   [93 chars]
    en  ...You mean it. Right — Tuesday, the north fence, and bring whatever you'd bring to your own.
    >>  ............................................
    pt  ...Você fala sério. Certo — terça, a cerca norte, e traga o que traria para a sua própria.
    >>  ............................................
  dialogue.conversations.village.offer_help.crit/2   [59 chars]
    en  Then you're one of us, %1$s, and I'll be telling people so.
    >>  ............................................
    pt  Então você é um dos nossos, %1$s, e eu vou dizer isso por aí.
    >>  ............................................
```


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `village.offer_help` lands on tier **success** (axis respect, difficulty 30, stance practical_help)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `village.offer_help.success`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +1  _(recorded under topic `village.offer_help`)_
- Does: arc `village` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.offer_help.success
WHO    VILLAGER — what the player reads after pressing "Tell me and I'll help fix it."
       spoken on: conversations.topic.village.fault.followup, button `offer_help`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.help.success`: the villager accepts. Subject `village.home`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.offer_help.success/1   [83 chars]
    en  ...Aye, alright. I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...É, tudo bem. Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  dialogue.conversations.village.offer_help.success/2   [53 chars]
    en  Hands. Good. We're short of those and long on advice.
    >>  ............................................
    pt  Mãos. Bom. Temos falta delas e sobra de conselho.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something, and I'll try not to ask for too much.
    >>  ............................................
    pt  ...Está bem. Vou achar algo, e vou tentar não pedir demais.
    >>  ............................................
  anxious.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those. Saying so out loud is harder than it should be.
    >>  ............................................
    pt  Mãos. Faltam delas. Dizer isso em voz alta é mais difícil do que devia.
    >>  ............................................
  athletic.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something that needs doing; there's forty years of it waiting.
    >>  ............................................
    pt  ...Está bem. Vou achar algo que precise ser feito; tem quarenta anos disso esperando.
    >>  ............................................
  athletic.dialogue.conversations.village.offer_help.success/2
    en  Hands. Every village I've known was short of those and long on advice.
    >>  ............................................
    pt  Mãos. Todo vilarejo que conheci teve falta delas e sobra de conselho.
    >>  ............................................
  confident.dialogue.conversations.village.offer_help.success/1
    en  ...Aye, alright. I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...É, está bem. Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  confident.dialogue.conversations.village.offer_help.success/2
    en  Hands. Good. We're short of those and long on advice.
    >>  ............................................
    pt  Mãos. Bom. Estamos com falta delas e sobra de conselho.
    >>  ............................................
  crabby.dialogue.conversations.village.offer_help.success/1
    en  ...Aye, alright. I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...É, está bem. Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  crabby.dialogue.conversations.village.offer_help.success/2
    en  Hands. Good. We're short of those and long on advice.
    >>  ............................................
    pt  Mãos. Bom. Estamos com falta delas e sobra de conselho.
    >>  ............................................
  extroverted.dialogue.conversations.village.offer_help.success/1
    en  ...Alright, %1$s. I'll find you something that needs doing, and I'll pick a kind one.
    >>  ............................................
    pt  ...Está bem, %1$s. Vou achar algo que precise ser feito, e escolho algo leve.
    >>  ............................................
  extroverted.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those and long on advice, and you knew which we needed.
    >>  ............................................
    pt  Mãos. Faltam elas e sobra conselho, e você sabia de qual a gente precisava.
    >>  ............................................
  flirty.dialogue.conversations.village.offer_help.success/1
    en  ...Alright, %1$s. I'll find you something that needs doing, and I'll pick a kind one.
    >>  ............................................
    pt  ...Está bem, %1$s. Vou achar algo que precise ser feito, e escolho algo leve.
    >>  ............................................
  flirty.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those and long on advice, and you knew which we needed.
    >>  ............................................
    pt  Mãos. Faltam elas e sobra conselho, e você sabia de qual a gente precisava.
    >>  ............................................
  friendly.dialogue.conversations.village.offer_help.success/1
    en  ...Alright, %1$s. I'll find you something that needs doing, and I'll pick a kind one.
    >>  ............................................
    pt  ...Está bem, %1$s. Vou achar algo que precise ser feito, e escolho algo leve.
    >>  ............................................
  friendly.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those and long on advice, and you knew which we needed.
    >>  ............................................
    pt  Mãos. Faltam elas e sobra conselho, e você sabia de qual a gente precisava.
    >>  ............................................
  gloomy.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something, and I'll try not to ask for too much.
    >>  ............................................
    pt  ...Está bem. Vou achar algo, e vou tentar não pedir demais.
    >>  ............................................
  gloomy.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those. Saying so out loud is harder than it should be.
    >>  ............................................
    pt  Mãos. Faltam delas. Dizer isso em voz alta é mais difícil do que devia.
    >>  ............................................
  greedy.dialogue.conversations.village.offer_help.success/1
    en  ...Aye, alright. I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...É, está bem. Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  greedy.dialogue.conversations.village.offer_help.success/2
    en  Hands. Good. We're short of those and long on advice.
    >>  ............................................
    pt  Mãos. Bom. Estamos com falta delas e sobra de conselho.
    >>  ............................................
  grumpy.dialogue.conversations.village.offer_help.success/1
    en  ...Aye, alright. I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...É, está bem. Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  grumpy.dialogue.conversations.village.offer_help.success/2
    en  Hands. Good. We're short of those and long on advice.
    >>  ............................................
    pt  Mãos. Bom. Estamos com falta delas e sobra de conselho.
    >>  ............................................
  introverted.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something.
    >>  ............................................
    pt  ...Está bem. Vou achar algo pra você.
    >>  ............................................
  introverted.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those.
    >>  ............................................
    pt  Mãos. Faltam delas.
    >>  ............................................
  lazy.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something that needs doing; there's forty years of it waiting.
    >>  ............................................
    pt  ...Está bem. Vou achar algo que precise ser feito; tem quarenta anos disso esperando.
    >>  ............................................
  lazy.dialogue.conversations.village.offer_help.success/2
    en  Hands. Every village I've known was short of those and long on advice.
    >>  ............................................
    pt  Mãos. Todo vilarejo que conheci teve falta delas e sobra de conselho.
    >>  ............................................
  odd.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something.
    >>  ............................................
    pt  ...Está bem. Vou achar algo pra você.
    >>  ............................................
  odd.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those.
    >>  ............................................
    pt  Mãos. Faltam delas.
    >>  ............................................
  peaceful.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something that needs doing; there's forty years of it waiting.
    >>  ............................................
    pt  ...Está bem. Vou achar algo que precise ser feito; tem quarenta anos disso esperando.
    >>  ............................................
  peaceful.dialogue.conversations.village.offer_help.success/2
    en  Hands. Every village I've known was short of those and long on advice.
    >>  ............................................
    pt  Mãos. Todo vilarejo que conheci teve falta delas e sobra de conselho.
    >>  ............................................
  peppy.dialogue.conversations.village.offer_help.success/1
    en  ...Right, alright! I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...Certo, está bem! Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  peppy.dialogue.conversations.village.offer_help.success/2
    en  Hands! Good. We're desperately short of those and drowning in advice.
    >>  ............................................
    pt  Mãos! Bom. Estamos desesperadamente sem elas e afogados em conselho.
    >>  ............................................
  playful.dialogue.conversations.village.offer_help.success/1
    en  ...Right, alright! I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...Certo, está bem! Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  playful.dialogue.conversations.village.offer_help.success/2
    en  Hands! Good. We're desperately short of those and drowning in advice.
    >>  ............................................
    pt  Mãos! Bom. Estamos desesperadamente sem elas e afogados em conselho.
    >>  ............................................
  relaxed.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something that needs doing; there's forty years of it waiting.
    >>  ............................................
    pt  ...Está bem. Vou achar algo que precise ser feito; tem quarenta anos disso esperando.
    >>  ............................................
  relaxed.dialogue.conversations.village.offer_help.success/2
    en  Hands. Every village I've known was short of those and long on advice.
    >>  ............................................
    pt  Mãos. Todo vilarejo que conheci teve falta delas e sobra de conselho.
    >>  ............................................
  sensitive.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something, and I'll try not to ask for too much.
    >>  ............................................
    pt  ...Está bem. Vou achar algo, e vou tentar não pedir demais.
    >>  ............................................
  sensitive.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those. Saying so out loud is harder than it should be.
    >>  ............................................
    pt  Mãos. Faltam delas. Dizer isso em voz alta é mais difícil do que devia.
    >>  ............................................
  shy.dialogue.conversations.village.offer_help.success/1
    en  ...Alright. I'll find you something.
    >>  ............................................
    pt  ...Está bem. Vou achar algo pra você.
    >>  ............................................
  shy.dialogue.conversations.village.offer_help.success/2
    en  Hands. We're short of those.
    >>  ............................................
    pt  Mãos. Faltam delas.
    >>  ............................................
  upbeat.dialogue.conversations.village.offer_help.success/1
    en  ...Right, alright! I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...Certo, está bem! Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  upbeat.dialogue.conversations.village.offer_help.success/2
    en  Hands! Good. We're desperately short of those and drowning in advice.
    >>  ............................................
    pt  Mãos! Bom. Estamos desesperadamente sem elas e afogados em conselho.
    >>  ............................................
  witty.dialogue.conversations.village.offer_help.success/1
    en  ...Right, alright! I'll find you something that needs doing. Don't make me regret it.
    >>  ............................................
    pt  ...Certo, está bem! Vou achar algo que precise ser feito. Não me faça me arrepender.
    >>  ............................................
  witty.dialogue.conversations.village.offer_help.success/2
    en  Hands! Good. We're desperately short of those and drowning in advice.
    >>  ............................................
    pt  Mãos! Bom. Estamos desesperadamente sem elas e afogados em conselho.
    >>  ............................................
```

</details>


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `village.offer_help` lands on tier **partial** (axis respect, difficulty 30, stance practical_help)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `village.offer_help.partial`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +1  _(recorded under topic `village.offer_help`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.offer_help.partial
WHO    VILLAGER — what the player reads after pressing "Tell me and I'll help fix it."
       spoken on: conversations.topic.village.fault.followup, button `offer_help`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.help.partial`: the villager qualifys. Subject `village.home`, polarity `neutral`, ends conversation, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.offer_help.partial/1   [49 chars]
    en  That's what people say. It's a fine thing to say.
    >>  ............................................
    pt  É o que as pessoas dizem. É uma bela coisa de se dizer.
    >>  ............................................
  dialogue.conversations.village.offer_help.partial/2   [76 chars]
    en  Mm. I'll believe it when I see you holding the other end of something, %1$s.
    >>  ............................................
    pt  Hm. Acredito quando te vir segurando a outra ponta de alguma coisa, %1$s.
    >>  ............................................
```


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `village.offer_help` lands on tier **rebuff** (axis respect, difficulty 30, stance practical_help)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts -1** — decision id `village.offer_help.rebuff`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +4, respect -2  _(recorded under topic `village.offer_help`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.offer_help.rebuff
WHO    VILLAGER — what the player reads after pressing "Tell me and I'll help fix it."
       spoken on: conversations.topic.village.fault.followup, button `offer_help`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.help.rebuff`: the villager refuses. Subject `village.home`, polarity `negative`, ends conversation, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.offer_help.rebuff/1   [73 chars]
    en  We've had offers. We've had a great many offers. The fence is still down.
    >>  ............................................
    pt  Já tivemos ofertas. Tivemos muitas ofertas. A cerca continua caída.
    >>  ............................................
  dialogue.conversations.village.offer_help.rebuff/2   [86 chars]
    en  Don't. It's easier to offer than to turn up, and I've stopped counting the difference.
    >>  ............................................
    pt  Não. É mais fácil oferecer que aparecer, e eu parei de contar a diferença.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.offer_help.rebuff/1
    en  Don't. Every offer that came to nothing took a bit more out of me, %1$s.
    >>  ............................................
    pt  Não. Cada oferta que deu em nada tirou mais um pouco de mim, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.village.offer_help.rebuff/2
    en  Please don't say it unless you'll do it. I'd rather hear nothing at all.
    >>  ............................................
    pt  Por favor não diga a menos que vá fazer. Prefiro não ouvir nada.
    >>  ............................................
  athletic.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. It'll get done when somebody turns up with a hammer.
    >>  ............................................
    pt  Já tivemos ofertas. Vai ser feito quando alguém aparecer com um martelo.
    >>  ............................................
  athletic.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Say it again in a week and I'll start believing it.
    >>  ............................................
    pt  Não. Diga de novo daqui a uma semana e eu começo a acreditar.
    >>  ............................................
  confident.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. We've had a great many offers. The fence is still down.
    >>  ............................................
    pt  Já tivemos ofertas. Muitas ofertas. A cerca continua caída.
    >>  ............................................
  confident.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Offers are cheap here and I've stopped counting them.
    >>  ............................................
    pt  Não. Oferta é barata aqui e eu parei de contar.
    >>  ............................................
  crabby.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. We've had a great many offers. The fence is still down.
    >>  ............................................
    pt  Já tivemos ofertas. Muitas ofertas. A cerca continua caída.
    >>  ............................................
  crabby.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Offers are cheap here and I've stopped counting them.
    >>  ............................................
    pt  Não. Oferta é barata aqui e eu parei de contar.
    >>  ............................................
  extroverted.dialogue.conversations.village.offer_help.rebuff/1
    en  ...We've had offers, %1$s. I'd like to believe yours and I've been wrong before.
    >>  ............................................
    pt  ...Já tivemos ofertas, %1$s. Eu gostaria de acreditar na sua e eu já errei antes.
    >>  ............................................
  extroverted.dialogue.conversations.village.offer_help.rebuff/2
    en  Don't. Not because it's you — because it's the fourth time somebody's said it.
    >>  ............................................
    pt  Não. Não porque é você — porque é a quarta vez que alguém diz isso.
    >>  ............................................
  flirty.dialogue.conversations.village.offer_help.rebuff/1
    en  ...We've had offers, %1$s. I'd like to believe yours and I've been wrong before.
    >>  ............................................
    pt  ...Já tivemos ofertas, %1$s. Eu gostaria de acreditar na sua e eu já errei antes.
    >>  ............................................
  flirty.dialogue.conversations.village.offer_help.rebuff/2
    en  Don't. Not because it's you — because it's the fourth time somebody's said it.
    >>  ............................................
    pt  Não. Não porque é você — porque é a quarta vez que alguém diz isso.
    >>  ............................................
  friendly.dialogue.conversations.village.offer_help.rebuff/1
    en  ...We've had offers, %1$s. I'd like to believe yours and I've been wrong before.
    >>  ............................................
    pt  ...Já tivemos ofertas, %1$s. Eu gostaria de acreditar na sua e eu já errei antes.
    >>  ............................................
  friendly.dialogue.conversations.village.offer_help.rebuff/2
    en  Don't. Not because it's you — because it's the fourth time somebody's said it.
    >>  ............................................
    pt  Não. Não porque é você — porque é a quarta vez que alguém diz isso.
    >>  ............................................
  gloomy.dialogue.conversations.village.offer_help.rebuff/1
    en  Don't. Every offer that came to nothing took a bit more out of me, %1$s.
    >>  ............................................
    pt  Não. Cada oferta que deu em nada tirou mais um pouco de mim, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.village.offer_help.rebuff/2
    en  Please don't say it unless you'll do it. I'd rather hear nothing at all.
    >>  ............................................
    pt  Por favor não diga a menos que vá fazer. Prefiro não ouvir nada.
    >>  ............................................
  greedy.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. We've had a great many offers. The fence is still down.
    >>  ............................................
    pt  Já tivemos ofertas. Muitas ofertas. A cerca continua caída.
    >>  ............................................
  greedy.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Offers are cheap here and I've stopped counting them.
    >>  ............................................
    pt  Não. Oferta é barata aqui e eu parei de contar.
    >>  ............................................
  grumpy.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. We've had a great many offers. The fence is still down.
    >>  ............................................
    pt  Já tivemos ofertas. Muitas ofertas. A cerca continua caída.
    >>  ............................................
  grumpy.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Offers are cheap here and I've stopped counting them.
    >>  ............................................
    pt  Não. Oferta é barata aqui e eu parei de contar.
    >>  ............................................
  introverted.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. The fence is still down.
    >>  ............................................
    pt  Já tivemos ofertas. A cerca continua caída.
    >>  ............................................
  introverted.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Come back when you've a day free and we'll see.
    >>  ............................................
    pt  Não. Volte quando tiver um dia livre e a gente vê.
    >>  ............................................
  lazy.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. It'll get done when somebody turns up with a hammer.
    >>  ............................................
    pt  Já tivemos ofertas. Vai ser feito quando alguém aparecer com um martelo.
    >>  ............................................
  lazy.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Say it again in a week and I'll start believing it.
    >>  ............................................
    pt  Não. Diga de novo daqui a uma semana e eu começo a acreditar.
    >>  ............................................
  odd.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. The fence is still down.
    >>  ............................................
    pt  Já tivemos ofertas. A cerca continua caída.
    >>  ............................................
  odd.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Come back when you've a day free and we'll see.
    >>  ............................................
    pt  Não. Volte quando tiver um dia livre e a gente vê.
    >>  ............................................
  peaceful.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. It'll get done when somebody turns up with a hammer.
    >>  ............................................
    pt  Já tivemos ofertas. Vai ser feito quando alguém aparecer com um martelo.
    >>  ............................................
  peaceful.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Say it again in a week and I'll start believing it.
    >>  ............................................
    pt  Não. Diga de novo daqui a uma semana e eu começo a acreditar.
    >>  ............................................
  peppy.dialogue.conversations.village.offer_help.rebuff/1
    en  Offers! We've had those. We've a whole shelf of them and no fence.
    >>  ............................................
    pt  Ofertas! Já tivemos. Temos uma prateleira delas e nenhuma cerca.
    >>  ............................................
  peppy.dialogue.conversations.village.offer_help.rebuff/2
    en  Right — no. I'd rather one afternoon than another promise, %1$s.
    >>  ............................................
    pt  Certo — não. Prefiro uma tarde a mais uma promessa, %1$s.
    >>  ............................................
  playful.dialogue.conversations.village.offer_help.rebuff/1
    en  Offers! We've had those. We've a whole shelf of them and no fence.
    >>  ............................................
    pt  Ofertas! Já tivemos. Temos uma prateleira delas e nenhuma cerca.
    >>  ............................................
  playful.dialogue.conversations.village.offer_help.rebuff/2
    en  Right — no. I'd rather one afternoon than another promise, %1$s.
    >>  ............................................
    pt  Certo — não. Prefiro uma tarde a mais uma promessa, %1$s.
    >>  ............................................
  relaxed.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. It'll get done when somebody turns up with a hammer.
    >>  ............................................
    pt  Já tivemos ofertas. Vai ser feito quando alguém aparecer com um martelo.
    >>  ............................................
  relaxed.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Say it again in a week and I'll start believing it.
    >>  ............................................
    pt  Não. Diga de novo daqui a uma semana e eu começo a acreditar.
    >>  ............................................
  sensitive.dialogue.conversations.village.offer_help.rebuff/1
    en  Don't. Every offer that came to nothing took a bit more out of me, %1$s.
    >>  ............................................
    pt  Não. Cada oferta que deu em nada tirou mais um pouco de mim, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.village.offer_help.rebuff/2
    en  Please don't say it unless you'll do it. I'd rather hear nothing at all.
    >>  ............................................
    pt  Por favor não diga a menos que vá fazer. Prefiro não ouvir nada.
    >>  ............................................
  shy.dialogue.conversations.village.offer_help.rebuff/1
    en  We've had offers. The fence is still down.
    >>  ............................................
    pt  Já tivemos ofertas. A cerca continua caída.
    >>  ............................................
  shy.dialogue.conversations.village.offer_help.rebuff/2
    en  No. Come back when you've a day free and we'll see.
    >>  ............................................
    pt  Não. Volte quando tiver um dia livre e a gente vê.
    >>  ............................................
  upbeat.dialogue.conversations.village.offer_help.rebuff/1
    en  Offers! We've had those. We've a whole shelf of them and no fence.
    >>  ............................................
    pt  Ofertas! Já tivemos. Temos uma prateleira delas e nenhuma cerca.
    >>  ............................................
  upbeat.dialogue.conversations.village.offer_help.rebuff/2
    en  Right — no. I'd rather one afternoon than another promise, %1$s.
    >>  ............................................
    pt  Certo — não. Prefiro uma tarde a mais uma promessa, %1$s.
    >>  ............................................
  witty.dialogue.conversations.village.offer_help.rebuff/1
    en  Offers! We've had those. We've a whole shelf of them and no fence.
    >>  ............................................
    pt  Ofertas! Já tivemos. Temos uma prateleira delas e nenhuma cerca.
    >>  ............................................
  witty.dialogue.conversations.village.offer_help.rebuff/2
    en  Right — no. I'd rather one afternoon than another promise, %1$s.
    >>  ............................................
    pt  Certo — não. Prefiro uma tarde a mais uma promessa, %1$s.
    >>  ............................................
```

</details>


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: **hearts +2** — decision id `village.fault.offer_help`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +5, trust +2  _(recorded under topic `village.followup.offer_help`)_
- Does: arc `village` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.followup.offer_help
WHO    VILLAGER — what the player reads after pressing "Tell me and I'll help fix it."
       spoken on: conversations.topic.village.fault.followup, button `offer_help`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.help.plain`: the villager accepts. Subject `village.home`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.followup.offer_help/1   [81 chars]
    en  ...You'd actually put a hand to it? Opinions I get plenty of. Hands, not so much.
    >>  ............................................
    pt  ...Você poria a mão mesmo? Opinião eu recebo bastante. Mão, nem tanto.
    >>  ............................................
  dialogue.conversations.village.followup.offer_help/2   [69 chars]
    en  Then you're worth more to this place than half the people born in it.
    >>  ............................................
    pt  Então você vale mais para este lugar do que metade de quem nasceu nele.
    >>  ............................................
  dialogue.conversations.village.followup.offer_help/3   [72 chars]
    en  Careful — say that where the mayor hears and you'll have a job for life.
    >>  ............................................
    pt  Cuidado — diga isso onde o prefeito ouça e você terá emprego vitalício.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? I'd stopped asking, because asking gets opinions.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Eu tinha parado de pedir, porque pedir rende opinião.
    >>  ............................................
  anxious.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half of us, and I'd not know how to say it better.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de nós, e não sei dizer melhor.
    >>  ............................................
  anxious.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and it stops being a kindness and becomes a duty.
    >>  ............................................
    pt  Cuidado — diga onde o prefeito ouça e deixa de ser gentileza e vira obrigação.
    >>  ............................................
  athletic.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd put a hand to it? Thirty years of opinions and about four pairs of hands.
    >>  ............................................
    pt  ...Você botaria a mão? Trinta anos de opinião e umas quatro duplas de mãos.
    >>  ............................................
  athletic.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more here than half the people born in it, and I've counted.
    >>  ............................................
    pt  Então você vale mais aqui que metade de quem nasceu nele, e eu contei.
    >>  ............................................
  athletic.dialogue.conversations.village.followup.offer_help/3
    en  Careful. Say that where the mayor hears and you'll be on a rota by Thursday.
    >>  ............................................
    pt  Cuidado. Diga onde o prefeito ouça e estará numa escala até quinta.
    >>  ............................................
  confident.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? Opinions I get plenty of. Hands, not so much.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Opinião eu recebo bastante. Mãos, nem tanto.
    >>  ............................................
  confident.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele.
    >>  ............................................
  confident.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and you'll have a job for life.
    >>  ............................................
    pt  Cuidado — diga isso onde o prefeito ouça e terá emprego pra vida toda.
    >>  ............................................
  crabby.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? Opinions I get plenty of. Hands, not so much.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Opinião eu recebo bastante. Mãos, nem tanto.
    >>  ............................................
  crabby.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele.
    >>  ............................................
  crabby.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and you'll have a job for life.
    >>  ............................................
    pt  Cuidado — diga isso onde o prefeito ouça e terá emprego pra vida toda.
    >>  ............................................
  extroverted.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it, %1$s? I get opinions. I don't get hands.
    >>  ............................................
    pt  ...Você botaria a mão mesmo, %1$s? Eu recebo opinião. Não recebo mãos.
    >>  ............................................
  extroverted.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele.
    >>  ............................................
  extroverted.dialogue.conversations.village.followup.offer_help/3
    en  Careful. Say that where the mayor hears and you'll never be free of us.
    >>  ............................................
    pt  Cuidado. Diga onde o prefeito ouça e nunca mais se livra da gente.
    >>  ............................................
  flirty.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it, %1$s? I get opinions. I don't get hands.
    >>  ............................................
    pt  ...Você botaria a mão mesmo, %1$s? Eu recebo opinião. Não recebo mãos.
    >>  ............................................
  flirty.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele.
    >>  ............................................
  flirty.dialogue.conversations.village.followup.offer_help/3
    en  Careful. Say that where the mayor hears and you'll never be free of us.
    >>  ............................................
    pt  Cuidado. Diga onde o prefeito ouça e nunca mais se livra da gente.
    >>  ............................................
  friendly.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it, %1$s? I get opinions. I don't get hands.
    >>  ............................................
    pt  ...Você botaria a mão mesmo, %1$s? Eu recebo opinião. Não recebo mãos.
    >>  ............................................
  friendly.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele.
    >>  ............................................
  friendly.dialogue.conversations.village.followup.offer_help/3
    en  Careful. Say that where the mayor hears and you'll never be free of us.
    >>  ............................................
    pt  Cuidado. Diga onde o prefeito ouça e nunca mais se livra da gente.
    >>  ............................................
  gloomy.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? I'd stopped asking, because asking gets opinions.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Eu tinha parado de pedir, porque pedir rende opinião.
    >>  ............................................
  gloomy.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half of us, and I'd not know how to say it better.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de nós, e não sei dizer melhor.
    >>  ............................................
  gloomy.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and it stops being a kindness and becomes a duty.
    >>  ............................................
    pt  Cuidado — diga onde o prefeito ouça e deixa de ser gentileza e vira obrigação.
    >>  ............................................
  greedy.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? Opinions I get plenty of. Hands, not so much.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Opinião eu recebo bastante. Mãos, nem tanto.
    >>  ............................................
  greedy.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele.
    >>  ............................................
  greedy.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and you'll have a job for life.
    >>  ............................................
    pt  Cuidado — diga isso onde o prefeito ouça e terá emprego pra vida toda.
    >>  ............................................
  grumpy.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? Opinions I get plenty of. Hands, not so much.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Opinião eu recebo bastante. Mãos, nem tanto.
    >>  ............................................
  grumpy.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele.
    >>  ............................................
  grumpy.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and you'll have a job for life.
    >>  ............................................
    pt  Cuidado — diga isso onde o prefeito ouça e terá emprego pra vida toda.
    >>  ............................................
  introverted.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd put a hand to it? Opinions I get. Hands, no.
    >>  ............................................
    pt  ...Você botaria a mão? Opinião eu recebo. Mãos, não.
    >>  ............................................
  introverted.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more here than most.
    >>  ............................................
    pt  Então você vale mais aqui que quase todos.
    >>  ............................................
  introverted.dialogue.conversations.village.followup.offer_help/3
    en  Careful. The mayor hears that and you're never free.
    >>  ............................................
    pt  Cuidado. O prefeito ouve isso e você nunca se livra.
    >>  ............................................
  lazy.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd put a hand to it? Thirty years of opinions and about four pairs of hands.
    >>  ............................................
    pt  ...Você botaria a mão? Trinta anos de opinião e umas quatro duplas de mãos.
    >>  ............................................
  lazy.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more here than half the people born in it, and I've counted.
    >>  ............................................
    pt  Então você vale mais aqui que metade de quem nasceu nele, e eu contei.
    >>  ............................................
  lazy.dialogue.conversations.village.followup.offer_help/3
    en  Careful. Say that where the mayor hears and you'll be on a rota by Thursday.
    >>  ............................................
    pt  Cuidado. Diga onde o prefeito ouça e estará numa escala até quinta.
    >>  ............................................
  odd.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd put a hand to it? Opinions I get. Hands, no.
    >>  ............................................
    pt  ...Você botaria a mão? Opinião eu recebo. Mãos, não.
    >>  ............................................
  odd.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more here than most.
    >>  ............................................
    pt  Então você vale mais aqui que quase todos.
    >>  ............................................
  odd.dialogue.conversations.village.followup.offer_help/3
    en  Careful. The mayor hears that and you're never free.
    >>  ............................................
    pt  Cuidado. O prefeito ouve isso e você nunca se livra.
    >>  ............................................
  peaceful.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd put a hand to it? Thirty years of opinions and about four pairs of hands.
    >>  ............................................
    pt  ...Você botaria a mão? Trinta anos de opinião e umas quatro duplas de mãos.
    >>  ............................................
  peaceful.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more here than half the people born in it, and I've counted.
    >>  ............................................
    pt  Então você vale mais aqui que metade de quem nasceu nele, e eu contei.
    >>  ............................................
  peaceful.dialogue.conversations.village.followup.offer_help/3
    en  Careful. Say that where the mayor hears and you'll be on a rota by Thursday.
    >>  ............................................
    pt  Cuidado. Diga onde o prefeito ouça e estará numa escala até quinta.
    >>  ............................................
  peppy.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? Opinions I get by the cartload. Hands, never.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Opinião eu recebo aos carros. Mãos, nunca.
    >>  ............................................
  peppy.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it, and I'll say so.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele, e eu vou dizer.
    >>  ............................................
  peppy.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and you'll have a job for life!
    >>  ............................................
    pt  Cuidado — diga onde o prefeito ouça e terá emprego pra vida toda!
    >>  ............................................
  playful.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? Opinions I get by the cartload. Hands, never.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Opinião eu recebo aos carros. Mãos, nunca.
    >>  ............................................
  playful.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it, and I'll say so.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele, e eu vou dizer.
    >>  ............................................
  playful.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and you'll have a job for life!
    >>  ............................................
    pt  Cuidado — diga onde o prefeito ouça e terá emprego pra vida toda!
    >>  ............................................
  relaxed.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd put a hand to it? Thirty years of opinions and about four pairs of hands.
    >>  ............................................
    pt  ...Você botaria a mão? Trinta anos de opinião e umas quatro duplas de mãos.
    >>  ............................................
  relaxed.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more here than half the people born in it, and I've counted.
    >>  ............................................
    pt  Então você vale mais aqui que metade de quem nasceu nele, e eu contei.
    >>  ............................................
  relaxed.dialogue.conversations.village.followup.offer_help/3
    en  Careful. Say that where the mayor hears and you'll be on a rota by Thursday.
    >>  ............................................
    pt  Cuidado. Diga onde o prefeito ouça e estará numa escala até quinta.
    >>  ............................................
  sensitive.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? I'd stopped asking, because asking gets opinions.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Eu tinha parado de pedir, porque pedir rende opinião.
    >>  ............................................
  sensitive.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half of us, and I'd not know how to say it better.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de nós, e não sei dizer melhor.
    >>  ............................................
  sensitive.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and it stops being a kindness and becomes a duty.
    >>  ............................................
    pt  Cuidado — diga onde o prefeito ouça e deixa de ser gentileza e vira obrigação.
    >>  ............................................
  shy.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd put a hand to it? Opinions I get. Hands, no.
    >>  ............................................
    pt  ...Você botaria a mão? Opinião eu recebo. Mãos, não.
    >>  ............................................
  shy.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more here than most.
    >>  ............................................
    pt  Então você vale mais aqui que quase todos.
    >>  ............................................
  shy.dialogue.conversations.village.followup.offer_help/3
    en  Careful. The mayor hears that and you're never free.
    >>  ............................................
    pt  Cuidado. O prefeito ouve isso e você nunca se livra.
    >>  ............................................
  upbeat.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? Opinions I get by the cartload. Hands, never.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Opinião eu recebo aos carros. Mãos, nunca.
    >>  ............................................
  upbeat.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it, and I'll say so.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele, e eu vou dizer.
    >>  ............................................
  upbeat.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and you'll have a job for life!
    >>  ............................................
    pt  Cuidado — diga onde o prefeito ouça e terá emprego pra vida toda!
    >>  ............................................
  witty.dialogue.conversations.village.followup.offer_help/1
    en  ...You'd actually put a hand to it? Opinions I get by the cartload. Hands, never.
    >>  ............................................
    pt  ...Você botaria a mão mesmo? Opinião eu recebo aos carros. Mãos, nunca.
    >>  ............................................
  witty.dialogue.conversations.village.followup.offer_help/2
    en  Then you're worth more to this place than half the people born in it, and I'll say so.
    >>  ............................................
    pt  Então você vale mais pra este lugar que metade de quem nasceu nele, e eu vou dizer.
    >>  ............................................
  witty.dialogue.conversations.village.followup.offer_help/3
    en  Careful — say that where the mayor hears and you'll have a job for life!
    >>  ............................................
    pt  Cuidado — diga onde o prefeito ouça e terá emprego pra vida toda!
    >>  ............................................
```

</details>


### Button `criticise_fairly` — "It could be run better, though."

*stance family `respectful_disagreement` · tone `blunt` · outcome `accepted` · answers the beat(s) `village.fault_named`, `village.fault_smith` · offered only once the villager has actually said `village:fault_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.fault.criticise` — accepted phrasings: "it could be run better though"; "it is badly managed"; "it could be managed better"
  - the message must contain one of: `run`, `managed`
  - scored words: `run`(1.5), `managed`(1.5), `better`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.village.fault.followup.criticise_fairly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.fault.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.fault.followup.criticise_fairly   [31 chars]
    en  It could be run better, though.
    >>  ............................................
    pt  Mas poderia ser administrado melhor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +100 when the village rank is `mayor`
- Does: **hearts +1** — decision id `village.fault.criticise_fairly`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `village.followup.criticise_fairly`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.criticise_fairly.mayor
WHO    VILLAGER — what the player reads after pressing "It could be run better, though."
       spoken on: conversations.topic.village.fault.followup, button `criticise_fairly`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.criticised.mayor`: the villager accepts. Subject `village.criticism`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.criticise_fairly.mayor/1   [90 chars]
    en  It could. I'd know — it's my name on it when it isn't. Go on, then: where would you start?
    >>  ............................................
    pt  Poderia. Eu saberia — é o meu nome nela quando não está. Vá lá: por onde você começaria?
    >>  ............................................
  dialogue.conversations.village.criticise_fairly.mayor/2   [87 chars]
    en  You're telling the mayor his village could be run better. ...Bold. And not wrong, %1$s.
    >>  ............................................
    pt  Você está dizendo ao prefeito que a vila dele podia ser melhor administrada. ...Ousado. E não errado, %1$s.
    >>  ............................................
  dialogue.conversations.village.criticise_fairly.mayor/3   [75 chars]
    en  I've a list of my own that long. The trouble is which end to pick it up by.
    >>  ............................................
    pt  Tenho uma lista desse tamanho. O problema é por qual ponta pegar.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: RULED OUT when the village rank is `mayor`  _(chance -2000)_
- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `confident`
- Does: **hearts +1** — decision id `village.fault.criticise_fairly`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `village.followup.criticise_fairly`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.followup.criticise_fairly.landed
WHO    VILLAGER — what the player reads after pressing "It could be run better, though."
       spoken on: conversations.topic.village.fault.followup, button `criticise_fairly`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.criticised.landed`: the villager accepts. Subject `village.criticism`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.followup.criticise_fairly.landed/1   [58 chars]
    en  It could. Somebody had to say it plainly and nobody would.
    >>  ............................................
    pt  Poderia. Alguém tinha que dizer claramente e ninguém dizia.
    >>  ............................................
  dialogue.conversations.village.followup.criticise_fairly.landed/2   [59 chars]
    en  Aye. Badly, in places. You'll not offend me with the truth.
    >>  ............................................
    pt  É. Mal, em alguns pontos. Você não me ofende com a verdade.
    >>  ............................................
  dialogue.conversations.village.followup.criticise_fairly.landed/3   [52 chars]
    en  Finally. I've been saying it at the well for a year.
    >>  ............................................
    pt  Enfim. Venho dizendo isso no poço faz um ano.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `0`

- Fires when: weighted +100 when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`
- Does: **hearts -1** — decision id `village.fault.criticise_fairly`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +3, warmth -2  _(recorded under topic `village.followup.criticise_fairly`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.followup.criticise_fairly.flat
WHO    VILLAGER — what the player reads after pressing "It could be run better, though."
       spoken on: conversations.topic.village.fault.followup, button `criticise_fairly`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.criticised.flat`: the villager resists. Subject `village.criticism`, polarity `negative`, ends conversation, outcome `resisted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.followup.criticise_fairly.flat/1   [58 chars]
    en  Easy to say, passing through. We're the ones living in it.
    >>  ............................................
    pt  Fácil dizer, de passagem. Somos nós que vivemos nela.
    >>  ............................................
  dialogue.conversations.village.followup.criticise_fairly.flat/2   [52 chars]
    en  ...Perhaps. It's still ours to criticise, not yours.
    >>  ............................................
    pt  ...Talvez. Mas a crítica é nossa, não sua.
    >>  ............................................
  dialogue.conversations.village.followup.criticise_fairly.flat/3   [61 chars]
    en  Run it better how? Everyone has a plan until it's their turn.
    >>  ............................................
    pt  Administrar melhor como? Todo mundo tem um plano até chegar a sua vez.
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `confident`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `anxious`, `sensitive`, `gloomy`, `introverted`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `village.followup.criticise_fairly`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.followup.criticise_fairly.polite
WHO    VILLAGER — what the player reads after pressing "It could be run better, though."
       spoken on: conversations.topic.village.fault.followup, button `criticise_fairly`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.criticised.polite`: the villager qualifys. Subject `village.criticism`, polarity `neutral`, ends conversation, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.followup.criticise_fairly.polite/1   [43 chars]
    en  Most places could. Ours no worse than most.
    >>  ............................................
    pt  Quase todo lugar poderia. A nossa não é pior que a maioria.
    >>  ............................................
  dialogue.conversations.village.followup.criticise_fairly.polite/2   [62 chars]
    en  Aye, probably. Nothing's run as well as it looks from outside.
    >>  ............................................
    pt  É, provavelmente. Nada é tão bem administrado quanto parece de fora.
    >>  ............................................
  dialogue.conversations.village.followup.criticise_fairly.polite/3   [61 chars]
    en  There's truth in that. There's truth in most complaints here.
    >>  ............................................
    pt  Tem verdade nisso. Tem verdade na maioria das reclamações aqui.
    >>  ............................................
```


### Button `agree` — "You're right about that."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `village.fault_named`, `village.fault_smith` · offered only once the villager has actually said `village:fault_named`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.fault.agree` — accepted phrasings: "you are right about that"; "that is true enough"; "i agree with the list"
  - the message must contain one of: `agree`, `true`
  - scored words: `agree`(1.5), `true`(1.2), `right`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.village.fault.followup.agree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.fault.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.fault.followup.agree   [24 chars]
    en  You're right about that.
    >>  ............................................
    pt  Você tem razão nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the season is `winter`
- Fires when: RULED OUT when the `seasons` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `village.fault.agree`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `village.followup.agree`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.agree.winter
WHO    VILLAGER — what the player reads after pressing "You're right about that."
       spoken on: conversations.topic.village.fault.followup, button `agree`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = season
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.agree.winter.terminal`: the villager accepts. Subject `village.season`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.agree.winter/1   [72 chars]
    en  In %2$s you find out what a village is actually made of. This one holds.
    >>  ............................................
    pt  No %2$s a gente descobre do que uma vila é realmente feita. Esta aqui aguenta.
    >>  ............................................
  dialogue.conversations.village.agree.winter/2   [67 chars]
    en  Ask me again in spring and I'll be less sentimental about it, %1$s.
    >>  ............................................
    pt  Me pergunte de novo na primavera e eu serei menos sentimental, %1$s.
    >>  ............................................
  dialogue.conversations.village.agree.winter/3   [70 chars]
    en  It's the cold months that prove it. Anyone can like a place in summer.
    >>  ............................................
    pt  São os meses frios que provam. Qualquer um gosta de um lugar no verão.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the season is `winter`  _(chance -2000)_
- Does: **hearts +1** — decision id `village.fault.agree`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `village.followup.agree`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.followup.agree
WHO    VILLAGER — what the player reads after pressing "You're right about that."
       spoken on: conversations.topic.village.fault.followup, button `agree`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.agreed`: the villager accepts. Subject `village.praise`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.followup.agree/1   [40 chars]
    en  Good. Then you've been paying attention.
    >>  ............................................
    pt  Bom. Então você andou prestando atenção.
    >>  ............................................
  dialogue.conversations.village.followup.agree/2   [44 chars]
    en  Aye. It's nice to be agreed with about home.
    >>  ............................................
    pt  É. É bom ser concordado sobre a própria casa.
    >>  ............................................
  dialogue.conversations.village.followup.agree/3   [38 chars]
    en  Right. Don't tell the mayor I said it.
    >>  ............................................
    pt  Certo. Não conte ao prefeito que eu disse.
    >>  ............................................
```


### Button `joke` — "It's held together with string and stubbornness."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `village.fault_named`, `village.fault_smith` · offered only once the villager has actually said `village:home`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.fault.joke` — accepted phrasings: "it is held together with string and stubbornness"; "the place runs on stubbornness"; "held together with string"
  - the message must contain one of: `stubbornness`, `held`
  - scored words: `stubbornness`(1.5), `held`(1.2), `together`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.village.fault.followup.joke
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.fault.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.fault.followup.joke   [48 chars]
    en  It's held together with string and stubbornness.
    >>  ............................................
    pt  Está preso com barbante e teimosia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.fault.joke`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, tension -1  _(recorded under topic `village.followup.joke`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.followup.joke
WHO    VILLAGER — what the player reads after pressing "It's held together with string and stubbornness."
       spoken on: conversations.topic.village.fault.followup, button `joke`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.joked`: the villager accepts. Subject `village.belonging`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.followup.joke/1   [96 chars]
    en  Ha! String, stubbornness and one very determined blacksmith. That's the whole engineering of it.
    >>  ............................................
    pt  Ha! Barbante, teimosia e um ferreiro muito determinado. É toda a engenharia da coisa.
    >>  ............................................
  dialogue.conversations.village.followup.joke/2   [52 chars]
    en  Mostly stubbornness. The string is decorative, %1$s.
    >>  ............................................
    pt  Teimosia, principalmente. O barbante é decorativo, %1$s.
    >>  ............................................
  dialogue.conversations.village.followup.joke/3   [82 chars]
    en  You've been paying attention. Don't say it near the mayor, he thinks it's masonry.
    >>  ............................................
    pt  Você andou prestando atenção. Não diga perto do prefeito, ele acha que é alvenaria.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `village.fault_named`, `village.fault_smith` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.fault.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.fault.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.fault.followup.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.village.fault.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.left`: the villager accepts. Subject `village.belonging`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.village.insulted.followup / leave; conversations.topic.village.praised.followup / leave; conversations.topic.village.respond / leave; conversations.topic.village.settled.followup / leave
```

```text
  dialogue.conversations.village.respond.leave/1   [21 chars]
    en  Aye. Mind how you go.
    >>  ............................................
    pt  É. Se cuida.
    >>  ............................................
  dialogue.conversations.village.respond.leave/2   [19 chars]
    en  Get on, then, %1$s.
    >>  ............................................
    pt  Então vá, %1$s.
    >>  ............................................
  dialogue.conversations.village.respond.leave/3   [15 chars]
    en  Leave it there.
    >>  ............................................
    pt  Deixe por aí.
    >>  ............................................
```

---


## `conversations.topic.village.insulted.followup`

**Reached from 1 route(s):** `conversations.topic.village.respond` / `insult`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.respond.insult` — e.g. "...It's my home, %1$s. Say that somewhere else."


```text
POOL   dialogue key: dialogue.conversations.topic.village.insulted.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.insulted.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.insulted.followup   [32 chars]
    en  Say it somewhere else next time.
    >>  ............................................
    pt  Da próxima vez, diga isso em outro lugar.
    >>  ............................................
```


### Button `apologize` — "That was out of order. I'm sorry."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `village.insulted` · offered only once the villager has actually said `player:insulted_village`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.insulted.apologize` — accepted phrasings: "that was out of order, i am sorry"; "that was rude of me"; "i should not have said that about your home"
  - the message must contain one of: `order`, `sorry`, `rude`
  - scored words: `order`(1.2), `sorry`(1.2), `rude`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.village.insulted.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.insulted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.insulted.followup.apologize   [33 chars]
    en  That was out of order. I'm sorry.
    >>  ............................................
    pt  Isso foi fora de propósito. Me desculpe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4  _(recorded under topic `village.insulted.apologize`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.insulted.apologize
WHO    VILLAGER — what the player reads after pressing "That was out of order. I'm sorry."
       spoken on: conversations.topic.village.insulted.followup, button `apologize`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.insulted.apologize`: the villager qualifys. Subject `village.insult`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.insulted.apologize/1   [59 chars]
    en  ...It was. People have built their whole lives in this mud.
    >>  ............................................
    pt  ...Foi. As pessoas construíram a vida inteira nesta lama.
    >>  ............................................
  dialogue.conversations.village.insulted.apologize/2   [71 chars]
    en  Aye. Say it to the mason as well, he laid half of what you walked past.
    >>  ............................................
    pt  É. Diga ao pedreiro também, ele assentou metade do que você passou por cima.
    >>  ............................................
  dialogue.conversations.village.insulted.apologize/3   [44 chars]
    en  Accepted. Don't make me hear it twice, %1$s.
    >>  ............................................
    pt  Aceito. Não me faça ouvir isso duas vezes, %1$s.
    >>  ............................................
```


### Button `explain` — "I've had a worse day than this place deserves."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `village.insulted`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.insulted.explain` — accepted phrasings: "i have had a worse day than this place deserves"; "it was not about the village"; "i did not mean it that way"
  - the message must contain one of: `worse`, `day`, `meant`
  - scored words: `worse`(1.2), `day`(1.0), `meant`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.village.insulted.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.insulted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.insulted.followup.explain   [46 chars]
    en  I've had a worse day than this place deserves.
    >>  ............................................
    pt  Tive um dia pior do que este lugar merece.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `village.insulted.explain`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.insulted.explain
WHO    VILLAGER — what the player reads after pressing "I've had a worse day than this place deserves."
       spoken on: conversations.topic.village.insulted.followup, button `explain`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.insulted.explain`: the villager qualifys. Subject `village.insult`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.insulted.explain/1   [71 chars]
    en  Then say that, and not the other thing. We'd have agreed about the day.
    >>  ............................................
    pt  Então diga isso, e não a outra coisa. A gente teria concordado sobre o dia.
    >>  ............................................
  dialogue.conversations.village.insulted.explain/2   [64 chars]
    en  Everyone has. Most of them don't take it out on the walls, %1$s.
    >>  ............................................
    pt  Todo mundo tem. A maioria não desconta nas paredes, %1$s.
    >>  ............................................
  dialogue.conversations.village.insulted.explain/3   [58 chars]
    en  ...Right. I'll take that. Come back when the day's turned.
    >>  ............................................
    pt  ...Certo. Aceito. Volte quando o dia melhorar.
    >>  ............................................
```


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `village.insulted` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.insulted.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.insulted.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.insulted.followup.leave   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.village.insulted.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.left`: the villager accepts. Subject `village.belonging`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.village.fault.followup / leave; conversations.topic.village.praised.followup / leave; conversations.topic.village.respond / leave; conversations.topic.village.settled.followup / leave
```

> Written out in full under **`conversations.topic.village.fault.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.village.none.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.none` — e.g. "I don't really have a place. I go where the road goes."


```text
POOL   dialogue key: dialogue.conversations.topic.village.none.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.none.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.none.respond   [23 chars]
    en  No village to speak of.
    >>  ............................................
    pt  Nenhuma vila para falar.
    >>  ............................................
```


### Button `sympathise` — "That's a hard way to live."

*stance family `empathy` · tone `gentle` · answers the beat(s) `village.none.to.village.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.none.sympathise` — accepted phrasings: "that is a hard way to live"; "that sounds rough"; "that must be difficult"
  - the message must contain one of: `hard`, `rough`, `difficult`
  - scored words: `hard`(1.5), `rough`(1.2), `difficult`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.village.none.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.none.respond.sympathise   [26 chars]
    en  That's a hard way to live.
    >>  ............................................
    pt  É um jeito difícil de viver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `village.none.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +3  _(recorded under topic `village.none.sympathise`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.none.sympathise
WHO    VILLAGER — what the player reads after pressing "That's a hard way to live."
       spoken on: conversations.topic.village.none.respond, button `sympathise`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.none.sympathise.terminal`: the villager accepts. Subject `village.no_home`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.none.sympathise/1   [66 chars]
    en  ...It is. Thank you for calling it what it is instead of a choice.
    >>  ............................................
    pt  ...É. Obrigado por chamar do que é em vez de chamar de escolha.
    >>  ............................................
  dialogue.conversations.village.none.sympathise/2   [73 chars]
    en  Hard's the word. Everyone else says 'free', as though that were the same.
    >>  ............................................
    pt  Difícil é a palavra. Todo mundo diz 'livre', como se fosse a mesma coisa.
    >>  ............................................
  dialogue.conversations.village.none.sympathise/3   [57 chars]
    en  Aye. Kind of you to notice it without making it a lesson.
    >>  ............................................
    pt  É. Gentil da sua parte notar sem transformar em lição.
    >>  ............................................
```


### Button `ask_where` — "Where do you stay?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `village.none.to.village.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.none.ask_where` — accepted phrasings: "where do you stay"; "where do you sleep"; "where are you living"
  - the message must contain one of: `where`, `stay`, `sleep`
  - scored words: `where`(1.5), `stay`(1.2), `sleep`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.village.none.respond.ask_where
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.none.respond.ask_where   [18 chars]
    en  Where do you stay?
    >>  ............................................
    pt  Onde você fica?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `village.none.ask_where`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.none.ask_where
WHO    VILLAGER — what the player reads after pressing "Where do you stay?"
       spoken on: conversations.topic.village.none.respond, button `ask_where`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.none.ask_where.terminal`: the villager accepts. Subject `village.no_home`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.none.ask_where/1   [52 chars]
    en  Wherever the roof holds. It changes with the season.
    >>  ............................................
    pt  Onde o telhado aguentar. Muda com a estação.
    >>  ............................................
  dialogue.conversations.village.none.ask_where/2   [61 chars]
    en  Here and there. I'd rather not be more exact than that, %1$s.
    >>  ............................................
    pt  Aqui e ali. Prefiro não ser mais exato que isso, %1$s.
    >>  ............................................
  dialogue.conversations.village.none.ask_where/3   [56 chars]
    en  Nowhere with a name. Ask me again when it does have one.
    >>  ............................................
    pt  Em nenhum lugar com nome. Me pergunte de novo quando tiver um.
    >>  ............................................
```


### Button `dismiss` — "Your own fault, surely."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `village.none.to.village.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.none.dismiss` — accepted phrasings: "your own fault surely"; "that is your own doing"; "you are to blame"
  - the message must contain one of: `fault`, `own`, `blame`
  - scored words: `fault`(1.5), `own`(1.0), `blame`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.village.none.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.none.respond.dismiss   [23 chars]
    en  Your own fault, surely.
    >>  ............................................
    pt  Culpa sua, com certeza.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `village.none.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -4, tension +5  _(recorded under topic `village.none.dismiss`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.none.dismiss
WHO    VILLAGER — what the player reads after pressing "Your own fault, surely."
       spoken on: conversations.topic.village.none.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.none.dismiss.terminal`: the villager dismisss. Subject `village.no_home`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.none.dismiss/1   [52 chars]
    en  ...My fault. Right. You know nothing about it, %1$s.
    >>  ............................................
    pt  ...Culpa minha. Certo. Você não sabe nada sobre isso, %1$s.
    >>  ............................................
  dialogue.conversations.village.none.dismiss/2   [54 chars]
    en  There's a lot you're assuming there. Most of it wrong.
    >>  ............................................
    pt  Tem muita coisa que você está presumindo. Quase tudo errado.
    >>  ............................................
  dialogue.conversations.village.none.dismiss/3   [55 chars]
    en  Aye, well. Easy judgement is cheap and you got it free.
    >>  ............................................
    pt  É, bom. Julgamento fácil é barato e você levou de graça.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.none.dismiss/1
    en  ...My fault. I've thought that at three in the morning more than you'd guess, %1$s.
    >>  ............................................
    pt  ...Culpa minha. Já pensei isso às três da manhã mais do que você imagina, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.village.none.dismiss/2
    en  Right. Yes. I know. I know better than you do, actually.
    >>  ............................................
    pt  Certo. Sim. Eu sei. Na verdade eu sei melhor que você.
    >>  ............................................
  anxious.dialogue.conversations.village.none.dismiss/3
    en  ...I'll not talk about the village with anybody for a while.
    >>  ............................................
    pt  ...Não vou falar do vilarejo com ninguém por um tempo.
    >>  ............................................
  athletic.dialogue.conversations.village.none.dismiss/1
    en  My fault. Well. Blame's cheap and the fence is still down.
    >>  ............................................
    pt  Culpa minha. Bom. Culpa é barata e a cerca continua caída.
    >>  ............................................
  athletic.dialogue.conversations.village.none.dismiss/2
    en  ...Aye, some of it. It'd take longer than you've got to explain which parts.
    >>  ............................................
    pt  ...É, parte. Levaria mais tempo do que você tem pra explicar quais partes.
    >>  ............................................
  athletic.dialogue.conversations.village.none.dismiss/3
    en  Right. There's no hurry to settle whose fault a village is.
    >>  ............................................
    pt  Certo. Não há pressa de resolver de quem é a culpa de um vilarejo.
    >>  ............................................
  confident.dialogue.conversations.village.none.dismiss/1
    en  My fault. Right. You know nothing about it.
    >>  ............................................
    pt  Culpa minha. Certo. Você não sabe nada disso.
    >>  ............................................
  confident.dialogue.conversations.village.none.dismiss/2
    en  That's an easy thing to say from where you're standing.
    >>  ............................................
    pt  É fácil de dizer de onde você está.
    >>  ............................................
  confident.dialogue.conversations.village.none.dismiss/3
    en  ...I'll not discuss the village with you again.
    >>  ............................................
    pt  ...Não discuto o vilarejo com você de novo.
    >>  ............................................
  crabby.dialogue.conversations.village.none.dismiss/1
    en  My fault. Right. You know nothing about it.
    >>  ............................................
    pt  Culpa minha. Certo. Você não sabe nada disso.
    >>  ............................................
  crabby.dialogue.conversations.village.none.dismiss/2
    en  That's an easy thing to say from where you're standing.
    >>  ............................................
    pt  É fácil de dizer de onde você está.
    >>  ............................................
  crabby.dialogue.conversations.village.none.dismiss/3
    en  ...I'll not discuss the village with you again.
    >>  ............................................
    pt  ...Não discuto o vilarejo com você de novo.
    >>  ............................................
  extroverted.dialogue.conversations.village.none.dismiss/1
    en  ...My fault, %1$s. That's the one thing I'd hoped you wouldn't say.
    >>  ............................................
    pt  ...Culpa minha, %1$s. É a única coisa que eu esperava que você não dissesse.
    >>  ............................................
  extroverted.dialogue.conversations.village.none.dismiss/2
    en  You've been here a season. I've been here my whole life.
    >>  ............................................
    pt  Você está aqui há uma estação. Eu estou aqui a vida toda.
    >>  ............................................
  extroverted.dialogue.conversations.village.none.dismiss/3
    en  ...Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  ...Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  flirty.dialogue.conversations.village.none.dismiss/1
    en  ...My fault, %1$s. That's the one thing I'd hoped you wouldn't say.
    >>  ............................................
    pt  ...Culpa minha, %1$s. É a única coisa que eu esperava que você não dissesse.
    >>  ............................................
  flirty.dialogue.conversations.village.none.dismiss/2
    en  You've been here a season. I've been here my whole life.
    >>  ............................................
    pt  Você está aqui há uma estação. Eu estou aqui a vida toda.
    >>  ............................................
  flirty.dialogue.conversations.village.none.dismiss/3
    en  ...Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  ...Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  friendly.dialogue.conversations.village.none.dismiss/1
    en  ...My fault, %1$s. That's the one thing I'd hoped you wouldn't say.
    >>  ............................................
    pt  ...Culpa minha, %1$s. É a única coisa que eu esperava que você não dissesse.
    >>  ............................................
  friendly.dialogue.conversations.village.none.dismiss/2
    en  You've been here a season. I've been here my whole life.
    >>  ............................................
    pt  Você está aqui há uma estação. Eu estou aqui a vida toda.
    >>  ............................................
  friendly.dialogue.conversations.village.none.dismiss/3
    en  ...Right. I'll not tell you how it stands again.
    >>  ............................................
    pt  ...Certo. Não te digo mais como as coisas estão.
    >>  ............................................
  gloomy.dialogue.conversations.village.none.dismiss/1
    en  ...My fault. I've thought that at three in the morning more than you'd guess, %1$s.
    >>  ............................................
    pt  ...Culpa minha. Já pensei isso às três da manhã mais do que você imagina, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.village.none.dismiss/2
    en  Right. Yes. I know. I know better than you do, actually.
    >>  ............................................
    pt  Certo. Sim. Eu sei. Na verdade eu sei melhor que você.
    >>  ............................................
  gloomy.dialogue.conversations.village.none.dismiss/3
    en  ...I'll not talk about the village with anybody for a while.
    >>  ............................................
    pt  ...Não vou falar do vilarejo com ninguém por um tempo.
    >>  ............................................
  greedy.dialogue.conversations.village.none.dismiss/1
    en  My fault. Right. You know nothing about it.
    >>  ............................................
    pt  Culpa minha. Certo. Você não sabe nada disso.
    >>  ............................................
  greedy.dialogue.conversations.village.none.dismiss/2
    en  That's an easy thing to say from where you're standing.
    >>  ............................................
    pt  É fácil de dizer de onde você está.
    >>  ............................................
  greedy.dialogue.conversations.village.none.dismiss/3
    en  ...I'll not discuss the village with you again.
    >>  ............................................
    pt  ...Não discuto o vilarejo com você de novo.
    >>  ............................................
  grumpy.dialogue.conversations.village.none.dismiss/1
    en  My fault. Right. You know nothing about it.
    >>  ............................................
    pt  Culpa minha. Certo. Você não sabe nada disso.
    >>  ............................................
  grumpy.dialogue.conversations.village.none.dismiss/2
    en  That's an easy thing to say from where you're standing.
    >>  ............................................
    pt  É fácil de dizer de onde você está.
    >>  ............................................
  grumpy.dialogue.conversations.village.none.dismiss/3
    en  ...I'll not discuss the village with you again.
    >>  ............................................
    pt  ...Não discuto o vilarejo com você de novo.
    >>  ............................................
  introverted.dialogue.conversations.village.none.dismiss/1
    en  ...My fault. Right.
    >>  ............................................
    pt  ...Culpa minha. Certo.
    >>  ............................................
  introverted.dialogue.conversations.village.none.dismiss/2
    en  You know nothing about it.
    >>  ............................................
    pt  Você não sabe nada disso.
    >>  ............................................
  introverted.dialogue.conversations.village.none.dismiss/3
    en  ...I'll leave it there.
    >>  ............................................
    pt  ...Deixo aí.
    >>  ............................................
  lazy.dialogue.conversations.village.none.dismiss/1
    en  My fault. Well. Blame's cheap and the fence is still down.
    >>  ............................................
    pt  Culpa minha. Bom. Culpa é barata e a cerca continua caída.
    >>  ............................................
  lazy.dialogue.conversations.village.none.dismiss/2
    en  ...Aye, some of it. It'd take longer than you've got to explain which parts.
    >>  ............................................
    pt  ...É, parte. Levaria mais tempo do que você tem pra explicar quais partes.
    >>  ............................................
  lazy.dialogue.conversations.village.none.dismiss/3
    en  Right. There's no hurry to settle whose fault a village is.
    >>  ............................................
    pt  Certo. Não há pressa de resolver de quem é a culpa de um vilarejo.
    >>  ............................................
  odd.dialogue.conversations.village.none.dismiss/1
    en  ...My fault. Right.
    >>  ............................................
    pt  ...Culpa minha. Certo.
    >>  ............................................
  odd.dialogue.conversations.village.none.dismiss/2
    en  You know nothing about it.
    >>  ............................................
    pt  Você não sabe nada disso.
    >>  ............................................
  odd.dialogue.conversations.village.none.dismiss/3
    en  ...I'll leave it there.
    >>  ............................................
    pt  ...Deixo aí.
    >>  ............................................
  peaceful.dialogue.conversations.village.none.dismiss/1
    en  My fault. Well. Blame's cheap and the fence is still down.
    >>  ............................................
    pt  Culpa minha. Bom. Culpa é barata e a cerca continua caída.
    >>  ............................................
  peaceful.dialogue.conversations.village.none.dismiss/2
    en  ...Aye, some of it. It'd take longer than you've got to explain which parts.
    >>  ............................................
    pt  ...É, parte. Levaria mais tempo do que você tem pra explicar quais partes.
    >>  ............................................
  peaceful.dialogue.conversations.village.none.dismiss/3
    en  Right. There's no hurry to settle whose fault a village is.
    >>  ............................................
    pt  Certo. Não há pressa de resolver de quem é a culpa de um vilarejo.
    >>  ............................................
  peppy.dialogue.conversations.village.none.dismiss/1
    en  My fault! Marvellous. Solved in one sentence, %1$s.
    >>  ............................................
    pt  Culpa minha! Maravilhoso. Resolvido numa frase, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.village.none.dismiss/2
    en  Right, well. I'll take that up with myself at length later.
    >>  ............................................
    pt  Certo, bom. Vou tratar disso comigo mesmo longamente depois.
    >>  ............................................
  peppy.dialogue.conversations.village.none.dismiss/3
    en  ...Ha. Yes. It's all me. Every bit of it.
    >>  ............................................
    pt  ...Ha. Sim. Sou todo eu. Cada pedacinho.
    >>  ............................................
  playful.dialogue.conversations.village.none.dismiss/1
    en  My fault! Marvellous. Solved in one sentence, %1$s.
    >>  ............................................
    pt  Culpa minha! Maravilhoso. Resolvido numa frase, %1$s.
    >>  ............................................
  playful.dialogue.conversations.village.none.dismiss/2
    en  Right, well. I'll take that up with myself at length later.
    >>  ............................................
    pt  Certo, bom. Vou tratar disso comigo mesmo longamente depois.
    >>  ............................................
  playful.dialogue.conversations.village.none.dismiss/3
    en  ...Ha. Yes. It's all me. Every bit of it.
    >>  ............................................
    pt  ...Ha. Sim. Sou todo eu. Cada pedacinho.
    >>  ............................................
  relaxed.dialogue.conversations.village.none.dismiss/1
    en  My fault. Well. Blame's cheap and the fence is still down.
    >>  ............................................
    pt  Culpa minha. Bom. Culpa é barata e a cerca continua caída.
    >>  ............................................
  relaxed.dialogue.conversations.village.none.dismiss/2
    en  ...Aye, some of it. It'd take longer than you've got to explain which parts.
    >>  ............................................
    pt  ...É, parte. Levaria mais tempo do que você tem pra explicar quais partes.
    >>  ............................................
  relaxed.dialogue.conversations.village.none.dismiss/3
    en  Right. There's no hurry to settle whose fault a village is.
    >>  ............................................
    pt  Certo. Não há pressa de resolver de quem é a culpa de um vilarejo.
    >>  ............................................
  sensitive.dialogue.conversations.village.none.dismiss/1
    en  ...My fault. I've thought that at three in the morning more than you'd guess, %1$s.
    >>  ............................................
    pt  ...Culpa minha. Já pensei isso às três da manhã mais do que você imagina, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.village.none.dismiss/2
    en  Right. Yes. I know. I know better than you do, actually.
    >>  ............................................
    pt  Certo. Sim. Eu sei. Na verdade eu sei melhor que você.
    >>  ............................................
  sensitive.dialogue.conversations.village.none.dismiss/3
    en  ...I'll not talk about the village with anybody for a while.
    >>  ............................................
    pt  ...Não vou falar do vilarejo com ninguém por um tempo.
    >>  ............................................
  shy.dialogue.conversations.village.none.dismiss/1
    en  ...My fault. Right.
    >>  ............................................
    pt  ...Culpa minha. Certo.
    >>  ............................................
  shy.dialogue.conversations.village.none.dismiss/2
    en  You know nothing about it.
    >>  ............................................
    pt  Você não sabe nada disso.
    >>  ............................................
  shy.dialogue.conversations.village.none.dismiss/3
    en  ...I'll leave it there.
    >>  ............................................
    pt  ...Deixo aí.
    >>  ............................................
  upbeat.dialogue.conversations.village.none.dismiss/1
    en  My fault! Marvellous. Solved in one sentence, %1$s.
    >>  ............................................
    pt  Culpa minha! Maravilhoso. Resolvido numa frase, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.village.none.dismiss/2
    en  Right, well. I'll take that up with myself at length later.
    >>  ............................................
    pt  Certo, bom. Vou tratar disso comigo mesmo longamente depois.
    >>  ............................................
  upbeat.dialogue.conversations.village.none.dismiss/3
    en  ...Ha. Yes. It's all me. Every bit of it.
    >>  ............................................
    pt  ...Ha. Sim. Sou todo eu. Cada pedacinho.
    >>  ............................................
  witty.dialogue.conversations.village.none.dismiss/1
    en  My fault! Marvellous. Solved in one sentence, %1$s.
    >>  ............................................
    pt  Culpa minha! Maravilhoso. Resolvido numa frase, %1$s.
    >>  ............................................
  witty.dialogue.conversations.village.none.dismiss/2
    en  Right, well. I'll take that up with myself at length later.
    >>  ............................................
    pt  Certo, bom. Vou tratar disso comigo mesmo longamente depois.
    >>  ............................................
  witty.dialogue.conversations.village.none.dismiss/3
    en  ...Ha. Yes. It's all me. Every bit of it.
    >>  ............................................
    pt  ...Ha. Sim. Sou todo eu. Cada pedacinho.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave you be."

*stance family `exit` · tone `plain` · answers the beat(s) `village.none.to.village.none` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.none.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.none.respond.leave   [18 chars]
    en  I'll leave you be.
    >>  ............................................
    pt  Deixo você quieto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.none.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you be."
       spoken on: conversations.topic.village.none.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.none.leave.terminal`: the villager accepts. Subject `village.no_home`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.none.leave/1   [11 chars]
    en  Aye. Go on.
    >>  ............................................
    pt  É. Pode ir.
    >>  ............................................
  dialogue.conversations.village.none.leave/2   [31 chars]
    en  Right. Mind yourself out there.
    >>  ............................................
    pt  Certo. Se cuide por aí.
    >>  ............................................
  dialogue.conversations.village.none.leave/3   [24 chars]
    en  We'll speak again, %1$s.
    >>  ............................................
    pt  A gente se fala, %1$s.
    >>  ............................................
```

---


## `conversations.topic.village.praised.followup`

**Reached from 2 route(s):** `conversations.topic.village.respond` / `affirm`; `conversations.topic.village.respond` / `affirm`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.respond.affirm` — e.g. "It is. Small, loud, and mine. I'd not swap it."
- `conversations.village.respond.agree.longknown` — e.g. "You've been here long enough to say that and mean it. Most agree out of politeness."


```text
POOL   dialogue key: dialogue.conversations.topic.village.praised.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.praised.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.praised.followup   [18 chars]
    en  It's home, anyway.
    >>  ............................................
    pt  É a nossa casa, enfim.
    >>  ............................................
```


### Button `ask_best` — "What's the best of it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `village.praise_taken`, `village.praise_earned` · offered only once the villager has actually said `village:praised`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.praised.ask_best` — accepted phrasings: "what is the best of it"; "what do you love most about it"; "what is the best part of living here"
  - the message must contain one of: `best`, `part`, `love`
  - scored words: `best`(1.5), `part`(1.0), `love`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.village.praised.followup.ask_best
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.praised.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.praised.followup.ask_best   [22 chars]
    en  What's the best of it?
    >>  ............................................
    pt  Qual é a melhor parte?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `village.praised.ask_best`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.praised.ask_best
WHO    VILLAGER — what the player reads after pressing "What's the best of it?"
       spoken on: conversations.topic.village.praised.followup, button `ask_best`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.praised.ask_best`: the villager discloses. Subject `village.praise`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.praised.ask_best/1   [67 chars]
    en  The square at dusk, when the work's done and nobody's shouting yet.
    >>  ............................................
    pt  A praça ao entardecer, quando o trabalho acabou e ninguém está gritando ainda.
    >>  ............................................
  dialogue.conversations.village.praised.ask_best/2   [88 chars]
    en  That everyone knows whose child is whose. It sounds small until you've lived without it.
    >>  ............................................
    pt  Que todo mundo sabe de quem é cada criança. Parece pouco até você viver sem isso.
    >>  ............................................
  dialogue.conversations.village.praised.ask_best/3   [54 chars]
    en  The well, %1$s. Not the water — the standing about it.
    >>  ............................................
    pt  O poço, %1$s. Não a água — o ficar em volta dele.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. It's about nine minutes and it's mine.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. São uns nove minutos e são meus.
    >>  ............................................
  anxious.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. It's the only hour where I can look at this place without counting what's wrong.
    >>  ............................................
    pt  Anoitecer na praça. É a única hora em que eu olho este lugar sem contar o que está errado.
    >>  ............................................
  anxious.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day, in the square. I go there most evenings and I've told nobody why.
    >>  ............................................
    pt  O fim do dia, na praça. Eu vou lá quase toda noite e não contei a ninguém por quê.
    >>  ............................................
  athletic.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. Same hour, every year.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. Mesma hora, todo ano.
    >>  ............................................
  athletic.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. It's been the best part of this place for as long as I've been in it.
    >>  ............................................
    pt  Anoitecer na praça. É a melhor parte deste lugar desde que eu estou nele.
    >>  ............................................
  athletic.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day. Nothing about it has changed and I'd not want it to.
    >>  ............................................
    pt  O fim do dia. Nada nisso mudou e eu não gostaria que mudasse.
    >>  ............................................
  confident.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda.
    >>  ............................................
  confident.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. That's the hour this place earns.
    >>  ............................................
    pt  O anoitecer na praça. É a hora que este lugar merece.
    >>  ............................................
  confident.dialogue.conversations.village.praised.ask_best/3
    en  The square, at the end of the day. It's the only time it looks like what it is.
    >>  ............................................
    pt  A praça, no fim do dia. É a única hora em que parece o que é.
    >>  ............................................
  crabby.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda.
    >>  ............................................
  crabby.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. That's the hour this place earns.
    >>  ............................................
    pt  O anoitecer na praça. É a hora que este lugar merece.
    >>  ............................................
  crabby.dialogue.conversations.village.praised.ask_best/3
    en  The square, at the end of the day. It's the only time it looks like what it is.
    >>  ............................................
    pt  A praça, no fim do dia. É a única hora em que parece o que é.
    >>  ............................................
  extroverted.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. Stand there with me one evening.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. Fique lá comigo uma noite.
    >>  ............................................
  extroverted.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. Everybody's tired and pleased at once, and it's the best of us.
    >>  ............................................
    pt  Anoitecer na praça. Todos cansados e contentes ao mesmo tempo, e é o melhor de nós.
    >>  ............................................
  extroverted.dialogue.conversations.village.praised.ask_best/3
    en  The square at the end of the day, %1$s. Come and see it before you decide about this place.
    >>  ............................................
    pt  A praça no fim do dia, %1$s. Venha ver antes de decidir sobre este lugar.
    >>  ............................................
  flirty.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. Stand there with me one evening.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. Fique lá comigo uma noite.
    >>  ............................................
  flirty.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. Everybody's tired and pleased at once, and it's the best of us.
    >>  ............................................
    pt  Anoitecer na praça. Todos cansados e contentes ao mesmo tempo, e é o melhor de nós.
    >>  ............................................
  flirty.dialogue.conversations.village.praised.ask_best/3
    en  The square at the end of the day, %1$s. Come and see it before you decide about this place.
    >>  ............................................
    pt  A praça no fim do dia, %1$s. Venha ver antes de decidir sobre este lugar.
    >>  ............................................
  friendly.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. Stand there with me one evening.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. Fique lá comigo uma noite.
    >>  ............................................
  friendly.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. Everybody's tired and pleased at once, and it's the best of us.
    >>  ............................................
    pt  Anoitecer na praça. Todos cansados e contentes ao mesmo tempo, e é o melhor de nós.
    >>  ............................................
  friendly.dialogue.conversations.village.praised.ask_best/3
    en  The square at the end of the day, %1$s. Come and see it before you decide about this place.
    >>  ............................................
    pt  A praça no fim do dia, %1$s. Venha ver antes de decidir sobre este lugar.
    >>  ............................................
  gloomy.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. It's about nine minutes and it's mine.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. São uns nove minutos e são meus.
    >>  ............................................
  gloomy.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. It's the only hour where I can look at this place without counting what's wrong.
    >>  ............................................
    pt  Anoitecer na praça. É a única hora em que eu olho este lugar sem contar o que está errado.
    >>  ............................................
  gloomy.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day, in the square. I go there most evenings and I've told nobody why.
    >>  ............................................
    pt  O fim do dia, na praça. Eu vou lá quase toda noite e não contei a ninguém por quê.
    >>  ............................................
  greedy.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda.
    >>  ............................................
  greedy.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. That's the hour this place earns.
    >>  ............................................
    pt  O anoitecer na praça. É a hora que este lugar merece.
    >>  ............................................
  greedy.dialogue.conversations.village.praised.ask_best/3
    en  The square, at the end of the day. It's the only time it looks like what it is.
    >>  ............................................
    pt  A praça, no fim do dia. É a única hora em que parece o que é.
    >>  ............................................
  grumpy.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda.
    >>  ............................................
  grumpy.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. That's the hour this place earns.
    >>  ............................................
    pt  O anoitecer na praça. É a hora que este lugar merece.
    >>  ............................................
  grumpy.dialogue.conversations.village.praised.ask_best/3
    en  The square, at the end of the day. It's the only time it looks like what it is.
    >>  ............................................
    pt  A praça, no fim do dia. É a única hora em que parece o que é.
    >>  ............................................
  introverted.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda.
    >>  ............................................
  introverted.dialogue.conversations.village.praised.ask_best/2
    en  Dusk. In the square. Before the noise starts again.
    >>  ............................................
    pt  Anoitecer. Na praça. Antes do barulho começar de novo.
    >>  ............................................
  introverted.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day, in the square. That's it.
    >>  ............................................
    pt  O fim do dia, na praça. É isso.
    >>  ............................................
  lazy.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. Same hour, every year.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. Mesma hora, todo ano.
    >>  ............................................
  lazy.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. It's been the best part of this place for as long as I've been in it.
    >>  ............................................
    pt  Anoitecer na praça. É a melhor parte deste lugar desde que eu estou nele.
    >>  ............................................
  lazy.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day. Nothing about it has changed and I'd not want it to.
    >>  ............................................
    pt  O fim do dia. Nada nisso mudou e eu não gostaria que mudasse.
    >>  ............................................
  odd.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda.
    >>  ............................................
  odd.dialogue.conversations.village.praised.ask_best/2
    en  Dusk. In the square. Before the noise starts again.
    >>  ............................................
    pt  Anoitecer. Na praça. Antes do barulho começar de novo.
    >>  ............................................
  odd.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day, in the square. That's it.
    >>  ............................................
    pt  O fim do dia, na praça. É isso.
    >>  ............................................
  peaceful.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. Same hour, every year.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. Mesma hora, todo ano.
    >>  ............................................
  peaceful.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. It's been the best part of this place for as long as I've been in it.
    >>  ............................................
    pt  Anoitecer na praça. É a melhor parte deste lugar desde que eu estou nele.
    >>  ............................................
  peaceful.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day. Nothing about it has changed and I'd not want it to.
    >>  ............................................
    pt  O fim do dia. Nada nisso mudou e eu não gostaria que mudasse.
    >>  ............................................
  peppy.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk! When the work's done and nobody's shouting yet. It's about nine minutes long.
    >>  ............................................
    pt  A praça ao anoitecer! Quando o trabalho acabou e ninguém está gritando ainda. Dura uns nove minutos.
    >>  ............................................
  peppy.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. Best nine minutes of any day and nobody has ever noticed but me.
    >>  ............................................
    pt  Anoitecer na praça. Os melhores nove minutos de qualquer dia e ninguém reparou além de mim.
    >>  ............................................
  peppy.dialogue.conversations.village.praised.ask_best/3
    en  The square at the end of the day. Come and stand in it once and you'll see.
    >>  ............................................
    pt  A praça no fim do dia. Fique nela uma vez e você vai ver.
    >>  ............................................
  playful.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk! When the work's done and nobody's shouting yet. It's about nine minutes long.
    >>  ............................................
    pt  A praça ao anoitecer! Quando o trabalho acabou e ninguém está gritando ainda. Dura uns nove minutos.
    >>  ............................................
  playful.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. Best nine minutes of any day and nobody has ever noticed but me.
    >>  ............................................
    pt  Anoitecer na praça. Os melhores nove minutos de qualquer dia e ninguém reparou além de mim.
    >>  ............................................
  playful.dialogue.conversations.village.praised.ask_best/3
    en  The square at the end of the day. Come and stand in it once and you'll see.
    >>  ............................................
    pt  A praça no fim do dia. Fique nela uma vez e você vai ver.
    >>  ............................................
  relaxed.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. Same hour, every year.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. Mesma hora, todo ano.
    >>  ............................................
  relaxed.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. It's been the best part of this place for as long as I've been in it.
    >>  ............................................
    pt  Anoitecer na praça. É a melhor parte deste lugar desde que eu estou nele.
    >>  ............................................
  relaxed.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day. Nothing about it has changed and I'd not want it to.
    >>  ............................................
    pt  O fim do dia. Nada nisso mudou e eu não gostaria que mudasse.
    >>  ............................................
  sensitive.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet. It's about nine minutes and it's mine.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda. São uns nove minutos e são meus.
    >>  ............................................
  sensitive.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. It's the only hour where I can look at this place without counting what's wrong.
    >>  ............................................
    pt  Anoitecer na praça. É a única hora em que eu olho este lugar sem contar o que está errado.
    >>  ............................................
  sensitive.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day, in the square. I go there most evenings and I've told nobody why.
    >>  ............................................
    pt  O fim do dia, na praça. Eu vou lá quase toda noite e não contei a ninguém por quê.
    >>  ............................................
  shy.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk, when the work's done and nobody's shouting yet.
    >>  ............................................
    pt  A praça ao anoitecer, quando o trabalho acabou e ninguém está gritando ainda.
    >>  ............................................
  shy.dialogue.conversations.village.praised.ask_best/2
    en  Dusk. In the square. Before the noise starts again.
    >>  ............................................
    pt  Anoitecer. Na praça. Antes do barulho começar de novo.
    >>  ............................................
  shy.dialogue.conversations.village.praised.ask_best/3
    en  The end of the day, in the square. That's it.
    >>  ............................................
    pt  O fim do dia, na praça. É isso.
    >>  ............................................
  upbeat.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk! When the work's done and nobody's shouting yet. It's about nine minutes long.
    >>  ............................................
    pt  A praça ao anoitecer! Quando o trabalho acabou e ninguém está gritando ainda. Dura uns nove minutos.
    >>  ............................................
  upbeat.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. Best nine minutes of any day and nobody has ever noticed but me.
    >>  ............................................
    pt  Anoitecer na praça. Os melhores nove minutos de qualquer dia e ninguém reparou além de mim.
    >>  ............................................
  upbeat.dialogue.conversations.village.praised.ask_best/3
    en  The square at the end of the day. Come and stand in it once and you'll see.
    >>  ............................................
    pt  A praça no fim do dia. Fique nela uma vez e você vai ver.
    >>  ............................................
  witty.dialogue.conversations.village.praised.ask_best/1
    en  The square at dusk! When the work's done and nobody's shouting yet. It's about nine minutes long.
    >>  ............................................
    pt  A praça ao anoitecer! Quando o trabalho acabou e ninguém está gritando ainda. Dura uns nove minutos.
    >>  ............................................
  witty.dialogue.conversations.village.praised.ask_best/2
    en  Dusk in the square. Best nine minutes of any day and nobody has ever noticed but me.
    >>  ............................................
    pt  Anoitecer na praça. Os melhores nove minutos de qualquer dia e ninguém reparou além de mim.
    >>  ............................................
  witty.dialogue.conversations.village.praised.ask_best/3
    en  The square at the end of the day. Come and stand in it once and you'll see.
    >>  ............................................
    pt  A praça no fim do dia. Fique nela uma vez e você vai ver.
    >>  ............................................
```

</details>


### Button `agree` — "I've come to think so too."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `village.praise_taken`, `village.praise_earned` · offered only once the villager has actually said `village:praised`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.praised.agree` — accepted phrasings: "i have come to think so too"; "it has grown on me"; "i have come round to it"
  - the message must contain one of: `come`, `think`, `grown`
  - scored words: `come`(1.2), `think`(1.0), `grown`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.village.praised.followup.agree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.praised.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.praised.followup.agree   [26 chars]
    en  I've come to think so too.
    >>  ............................................
    pt  Eu também passei a achar isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.praised.agree`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +2  _(recorded under topic `village.praised.agree`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.praised.agree
WHO    VILLAGER — what the player reads after pressing "I've come to think so too."
       spoken on: conversations.topic.village.praised.followup, button `agree`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.praised.agree`: the villager accepts. Subject `village.praise`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.praised.agree/1   [65 chars]
    en  Come to. That's the right way round — nobody arrives thinking it.
    >>  ............................................
    pt  Passou a achar. É a ordem certa — ninguém chega achando isso.
    >>  ............................................
  dialogue.conversations.village.praised.agree/2   [59 chars]
    en  Then you've stayed long enough for it to work on you, %1$s.
    >>  ............................................
    pt  Então você ficou tempo bastante pra isso fazer efeito, %1$s.
    >>  ............................................
  dialogue.conversations.village.praised.agree/3   [62 chars]
    en  Good. It takes about a winter, that. You're ahead of schedule.
    >>  ............................................
    pt  Bom. Leva um inverno, mais ou menos. Você está adiantado.
    >>  ............................................
```


### Button `joke` — "Careful — you'll have me moving here."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `village.praise_taken`, `village.praise_earned`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.praised.joke` — accepted phrasings: "careful, you will have me moving here"; "you have almost convinced me"; "i might move here"
  - the message must contain one of: `moving`, `convinced`
  - scored words: `moving`(1.5), `convinced`(1.5), `careful`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.village.praised.followup.joke
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.praised.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.praised.followup.joke   [37 chars]
    en  Careful — you'll have me moving here.
    >>  ............................................
    pt  Cuidado — você vai me fazer mudar pra cá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.praised.joke`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -1, warmth +3  _(recorded under topic `village.praised.joke`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.praised.joke
WHO    VILLAGER — what the player reads after pressing "Careful — you'll have me moving here."
       spoken on: conversations.topic.village.praised.followup, button `joke`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.praised.joke`: the villager accepts. Subject `village.praise`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.praised.joke/1   [70 chars]
    en  Then I've done my work. The mayor pays me in bread for this, you know.
    >>  ............................................
    pt  Então cumpri meu trabalho. O prefeito me paga em pão por isso, sabia.
    >>  ............................................
  dialogue.conversations.village.praised.joke/2   [74 chars]
    en  We've a cottage going and a fence that wants a second pair of hands, %1$s.
    >>  ............................................
    pt  Tem uma casinha vaga e uma cerca querendo um segundo par de mãos, %1$s.
    >>  ............................................
  dialogue.conversations.village.praised.joke/3   [68 chars]
    en  Ha! Say that near the mayor and you'll be on the register by supper.
    >>  ............................................
    pt  Ha! Diga isso perto do prefeito e você está no registro antes do jantar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `village.praise_taken`, `village.praise_earned` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.praised.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.praised.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.praised.followup.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.village.praised.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.left`: the villager accepts. Subject `village.belonging`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.village.fault.followup / leave; conversations.topic.village.insulted.followup / leave; conversations.topic.village.respond / leave; conversations.topic.village.settled.followup / leave
```

> Written out in full under **`conversations.topic.village.fault.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.village.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.home` — e.g. "%2$s? It's home. The bell's cracked and the gossip's fast, but it's home."


```text
POOL   dialogue key: dialogue.conversations.topic.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.respond   [38 chars]
    en  That's the place, for better or worse.
    >>  ............................................
    pt  É esse o lugar, para o bem ou para o mal.
    >>  ............................................
```


### Button `affirm` — "It's a good place to live."

*stance family `encouragement` · tone `plain` · answers the beat(s) `village.home.to.village`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.respond.affirm` — accepted phrasings: "it is a good place to live"; "lovely place"; "good place this"
  - the message must contain one of: `good`, `place`, `lovely`, `live`
  - scored words: `good`(1.0), `place`(1.2), `live`(1.0), `lovely`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.village.respond.affirm
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.respond.affirm   [26 chars]
    en  It's a good place to live.
    >>  ............................................
    pt  É um bom lugar para viver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when disposition familiarity >= 45
- Fires when: RULED OUT when the `dispositions` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `village.respond.affirm`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, respect +1  _(recorded under topic `village.respond.affirm`)_
- Does: session `turn`
- Then opens: `conversations.topic.village.praised.followup`
- …where the player's next choices will be: "What's the best of it?" | "I've come to think so too." | "Careful — you'll have me moving here." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.village.respond.agree.longknown
WHO    VILLAGER — what the player reads after pressing "It's a good place to live."
       spoken on: conversations.topic.village.respond, button `affirm`
       leaves the player on: conversations.topic.village.praised.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.praise_earned`: the villager accepts. Subject `village.home`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `village:home`, `village:praised` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.respond.agree.longknown/1   [83 chars]
    en  You've been here long enough to say that and mean it. Most agree out of politeness.
    >>  ............................................
    pt  Você está aqui há tempo bastante para dizer isso e falar sério. A maioria concorda por educação.
    >>  ............................................
  dialogue.conversations.village.respond.agree.longknown/2   [80 chars]
    en  Aye — and you've seen it through a winter or two now, %1$s. Your opinion counts.
    >>  ............................................
    pt  É — e você já viu isso atravessar um inverno ou dois, %1$s. Sua opinião conta.
    >>  ............................................
  dialogue.conversations.village.respond.agree.longknown/3   [73 chars]
    en  You'd know. You're not a visitor any more, whatever the rest of them say.
    >>  ............................................
    pt  Você saberia. Já não é visitante, digam o que disserem os outros.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when disposition familiarity >= 45  _(chance -2000)_
- Does: **hearts +2** — decision id `village.respond.affirm`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, respect +1  _(recorded under topic `village.respond.affirm`)_
- Does: session `turn`
- Then opens: `conversations.topic.village.praised.followup`
- …where the player's next choices will be: "What's the best of it?" | "I've come to think so too." | "Careful — you'll have me moving here." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.village.respond.affirm
WHO    VILLAGER — what the player reads after pressing "It's a good place to live."
       spoken on: conversations.topic.village.respond, button `affirm`
       leaves the player on: conversations.topic.village.praised.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.praise_taken`: the villager accepts. Subject `village.home`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `village:home`, `village:praised` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.respond.affirm/1   [46 chars]
    en  It is. Small, loud, and mine. I'd not swap it.
    >>  ............................................
    pt  É. Pequena, barulhenta, e minha. Não trocaria.
    >>  ............................................
  dialogue.conversations.village.respond.affirm/2   [46 chars]
    en  Aye. You'll not hear that said often, but aye.
    >>  ............................................
    pt  É. Não se ouve isso com frequência, mas é.
    >>  ............................................
  dialogue.conversations.village.respond.affirm/3   [58 chars]
    en  Good of you to say. Most visitors count the mud and leave.
    >>  ............................................
    pt  Gentil da sua parte dizer. A maioria dos visitantes conta a lama e vai embora.
    >>  ............................................
```


### Button `ask_improve` — "What would you change about it?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `village.home.to.village`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.respond.ask_improve` — accepted phrasings: "what would you change about it"; "how could it be better"; "what would you improve"
  - the message must contain one of: `change`, `improve`, `better`
  - scored words: `change`(1.5), `improve`(1.5), `better`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.village.respond.ask_improve
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.respond.ask_improve   [31 chars]
    en  What would you change about it?
    >>  ............................................
    pt  O que você mudaria nela?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when `village_has_building` = "blacksmith"
- Does: disposition — respect +3, familiarity +2  _(recorded under topic `village.respond.ask_improve`)_
- Does: session `turn`
- Then opens: `conversations.topic.village.fault.followup`
- …where the player's next choices will be: "Tell me and I'll help fix it." | "It could be run better, though." | "You're right about that." | "It's held together with string and stubbornness." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.village.ask_improve.smith
WHO    VILLAGER — what the player reads after pressing "What would you change about it?"
       spoken on: conversations.topic.village.respond, button `ask_improve`
       leaves the player on: conversations.topic.village.fault.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.fault_smith`: the villager disclose_problems. Subject `village.home`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `village:home`, `village:fault_named` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, respectful_disagreement, self_disclosure, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.ask_improve.smith/1   [95 chars]
    en  We've a smith, which puts us ahead of most. What we've not got is a decent road to reach it by.
    >>  ............................................
    pt  Temos uma ferraria, o que já nos põe à frente da maioria. O que não temos é uma estrada decente até ela.
    >>  ............................................
  dialogue.conversations.village.ask_improve.smith/2   [76 chars]
    en  The forge is the best thing here. Everything else could learn from it, %1$s.
    >>  ............................................
    pt  A forja é a melhor coisa daqui. O resto podia aprender com ela, %1$s.
    >>  ............................................
  dialogue.conversations.village.ask_improve.smith/3   [89 chars]
    en  With a smith we can mend what breaks. It's the things nobody can mend that want the work.
    >>  ............................................
    pt  Com uma ferreira a gente conserta o que quebra. São as coisas que ninguém conserta que dão trabalho.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. It's the road that keeps people from coming.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. É a estrada que impede as pessoas de virem.
    >>  ............................................
  anxious.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good and hardly anybody reaches him, and I think that wears on him.
    >>  ............................................
    pt  O ferreiro é bom e quase ninguém chega até ele, e eu acho que isso o desgasta.
    >>  ............................................
  anxious.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's a small complaint and it's cost this place more than people know.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É uma queixa pequena e custou mais a este lugar do que se sabe.
    >>  ............................................
  athletic.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. The road will come. Roads usually do, eventually.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. A estrada vem. Estradas costumam vir.
    >>  ............................................
  athletic.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good. The road has been bad for thirty years and it's in no hurry to improve.
    >>  ............................................
    pt  O ferreiro é bom. A estrada está ruim há trinta anos e não tem pressa de melhorar.
    >>  ............................................
  athletic.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's an old arrangement and we've all got used to walking.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É um arranjo antigo e todos nos acostumamos a caminhar.
    >>  ............................................
  confident.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. What we've not got is a decent road to reach it by.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. O que não temos é uma estrada decente pra chegar.
    >>  ............................................
  confident.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good. The road to the smith is the problem and it always has been.
    >>  ............................................
    pt  O ferreiro é bom. A estrada até o ferreiro é o problema e sempre foi.
    >>  ............................................
  confident.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. That's the whole of what's wrong here.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É tudo que está errado aqui.
    >>  ............................................
  crabby.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. What we've not got is a decent road to reach it by.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. O que não temos é uma estrada decente pra chegar.
    >>  ............................................
  crabby.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good. The road to the smith is the problem and it always has been.
    >>  ............................................
    pt  O ferreiro é bom. A estrada até o ferreiro é o problema e sempre foi.
    >>  ............................................
  crabby.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. That's the whole of what's wrong here.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É tudo que está errado aqui.
    >>  ............................................
  extroverted.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. What we lack is a road, and he'd tell you the same.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. O que falta é estrada, e ele diria o mesmo.
    >>  ............................................
  extroverted.dialogue.conversations.village.ask_improve.smith/2
    en  The smith's good — go and meet him. Just don't go in the wet; the road will take your boots.
    >>  ............................................
    pt  O ferreiro é bom — vá conhecê-lo. Só não vá no molhado; a estrada leva suas botas.
    >>  ............................................
  extroverted.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. Ask him about it and you'll have made a friend for the afternoon.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. Pergunte a ele e você terá feito um amigo pela tarde.
    >>  ............................................
  flirty.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. What we lack is a road, and he'd tell you the same.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. O que falta é estrada, e ele diria o mesmo.
    >>  ............................................
  flirty.dialogue.conversations.village.ask_improve.smith/2
    en  The smith's good — go and meet him. Just don't go in the wet; the road will take your boots.
    >>  ............................................
    pt  O ferreiro é bom — vá conhecê-lo. Só não vá no molhado; a estrada leva suas botas.
    >>  ............................................
  flirty.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. Ask him about it and you'll have made a friend for the afternoon.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. Pergunte a ele e você terá feito um amigo pela tarde.
    >>  ............................................
  friendly.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. What we lack is a road, and he'd tell you the same.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. O que falta é estrada, e ele diria o mesmo.
    >>  ............................................
  friendly.dialogue.conversations.village.ask_improve.smith/2
    en  The smith's good — go and meet him. Just don't go in the wet; the road will take your boots.
    >>  ............................................
    pt  O ferreiro é bom — vá conhecê-lo. Só não vá no molhado; a estrada leva suas botas.
    >>  ............................................
  friendly.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. Ask him about it and you'll have made a friend for the afternoon.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. Pergunte a ele e você terá feito um amigo pela tarde.
    >>  ............................................
  gloomy.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. It's the road that keeps people from coming.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. É a estrada que impede as pessoas de virem.
    >>  ............................................
  gloomy.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good and hardly anybody reaches him, and I think that wears on him.
    >>  ............................................
    pt  O ferreiro é bom e quase ninguém chega até ele, e eu acho que isso o desgasta.
    >>  ............................................
  gloomy.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's a small complaint and it's cost this place more than people know.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É uma queixa pequena e custou mais a este lugar do que se sabe.
    >>  ............................................
  greedy.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. What we've not got is a decent road to reach it by.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. O que não temos é uma estrada decente pra chegar.
    >>  ............................................
  greedy.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good. The road to the smith is the problem and it always has been.
    >>  ............................................
    pt  O ferreiro é bom. A estrada até o ferreiro é o problema e sempre foi.
    >>  ............................................
  greedy.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. That's the whole of what's wrong here.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É tudo que está errado aqui.
    >>  ............................................
  grumpy.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. What we've not got is a decent road to reach it by.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. O que não temos é uma estrada decente pra chegar.
    >>  ............................................
  grumpy.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good. The road to the smith is the problem and it always has been.
    >>  ............................................
    pt  O ferreiro é bom. A estrada até o ferreiro é o problema e sempre foi.
    >>  ............................................
  grumpy.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. That's the whole of what's wrong here.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É tudo que está errado aqui.
    >>  ............................................
  introverted.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith. We've not got a road to reach him by.
    >>  ............................................
    pt  Temos um ferreiro. Não temos estrada pra chegar até ele.
    >>  ............................................
  introverted.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is fine. The road isn't.
    >>  ............................................
    pt  O ferreiro está bem. A estrada não.
    >>  ............................................
  introverted.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. That's the shape of it.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É esse o formato.
    >>  ............................................
  lazy.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. The road will come. Roads usually do, eventually.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. A estrada vem. Estradas costumam vir.
    >>  ............................................
  lazy.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good. The road has been bad for thirty years and it's in no hurry to improve.
    >>  ............................................
    pt  O ferreiro é bom. A estrada está ruim há trinta anos e não tem pressa de melhorar.
    >>  ............................................
  lazy.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's an old arrangement and we've all got used to walking.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É um arranjo antigo e todos nos acostumamos a caminhar.
    >>  ............................................
  odd.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith. We've not got a road to reach him by.
    >>  ............................................
    pt  Temos um ferreiro. Não temos estrada pra chegar até ele.
    >>  ............................................
  odd.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is fine. The road isn't.
    >>  ............................................
    pt  O ferreiro está bem. A estrada não.
    >>  ............................................
  odd.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. That's the shape of it.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É esse o formato.
    >>  ............................................
  peaceful.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. The road will come. Roads usually do, eventually.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. A estrada vem. Estradas costumam vir.
    >>  ............................................
  peaceful.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good. The road has been bad for thirty years and it's in no hurry to improve.
    >>  ............................................
    pt  O ferreiro é bom. A estrada está ruim há trinta anos e não tem pressa de melhorar.
    >>  ............................................
  peaceful.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's an old arrangement and we've all got used to walking.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É um arranjo antigo e todos nos acostumamos a caminhar.
    >>  ............................................
  peppy.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most! What we've not got is a road worth walking to reach him.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria! O que não temos é uma estrada que preste pra chegar.
    >>  ............................................
  peppy.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is excellent. The road is a rumour. That's the situation in two sentences.
    >>  ............................................
    pt  O ferreiro é excelente. A estrada é um boato. É a situação em duas frases.
    >>  ............................................
  peppy.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's like owning a boat and no water.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É como ter um barco e nenhuma água.
    >>  ............................................
  playful.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most! What we've not got is a road worth walking to reach him.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria! O que não temos é uma estrada que preste pra chegar.
    >>  ............................................
  playful.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is excellent. The road is a rumour. That's the situation in two sentences.
    >>  ............................................
    pt  O ferreiro é excelente. A estrada é um boato. É a situação em duas frases.
    >>  ............................................
  playful.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's like owning a boat and no water.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É como ter um barco e nenhuma água.
    >>  ............................................
  relaxed.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. The road will come. Roads usually do, eventually.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. A estrada vem. Estradas costumam vir.
    >>  ............................................
  relaxed.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good. The road has been bad for thirty years and it's in no hurry to improve.
    >>  ............................................
    pt  O ferreiro é bom. A estrada está ruim há trinta anos e não tem pressa de melhorar.
    >>  ............................................
  relaxed.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's an old arrangement and we've all got used to walking.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É um arranjo antigo e todos nos acostumamos a caminhar.
    >>  ............................................
  sensitive.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most. It's the road that keeps people from coming.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria. É a estrada que impede as pessoas de virem.
    >>  ............................................
  sensitive.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is good and hardly anybody reaches him, and I think that wears on him.
    >>  ............................................
    pt  O ferreiro é bom e quase ninguém chega até ele, e eu acho que isso o desgasta.
    >>  ............................................
  sensitive.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's a small complaint and it's cost this place more than people know.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É uma queixa pequena e custou mais a este lugar do que se sabe.
    >>  ............................................
  shy.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith. We've not got a road to reach him by.
    >>  ............................................
    pt  Temos um ferreiro. Não temos estrada pra chegar até ele.
    >>  ............................................
  shy.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is fine. The road isn't.
    >>  ............................................
    pt  O ferreiro está bem. A estrada não.
    >>  ............................................
  shy.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. That's the shape of it.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É esse o formato.
    >>  ............................................
  upbeat.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most! What we've not got is a road worth walking to reach him.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria! O que não temos é uma estrada que preste pra chegar.
    >>  ............................................
  upbeat.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is excellent. The road is a rumour. That's the situation in two sentences.
    >>  ............................................
    pt  O ferreiro é excelente. A estrada é um boato. É a situação em duas frases.
    >>  ............................................
  upbeat.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's like owning a boat and no water.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É como ter um barco e nenhuma água.
    >>  ............................................
  witty.dialogue.conversations.village.ask_improve.smith/1
    en  We've a smith, which puts us ahead of most! What we've not got is a road worth walking to reach him.
    >>  ............................................
    pt  Temos um ferreiro, o que nos põe à frente da maioria! O que não temos é uma estrada que preste pra chegar.
    >>  ............................................
  witty.dialogue.conversations.village.ask_improve.smith/2
    en  The smith is excellent. The road is a rumour. That's the situation in two sentences.
    >>  ............................................
    pt  O ferreiro é excelente. A estrada é um boato. É a situação em duas frases.
    >>  ............................................
  witty.dialogue.conversations.village.ask_improve.smith/3
    en  A smith and no road. It's like owning a boat and no water.
    >>  ............................................
    pt  Um ferreiro e nenhuma estrada. É como ter um barco e nenhuma água.
    >>  ............................................
```

</details>


**Outcome 2 of 3** — base weight `0`

- Fires when: RULED OUT when `village_has_building` = "blacksmith"  _(chance -2000)_
- Fires when: weighted +100 when the mood is `passive`
- Does: disposition — respect +3, familiarity +2  _(recorded under topic `village.respond.ask_improve`)_
- Does: session `turn`
- Then opens: `conversations.topic.village.settled.followup`
- …where the player's next choices will be: "That's not nothing." | "Nothing at all? Truly?" | "Dull is a kind of luxury." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.village.ask_improve.passive
WHO    VILLAGER — what the player reads after pressing "What would you change about it?"
       spoken on: conversations.topic.village.respond, button `ask_improve`
       leaves the player on: conversations.topic.village.settled.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.no_complaint`: the villager reports. Subject `village.criticism`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `village:home`, `village:no_complaint` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.ask_improve.passive/1   [87 chars]
    en  Improve it. Hm. Nothing springs to mind, which is either a good sign or a very bad one.
    >>  ............................................
    pt  Melhorar. Hm. Não me vem nada, o que é um bom sinal ou um péssimo.
    >>  ............................................
  dialogue.conversations.village.ask_improve.passive/2   [81 chars]
    en  It's fine. Genuinely fine. Nobody ever wants to hear that, %1$s, but there it is.
    >>  ............................................
    pt  Está bem. Sinceramente bem. Ninguém nunca quer ouvir isso, %1$s, mas é o que é.
    >>  ............................................
  dialogue.conversations.village.ask_improve.passive/3   [80 chars]
    en  Ask me when something's gone wrong. On an ordinary day I've no complaints ready.
    >>  ............................................
    pt  Me pergunte quando algo der errado. Num dia comum eu não tenho reclamação pronta.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the mood is `passive`  _(chance -2000)_
- Does: disposition — respect +3, familiarity +2  _(recorded under topic `village.respond.ask_improve`)_
- Does: session `turn`
- Then opens: `conversations.topic.village.fault.followup`
- …where the player's next choices will be: "Tell me and I'll help fix it." | "It could be run better, though." | "You're right about that." | "It's held together with string and stubbornness." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.village.respond.ask_improve
WHO    VILLAGER — what the player reads after pressing "What would you change about it?"
       spoken on: conversations.topic.village.respond, button `ask_improve`
       leaves the player on: conversations.topic.village.fault.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.fault_named`: the villager disclose_problems. Subject `village.home`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `village:home`, `village:fault_named` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, respectful_disagreement, self_disclosure, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.respond.ask_improve/1   [71 chars]
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  dialogue.conversations.village.respond.ask_improve/2   [90 chars]
    en  Asked as though the answer mattered. ...The road, then. The road, and I'll show you where.
    >>  ............................................
    pt  Perguntado como se a resposta importasse. ...A estrada, então. A estrada, e eu te mostro onde.
    >>  ............................................
  dialogue.conversations.village.respond.ask_improve/3   [58 chars]
    en  A dozen things. Most of them small, all of them years old.
    >>  ............................................
    pt  Uma dúzia de coisas. A maioria pequena, todas com anos de idade.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  anxious.dialogue.conversations.village.respond.ask_improve/2
    en  The well. It's the one that frightens me — everything else is only inconvenient.
    >>  ............................................
    pt  O poço. É o que me assusta — todo o resto é só inconveniente.
    >>  ............................................
  anxious.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. I've said it at four meetings and I'll say it at a fifth.
    >>  ............................................
    pt  Poço, estrada, porcos. Já disse em quatro reuniões e vou dizer numa quinta.
    >>  ............................................
  athletic.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. The pigs can wait; they've waited thirty years.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Os porcos podem esperar; esperaram trinta anos.
    >>  ............................................
  athletic.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Then the road. Neither is urgent and both have been needed a long time.
    >>  ............................................
    pt  O poço. Depois a estrada. Nenhum é urgente e os dois são precisos faz tempo.
    >>  ............................................
  athletic.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. It'll all get done eventually, in about that order.
    >>  ............................................
    pt  Poço, estrada, porcos. Vai tudo ser feito uma hora, mais ou menos nessa ordem.
    >>  ............................................
  confident.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  confident.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Everything else can wait behind the well.
    >>  ............................................
    pt  O poço. Todo o resto pode esperar atrás do poço.
    >>  ............................................
  confident.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. In that order, and I've been saying it for years.
    >>  ............................................
    pt  Poço, estrada, porcos. Nessa ordem, e eu digo isso há anos.
    >>  ............................................
  crabby.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  crabby.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Everything else can wait behind the well.
    >>  ............................................
    pt  O poço. Todo o resto pode esperar atrás do poço.
    >>  ............................................
  crabby.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. In that order, and I've been saying it for years.
    >>  ............................................
    pt  Poço, estrada, porcos. Nessa ordem, e eu digo isso há anos.
    >>  ............................................
  extroverted.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go, %1$s.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Ask the mason about it — he's been saying the same thing longer than I have.
    >>  ............................................
    pt  O poço. Pergunte ao pedreiro — ele diz a mesma coisa há mais tempo que eu.
    >>  ............................................
  extroverted.dialogue.conversations.village.respond.ask_improve/3
    en  Well, then road. And if you ever get a say in it, the pigs. Please, the pigs.
    >>  ............................................
    pt  Poço, depois estrada. E se você um dia puder opinar, os porcos. Por favor, os porcos.
    >>  ............................................
  flirty.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go, %1$s.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Ask the mason about it — he's been saying the same thing longer than I have.
    >>  ............................................
    pt  O poço. Pergunte ao pedreiro — ele diz a mesma coisa há mais tempo que eu.
    >>  ............................................
  flirty.dialogue.conversations.village.respond.ask_improve/3
    en  Well, then road. And if you ever get a say in it, the pigs. Please, the pigs.
    >>  ............................................
    pt  Poço, depois estrada. E se você um dia puder opinar, os porcos. Por favor, os porcos.
    >>  ............................................
  friendly.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go, %1$s.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Ask the mason about it — he's been saying the same thing longer than I have.
    >>  ............................................
    pt  O poço. Pergunte ao pedreiro — ele diz a mesma coisa há mais tempo que eu.
    >>  ............................................
  friendly.dialogue.conversations.village.respond.ask_improve/3
    en  Well, then road. And if you ever get a say in it, the pigs. Please, the pigs.
    >>  ............................................
    pt  Poço, depois estrada. E se você um dia puder opinar, os porcos. Por favor, os porcos.
    >>  ............................................
  gloomy.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  gloomy.dialogue.conversations.village.respond.ask_improve/2
    en  The well. It's the one that frightens me — everything else is only inconvenient.
    >>  ............................................
    pt  O poço. É o que me assusta — todo o resto é só inconveniente.
    >>  ............................................
  gloomy.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. I've said it at four meetings and I'll say it at a fifth.
    >>  ............................................
    pt  Poço, estrada, porcos. Já disse em quatro reuniões e vou dizer numa quinta.
    >>  ............................................
  greedy.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  greedy.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Everything else can wait behind the well.
    >>  ............................................
    pt  O poço. Todo o resto pode esperar atrás do poço.
    >>  ............................................
  greedy.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. In that order, and I've been saying it for years.
    >>  ............................................
    pt  Poço, estrada, porcos. Nessa ordem, e eu digo isso há anos.
    >>  ............................................
  grumpy.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  grumpy.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Everything else can wait behind the well.
    >>  ............................................
    pt  O poço. Todo o resto pode esperar atrás do poço.
    >>  ............................................
  grumpy.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. In that order, and I've been saying it for years.
    >>  ............................................
    pt  Poço, estrada, porcos. Nessa ordem, e eu digo isso há anos.
    >>  ............................................
  introverted.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada.
    >>  ............................................
  introverted.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Then everything else.
    >>  ............................................
    pt  O poço. Depois todo o resto.
    >>  ............................................
  introverted.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. That's the list.
    >>  ............................................
    pt  Poço, estrada, porcos. É a lista.
    >>  ............................................
  lazy.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. The pigs can wait; they've waited thirty years.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Os porcos podem esperar; esperaram trinta anos.
    >>  ............................................
  lazy.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Then the road. Neither is urgent and both have been needed a long time.
    >>  ............................................
    pt  O poço. Depois a estrada. Nenhum é urgente e os dois são precisos faz tempo.
    >>  ............................................
  lazy.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. It'll all get done eventually, in about that order.
    >>  ............................................
    pt  Poço, estrada, porcos. Vai tudo ser feito uma hora, mais ou menos nessa ordem.
    >>  ............................................
  odd.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada.
    >>  ............................................
  odd.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Then everything else.
    >>  ............................................
    pt  O poço. Depois todo o resto.
    >>  ............................................
  odd.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. That's the list.
    >>  ............................................
    pt  Poço, estrada, porcos. É a lista.
    >>  ............................................
  peaceful.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. The pigs can wait; they've waited thirty years.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Os porcos podem esperar; esperaram trinta anos.
    >>  ............................................
  peaceful.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Then the road. Neither is urgent and both have been needed a long time.
    >>  ............................................
    pt  O poço. Depois a estrada. Nenhum é urgente e os dois são precisos faz tempo.
    >>  ............................................
  peaceful.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. It'll all get done eventually, in about that order.
    >>  ............................................
    pt  Poço, estrada, porcos. Vai tudo ser feito uma hora, mais ou menos nessa ordem.
    >>  ............................................
  peppy.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  peppy.dialogue.conversations.village.respond.ask_improve/2
    en  Well, road, pigs! In that order. I've a whole speech and you've caught the short version.
    >>  ............................................
    pt  Poço, estrada, porcos! Nessa ordem. Tenho um discurso inteiro e você pegou a versão curta.
    >>  ............................................
  peppy.dialogue.conversations.village.respond.ask_improve/3
    en  The well. Then the road. Then a serious conversation about the pigs.
    >>  ............................................
    pt  O poço. Depois a estrada. Depois uma conversa séria sobre os porcos.
    >>  ............................................
  playful.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  playful.dialogue.conversations.village.respond.ask_improve/2
    en  Well, road, pigs! In that order. I've a whole speech and you've caught the short version.
    >>  ............................................
    pt  Poço, estrada, porcos! Nessa ordem. Tenho um discurso inteiro e você pegou a versão curta.
    >>  ............................................
  playful.dialogue.conversations.village.respond.ask_improve/3
    en  The well. Then the road. Then a serious conversation about the pigs.
    >>  ............................................
    pt  O poço. Depois a estrada. Depois uma conversa séria sobre os porcos.
    >>  ............................................
  relaxed.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. The pigs can wait; they've waited thirty years.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Os porcos podem esperar; esperaram trinta anos.
    >>  ............................................
  relaxed.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Then the road. Neither is urgent and both have been needed a long time.
    >>  ............................................
    pt  O poço. Depois a estrada. Nenhum é urgente e os dois são precisos faz tempo.
    >>  ............................................
  relaxed.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. It'll all get done eventually, in about that order.
    >>  ............................................
    pt  Poço, estrada, porcos. Vai tudo ser feito uma hora, mais ou menos nessa ordem.
    >>  ............................................
  sensitive.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  sensitive.dialogue.conversations.village.respond.ask_improve/2
    en  The well. It's the one that frightens me — everything else is only inconvenient.
    >>  ............................................
    pt  O poço. É o que me assusta — todo o resto é só inconveniente.
    >>  ............................................
  sensitive.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. I've said it at four meetings and I'll say it at a fifth.
    >>  ............................................
    pt  Poço, estrada, porcos. Já disse em quatro reuniões e vou dizer numa quinta.
    >>  ............................................
  shy.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada.
    >>  ............................................
  shy.dialogue.conversations.village.respond.ask_improve/2
    en  The well. Then everything else.
    >>  ............................................
    pt  O poço. Depois todo o resto.
    >>  ............................................
  shy.dialogue.conversations.village.respond.ask_improve/3
    en  Well, road, pigs. That's the list.
    >>  ............................................
    pt  Poço, estrada, porcos. É a lista.
    >>  ............................................
  upbeat.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  upbeat.dialogue.conversations.village.respond.ask_improve/2
    en  Well, road, pigs! In that order. I've a whole speech and you've caught the short version.
    >>  ............................................
    pt  Poço, estrada, porcos! Nessa ordem. Tenho um discurso inteiro e você pegou a versão curta.
    >>  ............................................
  upbeat.dialogue.conversations.village.respond.ask_improve/3
    en  The well. Then the road. Then a serious conversation about the pigs.
    >>  ............................................
    pt  O poço. Depois a estrada. Depois uma conversa séria sobre os porcos.
    >>  ............................................
  witty.dialogue.conversations.village.respond.ask_improve/1
    en  The well, first. Then the road. Then whoever decided where the pigs go.
    >>  ............................................
    pt  O poço, primeiro. Depois a estrada. Depois quem decidiu onde ficam os porcos.
    >>  ............................................
  witty.dialogue.conversations.village.respond.ask_improve/2
    en  Well, road, pigs! In that order. I've a whole speech and you've caught the short version.
    >>  ............................................
    pt  Poço, estrada, porcos! Nessa ordem. Tenho um discurso inteiro e você pegou a versão curta.
    >>  ............................................
  witty.dialogue.conversations.village.respond.ask_improve/3
    en  The well. Then the road. Then a serious conversation about the pigs.
    >>  ............................................
    pt  O poço. Depois a estrada. Depois uma conversa séria sobre os porcos.
    >>  ............................................
```

</details>


### Button `insult` — "It's a miserable little place."

*stance family `dismissal` · tone `hostile` · answers the beat(s) `village.home.to.village`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.respond.insult` — accepted phrasings: "it is a miserable little place"; "this place is a dump"; "awful place"
  - the message must contain one of: `miserable`, `dump`, `awful`
  - scored words: `miserable`(1.5), `dump`(1.5), `awful`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.village.respond.insult
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.respond.insult   [30 chars]
    en  It's a miserable little place.
    >>  ............................................
    pt  É um lugarzinho miserável.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `village.respond.insult`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -4, tension +6  _(recorded under topic `village.respond.insult`)_
- Does: session `turn`
- Then opens: `conversations.topic.village.insulted.followup`
- …where the player's next choices will be: "That was out of order. I'm sorry." | "I've had a worse day than this place deserves." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.village.respond.insult
WHO    VILLAGER — what the player reads after pressing "It's a miserable little place."
       spoken on: conversations.topic.village.respond, button `insult`
       leaves the player on: conversations.topic.village.insulted.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.insulted`: the villager hurts. Subject `village.insult`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `village:home`, `player:insulted_village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.respond.insult/1   [47 chars]
    en  ...It's my home, %1$s. Say that somewhere else.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Diga isso em outro lugar.
    >>  ............................................
  dialogue.conversations.village.respond.insult/2   [46 chars]
    en  Miserable. Right. And yet here you are, in it.
    >>  ............................................
    pt  Miserável. Certo. E mesmo assim você está aqui, nela.
    >>  ............................................
  dialogue.conversations.village.respond.insult/3   [60 chars]
    en  Everyone here has built something. You've walked through it.
    >>  ............................................
    pt  Todo mundo aqui construiu algo. Você só passou por dentro.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. I've nowhere else and you know that.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Eu não tenho outro lugar e você sabe.
    >>  ............................................
  anxious.dialogue.conversations.village.respond.insult/2
    en  Don't. Please. I know what's wrong with it better than you do.
    >>  ............................................
    pt  Não. Por favor. Eu sei o que há de errado melhor que você.
    >>  ............................................
  anxious.dialogue.conversations.village.respond.insult/3
    en  ...Right. I'll not defend it to you. I'm too tired to.
    >>  ............................................
    pt  ...Certo. Não vou defender pra você. Estou cansado demais.
    >>  ............................................
  athletic.dialogue.conversations.village.respond.insult/1
    en  It's my home. Say that somewhere else and we'll both be happier.
    >>  ............................................
    pt  É a minha casa. Diga isso em outro lugar e nós dois ficamos mais felizes.
    >>  ............................................
  athletic.dialogue.conversations.village.respond.insult/2
    en  ...Aye, it has its faults. It's still where I'll be buried.
    >>  ............................................
    pt  ...É, tem defeitos. Ainda é onde eu vou ser enterrado.
    >>  ............................................
  athletic.dialogue.conversations.village.respond.insult/3
    en  Right. I'll not argue about it. It'll be here after both of us.
    >>  ............................................
    pt  Certo. Não vou discutir. Vai estar aqui depois de nós dois.
    >>  ............................................
  confident.dialogue.conversations.village.respond.insult/1
    en  It's my home. Say that somewhere else.
    >>  ............................................
    pt  É a minha casa. Diga isso em outro lugar.
    >>  ............................................
  confident.dialogue.conversations.village.respond.insult/2
    en  No. You'll not talk about this place like that in front of me.
    >>  ............................................
    pt  Não. Você não vai falar deste lugar assim na minha frente.
    >>  ............................................
  confident.dialogue.conversations.village.respond.insult/3
    en  ...We're done with that subject.
    >>  ............................................
    pt  ...Terminamos com esse assunto.
    >>  ............................................
  crabby.dialogue.conversations.village.respond.insult/1
    en  It's my home. Say that somewhere else.
    >>  ............................................
    pt  É a minha casa. Diga isso em outro lugar.
    >>  ............................................
  crabby.dialogue.conversations.village.respond.insult/2
    en  No. You'll not talk about this place like that in front of me.
    >>  ............................................
    pt  Não. Você não vai falar deste lugar assim na minha frente.
    >>  ............................................
  crabby.dialogue.conversations.village.respond.insult/3
    en  ...We're done with that subject.
    >>  ............................................
    pt  ...Terminamos com esse assunto.
    >>  ............................................
  extroverted.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. Everyone I love is inside those walls.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Todos que eu amo estão dentro daqueles muros.
    >>  ............................................
  extroverted.dialogue.conversations.village.respond.insult/2
    en  Don't. I'd hoped you were starting to see what's good about it.
    >>  ............................................
    pt  Não. Eu esperava que você estivesse começando a ver o que tem de bom.
    >>  ............................................
  extroverted.dialogue.conversations.village.respond.insult/3
    en  ...Right. Say it somewhere I'm not, and we'll stay friends.
    >>  ............................................
    pt  ...Certo. Diga onde eu não esteja, e a gente continua amigo.
    >>  ............................................
  flirty.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. Everyone I love is inside those walls.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Todos que eu amo estão dentro daqueles muros.
    >>  ............................................
  flirty.dialogue.conversations.village.respond.insult/2
    en  Don't. I'd hoped you were starting to see what's good about it.
    >>  ............................................
    pt  Não. Eu esperava que você estivesse começando a ver o que tem de bom.
    >>  ............................................
  flirty.dialogue.conversations.village.respond.insult/3
    en  ...Right. Say it somewhere I'm not, and we'll stay friends.
    >>  ............................................
    pt  ...Certo. Diga onde eu não esteja, e a gente continua amigo.
    >>  ............................................
  friendly.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. Everyone I love is inside those walls.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Todos que eu amo estão dentro daqueles muros.
    >>  ............................................
  friendly.dialogue.conversations.village.respond.insult/2
    en  Don't. I'd hoped you were starting to see what's good about it.
    >>  ............................................
    pt  Não. Eu esperava que você estivesse começando a ver o que tem de bom.
    >>  ............................................
  friendly.dialogue.conversations.village.respond.insult/3
    en  ...Right. Say it somewhere I'm not, and we'll stay friends.
    >>  ............................................
    pt  ...Certo. Diga onde eu não esteja, e a gente continua amigo.
    >>  ............................................
  gloomy.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. I've nowhere else and you know that.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Eu não tenho outro lugar e você sabe.
    >>  ............................................
  gloomy.dialogue.conversations.village.respond.insult/2
    en  Don't. Please. I know what's wrong with it better than you do.
    >>  ............................................
    pt  Não. Por favor. Eu sei o que há de errado melhor que você.
    >>  ............................................
  gloomy.dialogue.conversations.village.respond.insult/3
    en  ...Right. I'll not defend it to you. I'm too tired to.
    >>  ............................................
    pt  ...Certo. Não vou defender pra você. Estou cansado demais.
    >>  ............................................
  greedy.dialogue.conversations.village.respond.insult/1
    en  It's my home. Say that somewhere else.
    >>  ............................................
    pt  É a minha casa. Diga isso em outro lugar.
    >>  ............................................
  greedy.dialogue.conversations.village.respond.insult/2
    en  No. You'll not talk about this place like that in front of me.
    >>  ............................................
    pt  Não. Você não vai falar deste lugar assim na minha frente.
    >>  ............................................
  greedy.dialogue.conversations.village.respond.insult/3
    en  ...We're done with that subject.
    >>  ............................................
    pt  ...Terminamos com esse assunto.
    >>  ............................................
  grumpy.dialogue.conversations.village.respond.insult/1
    en  It's my home. Say that somewhere else.
    >>  ............................................
    pt  É a minha casa. Diga isso em outro lugar.
    >>  ............................................
  grumpy.dialogue.conversations.village.respond.insult/2
    en  No. You'll not talk about this place like that in front of me.
    >>  ............................................
    pt  Não. Você não vai falar deste lugar assim na minha frente.
    >>  ............................................
  grumpy.dialogue.conversations.village.respond.insult/3
    en  ...We're done with that subject.
    >>  ............................................
    pt  ...Terminamos com esse assunto.
    >>  ............................................
  introverted.dialogue.conversations.village.respond.insult/1
    en  ...It's my home.
    >>  ............................................
    pt  ...É a minha casa.
    >>  ............................................
  introverted.dialogue.conversations.village.respond.insult/2
    en  Say that somewhere else.
    >>  ............................................
    pt  Diga isso em outro lugar.
    >>  ............................................
  introverted.dialogue.conversations.village.respond.insult/3
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  lazy.dialogue.conversations.village.respond.insult/1
    en  It's my home. Say that somewhere else and we'll both be happier.
    >>  ............................................
    pt  É a minha casa. Diga isso em outro lugar e nós dois ficamos mais felizes.
    >>  ............................................
  lazy.dialogue.conversations.village.respond.insult/2
    en  ...Aye, it has its faults. It's still where I'll be buried.
    >>  ............................................
    pt  ...É, tem defeitos. Ainda é onde eu vou ser enterrado.
    >>  ............................................
  lazy.dialogue.conversations.village.respond.insult/3
    en  Right. I'll not argue about it. It'll be here after both of us.
    >>  ............................................
    pt  Certo. Não vou discutir. Vai estar aqui depois de nós dois.
    >>  ............................................
  odd.dialogue.conversations.village.respond.insult/1
    en  ...It's my home.
    >>  ............................................
    pt  ...É a minha casa.
    >>  ............................................
  odd.dialogue.conversations.village.respond.insult/2
    en  Say that somewhere else.
    >>  ............................................
    pt  Diga isso em outro lugar.
    >>  ............................................
  odd.dialogue.conversations.village.respond.insult/3
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  peaceful.dialogue.conversations.village.respond.insult/1
    en  It's my home. Say that somewhere else and we'll both be happier.
    >>  ............................................
    pt  É a minha casa. Diga isso em outro lugar e nós dois ficamos mais felizes.
    >>  ............................................
  peaceful.dialogue.conversations.village.respond.insult/2
    en  ...Aye, it has its faults. It's still where I'll be buried.
    >>  ............................................
    pt  ...É, tem defeitos. Ainda é onde eu vou ser enterrado.
    >>  ............................................
  peaceful.dialogue.conversations.village.respond.insult/3
    en  Right. I'll not argue about it. It'll be here after both of us.
    >>  ............................................
    pt  Certo. Não vou discutir. Vai estar aqui depois de nós dois.
    >>  ............................................
  peppy.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. I'm allowed to say that; you're not.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Eu posso dizer isso; você não.
    >>  ............................................
  peppy.dialogue.conversations.village.respond.insult/2
    en  Right! Insult my village. That'll go down beautifully in the inn.
    >>  ............................................
    pt  Certo! Insulte o meu vilarejo. Isso vai cair maravilhosamente na estalagem.
    >>  ............................................
  peppy.dialogue.conversations.village.respond.insult/3
    en  ...Ha. No. Try that on the mayor and see what happens.
    >>  ............................................
    pt  ...Ha. Não. Tente isso com o prefeito e veja o que acontece.
    >>  ............................................
  playful.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. I'm allowed to say that; you're not.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Eu posso dizer isso; você não.
    >>  ............................................
  playful.dialogue.conversations.village.respond.insult/2
    en  Right! Insult my village. That'll go down beautifully in the inn.
    >>  ............................................
    pt  Certo! Insulte o meu vilarejo. Isso vai cair maravilhosamente na estalagem.
    >>  ............................................
  playful.dialogue.conversations.village.respond.insult/3
    en  ...Ha. No. Try that on the mayor and see what happens.
    >>  ............................................
    pt  ...Ha. Não. Tente isso com o prefeito e veja o que acontece.
    >>  ............................................
  relaxed.dialogue.conversations.village.respond.insult/1
    en  It's my home. Say that somewhere else and we'll both be happier.
    >>  ............................................
    pt  É a minha casa. Diga isso em outro lugar e nós dois ficamos mais felizes.
    >>  ............................................
  relaxed.dialogue.conversations.village.respond.insult/2
    en  ...Aye, it has its faults. It's still where I'll be buried.
    >>  ............................................
    pt  ...É, tem defeitos. Ainda é onde eu vou ser enterrado.
    >>  ............................................
  relaxed.dialogue.conversations.village.respond.insult/3
    en  Right. I'll not argue about it. It'll be here after both of us.
    >>  ............................................
    pt  Certo. Não vou discutir. Vai estar aqui depois de nós dois.
    >>  ............................................
  sensitive.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. I've nowhere else and you know that.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Eu não tenho outro lugar e você sabe.
    >>  ............................................
  sensitive.dialogue.conversations.village.respond.insult/2
    en  Don't. Please. I know what's wrong with it better than you do.
    >>  ............................................
    pt  Não. Por favor. Eu sei o que há de errado melhor que você.
    >>  ............................................
  sensitive.dialogue.conversations.village.respond.insult/3
    en  ...Right. I'll not defend it to you. I'm too tired to.
    >>  ............................................
    pt  ...Certo. Não vou defender pra você. Estou cansado demais.
    >>  ............................................
  shy.dialogue.conversations.village.respond.insult/1
    en  ...It's my home.
    >>  ............................................
    pt  ...É a minha casa.
    >>  ............................................
  shy.dialogue.conversations.village.respond.insult/2
    en  Say that somewhere else.
    >>  ............................................
    pt  Diga isso em outro lugar.
    >>  ............................................
  shy.dialogue.conversations.village.respond.insult/3
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  upbeat.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. I'm allowed to say that; you're not.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Eu posso dizer isso; você não.
    >>  ............................................
  upbeat.dialogue.conversations.village.respond.insult/2
    en  Right! Insult my village. That'll go down beautifully in the inn.
    >>  ............................................
    pt  Certo! Insulte o meu vilarejo. Isso vai cair maravilhosamente na estalagem.
    >>  ............................................
  upbeat.dialogue.conversations.village.respond.insult/3
    en  ...Ha. No. Try that on the mayor and see what happens.
    >>  ............................................
    pt  ...Ha. Não. Tente isso com o prefeito e veja o que acontece.
    >>  ............................................
  witty.dialogue.conversations.village.respond.insult/1
    en  ...It's my home, %1$s. I'm allowed to say that; you're not.
    >>  ............................................
    pt  ...É a minha casa, %1$s. Eu posso dizer isso; você não.
    >>  ............................................
  witty.dialogue.conversations.village.respond.insult/2
    en  Right! Insult my village. That'll go down beautifully in the inn.
    >>  ............................................
    pt  Certo! Insulte o meu vilarejo. Isso vai cair maravilhosamente na estalagem.
    >>  ............................................
  witty.dialogue.conversations.village.respond.insult/3
    en  ...Ha. No. Try that on the mayor and see what happens.
    >>  ............................................
    pt  ...Ha. Não. Tente isso com o prefeito e veja o que acontece.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `village.home.to.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.village.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.left`: the villager accepts. Subject `village.belonging`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.village.fault.followup / leave; conversations.topic.village.insulted.followup / leave; conversations.topic.village.praised.followup / leave; conversations.topic.village.settled.followup / leave
```

> Written out in full under **`conversations.topic.village.fault.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.village.settled.followup`

**Reached from 1 route(s):** `conversations.topic.village.respond` / `ask_improve`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.ask_improve.passive` — e.g. "Improve it. Hm. Nothing springs to mind, which is either a good sign or a very bad one."


```text
POOL   dialogue key: dialogue.conversations.topic.village.settled.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.settled.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.settled.followup   [32 chars]
    en  Nothing to mend that I can name.
    >>  ............................................
    pt  Nada pra consertar que eu saiba nomear.
    >>  ............................................
```


### Button `agree` — "That's not nothing."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `village.no_complaint` · offered only once the villager has actually said `village:no_complaint`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.settled.agree` — accepted phrasings: "that is not nothing"; "that is something, though"; "a peaceful place is worth having"
  - the message must contain one of: `nothing`, `something`, `peaceful`
  - scored words: `nothing`(1.2), `something`(1.2), `peaceful`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.village.settled.followup.agree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.settled.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.settled.followup.agree   [19 chars]
    en  That's not nothing.
    >>  ............................................
    pt  Isso não é pouca coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.settled.agree`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +1, warmth +2  _(recorded under topic `village.settled.agree`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.settled.agree
WHO    VILLAGER — what the player reads after pressing "That's not nothing."
       spoken on: conversations.topic.village.settled.followup, button `agree`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.settled.agree`: the villager accepts. Subject `village.belonging`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.settled.agree/1   [53 chars]
    en  No. It isn't. Takes a while to see it that way, mind.
    >>  ............................................
    pt  Não é mesmo. Mas demora pra ver assim.
    >>  ............................................
  dialogue.conversations.village.settled.agree/2   [54 chars]
    en  That's the kindest reading of 'fine' I've heard, %1$s.
    >>  ............................................
    pt  É a leitura mais gentil de 'tudo bem' que eu já ouvi, %1$s.
    >>  ............................................
  dialogue.conversations.village.settled.agree/3   [68 chars]
    en  Aye. Most places would trade us for it and not know what they'd got.
    >>  ............................................
    pt  É. A maioria dos lugares trocaria com a gente e não saberia o que ganhou.
    >>  ............................................
```


### Button `press` — "Nothing at all? Truly?"

*stance family `curiosity` · tone `plain` · outcome `qualified` · answers the beat(s) `village.no_complaint` · offered only once the villager has actually said `village:no_complaint`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.settled.press` — accepted phrasings: "nothing at all, truly"; "surely there is something"; "there must be something"
  - the message must contain one of: `truly`, `surely`
  - scored words: `truly`(1.2), `surely`(1.5), `something`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.village.settled.followup.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.settled.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.settled.followup.press   [22 chars]
    en  Nothing at all? Truly?
    >>  ............................................
    pt  Nada mesmo? Sério?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `village.settled.press`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.settled.press
WHO    VILLAGER — what the player reads after pressing "Nothing at all? Truly?"
       spoken on: conversations.topic.village.settled.followup, button `press`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.settled.press`: the villager qualifys. Subject `village.belonging`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.settled.press/1   [82 chars]
    en  ...The road. There, you've dragged it out of me. But it's been the road for years.
    >>  ............................................
    pt  ...A estrada. Pronto, você arrancou de mim. Mas é a estrada faz anos.
    >>  ............................................
  dialogue.conversations.village.settled.press/2   [74 chars]
    en  Truly. Ask me in February and you'll get a list as long as your arm, %1$s.
    >>  ............................................
    pt  Sério. Me pergunte em fevereiro e você ganha uma lista do tamanho do seu braço, %1$s.
    >>  ............................................
  dialogue.conversations.village.settled.press/3   [67 chars]
    en  Nothing worth the breath. That's a strange thing to have to defend.
    >>  ............................................
    pt  Nada que valha o fôlego. É estranho ter que defender isso.
    >>  ............................................
```


### Button `joke` — "Dull is a kind of luxury."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `village.no_complaint`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.settled.joke` — accepted phrasings: "dull is a kind of luxury"; "quiet is a luxury"; "boring is underrated"
  - the message must contain one of: `dull`, `luxury`
  - scored words: `dull`(1.5), `luxury`(1.5), `quiet`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.village.settled.followup.joke
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.settled.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.settled.followup.joke   [25 chars]
    en  Dull is a kind of luxury.
    >>  ............................................
    pt  Sem graça é um tipo de luxo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.settled.joke`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -1, warmth +3  _(recorded under topic `village.settled.joke`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.settled.joke
WHO    VILLAGER — what the player reads after pressing "Dull is a kind of luxury."
       spoken on: conversations.topic.village.settled.followup, button `joke`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.settled.joke`: the villager accepts. Subject `village.belonging`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.settled.joke/1   [53 chars]
    en  It is. Try telling that to anyone under twenty, mind.
    >>  ............................................
    pt  É mesmo. Mas tente dizer isso pra alguém com menos de vinte anos.
    >>  ............................................
  dialogue.conversations.village.settled.joke/2   [70 chars]
    en  A luxury. Ha. I'll use that on the next one who calls us sleepy, %1$s.
    >>  ............................................
    pt  Um luxo. Ha. Vou usar essa no próximo que nos chamar de sonolentos, %1$s.
    >>  ............................................
  dialogue.conversations.village.settled.joke/3   [76 chars]
    en  Aye. Excitement is what a village calls it afterwards, when everyone's fine.
    >>  ............................................
    pt  É. Emoção é como um vilarejo chama depois, quando todo mundo está bem.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `village.no_complaint` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.settled.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.settled.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.settled.followup.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.village.settled.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.left`: the villager accepts. Subject `village.belonging`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.village.fault.followup / leave; conversations.topic.village.insulted.followup / leave; conversations.topic.village.praised.followup / leave; conversations.topic.village.respond / leave
```

> Written out in full under **`conversations.topic.village.fault.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.village.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.toddler` — e.g. "I like it here! My house is here and my toys are here."


```text
POOL   dialogue key: dialogue.conversations.topic.village.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.toddler.respond   [18 chars]
    en  That's my village.
    >>  ............................................
    pt  É a minha vila.
    >>  ............................................
```


### Button `delight` — "It's a good village."

*stance family `encouragement` · tone `playful` · answers the beat(s) `village.toddler.to.village.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.toddler.delight` — accepted phrasings: "it is a good village"; "that is a good village"; "a good village"
  - the message must contain one of: `village`, `good`
  - scored words: `village`(1.5), `good`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.village.toddler.respond.delight
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.toddler.respond.delight   [20 chars]
    en  It's a good village.
    >>  ............................................
    pt  É uma vila boa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.toddler.delight`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `village.toddler.delight`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.toddler.delight
WHO    VILLAGER — what the player reads after pressing "It's a good village."
       spoken on: conversations.topic.village.toddler.respond, button `delight`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.toddler.delight.terminal`: the villager celebrates. Subject `village.toddler`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.toddler.delight/1   [57 chars]
    en  It's the BEST village. There isn't a better one anywhere.
    >>  ............................................
    pt  É a MELHOR vila. Não tem nenhuma melhor em lugar nenhum.
    >>  ............................................
  dialogue.conversations.village.toddler.delight/2   [60 chars]
    en  You like it too! Then you should live here. Everyone should.
    >>  ............................................
    pt  Você também gosta! Então você devia morar aqui. Todo mundo devia.
    >>  ............................................
  dialogue.conversations.village.toddler.delight/3   [39 chars]
    en  Good village. Good, good village, %1$s.
    >>  ............................................
    pt  Vila boa. Boa, boa vila, %1$s.
    >>  ............................................
```


### Button `ask` — "What's your favourite bit of it?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `village.toddler.to.village.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.toddler.ask` — accepted phrasings: "what is your favourite bit of it"; "favourite bit of the village"; "which bit do you like best"
  - the message must contain one of: `favourite`, `bit`
  - scored words: `favourite`(1.5), `bit`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.village.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.toddler.respond.ask   [32 chars]
    en  What's your favourite bit of it?
    >>  ............................................
    pt  Qual é a sua parte favorita dela?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `village.toddler.ask`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.toddler.ask
WHO    VILLAGER — what the player reads after pressing "What's your favourite bit of it?"
       spoken on: conversations.topic.village.toddler.respond, button `ask`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.toddler.ask.terminal`: the villager asks. Subject `village.toddler`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.toddler.ask/1   [53 chars]
    en  The bit with the water in. But I'm not allowed close.
    >>  ............................................
    pt  A parte com água. Mas eu não posso chegar perto.
    >>  ............................................
  dialogue.conversations.village.toddler.ask/2   [47 chars]
    en  Behind the houses. There's a gap and it's MINE.
    >>  ............................................
    pt  Atrás das casas. Tem um vão e ele é MEU.
    >>  ............................................
  dialogue.conversations.village.toddler.ask/3   [55 chars]
    en  The high bit! You can see everything from the high bit.
    >>  ............................................
    pt  A parte alta! Da parte alta dá pra ver tudo.
    >>  ............................................
```


### Button `leave` — "Off you go, then."

*stance family `exit` · tone `plain` · answers the beat(s) `village.toddler.to.village.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.toddler.respond.leave   [17 chars]
    en  Off you go, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go, then."
       spoken on: conversations.topic.village.toddler.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.toddler.leave.terminal`: the villager accepts. Subject `village.toddler`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.toddler.leave/1   [34 chars]
    en  Bye! I'm going to go and be in it.
    >>  ............................................
    pt  Tchau! Vou ficar dentro dela.
    >>  ............................................
  dialogue.conversations.village.toddler.leave/2   [15 chars]
    en  Okay bye, %1$s!
    >>  ............................................
    pt  Tá, tchau, %1$s!
    >>  ............................................
  dialogue.conversations.village.toddler.leave/3   [34 chars]
    en  Bye bye. Don't get lost, it's big.
    >>  ............................................
    pt  Tchau tchau. Não se perde, ela é grande.
    >>  ............................................
```

---


## `conversations.topic.village.young.respond`

**Reached from 2 route(s):** `conversations.cat.village` / `village`; `conversations.cat.village` / `village`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.village.child` — e.g. "It's good! I know all the shortcuts. ALL of them."
- `conversations.village.teen` — e.g. "It's small. Everyone knows everything about everyone."


```text
POOL   dialogue key: dialogue.conversations.topic.village.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.village.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.village.young.respond   [20 chars]
    en  That's where I live!
    >>  ............................................
    pt  É onde eu moro!
    >>  ............................................
```


### Button `play_along` — "It's a good village, isn't it."

*stance family `encouragement` · tone `playful` · answers the beat(s) `village.child.to.village.young`, `village.teen.to.village.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.young.play_along` — accepted phrasings: "it is a good village"; "nice village"; "good village this"
  - the message must contain one of: `good`, `village`, `nice`
  - scored words: `good`(1.0), `village`(1.2), `nice`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.village.young.respond.play_along
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.young.respond.play_along   [30 chars]
    en  It's a good village, isn't it.
    >>  ............................................
    pt  É uma boa vila, né.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `village.young.play_along`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `village.young.play_along`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.young.play_along
WHO    VILLAGER — what the player reads after pressing "It's a good village, isn't it."
       spoken on: conversations.topic.village.young.respond, button `play_along`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.young.play_along.terminal`: the villager accepts. Subject `village.young`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.young.play_along/1   [37 chars]
    en  It IS. There's a bell and everything.
    >>  ............................................
    pt  É MESMO. Tem um sino e tudo.
    >>  ............................................
  dialogue.conversations.village.young.play_along/2   [48 chars]
    en  Best village. I've only seen the one, but still.
    >>  ............................................
    pt  A melhor vila. Só vi essa, mas mesmo assim.
    >>  ............................................
  dialogue.conversations.village.young.play_along/3   [23 chars]
    en  You think so too! Good.
    >>  ............................................
    pt  Você também acha! Que bom.
    >>  ............................................
```


### Button `ask` — "What's the best bit?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `village.child.to.village.young`, `village.teen.to.village.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.young.ask` — accepted phrasings: "what is the best bit"; "what is your favourite bit"; "best part of it"
  - the message must contain one of: `best`, `bit`, `favourite`
  - scored words: `best`(1.5), `bit`(1.0), `favourite`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.village.young.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.young.respond.ask   [20 chars]
    en  What's the best bit?
    >>  ............................................
    pt  Qual é a melhor parte?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `village.young.ask`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.young.ask
WHO    VILLAGER — what the player reads after pressing "What's the best bit?"
       spoken on: conversations.topic.village.young.respond, button `ask`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.young.ask.terminal`: the villager asks. Subject `village.young`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.young.ask/1   [32 chars]
    en  The well. You can shout into it.
    >>  ............................................
    pt  O poço. Dá para gritar dentro dele.
    >>  ............................................
  dialogue.conversations.village.young.ask/2   [60 chars]
    en  Market day. Everyone's out and nobody's counting where I am.
    >>  ............................................
    pt  Dia de feira. Todo mundo na rua e ninguém contando onde eu estou.
    >>  ............................................
  dialogue.conversations.village.young.ask/3   [32 chars]
    en  The big tree. It's mine, mostly.
    >>  ............................................
    pt  A árvore grande. É minha, mais ou menos.
    >>  ............................................
```


### Button `dismiss` — "It's a bit dull, though."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `village.child.to.village.young`, `village.teen.to.village.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.young.dismiss` — accepted phrasings: "it is a bit dull"; "it is boring here"; "bit dull, this place"
  - the message must contain one of: `dull`, `boring`
  - scored words: `dull`(1.5), `boring`(1.5), `quiet`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.village.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.young.respond.dismiss   [24 chars]
    en  It's a bit dull, though.
    >>  ............................................
    pt  Mas é meio sem graça.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `village.young.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `village.young.dismiss`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.young.dismiss
WHO    VILLAGER — what the player reads after pressing "It's a bit dull, though."
       spoken on: conversations.topic.village.young.respond, button `dismiss`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.young.dismiss.terminal`: the villager dismisss. Subject `village.young`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.young.dismiss/1   [14 chars]
    en  It's NOT dull.
    >>  ............................................
    pt  NÃO é sem graça.
    >>  ............................................
  dialogue.conversations.village.young.dismiss/2   [26 chars]
    en  You haven't seen the well.
    >>  ............................................
    pt  Você não viu o poço.
    >>  ............................................
  dialogue.conversations.village.young.dismiss/3   [19 chars]
    en  ...It's a bit dull.
    >>  ............................................
    pt  ...É meio sem graça.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull. It's mine, %1$s.
    >>  ............................................
    pt  NÃO é chato. É meu, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.village.young.dismiss/2
    en  ...Don't say that about it. It's where I'm from.
    >>  ............................................
    pt  ...Não fale assim dele. É de onde eu sou.
    >>  ............................................
  anxious.dialogue.conversations.village.young.dismiss/3
    en  ...Fine. I won't show you the rest of it, then.
    >>  ............................................
    pt  ...Tudo bem. Então não te mostro o resto.
    >>  ............................................
  athletic.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull. It's slow, which isn't the same thing.
    >>  ............................................
    pt  NÃO é chato. É lento, que não é a mesma coisa.
    >>  ............................................
  athletic.dialogue.conversations.village.young.dismiss/2
    en  ...You'll see it differently after a winter here.
    >>  ............................................
    pt  ...Você vai ver diferente depois de um inverno aqui.
    >>  ............................................
  athletic.dialogue.conversations.village.young.dismiss/3
    en  Right you are. Give it time and ask me again.
    >>  ............................................
    pt  Você tem razão. Dê tempo e me pergunte de novo.
    >>  ............................................
  confident.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull.
    >>  ............................................
    pt  NÃO é chato.
    >>  ............................................
  confident.dialogue.conversations.village.young.dismiss/2
    en  Right. Then go and be bored somewhere else.
    >>  ............................................
    pt  Certo. Então vá ficar entediado em outro lugar.
    >>  ............................................
  confident.dialogue.conversations.village.young.dismiss/3
    en  ...You've not looked properly.
    >>  ............................................
    pt  ...Você não olhou direito.
    >>  ............................................
  crabby.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull.
    >>  ............................................
    pt  NÃO é chato.
    >>  ............................................
  crabby.dialogue.conversations.village.young.dismiss/2
    en  Right. Then go and be bored somewhere else.
    >>  ............................................
    pt  Certo. Então vá ficar entediado em outro lugar.
    >>  ............................................
  crabby.dialogue.conversations.village.young.dismiss/3
    en  ...You've not looked properly.
    >>  ............................................
    pt  ...Você não olhou direito.
    >>  ............................................
  extroverted.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull, %1$s. I'd show you the good parts if you asked.
    >>  ............................................
    pt  NÃO é chato, %1$s. Eu te mostraria as partes boas se você pedisse.
    >>  ............................................
  extroverted.dialogue.conversations.village.young.dismiss/2
    en  You've not met everyone yet. That's the whole of it, really.
    >>  ............................................
    pt  Você ainda não conheceu todo mundo. É isso, na verdade.
    >>  ............................................
  extroverted.dialogue.conversations.village.young.dismiss/3
    en  ...Right. I'll take you round and you can decide after.
    >>  ............................................
    pt  ...Certo. Eu te levo pra dar uma volta e você decide depois.
    >>  ............................................
  flirty.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull, %1$s. I'd show you the good parts if you asked.
    >>  ............................................
    pt  NÃO é chato, %1$s. Eu te mostraria as partes boas se você pedisse.
    >>  ............................................
  flirty.dialogue.conversations.village.young.dismiss/2
    en  You've not met everyone yet. That's the whole of it, really.
    >>  ............................................
    pt  Você ainda não conheceu todo mundo. É isso, na verdade.
    >>  ............................................
  flirty.dialogue.conversations.village.young.dismiss/3
    en  ...Right. I'll take you round and you can decide after.
    >>  ............................................
    pt  ...Certo. Eu te levo pra dar uma volta e você decide depois.
    >>  ............................................
  friendly.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull, %1$s. I'd show you the good parts if you asked.
    >>  ............................................
    pt  NÃO é chato, %1$s. Eu te mostraria as partes boas se você pedisse.
    >>  ............................................
  friendly.dialogue.conversations.village.young.dismiss/2
    en  You've not met everyone yet. That's the whole of it, really.
    >>  ............................................
    pt  Você ainda não conheceu todo mundo. É isso, na verdade.
    >>  ............................................
  friendly.dialogue.conversations.village.young.dismiss/3
    en  ...Right. I'll take you round and you can decide after.
    >>  ............................................
    pt  ...Certo. Eu te levo pra dar uma volta e você decide depois.
    >>  ............................................
  gloomy.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull. It's mine, %1$s.
    >>  ............................................
    pt  NÃO é chato. É meu, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.village.young.dismiss/2
    en  ...Don't say that about it. It's where I'm from.
    >>  ............................................
    pt  ...Não fale assim dele. É de onde eu sou.
    >>  ............................................
  gloomy.dialogue.conversations.village.young.dismiss/3
    en  ...Fine. I won't show you the rest of it, then.
    >>  ............................................
    pt  ...Tudo bem. Então não te mostro o resto.
    >>  ............................................
  greedy.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull.
    >>  ............................................
    pt  NÃO é chato.
    >>  ............................................
  greedy.dialogue.conversations.village.young.dismiss/2
    en  Right. Then go and be bored somewhere else.
    >>  ............................................
    pt  Certo. Então vá ficar entediado em outro lugar.
    >>  ............................................
  greedy.dialogue.conversations.village.young.dismiss/3
    en  ...You've not looked properly.
    >>  ............................................
    pt  ...Você não olhou direito.
    >>  ............................................
  grumpy.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull.
    >>  ............................................
    pt  NÃO é chato.
    >>  ............................................
  grumpy.dialogue.conversations.village.young.dismiss/2
    en  Right. Then go and be bored somewhere else.
    >>  ............................................
    pt  Certo. Então vá ficar entediado em outro lugar.
    >>  ............................................
  grumpy.dialogue.conversations.village.young.dismiss/3
    en  ...You've not looked properly.
    >>  ............................................
    pt  ...Você não olhou direito.
    >>  ............................................
  introverted.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull.
    >>  ............................................
    pt  NÃO é chato.
    >>  ............................................
  introverted.dialogue.conversations.village.young.dismiss/2
    en  ...You just haven't looked.
    >>  ............................................
    pt  ...Você é que não olhou.
    >>  ............................................
  introverted.dialogue.conversations.village.young.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  lazy.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull. It's slow, which isn't the same thing.
    >>  ............................................
    pt  NÃO é chato. É lento, que não é a mesma coisa.
    >>  ............................................
  lazy.dialogue.conversations.village.young.dismiss/2
    en  ...You'll see it differently after a winter here.
    >>  ............................................
    pt  ...Você vai ver diferente depois de um inverno aqui.
    >>  ............................................
  lazy.dialogue.conversations.village.young.dismiss/3
    en  Right you are. Give it time and ask me again.
    >>  ............................................
    pt  Você tem razão. Dê tempo e me pergunte de novo.
    >>  ............................................
  odd.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull.
    >>  ............................................
    pt  NÃO é chato.
    >>  ............................................
  odd.dialogue.conversations.village.young.dismiss/2
    en  ...You just haven't looked.
    >>  ............................................
    pt  ...Você é que não olhou.
    >>  ............................................
  odd.dialogue.conversations.village.young.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  peaceful.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull. It's slow, which isn't the same thing.
    >>  ............................................
    pt  NÃO é chato. É lento, que não é a mesma coisa.
    >>  ............................................
  peaceful.dialogue.conversations.village.young.dismiss/2
    en  ...You'll see it differently after a winter here.
    >>  ............................................
    pt  ...Você vai ver diferente depois de um inverno aqui.
    >>  ............................................
  peaceful.dialogue.conversations.village.young.dismiss/3
    en  Right you are. Give it time and ask me again.
    >>  ............................................
    pt  Você tem razão. Dê tempo e me pergunte de novo.
    >>  ............................................
  peppy.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull! There's a whole thing behind the mill you don't know about.
    >>  ............................................
    pt  NÃO é chato! Tem uma coisa inteira atrás do moinho que você não sabe.
    >>  ............................................
  peppy.dialogue.conversations.village.young.dismiss/2
    en  Right! Dull. Says the person who's never been up the tower.
    >>  ............................................
    pt  Certo! Chato. Diz quem nunca subiu a torre.
    >>  ............................................
  peppy.dialogue.conversations.village.young.dismiss/3
    en  ...Ha. Come with me tomorrow and then say that.
    >>  ............................................
    pt  ...Ha. Venha comigo amanhã e aí você diz isso.
    >>  ............................................
  playful.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull! There's a whole thing behind the mill you don't know about.
    >>  ............................................
    pt  NÃO é chato! Tem uma coisa inteira atrás do moinho que você não sabe.
    >>  ............................................
  playful.dialogue.conversations.village.young.dismiss/2
    en  Right! Dull. Says the person who's never been up the tower.
    >>  ............................................
    pt  Certo! Chato. Diz quem nunca subiu a torre.
    >>  ............................................
  playful.dialogue.conversations.village.young.dismiss/3
    en  ...Ha. Come with me tomorrow and then say that.
    >>  ............................................
    pt  ...Ha. Venha comigo amanhã e aí você diz isso.
    >>  ............................................
  relaxed.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull. It's slow, which isn't the same thing.
    >>  ............................................
    pt  NÃO é chato. É lento, que não é a mesma coisa.
    >>  ............................................
  relaxed.dialogue.conversations.village.young.dismiss/2
    en  ...You'll see it differently after a winter here.
    >>  ............................................
    pt  ...Você vai ver diferente depois de um inverno aqui.
    >>  ............................................
  relaxed.dialogue.conversations.village.young.dismiss/3
    en  Right you are. Give it time and ask me again.
    >>  ............................................
    pt  Você tem razão. Dê tempo e me pergunte de novo.
    >>  ............................................
  sensitive.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull. It's mine, %1$s.
    >>  ............................................
    pt  NÃO é chato. É meu, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.village.young.dismiss/2
    en  ...Don't say that about it. It's where I'm from.
    >>  ............................................
    pt  ...Não fale assim dele. É de onde eu sou.
    >>  ............................................
  sensitive.dialogue.conversations.village.young.dismiss/3
    en  ...Fine. I won't show you the rest of it, then.
    >>  ............................................
    pt  ...Tudo bem. Então não te mostro o resto.
    >>  ............................................
  shy.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull.
    >>  ............................................
    pt  NÃO é chato.
    >>  ............................................
  shy.dialogue.conversations.village.young.dismiss/2
    en  ...You just haven't looked.
    >>  ............................................
    pt  ...Você é que não olhou.
    >>  ............................................
  shy.dialogue.conversations.village.young.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  upbeat.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull! There's a whole thing behind the mill you don't know about.
    >>  ............................................
    pt  NÃO é chato! Tem uma coisa inteira atrás do moinho que você não sabe.
    >>  ............................................
  upbeat.dialogue.conversations.village.young.dismiss/2
    en  Right! Dull. Says the person who's never been up the tower.
    >>  ............................................
    pt  Certo! Chato. Diz quem nunca subiu a torre.
    >>  ............................................
  upbeat.dialogue.conversations.village.young.dismiss/3
    en  ...Ha. Come with me tomorrow and then say that.
    >>  ............................................
    pt  ...Ha. Venha comigo amanhã e aí você diz isso.
    >>  ............................................
  witty.dialogue.conversations.village.young.dismiss/1
    en  It's NOT dull! There's a whole thing behind the mill you don't know about.
    >>  ............................................
    pt  NÃO é chato! Tem uma coisa inteira atrás do moinho que você não sabe.
    >>  ............................................
  witty.dialogue.conversations.village.young.dismiss/2
    en  Right! Dull. Says the person who's never been up the tower.
    >>  ............................................
    pt  Certo! Chato. Diz quem nunca subiu a torre.
    >>  ............................................
  witty.dialogue.conversations.village.young.dismiss/3
    en  ...Ha. Come with me tomorrow and then say that.
    >>  ............................................
    pt  ...Ha. Venha comigo amanhã e aí você diz isso.
    >>  ............................................
```

</details>


### Button `leave` — "Off you go."

*stance family `exit` · tone `plain` · answers the beat(s) `village.child.to.village.young`, `village.teen.to.village.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.village.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.village.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.village.young.respond.leave   [11 chars]
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
POOL   dialogue key: dialogue.conversations.village.young.leave
WHO    VILLAGER — what the player reads after pressing "Off you go."
       spoken on: conversations.topic.village.young.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.young.leave.terminal`: the villager accepts. Subject `village.young`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.village.young.leave/1   [4 chars]
    en  Bye!
    >>  ............................................
    pt  Tchau!
    >>  ............................................
  dialogue.conversations.village.young.leave/2   [20 chars]
    en  See you round, %1$s!
    >>  ............................................
    pt  Até mais, %1$s!
    >>  ............................................
  dialogue.conversations.village.young.leave/3   [9 chars]
    en  Okay bye!
    >>  ............................................
    pt  Tá, tchau!
    >>  ............................................
```

---

